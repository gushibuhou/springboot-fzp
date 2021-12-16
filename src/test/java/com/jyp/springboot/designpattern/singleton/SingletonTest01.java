package com.jyp.springboot.designpattern.singleton;/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2021/6/3
 * Time: 17:40
 */


/**
 * @description: TODO
 * @author Lenovo
 * @date 2021/6/3 17:40
 * @version 1.0
 */
public class SingletonTest01 {
    public static void main(String[] args) {
        MyThread01 thread1 = new MyThread01("thread1");
        MyThread01 thread2 = new MyThread01("thread2");
        Thread t1=new Thread(thread1);
        Thread t2=new Thread(thread2);
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
            Singleton01 singleton1 = thread1.getSingleton();
            Singleton01 singleton2 = thread2.getSingleton();
            System.out.println(singleton1);
            System.out.println(singleton2);
            System.out.println(singleton2.equals(singleton1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//饿汉式(静态变量)
class Singleton01{
    //1.构造器私有化,外部能new
    private Singleton01(){

    }
    //2.本类静态变量创建对象实例
    private final static Singleton01 instance = new Singleton01();

    //3.提供一个公有的静态方法,返回实例对象
    public static Singleton01 getInstance(){
        return instance;
    }
}
class MyThread01 implements Runnable{
    private String name ;       // 表示线程的名称
    private Singleton01 singleton ;       // 表示线程的名称
    public MyThread01(String name){
        this.name = name ;      // 通过构造方法配置name属性
    }
    public void run(){  // 覆写run()方法，作为线程 的操作主体
        singleton = Singleton01.getInstance();
        System.out.println(name + "运行,i=" + singleton);
    }
    public Singleton01 getSingleton(){
        return singleton;
    }
}
