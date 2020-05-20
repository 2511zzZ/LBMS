package com.zzz.model;

import lombok.Data;

@Data
public class AlarmOverview {

    private int waitingNum;
    private int doneNum;
    private int avgDealCost;

    public AlarmOverview(){}

    public AlarmOverview(int waitingNum, int doneNum, int avgDealCost) {
        this.waitingNum = waitingNum;
        this.doneNum = doneNum;
        this.avgDealCost = avgDealCost;
    }
}
