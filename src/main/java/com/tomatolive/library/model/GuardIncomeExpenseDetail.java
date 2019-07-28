package com.tomatolive.library.model;

public class GuardIncomeExpenseDetail {
    private String anchorIncomeGold = "";
    private String anchorName = "";
    private String createTime = "";
    private String gold = "";
    private String guardId = "";
    private String guardName = "";
    private String guardType = "";
    private String userName = "";

    public String getGuardId() {
        return this.guardId;
    }

    public void setGuardId(String str) {
        this.guardId = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
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

    public String getGuardName() {
        return this.guardName;
    }

    public void setGuardName(String str) {
        this.guardName = str;
    }

    public String getGuardType() {
        return this.guardType;
    }

    public void setGuardType(String str) {
        this.guardType = str;
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
        stringBuilder.append("GuardIncomeExpenseDetail{guardId='");
        stringBuilder.append(this.guardId);
        stringBuilder.append('\'');
        stringBuilder.append(", userName='");
        stringBuilder.append(this.userName);
        stringBuilder.append('\'');
        stringBuilder.append(", anchorIncomeGold='");
        stringBuilder.append(this.anchorIncomeGold);
        stringBuilder.append('\'');
        stringBuilder.append(", createTime='");
        stringBuilder.append(this.createTime);
        stringBuilder.append('\'');
        stringBuilder.append(", guardName='");
        stringBuilder.append(this.guardName);
        stringBuilder.append('\'');
        stringBuilder.append(", guardType='");
        stringBuilder.append(this.guardType);
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
