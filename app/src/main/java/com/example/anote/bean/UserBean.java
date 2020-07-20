package com.example.anote.bean;

import java.io.Serializable;


/**
 * @Description:
 * @Author: zjx
 * @CreateDate: 2020/7/17 0017
 */
public class UserBean implements Serializable {
    private String userId;
    private String name;
    private long currentTime;

    public UserBean(String userId,String name) {
        this.userId=userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}
