package com.zzz.service.impl;

import com.zzz.dao.AlarmDao;
import com.zzz.model.AlarmOverview;
import com.zzz.model.AnchorAlarm;
import com.zzz.model.AnchorAlarmTrans;
import com.zzz.model.SysUser;
import com.zzz.service.AlarmService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AlarmDao alarmDao;

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
        alarmDao.processAlarm(alarmId, operation, new Date());
    }

    @Override
    public List<AnchorAlarmTrans> getAlarms() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return alarmDao.getAlarms(user.getEmployeeId());
    }

    @Override
    public void insertAlarmTrans(AnchorAlarmTrans anchorAlarmTrans) {
        alarmDao.insertAlarmTrans(anchorAlarmTrans);
    }

    @Override
    public AnchorAlarm getAlarmById(String alarmId) {
        return alarmDao.getAlarmById(alarmId);
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
}
