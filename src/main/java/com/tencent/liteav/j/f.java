package com.tencent.liteav.j;

import android.os.Environment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: VideoUtil */
public class f {
    public static String a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory());
        stringBuilder.append(File.separator);
        stringBuilder.append("txrtmp");
        File file = new File(stringBuilder.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(valueOf);
        stringBuilder2.append("000");
        valueOf = simpleDateFormat.format(new Date(Long.valueOf(stringBuilder2.toString()).longValue()));
        String str = null;
        if (i == 0) {
            str = String.format("TXVideo_%s_reverse.mp4", new Object[]{valueOf});
        } else if (i == 1) {
            str = String.format("TXVideo_%s_process.mp4", new Object[]{valueOf});
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(file);
        stringBuilder3.append("/");
        stringBuilder3.append(str);
        return stringBuilder3.toString();
    }
}
