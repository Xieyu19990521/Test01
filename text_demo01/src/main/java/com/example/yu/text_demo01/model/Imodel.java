package com.example.yu.text_demo01.model;

import com.example.yu.text_demo01.callback.MyCallBack;

import java.util.Map;

public interface Imodel {
    void onStartRequest(String s, Map<String, String> map, Class clas, MyCallBack callBack);
}
