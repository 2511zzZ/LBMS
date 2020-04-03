package com.zzz.service;

import com.zzz.model.SysUser;
import com.zzz.model.SysUserDetails;
import com.zzz.model.SysUserSettings;

import java.util.List;

public interface SysUserService {
    List<SysUserDetails> getUsers(int page, int pageSize);

    Integer getTotalNum();

    SysUserDetails getUser(int employeeId);

    SysUser getSimpleUserByName(String username);

    boolean changePassword(int employeeId, String oldPassword, String newPassword);

    void changeDetails(int employeeId, SysUserDetails currentUser);

    Integer getRole(int employeeId);

    SysUserSettings getSettings(int employeeId);

    void changeSettings(int employeeId, String setting1, String setting2);
}
