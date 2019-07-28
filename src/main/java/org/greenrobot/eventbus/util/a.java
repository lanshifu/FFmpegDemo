package org.greenrobot.eventbus.util;

import android.content.res.Resources;
import android.util.Log;
import org.greenrobot.eventbus.c;

/* compiled from: ErrorDialogConfig */
public class a {
    final Resources a;
    final int b;
    final int c;
    final c d;
    c e;
    boolean f;
    String g;
    int h;
    Class<?> i;

    public int a(Throwable th) {
        Integer a = this.d.a(th);
        if (a != null) {
            return a.intValue();
        }
        String str = c.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("No specific message ressource ID found for ");
        stringBuilder.append(th);
        Log.d(str, stringBuilder.toString());
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public c a() {
        return this.e != null ? this.e : c.a();
    }
}
