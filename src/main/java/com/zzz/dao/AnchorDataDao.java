package com.zzz.dao;

import com.zzz.model.Anchor;
import com.zzz.model.HistoryData;
import com.zzz.model.OnlineData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AnchorDataDao {
    OnlineData getAnchorOnlineData(int anchorId);

    HistoryData getAnchorHistoryData(int anchorId, Date date);

    List<HistoryData> getAnchorHistoryDatas(int anchorId, Date begin, Date end, int offset, int limit);

    Integer getHistoryDataNum(int anchorId, Date begin, Date end);
}
