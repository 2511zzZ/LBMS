package com.zzz.service;

import com.zzz.model.Anchor;
import com.zzz.model.HistoryData;
import com.zzz.model.OnlineData;

import java.util.Date;
import java.util.List;

public interface AnchorService {
    List<Anchor> getAnchors(int page, int pageSize);

    Integer getTotalNum();

    List<Anchor> getOnlineAnchors(int page, int pageSize);

    Integer getOnlineTotalNum();

    Anchor getAnchor(int anchorId);

    void banAnchor(int anchorId, Date begin, Date end, String reason);
}
