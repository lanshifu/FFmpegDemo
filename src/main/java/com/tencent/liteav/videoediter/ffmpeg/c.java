package com.tencent.liteav.videoediter.ffmpeg;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.g.b;
import com.tencent.liteav.videoediter.ffmpeg.jni.FFDecodedFrame;
import com.tencent.liteav.videoediter.ffmpeg.jni.TXFFAudioDecoderJNI;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(16)
/* compiled from: TXSWAudioDecoder */
public class c implements b {
    public static String[] a = new String[]{"audio/mp4a-latm", "audio/mpeg"};
    private ByteBuffer b;
    private int c;
    private int d;
    private int e;
    private TXFFAudioDecoderJNI f;
    private List<e> g;
    private AtomicBoolean h;
    private FFDecodedFrame i;

    public void a(MediaFormat mediaFormat, Surface surface) {
    }

    public static boolean a(String str) {
        for (String equals : a) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002c A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002b A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002c A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002b A:{RETURN} */
    private int b(java.lang.String r6) {
        /*
        r5 = this;
        r0 = r6.hashCode();
        r1 = -53558318; // 0xfffffffffccec3d2 float:-8.588679E36 double:NaN;
        r2 = 1;
        r3 = 0;
        r4 = -1;
        if (r0 == r1) goto L_0x001c;
    L_0x000c:
        r1 = 1504831518; // 0x59b1e81e float:6.2595358E15 double:7.43485556E-315;
        if (r0 == r1) goto L_0x0012;
    L_0x0011:
        goto L_0x0026;
    L_0x0012:
        r0 = "audio/mpeg";
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x0026;
    L_0x001a:
        r6 = 1;
        goto L_0x0027;
    L_0x001c:
        r0 = "audio/mp4a-latm";
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x0026;
    L_0x0024:
        r6 = 0;
        goto L_0x0027;
    L_0x0026:
        r6 = -1;
    L_0x0027:
        switch(r6) {
            case 0: goto L_0x002c;
            case 1: goto L_0x002b;
            default: goto L_0x002a;
        };
    L_0x002a:
        return r4;
    L_0x002b:
        return r2;
    L_0x002c:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoediter.ffmpeg.c.b(java.lang.String):int");
    }

    public c() {
        this.h = new AtomicBoolean(false);
        this.g = new LinkedList();
        this.g = Collections.synchronizedList(this.g);
    }

    public void a(MediaFormat mediaFormat) {
        b();
        this.c = mediaFormat.getInteger("channel-count");
        this.d = mediaFormat.getInteger("sample-rate");
        if (mediaFormat.containsKey("max-input-size")) {
            this.e = mediaFormat.getInteger("max-input-size");
        }
        ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-0");
        if (byteBuffer != null) {
            byteBuffer.position(0);
        }
        String string = mediaFormat.getString(IMediaFormat.KEY_MIME);
        this.f = new TXFFAudioDecoderJNI();
        this.f.configureInput(b(string), byteBuffer, byteBuffer != null ? byteBuffer.capacity() : 0, this.d, this.c);
        int i = (this.c * Filter.K) * 2;
        this.b = ByteBuffer.allocateDirect(i > this.e ? i : this.e);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("createDecoderByFormat: type = ");
        stringBuilder.append(string);
        stringBuilder.append(", mediaFormat = ");
        stringBuilder.append(mediaFormat.toString());
        stringBuilder.append(", calculateBufferSize = ");
        stringBuilder.append(i);
        stringBuilder.append(", mMaxInputSize = ");
        stringBuilder.append(this.e);
        TXCLog.i("TXSWAudioDecoder", stringBuilder.toString());
    }

    public void a() {
        if (this.h.get()) {
            TXCLog.w("TXSWAudioDecoder", "start error: decoder have been started!");
            return;
        }
        this.g.clear();
        this.h.set(true);
    }

    public void b() {
        if (this.h.get()) {
            this.g.clear();
            if (this.f != null) {
                this.f.release();
                this.f = null;
            }
            this.h.set(false);
            return;
        }
        TXCLog.w("TXSWAudioDecoder", "stop error: decoder isn't starting yet!!");
    }

    public e c() {
        if (this.h.get()) {
            this.b.position(0);
            e eVar = new e();
            eVar.a(this.b);
            eVar.h(this.c);
            eVar.g(this.d);
            eVar.d(this.b.capacity());
            return eVar;
        }
        TXCLog.w("TXSWAudioDecoder", "find frame error: decoder isn't starting yet!!");
        return null;
    }

    public void a(e eVar) {
        if (this.h.get()) {
            if (eVar.f() == 1) {
                byte[] a = a(eVar.b(), eVar.g());
                if (a == null) {
                    this.i = null;
                    return;
                }
                this.i = this.f.decode(a, eVar.e(), eVar.f());
            } else if (eVar.f() == 4) {
                this.i = new FFDecodedFrame();
                this.i.data = new byte[0];
                this.i.flags = 4;
                this.i.pts = eVar.e();
            }
            eVar.a(null);
            eVar.d(0);
            this.b.position(0);
            return;
        }
        TXCLog.e("TXSWAudioDecoder", "decode error: decoder isn't starting yet!!");
    }

    private byte[] a(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        try {
            byteBuffer.get(bArr);
            return bArr;
        } catch (BufferUnderflowException e) {
            e.printStackTrace();
            return null;
        }
    }

    public e d() {
        if (!this.h.get()) {
            TXCLog.e("TXSWAudioDecoder", "decode error: decoder isn't starting yet!!");
            return null;
        } else if (this.i == null || this.i.data == null) {
            return null;
        } else {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.i.data.length);
            allocateDirect.put(this.i.data);
            allocateDirect.position(0);
            e eVar = new e();
            eVar.a(allocateDirect);
            eVar.d(this.i.data.length);
            eVar.a(this.i.pts);
            eVar.c(this.i.flags);
            eVar.h(this.c);
            eVar.g(this.i.sampleRate);
            this.i = null;
            return eVar;
        }
    }
}
