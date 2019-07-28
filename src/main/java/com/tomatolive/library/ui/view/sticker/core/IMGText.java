package com.tomatolive.library.ui.view.sticker.core;

import android.text.TextUtils;

public class IMGText {
    private int color = -1;
    private String id;
    private String text;

    public IMGText(String str, int i) {
        this.text = str;
        this.color = i;
    }

    public IMGText(String str, String str2, int i) {
        this.id = str;
        this.text = str2;
        this.color = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.text);
    }

    public int length() {
        return isEmpty() ? 0 : this.text.length();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IMGText{text='");
        stringBuilder.append(this.text);
        stringBuilder.append('\'');
        stringBuilder.append(", color=");
        stringBuilder.append(this.color);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
