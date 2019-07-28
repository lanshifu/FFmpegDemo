package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;

/* compiled from: ChunkedTrackBlacklistUtil */
/* renamed from: jp */
public final class jp {
    public static boolean a(e eVar, int i, Exception exception) {
        return jp.a(eVar, i, exception, 60000);
    }

    public static boolean a(e eVar, int i, Exception exception, long j) {
        if (!jp.a(exception)) {
            return false;
        }
        boolean a = eVar.a(i, j);
        int i2 = ((InvalidResponseCodeException) exception).responseCode;
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Blacklisted: duration=");
            stringBuilder.append(j);
            stringBuilder.append(", responseCode=");
            stringBuilder.append(i2);
            stringBuilder.append(", format=");
            stringBuilder.append(eVar.a(i));
            Log.w("ChunkedTrackBlacklist", stringBuilder.toString());
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Blacklisting failed (cannot blacklist last enabled track): responseCode=");
            stringBuilder2.append(i2);
            stringBuilder2.append(", format=");
            stringBuilder2.append(eVar.a(i));
            Log.w("ChunkedTrackBlacklist", stringBuilder2.toString());
        }
        return a;
    }

    public static boolean a(Exception exception) {
        boolean z = false;
        if (!(exception instanceof InvalidResponseCodeException)) {
            return false;
        }
        int i = ((InvalidResponseCodeException) exception).responseCode;
        if (i == 404 || i == 410) {
            z = true;
        }
        return z;
    }
}
