package com.tencent.liteav.h;

import android.content.Context;
import android.media.MediaFormat;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.liteav.audio.d;
import com.tencent.liteav.audio.impl.Record.e;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.muxer.c;
import com.tencent.liteav.videoencoder.TXSVideoEncoderParam;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCStreamRecord */
public class a implements d, com.tencent.liteav.videoencoder.d {
    private e a = new e();
    private com.tencent.liteav.videoencoder.a b = new com.tencent.liteav.videoencoder.a();
    private c c;
    private a d;
    private b e;
    private long f = 0;
    private long g = -1;
    private boolean h = false;
    private Handler i = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (a.this.e != null) {
                switch (message.what) {
                    case 1:
                        a.this.e.a(((Long) message.obj).longValue());
                        return;
                    case 2:
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("record complete. errcode = ");
                        stringBuilder.append(message.arg1);
                        stringBuilder.append(", errmsg = ");
                        stringBuilder.append((String) message.obj);
                        stringBuilder.append(", outputPath = ");
                        stringBuilder.append(a.this.d.f);
                        stringBuilder.append(", coverImage = ");
                        stringBuilder.append(a.this.d.g);
                        TXCLog.d("TXCStreamRecord", stringBuilder.toString());
                        if (!(message.arg1 != 0 || a.this.d.g == null || a.this.d.g.isEmpty() || com.tencent.liteav.basic.util.b.a(a.this.d.f, a.this.d.g))) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("saveVideoThumb error. sourcePath = ");
                            stringBuilder.append(a.this.d.f);
                            stringBuilder.append(", coverImagePath = ");
                            stringBuilder.append(a.this.d.g);
                            TXCLog.e("TXCStreamRecord", stringBuilder.toString());
                        }
                        if (message.arg1 != 0) {
                            try {
                                File file = new File(a.this.d.f);
                                if (file.exists()) {
                                    file.delete();
                                }
                            } catch (Exception unused) {
                            }
                        }
                        a.this.e.a(message.arg1, (String) message.obj, a.this.d.f, a.this.d.g);
                        return;
                    default:
                        return;
                }
            }
        }
    };

    /* compiled from: TXCStreamRecord */
    public static class a {
        public int a = 544;
        public int b = 960;
        public int c = 20;
        public int d = 1000;
        public EGLContext e;
        public String f;
        public String g;
        public int h = 0;
        public int i = 0;
        public int j = 16;

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("TXCStreamRecordParams: [width=");
            stringBuilder2.append(this.a);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("; height=");
            stringBuilder2.append(this.b);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("; fps=");
            stringBuilder2.append(this.c);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("; bitrate=");
            stringBuilder2.append(this.d);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("; channels=");
            stringBuilder2.append(this.h);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("; samplerate=");
            stringBuilder2.append(this.i);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("; bits=");
            stringBuilder2.append(this.j);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("; EGLContext=");
            stringBuilder2.append(this.e);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("; coveriamge=");
            stringBuilder2.append(this.g);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("; outputpath=");
            stringBuilder2.append(this.f);
            stringBuilder2.append("]");
            stringBuilder.append(stringBuilder2.toString());
            return stringBuilder.toString();
        }
    }

    /* compiled from: TXCStreamRecord */
    public interface b {
        void a(int i, String str, String str2, String str3);

        void a(long j);
    }

    public void onRecordError(int i, String str) {
    }

    public void onRecordPcmData(byte[] bArr, long j, int i, int i2, int i3) {
    }

    public void onRecordRawPcmData(byte[] bArr, long j, int i, int i2, int i3, boolean z) {
    }

    public a(Context context) {
        this.c = new c(context, 2);
    }

    public void a(b bVar) {
        this.e = bVar;
    }

    public void a(a aVar) {
        this.d = aVar;
        this.f = 0;
        this.g = -1;
        this.c.a(this.d.f);
        if (aVar.h > 0 && aVar.i > 0 && aVar.j > 0) {
            this.a.a(com.tencent.liteav.audio.b.f, aVar.i, aVar.h, aVar.j, new WeakReference(this));
            this.c.b(com.tencent.liteav.basic.util.b.a(this.d.i, this.d.h, 2));
            this.h = true;
        }
        this.b.setListener(this);
        TXSVideoEncoderParam tXSVideoEncoderParam = new TXSVideoEncoderParam();
        tXSVideoEncoderParam.width = this.d.a;
        tXSVideoEncoderParam.height = this.d.b;
        tXSVideoEncoderParam.fps = this.d.c;
        tXSVideoEncoderParam.glContext = this.d.e;
        tXSVideoEncoderParam.annexb = true;
        tXSVideoEncoderParam.appendSpsPps = false;
        this.b.setBitrate(this.d.d);
        this.b.start(tXSVideoEncoderParam);
    }

    public void a() {
        this.h = false;
        this.a.a();
        this.b.stop();
        if (this.c.b() < 0) {
            this.i.sendMessage(Message.obtain(this.i, 2, 1, 0, "mp4合成失败"));
        } else {
            this.i.sendMessage(Message.obtain(this.i, 2, 0, 0, ""));
        }
    }

    public void a(int i, long j) {
        this.b.pushVideoFrame(i, this.d.a, this.d.b, j);
    }

    public void a(byte[] bArr, long j) {
        if (this.h) {
            this.a.a(bArr, j);
        } else {
            TXCLog.e("TXCStreamRecord", "drainAudio fail because of not init yet!");
        }
    }

    public static String a(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(valueOf);
            stringBuilder.append("000");
            valueOf = simpleDateFormat.format(new Date(Long.valueOf(stringBuilder.toString()).longValue()));
            String a = a(context);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("TXUGC_%s");
            stringBuilder2.append(str);
            return new File(a, String.format(stringBuilder2.toString(), new Object[]{valueOf})).getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String a(Context context) {
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getPath();
        }
        return context.getFilesDir().getPath();
    }

    private String a(int i) {
        Object obj;
        switch (i) {
            case 10000002:
                obj = "未启动视频编码器";
                break;
            case 10000003:
                obj = "非法视频输入参数";
                break;
            case 10000004:
                obj = "视频编码初始化失败";
                break;
            case 10000005:
                obj = "视频编码失败";
                break;
            default:
                obj = "";
                break;
        }
        this.i.sendMessage(Message.obtain(this.i, 2, 1, 0, obj));
        return obj;
    }

    public void onRecordEncData(byte[] bArr, long j, int i, int i2, int i3) {
        this.c.a(bArr, 0, bArr.length, j * 1000, 1);
    }

    public void onEncodeNAL(com.tencent.liteav.basic.g.b bVar, int i) {
        if (i == 0) {
            this.c.b(bVar.nalData, 0, bVar.nalData.length, bVar.pts * 1000, bVar.info.flags);
            if (this.g < 0) {
                this.g = bVar.pts;
            }
            if (bVar.pts > this.f + 500) {
                this.i.sendMessage(Message.obtain(this.i, 1, new Long(bVar.pts - this.g)));
                this.f = bVar.pts;
                return;
            }
            return;
        }
        String a = a(i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("video encode error! errmsg: ");
        stringBuilder.append(a);
        TXCLog.e("TXCStreamRecord", stringBuilder.toString());
    }

    public void onEncodeFormat(MediaFormat mediaFormat) {
        this.c.a(mediaFormat);
        if (this.c.c() && this.c.a() < 0) {
            this.i.sendMessage(Message.obtain(this.i, 2, 1, 0, "mp4封装器启动失败"));
        }
    }
}
