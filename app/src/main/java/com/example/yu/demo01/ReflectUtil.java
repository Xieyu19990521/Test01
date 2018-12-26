package com.example.yu.demo01;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {
    public static void getMethod(){
        try {
            Class<?> clazz=Class.forName("com.example.yu.demo01.DemoBean");
            Constructor con = clazz.getDeclaredConstructor(new Class[]{});

          //创建Private的对象
            Object object =con.newInstance(new Object[]{});
            Method[] methods = clazz.getDeclaredMethods();
            for(Method s:methods){
                System.out.println(s+"======");
            }
              /*Field field = clazz.getDeclaredField("string");

            //设置避开java访问控制检测
            field.setAccessible(true);

            //获取修改前的值
            Object str = field.get(object);
            Log.i("TAG",(String)str);
            System.out.println("修改之前name的值："+(String)str);
            field.set(object,"李四");
            Method getNameMethod = clazz.getDeclaredMethod("getString");

            //设置避开java访问控制检测
            getNameMethod.setAccessible(true);

            //调用方法，返回值
            Object  o1= getNameMethod.invoke(object, new Object[]{});
            System.out.println("修改之后name的值："+(String)o1);
            Log.i("TAG",(String)o1);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
