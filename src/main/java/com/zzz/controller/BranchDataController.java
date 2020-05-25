package com.zzz.controller;

import com.zzz.exception.BadDateFormatException;
import com.zzz.exception.ForBiddenException;
import com.zzz.model.HistoryDatas.BranchHistoryData;
import com.zzz.model.OnlineDatas.BranchOnlineData;
import com.zzz.model.SysUser;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.BranchDataService;
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
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/branchData")
public class BranchDataController {

    @Autowired
    private BranchDataService branchDataService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public Results<BranchOnlineData> getBranchOnlineData(@RequestParam int branchId,
                                                         @RequestParam String datetimeStr) throws ForBiddenException, BadDateFormatException {

        Date datetime;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
            datetime = simpleDateFormat.parse(datetimeStr);
        }catch (ParseException e){
            throw new BadDateFormatException();
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(!permissionService.hasBranchPermission(user,branchId)){
            throw new ForBiddenException();
        }
        List<BranchOnlineData> branchOnlineData = branchDataService.getBranchOnlineData(branchId, datetime);
        return Results.success(ResponseCode.SUCCESS, branchOnlineData.size(), branchOnlineData);
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Results<BranchHistoryData> getBranchHistoryData(@RequestParam int branchId, @RequestParam String dateStr) throws BadDateFormatException, ForBiddenException {
        Date date;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            date = simpleDateFormat.parse(dateStr);
        }catch (ParseException e){
            throw new BadDateFormatException();
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(!permissionService.hasBranchPermission(user,branchId)){
            throw new ForBiddenException();
        }

        return Results.success(ResponseCode.SUCCESS, branchDataService.getBranchHistoryData(branchId, date));
    }
    @RequestMapping(value = "/historys", method = RequestMethod.GET)
    public Results<BranchHistoryData> getBranchHistoryData(@RequestParam int branchId,
                                                     @RequestParam String dateBeginStr,
                                                     @RequestParam String dateEndStr2,
                                                     @RequestParam(name="page", defaultValue = "1") int page,
                                                     @RequestParam(name="pageSize", defaultValue = "30") int pageSize) throws BadDateFormatException, ForBiddenException {
        Date begin;
        Date end;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            begin = simpleDateFormat.parse(dateBeginStr);
            end = simpleDateFormat.parse(dateEndStr2);
        }catch (ParseException e){
            throw new BadDateFormatException();
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(!permissionService.hasBranchPermission(user,branchId)){
            throw new ForBiddenException();
        }

        int total = branchDataService.getHistoryDataNum(branchId, begin, end);
        return Results.success(ResponseCode.SUCCESS, total, branchDataService.getBranchHistoryData(branchId, begin, end, page, pageSize));
    }
}
