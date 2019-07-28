package com.tencent.liteav.videoediter.ffmpeg.jni;

import android.util.Log;
import com.tencent.liteav.basic.log.TXCLog;
import com.yalantis.ucrop.view.CropImageView;

public class TXFFQuickJointerJNI {
    private static final String TAG = "TXFFQuickJointerJNI";
    private long handle;
    private boolean isInitSuccess;
    private a mCallback;
    private int mTotalVideoNums;

    public interface a {
        void a(float f);
    }

    private native void destroy(long j);

    private native int getVideoHeight(long j);

    private native int getVideoWidth(long j);

    private native long init();

    private native void setDstPath(long j, String str);

    private native void setSrcPaths(long j, String[] strArr);

    private native int start(long j);

    private native int stop(long j);

    private native int verify(long j);

    public TXFFQuickJointerJNI() {
        this.handle = -1;
        this.handle = init();
        if (this.handle != -1) {
            this.isInitSuccess = true;
        }
    }

    public synchronized void destroy() {
        if (this.isInitSuccess) {
            destroy(this.handle);
            this.isInitSuccess = false;
            this.handle = -1;
        }
    }

    public synchronized int getVideoWidth() {
        if (!this.isInitSuccess) {
            return 0;
        }
        return getVideoWidth(this.handle);
    }

    public synchronized int getVideoHeight() {
        if (!this.isInitSuccess) {
            return 0;
        }
        return getVideoHeight(this.handle);
    }

    /* JADX WARNING: Missing block: B:17:0x003c, code skipped:
            return;
     */
    public synchronized void setSrcPaths(java.util.List<java.lang.String> r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.isInitSuccess;	 Catch:{ all -> 0x003d }
        if (r0 == 0) goto L_0x003b;
    L_0x0005:
        if (r4 == 0) goto L_0x0032;
    L_0x0007:
        r0 = r4.size();	 Catch:{ all -> 0x003d }
        if (r0 != 0) goto L_0x000e;
    L_0x000d:
        goto L_0x0032;
    L_0x000e:
        r0 = r4.size();	 Catch:{ all -> 0x003d }
        r3.mTotalVideoNums = r0;	 Catch:{ all -> 0x003d }
        r0 = r4.size();	 Catch:{ all -> 0x003d }
        r0 = new java.lang.String[r0];	 Catch:{ all -> 0x003d }
        r1 = 0;
    L_0x001b:
        r2 = r4.size();	 Catch:{ all -> 0x003d }
        if (r1 >= r2) goto L_0x002c;
    L_0x0021:
        r2 = r4.get(r1);	 Catch:{ all -> 0x003d }
        r2 = (java.lang.String) r2;	 Catch:{ all -> 0x003d }
        r0[r1] = r2;	 Catch:{ all -> 0x003d }
        r1 = r1 + 1;
        goto L_0x001b;
    L_0x002c:
        r1 = r3.handle;	 Catch:{ all -> 0x003d }
        r3.setSrcPaths(r1, r0);	 Catch:{ all -> 0x003d }
        goto L_0x003b;
    L_0x0032:
        r4 = "TXFFQuickJointerJNI";
        r0 = "quick joiner path empty!!!";
        com.tencent.liteav.basic.log.TXCLog.e(r4, r0);	 Catch:{ all -> 0x003d }
        monitor-exit(r3);
        return;
    L_0x003b:
        monitor-exit(r3);
        return;
    L_0x003d:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoediter.ffmpeg.jni.TXFFQuickJointerJNI.setSrcPaths(java.util.List):void");
    }

    public synchronized void setDstPath(String str) {
        if (this.isInitSuccess) {
            setDstPath(this.handle, str);
        }
    }

    public synchronized int start() {
        if (!this.isInitSuccess) {
            return -1;
        }
        if (this.mTotalVideoNums == 0) {
            TXCLog.e(TAG, "quick joiner path empty!!!");
            return -1;
        }
        return start(this.handle);
    }

    public synchronized int verify() {
        if (!this.isInitSuccess) {
            return -1;
        }
        return verify(this.handle);
    }

    public synchronized void stop() {
        if (this.isInitSuccess) {
            stop(this.handle);
        }
    }

    public void setOnJoinerCallback(a aVar) {
        this.mCallback = aVar;
    }

    public void callbackFromNative(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("callbackFromNative: index = ");
        stringBuilder.append(i);
        Log.i(str, stringBuilder.toString());
        if (this.mCallback != null) {
            this.mCallback.a(this.mTotalVideoNums > 0 ? ((float) (i + 1)) / ((float) this.mTotalVideoNums) : CropImageView.DEFAULT_ASPECT_RATIO);
        }
    }
}
