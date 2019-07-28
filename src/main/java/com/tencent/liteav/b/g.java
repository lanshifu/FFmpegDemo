package com.tencent.liteav.b;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.g.b;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(16)
/* compiled from: TXCombineVideoDecoder */
public class g implements b {
    private MediaCodec a;
    private ByteBuffer[] b;
    private ByteBuffer[] c;
    private AtomicBoolean d = new AtomicBoolean(false);
    private long e = 1000;

    public void a(MediaFormat mediaFormat) {
        if (mediaFormat == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("create VideoDecoder error format:");
            stringBuilder.append(mediaFormat);
            TXCLog.e("TXCombineVideoDecoder", stringBuilder.toString());
            return;
        }
        try {
            this.a = MediaCodec.createDecoderByType(mediaFormat.getString(IMediaFormat.KEY_MIME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(MediaFormat mediaFormat, Surface surface) {
        if (mediaFormat == null) {
            TXCLog.e("TXCombineVideoDecoder", "configure VideoDecoder error");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("format: ");
        stringBuilder.append(mediaFormat);
        stringBuilder.append(", surface: ");
        stringBuilder.append(surface);
        stringBuilder.append(", mMediaCodec: ");
        stringBuilder.append(this.a);
        TXCLog.d("TXCombineVideoDecoder", stringBuilder.toString());
        mediaFormat.setInteger("rotation-degrees", 0);
        this.a.configure(mediaFormat, surface, null, 0);
    }

    public void a() {
        TXCLog.d("TXCombineVideoDecoder", "start");
        if (this.a == null) {
            TXCLog.e("TXCombineVideoDecoder", "start VideoDecoder error");
            return;
        }
        this.a.start();
        this.b = this.a.getInputBuffers();
        this.c = this.a.getOutputBuffers();
        this.d.getAndSet(true);
    }

    public void b() {
        TXCLog.d("TXCombineVideoDecoder", "stop");
        if (this.a == null) {
            TXCLog.e("TXCombineVideoDecoder", "stop VideoDecoder error");
            return;
        }
        try {
            this.a.stop();
            this.a.release();
        } catch (IllegalStateException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("video decoder stop exception: ");
            stringBuilder.append(e);
            TXCLog.e("TXCombineVideoDecoder", stringBuilder.toString());
        } catch (Throwable th) {
            this.d.getAndSet(false);
        }
        this.d.getAndSet(false);
    }

    public e c() {
        if (!this.d.get()) {
            return null;
        }
        int dequeueInputBuffer;
        try {
            dequeueInputBuffer = this.a.dequeueInputBuffer(this.e);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("video dequeueInputBuffer exception: ");
            stringBuilder.append(e);
            TXCLog.e("TXCombineVideoDecoder", stringBuilder.toString());
            dequeueInputBuffer = -1;
        }
        if (dequeueInputBuffer < 0) {
            return null;
        }
        ByteBuffer inputBuffer;
        if (VERSION.SDK_INT >= 21) {
            inputBuffer = this.a.getInputBuffer(dequeueInputBuffer);
        } else {
            inputBuffer = this.b[dequeueInputBuffer];
        }
        return new e(inputBuffer, 0, 0, dequeueInputBuffer, 0, 0);
    }

    public void a(e eVar) {
        if (this.d.get()) {
            this.a.queueInputBuffer(eVar.d(), 0, eVar.g(), eVar.e(), eVar.f());
        }
    }

    public e d() {
        if (!this.d.get()) {
            return null;
        }
        BufferInfo bufferInfo = new BufferInfo();
        int dequeueOutputBuffer = this.a.dequeueOutputBuffer(bufferInfo, this.e);
        if (dequeueOutputBuffer != -1) {
            StringBuilder stringBuilder;
            if (dequeueOutputBuffer == -3) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("INFO_OUTPUT_BUFFERS_CHANGED info.size :");
                stringBuilder.append(bufferInfo.size);
                TXCLog.d("TXCombineVideoDecoder", stringBuilder.toString());
            } else if (dequeueOutputBuffer == -2) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("INFO_OUTPUT_FORMAT_CHANGED info.size :");
                stringBuilder.append(bufferInfo.size);
                TXCLog.d("TXCombineVideoDecoder", stringBuilder.toString());
            } else if (dequeueOutputBuffer >= 0 && dequeueOutputBuffer >= 0) {
                this.a.releaseOutputBuffer(dequeueOutputBuffer, true);
                return new e(null, bufferInfo.size, bufferInfo.presentationTimeUs, dequeueOutputBuffer, bufferInfo.flags, 0);
            }
        }
        return null;
    }

    public e a(e eVar, e eVar2) {
        if (!this.d.get()) {
            return null;
        }
        eVar2.k(eVar.n());
        eVar2.j(eVar.m());
        eVar2.e(eVar.h());
        eVar2.f(eVar.i());
        eVar2.i(eVar.l());
        eVar2.h(eVar.k());
        eVar2.g(eVar.j());
        return eVar2;
    }
}
