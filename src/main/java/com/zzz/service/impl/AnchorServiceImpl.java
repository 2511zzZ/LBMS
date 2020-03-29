package com.zzz.service.impl;

import com.zzz.dao.AnchorDao;
import com.zzz.dao.SysUserDao;
import com.zzz.model.Anchor;
import com.zzz.model.HistoryData;
import com.zzz.model.OnlineData;
import com.zzz.model.SysUserDetails;
import com.zzz.service.AnchorService;
import com.zzz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AnchorServiceImpl implements AnchorService {

    @Autowired
    AnchorDao anchorDao;

    @Override
    public List<Anchor> getAnchors(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return anchorDao.getAnchorWithPagination(offset, pageSize);
    }

    @Override
    public Integer getTotalNum() {
        return anchorDao.getTotalNum();
    }

    @Override
    public List<Anchor> getOnlineAnchors(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return anchorDao.getOnlineAnchorWithPagination(offset, pageSize);
    }

    @Override
    public Integer getOnlineTotalNum() {
        return anchorDao.getOnlineTotalNum();
    }

    @Override
    public Anchor getAnchor(int anchorId) {
        return anchorDao.getAnchorById(anchorId);
    }
}
