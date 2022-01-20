package com.jyp.springboot;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**排行榜
 * @title: LeaderboardTest类
 * @Author jiangyp
 * @Date: 2022/1/20 10:17:05
 * @Version 1.0
 */
public class LeaderboardTest {
    @Test
    public void test(){
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("张三",73));
        list.add(new Person("李四",65));
        list.add(new Person("王五",92));
        list.add(new Person("商六",65));
        list.add(new Person("张三",85));
        list.sort(new  Comparator<Person>(){
            @Override
            public int compare(Person o1, Person o2) {
                return  -Double.compare(o1.getFraction(), o2.getFraction());
            }
        });
        for (int i = 0; i < list.size(); i++) {
            Person person = list.get(i);
            System.out.println("第" + (i+1) + "名:" + person.getName() + ",分数:" + person.getFraction());
        }
    }

    @Data
    class Person{
        private String name;
        private int fraction;
        Person(){

        }
        Person(String name,int fraction){
            this.name = name;
            this.fraction = fraction;
        }

    }
}  