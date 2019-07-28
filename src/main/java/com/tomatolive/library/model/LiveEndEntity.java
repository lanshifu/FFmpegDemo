package com.tomatolive.library.model;

import android.text.TextUtils;

public class LiveEndEntity {
    public String avatar;
    public String endTime;
    public String expGrade;
    public String herald;
    public String isFollowed;
    public String liveCount;
    public String liveCoverUrl;
    public String liveId;
    public String maxPopularity = "0";
    public String nickname;
    public String onlineUserCountPeekValue;
    public String popularity = "0";
    public String publishTime;
    public String sex;
    public String startTime;
    public String tag;
    public String topic;
    public String userId;

    public boolean isAttention() {
        return TextUtils.equals(this.isFollowed, "1");
    }
}
