package com.tomatolive.library.model;

import java.io.Serializable;

public class LabelEntity implements Serializable {
    public String id;
    public boolean isSelected = false;
    public String keyword;
    public String name;

    public LabelEntity(String str) {
        this.name = str;
    }
}
