package com.tencent.rtmp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
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
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.i;
import com.tencent.liteav.network.f;
import com.tencent.liteav.network.g;
import com.tencent.liteav.o;
import com.tencent.liteav.txcvodplayer.TextureRenderView;
import com.tencent.rtmp.TXLivePlayer.ITXSnapshotListener;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class TXVodPlayer implements a, g {
    public static final int PLAYER_TYPE_EXO = 1;
    public static final int PLAYER_TYPE_FFPLAY = 0;
    public static final String TAG = "TXVodPlayer";
    private boolean mAutoPlay = true;
    private int mBitrateIndex;
    private TXVodPlayConfig mConfig;
    private Context mContext;
    private boolean mEnableHWDec = false;
    private boolean mIsGetPlayInfo;
    private boolean mIsNeedClearLastImg = true;
    private ITXLivePlayListener mListener;
    private boolean mLoop;
    private boolean mMirror;
    private boolean mMute = false;
    private f mNetApi;
    private ITXVodPlayListener mNewListener;
    private String mPlayUrl = "";
    private o mPlayer;
    private float mRate = 1.0f;
    private int mRenderMode;
    private int mRenderRotation;
    private boolean mSnapshotRunning = false;
    protected float mStartTime;
    private Surface mSurface;
    private TXCloudVideoView mTXCloudVideoView;
    private TextureRenderView mTextureView;
    private String mToken;

    public TXVodPlayer(Context context) {
        TXCLog.init();
        this.mListener = null;
        this.mNewListener = null;
        this.mContext = context.getApplicationContext();
    }

    public void setConfig(TXVodPlayConfig tXVodPlayConfig) {
        this.mConfig = tXVodPlayConfig;
        if (this.mConfig == null) {
            this.mConfig = new TXVodPlayConfig();
        }
        if (this.mPlayer != null) {
            i p = this.mPlayer.p();
            if (p == null) {
                p = new i();
            }
            p.e = this.mConfig.mConnectRetryCount;
            p.f = this.mConfig.mConnectRetryInterval;
            p.q = this.mConfig.mTimeout;
            p.i = this.mEnableHWDec;
            p.m = this.mConfig.mCacheFolderPath;
            p.n = this.mConfig.mMaxCacheItems;
            p.o = this.mConfig.mPlayerType;
            p.p = this.mConfig.mHeaders;
            p.r = this.mConfig.enableAccurateSeek;
            p.s = this.mConfig.autoRotate;
            p.t = this.mConfig.smoothSwitchBitrate;
            p.u = this.mConfig.cacheMp4ExtName;
            p.v = this.mConfig.progressInterval;
            this.mPlayer.a(p);
        }
    }

    public void setPlayerView(TXCloudVideoView tXCloudVideoView) {
        this.mTXCloudVideoView = tXCloudVideoView;
        if (this.mPlayer != null) {
            this.mPlayer.a(tXCloudVideoView);
        }
    }

    public void setPlayerView(TextureRenderView textureRenderView) {
        this.mTextureView = textureRenderView;
        if (this.mPlayer != null) {
            this.mPlayer.a(textureRenderView);
        }
    }

    public void setSurface(Surface surface) {
        this.mSurface = surface;
        if (this.mPlayer != null) {
            this.mPlayer.a(this.mSurface);
        }
    }

    public int startPlay(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            return -1;
        }
        TXCDRApi.initCrashReport(this.mContext);
        int i = this.mBitrateIndex;
        stopPlay(this.mIsNeedClearLastImg);
        this.mBitrateIndex = i;
        if (this.mToken != null) {
            String path = Uri.parse(str).getPath();
            if (path != null) {
                String[] split = path.split("/");
                if (split.length > 0) {
                    i = str.lastIndexOf(split[split.length - 1]);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str.substring(0, i));
                    stringBuilder.append("voddrm.token.");
                    stringBuilder.append(this.mToken);
                    stringBuilder.append(".");
                    stringBuilder.append(str.substring(i));
                    str = stringBuilder.toString();
                }
            }
        }
        this.mPlayUrl = checkPlayUrl(str);
        TXCLog.d(TAG, "===========================================================================================================================================================");
        TXCLog.d(TAG, "===========================================================================================================================================================");
        str = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("=====  StartPlay url = ");
        stringBuilder2.append(this.mPlayUrl);
        stringBuilder2.append(" SDKVersion = ");
        stringBuilder2.append(TXCCommonUtil.getSDKID());
        stringBuilder2.append(" , ");
        stringBuilder2.append(TXCCommonUtil.getSDKVersionStr());
        stringBuilder2.append("    ======");
        TXCLog.d(str, stringBuilder2.toString());
        TXCLog.d(TAG, "===========================================================================================================================================================");
        TXCLog.d(TAG, "===========================================================================================================================================================");
        this.mPlayer = new o(this.mContext);
        updateConfig();
        if (this.mTXCloudVideoView != null) {
            this.mTXCloudVideoView.clearLog();
            this.mTXCloudVideoView.setVisibility(0);
            this.mPlayer.a(this.mTXCloudVideoView);
        } else if (this.mSurface != null) {
            this.mPlayer.a(this.mSurface);
        } else if (this.mTextureView != null) {
            this.mPlayer.a(this.mTextureView);
        }
        this.mPlayer.e(this.mBitrateIndex);
        this.mPlayer.a((a) this);
        this.mPlayer.c(this.mAutoPlay);
        this.mPlayer.c(this.mStartTime);
        this.mPlayer.a(this.mPlayUrl, 0);
        this.mPlayer.b(this.mMute);
        this.mPlayer.b(this.mRate);
        this.mPlayer.b(this.mRenderRotation);
        this.mPlayer.a(this.mRenderMode);
        this.mPlayer.d(this.mLoop);
        setMirror(this.mMirror);
        return 0;
    }

    public int startPlay(TXPlayerAuthBuilder tXPlayerAuthBuilder) {
        this.mNetApi = new f();
        this.mNetApi.a(tXPlayerAuthBuilder.isHttps);
        this.mNetApi.a((g) this);
        return this.mNetApi.a(tXPlayerAuthBuilder.appId, tXPlayerAuthBuilder.fileId, tXPlayerAuthBuilder.timeout, tXPlayerAuthBuilder.us, tXPlayerAuthBuilder.exper, tXPlayerAuthBuilder.sign);
    }

    private String checkPlayUrl(String str) {
        if (str.startsWith("http")) {
            try {
                byte[] bytes = str.getBytes("UTF-8");
                StringBuilder stringBuilder = new StringBuilder(bytes.length);
                int i = 0;
                while (i < bytes.length) {
                    int i2 = bytes[i] < (byte) 0 ? bytes[i] + 256 : bytes[i];
                    if (!(i2 <= 32 || i2 >= 127 || i2 == 34 || i2 == 37 || i2 == 60 || i2 == 62 || i2 == 91 || i2 == 125 || i2 == 92 || i2 == 93 || i2 == 94 || i2 == 96 || i2 == 123)) {
                        if (i2 != 124) {
                            stringBuilder.append((char) i2);
                            i++;
                        }
                    }
                    if (i2 == 37) {
                        TXCLog.w(TAG, "传入URL已转码");
                        return str;
                    }
                    stringBuilder.append(String.format("%%%02X", new Object[]{Integer.valueOf(i2)}));
                    i++;
                }
                str = stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str.trim();
    }

    public int stopPlay(boolean z) {
        if (z && this.mTXCloudVideoView != null) {
            this.mTXCloudVideoView.setVisibility(8);
        }
        if (this.mPlayer != null) {
            this.mPlayer.a(z);
        }
        this.mPlayUrl = "";
        if (this.mNetApi != null) {
            this.mNetApi.a(null);
            this.mNetApi = null;
        }
        this.mBitrateIndex = 0;
        this.mIsGetPlayInfo = false;
        return 0;
    }

    public boolean isPlaying() {
        return this.mPlayer != null ? this.mPlayer.c() : false;
    }

    public void pause() {
        if (this.mPlayer != null) {
            this.mPlayer.a();
        }
    }

    public void resume() {
        if (this.mPlayer != null) {
            this.mPlayer.b();
        }
    }

    public void seek(int i) {
        if (this.mPlayer != null) {
            this.mPlayer.a_(i);
        }
    }

    public void seek(float f) {
        if (this.mPlayer != null) {
            this.mPlayer.a(f);
        }
    }

    public float getCurrentPlaybackTime() {
        return this.mPlayer != null ? this.mPlayer.h() : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float getBufferDuration() {
        return this.mPlayer != null ? this.mPlayer.i() : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float getDuration() {
        return this.mPlayer != null ? this.mPlayer.j() : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float getPlayableDuration() {
        return this.mPlayer != null ? this.mPlayer.k() : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public int getWidth() {
        return this.mPlayer != null ? this.mPlayer.l() : 0;
    }

    public int getHeight() {
        return this.mPlayer != null ? this.mPlayer.m() : 0;
    }

    @Deprecated
    public void setPlayListener(ITXLivePlayListener iTXLivePlayListener) {
        this.mListener = iTXLivePlayListener;
    }

    public void setVodListener(ITXVodPlayListener iTXVodPlayListener) {
        this.mNewListener = iTXVodPlayListener;
    }

    public void setRenderMode(int i) {
        this.mRenderMode = i;
        if (this.mPlayer != null) {
            this.mPlayer.a(i);
        }
    }

    public void setRenderRotation(int i) {
        this.mRenderRotation = i;
        if (this.mPlayer != null) {
            this.mPlayer.b(i);
        }
    }

    public boolean enableHardwareDecode(boolean z) {
        if (z) {
            StringBuilder stringBuilder;
            if (VERSION.SDK_INT < 18) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("enableHardwareDecode failed, android system build.version = ");
                stringBuilder.append(VERSION.SDK_INT);
                stringBuilder.append(", the minimum build.version should be 18(android 4.3 or later)");
                TXCLog.e("HardwareDecode", stringBuilder.toString());
                return false;
            } else if (isAVCDecBlacklistDevices()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("enableHardwareDecode failed, MANUFACTURER = ");
                stringBuilder.append(Build.MANUFACTURER);
                stringBuilder.append(", MODEL");
                stringBuilder.append(Build.MODEL);
                TXCLog.e("HardwareDecode", stringBuilder.toString());
                return false;
            }
        }
        this.mEnableHWDec = z;
        updateConfig();
        return true;
    }

    public void setMute(boolean z) {
        this.mMute = z;
        if (this.mPlayer != null) {
            this.mPlayer.b(z);
        }
    }

    public void setAutoPlay(boolean z) {
        this.mAutoPlay = z;
        if (this.mPlayer != null) {
            this.mPlayer.c(z);
        }
    }

    public void setRate(float f) {
        this.mRate = f;
        if (this.mPlayer != null) {
            this.mPlayer.b(f);
        }
    }

    public int getBitrateIndex() {
        return this.mPlayer != null ? this.mPlayer.n() : 0;
    }

    public void setBitrateIndex(int i) {
        if (this.mPlayer != null) {
            this.mPlayer.e(i);
        }
        this.mBitrateIndex = i;
    }

    public ArrayList<TXBitrateItem> getSupportedBitrates() {
        if (this.mPlayer != null) {
            return this.mPlayer.o();
        }
        return new ArrayList();
    }

    public void snapshot(ITXSnapshotListener iTXSnapshotListener) {
        if (!this.mSnapshotRunning && iTXSnapshotListener != null) {
            this.mSnapshotRunning = true;
            TextureView d = this.mPlayer != null ? this.mPlayer.d() : null;
            if (d != null) {
                Bitmap createBitmap;
                Bitmap bitmap = d.getBitmap();
                if (bitmap != null) {
                    Matrix transform = d.getTransform(null);
                    if (this.mMirror) {
                        transform.postScale(-1.0f, 1.0f);
                    }
                    createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), transform, true);
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

    public void setMirror(boolean z) {
        if (this.mPlayer != null) {
            this.mPlayer.e(z);
        }
        this.mMirror = z;
    }

    public void setStartTime(float f) {
        this.mStartTime = f;
    }

    public void onNotifyEvent(int i, Bundle bundle) {
        if (i == 15001) {
            if (this.mTXCloudVideoView != null) {
                this.mTXCloudVideoView.setLogText(bundle, null, 0);
            }
            if (this.mListener != null) {
                this.mListener.onNetStatus(bundle);
            }
            if (this.mNewListener != null) {
                this.mNewListener.onNetStatus(this, bundle);
                return;
            }
            return;
        }
        if (this.mTXCloudVideoView != null) {
            this.mTXCloudVideoView.setLogText(null, bundle, i);
        }
        if (this.mListener != null) {
            this.mListener.onPlayEvent(i, bundle);
        }
        if (this.mNewListener != null) {
            this.mNewListener.onPlayEvent(this, i, bundle);
        }
    }

    private boolean isAVCDecBlacklistDevices() {
        return Build.MANUFACTURER.equalsIgnoreCase("HUAWEI") && Build.MODEL.equalsIgnoreCase("Che2-TL00");
    }

    private void postBitmapToMainThread(final ITXSnapshotListener iTXSnapshotListener, final Bitmap bitmap) {
        if (iTXSnapshotListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (iTXSnapshotListener != null) {
                        iTXSnapshotListener.onSnapshot(bitmap);
                    }
                    TXVodPlayer.this.mSnapshotRunning = false;
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void updateConfig() {
        setConfig(this.mConfig);
    }

    public void onNetSuccess(f fVar) {
        if (fVar == this.mNetApi) {
            com.tencent.liteav.network.i a = fVar.a();
            if (!this.mIsGetPlayInfo) {
                startPlay(a.a());
            }
            this.mIsGetPlayInfo = false;
            Bundle bundle = new Bundle();
            bundle.putInt("EVT_ID", TXLiveConstants.PLAY_EVT_GET_PLAYINFO_SUCC);
            bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
            bundle.putString(TXLiveConstants.EVT_DESCRIPTION, "文件信息请求成功");
            bundle.putString(TXLiveConstants.EVT_PLAY_URL, a.a());
            bundle.putString(TXLiveConstants.EVT_PLAY_COVER_URL, a.b());
            bundle.putString(TXLiveConstants.EVT_PLAY_NAME, a.f());
            bundle.putString(TXLiveConstants.EVT_PLAY_DESCRIPTION, a.g());
            if (a.d() != null) {
                bundle.putInt(TXLiveConstants.EVT_PLAY_DURATION, a.d().c());
            }
            onNotifyEvent(TXLiveConstants.PLAY_EVT_GET_PLAYINFO_SUCC, bundle);
        }
    }

    public void onNetFailed(f fVar, String str, int i) {
        if (fVar == this.mNetApi) {
            this.mIsGetPlayInfo = false;
            Bundle bundle = new Bundle();
            bundle.putInt("EVT_ID", TXLiveConstants.PLAY_EVT_GET_PLAYINFO_SUCC);
            bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
            bundle.putString(TXLiveConstants.EVT_DESCRIPTION, str);
            bundle.putInt("EVT_PARAM1", i);
            onNotifyEvent(TXLiveConstants.PLAY_ERR_GET_PLAYINFO_FAIL, bundle);
        }
    }

    public void setToken(String str) {
        this.mToken = str;
    }

    public void setLoop(boolean z) {
        this.mLoop = z;
        if (this.mPlayer != null) {
            this.mPlayer.d(this.mLoop);
        }
    }

    public boolean isLoop() {
        return this.mLoop;
    }
}
