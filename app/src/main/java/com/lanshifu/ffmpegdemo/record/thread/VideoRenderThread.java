package com.lanshifu.ffmpegdemo.record.thread;


import com.lanshifu.ffmpegdemo.opengl.EglHelper;
import com.lanshifu.ffmpegdemo.record.BaseVideoRecorder;

import java.lang.ref.WeakReference;

import javax.microedition.khronos.opengles.GL10;


/**
 * 视频的渲染线程
 */
public class VideoRenderThread extends Thread {

    private WeakReference<BaseVideoRecorder> mVideoRecorderWr;
    private volatile boolean mShouldExit = false;
    private EglHelper mEglHelper;
    private boolean mHashCreateContext = false;
    private boolean mHashSurfaceCreated = false;
    private boolean mHashSurfaceChanged = false;
    private int mWidth;
    private int mHeight;

    public VideoRenderThread(WeakReference<BaseVideoRecorder> videoRecorderWr) {
        this.mVideoRecorderWr = videoRecorderWr;
        mEglHelper = new EglHelper();
    }

    @Override
    public void run() {

        try {
            while (true) {
                if (mShouldExit) {
                    return;
                }

                BaseVideoRecorder videoRecorder = mVideoRecorderWr.get();
                if (videoRecorder == null) {
                    return;
                }

                // 1. 创建 EGL 上下文
                if (!mHashCreateContext) {
                    mEglHelper.initCreateEgl(videoRecorder.mSurface, videoRecorder.mEglContext);
                    mHashCreateContext = true;
                }

                // 回调 Render
                GL10 gl = (GL10) mEglHelper.getEglContext().getGL();
                if (!mHashSurfaceCreated) {
                    videoRecorder.mRenderer.onSurfaceCreated(gl, mEglHelper.getEGLConfig());
                    mHashSurfaceCreated = true;
                }

                if (!mHashSurfaceChanged) {
                    videoRecorder.mRenderer.onSurfaceChanged(gl, mWidth, mHeight);
                    mHashSurfaceChanged = true;
                }

                videoRecorder.mRenderer.onDrawFrame(gl);

                mEglHelper.swapBuffers();

                // 60 fps
                Thread.sleep(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            onDestroy();
        }
    }

    private void onDestroy() {
        mEglHelper.destroy();
    }

    public void requestExit() {
        mShouldExit = true;
    }

    public void setSize(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }
}
