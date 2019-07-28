package com.tomatolive.library.model.db;

public class GiftDownloadItemDBEntity extends BaseDBEntity {
    public int active_time;
    public String boxId;
    public String boxName;
    public String boxType;
    public String broadcastRange;
    public int duration;
    public int effect_type;
    public String giftDirPath = "";
    public String giftId;
    public String imgurl = "";
    public String isBroadcast;
    public String jsonname;
    public String name = "";
    public int num = 1;
    public String onlineUrl;
    public String price = "";
    public String tomatoPrice = "";
    public String typeid = "";
    public String zipurl;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GiftDownloadItemDBEntity{giftId='");
        stringBuilder.append(this.giftId);
        stringBuilder.append('\'');
        stringBuilder.append(", giftDirPath='");
        stringBuilder.append(this.giftDirPath.length());
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
