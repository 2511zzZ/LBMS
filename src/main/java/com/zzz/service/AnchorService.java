package com.zzz.service;

import com.zzz.model.Anchor;
import com.zzz.model.SysUser;

import java.util.Date;
import java.util.List;

public interface AnchorService {
    List<Anchor> getAnchors(int page, int pageSize);

    Integer getTotalNum();

    List<Anchor> getOnlineAnchors(int page, int pageSize);

    Integer getOnlineTotalNum();

    Anchor getAnchor(int anchorId);

    void banAnchor(int anchorId, Date begin, Date end, String reason);

    List<Anchor> recommendAnchors();

    List<Integer> getAnchorIds();

    Integer getOnlineAnchorNum(SysUser user);

    Integer getTeamIdByAnchorId(int anchorId);

    void banAnchor(int anchorId);

    void changeAnchor(int anchorId, int roomId, String nickname);
}
