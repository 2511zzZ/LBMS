package com.zzz.service;

import com.zzz.model.SysUser;

import java.util.List;

public interface SysUserService {
    List<SysUser> getUsers(int page, int pageSize);

    Integer getTotalNum();
}
