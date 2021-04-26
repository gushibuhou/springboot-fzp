package com.jyp.springboot;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/7/30
 * Time: 17:12
 */
public class Synchronized2  implements Runnable {
    /**
     * 全局变量
     * 创建一个计数器
     */
    private static int counter = 1;
    @Override
    public void run() {
        Date startDate = DateUtil.date();

        for (int i = 0; i < 500; i++) {
            synchronized (this) {
               try {
                    System.out.println("线程 ：" + Thread.currentThread().getName() + " 当前计数器 ：" + (counter++));
                    System.out.println("开始时间 ：" + startDate + " 当前时间 ：" + DateUtil.date());
                    System.out.println();
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Synchronized2 syncThread = new Synchronized2();
        Synchronized2 syncThread1 = new Synchronized2();
        Thread thread1 = new Thread(syncThread, "sync-thread-1");
        Thread thread2 = new Thread(syncThread, "sync-thread-2");
        thread1.start();
        thread2.start();
    }
}
