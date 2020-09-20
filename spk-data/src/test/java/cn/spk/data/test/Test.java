package cn.spk.data.test;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

public class Test {

    static ArrayList<String> arrayList = new ArrayList<>();

    public static void main(String[] args) {


//        Integer integer1 = new Integer(7);
//        Integer integer2 = new Integer(7);
//        Integer integer3 = 7;
//        int i = 7;
//        //false比较对象内存地址
//        System.out.println("integer1 == integer2:" + (integer1 == integer2));
//        //true比较大小
//        System.out.println("integer1.equals(integer2):" + integer1.equals(integer2));
//        //false
//        System.out.println("integer1 == integer3:" + (integer1 == integer3));//false
//        //true
//        System.out.println("integer1.equals(integer3):" + integer1.equals(integer3));//false
//        //true 自动装箱拆箱
//        System.out.println("integer1 == i:" + (integer1 == i));
        initString("33");
        initString("44");
        System.out.println(arrayList);

    }

    static void initString(String s) {
        arrayList.add(s);
        ArrayList<String> cc = new ArrayList<>();
        cc.add("1");
        arrayList.add("1");
        System.out.println(StringUtils.join(cc, ""));
        System.out.println(System.identityHashCode(arrayList));
    }
}
