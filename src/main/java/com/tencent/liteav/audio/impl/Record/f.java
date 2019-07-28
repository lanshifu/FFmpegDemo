package com.tencent.liteav.audio.impl.Record;

import android.content.Context;
import android.media.AudioRecord;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.ugc.TXRecordCommon;
import java.lang.ref.WeakReference;

/* compiled from: TXCAudioSysRecord */
public class f implements Runnable {
    private static final String a;
    private static f b = null;
    private Context c;
    private int d = TXRecordCommon.AUDIO_SAMPLERATE_48000;
    private int e = 1;
    private int f = 16;
    private int g = TXEAudioDef.TXE_AEC_NONE;
    private AudioRecord h;
    private byte[] i = null;
    private WeakReference<h> j;
    private Thread k = null;
    private boolean l = false;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(f.class.getSimpleName());
        a = stringBuilder.toString();
    }

    public static f a() {
        if (b == null) {
            synchronized (f.class) {
                if (b == null) {
                    b = new f();
                }
            }
        }
        return b;
    }

    private f() {
    }

    public synchronized void a(h hVar) {
        if (hVar == null) {
            try {
                this.j = null;
            } catch (Throwable th) {
            }
        } else {
            this.j = new WeakReference(hVar);
        }
    }

    public void a(Context context, int i, int i2, int i3, int i4) {
        b();
        this.c = context;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = i4;
        this.l = true;
        this.k = new Thread(this, "AudioSysRecord Thread");
        this.k.start();
    }

    public void b() {
        this.l = false;
        long currentTimeMillis = System.currentTimeMillis();
        if (!(this.k == null || !this.k.isAlive() || Thread.currentThread().getId() == this.k.getId())) {
            try {
                this.k.join();
            } catch (Exception e) {
                e.printStackTrace();
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("record stop Exception: ");
                stringBuilder.append(e.getMessage());
                TXCLog.e(str, stringBuilder.toString());
            }
        }
        String str2 = a;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("stop record cost time(MS): ");
        stringBuilder2.append(System.currentTimeMillis() - currentTimeMillis);
        TXCLog.i(str2, stringBuilder2.toString());
        this.k = null;
    }

    public synchronized boolean c() {
        return this.l;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ad  */
    private void d() {
        /*
        r18 = this;
        r1 = r18;
        r8 = r1.d;
        r9 = r1.e;
        r10 = r1.f;
        r0 = r1.g;
        r2 = a;
        r3 = "audio record sampleRate = %d, channels = %d, bits = %d, aectype = %d";
        r11 = 4;
        r4 = new java.lang.Object[r11];
        r5 = java.lang.Integer.valueOf(r8);
        r12 = 0;
        r4[r12] = r5;
        r5 = java.lang.Integer.valueOf(r9);
        r13 = 1;
        r4[r13] = r5;
        r5 = java.lang.Integer.valueOf(r10);
        r14 = 2;
        r4[r14] = r5;
        r5 = java.lang.Integer.valueOf(r0);
        r15 = 3;
        r4[r15] = r5;
        r3 = java.lang.String.format(r3, r4);
        com.tencent.liteav.basic.log.TXCLog.i(r2, r3);
        if (r9 != r13) goto L_0x003b;
    L_0x0036:
        r2 = 16;
        r5 = 16;
        goto L_0x003f;
    L_0x003b:
        r2 = 12;
        r5 = 12;
    L_0x003f:
        r7 = 8;
        if (r10 != r7) goto L_0x0045;
    L_0x0043:
        r6 = 3;
        goto L_0x0046;
    L_0x0045:
        r6 = 2;
    L_0x0046:
        r4 = android.media.AudioRecord.getMinBufferSize(r8, r5, r6);
        r2 = com.tencent.liteav.audio.TXEAudioDef.TXE_AEC_SYSTEM;	 Catch:{ IllegalArgumentException -> 0x00a2 }
        if (r0 != r2) goto L_0x0089;
    L_0x004e:
        r0 = a;	 Catch:{ IllegalArgumentException -> 0x00a2 }
        r2 = "audio record type: system aec";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r2);	 Catch:{ IllegalArgumentException -> 0x00a2 }
        r0 = r1.c;	 Catch:{ IllegalArgumentException -> 0x00a2 }
        if (r0 == 0) goto L_0x0066;
    L_0x0059:
        r0 = r1.c;	 Catch:{ IllegalArgumentException -> 0x00a2 }
        r2 = "audio";
        r0 = r0.getSystemService(r2);	 Catch:{ IllegalArgumentException -> 0x00a2 }
        r0 = (android.media.AudioManager) r0;	 Catch:{ IllegalArgumentException -> 0x00a2 }
        r0.setMode(r15);	 Catch:{ IllegalArgumentException -> 0x00a2 }
    L_0x0066:
        r0 = new android.media.AudioRecord;	 Catch:{ IllegalArgumentException -> 0x00a2 }
        r3 = 7;
        r16 = r4 * 2;
        r2 = r0;
        r11 = r4;
        r4 = r8;
        r17 = 8;
        r7 = r16;
        r2.<init>(r3, r4, r5, r6, r7);	 Catch:{ IllegalArgumentException -> 0x00a0 }
        r1.h = r0;	 Catch:{ IllegalArgumentException -> 0x00a0 }
        r0 = r1.c;	 Catch:{ IllegalArgumentException -> 0x00a0 }
        if (r0 == 0) goto L_0x00a9;
    L_0x007b:
        r0 = r1.c;	 Catch:{ IllegalArgumentException -> 0x00a0 }
        r2 = "audio";
        r0 = r0.getSystemService(r2);	 Catch:{ IllegalArgumentException -> 0x00a0 }
        r0 = (android.media.AudioManager) r0;	 Catch:{ IllegalArgumentException -> 0x00a0 }
        r0.setMode(r12);	 Catch:{ IllegalArgumentException -> 0x00a0 }
        goto L_0x00a9;
    L_0x0089:
        r11 = r4;
        r17 = 8;
        r0 = a;	 Catch:{ IllegalArgumentException -> 0x00a0 }
        r2 = "audio record type: system normal";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r2);	 Catch:{ IllegalArgumentException -> 0x00a0 }
        r0 = new android.media.AudioRecord;	 Catch:{ IllegalArgumentException -> 0x00a0 }
        r3 = 1;
        r7 = r11 * 2;
        r2 = r0;
        r4 = r8;
        r2.<init>(r3, r4, r5, r6, r7);	 Catch:{ IllegalArgumentException -> 0x00a0 }
        r1.h = r0;	 Catch:{ IllegalArgumentException -> 0x00a0 }
        goto L_0x00a9;
    L_0x00a0:
        r0 = move-exception;
        goto L_0x00a6;
    L_0x00a2:
        r0 = move-exception;
        r11 = r4;
        r17 = 8;
    L_0x00a6:
        r0.printStackTrace();
    L_0x00a9:
        r0 = r1.h;
        if (r0 == 0) goto L_0x0122;
    L_0x00ad:
        r0 = r1.h;
        r0 = r0.getState();
        if (r0 == r13) goto L_0x00b6;
    L_0x00b5:
        goto L_0x0122;
    L_0x00b6:
        r0 = r9 * 1024;
        r0 = r0 * r10;
        r0 = r0 / 8;
        if (r0 <= r11) goto L_0x00c3;
    L_0x00be:
        r0 = new byte[r11];
        r1.i = r0;
        goto L_0x00c7;
    L_0x00c3:
        r0 = new byte[r0];
        r1.i = r0;
    L_0x00c7:
        r0 = a;
        r2 = "audio record: mic open rate=%dHZ, channels=%d, bits=%d, buffer=%d/%d, state=%d";
        r3 = 6;
        r3 = new java.lang.Object[r3];
        r4 = java.lang.Integer.valueOf(r8);
        r3[r12] = r4;
        r4 = java.lang.Integer.valueOf(r9);
        r3[r13] = r4;
        r4 = java.lang.Integer.valueOf(r10);
        r3[r14] = r4;
        r4 = java.lang.Integer.valueOf(r11);
        r3[r15] = r4;
        r4 = r1.i;
        r4 = r4.length;
        r4 = java.lang.Integer.valueOf(r4);
        r5 = 4;
        r3[r5] = r4;
        r4 = 5;
        r5 = r1.h;
        r5 = r5.getState();
        r5 = java.lang.Integer.valueOf(r5);
        r3[r4] = r5;
        r2 = java.lang.String.format(r2, r3);
        com.tencent.liteav.basic.log.TXCLog.i(r0, r2);
        r0 = r1.h;
        if (r0 == 0) goto L_0x0121;
    L_0x0108:
        r0 = r1.h;	 Catch:{ Exception -> 0x010e }
        r0.startRecording();	 Catch:{ Exception -> 0x010e }
        goto L_0x0121;
    L_0x010e:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = a;
        r2 = "mic startRecording failed.";
        com.tencent.liteav.basic.log.TXCLog.e(r0, r2);
        r0 = com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_RECORD_ERR_NO_MIC_PERMIT;
        r2 = "start recording failed!";
        r1.a(r0, r2);
        return;
    L_0x0121:
        return;
    L_0x0122:
        r0 = a;
        r2 = "audio record: initialize the mic failed.";
        com.tencent.liteav.basic.log.TXCLog.e(r0, r2);
        r18.e();
        r0 = com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_RECORD_ERR_NO_MIC_PERMIT;
        r2 = "open mic failed!";
        r1.a(r0, r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.audio.impl.Record.f.d():void");
    }

    private void e() {
        if (this.h != null) {
            TXCLog.i(a, "stop mic");
            try {
                this.h.setRecordPositionUpdateListener(null);
                this.h.stop();
                this.h.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.h = null;
        this.i = null;
    }

    private void a(byte[] bArr, int i, long j) {
        h hVar;
        synchronized (this) {
            hVar = this.j != null ? (h) this.j.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordPCM(bArr, i, j);
        } else {
            TXCLog.e(a, "onRecordPcmData:no callback");
        }
    }

    private void a(int i, String str) {
        h hVar;
        synchronized (this) {
            hVar = this.j != null ? (h) this.j.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordError(i, str);
        } else {
            TXCLog.e(a, "onRecordError:no callback");
        }
    }

    private void f() {
        h hVar;
        synchronized (this) {
            hVar = this.j != null ? (h) this.j.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordStart();
        } else {
            TXCLog.e(a, "onRecordStart:no callback");
        }
    }

    private void g() {
        h hVar;
        synchronized (this) {
            hVar = this.j != null ? (h) this.j.get() : null;
        }
        if (hVar != null) {
            hVar.onAudioRecordStop();
        } else {
            TXCLog.e(a, "onRecordStop:no callback");
        }
    }

    public void run() {
        if (this.l) {
            int i;
            f();
            d();
            loop0:
            while (true) {
                i = 0;
                int i2 = 0;
                while (this.l && !Thread.interrupted() && this.h != null && i <= 5) {
                    System.currentTimeMillis();
                    int read = this.h.read(this.i, i2, this.i.length - i2);
                    if (read == this.i.length - i2) {
                        a(this.i, this.i.length, TXCTimeUtil.getTimeTick());
                    } else if (read <= 0) {
                        String str = a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("read pcm eror, len =");
                        stringBuilder.append(read);
                        TXCLog.e(str, stringBuilder.toString());
                        i++;
                    } else {
                        i2 += read;
                    }
                }
            }
            e();
            if (i > 5) {
                a(TXEAudioDef.TXE_AUDIO_RECORD_ERR_NO_MIC_PERMIT, "read data failed!");
            } else {
                g();
            }
            return;
        }
        TXCLog.w(a, "audio record: abandom start audio sys record thread!");
    }
}
