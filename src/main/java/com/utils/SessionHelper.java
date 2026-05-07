package com.utils;

import com.entity.SysUserEntity;

import javax.servlet.http.HttpSession;

public final class SessionHelper {
    public static final String LOGIN_USER = "LOGIN_USER";

    private SessionHelper() {
    }

    public static void setLoginUser(HttpSession session, SysUserEntity user) {
        session.setAttribute(LOGIN_USER, user);
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRoleCode());
        session.setAttribute("username", user.getUsername());
    }

    public static SysUserEntity getLoginUser(HttpSession session) {
        Object value = session.getAttribute(LOGIN_USER);
        return value instanceof SysUserEntity ? (SysUserEntity) value : null;
    }

    public static void logout(HttpSession session) {
        session.removeAttribute(LOGIN_USER);
        session.removeAttribute("userId");
        session.removeAttribute("role");
        session.removeAttribute("username");
        session.invalidate();
    }
}
