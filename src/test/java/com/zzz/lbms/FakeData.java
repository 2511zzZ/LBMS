package com.zzz.lbms;

import com.zzz.quartz.QuartzScheduler;
import com.zzz.service.fakedata.FakeDataService;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FakeData {

    @Autowired
    FakeDataService fakeDataService;
    @Autowired
    QuartzScheduler quartzScheduler;

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
    void quartzTest() throws SchedulerException, InterruptedException {
        System.out.println("定时任务开始");
        quartzScheduler.startJob();
        // 线程等待时间必须>=一分钟
        // 否则可能线程可能在定时任务尚未执行时shutdown
        Thread.sleep(60000);
    }

}
