package com.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.AdoptionApplicationDao;
import com.entity.AdoptionApplicationEntity;
import com.service.AdoptionApplicationService;
import org.springframework.stereotype.Service;

@Service
public class AdoptionApplicationServiceImpl extends ServiceImpl<AdoptionApplicationDao, AdoptionApplicationEntity> implements AdoptionApplicationService {
}
