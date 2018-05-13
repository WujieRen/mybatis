package com.mybatis.model;

/**
 * Created by renwujie on 2018/05/13 at 22:22
 */
public class Blog {
    private int id;
    private String titleName;
    private  String content;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
