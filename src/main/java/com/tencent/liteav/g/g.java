package com.tencent.liteav.g;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(19)
/* compiled from: TXHWAudioDecoder */
public class g implements b {
    private MediaCodec a;
    private ByteBuffer[] b;
    private ByteBuffer[] c;
    private AtomicBoolean d = new AtomicBoolean(false);
    private long e = 1000;

    public void a(MediaFormat mediaFormat) {
        if (mediaFormat == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("create AudioDecoder error format:");
            stringBuilder.append(mediaFormat);
            TXCLog.e("TXHWAudioDecoder", stringBuilder.toString());
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
            TXCLog.e("TXHWAudioDecoder", "configure AudioDecoder error");
        } else {
            this.a.configure(mediaFormat, null, null, 0);
        }
    }

    public void a() {
        if (this.a == null) {
            TXCLog.e("TXHWAudioDecoder", "start AudioDecoder error");
            return;
        }
        this.a.start();
        this.c = this.a.getInputBuffers();
        this.b = this.a.getOutputBuffers();
        this.d.getAndSet(true);
    }

    public void b() {
        if (this.a == null) {
            TXCLog.e("TXHWAudioDecoder", "stop AudioDecoder error");
            return;
        }
        try {
            this.a.stop();
            this.a.release();
        } catch (IllegalStateException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("audio decoder stop exception: ");
            stringBuilder.append(e);
            TXCLog.e("TXHWAudioDecoder", stringBuilder.toString());
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
            stringBuilder.append("audio dequeueInputBuffer exception: ");
            stringBuilder.append(e);
            TXCLog.e("TXHWAudioDecoder", stringBuilder.toString());
            dequeueInputBuffer = -1;
        }
        if (dequeueInputBuffer < 0) {
            return null;
        }
        ByteBuffer inputBuffer;
        if (VERSION.SDK_INT >= 21) {
            inputBuffer = this.a.getInputBuffer(dequeueInputBuffer);
        } else {
            inputBuffer = this.c[dequeueInputBuffer];
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
        if (dequeueOutputBuffer == -1 || dequeueOutputBuffer == -3 || dequeueOutputBuffer == -2 || dequeueOutputBuffer < 0 || dequeueOutputBuffer < 0) {
            return null;
        }
        e eVar = new e();
        eVar.a(1);
        eVar.a(bufferInfo.presentationTimeUs);
        eVar.c(bufferInfo.flags);
        eVar.d(bufferInfo.size);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bufferInfo.size);
        if (VERSION.SDK_INT >= 21) {
            allocateDirect.put(this.a.getOutputBuffer(dequeueOutputBuffer));
        } else {
            ByteBuffer byteBuffer = this.a.getOutputBuffers()[dequeueOutputBuffer];
            byteBuffer.rewind();
            byteBuffer.limit(bufferInfo.size);
            allocateDirect.put(byteBuffer);
        }
        allocateDirect.position(0);
        if (eVar.g() >= 0) {
            allocateDirect.limit(eVar.g());
        }
        eVar.a(allocateDirect);
        this.a.releaseOutputBuffer(dequeueOutputBuffer, false);
        return eVar;
    }
}
