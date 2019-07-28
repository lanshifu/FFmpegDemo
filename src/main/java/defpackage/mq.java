package defpackage;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.Window;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;

/* compiled from: ImmersiveManage */
/* renamed from: mq */
public class mq {
    public static void a(Activity activity, int i, int i2, boolean z) {
        if (VERSION.SDK_INT >= 23) {
            mq.a(activity, false, false, i, i2, z);
        }
    }

    public static void a(Activity activity, boolean z, boolean z2, int i, int i2, boolean z3) {
        try {
            Window window = activity.getWindow();
            if (VERSION.SDK_INT < 19 || VERSION.SDK_INT >= 21) {
                if (VERSION.SDK_INT >= 21) {
                    boolean z4 = false;
                    if (z && z2) {
                        window.clearFlags(201326592);
                        if (i == 0) {
                            z4 = true;
                        }
                        mr.a(activity, z, z2, z4, z3);
                        window.addFlags(CheckView.UNCHECKED);
                    } else if (!z && !z2) {
                        window.requestFeature(1);
                        window.clearFlags(201326592);
                        if (i == 0) {
                            z4 = true;
                        }
                        mr.a(activity, z, z2, z4, z3);
                        window.addFlags(CheckView.UNCHECKED);
                    } else if (!z && z2) {
                        window.requestFeature(1);
                        window.clearFlags(201326592);
                        if (i == 0) {
                            z4 = true;
                        }
                        mr.a(activity, z, z2, z4, z3);
                        window.addFlags(CheckView.UNCHECKED);
                    } else {
                        return;
                    }
                    window.setStatusBarColor(i);
                    window.setNavigationBarColor(i2);
                }
            }
            window.setFlags(67108864, 67108864);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
