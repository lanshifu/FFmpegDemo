package com.tomatolive.library.ui.view.divider.decoration;

import android.content.Context;
import com.blankj.utilcode.util.b;

public class Dp2Px {
    private Dp2Px() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int convert(Context context, float f) {
        return b.a(f);
    }
}
