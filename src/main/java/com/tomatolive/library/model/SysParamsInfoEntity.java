package com.tomatolive.library.model;

import android.text.TextUtils;

public class SysParamsInfoEntity {
    public String enableGradeUpperLimit = "";

    public boolean isEnableGrade120() {
        return TextUtils.equals(this.enableGradeUpperLimit, "1");
    }
}
