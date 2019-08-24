package com.lanshifu.ffmpegdemo.avcodec.surface;

import android.content.Context;
import android.opengl.EGLContext;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 *  GLSurfaceView 扩展，参考GLSurfaceView
 *
 *  也可以直接继承GLSurfaceView，暴露EGLContext 出来
 */
public class EglSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private Surface mSurface;
    private Thread mThread;
    private EGLContext mEGLContext;
    private int mRenderMode;
    private Render mRender;

    private EglRunnable mEglRunnable;
    private EglHelper mEglHelper;

    public EglSurfaceView(Context context) {
        this(context, null);
    }

    public EglSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EglSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取surfaceHolder预览对象
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mSurface == null) {
            mSurface = holder.getSurface();
        }

        mEglRunnable = new EglRunnable();

        mThread = new Thread(mEglRunnable);
        mEglRunnable.isSurfaceCreate = true;
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mEglRunnable.width = width;
        mEglRunnable.height = height;
        mEglRunnable.isSurfaceChange = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mEglRunnable.isStop = true;
        mThread.isInterrupted();
        mSurface = null;
        mEGLContext = null;
    }

    private class EglRunnable implements Runnable {

        private boolean isSurfaceCreate;
        private boolean isSurfaceChange;
        private boolean isStart;
        private boolean isStop;

        private int width;
        private int height;

        private Object object;

        @Override
        public void run() {
            isStart = false;
            isStop = false;
            mEglHelper = new EglHelper();
            mEglHelper.init(mSurface, mEGLContext);
            while (!isStop) {
                if (isStart) {
                    if (getRenderMode() == GLSurfaceView.RENDERMODE_CONTINUOUSLY) {
                        try {
                            Thread.sleep(1000 / 60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (isSurfaceCreate && mRender != null) {
                    mRender.onSurfaceCreated();
                    isSurfaceCreate = false;
                }
                if (isSurfaceChange && mRender != null) {
                    mRender.onSurfaceChanged(width, height);
                    isSurfaceChange = false;
                }

                if (mRender != null) {
                    mRender.onDrawFrame();
                    if (!isStart) {
                        //首次调用
                        mRender.onDrawFrame();
                    }
                }
                //OpenGL ES绘制完毕后调用swapBuffer,将前台的FrameBuffer和后台FrameBuffer进行交换
                mEglHelper.swapBuffer();
                isStart = true;

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            release();
        }

        /**
         * 释放资源
         */
        private void release() {
            mEglHelper.release();
            object = null;
        }

        /**
         * 重新绘制
         */
        void requestRender() {
            if (object != null) {
                synchronized (object) {
                    object.notifyAll();
                }
            }
        }
    }


    public void setRenderMode(int renderMode) {
        this.mRenderMode = renderMode;
    }

    public int getRenderMode() {
        return mRenderMode;
    }

    public void requestRender() {
        if (mEglRunnable != null) {
            mEglRunnable.requestRender();
        }
    }

    public EGLContext getEGLContext() {
        if (mEglHelper != null) {
            return mEglHelper.getEglContext();
        }
        return null;
    }

    /**
     * 回调接口
     */
    public interface Render {
        void onSurfaceCreated();

        void onSurfaceChanged(int width, int height);

        void onDrawFrame();

    }

    public void setRender(Render render) {
        this.mRender = render;
    }

    /**
     * egl上下文暴露出去
     * @return
     */
    public EGLContext getEglContext() {
        if (mEglHelper != null) {
            return mEglHelper.getEglContext();
        }
        return null;
    }
}
