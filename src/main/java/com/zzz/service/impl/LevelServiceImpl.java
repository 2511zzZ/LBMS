package com.zzz.service.impl;

import com.zzz.dao.LevelDao;
import com.zzz.model.Anchor;
import com.zzz.model.levels.Branch;
import com.zzz.model.levels.Group;
import com.zzz.model.levels.Team;
import com.zzz.model.levels.Total;
import com.zzz.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LevelServiceImpl implements LevelService {

    @Autowired
    LevelDao levelDao;

    @Override
    public Total getTotal() {
        return levelDao.getTotal();
    }

    @Override
    public List<Integer> getTeamIds() {
        return levelDao.getTeamIds();
    }

    @Override
    public List<Integer> getGroupIds() {
        return levelDao.getGroupIds();
    }

    @Override
    public List<Integer> getBranchIds() {
        return levelDao.getBranchIds();
    }

    @Override
    public Integer getLevelIdByEmployeeId(Integer role, Integer employeeId) {
        return levelDao.getLevelIdByEmployeeId(getTableName(role), getLevelIdName(role), employeeId);
    }

    @Override
    public List<Team> getTeams(int page, int pageSize, Integer role, Integer employeeId) {
        Integer levelId = this.getLevelIdByEmployeeId(role, employeeId);
        int offset = (page - 1) * pageSize;
        return levelDao.getTeamsByUser(getLevelIdName(role), levelId, offset, pageSize);
    }

    @Override
    public int countTeam(Integer role, Integer employeeId) {
        Integer levelId = this.getLevelIdByEmployeeId(role, employeeId);
        return levelDao.countTeam(getLevelIdName(role), levelId);
    }

    @Override
    public List<Group> getGroups(int page, int pageSize, Integer role, Integer employeeId) {
        Integer levelId = this.getLevelIdByEmployeeId(role, employeeId);
        int offset = (page - 1) * pageSize;
        return levelDao.getGroupsByUser(getLevelIdName(role), levelId, offset, pageSize);
    }

    @Override
    public int countGroup(Integer role, Integer employeeId) {
        Integer levelId = this.getLevelIdByEmployeeId(role, employeeId);
        return levelDao.countGroup(getLevelIdName(role), levelId);
    }

    @Override
    public List<Branch> getBranchs(int page, int pageSize, Integer role, Integer employeeId) {
        Integer levelId = this.getLevelIdByEmployeeId(role, employeeId);
        int offset = (page - 1) * pageSize;
        return levelDao.getBranchsByUser(getLevelIdName(role), levelId, offset, pageSize);
    }

    @Override
    public int countBranch(Integer role, Integer employeeId) {
        Integer levelId = this.getLevelIdByEmployeeId(role, employeeId);
        return levelDao.countBranch(getLevelIdName(role), levelId);
    }

    @Override
    public List<Anchor> getAnchors(int page, int pageSize, Integer role, Integer employeeId) {
        Integer levelId = this.getLevelIdByEmployeeId(role, employeeId);
        int offset = (page - 1) * pageSize;
        return levelDao.getAnchorsByUser(getLevelIdName(role), levelId, offset, pageSize);
    }

    @Override
    public int countAnchor(Integer role, Integer employeeId) {
        Integer levelId = this.getLevelIdByEmployeeId(role, employeeId);
        return levelDao.countAnchor(getLevelIdName(role), levelId);
    }

    private static String getLevelIdName(int role){
        if(role == 1){
            return "total_id";
        }
        if(role == 2){
            return "branch_id";
        }
        if(role == 3){
            return "group_id";
        }
        if(role == 4){
            return "team_id";
        }
        return null;
    }

    private static String getTableName(int role){
        if(role == 1){
            return "struc_total";
        }
        if(role == 2){
            return "struc_branch";
        }
        if(role == 3){
            return "struc_group";
        }
        if(role == 4){
            return "struc_team";
        }
        return null;
    }
}
