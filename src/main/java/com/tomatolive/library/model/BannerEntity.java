package com.tomatolive.library.model;

import java.io.Serializable;

public class BannerEntity implements Serializable {
    public String allow_close;
    public String content;
    public String description;
    public String disable;
    public String id;
    public String image;
    public String img;
    public String method;
    public String name;
    public String terminal;
    public String title;
    public String url;

    public BannerEntity(String str) {
        this.image = str;
    }
}
