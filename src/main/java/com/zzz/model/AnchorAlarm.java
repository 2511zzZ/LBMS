package com.zzz.model;

import lombok.Data;

import java.util.Date;

@Data
public class AnchorAlarm{

    private String alarmId;
    private Integer anchorId;
    private String anchorName;
    private String reason;
    private Integer status;
    private Date startTime;
    private Date endTime;
    private Integer finalDealId;
    private String finalDealName;
    private Integer dealRole;

    public AnchorAlarm(){}

    public AnchorAlarm(String alarmId, Integer anchorId, String anchorName, String reason, Integer status, Date startTime, Date endTime, Integer finalDealId, String finalDealName, Integer dealRole) {
        this.alarmId = alarmId;
        this.anchorId = anchorId;
        this.anchorName = anchorName;
        this.reason = reason;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.finalDealId = finalDealId;
        this.finalDealName = finalDealName;
        this.dealRole = dealRole;
    }
}
