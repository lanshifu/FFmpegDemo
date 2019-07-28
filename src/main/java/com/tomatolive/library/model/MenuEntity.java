package com.tomatolive.library.model;

import com.tomatolive.library.base.BaseActivity;
import java.io.Serializable;

public class MenuEntity implements Serializable {
    private Class<? extends BaseActivity> cls;
    private boolean isChecked = false;
    public boolean isSelected = false;
    public String menuDesc;
    private String menuID;
    public int menuIcon;
    public String menuTitle;
    public int menuType;
    public int position = 0;

    public MenuEntity(String str) {
        this.menuTitle = str;
    }

    public MenuEntity(String str, int i) {
        this.menuTitle = str;
        this.menuIcon = i;
    }

    public MenuEntity(String str, int i, boolean z) {
        this.menuTitle = str;
        this.menuIcon = i;
        this.isSelected = z;
    }

    public MenuEntity(String str, String str2) {
        this.menuTitle = str;
        this.menuDesc = str2;
    }

    public MenuEntity(String str, String str2, int i) {
        this.menuTitle = str;
        this.menuDesc = str2;
        this.menuIcon = i;
    }

    public MenuEntity(String str, int i, Class<? extends BaseActivity> cls) {
        this.menuTitle = str;
        this.menuIcon = i;
        this.cls = cls;
    }

    public MenuEntity(String str, int i, int i2, Class<? extends BaseActivity> cls) {
        this.menuTitle = str;
        this.menuIcon = i;
        this.menuType = i2;
        this.cls = cls;
    }

    public String getMenuTitle() {
        return this.menuTitle;
    }

    public void setMenuTitle(String str) {
        this.menuTitle = str;
    }

    public int getMenuIcon() {
        return this.menuIcon;
    }

    public void setMenuIcon(int i) {
        this.menuIcon = i;
    }

    public Class<? extends BaseActivity> getCls() {
        return this.cls;
    }

    public void setCls(Class<? extends BaseActivity> cls) {
        this.cls = cls;
    }

    public int getMenuType() {
        return this.menuType;
    }

    public void setMenuType(int i) {
        this.menuType = i;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public String getMenuDesc() {
        return this.menuDesc;
    }

    public void setMenuDesc(String str) {
        this.menuDesc = str;
    }

    public String getMenuID() {
        return this.menuID;
    }

    public void setMenuID(String str) {
        this.menuID = str;
    }
}
