package com.zzz.model.HistoryDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AnchorHistoryData extends HistoryData {

    private Integer anchorId;

    public AnchorHistoryData(){}

    public AnchorHistoryData(int anchorId, int watchNum, int gift, int bulletScreen, int maxWatchNum, Date date){
        super(watchNum, gift, bulletScreen, maxWatchNum, date);
        this.anchorId = anchorId;
    }
}
