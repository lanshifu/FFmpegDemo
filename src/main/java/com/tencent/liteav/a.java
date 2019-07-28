package com.tencent.liteav;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.beauty.e;
import com.tencent.liteav.videoencoder.d;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* compiled from: TXCBackgroundPusher */
public class a implements e, d {
    private static final String a = "a";
    private int b = IjkMediaCodecInfo.RANK_SECURE;
    private long c = 0;
    private a d;
    private HandlerThread e;
    private boolean f = false;
    private com.tencent.liteav.videoencoder.b g;
    private com.tencent.liteav.basic.g.b h;
    private com.tencent.liteav.beauty.d i = null;
    private ByteBuffer j = null;
    private Bitmap k = null;
    private int l = 0;
    private int m = 0;
    private WeakReference<b> n = null;

    /* compiled from: TXCBackgroundPusher */
    private class a extends Handler {
        private int b = IjkMediaCodecInfo.RANK_SECURE;
        private long c = 0;

        public a(Looper looper, int i, long j) {
            super(looper);
            this.b = i;
            this.c = j;
        }

        public void handleMessage(Message message) {
            if (message.what == 1001) {
                try {
                    a.this.e();
                    if (System.currentTimeMillis() < this.c) {
                        sendEmptyMessageDelayed(1001, (long) this.b);
                        return;
                    }
                    TXCLog.w(a.a, "bkgpush:stop background publish when timeout");
                    if (a.this.n != null && a.this.f) {
                        b bVar = (b) a.this.n.get();
                        if (bVar != null) {
                            bVar.a();
                        }
                        a.this.f = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* compiled from: TXCBackgroundPusher */
    public interface b {
        void a();

        void a(Bitmap bitmap, ByteBuffer byteBuffer, int i, int i2);

        void a(com.tencent.liteav.videoencoder.b bVar);
    }

    public void didProcessFrame(byte[] bArr, int i, int i2, int i3, long j) {
    }

    public void onEncodeFormat(MediaFormat mediaFormat) {
    }

    public int willAddWatermark(int i, int i2, int i3) {
        return 0;
    }

    public void didProcessFrame(int i, int i2, int i3, long j) {
        TXCLog.w(a, "bkgpush: got texture");
        if (this.g != null) {
            this.g.a(i, i2, i3, TXCTimeUtil.getTimeTick());
        }
    }

    public void onEncodeNAL(com.tencent.liteav.basic.g.b bVar, int i) {
        Object bVar2;
        this.h = bVar2;
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bkgpush: got nal type: ");
        if (bVar2 != null) {
            bVar2 = Integer.valueOf(bVar2.nalType);
        }
        stringBuilder.append(bVar2);
        TXCLog.w(str, stringBuilder.toString());
        if (this.g != null) {
            this.g.a(null);
            com.tencent.liteav.videoencoder.b bVar3 = this.g;
            try {
                if (this.n != null) {
                    b bVar4 = (b) this.n.get();
                    if (bVar4 != null) {
                        bVar4.a(bVar3);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public a(b bVar) {
        this.n = new WeakReference(bVar);
    }

    public void a(int i, int i2) {
        if (this.f) {
            TXCLog.w(a, "bkgpush: start background publish return when started");
            return;
        }
        this.f = true;
        b(i, i2);
        c();
        if (this.d != null) {
            this.d.sendEmptyMessageDelayed(1001, (long) this.b);
        }
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bkgpush: start background publish with time:");
        stringBuilder.append((this.c - System.currentTimeMillis()) / 1000);
        stringBuilder.append(", interval:");
        stringBuilder.append(this.b);
        TXCLog.w(str, stringBuilder.toString());
    }

    public void a(int i, int i2, Bitmap bitmap, int i3, int i4) {
        if (this.f) {
            TXCLog.w(a, "bkgpush: start background publish return when started");
            return;
        }
        if (bitmap == null) {
            try {
                TXCLog.w(a, "bkgpush: background publish img is empty, add default img");
                ColorDrawable colorDrawable = new ColorDrawable(-16777216);
                Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Config.ARGB_8888);
                colorDrawable.draw(new Canvas(createBitmap));
                bitmap = createBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Error unused) {
            }
        }
        TXCLog.w(a, "bkgpush: generate bitmap");
        this.k = bitmap;
        this.l = i3;
        this.m = i4;
        a(i, i2);
    }

    public void a() {
        this.f = false;
        this.j = null;
        this.k = null;
        TXCLog.w(a, "bkgpush: stop background publish");
        d();
    }

    private void b(int i, int i2) {
        if (i > 0) {
            if (i >= 8) {
                i = 8;
            } else if (i <= 3) {
                i = 3;
            }
            this.b = 1000 / i;
        } else {
            this.b = 200;
        }
        long j = (long) i2;
        if (i2 > 0) {
            this.c = System.currentTimeMillis() + (j * 1000);
        } else {
            this.c = System.currentTimeMillis() + 300000;
        }
    }

    private void c() {
        d();
        this.e = new HandlerThread("TXImageCapturer");
        this.e.start();
        this.d = new a(this.e.getLooper(), this.b, this.c);
    }

    private void d() {
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
            this.d = null;
        }
        if (this.e != null) {
            this.e.quit();
            this.e = null;
        }
    }

    private void e() {
        try {
            if (this.n != null && this.f) {
                b bVar = (b) this.n.get();
                if (bVar != null) {
                    Bitmap bitmap = this.k;
                    ByteBuffer byteBuffer = this.j;
                    if (byteBuffer == null && bitmap != null) {
                        byteBuffer = ByteBuffer.allocateDirect((bitmap.getWidth() * bitmap.getHeight()) * 4);
                        bitmap.copyPixelsToBuffer(byteBuffer);
                        byteBuffer.rewind();
                        this.j = byteBuffer;
                    }
                    if (bitmap != null && byteBuffer != null) {
                        bVar.a(bitmap, byteBuffer, this.l, this.m);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
