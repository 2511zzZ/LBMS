package com.zzz.model.OnlineDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BranchOnlineData extends OnlineData {
    private Integer branchId;

    public BranchOnlineData(){}

    public BranchOnlineData(int branchId, int watchNum, int gift, int bulletScreen, Date time){
        super(watchNum, gift, bulletScreen, time);
        this.branchId = branchId;
    }
}
