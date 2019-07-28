package com.tencent.liteav.audio.impl.Record;

import android.content.Context;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* compiled from: TXCAudioBGMRecord */
public class a implements Runnable {
    private WeakReference<h> a;
    private Context b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private Thread g;
    private byte[] h;

    public synchronized void a(h hVar) {
        if (hVar == null) {
            try {
                this.a = null;
            } catch (Throwable th) {
            }
        } else {
            this.a = new WeakReference(hVar);
        }
    }

    public void a(Context context, int i, int i2, int i3) {
        a();
        this.b = context;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = true;
        this.g = new Thread(this, "AudioSysRecord Thread");
        this.g.start();
    }

    public void a() {
        this.f = false;
        long currentTimeMillis = System.currentTimeMillis();
        if (!(this.g == null || !this.g.isAlive() || Thread.currentThread().getId() == this.g.getId())) {
            try {
                this.g.join();
            } catch (Exception e) {
                e.printStackTrace();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("record stop Exception: ");
                stringBuilder.append(e.getMessage());
                TXCLog.e("AudioCenter:TXCAudioBGMRecord", stringBuilder.toString());
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("stop record cost time(MS): ");
        stringBuilder2.append(System.currentTimeMillis() - currentTimeMillis);
        TXCLog.i("AudioCenter:TXCAudioBGMRecord", stringBuilder2.toString());
        this.g = null;
    }

    public boolean b() {
        return this.f;
    }

    private void a(byte[] bArr, int i, long j) {
        h hVar;
        synchronized (this) {
            hVar = this.a != null ? (h) this.a.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordPCM(bArr, i, j);
        } else {
            TXCLog.e("AudioCenter:TXCAudioBGMRecord", "onRecordPcmData:no callback");
        }
    }

    private void c() {
        h hVar;
        synchronized (this) {
            hVar = this.a != null ? (h) this.a.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordStart();
        } else {
            TXCLog.e("AudioCenter:TXCAudioBGMRecord", "onRecordStart:no callback");
        }
    }

    private void d() {
        h hVar;
        synchronized (this) {
            hVar = this.a != null ? (h) this.a.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordStop();
        } else {
            TXCLog.e("AudioCenter:TXCAudioBGMRecord", "onRecordStop:no callback");
        }
    }

    public void run() {
        if (this.f) {
            c();
            int i = this.c;
            int i2 = this.d;
            int i3 = this.e;
            int i4 = ((i2 * Filter.K) * i3) / 8;
            this.h = new byte[i4];
            Arrays.fill(this.h, (byte) 0);
            long j = 0;
            long currentTimeMillis = System.currentTimeMillis();
            while (this.f && !Thread.interrupted()) {
                if (((((((System.currentTimeMillis() - currentTimeMillis) * ((long) i)) * ((long) i2)) * ((long) i3)) / 8) / 1000) - j < ((long) i4)) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    j += (long) this.h.length;
                    a(this.h, this.h.length, TXCTimeUtil.getTimeTick());
                }
            }
            d();
            return;
        }
        TXCLog.w("AudioCenter:TXCAudioBGMRecord", "audio record: abandom start audio sys record thread!");
    }
}
