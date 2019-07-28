package com.tomatolive.library.model;

import java.io.Serializable;
import java.util.List;

public class OnLineUsersEntity implements Serializable {
    private List<UserEntity> list;
    private int popularity;

    public List<UserEntity> getUserEntityList() {
        return this.list;
    }

    public void setUserEntitieList(List<UserEntity> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return this.popularity;
    }

    public void setTotalCount(int i) {
        this.popularity = i;
    }
}
