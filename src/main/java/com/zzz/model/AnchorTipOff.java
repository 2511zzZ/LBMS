package com.zzz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AnchorTipOff {

    private Integer anchorId;
    private Integer tipNum;
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date time = new Date();

    public AnchorTipOff(){}

    public AnchorTipOff(int anchorId, int tipNum, Date time){
        this.anchorId = anchorId;
        this.tipNum = tipNum;
        this.time = time;
    }

}
