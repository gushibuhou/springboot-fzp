package com.jyp.springboot.controller;

import com.jyp.springboot.dao.AccountDao;
import com.jyp.springboot.entity.Account;
import com.jyp.springboot.service.impl.AccountTranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/9
 * Time: 10:47
 */
//Transactional
@RestController
@RequestMapping("/account_tran")
public class AccountTranController {

    @Autowired
    AccountTranService accountService;
//    参考路径: http://localhost:8082/springboot/accountTran/list
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public  List<Account> getAccounts(){
        return accountService.selectAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  Account getAccountById(@PathVariable("id") int id){
        return accountService.select(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  String updateAccount(@PathVariable("id")int id , @RequestParam(value = "name",required = true)String name,
                                 @RequestParam(value = "money" ,required = true)double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t=accountService.update(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public  String postAccount( @RequestParam(value = "name")String name,
                                @RequestParam(value = "money" )double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        int t= accountService.insert(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }

    }
    //参考路径: http://localhost:8082/springboot/accountTran/transfer?id1=1&id2=2&money=100
    @RequestMapping(value = "/transfer",method = RequestMethod.GET)
    public void transfer(@RequestParam("id1") int id1,@RequestParam("id2") int id2,@RequestParam("money") String money){
        accountService.transfer(id1,id2,money);
    }

}
