package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.AdoptionApplicationEntity;
import com.entity.AdoptionFollowupEntity;
import com.entity.AnimalEntity;
import com.entity.ArticleEntity;
import com.entity.SysUserEntity;
import com.service.AdoptionApplicationService;
import com.service.AdoptionFollowupService;
import com.service.AnimalService;
import com.service.ArticleService;
import com.service.DashboardService;
import com.service.SysUserService;
import com.utils.RoleHelper;
import com.utils.SessionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserPageController {

    @Resource
    private AnimalService animalService;
    @Resource
    private ArticleService articleService;
    @Resource
    private DashboardService dashboardService;
    @Resource
    private AdoptionApplicationService adoptionApplicationService;
    @Resource
    private AdoptionFollowupService adoptionFollowupService;
    @Resource
    private SysUserService sysUserService;

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isUser(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "user-home";
    }

    @GetMapping("/animals")
    public String animals(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isUser(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        model.addAttribute("pageTitle", "可领养动物");
        model.addAttribute("pageDesc", "查看全部公开动物档案、健康信息与领养要求");
        return "user-animals";
    }

    @GetMapping("/applications")
    public String applications(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isUser(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "user-applications";
    }

    @GetMapping("/articles")
    public String articles(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isUser(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        return "user-articles";
    }

    @GetMapping("/animal/{id}")
    public String animalDetail(@PathVariable Long id, Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isUser(user)) {
            return "redirect:/login";
        }
        AnimalEntity animal = animalService.selectById(id);
        model.addAttribute("loginUser", user);
        model.addAttribute("animal", animal);
        if (animal == null) {
            model.addAttribute("myApplication", null);
            model.addAttribute("followups", null);
            return "animal-detail";
        }
        AdoptionApplicationEntity application = adoptionApplicationService.selectOne(
                new EntityWrapper<AdoptionApplicationEntity>()
                        .eq("animal_id", id)
                        .eq("applicant_user_id", user.getId())
        );
        List<AdoptionFollowupEntity> followups = adoptionFollowupService.selectList(
                new EntityWrapper<AdoptionFollowupEntity>().eq("animal_id", id)
        );
        model.addAttribute("myApplication", application);
        model.addAttribute("followups", followups);
        return "animal-detail";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        SysUserEntity user = SessionHelper.getLoginUser(session);
        if (!RoleHelper.isUser(user)) {
            return "redirect:/login";
        }
        fillCommonModel(model, user);
        model.addAttribute("loginUser", sysUserService.selectById(user.getId()));
        model.addAttribute("myApplications", dashboardService.myApplications(user.getId()));
        return "user-profile";
    }

    private void fillCommonModel(Model model, SysUserEntity user) {
        model.addAttribute("loginUser", user);
        model.addAttribute("animals", dashboardService.latestOpenAnimals());
        model.addAttribute("allAnimals", animalService.selectList(new EntityWrapper<AnimalEntity>().orderBy("create_time", false)));
        model.addAttribute("articles", articleService.selectList(new EntityWrapper<ArticleEntity>().orderBy("create_time", false)));
        model.addAttribute("applications", dashboardService.myApplications(user.getId()));
    }
}
