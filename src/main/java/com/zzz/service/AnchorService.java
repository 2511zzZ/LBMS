package com.zzz.service;

import com.zzz.model.Anchor;

import java.util.List;

public interface AnchorService {
    List<Anchor> getAnchors(int page, int pageSize);

    Integer getTotalNum();

    List<Anchor> getOnlineAnchors(int page, int pageSize);

    Integer getOnlineTotalNum();
}
