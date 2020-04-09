package com.zzz.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionDao {
    int branchHasTeamPermission(int branchId, int teamId);

    int groupHasTeamPermission(int groupId, int teamId);

    int branchHasGroupPermission(int branchId, int groupId);
}
