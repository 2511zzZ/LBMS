package com.zzz.dao;

import com.zzz.model.HistoryDatas.TeamHistoryData;
import com.zzz.model.OnlineDatas.TeamOnlineData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface TeamDataDao {
    TeamOnlineData getTeamOnlineData(int teamId);

    TeamHistoryData getTeamHistoryData(int teamId, Date date);

    List<TeamHistoryData> getTeamHistoryDatas(int teamId, Date begin, Date end, int offset, int limit);

    Integer getHistoryDataNum(int teamId, Date begin, Date end);
}
