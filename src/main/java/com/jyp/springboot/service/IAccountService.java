package com.jyp.springboot.service;

import com.jyp.springboot.entity.Account;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/9
 * Time: 10:36
 */
public interface IAccountService {
    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
