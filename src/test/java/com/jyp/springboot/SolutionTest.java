package com.jyp.springboot;

import org.junit.Test;

/**
 * @title: SolutionTest类
 * @Author jiangyp
 * @Date: 2021/8/6 10:35
 * @Version 1.0
 */
public class SolutionTest {

    public boolean isUnique(String astr) {
        for (int i = 0; i < astr.length(); i++) {
            String s = astr.substring(i, i + 1);
            if(astr.lastIndexOf(s) != astr.indexOf(s)){
                return false;
            }
        }
        return true;
    }
    @Test
    public void test(){
        System.out.println(isUnique("leetcode"));
        System.out.println(isUnique("abc"));
    }

    public boolean CheckPermutation(String s1, String s2) {
        if(s1.length() + s2.length() <= 0){
            return true;
        }else if(s1.length() != s2.length()){
            return false;
        }else{
            String s = s1.substring(1, 2);
            if(!s2.contains(s)){
                return false;
            }else{
                return false;
            }
        }
    }

    @Test
    public void test1(){
        System.out.println(CheckPermutation("abc","bca"));
        System.out.println(CheckPermutation("abc","bad"));
    }
}  