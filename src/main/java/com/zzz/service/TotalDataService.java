package com.zzz.service;

import com.zzz.model.HistoryDatas.TotalHistoryData;
import com.zzz.model.OnlineDatas.TotalOnlineData;

import java.util.Date;
import java.util.List;

public interface TotalDataService {

    TotalOnlineData getTotalOnlineData(int totalId);

    TotalHistoryData getTotalHistoryData(int totalId, Date date);

    List<TotalHistoryData> getTotalHistoryData(int totalId, Date begin, Date end, int page, int pageSize);

    Integer getHistoryDataNum(int totalId, Date begin, Date end);

    TotalOnlineData calTotalRealtimeDataFromBranchs(int totalId, Date datetime);
}
