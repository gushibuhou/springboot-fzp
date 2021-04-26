package com.jyp.springboot;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2021/4/26
 * Time: 11:47
 */
import com.jyp.springboot.bean.Hero;
import com.jyp.springboot.util.HeroChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LamdbaTest {
    public static void main(String[] args) {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);
        System.out.println("使用Lamdba的方式，筛选出 hp>100 && damange<50的英雄");
        filter(heros,h->h.hp>100 && h.damage<50);
    }

    private static void filter(List<Hero> heros,HeroChecker checker) {
        for (Hero hero : heros) {
            if(checker.test(hero))
                System.out.print(hero);

        }
    }

}
