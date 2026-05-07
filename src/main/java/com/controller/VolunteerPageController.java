package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.AdoptionFollowupEntity;
import com.entity.AnimalEntity;
import com.entity.SysUserEntity;
import com.service.AdoptionFollowupService;
import com.service.AnimalService;
import com.service.DashboardService;
import com.service.SysUserService;
import com.utils.RoleHelper;
import com.utils.SessionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/volunteer")
public class VolunteerPageController {

    @Resource
    private DashboardService dashboardService;
    @Resource
    private AdoptionFollowupService adoptionFollowupService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private AnimalService animalService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isVolunteer(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "volunteer-dashboard";
    }

    @GetMapping("/rescues")
    public String rescues(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isVolunteer(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "volunteer-rescues";
    }

    @GetMapping("/care")
    public String care(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isVolunteer(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "volunteer-care";
    }

    @GetMapping("/followups")
    public String followups(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isVolunteer(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "volunteer-followups";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isVolunteer(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        model.addAttribute("profileUser", sysUserService.selectById(user.getId()));
        return "volunteer-profile";
    }

    private void fillCommonModel(Model model, SysUserEntity user) {
        model.addAttribute("loginUser", user);
        model.addAttribute("tasks", dashboardService.volunteerTasks(user.getId()));
        model.addAttribute("followups", adoptionFollowupService.selectList(
                new EntityWrapper<AdoptionFollowupEntity>().eq("volunteer_user_id", user.getId())
        ));
        model.addAttribute("users", sysUserService.selectList(null));
        model.addAttribute("availableAnimals", animalService.selectList(
                new EntityWrapper<AnimalEntity>().orderBy("create_time", false)
        ));
        model.addAttribute("followupCandidates", dashboardService.adoptionApplications());
    }
}
