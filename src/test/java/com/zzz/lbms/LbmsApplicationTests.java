package com.zzz.lbms;

import com.zzz.quartz.QuartzScheduler;
import com.zzz.service.ReportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

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
    void reportTest(){
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, List> excl = new HashMap<>();
        List<String> rowlist = new ArrayList<>();
        rowlist.add("姓名");
        rowlist.add("观看人数");
        rowlist.add("礼物");
        excl.put("rowList", rowlist);
        excl.put("dataList", list);
        for (int i=0;i<5;i++){
            HashMap<String,String> map=new HashMap<>();
            map.put("姓名","xxx");
            map.put("观看人数",i*1000+"");
            map.put("礼物",i*100+"");
            list.add(map);
        }

        //生成文件
        HSSFWorkbook excel = Reporter.getExcel(excl, "sheet_name");

        //保存在本地
        File file = new File("D:\\");
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(new File(file, "testReport.xls"));
            excel.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void reportServiceTest(){
        System.out.println(reportService.getExcelFile("branch", 1, new Date()));
    }
}
