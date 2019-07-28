package com.tencent.liteav.audio.impl.Record;

import android.content.Context;
import com.tencent.liteav.basic.log.TXCLog;
import java.lang.ref.WeakReference;

/* compiled from: TXCAudioBaseRecord */
public abstract class b {
    protected int a = 0;
    protected int b = 0;
    protected int c = 0;
    private WeakReference<h> d = null;

    public void a(Context context, int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public synchronized void a(h hVar) {
        if (hVar == null) {
            try {
                this.d = null;
            } catch (Throwable th) {
            }
        } else {
            this.d = new WeakReference(hVar);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(byte[] bArr, int i, long j) {
        h hVar;
        synchronized (this) {
            hVar = this.d != null ? (h) this.d.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordPCM(bArr, i, j);
        } else {
            TXCLog.e("AudioCenter:TXCAudioBaseRecord", "onRecordPcmData:no callback");
        }
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        h hVar;
        synchronized (this) {
            hVar = this.d != null ? (h) this.d.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordStart();
        } else {
            TXCLog.e("AudioCenter:TXCAudioBaseRecord", "onRecordStart:no callback");
        }
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        h hVar;
        synchronized (this) {
            hVar = this.d != null ? (h) this.d.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordStop();
        } else {
            TXCLog.e("AudioCenter:TXCAudioBaseRecord", "onRecordStop:no callback");
        }
    }
}
