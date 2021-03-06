package com.zzz.service;

import com.zzz.model.AlarmOverview;
import com.zzz.model.AnchorAlarm;
import com.zzz.model.AnchorAlarmTrans;
import com.zzz.model.AnchorAlarmTransWay;

import java.util.Date;
import java.util.List;

public interface AlarmService {
    int getThreshold();

    int getMaxTipNum();

    int getSumTipNum(int anchorId, Date datetime, int threshold);

    void setThresholdAndMaxNum(int newThreshold, int maxTipNum);

    void insertAlarm(AnchorAlarm anchorAlarm);

    Integer getAnchorIdByAlarm(String alarmId);

    void processAlarm(String alarmId, int operation);

    List<AnchorAlarmTrans> getAlarms(int status, int employeeId);

    List<AnchorAlarmTrans> getUndoAlarms();

    void insertAlarmTrans(AnchorAlarmTrans anchorAlarmTrans);

    AlarmOverview getAlarmOverview(Integer employeeId);

    void deleteAlert(String alarmId);

    AnchorAlarmTransWay getAlarmTransWay(String alarmId);

    Integer getAlarmTransSetting();

    void setAlarmTransSetting(int transTime);
}
