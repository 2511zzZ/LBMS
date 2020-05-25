package com.zzz.dao;

import com.zzz.model.AnchorAlarm;
import com.zzz.model.AnchorAlarmTrans;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AlarmDao {
    Integer getThreshold();

    Integer getMaxTipNum();

    Integer getSumTipNum(int anchorId, Date timeNow, int threshold);

    void setThresholdAndMaxNum(int newThreshold, int maxTipNum);

    void insertAlarm(AnchorAlarm anchorAlarm);

    Integer getAnchorIdByAlarm(String alarmId);

    void processAlarm(String alarmId, Integer operation, Date endTime, Integer employeeId, String employeeName, Integer role);

    List<AnchorAlarmTrans> getAlarms(Integer employeeId);

    void insertAlarmTrans(AnchorAlarmTrans anchorAlarmTrans);

    AnchorAlarm getAlarmById(String alarmId);

    Integer getWaitingNum(int employeeId);

    List<AnchorAlarm> getAlarmsOverview(Integer employeeId, Date date);

    void deleteAlert(String alarmId);

    List<AnchorAlarmTrans> getUndoAlarms();
}
