package com.zzz.controller;

import com.zzz.exception.BadDateFormatException;
import com.zzz.exception.ForBiddenException;
import com.zzz.model.HistoryDatas.TeamHistoryData;
import com.zzz.model.OnlineDatas.TeamOnlineData;
import com.zzz.model.SysUser;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.PermissionService;
import com.zzz.service.TeamDataService;
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
@RequestMapping("/teamData")
public class TeamDataController {

    @Autowired
    private TeamDataService teamDataService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public Results<TeamOnlineData> getTeamOnlineData(@RequestParam int teamId,
                                                     @RequestParam Date datetime) throws ForBiddenException {

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(!permissionService.hasTeamPermission(user,teamId)){
            throw new ForBiddenException();
        }
        List<TeamOnlineData> teamOnlineData = teamDataService.getTeamOnlineData(teamId, datetime);
        return Results.success(ResponseCode.SUCCESS, teamOnlineData.size(), teamOnlineData);
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Results<TeamHistoryData> getTeamHistoryData(@RequestParam int teamId, @RequestParam String dateStr) throws BadDateFormatException, ForBiddenException {
        Date date;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            date = simpleDateFormat.parse(dateStr);
        }catch (ParseException e){
            throw new BadDateFormatException();
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(!permissionService.hasTeamPermission(user,teamId)){
            throw new ForBiddenException();
        }

        return Results.success(ResponseCode.SUCCESS, teamDataService.getTeamHistoryData(teamId, date));
    }
    @RequestMapping(value = "/historys", method = RequestMethod.GET)
    public Results<TeamHistoryData> getTeamHistoryData(@RequestParam int teamId,
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
        if(!permissionService.hasTeamPermission(user,teamId)){
            throw new ForBiddenException();
        }

        int total = teamDataService.getHistoryDataNum(teamId, begin, end);
        return Results.success(ResponseCode.SUCCESS, total, teamDataService.getTeamHistoryData(teamId, begin, end, page, pageSize));
    }
}
