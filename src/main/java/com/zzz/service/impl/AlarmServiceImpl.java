package com.zzz.service.impl;

import com.zzz.dao.AlarmDao;
import com.zzz.dao.SysUserDao;
import com.zzz.model.*;
import com.zzz.service.AlarmService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AlarmDao alarmDao;

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public int getThreshold() {
        return alarmDao.getThreshold();
    }

    @Override
    public int getMaxTipNum() {
        return alarmDao.getMaxTipNum();
    }

    @Override
    public int getSumTipNum(int anchorId, Date timeNow, int threshold) {
        Integer sum = alarmDao.getSumTipNum(anchorId, timeNow, threshold);
        if(sum==null){
            return 0;
        }
        return sum;
    }

    @Override
    public void setThresholdAndMaxNum(int newThreshold, int maxTipNum) {
        alarmDao.setThresholdAndMaxNum(newThreshold, maxTipNum);
    }

    @Override
    public void insertAlarm(AnchorAlarm anchorAlarm) {
        alarmDao.insertAlarm(anchorAlarm);
    }

    @Override
    public Integer getAnchorIdByAlarm(String alarmId) {
        return alarmDao.getAnchorIdByAlarm(alarmId);
    }

    @Override
    public void processAlarm(String alarmId, int operation) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        SysUserDetails userDetails = sysUserDao.getUserById(user.getEmployeeId());
        alarmDao.processAlarm(alarmId, operation, new Date(), userDetails.getEmployeeId(), userDetails.getName());
    }

    @Override
    public List<AnchorAlarmTrans> getAlarms(int status) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<AnchorAlarmTrans> anchorAlarmTrans = alarmDao.getAlarms(user.getEmployeeId());
        for(AnchorAlarmTrans alarmTrans: anchorAlarmTrans){
            alarmTrans.setAlarm(alarmDao.getAlarmById(alarmTrans.getAlarmId()));
        }

        List<AnchorAlarmTrans> anchorAlarms = new ArrayList<>();
        for(AnchorAlarmTrans alarmTrans: anchorAlarmTrans){
            // status与输入分类相同且未被标记为delete
            if(alarmTrans.getAlarm().getStatus() == status && alarmTrans.getIsDelete() == 0){
                anchorAlarms.add(alarmTrans);
            }
        }
        return anchorAlarms;
    }

    @Override
    public void insertAlarmTrans(AnchorAlarmTrans anchorAlarmTrans) {
        alarmDao.insertAlarmTrans(anchorAlarmTrans);
    }

    @Override
    public AlarmOverview getAlarmOverview(Integer employeeId) {
        int waitingNum = alarmDao.getWaitingNum(employeeId);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayInThisMonth = calendar.getTime();

        List<AnchorAlarm> doneAnchorAlarms = alarmDao.getAlarmsOverview(employeeId, firstDayInThisMonth);

        int doneNum = 0;
        int avgDealCost = 0;    // 平均处理时间/秒
        int avgDealCostSum = 0;
        if(!doneAnchorAlarms.isEmpty()){
            doneNum = doneAnchorAlarms.size();
            for(AnchorAlarm anchorAlarm: doneAnchorAlarms){
                avgDealCostSum += (anchorAlarm.getEndTime().getTime() - anchorAlarm.getStartTime().getTime())/1000;
            }
        }
        avgDealCost = avgDealCostSum/doneAnchorAlarms.size();
        return new AlarmOverview(waitingNum, doneNum, avgDealCost);
    }

    @Override
    public void deleteAlert(String alarmId) {
        alarmDao.deleteAlert(alarmId);
    }
}
