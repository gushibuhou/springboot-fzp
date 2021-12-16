package com.jyp.springboot.dao;

import com.jyp.springboot.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/10
 * Time: 13:44
 */
//Transactional
@Mapper
public interface AccountTranMapper {

    Account findAccount(Integer  id);

    List<Account> selectAll();

    int insert(Account account);

    int update(Account account);

    int updateMoney(Account account);

    int delete(Integer  id);


}
