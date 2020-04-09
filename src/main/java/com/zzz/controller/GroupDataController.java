package com.zzz.controller;

import com.zzz.model.HistoryDatas.GroupHistoryData;
import com.zzz.model.OnlineDatas.GroupOnlineData;
import com.zzz.model.SysUser;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.GroupDataService;
import com.zzz.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
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
@RequestMapping("/groupData")
public class GroupDataController {

    @Autowired
    private GroupDataService groupDataService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public Results<GroupOnlineData> getGroupOnlineData(@RequestParam int groupId){

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(!permissionService.hasGroupPermission(user,groupId)){
            return Results.failure(ResponseCode.FORBIDDEN);
        }

        return Results.success(ResponseCode.SUCCESS, groupDataService.getGroupOnlineData(groupId));
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Results<GroupHistoryData> getGroupHistoryData(@RequestParam int groupId, @RequestParam String dateStr){
        Date date;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            date = simpleDateFormat.parse(dateStr);
        }catch (ParseException e){
            return Results.failure(ResponseCode.BAD_DATE_FORMAT);
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(!permissionService.hasGroupPermission(user,groupId)){
            return Results.failure(ResponseCode.FORBIDDEN);
        }

        return Results.success(ResponseCode.SUCCESS, groupDataService.getGroupHistoryData(groupId, date));
    }
    @RequestMapping(value = "/historys", method = RequestMethod.GET)
    public Results<GroupHistoryData> getGroupHistoryData(@RequestParam int groupId,
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
            return Results.failure(ResponseCode.BAD_DATE_FORMAT);
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(!permissionService.hasGroupPermission(user,groupId)){
            return Results.failure(ResponseCode.FORBIDDEN);
        }

        int total = groupDataService.getHistoryDataNum(groupId, begin, end);
        return Results.success(ResponseCode.SUCCESS, total, groupDataService.getGroupHistoryData(groupId, begin, end, page, pageSize));
    }
}
