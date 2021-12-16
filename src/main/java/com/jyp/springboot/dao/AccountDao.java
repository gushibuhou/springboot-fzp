package com.jyp.springboot.dao;

import com.jyp.springboot.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/9
 * Time: 15:50
 */
//jpa
public interface AccountDao extends JpaRepository<Account,Integer> {
    //根据属性查询,要查那个属性和方法名有关
    //可以参考 https://www.jianshu.com/p/c14640b63653 jpa 关键字说明
    Account findByName(String name);

    //自定义查询 PQL
    //注意:对象名必须与实体类的类名一致，严格区分大小写。
    @Query("SELECT a FROM Account a WHERE a.name = :name1 OR a.name = :name2 ")
    List<Account> findByTwoName(@Param("name1")String name1,@Param("name2")String name2);

    //自定义查询 SQL,2个name
    @Query(nativeQuery = true, value = "SELECT * FROM account WHERE name = :name1  OR name = :name2 ")
    List<Account> findSQL(@Param("name1") String name1, @Param("name2") String name2);

    //自定义查询 SQL,id或者name
    @Query(nativeQuery = true, value = "SELECT * FROM account WHERE id LIKE :id  OR name LIKE :name ")
    List<Account> findAccountByIdOrName(@Param("id") String id, @Param("name") String name);

}
