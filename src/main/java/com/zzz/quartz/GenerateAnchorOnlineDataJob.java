package com.zzz.quartz;

import com.zzz.fakedata.RealtimeDataGenerator;
import com.zzz.model.OnlineDatas.AnchorOnlineData;
import com.zzz.service.AnchorService;
import com.zzz.service.fakedata.FakeDataService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class GenerateAnchorOnlineDataJob implements Job{

    @Autowired
    AnchorService anchorService;
    @Autowired
    FakeDataService fakeDataService;

    @Override
    public void execute(JobExecutionContext arg0) {
        before();
        System.out.println("测试：anchor的总数为：" + anchorService.getTotalNum());
        // TODO 业务
        Integer[] anchorIds = {111, 222, 333, 444};
        List<Integer> anchorIdList = Arrays.asList(anchorIds);

        List<AnchorOnlineData> anchorOnlineData = RealtimeDataGenerator.fakeAnchorOnlineData(anchorIdList);


        fakeDataService.insertRealtimeData(anchorOnlineData);
//        fakeDataService.insertRealtimeData(RealtimeDataGenerator.fakeAnchorOnlineData(anchorService.getAnchorIds()));
        after();
    }

    private void before(){
        System.out.println("开始生成anchor实时数据");
    }

    private void after(){
        System.out.println("anchor实时数据生成完成");
    }

}