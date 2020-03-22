package com.zzz.dao;

import com.zzz.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao {
    List<SysUser> getUserWithPagination(int offset, int limit);

    Integer getTotalNum();
}
