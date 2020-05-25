package com.zzz.service.impl;

import com.zzz.dao.TopCardsDataDao;
import com.zzz.model.SysUser;
import com.zzz.service.LevelService;
import com.zzz.service.TopCardsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TopCardsDataServiceImpl implements TopCardsDataService {

    @Autowired
    TopCardsDataDao onlineDataDao;

    @Autowired
    LevelService levelService;

    @Override
    public Integer getOnlineAnchorNum(SysUser user) {
        return null;
    }

    @Override
    public Integer getOnlineWatcher(SysUser user) {
        String tableName = getTableName(user.getRole());
        String levelIdName = getLevelIdName(user.getRole());
        Integer levelId = levelService.getLevelIdByEmployeeId(user.getRole(), user.getEmployeeId());
        return onlineDataDao.getOnlineWatcher(tableName, levelIdName, levelId);
    }

    @Override
    public Integer getGiftSum(SysUser user) {
        String tableName = getTableName(user.getRole());
        String levelIdName = getLevelIdName(user.getRole());
        Integer levelId = levelService.getLevelIdByEmployeeId(user.getRole(), user.getEmployeeId());
        return onlineDataDao.getGiftSum(tableName, levelIdName, levelId);
    }

    private static String getLevelIdName(int role){
        if(role == 1){
            return "total_id";
        }
        if(role == 2){
            return "branch_id";
        }
        if(role == 3){
            return "group_id";
        }
        if(role == 4){
            return "team_id";
        }
        return null;
    }

    private static String getTableName(int role){
        if(role == 1){
            return "total_realtime";
        }
        if(role == 2){
            return "branch_realtime";
        }
        if(role == 3){
            return "group_realtime";
        }
        if(role == 4){
            return "team_realtime";
        }
        return null;
    }
}
