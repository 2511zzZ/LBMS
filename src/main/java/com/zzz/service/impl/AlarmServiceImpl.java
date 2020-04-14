package com.zzz.service.impl;

import com.zzz.dao.AlarmDao;
import com.zzz.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AlarmDao alarmDao;

    @Override
    public int getThreshold() {
        return alarmDao.getThreshold();
    }

    @Override
    public int getMaxTipNum() {
        return alarmDao.getMaxTipNum();
    }

    @Override
    public int getSumTipNum(int anchorId, Date timeNow, int threshold) {
        return alarmDao.getSumTipNum(anchorId, timeNow, threshold);
    }

    @Override
    public void setThresholdAndMaxNum(int newThreshold, int maxTipNum) {
        alarmDao.setThresholdAndMaxNum(newThreshold, maxTipNum);
    }
}
