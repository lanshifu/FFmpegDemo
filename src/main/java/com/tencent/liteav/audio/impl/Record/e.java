package com.tencent.liteav.audio.impl.Record;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import com.tencent.liteav.audio.b;
import com.tencent.liteav.audio.d;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.ugc.TXRecordCommon;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Vector;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: TXCAudioHWEncoder */
public class e extends Thread {
    private BufferInfo a;
    private MediaCodecInfo b;
    private MediaFormat c;
    private MediaCodec d;
    private Vector<byte[]> e;
    private WeakReference<d> f;
    private volatile boolean g = false;
    private volatile boolean h = false;
    private final Object i = new Object();
    private long j = 0;
    private int k = b.a;
    private int l = b.b;
    private int m = b.c;
    private byte[] n;

    static {
        com.tencent.liteav.basic.util.b.e();
    }

    @TargetApi(16)
    public e() {
        super("TXAudioRecordThread");
    }

    public void a(int i, int i2, int i3, int i4, WeakReference<d> weakReference) {
        this.f = weakReference;
        this.a = new BufferInfo();
        this.e = new Vector();
        this.k = i2;
        this.l = i3;
        this.m = i4;
        b();
    }

    public void a(byte[] bArr, long j) {
        if (!(this.e == null || bArr == null)) {
            synchronized (this.e) {
                if (this.e == null) {
                    return;
                }
                this.e.add(bArr);
            }
        }
        synchronized (this.i) {
            this.i.notify();
        }
    }

    public void a() {
        c();
    }

    private void b() {
        this.b = a("audio/mp4a-latm");
        if (this.b == null) {
            TXCLog.e("AudioCenter:TXCAudioHWEncoder", "Unable to find an appropriate codec for audio/mp4a-latm");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("selected codec: ");
        stringBuilder.append(this.b.getName());
        TXCLog.i("AudioCenter:TXCAudioHWEncoder", stringBuilder.toString());
        int i = this.k;
        int i2 = TXRecordCommon.AUDIO_SAMPLERATE_32000;
        if (i >= TXRecordCommon.AUDIO_SAMPLERATE_32000) {
            i2 = 64000;
        }
        this.c = MediaFormat.createAudioFormat("audio/mp4a-latm", this.k, this.l);
        this.c.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, i2);
        this.c.setInteger("channel-count", this.l);
        this.c.setInteger("sample-rate", this.k);
        this.c.setInteger("aac-profile", 2);
        stringBuilder = new StringBuilder();
        stringBuilder.append("format: ");
        stringBuilder.append(this.c);
        TXCLog.i("AudioCenter:TXCAudioHWEncoder", stringBuilder.toString());
        try {
            d();
        } catch (Exception e) {
            e.printStackTrace();
        }
        start();
    }

    private void c() {
        this.h = true;
    }

    @TargetApi(16)
    private void d() throws IOException {
        if (this.d == null) {
            this.d = MediaCodec.createEncoderByType("audio/mp4a-latm");
            this.d.configure(this.c, null, null, 1);
            this.d.start();
            TXCLog.i("AudioCenter:TXCAudioHWEncoder", "prepare finishing");
            this.g = true;
        }
    }

    private void e() {
        if (this.d != null) {
            this.d.stop();
            this.d.release();
            this.d = null;
        }
        this.g = false;
    }

    public void run() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(Filter.K);
        while (!this.h) {
            if (this.g) {
                boolean isEmpty;
                synchronized (this.e) {
                    isEmpty = this.e.isEmpty();
                }
                if (isEmpty) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    byte[] bArr;
                    synchronized (this.e) {
                        bArr = (byte[]) this.e.remove(0);
                    }
                    if (bArr != null) {
                        try {
                            allocateDirect.clear();
                            if (bArr.length > allocateDirect.capacity()) {
                                allocateDirect = ByteBuffer.allocateDirect(bArr.length);
                            }
                            allocateDirect.clear();
                            allocateDirect.put(bArr);
                            allocateDirect.flip();
                            a(allocateDirect, bArr.length, f());
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            } else {
                synchronized (this.i) {
                    try {
                        this.i.wait();
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        e();
    }

    private void a(ByteBuffer byteBuffer, int i, long j) {
        if (!this.h) {
            ByteBuffer[] inputBuffers = this.d.getInputBuffers();
            int dequeueInputBuffer = this.d.dequeueInputBuffer(10000);
            if (dequeueInputBuffer >= 0) {
                ByteBuffer byteBuffer2 = inputBuffers[dequeueInputBuffer];
                byteBuffer2.clear();
                if (byteBuffer != null) {
                    byteBuffer2.put(byteBuffer);
                }
                if (i <= 0) {
                    TXCLog.i("AudioCenter:TXCAudioHWEncoder", "send BUFFER_FLAG_END_OF_STREAM");
                    this.d.queueInputBuffer(dequeueInputBuffer, 0, 0, j, 4);
                } else {
                    this.d.queueInputBuffer(dequeueInputBuffer, 0, i, j, 0);
                }
            }
            ByteBuffer[] outputBuffers = this.d.getOutputBuffers();
            do {
                i = this.d.dequeueOutputBuffer(this.a, 10000);
                if (i != -1) {
                    if (i == -3) {
                        outputBuffers = this.d.getOutputBuffers();
                        continue;
                    } else if (i == -2) {
                        this.d.getOutputFormat();
                        continue;
                    } else if (i < 0) {
                        continue;
                    } else {
                        ByteBuffer byteBuffer3 = outputBuffers[i];
                        if ((this.a.flags & 2) != 0) {
                            TXCLog.d("AudioCenter:TXCAudioHWEncoder", "drain:BUFFER_FLAG_CODEC_CONFIG");
                            this.a.size = 0;
                        }
                        if (this.a.size != 0) {
                            this.a.presentationTimeUs = f();
                            this.n = new byte[byteBuffer3.limit()];
                            byteBuffer3.get(this.n);
                            b(this.n, this.a.presentationTimeUs);
                            this.j = this.a.presentationTimeUs;
                        }
                        this.d.releaseOutputBuffer(i, false);
                        continue;
                    }
                }
            } while (i >= 0);
        }
    }

    private long f() {
        long timeTick = TXCTimeUtil.getTimeTick();
        return timeTick < this.j ? timeTick + (this.j - timeTick) : timeTick;
    }

    private static final MediaCodecInfo a(String str) {
        TXCLog.v("AudioCenter:TXCAudioHWEncoder", "selectAudioCodec:");
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                for (int i2 = 0; i2 < supportedTypes.length; i2++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("supportedType:");
                    stringBuilder.append(codecInfoAt.getName());
                    stringBuilder.append(",MIME=");
                    stringBuilder.append(supportedTypes[i2]);
                    TXCLog.i("AudioCenter:TXCAudioHWEncoder", stringBuilder.toString());
                    if (supportedTypes[i2].equalsIgnoreCase(str)) {
                        return codecInfoAt;
                    }
                }
                continue;
            }
        }
        return null;
    }

    private void b(byte[] bArr, long j) {
        if (this.f != null) {
            d dVar = (d) this.f.get();
            if (dVar != null) {
                dVar.onRecordEncData(bArr, j, this.k, this.l, this.m);
            }
        }
    }
}
