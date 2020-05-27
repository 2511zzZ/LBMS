package com.zzz.service;


import java.util.Date;

public interface ReportService {

    String getExcelFile(String level, Integer levelId, Date datetime);

    String getPdfFile(String level, Integer levelId, Date datetime, String password);
}
