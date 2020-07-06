package cn.spk.data.bean;

import cn.spk.data.entity.FrameUser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class clazz = Class.forName("cn.spk.data.entity.FrameUser");
        Constructor constructor = clazz.getConstructor();
        FrameUser frameUser = (FrameUser) constructor.newInstance();
        Field field = clazz.getDeclaredField("username");
//        field.setAccessible(true);
//        field.set(frameUser,"zhangsan");
        System.out.println("set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
        Method method=clazz.getDeclaredMethod("set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1),String.class);
        method.invoke(frameUser,"lisi");
        System.out.println(frameUser.getUsername());
    }
}
