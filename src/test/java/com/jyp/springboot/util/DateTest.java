package com.jyp.springboot.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @title: DateTest类
 * @Author jiangyp
 * @Date: 2021/7/13 8:56
 * @Version 1.0
 */
public class DateTest {
    @Test
    public void dateTest(){
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.set(2009, 11, 31);//月份是从0开始的，所以11表示12月
        Date now = ca.getTime();
        ca.add(Calendar.MONTH, -1); //月份减1
        Date lastMonth = ca.getTime(); //结果
        ca.set(2009, 0, 1);//月份是从0开始的，所以11表示12月
        ca.add(Calendar.DATE, -1); //月份减1
        Date lastDate = ca.getTime(); //结果
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sf.format(now));
        System.out.println(sf.format(lastMonth));
        System.out.println(sf.format(lastDate));
    }
}  