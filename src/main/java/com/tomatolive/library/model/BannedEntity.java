package com.tomatolive.library.model;

import android.text.TextUtils;
import java.io.Serializable;

public class BannedEntity implements Serializable {
    public String avatar;
    public String banPostStatus = "1";
    public String clearTime;
    public int count = 0;
    public String createTime = "";
    public String duration = "";
    public String exp = "";
    public String expGrade = "";
    public String lastEnterTime = "";
    public String managerStatus = "1";
    public String name;
    public String userId;

    public boolean isBanned() {
        return TextUtils.equals("1", this.banPostStatus);
    }

    public boolean isHouseManager() {
        return TextUtils.equals("1", this.managerStatus);
    }
}
