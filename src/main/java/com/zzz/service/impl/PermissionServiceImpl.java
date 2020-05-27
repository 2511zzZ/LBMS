package com.zzz.service.impl;

import com.zzz.dao.AnchorDao;
import com.zzz.dao.PermissionDao;
import com.zzz.dao.StructureDao;
import com.zzz.model.SysUser;
import com.zzz.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Autowired
    AnchorDao anchorDao;

    @Autowired
    StructureDao structureDao;


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

    @Override
    public boolean hasTeamPermission(SysUser user, int teamId) {
        if(user.getRole()==1){
            return true;
        }else if(user.getRole()==2){
            int branchId = structureDao.getBranchId(user.getEmployeeId());
            return permissionDao.branchHasTeamPermission(branchId,teamId)!=0;
        }else if(user.getRole()==3){
            int groupId = structureDao.getGroupId(user.getEmployeeId());
            return permissionDao.groupHasTeamPermission(groupId,teamId)!=0;
        }else if(user.getRole()==4){
            return teamId == structureDao.getTeamId(user.getEmployeeId());
        }

        return false;
    }

    @Override
    public boolean hasGroupPermission(SysUser user, int groupId) {

        if(user.getRole()==1){
            return true;
        }else if(user.getRole()==2){
            int branchId = structureDao.getBranchId(user.getEmployeeId());
            return permissionDao.branchHasGroupPermission(branchId,groupId)!=0;
        }else if(user.getRole()==3){
            return groupId == structureDao.getGroupId(user.getEmployeeId());
        }

        return false;
    }

    @Override
    public boolean hasBranchPermission(SysUser user, int branchId) {
        if(user.getRole()==1){
            return true;
        }else if(user.getRole()==2){
            return branchId == structureDao.getBranchId(user.getEmployeeId());
        }

        return false;
    }

    @Override
    public boolean hasTotalPermission(SysUser user, int totalId) {
        return user.getRole() == 1;
    }

    @Override
    public boolean hasPermission(SysUser user, String level, int levelId) {
        if(level.equals("anchor")){
            return hasPermission(user, levelId);
        }
        if(level.equals("team")){
            return hasTeamPermission(user, levelId);
        }
        if(level.equals("group")){
            return hasGroupPermission(user, levelId);
        }
        if(level.equals("branch")){
            return hasBranchPermission(user, levelId);
        }
        if(level.equals("total")){
            return hasTotalPermission(user, levelId);
        }
        return false;
    }
}
