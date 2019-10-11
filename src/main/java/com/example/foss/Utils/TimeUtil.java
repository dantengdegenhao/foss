package com.example.foss.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String getNowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }
}
