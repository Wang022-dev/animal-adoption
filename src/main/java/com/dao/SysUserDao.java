package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
}
