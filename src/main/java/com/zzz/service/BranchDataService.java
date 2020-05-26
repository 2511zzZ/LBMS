package com.zzz.service;

import com.zzz.model.HistoryDatas.BranchHistoryData;
import com.zzz.model.OnlineDatas.BranchOnlineData;
import com.zzz.model.OnlineDatas.GroupOnlineData;

import java.util.Date;
import java.util.List;

public interface BranchDataService {

    List<BranchOnlineData> getBranchOnlineData(int branchId, Date datetime);

    BranchHistoryData getBranchHistoryData(int branchId, Date date);

    List<BranchHistoryData> getBranchHistoryData(int branchId, Date begin, Date end, int page, int pageSize);

    Integer getHistoryDataNum(int branchId, Date begin, Date end);

    BranchOnlineData calBranchRealtimeDataFromGroups(int branchId, Date datetime);

    List<GroupOnlineData> getGroupOnlineRank(int levelId);
}
