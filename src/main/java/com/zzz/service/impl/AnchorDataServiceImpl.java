package com.zzz.service.impl;

import com.zzz.dao.AnchorDao;
import com.zzz.dao.AnchorDataDao;
import com.zzz.model.Anchor;
import com.zzz.model.HistoryData;
import com.zzz.model.OnlineData;
import com.zzz.service.AnchorDataService;
import com.zzz.service.AnchorService;
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
    public OnlineData getAnchorOnlineData(int anchorId) {
        return anchorDataDao.getAnchorOnlineData(anchorId);
    }

    @Override
    public HistoryData getAnchorHistoryData(int anchorId, Date date) {
        return anchorDataDao.getAnchorHistoryData(anchorId, date);
    }

    @Override
    public List<HistoryData> getAnchorHistoryData(int anchorId, Date begin, Date end, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return anchorDataDao.getAnchorHistoryDatas(anchorId, begin, end, offset, pageSize);
    }

    @Override
    public Integer getHistoryDataNum(int anchorId, Date begin, Date end) {
        return anchorDataDao.getHistoryDataNum(anchorId, begin, end);
    }
}
