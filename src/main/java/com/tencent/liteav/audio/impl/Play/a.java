package com.tencent.liteav.audio.impl.Play;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.c;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/* compiled from: TXCAudioHWDecoder */
public class a implements Runnable {
    private static final String a;
    private WeakReference<c> b = null;
    private MediaCodec c = null;
    private BufferInfo d;
    private MediaFormat e;
    private long f = 0;
    private volatile boolean g = false;
    private Vector<com.tencent.liteav.basic.g.a> h;
    private List i;
    private Thread j = null;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(a.class.getSimpleName());
        a = stringBuilder.toString();
    }

    public void a(WeakReference<c> weakReference) {
        if (this.g) {
            b();
        }
        this.b = weakReference;
        this.f = 0;
        this.h = new Vector();
        this.i = new ArrayList();
        this.g = true;
        this.j = new Thread(this);
        this.j.setName(a);
        this.j.start();
    }

    public void a(com.tencent.liteav.basic.g.a aVar) {
        if (this.g) {
            synchronized (this.h) {
                if (this.h != null) {
                    this.h.add(aVar);
                }
            }
        }
    }

    public long a() {
        if (this.e == null) {
            return 0;
        }
        float integer = (float) this.e.getInteger("sample-rate");
        if (integer != CropImageView.DEFAULT_ASPECT_RATIO) {
            return (long) (((((float) this.i.size()) * 1024.0f) * 1000.0f) / integer);
        }
        return 0;
    }

    public void b() {
        this.g = false;
        if (this.j != null) {
            try {
                this.j.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.j = null;
        }
    }

    public void run() {
        Object e;
        String str;
        StringBuilder stringBuilder;
        while (true) {
            ByteBuffer[] byteBufferArr = null;
            if (this.g) {
                boolean isEmpty;
                synchronized (this.h) {
                    isEmpty = this.h.isEmpty();
                }
                if (isEmpty) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    com.tencent.liteav.basic.g.a aVar;
                    int i = -1;
                    if (this.c != null) {
                        try {
                            byteBufferArr = this.c.getInputBuffers();
                            i = 1;
                            try {
                                int dequeueInputBuffer = this.c.dequeueInputBuffer(10000);
                                if (dequeueInputBuffer >= 0) {
                                    i = dequeueInputBuffer;
                                } else {
                                    return;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                str = a;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Exception. step: ");
                                stringBuilder.append(i);
                                stringBuilder.append(", error: ");
                                stringBuilder.append(e);
                                TXCLog.e(str, stringBuilder.toString());
                                return;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            i = 0;
                            str = a;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Exception. step: ");
                            stringBuilder.append(i);
                            stringBuilder.append(", error: ");
                            stringBuilder.append(e);
                            TXCLog.e(str, stringBuilder.toString());
                            return;
                        }
                    }
                    synchronized (this.h) {
                        aVar = (com.tencent.liteav.basic.g.a) this.h.remove(0);
                    }
                    if (aVar.packetType == com.tencent.liteav.basic.a.a.k) {
                        b(aVar);
                    } else if (aVar.packetType == com.tencent.liteav.basic.a.a.l) {
                        this.i.add(new Long(aVar.timestamp));
                        a(aVar, byteBufferArr, i);
                    } else {
                        TXCLog.e(a, "not support audio format");
                    }
                }
            } else {
                if (this.c != null) {
                    this.c.stop();
                    this.c.release();
                    this.c = null;
                }
                return;
            }
        }
        while (true) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00fc A:{LOOP_START, SYNTHETIC, PHI: r0 , Splitter:B:28:0x00fc, LOOP:0: B:28:0x00fc->B:78:0x00fc} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x00fc */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x017a A:{LOOP_START, SYNTHETIC, PHI: r0 , Splitter:B:56:0x017a, LOOP:1: B:56:0x017a->B:86:0x017a} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x017a */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:28|29|(3:30|31|79)|78) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:56|57|(3:58|59|87)|86) */
    /* JADX WARNING: Missing block: B:34:0x010d, code skipped:
            r1 = e;
     */
    /* JADX WARNING: Missing block: B:35:0x010e, code skipped:
            r5 = 0;
     */
    /* JADX WARNING: Missing block: B:36:0x010f, code skipped:
            r6 = a;
            r7 = new java.lang.StringBuilder();
            r7.append("CodecException: ");
            r7.append(r1);
            r7.append(". step: ");
            r7.append(r5);
            r7.append(", mediaformat: ");
            r7.append(r9.e);
            com.tencent.liteav.basic.log.TXCLog.e(r6, r7.toString());
            r0 = r0 + 1;
     */
    /* JADX WARNING: Missing block: B:37:0x0138, code skipped:
            if (r0 > 1) goto L_0x013a;
     */
    /* JADX WARNING: Missing block: B:38:0x013a, code skipped:
            com.tencent.liteav.basic.log.TXCLog.e(a, "decoder start error!");
            r9.c.release();
            r9.c = null;
     */
    /* JADX WARNING: Missing block: B:39:0x014a, code skipped:
            return com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE;
     */
    /* JADX WARNING: Missing block: B:41:0x014f, code skipped:
            if (r1.isRecoverable() != false) goto L_0x0151;
     */
    /* JADX WARNING: Missing block: B:43:?, code skipped:
            r9.c.stop();
     */
    /* JADX WARNING: Missing block: B:46:0x015b, code skipped:
            if (r1.isTransient() != false) goto L_0x015d;
     */
    /* JADX WARNING: Missing block: B:49:?, code skipped:
            java.lang.Thread.sleep(20);
     */
    /* JADX WARNING: Missing block: B:51:0x0163, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:52:0x0164, code skipped:
            r1.printStackTrace();
     */
    /* JADX WARNING: Missing block: B:53:0x0168, code skipped:
            com.tencent.liteav.basic.log.TXCLog.e(a, "decoder cath unrecoverable error!");
            r9.c.release();
            r9.c = null;
     */
    /* JADX WARNING: Missing block: B:54:0x0178, code skipped:
            return com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE;
     */
    /* JADX WARNING: Missing block: B:67:0x01a1, code skipped:
            r1 = e;
     */
    /* JADX WARNING: Missing block: B:68:0x01a2, code skipped:
            r5 = 0;
     */
    /* JADX WARNING: Missing block: B:69:0x01a3, code skipped:
            r6 = a;
            r7 = new java.lang.StringBuilder();
            r7.append("CodecException1: ");
            r7.append(r1);
            r7.append(". step: ");
            r7.append(r5);
            com.tencent.liteav.basic.log.TXCLog.e(r6, r7.toString());
            r0 = r0 + 1;
     */
    /* JADX WARNING: Missing block: B:70:0x01c2, code skipped:
            if (r0 > 1) goto L_0x01c4;
     */
    /* JADX WARNING: Missing block: B:71:0x01c4, code skipped:
            com.tencent.liteav.basic.log.TXCLog.e(a, "decoder start error!");
            r9.c.release();
            r9.c = null;
     */
    /* JADX WARNING: Missing block: B:72:0x01d4, code skipped:
            return com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE;
     */
    /* JADX WARNING: Missing block: B:74:?, code skipped:
            r9.c.reset();
     */
    private int b(com.tencent.liteav.basic.g.a r10) {
        /*
        r9 = this;
        r0 = r10.audioData;
        r0 = r0.length;
        r1 = 2;
        if (r0 == r1) goto L_0x001f;
    L_0x0006:
        r0 = a;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "aac seq header len not equal to 2 , with len ";
        r2.append(r3);
        r3 = r10.audioData;
        r3 = r3.length;
        r2.append(r3);
        r2 = r2.toString();
        com.tencent.liteav.basic.log.TXCLog.w(r0, r2);
    L_0x001f:
        r0 = r10.audioData;
        if (r0 == 0) goto L_0x01db;
    L_0x0023:
        r0 = r10.audioData;
        r2 = 0;
        r0 = r0[r2];
        r0 = r10.audioData;
        r0 = r0[r2];
        r0 = r0 & 7;
        r3 = 1;
        r0 = r0 << r3;
        r4 = r10.audioData;
        r4 = r4[r3];
        r4 = r4 & 128;
        r4 = r4 >> 7;
        r0 = r0 | r4;
        r0 = com.tencent.liteav.audio.impl.b.a(r0);
        r4 = r10.audioData;
        r4 = r4[r3];
        r4 = r4 & 120;
        r4 = r4 >> 3;
        r5 = "audio/mp4a-latm";
        r5 = android.media.MediaFormat.createAudioFormat(r5, r0, r4);
        r9.e = r5;
        r5 = r9.e;
        r6 = "bitrate";
        r7 = 64000; // 0xfa00 float:8.9683E-41 double:3.162E-319;
        r5.setInteger(r6, r7);
        r5 = r9.e;
        r6 = "is-adts";
        r5.setInteger(r6, r2);
        r5 = r9.e;
        r6 = "aac-profile";
        r5.setInteger(r6, r1);
        r1 = a;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "audio decoder media format: ";
        r5.append(r6);
        r6 = r9.e;
        r5.append(r6);
        r5 = r5.toString();
        com.tencent.liteav.basic.log.TXCLog.i(r1, r5);
        r1 = r9.b;
        if (r1 == 0) goto L_0x009b;
    L_0x0081:
        r1 = r9.b;
        r1 = r1.get();
        r1 = (com.tencent.liteav.audio.c) r1;
        r5 = new com.tencent.liteav.basic.g.a;
        r5.<init>();
        r6 = com.tencent.liteav.basic.a.a.h;
        r5.bitsPerChannel = r6;
        r5.channelsPerSample = r4;
        r5.sampleRate = r0;
        if (r1 == 0) goto L_0x009b;
    L_0x0098:
        r1.onPlayAudioInfoChanged(r5, r5);
    L_0x009b:
        r0 = r9.c;
        if (r0 == 0) goto L_0x00cd;
    L_0x009f:
        r0 = r9.c;	 Catch:{ Exception -> 0x00ad }
        r0.stop();	 Catch:{ Exception -> 0x00ad }
        r0 = r9.c;	 Catch:{ Exception -> 0x00aa }
        r0.release();	 Catch:{ Exception -> 0x00aa }
        goto L_0x00cd;
    L_0x00aa:
        r0 = move-exception;
        r1 = 1;
        goto L_0x00af;
    L_0x00ad:
        r0 = move-exception;
        r1 = 0;
    L_0x00af:
        r4 = a;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "hw audio decoder release error: ";
        r5.append(r6);
        r5.append(r1);
        r1 = ". error: ";
        r5.append(r1);
        r5.append(r0);
        r0 = r5.toString();
        com.tencent.liteav.basic.log.TXCLog.e(r4, r0);
    L_0x00cd:
        r0 = "audio/mp4a-latm";
        r0 = android.media.MediaCodec.createDecoderByType(r0);	 Catch:{ IOException -> 0x00d6 }
        r9.c = r0;	 Catch:{ IOException -> 0x00d6 }
        goto L_0x00f4;
    L_0x00d6:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = a;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "createDecoderByType exception: ";
        r4.append(r5);
        r0 = r0.getMessage();
        r4.append(r0);
        r0 = r4.toString();
        com.tencent.liteav.basic.log.TXCLog.e(r1, r0);
    L_0x00f4:
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 21;
        r4 = 0;
        if (r0 < r1) goto L_0x0179;
    L_0x00fb:
        r0 = 0;
    L_0x00fc:
        r1 = r9.c;	 Catch:{ CodecException -> 0x010d }
        r5 = r9.e;	 Catch:{ CodecException -> 0x010d }
        r1.configure(r5, r4, r4, r2);	 Catch:{ CodecException -> 0x010d }
        r1 = r9.c;	 Catch:{ CodecException -> 0x010a }
        r1.start();	 Catch:{ CodecException -> 0x010a }
        goto L_0x0186;
    L_0x010a:
        r1 = move-exception;
        r5 = 1;
        goto L_0x010f;
    L_0x010d:
        r1 = move-exception;
        r5 = 0;
    L_0x010f:
        r6 = a;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "CodecException: ";
        r7.append(r8);
        r7.append(r1);
        r8 = ". step: ";
        r7.append(r8);
        r7.append(r5);
        r5 = ", mediaformat: ";
        r7.append(r5);
        r5 = r9.e;
        r7.append(r5);
        r5 = r7.toString();
        com.tencent.liteav.basic.log.TXCLog.e(r6, r5);
        r0 = r0 + r3;
        if (r0 <= r3) goto L_0x014b;
    L_0x013a:
        r10 = a;
        r0 = "decoder start error!";
        com.tencent.liteav.basic.log.TXCLog.e(r10, r0);
        r10 = r9.c;
        r10.release();
        r9.c = r4;
        r10 = com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE;
        return r10;
    L_0x014b:
        r5 = r1.isRecoverable();
        if (r5 == 0) goto L_0x0157;
    L_0x0151:
        r1 = r9.c;	 Catch:{ Exception -> 0x00fc }
        r1.stop();	 Catch:{ Exception -> 0x00fc }
        goto L_0x00fc;
    L_0x0157:
        r1 = r1.isTransient();
        if (r1 == 0) goto L_0x0168;
    L_0x015d:
        r5 = 20;
        java.lang.Thread.sleep(r5);	 Catch:{ InterruptedException -> 0x0163 }
        goto L_0x00fc;
    L_0x0163:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00fc;
    L_0x0168:
        r10 = a;
        r0 = "decoder cath unrecoverable error!";
        com.tencent.liteav.basic.log.TXCLog.e(r10, r0);
        r10 = r9.c;
        r10.release();
        r9.c = r4;
        r10 = com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE;
        return r10;
    L_0x0179:
        r0 = 0;
    L_0x017a:
        r1 = r9.c;	 Catch:{ IllegalStateException -> 0x01a1 }
        r5 = r9.e;	 Catch:{ IllegalStateException -> 0x01a1 }
        r1.configure(r5, r4, r4, r2);	 Catch:{ IllegalStateException -> 0x01a1 }
        r1 = r9.c;	 Catch:{ IllegalStateException -> 0x019e }
        r1.start();	 Catch:{ IllegalStateException -> 0x019e }
    L_0x0186:
        r0 = r9.c;
        if (r0 == 0) goto L_0x019b;
    L_0x018a:
        r0 = r9.c;
        r0 = r0.getInputBuffers();
        r1 = r9.c;
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r1 = r1.dequeueInputBuffer(r2);
        r9.a(r10, r0, r1);
    L_0x019b:
        r10 = com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
        return r10;
    L_0x019e:
        r1 = move-exception;
        r5 = 1;
        goto L_0x01a3;
    L_0x01a1:
        r1 = move-exception;
        r5 = 0;
    L_0x01a3:
        r6 = a;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "CodecException1: ";
        r7.append(r8);
        r7.append(r1);
        r1 = ". step: ";
        r7.append(r1);
        r7.append(r5);
        r1 = r7.toString();
        com.tencent.liteav.basic.log.TXCLog.e(r6, r1);
        r0 = r0 + r3;
        if (r0 <= r3) goto L_0x01d5;
    L_0x01c4:
        r10 = a;
        r0 = "decoder start error!";
        com.tencent.liteav.basic.log.TXCLog.e(r10, r0);
        r10 = r9.c;
        r10.release();
        r9.c = r4;
        r10 = com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE;
        return r10;
    L_0x01d5:
        r1 = r9.c;	 Catch:{ Exception -> 0x017a }
        r1.reset();	 Catch:{ Exception -> 0x017a }
        goto L_0x017a;
    L_0x01db:
        r10 = com.tencent.liteav.audio.TXEAudioDef.TXE_AUDIO_COMMON_ERR_INVALID_PARAMS;
        return r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.audio.impl.Play.a.b(com.tencent.liteav.basic.g.a):int");
    }

    private int a(com.tencent.liteav.basic.g.a aVar, ByteBuffer[] byteBufferArr, int i) {
        if (i >= 0) {
            try {
                if (aVar.audioData != null) {
                    ByteBuffer byteBuffer = byteBufferArr[i];
                    byteBuffer.clear();
                    byteBuffer.put(aVar.audioData);
                }
                if (aVar == null || aVar.audioData.length <= 0) {
                    this.c.queueInputBuffer(i, 0, 0, c(), 4);
                } else {
                    this.c.queueInputBuffer(i, 0, aVar.audioData.length, c(), 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (i == -1) {
            return -1;
        }
        ByteBuffer[] outputBuffers = this.c.getOutputBuffers();
        if (this.d == null) {
            this.d = new BufferInfo();
        }
        int dequeueOutputBuffer;
        do {
            dequeueOutputBuffer = this.c.dequeueOutputBuffer(this.d, 10000);
            c cVar;
            if (dequeueOutputBuffer == -3) {
                outputBuffers = this.c.getOutputBuffers();
                continue;
            } else if (dequeueOutputBuffer == -2) {
                this.e = this.c.getOutputFormat();
                if (this.b != null) {
                    cVar = (c) this.b.get();
                    com.tencent.liteav.basic.g.a aVar2 = new com.tencent.liteav.basic.g.a();
                    aVar2.bitsPerChannel = com.tencent.liteav.basic.a.a.h;
                    aVar2.channelsPerSample = this.e.getInteger("channel-count");
                    aVar2.sampleRate = this.e.getInteger("sample-rate");
                    if (cVar != null) {
                        cVar.onPlayAudioInfoChanged(aVar2, aVar2);
                        continue;
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            } else if (dequeueOutputBuffer >= 0) {
                ByteBuffer byteBuffer2 = outputBuffers[dequeueOutputBuffer];
                byteBuffer2.position(this.d.offset);
                byteBuffer2.limit(this.d.offset + this.d.size);
                byte[] bArr = new byte[this.d.size];
                byteBuffer2.get(bArr);
                long longValue = ((Long) this.i.get(0)).longValue();
                this.i.remove(0);
                if (this.b != null) {
                    cVar = (c) this.b.get();
                    if (cVar != null) {
                        cVar.onPlayPcmData(bArr, longValue);
                    }
                }
                this.c.releaseOutputBuffer(dequeueOutputBuffer, false);
                continue;
            } else {
                continue;
            }
        } while (dequeueOutputBuffer >= 0);
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
    }

    private long c() {
        long timeTick = TXCTimeUtil.getTimeTick();
        if (timeTick < this.f) {
            timeTick += this.f - timeTick;
        }
        this.f = timeTick;
        return timeTick;
    }
}
