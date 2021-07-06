package com.jyp.springboot;

import org.junit.Test;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/2/26
 * Time: 11:38
 */
public class Suanfa {

    @Test
    public void test() {
        int[] nums = new int[]{0,0,0};
        List<List<Integer>> list = threeSum(nums);
        System.out.println(list.toString());
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int len = nums.length;
        if(nums == null || len < 3) return list;
        Arrays.sort(nums); // 排序
        for(int i: nums){
            System.out.print(i + ",");
        }
        for(int i = 0;i < len;i++){
            if(nums[i] > 0){
                break;
            }
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    list.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return list;
    }
    @Test
    public void test1() {
        String s = "babad";

        System.out.println(s + ":" + longestPalindrome(s));
        s = "cbbd";
        System.out.println(s + ":" + longestPalindrome(s));
        s = "a";
        System.out.println(s + ":" + longestPalindrome(s));
        s = "ac";
        System.out.println(s + ":" + longestPalindrome(s));
        s = "ccc";
        System.out.println(s + ":" + longestPalindrome(s));
        s = "cccc";
        System.out.println(s + ":" + longestPalindrome(s));
        s = "ccccc";
        System.out.println(s + ":" + longestPalindrome(s));
        s = "cccccc";
        System.out.println(s + ":" + longestPalindrome(s));
        s = "ccccccc";
        System.out.println(s + ":" + longestPalindrome(s));
    }
    public String longestPalindrome(String s) {
        if(null == s || s.length() <= 0){
            return "";
        }else{
            int start = 0;
            int end = 0;
            int j = 0;
            int k = 0;
            boolean isJudge = false;
            for (int i = 0; i < s.length(); i++) {
                isJudge = false;
                if(i > 0 && i < s.length()-1 && s.substring(i-1,i).equals(s.substring(i+1,i+2))){
                    j = i - 1;
                    k = i + 1;
                    while(j >= 0 && k <= s.length()-1 && s.substring(j,j+1).equals(s.substring(k,k+1))){
                        j--;
                        k++;
                        if(!isJudge){
                            isJudge = true;
                        }
                    }
                    if(isJudge){
                        j++;
                        k--;
                    }
                    if(k - j > end - start){
                        start = j;
                        end = k;
                    }
                }
                if(i < s.length()-1 && s.substring(i,i+1).equals(s.substring(i+1,i+2))){
                    j = i;
                    k = i+1;
                    while(j >= 0 && k <= s.length()-1 && s.substring(j,j+1).equals(s.substring(k,k+1))){
                        j--;
                        k++;
                        if(!isJudge){
                            isJudge = true;
                        }
                    }
                    if(isJudge){
                        j++;
                        k--;
                    }
                    if(k - j > end - start){
                        start = j;
                        end = k;
                    }
                }
            }
            return s.substring(start,end+1);
        }
    }
    @Test
    public void test2() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println("s=" + s + ",numRows=" + numRows + ",返回:"+ convert(s,numRows));
        numRows = 4;
        System.out.println("s=" + s + ",numRows=" + numRows + ",返回:"+ convert(s,numRows));
        s="A";
        numRows = 1;
        System.out.println("s=" + s + ",numRows=" + numRows + ",返回:"+ convert(s,numRows));
    }
    public String convert(String s, int numRows) {
        String ss = "";
        if(numRows == 1){
            ss= s;
        }else{
            int num = 2 * numRows - 2;
            int mid = 0;
            for (int i = 0; i < numRows; i++) {
                for (int j = i; j < s.length(); j+=num) {
                    ss += s.substring(j,j+1);
                    mid = (numRows - 1 - i ) * 2;
                    if(mid > 1 && mid < num && j + mid < s.length()){
                        ss += s.substring(j + mid,j+mid+1);
                    }
                }
            }
            /*
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < s.length(); j++) {
                    k = (j %num + 1)%(2 * numRows);
                    if(k == i+1 ||k == (2 * numRows) - (i+1)){
                        ss += s.substring(j,j+1);
                    }
                }
            }
            */
        }
        return ss;
    }
    @Test
    public void test3() {
        //System.out.println((-1 << 2));
        //System.out.println((1 << 2) -1);

        int x;

        x = 123;
        System.out.println("x:" + x + ",返回:" + reverse2(x));
        x = -123;
        System.out.println("x:" + x + ",返回:" + reverse2(x));
        x = -1234;
        System.out.println("x:" + x + ",返回:" + reverse2(x));
        x = 120;
        System.out.println("x:" + x + ",返回:" + reverse2(x));
        x = 0;
        System.out.println("x:" + x + ",返回:" + reverse2(x));
        x = 2147483647;
        System.out.println("x:" + x + ",返回:" + reverse2(x));
        x = -2147483647;
        System.out.println("x:" + x + ",返回:" + reverse2(x));
        x = -2147483648;
        System.out.println("x:" + x + ",返回:" + reverse2(x));

        /*
        long l = -9223372036854775808L;
        System.out.println("l:" + l + ",-l返回:" + -l);
        System.out.println("l:" + l + ",Math.abs(l)返回:" + Math.abs(l));
        x = -2147483648;
        System.out.println("x:" + x + ",返回:" + Math.abs(x));
        x = -2147483647;
        System.out.println("x:" + x + ",返回:" + Math.abs(x));
        x = -2147483648;
        System.out.println("x:" + x + ",返回:" + negation(x));
        x = -2147483647;
        System.out.println("x:" + x + ",返回:" + negation(x));
        x = 2147483647;
        System.out.println("x:" + x + ",返回:" + negation(x));
        x = -1;
        System.out.println("x:" + x + ",返回:" + negation(x));
        x = 0;
        System.out.println("x:" + x + ",返回:" + negation(x));
        x = 1;
        System.out.println("x:" + x + ",返回:" + negation(x));
        */
        //System.out.println("x:" + x + ",返回:" + reverse(x));

    }
    public int reverse(int x) {
        String s ="";
        StringBuilder returnS = new StringBuilder();
        Long l;
        if(x < 0){
            if(x == -1<<31){
                return 0;
            }else{
                s=String.valueOf(-x);
                returnS.append("-");
                for (int i = s.length()-1; i >=0; i--) {
                    returnS.append(s.substring(i,i+1));
                }
                l = Long.valueOf(returnS.toString());
            }
        }else{
            s=String.valueOf(x);
            for (int i = s.length()-1; i >=0; i--) {
                returnS.append(s.substring(i,i+1));
            }
            l = Long.valueOf(returnS.toString());
        }
        if(l < -1<<31 || l > (1 << 31) -1){
            return 0;
        }else{
            return Integer.valueOf(returnS.toString());
        }
    }

    /**
     * 取反
     * @param x
     * @return
     */
    public int negation(int x){
        return ~x + 1;
    }
    public int reverse3(int x) {
        String s;
        StringBuilder returnS = new StringBuilder();
        Long l;
        if(x < 0){
            returnS.append("-");
            s=String.valueOf(x).substring(1);
        }else{
            s=String.valueOf(x);
        }
        returnS.append(new StringBuffer(s).reverse().toString());
        l = Long.valueOf(returnS.toString());
        if(l < -1<<31 || l > (1 << 31) -1){
            return 0;
        }else{
            return Integer.valueOf(returnS.toString());
        }
    }
    public int reverse4(int x) {
        int y = x < 0 ? -x : x;
        long l = 0L;
        int mid;
        if(x == -1<<31){
            return 0;
        }else {
            while (y >= 1 || y <= -1) {
                mid = y % 10;
                l = l * 10 + mid;
                y /= 10;
            }
        }
        if(x < 0){
            l = -l;
        }
        if(l < -1<<31 || l > (1 << 31) -1){
            return 0;
        }else{
            return (int) l;
        }
    }
    public int reverse2(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }
    @Test
    public void test5(){
        String s = "2021-05-13T05:16:44.842+08:00";
        System.out.println(s);
        System.out.println(timeFormatConversion(s));
    }

    public String timeFormatConversion(String s){
        if(s.length() == 29){
            return s.substring(0,19) + s.substring(23);
        }else{
            return s;
        }
    }
}
