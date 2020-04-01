package com.zzz.dao;

import com.zzz.model.Anchor;
import com.zzz.model.HistoryData;
import com.zzz.model.OnlineData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AnchorDao {
    List<Anchor> getAnchorWithPagination(int offset, int limit);

    Integer getTotalNum();

    List<Anchor> getOnlineAnchorWithPagination(int offset, int limit);

    Integer getOnlineTotalNum();

    Anchor getAnchorById(int anchorId);

    void banAnchor(int anchorId, Date begin, Date end, String reason);
}
