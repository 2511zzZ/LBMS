package com.zzz.service.impl;

import com.zzz.dao.AnchorDao;
import com.zzz.dao.StructureDao;
import com.zzz.dao.SysUserDao;
import com.zzz.model.Anchor;
import com.zzz.model.Level;
import com.zzz.model.Structure;
import com.zzz.model.SysUser;
import com.zzz.service.AnchorService;
import com.zzz.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StructureServiceImpl implements StructureService {

    @Autowired
    StructureDao structureDao;
    @Autowired
    SysUserDao userDao;

    private static Map<Integer, Level> map = new HashMap<>();
    static {
        map.put(1, Level.TOTAL);
        map.put(2, Level.BRANCH);
        map.put(3, Level.GROUP);
        map.put(4, Level.TEAM);
        map.put(5, Level.ANCHOR);
    }


    @Override
    public Structure getStructure(int employeeId) {
        Structure structure = new Structure();

        structure.setEmployeeId(employeeId);

        // levelCode = 1,2,3,4
        SysUser user = userDao.getSimpleUserById(employeeId);

        int levelCode = 0;
        if(user!=null){
            levelCode = user.getRole();
        }

        switch (levelCode){
            case 1://总经理
                structure.setLevelId(structureDao.getTotalId(employeeId));
                structure.setSuperior(null);
                structure.setSubordinates(structureDao.getBranchIds(employeeId));
                break;
            case 2://分区经理
                int branchId = structureDao.getBranchId(employeeId);
                structure.setLevelId(branchId);
                structure.setSuperior(1);
                structure.setSubordinates(structureDao.getGroupIds(branchId));
                break;
            case 3://超管
                int groupId = structureDao.getGroupId(employeeId);
                structure.setLevelId(groupId);
                int superEmployeeId = structureDao.getSuperBranchEmployeeId(groupId);
                structure.setSuperior(superEmployeeId);
                structure.setSubordinates(structureDao.getTeamIds(groupId));
                break;
            case 4:
                int teamId = structureDao.getTeamId(employeeId);
                structure.setLevelId(teamId);
                int superGroupEmployeeId = structureDao.getSuperGroupEmployeeId(teamId);
                structure.setSuperior(superGroupEmployeeId);
                structure.setSubordinates(structureDao.getAnchorIds(teamId));
                break;
        }
        return structure;
    }
}
