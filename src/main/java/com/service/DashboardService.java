package com.service;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    List<Map<String, Object>> roleAnimalStats();
    List<Map<String, Object>> latestOpenAnimals();
    List<Map<String, Object>> pendingRescueList();
    List<Map<String, Object>> adoptionApplications();
    List<Map<String, Object>> volunteerTasks(Long userId);
    List<Map<String, Object>> myApplications(Long userId);
}
