package com.zzz.lbms;

import com.itextpdf.text.DocumentException;
import com.zzz.quartz.QuartzScheduler;
import com.zzz.service.ReportService;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LbmsApplicationTests {

    @Autowired
    QuartzScheduler quartzScheduler;
    @Autowired
    ReportService reportService;

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
        Thread.sleep(60 * 60 * 1000);
    }

    @Test
    void reportServiceTest(){
        System.out.println(reportService.getExcelFile("branch", 1, new Date()));
    }

    @Test
    void reportServiceTest2() throws IOException, DocumentException {
        System.out.println(reportService.getPdfFile("branch", 1, new Date(), ""));
    }

}
