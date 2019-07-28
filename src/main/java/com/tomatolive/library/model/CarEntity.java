package com.tomatolive.library.model;

import android.text.TextUtils;
import com.tomatolive.library.utils.z;
import java.io.Serializable;

public class CarEntity implements Serializable {
    public String brief;
    public String description = "";
    public String id;
    public String imgUrl;
    public String isPermission;
    public String monthGold;
    public String monthPrice;
    public String name;
    public String onlineUrl;
    public String permissionUserList;
    public String weekGold;
    public String weekPrice;
    public String zipUrl;

    public boolean isPublicCar() {
        if (isPublicPermission()) {
            return true;
        }
        if (TextUtils.isEmpty(this.permissionUserList)) {
            return false;
        }
        if (!this.permissionUserList.contains(",")) {
            return TextUtils.equals(this.permissionUserList, z.a().d());
        }
        String[] split = TextUtils.split(this.permissionUserList, ",");
        if (split.length > 0) {
            return TextUtils.equals(split[0], z.a().d());
        }
        return true;
    }

    public boolean isPublicPermission() {
        return TextUtils.equals(this.isPermission, "0");
    }
}
