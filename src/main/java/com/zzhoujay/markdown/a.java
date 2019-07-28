package com.zzhoujay.markdown;

import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.widget.TextView;
import defpackage.th;
import java.io.IOException;

/* compiled from: MarkDown */
public class a {
    public static Spanned a(String str, ImageGetter imageGetter, TextView textView) {
        try {
            return new b(str, new th(textView, imageGetter)).a();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
