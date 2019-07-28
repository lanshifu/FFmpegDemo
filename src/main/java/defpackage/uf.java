package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;

/* compiled from: ContextKit */
/* renamed from: uf */
public class uf {
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0018 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0015  */
    public static android.app.Activity a(android.content.Context r3) {
        /*
        r0 = 0;
        if (r3 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = r3 instanceof android.app.Activity;
        if (r1 != 0) goto L_0x0013;
    L_0x0008:
        r2 = r3 instanceof android.content.ContextWrapper;
        if (r2 == 0) goto L_0x0013;
    L_0x000c:
        r3 = (android.content.ContextWrapper) r3;
        r3 = r3.getBaseContext();
        goto L_0x0004;
    L_0x0013:
        if (r1 == 0) goto L_0x0018;
    L_0x0015:
        r3 = (android.app.Activity) r3;
        return r3;
    L_0x0018:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uf.a(android.content.Context):android.app.Activity");
    }

    public static boolean b(Context context) {
        Activity a = uf.a(context);
        if (a == null || a.isFinishing()) {
            return false;
        }
        if (VERSION.SDK_INT < 17 || !a.isDestroyed()) {
            return true;
        }
        return false;
    }
}
