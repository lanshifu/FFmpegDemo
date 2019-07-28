package org.greenrobot.eventbus.util;

import android.util.Log;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ExceptionToResourceMapping */
public class c {
    public final Map<Class<? extends Throwable>, Integer> a;

    public Integer a(Throwable th) {
        StringBuilder stringBuilder;
        Throwable th2 = th;
        int i = 20;
        do {
            Integer b = b(th2);
            if (b != null) {
                return b;
            }
            th2 = th2.getCause();
            i--;
            if (i <= 0 || th2 == th) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("No specific message resource ID found for ");
                stringBuilder.append(th);
                Log.d("EventBus", stringBuilder.toString());
            }
        } while (th2 != null);
        stringBuilder = new StringBuilder();
        stringBuilder.append("No specific message resource ID found for ");
        stringBuilder.append(th);
        Log.d("EventBus", stringBuilder.toString());
        return null;
    }

    /* Access modifiers changed, original: protected */
    public Integer b(Throwable th) {
        Class cls = th.getClass();
        Integer num = (Integer) this.a.get(cls);
        if (num == null) {
            Class cls2 = null;
            for (Entry entry : this.a.entrySet()) {
                Class cls3 = (Class) entry.getKey();
                if (cls3.isAssignableFrom(cls) && (cls2 == null || cls2.isAssignableFrom(cls3))) {
                    num = (Integer) entry.getValue();
                    cls2 = cls3;
                }
            }
        }
        return num;
    }
}
