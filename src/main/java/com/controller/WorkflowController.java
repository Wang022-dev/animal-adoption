package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.AdoptionApplicationEntity;
import com.entity.AdoptionFollowupEntity;
import com.entity.AnimalEntity;
import com.entity.AnimalStatusLogEntity;
import com.entity.RescueRecordEntity;
import com.entity.SysUserEntity;
import com.service.AdoptionApplicationService;
import com.service.AdoptionFollowupService;
import com.service.AnimalService;
import com.service.AnimalStatusLogService;
import com.service.RescueRecordService;
import com.utils.RoleHelper;
import com.utils.SessionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;

@Controller
public class WorkflowController {

    @Resource
    private AnimalService animalService;
    @Resource
    private RescueRecordService rescueRecordService;
    @Resource
    private AdoptionApplicationService adoptionApplicationService;
    @Resource
    private AdoptionFollowupService adoptionFollowupService;
    @Resource
    private AnimalStatusLogService animalStatusLogService;

    @PostMapping("/volunteer/rescue/create")
    public String createRescue(@RequestParam String name,
                               @RequestParam String category,
                               @RequestParam String breed,
                               @RequestParam String ageDesc,
                               @RequestParam String gender,
                               @RequestParam(required = false) BigDecimal weight,
                               @RequestParam String foundLocation,
                               @RequestParam String currentLocation,
                               @RequestParam String emergencyLevel,
                               @RequestParam String healthStatus,
                               @RequestParam String personality,
                               @RequestParam String adoptRequirements,
                               @RequestParam String rescueDesc,
                               HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isVolunteer(user)) {
            return "redirect:/login";
        }
        AnimalEntity animal = new AnimalEntity();
        animal.setAnimalNo("AN" + System.currentTimeMillis());
        animal.setName(name);
        animal.setCategory(category);
        animal.setBreed(breed);
        animal.setAgeDesc(ageDesc);
        animal.setGender(gender);
        animal.setWeight(weight);
        animal.setFoundLocation(foundLocation);
        animal.setFoundTime(new Date());
        animal.setCurrentLocation(currentLocation);
        animal.setEmergencyLevel(emergencyLevel);
        animal.setHealthStatus(healthStatus);
        animal.setPersonality(personality);
        animal.setAdoptRequirements(adoptRequirements);
        animal.setRescueDesc(rescueDesc);
        animal.setStatus("RESCUED_PENDING_REVIEW");
        animal.setCreatedBy(user.getId());
        animalService.insert(animal);
        AnimalEntity persistedAnimal = animalService.selectOne(new EntityWrapper<AnimalEntity>()
                .eq("animal_no", animal.getAnimalNo()));
        if (persistedAnimal != null) {
            animal = persistedAnimal;
        }

        RescueRecordEntity rescue = new RescueRecordEntity();
        rescue.setAnimalId(animal.getId());
        rescue.setVolunteerUserId(user.getId());
        rescue.setFoundLocation(foundLocation);
        rescue.setFoundTime(new Date());
        rescue.setRescueTime(new Date());
        rescue.setEmergencyLevel(emergencyLevel);
        rescue.setInitialHealthDesc(healthStatus);
        rescue.setRescueDesc(rescueDesc);
        rescue.setAuditStatus("PENDING");
        rescue.setCreateTime(new Date());
        rescue.setUpdateTime(new Date());
        rescueRecordService.insertAllColumn(rescue);

        logStatus(animal.getId(), null, "RESCUED_PENDING_REVIEW", "志愿者提交救助记录", user.getId());
        return "redirect:/volunteer/dashboard";
    }

    @PostMapping("/admin/rescue/approve")
    public String approveRescue(@RequestParam Long rescueId,
                                @RequestParam String auditRemark,
                                HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        RescueRecordEntity rescue = rescueRecordService.selectById(rescueId);
        if (rescue != null) {
            rescue.setAuditStatus("APPROVED");
            rescue.setAuditBy(user.getId());
            rescue.setAuditRemark(auditRemark);
            rescue.setAuditTime(new Date());
            rescueRecordService.updateById(rescue);

            AnimalEntity animal = animalService.selectById(rescue.getAnimalId());
            String from = animal.getStatus();
            animal.setStatus("UNDER_RESCUE");
            animalService.updateById(animal);
            logStatus(animal.getId(), from, "UNDER_RESCUE", "管理员审核通过救助记录", user.getId());
        }
        return "redirect:/admin/rescues";
    }

    @PostMapping("/admin/rescue/reject")
    public String rejectRescue(@RequestParam Long rescueId,
                               @RequestParam String auditRemark,
                               HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        RescueRecordEntity rescue = rescueRecordService.selectById(rescueId);
        if (rescue != null) {
            rescue.setAuditStatus("REJECTED");
            rescue.setAuditBy(user.getId());
            rescue.setAuditRemark(auditRemark);
            rescue.setAuditTime(new Date());
            rescueRecordService.updateById(rescue);

            AnimalEntity animal = animalService.selectById(rescue.getAnimalId());
            if (animal != null) {
                String from = animal.getStatus();
                animal.setStatus("RESCUE_REJECTED");
                animalService.updateById(animal);
                logStatus(animal.getId(), from, "RESCUE_REJECTED", "管理员驳回救助记录", user.getId());
            }
        }
        return "redirect:/admin/rescues";
    }

    @PostMapping("/admin/animal/open-adoption")
    public String openAdoption(@RequestParam Long animalId, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        AnimalEntity animal = animalService.selectById(animalId);
        if (animal != null) {
            String from = animal.getStatus();
            animal.setStatus("OPEN_FOR_ADOPTION");
            animalService.updateById(animal);
            logStatus(animal.getId(), from, "OPEN_FOR_ADOPTION", "管理员开放领养", user.getId());
        }
        return "redirect:/admin/animals";
    }

    @PostMapping("/user/adoption/apply")
    public String applyAdoption(@RequestParam Long animalId,
                                @RequestParam String applyReason,
                                @RequestParam String housingInfo,
                                @RequestParam String familyInfo,
                                @RequestParam String petExperience,
                                @RequestParam String workStability,
                                @RequestParam String contactTime,
                                @RequestParam String acceptVisit,
                                HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isUser(user)) {
            return "redirect:/login";
        }
        AdoptionApplicationEntity existing = adoptionApplicationService.selectOne(new EntityWrapper<AdoptionApplicationEntity>()
                .eq("animal_id", animalId)
                .eq("applicant_user_id", user.getId()));
        if (existing == null) {
            AdoptionApplicationEntity application = new AdoptionApplicationEntity();
            application.setAnimalId(animalId);
            application.setApplicantUserId(user.getId());
            application.setApplyReason(applyReason + "；接受回访：" + acceptVisit + "；方便联系时段：" + contactTime);
            application.setHousingInfo(housingInfo);
            application.setFamilyInfo(familyInfo + "；工作稳定性：" + workStability);
            application.setPetExperience(petExperience);
            application.setStatus("PENDING");
            adoptionApplicationService.insert(application);

            AnimalEntity animal = animalService.selectById(animalId);
            String from = animal.getStatus();
            animal.setStatus("APPLICATION_REVIEW");
            animalService.updateById(animal);
            logStatus(animal.getId(), from, "APPLICATION_REVIEW", "用户提交领养申请", user.getId());
        } else if ("SUPPLEMENT".equals(existing.getStatus())) {
            existing.setApplyReason(applyReason + "；接受回访：" + acceptVisit + "；方便联系时段：" + contactTime);
            existing.setHousingInfo(housingInfo);
            existing.setFamilyInfo(familyInfo + "；工作稳定性：" + workStability);
            existing.setPetExperience(petExperience);
            existing.setStatus("PENDING");
            existing.setAuditBy(null);
            existing.setAuditRemark(null);
            existing.setAuditTime(null);
            adoptionApplicationService.updateById(existing);

            AnimalEntity animal = animalService.selectById(animalId);
            String from = animal.getStatus();
            animal.setStatus("APPLICATION_REVIEW");
            animalService.updateById(animal);
            logStatus(animal.getId(), from, "APPLICATION_REVIEW", "用户补充后重新提交领养申请", user.getId());
        }
        return "redirect:/user/applications";
    }

    @PostMapping("/admin/adoption/approve")
    public String approveAdoption(@RequestParam Long applicationId,
                                  @RequestParam String auditRemark,
                                  HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        AdoptionApplicationEntity application = adoptionApplicationService.selectById(applicationId);
        if (application != null) {
            application.setStatus("APPROVED");
            application.setAuditBy(user.getId());
            application.setAuditRemark(auditRemark);
            application.setAuditTime(new Date());
            adoptionApplicationService.updateById(application);

            AnimalEntity animal = animalService.selectById(application.getAnimalId());
            String from = animal.getStatus();
            animal.setStatus("FOLLOWUP_PENDING");
            animalService.updateById(animal);
            logStatus(animal.getId(), from, "FOLLOWUP_PENDING", "管理员审核通过领养申请，进入待回访阶段", user.getId());
        }
        return "redirect:/admin/applications";
    }

    @PostMapping("/admin/adoption/reject")
    public String rejectAdoption(@RequestParam Long applicationId,
                                 @RequestParam String auditRemark,
                                 HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        AdoptionApplicationEntity application = adoptionApplicationService.selectById(applicationId);
        if (application != null) {
            application.setStatus("REJECTED");
            application.setAuditBy(user.getId());
            application.setAuditRemark(auditRemark);
            application.setAuditTime(new Date());
            adoptionApplicationService.updateById(application);

            AnimalEntity animal = animalService.selectById(application.getAnimalId());
            if (animal != null) {
                String from = animal.getStatus();
                animal.setStatus("OPEN_FOR_ADOPTION");
                animalService.updateById(animal);
                logStatus(animal.getId(), from, "OPEN_FOR_ADOPTION", "管理员驳回领养申请，重新开放领养", user.getId());
            }
        }
        return "redirect:/admin/applications";
    }

    @PostMapping("/admin/adoption/supplement")
    public String supplementAdoption(@RequestParam Long applicationId,
                                     @RequestParam String auditRemark,
                                     HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        AdoptionApplicationEntity application = adoptionApplicationService.selectById(applicationId);
        if (application != null) {
            application.setStatus("SUPPLEMENT");
            application.setAuditBy(user.getId());
            application.setAuditRemark(auditRemark);
            application.setAuditTime(new Date());
            adoptionApplicationService.updateById(application);

            AnimalEntity animal = animalService.selectById(application.getAnimalId());
            if (animal != null) {
                String from = animal.getStatus();
                animal.setStatus("APPLICATION_REVIEW");
                animalService.updateById(animal);
                logStatus(animal.getId(), from, "APPLICATION_REVIEW", "管理员要求补充领养资料", user.getId());
            }
        }
        return "redirect:/admin/applications";
    }

    @PostMapping("/volunteer/followup/create")
    public String createFollowup(@RequestParam Long animalId,
                                 @RequestParam Long adopterUserId,
                                 @RequestParam String followupResult,
                                 @RequestParam String followupContent,
                                 HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isVolunteer(user)) {
            return "redirect:/login";
        }
        AdoptionApplicationEntity approvedApplication = adoptionApplicationService.selectOne(
                new EntityWrapper<AdoptionApplicationEntity>()
                        .eq("animal_id", animalId)
                        .eq("applicant_user_id", adopterUserId)
                        .eq("status", "APPROVED")
        );
        if (approvedApplication == null) {
            return "redirect:/volunteer/followups?invalidPair=1";
        }
        AdoptionFollowupEntity followup = new AdoptionFollowupEntity();
        followup.setAnimalId(animalId);
        followup.setAdopterUserId(adopterUserId);
        followup.setVolunteerUserId(user.getId());
        followup.setFollowupTime(new Date());
        followup.setFollowupResult(followupResult);
        followup.setFollowupContent(followupContent);
        adoptionFollowupService.insert(followup);

        AnimalEntity animal = animalService.selectById(animalId);
        if (animal != null) {
            String from = animal.getStatus();
            String nextStatus;
            if ("NORMAL".equals(followupResult)) {
                nextStatus = "ADOPTION_COMPLETED";
            } else if ("RETURNED".equals(followupResult)) {
                nextStatus = "RETURNED_TO_CENTER";
            } else {
                nextStatus = "FOLLOWUP_EXCEPTION";
            }
            animal.setStatus(nextStatus);
            animalService.updateById(animal);
            logStatus(animal.getId(), from, nextStatus, "志愿者完成回访记录", user.getId());
        }
        return "redirect:/volunteer/followups";
    }

    private void logStatus(Long animalId, String from, String to, String remark, Long userId) {
        AnimalStatusLogEntity log = new AnimalStatusLogEntity();
        log.setAnimalId(animalId);
        log.setFromStatus(from);
        log.setToStatus(to);
        log.setRemark(remark);
        log.setOperatorUserId(userId);
        animalStatusLogService.insert(log);
    }
}


