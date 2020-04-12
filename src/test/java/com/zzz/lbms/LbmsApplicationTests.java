package com.zzz.lbms;

import com.zzz.quartz.QuartzScheduler;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class LbmsApplicationTests {

    @Autowired
    QuartzScheduler quartzScheduler;

    @Test
    void contextLoads() {
    }

    @Test
    void nameTest(){

        String[] nameArray = {"1", "2", "3", "4", "4", "4", "4", "4", "4", "4"};
        Set<String> names = new HashSet<>();
        for(int i=0;i<4;i++) {

            String name = nameArray[(int)(Math.random()*10)];

            while (names.contains(name)) {
                name = nameArray[(int)(Math.random()*10)];
            }
            names.add(name);
            System.out.println(name);
        }
    }

    @Test
    void quartzTest() throws SchedulerException, InterruptedException {
        System.out.println("定时任务开始");
        quartzScheduler.startJob();
        Thread.sleep(2000);
    }
}
