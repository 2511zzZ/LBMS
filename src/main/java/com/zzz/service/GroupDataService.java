package com.zzz.service;

import com.zzz.model.HistoryDatas.GroupHistoryData;
import com.zzz.model.OnlineDatas.GroupOnlineData;

import java.util.Date;
import java.util.List;

public interface GroupDataService {

    GroupOnlineData getGroupOnlineData(int groupId);

    GroupHistoryData getGroupHistoryData(int groupId, Date date);

    List<GroupHistoryData> getGroupHistoryData(int groupId, Date begin, Date end, int page, int pageSize);

    Integer getHistoryDataNum(int groupId, Date begin, Date end);
}
