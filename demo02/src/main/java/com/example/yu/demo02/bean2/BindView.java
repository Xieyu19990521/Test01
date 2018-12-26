package com.example.yu.demo02.bean2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)//表示正在运行
public @interface BindView {

    int id(); //注解属性,同findViewById()的值

    boolean click() default false; //是否设置了点击事件
}
