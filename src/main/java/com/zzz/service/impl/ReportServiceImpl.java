package com.zzz.service.impl;

import com.itextpdf.text.DocumentException;
import com.zzz.lbms.Reporter;
import com.zzz.lbms.Utils;
import com.zzz.lbms.pdf.Excel2Pdf;
import com.zzz.lbms.pdf.ExcelObject;
import com.zzz.model.HistoryDatas.*;
import com.zzz.service.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ReportServiceImpl implements ReportService {

    @Resource
    TotalDataService totalDataService;
    @Resource
    BranchDataService branchDataService;
    @Resource
    GroupDataService groupDataService;
    @Resource
    TeamDataService teamDataService;
    @Resource
    AnchorDataService anchorDataService;

    private static String path = "D:\\Projects\\LBMS\\src\\main\\resources\\static\\reportFiles\\";

    @Override
    public String getExcelFile(String level, Integer levelId, Date datetime) {
        // todo 根据项目根目录生成path
        return path + generateExcelAndGetName(level, levelId, datetime);
    }

    @Override
    public String getPdfFile(String level, Integer levelId, Date datetime, String password) throws IOException, DocumentException {

        String excelFileName = generateExcelAndGetName(level, levelId, datetime);
        String fileIn = path + excelFileName;

        InputStream in = new FileInputStream(fileIn);
//        this.getClass().getResourceAsStream(excelFileName);
        Excel2Pdf excel2Pdf = new Excel2Pdf(Collections.singletonList(new ExcelObject(in)), new FileOutputStream(fileOut(fileIn)));
        excel2Pdf.convert(password);
//        String pdfFileName = ("D:\\Projects\\LBMS\\target\\classes\\static\\reportFiles\\" + excelFileName).replace("xls", "pdf");
        return fileIn.replace("xls", "pdf");
    }

    private String generateExcelAndGetName(String level, Integer levelId, Date datetime){

        Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);



        // todo 若文件已存在则直接返回
        String fileName = Utils.generateExcelName(level, levelId, datetime) + ".xls";
        logger.info("生成Excel文件[{}]", fileName);

        Date firstDay = getFirstAndLastDayOfMonth(datetime).first;
        Date lastDay = getFirstAndLastDayOfMonth(datetime).last;

        Map<String, List> excl = new HashMap<>();
        if(level.equals("total")){
            excl = getExcelMap(totalDataService.getTotalHistoryData(levelId, firstDay, lastDay, 1, 31), level);
        }
        if(level.equals("branch")){
            excl = getExcelMap(branchDataService.getBranchHistoryData(levelId, firstDay, lastDay, 1, 31), level);
        }
        if(level.equals("group")){
            excl = getExcelMap(groupDataService.getGroupHistoryData(levelId, firstDay, lastDay, 1, 31), level);
        }
        if(level.equals("team")){
            excl = getExcelMap(teamDataService.getTeamHistoryData(levelId, firstDay, lastDay, 1, 31), level);
        }
        if(level.equals("anchor")){
            excl = getExcelMap(anchorDataService.getAnchorHistoryData(levelId, firstDay, lastDay, 1, 31), level);
        }

        HSSFWorkbook excel = Reporter.getExcel(excl, "历史数据");

        //保存在本地
        File file = new File(path);
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(new File(file, fileName));
            excel.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    private Map<String, List> getExcelMap(List historyDataList, String level) {

        Map<String, List> excl = new HashMap<>();
        List<Map> dataMapList = new ArrayList<>();
        for(Object obj: historyDataList){
            HistoryData historyData = (HistoryData)obj;
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("日期", new SimpleDateFormat("yyyy-MM-dd").format(historyData.getDate()));
            dataMap.put("平均观看人数", String.valueOf(historyData.getWatchNum()));
            dataMap.put("礼物金额", String.valueOf(historyData.getGift()));
            dataMap.put("弹幕数", String.valueOf(historyData.getBulletScreen()));
            dataMap.put("最大观看人数", String.valueOf(historyData.getMaxWatchNum()));

            // five pieces of shit
            if(level.equals("total")){
                TotalHistoryData totalHistoryData = (TotalHistoryData)obj;
                dataMap.put("编号", String.valueOf(totalHistoryData.getTotalId()));
            }
            if(level.equals("branch")){
                BranchHistoryData branchHistoryData = (BranchHistoryData)obj;
                dataMap.put("编号", String.valueOf(branchHistoryData.getBranchId()));
            }
            if(level.equals("group")){
                GroupHistoryData groupHistoryData = (GroupHistoryData)obj;
                dataMap.put("编号", String.valueOf(groupHistoryData.getGroupId()));
            }
            if(level.equals("team")){
                TeamHistoryData teamHistoryData = (TeamHistoryData)obj;
                dataMap.put("编号", String.valueOf(teamHistoryData.getTeamId()));
            }
            if(level.equals("anchor")){
                AnchorHistoryData anchorHistoryData = (AnchorHistoryData) obj;
                dataMap.put("编号", String.valueOf(anchorHistoryData.getAnchorId()));
            }
            dataMapList.add(dataMap);
        }
        String[] columnArray = {"编号","日期","平均观看人数","礼物金额","弹幕数","最大观看人数"};
        List<String> columnList = Arrays.asList(columnArray);
        excl.put("rowList", columnList);
        excl.put("dataList", dataMapList);
        return excl;
    }

    private BackObject getFirstAndLastDayOfMonth(Date datetime){
        BackObject backObject = new BackObject();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        backObject.first = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        backObject.last = calendar.getTime();
        return backObject;
    }

    static class BackObject{
        public Date first;
        public Date last;
    }

    private File fileOut(String fileIn) throws UnsupportedEncodingException {
//        String uri = this.getClass().getResource(fileIn).getPath();
//        String fileOut = uri.replaceAll(".xls$|.xlsx$", ".pdf");
//        System.out.println(fileIn);
//        // utf-8编码转化为汉字
//        Pattern pattern = Pattern.compile("(?<=1).*(?=.)");
//        Matcher m = pattern.matcher(fileOut);
//        while(m.find()){
//            fileOut = m.replaceAll(URLDecoder.decode(m.group(), "UTF-8"));
//        }
//        System.out.println(fileOut);
        String fileOut = fileIn.replace(".xls", ".pdf");
        return new File(fileOut);
    }
}
