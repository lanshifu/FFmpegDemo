package com.tomatolive.library.model;

public class ExpenseCarDetailEntity {
    private String carId = "";
    private String carName = "";
    private String createTime = "";
    private String duringDate = "";
    private String gold = "";

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String str) {
        this.carId = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getGold() {
        return this.gold;
    }

    public void setGold(String str) {
        this.gold = str;
    }

    public String getCarName() {
        return this.carName;
    }

    public void setCarName(String str) {
        this.carName = str;
    }

    public String getDuringDate() {
        return this.duringDate;
    }

    public void setDuringDate(String str) {
        this.duringDate = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ExpenseCarDetailEntity{createTime='");
        stringBuilder.append(this.createTime);
        stringBuilder.append('\'');
        stringBuilder.append(", carId='");
        stringBuilder.append(this.carId);
        stringBuilder.append('\'');
        stringBuilder.append(", gold='");
        stringBuilder.append(this.gold);
        stringBuilder.append('\'');
        stringBuilder.append(", carName='");
        stringBuilder.append(this.carName);
        stringBuilder.append('\'');
        stringBuilder.append(", duringDate='");
        stringBuilder.append(this.duringDate);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
