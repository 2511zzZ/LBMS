package com.zzz.dao;

import com.zzz.model.HistoryDatas.AnchorHistoryData;
import com.zzz.model.OnlineDatas.AnchorOnlineData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AnchorDataDao {
    AnchorOnlineData getAnchorOnlineData(int anchorId);

    AnchorHistoryData getAnchorHistoryData(int anchorId, Date date);

    List<AnchorHistoryData> getAnchorHistoryDatas(int anchorId, Date begin, Date end, int offset, int limit);

    Integer getHistoryDataNum(int anchorId, Date begin, Date end);
}
