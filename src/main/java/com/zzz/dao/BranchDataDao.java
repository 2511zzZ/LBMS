package com.zzz.dao;

import com.zzz.model.HistoryDatas.BranchHistoryData;
import com.zzz.model.OnlineDatas.BranchOnlineData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface BranchDataDao {
    BranchOnlineData getBranchOnlineData(int branchId);

    BranchHistoryData getBranchHistoryData(int branchId, Date date);

    List<BranchHistoryData> getBranchHistoryDatas(int branchId, Date begin, Date end, int offset, int limit);

    Integer getHistoryDataNum(int branchId, Date begin, Date end);

    BranchOnlineData calBranchRealtimeDataFromGroups(int branchId, Date datetime);
}
