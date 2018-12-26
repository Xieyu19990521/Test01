package com.example.yu.demo02;

public class DemoBean {
    private String mStr = "not init";

    public String getStr() {
        return mStr;
    }

    public void setStr(String str) {
        changeStr(str);
    }


    private void changeStr(String str){
        mStr = str;
    }

    private String changeStr123(String str){
        mStr = str;
        return "aaaaaaaaaaaaaaa";
    }

    private void initStr(){
        mStr = "init success";
    }
}
