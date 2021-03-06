package com.zzz.dao;

import com.zzz.model.Anchor;
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

    Integer branchHasPermission(int branchId, int anchorId);

    Integer groupHasPermission(int groupId, int anchorId);

    Integer teamHasPermission(int teamId, int anchorId);

    List<Anchor> branchGetAnchors(Integer branchId, int offset, int limit);

    List<Anchor> groupGetAnchors(Integer groupId, int offset, int limit);

    List<Anchor> teamGetAnchors(Integer teamId, int offset, int limit);

    List<Anchor> branchGetOnlineAnchors(Integer branchId, int offset, int limit);

    List<Anchor> groupGetOnlineAnchors(Integer groupId, int offset, int limit);

    List<Anchor> teamGetOnlineAnchors(Integer teamId, int offset, int limit);

    List<Anchor> recommendAnchors();

    List<Integer> getAnchorIds();

    void fakeAnchorOnlineData(int anchorId, int isOnline, Date datetime);

    Integer getOnlineAnchorNum(String levelIdName, Integer levelId);

    Integer getTeamIdByAnchorId(int anchorId);

    void banAnchorByAlarm(int anchorId);

    void changeAnchor(int anchorId, int roomId, String nickname);
}
