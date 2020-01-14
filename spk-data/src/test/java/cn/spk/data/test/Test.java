package cn.spk.data.test;

public class Test {
    public static void main(String[] args) {
        Integer integer1 = new Integer(7);
        Integer integer2 = new Integer(7);
        Integer integer3 = 7;
        int i = 7;
        //false比较对象内存地址
        System.out.println("integer1 == integer2:" + (integer1 == integer2));
        //true比较大小
        System.out.println("integer1.equals(integer2):" + integer1.equals(integer2));
        //false
        System.out.println("integer1 == integer3:" + (integer1 == integer3));//false
        //true
        System.out.println("integer1.equals(integer3):" + integer1.equals(integer3));//false
        //true 自动装箱拆箱
        System.out.println("integer1 == i:" + (integer1 == i));
    }
}
