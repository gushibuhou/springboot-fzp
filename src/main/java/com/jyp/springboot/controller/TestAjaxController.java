package com.jyp.springboot.controller;

import com.jyp.springboot.dao.AccountDao;
import com.jyp.springboot.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/12
 * Time: 10:41
 */
@Controller
@RequestMapping("/test_ajax")
public class TestAjaxController {

    @Autowired
    AccountDao accountDao;

    // 访问 test_ajax.jsp 参考路径: http://localhost:8082/springboot/test_ajax/index
    @RequestMapping("/index")
    public String hello() {
        return "test_ajax";
    }

    //ajax,单参数访问,返回boolean
    @RequestMapping("/checkAccount")
    @ResponseBody
    public boolean checkAccount(String accountId) {
        Account account = accountDao.findById(Integer.valueOf(accountId)).orElse(null);
        boolean flag = false;
        if (null != account) {
            flag = true;
        }

        return flag;
    }

    //ajax,简单多参数访问,返回list
    @RequestMapping("/register")
    @ResponseBody
    public List<Account> register(String accountId, String accountName) {
        List<Account> list = accountDao.findAccountByIdOrName(accountId, accountName);
        return list;
    }
}
