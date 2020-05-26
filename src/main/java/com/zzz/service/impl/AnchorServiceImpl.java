package com.zzz.service.impl;

import com.zzz.dao.AnchorDao;
import com.zzz.dao.LevelDao;
import com.zzz.dao.StructureDao;
import com.zzz.model.Anchor;
import com.zzz.model.SysUser;
import com.zzz.service.AnchorService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AnchorServiceImpl implements AnchorService {

    @Autowired
    AnchorDao anchorDao;

    @Autowired
    StructureDao structureDao;

    @Autowired
    LevelDao levelDao;

    @Override
    public List<Anchor> getAnchors(int page, int pageSize) {

        int offset = (page - 1) * pageSize;

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        if(user.getRole()==1){
            return anchorDao.getAnchorWithPagination(offset, pageSize);
        }else if(user.getRole()==2){
            return anchorDao.branchGetAnchors(structureDao.getBranchId(user.getEmployeeId()), offset, pageSize);
        }else if(user.getRole()==3){
            return anchorDao.groupGetAnchors(structureDao.getGroupId(user.getEmployeeId()), offset, pageSize);
        }else if(user.getRole()==4){
            return anchorDao.teamGetAnchors(structureDao.getTeamId(user.getEmployeeId()), offset, pageSize);
        }


        return null;
    }

    @Override
    public Integer getTotalNum() {
        return anchorDao.getTotalNum();
    }

    @Override
    public List<Anchor> getOnlineAnchors(int page, int pageSize) {

        int offset = (page - 1) * pageSize;

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        if(user.getRole()==1){
            return anchorDao.getOnlineAnchorWithPagination(offset, pageSize);
        }else if(user.getRole()==2){
            return anchorDao.branchGetOnlineAnchors(structureDao.getBranchId(user.getEmployeeId()), offset, pageSize);
        }else if(user.getRole()==3){
            return anchorDao.groupGetOnlineAnchors(structureDao.getGroupId(user.getEmployeeId()), offset, pageSize);
        }else if(user.getRole()==4){
            return anchorDao.teamGetOnlineAnchors(structureDao.getTeamId(user.getEmployeeId()), offset, pageSize);
        }


        return null;
    }

    @Override
    public Integer getOnlineTotalNum() {
        return anchorDao.getOnlineTotalNum();
    }

    @Override
    public Anchor getAnchor(int anchorId) {
        return anchorDao.getAnchorById(anchorId);
    }

    @Override
    public void banAnchor(int anchorId, Date begin, Date end, String reason) {
        anchorDao.banAnchor(anchorId, begin, end, reason);
    }

    @Override
    public List<Anchor> recommendAnchors() {
        return anchorDao.recommendAnchors();
    }

    @Override
    public List<Integer> getAnchorIds() {
        return anchorDao.getAnchorIds();
    }

    @Override
    public Integer getOnlineAnchorNum(SysUser user) {
        int role = user.getRole();
        Integer levelId = levelDao.getLevelIdByEmployeeId(getTableName(role), getLevelIdName(role), user.getEmployeeId());
        if(role == 1){
            return anchorDao.getOnlineAnchorNum("1", levelId);
        }
        return anchorDao.getOnlineAnchorNum(getLevelIdName(role), levelId);
    }

    @Override
    public Integer getTeamIdByAnchorId(int anchorId) {
        return anchorDao.getTeamIdByAnchorId(anchorId);
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
