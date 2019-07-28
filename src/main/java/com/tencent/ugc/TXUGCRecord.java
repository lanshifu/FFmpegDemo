package com.tencent.ugc;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.AudioRecord;
import android.media.MediaFormat;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.liteav.audio.TXCAudioUGCRecorder;
import com.tencent.liteav.audio.TXCUGCBGMPlayer;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.d;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.e.m;
import com.tencent.liteav.basic.e.n;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.beauty.e;
import com.tencent.liteav.j;
import com.tencent.liteav.muxer.c;
import com.tencent.liteav.renderer.TXCGLSurfaceView;
import com.tencent.liteav.videoediter.ffmpeg.b;
import com.tencent.liteav.videoencoder.TXSVideoEncoderParam;
import com.tencent.rtmp.TXLog;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.ugc.TXRecordCommon.ITXBGMNotify;
import com.tencent.ugc.TXRecordCommon.ITXSnapshotListener;
import com.tencent.ugc.TXRecordCommon.ITXVideoRecordListener;
import com.tencent.ugc.TXRecordCommon.TXRecordResult;
import com.tencent.ugc.TXRecordCommon.TXUGCCustomConfig;
import com.tencent.ugc.TXRecordCommon.TXUGCSimpleConfig;
import com.tencent.ugc.TXUGCPartsManager.IPartsManagerListener;
import com.tencent.ugc.TXVideoEditConstants.TXRect;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

public class TXUGCRecord implements d, com.tencent.liteav.basic.d.a, m, e, com.tencent.liteav.videoencoder.d, IPartsManagerListener {
    private static final int DEFAULT_CHANNEL = 1;
    public static float ENCODE_SPEED_FAST = 1.25f;
    public static float ENCODE_SPEED_FASTEST = 2.0f;
    public static float ENCODE_SPEED_SLOW = 0.8f;
    public static float ENCODE_SPEED_SLOWEST = 0.5f;
    private static final String OUTPUT_DIR_NAME = "TXUGC";
    private static final String OUTPUT_TEMP_DIR_NAME = "TXUGCParts";
    private static final String OUTPUT_VIDEO_COVER_NAME = "TXUGCCover.jpg";
    private static final String OUTPUT_VIDEO_NAME = "TXUGCRecord.mp4";
    public static float PLAY_SPEED_FAST = 0.8f;
    public static float PLAY_SPEED_FASTEST = 0.5f;
    public static float PLAY_SPEED_SLOW = 1.25f;
    public static float PLAY_SPEED_SLOWEST = 2.0f;
    private static final int STATE_NO_PERMISSION = -1;
    private static final int STATE_RECORDING = 1;
    private static final int STATE_RECORD_INIT = 1;
    private static final int STATE_RECORD_PAUSE = 3;
    private static final int STATE_RECORD_RECORDING = 2;
    private static final int STATE_SUCCESS = 0;
    private static final String TAG = "TXUGCRecord";
    private static TXUGCRecord instance;
    private boolean isReachedMaxDuration = false;
    private boolean mBGMDeletePart = false;
    private long mBGMEndTime;
    private ITXBGMNotify mBGMNotify = null;
    private com.tencent.liteav.audio.e mBGMNotifyProxy = null;
    private String mBGMPath;
    private boolean mBGMPlayStop;
    private long mBGMStartTime;
    private CopyOnWriteArrayList<Long> mBgmPartBytesList;
    private com.tencent.liteav.capturer.a mCameraCapture = null;
    private int mCameraOrientationReadyChange = -1;
    private int mCameraResolution = 5;
    private boolean mCapturing = false;
    private j mConfig = new j();
    private Context mContext;
    private String mCoverCurTempPath = null;
    private String mCoverPath = null;
    private int mCropHeight;
    private int mCropWidth;
    private long mCurrentRecordDuration;
    private VideoCustomProcessListener mCustomProcessListener;
    private int mDisplayType;
    private int mFps = 20;
    private int mGop = 3;
    private boolean mInitCompelete = false;
    private c mMP4Muxer = null;
    private Handler mMainHandler;
    private int mMaxDuration;
    private int mMinDuration;
    private int mRecordRetCode;
    private int mRecordSpeed = 2;
    private long mRecordStartTime = 0;
    private volatile int mRecordState = 1;
    private boolean mRecording = false;
    private int mRenderMode = 0;
    private int mRenderRotationReadyChange = -1;
    private String mSaveDir = null;
    private boolean mSmartLicenseSupport = true;
    private boolean mSnapshotRunning = false;
    private float mSpecialRadio = 0.5f;
    private boolean mStartMuxer = false;
    private boolean mStartPreview = false;
    private TXCloudVideoView mTXCloudVideoView;
    private b mTXFFQuickJoiner;
    private com.tencent.liteav.videoediter.a.c mTXMultiMediaComposer = null;
    private TXUGCPartsManager mTXUGCPartsManager;
    private a mTouchFocusRunnable = new a(this, null);
    private TXRect mTxWaterMarkRect;
    private boolean mUseSWEncoder = false;
    private com.tencent.liteav.videoencoder.b mVideoEncoder = null;
    private String mVideoFileCurTempPath = null;
    private String mVideoFilePath = null;
    private String mVideoFileTempDir = null;
    private int mVideoHeight = 0;
    private com.tencent.liteav.beauty.d mVideoPreprocessor = null;
    private ITXVideoRecordListener mVideoRecordListener;
    private TXCGLSurfaceView mVideoView;
    private int mVideoWidth = 0;
    private int mVoiceEnvironment = -1;
    private int mVoiceKind = -1;
    private Bitmap mWatermarkBitmap;
    private boolean needCompose = false;

    public interface VideoCustomProcessListener {
        void onDetectFacePoints(float[] fArr);

        int onTextureCustomProcess(int i, int i2, int i3);

        void onTextureDestroyed();
    }

    private class a implements Runnable {
        private float b;
        private float c;

        private a() {
        }

        /* synthetic */ a(TXUGCRecord tXUGCRecord, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void a(float f, float f2) {
            this.b = f;
            this.c = f2;
        }

        public void run() {
            if (TXUGCRecord.this.mTXCloudVideoView != null) {
                if (TXUGCRecord.this.mCameraCapture != null && TXUGCRecord.this.mConfig.f) {
                    TXUGCRecord.this.mCameraCapture.a(this.b / ((float) TXUGCRecord.this.mTXCloudVideoView.getWidth()), this.c / ((float) TXUGCRecord.this.mTXCloudVideoView.getHeight()));
                }
                if (TXUGCRecord.this.mConfig.f) {
                    TXUGCRecord.this.mTXCloudVideoView.onTouchFocus((int) this.b, (int) this.c);
                }
            }
        }
    }

    public void didProcessFrame(byte[] bArr, int i, int i2, int i3, long j) {
    }

    public void onNotifyEvent(int i, Bundle bundle) {
    }

    public void onRecordPcmData(byte[] bArr, long j, int i, int i2, int i3) {
    }

    public void onRecordRawPcmData(byte[] bArr, long j, int i, int i2, int i3, boolean z) {
    }

    public static synchronized TXUGCRecord getInstance(Context context) {
        TXUGCRecord tXUGCRecord;
        synchronized (TXUGCRecord.class) {
            if (instance == null) {
                instance = new TXUGCRecord(context);
            }
            tXUGCRecord = instance;
        }
        return tXUGCRecord;
    }

    protected TXUGCRecord(Context context) {
        TXCLog.init();
        if (context != null) {
            this.mContext = context.getApplicationContext();
            this.mMainHandler = new Handler(this.mContext.getMainLooper());
            this.mTXUGCPartsManager = new TXUGCPartsManager();
            this.mBgmPartBytesList = new CopyOnWriteArrayList();
            com.tencent.liteav.basic.c.e.a().a(null, this.mContext);
        }
        this.mUseSWEncoder = com.tencent.liteav.basic.util.b.g();
        initFileAndFolder();
    }

    private void initFileAndFolder() {
        this.mSaveDir = getDefaultDir();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mSaveDir);
        stringBuilder.append(File.separator);
        stringBuilder.append(OUTPUT_VIDEO_NAME);
        this.mVideoFilePath = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.mSaveDir);
        stringBuilder.append(File.separator);
        stringBuilder.append(OUTPUT_VIDEO_COVER_NAME);
        this.mCoverPath = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.mSaveDir);
        stringBuilder.append(File.separator);
        stringBuilder.append(OUTPUT_TEMP_DIR_NAME);
        this.mVideoFileTempDir = stringBuilder.toString();
        File file = new File(this.mVideoFileTempDir);
        if (!file.exists()) {
            file.mkdir();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.mVideoFileTempDir);
        stringBuilder.append(File.separator);
        stringBuilder.append(String.format("temp_TXUGC_%s.mp4", new Object[]{getTimeString()}));
        this.mVideoFileCurTempPath = stringBuilder.toString();
        file = new File(this.mVideoFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public void setVideoRecordListener(ITXVideoRecordListener iTXVideoRecordListener) {
        this.mVideoRecordListener = iTXVideoRecordListener;
    }

    public void onDeleteLastPart() {
        if (this.mBgmPartBytesList.size() != 0) {
            this.mBgmPartBytesList.remove(this.mBgmPartBytesList.size() - 1);
            this.mBGMDeletePart = true;
        }
    }

    public void onDeleteAllParts() {
        this.mBgmPartBytesList.clear();
        this.mBGMDeletePart = false;
    }

    public int startCameraSimplePreview(TXUGCSimpleConfig tXUGCSimpleConfig, TXCloudVideoView tXCloudVideoView) {
        if (tXCloudVideoView == null || tXUGCSimpleConfig == null) {
            TXCLog.e(TAG, "startCameraPreview: invalid param");
            return -1;
        }
        this.mConfig.u = tXUGCSimpleConfig.needEdit;
        this.mConfig.a = tXUGCSimpleConfig.videoQuality;
        this.mConfig.o = tXUGCSimpleConfig.isFront;
        this.mConfig.f = tXUGCSimpleConfig.touchFocus;
        this.mMinDuration = tXUGCSimpleConfig.minDuration;
        this.mMaxDuration = tXUGCSimpleConfig.maxDuration;
        startCameraPreviewInternal(tXCloudVideoView, this.mConfig);
        return 0;
    }

    public int startCameraCustomPreview(TXUGCCustomConfig tXUGCCustomConfig, TXCloudVideoView tXCloudVideoView) {
        this.mConfig.u = tXUGCCustomConfig.needEdit;
        if (tXCloudVideoView == null || tXUGCCustomConfig == null) {
            TXCLog.e(TAG, "startCameraPreview: invalid param");
            return -1;
        }
        this.mConfig.a = -1;
        if (tXUGCCustomConfig.videoBitrate < IjkMediaCodecInfo.RANK_LAST_CHANCE) {
            tXUGCCustomConfig.videoBitrate = IjkMediaCodecInfo.RANK_LAST_CHANCE;
        }
        if (tXUGCCustomConfig.needEdit) {
            this.mConfig.d = 10000;
        } else {
            this.mConfig.d = tXUGCCustomConfig.videoBitrate;
        }
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bi, this.mConfig.d, "");
        if (tXUGCCustomConfig.videoFps < 15) {
            tXUGCCustomConfig.videoFps = 15;
        } else if (tXUGCCustomConfig.videoFps > 30) {
            tXUGCCustomConfig.videoFps = 30;
        }
        this.mConfig.c = tXUGCCustomConfig.videoFps;
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bj, this.mConfig.c, "");
        if (tXUGCCustomConfig.videoGop < 1) {
            tXUGCCustomConfig.videoGop = 1;
        } else if (tXUGCCustomConfig.videoGop > 10) {
            tXUGCCustomConfig.videoGop = 10;
        }
        if (tXUGCCustomConfig.needEdit) {
            this.mConfig.e = 0;
        } else {
            this.mConfig.e = tXUGCCustomConfig.videoGop;
        }
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bk, this.mConfig.e, "");
        if (tXUGCCustomConfig.audioSampleRate == TXRecordCommon.AUDIO_SAMPLERATE_8000 || tXUGCCustomConfig.audioSampleRate == TXRecordCommon.AUDIO_SAMPLERATE_16000 || tXUGCCustomConfig.audioSampleRate == TXRecordCommon.AUDIO_SAMPLERATE_32000 || tXUGCCustomConfig.audioSampleRate == TXRecordCommon.AUDIO_SAMPLERATE_44100 || tXUGCCustomConfig.audioSampleRate == TXRecordCommon.AUDIO_SAMPLERATE_48000) {
            this.mConfig.t = tXUGCCustomConfig.audioSampleRate;
        } else {
            this.mConfig.t = TXRecordCommon.AUDIO_SAMPLERATE_48000;
        }
        this.mConfig.b = tXUGCCustomConfig.videoResolution;
        this.mConfig.o = tXUGCCustomConfig.isFront;
        this.mConfig.f = tXUGCCustomConfig.touchFocus;
        this.mConfig.q = tXUGCCustomConfig.enableHighResolutionCapture;
        this.mMinDuration = tXUGCCustomConfig.minDuration;
        this.mMaxDuration = tXUGCCustomConfig.maxDuration;
        this.mConfig.u = tXUGCCustomConfig.needEdit;
        startCameraPreviewInternal(tXCloudVideoView, this.mConfig);
        return 0;
    }

    public void setVideoResolution(int i) {
        if (this.mRecordState != 1) {
            TXCLog.e(TAG, "setVideoResolution err, state not init");
        } else if (this.mTXCloudVideoView == null) {
            TXCLog.e(TAG, "setVideoResolution, mTXCloudVideoView is null");
        } else if (this.mConfig.b == i) {
            TXCLog.i(TAG, "setVideoResolution, resolution not change");
        } else {
            this.mConfig.a = -1;
            this.mConfig.b = i;
            stopCameraPreview();
            startCameraPreviewInternal(this.mTXCloudVideoView, this.mConfig);
        }
    }

    public void setVideoBitrate(int i) {
        if (this.mRecordState != 1) {
            TXCLog.e(TAG, "setVideoBitrate err, state not init");
            return;
        }
        this.mConfig.a = -1;
        this.mConfig.d = i;
    }

    public void stopCameraPreview() {
        try {
            TXCLog.i(TAG, "ugcRecord, stopCameraPreview");
            synchronized (this) {
                this.mCapturing = false;
                if (this.mCameraCapture != null) {
                    this.mCameraCapture.b();
                }
            }
            if (this.mVideoView != null) {
                this.mVideoView.b(new Runnable() {
                    public void run() {
                        if (TXUGCRecord.this.mVideoPreprocessor != null) {
                            TXUGCRecord.this.mVideoPreprocessor.a();
                        }
                    }
                });
                this.mVideoView.b(false);
                this.mVideoView = null;
            }
            if (this.mCustomProcessListener != null) {
                this.mCustomProcessListener.onTextureDestroyed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TXUGCPartsManager getPartsManager() {
        return this.mTXUGCPartsManager;
    }

    public void setMute(boolean z) {
        TXCAudioUGCRecorder.getInstance().setMute(z);
    }

    public int startRecord() {
        if (VERSION.SDK_INT < 18) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("API level is too low (record need 18, current is ");
            stringBuilder.append(VERSION.SDK_INT);
            stringBuilder.append(")");
            TXCLog.e(str, stringBuilder.toString());
            return -3;
        } else if (this.mRecording) {
            TXCLog.e(TAG, "startRecord: there is existing uncompleted record task");
            return -1;
        } else {
            try {
                TXCDRApi.initCrashReport(this.mContext);
                this.mCoverCurTempPath = this.mCoverPath;
                File file = new File(this.mCoverPath);
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return startRecordInternal();
        }
    }

    public int startRecord(String str, String str2) {
        if (VERSION.SDK_INT < 18) {
            str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("API level is too low (record need 18, current is ");
            stringBuilder.append(VERSION.SDK_INT);
            stringBuilder.append(")");
            TXCLog.e(str, stringBuilder.toString());
            return -3;
        } else if (this.mRecording) {
            TXCLog.e(TAG, "startRecord: there is existing uncompleted record task");
            return -1;
        } else if (TextUtils.isEmpty(str)) {
            TXCLog.e(TAG, "startRecord: init videoRecord failed, videoFilePath is empty");
            return -2;
        } else {
            this.mVideoFilePath = str;
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            str = getDefaultDir();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(File.separator);
            stringBuilder2.append(OUTPUT_TEMP_DIR_NAME);
            this.mVideoFileTempDir = stringBuilder2.toString();
            File file2 = new File(this.mVideoFileTempDir);
            if (!file2.exists()) {
                file2.mkdir();
            }
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(this.mVideoFileTempDir);
            stringBuilder3.append(File.separator);
            stringBuilder3.append(String.format("temp_TXUGC_%s.mp4", new Object[]{getTimeString()}));
            this.mVideoFileCurTempPath = stringBuilder3.toString();
            this.mCoverPath = str2;
            this.mCoverCurTempPath = str2;
            return startRecordInternal();
        }
    }

    public int startRecord(String str, String str2, String str3) {
        StringBuilder stringBuilder;
        if (VERSION.SDK_INT < 18) {
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("API level is too low (record need 18, current is ");
            stringBuilder.append(VERSION.SDK_INT);
            stringBuilder.append(")");
            TXCLog.e(str, stringBuilder.toString());
            return -3;
        } else if (this.mRecording) {
            TXCLog.e(TAG, "startRecord: there is existing uncompleted record task");
            return -1;
        } else if (TextUtils.isEmpty(str)) {
            TXCLog.e(TAG, "startRecord: init videoRecord failed, videoFilePath is empty");
            return -2;
        } else {
            this.mVideoFilePath = str;
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            if (TextUtils.isEmpty(str2)) {
                str = getDefaultDir();
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(File.separator);
                stringBuilder.append(OUTPUT_TEMP_DIR_NAME);
                this.mVideoFileTempDir = stringBuilder.toString();
            } else {
                this.mVideoFileTempDir = str2;
            }
            File file2 = new File(this.mVideoFileTempDir);
            if (!file2.exists()) {
                file2.mkdir();
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.mVideoFileTempDir);
            stringBuilder2.append(File.separator);
            stringBuilder2.append(String.format("temp_TXUGC_%s.mp4", new Object[]{getTimeString()}));
            this.mVideoFileCurTempPath = stringBuilder2.toString();
            this.mCoverPath = str3;
            this.mCoverCurTempPath = str3;
            return startRecordInternal();
        }
    }

    private int startRecordInternal() {
        if (!this.mInitCompelete) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startRecordInternal, mInitCompelete = ");
            stringBuilder.append(this.mInitCompelete);
            TXCLog.i(str, stringBuilder.toString());
            return -4;
        } else if (!checkLicenseMatch()) {
            return -5;
        } else {
            TXCAudioUGCRecorder.getInstance().setAECType(TXEAudioDef.TXE_AEC_NONE, this.mContext);
            TXCAudioUGCRecorder.getInstance().setListener(this);
            TXCAudioUGCRecorder.getInstance().setChannels(1);
            TXCAudioUGCRecorder.getInstance().setSampleRate(this.mConfig.t);
            TXCAudioUGCRecorder.getInstance().startRecord(this.mContext);
            switch (this.mRecordSpeed) {
                case 0:
                    TXCAudioUGCRecorder.getInstance().setSpeedRate(ENCODE_SPEED_SLOWEST);
                    break;
                case 1:
                    TXCAudioUGCRecorder.getInstance().setSpeedRate(ENCODE_SPEED_SLOW);
                    break;
                case 2:
                    TXCAudioUGCRecorder.getInstance().setSpeedRate(1.0f);
                    break;
                case 3:
                    TXCAudioUGCRecorder.getInstance().setSpeedRate(ENCODE_SPEED_FAST);
                    break;
                case 4:
                    TXCAudioUGCRecorder.getInstance().setSpeedRate(ENCODE_SPEED_FASTEST);
                    break;
            }
            if (this.mVideoEncoder != null) {
                this.mVideoWidth = 0;
                this.mVideoHeight = 0;
            }
            boolean z = this.mConfig.g < 1280 && this.mConfig.h < 1280;
            this.mUseSWEncoder = z;
            if (this.mMP4Muxer == null) {
                this.mMP4Muxer = new c(this.mContext, this.mUseSWEncoder ? 0 : 2);
            }
            TXCLog.i(TAG, "startRecord");
            this.mMP4Muxer.a(this.mRecordSpeed);
            this.mMP4Muxer.a(this.mVideoFileCurTempPath);
            addAudioTrack();
            TXCDRApi.txReportDAU(this.mContext.getApplicationContext(), com.tencent.liteav.basic.datareport.a.au);
            this.mRecordState = 2;
            this.mRecording = true;
            this.mRecordStartTime = 0;
            if (this.mBGMDeletePart) {
                TXCAudioUGCRecorder.getInstance().clearCache();
            }
            TXCAudioUGCRecorder.getInstance().resume();
            return 0;
        }
    }

    public int stopRecord() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stopRecord called, mRecording = ");
        stringBuilder.append(this.mRecording);
        stringBuilder.append(", needCompose = ");
        stringBuilder.append(this.needCompose);
        TXCLog.i(str, stringBuilder.toString());
        if (this.mRecording) {
            this.needCompose = true;
            return stopRecordForClip();
        }
        int composeRecord = composeRecord();
        if (composeRecord != 0) {
            callbackRecordFail(composeRecord);
        }
        return 0;
    }

    private int composeRecord() {
        if (this.mTXFFQuickJoiner == null) {
            this.mTXFFQuickJoiner = new b();
        }
        this.mTXFFQuickJoiner.a(new com.tencent.liteav.videoediter.ffmpeg.b.a() {
            public void a(b bVar, int i, String str) {
                if (i == 0) {
                    TXUGCRecord.this.callbackRecordSuccess();
                } else if (i == 1) {
                    TXUGCRecord.this.callbackRecordFail(-7);
                    TXLog.e(TXUGCRecord.TAG, "composeRecord, quick joiner result cancel");
                } else if (i == -1) {
                    TXUGCRecord.this.callbackRecordFail(-8);
                    TXLog.e(TXUGCRecord.TAG, "composeRecord, quick joiner result verify fail");
                } else if (i == -2) {
                    TXUGCRecord.this.callbackRecordFail(-9);
                    TXLog.e(TXUGCRecord.TAG, "composeRecord, quick joiner result err");
                }
                bVar.a(null);
                bVar.c();
                bVar.d();
                TXUGCRecord.this.mTXFFQuickJoiner = null;
                TXUGCRecord.this.mRecordState = 1;
            }

            public void a(b bVar, float f) {
                String str = TXUGCRecord.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("joiner progress ");
                stringBuilder.append(f);
                TXCLog.i(str, stringBuilder.toString());
            }
        });
        if (this.mTXFFQuickJoiner.a(this.mTXUGCPartsManager.getPartsPathList()) != 0) {
            TXLog.e(TAG, "composeRecord, quick joiner set src path err");
            return -4;
        } else if (this.mTXFFQuickJoiner.a(this.mVideoFilePath) != 0) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("composeRecord, quick joiner set dst path err, mVideoFilePath = ");
            stringBuilder.append(this.mVideoFilePath);
            TXLog.e(str, stringBuilder.toString());
            return -5;
        } else if (this.mTXFFQuickJoiner.b() == 0) {
            return 0;
        } else {
            TXLog.e(TAG, "composeRecord, quick joiner start fail");
            return -6;
        }
    }

    public void release() {
        TXCAudioUGCRecorder.getInstance().stopRecord();
        TXCAudioUGCRecorder.getInstance().setChangerType(-1, -1);
        TXCAudioUGCRecorder.getInstance().setReverbType(0);
        this.mTXCloudVideoView = null;
        this.mRecordState = 1;
        this.mRenderMode = 0;
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.a(null);
        }
    }

    private int stopRecordForClip() {
        if (this.mRecording) {
            int b;
            String str;
            this.mRecording = false;
            TXCAudioUGCRecorder.getInstance().pause();
            if (this.mVideoEncoder != null) {
                this.mVideoEncoder.b();
            }
            this.mStartMuxer = false;
            stopEncoder(this.mVideoEncoder);
            this.mVideoEncoder = null;
            synchronized (this) {
                if (this.mMP4Muxer != null) {
                    b = this.mMP4Muxer.b();
                    this.mMP4Muxer = null;
                } else {
                    b = 0;
                }
            }
            File file = new File(this.mVideoFileCurTempPath);
            if (b != 0) {
                if (file.exists()) {
                    asyncDeleteFile(this.mVideoFileCurTempPath);
                    this.mVideoFileCurTempPath = null;
                }
                TXCLog.e(TAG, "stopRecordForClip, maybe mMP4Muxer not write data");
                if (!TextUtils.isEmpty(this.mBGMPath)) {
                    this.mBGMDeletePart = true;
                }
                if (!(this.needCompose || this.isReachedMaxDuration)) {
                    return -3;
                }
            }
            if (TextUtils.isEmpty(this.mVideoFileCurTempPath) || !file.exists() || file.length() == 0) {
                str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("stopRecordForClip, file err ---> path = ");
                stringBuilder.append(this.mVideoFileCurTempPath);
                TXCLog.e(str, stringBuilder.toString());
                if (!TextUtils.isEmpty(this.mBGMPath)) {
                    this.mBGMDeletePart = true;
                }
                if (!(this.needCompose || this.isReachedMaxDuration)) {
                    return -3;
                }
            }
            str = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("stopRecordForClip, tempVideoFile exist, path = ");
            stringBuilder2.append(this.mVideoFileCurTempPath);
            stringBuilder2.append(", length = ");
            stringBuilder2.append(file.length());
            TXCLog.i(str, stringBuilder2.toString());
            PartInfo partInfo = new PartInfo();
            partInfo.setPath(this.mVideoFileCurTempPath);
            partInfo.setDuration(this.mCurrentRecordDuration);
            this.mTXUGCPartsManager.addClipInfo(partInfo);
            if (!TextUtils.isEmpty(this.mBGMPath)) {
                long curPosition = TXCUGCBGMPlayer.getInstance().getCurPosition() - TXCAudioUGCRecorder.getInstance().getPcmCacheLen();
                String str2 = TAG;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("stopRecordForClip, bgmCurProgress = ");
                stringBuilder3.append(curPosition);
                stringBuilder3.append(", bgm player position = ");
                stringBuilder3.append(TXCUGCBGMPlayer.getInstance().getCurPosition());
                stringBuilder3.append(", record bgm cache = ");
                stringBuilder3.append(TXCAudioUGCRecorder.getInstance().getPcmCacheLen());
                TXCLog.i(str2, stringBuilder3.toString());
                this.mBgmPartBytesList.add(Long.valueOf(curPosition));
                this.mBGMDeletePart = false;
            }
            callbackEvent(1, null);
            str = this.mCoverCurTempPath;
            if (!TextUtils.isEmpty(this.mCoverCurTempPath)) {
                this.mCoverCurTempPath = null;
            }
            asyncGenCoverAndDetermineCompose(str);
            return 0;
        }
        TXCLog.e(TAG, "stopRecordForClip: there is no existing uncompleted record task");
        return -2;
    }

    private void asyncGenCoverAndDetermineCompose(final String str) {
        AsyncTask.execute(new Runnable() {
            public void run() {
                try {
                    if (!(TextUtils.isEmpty(TXUGCRecord.this.mVideoFileCurTempPath) || TextUtils.isEmpty(str))) {
                        com.tencent.liteav.basic.util.b.a(TXUGCRecord.this.mVideoFileCurTempPath, str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                TXUGCRecord.this.mMainHandler.post(new Runnable() {
                    public void run() {
                        String str = TXUGCRecord.TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("stopRecordForClip, isReachedMaxDuration = ");
                        stringBuilder.append(TXUGCRecord.this.isReachedMaxDuration);
                        stringBuilder.append(", needCompose = ");
                        stringBuilder.append(TXUGCRecord.this.needCompose);
                        TXCLog.i(str, stringBuilder.toString());
                        int access$1300;
                        if (TXUGCRecord.this.isReachedMaxDuration) {
                            TXUGCRecord.this.mRecordRetCode = 2;
                            access$1300 = TXUGCRecord.this.composeRecord();
                            if (access$1300 != 0) {
                                TXUGCRecord.this.callbackRecordFail(access$1300);
                            }
                            return;
                        }
                        if (TXUGCRecord.this.needCompose) {
                            TXUGCRecord.this.mRecordRetCode = 0;
                            TXUGCRecord.this.needCompose = false;
                            access$1300 = TXUGCRecord.this.composeRecord();
                            if (access$1300 != 0) {
                                TXUGCRecord.this.callbackRecordFail(access$1300);
                            }
                        }
                    }
                });
            }
        });
    }

    private void callbackRecordFail(int i) {
        TXRecordResult tXRecordResult = new TXRecordResult();
        tXRecordResult.retCode = i;
        tXRecordResult.descMsg = "record video failed";
        if (this.mVideoRecordListener != null) {
            this.mVideoRecordListener.onRecordComplete(tXRecordResult);
        }
    }

    private void callbackRecordSuccess() {
        TXRecordResult tXRecordResult = new TXRecordResult();
        if (((long) this.mTXUGCPartsManager.getDuration()) < ((long) this.mMinDuration)) {
            this.mRecordRetCode = 1;
        }
        tXRecordResult.retCode = this.mRecordRetCode;
        tXRecordResult.descMsg = "record success";
        tXRecordResult.videoPath = this.mVideoFilePath;
        tXRecordResult.coverPath = this.mCoverPath;
        if (this.mVideoRecordListener != null) {
            this.mVideoRecordListener.onRecordComplete(tXRecordResult);
        }
    }

    private String getDefaultDir() {
        String stringBuilder;
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(Environment.getExternalStorageDirectory());
            stringBuilder2.append(File.separator);
            stringBuilder2.append(OUTPUT_DIR_NAME);
            stringBuilder = stringBuilder2.toString();
            File file = new File(stringBuilder);
            if (!file.exists()) {
                file.mkdir();
            }
            return stringBuilder;
        }
        File filesDir = this.mContext.getFilesDir();
        if (filesDir != null) {
            stringBuilder = filesDir.getPath();
        } else {
            stringBuilder = null;
        }
        return stringBuilder;
    }

    private String getTimeString() {
        return new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date(System.currentTimeMillis()));
    }

    private void asyncDeleteFile(final String str) {
        if (str != null && !str.isEmpty()) {
            try {
                new AsyncTask() {
                    /* Access modifiers changed, original: protected */
                    public Object doInBackground(Object[] objArr) {
                        File file = new File(str);
                        if (file.isFile() && file.exists()) {
                            file.delete();
                        }
                        return null;
                    }
                }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Object[0]);
            } catch (Exception e) {
                String str2 = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("asyncDeleteFile, exception = ");
                stringBuilder.append(e);
                TXCLog.d(str2, stringBuilder.toString());
            }
        }
    }

    public int pauseRecord() {
        if (this.mRecording) {
            TXCLog.i(TAG, "pauseRecord called");
            this.mRecordState = 3;
            return stopRecordForClip();
        }
        TXCLog.e(TAG, "pauseRecord: there is no existing uncompleted record task");
        return -2;
    }

    public int resumeRecord() {
        if (this.mRecording) {
            TXCLog.e(TAG, "resumeRecord: there is existing uncompleted record task");
            return -1;
        }
        TXCLog.i(TAG, "resumeRecord called");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mVideoFileTempDir);
        stringBuilder.append(File.separator);
        stringBuilder.append(String.format("temp_TXUGC_%s.mp4", new Object[]{getTimeString()}));
        this.mVideoFileCurTempPath = stringBuilder.toString();
        int startRecordInternal = startRecordInternal();
        callbackEvent(2, null);
        return startRecordInternal;
    }

    private void changeRecordSpeed() {
        switch (this.mRecordSpeed) {
            case 0:
                TXCUGCBGMPlayer.getInstance().setSpeedRate(PLAY_SPEED_SLOWEST);
                TXCAudioUGCRecorder.getInstance().setSpeedRate(ENCODE_SPEED_SLOWEST);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bl, this.mRecordSpeed, "SLOWEST");
                return;
            case 1:
                TXCUGCBGMPlayer.getInstance().setSpeedRate(PLAY_SPEED_SLOW);
                TXCAudioUGCRecorder.getInstance().setSpeedRate(ENCODE_SPEED_SLOW);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bl, this.mRecordSpeed, "SLOW");
                return;
            case 2:
                TXCUGCBGMPlayer.getInstance().setSpeedRate(1.0f);
                TXCAudioUGCRecorder.getInstance().setSpeedRate(1.0f);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bl, this.mRecordSpeed, "NORMAL");
                return;
            case 3:
                TXCUGCBGMPlayer.getInstance().setSpeedRate(PLAY_SPEED_FAST);
                TXCAudioUGCRecorder.getInstance().setSpeedRate(ENCODE_SPEED_FAST);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bl, this.mRecordSpeed, "FAST");
                return;
            case 4:
                TXCUGCBGMPlayer.getInstance().setSpeedRate(PLAY_SPEED_FASTEST);
                TXCAudioUGCRecorder.getInstance().setSpeedRate(ENCODE_SPEED_FASTEST);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bb);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bl, this.mRecordSpeed, "FASTEST");
                return;
            default:
                return;
        }
    }

    public boolean setMicVolume(float f) {
        TXCAudioUGCRecorder.getInstance().setVolume(f);
        return true;
    }

    public boolean switchCamera(final boolean z) {
        this.mConfig.o = z;
        if (this.mVideoView != null) {
            this.mVideoView.b(new Runnable() {
                public void run() {
                    if (TXUGCRecord.this.mCameraCapture != null) {
                        TXUGCRecord.this.mCameraCapture.b();
                        TXUGCRecord.this.mVideoView.a(false);
                        TXUGCRecord.this.mCameraCapture.a(TXUGCRecord.this.mVideoView.getSurfaceTexture());
                        if (TXUGCRecord.this.mCameraCapture.c(z) == 0) {
                            TXUGCRecord.this.mCapturing = true;
                        } else {
                            TXUGCRecord.this.mCapturing = false;
                            TXUGCRecord.this.callbackEvent(3, null);
                        }
                        TXUGCRecord.this.mVideoView.a(TXUGCRecord.this.mConfig.c);
                    }
                    TXUGCRecord.this.setWatermark(TXUGCRecord.this.mWatermarkBitmap, TXUGCRecord.this.mTxWaterMarkRect);
                }
            });
        }
        return true;
    }

    public void setAspectRatio(int i) {
        this.mDisplayType = i;
        if (i == 0) {
            this.mCropWidth = this.mConfig.g;
            this.mCropHeight = this.mConfig.h;
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bd);
        } else if (i == 1) {
            this.mCropHeight = (((int) ((((float) this.mConfig.g) * 4.0f) / 3.0f)) / 16) * 16;
            this.mCropWidth = this.mConfig.g;
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bc);
        } else if (i == 2) {
            this.mCropHeight = this.mConfig.g;
            this.mCropWidth = this.mConfig.g;
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bb);
        }
    }

    private boolean checkLicenseMatch() {
        int a = com.tencent.liteav.basic.c.e.a().a(null, this.mContext);
        if (a != 0) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("checkLicenseMatch, checkErrCode = ");
            stringBuilder.append(a);
            TXCLog.e(str, stringBuilder.toString());
            return false;
        }
        if (com.tencent.liteav.basic.c.e.a().c() == 2 && !this.mSmartLicenseSupport) {
            TXCLog.e(TAG, "checkLicenseMatch, called UnSupported method!");
        }
        return true;
    }

    private boolean isSmartLicense() {
        com.tencent.liteav.basic.c.e.a().a(null, this.mContext);
        if (com.tencent.liteav.basic.c.e.a().c() == -1) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("isSmartLicense, license type is = ");
            stringBuilder.append(com.tencent.liteav.basic.c.e.a().c());
            TXCLog.i(str, stringBuilder.toString());
            this.mSmartLicenseSupport = false;
        } else if (com.tencent.liteav.basic.c.e.a().c() == 2) {
            return true;
        }
        return false;
    }

    public void snapshot(final ITXSnapshotListener iTXSnapshotListener) {
        if (checkLicenseMatch() && !this.mSnapshotRunning && iTXSnapshotListener != null) {
            this.mSnapshotRunning = true;
            if (this.mVideoView != null) {
                this.mVideoView.a(new n() {
                    public void onTakePhotoComplete(Bitmap bitmap) {
                        iTXSnapshotListener.onSnapshot(bitmap);
                    }
                });
            }
            this.mSnapshotRunning = false;
        }
    }

    public void setRecordSpeed(int i) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setRecordSpeed is not supported in UGC_Smart license");
            return;
        }
        this.mRecordSpeed = i;
        if (!TextUtils.isEmpty(this.mBGMPath)) {
            changeRecordSpeed();
        }
    }

    public void setVideoProcessListener(VideoCustomProcessListener videoCustomProcessListener) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setVideoProcessListener is not supported in UGC_Smart license");
        } else {
            this.mCustomProcessListener = videoCustomProcessListener;
        }
    }

    public void setReverb(int i) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setReverb is not supported in UGC_Smart license");
            return;
        }
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bo, i, "");
        TXCAudioUGCRecorder.getInstance().setReverbType(i);
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.az);
    }

    public void setVoiceChangerType(int i) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setVoiceChangerType is not supported in UGC_Smart license");
            return;
        }
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
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bn, i, "");
        TXCAudioUGCRecorder.getInstance().setChangerType(this.mVoiceKind, this.mVoiceEnvironment);
    }

    public int setBGM(String str) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBGM is not supported in UGC_Smart license");
            return -1;
        } else if (TextUtils.isEmpty(str)) {
            TXCLog.e(TAG, "setBGM, path is empty");
            stopBGM();
            TXCUGCBGMPlayer.getInstance().setOnPlayListener(null);
            return 0;
        } else {
            this.mBGMPath = str;
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bm);
            if (this.mBGMNotifyProxy == null) {
                this.mBGMNotifyProxy = new com.tencent.liteav.audio.e() {
                    public void a() {
                        TXUGCRecord.this.mBGMPlayStop = false;
                        if (TXUGCRecord.this.mBGMNotify != null) {
                            TXUGCRecord.this.mBGMNotify.onBGMStart();
                        }
                    }

                    public void a(int i) {
                        if (TXUGCRecord.this.mBGMNotify != null) {
                            TXUGCRecord.this.mBGMNotify.onBGMComplete(0);
                        }
                        TXUGCRecord.this.mMainHandler.post(new Runnable() {
                            public void run() {
                                if (TXUGCRecord.this.mRecording) {
                                    TXCUGCBGMPlayer.getInstance().stopPlay();
                                    TXCUGCBGMPlayer.getInstance().playFromTime(TXUGCRecord.this.mBGMStartTime, TXUGCRecord.this.mBGMEndTime);
                                    TXCUGCBGMPlayer.getInstance().startPlay(TXUGCRecord.this.mBGMPath);
                                }
                            }
                        });
                    }

                    public void a(long j, long j2) {
                        if (TXUGCRecord.this.mBGMNotify != null) {
                            TXUGCRecord.this.mBGMNotify.onBGMProgress(j, j2);
                        }
                    }
                };
            }
            TXCUGCBGMPlayer.getInstance().setOnPlayListener(this.mBGMNotifyProxy);
            return (int) TXCUGCBGMPlayer.getDurationMS(str);
        }
    }

    public void setBGMNofify(ITXBGMNotify iTXBGMNotify) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBGMNofify is not supported in UGC_Smart license");
            return;
        }
        if (iTXBGMNotify == null) {
            this.mBGMNotify = null;
        } else {
            this.mBGMNotify = iTXBGMNotify;
        }
    }

    public boolean playBGMFromTime(int i, int i2) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "playBGMFromTime is not supported in UGC_Smart license");
            return false;
        } else if (TextUtils.isEmpty(this.mBGMPath)) {
            TXCLog.e(TAG, "playBGMFromTime, path is empty");
            return false;
        } else if (i < 0 || i2 < 0) {
            TXCLog.e(TAG, "playBGMFromTime, time is negative number");
            return false;
        } else if (i >= i2) {
            TXCLog.e(TAG, "playBGMFromTime, start time is bigger than end time");
            return false;
        } else {
            long j = (long) i;
            this.mBGMStartTime = j;
            long j2 = (long) i2;
            this.mBGMEndTime = j2;
            this.mBGMDeletePart = false;
            this.mTXUGCPartsManager.setPartsManagerObserver(this);
            changeRecordSpeed();
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aA);
            if (TXCAudioUGCRecorder.getInstance().isRecording()) {
                TXCAudioUGCRecorder.getInstance().enableBGMRecord(true);
                TXCAudioUGCRecorder.getInstance().setChannels(1);
                TXCAudioUGCRecorder.getInstance().startRecord(this.mContext);
            }
            TXCUGCBGMPlayer.getInstance().playFromTime(j, j2);
            TXCUGCBGMPlayer.getInstance().startPlay(this.mBGMPath);
            return true;
        }
    }

    public boolean stopBGM() {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "stopBGM is not supported in UGC_Smart license");
            return false;
        }
        this.mBGMPath = null;
        this.mTXUGCPartsManager.removePartsManagerObserver(this);
        TXCUGCBGMPlayer.getInstance().stopPlay();
        TXCAudioUGCRecorder.getInstance().enableBGMRecord(false);
        TXCUGCBGMPlayer.getInstance().setOnPlayListener(null);
        return true;
    }

    public boolean pauseBGM() {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "pauseBGM is not supported in UGC_Smart license");
            return false;
        }
        TXCUGCBGMPlayer.getInstance().pause();
        return true;
    }

    public boolean resumeBGM() {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "resumeBGM is not supported in UGC_Smart license");
            return false;
        } else if (TextUtils.isEmpty(this.mBGMPath)) {
            TXCLog.e(TAG, "resumeBGM, mBGMPath is empty");
            return false;
        } else {
            changeRecordSpeed();
            if (this.mBGMDeletePart) {
                long j = 0;
                if (this.mBgmPartBytesList.size() > 0) {
                    j = ((Long) this.mBgmPartBytesList.get(this.mBgmPartBytesList.size() - 1)).longValue();
                }
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("resumeBGM, curBGMBytesProgress = ");
                stringBuilder.append(j);
                TXCLog.i(str, stringBuilder.toString());
                if (this.mBGMPlayStop) {
                    TXCUGCBGMPlayer.getInstance().startPlay(this.mBGMPath);
                }
                TXCUGCBGMPlayer.getInstance().seekBytes(j);
                TXCAudioUGCRecorder.getInstance().clearCache();
            }
            TXCUGCBGMPlayer.getInstance().resume();
            return true;
        }
    }

    public boolean seekBGM(int i, int i2) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "seekBGM is not supported in UGC_Smart license");
            return false;
        }
        TXCUGCBGMPlayer.getInstance().playFromTime((long) i, (long) i2);
        return true;
    }

    public boolean setBGMVolume(float f) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBGMVolume is not supported in UGC_Smart license");
            return false;
        }
        TXCUGCBGMPlayer.getInstance().setVolume(f);
        return true;
    }

    public int getMusicDuration(String str) {
        if (!isSmartLicense()) {
            return (int) TXCUGCBGMPlayer.getDurationMS(str);
        }
        TXCLog.e(TAG, "getMusicDuration is not supported in UGC_Smart license");
        return 0;
    }

    public void setWatermark(Bitmap bitmap, TXRect tXRect) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setWatermark is not supported in UGC_Smart license");
            return;
        }
        if (!(this.mVideoPreprocessor == null || bitmap == null || tXRect == null)) {
            this.mVideoPreprocessor.a(bitmap, tXRect.x, tXRect.y, tXRect.width);
        }
        this.mWatermarkBitmap = bitmap;
        this.mTxWaterMarkRect = tXRect;
    }

    public void setMotionTmpl(String str) {
        if (com.tencent.liteav.basic.c.e.a().c() >= 4 && this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.a(str);
        }
    }

    public void setMotionMute(boolean z) {
        if (com.tencent.liteav.basic.c.e.a().c() >= 4 && this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.b(z);
        }
    }

    @TargetApi(18)
    public void setGreenScreenFile(String str, boolean z) {
        if (VERSION.SDK_INT >= 18) {
            if (com.tencent.liteav.basic.c.e.a().c() != 5) {
                TXCLog.e(TAG, "setGreenScreenFile is only supported in EnterprisePro license");
                return;
            }
            if (this.mVideoPreprocessor != null) {
                this.mVideoPreprocessor.a(str, z);
            }
        }
    }

    public void setFaceVLevel(int i) {
        if (com.tencent.liteav.basic.c.e.a().c() != 5) {
            TXCLog.e(TAG, "setFaceVLevel is only supported in EnterprisePro license");
            return;
        }
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.i(i);
        }
    }

    public void setFaceShortLevel(int i) {
        if (com.tencent.liteav.basic.c.e.a().c() != 5) {
            TXCLog.e(TAG, "setFaceShortLevel is only supported in EnterprisePro license");
            return;
        }
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.j(i);
        }
    }

    public void setChinLevel(int i) {
        if (com.tencent.liteav.basic.c.e.a().c() != 5) {
            TXCLog.e(TAG, "setChinLevel is only supported in EnterprisePro license");
            return;
        }
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.k(i);
        }
    }

    public void setNoseSlimLevel(int i) {
        if (com.tencent.liteav.basic.c.e.a().c() != 5) {
            TXCLog.e(TAG, "setNoseSlimLevel is only supported in EnterprisePro license");
            return;
        }
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.l(i);
        }
    }

    public void setEyeScaleLevel(float f) {
        if (com.tencent.liteav.basic.c.e.a().c() != 5) {
            TXCLog.e(TAG, "setEyeScaleLevel is only supported in EnterprisePro license");
            return;
        }
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.g((int) f);
        }
    }

    public void setFaceScaleLevel(float f) {
        if (com.tencent.liteav.basic.c.e.a().c() != 5) {
            TXCLog.e(TAG, "setFaceScaleLevel is only supported in EnterprisePro license");
            return;
        }
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.h((int) f);
        }
    }

    public void setBeautyStyle(int i) {
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.b(i);
        }
    }

    public void setBeautyDepth(int i, int i2, int i3, int i4) {
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.b(i);
            this.mVideoPreprocessor.c(i2);
            this.mVideoPreprocessor.d(i3);
            this.mVideoPreprocessor.e(i4);
        }
    }

    public void setFilter(Bitmap bitmap) {
        setFilter(bitmap, this.mSpecialRadio, null, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
    }

    public void setFilter(Bitmap bitmap, float f, Bitmap bitmap2, float f2, float f3) {
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.a(f3, bitmap, f, bitmap2, f2);
        }
    }

    public void setSpecialRatio(float f) {
        this.mSpecialRadio = f;
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.a(f);
        }
    }

    private void setSharpenLevel(int i) {
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.f(i);
        }
    }

    public boolean toggleTorch(boolean z) {
        if (this.mCameraCapture != null) {
            this.mCameraCapture.a(z);
        }
        return true;
    }

    public int getMaxZoom() {
        return this.mCameraCapture.a();
    }

    public boolean setZoom(int i) {
        return this.mCameraCapture != null ? this.mCameraCapture.c(i) : false;
    }

    public void setFocusPosition(float f, float f2) {
        this.mTouchFocusRunnable.a(f, f2);
        this.mMainHandler.postDelayed(this.mTouchFocusRunnable, 100);
    }

    public void setVideoRenderMode(int i) {
        if (i == 1) {
            this.mRenderMode = 1;
        } else {
            this.mRenderMode = 0;
        }
    }

    private int startCameraPreviewInternal(TXCloudVideoView tXCloudVideoView, j jVar) {
        TXCLog.i(TAG, "ugcRecord, startCameraPreviewInternal");
        this.mStartPreview = true;
        if (this.mTXCloudVideoView != null) {
            this.mTXCloudVideoView.removeVideoView();
            this.mTXCloudVideoView.removeFocusIndicatorView();
        }
        this.mTXCloudVideoView = tXCloudVideoView;
        initConfig();
        calcVideoEncInfo();
        initModules();
        this.mInitCompelete = false;
        this.mVideoView.setRendMode(this.mRenderMode);
        this.mVideoView.setSurfaceTextureListener(this);
        return 0;
    }

    public void setHomeOrientation(int i) {
        this.mCameraOrientationReadyChange = i;
        resetRotation();
    }

    public void setRenderRotation(int i) {
        this.mRenderRotationReadyChange = i;
        resetRotation();
    }

    private void resetRotation() {
        if (this.mVideoView != null) {
            this.mVideoView.b(new Runnable() {
                public void run() {
                    if (TXUGCRecord.this.mRenderRotationReadyChange != -1) {
                        TXUGCRecord.this.mConfig.s = TXUGCRecord.this.mRenderRotationReadyChange;
                        TXUGCRecord.this.mRenderRotationReadyChange = -1;
                    }
                    if (TXUGCRecord.this.mCameraOrientationReadyChange != -1) {
                        TXUGCRecord.this.mConfig.r = TXUGCRecord.this.mCameraOrientationReadyChange;
                        TXUGCRecord.this.mCameraCapture.d(TXUGCRecord.this.mConfig.r);
                        TXUGCRecord.this.mCameraOrientationReadyChange = -1;
                    }
                }
            });
            return;
        }
        this.mConfig.s = this.mRenderRotationReadyChange;
        this.mConfig.r = this.mCameraOrientationReadyChange;
    }

    @TargetApi(16)
    private void addAudioTrack() {
        MediaFormat a = com.tencent.liteav.basic.util.b.a(TXCAudioUGCRecorder.getInstance().getSampleRate(), TXCAudioUGCRecorder.getInstance().getChannels(), 2);
        if (this.mMP4Muxer != null) {
            this.mMP4Muxer.b(a);
        }
    }

    private void initModules() {
        this.mVideoView = this.mTXCloudVideoView.getGLSurfaceView();
        if (this.mVideoView == null) {
            this.mVideoView = new TXCGLSurfaceView(this.mTXCloudVideoView.getContext());
            this.mTXCloudVideoView.addVideoView(this.mVideoView);
        }
        if (this.mCameraCapture == null) {
            this.mCameraCapture = new com.tencent.liteav.capturer.a();
        }
        this.mCameraCapture.a(this.mConfig.q ? 7 : this.mCameraResolution);
        this.mCameraCapture.b(this.mConfig.c);
        if (this.mVideoPreprocessor == null) {
            this.mVideoPreprocessor = new com.tencent.liteav.beauty.d(this.mContext, true);
        }
        this.mVideoPreprocessor.a((e) this);
        this.mVideoEncoder = null;
    }

    /* JADX WARNING: Missing block: B:12:0x0094, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:17:0x00a4, code skipped:
            return false;
     */
    private boolean startCapture(android.graphics.SurfaceTexture r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = "TXUGCRecord";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a5 }
        r1.<init>();	 Catch:{ all -> 0x00a5 }
        r2 = "startCapture, mCapturing = ";
        r1.append(r2);	 Catch:{ all -> 0x00a5 }
        r2 = r3.mCapturing;	 Catch:{ all -> 0x00a5 }
        r1.append(r2);	 Catch:{ all -> 0x00a5 }
        r2 = ", mCameraCapture = ";
        r1.append(r2);	 Catch:{ all -> 0x00a5 }
        r2 = r3.mCameraCapture;	 Catch:{ all -> 0x00a5 }
        r1.append(r2);	 Catch:{ all -> 0x00a5 }
        r1 = r1.toString();	 Catch:{ all -> 0x00a5 }
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);	 Catch:{ all -> 0x00a5 }
        r0 = 0;
        if (r4 == 0) goto L_0x00a3;
    L_0x0026:
        r1 = r3.mCapturing;	 Catch:{ all -> 0x00a5 }
        if (r1 != 0) goto L_0x00a3;
    L_0x002a:
        r1 = r3.mCameraCapture;	 Catch:{ all -> 0x00a5 }
        r1.a(r4);	 Catch:{ all -> 0x00a5 }
        r4 = r3.mCameraCapture;	 Catch:{ all -> 0x00a5 }
        r1 = r3.mConfig;	 Catch:{ all -> 0x00a5 }
        r1 = r1.c;	 Catch:{ all -> 0x00a5 }
        r4.b(r1);	 Catch:{ all -> 0x00a5 }
        r4 = r3.mCameraCapture;	 Catch:{ all -> 0x00a5 }
        r1 = r3.mConfig;	 Catch:{ all -> 0x00a5 }
        r1 = r1.f;	 Catch:{ all -> 0x00a5 }
        r4.b(r1);	 Catch:{ all -> 0x00a5 }
        r4 = "TXUGCRecord";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a5 }
        r1.<init>();	 Catch:{ all -> 0x00a5 }
        r2 = "startCapture, setHomeOriention = ";
        r1.append(r2);	 Catch:{ all -> 0x00a5 }
        r2 = r3.mConfig;	 Catch:{ all -> 0x00a5 }
        r2 = r2.r;	 Catch:{ all -> 0x00a5 }
        r1.append(r2);	 Catch:{ all -> 0x00a5 }
        r1 = r1.toString();	 Catch:{ all -> 0x00a5 }
        com.tencent.liteav.basic.log.TXCLog.i(r4, r1);	 Catch:{ all -> 0x00a5 }
        r4 = r3.mCameraCapture;	 Catch:{ all -> 0x00a5 }
        r1 = r3.mConfig;	 Catch:{ all -> 0x00a5 }
        r1 = r1.r;	 Catch:{ all -> 0x00a5 }
        r4.d(r1);	 Catch:{ all -> 0x00a5 }
        r4 = r3.mCameraCapture;	 Catch:{ all -> 0x00a5 }
        r1 = r3.mConfig;	 Catch:{ all -> 0x00a5 }
        r1 = r1.o;	 Catch:{ all -> 0x00a5 }
        r4 = r4.c(r1);	 Catch:{ all -> 0x00a5 }
        if (r4 != 0) goto L_0x0095;
    L_0x0070:
        r4 = 1;
        r3.mCapturing = r4;	 Catch:{ all -> 0x00a5 }
        r0 = r3.mVideoView;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0093;
    L_0x0077:
        r0 = r3.mVideoView;	 Catch:{ all -> 0x00a5 }
        r1 = r3.mConfig;	 Catch:{ all -> 0x00a5 }
        r1 = r1.c;	 Catch:{ all -> 0x00a5 }
        r0.setFPS(r1);	 Catch:{ all -> 0x00a5 }
        r0 = r3.mVideoView;	 Catch:{ all -> 0x00a5 }
        r0.setSurfaceTextureListener(r3);	 Catch:{ all -> 0x00a5 }
        r0 = r3.mVideoView;	 Catch:{ all -> 0x00a5 }
        r0.setNotifyListener(r3);	 Catch:{ all -> 0x00a5 }
        r0 = r3.mVideoView;	 Catch:{ all -> 0x00a5 }
        r1 = r3.mConfig;	 Catch:{ all -> 0x00a5 }
        r1 = r1.c;	 Catch:{ all -> 0x00a5 }
        r0.a(r1);	 Catch:{ all -> 0x00a5 }
    L_0x0093:
        monitor-exit(r3);	 Catch:{ all -> 0x00a5 }
        return r4;
    L_0x0095:
        r3.mCapturing = r0;	 Catch:{ all -> 0x00a5 }
        r4 = "TXUGCRecord";
        r1 = "startCapture fail!";
        com.tencent.rtmp.TXLog.e(r4, r1);	 Catch:{ all -> 0x00a5 }
        r3.onRecordError();	 Catch:{ all -> 0x00a5 }
        monitor-exit(r3);	 Catch:{ all -> 0x00a5 }
        return r0;
    L_0x00a3:
        monitor-exit(r3);	 Catch:{ all -> 0x00a5 }
        return r0;
    L_0x00a5:
        r4 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x00a5 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.ugc.TXUGCRecord.startCapture(android.graphics.SurfaceTexture):boolean");
    }

    private void stopEncoder(final com.tencent.liteav.videoencoder.b bVar) {
        if (this.mVideoView != null) {
            this.mVideoView.b(new Runnable() {
                public void run() {
                    try {
                        if (bVar != null) {
                            bVar.a();
                            bVar.a(null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void startEncoder(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("New encode size width = ");
        stringBuilder.append(i);
        stringBuilder.append(" height = ");
        stringBuilder.append(i2);
        stringBuilder.append(", mVideoView = ");
        stringBuilder.append(this.mVideoView);
        TXCLog.i(str, stringBuilder.toString());
        stopEncoder(this.mVideoEncoder);
        this.mVideoEncoder = null;
        EGLContext eglGetCurrentContext = ((EGL10) EGLContext.getEGL()).eglGetCurrentContext();
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        TXSVideoEncoderParam tXSVideoEncoderParam = new TXSVideoEncoderParam();
        tXSVideoEncoderParam.width = i;
        tXSVideoEncoderParam.height = i2;
        tXSVideoEncoderParam.fps = this.mConfig.c;
        tXSVideoEncoderParam.fullIFrame = this.mConfig.u;
        tXSVideoEncoderParam.glContext = eglGetCurrentContext;
        tXSVideoEncoderParam.annexb = true;
        tXSVideoEncoderParam.appendSpsPps = false;
        if (this.mUseSWEncoder) {
            this.mVideoEncoder = new com.tencent.liteav.videoencoder.b(2);
            tXSVideoEncoderParam.encoderMode = 1;
            tXSVideoEncoderParam.encoderProfile = 3;
        } else {
            this.mVideoEncoder = new com.tencent.liteav.videoencoder.b(1);
            tXSVideoEncoderParam.encoderMode = 3;
            tXSVideoEncoderParam.encoderProfile = 1;
        }
        tXSVideoEncoderParam.record = true;
        if (!this.mConfig.u) {
            this.mVideoEncoder.a(this.mConfig.d);
        } else if (this.mUseSWEncoder) {
            this.mVideoEncoder.a(24000);
        } else {
            this.mVideoEncoder.a(15000);
        }
        tXSVideoEncoderParam.realTime = true;
        tXSVideoEncoderParam.enableBlackList = false;
        this.mVideoEncoder.a((com.tencent.liteav.videoencoder.d) this);
        this.mVideoEncoder.a(tXSVideoEncoderParam);
    }

    private void encodeFrame(int i, int i2, int i3) {
        if (!(this.mVideoEncoder != null && this.mVideoWidth == i2 && this.mVideoHeight == i3)) {
            startEncoder(i2, i3);
        }
        this.mVideoEncoder.a(i, i2, i3, TXCTimeUtil.getTimeTick());
    }

    private void onRecordError() {
        if (this.mVideoRecordListener != null && this.mRecording) {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    TXUGCRecord.this.stopRecordForClip();
                }
            });
            TXCUGCBGMPlayer.getInstance().pause();
            this.mRecording = false;
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    TXRecordResult tXRecordResult = new TXRecordResult();
                    tXRecordResult.descMsg = "record video failed";
                    tXRecordResult.retCode = -1;
                    if (TXUGCRecord.this.mVideoRecordListener != null) {
                        TXUGCRecord.this.mVideoRecordListener.onRecordComplete(tXRecordResult);
                    }
                }
            });
        }
    }

    private void initConfig() {
        if (this.mConfig.a < 0) {
            switch (this.mConfig.b) {
                case 0:
                    this.mConfig.g = 360;
                    this.mConfig.h = 640;
                    this.mCameraResolution = 4;
                    TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.be, 360, "360x640");
                    break;
                case 1:
                    this.mConfig.g = 540;
                    this.mConfig.h = 960;
                    this.mCameraResolution = 5;
                    TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bf, 540, "540x960");
                    break;
                case 2:
                    this.mConfig.g = 720;
                    this.mConfig.h = 1280;
                    this.mCameraResolution = 6;
                    TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bg, 720, "720x1280");
                    break;
                case 3:
                    this.mConfig.g = 1080;
                    this.mConfig.h = 1920;
                    this.mCameraResolution = 7;
                    TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bh, 1080, "1080x1920");
                    break;
                default:
                    this.mConfig.g = 540;
                    this.mConfig.h = 960;
                    this.mCameraResolution = 5;
                    TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bf, 720, "720x1280");
                    break;
            }
        }
        switch (this.mConfig.a) {
            case 0:
                this.mConfig.b = 0;
                this.mConfig.g = 360;
                this.mConfig.h = 640;
                this.mConfig.d = 2400;
                this.mCameraResolution = 4;
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.be);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bi, 2400, "");
                break;
            case 1:
                this.mConfig.b = 1;
                this.mConfig.g = 540;
                this.mConfig.h = 960;
                this.mConfig.d = 6500;
                this.mCameraResolution = 5;
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bf);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bi, 6500, "");
                break;
            case 2:
                this.mConfig.b = 2;
                this.mConfig.g = 720;
                this.mConfig.h = 1280;
                this.mConfig.d = 9600;
                this.mCameraResolution = 6;
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bg);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bi, 9600, "");
                break;
            default:
                this.mConfig.b = 1;
                this.mConfig.g = 540;
                this.mConfig.h = 960;
                this.mConfig.d = 6500;
                this.mCameraResolution = 5;
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bf);
                TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bi, 6500, "");
                break;
        }
        this.mConfig.c = this.mFps;
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bj, this.mFps, "");
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("record:camera init record param, width:");
        stringBuilder.append(this.mConfig.g);
        stringBuilder.append(",height:");
        stringBuilder.append(this.mConfig.h);
        stringBuilder.append(",bitrate:");
        stringBuilder.append(this.mConfig.d);
        stringBuilder.append(",fps:");
        stringBuilder.append(this.mConfig.c);
        TXCLog.w(str, stringBuilder.toString());
    }

    private void calcVideoEncInfo() {
        if (this.mConfig.h != 0) {
            double d = (double) this.mConfig.g;
            double d2 = (double) this.mConfig.h;
            Double.isNaN(d);
            Double.isNaN(d2);
            d /= d2;
            this.mConfig.g = ((this.mConfig.g + 15) / 16) * 16;
            this.mConfig.h = ((this.mConfig.h + 15) / 16) * 16;
            d2 = (double) this.mConfig.g;
            double d3 = (double) this.mConfig.h;
            Double.isNaN(d2);
            Double.isNaN(d3);
            d2 /= d3;
            d3 = (double) (this.mConfig.g + 16);
            double d4 = (double) this.mConfig.h;
            Double.isNaN(d3);
            Double.isNaN(d4);
            d3 /= d4;
            d4 = (double) (this.mConfig.g - 16);
            double d5 = (double) this.mConfig.h;
            Double.isNaN(d4);
            Double.isNaN(d5);
            d4 /= d5;
            j jVar = this.mConfig;
            d2 -= d;
            d3 -= d;
            int i = Math.abs(d2) < Math.abs(d3) ? Math.abs(d2) < Math.abs(d4 - d) ? this.mConfig.g : this.mConfig.g - 16 : Math.abs(d3) < Math.abs(d4 - d) ? this.mConfig.g + 16 : this.mConfig.g - 16;
            jVar.g = i;
        }
    }

    private boolean onRecordProgress(long j) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRecordProgress = ");
        stringBuilder.append(j);
        TXCLog.i(str, stringBuilder.toString());
        if (this.mRecordSpeed != 2) {
            if (this.mRecordSpeed == 3) {
                j = (long) (((float) j) / ENCODE_SPEED_FAST);
            } else if (this.mRecordSpeed == 4) {
                j = (long) (((float) j) / ENCODE_SPEED_FASTEST);
            } else if (this.mRecordSpeed == 1) {
                j = (long) (((float) j) / ENCODE_SPEED_SLOW);
            } else if (this.mRecordSpeed == 0) {
                j = (long) (((float) j) / ENCODE_SPEED_SLOWEST);
            }
        }
        this.mCurrentRecordDuration = j;
        j = ((long) this.mTXUGCPartsManager.getDuration()) + this.mCurrentRecordDuration;
        this.mMainHandler.post(new Runnable() {
            public void run() {
                if (TXUGCRecord.this.mVideoRecordListener != null) {
                    TXUGCRecord.this.mVideoRecordListener.onRecordProgress(j);
                }
            }
        });
        if (j >= ((long) this.mMaxDuration)) {
            this.isReachedMaxDuration = true;
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    TXUGCRecord.this.stopRecordForClip();
                }
            });
            return false;
        }
        this.isReachedMaxDuration = false;
        return true;
    }

    private int getSreenRotation() {
        return (this.mContext == null || this.mContext.getResources().getConfiguration().orientation != 2) ? 0 : 90;
    }

    private int getRecordState() {
        int minBufferSize = AudioRecord.getMinBufferSize(TXRecordCommon.AUDIO_SAMPLERATE_44100, 16, 2);
        AudioRecord audioRecord = new AudioRecord(0, TXRecordCommon.AUDIO_SAMPLERATE_44100, 16, 2, minBufferSize * 100);
        short[] sArr = new short[minBufferSize];
        try {
            audioRecord.startRecording();
            if (audioRecord.getRecordingState() != 3) {
                audioRecord.stop();
                audioRecord.release();
                TXCLog.i("CheckAudioPermission", "录音机被占用");
                return 1;
            } else if (audioRecord.read(sArr, 0, sArr.length) <= 0) {
                audioRecord.stop();
                audioRecord.release();
                TXCLog.i("CheckAudioPermission", "录音的结果为空");
                return -1;
            } else {
                audioRecord.stop();
                audioRecord.release();
                return 0;
            }
        } catch (Exception unused) {
            audioRecord.release();
            TXCLog.i("CheckAudioPermission", "无法进入录音初始状态");
            return -1;
        }
    }

    private void callbackEvent(final int i, final Bundle bundle) {
        this.mMainHandler.post(new Runnable() {
            public void run() {
                if (TXUGCRecord.this.mVideoRecordListener != null) {
                    TXUGCRecord.this.mVideoRecordListener.onRecordEvent(i, bundle);
                }
            }
        });
    }

    public void onRecordEncData(byte[] bArr, long j, int i, int i2, int i3) {
        if (this.mMP4Muxer != null && this.mRecording) {
            this.mMP4Muxer.a(bArr, 0, bArr.length, j * 1000, 0);
        }
    }

    public void onRecordError(int i, String str) {
        if (i == TXEAudioDef.TXE_AUDIO_RECORD_ERR_NO_MIC_PERMIT) {
            TXLog.e(TAG, "onRecordError, audio no mic permit");
            onRecordError();
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ugcRecord, onSurfaceTextureAvailable, surfaceTexture = ");
        stringBuilder.append(surfaceTexture);
        stringBuilder.append(", mCapturing = ");
        stringBuilder.append(this.mCapturing);
        stringBuilder.append(", mStartPreview = ");
        stringBuilder.append(this.mStartPreview);
        TXCLog.i(str, stringBuilder.toString());
        if (this.mStartPreview && surfaceTexture != null) {
            if (!startCapture(surfaceTexture)) {
                callbackEvent(3, null);
            } else if (TXCAudioUGCRecorder.getInstance().isRecording()) {
                this.mInitCompelete = true;
                return;
            } else if (getRecordState() == -1) {
                callbackEvent(4, null);
            }
            this.mInitCompelete = true;
        }
    }

    public void onSurfaceTextureDestroy(SurfaceTexture surfaceTexture) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ugcRecord, onSurfaceTextureDestroy, surfaceTexture = ");
        stringBuilder.append(surfaceTexture);
        stringBuilder.append(", mCapturing = ");
        stringBuilder.append(this.mCapturing);
        TXCLog.i(str, stringBuilder.toString());
        if (this.mCustomProcessListener != null) {
            this.mCustomProcessListener.onTextureDestroyed();
        }
        if (this.mVideoPreprocessor != null) {
            this.mVideoPreprocessor.a();
        }
        if (this.mVideoEncoder != null) {
            this.mVideoEncoder.a();
            this.mVideoEncoder.a(null);
            this.mVideoEncoder = null;
        }
    }

    public int willAddWatermark(int i, int i2, int i3) {
        if (this.mCustomProcessListener != null) {
            i = this.mCustomProcessListener.onTextureCustomProcess(i, i2, i3);
        }
        if (this.mVideoView != null) {
            this.mVideoView.a(i, false, this.mConfig.s, i2, i3);
        }
        return i;
    }

    public void didProcessFrame(int i, int i2, int i3, long j) {
        if (this.mRecording) {
            encodeFrame(i, i2, i3);
        }
    }

    public void didDetectFacePoints(float[] fArr) {
        if (this.mCustomProcessListener != null) {
            this.mCustomProcessListener.onDetectFacePoints(fArr);
        }
    }

    public void onEncodeNAL(com.tencent.liteav.basic.g.b bVar, int i) {
        if (i == 0) {
            synchronized (this) {
                if (this.mMP4Muxer == null) {
                } else if (!(bVar == null || bVar.nalData == null)) {
                    if (this.mStartMuxer) {
                        recordVideoData(bVar, bVar.nalData);
                    } else if (bVar.nalType == 0) {
                        MediaFormat a = com.tencent.liteav.basic.util.b.a(bVar.nalData, this.mVideoWidth, this.mVideoHeight);
                        if (a != null) {
                            this.mMP4Muxer.a(a);
                            this.mMP4Muxer.a();
                            this.mStartMuxer = true;
                            this.mRecordStartTime = 0;
                            TXLog.i(TAG, "onEncodeNAL, mMP4Muxer.start(), mStartMuxer = true");
                        }
                        recordVideoData(bVar, bVar.nalData);
                    }
                }
            }
        } else {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onEncodeNAL error: ");
            stringBuilder.append(i);
            TXCLog.e(str, stringBuilder.toString());
        }
    }

    public void onEncodeFormat(MediaFormat mediaFormat) {
        synchronized (this) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onEncodeFormat: ");
            stringBuilder.append(mediaFormat.toString());
            Log.i(str, stringBuilder.toString());
            if (this.mMP4Muxer != null) {
                this.mMP4Muxer.a(mediaFormat);
                if (!this.mStartMuxer) {
                    this.mMP4Muxer.a();
                    this.mStartMuxer = true;
                    TXLog.i(TAG, "onEncodeFormat, mMP4Muxer.start(), mStartMuxer = true");
                }
            }
        }
    }

    private void recordVideoData(com.tencent.liteav.basic.g.b bVar, byte[] bArr) {
        if (this.mRecordStartTime == 0) {
            this.mRecordStartTime = bVar.pts;
        }
        int i = bVar.info == null ? bVar.nalType == 0 ? 1 : 0 : bVar.info.flags;
        if (onRecordProgress(bVar.pts - this.mRecordStartTime)) {
            this.mMP4Muxer.b(bArr, 0, bArr.length, bVar.pts * 1000, i);
        }
    }

    public int onTextureProcess(int i, float[] fArr) {
        if (this.mVideoPreprocessor != null) {
            int i2 = this.mConfig.g;
            int i3 = this.mConfig.h;
            int i4 = this.mCropWidth;
            int i5 = this.mCropHeight;
            if (this.mConfig.r == 2 || this.mConfig.r == 0) {
                i2 = this.mConfig.h;
                i3 = this.mConfig.g;
                i4 = this.mCropHeight;
                i5 = this.mCropWidth;
            }
            if (this.mDisplayType != 0) {
                this.mVideoPreprocessor.a(com.tencent.liteav.basic.util.b.a(this.mCameraCapture.e(), this.mCameraCapture.f(), this.mCropHeight, this.mCropWidth));
                this.mVideoPreprocessor.a(i4, i5);
                this.mVideoView.setRendMode(1);
            } else {
                this.mVideoPreprocessor.a(com.tencent.liteav.basic.util.b.a(this.mCameraCapture.e(), this.mCameraCapture.f(), this.mConfig.h, this.mConfig.g));
                this.mVideoPreprocessor.a(i2, i3);
                this.mVideoView.setRendMode(this.mRenderMode);
            }
            this.mVideoPreprocessor.a(false);
            this.mVideoPreprocessor.a(this.mCameraCapture.c());
            this.mVideoPreprocessor.a(fArr);
            this.mVideoPreprocessor.a(i, this.mCameraCapture.e(), this.mCameraCapture.f(), this.mCameraCapture.c(), 4, 0);
        }
        return 0;
    }
}
