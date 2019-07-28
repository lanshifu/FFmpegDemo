package defpackage;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;

/* compiled from: TimeBasedFileRollOverRunnable */
/* renamed from: vw */
public class vw implements Runnable {
    private final Context a;
    private final vs b;

    public vw(Context context, vs vsVar) {
        this.a = context;
        this.b = vsVar;
    }

    public void run() {
        try {
            CommonUtils.a(this.a, "Performing time based file roll over.");
            if (!this.b.c()) {
                this.b.d();
            }
        } catch (Exception e) {
            CommonUtils.a(this.a, "Failed to roll over file", e);
        }
    }
}
