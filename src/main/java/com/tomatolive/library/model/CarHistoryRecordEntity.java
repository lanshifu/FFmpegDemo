package com.tomatolive.library.model;

public class CarHistoryRecordEntity {
    private String carName;
    private String postTime;
    private String userName;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getCarName() {
        return this.carName;
    }

    public void setCarName(String str) {
        this.carName = str;
    }

    public String getPostTime() {
        return this.postTime;
    }

    public void setPostTime(String str) {
        this.postTime = str;
    }

    public CarHistoryRecordEntity(String str, String str2, String str3) {
        this.userName = str;
        this.carName = str2;
        this.postTime = str3;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CarHistoryRecordEntity{userName='");
        stringBuilder.append(this.userName);
        stringBuilder.append('\'');
        stringBuilder.append(", carName='");
        stringBuilder.append(this.carName);
        stringBuilder.append('\'');
        stringBuilder.append(", postTime='");
        stringBuilder.append(this.postTime);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
