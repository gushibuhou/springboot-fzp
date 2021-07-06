package com.jyp.springboot;

import com.jyp.springboot.bean.Hero;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2021/4/26
 * Time: 14:34
 */
public class CollectionTest {

    @Test
    public void list(){
        List<Hero> heros = new ArrayList<Hero>();

        for (int j = 0; j < 2000000; j++) {
            Hero h = new Hero("Hero " + j);
            heros.add(h);
        }

        // 进行10次查找，观察大体的平均值
        for (int i = 0; i < 10; i++) {
            // 打乱heros中元素的顺序
            Collections.shuffle(heros);

            long start = System.currentTimeMillis();

            String target = "Hero 1000000";

            for (Hero hero : heros) {
                if (hero.name.equals(target)) {
                    System.out.println("找到了 hero!" );
                    break;
                }
            }
            long end = System.currentTimeMillis();
            long elapsed = end - start;
            System.out.println("一共花了：" + elapsed + " 毫秒");
        }
    }
    @Test
    public void map(){
        HashMap<String,Hero> heroMap = new HashMap<String,Hero>();
        for (int j = 0; j < 2000000; j++) {
            Hero h = new Hero("Hero " + j);
            heroMap.put(h.name, h);
        }
        System.out.println("数据准备完成");

        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();

            //查找名字是Hero 1000000的对象
            Hero target = heroMap.get("Hero 1000000");
            System.out.println("找到了 hero!" + target.name);

            long end = System.currentTimeMillis();
            long elapsed = end - start;
            System.out.println("一共花了：" + elapsed + " 毫秒");
        }
    }

    @Test
    public void hashcodetest(){
        int length;
        Random	random = new Random();
        String[] ss = new String[100];
        String	s = new String("1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int sLen = 62;
        Set<String> setList = new HashSet<>(100);
        StringBuffer sb ;
        for (int i = 0; i < 100; i++) {
            length = (int)Math.round(Math.random()*(10 - 2) + 2);
            sb = new StringBuffer();
            for (int j = 0; j < length; j++){
                sb.append(s.charAt(random.nextInt(sLen)));
            }
            ss[i] = sb.toString();
            if(setList.contains(ss[i])){
               i--;
            }else{
                setList.add(ss[i]);
            }
        }
        int i =0;
        for (String string : setList) {
            System.out.println("i:" + i++ + ",字符串长度:" + string.length() + ",字符串:" + string + ",hascode的值:" + hashcode(string,s));
        }
    }

    public int hashcode(String ss,String s){
        int length = 0;
        if(StringUtils.isNotEmpty(ss) && ss.length() > 0){
            for (int i = 0; i < ss.length(); i++) {
                length += length * 23 + s.indexOf(ss.substring(i,i+1));
            }
        }
        if(Math.abs(length) > 2000){
            length = Math.abs(length) % 2000;
        }

        return length;
    }
}
