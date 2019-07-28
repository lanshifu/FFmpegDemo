package defpackage;

import android.text.TextUtils;
import com.bumptech.glide.load.model.GlideUrl;

/* compiled from: ImageGlideUrl */
/* renamed from: py */
public class py extends GlideUrl {
    private String a;
    private String b;

    public py(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        super(stringBuilder.toString());
        this.a = str;
        this.b = str2;
    }

    public String getCacheKey() {
        if (!TextUtils.isEmpty(this.b)) {
            return this.b;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append("");
        return stringBuilder.toString();
    }
}
