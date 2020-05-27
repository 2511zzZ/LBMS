package com.zzz.lbms;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

public class Reporter {
    /**
     * 创建excel表格
     *
     * @param tableName sheet表名
     * @return HSSFWorkbook
     */
    public static HSSFWorkbook getExcel(Map<String, List> resultMap, String tableName) {

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet(tableName);
        //设置表头
        //创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row = sheet.createRow(0);
        //创建单元格并设置单元格内容

        List rowList = resultMap.get("rowList");
        List dataList = resultMap.get("dataList");
        System.out.println("数据条数："+dataList.size());

        //插入表头单元格内容,表头数据
        for (int i = 0; i < rowList.size(); i++) {
            row.createCell(i).setCellValue(rowList.get(i).toString().trim());
        }
        //插入行数据
        for (int i = 0; i < dataList.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            Map<String, String> map2 = (Map<String, String>) dataList.get(i);
            for (Map.Entry<String, String> en : map2.entrySet()) {
                for (int j = 0; j < rowList.size(); j++) {
                    if (rowList.get(j).equals(en.getKey())) {
                        if (en.getValue().equals("")) {
                            row1.createCell(j).setCellValue("");
                        } else {
                            row1.createCell(j).setCellValue(en.getValue());
                        }
                    }
                }

            }
        }
        return wb;
    }

}
