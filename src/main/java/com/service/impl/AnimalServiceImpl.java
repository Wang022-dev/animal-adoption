package com.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.AnimalDao;
import com.entity.AnimalEntity;
import com.service.AnimalService;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalDao, AnimalEntity> implements AnimalService {
}
