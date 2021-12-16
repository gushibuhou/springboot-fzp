package com.jyp.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/14
 * Time: 9:09
 */
@Controller
public class TestJspController {
    // 访问 test_ajax.jsp 参考路径: http://localhost:8082/springboot/test_ajax
    @RequestMapping("/test_ajax")
    public String testAjax() {
        return "test_ajax";
    }

    // 访问 test_ajax.jsp 参考路径: http://localhost:8082/springboot/test_layui
    @RequestMapping("/test_layui")
    public String testLayui() {
        return "test_layui";
    }
}
