package com.zzz.model.OnlineDatas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzz.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class OnlineData extends BaseEntity<Long> {
    private Integer watchNum;
    private Integer gift;
    private Integer bulletScreen;
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date time = new Date();

    public OnlineData(){}

    public OnlineData(int watchNum, int gift, int bulletScreen, Date time){
        this.watchNum = watchNum;
        this.gift = gift;
        this.bulletScreen = bulletScreen;
        this.time = time;
    }
}
