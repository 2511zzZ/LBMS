package com.zzz.model.OnlineDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupOnlineData extends OnlineData {
    private Integer groupId;

    public GroupOnlineData(){}

    public GroupOnlineData(int groupId, int watchNum, int gift, int bulletScreen, Date time){
        super(watchNum, gift, bulletScreen, time);
        this.groupId = groupId;
    }
}
