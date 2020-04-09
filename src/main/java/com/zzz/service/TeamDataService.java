package com.zzz.service;

import com.zzz.model.HistoryDatas.TeamHistoryData;
import com.zzz.model.OnlineDatas.TeamOnlineData;

import java.util.Date;
import java.util.List;

public interface TeamDataService {

    TeamOnlineData getTeamOnlineData(int teamId);

    TeamHistoryData getTeamHistoryData(int teamId, Date date);

    List<TeamHistoryData> getTeamHistoryData(int teamId, Date begin, Date end, int page, int pageSize);

    Integer getHistoryDataNum(int teamId, Date begin, Date end);
}
