package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.AnimalStatusLogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnimalStatusLogDao extends BaseMapper<AnimalStatusLogEntity> {
}
