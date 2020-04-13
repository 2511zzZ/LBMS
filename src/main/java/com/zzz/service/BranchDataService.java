package com.zzz.service;

import com.zzz.model.HistoryDatas.BranchHistoryData;
import com.zzz.model.OnlineDatas.BranchOnlineData;

import java.util.Date;
import java.util.List;

public interface BranchDataService {

    BranchOnlineData getBranchOnlineData(int branchId);

    BranchHistoryData getBranchHistoryData(int branchId, Date date);

    List<BranchHistoryData> getBranchHistoryData(int branchId, Date begin, Date end, int page, int pageSize);

    Integer getHistoryDataNum(int branchId, Date begin, Date end);

    BranchOnlineData calBranchRealtimeDataFromGroups(int branchId, Date datetime);
}
