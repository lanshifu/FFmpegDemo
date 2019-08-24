package com.lanshifu.ffmpegdemo.avcodec.codec.thread;

import android.opengl.EGLContext;
import android.view.Surface;

import com.lanshifu.ffmpegdemo.avcodec.codec.MediaEncodeManager;
import com.lanshifu.ffmpegdemo.avcodec.surface.EglHelper;
import com.lanshifu.ffmpegdemo.avcodec.surface.EglSurfaceView;

import java.lang.ref.WeakReference;

/**
 * EglRender thread
 */
public class EglRenderThread extends Thread {

    private boolean mIsStop;

    private EglHelper mEglHelper;

    private Surface mSurface;
    private EGLContext mEGLContext;
    private EglSurfaceView.Render mSurfaceRender;

    private MediaEncodeManager mMediaEncodeManager;

    private int renderMode;
    private int width;
    private int height;
    
    private boolean mSurfaceCreate;

    public EglRenderThread(WeakReference<MediaEncodeManager> mediaEncodeManagerWeakReference, EGLContext eglContext,
                           int renderMode, int width, int height) {
        this.mMediaEncodeManager = mediaEncodeManagerWeakReference.get();
        this.mSurface = mMediaEncodeManager.mSurface;
        this.mEGLContext = eglContext;
        this.mSurfaceRender = mMediaEncodeManager.mEglSurfaceRender;
        this.renderMode = renderMode;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        super.run();
        mIsStop = false;
        mEglHelper = new EglHelper();
        mEglHelper.init(mSurface, mEGLContext);

        while (!mIsStop) {
            if (mSurfaceCreate && mSurfaceRender != null) {
                mSurfaceRender.onSurfaceCreated();
                mSurfaceCreate = false;
            }
            if (mMediaEncodeManager.mSurfaceChange && mSurfaceRender != null) {
                mSurfaceRender.onSurfaceChanged(width, height);
                mMediaEncodeManager.mSurfaceChange = false;
            }

            if (mSurfaceRender != null) {
                mSurfaceRender.onDrawFrame();
            }
            //OpenGL ES绘制完毕后调用swapBuffer,将前台的FrameBuffer和后台FrameBuffer进行交换
            mEglHelper.swapBuffer();
        }
        mEglHelper.release();
        mEglHelper = null;
    }

    public void stopEglRender() {
        mIsStop = true;
    }
}
