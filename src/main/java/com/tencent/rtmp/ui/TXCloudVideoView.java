package com.tencent.rtmp.ui;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.tencent.liteav.p;
import com.tencent.liteav.renderer.TXCFocusIndicatorView;
import com.tencent.liteav.renderer.TXCGLSurfaceView;

public class TXCloudVideoView extends FrameLayout implements OnTouchListener {
    private static final int FOCUS_AREA_SIZE_DP = 70;
    private static final String TAG = "TXCloudVideoView";
    private p mCapture;
    private int mCurrentScale;
    private boolean mFocus;
    private int mFocusAreaSize;
    private TXCFocusIndicatorView mFocusIndicatorView;
    private TXCGLSurfaceView mGLSurfaceView;
    private TXLogView mLogView;
    private ScaleGestureDetector mScaleGestureDetector;
    private OnScaleGestureListener mScaleGestureListener;
    private a mTouchFocusRunnable;
    private TextureView mVideoView;
    private boolean mZoom;

    private class a implements Runnable {
        private View b;
        private MotionEvent c;

        private a() {
        }

        /* synthetic */ a(TXCloudVideoView tXCloudVideoView, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void a(View view) {
            this.b = view;
        }

        public void a(MotionEvent motionEvent) {
            this.c = motionEvent;
        }

        public void run() {
            if (TXCloudVideoView.this.mCapture != null && TXCloudVideoView.this.mFocus) {
                TXCloudVideoView.this.mCapture.a(this.c.getX() / ((float) this.b.getWidth()), this.c.getY() / ((float) this.b.getHeight()));
            }
            if (TXCloudVideoView.this.mFocus) {
                TXCloudVideoView.this.onTouchFocus((int) this.c.getX(), (int) this.c.getY());
            }
        }
    }

    private int clamp(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    public void adjustVideoSize() {
    }

    public void enableHardwareDecode(boolean z) {
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void refreshLastFrame() {
    }

    public void setGLOnTouchListener(OnTouchListener onTouchListener) {
    }

    public void setMirror(boolean z) {
    }

    public void setRenderMode(int i) {
    }

    public void setRenderRotation(int i) {
    }

    public void setStreamUrl(String str) {
    }

    public void setSurfaceTextureListener(SurfaceTextureListener surfaceTextureListener) {
    }

    public void setUseBeautyView(boolean z) {
    }

    public TXCloudVideoView(Context context) {
        this(context, null);
    }

    public TXCloudVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFocusAreaSize = 0;
        this.mFocus = false;
        this.mZoom = false;
        this.mCurrentScale = 1;
        this.mScaleGestureDetector = null;
        this.mScaleGestureListener = new OnScaleGestureListener() {
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                int e = TXCloudVideoView.this.mCapture != null ? TXCloudVideoView.this.mCapture.e() : 0;
                if (e > 0) {
                    float scaleFactor = scaleGestureDetector.getScaleFactor();
                    if (scaleFactor > 1.0f) {
                        scaleFactor = ((0.2f / ((float) e)) * ((float) (e - TXCloudVideoView.this.mCurrentScale))) + 1.0f;
                        if (scaleFactor <= 1.1f) {
                            scaleFactor = 1.1f;
                        }
                    } else if (scaleFactor < 1.0f) {
                        scaleFactor = 1.0f - ((0.2f / ((float) e)) * ((float) TXCloudVideoView.this.mCurrentScale));
                        if (scaleFactor >= 0.9f) {
                            scaleFactor = 0.9f;
                        }
                    }
                    int round = Math.round(((float) TXCloudVideoView.this.mCurrentScale) * scaleFactor);
                    if (round == TXCloudVideoView.this.mCurrentScale) {
                        if (scaleFactor > 1.0f) {
                            round++;
                        } else if (scaleFactor < 1.0f) {
                            round--;
                        }
                    }
                    if (round < e) {
                        e = round;
                    }
                    if (e <= 1) {
                        e = 1;
                    }
                    if (scaleFactor > 1.0f) {
                        if (e < TXCloudVideoView.this.mCurrentScale) {
                            e = TXCloudVideoView.this.mCurrentScale;
                        }
                    } else if (scaleFactor < 1.0f && e > TXCloudVideoView.this.mCurrentScale) {
                        e = TXCloudVideoView.this.mCurrentScale;
                    }
                    TXCloudVideoView.this.mCurrentScale = e;
                    if (TXCloudVideoView.this.mCapture != null) {
                        TXCloudVideoView.this.mCapture.a(TXCloudVideoView.this.mCurrentScale);
                    }
                }
                return false;
            }
        };
        this.mTouchFocusRunnable = new a(this, null);
        this.mLogView = new TXLogView(context);
        this.mScaleGestureDetector = new ScaleGestureDetector(context, this.mScaleGestureListener);
    }

    public void addVideoView(TXCGLSurfaceView tXCGLSurfaceView) {
        if (this.mGLSurfaceView != null) {
            removeView(this.mGLSurfaceView);
        }
        this.mGLSurfaceView = tXCGLSurfaceView;
        addView(this.mGLSurfaceView);
        removeView(this.mLogView);
        addView(this.mLogView);
    }

    public void addVideoView(TextureView textureView) {
        if (this.mVideoView != null) {
            removeView(this.mVideoView);
        }
        this.mVideoView = textureView;
        addView(this.mVideoView);
        removeView(this.mLogView);
        addView(this.mLogView);
    }

    public void removeVideoView() {
        if (this.mVideoView != null) {
            removeView(this.mVideoView);
            this.mVideoView = null;
        }
        if (this.mGLSurfaceView != null) {
            removeView(this.mGLSurfaceView);
            this.mGLSurfaceView = null;
        }
    }

    public void removeFocusIndicatorView() {
        if (this.mFocusIndicatorView != null) {
            removeView(this.mFocusIndicatorView);
            this.mFocusIndicatorView = null;
        }
    }

    public TextureView getVideoView() {
        return this.mVideoView;
    }

    public TXCGLSurfaceView getGLSurfaceView() {
        return this.mGLSurfaceView;
    }

    public TextureView getHWVideoView() {
        return this.mVideoView;
    }

    public void clearLastFrame(boolean z) {
        if (z) {
            setVisibility(8);
        }
    }

    public void onTouchFocus(int i, int i2) {
        if (this.mGLSurfaceView != null) {
            if (i < 0 || i2 < 0) {
                if (this.mFocusIndicatorView != null) {
                    this.mFocusIndicatorView.setVisibility(8);
                }
                return;
            }
            if (this.mFocusIndicatorView == null) {
                this.mFocusIndicatorView = new TXCFocusIndicatorView(getContext());
                this.mFocusIndicatorView.setVisibility(0);
                addView(this.mFocusIndicatorView);
            }
            Rect touchRect = getTouchRect(i, i2, this.mGLSurfaceView.getWidth(), this.mGLSurfaceView.getHeight(), 1.0f);
            this.mFocusIndicatorView.show(touchRect.left, touchRect.top, touchRect.right - touchRect.left);
        }
    }

    private Rect getTouchRect(int i, int i2, int i3, int i4, float f) {
        if (this.mFocusAreaSize == 0 && this.mGLSurfaceView != null) {
            this.mFocusAreaSize = (int) ((this.mGLSurfaceView.getResources().getDisplayMetrics().density * 70.0f) + 0.5f);
        }
        int intValue = Float.valueOf(((float) this.mFocusAreaSize) * f).intValue();
        int i5 = intValue / 2;
        i = clamp(i - i5, 0, i3 - intValue);
        i2 = clamp(i2 - i5, 0, i4 - intValue);
        return new Rect(i, i2, i + intValue, intValue + i2);
    }

    public void disableLog(boolean z) {
        this.mLogView.b(z);
    }

    public void showLog(boolean z) {
        this.mLogView.a(z);
    }

    public void clearLog() {
        this.mLogView.a();
    }

    public void setLogText(Bundle bundle, Bundle bundle2, int i) {
        this.mLogView.a(bundle, bundle2, i);
    }

    public void setLogMargin(int i, int i2, int i3, int i4) {
        LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = this.mLogView.getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams = (LayoutParams) layoutParams2;
        } else {
            layoutParams = new LayoutParams(-1, -1);
        }
        layoutParams.leftMargin = TXLogView.a(getContext(), (float) i);
        layoutParams.rightMargin = TXLogView.a(getContext(), (float) i2);
        layoutParams.topMargin = TXLogView.a(getContext(), (float) i3);
        layoutParams.bottomMargin = TXLogView.a(getContext(), (float) i4);
        this.mLogView.setLayoutParams(layoutParams);
    }

    public void start(boolean z, boolean z2, p pVar) {
        this.mFocus = z;
        this.mZoom = z2;
        if (this.mFocus || this.mZoom) {
            setOnTouchListener(this);
            this.mCapture = pVar;
        }
        post(new Runnable() {
            public void run() {
                if (TXCloudVideoView.this.mGLSurfaceView != null) {
                    TXCloudVideoView.this.mGLSurfaceView.setVisibility(0);
                }
            }
        });
    }

    public void stop(boolean z) {
        if (this.mFocus || this.mZoom) {
            setOnTouchListener(null);
        }
        this.mCapture = null;
        if (z) {
            post(new Runnable() {
                public void run() {
                    if (TXCloudVideoView.this.mGLSurfaceView != null) {
                        TXCloudVideoView.this.mGLSurfaceView.setVisibility(8);
                    }
                }
            });
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 1 && motionEvent.getAction() == 0) {
            this.mTouchFocusRunnable.a(view);
            this.mTouchFocusRunnable.a(motionEvent);
            postDelayed(this.mTouchFocusRunnable, 100);
        } else if (motionEvent.getPointerCount() > 1 && motionEvent.getAction() == 2) {
            removeCallbacks(this.mTouchFocusRunnable);
            onTouchFocus(-1, -1);
            if (this.mScaleGestureDetector != null && this.mZoom) {
                this.mScaleGestureDetector.onTouchEvent(motionEvent);
            }
        }
        if (this.mZoom && motionEvent.getAction() == 0) {
            performClick();
        }
        return this.mZoom;
    }
}
