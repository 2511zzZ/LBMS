package com.zzz.dao;

import com.zzz.model.Anchor;
import com.zzz.model.levels.Branch;
import com.zzz.model.levels.Group;
import com.zzz.model.levels.Team;
import com.zzz.model.levels.Total;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LevelDao {

    List<Team> getTeamByParam(Integer teamId, String teamName, Integer employeeId, int offset, int limit);

    int getTeamNumWithParam(Integer teamId, String teamName, Integer employeeId);

    List<Group> getGroupByParam(Integer groupId, String groupName, Integer employeeId, int offset, int limit);

    int getGroupNumWithParam(Integer groupId, String groupName, Integer employeeId);

    List<Branch> getBranchByParam(Integer branchId, String branchName, Integer employeeId, int offset, int limit);

    int getBranchNumWithParam(Integer branchId, String branchName, Integer employeeId);


    Total getTotal();

    List<Integer> getTeamIds();

    List<Integer> getGroupIds();

    List<Integer> getBranchIds();

    Integer getLevelIdByEmployeeId(String tableName, String levelIdName, Integer employeeId);

    List<Team> getTeamsByUser(String levelIdName, Integer levelId, Integer offset, Integer limit);

    List<Group> getGroupsByUser(String levelIdName, Integer levelId, Integer offset, Integer limit);

    List<Branch> getBranchsByUser(String levelIdName, Integer levelId, Integer offset, Integer limit);

    List<Anchor> getAnchorsByUser(String levelIdName, Integer levelId, Integer offset, Integer limit);

    int countAnchor(String levelIdName, Integer levelId);
    
    int countTeam(String levelIdName, Integer levelId);

    int countGroup(String levelIdName, Integer levelId);

    int countBranch(String levelIdName, Integer levelId);

    int countAllAnchor();
}
