package com.zzz.service.impl;

import com.zzz.dao.SysUserDao;
import com.zzz.model.SysUser;
import com.zzz.model.SysUserDetails;
import com.zzz.model.SysUserSettings;
import com.zzz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao userDao;

    @Override
    public List<SysUserDetails> getUsers(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return userDao.getUserWithPagination(offset, pageSize);
    }

    @Override
    public Integer getTotalNum() {
        return userDao.getTotalNum();
    }

    @Override
    public SysUserDetails getUser(int employeeId){return userDao.getUserById(employeeId);}

    @Override
    public SysUser getSimpleUserByName(String username) {
        return userDao.getSimpleUserByName(username);
    }

    @Override
    public boolean changePassword(int employeeId, String oldPassword, String newPassword){
        if(!(userDao.getSimpleUserById(employeeId).getPassword().equals(oldPassword))){
            return false;
        }
        userDao.changePassword(employeeId, newPassword);
        return true;
    }

    @Override
    public void changeDetails(int employeeId, SysUserDetails currentUser) {
        String nickname = currentUser.getNickname();
        String avatar = currentUser.getAvatar();
        userDao.changeDetails(employeeId, nickname,avatar);
    }

    @Override
    public Integer getRole(int employeeId) {
        return userDao.getSimpleUserById(employeeId).getRole();
    }

    @Override
    public SysUserSettings getSettings(int employeeId) {
        return userDao.getSettings(employeeId);
    }

    @Override
    public void changeSettings(int employeeId, String setting1, String setting2) {
        SysUserSettings settings = new SysUserSettings(employeeId, setting1, setting2);
        userDao.changeSettings(settings);
    }
}
