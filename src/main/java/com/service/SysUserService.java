package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.SysUserEntity;

public interface SysUserService extends IService<SysUserEntity> {
    SysUserEntity login(String username, String password);
}
