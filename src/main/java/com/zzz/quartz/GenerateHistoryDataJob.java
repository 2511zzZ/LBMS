package com.zzz.quartz;


import com.zzz.model.HistoryDatas.AnchorHistoryData;
import com.zzz.service.AnchorDataService;
import com.zzz.service.AnchorService;
import com.zzz.service.fakedata.FakeDataService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class GenerateHistoryDataJob implements Job {

    @Autowired
    AnchorService anchorService;

    @Autowired
    FakeDataService fakeDataService;

    @Autowired
    AnchorDataService anchorDataService;

    @Override
    public void execute(JobExecutionContext arg0) {

        before();

        Date date = new Date();

        List<AnchorHistoryData> anchorHistoryData = anchorDataService.calAnchorHistoryFromRealtime(date);

        fakeDataService.insertAnchorHistoryData(anchorHistoryData);

        after();

    }

    private void before(){
        System.out.println("开始计算历史数据");
    }

    private void after(){
        System.out.println("历史数据计算完成");
    }

}