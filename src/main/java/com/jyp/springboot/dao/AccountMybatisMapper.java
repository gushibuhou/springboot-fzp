package com.jyp.springboot.dao;

import com.jyp.springboot.entity.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/10
 * Time: 10:56
 */
//mybatis
@Mapper
public interface AccountMybatisMapper {
    @Insert("insert into account(name, money) values(#{name}, #{money})")
    int add(@Param("name") String name, @Param("money") double money);

    @Update("update account set name = #{name}, money = #{money} where id = #{id}")
    int update(@Param("name") String name, @Param("money") double money, @Param("id") int  id);

    @Update("update account set money = #{money} where id = #{id}")
    int updateMoney(Account account);

    @Delete("delete from account where id = #{id}")
    int delete(int id);

    @Select("select id, name as name, money as money from account where id = #{id}")
    Account findAccount(@Param("id") int id);

    @Select("select id, name as name, money as money from account")
    List<Account> findAccountList();

}
