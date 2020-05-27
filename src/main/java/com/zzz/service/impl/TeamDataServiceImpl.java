package com.zzz.service.impl;

import com.zzz.dao.TeamDataDao;
import com.zzz.model.HistoryDatas.AnchorHistoryData;
import com.zzz.model.HistoryDatas.TeamHistoryData;
import com.zzz.model.OnlineDatas.AnchorOnlineData;
import com.zzz.model.OnlineDatas.TeamOnlineData;
import com.zzz.service.TeamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TeamDataServiceImpl implements TeamDataService {

    @Autowired
    TeamDataDao teamDataDao;


    @Override
    public List<TeamOnlineData> getTeamOnlineData(int teamId, Date datetime) {
        return teamDataDao.getTeamOnlineData(teamId, datetime);
    }

    @Override
    public TeamHistoryData getTeamHistoryData(int teamId, Date date) {
        return teamDataDao.getTeamHistoryData(teamId, date);
    }

    @Override
    public List<TeamHistoryData> getTeamHistoryData(int teamId, Date begin, Date end, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return teamDataDao.getTeamHistoryDatas(teamId, begin, end, offset, pageSize);
    }

    @Override
    public Integer getHistoryDataNum(int teamId, Date begin, Date end) {
        return teamDataDao.getHistoryDataNum(teamId, begin, end);
    }

    @Override
    public TeamOnlineData calTeamRealtimeDataFromAnchors(int teamId, Date datetime) {
        return teamDataDao.calTeamRealtimeDataFromAnchors(teamId, datetime);
    }

    @Override
    public List<AnchorOnlineData> getAnchorOnlineRank(int levelId) {
        return teamDataDao.getAnchorOnlineRank(levelId);
    }

    @Override
    public List<AnchorHistoryData> getAnchorHistoryRank(Date begin, Date end, int levelId) {
        return teamDataDao.getAnchorHistoryRank(begin, end, levelId);
    }
}
