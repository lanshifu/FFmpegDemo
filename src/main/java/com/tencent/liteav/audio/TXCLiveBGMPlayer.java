package com.tencent.liteav.audio;

import android.content.Context;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import com.tencent.liteav.audio.impl.TXCTraeJNI;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.b;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TXCLiveBGMPlayer implements Runnable {
    private static final int PLAY_ERR_AUDIO_TRACK = -3;
    private static final int PLAY_ERR_AUDIO_TRACK_PLAY = -4;
    private static final int PLAY_ERR_FILE_NOTFOUND = -2;
    private static final int PLAY_ERR_OPEN = -1;
    private static final int PLAY_SUCCESS = 0;
    private static final String TAG;
    private static TXCLiveBGMPlayer instance;
    private int mAECType = TXEAudioDef.TXE_AEC_NONE;
    private Context mContext = null;
    private String mFilePath = null;
    private boolean mIsPause = false;
    private boolean mIsRunning = false;
    private float mPitch = CropImageView.DEFAULT_ASPECT_RATIO;
    private Thread mThread = null;
    private WeakReference<e> mWeakListener = null;

    private native int nativeGetBitsPerChannel();

    private native int nativeGetChannels();

    private native long nativeGetCurDurationMS();

    private native long nativeGetCurPtsMS();

    private native long nativeGetDurationMS(String str);

    private native int nativeGetSampleRate();

    private native void nativePause();

    private native int nativeRead(byte[] bArr, int i);

    private native void nativeResume();

    private native void nativeSetPitch(float f);

    private native void nativeSetVolume(float f);

    private native boolean nativeStartPlay(String str, TXCLiveBGMPlayer tXCLiveBGMPlayer);

    private native void nativeStopPlay();

    static {
        b.e();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(TXCLiveBGMPlayer.class.getSimpleName());
        TAG = stringBuilder.toString();
    }

    public static TXCLiveBGMPlayer getInstance() {
        if (instance == null) {
            synchronized (TXCLiveBGMPlayer.class) {
                if (instance == null) {
                    instance = new TXCLiveBGMPlayer();
                }
            }
        }
        return instance;
    }

    private TXCLiveBGMPlayer() {
    }

    public synchronized void setOnPlayListener(e eVar) {
        if (eVar == null) {
            try {
                this.mWeakListener = null;
            } catch (Throwable th) {
            }
        }
        this.mWeakListener = new WeakReference(eVar);
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public boolean startPlay(String str, int i) {
        if (str == null || str.isEmpty()) {
            TXCLog.e(TAG, "start live bgm failed! invalid params!");
            return false;
        }
        stopPlay();
        this.mAECType = i;
        this.mFilePath = str;
        this.mIsRunning = true;
        onPlayStart();
        if (nativeStartPlay(this.mFilePath, this)) {
            if (this.mAECType == TXEAudioDef.TXE_AEC_TRAE) {
                TXCTraeJNI.traeStartPlay(this.mContext);
            } else if (this.mThread == null) {
                this.mThread = new Thread(this, "BGMPlayer");
                this.mThread.start();
            }
            String str2 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startPlay filePath = ");
            stringBuilder.append(str);
            TXCLog.i(str2, stringBuilder.toString());
            return true;
        }
        onPlayEnd(-1);
        return false;
    }

    public void stopPlay() {
        this.mIsRunning = false;
        long currentTimeMillis = System.currentTimeMillis();
        if (!(this.mThread == null || !this.mThread.isAlive() || Thread.currentThread().getId() == this.mThread.getId())) {
            try {
                this.mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.mThread = null;
        nativeStopPlay();
        TXCTraeJNI.traeStopPlay();
        this.mIsPause = false;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stopBGMPlay cost(MS): ");
        stringBuilder.append(System.currentTimeMillis() - currentTimeMillis);
        TXCLog.i(str, stringBuilder.toString());
    }

    public boolean isRunning() {
        return this.mIsRunning && !this.mIsPause;
    }

    public boolean isPlaying() {
        return this.mIsRunning;
    }

    public void switchAecType(int i) {
        if (!this.mIsRunning) {
            TXCLog.w(TAG, "未开始播放BGM，不能切换AEC Type");
        } else if (this.mAECType == i) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("无需切换AEC Type. aecType = ");
            stringBuilder.append(this.mAECType);
            TXCLog.i(str, stringBuilder.toString());
        } else {
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("切换AEC Type为 ");
            stringBuilder2.append(i);
            TXCLog.i(str2, stringBuilder2.toString());
            this.mAECType = i;
            if (this.mAECType == TXEAudioDef.TXE_AEC_TRAE) {
                if (!(this.mThread == null || !this.mThread.isAlive() || Thread.currentThread().getId() == this.mThread.getId())) {
                    try {
                        this.mThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.mThread = null;
            } else if (this.mThread == null) {
                this.mThread = new Thread(this, "BGMPlayer");
                this.mThread.start();
            }
        }
    }

    public void pause() {
        TXCLog.i(TAG, "pause");
        this.mIsPause = true;
        nativePause();
    }

    public void resume() {
        TXCLog.i(TAG, "resume");
        this.mIsPause = false;
        nativeResume();
    }

    public void setVolume(float f) {
        nativeSetVolume(f);
    }

    public long getMusicDuration(String str) {
        return nativeGetDurationMS(str);
    }

    public void setPitch(float f) {
        this.mPitch = f;
        nativeSetPitch(f);
    }

    private void onPlayStart() {
        final e eVar;
        synchronized (this) {
            eVar = this.mWeakListener != null ? (e) this.mWeakListener.get() : null;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (eVar != null) {
                    eVar.a();
                }
            }
        });
    }

    private void onPlayEnd(final int i) {
        final e eVar;
        synchronized (this) {
            eVar = this.mWeakListener != null ? (e) this.mWeakListener.get() : null;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (eVar != null) {
                    eVar.a(i);
                }
            }
        });
    }

    private void onPlayProgress(long j, long j2) {
        e eVar;
        synchronized (this) {
            eVar = this.mWeakListener != null ? (e) this.mWeakListener.get() : null;
        }
        if (eVar != null) {
            eVar.a(j, j2);
        }
    }

    private void nativeOPlayProgress(long j, long j2) {
        onPlayProgress(j, j2);
        if (j == j2) {
            onPlayEnd(0);
        }
    }

    public void run() {
        String str;
        StringBuilder stringBuilder;
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        AudioTrack audioTrack = null;
        if (this.mFilePath == null || this.mFilePath.isEmpty()) {
            i = -2;
        } else {
            int nativeGetSampleRate = nativeGetSampleRate();
            int nativeGetChannels = nativeGetChannels();
            int nativeGetBitsPerChannel = nativeGetBitsPerChannel();
            int i2 = 3;
            int i3 = nativeGetChannels == 1 ? 2 : 3;
            if (nativeGetBitsPerChannel != 8) {
                i2 = 2;
            }
            try {
                AudioTrack audioTrack2 = new AudioTrack(3, nativeGetSampleRate, i3, i2, AudioTrack.getMinBufferSize(nativeGetSampleRate, i3, i2), 1);
                try {
                    audioTrack2.play();
                    nativeGetChannels *= IjkMediaMeta.FF_PROFILE_H264_INTRA;
                    byte[] bArr = new byte[nativeGetChannels];
                    while (this.mIsRunning && !Thread.interrupted() && this.mAECType != TXEAudioDef.TXE_AEC_TRAE) {
                        nativeGetBitsPerChannel = nativeRead(bArr, nativeGetChannels);
                        if (nativeGetBitsPerChannel < 0) {
                            onPlayProgress(nativeGetCurDurationMS(), nativeGetCurDurationMS());
                            break;
                        } else if (nativeGetBitsPerChannel != 0) {
                            audioTrack2.write(bArr, 0, nativeGetBitsPerChannel);
                            onPlayProgress(nativeGetCurPtsMS(), nativeGetCurDurationMS());
                        } else if (this.mIsPause) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("AudioTrack play Exception: ");
                    stringBuilder.append(e2.getMessage());
                    TXCLog.e(str, stringBuilder.toString());
                    i = -4;
                }
                audioTrack = audioTrack2;
            } catch (Exception e22) {
                e22.printStackTrace();
                str = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("new AudioTrack Exception: ");
                stringBuilder2.append(e22.getMessage());
                TXCLog.e(str, stringBuilder2.toString());
                i = -3;
            }
        }
        if (audioTrack != null) {
            try {
                audioTrack.pause();
                audioTrack.flush();
                audioTrack.stop();
                audioTrack.release();
            } catch (Exception e222) {
                e222.printStackTrace();
            }
        }
        String str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Live BGM player play time: ");
        stringBuilder.append(System.currentTimeMillis() - currentTimeMillis);
        TXCLog.i(str2, stringBuilder.toString());
        if (this.mIsRunning) {
            onPlayEnd(i);
        }
    }
}
