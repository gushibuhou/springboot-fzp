package com.jyp.springboot;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2021/1/21
 * Time: 13:49
 */
public class OrderTest {


    @Test
    public void test(){
        Integer[] a = initA();
        Integer[] b = a.clone();
        long startTime = System.nanoTime();  //获取开始时间
        bubble(b);//冒泡排序
        long endTime = System.nanoTime();  //获取结束时间
        System.out.println("冒泡排序程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
        b = a.clone();
        startTime = System.nanoTime();  //获取开始时间
        bubble2(b);//改良冒泡排序
        endTime = System.nanoTime();  //获取结束时间
        System.out.println("改良冒泡排序程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
        b = a.clone();
        startTime = System.nanoTime();  //获取开始时间
        head(b);//堆排序
        endTime = System.nanoTime();  //获取结束时间
        System.out.println("堆排序程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间head
        b = a.clone();
        startTime = System.nanoTime();  //获取开始时间
        select(b);//选择排序
        endTime = System.nanoTime();  //获取结束时间
        System.out.println("选择排序程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
        /*
        b = a.clone();
        startTime = System.nanoTime();  //获取开始时间
        insert(b);//插入排序
        endTime = System.nanoTime();  //获取结束时间
        System.out.println("插入排序程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
        */
        b = a.clone();
        startTime = System.nanoTime();  //获取开始时间
        insert2(b);//插入排序
        endTime = System.nanoTime();  //获取结束时间
        System.out.println("优化插入排序程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间

        b = a.clone();
        startTime = System.nanoTime();  //获取开始时间
        shell(b);//插入排序
        endTime = System.nanoTime();  //获取结束时间
        System.out.println("希尔排序程序运行时间：" + (endTime - startTime) + "ns"); //输出程序运行时间
    }



    public Integer[] initA(){
        int max = 10;
        int min = 1;
        Integer[] a = new Integer[max];
        for (int i = 0; i < max; i++) {
            a[i] = (int)(Math.random()*(max-min) + min);
        }
        return a;
    }
    /**
     * 冒泡排序
     * 从右侧开始,比较相邻的2个数,小的排左边
     1轮过后,最小的在最左边
     时间复杂度 n平方
     * @param a
     */
    public void bubble(Integer[] a){
        System.out.println("开始前,a:" + Arrays.toString(a));
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length - i; j++) {//最开始的长度是a.length-1,也就是a.length-0-1,每次减1,最后是1,也就是a.length-(a.length-1)
                int k = a.length-j;//最右侧最开始是a.length-1,每次减1,最后是1,也就是a.length-(a.length-1)
                if(a[k] < a[k-1]){//最右侧和次右侧比较
                    int temp = a[k-1];
                    a[k-1] = a[k];
                    a[k] = temp;
                }
            }
        }
        System.out.println("开始后,a:" + Arrays.toString(a));
    }

    /**
     * 冒泡排序
     * 从右侧开始,比较相邻的2个数,小的排左边
     1轮过后,最小的在最左边
     时间复杂度 n平方
     * @param a
     */
    public void bubble2(Integer[] a){
        System.out.println("开始前,a:" + Arrays.toString(a));
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length - 1; j > 0; j--) {//最开始的长度是a.length-1,也就是a.length-0-1,每次减1,最后是1,也就是a.length-(a.length-1)
                if(a[j] < a[j-1]){//最右侧和次右侧比较
                    int temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println("开始后,a:" + Arrays.toString(a));
    }

    /**
     * 选择排序 select
     从待排序的数据中,选出最小值,和待排序的数据中的最左边的数据对调
     1轮过后,最小的在最左边
     时间复杂度 n平方
     * @param a
     */
    public void select(Integer[] a){
        System.out.println("开始前,a:" + Arrays.toString(a));
        for (int i = 0; i < a.length; i++) {
            int value = a[i];//要比较的数
            int k = i;//最小值位置
            for (int j = i+1; j < a.length ; j++) {
                if(a[j] < value){//如果a[j] 小于 要比较的数
                    value = a[j];//将值赋值给比较值
                    k = j;//将新位置赋值给最小值位置
                }
            }
            if(k != i){//如果最小值不是第一个,第一个数和最小值互换
                int temp = a[i];
                a[i] = a[k];
                a[k] = temp;
            }
            //System.out.println("执行" + String.valueOf(i+1) + "次,a:" + Arrays.toString(a));
        }
        System.out.println("开始后,a:" + Arrays.toString(a));
    }

    /**
     * 堆排序 head
     从最后1个非叶子节点开始,如果存在比它小的子节点,互换后,迭代排序子树
     1轮过后,最小的在最左边
     时间复杂度 n平方
     * @param a
     */
    public void head(Integer[] a){
        System.out.println("堆排序前,a:" + Arrays.toString(a));
        int min;
        int temp;
        for (int i = a.length/2-1; i >=0 ; i--) {//从最后1个非叶子节点开始
            if(i*2 + 2 < a.length){
                if(a[i*2 + 1] < a[i*2 + 2]){
                    min = i*2 + 1;
                }else{
                    min = i*2 + 2;
                }
            }else{
                min = i*2 + 1;
            }
            //System.out.println("head:i=" + i + ",min=" + min + ",a[i]=" + a[i]+ ",a[min]=" + a[min]);
            if(a[min] < a[i]){
                temp = a[min];
                a[min] = a[i];
                a[i] = temp;
                //System.out.println(Arrays.toString(a));
                if(headLoop(a,min)){
                    i++;
                }
            }
        }
        System.out.println("堆排序后,a:" + Arrays.toString(a));
    }

    public boolean headLoop(Integer[] a,int i){
        boolean b = false;
        int min;
        int temp;
        if(i * 2 +1 < a.length){
            if(i*2 + 2 < a.length){
                if(a[i*2 + 1] < a[i*2 + 2]){
                    min = i*2 + 1;
                }else{
                    min = i*2 + 2;
                }
            }else{
                min = i*2 + 1;
            }
            //System.out.println("headLoop:i=" + i + ",min=" + min + ",a[i]=" + a[i]+ ",a[min]=" + a[min]);
            if(a[min] < a[i]) {
                temp = a[min];
                a[min] = a[i];
                a[i] = temp;
                //System.out.println(Arrays.toString(a));
                headLoop(a,min);
                b = true;
            }
        }
        return b;
    }

    /**
     * 插入排序 insert
     将右侧待排序的数据,逐个插到左边已排序的数据中的正确位置
     初步认定第1个是已经排序好的,从第2个开始
     先和左边排序好的最右边的数据比较大小
     时间复杂度 n平方
     * @param a
     */
    public void insert(Integer[] a){
        System.out.println("开始前,a:" + Arrays.toString(a));
        int temp;
        for (int i = 1; i < a.length; i++) {//从第2个开始,知道结束
            for (int j = i; j > 0 ; j--) {//j是要排序的数,先和左边排序好的最右边的数据比较大小,然后左移1位
                //System.out.println("i:" + i +",j:" + j + ",a[j]:" + a[j] + ",a[j-1]:" + a[j-1]);
                if(a[j] < a[j-1]){//每次都和它左边的比较,如果a[j] 小于 k,互换
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }else{//如果左边的数没有大于它,就结束这次循环
                    break;
                }
            }
            //System.out.println("执行" + String.valueOf(i+1) + "次,a:" + Arrays.toString(a));
        }
        System.out.println("开始后,a:" + Arrays.toString(a));
    }
    /**
     * 优化插入排序 insert
     将右侧待排序的数据,逐个插到左边已排序的数据中的正确位置
     初步认定第1个是已经排序好的,从第2个开始
     先和左边排序好的最右边的数据比较大小
     时间复杂度 n平方
     * @param a
     */
    public void insert2(Integer[] a){
        System.out.println("开始前,a:" + Arrays.toString(a));
        int value;//存储要比对的值
        for (int i = 1; i < a.length; i++) {//从第2个开始,知道结束
            value = a[i];//将要比对的值赋值给value
            int j;
            for (j = i; j > 0 ; j--) {//j是要排序的数,先和左边排序好的最右边的数据(j-1)比较大小,每次左移1位
                //System.out.println("i:" + i +",j:" + j + ",a[j]:" + a[j] + ",a[j-1]:" + a[j-1]);
                if(value < a[j-1]){//如果左边的值比较大
                    a[j] = a[j-1];//左边的值右移1位
                }else{
                    break;
                }
            }
            a[j] = value;//最后将比对的值赋值给结束为止
            //System.out.println("执行" + String.valueOf(i+1) + "次,a:" + Arrays.toString(a));
        }
        System.out.println("开始后,a:" + Arrays.toString(a));
    }
    /**
     * 希尔排序 shell
     按步长i(初始值=length/2)分组,组内直接插入排序
     步长i=i/2,继续分组排序,直到i=1
     * @param a
     */
    public void shell(Integer[] a){
        System.out.println("开始前,a:" + Arrays.toString(a));
        for (int i = a.length/2; i > 0 ; i/=2) {//i是步长,初始值按总个数/2,每次/2
            for (int j = 0; j < i; j++) {//循环1个步长
                insert3(a,j,i);//带步长的插入排序
            }
        }
        System.out.println("开始后,a:" + Arrays.toString(a));
    }

    /**
     * 带步长的插入排序
     * @param a  数组
     * @param start  开始下标
     * @param delta  步长
     */
    public void insert3(Integer[] a,int start,int delta){
        int value;//存储要比对的值
        for (int i = start + delta; i < a.length; i += delta) {//从第2个元素开始,直到结束
            value = a[i];//将要比对的值赋值给value
            int j;
            for (j = i; j-delta >= 0; j-=delta) {//j是要排序的数,先和左边排序好的最右边的数据(j-delta)比较大小,每次左移delta位
                //System.out.println("i:" + i +",j:" + j + ",a[i]:" + a[i] + ",a[j-delta]:" + a[j-delta]);
                if (value < a[j - delta]) {//如果左边的值比较大
                    a[j] = a[j - delta];//左边的值右移delta位
                } else {
                    break;
                }
            }
            a[j] = value;//最后将比对的值赋值给结束为止
        }
    }



}
