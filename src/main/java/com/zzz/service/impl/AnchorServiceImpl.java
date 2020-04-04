package com.zzz.service.impl;

import com.zzz.dao.AnchorDao;
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
    public boolean hasPermission(SysUser user, int anchorId) {

        //1.总经理 2.分区经理  3.超管 4.管理员
        if(user.getRole()==1){
            return true;
        }else if(user.getRole()==2){
            int branchId = structureDao.getBranchId(user.getEmployeeId());
            return anchorDao.branchHasPermission(branchId,anchorId)!=0;
        }else if(user.getRole()==3){
            int groupId = structureDao.getGroupId(user.getEmployeeId());
            return anchorDao.groupHasPermission(groupId,anchorId)!=0;
        }else if(user.getRole()==4){
            int teamId = structureDao.getTeamId(user.getEmployeeId());
            return anchorDao.teamHasPermission(teamId, anchorId)!=0;
        }

        return false;
    }
}
