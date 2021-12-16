package com.jyp.springboot.designpattern.singleton;
/**
 * @title: SingletonTest03类
 * @Author jiangyp
 * @Date: 2021/7/6 11:31
 * @Version 1.0
 */
public class SingletonTest03 {
    public static void main(String[] args) {
        MyThread03 thread1 = new MyThread03("thread1");
        MyThread03 thread2 = new MyThread03("thread2");
        Thread t1=new Thread(thread1);
        Thread t2=new Thread(thread2);
        t1.start();
        t2.start();
        /*
        try {
            Thread.sleep(1000);
            Singleton03 singleton1 = thread1.getSingleton();
            Singleton03 singleton2 = thread2.getSingleton();
            System.out.println(singleton1);
            System.out.println(singleton2);
            System.out.println(singleton2.equals(singleton1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }
}

/*
饿汉式(静态变量)
 */
class Singleton03{
    private static Singleton03 instance;
    /*
    1.构造器私有化,外部能new
     */
    private Singleton03(){

    }
    /*
    3.提供一个公有的静态方法,返回实例对象
     */
    public static Singleton03 getInstance(){
        if(null == instance){
            instance = new Singleton03();
        }
        return instance;
    }
}

class MyThread03 implements Runnable{
    /*
    表示线程的名称
     */
    private String name ;
    /*
    表示线程的名称
     */
    private Singleton03 singleton ;
    /*
    通过构造方法配置name属性
     */
    public MyThread03(String name){
        this.name = name ;
    }
    /*
    覆写run()方法，作为线程 的操作主体
     */
    public void run(){
        singleton = Singleton03.getInstance();
        System.out.println(name + "运行,singleton=" + singleton);
    }
    public Singleton03 getSingleton(){
        return singleton;
    }
}