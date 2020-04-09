package com.zzz.service;

import com.zzz.model.HistoryDatas.AnchorHistoryData;
import com.zzz.model.OnlineDatas.AnchorOnlineData;

import java.util.Date;
import java.util.List;

public interface AnchorDataService {

    AnchorOnlineData getAnchorOnlineData(int anchorId);

    AnchorHistoryData getAnchorHistoryData(int anchorId, Date date);

    List<AnchorHistoryData> getAnchorHistoryData(int anchorId, Date begin, Date end, int page, int pageSize);

    Integer getHistoryDataNum(int anchorId, Date begin, Date end);
}
