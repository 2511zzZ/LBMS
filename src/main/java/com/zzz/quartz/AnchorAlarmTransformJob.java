package com.zzz.quartz;

import com.zzz.controller.websocket.WebSocketServer;
import com.zzz.model.AnchorAlarmTrans;
import com.zzz.model.SysUserDetails;
import com.zzz.service.AlarmService;
import com.zzz.service.StructureService;
import com.zzz.service.SysUserService;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AnchorAlarmTransformJob implements Job{

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

        // 系统设置 警报传递阈值
        int trans_time = 10;

        // 找到当前所有未处理警报 其每条警报时间最大的数据
        List<AnchorAlarmTrans> anchorAlarms = alarmService.getUndoAlarms();
        for(AnchorAlarmTrans alarmTrans:anchorAlarms){
            // 如果该时间距离现在超过10分钟
//            System.out.println(alarmTrans.getTime().getTime() - new Date().getTime());
            if((new Date().getTime() - alarmTrans.getTime().getTime()) > trans_time * 60 * 1000){
                // 获取该用户的上级用户
                int superiorEmployeeId = structureService.getStructure(alarmTrans.getEmployeeId()).getSuperior();
                SysUserDetails superior = userService.getUser(superiorEmployeeId);
                // 插入一条新的警报传递信息
                alarmService.insertAlarmTrans(new AnchorAlarmTrans(
                        alarmTrans.getAlarmId(), superiorEmployeeId,
                        superior.getName(),
                        superior.getRole(),
                        0,0,
                        datetime
                ));
                // 发送websocket信息
                String message = "new alarm";
                WebSocketServer.sendInfo(message,String.valueOf(superiorEmployeeId));
            }
        }

        after();
    }

    private void before(){
        System.out.println("开始检测举报传递");
    }

    private void after(){
        System.out.println("举报传递检测完成");
    }

}