package com.jyp.springboot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2020/1/10
 * Time: 15:06
 */
public class JVMTest {
    public static int count;

    static A a = new A();
    static B b = new B();
    @Test
    public void test() {
        //demo1();
        //clone(new Date());//克隆
        //neicunyichu1();//方法调用自己,造成内存溢出
        /*try {
            Deadlock();//相互等待,造成死锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //duineicunyichu2();
        //new Demo1_8().test();
        //stringTest();
        //stringTable();
        //stringTable1();
        directMomery1();
    }
    @Test
    public void test1() {
        //生成公钥和私钥
        try {
            RSAEncrypt.genKeyPair();
            //加密字符串
            String password = "oracle";
            System.out.println("随机生成的公钥为:" + RSAEncrypt.keyMap.get(0));
            System.out.println("随机生成的私钥为:" + RSAEncrypt.keyMap.get(1));
            //加密
            String passwordEn = RSAEncrypt.encrypt(password,RSAEncrypt.keyMap.get(0));
            System.out.println(password + "\t加密后的字符串为:" + passwordEn);
            //解密
            String messageDe = RSAEncrypt.decrypt(passwordEn,RSAEncrypt.keyMap.get(1));
            System.out.println("还原后的字符串为:" + messageDe);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void  demo1(){
        demo2();
    }
    public void  demo2(){
        demo3(1,2);
    }
    public int  demo3(int a,int b){
        int c = a + b;
        return c;
    }
    public void clone(Date d){
        Date dd = new Date();
        Date dd1 = new Date();
        System.out.println(System.identityHashCode(d));
        dd = d;
        dd1 = (Date)d.clone();
        System.out.println(System.identityHashCode(dd));
        System.out.println(System.identityHashCode(dd1));
    }
    //栈内存溢出,栈帧过多,报 java.lang.StackOverflowError 错误,
    // 可以在edit configarations 修改 vm opitions -Xss256k
    public void neicunyichu1(){
        try {
            selftuning();
        }catch(Throwable e){
            e.printStackTrace();
            System.out.println(count);
        }
    }
    //调用自己的方法
    private void selftuning(){
        count++;
        selftuning();
        //@JsonIgnore
    }
    //相互等待,造成死锁
    public void Deadlock() throws InterruptedException {

        new Thread(()->{
            synchronized (a){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println("我获得了a和b");
                }
            }
        }).start();
        Thread.sleep(1000);
        new Thread(()-> {
            synchronized (b) {
                synchronized (a) {
                    System.out.println("我获得了a和b");
                }
            }
        }).start();
    }

    public static class A{

    }
    public static class B{

    }
    //堆内存溢出,死循环创建有用的对象
    public static void duineicunyichu1(){
        int i = 0;
        try {
            List<String> list = new ArrayList<String>();
            String a = "hello";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(i);
        }

    }
    //堆内存演示
    public static void duineicunyichu2(){
        try {
            System.out.println("1...");
            Thread.sleep(30000);
            byte[] array = new byte[1024 * 1024 * 10];//分配10M的空间
            System.out.println("2...");
            Thread.sleep(30000);
            array = null;
            System.gc();
            System.out.println("3...");
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 演示元空间内存溢出
     */
    public class Demo1_8 extends ClassLoader{
        public void test(){
            int j = 0;
            try {
                Demo1_8 demo18 = new Demo1_8();
                for (int i = 0; i < 10000; i++,j++) {
                    //ClassWriter 作用是生成类的二进制字节码
                    ClassWriter cw = new ClassWriter(0);
                    //版本号,public,类名,包名,父类,接口
                    cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,"Class" + i,null,"java/lang/Object",null);
                    //返回 byte[]
                    byte[] code = cw.toByteArray();
                    //执行了类的加载
                    demo18.defineClass("Class" + i,code,0,code.length);
                }
            } finally {
                System.out.println(j);
            }
        }
    }

    public void stringTest(){
        String s1 = "a";
        String s2 = "b";
        String s5 = s1 + s2;    //new StringBuilder().append("a").append("b").toString()  new String("ab"); 这时候只有堆中有ab的对象,串池没有"ab"
        s5.intern();  //将堆的对象"ab"存放到常量池中,JVM1.6如果串池没有,会拷贝一份放入,所以1.6的 s5 == "ab" 为false,JVM1.8的是直接放入,所以1.8的 s5 == "ab" 为true
        String s3 = "ab";
        String s4 = "a" + "b";  //javac 在编译期的优化,结果已经在编译期确定为ab(对于参数都是已知的,编译期会优化)
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s4 == s5);

    }

    /**
     * 演示 stringTable 位置
     * 在jdk8下设置 -Xmx10m -XX:-UseGCOverheadLimit
     * java.lang.OutOfMemoryError: GC overhead limit exceeded
     * java.lang.OutOfMemoryError: Java heap space
     * 在jdk6下设置 -XX:MaxPermSize=10m
     */
    public void stringTable(){
        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            for (int j = 0; j < 260000; j++) {
                list.add(String.valueOf(j).intern());
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }

    /**
     * -Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
     * -Xmx10m
     * -XX:+PrintStringTableStatistics 打印字符串表的统计信息
     * -XX:+PrintGCDetails 垃圾回收的次数
     * -verbose:gc 垃圾回收花费的时间
     */
    public void stringTable1(){
        int i = 0;
        try {
            for (int j = 0; j < 10000; j++) {
                String.valueOf(j).intern();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }

    /**
     * 直接内存溢出
     * java.lang.OutOfMemoryError: Direct buffer memory
     */
    public void directMomery1(){
        List<ByteBuffer> list = new ArrayList<>();
        int i = 0;
        int _100Mb = 1024 * 1024 * 100;
        try {
            while(true){
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_100Mb);
                list.add(byteBuffer);
                i++;
            }
        } finally {
            System.out.println(i);
        }
    }
}
