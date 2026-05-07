package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.annotation.IgnoreAuth;
import com.entity.SysUserEntity;
import com.service.SysUserService;
import com.utils.RoleConstants;
import com.utils.SessionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class AuthPageController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping({"/", "/login"})
    public String loginPage(Model model, HttpSession session,
                            @RequestParam(value = "registered", required = false) Integer registered) {
        String captcha = buildCaptcha();
        session.setAttribute("LOGIN_CAPTCHA", captcha);
        model.addAttribute("captchaCode", captcha);
        model.addAttribute("showVisualLanding", true);
        if (registered != null && registered == 1) {
            model.addAttribute("success", "注册成功，请使用新账号登录");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("brandStats", buildBrandStats());
        return "register";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String captcha,
                          Model model,
                          HttpSession session) {
        String expectedCaptcha = (String) session.getAttribute("LOGIN_CAPTCHA");
        if (expectedCaptcha == null || !expectedCaptcha.equalsIgnoreCase(captcha.trim())) {
            model.addAttribute("error", "验证码错误，请重新输入");
            model.addAttribute("captchaCode", buildAndStoreCaptcha(session));
            model.addAttribute("showVisualLanding", true);
            return "login";
        }
        SysUserEntity user = sysUserService.login(username, password);
        if (user == null) {
            model.addAttribute("error", "用户名或密码错误");
            model.addAttribute("captchaCode", buildAndStoreCaptcha(session));
            model.addAttribute("showVisualLanding", true);
            return "login";
        }
        SessionHelper.setLoginUser(session, user);
        if (RoleConstants.ADMIN.equals(user.getRoleCode())) {
            return "redirect:/admin/dashboard";
        }
        if (RoleConstants.VOLUNTEER.equals(user.getRoleCode())) {
            return "redirect:/volunteer/dashboard";
        }
        return "redirect:/user/home";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String realName,
                             @RequestParam String gender,
                             @RequestParam String phone,
                             @RequestParam String email,
                             Model model,
                             HttpSession session) {
        SysUserEntity existing = sysUserService.selectOne(new EntityWrapper<SysUserEntity>().eq("username", username));
        if (existing != null) {
            model.addAttribute("error", "该账号已存在，请更换用户名");
            model.addAttribute("brandStats", buildBrandStats());
            return "register";
        }
        SysUserEntity user = new SysUserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleCode(RoleConstants.USER);
        user.setRealName(realName);
        user.setGender(gender);
        user.setPhone(phone);
        user.setEmail(email);
        user.setStatus(1);
        user.setBio("新注册领养用户");
        boolean inserted = sysUserService.insert(user);
        if (!inserted) {
            model.addAttribute("error", "注册失败，请稍后重试");
            model.addAttribute("brandStats", buildBrandStats());
            return "register";
        }
        model.addAttribute("success", "注册成功，请使用新账号登录");
        model.addAttribute("captchaCode", buildAndStoreCaptcha(session));
        model.addAttribute("showVisualLanding", true);
        return "redirect:/login?registered=1";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        SessionHelper.logout(session);
        return "redirect:/login";
    }

    private String buildAndStoreCaptcha(HttpSession session) {
        String captcha = buildCaptcha();
        session.setAttribute("LOGIN_CAPTCHA", captcha);
        return captcha;
    }

    private String buildCaptcha() {
        String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return builder.toString();
    }

    private List<String> buildBrandStats() {
        return Arrays.asList("365+ 救助档案", "128 只已完成领养", "42 名活跃志愿者");
    }
}
