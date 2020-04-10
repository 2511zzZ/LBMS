package com.zzz.service.impl;

import com.zzz.dao.LevelDao;
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
    public List<Team> getTeamByParam(Integer teamId, String teamName, Integer employeeId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return levelDao.getTeamByParam(teamId, teamName, employeeId, offset, pageSize);
    }

    @Override
    public List<Team> getTeams(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return levelDao.getTeams(offset, pageSize);
    }

    @Override
    public int getTeamTotalNum() {
        return levelDao.getTeamTotalNum();
    }

    @Override
    public int getTeamNumWithParam(Integer teamId, String teamName, Integer employeeId) {
        return levelDao.getTeamNumWithParam(teamId, teamName, employeeId);
    }


    @Override
    public List<Group> getGroupByParam(Integer groupId, String groupName, Integer employeeId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return levelDao.getGroupByParam(groupId, groupName, employeeId, offset, pageSize);
    }

    @Override
    public List<Group> getGroups(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return levelDao.getGroups(offset, pageSize);
    }

    @Override
    public int getGroupTotalNum() {
        return levelDao.getGroupTotalNum();
    }

    @Override
    public int getGroupNumWithParam(Integer groupId, String groupName, Integer employeeId) {
        return levelDao.getGroupNumWithParam(groupId, groupName, employeeId);
    }



    @Override
    public List<Branch> getBranchByParam(Integer branchId, String branchName, Integer employeeId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return levelDao.getBranchByParam(branchId, branchName, employeeId, offset, pageSize);
    }

    @Override
    public List<Branch> getBranchs(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return levelDao.getBranchs(offset, pageSize);
    }

    @Override
    public int getBranchTotalNum() {
        return levelDao.getBranchTotalNum();
    }

    @Override
    public int getBranchNumWithParam(Integer branchId, String branchName, Integer employeeId) {
        return levelDao.getBranchNumWithParam(branchId, branchName, employeeId);
    }

    @Override
    public Total getTotal() {
        return levelDao.getTotal();
    }


}
