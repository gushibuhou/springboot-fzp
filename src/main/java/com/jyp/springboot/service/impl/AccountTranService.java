package com.jyp.springboot.service.impl;

import com.jyp.springboot.dao.AccountTranMapper;
import com.jyp.springboot.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/10
 * Time: 13:46
 */
//Transactional
@Service
public class AccountTranService {
    @Autowired
    AccountTranMapper accountMapper;

    public Account select(int id1) throws RuntimeException{
        return accountMapper.findAccount(1);
    }

    public List<Account> selectAll() throws RuntimeException{
        return accountMapper.selectAll();
    }

    public int insert(Account account) throws RuntimeException{
        return accountMapper.insert(account);
    }

    public int update(Account account) throws RuntimeException{
        return accountMapper.update(account);
    }

    public int updateMoney(Account account) throws RuntimeException{
        return accountMapper.updateMoney(account);
    }

    public int delete(Integer  id) throws RuntimeException{
        return accountMapper.delete(id);
    }

    @Transactional
    public void transfer(int id1, int id2, String money) throws RuntimeException{
        //获取用户
        Account account1 =accountMapper.findAccount(id1);
        Account account2 =accountMapper.findAccount(id2);
        //获取金额
        BigDecimal bd1 = new BigDecimal(Double.toString(account1.getMoney()));
        BigDecimal bd2 = new BigDecimal(Double.toString(account2.getMoney()));
        //金额加减
        BigDecimal bmoney = new BigDecimal(money);
        BigDecimal nbd1 = bd1.subtract(bmoney);
        BigDecimal nbd2 = bd2.add(bmoney);
        //转成double
        double d1 = nbd1.doubleValue();
        double d2 = nbd2.doubleValue();
        //设置参数
        account1.setMoney(d1);
        account2.setMoney(d2);
        //存入数据库
        accountMapper.update(account1);
//        int i=1/0;
        accountMapper.update(account2);
    }

}
