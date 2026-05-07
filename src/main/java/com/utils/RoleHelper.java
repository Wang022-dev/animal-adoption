package com.utils;

import com.entity.SysUserEntity;

public final class RoleHelper {
    private RoleHelper() {
    }

    public static boolean isAdmin(SysUserEntity user) {
        return user != null && RoleConstants.ADMIN.equals(user.getRoleCode());
    }

    public static boolean isVolunteer(SysUserEntity user) {
        return user != null && RoleConstants.VOLUNTEER.equals(user.getRoleCode());
    }

    public static boolean isUser(SysUserEntity user) {
        return user != null && RoleConstants.USER.equals(user.getRoleCode());
    }
}
