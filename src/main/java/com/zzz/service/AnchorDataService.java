package com.zzz.service;

import com.zzz.model.Anchor;
import com.zzz.model.HistoryData;
import com.zzz.model.OnlineData;

import java.util.Date;
import java.util.List;

public interface AnchorDataService {

    OnlineData getAnchorOnlineData(int anchorId);

    HistoryData getAnchorHistoryData(int anchorId, Date date);

    List<HistoryData> getAnchorHistoryData(int anchorId, Date begin, Date end, int page, int pageSize);

    Integer getHistoryDataNum(int anchorId, Date begin, Date end);
}
