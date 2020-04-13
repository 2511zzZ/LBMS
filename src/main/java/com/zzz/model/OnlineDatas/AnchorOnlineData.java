package com.zzz.model.OnlineDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AnchorOnlineData extends OnlineData {

    private Integer anchorId;

    public AnchorOnlineData(){}

    public AnchorOnlineData(int anchorId, int watchNum, int gift, int bulletScreen, Date time){
        super(watchNum, gift, bulletScreen, time);
        this.anchorId = anchorId;
    }
}
