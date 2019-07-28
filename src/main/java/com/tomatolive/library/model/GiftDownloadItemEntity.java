package com.tomatolive.library.model;

import android.text.TextUtils;
import com.blankj.utilcode.util.e;

public class GiftDownloadItemEntity extends BaseGiftBackpackEntity {
    public int active_time;
    public String boxId;
    public String boxName;
    public String boxType;
    public String broadcastRange;
    public int duration;
    public int effect_type;
    public String giftDirPath = "";
    public String id = "";
    public String imgurl = "";
    public String isBroadcast;
    public boolean isStayTuned = false;
    public String jsonname;
    public String name = "";
    public int num = 1;
    public String onlineUrl;
    public String price = "";
    public String tomatoPrice = "";
    public String typeid = "";
    public String zipurl;

    public GiftDownloadItemEntity(String str, String str2, boolean z) {
        this.name = str;
        this.price = str2;
        this.isStayTuned = z;
    }

    public boolean isBigAnim() {
        return this.effect_type == 2;
    }

    public boolean isBroadcastFlag() {
        return TextUtils.equals(this.isBroadcast, "1");
    }

    public String getLocalDirName() {
        return e.c(this.zipurl);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GiftDownloadItemEntity{giftDirPath='");
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
