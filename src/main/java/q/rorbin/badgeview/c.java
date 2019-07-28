package q.rorbin.badgeview;

import android.content.Context;

/* compiled from: DisplayUtil */
public class c {
    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
