package com.tomatolive.library.utils;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.TypedValue;
import com.tomatolive.library.a;

/* compiled from: SystemUtils */
public class w {
    public static String a(int i) {
        Application c = a.a().c();
        return c != null ? c.getResources().getString(i) : "";
    }

    public static Bitmap a(Resources resources, int i) {
        TypedValue typedValue = new TypedValue();
        resources.openRawResource(i, typedValue);
        Options options = new Options();
        options.inTargetDensity = typedValue.density;
        return BitmapFactory.decodeResource(resources, i, options);
    }
}
