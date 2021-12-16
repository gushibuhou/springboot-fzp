package com.jyp.springboot.dao;

import com.jyp.springboot.entity.Account;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/9
 * Time: 9:21
 */
public interface IAccountDAO {
    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
