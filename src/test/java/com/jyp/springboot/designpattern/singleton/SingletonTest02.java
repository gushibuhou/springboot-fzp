package com.jyp.springboot.designpattern.singleton;


/**
 * @title: SingletonTest02类
 * @Author jiangyp
 * @Date: 2021/7/6 11:31
 * @Version 1.0
 */
public class SingletonTest02 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("thread1");
        MyThread thread2 = new MyThread("thread2");
        thread1.run();
        thread2.run();
        Singleton singleton1 = thread1.getSingleton();
        Singleton singleton2 = thread2.getSingleton();
        System.out.println(singleton1);
        System.out.println(singleton2);
        System.out.println(singleton2.equals(singleton1));
    }
}
//饿汉式(静态变量)
class Singleton{
    //1.构造器私有化,外部能new
    private Singleton(){

    }
    //2.本类静态变量创建对象实例
    private final static Singleton instance = new Singleton();

    //3.提供一个公有的静态方法,返回实例对象
    public static Singleton getInstance(){
        return instance;
    }
}
class MyThread implements Runnable{
    private String name ;       // 表示线程的名称
    private Singleton singleton ;       // 表示线程的名称
    public MyThread(String name){
        this.name = name ;      // 通过构造方法配置name属性
    }
    public void run(){  // 覆写run()方法，作为线程 的操作主体
        singleton = Singleton.getInstance();
    }
    public Singleton getSingleton(){
        return singleton;
    }
}