package com.controller;

import com.entity.SysUserEntity;
import com.service.AdoptionApplicationService;
import com.service.AnimalService;
import com.service.DashboardService;
import com.service.RescueRecordService;
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
@RequestMapping("/admin")
public class AdminPageController {

    @Resource
    private DashboardService dashboardService;
    @Resource
    private AnimalService animalService;
    @Resource
    private RescueRecordService rescueRecordService;
    @Resource
    private AdoptionApplicationService adoptionApplicationService;
    @Resource
    private SysUserService sysUserService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "admin-dashboard";
    }

    @GetMapping("/animals")
    public String animals(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "admin-animals";
    }

    @GetMapping("/rescues")
    public String rescues(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "admin-rescues";
    }

    @GetMapping("/applications")
    public String applications(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "admin-applications";
    }

    @GetMapping("/members")
    public String members(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "admin-members";
    }

    @GetMapping("/analytics")
    public String analytics(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isAdmin(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "admin-analytics";
    }

    private void fillCommonModel(Model model, SysUserEntity user) {
        model.addAttribute("loginUser", user);
        model.addAttribute("animalStats", dashboardService.roleAnimalStats());
        model.addAttribute("animals", animalService.selectList(null));
        model.addAttribute("rescues", dashboardService.pendingRescueList());
        model.addAttribute("applications", dashboardService.adoptionApplications());
        model.addAttribute("members", sysUserService.selectList(null));
        model.addAttribute("rescueRecords", rescueRecordService.selectList(null));
    }
}
