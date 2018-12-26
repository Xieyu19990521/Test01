package com.example.yu.demo01;

public class DemoBean {

    private String string="string is one";

    public DemoBean(String string) {
        this.string = string;
    }

    public DemoBean() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
