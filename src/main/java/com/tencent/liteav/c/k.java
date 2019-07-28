package com.tencent.liteav.c;

import android.graphics.Bitmap;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.ugc.TXVideoEditConstants;
import java.io.File;
import java.io.IOException;
import java.util.List;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

/* compiled from: VideoSourceConfig */
public class k {
    private static k b;
    public String a;
    private int c = 1;
    private List<Bitmap> d;
    private int e;

    public static k a() {
        if (b == null) {
            b = new k();
        }
        return b;
    }

    private k() {
    }

    public void a(List<Bitmap> list, int i) {
        this.d = list;
        this.e = i;
        this.c = 2;
    }

    public List<Bitmap> b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        if (TextUtils.isEmpty(this.a) || !new File(this.a).exists()) {
            return TXVideoEditConstants.ERR_SOURCE_NO_FOUND;
        }
        if (VERSION.SDK_INT >= 16) {
            try {
                MediaExtractor mediaExtractor = new MediaExtractor();
                mediaExtractor.setDataSource(this.a);
                int trackCount = mediaExtractor.getTrackCount();
                if (trackCount < 1) {
                    return TXVideoEditConstants.ERR_SOURCE_NO_TRACK;
                }
                for (int i = 0; i < trackCount; i++) {
                    MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("checkLegality :");
                    stringBuilder.append(trackFormat.toString());
                    TXCLog.i("VideoSourceConfig", stringBuilder.toString());
                    if (trackFormat.getString(IMediaFormat.KEY_MIME).startsWith("audio") && trackFormat.containsKey("channel-count") && trackFormat.getInteger("channel-count") > 2) {
                        mediaExtractor.release();
                        return -1004;
                    }
                }
                mediaExtractor.release();
            } catch (Exception e) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Exception:");
                stringBuilder2.append(e.toString());
                TXCLog.e("VideoSourceConfig", stringBuilder2.toString());
                return TXVideoEditConstants.ERR_SOURCE_DAMAGED;
            }
        }
        return 0;
    }

    public int a(String str) {
        if (!new File(str).exists()) {
            return TXVideoEditConstants.ERR_SOURCE_NO_FOUND;
        }
        if (VERSION.SDK_INT >= 16) {
            MediaExtractor mediaExtractor = new MediaExtractor();
            try {
                mediaExtractor.setDataSource(str);
                int trackCount = mediaExtractor.getTrackCount();
                for (int i = 0; i < trackCount; i++) {
                    MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("BGM checkLegality :");
                    stringBuilder.append(trackFormat.toString());
                    TXCLog.i("VideoSourceConfig", stringBuilder.toString());
                    if (trackFormat.getString(IMediaFormat.KEY_MIME).startsWith("audio") && trackFormat.containsKey("channel-count") && trackFormat.getInteger("channel-count") > 2) {
                        return -1004;
                    }
                }
            } catch (Exception e) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Exception:");
                stringBuilder2.append(e.toString());
                TXCLog.e("VideoSourceConfig", stringBuilder2.toString());
                return TXVideoEditConstants.ERR_SOURCE_DAMAGED;
            }
        }
        return 0;
    }

    public boolean f() {
        if (VERSION.SDK_INT < 16) {
            TXCLog.e("VideoSourceConfig", "judgeFullIFrame SDK version is less:16");
            return false;
        }
        if (VERSION.SDK_INT >= 16) {
            int i;
            MediaExtractor mediaExtractor = new MediaExtractor();
            try {
                mediaExtractor.setDataSource(this.a);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (i = 0; i < mediaExtractor.getTrackCount(); i++) {
                if (mediaExtractor.getTrackFormat(i).getString(IMediaFormat.KEY_MIME).startsWith("video/")) {
                    mediaExtractor.selectTrack(i);
                }
            }
            mediaExtractor.seekTo(0, 0);
            i = mediaExtractor.getSampleFlags();
            mediaExtractor.advance();
            mediaExtractor.advance();
            mediaExtractor.advance();
            int sampleFlags = mediaExtractor.getSampleFlags();
            mediaExtractor.advance();
            mediaExtractor.advance();
            int sampleFlags2 = mediaExtractor.getSampleFlags();
            mediaExtractor.release();
            if (i == sampleFlags && i == sampleFlags2 && i == 1) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void g() {
        this.c = 1;
        this.a = null;
        this.e = 0;
        if (this.d != null) {
            this.d.clear();
        }
    }
}
