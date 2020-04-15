package com.zzz.dao;

import com.zzz.model.AnchorAlarm;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface AlarmDao {
    int getThreshold();

    int getMaxTipNum();

    int getSumTipNum(int anchorId, Date timeNow, int threshold);

    void setThresholdAndMaxNum(int newThreshold, int maxTipNum);

    void insertAlarm(AnchorAlarm anchorAlarm);

    Integer getAnchorIdByAlarm(String alarmId);

    void processAlarm(String alarmId, int operation, Date endTime);
}
