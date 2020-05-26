package com.zzz.service;

import com.zzz.model.HistoryDatas.GroupHistoryData;
import com.zzz.model.OnlineDatas.GroupOnlineData;
import com.zzz.model.OnlineDatas.TeamOnlineData;

import java.util.Date;
import java.util.List;

public interface GroupDataService {

    List<GroupOnlineData> getGroupOnlineData(int groupId, Date datetime);

    GroupHistoryData getGroupHistoryData(int groupId, Date date);

    List<GroupHistoryData> getGroupHistoryData(int groupId, Date begin, Date end, int page, int pageSize);

    Integer getHistoryDataNum(int groupId, Date begin, Date end);

    GroupOnlineData calGroupRealtimeDataFromTeams(int groupId, Date datetime);

    List<TeamOnlineData> getTeamOnlineRank(int levelId);
}
