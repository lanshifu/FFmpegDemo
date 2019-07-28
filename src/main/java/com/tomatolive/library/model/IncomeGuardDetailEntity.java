package com.tomatolive.library.model;

public class IncomeGuardDetailEntity {
    private String anchorIncomeGold = "";
    private String createTime = "";
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

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IncomeGuardDetailEntity{guardId='");
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
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
