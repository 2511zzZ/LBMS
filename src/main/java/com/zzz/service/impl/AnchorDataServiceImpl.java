package com.zzz.service.impl;

import com.zzz.dao.AnchorDataDao;
import com.zzz.model.HistoryDatas.AnchorHistoryData;
import com.zzz.model.OnlineDatas.AnchorOnlineData;
import com.zzz.service.AnchorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AnchorDataServiceImpl implements AnchorDataService {

    @Autowired
    AnchorDataDao anchorDataDao;

    @Override
    public AnchorOnlineData getAnchorOnlineData(int anchorId) {
        return anchorDataDao.getAnchorOnlineData(anchorId);
    }

    @Override
    public AnchorHistoryData getAnchorHistoryData(int anchorId, Date date) {
        return anchorDataDao.getAnchorHistoryData(anchorId, date);
    }

    @Override
    public List<AnchorHistoryData> getAnchorHistoryData(int anchorId, Date begin, Date end, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return anchorDataDao.getAnchorHistoryDatas(anchorId, begin, end, offset, pageSize);
    }

    @Override
    public Integer getHistoryDataNum(int anchorId, Date begin, Date end) {
        return anchorDataDao.getHistoryDataNum(anchorId, begin, end);
    }

    @Override
    public List<AnchorHistoryData> calAnchorHistoryFromRealtime(Date date) {
        return anchorDataDao.calAnchorHistoryFromRealtime(date);
    }
}
