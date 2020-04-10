package com.zzz.dao;

import com.zzz.model.levels.Branch;
import com.zzz.model.levels.Group;
import com.zzz.model.levels.Team;
import com.zzz.model.levels.Total;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LevelDao {

    List<Team> getTeamByParam(Integer teamId, String teamName, Integer employeeId, int offset, int limit);

    List<Team> getTeams(int offset, int limit);

    int getTeamTotalNum();

    int getTeamNumWithParam(Integer teamId, String teamName, Integer employeeId);


    List<Group> getGroupByParam(Integer groupId, String groupName, Integer employeeId, int offset, int limit);

    List<Group> getGroups(int offset, int limit);

    int getGroupTotalNum();

    int getGroupNumWithParam(Integer groupId, String groupName, Integer employeeId);


    List<Branch> getBranchByParam(Integer branchId, String branchName, Integer employeeId, int offset, int limit);

    List<Branch> getBranchs(int offset, int limit);

    int getBranchTotalNum();

    int getBranchNumWithParam(Integer branchId, String branchName, Integer employeeId);


    Total getTotal();
}
