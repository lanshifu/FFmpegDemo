package com.lanshifu.ffmpegdemo.player;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class VideoView extends SurfaceView {

    private VideoPlayer mVideoPlayer;

    public VideoView(Context context) {
        this(context,null);
    }

    public VideoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //设置显示的像素格式
        SurfaceHolder holder = getHolder();
        holder.setFormat(PixelFormat.RGBA_8888);

        mVideoPlayer = new VideoPlayer();
    }

    public void play(String uri){
        mVideoPlayer.setDataSource(uri);
        mVideoPlayer.play(getHolder().getSurface());
    }
}
