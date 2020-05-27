package com.zzz.dao;

import com.zzz.model.HistoryDatas.BranchHistoryData;
import com.zzz.model.HistoryDatas.GroupHistoryData;
import com.zzz.model.OnlineDatas.BranchOnlineData;
import com.zzz.model.OnlineDatas.GroupOnlineData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface BranchDataDao {
    List<BranchOnlineData> getBranchOnlineData(int branchId, Date datetime);

    BranchHistoryData getBranchHistoryData(int branchId, Date date);

    List<BranchHistoryData> getBranchHistoryDatas(int branchId, Date begin, Date end, int offset, int limit);

    Integer getHistoryDataNum(int branchId, Date begin, Date end);

    BranchOnlineData calBranchRealtimeDataFromGroups(int branchId, Date datetime);

    List<GroupOnlineData> getGroupOnlineRank(int levelId);

    List<GroupHistoryData> getGroupHistoryRank(Date begin, Date end, int branchId);
}
