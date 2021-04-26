package com.jyp.springboot;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/7/31
 * Time: 10:57
 */
public class Synchronized3   implements Runnable {
    /**
     * 全局变量
     * 创建一个计数器
     */
    private static int counter = 1;
    @Override
    public synchronized void run() {
        Date startDate = DateUtil.date();
        //synchronized (this) {
            for (int i = 0; i < 500; i++) {
                try {
                    System.out.println("线程 ：" + Thread.currentThread().getName() + " 当前计数器 ：" + (counter++));
                    System.out.println("开始时间 ：" + startDate + " 当前时间 ：" + DateUtil.date());
                    System.out.println();
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        //}
    }
    public static void main(String[] args) throws InterruptedException {
        Synchronized3 syncThread = new Synchronized3();
        Synchronized3 syncThread1 = new Synchronized3();
        Thread thread1 = new Thread(syncThread, "sync-thread-1");
        Thread thread2 = new Thread(syncThread1, "sync-thread-2");
        thread1.start();
        thread2.start();
    }
}
