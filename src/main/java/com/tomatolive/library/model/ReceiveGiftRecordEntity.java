package com.tomatolive.library.model;

import android.text.TextUtils;

public class ReceiveGiftRecordEntity {
    public String anchorId;
    public String avatar;
    public String createTime;
    public String expGrade;
    public String giftName;
    public String giftNum;
    public int guardType = 0;
    public String liveAdminStatus = "";
    public String role;
    public String sex;
    public String tomatoPrice;
    public String userId;
    public String userName;

    public String getRole() {
        if (TextUtils.equals(this.liveAdminStatus, "1")) {
            return "5";
        }
        return this.role;
    }
}
