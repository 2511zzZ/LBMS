package com.zzz.service.impl;

import com.zzz.dao.BranchDataDao;
import com.zzz.model.HistoryDatas.BranchHistoryData;
import com.zzz.model.HistoryDatas.GroupHistoryData;
import com.zzz.model.OnlineDatas.BranchOnlineData;
import com.zzz.model.OnlineDatas.GroupOnlineData;
import com.zzz.service.BranchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BranchDataServiceImpl implements BranchDataService {

    @Autowired
    BranchDataDao branchDataDao;


    @Override
    public List<BranchOnlineData> getBranchOnlineData(int branchId, Date datetime) {
        return branchDataDao.getBranchOnlineData(branchId, datetime);
    }

    @Override
    public BranchHistoryData getBranchHistoryData(int branchId, Date date) {
        return branchDataDao.getBranchHistoryData(branchId, date);
    }

    @Override
    public List<BranchHistoryData> getBranchHistoryData(int branchId, Date begin, Date end, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return branchDataDao.getBranchHistoryDatas(branchId, begin, end, offset, pageSize);
    }

    @Override
    public Integer getHistoryDataNum(int branchId, Date begin, Date end) {
        return branchDataDao.getHistoryDataNum(branchId, begin, end);
    }

    @Override
    public BranchOnlineData calBranchRealtimeDataFromGroups(int branchId, Date datetime) {
        return branchDataDao.calBranchRealtimeDataFromGroups(branchId, datetime);
    }

    @Override
    public List<GroupOnlineData> getGroupOnlineRank(int levelId) {
        return branchDataDao.getGroupOnlineRank(levelId);
    }

    @Override
    public List<GroupHistoryData> getGroupHistoryRank(Date begin, Date end, int branchId) {
        return branchDataDao.getGroupHistoryRank(begin,end,branchId);
    }

    @Override
    public BranchHistoryData calHistory(int branchId, Date datetime) {
        return branchDataDao.calHistory(branchId, datetime);
    }
}
