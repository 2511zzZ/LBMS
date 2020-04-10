package com.zzz.controller;

import com.zzz.model.levels.Branch;
import com.zzz.model.levels.Group;
import com.zzz.model.levels.Team;
import com.zzz.model.levels.Total;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class LevelController {

    @Autowired
    private LevelService levelService;


    //todo: level接口的权限配置
    @RequestMapping(value="/team", method = RequestMethod.GET)
    public Results<Team> getTeamByParam(@RequestParam(required = false) Integer teamId,
                                     @RequestParam(required = false) String teamName,
                                     @RequestParam(required = false) Integer employeeId,
                                     @RequestParam(name="page", defaultValue = "1") int page,
                                     @RequestParam(name="pageSize", defaultValue = "30") int pageSize){
        List<Team> teamList = levelService.getTeamByParam(teamId, teamName, employeeId, page, pageSize);
        int total = levelService.getTeamNumWithParam(teamId, teamName, employeeId);
        return Results.success(ResponseCode.SUCCESS, total, teamList);
    }

    @RequestMapping(value = "/team/list", method = RequestMethod.GET)
    public Results<Team> getTeamList(@RequestParam(name="page", defaultValue = "1") int page,
                                       @RequestParam(name="pageSize", defaultValue = "10") int pageSize){

        List<Team> teamList = levelService.getTeams(page, pageSize);
        int total = levelService.getTeamTotalNum();
        return Results.success(ResponseCode.SUCCESS, total, teamList);
    }



    @RequestMapping(value="/group", method = RequestMethod.GET)
    public Results<Group> getGroupByParam(@RequestParam(required = false) Integer groupId,
                                          @RequestParam(required = false) String groupName,
                                          @RequestParam(required = false) Integer employeeId,
                                          @RequestParam(name="page", defaultValue = "1") int page,
                                          @RequestParam(name="pageSize", defaultValue = "30") int pageSize){
        List<Group> groupList = levelService.getGroupByParam(groupId, groupName, employeeId, page, pageSize);
        int total = levelService.getGroupNumWithParam(groupId, groupName, employeeId);
        return Results.success(ResponseCode.SUCCESS, total, groupList);
    }

    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public Results<Group> getGroupList(@RequestParam(name="page", defaultValue = "1") int page,
                                       @RequestParam(name="pageSize", defaultValue = "10") int pageSize){

        List<Group> groupList = levelService.getGroups(page, pageSize);
        int total = levelService.getGroupTotalNum();
        return Results.success(ResponseCode.SUCCESS, total, groupList);
    }


    @RequestMapping(value="/branch", method = RequestMethod.GET)
    public Results<Branch> getBranchByParam(@RequestParam(required = false) Integer branchId,
                                            @RequestParam(required = false) String branchName,
                                            @RequestParam(required = false) Integer employeeId,
                                            @RequestParam(name="page", defaultValue = "1") int page,
                                            @RequestParam(name="pageSize", defaultValue = "30") int pageSize){
        List<Branch> branchList = levelService.getBranchByParam(branchId, branchName, employeeId, page, pageSize);
        int total = levelService.getBranchNumWithParam(branchId, branchName, employeeId);
        return Results.success(ResponseCode.SUCCESS, total, branchList);
    }

    @RequestMapping(value = "/branch/list", method = RequestMethod.GET)
    public Results<Branch> getBranchList(@RequestParam(name="page", defaultValue = "1") int page,
                                         @RequestParam(name="pageSize", defaultValue = "10") int pageSize){

        List<Branch> branchList = levelService.getBranchs(page, pageSize);
        int total = levelService.getBranchTotalNum();
        return Results.success(ResponseCode.SUCCESS, total, branchList);
    }


    @RequestMapping(value="/total", method = RequestMethod.GET)
    public Results<Total> getBranchByParam(){
        Total total = levelService.getTotal();
        return Results.success(ResponseCode.SUCCESS, total);
    }


}
