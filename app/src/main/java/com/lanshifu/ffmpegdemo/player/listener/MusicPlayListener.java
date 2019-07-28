package com.lanshifu.ffmpegdemo.player.listener;

public interface MusicPlayListener {

    void onProgress(int progress);

    void onError(String error);

    void onPlayStar();

    void onPlayEnd();

}
