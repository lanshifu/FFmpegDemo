package sj.keyboard.interfaces;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.EditText;
import java.io.IOException;

public abstract class EmoticonFilter {
    public abstract void filter(EditText editText, CharSequence charSequence, int i, int i2, int i3);

    public static Drawable getDrawableFromAssets(Context context, String str) {
        try {
            return new BitmapDrawable(BitmapFactory.decodeStream(context.getAssets().open(str)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Drawable getDrawable(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.indexOf(".") >= 0) {
            str = str.substring(0, str.indexOf("."));
        }
        int identifier = context.getResources().getIdentifier(str, "mipmap", context.getPackageName());
        if (identifier <= 0) {
            identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        }
        try {
            return VERSION.SDK_INT >= 21 ? context.getResources().getDrawable(identifier, (Theme) null) : context.getResources().getDrawable(identifier);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Drawable getDrawable(Context context, int i) {
        if (i <= 0) {
            return null;
        }
        try {
            return VERSION.SDK_INT >= 21 ? context.getResources().getDrawable(i, (Theme) null) : context.getResources().getDrawable(i);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
