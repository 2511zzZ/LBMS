package com.zzz.model;

import lombok.Data;

import java.util.List;

@Data
public class AnchorAlarmTransWay {

    private String alarmId;
    private Integer status;
    private Integer finalDealLevel;
    private String finalDealName;
    private List<AnchorAlarmTrans> anchorAlarmTransWay;

    public AnchorAlarmTransWay(String alarmId, Integer status, Integer finalDealLevel, String finalDealName, List<AnchorAlarmTrans> anchorAlarmTransWay) {
        this.alarmId = alarmId;
        this.status = status;
        this.finalDealLevel = finalDealLevel;
        this.finalDealName = finalDealName;
        this.anchorAlarmTransWay = anchorAlarmTransWay;
    }
}
