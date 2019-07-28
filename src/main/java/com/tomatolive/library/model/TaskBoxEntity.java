package com.tomatolive.library.model;

public class TaskBoxEntity {
    private String id;
    private String openTime;
    private int position;
    private String propImg;
    private String propName;
    private int propNumber;
    private int status;

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setPropNumber(int i) {
        this.propNumber = i;
    }

    public int getPropNumber() {
        return this.propNumber;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getPropImg() {
        return this.propImg;
    }

    public void setPropImg(String str) {
        this.propImg = str;
    }

    public String getPropName() {
        return this.propName;
    }

    public void setPropName(String str) {
        this.propName = str;
    }

    public String getOpenTime() {
        return this.openTime;
    }

    public void setOpenTime(String str) {
        this.openTime = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TaskBoxEntity{id='");
        stringBuilder.append(this.id);
        stringBuilder.append('\'');
        stringBuilder.append(", propImg='");
        stringBuilder.append(this.propImg);
        stringBuilder.append('\'');
        stringBuilder.append(", propName='");
        stringBuilder.append(this.propName);
        stringBuilder.append('\'');
        stringBuilder.append(", openTime=");
        stringBuilder.append(this.openTime);
        stringBuilder.append(", position=");
        stringBuilder.append(this.position);
        stringBuilder.append(", propNumber=");
        stringBuilder.append(this.propNumber);
        stringBuilder.append(", status=");
        stringBuilder.append(this.status);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
