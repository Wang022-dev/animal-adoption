package com.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.AnimalStatusLogDao;
import com.entity.AnimalStatusLogEntity;
import com.service.AnimalStatusLogService;
import org.springframework.stereotype.Service;

@Service
public class AnimalStatusLogServiceImpl extends ServiceImpl<AnimalStatusLogDao, AnimalStatusLogEntity> implements AnimalStatusLogService {
}
