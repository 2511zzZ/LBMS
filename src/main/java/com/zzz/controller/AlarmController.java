package com.zzz.controller;

import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.AlarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/alarm")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @RequestMapping(value = "/threshold", method = RequestMethod.GET)
    public Results<Integer> getThreshold(){
        return Results.success(ResponseCode.SUCCESS,alarmService.getThreshold());
    }

    @RequestMapping(value = "/maxAlarmNum", method = RequestMethod.GET)
    public Results<Integer> getMaxAlarmNum(){
        return Results.success(ResponseCode.SUCCESS,alarmService.getMaxTipNum());
    }

    @RequestMapping(value="/sumAlarmNum", method = RequestMethod.GET)
    public Results<Integer> getSumAlarmNum(@RequestParam int anchorId){
        return Results.success(ResponseCode.SUCCESS, alarmService.getSumTipNum(anchorId, new Date(), alarmService.getThreshold()));
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public Results setThresholdAndMaxNum(@RequestParam int newThreshold,
                                         @RequestParam int maxTipNum){
        alarmService.setThresholdAndMaxNum(newThreshold, maxTipNum);
        return Results.success();
    }
}
