package com.zzz.service.impl;

import com.zzz.dao.TotalDataDao;
import com.zzz.model.HistoryDatas.BranchHistoryData;
import com.zzz.model.HistoryDatas.TotalHistoryData;
import com.zzz.model.OnlineDatas.BranchOnlineData;
import com.zzz.model.OnlineDatas.TotalOnlineData;
import com.zzz.service.TotalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TotalDataServiceImpl implements TotalDataService {

    @Autowired
    TotalDataDao totalDataDao;


    @Override
    public List<TotalOnlineData> getTotalOnlineData(int totalId, Date datetime) {
        return totalDataDao.getTotalOnlineData(totalId, datetime);
    }

    @Override
    public TotalHistoryData getTotalHistoryData(int totalId, Date date) {
        return totalDataDao.getTotalHistoryData(totalId, date);
    }

    @Override
    public List<TotalHistoryData> getTotalHistoryData(int totalId, Date begin, Date end, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return totalDataDao.getTotalHistoryDatas(totalId, begin, end, offset, pageSize);
    }

    @Override
    public Integer getHistoryDataNum(int totalId, Date begin, Date end) {
        return totalDataDao.getHistoryDataNum(totalId, begin, end);
    }

    @Override
    public TotalOnlineData calTotalRealtimeDataFromBranchs(int totalId, Date datetime) {
        return totalDataDao.calTotalRealtimeDataFromBranchs(totalId, datetime);
    }

    @Override
    public Integer getSumWatch() {
        return totalDataDao.getSumWatch();
    }

    @Override
    public TotalOnlineData getLastOnlineData() {
        return totalDataDao.getLastOnlineData();
    }

    @Override
    public List<BranchOnlineData> getBranchOnlineRank(Integer totalId) {
        return totalDataDao.getBranchOnlineRank(totalId);
    }

    @Override
    public List<BranchHistoryData> getBranchHistoryRank(Date begin, Date end, int levelId) {
        return totalDataDao.getBranchHistoryRank(begin, end, levelId);
    }

    @Override
    public TotalHistoryData calHistory(int totalId, Date datetime) {
        return totalDataDao.calHistory(totalId, datetime);
    }
}
