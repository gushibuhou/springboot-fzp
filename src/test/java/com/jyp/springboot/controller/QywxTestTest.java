package com.jyp.springboot.controller;

import cn.hutool.http.HttpUtil;
import com.jyp.springboot.util.Md5Utils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: QywxTestTest类
 * @Author jiangyp
 * @Date: 2021/12/7 9:33:24
 * @Version 1.0
 */
public class QywxTestTest {


    @Test
    public void sendByPhones() {
        Map map = new HashMap();
        map.put("phones","13960022516");
        map.put("content","微信到账123456789元");
        map.put("qywxName","SB");
        map.put("platformId","10");
        String key = "yzzf7brulm4p03npdb5a99x4bluse4ds44rqq89dcaaqcdg13b3srcq3c3bq2r2v";
        String sign = Md5Utils.getSign(map, key);
        map.put("sign",sign);

        String result = HttpUtil.post("http://10.1.7.95:8180/qywx/sendByPhones", map,-1);
//        String result = HttpUtil.post("http://10.10.113.76:8180/qywx/sendByPhones", map,-1);
        System.out.println(result);
    }

    @Test
    public void sendByUserIds() {
        Map map = new HashMap();
        map.put("userIds","jiangyuanpeng");
        map.put("content","微信到账123456789元");
        map.put("qywxName","SB");
        map.put("platformId","10");
        String key = "yzzf7brulm4p03npdb5a99x4bluse4ds44rqq89dcaaqcdg13b3srcq3c3bq2r2v";
        String sign = Md5Utils.getSign(map, key);
        map.put("sign",sign);

        String result = HttpUtil.post("http://10.10.113.76:8180/qywx/sendByUserIds", map,-1);
        System.out.println(result);
    }


    @Test
    public void batchSendSms() {
        Map map = new HashMap();
        map.put("mobiles","18759492032");
        map.put("content","微信到账123456789元");
        map.put("platformId","10");
        String key = "yzzf7brulm4p03npdb5a99x4bluse4ds44rqq89dcaaqcdg13b3srcq3c3bq2r2v";
        String sign = Md5Utils.getSign(map, key);
        map.put("sign",sign);

        String result = HttpUtil.post("http://10.10.113.76:8180/sms/batchSendSms", map,-1);
        System.out.println(result);
    }
}  