package com.example.yu.demo02;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;


public class ButterKnifeProcess {
    public static void bind(final Object object) {
        try {
            parse(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parse(Object object) throws Exception {
        final Class<?> clazz = object.getClass();
        View view = null;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Annotation.class)) {
                Annotation injectView = field.getAnnotation(Annotation.class);
                int id = injectView.value();
                if (id < 0) {
                    throw new Exception("error");
                } else {
                    field.setAccessible(true);
                    if (object instanceof View) {
                        view = ((View) object).findViewById(id);
                    } else if (object instanceof Activity) {
                        view = ((Activity) object).findViewById(id);
                    }
                    field.set(object, view);
                }
            }
        }
    }
}
