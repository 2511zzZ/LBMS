package com.zzz.model;

import lombok.Data;

import java.util.Date;

@Data
public class AnchorAlarmTrans {

    private String alarmId;
    private Integer employeeId;
    private String username;
    private Integer role;
    private Integer isTimeout;
    private Integer isDelete;
    private Date time;

    private AnchorAlarm alarm;

    public AnchorAlarmTrans(){}

    public AnchorAlarmTrans(String alarmId, Integer employeeId, String username, Integer role, Integer isTimeout, Integer isDelete, Date time) {
        this.alarmId = alarmId;
        this.employeeId = employeeId;
        this.username = username;
        this.role = role;
        this.isTimeout = isTimeout;
        this.isDelete = isDelete;
        this.time = time;
    }

    public void setAlarm(AnchorAlarm alarm) {
        this.alarm = alarm;
    }
}
