package com.zzz.dao;

import com.zzz.model.Anchor;
import com.zzz.model.OnlineDatas.*;
import com.zzz.model.SysUser;
import com.zzz.model.SysUserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FakeDataDao {

    int insertAnchors(List<Anchor> anchorList);

    int insertUserDetails(List<SysUserDetails> userDetailsList);

    List<SysUserDetails> queryAllUserDetails();

    List<SysUser> queryAllUsers();

    int insertUsers(List<SysUser> userList);

    void insertIntoTotal(int totalId, String levelName, Integer employeeId);

    void insertIntoBranch(int branchId, String levelName, Integer employeeId);

    void insertIntoGroup(int groupId, String levelName, Integer employeeId);

    void insertIntoTeam(int teamId, String levelName, Integer employeeId);

    void insertStrucManage(int anchorId, int teamId, int groupId, int branchId);

    void insertRealtimeData(List<AnchorOnlineData> anchorOnlineData);

    void insertTeamRealtimeData(List<TeamOnlineData> teamOnlineData);

    void insertGroupRealtimeData(List<GroupOnlineData> groupOnlineData);

    void insertBranchRealtimeData(List<BranchOnlineData> branchOnlineData);

    void insertTotalRealtimeData(TotalOnlineData totalOnlineData);
}
