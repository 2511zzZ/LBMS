// 这个类用于配置定时任务随SpringBoot的启动而启动

//package com.zzz.quartz;
//
//import org.quartz.SchedulerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.ContextRefreshedEvent;
//
//@Configuration
//public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {
//
//    @Autowired
//    private QuartzScheduler quartzScheduler;
//
//    /**
//     * 初始启动quartz
//     */
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        try {
//            quartzScheduler.startJob();
//            System.out.println("任务已经启动...");
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//}
