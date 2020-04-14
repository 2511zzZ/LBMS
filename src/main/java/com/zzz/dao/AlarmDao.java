package com.zzz.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface AlarmDao {
    int getThreshold();

    int getMaxTipNum();

    int getSumTipNum(int anchorId, Date timeNow, int threshold);

    void setThresholdAndMaxNum(int newThreshold, int maxTipNum);
}
