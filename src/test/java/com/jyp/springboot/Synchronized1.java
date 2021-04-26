package com.jyp.springboot;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/7/27
 * Time: 14:20
 */
//作用于对象
public class Synchronized1  implements Runnable{
    static int i = 0;
    static Synchronized1 instance = new Synchronized1();

    @Override
    public void run() {
        for (int j = 0; j < 500; ++j) {
            synchronized (this) {
                i++;
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        Thread t3 = new Thread(instance);
        t1.start(); t2.start();t3.start();
        //t1.join(); t2.join();t3.join();
        //System.out.println(i);
    }
}
