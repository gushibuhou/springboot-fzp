package com.jyp.springboot;

import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/12/1
 * Time: 9:13
 */

/**
 * 测试Try,Catch,Finally 3个return的先后顺序
 * 1.Try和Catch的return的优先级一样,属于2选1的情况
 * 2.如果能执行到Try/Catch的return,先执行return中的运算,确定了返回值(如果返回值是基本数据类型,finally无法影响最终的返回值,如果返回值是对象finally无法影响最终的返回值的地址,单可以修改对象里的值)
 * 3.执行finally,如果finally有return,则执行finally的return,不去管Try/Catch的return,否则就去执行Try/Catch的return
 * 4.如果Try,Catch,Finally都没有return,或者有return但执行不到,则执行Try,Catch,Finally外的return
 */
public class TryCatchFinallyTest {
    @Test
    public void test() {
        System.out.println("test2():" + test2());
        //System.out.println(getMap().get("KEY").toString());
    }

    /**
     *  说明try的return的运算在finally之前完成
     * @return
     */
    public static int test1() {
        int b = 20;
        try {
            System.out.println("进入 try,b=" + String.valueOf(b));
            return b += 80;
        }
        catch (Exception e) {
            System.out.println("catch block");
        }
        finally {
            System.out.println("进入finally,在try的return执行了b += 80的运算,现在b=" + String.valueOf(b));
            if (b > 25) {
                System.out.println("b>25, b = " + b + ",说明try的return的运算在finally之前完成");
            }
            System.out.println("此时finally没有return,所以调用了try的return");
        }
        return b;
    }

    /**
     * test12是为了说明try的return的运行和返回是分开的
     * @return
     */
    public String test11() {
        System.out.println("本次测试在try的return调用了test12");
        try {
            System.out.println("进入try");
            return test12();
        } finally {
            System.out.println("进入finally");
        }
    }

    public String test12() {
        System.out.println("进入test12");
        return "test12返回";
    }

    /**
     * 说明执行顺序
     * 1.try的return运算
     * 2.finally的return返回
     * 3.try的return返回(因为finally的return返回了,所以这一句执行不到)
     * @return
     */
    public int test2() {
        int b = 20;
        try {
            System.out.println("进入 try,b=" + String.valueOf(b));
            return b += 80;
        } catch (Exception e) {
            System.out.println("进入catch");
        } finally {
            System.out.println("进入finally,在try的return执行了b += 80的运算,现在b=" + String.valueOf(b));
            if (b > 25) {
                System.out.println("b>25, b = " + b + ",说明try的return的运算在finally之前完成");
            }
            System.out.println("finally准备返回 200,如果返回是200,说明finally的return返回在try的return返回之前");
            return 200;
        }
        // return b;
    }

    /**
     * 说明finally的修改不能影响try的返回值
     * @return
     */
    public int test3() {
        int b = 20;
        try {
            System.out.println("进入 try,此时b=" + String.valueOf(b));
            return b += 80;
        } catch (Exception e) {
            System.out.println("进入catch");
        } finally {
            System.out.println("进入finally,在try的return执行了b += 80的运算,现在b=" + String.valueOf(b));
            if (b > 25) {
                System.out.println("b>25, b = " + b + ",说明try的return的运算在finally之前完成");
            }
            b = 150;
            System.out.println("改变了b的值,此时b=" + String.valueOf(b) + ",如果返回的值和此时b的值一样,说明finally的修改可以影响到try的返回值");
        }
        return 2000;
    }

    /**
     * 如果try的return是一个对象,那么finally是可以修改对象里面的值,但不能修改对象的地址
     * @return
     */
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("KEY", "INIT");
        try {
            map.put("KEY", "TRY");
            System.out.println("进入 try,此时map的KEY=" + map.get("KEY"));
            return map;
        }
        catch (Exception e) {
            System.out.println("进入 finally,此时map的KEY=" + map.get("KEY"));
            map.put("KEY", "CATCH");
            System.out.println("catch修改了KEY,此时map的KEY=" + map.get("KEY"));
        }
        finally {
            System.out.println("进入 finally,此时map的KEY=" + map.get("KEY"));
            System.out.println("如果返回的map的KEY是TRY,说明finally的修改不能影响try的return的对象里面的值");
            map.put("KEY", "FINALLY");
            System.out.println("finally修改了KEY,此时map的KEY=" + map.get("KEY"));
            System.out.println("如果返回的map的KEY是FINALLY,说明finally的修改会影响try的return的对象里面的值,但是不能影响try的return的对象的地址");
            map = null;
            System.out.println("finally将map置为空,此时map=" + map);
            System.out.println("如果返回的map为空,说明finally的修改能影响try的return的对象的地址");
        }
        System.out.println("说明返回的map是try外面的map");
        return map;
    }

    /**
     * try外面的return优先级是最低的,只有try,catch,finally的里面都没有return或者return没有被执行到
     * @return
     */
    public int test4() {
        int b = 20;
        try {
            System.out.println("进入try");
            b = b / 0;
            return b += 80;
        } catch (Exception e) {
            System.out.println("进入catch,因为try执行了b = b / 0,此时b=" + String.valueOf(b));
            b += 15;
        } finally {
            System.out.println("进入finally,catch执行了b += 15,此时b=" + String.valueOf(b));
            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }
            b += 50;
        }
        System.out.println("因为try里面唯一的一个return无法被执行到,所以这个时候try外面的return会被执行到,准备返回204");
        return 204;
    }

    /**
     *
     * @return
     */
    public static int test5() {
        int b = 20;
        try {
            System.out.println("进入try");
            b = b /0;
            return b += 80;
        } catch (Exception e) {
            System.out.println("进入catch,因为try执行了b = b / 0,此时b=" + String.valueOf(b));
            return b += 15;
        } finally {
            System.out.println("进入finally,catch执行了b += 15,此时b=" + String.valueOf(b));
            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }
            b += 50;
            System.out.println("改变了b的值,此时b=" + String.valueOf(b) + ",如果返回的值和此时b的值一样,说明finally的修改可以影响到catch的返回值");
        }
        //return b;
    }


}
