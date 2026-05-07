package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.SysUserDao;
import com.entity.SysUserEntity;
import com.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Override
    public SysUserEntity login(String username, String password) {
        return this.selectOne(new EntityWrapper<SysUserEntity>()
                .eq("username", username)
                .eq("password", password)
                .eq("status", 1));
    }
}
