package com.lanshifu.ffmpegdemo.player;

import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;

public class VideoPlayer {
    private static final String TAG = "VideoPlayer";

    static {
        System.loadLibrary("video-player");
    }

    private String url;

    public void setDataSource(String url) {
        this.url = url;
    }


    public void play(Surface surface) {

        if (TextUtils.isEmpty(url)) {
            Log.e(TAG, "play: url == null, had you call setDataSource(url) ?");
            return;
        }

        nativePlay(url,surface);
    }

    private native void nativePlay(String url,Surface surface);
}
