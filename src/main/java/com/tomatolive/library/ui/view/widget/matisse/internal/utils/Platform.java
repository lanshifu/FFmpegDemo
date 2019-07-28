package com.tomatolive.library.ui.view.widget.matisse.internal.utils;

import android.os.Build.VERSION;

public class Platform {
    public static boolean hasICS() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean hasKitKat() {
        return VERSION.SDK_INT >= 19;
    }
}
