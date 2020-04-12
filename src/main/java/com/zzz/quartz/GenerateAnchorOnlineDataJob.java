package com.zzz.quartz;

import com.zzz.service.AnchorService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class GenerateAnchorOnlineDataJob implements Job{

    @Autowired
    AnchorService anchorService;

    @Override
    public void execute(JobExecutionContext arg0) {
        before();
        System.out.println("测试：anchor的总数为：" + anchorService.getTotalNum());
        // TODO 业务
        after();
    }

    private void before(){
        System.out.println("开始生成anchor数据");
    }

    private void after(){
        System.out.println("anchor数据生成完成");
    }

}