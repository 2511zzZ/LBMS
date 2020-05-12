package com.zzz.dao;

import com.zzz.model.HistoryDatas.TotalHistoryData;
import com.zzz.model.OnlineDatas.TotalOnlineData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface TotalDataDao {
    List<TotalOnlineData> getTotalOnlineData(int totalId, Date datetime);

    TotalHistoryData getTotalHistoryData(int totalId, Date date);

    List<TotalHistoryData> getTotalHistoryDatas(int totalId, Date begin, Date end, int offset, int limit);

    Integer getHistoryDataNum(int totalId, Date begin, Date end);

    TotalOnlineData calTotalRealtimeDataFromBranchs(int totalId, Date datetime);
}
