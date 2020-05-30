package com.zzz.lbms;

import com.zzz.dao.AnchorDao;
import com.zzz.fakedata.HistoryDataGenerator;
import com.zzz.model.HistoryDatas.*;
import com.zzz.quartz.QuartzScheduler;
import com.zzz.service.*;
import com.zzz.service.fakedata.FakeDataService;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// 由于集成了websocket,需要给Test配置webEnvironment
// 每次测试都会选用一个随即可用的端口模拟启动一个完整的服务器
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FakeData {

    @Autowired
    FakeDataService fakeDataService;
    @Autowired
    QuartzScheduler quartzScheduler;
    @Autowired
    AnchorDao anchorDao;
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
    @Autowired
    AnchorService anchorService;

    @Test
    void insertAnchors(){
        fakeDataService.insertAnchors();
    }

    @Test
    void insertUserDetails(){
        fakeDataService.insertUserDetails();
    }

    @Test
    void insertUsers(){
        fakeDataService.insertUsers();
    }

    @Test
    void insertStructures() {fakeDataService.insertStructures(); }

    @Test
    void insertStrucManage() {fakeDataService.insertStrucManage();}

    @Test
    void OnlineDataGenerate() throws SchedulerException, InterruptedException {
        System.out.println("定时任务开始");
        // 包含主播实时数据生成、警报检测与生成、历史数据的计算三个任务
        quartzScheduler.startJob();
        // 线程等待时间至少大于一分钟，否则可能线程可能在定时任务尚未执行时shutdown
        Thread.sleep(60*60*1000);
    }

    @Test
    void GenerateHistoryData() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -2);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        while(calendar.getTime().compareTo(new Date()) < 0){

            Date datetime = calendar.getTime();
            System.out.println(datetime);

            List<AnchorHistoryData> anchorHistoryData = HistoryDataGenerator.fakeAnchorHistoryData(anchorService.getAnchorIds(), datetime);
            fakeDataService.insertAnchorHistoryData(anchorHistoryData);
            //插入team数据
            List<TeamHistoryData> teamHistoryData = new ArrayList<>();
            for(int teamId:levelService.getTeamIds()){
                teamHistoryData.add(teamDataService.calHistory(teamId, datetime));
            }
            fakeDataService.insertTeamHistoryData(teamHistoryData);

            // 插入group数据
            List<GroupHistoryData> groupHistoryData = new ArrayList<>();
            for(int groupId:levelService.getGroupIds()){
                groupHistoryData.add(groupDataService.calHistory(groupId, datetime));
            }
            fakeDataService.insertGroupHistoryData(groupHistoryData);

            // 插入branch数据
            List<BranchHistoryData> branchHistoryData = new ArrayList<>();
            for(int branchId:levelService.getBranchIds()){
                branchHistoryData.add(branchDataService.calHistory(branchId, datetime));
            }
            fakeDataService.insertBranchHistoryData(branchHistoryData);

            // 插入total数据
            TotalHistoryData totalHistoryData = totalDataService.calHistory(levelService.getTotal().getTotalId(), datetime);
            fakeDataService.insertTotalHistoryData(totalHistoryData);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    @Test
    void fakeAnchorOnlineData() {
        List<Integer> anchorIds = anchorDao.getAnchorIds();
        for(int anchorId:anchorIds){
            int isOnline = Math.random() < 0.75 ? 1 : 0;
            anchorDao.fakeAnchorOnlineData(anchorId, isOnline, new Date());
        }
    }
}
