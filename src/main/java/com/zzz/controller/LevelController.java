package com.zzz.controller;

import com.zzz.model.Anchor;
import com.zzz.model.SysUser;
import com.zzz.model.levels.Branch;
import com.zzz.model.levels.Group;
import com.zzz.model.levels.Team;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
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

    @RequestMapping(value = "/team/list", method = RequestMethod.GET)
    public Results<Team> getTeamList(@RequestParam(name="page", defaultValue = "1") int page,
                                       @RequestParam(name="pageSize", defaultValue = "10") int pageSize){
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<Team> teamList = levelService.getTeams(page, pageSize, user.getRole(), user.getEmployeeId());
        int total = levelService.countTeam(user.getRole(), user.getEmployeeId());
        return Results.success(ResponseCode.SUCCESS, total, teamList);
    }


    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public Results<Group> getGroupList(@RequestParam(name="page", defaultValue = "1") int page,
                                       @RequestParam(name="pageSize", defaultValue = "10") int pageSize){

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<Group> groupList = levelService.getGroups(page, pageSize, user.getRole(), user.getEmployeeId());
        int total = levelService.countGroup(user.getRole(), user.getEmployeeId());
        return Results.success(ResponseCode.SUCCESS, total, groupList);
    }

    @RequestMapping(value = "/branch/list", method = RequestMethod.GET)
    public Results<Branch> getBranchList(@RequestParam(name="page", defaultValue = "1") int page,
                                         @RequestParam(name="pageSize", defaultValue = "10") int pageSize){

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<Branch> branchList = levelService.getBranchs(page, pageSize, user.getRole(), user.getEmployeeId());
        int total = levelService.countBranch(user.getRole(), user.getEmployeeId());
        return Results.success(ResponseCode.SUCCESS, total, branchList);
    }

    @RequestMapping(value = "/anchor/list", method = RequestMethod.GET)
    public Results<Anchor> getAnchorList(@RequestParam(name="page", defaultValue = "1") int page,
                                         @RequestParam(name="pageSize", defaultValue = "10") int pageSize){

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<Anchor> anchorList = levelService.getAnchors(page, pageSize, user.getRole(), user.getEmployeeId());
        int total = levelService.countAnchor(user.getRole(), user.getEmployeeId());
        return Results.success(ResponseCode.SUCCESS, total, anchorList);
    }

    @RequestMapping(value="/levelId", method = RequestMethod.GET)
    public Results<Integer> getLevelIdByEmployeeId(){
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Integer levelId = levelService.getLevelIdByEmployeeId(user.getRole(), user.getEmployeeId());
        return Results.success(ResponseCode.SUCCESS, levelId);
    }
}
