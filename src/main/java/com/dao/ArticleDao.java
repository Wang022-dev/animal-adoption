package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleDao extends BaseMapper<ArticleEntity> {
}
