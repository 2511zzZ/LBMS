package com.zzz.lbms;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static Date dateStrToData(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        return simpleDateFormat.parse(dateStr);
    }

    public static String generateAlarmId(int anchorId, Date startTime){
        // anchorId + minute唯一确定一条警报
        // 202004151332 00004
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        int year = cal.get(java.util.Calendar.YEAR);
        String yearStr = String.valueOf(year%100);
        int month = cal.get(java.util.Calendar.MONTH) + 1; //月份从0开始
        int day = cal.get(java.util.Calendar.DATE);//获取日
        int hour = cal.get(Calendar.HOUR_OF_DAY);//小时（calendar.HOUR 12小时制，calendar.HOUR_OF_DAY 24小时）
        int minute = cal.get(java.util.Calendar.MINUTE);//分

        // 也可以使用MySQL的LPAD函数实现左填充
        String dateStr = yearStr + datePaddingZero(month) + datePaddingZero(day) + datePaddingZero(hour) + datePaddingZero(minute);

        String anchorStr = anchorIdPaddingZero(anchorId);

        return dateStr + anchorStr;

    }

    public static String generateExcelName(String level, Integer levelId, Date datetime){
        Calendar cal = Calendar.getInstance();
        cal.setTime(datetime);
        String year = String.valueOf(cal.get(java.util.Calendar.YEAR)%100);
        String month = datePaddingZero(cal.get(java.util.Calendar.MONTH) + 1);
        return year + month + levelId + getLevelName(level) + "报表";

    }

    public static String DoMD5(String password){
        String encryptedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            encryptedPassword = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return encryptedPassword;
    }

    private static String datePaddingZero(int i){
        return i>=10 ? ""+i : "0"+i;
    }

    private static String anchorIdPaddingZero(int anchorId){
        if(anchorId<10){
            return "0000" + anchorId;
        }
        if(anchorId<100){
            return "000" + anchorId;
        }
        if(anchorId<1000){
            return "00" + anchorId;
        }
        if(anchorId<10000){
            return "0" + anchorId;
        }
        return anchorId+"";
    }

    private static String getLevelName(String level){
        if(level.equals("total")){return "总览";}
        if(level.equals("branch")){return "分区";}
        if(level.equals("group")){return "大组";}
        if(level.equals("team")){return "小组";}
        if(level.equals("anchor")){return "主播";}
        return "";
    }
}
