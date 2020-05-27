package com.zzz.service.impl;

import com.zzz.dao.GroupDataDao;
import com.zzz.model.HistoryDatas.GroupHistoryData;
import com.zzz.model.HistoryDatas.TeamHistoryData;
import com.zzz.model.OnlineDatas.GroupOnlineData;
import com.zzz.model.OnlineDatas.TeamOnlineData;
import com.zzz.service.GroupDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GroupDataServiceImpl implements GroupDataService {

    @Autowired
    GroupDataDao groupDataDao;


    @Override
    public List<GroupOnlineData> getGroupOnlineData(int groupId, Date datetime) {
        return groupDataDao.getGroupOnlineData(groupId, datetime);
    }

    @Override
    public GroupHistoryData getGroupHistoryData(int groupId, Date date) {
        return groupDataDao.getGroupHistoryData(groupId, date);
    }

    @Override
    public List<GroupHistoryData> getGroupHistoryData(int groupId, Date begin, Date end, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return groupDataDao.getGroupHistoryDatas(groupId, begin, end, offset, pageSize);
    }

    @Override
    public Integer getHistoryDataNum(int groupId, Date begin, Date end) {
        return groupDataDao.getHistoryDataNum(groupId, begin, end);
    }

    @Override
    public GroupOnlineData calGroupRealtimeDataFromTeams(int groupId, Date datetime) {
        return groupDataDao.calGroupRealtimeDataFromTeams(groupId, datetime);
    }

    @Override
    public List<TeamOnlineData> getTeamOnlineRank(int levelId) {
        return groupDataDao.getTeamOnlineRank(levelId);
    }

    @Override
    public List<TeamHistoryData> getTeamHistoryRank(Date begin, Date end, int levelId) {
        return groupDataDao.getTeamHistoryRank(begin, end, levelId);
    }
}
