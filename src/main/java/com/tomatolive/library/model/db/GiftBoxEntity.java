package com.tomatolive.library.model.db;

public class GiftBoxEntity extends BaseDBEntity {
    public long expirationTime;
    public String giftBoxUniqueCode;
    public volatile long incrementTime;
    public String liveId;
    public long openTime;
    public String presenterAvatar;
    public String presenterId;
    public String presenterName;
    public String userId;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GiftBoxEntity{userId='");
        stringBuilder.append(this.userId);
        stringBuilder.append('\'');
        stringBuilder.append(", giftBoxUniqueCode='");
        stringBuilder.append(this.giftBoxUniqueCode);
        stringBuilder.append('\'');
        stringBuilder.append(", openTime=");
        stringBuilder.append(this.openTime);
        stringBuilder.append(", expirationTime=");
        stringBuilder.append(this.expirationTime);
        stringBuilder.append(", liveId='");
        stringBuilder.append(this.liveId);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
