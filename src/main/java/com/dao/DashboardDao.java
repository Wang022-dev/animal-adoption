package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.AnimalEntity;
import com.entity.RescueRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashboardDao extends BaseMapper<AnimalEntity> {
    List<Map<String, Object>> roleAnimalStats();
    List<Map<String, Object>> latestOpenAnimals();
    List<Map<String, Object>> pendingRescueList();
    List<Map<String, Object>> adoptionApplications();
    List<Map<String, Object>> volunteerTasks(@Param("userId") Long userId);
    List<Map<String, Object>> myApplications(@Param("userId") Long userId);
}
