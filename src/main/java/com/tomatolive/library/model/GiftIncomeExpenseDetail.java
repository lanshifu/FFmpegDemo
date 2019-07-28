package com.tomatolive.library.model;

public class GiftIncomeExpenseDetail {
    private String anchorIncomeGold = "";
    private String anchorName = "";
    private String createTime = "";
    private String giftId = "";
    private String giftName = "";
    private String giftNum = "";
    private String gold = "";
    private String userName = "";

    public String getGiftId() {
        return this.giftId;
    }

    public void setGiftId(String str) {
        this.giftId = str;
    }

    public String getGiftName() {
        return this.giftName;
    }

    public void setGiftName(String str) {
        this.giftName = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getGiftNum() {
        return this.giftNum;
    }

    public void setGiftNum(String str) {
        this.giftNum = str;
    }

    public String getAnchorIncomeGold() {
        return this.anchorIncomeGold;
    }

    public void setAnchorIncomeGold(String str) {
        this.anchorIncomeGold = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getAnchorName() {
        return this.anchorName;
    }

    public void setAnchorName(String str) {
        this.anchorName = str;
    }

    public String getGold() {
        return this.gold;
    }

    public void setGold(String str) {
        this.gold = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GiftIncomeExpenseDetail{giftId='");
        stringBuilder.append(this.giftId);
        stringBuilder.append('\'');
        stringBuilder.append(", giftName='");
        stringBuilder.append(this.giftName);
        stringBuilder.append('\'');
        stringBuilder.append(", userName='");
        stringBuilder.append(this.userName);
        stringBuilder.append('\'');
        stringBuilder.append(", giftNum='");
        stringBuilder.append(this.giftNum);
        stringBuilder.append('\'');
        stringBuilder.append(", anchorIncomeGold='");
        stringBuilder.append(this.anchorIncomeGold);
        stringBuilder.append('\'');
        stringBuilder.append(", createTime='");
        stringBuilder.append(this.createTime);
        stringBuilder.append('\'');
        stringBuilder.append(", anchorName='");
        stringBuilder.append(this.anchorName);
        stringBuilder.append('\'');
        stringBuilder.append(", gold='");
        stringBuilder.append(this.gold);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
