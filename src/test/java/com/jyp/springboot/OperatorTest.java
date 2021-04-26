package com.jyp.springboot;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/7/10
 * Time: 11:36
 */
public class OperatorTest {
    @Test
    public void zuoyiyouyi(){
        int i = 2 << 4;
        System.out.println(i);
        int b1 = -2 >> 1;
        int b2 = -2 >>> 1;
        System.out.println(b1);
        System.out.println(b2);
    }
}
