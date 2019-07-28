package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* compiled from: NetworkUtils */
/* renamed from: fl */
public class fl {
    private static NetworkInfo c(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean a(Context context) {
        NetworkInfo c = fl.c(context);
        return c != null && c.isAvailable();
    }

    public static boolean b(Context context) {
        NetworkInfo c = fl.c(context);
        return c != null && c.isConnected();
    }
}
