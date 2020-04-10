package com.zzz.service;

import com.zzz.model.levels.Branch;
import com.zzz.model.levels.Group;
import com.zzz.model.levels.Team;
import com.zzz.model.levels.Total;

import java.util.List;

public interface LevelService {

    List<Team> getTeamByParam(Integer teamId, String teamName, Integer employeeId, int page, int pageSize);

    List<Team> getTeams(int page, int pageSize);

    int getTeamTotalNum();

    int getTeamNumWithParam(Integer teamId, String teamName, Integer employeeId);


    List<Group> getGroupByParam(Integer groupId, String groupName, Integer employeeId, int page, int pageSize);

    List<Group> getGroups(int page, int pageSize);

    int getGroupTotalNum();

    int getGroupNumWithParam(Integer groupId, String groupName, Integer employeeId);


    List<Branch> getBranchByParam(Integer branchId, String branchName, Integer employeeId, int page, int pageSize);

    List<Branch> getBranchs(int page, int pageSize);

    int getBranchTotalNum();

    int getBranchNumWithParam(Integer branchId, String branchName, Integer employeeId);


    Total getTotal();
}
