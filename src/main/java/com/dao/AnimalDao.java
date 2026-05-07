package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.AnimalEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnimalDao extends BaseMapper<AnimalEntity> {
}
