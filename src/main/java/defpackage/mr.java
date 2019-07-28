package defpackage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: LightStatusBarUtils */
/* renamed from: mr */
public class mr {
    public static void a(Activity activity, boolean z, boolean z2, boolean z3, boolean z4) {
        switch (ms.a()) {
            case 1:
                if (ms.b() >= 7) {
                    mr.d(activity, z, z2, z3, z4);
                    return;
                } else {
                    mr.b(activity, z, z2, z3, z4);
                    return;
                }
            case 2:
                mr.c(activity, z, z2, z3, z4);
                return;
            case 3:
                mr.d(activity, z, z2, z3, z4);
                return;
            default:
                return;
        }
    }

    private static boolean b(Activity activity, boolean z, boolean z2, boolean z3, boolean z4) {
        mr.a(activity, z, z2);
        Class cls = activity.getWindow().getClass();
        try {
            Class cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z4 ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
            return true;
        } catch (Exception unused) {
            mr.d(activity, z, z2, z3, z4);
            return false;
        }
    }

    private static boolean c(Activity activity, boolean z, boolean z2, boolean z3, boolean z4) {
        if (activity != null) {
            mr.a(activity, z, z2);
            try {
                LayoutParams attributes = activity.getWindow().getAttributes();
                Field declaredField = LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z4 ? i | i2 : (i ^ -1) & i2);
                activity.getWindow().setAttributes(attributes);
                return true;
            } catch (Exception unused) {
                mr.d(activity, z, z2, z3, z4);
            }
        }
        return false;
    }

    @TargetApi(11)
    private static void d(Activity activity, boolean z, boolean z2, boolean z3, boolean z4) {
        if (z3) {
            try {
                Window window = activity.getWindow();
                if (VERSION.SDK_INT >= 21) {
                    if (z && z2) {
                        if (!z4 || VERSION.SDK_INT < 23) {
                            window.getDecorView().setSystemUiVisibility(256);
                        } else {
                            window.getDecorView().setSystemUiVisibility(8448);
                        }
                    } else if (z || z2) {
                        if (!z && z2) {
                            if (!z4 || VERSION.SDK_INT < 23) {
                                window.getDecorView().setSystemUiVisibility(1280);
                            } else {
                                window.getDecorView().setSystemUiVisibility(9472);
                            }
                        }
                    } else if (!z4 || VERSION.SDK_INT < 23) {
                        window.getDecorView().setSystemUiVisibility(1280);
                    } else {
                        window.getDecorView().setSystemUiVisibility(9472);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            View decorView = activity.getWindow().getDecorView();
            if (!z4 || VERSION.SDK_INT < 23) {
                decorView.setSystemUiVisibility(0);
            } else {
                decorView.setSystemUiVisibility(8192);
            }
        }
    }

    private static void a(Activity activity, boolean z, boolean z2) {
        if (VERSION.SDK_INT < 16) {
            return;
        }
        if (z && z2) {
            activity.getWindow().getDecorView().setSystemUiVisibility(256);
        } else if (!z && !z2) {
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        } else if (!z && z2) {
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        }
    }
}
