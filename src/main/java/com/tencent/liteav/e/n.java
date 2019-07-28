package com.tencent.liteav.e;

import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.i;
import com.tencent.liteav.videoediter.ffmpeg.jni.TXFFQuickJointerJNI;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: MoovHeaderProcessor */
public class n {
    private static n a;
    private final i b = i.a();

    public static n a() {
        if (a == null) {
            a = new n();
        }
        return a;
    }

    private n() {
    }

    public void b() {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.b.b() && this.b.c()) {
            File file = new File(i.a().i);
            File file2 = new File(file.getParentFile(), "moov_tmp.mp4");
            if (file2.exists()) {
                file2.delete();
            }
            try {
                file2.createNewFile();
                TXFFQuickJointerJNI tXFFQuickJointerJNI = new TXFFQuickJointerJNI();
                tXFFQuickJointerJNI.setDstPath(file2.getAbsolutePath());
                ArrayList arrayList = new ArrayList();
                arrayList.add(file.getAbsolutePath());
                tXFFQuickJointerJNI.setSrcPaths(arrayList);
                Object obj = tXFFQuickJointerJNI.start() == 0 ? 1 : null;
                tXFFQuickJointerJNI.stop();
                tXFFQuickJointerJNI.destroy();
                if (obj == null) {
                    TXCLog.e("MoovHeaderProcessor", "moov: change to moov type video file error!!");
                } else if (file.delete()) {
                    boolean renameTo = file2.renameTo(file);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("moov: rename file success = ");
                    stringBuilder.append(renameTo);
                    TXCLog.i("MoovHeaderProcessor", stringBuilder.toString());
                    long currentTimeMillis2 = System.currentTimeMillis();
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("doProcessMoovHeader cost time:");
                    stringBuilder2.append(String.valueOf(currentTimeMillis2 - currentTimeMillis));
                    TXCLog.d("MoovHeaderProcessor", stringBuilder2.toString());
                } else {
                    TXCLog.e("MoovHeaderProcessor", "moov: delete original file error!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                TXCLog.e("MoovHeaderProcessor", "moov: create moov tmp file error!");
            }
        }
    }
}
