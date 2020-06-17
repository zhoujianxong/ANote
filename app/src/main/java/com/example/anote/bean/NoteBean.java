package com.example.anote.bean;

import cn.bmob.v3.BmobObject;

public class NoteBean extends BmobObject {
    private String title;
    private String webUrl;
    private String describe;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "NoteBean{" +
                "title='" + title + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
