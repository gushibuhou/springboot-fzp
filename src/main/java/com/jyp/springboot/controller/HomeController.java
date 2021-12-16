package com.jyp.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title: HomeController类
 * @Author jiangyp
 * @Date: 2021/8/3 11:03
 * @Version 1.0
 */
@Controller
public class HomeController {

    @GetMapping("/homehtml")
    public String homehtml(){
        return "home";
    }
    @GetMapping("/home")
    public @ResponseBody String home(){
        return "home";
    }
}  