package com.zzz.quartz;

import com.zzz.controller.websocket.WebSocketServer;
import com.zzz.lbms.Utils;
import com.zzz.model.Anchor;
import com.zzz.model.AnchorAlarm;
import com.zzz.model.AnchorAlarmTrans;
import com.zzz.model.SysUserDetails;
import com.zzz.service.AlarmService;
import com.zzz.service.AnchorService;
import com.zzz.service.StructureService;
import com.zzz.service.SysUserService;
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

    @Autowired
    SysUserService userService;

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
            // 获取最近举报总数
            int sumTipNum = alarmService.getSumTipNum(anchorId, datetime, threshold);
            if(sumTipNum >= max_tip_num){
                // anchorId对于的小组长id
                int superiorEmployeeId = structureService.getEmployeeIdByAnchor(anchorId);
                String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datetime);
                String message = dateStr + ":" + anchorId + "号主播收到了"+sumTipNum+"次警报";
                // 1:待处理 2:已封禁 3:已解除 4:超时
                int status = 1;
                Anchor anchor = anchorService.getAnchor(anchorId);
                String reason = anchorId + "号主播" + anchor.getName() + "被频繁举报";
                String alarmId = Utils.generateAlarmId(anchorId, datetime);
                // 插入警报基础信息表
                alarmService.insertAlarm(new AnchorAlarm(alarmId, anchorId, anchor.getName(), reason, status, datetime,
                        null, null, null, null
                ));
                // 插入警报传输信息表
                SysUserDetails currentUser = userService.getUser(superiorEmployeeId);
                alarmService.insertAlarmTrans(new AnchorAlarmTrans(
                        alarmId, superiorEmployeeId,
                        currentUser.getName(),
                        currentUser.getRole(),
                        0,0,
                        datetime

                ));

                // WebSocket主动推送
                WebSocketServer.sendInfo(message,String.valueOf(superiorEmployeeId));
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