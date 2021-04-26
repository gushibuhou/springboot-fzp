package com.jyp.springboot;
import cn.hutool.core.date.DateUtil;
import java.util.Date;
/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/7/31
 * Time: 11:41
 */
public class Synchronized4  implements Runnable {
    private static int counter = 1;

    /**
     * 静态的同步方法
     */
    public synchronized static void method() {
        Date startDate = DateUtil.date();
        for (int i = 0; i < 10000; i++) {
//            try {
            counter++;
                //System.out.println("线程 ：" + Thread.currentThread().getName() + " 当前计数器 ：" + (counter++));
//                System.out.println("开始时间 ：" + startDate + " 当前时间 ：" + DateUtil.date());
//                System.out.println();
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
    @Override
    public void run() {
        method();
    }
    public static void main(String[] args) throws InterruptedException {
        Synchronized4 syncThread = new Synchronized4();
        Synchronized4 syncThread1 = new Synchronized4();
        Thread thread1 = new Thread(syncThread, "sync-thread-1");
        thread1.start();
        Thread thread2 = new Thread(syncThread1, "sync-thread-2");
        thread2.start();
        thread1.join(); thread2.join();
        System.out.println(counter);
    }
}
