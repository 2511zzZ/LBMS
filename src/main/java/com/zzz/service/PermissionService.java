package com.zzz.service;

import com.zzz.model.SysUser;

public interface PermissionService {

    boolean hasPermission(SysUser user, int anchorId);

    boolean hasTeamPermission(SysUser user, int teamId);

    boolean hasGroupPermission(SysUser user, int groupId);

    boolean hasBranchPermission(SysUser user, int branchId);

    boolean hasTotalPermission(SysUser user, int totalId);

    boolean hasPermission(SysUser user, String level, int levelId);
}
