package com.jyp.springboot;/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2021/6/12
 * Time: 11:25
 */

import org.junit.Test;

import java.util.Arrays;

/**
 * @description: TODO
 * @author Lenovo
 * @date 2021/6/12 11:25
 * @version 1.0
 */
public class ArrayTest {
    @Test
    public void test(){
        int array1 = 5;
        int array2 = 8;
        int[][] a = new int[array1][array2];
        for (int i = 0; i < array1; i++) {
            for (int j = 0; j < array2; j++) {
                a[i][j] = (int) (Math.random() * 100);
            }
        }
        for (int i = 0; i < array1; i++) {
            System.out.println(Arrays.toString(a[i]));
        }
        System.out.println("-----------------------");
        int[][] b = new int[array1][array2];
        System.arraycopy(a,0,b,0,array1);
        for (int i = 0; i < array1; i++) {
            System.out.println(Arrays.toString(b[i]));
        }
        System.out.println("-----------------------");
        for (int i = 0; i < array1; i++) {
            Arrays.sort(b[i]);
        }
        for (int i = 0; i < array1; i++) {
            System.out.println(Arrays.toString(b[i]));
        }
        System.out.println("-----------------------");
        a= Arrays.copyOfRange(b,0,b.length);
        for (int i = 0; i < array1; i++) {
            System.out.println(Arrays.toString(a[i]));
        }
        System.out.println("-----------------------");
    }
}
