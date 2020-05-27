package com.zzz.controller;

import com.itextpdf.text.DocumentException;
import com.zzz.exception.ForBiddenException;
import com.zzz.model.SysUser;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.PermissionService;
import com.zzz.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {

    @Resource
    ReportService reportService;
    @Resource
    PermissionService permissionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Results<String> getReportFile(@RequestParam String level,
                                         @RequestParam Integer levelId,
                                         @RequestParam String datetimeStr,
                                         @RequestParam String type,
                                         @RequestParam String password) throws ParseException, ForBiddenException, IOException, DocumentException {

        Date datetime = new SimpleDateFormat("yyyy-MM-dd").parse(datetimeStr);
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        if(!permissionService.hasPermission(user, level, levelId)){
            throw new ForBiddenException("权限不足");
        }

        if (type.equals("excel")){
            return Results.success(ResponseCode.SUCCESS, reportService.getExcelFile(level, levelId, datetime));
        }
        if (type.equals("pdf")){
            return Results.success(ResponseCode.SUCCESS, reportService.getPdfFile(level, levelId, datetime, password));
        }
        return Results.failure(400, "不支持的参数类型");
    }

}
