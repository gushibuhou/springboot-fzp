package com.jyp.springboot.controller;

import com.jyp.springboot.entity.Account;
import com.jyp.springboot.service.impl.AccountMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/10
 * Time: 11:03
 */
//mybatis
@RestController
@RequestMapping("/account_mybatis")
public class AccountMybatisController {

    @Autowired
    AccountMybatisService accountService;
    //参考路径: http://localhost:8082/springboot/account_mybatis/list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return accountService.findAccountList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id) {
        return accountService.findAccount(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "money", required = true) double money) {
        int t= accountService.update(name,money,id);
        if(t==1) {
            return "success";
        }else {
            return "fail";
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id")int id) {
        int t= accountService.delete(id);
        if(t==1) {
            return "success";
        }else {
            return "fail";
        }

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "money") double money) {

        int t= accountService.add(name,money);
        if(t==1) {
            return "success";
        }else {
            return "fail";
        }

    }

    //参考路径: http://localhost:8082/springboot/account_mybatis/transfer?id1=1&id2=2&money=100
    @RequestMapping(value = "/transfer",method = RequestMethod.GET)
    public void transfer(@RequestParam("id1") int id1,@RequestParam("id2") int id2,@RequestParam("money") String money){
        accountService.transfer(id1,id2,money);
    }
}
