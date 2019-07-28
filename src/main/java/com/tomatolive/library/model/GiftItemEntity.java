package com.tomatolive.library.model;

public class GiftItemEntity extends GiftDownloadItemEntity {
    public int activeTime;
    public String anchorId;
    public String anchorName;
    public int animalType;
    public String animalUrl;
    public String avatar;
    public String clientIp;
    public String expGrade;
    public int guardType;
    public boolean isProp = false;
    public String liveCount;
    public String liveId;
    public String role;
    public int sendIndex;
    public String sendUserName;
    public String sex;
    public String userId;

    public boolean isBigProp() {
        return this.animalType == 2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GiftItemEntity{giftDirPath='");
        stringBuilder.append(this.giftDirPath.length());
        stringBuilder.append('\'');
        stringBuilder.append(", id='");
        stringBuilder.append(this.id);
        stringBuilder.append('\'');
        stringBuilder.append(", typeid='");
        stringBuilder.append(this.typeid);
        stringBuilder.append('\'');
        stringBuilder.append(", name='");
        stringBuilder.append(this.name);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
