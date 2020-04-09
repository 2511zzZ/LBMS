package com.zzz.service;

import com.zzz.model.SysUser;

public interface PermissionService {

    boolean hasPermission(SysUser user, int anchorId);
}
