package com.jyp.springboot.designpattern.singleton;
/**
 * @title: SingletonTest02类
 * @Author jiangyp
 * @Date: 2021/7/6 11:31
 * @Version 1.0
 */
public class SingletonTest02 {
    public static void main(String[] args) {
        MyThread02 thread1 = new MyThread02("thread1");
        MyThread02 thread2 = new MyThread02("thread2");
        Thread t1=new Thread(thread1);
        Thread t2=new Thread(thread2);
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
            Singleton02 singleton1 = thread1.getSingleton();
            Singleton02 singleton2 = thread2.getSingleton();
            System.out.println(singleton1);
            System.out.println(singleton2);
            System.out.println(singleton2.equals(singleton1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
饿汉式(静态变量)
 */
class Singleton02{
    private static Singleton02 instance;
    /*
    1.构造器私有化,外部能new
     */
    private Singleton02(){

    }
    /*
    2.本类静态变量创建对象实例
     */
    static{
        instance = new Singleton02();
    }
    /*
    3.提供一个公有的静态方法,返回实例对象
     */
    public static Singleton02 getInstance(){
        return instance;
    }
}
class MyThread02 implements Runnable{
    /*
    表示线程的名称
     */
    private String name ;
    /*
    表示线程的名称
     */
    private Singleton02 singleton ;
    /*
    通过构造方法配置name属性
     */
    public MyThread02(String name){
        this.name = name ;
    }
    /*
    覆写run()方法，作为线程 的操作主体
     */
    public void run(){
        singleton = Singleton02.getInstance();
        System.out.println(name + "运行,i=" + singleton);
    }
    public Singleton02 getSingleton(){
        return singleton;
    }
}