package defpackage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import io.fabric.sdk.android.h;

/* compiled from: PreferenceStoreImpl */
/* renamed from: wa */
public class wa implements vz {
    private final SharedPreferences a;
    private final String b;
    private final Context c;

    public wa(Context context, String str) {
        if (context != null) {
            this.c = context;
            this.b = str;
            this.a = this.c.getSharedPreferences(this.b, 0);
            return;
        }
        throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
    }

    @Deprecated
    public wa(h hVar) {
        this(hVar.r(), hVar.getClass().getName());
    }

    public SharedPreferences a() {
        return this.a;
    }

    public Editor b() {
        return this.a.edit();
    }

    @TargetApi(9)
    public boolean a(Editor editor) {
        if (VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }
}
