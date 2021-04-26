package com.jyp.springboot;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/7/31
 * Time: 13:53
 */
public class Synchronized5 implements Runnable{

    static int i = 0;
    static Synchronized5 instance = new Synchronized5();

    public synchronized void increace() {
        i++;
        //System.out.println(Thread.currentThread().getName() + ":" + i);
    }


    @Override
    public void run() {
        for (int j = 0; j < 1000000; ++j) {
            increace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
//        Thread t1 = new Thread(new Synchronized5());
//        Thread t2 = new Thread(new Synchronized5());
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(i);
    }
}
