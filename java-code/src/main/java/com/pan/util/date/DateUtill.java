package com.pan.util.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtill {


    public LocalDate toDay = LocalDate.now();
    /***
     * 判断是否当天日期
     */

    public boolean isToday(String time , SimpleDateFormat sdf){
        return  false;
    }

    public static void main(String[] args) {



        for (int i=0;i<1000;i++){
            System.out.println(Math.random() + String.valueOf(System.currentTimeMillis()));
        }
    }
}
