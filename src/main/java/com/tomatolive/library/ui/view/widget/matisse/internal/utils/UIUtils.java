package com.tomatolive.library.ui.view.widget.matisse.internal.utils;

import android.content.Context;

public class UIUtils {
    public static int spanCount(Context context, int i) {
        int round = Math.round(((float) context.getResources().getDisplayMetrics().widthPixels) / ((float) i));
        return round == 0 ? 1 : round;
    }
}
