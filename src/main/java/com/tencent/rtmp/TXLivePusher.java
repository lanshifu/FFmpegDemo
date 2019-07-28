package com.tencent.rtmp;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaFormat;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import com.tencent.liteav.audio.TXCAudioUGCRecorder;
import com.tencent.liteav.basic.c.e;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.e.n;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.module.TXCStatus;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.basic.util.b;
import com.tencent.liteav.c;
import com.tencent.liteav.d;
import com.tencent.liteav.h;
import com.tencent.liteav.network.TXCStreamUploader;
import com.tencent.liteav.network.l;
import com.tencent.liteav.qos.TXCQoS;
import com.tencent.liteav.r;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.ugc.TXRecordCommon.ITXVideoRecordListener;
import com.tencent.ugc.TXRecordCommon.TXRecordResult;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TXLivePusher implements com.tencent.liteav.basic.d.a, com.tencent.liteav.c.a, com.tencent.liteav.qos.a, r {
    public static final int RGB_BGRA = 4;
    public static final int RGB_RGBA = 5;
    private static final byte SEI_MSG_TYPE = (byte) -14;
    private static final String TAG = "TXLivePusher";
    public static final int YUV_420P = 3;
    public static final int YUV_420SP = 1;
    public static final int YUV_420YpCbCr = 2;
    private AudioCustomProcessListener mAudioProcessListener;
    private float mBgmPitch = CropImageView.DEFAULT_ASPECT_RATIO;
    private c mCaptureAndEnc = null;
    private TXLivePushConfig mConfig = null;
    private Context mContext = null;
    private d mDataReport = null;
    private String mID = "";
    private boolean mIsRecording = false;
    private ITXLivePushListener mListener = null;
    private com.tencent.liteav.muxer.c mMP4Muxer = null;
    private Handler mMainHandler = null;
    private ArrayList<a> mMsgArray = new ArrayList();
    private h mNewConfig = null;
    private boolean mNotifyStatus = false;
    private VideoCustomProcessListener mPreprocessListener;
    private String mPushUrl = "";
    private TXCQoS mQos = null;
    private ITXVideoRecordListener mRecordListener;
    private long mRecordStartTime = 0;
    private Runnable mSnapShotTimeOutRunnable = new Runnable() {
        public void run() {
            TXLivePusher.this.mSnapshotRunning = false;
        }
    };
    private boolean mSnapshotRunning = false;
    private boolean mStartMuxer = false;
    private TXCStreamUploader mStreamUploader = null;
    private TXCloudVideoView mTXCloudVideoView;
    private String mVideoFilePath = "";
    private int mVideoQuality = -1;
    private int mVoiceEnvironment = -1;
    private int mVoiceKind = -1;

    public interface AudioCustomProcessListener {
        void onRecordPcmData(byte[] bArr, long j, int i, int i2, int i3);

        void onRecordRawPcmData(byte[] bArr, long j, int i, int i2, int i3, boolean z);
    }

    public interface ITXSnapshotListener {
        void onSnapshot(Bitmap bitmap);
    }

    public interface OnBGMNotify {
        void onBGMComplete(int i);

        void onBGMProgress(long j, long j2);

        void onBGMStart();
    }

    public interface VideoCustomProcessListener {
        void onDetectFacePoints(float[] fArr);

        int onTextureCustomProcess(int i, int i2, int i3);

        void onTextureDestoryed();
    }

    private class a {
        long a;
        byte[] b;

        private a() {
        }

        /* synthetic */ a(TXLivePusher tXLivePusher, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private int getAdjustStrategy(boolean z, boolean z2) {
        return z ? z2 ? 1 : 0 : -1;
    }

    public int onGetVideoQueueMaxCount() {
        return 5;
    }

    public TXLivePusher(Context context) {
        TXCLog.init();
        this.mNewConfig = new h();
        this.mContext = context.getApplicationContext();
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mCaptureAndEnc = new c(this.mContext);
        this.mCaptureAndEnc.a((com.tencent.liteav.basic.d.a) this);
        e.a().a(null, this.mContext);
    }

    public void setConfig(TXLivePushConfig tXLivePushConfig) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setConfig ");
        stringBuilder.append(tXLivePushConfig);
        stringBuilder.append(", ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        if (tXLivePushConfig == null) {
            tXLivePushConfig = new TXLivePushConfig();
        }
        this.mConfig = tXLivePushConfig;
        transferConfig(tXLivePushConfig);
        applyConfig();
    }

    public TXLivePushConfig getConfig() {
        return this.mConfig;
    }

    public void setPushListener(ITXLivePushListener iTXLivePushListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setPushListener ");
        stringBuilder.append(iTXLivePushListener);
        TXCLog.d(str, stringBuilder.toString());
        this.mListener = iTXLivePushListener;
    }

    public int startPusher(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api startPusher ");
        stringBuilder.append(this);
        TXCLog.d(str2, stringBuilder.toString());
        StringBuilder stringBuilder2;
        if (TextUtils.isEmpty(str)) {
            str = TAG;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("start push error when url is empty ");
            stringBuilder2.append(this);
            TXCLog.e(str, stringBuilder2.toString());
            return -1;
        }
        if (!TextUtils.isEmpty(this.mPushUrl) && isPushing()) {
            if (this.mPushUrl.equalsIgnoreCase(str)) {
                str = TAG;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("ignore start push when new url is the same with old url  ");
                stringBuilder2.append(this);
                TXCLog.w(str, stringBuilder2.toString());
                return -1;
            }
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append(" stop old push when new url is not the same with old url  ");
            stringBuilder.append(this);
            TXCLog.w(str2, stringBuilder.toString());
            stopPusher();
        }
        TXCLog.d(TAG, "================================================================================================================================================");
        TXCLog.d(TAG, "================================================================================================================================================");
        str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("============= startPush pushUrl = ");
        stringBuilder.append(str);
        stringBuilder.append(" SDKVersion = ");
        stringBuilder.append(TXCCommonUtil.getSDKID());
        stringBuilder.append(" , ");
        stringBuilder.append(TXCCommonUtil.getSDKVersionStr());
        stringBuilder.append("=============");
        TXCLog.d(str2, stringBuilder.toString());
        TXCLog.d(TAG, "================================================================================================================================================");
        TXCLog.d(TAG, "================================================================================================================================================");
        this.mPushUrl = str;
        updateId(this.mPushUrl);
        startNetworkModule();
        startEncoder();
        startQosModule();
        startDataReportModule();
        startStatusNotify();
        if (this.mTXCloudVideoView != null) {
            this.mTXCloudVideoView.clearLog();
        }
        return 0;
    }

    public void stopPusher() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api stopPusher ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        stopRecord();
        stopStatusNotify();
        stopDataReportModule();
        stopQosModule();
        stopEncoder();
        this.mNewConfig.I = false;
        stopNetworkModule();
    }

    public void pausePusher() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api pausePusher ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.g();
        }
    }

    public void resumePusher() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api resumePusher ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.h();
        }
    }

    public boolean isPushing() {
        return this.mCaptureAndEnc != null ? this.mCaptureAndEnc.i() : false;
    }

    public void startCameraPreview(TXCloudVideoView tXCloudVideoView) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api startCameraPreview ");
        stringBuilder.append(tXCloudVideoView);
        stringBuilder.append(", ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        setConfig(this.mConfig);
        if (this.mNewConfig.F) {
            TXCLog.e(TAG, "enable pure audio push , so can not start preview!");
            return;
        }
        if (!(this.mTXCloudVideoView == tXCloudVideoView || this.mTXCloudVideoView == null)) {
            this.mTXCloudVideoView.removeVideoView();
        }
        this.mTXCloudVideoView = tXCloudVideoView;
        if (this.mCaptureAndEnc == null) {
            this.mCaptureAndEnc = new c(this.mContext);
        }
        this.mCaptureAndEnc.a((com.tencent.liteav.basic.d.a) this);
        this.mCaptureAndEnc.a((com.tencent.liteav.c.a) this);
        this.mCaptureAndEnc.a(tXCloudVideoView);
        this.mCaptureAndEnc.b(this.mConfig.mBeautyLevel, this.mConfig.mWhiteningLevel, this.mConfig.mRuddyLevel);
        e.a().a(null, this.mContext);
    }

    public void stopCameraPreview(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api stopCameraPreview ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(z);
        }
    }

    public void setSurface(Surface surface) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setSurface ");
        stringBuilder.append(surface);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(surface);
        }
    }

    public void setSurfaceSize(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setSurfaceSize ");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(i, i2);
        }
    }

    public void switchCamera() {
        TXCLog.d(TAG, "liteav_api switchCamera ");
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.j();
        }
    }

    public boolean turnOnFlashLight(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api turnOnFlashLight ");
        stringBuilder.append(z);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc == null) {
            return false;
        }
        return this.mCaptureAndEnc.b(z);
    }

    public void setMute(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setMute ");
        stringBuilder.append(z);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.d(z);
        }
        if (this.mConfig.mEnablePureAudioPush && this.mStreamUploader != null) {
            this.mStreamUploader.setAudioMute(z);
        }
    }

    public void onLogRecord(String str) {
        TXCLog.d("User", str);
    }

    public int sendCustomVideoData(byte[] bArr, int i, int i2, int i3) {
        if (this.mCaptureAndEnc == null) {
            return -1000;
        }
        if (i == 3) {
            i = 1;
        } else if (i != 5) {
            return -1000;
        } else {
            i = 2;
        }
        return this.mCaptureAndEnc.a(bArr, i, i2, i3);
    }

    public int sendCustomVideoTexture(int i, int i2, int i3) {
        return this.mCaptureAndEnc != null ? this.mCaptureAndEnc.c(i, i2, i3) : -1000;
    }

    public void sendCustomPCMData(byte[] bArr) {
        this.mCaptureAndEnc.a(bArr);
    }

    public int getMaxZoom() {
        if (this.mCaptureAndEnc == null) {
            return 0;
        }
        return this.mCaptureAndEnc.n();
    }

    public boolean setZoom(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setZoom ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc == null) {
            return false;
        }
        return this.mCaptureAndEnc.i(i);
    }

    public void setFocusPosition(float f, float f2) {
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(f, f2);
        }
    }

    public boolean setMirror(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setMirror ");
        stringBuilder.append(z);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mConfig != null) {
            this.mConfig.setVideoEncoderXMirror(z);
        }
        if (this.mCaptureAndEnc == null) {
            return false;
        }
        this.mCaptureAndEnc.e(z);
        return true;
    }

    public void setExposureCompensation(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setExposureCompensation ");
        stringBuilder.append(f);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.c(f);
        }
    }

    public void setBGMNofify(OnBGMNotify onBGMNotify) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setBGMNofify ");
        stringBuilder.append(onBGMNotify);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(onBGMNotify);
        }
    }

    public boolean playBGM(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api playBGM ");
        stringBuilder.append(str);
        TXCLog.d(str2, stringBuilder.toString());
        return this.mCaptureAndEnc.c(str);
    }

    public boolean stopBGM() {
        TXCLog.d(TAG, "liteav_api stopBGM ");
        return this.mCaptureAndEnc.o();
    }

    public boolean pauseBGM() {
        TXCLog.d(TAG, "liteav_api pauseBGM ");
        return this.mCaptureAndEnc.p();
    }

    public boolean resumeBGM() {
        TXCLog.d(TAG, "liteav_api resumeBGM ");
        return this.mCaptureAndEnc.q();
    }

    public boolean setMicVolume(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setMicVolume ");
        stringBuilder.append(f);
        TXCLog.d(str, stringBuilder.toString());
        return this.mCaptureAndEnc.d(f);
    }

    public boolean setBGMVolume(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setBGMVolume ");
        stringBuilder.append(f);
        TXCLog.d(str, stringBuilder.toString());
        return this.mCaptureAndEnc.e(f);
    }

    public void setBgmPitch(float f) {
        this.mBgmPitch = f;
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.b(f);
        }
    }

    public int getMusicDuration(String str) {
        return this.mCaptureAndEnc.d(str);
    }

    public void startScreenCapture() {
        TXCLog.d(TAG, "liteav_api startScreenCapture ");
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.k();
        }
    }

    public void stopScreenCapture() {
        TXCLog.d(TAG, "liteav_api stopScreenCapture ");
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.l();
        }
    }

    public void setRenderRotation(int i) {
        TXCLog.d(TAG, "liteav_api setRenderRotation ");
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(i);
        }
    }

    public void setVideoProcessListener(VideoCustomProcessListener videoCustomProcessListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setVideoProcessListener ");
        stringBuilder.append(videoCustomProcessListener);
        TXCLog.d(str, stringBuilder.toString());
        this.mPreprocessListener = videoCustomProcessListener;
        if (this.mPreprocessListener == null) {
            if (this.mCaptureAndEnc != null) {
                this.mCaptureAndEnc.a(null);
            }
        } else if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a((r) this);
        }
    }

    public void setAudioProcessListener(AudioCustomProcessListener audioCustomProcessListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setAudioProcessListener ");
        stringBuilder.append(audioCustomProcessListener);
        TXCLog.d(str, stringBuilder.toString());
        this.mAudioProcessListener = audioCustomProcessListener;
    }

    public void snapshot(final ITXSnapshotListener iTXSnapshotListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api snapshot ");
        stringBuilder.append(iTXSnapshotListener);
        TXCLog.d(str, stringBuilder.toString());
        if (!this.mSnapshotRunning && iTXSnapshotListener != null && this.mCaptureAndEnc != null) {
            if (this.mCaptureAndEnc != null) {
                this.mSnapshotRunning = true;
                this.mCaptureAndEnc.a(new n() {
                    public void onTakePhotoComplete(Bitmap bitmap) {
                        TXLivePusher.this.postBitmapToMainThread(iTXSnapshotListener, bitmap);
                        TXLivePusher.this.mSnapshotRunning = false;
                        TXLivePusher.this.mMainHandler.removeCallbacks(TXLivePusher.this.mSnapShotTimeOutRunnable);
                    }
                });
                this.mMainHandler.postDelayed(this.mSnapShotTimeOutRunnable, 2000);
            } else {
                this.mSnapshotRunning = false;
            }
        }
    }

    public int startRecord(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api startRecord ");
        stringBuilder.append(str);
        TXCLog.d(str2, stringBuilder.toString());
        if (VERSION.SDK_INT < 18) {
            str = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("API levl is too low (record need 18, current is");
            stringBuilder2.append(VERSION.SDK_INT);
            stringBuilder2.append(")");
            TXCLog.e(str, stringBuilder2.toString());
            return -3;
        } else if (this.mIsRecording) {
            TXCLog.w(TAG, "ignore start record when recording");
            return -1;
        } else if (this.mCaptureAndEnc == null || !this.mCaptureAndEnc.i()) {
            TXCLog.w(TAG, "ignore start record when not pushing");
            return -2;
        } else {
            TXCLog.w(TAG, "start record ");
            this.mIsRecording = true;
            this.mVideoFilePath = str;
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            this.mMP4Muxer = new com.tencent.liteav.muxer.c(this.mContext, 2);
            this.mStartMuxer = false;
            this.mMP4Muxer.a(this.mVideoFilePath);
            addAudioTrack();
            TXCDRApi.txReportDAU(this.mContext.getApplicationContext(), com.tencent.liteav.basic.datareport.a.aH);
            if (this.mCaptureAndEnc != null) {
                this.mCaptureAndEnc.s();
            }
            return 0;
        }
    }

    public void stopRecord() {
        TXCLog.d(TAG, "liteav_api stopRecord ");
        if (this.mIsRecording && this.mMP4Muxer != null) {
            int b = this.mMP4Muxer.b();
            TXCLog.w(TAG, "start record ");
            this.mIsRecording = false;
            if (b == 0) {
                final String str = this.mVideoFilePath;
                AsyncTask.execute(new Runnable() {
                    public void run() {
                        File parentFile = new File(str).getParentFile();
                        String format = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date(System.currentTimeMillis()));
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(parentFile);
                        stringBuilder.append(File.separator);
                        stringBuilder.append(String.format("TXUGCCover_%s.jpg", new Object[]{format}));
                        String stringBuilder2 = stringBuilder.toString();
                        b.a(str, stringBuilder2);
                        TXLivePusher.this.callbackRecordSuccess(str, stringBuilder2);
                    }
                });
                return;
            }
            callbackRecordFail();
        }
    }

    public void setVideoRecordListener(ITXVideoRecordListener iTXVideoRecordListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setVideoRecordListener ");
        stringBuilder.append(iTXVideoRecordListener);
        TXCLog.d(str, stringBuilder.toString());
        this.mRecordListener = iTXVideoRecordListener;
    }

    public boolean setBeautyFilter(int i, int i2, int i3, int i4) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setBeautyFilter ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        stringBuilder.append(", ");
        stringBuilder.append(i3);
        stringBuilder.append(", ");
        stringBuilder.append(i4);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.b(i);
            this.mCaptureAndEnc.b(i2, i3, i4);
        }
        if (this.mConfig != null) {
            this.mConfig.mBeautyLevel = i2;
            this.mConfig.mWhiteningLevel = i3;
            this.mConfig.mRuddyLevel = i4;
        }
        return true;
    }

    public void setFilter(Bitmap bitmap) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setFilter ");
        stringBuilder.append(bitmap);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(bitmap);
        }
    }

    public void setMotionTmpl(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api motionPath ");
        stringBuilder.append(str);
        TXCLog.d(str2, stringBuilder.toString());
        e.a().a(null, this.mContext);
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(str);
        }
    }

    public void setMotionMute(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setMotionMute ");
        stringBuilder.append(z);
        TXCLog.d(str, stringBuilder.toString());
        e.a().a(null, this.mContext);
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.c(z);
        }
    }

    @TargetApi(18)
    public boolean setGreenScreenFile(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setGreenScreenFile ");
        stringBuilder.append(str);
        TXCLog.d(str2, stringBuilder.toString());
        e.a().a(null, this.mContext);
        return this.mCaptureAndEnc != null ? this.mCaptureAndEnc.b(str) : false;
    }

    public void setEyeScaleLevel(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setEyeScaleLevel ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        e.a().a(null, this.mContext);
        if (this.mConfig != null) {
            this.mConfig.setEyeScaleLevel(i);
        }
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.c(i);
        }
    }

    public void setFaceSlimLevel(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setFaceSlimLevel ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        e.a().a(null, this.mContext);
        if (this.mConfig != null) {
            this.mConfig.setFaceSlimLevel(i);
        }
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.d(i);
        }
    }

    public void setFaceVLevel(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setFaceVLevel ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        e.a().a(null, this.mContext);
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.e(i);
        }
    }

    public void setSpecialRatio(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setSpecialRatio ");
        stringBuilder.append(f);
        TXCLog.d(str, stringBuilder.toString());
        e.a().a(null, this.mContext);
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(f);
        }
    }

    public void setFaceShortLevel(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setFaceShortLevel ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        e.a().a(null, this.mContext);
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.f(i);
        }
    }

    public void setChinLevel(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setChinLevel ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        e.a().a(null, this.mContext);
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.g(i);
        }
    }

    public void setNoseSlimLevel(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setNoseSlimLevel ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        e.a().a(null, this.mContext);
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.h(i);
        }
    }

    /* JADX WARNING: Missing block: B:14:0x00c8, code skipped:
            r13 = false;
     */
    /* JADX WARNING: Missing block: B:15:0x00c9, code skipped:
            r10 = 1;
     */
    /* JADX WARNING: Missing block: B:30:0x01fa, code skipped:
            r13 = false;
     */
    /* JADX WARNING: Missing block: B:31:0x01fb, code skipped:
            r11.mVideoQuality = r12;
            r11.mConfig.enableVideoHardEncoderMainProfile(r10 ^ 1);
            r12 = r11.mConfig;
     */
    /* JADX WARNING: Missing block: B:32:0x0206, code skipped:
            if (r10 == 0) goto L_0x0209;
     */
    /* JADX WARNING: Missing block: B:33:0x0208, code skipped:
            r1 = 1;
     */
    /* JADX WARNING: Missing block: B:34:0x0209, code skipped:
            r12.setVideoEncodeGop(r1);
     */
    /* JADX WARNING: Missing block: B:35:0x020e, code skipped:
            if (r11.mNewConfig == null) goto L_0x0218;
     */
    /* JADX WARNING: Missing block: B:36:0x0210, code skipped:
            r11.mNewConfig.I = r10;
            r11.mNewConfig.J = r13;
     */
    /* JADX WARNING: Missing block: B:37:0x0218, code skipped:
            setConfig(r11.mConfig);
     */
    /* JADX WARNING: Missing block: B:38:0x021d, code skipped:
            return;
     */
    public void setVideoQuality(int r12, boolean r13, boolean r14) {
        /*
        r11 = this;
        r0 = TAG;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "liteav_api setVideoQuality ";
        r1.append(r2);
        r1.append(r12);
        r2 = ", ";
        r1.append(r2);
        r1.append(r13);
        r2 = ", ";
        r1.append(r2);
        r1.append(r14);
        r1 = r1.toString();
        com.tencent.liteav.basic.log.TXCLog.d(r0, r1);
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 3;
        r2 = 18;
        r3 = 2;
        r4 = 1;
        if (r0 >= r2) goto L_0x0034;
    L_0x002f:
        if (r12 == r3) goto L_0x0033;
    L_0x0031:
        if (r12 != r1) goto L_0x0034;
    L_0x0033:
        r12 = 1;
    L_0x0034:
        r0 = r11.mConfig;
        if (r0 != 0) goto L_0x003f;
    L_0x0038:
        r0 = new com.tencent.rtmp.TXLivePushConfig;
        r0.<init>();
        r11.mConfig = r0;
    L_0x003f:
        r0 = r11.mConfig;
        r0.setVideoFPS(r2);
        r0 = 1200; // 0x4b0 float:1.682E-42 double:5.93E-321;
        r5 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        r6 = 1800; // 0x708 float:2.522E-42 double:8.893E-321;
        r7 = 600; // 0x258 float:8.41E-43 double:2.964E-321;
        r8 = 800; // 0x320 float:1.121E-42 double:3.953E-321;
        r9 = 48000; // 0xbb80 float:6.7262E-41 double:2.3715E-319;
        r10 = 0;
        switch(r12) {
            case 1: goto L_0x01d4;
            case 2: goto L_0x01ab;
            case 3: goto L_0x0184;
            case 4: goto L_0x00cc;
            case 5: goto L_0x00a7;
            case 6: goto L_0x0071;
            default: goto L_0x0055;
        };
    L_0x0055:
        r13 = r11.mConfig;
        r13.setHardwareAcceleration(r3);
        r13 = TAG;
        r14 = new java.lang.StringBuilder;
        r14.<init>();
        r0 = "setVideoPushQuality: invalid quality ";
        r14.append(r0);
        r14.append(r12);
        r12 = r14.toString();
        com.tencent.liteav.basic.log.TXCLog.e(r13, r12);
        return;
    L_0x0071:
        r13 = r11.mConfig;
        r13.enableAEC(r4);
        r13 = r11.mConfig;
        r13.setHardwareAcceleration(r4);
        r13 = r11.mConfig;
        r13.setVideoResolution(r10);
        r13 = r11.mConfig;
        r13.setAudioSampleRate(r9);
        r13 = r11.mConfig;
        r13.setAutoAdjustBitrate(r4);
        r13 = r11.mConfig;
        r14 = 5;
        r13.setAutoAdjustStrategy(r14);
        r13 = r11.mConfig;
        r14 = 190; // 0xbe float:2.66E-43 double:9.4E-322;
        r13.setMinVideoBitrate(r14);
        r13 = r11.mConfig;
        r14 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        r13.setVideoBitrate(r14);
        r13 = r11.mConfig;
        r14 = 810; // 0x32a float:1.135E-42 double:4.0E-321;
        r13.setMaxVideoBitrate(r14);
        r13 = 1;
        goto L_0x00c9;
    L_0x00a7:
        r13 = r11.mConfig;
        r13.enableAEC(r4);
        r13 = r11.mConfig;
        r13.setHardwareAcceleration(r4);
        r13 = r11.mConfig;
        r14 = 6;
        r13.setVideoResolution(r14);
        r13 = r11.mConfig;
        r13.setAutoAdjustBitrate(r10);
        r13 = r11.mConfig;
        r14 = 350; // 0x15e float:4.9E-43 double:1.73E-321;
        r13.setVideoBitrate(r14);
        r13 = r11.mConfig;
        r13.setAudioSampleRate(r9);
    L_0x00c8:
        r13 = 0;
    L_0x00c9:
        r10 = 1;
        goto L_0x01fb;
    L_0x00cc:
        r13 = android.os.Build.VERSION.SDK_INT;
        r14 = 4;
        if (r13 >= r2) goto L_0x00fb;
    L_0x00d1:
        r13 = r11.mConfig;
        r13.enableAEC(r4);
        r13 = r11.mConfig;
        r13.setHardwareAcceleration(r10);
        r13 = r11.mConfig;
        r13.setVideoResolution(r10);
        r13 = r11.mConfig;
        r13.setAutoAdjustBitrate(r4);
        r13 = r11.mConfig;
        r13.setAutoAdjustStrategy(r14);
        r13 = r11.mConfig;
        r13.setMinVideoBitrate(r5);
        r13 = r11.mConfig;
        r13.setVideoBitrate(r8);
        r13 = r11.mConfig;
        r13.setMaxVideoBitrate(r8);
        goto L_0x017d;
    L_0x00fb:
        r13 = r11.mVideoQuality;
        if (r13 != r4) goto L_0x0128;
    L_0x00ff:
        r13 = r11.mConfig;
        r13.enableAEC(r4);
        r13 = r11.mConfig;
        r13.setHardwareAcceleration(r4);
        r13 = r11.mConfig;
        r13.setVideoResolution(r10);
        r13 = r11.mConfig;
        r13.setAutoAdjustBitrate(r4);
        r13 = r11.mConfig;
        r13.setAutoAdjustStrategy(r14);
        r13 = r11.mConfig;
        r13.setMinVideoBitrate(r5);
        r13 = r11.mConfig;
        r13.setVideoBitrate(r8);
        r13 = r11.mConfig;
        r13.setMaxVideoBitrate(r8);
        goto L_0x017d;
    L_0x0128:
        r13 = r11.mVideoQuality;
        if (r13 != r1) goto L_0x0155;
    L_0x012c:
        r13 = r11.mConfig;
        r13.enableAEC(r4);
        r13 = r11.mConfig;
        r13.setHardwareAcceleration(r4);
        r13 = r11.mConfig;
        r13.setVideoResolution(r3);
        r13 = r11.mConfig;
        r13.setAutoAdjustBitrate(r4);
        r13 = r11.mConfig;
        r13.setAutoAdjustStrategy(r14);
        r13 = r11.mConfig;
        r13.setMinVideoBitrate(r7);
        r13 = r11.mConfig;
        r13.setVideoBitrate(r6);
        r13 = r11.mConfig;
        r13.setMaxVideoBitrate(r6);
        goto L_0x017d;
    L_0x0155:
        r13 = r11.mConfig;
        r13.enableAEC(r4);
        r13 = r11.mConfig;
        r13.setHardwareAcceleration(r4);
        r13 = r11.mConfig;
        r13.setVideoResolution(r4);
        r13 = r11.mConfig;
        r13.setAutoAdjustBitrate(r4);
        r13 = r11.mConfig;
        r13.setAutoAdjustStrategy(r14);
        r13 = r11.mConfig;
        r13.setMinVideoBitrate(r7);
        r13 = r11.mConfig;
        r13.setVideoBitrate(r0);
        r13 = r11.mConfig;
        r13.setMaxVideoBitrate(r0);
    L_0x017d:
        r13 = r11.mConfig;
        r13.setAudioSampleRate(r9);
        goto L_0x00c8;
    L_0x0184:
        r0 = r11.mConfig;
        r0.enableAEC(r10);
        r0 = r11.mConfig;
        r0.setHardwareAcceleration(r4);
        r0 = r11.mConfig;
        r0.setVideoResolution(r3);
        r0 = r11.mConfig;
        r0.setAudioSampleRate(r9);
        r11.setAdjustStrategy(r13, r14);
        r13 = r11.mConfig;
        r13.setMinVideoBitrate(r7);
        r13 = r11.mConfig;
        r13.setVideoBitrate(r6);
        r13 = r11.mConfig;
        r13.setMaxVideoBitrate(r6);
        goto L_0x01fa;
    L_0x01ab:
        r2 = r11.mConfig;
        r2.enableAEC(r10);
        r2 = r11.mConfig;
        r2.setHardwareAcceleration(r3);
        r2 = r11.mConfig;
        r2.setVideoResolution(r4);
        r2 = r11.mConfig;
        r2.setAudioSampleRate(r9);
        r11.setAdjustStrategy(r13, r14);
        r13 = r11.mConfig;
        r13.setMinVideoBitrate(r7);
        r13 = r11.mConfig;
        r13.setVideoBitrate(r0);
        r13 = r11.mConfig;
        r14 = 1500; // 0x5dc float:2.102E-42 double:7.41E-321;
        r13.setMaxVideoBitrate(r14);
        goto L_0x01fa;
    L_0x01d4:
        r0 = r11.mConfig;
        r0.enableAEC(r10);
        r0 = r11.mConfig;
        r0.setHardwareAcceleration(r3);
        r0 = r11.mConfig;
        r0.setVideoResolution(r10);
        r0 = r11.mConfig;
        r0.setAudioSampleRate(r9);
        r11.setAdjustStrategy(r13, r14);
        r13 = r11.mConfig;
        r13.setMinVideoBitrate(r5);
        r13 = r11.mConfig;
        r13.setVideoBitrate(r8);
        r13 = r11.mConfig;
        r13.setMaxVideoBitrate(r8);
    L_0x01fa:
        r13 = 0;
    L_0x01fb:
        r11.mVideoQuality = r12;
        r12 = r11.mConfig;
        r14 = r10 ^ 1;
        r12.enableVideoHardEncoderMainProfile(r14);
        r12 = r11.mConfig;
        if (r10 == 0) goto L_0x0209;
    L_0x0208:
        r1 = 1;
    L_0x0209:
        r12.setVideoEncodeGop(r1);
        r12 = r11.mNewConfig;
        if (r12 == 0) goto L_0x0218;
    L_0x0210:
        r12 = r11.mNewConfig;
        r12.I = r10;
        r12 = r11.mNewConfig;
        r12.J = r13;
    L_0x0218:
        r12 = r11.mConfig;
        r11.setConfig(r12);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.TXLivePusher.setVideoQuality(int, boolean, boolean):void");
    }

    public void setReverb(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setReverb ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.j(i);
        }
    }

    public void setVoiceChangerType(int i) {
        switch (i) {
            case 1:
                this.mVoiceKind = 6;
                this.mVoiceEnvironment = -1;
                break;
            case 2:
                this.mVoiceKind = 4;
                this.mVoiceEnvironment = -1;
                break;
            case 3:
                this.mVoiceKind = 5;
                this.mVoiceEnvironment = -1;
                break;
            case 4:
                this.mVoiceKind = -1;
                this.mVoiceEnvironment = 9;
                break;
            case 5:
                this.mVoiceKind = 536936433;
                this.mVoiceEnvironment = 50;
                break;
            case 6:
                this.mVoiceKind = -1;
                this.mVoiceEnvironment = 5;
                break;
            case 7:
                this.mVoiceKind = 13;
                this.mVoiceEnvironment = 1;
                break;
            case 8:
                this.mVoiceKind = 13;
                this.mVoiceEnvironment = -1;
                break;
            case 9:
                this.mVoiceKind = 10;
                this.mVoiceEnvironment = 4;
                break;
            case 10:
                this.mVoiceKind = 10;
                this.mVoiceEnvironment = 20;
                break;
            case 11:
                this.mVoiceKind = -1;
                this.mVoiceEnvironment = 2;
                break;
            default:
                this.mVoiceKind = -1;
                this.mVoiceEnvironment = -1;
                break;
        }
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.b(this.mVoiceKind, this.mVoiceEnvironment);
        }
    }

    public void onNotifyEvent(final int i, final Bundle bundle) {
        if (this.mMainHandler != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    if (TXLivePusher.this.mTXCloudVideoView != null) {
                        TXLivePusher.this.mTXCloudVideoView.setLogText(null, bundle, i);
                    }
                }
            });
        }
        transferPushEvent(i, bundle);
    }

    public int onTextureCustomProcess(int i, int i2, int i3) {
        return this.mPreprocessListener != null ? this.mPreprocessListener.onTextureCustomProcess(i, i2, i3) : 0;
    }

    public void onDetectFacePoints(float[] fArr) {
        if (this.mPreprocessListener != null) {
            this.mPreprocessListener.onDetectFacePoints(fArr);
        }
    }

    public void onTextureDestoryed() {
        if (this.mPreprocessListener != null) {
            this.mPreprocessListener.onTextureDestoryed();
        }
    }

    public int onGetEncoderRealBitrate() {
        return TXCStatus.d(this.mID, 4002);
    }

    public int onGetQueueInputSize() {
        int d = TXCStatus.d(this.mID, 7002);
        if (this.mNewConfig.I) {
            return d + TXCStatus.d(this.mID, 7001);
        }
        return d + TXCStatus.d(this.mID, 4002);
    }

    public int onGetQueueOutputSize() {
        if (this.mStreamUploader == null) {
            return 0;
        }
        return TXCStatus.d(this.mID, 7004) + TXCStatus.d(this.mID, 7003);
    }

    public int onGetVideoQueueCurrentCount() {
        return TXCStatus.d(this.mID, 7005);
    }

    public int onGetVideoDropCount() {
        return TXCStatus.d(this.mID, 7007);
    }

    public void onEncoderParamsChanged(int i, int i2, int i3) {
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(i, i2, i3);
        }
        if (!(i2 == 0 || i3 == 0)) {
            this.mNewConfig.a = i2;
            this.mNewConfig.b = i3;
        }
        if (i != 0) {
            this.mNewConfig.c = i;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mode:");
            stringBuilder.append(this.mNewConfig.f);
            stringBuilder.append(" bitrate:");
            stringBuilder.append(i);
            stringBuilder.append(" videosize:");
            stringBuilder.append(this.mNewConfig.a);
            stringBuilder.append(" * ");
            stringBuilder.append(this.mNewConfig.b);
            TXCDRApi.reportEvent40003(this.mPushUrl, com.tencent.liteav.basic.datareport.a.N, "Qos Result", stringBuilder.toString());
        }
    }

    public void onEnableDropStatusChanged(boolean z) {
        if (this.mStreamUploader != null) {
            this.mStreamUploader.setDropEanble(z);
        }
    }

    public void onEncAudio(byte[] bArr, long j, int i, int i2) {
        if (!(this.mStreamUploader == null || bArr == null)) {
            this.mStreamUploader.pushAAC(bArr, j);
        }
        if (this.mIsRecording && this.mMP4Muxer != null && this.mStartMuxer && bArr != null) {
            this.mMP4Muxer.a(bArr, 0, bArr.length, j * 1000, 0);
        }
    }

    public void onEncVideo(com.tencent.liteav.basic.g.b bVar) {
        byte[] bArr;
        if (this.mQos != null) {
            this.mQos.setHasVideo(true);
        }
        if (!(this.mStreamUploader == null || bVar == null || bVar.nalData == null)) {
            synchronized (this) {
                if (!(this.mMsgArray == null || this.mMsgArray.isEmpty())) {
                    Iterator it = this.mMsgArray.iterator();
                    int i = 0;
                    while (true) {
                        int i2 = 10240;
                        if (!it.hasNext()) {
                            break;
                        }
                        a aVar = (a) it.next();
                        if (aVar.a > bVar.pts) {
                            break;
                        }
                        if (aVar.b.length <= 10240) {
                            i2 = aVar.b.length;
                        }
                        i += i2 + 5;
                    }
                    if (i != 0) {
                        bArr = new byte[(i + bVar.nalData.length)];
                        byte[] bArr2 = new byte[5];
                        Iterator it2 = this.mMsgArray.iterator();
                        int i3 = 0;
                        int i4 = 0;
                        while (it2.hasNext()) {
                            a aVar2 = (a) it2.next();
                            if (aVar2.a > bVar.pts) {
                                break;
                            }
                            i3++;
                            int length = aVar2.b.length <= 10240 ? aVar2.b.length : 10240;
                            int i5 = length + 1;
                            bArr2[0] = (byte) ((i5 >> 24) & 255);
                            bArr2[1] = (byte) ((i5 >> 16) & 255);
                            bArr2[2] = (byte) ((i5 >> 8) & 255);
                            bArr2[3] = (byte) (i5 & 255);
                            bArr2[4] = (byte) 6;
                            System.arraycopy(bArr2, 0, bArr, i4, bArr2.length);
                            i4 += bArr2.length;
                            System.arraycopy(aVar2.b, 0, bArr, i4, length);
                            i4 += length;
                        }
                        long j = bVar.pts;
                        for (i = 0; i < i3; i++) {
                            this.mMsgArray.remove(0);
                        }
                        System.arraycopy(bVar.nalData, 0, bArr, i4, bVar.nalData.length);
                        bVar.nalData = bArr;
                    }
                }
            }
            this.mStreamUploader.pushNAL(bVar);
        }
        if (this.mIsRecording && this.mMP4Muxer != null && bVar != null && bVar.nalData != null) {
            bArr = transferAvccToAnnexb(bVar.nalData);
            if (this.mStartMuxer) {
                recordVideoData(bVar, bArr);
            } else if (bVar.nalType == 0) {
                MediaFormat a = b.a(bArr, this.mCaptureAndEnc.b(), this.mCaptureAndEnc.c());
                if (a != null) {
                    this.mMP4Muxer.a(a);
                    this.mMP4Muxer.a();
                    this.mStartMuxer = true;
                    this.mRecordStartTime = 0;
                }
                recordVideoData(bVar, bArr);
            }
        }
    }

    public void onRecordRawPcm(byte[] bArr, long j, int i, int i2, int i3, boolean z) {
        AudioCustomProcessListener audioCustomProcessListener = this.mAudioProcessListener;
        if (audioCustomProcessListener != null) {
            audioCustomProcessListener.onRecordRawPcmData(bArr, j, i, i2, i3, z);
        }
    }

    public void onRecordPcm(byte[] bArr, long j, int i, int i2, int i3) {
        AudioCustomProcessListener audioCustomProcessListener = this.mAudioProcessListener;
        if (audioCustomProcessListener != null) {
            audioCustomProcessListener.onRecordPcmData(bArr, j, i, i2, i3);
        }
    }

    public void onEncVideoFormat(MediaFormat mediaFormat) {
        if (this.mIsRecording && this.mMP4Muxer != null) {
            this.mMP4Muxer.a(mediaFormat);
            if (!this.mStartMuxer) {
                this.mMP4Muxer.a();
                this.mStartMuxer = true;
                this.mRecordStartTime = 0;
            }
        }
    }

    private void callbackRecordFail() {
        this.mMainHandler.post(new Runnable() {
            public void run() {
                TXRecordResult tXRecordResult = new TXRecordResult();
                tXRecordResult.retCode = -1;
                tXRecordResult.descMsg = "record video failed";
                if (TXLivePusher.this.mRecordListener != null) {
                    TXLivePusher.this.mRecordListener.onRecordComplete(tXRecordResult);
                }
                TXCLog.w(TXLivePusher.TAG, "record complete fail");
            }
        });
    }

    private void callbackRecordSuccess(final String str, final String str2) {
        this.mMainHandler.post(new Runnable() {
            public void run() {
                TXRecordResult tXRecordResult = new TXRecordResult();
                tXRecordResult.retCode = 0;
                tXRecordResult.descMsg = "record success";
                tXRecordResult.videoPath = str;
                tXRecordResult.coverPath = str2;
                if (TXLivePusher.this.mRecordListener != null) {
                    TXLivePusher.this.mRecordListener.onRecordComplete(tXRecordResult);
                }
                TXCLog.w(TXLivePusher.TAG, "record complete success");
            }
        });
    }

    @TargetApi(16)
    private void addAudioTrack() {
        MediaFormat a = b.a(TXCAudioUGCRecorder.getInstance().getSampleRate(), TXCAudioUGCRecorder.getInstance().getChannels(), 2);
        if (this.mMP4Muxer != null) {
            this.mMP4Muxer.b(a);
        }
    }

    private void recordVideoData(com.tencent.liteav.basic.g.b bVar, byte[] bArr) {
        if (this.mRecordStartTime == 0) {
            this.mRecordStartTime = bVar.pts;
        }
        final long j = bVar.pts - this.mRecordStartTime;
        int i = bVar.info == null ? bVar.nalType == 0 ? 1 : 0 : bVar.info.flags;
        this.mMP4Muxer.b(bArr, 0, bArr.length, bVar.pts * 1000, i);
        this.mMainHandler.post(new Runnable() {
            public void run() {
                if (TXLivePusher.this.mRecordListener != null) {
                    TXLivePusher.this.mRecordListener.onRecordProgress(j);
                }
            }
        });
    }

    private void postBitmapToMainThread(final ITXSnapshotListener iTXSnapshotListener, final Bitmap bitmap) {
        if (iTXSnapshotListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (iTXSnapshotListener != null) {
                        iTXSnapshotListener.onSnapshot(bitmap);
                    }
                }
            });
        }
    }

    private void setAdjustStrategy(boolean z, boolean z2) {
        int adjustStrategy = getAdjustStrategy(z, z2);
        if (adjustStrategy == -1) {
            this.mConfig.setAutoAdjustBitrate(false);
            this.mConfig.setAutoAdjustStrategy(-1);
            return;
        }
        this.mConfig.setAutoAdjustBitrate(true);
        this.mConfig.setAutoAdjustStrategy(adjustStrategy);
    }

    private void updateId(String str) {
        str = String.format("%s-%d", new Object[]{str, Long.valueOf(TXCTimeUtil.getTimeTick() % 10000)});
        if (this.mStreamUploader != null) {
            this.mStreamUploader.setID(str);
        }
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.setID(str);
        }
        if (this.mDataReport != null) {
            this.mDataReport.d(str);
        }
        this.mID = str;
    }

    private void startStatusNotify() {
        this.mNotifyStatus = true;
        if (this.mMainHandler != null) {
            this.mMainHandler.postDelayed(new Runnable() {
                public void run() {
                    if (TXLivePusher.this.mNotifyStatus) {
                        TXLivePusher.this.statusNotify();
                    }
                }
            }, 2000);
        }
    }

    private void stopStatusNotify() {
        this.mNotifyStatus = false;
    }

    private void statusNotify() {
        int[] a = b.a();
        int i = a[0] / 10;
        int i2 = a[1] / 10;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append("/");
        stringBuilder.append(i2);
        stringBuilder.append("%");
        String stringBuilder2 = stringBuilder.toString();
        i = TXCStatus.d(this.mID, 7004);
        int d = TXCStatus.d(this.mID, 7003);
        int d2 = TXCStatus.d(this.mID, 7002);
        int d3 = TXCStatus.d(this.mID, 7001);
        int d4 = TXCStatus.d(this.mID, 7007);
        int d5 = TXCStatus.d(this.mID, 7005);
        int d6 = TXCStatus.d(this.mID, 7006);
        String c = TXCStatus.c(this.mID, 7012);
        double e = TXCStatus.e(this.mID, 4001);
        int d7 = TXCStatus.d(this.mID, 4003);
        Bundle bundle = new Bundle();
        bundle.putInt(TXLiveConstants.NET_STATUS_NET_SPEED, d + i);
        bundle.putInt(TXLiveConstants.NET_STATUS_VIDEO_FPS, (int) e);
        if (e < 1.0d) {
            e = 15.0d;
        }
        String str = TXLiveConstants.NET_STATUS_VIDEO_GOP;
        e = (double) (((float) ((d7 * 10) / ((int) e))) / 10.0f);
        Double.isNaN(e);
        bundle.putInt(str, (int) (e + 0.5d));
        bundle.putInt(TXLiveConstants.NET_STATUS_DROP_SIZE, d4);
        bundle.putInt(TXLiveConstants.NET_STATUS_VIDEO_BITRATE, d3);
        bundle.putInt(TXLiveConstants.NET_STATUS_AUDIO_BITRATE, d2);
        bundle.putInt(TXLiveConstants.NET_STATUS_CODEC_CACHE, d6);
        bundle.putInt(TXLiveConstants.NET_STATUS_CACHE_SIZE, d5);
        bundle.putCharSequence(TXLiveConstants.NET_STATUS_SERVER_IP, c);
        bundle.putCharSequence(TXLiveConstants.NET_STATUS_CPU_USAGE, stringBuilder2);
        if (this.mCaptureAndEnc != null) {
            bundle.putString(TXLiveConstants.NET_STATUS_AUDIO_INFO, this.mCaptureAndEnc.d());
            bundle.putInt(TXLiveConstants.NET_STATUS_VIDEO_WIDTH, this.mCaptureAndEnc.b());
            bundle.putInt(TXLiveConstants.NET_STATUS_VIDEO_HEIGHT, this.mCaptureAndEnc.c());
        }
        if (this.mTXCloudVideoView != null) {
            this.mTXCloudVideoView.setLogText(bundle, null, 0);
        }
        if (this.mListener != null) {
            this.mListener.onNetStatus(bundle);
        }
        if (this.mDataReport != null) {
            this.mDataReport.e();
        }
        if (this.mMainHandler != null && this.mNotifyStatus) {
            this.mMainHandler.postDelayed(new Runnable() {
                public void run() {
                    if (TXLivePusher.this.mNotifyStatus) {
                        TXLivePusher.this.statusNotify();
                    }
                }
            }, 2000);
        }
    }

    private void startNetworkModule() {
        l lVar = new l();
        lVar.d = com.tencent.liteav.audio.b.a().d();
        lVar.e = com.tencent.liteav.audio.b.a().e();
        lVar.a = 0;
        lVar.c = 20;
        lVar.b = 0;
        lVar.f = 3;
        int i = 1;
        lVar.j = true;
        lVar.l = true;
        lVar.k = false;
        lVar.h = 40;
        lVar.i = 5000;
        lVar.m = this.mNewConfig.I;
        lVar.n = this.mNewConfig.J;
        lVar.o = getQuicMode(this.mVideoQuality);
        this.mStreamUploader = new TXCStreamUploader(this.mContext, lVar);
        this.mStreamUploader.setID(this.mID);
        if ((this.mNewConfig.K & 1) != 0) {
            if (this.mStreamUploader != null) {
                this.mStreamUploader.setAudioInfo(this.mNewConfig.q, this.mNewConfig.r);
            }
        } else if (this.mStreamUploader != null) {
            this.mStreamUploader.setAudioInfo(this.mNewConfig.q, 1);
        }
        this.mStreamUploader.setNotifyListener(this);
        if (this.mConfig.mEnablePureAudioPush && this.mCaptureAndEnc != null) {
            this.mStreamUploader.setAudioMute(this.mCaptureAndEnc.m());
        }
        this.mPushUrl = this.mStreamUploader.start(this.mPushUrl, this.mNewConfig.G, this.mNewConfig.H);
        if (this.mNewConfig.F) {
            this.mStreamUploader.setMode(1);
        }
        if (this.mNewConfig.I) {
            int i2 = this.mNewConfig.o;
            int i3 = this.mNewConfig.p;
            if (i2 < 5) {
                i2 = 5;
            }
            if (i3 <= 1) {
                i = i3;
            }
            this.mStreamUploader.setRetryInterval(i);
            this.mStreamUploader.setRetryTimes(i2);
            this.mStreamUploader.setVideoDropParams(false, this.mNewConfig.h, 1000);
        } else {
            this.mStreamUploader.setRetryInterval(this.mNewConfig.p);
            this.mStreamUploader.setRetryTimes(this.mNewConfig.o);
            this.mStreamUploader.setVideoDropParams(true, 40, 3000);
        }
        this.mStreamUploader.setSendStrategy(this.mNewConfig.I, this.mNewConfig.J);
    }

    private void stopNetworkModule() {
        if (this.mStreamUploader != null) {
            this.mStreamUploader.stop();
            this.mStreamUploader.setNotifyListener(null);
            this.mStreamUploader = null;
        }
    }

    private void startQosModule() {
        this.mQos = new TXCQoS(true);
        this.mQos.setListener(this);
        this.mQos.setNotifyListener(this);
        this.mQos.setAutoAdjustBitrate(this.mNewConfig.g);
        this.mQos.setAutoAdjustStrategy(this.mNewConfig.f);
        this.mQos.setDefaultVideoResolution(this.mNewConfig.k);
        this.mQos.setVideoEncBitrate(this.mNewConfig.e, this.mNewConfig.d, this.mNewConfig.c);
        if (this.mNewConfig.g) {
            this.mQos.start(2000);
        }
    }

    private void stopQosModule() {
        if (this.mQos != null) {
            this.mQos.stop();
            this.mQos.setListener(null);
            this.mQos.setNotifyListener(null);
            this.mQos = null;
        }
    }

    private void startDataReportModule() {
        this.mDataReport = new d(this.mContext);
        this.mDataReport.d(this.mID);
        this.mDataReport.a(this.mNewConfig.c);
        this.mDataReport.b(this.mNewConfig.q);
        this.mDataReport.a(this.mNewConfig.a, this.mNewConfig.b);
        this.mDataReport.a(this.mPushUrl);
        this.mDataReport.a();
    }

    private void stopDataReportModule() {
        if (this.mDataReport != null) {
            this.mDataReport.b();
            this.mDataReport = null;
        }
    }

    private void startEncoder() {
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.setID(this.mID);
            this.mCaptureAndEnc.a((com.tencent.liteav.c.a) this);
            this.mCaptureAndEnc.b(this.mVoiceKind, this.mVoiceEnvironment);
            this.mCaptureAndEnc.b(this.mBgmPitch);
            this.mCaptureAndEnc.e();
        }
    }

    private void stopEncoder() {
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(null);
            this.mCaptureAndEnc.f();
            this.mCaptureAndEnc.a(null);
        }
    }

    private void transferConfig(TXLivePushConfig tXLivePushConfig) {
        h hVar = this.mNewConfig;
        hVar.c = tXLivePushConfig.mVideoBitrate;
        hVar.e = tXLivePushConfig.mMinVideoBitrate;
        hVar.d = tXLivePushConfig.mMaxVideoBitrate;
        hVar.f = tXLivePushConfig.mAutoAdjustStrategy;
        hVar.g = tXLivePushConfig.mAutoAdjustBitrate;
        hVar.h = tXLivePushConfig.mVideoFPS;
        hVar.i = tXLivePushConfig.mVideoEncodeGop;
        hVar.j = tXLivePushConfig.mHardwareAccel;
        hVar.k = tXLivePushConfig.mVideoResolution;
        hVar.n = tXLivePushConfig.mEnableVideoHardEncoderMainProfile;
        hVar.q = tXLivePushConfig.mAudioSample;
        hVar.r = tXLivePushConfig.mAudioChannels;
        hVar.s = tXLivePushConfig.mEnableAec;
        hVar.w = tXLivePushConfig.mPauseFlag;
        hVar.v = tXLivePushConfig.mPauseFps;
        hVar.t = tXLivePushConfig.mPauseImg;
        hVar.u = tXLivePushConfig.mPauseTime;
        hVar.F = tXLivePushConfig.mEnablePureAudioPush;
        hVar.D = tXLivePushConfig.mTouchFocus;
        hVar.E = tXLivePushConfig.mEnableZoom;
        hVar.x = tXLivePushConfig.mWatermark;
        hVar.y = tXLivePushConfig.mWatermarkX;
        hVar.z = tXLivePushConfig.mWatermarkY;
        hVar.A = tXLivePushConfig.mWatermarkXF;
        hVar.B = tXLivePushConfig.mWatermarkYF;
        hVar.C = tXLivePushConfig.mWatermarkWidth;
        hVar.l = tXLivePushConfig.mHomeOrientation;
        hVar.G = tXLivePushConfig.mEnableNearestIP;
        hVar.H = tXLivePushConfig.mRtmpChannelType;
        hVar.o = tXLivePushConfig.mConnectRetryCount;
        hVar.p = tXLivePushConfig.mConnectRetryInterval;
        hVar.m = tXLivePushConfig.mFrontCamera;
        hVar.K = tXLivePushConfig.mCustomModeType;
        hVar.L = tXLivePushConfig.mVideoEncoderXMirror;
        hVar.M = tXLivePushConfig.mEnableHighResolutionCapture;
        hVar.N = tXLivePushConfig.mEnableScreenCaptureAutoRotate;
        hVar.a();
    }

    private void applyConfig() {
        if (this.mCaptureAndEnc != null) {
            this.mCaptureAndEnc.a(this.mNewConfig);
            int i = 1;
            if (this.mCaptureAndEnc.i()) {
                if (this.mStreamUploader != null) {
                    if (this.mNewConfig.I) {
                        int i2 = this.mNewConfig.o;
                        int i3 = this.mNewConfig.p;
                        if (i2 < 5) {
                            i2 = 5;
                        }
                        if (i3 <= 1) {
                            i = i3;
                        }
                        this.mStreamUploader.setRetryInterval(i);
                        this.mStreamUploader.setRetryTimes(i2);
                        this.mStreamUploader.setVideoDropParams(false, this.mNewConfig.h, 1000);
                    } else {
                        this.mStreamUploader.setRetryInterval(this.mNewConfig.p);
                        this.mStreamUploader.setRetryTimes(this.mNewConfig.o);
                        this.mStreamUploader.setVideoDropParams(true, 40, 3000);
                    }
                    this.mStreamUploader.setSendStrategy(this.mNewConfig.I, this.mNewConfig.J);
                }
                if (this.mQos != null) {
                    this.mQos.stop();
                    this.mQos.setAutoAdjustBitrate(this.mNewConfig.g);
                    this.mQos.setAutoAdjustStrategy(this.mNewConfig.f);
                    this.mQos.setDefaultVideoResolution(this.mNewConfig.k);
                    this.mQos.setVideoEncBitrate(this.mNewConfig.e, this.mNewConfig.d, this.mNewConfig.c);
                    if (this.mNewConfig.g) {
                        this.mQos.start(2000);
                    }
                }
            } else if ((this.mNewConfig.K & 1) != 0) {
                if (this.mStreamUploader != null) {
                    this.mStreamUploader.setAudioInfo(this.mNewConfig.q, this.mNewConfig.r);
                }
            } else if (this.mStreamUploader != null) {
                this.mStreamUploader.setAudioInfo(this.mNewConfig.q, 1);
            }
        }
    }

    private void transferPushEvent(int i, final Bundle bundle) {
        int i2 = TXLiveConstants.PUSH_WARNING_VIDEO_ENCODE_SW_SWITCH_HW;
        if (i == TXLiveConstants.PUSH_ERR_INVALID_ADDRESS) {
            i2 = TXLiveConstants.PUSH_ERR_INVALID_ADDRESS;
        } else if (i != TXLiveConstants.PUSH_WARNING_VIDEO_ENCODE_SW_SWITCH_HW) {
            switch (i) {
                case TXLiveConstants.PUSH_ERR_SCREEN_CAPTURE_UNSURPORT /*-1309*/:
                    i2 = TXLiveConstants.PUSH_ERR_SCREEN_CAPTURE_UNSURPORT;
                    break;
                case TXLiveConstants.PUSH_ERR_SCREEN_CAPTURE_START_FAILED /*-1308*/:
                    i2 = TXLiveConstants.PUSH_ERR_SCREEN_CAPTURE_START_FAILED;
                    break;
                case TXLiveConstants.PUSH_ERR_NET_DISCONNECT /*-1307*/:
                    i2 = TXLiveConstants.PUSH_ERR_NET_DISCONNECT;
                    break;
                default:
                    switch (i) {
                        case TXLiveConstants.PUSH_ERR_VIDEO_ENCODE_FAIL /*-1303*/:
                            i2 = TXLiveConstants.PUSH_ERR_VIDEO_ENCODE_FAIL;
                            break;
                        case TXLiveConstants.PUSH_ERR_OPEN_MIC_FAIL /*-1302*/:
                            i2 = TXLiveConstants.PUSH_ERR_OPEN_MIC_FAIL;
                            break;
                        case TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL /*-1301*/:
                            i2 = TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL;
                            break;
                        default:
                            switch (i) {
                                case 1001:
                                    i2 = 1001;
                                    break;
                                case TXLiveConstants.PUSH_EVT_PUSH_BEGIN /*1002*/:
                                    i2 = TXLiveConstants.PUSH_EVT_PUSH_BEGIN;
                                    break;
                                case 1003:
                                    i2 = 1003;
                                    break;
                                case 1004:
                                    i2 = 1004;
                                    break;
                                case TXLiveConstants.PUSH_EVT_CHANGE_RESOLUTION /*1005*/:
                                    i2 = TXLiveConstants.PUSH_EVT_CHANGE_RESOLUTION;
                                    break;
                                case TXLiveConstants.PUSH_EVT_CHANGE_BITRATE /*1006*/:
                                    i2 = TXLiveConstants.PUSH_EVT_CHANGE_BITRATE;
                                    break;
                                case TXLiveConstants.PUSH_EVT_FIRST_FRAME_AVAILABLE /*1007*/:
                                    i2 = TXLiveConstants.PUSH_EVT_FIRST_FRAME_AVAILABLE;
                                    break;
                                case TXLiveConstants.PUSH_EVT_START_VIDEO_ENCODER /*1008*/:
                                    i2 = TXLiveConstants.PUSH_EVT_START_VIDEO_ENCODER;
                                    break;
                                default:
                                    switch (i) {
                                        case TXLiveConstants.PUSH_EVT_ROOM_IN /*1018*/:
                                            i2 = TXLiveConstants.PUSH_EVT_ROOM_IN;
                                            break;
                                        case TXLiveConstants.PUSH_EVT_ROOM_OUT /*1019*/:
                                            i2 = TXLiveConstants.PUSH_EVT_ROOM_OUT;
                                            break;
                                        case TXLiveConstants.PUSH_EVT_ROOM_USERLIST /*1020*/:
                                            i2 = TXLiveConstants.PUSH_EVT_ROOM_USERLIST;
                                            break;
                                        case TXLiveConstants.PUSH_EVT_ROOM_NEED_REENTER /*1021*/:
                                            i2 = TXLiveConstants.PUSH_EVT_ROOM_NEED_REENTER;
                                            break;
                                        default:
                                            switch (i) {
                                                case TXLiveConstants.PUSH_WARNING_NET_BUSY /*1101*/:
                                                    i2 = TXLiveConstants.PUSH_WARNING_NET_BUSY;
                                                    break;
                                                case TXLiveConstants.PUSH_WARNING_RECONNECT /*1102*/:
                                                    i2 = TXLiveConstants.PUSH_WARNING_RECONNECT;
                                                    break;
                                                case TXLiveConstants.PUSH_WARNING_HW_ACCELERATION_FAIL /*1103*/:
                                                    i2 = TXLiveConstants.PUSH_WARNING_HW_ACCELERATION_FAIL;
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 3002:
                                                            i2 = 3002;
                                                            break;
                                                        case 3003:
                                                            i2 = 3003;
                                                            break;
                                                        case TXLiveConstants.PUSH_WARNING_SERVER_DISCONNECT /*3004*/:
                                                            i2 = TXLiveConstants.PUSH_WARNING_SERVER_DISCONNECT;
                                                            break;
                                                        case 3005:
                                                            i2 = 3005;
                                                            break;
                                                        default:
                                                            String str = TAG;
                                                            StringBuilder stringBuilder = new StringBuilder();
                                                            stringBuilder.append("unhandled event : ");
                                                            stringBuilder.append(i);
                                                            TXCLog.w(str, stringBuilder.toString());
                                                            return;
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        if (this.mMainHandler != null) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    if (TXLivePusher.this.mListener != null) {
                        TXLivePusher.this.mListener.onPushEvent(i2, bundle);
                    }
                }
            });
        }
    }

    private byte[] transferAvccToAnnexb(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        int i = 0;
        while (true) {
            int i2 = i + 4;
            if (i2 >= length) {
                return bArr2;
            }
            int i3 = ByteBuffer.wrap(bArr, i, 4).getInt();
            if (i2 + i3 <= length) {
                bArr2[i] = (byte) 0;
                bArr2[i + 1] = (byte) 0;
                bArr2[i + 2] = (byte) 0;
                bArr2[i + 3] = (byte) 1;
            }
            i = (i + i3) + 4;
        }
    }

    private byte[] add_emulation_prevention_three_byte(byte[] bArr) {
        int length = ((bArr.length * 4) / 3) + 2;
        byte[] bArr2 = new byte[length];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length && i2 < length) {
            if (i + 3 < bArr.length && bArr[i] == (byte) 0) {
                int i3 = i + 1;
                if (bArr[i3] == (byte) 0) {
                    int i4 = i + 2;
                    if (bArr[i4] >= (byte) 0 && bArr[i4] <= (byte) 3) {
                        i4 = i2 + 1;
                        bArr2[i2] = bArr[i];
                        i = i4 + 1;
                        i2 = i3 + 1;
                        bArr2[i4] = bArr[i3];
                        i3 = i + 1;
                        bArr2[i] = (byte) 3;
                        i = i2;
                        i2 = i3;
                    }
                }
            }
            bArr2[i2] = bArr[i];
            i++;
            i2++;
        }
        bArr = new byte[i2];
        System.arraycopy(bArr2, 0, bArr, 0, i2);
        return bArr;
    }

    @Deprecated
    public void sendMessage(byte[] bArr) {
        synchronized (this) {
            if (this.mMsgArray != null) {
                a aVar = new a(this, null);
                aVar.a = TXCTimeUtil.getTimeTick();
                aVar.b = add_emulation_prevention_three_byte(bArr);
                this.mMsgArray.add(aVar);
            }
        }
    }

    public boolean sendMessageEx(byte[] bArr) {
        if (bArr.length <= 0 || bArr.length > IjkMediaMeta.FF_PROFILE_H264_INTRA) {
            return false;
        }
        synchronized (this) {
            if (this.mMsgArray != null) {
                a aVar = new a(this, null);
                aVar.a = TXCTimeUtil.getTimeTick();
                aVar.b = appendSEIBuffer(bArr.length, add_emulation_prevention_three_byte(bArr));
                this.mMsgArray.add(aVar);
            }
        }
        return true;
    }

    private byte[] appendSEIBuffer(int i, byte[] bArr) {
        byte[] intToBytes = intToBytes(i);
        byte[] bArr2 = new byte[(((intToBytes.length + 1) + bArr.length) + 1)];
        bArr2[0] = SEI_MSG_TYPE;
        System.arraycopy(intToBytes, 0, bArr2, 1, intToBytes.length);
        int length = 1 + intToBytes.length;
        System.arraycopy(bArr, 0, bArr2, length, bArr.length);
        bArr2[length + bArr.length] = Byte.MIN_VALUE;
        return bArr2;
    }

    private byte[] intToBytes(int i) {
        int i2 = (i / 255) + 1;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i3 < i4) {
                bArr[i3] = (byte) -1;
                i3++;
            } else {
                bArr[i4] = (byte) ((i % 255) & 255);
                return bArr;
            }
        }
    }

    private int getQuicMode(int i) {
        switch (i) {
            case 1:
                return (int) com.tencent.liteav.basic.f.b.a().a("QUICMode", "Live");
            case 2:
                return (int) com.tencent.liteav.basic.f.b.a().a("QUICMode", "Live");
            case 3:
                return (int) com.tencent.liteav.basic.f.b.a().a("QUICMode", "Live");
            case 4:
                return (int) com.tencent.liteav.basic.f.b.a().a("QUICMode", "LinkMain");
            case 5:
                return (int) com.tencent.liteav.basic.f.b.a().a("QUICMode", "LinkSub");
            case 6:
                return (int) com.tencent.liteav.basic.f.b.a().a("QUICMode", "RTC");
            default:
                return 0;
        }
    }
}
