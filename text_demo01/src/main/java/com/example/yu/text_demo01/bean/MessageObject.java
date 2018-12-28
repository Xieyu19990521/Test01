package com.example.yu.text_demo01.bean;

public class MessageObject {
    Object object;
    String id;

    public MessageObject(Object object, String id) {
        this.object = object;
        this.id = id;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
