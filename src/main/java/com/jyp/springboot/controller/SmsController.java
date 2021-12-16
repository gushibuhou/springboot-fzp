package com.jyp.springboot.controller;

import com.mascloud.sdkclient.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
/**
 * 移动的短信,登录失败,被停了
 */

/**
 * @title: SmsController类
 * @Author jiangyp
 * @Date: 2021/9/7 10:30
 * @Version 1.0
 */
@Controller
@RequestMapping("/sms")
@Slf4j
public class SmsController {

    @RequestMapping("/send")
    @ResponseBody
    public String send(String[] mobiles, String content) {
        Client client =  Client.getInstance();
        // 正式环境IP，登录验证URL，用户名，密码，集团客户名称
        boolean isLoggedin = client.login("http://112.35.4.197:15000",
                "jyp",
                "Dx123456!@#",
                "福建三宝物联科技有限公司");
        System.out.println("登录结果: " + isLoggedin);
        int sendResult = client. sendDSMS (
                mobiles,
                content,
                "",
                1,
                "YGNnsn9sm",
                null,
                true
        );
        System.out.println("推送结果: " + sendResult);
        String sendString = null;
        switch (sendResult){
            case 1:
                sendString = "短信发送成功";
                break;
            case 101:
                sendString = "短信内容为空";
                break;
            case 102:
                sendString = "号码数组为空";
                break;
            case 103:
                sendString = "号码数组为空数组";
                break;
            case 104:
                sendString = "批次短信的号码中存在非法号码， SDK带有号码的验证处理。";
                break;
            case 105:
                sendString = "未进行身份认证或认证失败，用户请确认输入的用户名，密码和企业名是否正确。";
                break;
            case 106:
                sendString = "网关签名为空， 用户需要填写网关签名编号";
                break;
            case 107:
                sendString = "其它错误";
                break;
            case 108:
                sendString = "JMS异常，用户侧网络问题。需要检查防火墙配置和网络连通性，看是否能够与云MAS平台正常连接。";
                break;
            case 109:
                sendString = "批次短信号码中存在重复号码";
                break;
            case 110:
                sendString = "发送的号码在一分钟之内全部内容（包括，号码，模板ID,模板内容都相同）都重复发送";
                break;
            case 111:
                sendString = "扩展码错误,扩展码只能是15位以内数字或空字符串";
                break;
            case 112:
                sendString = "签名错误或普通短信不允许使用模板短信的签名编码";
                break;
            default:
                sendString = "未知错误";
                break;
        }
        return sendString;

    }

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(String url,String userAccount, String password,String ecname) {
        Client client =  Client.getInstance();
        // 正式环境IP，登录验证URL，用户名，密码，集团客户名称
        /*boolean login = client.login("http://112.35.4.197:15000",
                "jyp",
                "Dx123456!@#",
                "福建三宝物联科技有限公司");
                */
        boolean isLoggedin = client.login(url,userAccount,password,ecname);
        if( isLoggedin ) {
            System.out.println( "Login successed" );
        } else {
            System.out.println( "Login failed" );
        }
        return isLoggedin;
    }
}

