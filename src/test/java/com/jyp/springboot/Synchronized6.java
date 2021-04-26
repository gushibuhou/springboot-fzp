package com.jyp.springboot;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/7/31
 * Time: 14:03
 */
public class Synchronized6  implements Runnable{

    static int i = 0;
    static Synchronized6 instance = new Synchronized6();

    private static synchronized void increace() {
        i++;
    }


    @Override
    public void run() {
        for (int j = 0; j < 10000000; ++j) {
            increace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Synchronized6());
        Thread t2 = new Thread(new Synchronized6());
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(i);
    }
}
