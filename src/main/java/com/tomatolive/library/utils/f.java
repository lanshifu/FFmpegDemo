package com.tomatolive.library.utils;

import android.text.TextUtils;
import com.blankj.utilcode.util.e;
import java.io.File;
import java.io.FileFilter;

/* compiled from: FileFilterUtils */
public class f implements FileFilter {
    private String a;

    public f(String str) {
        this.a = str;
    }

    public boolean accept(File file) {
        if (e.a(file)) {
            return true;
        }
        return TextUtils.equals(e.d(file), this.a);
    }
}
