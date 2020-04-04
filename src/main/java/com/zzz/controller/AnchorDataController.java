package com.zzz.controller;

import com.zzz.model.HistoryData;
import com.zzz.model.OnlineData;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.AnchorDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/anchorData")
public class AnchorDataController {

    @Autowired
    private AnchorDataService anchorDataService;


    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public Results<OnlineData> getAnchorOnlineData(@RequestParam int anchorId){
        return Results.success(ResponseCode.SUCCESS, anchorDataService.getAnchorOnlineData(anchorId));
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Results<HistoryData> getAnchorHistoryData(@RequestParam int anchorId, @RequestParam String dateStr){
        Date date;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            date = simpleDateFormat.parse(dateStr);
        }catch (ParseException e){
            return Results.failure(5002, "日期格式错误");
        }
        return Results.success(ResponseCode.SUCCESS, anchorDataService.getAnchorHistoryData(anchorId, date));
    }
    @RequestMapping(value = "/historys", method = RequestMethod.GET)
    public Results<HistoryData> getAnchorHistoryData(@RequestParam int anchorId,
                                                     @RequestParam String dateBeginStr,
                                                     @RequestParam String dateEndStr2,
                                                     @RequestParam(name="page", defaultValue = "1") int page,
                                                     @RequestParam(name="pageSize", defaultValue = "30") int pageSize){
        Date begin;
        Date end;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            begin = simpleDateFormat.parse(dateBeginStr);
            end = simpleDateFormat.parse(dateEndStr2);
        }catch (ParseException e){
            return Results.failure(5002, "日期格式错误");
        }
        int total = anchorDataService.getHistoryDataNum(anchorId, begin, end);
        return Results.success(ResponseCode.SUCCESS, total, anchorDataService.getAnchorHistoryData(anchorId, begin, end, page, pageSize));
    }
}
