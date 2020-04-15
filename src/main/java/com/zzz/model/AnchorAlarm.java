package com.zzz.model;

import lombok.Data;

import java.util.Date;

@Data
public class AnchorAlarm{

    private String alarmId;
    private Integer anchorId;
    private Integer teamId;
    private Integer status;
    private Date startTime;
    private Date endTime;

    public AnchorAlarm(){}

    public AnchorAlarm(String alarmId, int anchorId, int teamId, int status, Date startTime, Date endTime){
        this.alarmId = alarmId;
        this.anchorId = anchorId;
        this.teamId = teamId;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
