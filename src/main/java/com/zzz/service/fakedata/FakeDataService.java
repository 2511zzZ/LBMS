package com.zzz.service.fakedata;

import com.zzz.dao.FakeDataDao;
import com.zzz.fakedata.AnchorGenerator;
import com.zzz.fakedata.FakeDataConfig;
import com.zzz.fakedata.SysUserGenerator;
import com.zzz.model.AnchorTipOff;
import com.zzz.model.HistoryDatas.AnchorHistoryData;
import com.zzz.model.OnlineDatas.*;
import com.zzz.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FakeDataService {

    @Autowired
    FakeDataDao fakeDataDao;

    public void insertAnchors(){
        fakeDataDao.insertAnchors(AnchorGenerator.fakeAnchors());
    }

    public void insertUserDetails(){
        fakeDataDao.insertUserDetails(SysUserGenerator.fakeUserDetails());
    }

    public void insertUsers(){
        fakeDataDao.insertUsers(SysUserGenerator.fakeUsers(fakeDataDao.queryAllUserDetails()));
    }

    public void insertStructures(){
        // 查sys_user表 根据role插入structure
        List<SysUser> userList = fakeDataDao.queryAllUsers();
        int totalId = 1;
        int branchId = 1;
        int groupId = 1;
        int teamId = 1;
        for(SysUser user: userList){
            if(user.getRole()==1){
                fakeDataDao.insertIntoTotal(totalId, "全部游戏", user.getEmployeeId());
                totalId++;
            }else if(user.getRole()==2){
                fakeDataDao.insertIntoBranch(branchId, "分支"+branchId, user.getEmployeeId());
                branchId++;
            }else if(user.getRole()==3){
                fakeDataDao.insertIntoGroup(groupId, "大组"+groupId, user.getEmployeeId());
                groupId++;
            }else if(user.getRole()==4){
                fakeDataDao.insertIntoTeam(teamId, "小组"+teamId, user.getEmployeeId());
                teamId++;
            }
        }
    }

    public void insertStrucManage(){
        for(int i=1;i<=FakeDataConfig.anchorNum;i++){
            fakeDataDao.insertStrucManage(i, (i-1)/10+1, (i-1)/100+1, (i-1)/1000+1);
        }
    }

    public void insertRealtimeData(List<AnchorOnlineData> anchorOnlineData) {
        fakeDataDao.insertRealtimeData(anchorOnlineData);
    }

    public void insertTeamRealtimeData(List<TeamOnlineData> teamOnlineData) {
        fakeDataDao.insertTeamRealtimeData(teamOnlineData);
    }

    public void insertGroupRealtimeData(List<GroupOnlineData> groupOnlineData) {
        fakeDataDao.insertGroupRealtimeData(groupOnlineData);
    }

    public void insertBranchRealtimeData(List<BranchOnlineData> branchOnlineData) {
        fakeDataDao.insertBranchRealtimeData(branchOnlineData);
    }

    public void insertTotalRealtimeData(TotalOnlineData totalOnlineData) {
        fakeDataDao.insertTotalRealtimeData(totalOnlineData);
    }

    public void insertAnchorTipOff(List<AnchorTipOff> fakeAnchorTipOffData) {
        fakeDataDao.insertAnchorTipOff(fakeAnchorTipOffData);
    }

    public void insertAnchorHistoryData(List<AnchorHistoryData> anchorHistoryData) {
        fakeDataDao.insertAnchorHistoryData(anchorHistoryData);
    }
}
