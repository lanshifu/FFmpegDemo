package com.tencent.liteav.videoediter.ffmpeg;

import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.videoediter.ffmpeg.jni.FFMediaInfo;
import com.tencent.liteav.videoediter.ffmpeg.jni.TXFFMediaInfoJNI;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;

/* compiled from: TXFFMediaRetriever */
public class a {
    private String a;
    private FFMediaInfo b;

    public int a(String str) {
        if (str == null) {
            TXCLog.e("TXFFMediaRetriever", "setDataSource: path can't be null!");
            return -1;
        } else if (!new File(str).exists()) {
            TXCLog.e("TXFFMediaRetriever", "setDataSource: file isn't exists!");
            return -1;
        } else if (str.equals(this.a)) {
            return 0;
        } else {
            this.a = str;
            this.b = TXFFMediaInfoJNI.getMediaInfo(this.a);
            if (this.b != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("setDataSource: media info = ");
                stringBuilder.append(this.b.toString());
                TXCLog.i("TXFFMediaRetriever", stringBuilder.toString());
                return 0;
            }
            TXCLog.e("TXFFMediaRetriever", "setDataSource: get media info fail!");
            return -1;
        }
    }

    public int a() {
        if (this.a != null && this.b != null) {
            return this.b.rotation;
        }
        TXCLog.e("TXFFMediaRetriever", "getRotation: you must set path first!");
        return 0;
    }

    public int b() {
        if (this.a != null && this.b != null) {
            return this.b.width;
        }
        TXCLog.e("TXFFMediaRetriever", "getVideoWidth: you must set path first!");
        return 0;
    }

    public int c() {
        if (this.a != null && this.b != null) {
            return this.b.height;
        }
        TXCLog.e("TXFFMediaRetriever", "getVideoHeight: you must set path first!");
        return 0;
    }

    public float d() {
        if (this.a != null && this.b != null) {
            return this.b.fps;
        }
        TXCLog.e("TXFFMediaRetriever", "getVideoFPS: you must set path first!");
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public long e() {
        if (this.a != null && this.b != null) {
            return this.b.videoBitrate;
        }
        TXCLog.e("TXFFMediaRetriever", "getVideoBitrate: you must set path first!");
        return 0;
    }

    public long f() {
        if (this.a != null && this.b != null) {
            return this.b.videoDuration;
        }
        TXCLog.e("TXFFMediaRetriever", "getVideoDuration: you must set path first!");
        return 0;
    }

    public int g() {
        if (this.a != null && this.b != null) {
            return this.b.sampleRate;
        }
        TXCLog.e("TXFFMediaRetriever", "getSampleRate: you must set path first!");
        return 0;
    }

    public long h() {
        if (this.a != null && this.b != null) {
            return this.b.audioDuration;
        }
        TXCLog.e("TXFFMediaRetriever", "getAudioDuration: you must set path first!");
        return 0;
    }
}
