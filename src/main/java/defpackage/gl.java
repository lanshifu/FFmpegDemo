package defpackage;

import android.content.Context;
import java.util.List;

/* compiled from: Util */
/* renamed from: gl */
public class gl {
    public static int a(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static boolean a(List list) {
        return list == null || list.size() == 0;
    }
}
