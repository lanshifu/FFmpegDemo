package com.tencent.liteav;

import android.content.Context;
import android.view.Surface;
import android.view.TextureView;
import com.tencent.liteav.basic.d.a;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.TXLivePlayer.ITXAudioRawDataListener;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.ugc.TXRecordCommon.ITXVideoRecordListener;
import java.lang.ref.WeakReference;

/* compiled from: TXIPlayer */
public abstract class s {
    protected i b = null;
    protected Context c = null;
    protected TXCloudVideoView d = null;
    protected WeakReference<a> e;

    public int a(String str) {
        return -1;
    }

    public abstract int a(String str, int i);

    public abstract int a(boolean z);

    public abstract void a(int i);

    public void a(int i, int i2) {
    }

    public void a(Context context, int i) {
    }

    public void a(Surface surface) {
    }

    public void a(t tVar) {
    }

    public void a(ITXAudioRawDataListener iTXAudioRawDataListener) {
    }

    public void a(ITXVideoRecordListener iTXVideoRecordListener) {
    }

    public boolean a(byte[] bArr) {
        return false;
    }

    public abstract void b(int i);

    public abstract void b(boolean z);

    public abstract int c(int i);

    public abstract boolean c();

    public TextureView d() {
        return null;
    }

    public abstract int e();

    public boolean f() {
        return false;
    }

    public void g() {
    }

    public s(Context context) {
        if (context != null) {
            this.c = context.getApplicationContext();
        }
    }

    public i p() {
        return this.b;
    }

    public void a(i iVar) {
        this.b = iVar;
        if (this.b == null) {
            this.b = new i();
        }
    }

    public void a() {
        TXCLog.w("TXIPlayer", "pause not support");
    }

    public void b() {
        TXCLog.w("TXIPlayer", "resume not support");
    }

    public void a_(int i) {
        TXCLog.w("TXIPlayer", "seek not support");
    }

    public void a(TXCloudVideoView tXCloudVideoView) {
        this.d = tXCloudVideoView;
    }

    public void a(a aVar) {
        this.e = new WeakReference(aVar);
    }

    public void c(boolean z) {
        TXCLog.w("TXIPlayer", "autoPlay not implement");
    }

    public void b(float f) {
        TXCLog.w("TXIPlayer", "rate not implement");
    }
}
