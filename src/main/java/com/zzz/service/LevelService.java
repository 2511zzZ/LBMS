package com.zzz.service;

import com.zzz.model.Anchor;
import com.zzz.model.levels.Branch;
import com.zzz.model.levels.Group;
import com.zzz.model.levels.Team;
import com.zzz.model.levels.Total;

import java.util.List;

public interface LevelService {

    List<Team> getTeams(int page, int pageSize, Integer role, Integer employeeId);

    int countTeam(Integer role, Integer employeeId);

    List<Group> getGroups(int page, int pageSize, Integer role, Integer employeeId);

    int countGroup(Integer role, Integer employeeId);

    List<Branch> getBranchs(int page, int pageSize, Integer role, Integer employeeId);

    int countBranch(Integer role, Integer employeeId);

    List<Anchor> getAnchors(int page, int pageSize, Integer role, Integer employeeId);

    int countAnchor(Integer role, Integer employeeId);

    Total getTotal();

    List<Integer> getTeamIds();

    List<Integer> getGroupIds();

    List<Integer> getBranchIds();

    Integer getLevelIdByEmployeeId(Integer role, Integer employeeId);
}
