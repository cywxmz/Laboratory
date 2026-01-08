package cn.navg.laboratory.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//获取当前时间的方法
public class GetNowDataTime {
    //格式字符串
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    // 1. 获取当前本地日期时间
    private static final LocalDateTime currentTime = LocalDateTime.now();
    
    // 2. 定义格式化模板（yyyy-MM-dd HH:mm:ss）
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    
    // 3. 格式化当前时间为字符串
    public static final String formattedTime = currentTime.format(formatter);
    
    
    //第二种格式化时间
    // 4. 格式化当前时间为字符串（yyyy-MM-dd）
    public static final String formattedDate = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
    public static  void  main(String[] args) {
    }
}
