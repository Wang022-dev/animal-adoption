package com.service.impl;

import com.dao.DashboardDao;
import com.service.DashboardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Resource
    private DashboardDao dashboardDao;

    @Override
    public List<Map<String, Object>> roleAnimalStats() {
        return dashboardDao.roleAnimalStats();
    }

    @Override
    public List<Map<String, Object>> latestOpenAnimals() {
        return dashboardDao.latestOpenAnimals();
    }

    @Override
    public List<Map<String, Object>> pendingRescueList() {
        return dashboardDao.pendingRescueList();
    }

    @Override
    public List<Map<String, Object>> adoptionApplications() {
        return dashboardDao.adoptionApplications();
    }

    @Override
    public List<Map<String, Object>> volunteerTasks(Long userId) {
        return dashboardDao.volunteerTasks(userId);
    }

    @Override
    public List<Map<String, Object>> myApplications(Long userId) {
        return dashboardDao.myApplications(userId);
    }
}
