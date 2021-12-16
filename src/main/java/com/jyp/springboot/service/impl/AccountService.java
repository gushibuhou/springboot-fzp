package com.jyp.springboot.service.impl;

import com.jyp.springboot.dao.AccountDao;
import com.jyp.springboot.dao.IAccountDAO;
import com.jyp.springboot.entity.Account;
import com.jyp.springboot.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/9
 * Time: 10:37
 */
@Service
public class AccountService implements IAccountService {
    /*第3篇
    */


    @Autowired
    IAccountDAO accountDAO;

    @Autowired
    private AccountDao accountDao;

    @Override
    public int add(Account account) {
        return accountDAO.add(account);
        /*
        accountDao.save(account);
        return 1;
        */
    }

    @Override
    public int update(Account account) {
        return accountDAO.update(account);
        /*
        accountDao.saveAndFlush(account);
        return 1;
        */
    }

    @Override
    public int delete(int id) {
        return accountDAO.delete(id);
        /*
        accountDao.deleteById(id);
        return 1;
        */
    }

    @Override
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
        /*
        Optional<Account> optional = accountDao.findById(id);
        Account account = null;
        if (optional.isPresent()) {
            account = optional.get();
        }
        return account;
        */
    }

    @Override
    public List<Account> findAccountList() {
        return accountDAO.findAccountList();
        /*
        return accountDao.findAll(new Sort(Sort.Direction.DESC,"name"));
        */
    }


}
