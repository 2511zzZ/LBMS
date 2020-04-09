package com.zzz.service.impl;

import com.zzz.dao.GroupDataDao;
import com.zzz.model.HistoryDatas.GroupHistoryData;
import com.zzz.model.OnlineDatas.GroupOnlineData;
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
    public GroupOnlineData getGroupOnlineData(int groupId) {
        return groupDataDao.getGroupOnlineData(groupId);
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
}
