package com.tomatolive.library.model;

public class ChatEntity {
    private String carIcon;
    private String date;
    private String expGrade;
    private String giftDirPath;
    private String giftId;
    private String giftName;
    private int guardType;
    private String msgSendName;
    private String msgText;
    private int msgType;
    private String propId;
    private String propName;
    private String propNum;
    private String role;
    private String sex;
    private String targetAvatar;
    private String targetId;
    private String targetName;
    private String uid;
    private String userAvatar;

    public String getCarIcon() {
        return this.carIcon;
    }

    public void setCarIcon(String str) {
        this.carIcon = str;
    }

    public int getGuardType() {
        return this.guardType;
    }

    public void setGuardType(int i) {
        this.guardType = i;
    }

    public String getExpGrade() {
        return this.expGrade;
    }

    public void setExpGrade(String str) {
        this.expGrade = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String str) {
        this.role = str;
    }

    public String getTargetAvatar() {
        return this.targetAvatar == null ? "" : this.targetAvatar;
    }

    public void setTargetAvatar(String str) {
        this.targetAvatar = str;
    }

    public String getTargetId() {
        return this.targetId == null ? "" : this.targetId;
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }

    public String getTargetName() {
        return this.targetName == null ? "" : this.targetName;
    }

    public void setTargetName(String str) {
        this.targetName = str;
    }

    public String getUid() {
        return this.uid == null ? "" : this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getDate() {
        return this.date == null ? "" : this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getMsgSendName() {
        return this.msgSendName;
    }

    public void setMsgSendName(String str) {
        this.msgSendName = str;
    }

    public String getMsgText() {
        return this.msgText;
    }

    public void setMsgText(String str) {
        this.msgText = str;
    }

    public int getMsgType() {
        return this.msgType;
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

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

    public String getUserAvatar() {
        return this.userAvatar;
    }

    public void setUserAvatar(String str) {
        this.userAvatar = str;
    }

    public String getGiftDirPath() {
        return this.giftDirPath;
    }

    public void setGiftDirPath(String str) {
        this.giftDirPath = str;
    }

    public String getPropId() {
        return this.propId;
    }

    public void setPropId(String str) {
        this.propId = str;
    }

    public String getPropName() {
        return this.propName;
    }

    public void setPropName(String str) {
        this.propName = str;
    }

    public String getPropNum() {
        return this.propNum;
    }

    public void setPropNum(String str) {
        this.propNum = str;
    }
}
