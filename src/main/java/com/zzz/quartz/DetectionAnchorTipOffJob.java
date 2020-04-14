package com.zzz.quartz;

import com.zzz.controller.websocket.WebSocketServer;
import com.zzz.service.AlarmService;
import com.zzz.service.AnchorService;
import com.zzz.service.StructureService;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetectionAnchorTipOffJob implements Job{

    @Autowired
    AnchorService anchorService;

    @Autowired
    AlarmService alarmService;

    @Autowired
    StructureService structureService;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext arg0) {
        before();

        Date datetime = Timestamp.valueOf(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE)
                        .format(new Date())
        );
        int threshold = alarmService.getThreshold();
        int max_tip_num = alarmService.getMaxTipNum();

        for(int anchorId:anchorService.getAnchorIds()){
            int sumTipNum = alarmService.getSumTipNum(anchorId, datetime, threshold);
            if(sumTipNum >= max_tip_num){
                // 向anchorId的直接上级发送websocket警报
                String toUserId = String.valueOf(structureService.getEmployeeIdByAnchor(anchorId));
                String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datetime);
                String message = dateStr + ":" + anchorId + "号主播收到了"+sumTipNum+"次警报";
                WebSocketServer.sendInfo(message,toUserId);
            }
        }

        after();
    }

    private void before(){
        System.out.println("开始检测举报数");
    }

    private void after(){
        System.out.println("举报数检测完成");
    }

}