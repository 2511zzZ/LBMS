package com.zzz.dao;

import com.zzz.model.HistoryDatas.GroupHistoryData;
import com.zzz.model.OnlineDatas.GroupOnlineData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface GroupDataDao {
    List<GroupOnlineData> getGroupOnlineData(int groupId, Date datetime);

    GroupHistoryData getGroupHistoryData(int groupId, Date date);

    List<GroupHistoryData> getGroupHistoryDatas(int groupId, Date begin, Date end, int offset, int limit);

    Integer getHistoryDataNum(int groupId, Date begin, Date end);

    GroupOnlineData calGroupRealtimeDataFromTeams(int groupId, Date datetime);
}
