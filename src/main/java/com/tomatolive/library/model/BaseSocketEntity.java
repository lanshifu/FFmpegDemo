package com.tomatolive.library.model;

public class BaseSocketEntity<T> {
    private T businessData;
    private String messageType;
    private String r;
    private String s;
    private String t;

    public String getMessageType() {
        return this.messageType == null ? "" : this.messageType;
    }

    public void setMessageType(String str) {
        this.messageType = str;
    }

    public T getBusinessData() {
        return this.businessData;
    }

    public void setBusinessData(T t) {
        this.businessData = t;
    }

    public void setRandomStr(String str) {
        this.r = str;
    }

    public String getRandomStr() {
        return this.r;
    }

    public void setTimestampStr(String str) {
        this.t = str;
    }

    public String getTimestampStr() {
        return this.t;
    }

    public void setSign(String str) {
        this.s = str;
    }

    public String getSign() {
        return this.s;
    }
}
