package com.example.yu.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DaoBean {
    @Id(autoincrement = true)
    long id;
    String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 1715237303)
    public DaoBean(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 405743142)
    public DaoBean() {
    }
}
