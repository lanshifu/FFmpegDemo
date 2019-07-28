package com.lanshifu.ffmpegdemo.player;

import android.text.TextUtils;
import android.util.Log;

import com.lanshifu.ffmpegdemo.player.listener.MusicPlayListener;
import com.lanshifu.ffmpegdemo.player.listener.MusicPrepareListener;

public class MusicPlayer {

    static {
        System.loadLibrary("music-player");
    }

    private static final String TAG = "music-player";

    private MusicPlayListener mMusicPlayListener;

    private MusicPrepareListener mMusicPrepareListener;

    public void setMusicPrepareListener(MusicPrepareListener musicPrepareListener) {
        mMusicPrepareListener = musicPrepareListener;
    }

    public void setMusicPlayListener(MusicPlayListener musicPlayListener) {
        mMusicPlayListener = musicPlayListener;
    }

    private String url;

    public void setDataSource(String url) {
        this.url = url;
    }


    public void play() {

        if (TextUtils.isEmpty(url)) {
            Log.e(TAG, "play: url == null, had you call setDataSource(url) ?");
            return;
        }

        nativePlay(url);


    }

    public void prepare() {
        if (TextUtils.isEmpty(url)) {
            throw new NullPointerException("url is null, please call method setDataSource");
        }
        nativePrepare(url);
    }

    public void prepareAsync() {
        if (TextUtils.isEmpty(url)) {
            throw new NullPointerException("url is null, please call method setDataSource");
        }
        nativePrepareAsync(url);
    }


    private native void nativePlay(String url);

    private native void nativePrepare(String url);

    private native void nativePrepareAsync(String url);


    /** native 调用 */
    private void onProgress(int progress) {
        Log.d(TAG, "c->java onProgress: " + progress);
        if (mMusicPlayListener != null) {
            mMusicPlayListener.onProgress(progress);
        }
    }

    private void onError(int code, String error) {
        Log.d(TAG, "c->java error: c->java" + error);
        if (mMusicPlayListener != null) {
            mMusicPlayListener.onError(error);
        }
    }

    private void onPlayStart() {
        Log.d(TAG, "c->java onPlayStart:");
        if (mMusicPlayListener != null) {
            mMusicPlayListener.onPlayStar();
        }
    }

    private void onPlayEnd() {
        Log.d(TAG, "c->java onPlayEnd: ");
        if (mMusicPlayListener != null) {
            mMusicPlayListener.onPlayEnd();
        }
    }

    //异步prepare完成回调
    private void onPrepared(){
        Log.d(TAG, "c->java  onPrepare: ");
        if (mMusicPrepareListener != null){
            mMusicPrepareListener.onPrepare();
        }
    }

    /**native 调用*/


}
