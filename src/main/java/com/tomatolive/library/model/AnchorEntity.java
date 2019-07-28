package com.tomatolive.library.model;

import android.text.TextUtils;
import java.io.Serializable;

public class AnchorEntity implements Serializable {
    public String anchor_id;
    public String avatar;
    public String balance = "";
    public String contribution = "";
    public String exp = "";
    public String expGrade = "";
    public String expend = "0";
    public String followAnchorCount = "0";
    public String followStatus = "0";
    public String followed = "0";
    public String followerCount = "0";
    public String guardType = "";
    public String income = "0";
    public int isChecked;
    public int isFrozen;
    public String isLiving = "0";
    public String liveId;
    public String liveStatus = "0";
    public String name;
    public String nextGradeExp = "";
    public String nickname;
    public String phone;
    public String realName;
    public String sex;
    public String sign;
    public String streamName = "";
    public String userId = "";

    public boolean isAttention() {
        return TextUtils.equals("1", this.followed) || TextUtils.equals("1", this.followStatus);
    }
}
