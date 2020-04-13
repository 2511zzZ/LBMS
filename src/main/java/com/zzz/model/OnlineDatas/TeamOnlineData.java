package com.zzz.model.OnlineDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeamOnlineData extends OnlineData {
    private Integer teamId;

    public TeamOnlineData(){}

    public TeamOnlineData(int teamId, int watchNum, int gift, int bulletScreen, Date time){
        super(watchNum, gift, bulletScreen, time);
        this.teamId = teamId;
    }
}
