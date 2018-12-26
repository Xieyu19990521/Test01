package com.example.yu.demo02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Annotation(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnifeProcess.bind(this);
        button.setOnClickListener(this);
    }

    private void reflexDemoBean() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException {
        DemoBean demoBean = new DemoBean();
        Class c = Class.forName(demoBean.getClass().getCanonicalName());
        // 获取所有的变量
        Field[] fields = c.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        sb.append(Modifier.toString(demoBean.getClass().getModifiers()) + " class " + demoBean.getClass().getSimpleName() + "{\n");
        // 遍历每一个变量
        for (Field field : fields) {
            field.setAccessible(true);
            // 获得变量的修饰符，例如public，static等等
            sb.append(Modifier.toString(field.getModifiers()) + " ");
            // 变量的类型的名字
            sb.append(field.getType().getSimpleName() + " ");
            // 变量的名字
            sb.append(field.getName() + ";\n");

            //field.set(demoBean, "aaaaaaaaaaaaaaaaaaaaaaaaaaa");
            Object o = field.get(demoBean);
            if (o instanceof String) {
                String str = String.valueOf(o);
                sb.append(str + ";\n");
            }
        }
        sb.append("}\n");
        System.out.println("--------------------------------------------------------------------");
        System.out.println(sb);

        //获得构造方法
        Constructor<?> cons = c.getConstructor();
        System.out.println("--------------------------------------------------------------------");
        System.out.println("构造方法方法名：" + cons.getName());
        System.out.println("构造方法修饰符：" + Modifier.toString(cons.getModifiers()));
        System.out.println("构造方法全部：" + cons);


        // 获取所有的方法
        //public Method[] getMethods()返回某个类的所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法。
        //public Method[] getDeclaredMethods()对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法。
        Method[] methods = c.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("方法名：" + methods[i].getName());
            System.out.println("方法返回类型：" + methods[i].getReturnType());
            System.out.println("方法访问修饰符：" + Modifier.toString(methods[i].getModifiers()));
            System.out.println("方法代码写法： " + methods[i]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                try {
                    reflexDemoBean();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                break;
                default:break;
        }
    }
}
