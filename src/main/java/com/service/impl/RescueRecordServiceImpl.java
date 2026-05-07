package com.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.RescueRecordDao;
import com.entity.RescueRecordEntity;
import com.service.RescueRecordService;
import org.springframework.stereotype.Service;

@Service
public class RescueRecordServiceImpl extends ServiceImpl<RescueRecordDao, RescueRecordEntity> implements RescueRecordService {
}
