package com.zzz.service;

import com.zzz.model.AnchorAlarm;

import java.util.Date;

public interface AlarmService {
    int getThreshold();

    int getMaxTipNum();

    int getSumTipNum(int anchorId, Date datetime, int threshold);

    void setThresholdAndMaxNum(int newThreshold, int maxTipNum);

    void insertAlarm(AnchorAlarm anchorAlarm);

    Integer getAnchorIdByAlarm(String alarmId);

    void processAlarm(String alarmId, int operation);
}
