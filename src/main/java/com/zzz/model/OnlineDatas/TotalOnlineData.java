package com.zzz.model.OnlineDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TotalOnlineData extends OnlineData {
    private Integer totalId;

    public TotalOnlineData(){}

    public TotalOnlineData(int totalId, int watchNum, int gift, int bulletScreen, Date time){
        super(watchNum, gift, bulletScreen, time);
        this.totalId = totalId;
    }
}
