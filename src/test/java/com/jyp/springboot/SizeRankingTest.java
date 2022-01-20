package com.jyp.springboot;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** 获取list元素的排名
 * @title: SizeRankingTest类
 * @Author jiangyp
 * @Date: 2022/1/20 8:57:07
 * @Version 1.0
 */
public class SizeRankingTest {
    @Test
    public void test(){
        List<Integer> intList = new ArrayList<>();
        intList.add(14);
        intList.add(2);
        intList.add(62);
        intList.add(40);
        intList.add(22);
        intList.add(40);
        Integer[][] integers = sizeRanking(intList);
        for (int i = 0; i < integers.length; i++) {
            System.out.println("第" + (i+1) + "个值:" + integers[i][0] + ",排名:" + integers[i][1]);
        }
    }
    public Integer[][] sizeRanking(List<Integer> list){
        int size = list.size();
        if(size <= 0){
            return new Integer[size][2];
        }
        Integer[] cloneList = new Integer[size];
        Integer[][] cloneList2 = new Integer[size][2];
        for (int i = 0; i < size; i++) {
            cloneList[i] = list.get(i);
            cloneList2[i][0] = list.get(i);
        }
        int num = 1;
        int val;
        int site;
        for (int i = 0; i < size; i++) {
            val = 0;
            site = 0;
            for (int j = 0; j < size; j++) {
                if(cloneList[j] > val){
                    val = cloneList[j];
                    site = j;
                }
            }
            cloneList[site] = 0;
            cloneList2[site][1] = num++;
        }
        return cloneList2;
    }

}  