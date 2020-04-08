package com.zzz.dao;

import com.zzz.model.Anchor;
import com.zzz.model.SysUser;
import com.zzz.model.SysUserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FakeDataDao {

    int insertAnchors(List<Anchor> anchorList);

    int insertUserDetails(List<SysUserDetails> userDetailsList);

    List<SysUserDetails> queryAllUserDetails();

    int insertUsers(List<SysUser> userList);
}
