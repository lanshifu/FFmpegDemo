package defpackage;

import android.os.Environment;
import android.os.StatFs;

/* compiled from: SDCardUtils */
/* renamed from: ep */
public class ep {
    public static long a() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
    }
}
