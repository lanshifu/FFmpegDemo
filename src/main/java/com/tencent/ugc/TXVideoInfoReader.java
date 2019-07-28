package com.tencent.ugc;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.g.h;
import com.tencent.ugc.TXVideoEditConstants.TXVideoInfo;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TXVideoInfoReader {
    private static final int RETRY_MAX_COUNT = 3;
    private static TXVideoInfoReader sInstance;
    private String TAG = TXVideoInfoReader.class.getSimpleName();
    private int mCount;
    private a mGenerateImageThread;
    private Handler mHandler;
    private long mImageVideoDuration;
    private volatile WeakReference<OnSampleProgrocess> mListener;
    private AtomicInteger mRetryGeneThreadTimes;
    private String mVideoPath;

    public interface OnSampleProgrocess {
        void sampleProcess(int i, Bitmap bitmap);
    }

    class a extends Thread {
        private h b;
        private String c;
        private long d;
        private volatile Bitmap e;
        private int f;

        public a(String str) {
            this.f = TXVideoInfoReader.this.mListener.hashCode();
            this.c = str;
        }

        public void run() {
            this.b = new h();
            this.b.a(this.c);
            this.d = this.b.a() * 1000;
            long access$100 = this.d / ((long) TXVideoInfoReader.this.mCount);
            TXCLog.i(TXVideoInfoReader.this.TAG, String.format("run duration = %s ", new Object[]{Long.valueOf(this.d)}));
            TXCLog.i(TXVideoInfoReader.this.TAG, String.format("run count = %s ", new Object[]{Integer.valueOf(TXVideoInfoReader.this.mCount)}));
            for (int i = 0; i < TXVideoInfoReader.this.mCount && !Thread.currentThread().isInterrupted(); i++) {
                long j = ((long) i) * access$100;
                if (j > this.d) {
                    j = this.d;
                }
                TXCLog.i(TXVideoInfoReader.this.TAG, String.format("current frame time = %s", new Object[]{Long.valueOf(j)}));
                Bitmap a = this.b.a(j);
                String access$200 = TXVideoInfoReader.this.TAG;
                String str = "the %s of bitmap is null ? %s";
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(i);
                objArr[1] = Boolean.valueOf(a == null);
                TXCLog.i(access$200, String.format(str, objArr));
                if (a == null) {
                    TXCLog.d(TXVideoInfoReader.this.TAG, "getSampleImages failed!!!");
                    if (i == 0) {
                        if (TXVideoInfoReader.this.mRetryGeneThreadTimes.get() < 3) {
                            TXCLog.d(TXVideoInfoReader.this.TAG, "retry to get sample images");
                            TXVideoInfoReader.this.mHandler.post(new Runnable() {
                                public void run() {
                                    TXVideoInfoReader.this.getSampleImages(TXVideoInfoReader.this.mCount, a.this.c, (OnSampleProgrocess) TXVideoInfoReader.this.mListener.get());
                                    TXVideoInfoReader.this.mRetryGeneThreadTimes.getAndIncrement();
                                }
                            });
                        }
                    } else if (!(this.e == null || this.e.isRecycled())) {
                        TXCLog.d(TXVideoInfoReader.this.TAG, "copy last image");
                        a = this.e.copy(this.e.getConfig(), true);
                    }
                }
                this.e = a;
                if (TXVideoInfoReader.this.mRetryGeneThreadTimes.get() != 0) {
                    TXVideoInfoReader.this.mRetryGeneThreadTimes.getAndSet(0);
                }
                if (TXVideoInfoReader.this.mListener != null && TXVideoInfoReader.this.mListener.get() != null && TXVideoInfoReader.this.mCount > 0 && TXVideoInfoReader.this.mListener.hashCode() == this.f) {
                    TXVideoInfoReader.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (TXVideoInfoReader.this.mListener != null && TXVideoInfoReader.this.mListener.get() != null && TXVideoInfoReader.this.mListener.hashCode() == a.this.f) {
                                TXCLog.i(TXVideoInfoReader.this.TAG, "return image success");
                                ((OnSampleProgrocess) TXVideoInfoReader.this.mListener.get()).sampleProcess(i, a);
                            }
                        }
                    });
                }
            }
            this.e = null;
            this.b.k();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public long getDuration(String str) {
        try {
            h hVar = new h();
            if (TextUtils.isEmpty(str) || !new File(str).exists()) {
                return 0;
            }
            hVar.a(str);
            long a = hVar.a();
            hVar.k();
            return a;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private TXVideoInfoReader() {
        TXCLog.init();
        this.mRetryGeneThreadTimes = new AtomicInteger(0);
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public static TXVideoInfoReader getInstance() {
        if (sInstance == null) {
            sInstance = new TXVideoInfoReader();
        }
        return sInstance;
    }

    public TXVideoInfo getVideoFileInfo(String str) {
        if (VERSION.SDK_INT < 18) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        TXVideoInfo tXVideoInfo = new TXVideoInfo();
        h hVar = new h();
        hVar.a(str);
        tXVideoInfo.duration = hVar.a();
        str = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getVideoFileInfo: duration = ");
        stringBuilder.append(tXVideoInfo.duration);
        TXCLog.i(str, stringBuilder.toString());
        tXVideoInfo.coverImage = hVar.j();
        tXVideoInfo.fps = hVar.g();
        tXVideoInfo.bitrate = (int) (hVar.h() / IjkMediaMeta.AV_CH_SIDE_RIGHT);
        tXVideoInfo.audioSampleRate = hVar.i();
        int d = hVar.d();
        String str2 = this.TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("rotation: ");
        stringBuilder2.append(d);
        TXCLog.i(str2, stringBuilder2.toString());
        if (d == 90 || d == 270) {
            tXVideoInfo.width = hVar.e();
            tXVideoInfo.height = hVar.f();
        } else {
            tXVideoInfo.width = hVar.f();
            tXVideoInfo.height = hVar.e();
        }
        hVar.k();
        tXVideoInfo.fileSize = file.length();
        return tXVideoInfo;
    }

    public Bitmap getSampleImage(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            TXCLog.w(this.TAG, "videoPath is null");
            return null;
        } else if (new File(str).exists()) {
            h hVar = new h();
            hVar.a(str);
            this.mImageVideoDuration = hVar.a() * 1000;
            j *= 1000;
            if (j > this.mImageVideoDuration) {
                j = this.mImageVideoDuration;
            }
            if (this.mImageVideoDuration <= 0) {
                TXCLog.w(this.TAG, "video duration is 0");
                hVar.k();
                return null;
            }
            Bitmap a = hVar.a(j);
            if (a == null) {
                TXCLog.d(this.TAG, "getSampleImages failed!!!");
                hVar.k();
                return a;
            }
            String str2 = this.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getSampleImages bmp  = ");
            stringBuilder.append(a);
            stringBuilder.append(",time=");
            stringBuilder.append(j);
            stringBuilder.append(",duration=");
            stringBuilder.append(this.mImageVideoDuration);
            TXCLog.d(str2, stringBuilder.toString());
            hVar.k();
            return a;
        } else {
            TXCLog.w(this.TAG, "videoPath is not exist");
            return null;
        }
    }

    public void getSampleImages(int i, String str, OnSampleProgrocess onSampleProgrocess) {
        this.mCount = i;
        this.mListener = new WeakReference(onSampleProgrocess);
        if (!TextUtils.isEmpty(str) && new File(str).exists()) {
            cancelThread();
            this.mGenerateImageThread = new a(str);
            this.mGenerateImageThread.start();
            TXCLog.i(this.TAG, "getSampleImages: thread start");
        }
    }

    public void cancel() {
        cancelThread();
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mListener != null) {
            this.mListener.clear();
            this.mListener = null;
        }
    }

    private void cancelThread() {
        if (this.mGenerateImageThread != null && this.mGenerateImageThread.isAlive() && !this.mGenerateImageThread.isInterrupted()) {
            TXCLog.i(this.TAG, "cancelThread: thread cancel");
            this.mGenerateImageThread.interrupt();
            this.mGenerateImageThread = null;
        }
    }
}
