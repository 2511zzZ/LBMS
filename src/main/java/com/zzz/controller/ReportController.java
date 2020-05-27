package com.zzz.controller;

import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {

    @Resource
    ReportService reportService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Results<String> getReportFile(@RequestParam String level,
                                         @RequestParam Integer levelId,
                                         @RequestParam String datetimeStr,
                                         @RequestParam String type,
                                         @RequestParam String password) throws ParseException {

        Date datetime = new SimpleDateFormat("yyyy-MM-dd").parse(datetimeStr);

        if (type.equals("excel")){
            return Results.success(ResponseCode.SUCCESS, reportService.getExcelFile(level, levelId, datetime));
        }
        if (type.equals("pdf")){
            return Results.success(ResponseCode.SUCCESS, reportService.getPdfFile(level, levelId, datetime, password));
        }
        return Results.failure(400, "不支持的参数类型");
    }

}
