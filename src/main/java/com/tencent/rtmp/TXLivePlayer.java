package com.tencent.rtmp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import com.tencent.liteav.basic.d.a;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import com.tencent.liteav.i;
import com.tencent.liteav.m;
import com.tencent.liteav.s;
import com.tencent.liteav.t;
import com.tencent.liteav.u;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.ugc.TXRecordCommon.ITXVideoRecordListener;

public class TXLivePlayer implements a {
    public static final int PLAY_TYPE_LIVE_FLV = 1;
    public static final int PLAY_TYPE_LIVE_RTMP = 0;
    public static final int PLAY_TYPE_LIVE_RTMP_ACC = 5;
    public static final int PLAY_TYPE_LOCAL_VIDEO = 6;
    public static final int PLAY_TYPE_VOD_FLV = 2;
    public static final int PLAY_TYPE_VOD_HLS = 3;
    public static final int PLAY_TYPE_VOD_MP4 = 4;
    public static final String TAG = "TXLivePlayer";
    private ITXAudioRawDataListener mAudioRawDataListener;
    private int mAudioRoute = 0;
    private boolean mAutoPlay = true;
    private TXLivePlayConfig mConfig;
    private Context mContext;
    private int mCurrentPlayType = -1;
    private boolean mEnableHWDec = false;
    private boolean mIsNeedClearLastImg = true;
    private boolean mIsShiftPlaying;
    private ITXLivePlayListener mListener;
    private String mLivePlayUrl;
    private boolean mMute = false;
    private String mPlayUrl = "";
    private s mPlayer;
    private long mProgressStartTime;
    private float mRate = 1.0f;
    private int mRenderMode;
    private int mRenderRotation;
    private boolean mSnapshotRunning = false;
    private Surface mSurface;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    private TXCloudVideoView mTXCloudVideoView;
    private m mTimeShiftUtil;
    private ITXVideoRawDataListener mVideoRawDataListener = null;

    public interface ITXAudioRawDataListener {
        void onAudioInfoChanged(int i, int i2, int i3);

        void onPcmDataAvailable(byte[] bArr, long j);
    }

    public interface ITXSnapshotListener {
        void onSnapshot(Bitmap bitmap);
    }

    public interface ITXVideoRawDataListener {
        void onVideoRawDataAvailable(byte[] bArr, int i, int i2, int i3);
    }

    public TXLivePlayer(Context context) {
        TXCLog.init();
        this.mListener = null;
        this.mContext = context.getApplicationContext();
    }

    public void setConfig(TXLivePlayConfig tXLivePlayConfig) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setConfig ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        this.mConfig = tXLivePlayConfig;
        if (this.mConfig == null) {
            this.mConfig = new TXLivePlayConfig();
        }
        if (this.mPlayer != null) {
            i p = this.mPlayer.p();
            if (p == null) {
                p = new i();
            }
            p.a = this.mConfig.mCacheTime;
            p.g = this.mConfig.mAutoAdjustCacheTime;
            p.c = this.mConfig.mMinAutoAdjustCacheTime;
            p.b = this.mConfig.mMaxAutoAdjustCacheTime;
            p.d = this.mConfig.mVideoBlockThreshold;
            p.e = this.mConfig.mConnectRetryCount;
            p.f = this.mConfig.mConnectRetryInterval;
            p.h = this.mConfig.mEnableAec;
            p.j = this.mConfig.mEnableNearestIP;
            p.l = this.mConfig.mRtmpChannelType;
            p.i = this.mEnableHWDec;
            p.m = this.mConfig.mCacheFolderPath;
            p.n = this.mConfig.mMaxCacheItems;
            p.k = this.mConfig.mEnableMessage;
            p.p = this.mConfig.mHeaders;
            this.mPlayer.a(p);
        }
    }

    public void setPlayerView(TXCloudVideoView tXCloudVideoView) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setPlayerView old view : ");
        stringBuilder.append(this.mTXCloudVideoView);
        stringBuilder.append(", new view : ");
        stringBuilder.append(tXCloudVideoView);
        stringBuilder.append(", ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        this.mTXCloudVideoView = tXCloudVideoView;
        if (this.mPlayer != null) {
            this.mPlayer.a(tXCloudVideoView);
        }
    }

    public void setSurface(Surface surface) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setSurface old : ");
        stringBuilder.append(this.mSurface);
        stringBuilder.append(", new : ");
        stringBuilder.append(surface);
        stringBuilder.append(", ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        this.mSurface = surface;
        if (this.mPlayer != null) {
            this.mPlayer.a(this.mSurface);
        }
    }

    public void setSurfaceSize(int i, int i2) {
        this.mSurfaceWidth = i;
        this.mSurfaceHeight = i2;
        if (this.mPlayer != null) {
            this.mPlayer.a(i, i2);
        }
    }

    public int startPlay(String str, int i) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api startPlay ");
        stringBuilder.append(this);
        TXCLog.d(str2, stringBuilder.toString());
        StringBuilder stringBuilder2;
        if (TextUtils.isEmpty(str)) {
            str = TAG;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("start play error when url is empty ");
            stringBuilder2.append(this);
            TXCLog.e(str, stringBuilder2.toString());
            return -1;
        }
        StringBuilder stringBuilder3;
        if (!TextUtils.isEmpty(this.mPlayUrl) && isPlaying()) {
            if (this.mPlayUrl.equalsIgnoreCase(str)) {
                str = TAG;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("start play error when new url is the same with old url  ");
                stringBuilder2.append(this);
                TXCLog.e(str, stringBuilder2.toString());
                return -1;
            }
            str2 = TAG;
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(" stop old play when new url is not the same with old url  ");
            stringBuilder3.append(this);
            TXCLog.w(str2, stringBuilder3.toString());
            if (this.mPlayer != null) {
                this.mPlayer.a(false);
            }
            this.mPlayUrl = "";
        }
        TXCDRApi.initCrashReport(this.mContext);
        TXCLog.d(TAG, "===========================================================================================================================================================");
        TXCLog.d(TAG, "===========================================================================================================================================================");
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append("=====  StartPlay url = ");
        stringBuilder3.append(str);
        stringBuilder3.append(" playType = ");
        stringBuilder3.append(i);
        stringBuilder3.append(" SDKVersion = ");
        stringBuilder3.append(TXCCommonUtil.getSDKID());
        stringBuilder3.append(" , ");
        stringBuilder3.append(TXCCommonUtil.getSDKVersionStr());
        stringBuilder3.append("    ======");
        TXCLog.d(str2, stringBuilder3.toString());
        TXCLog.d(TAG, "===========================================================================================================================================================");
        TXCLog.d(TAG, "===========================================================================================================================================================");
        if (this.mCurrentPlayType == -1 || this.mCurrentPlayType != i) {
            this.mPlayer = u.a(this.mContext, i);
        }
        this.mCurrentPlayType = i;
        if (this.mPlayer == null) {
            return -2;
        }
        this.mPlayUrl = checkPlayUrl(str, i);
        setConfig(this.mConfig);
        if (this.mTXCloudVideoView != null) {
            this.mTXCloudVideoView.clearLog();
            this.mTXCloudVideoView.setVisibility(0);
        }
        this.mPlayer.a(this.mTXCloudVideoView);
        this.mPlayer.a((a) this);
        this.mPlayer.c(this.mAutoPlay);
        if (this.mSurface != null) {
            this.mPlayer.a(this.mSurface);
            this.mPlayer.a(this.mSurfaceWidth, this.mSurfaceHeight);
        }
        this.mPlayer.a(this.mPlayUrl, i);
        this.mPlayer.b(this.mMute);
        this.mPlayer.b(this.mRate);
        this.mPlayer.b(this.mRenderRotation);
        this.mPlayer.a(this.mRenderMode);
        setAudioRoute(this.mAudioRoute);
        this.mPlayer.a(this.mAudioRawDataListener);
        setVideoRawDataListener(this.mVideoRawDataListener);
        if (this.mPlayer.f()) {
            this.mLivePlayUrl = this.mPlayUrl;
            this.mProgressStartTime = this.mTimeShiftUtil != null ? this.mTimeShiftUtil.a() : 0;
            if (this.mProgressStartTime > 0) {
                this.mPlayer.g();
            }
        }
        return 0;
    }

    public int switchStream(String str) {
        return this.mPlayer != null ? this.mPlayer.a(str) : -1;
    }

    public int stopPlay(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api stopPlay ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        if (z && this.mTXCloudVideoView != null) {
            this.mTXCloudVideoView.setVisibility(8);
        }
        if (this.mPlayer != null) {
            this.mPlayer.a(z);
        }
        this.mPlayUrl = "";
        this.mProgressStartTime = 0;
        this.mTimeShiftUtil = null;
        this.mIsShiftPlaying = false;
        return 0;
    }

    public boolean isPlaying() {
        return this.mPlayer != null ? this.mPlayer.c() : false;
    }

    public void pause() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api pause ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mPlayer != null) {
            TXCLog.w(TAG, "pause play");
            this.mPlayer.a();
        }
    }

    public void resume() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api resume ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mPlayer != null) {
            TXCLog.w(TAG, "resume play");
            this.mPlayer.b();
            setAudioRoute(this.mAudioRoute);
        }
    }

    public void seek(int i) {
        TXCLog.d(TAG, "liteav_api ");
        if (this.mPlayer == null) {
            return;
        }
        if (this.mPlayer.f() || this.mIsShiftPlaying) {
            CharSequence a = this.mTimeShiftUtil != null ? this.mTimeShiftUtil.a((long) i) : "";
            if (!TextUtils.isEmpty(a)) {
                this.mIsShiftPlaying = startPlay(a, 3) == 0;
                if (this.mIsShiftPlaying) {
                    this.mProgressStartTime = (long) (i * 1000);
                    return;
                }
                return;
            } else if (this.mListener != null) {
                this.mListener.onPlayEvent(TXLiveConstants.PLAY_ERR_NET_DISCONNECT, new Bundle());
                return;
            } else {
                return;
            }
        }
        this.mPlayer.a_(i);
    }

    public int prepareLiveSeek(String str, int i) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api prepareLiveSeek ");
        stringBuilder.append(this);
        TXCLog.d(str2, stringBuilder.toString());
        if (this.mTimeShiftUtil == null) {
            this.mTimeShiftUtil = new m(this.mContext);
        }
        return this.mTimeShiftUtil != null ? this.mTimeShiftUtil.a(this.mPlayUrl, str, i, new m.a() {
            public void onLiveTime(long j) {
                TXLivePlayer.this.mProgressStartTime = j;
                if (TXLivePlayer.this.mPlayer != null) {
                    TXLivePlayer.this.mPlayer.g();
                }
            }
        }) : -1;
    }

    public int resumeLive() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api resumeLive ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        if (!this.mIsShiftPlaying) {
            return -1;
        }
        this.mIsShiftPlaying = false;
        return startPlay(this.mLivePlayUrl, 1);
    }

    public void setPlayListener(ITXLivePlayListener iTXLivePlayListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setPlayListener ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        this.mListener = iTXLivePlayListener;
    }

    public void setVideoRecordListener(ITXVideoRecordListener iTXVideoRecordListener) {
        TXCLog.d(TAG, "liteav_api setVideoRecordListener");
        if (this.mPlayer != null) {
            this.mPlayer.a(iTXVideoRecordListener);
        }
    }

    public int startRecord(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api startRecord ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        if (VERSION.SDK_INT < 18) {
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("API levl is too low (record need 18, current is");
            stringBuilder2.append(VERSION.SDK_INT);
            stringBuilder2.append(")");
            TXCLog.e(str2, stringBuilder2.toString());
            return -3;
        } else if (!isPlaying()) {
            TXCLog.e(TAG, "startRecord: there is no playing stream");
            return -1;
        } else if (this.mPlayer != null) {
            return this.mPlayer.c(i);
        } else {
            return -1;
        }
    }

    public int stopRecord() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api stopRecord ");
        stringBuilder.append(this);
        TXCLog.d(str, stringBuilder.toString());
        return this.mPlayer != null ? this.mPlayer.e() : -1;
    }

    public void setRenderMode(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setRenderMode ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        this.mRenderMode = i;
        if (this.mPlayer != null) {
            this.mPlayer.a(i);
        }
    }

    public void setRenderRotation(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setRenderRotation ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        this.mRenderRotation = i;
        if (this.mPlayer != null) {
            this.mPlayer.b(i);
        }
    }

    public boolean enableHardwareDecode(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api enableHardwareDecode ");
        stringBuilder.append(z);
        TXCLog.d(str, stringBuilder.toString());
        if (z) {
            StringBuilder stringBuilder2;
            if (VERSION.SDK_INT < 18) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("enableHardwareDecode failed, android system build.version = ");
                stringBuilder2.append(VERSION.SDK_INT);
                stringBuilder2.append(", the minimum build.version should be 18(android 4.3 or later)");
                TXCLog.e("HardwareDecode", stringBuilder2.toString());
                return false;
            } else if (isAVCDecBlacklistDevices()) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("enableHardwareDecode failed, MANUFACTURER = ");
                stringBuilder2.append(Build.MANUFACTURER);
                stringBuilder2.append(", MODEL");
                stringBuilder2.append(Build.MODEL);
                TXCLog.e("HardwareDecode", stringBuilder2.toString());
                return false;
            }
        }
        this.mEnableHWDec = z;
        if (this.mPlayer != null) {
            i p = this.mPlayer.p();
            if (p == null) {
                p = new i();
            }
            p.i = this.mEnableHWDec;
            this.mPlayer.a(p);
        }
        return true;
    }

    public void setMute(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setMute ");
        stringBuilder.append(z);
        TXCLog.d(str, stringBuilder.toString());
        this.mMute = z;
        if (this.mPlayer != null) {
            this.mPlayer.b(z);
        }
    }

    public void setAutoPlay(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setAutoPlay ");
        stringBuilder.append(z);
        TXCLog.d(str, stringBuilder.toString());
        this.mAutoPlay = z;
    }

    public void setRate(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setRate ");
        stringBuilder.append(f);
        TXCLog.d(str, stringBuilder.toString());
        this.mRate = f;
        if (this.mPlayer != null) {
            this.mPlayer.b(f);
        }
    }

    public void setAudioRoute(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setAudioRoute ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        this.mAudioRoute = i;
        if (this.mPlayer != null) {
            this.mPlayer.a(this.mContext, this.mAudioRoute);
        }
    }

    public void onNotifyEvent(int i, Bundle bundle) {
        if (i == 15001) {
            if (this.mTXCloudVideoView != null) {
                this.mTXCloudVideoView.setLogText(bundle, null, 0);
            }
            if (this.mListener != null) {
                this.mListener.onNetStatus(bundle);
            }
        } else if (i == TXLiveConstants.PLAY_EVT_PLAY_PROGRESS) {
            long j = ((long) bundle.getInt(TXLiveConstants.EVT_PLAY_PROGRESS_MS)) + this.mProgressStartTime;
            if (j > 0) {
                bundle.putInt(TXLiveConstants.EVT_PLAY_PROGRESS, (int) (j / 1000));
                bundle.putInt(TXLiveConstants.EVT_PLAY_PROGRESS_MS, (int) j);
                if (this.mListener != null) {
                    this.mListener.onPlayEvent(i, bundle);
                }
            }
        } else {
            if (this.mTXCloudVideoView != null) {
                this.mTXCloudVideoView.setLogText(null, bundle, i);
            }
            if (this.mListener != null) {
                this.mListener.onPlayEvent(i, bundle);
            }
        }
    }

    public boolean addVideoRawData(byte[] bArr) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api addVideoRawData ");
        stringBuilder.append(bArr);
        TXCLog.d(str, stringBuilder.toString());
        if (this.mPlayUrl == null || this.mPlayUrl.isEmpty()) {
            return false;
        }
        if (this.mEnableHWDec) {
            TXLog.e(TAG, "can not addVideoRawData because of hw decode has set!");
            return false;
        } else if (this.mPlayer == null) {
            TXCLog.e(TAG, "player hasn't created or not instanceof live player");
            return false;
        } else {
            this.mPlayer.a(bArr);
            return true;
        }
    }

    public void setVideoRawDataListener(final ITXVideoRawDataListener iTXVideoRawDataListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setVideoRawDataListener ");
        stringBuilder.append(iTXVideoRawDataListener);
        TXCLog.d(str, stringBuilder.toString());
        this.mVideoRawDataListener = iTXVideoRawDataListener;
        if (this.mPlayer != null) {
            if (iTXVideoRawDataListener != null) {
                this.mPlayer.a(new t() {
                    public void onVideoRawDataAvailable(byte[] bArr, int i, int i2, int i3) {
                        iTXVideoRawDataListener.onVideoRawDataAvailable(bArr, i, i2, i3);
                    }
                });
            } else {
                this.mPlayer.a(null);
            }
        }
    }

    public void snapshot(ITXSnapshotListener iTXSnapshotListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api snapshot ");
        stringBuilder.append(iTXSnapshotListener);
        TXCLog.d(str, stringBuilder.toString());
        if (!this.mSnapshotRunning && iTXSnapshotListener != null) {
            this.mSnapshotRunning = true;
            TextureView d = this.mPlayer != null ? this.mPlayer.d() : null;
            if (d != null) {
                Bitmap bitmap;
                Bitmap createBitmap;
                try {
                    bitmap = d.getBitmap();
                } catch (OutOfMemoryError unused) {
                    bitmap = null;
                }
                if (bitmap != null) {
                    createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), d.getTransform(null), true);
                    bitmap.recycle();
                } else {
                    createBitmap = bitmap;
                }
                postBitmapToMainThread(iTXSnapshotListener, createBitmap);
            } else {
                this.mSnapshotRunning = false;
            }
        }
    }

    public void setAudioRawDataListener(ITXAudioRawDataListener iTXAudioRawDataListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("liteav_api setAudioRawDataListener ");
        stringBuilder.append(iTXAudioRawDataListener);
        TXCLog.d(str, stringBuilder.toString());
        this.mAudioRawDataListener = iTXAudioRawDataListener;
        if (this.mPlayer != null) {
            this.mPlayer.a(iTXAudioRawDataListener);
        }
    }

    private boolean isAVCDecBlacklistDevices() {
        return Build.MANUFACTURER.equalsIgnoreCase("HUAWEI") && Build.MODEL.equalsIgnoreCase("Che2-TL00");
    }

    private String checkPlayUrl(String str, int i) {
        if (i != 6) {
            try {
                byte[] bytes = str.getBytes("UTF-8");
                StringBuilder stringBuilder = new StringBuilder(bytes.length);
                int i2 = 0;
                while (i2 < bytes.length) {
                    int i3 = bytes[i2] < (byte) 0 ? bytes[i2] + 256 : bytes[i2];
                    if (!(i3 <= 32 || i3 >= 127 || i3 == 34 || i3 == 37 || i3 == 60 || i3 == 62 || i3 == 91 || i3 == 125 || i3 == 92 || i3 == 93 || i3 == 94 || i3 == 96 || i3 == 123)) {
                        if (i3 != 124) {
                            stringBuilder.append((char) i3);
                            i2++;
                        }
                    }
                    stringBuilder.append(String.format("%%%02X", new Object[]{Integer.valueOf(i3)}));
                    i2++;
                }
                str = stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str.trim();
    }

    private void postBitmapToMainThread(final ITXSnapshotListener iTXSnapshotListener, final Bitmap bitmap) {
        if (iTXSnapshotListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (iTXSnapshotListener != null) {
                        iTXSnapshotListener.onSnapshot(bitmap);
                    }
                    TXLivePlayer.this.mSnapshotRunning = false;
                }
            });
        }
    }
}
