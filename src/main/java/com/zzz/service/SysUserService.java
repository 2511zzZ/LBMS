package com.zzz.service;

import com.zzz.model.SysUserDetails;

import java.util.List;

public interface SysUserService {
    List<SysUserDetails> getUsers(int page, int pageSize);

    Integer getTotalNum();

    SysUserDetails getUser(int employeeId);

    boolean changePassword(int employeeId, String oldPassword, String newPassword);

    void changeDetails(int employeeId, SysUserDetails currentUser);

    Integer getRole(int employeeId);
}
