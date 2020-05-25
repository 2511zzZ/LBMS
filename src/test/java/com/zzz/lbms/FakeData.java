package com.zzz.lbms;

import com.zzz.dao.AnchorDao;
import com.zzz.quartz.QuartzScheduler;
import com.zzz.service.fakedata.FakeDataService;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void fakeAnchorOnlineData() {
        List<Integer> anchorIds = anchorDao.getAnchorIds();
        for(int anchorId:anchorIds){
            int isOnline = Math.random() < 0.75 ? 1 : 0;
            anchorDao.fakeAnchorOnlineData(anchorId, isOnline, new Date());
        }
    }
}
