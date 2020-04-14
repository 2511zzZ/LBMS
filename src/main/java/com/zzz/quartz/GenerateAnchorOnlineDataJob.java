package com.zzz.quartz;

import com.zzz.fakedata.RealtimeDataGenerator;
import com.zzz.model.OnlineDatas.BranchOnlineData;
import com.zzz.model.OnlineDatas.GroupOnlineData;
import com.zzz.model.OnlineDatas.TeamOnlineData;
import com.zzz.model.OnlineDatas.TotalOnlineData;
import com.zzz.service.*;
import com.zzz.service.fakedata.FakeDataService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GenerateAnchorOnlineDataJob implements Job{

    @Autowired
    AnchorService anchorService;

    @Autowired
    FakeDataService fakeDataService;

    @Autowired
    LevelService levelService;

    @Autowired
    TeamDataService teamDataService;

    @Autowired
    GroupDataService groupDataService;

    @Autowired
    BranchDataService branchDataService;

    @Autowired
    TotalDataService totalDataService;

    @Override
    public void execute(JobExecutionContext arg0) {
        before();


        // 删除毫秒，否则存入数据库时可能导致进位
        Date datetime = Timestamp.valueOf(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE)
                        .format(new Date())
        );

        // 随便测一组数据
//        Integer[] anchorIds = {10, 11, 12, 13};
//        List<Integer> anchorIdList = Arrays.asList(anchorIds);
//        List<AnchorOnlineData> anchorOnlineData = RealtimeDataGenerator.fakeAnchorOnlineData(anchorIdList, datetime);
//        fakeDataService.insertRealtimeData(anchorOnlineData);

        // 插入anchor举报数据
        fakeDataService.insertAnchorTipOff(RealtimeDataGenerator.fakeAnchorTipOffData(anchorService.getAnchorIds(), datetime));

        // 插入anchor数据
        fakeDataService.insertRealtimeData(RealtimeDataGenerator.fakeAnchorOnlineData(anchorService.getAnchorIds(), datetime));

        //插入team数据
        List<TeamOnlineData> teamOnlineData = new ArrayList<>();
        for(int teamId:levelService.getTeamIds()){
            teamOnlineData.add(teamDataService.calTeamRealtimeDataFromAnchors(teamId, datetime));
        }
        fakeDataService.insertTeamRealtimeData(teamOnlineData);

        // 插入group数据
        List<GroupOnlineData> groupOnlineData = new ArrayList<>();
        for(int groupId:levelService.getGroupIds()){
            groupOnlineData.add(groupDataService.calGroupRealtimeDataFromTeams(groupId, datetime));
        }
        fakeDataService.insertGroupRealtimeData(groupOnlineData);

        // 插入branch数据
        List<BranchOnlineData> branchOnlineData = new ArrayList<>();
        for(int branchId:levelService.getBranchIds()){
            branchOnlineData.add(branchDataService.calBranchRealtimeDataFromGroups(branchId, datetime));
        }
        fakeDataService.insertBranchRealtimeData(branchOnlineData);

        // 插入total数据
        TotalOnlineData totalOnlineData = totalDataService.calTotalRealtimeDataFromBranchs(levelService.getTotal().getTotalId(), datetime);
        fakeDataService.insertTotalRealtimeData(totalOnlineData);

        after();
    }

    private void before(){
        System.out.println("开始生成实时数据");
    }

    private void after(){
        System.out.println("实时数据生成完成");
    }

}