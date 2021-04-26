package com.jyp.springboot;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForReadableInstant;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2021/4/19
 * Time: 17:16
 */
public class Solution {
    @Test
    public void test(){
        /*
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode l3 = addTwoNumbers(l1,l2);
        */

        ListNode root = new ListNode(0);
        ListNode cursor = root;
        ListNode sumNode = new ListNode(7);
        cursor.next = sumNode;
        cursor = cursor.next;
        sumNode = new ListNode(0);
        cursor.next = sumNode;
        cursor = cursor.next;
        sumNode = new ListNode(8);
        cursor.next = sumNode;
        cursor = sumNode;

    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while(l1 != null|| l2 != null|| carry != 0){
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;
            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return root.next;
    }
    @Test
    public void test1(){
        String s = "";
        s = "abcefcghi";
        lengthOfLongestSubstring3(s);
        /*
        s = "abcabcbbc";
        lengthOfLongestSubstring2(s);
        s = "dvdf";
        lengthOfLongestSubstring2(s);
        s = "abba";
        lengthOfLongestSubstring2(s);
        s = "aab";
        lengthOfLongestSubstring2(s);
        s = "tmmzuxt";
        lengthOfLongestSubstring2(s);
        s = "bbbbb";
        lengthOfLongestSubstring2(s);
        s = "pwwkew";
        lengthOfLongestSubstring2(s);
        */
    }
    public int lengthOfLongestSubstring(String s) {
        if(null == s || s.length() ==0){
            return 0;
        }
        char[] c = s.toCharArray();
        int max = 1;
        for (int i = 0; i < c.length; i++) {
            int length = c.length - i;
            Set sc = new HashSet();
            for (int j = i; j < c.length; j++) {
                if(sc.contains(c[j])){
                    length = j - i;
                    if(length > max){
                        max = length;
                    }
                    break;
                }else{
                    sc.add(c[j]);
                }
            }
            if(length > max){
                max = length;
            }
        }
        System.out.println(max);
        return max;
    }
    public int lengthOfLongestSubstring2(String s) {
        if(null == s || s.length() ==0){//为空返回0
            return 0;
        }
        char[] c = s.toCharArray();
        int max = 1;
        int length = s.length();
        Set sc = new HashSet();
        int start = 0;
        for (int i = 0; i < c.length; i++) {
            if(sc.contains(c[i])){
                length = i - start;//计算这个元素之前的长度
                if(length > max){
                    max = length;
                }
                int newStart = s.substring(0,i).lastIndexOf(c[i]) +1;
                for (int j = start; j < newStart; j++) {//清除开始位置到重复位置的值
                    sc.remove(c[j]);
                }
                sc.add(c[i]);//加入当前位置
                start = newStart;
            }else{
                sc.add(c[i]);
                length = i - start +1;
            }
        }
        if(length > max){
            max = length;
        }
        System.out.println(max);
        return max;
    }

    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        System.out.println(ans);
        return ans;
    }

    @Test
    public void test2(){
        int[] nums1=new int[]{};
        int[] nums2={};
        findMedianSortedArrays(nums1,nums2);
        nums1 = new int[]{1,3};
        nums2 = new int[]{2};
        findMedianSortedArrays(nums1,nums2);
        nums1 = new int[]{1,2};
        nums2 = new int[]{3,4};
        findMedianSortedArrays(nums1,nums2);
        nums1 = new int[]{0,0};
        nums2 = new int[]{0,0};
        findMedianSortedArrays(nums1,nums2);
        nums1 = new int[]{};
        nums2 = new int[]{1};
        findMedianSortedArrays(nums1,nums2);
        nums1 = new int[]{2};
        nums2 = new int[]{};
        findMedianSortedArrays(nums1,nums2);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        double d = 0;
        if(nums.length > 0){
            for (int i = 0,j = 0; i + j < nums.length; ) {
                if(i >= nums1.length){
                    nums[i+j] = nums2[j++];
                }else if(j >= nums2.length){
                    nums[i+j] = nums1[i++];
                }else if(nums1[i] < nums2[j]){
                    nums[i+j] = nums1[i++];
                }else{
                    nums[i+j] = nums2[j++];
                }
            }
            //System.out.println(ArrayUtils.toString(nums, ","));
            if(nums.length % 2 == 0){
                d = (nums[nums.length/2] + nums[nums.length/2 - 1])/2.0;
            }else{
                d = nums[nums.length/2];
            }
        }
        System.out.println(d);
        return d;
    }
    @Test
    public void test3(){
        double tian = 0.5;
        int money = 0;
        for (int i = 1;i <= 10; i++) {
            tian *=2;
            money += tian;
        }
        int money1 = (1 << 10) -1;
        System.out.println(money);
        System.out.println(money1);
    }
    @Test
    public void test4(){
        for (int j = 0; j < 100; j++) {
            if(0==j%3 || 0==j%5){
                continue; //如果是双数，后面的代码不执行，直接进行下一次循环
            }
            System.out.println(j);
        }
    }
    @Test
    public void test5(){
        int p = 10000;
        double r = 0.05;
        for (int i = 1; 1 < 2 ; i++) {
            p*=(1+0.05);
            if(p > 1000000){
                System.out.println(i);
                System.out.println(p);
                break;
            }
        }
    }
    @Test
    public void test6(){
        int fenzi = 1;
        int fenmu = 2;
        double chazhi = 1;
        double value = 0;
        double huangjin = 0.618;
        for (int i = 1; i < 20 ; i++) {
            for (int j = i + 1; j <= 20 ; j++) {
                if(Math.abs(i*1.0/j - huangjin) < chazhi ){
                    value = i*1.0/j;
                    chazhi = Math.abs(value - huangjin);
                    fenzi = i;
                    fenmu = j;
                }
            }
        }
        System.out.println("fenzi:" + fenzi +",fenmu:" + fenmu +",value:" + value +",chazhi:" + chazhi );
    }
    @Test
    public void test7(){
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if(i*i*i + j*j*j + k*k*k == i*100 + j*10 + k){
                        System.out.println(i*100 + j*10 + k);
                    }
                }
            }
        }
    }
    @Test
    public void test8(){
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                for (int k = 1; k < 14; k++) {
                    for (int l = 1; l < 10; l++) {
                        if( i+j==8&&k-l==6
                            &&i+k==14&&j+l==10
                                ){
                            System.out.println("i=" + i + ",j=" + j + ",k=" + k + ",l=" + l);
                        }
                    }
                }
            }
        }
    }
    @Test
    //数组反转
    public void arrayiInversion(){
        int[] a = new int[5];
        a[0] = (int) (Math.random() * 100);
        a[1] = (int) (Math.random() * 100);
        a[2] = (int) (Math.random() * 100);
        a[3] = (int) (Math.random() * 100);
        a[4] = (int) (Math.random() * 100);
        System.out.println(ArrayUtils.toString(a));
        int value;
        for (int i = 0; i <= a.length/2; i++) {
            value = a[i];
            a[i] = a[a.length -1 -i];
            a[a.length -1 -i] = value;
        }
        System.out.println(ArrayUtils.toString(a));
    }
    @Test
    //排序
    public void sort(){
        int[] a = new int[5];
        a[0] = (int) (Math.random() * 100);
        a[1] = (int) (Math.random() * 100);
        a[2] = (int) (Math.random() * 100);
        a[3] = (int) (Math.random() * 100);
        a[4] = (int) (Math.random() * 100);
        int[] b = a.clone();
        System.out.println(ArrayUtils.toString(a));
        //选择排序
        int value;
        int k =0;
        for (int i = 0; i < a.length; i++) {
            value = a[i];
            k = i;
            for (int j = i+1; j < a.length; j++) {
                if(a[j] < value){
                    value = a[j];
                    k = j;
                }
            }
            if(k != i){
                a[k] = a[i];
                a[i] = value;
            }
        }
        System.out.println(ArrayUtils.toString(a));
        System.out.println(ArrayUtils.toString(b));
        //冒泡排序
        for (int i = 0; i < b.length; i++) {
            for (int j = b.length - 1; j > 0; j--) {
                if(b[j] < b[j-1]){
                    value = b[j];
                    b[j] = b[j-1];
                    b[j-1] = value;
                }
            }
        }
        System.out.println(ArrayUtils.toString(b));
    }
}
