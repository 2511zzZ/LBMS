package com.zzz.controller;

import com.zzz.exception.AnchorNotExistException;
import com.zzz.exception.BadOperationException;
import com.zzz.exception.ForBiddenException;
import com.zzz.model.AlarmOverview;
import com.zzz.model.AnchorAlarmTrans;
import com.zzz.model.AnchorAlarmTransWay;
import com.zzz.model.SysUser;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.AlarmService;
import com.zzz.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/alarm")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value="/settings", method = RequestMethod.POST)
    public Results setThresholdAndMaxNum(@RequestParam int newThreshold,
                                         @RequestParam int maxTipNum){
        newThreshold = Math.max(newThreshold, 1);
        newThreshold = Math.min(newThreshold, 30);
        maxTipNum = Math.max(maxTipNum, 1);
        alarmService.setThresholdAndMaxNum(newThreshold, maxTipNum);
        return Results.success();
    }

    @RequestMapping(value = "/settings/threshold", method = RequestMethod.GET)
    public Results<Integer> getThreshold(){
        return Results.success(ResponseCode.SUCCESS,alarmService.getThreshold());
    }

    @RequestMapping(value = "/settings/maxAlarmNum", method = RequestMethod.GET)
    public Results<Integer> getMaxAlarmNum(){
        return Results.success(ResponseCode.SUCCESS,alarmService.getMaxTipNum());
    }

    @RequestMapping(value="/sumAlarmNum", method = RequestMethod.GET)
    public Results<Integer> getSumAlarmNum(@RequestParam int anchorId) throws ForBiddenException {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        if(!permissionService.hasPermission(user,anchorId)){
            throw new ForBiddenException();
        }
        return Results.success(ResponseCode.SUCCESS, alarmService.getSumTipNum(anchorId, new Date(), alarmService.getThreshold()));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Results processAlarm(@RequestParam String alarmId,
                                @RequestParam int operation) throws BadOperationException, AnchorNotExistException, ForBiddenException {
        if(alarmService.getAnchorIdByAlarm(alarmId)==null){
            throw new AnchorNotExistException();
        }
        int anchorId = alarmService.getAnchorIdByAlarm(alarmId);

        if(operation!=2 && operation!=3){
            throw new BadOperationException();
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(!permissionService.hasPermission(user,anchorId)){
            throw new ForBiddenException();
        }

        alarmService.processAlarm(alarmId, operation);
        return Results.success();
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Results<AnchorAlarmTrans> getAlarms(int status) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<AnchorAlarmTrans> anchorAlarmTrans = alarmService.getAlarms(status, user.getEmployeeId());
        return Results.success(ResponseCode.SUCCESS, anchorAlarmTrans.size(), anchorAlarmTrans);
    }

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public Results<AlarmOverview> getAlarmOverview() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return Results.success(ResponseCode.SUCCESS, alarmService.getAlarmOverview(user.getEmployeeId()));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Results deleteAlert(@RequestParam String alarmId) {
        alarmService.deleteAlert(alarmId);
        return Results.success();
    }

    @RequestMapping(value = "/transform", method = RequestMethod.GET)
    public Results<AnchorAlarmTransWay> getAlarmTransWay(@RequestParam String alarmId) {
        return Results.success(ResponseCode.SUCCESS, alarmService.getAlarmTransWay(alarmId));
    }

    @RequestMapping(value = "/transform/settings", method = RequestMethod.GET)
    public Results<Integer> getAlarmTransSetting() {
        return Results.success(ResponseCode.SUCCESS, alarmService.getAlarmTransSetting());
    }

    @RequestMapping(value = "/transform/settings", method = RequestMethod.POST)
    public Results<Integer> setAlarmTransSetting(@RequestParam int transTime) {
        transTime = Math.max(transTime, 1);
        transTime = Math.min(transTime, 30);
        alarmService.setAlarmTransSetting(transTime);
        return Results.success(ResponseCode.SUCCESS);
    }

    // 警报测试接口
//    @RequestMapping(value="/", method = RequestMethod.POST)
//    public Results newAnchorAlarm(@RequestParam int anchorId,
//                                  @RequestParam String startTime){
//        Date datetime;
//        try{
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
//            datetime = simpleDateFormat.parse(startTime);
//        }catch (ParseException e){
//            return Results.failure(ResponseCode.BAD_DATE_FORMAT);
//        }
//        alarmService.insertAlarm(new AnchorAlarm(
//                Utils.generateAlarmId(anchorId, datetime),
//                anchorId,
//                1,
//                1,
//                datetime,
//                null));
//        return Results.success();
//    }
}
