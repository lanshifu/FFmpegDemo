package com.tencent.liteav.audio;

import android.media.AudioTrack;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.b;
import java.lang.ref.WeakReference;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TXCUGCBGMPlayer implements Runnable {
    private static final int PLAY_ERR_AUDIO_TRACK = -3;
    private static final int PLAY_ERR_AUDIO_TRACK_PLAY = -4;
    private static final int PLAY_ERR_FILE_NOTFOUND = -2;
    private static final int PLAY_ERR_OPEN = -1;
    private static final int PLAY_SUCCESS = 0;
    private static final String TAG = "AudioCenter:TXCUGCBGMPlayer";
    private static TXCUGCBGMPlayer instance;
    private long mEndTimeMS = 0;
    private String mFilePath = null;
    private boolean mIsPause = false;
    private boolean mIsRunning = false;
    private long mSeekBytes = 0;
    private float mSpeedRate = 1.0f;
    private long mStartTimeMS = 0;
    private Thread mThread = null;
    private float mVolume = 1.0f;
    private WeakReference<e> mWeakListener = null;

    private native int nativeGetBitsPerChannel();

    private native int nativeGetChannels();

    private native long nativeGetCurDurationMS();

    private native long nativeGetCurPosition();

    private native long nativeGetCurPtsMS();

    private static native long nativeGetDurationMS(String str);

    private native int nativeGetSampleRate();

    private native void nativePause();

    private native void nativePlayFromTime(long j, long j2);

    private native int nativeRead(byte[] bArr, int i);

    private native void nativeResume();

    private native void nativeSeekBytes(long j);

    private native void nativeSetSpeedRate(float f);

    private native void nativeSetVolume(float f);

    private native boolean nativeStartPlay(String str);

    private native void nativeStopPlay();

    static {
        b.e();
    }

    public static TXCUGCBGMPlayer getInstance() {
        if (instance == null) {
            synchronized (TXCUGCBGMPlayer.class) {
                if (instance == null) {
                    instance = new TXCUGCBGMPlayer();
                }
            }
        }
        return instance;
    }

    private TXCUGCBGMPlayer() {
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

    public void startPlay(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startPlay:");
        stringBuilder.append(str);
        TXCLog.i(str2, stringBuilder.toString());
        if (str != null && !str.isEmpty()) {
            if (this.mIsRunning) {
                TXCLog.w(TAG, "BGM正在播放中，将重新启动");
                stopPlay();
            }
            this.mIsPause = false;
            this.mSeekBytes = 0;
            this.mFilePath = str;
            this.mIsRunning = true;
            this.mThread = new Thread(this, "UGCBGMPlayer");
            this.mThread.start();
        }
    }

    public void stopPlay() {
        TXCLog.i(TAG, "stopPlay");
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
        synchronized (this) {
            nativeStopPlay();
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stopBGMPlay cost(MS): ");
        stringBuilder.append(System.currentTimeMillis() - currentTimeMillis);
        TXCLog.i(str, stringBuilder.toString());
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
        this.mVolume = f;
        nativeSetVolume(f);
    }

    public void setSpeedRate(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSpeedRate:");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        this.mSpeedRate = f;
        nativeSetSpeedRate(f);
    }

    public void playFromTime(long j, long j2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("playFromTime:");
        stringBuilder.append(j);
        stringBuilder.append(", ");
        stringBuilder.append(j2);
        TXCLog.i(str, stringBuilder.toString());
        this.mStartTimeMS = j;
        this.mEndTimeMS = j2;
        nativePlayFromTime(j, j2);
    }

    public void seekBytes(long j) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("seekBytes:");
        stringBuilder.append(j);
        TXCLog.i(str, stringBuilder.toString());
        if (j < 0) {
            TXCLog.e(TAG, "seek bytes can not be negative. change to 0");
            j = 0;
        }
        this.mSeekBytes = j;
        nativeSeekBytes(j);
    }

    public long getCurPosition() {
        long nativeGetCurPosition = nativeGetCurPosition();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getCurPosition:");
        stringBuilder.append(nativeGetCurPosition);
        TXCLog.i(str, stringBuilder.toString());
        return nativeGetCurPosition;
    }

    public static long getDurationMS(String str) {
        return nativeGetDurationMS(str);
    }

    private void onPlayStart() {
        e eVar;
        synchronized (this) {
            eVar = this.mWeakListener != null ? (e) this.mWeakListener.get() : null;
        }
        if (eVar != null) {
            eVar.a();
        }
    }

    private void onPlayEnd(int i) {
        e eVar;
        synchronized (this) {
            eVar = this.mWeakListener != null ? (e) this.mWeakListener.get() : null;
        }
        if (eVar != null) {
            eVar.a(i);
        }
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

    public void run() {
        String str;
        String str2;
        StringBuilder stringBuilder;
        long currentTimeMillis = System.currentTimeMillis();
        onPlayStart();
        int i = 0;
        AudioTrack audioTrack = null;
        if (this.mFilePath == null || this.mFilePath.isEmpty()) {
            str = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("file path = ");
            stringBuilder2.append(this.mFilePath);
            TXCLog.e(str, stringBuilder2.toString());
            i = -2;
        } else {
            nativeSetVolume(this.mVolume);
            nativeSetSpeedRate(this.mSpeedRate);
            nativePlayFromTime(this.mStartTimeMS, this.mEndTimeMS);
            nativeSeekBytes(this.mSeekBytes);
            if (nativeStartPlay(this.mFilePath)) {
                if (this.mIsPause) {
                    nativePause();
                } else {
                    nativeResume();
                }
                str = TAG;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("start play bgm: path = ");
                stringBuilder3.append(this.mFilePath);
                stringBuilder3.append("volume = ");
                stringBuilder3.append(this.mVolume);
                stringBuilder3.append(", speedRate = ");
                stringBuilder3.append(this.mSpeedRate);
                stringBuilder3.append(", startTime = ");
                stringBuilder3.append(this.mStartTimeMS);
                stringBuilder3.append(", endTime = ");
                stringBuilder3.append(this.mEndTimeMS);
                stringBuilder3.append(", seekBytes = ");
                stringBuilder3.append(this.mSeekBytes);
                stringBuilder3.append(", pause = ");
                stringBuilder3.append(this.mIsPause);
                TXCLog.i(str, stringBuilder3.toString());
                int nativeGetSampleRate = nativeGetSampleRate();
                int nativeGetChannels = nativeGetChannels();
                int nativeGetBitsPerChannel = nativeGetBitsPerChannel();
                int i2 = nativeGetChannels == 1 ? 2 : 3;
                int i3 = nativeGetBitsPerChannel == 8 ? 3 : 2;
                try {
                    AudioTrack audioTrack2 = new AudioTrack(3, nativeGetSampleRate, i2, i3, AudioTrack.getMinBufferSize(nativeGetSampleRate, i2, i3), 1);
                    try {
                        audioTrack2.play();
                        byte[] bArr = new byte[((nativeGetChannels * IjkMediaMeta.FF_PROFILE_H264_INTRA) * 2)];
                        while (this.mIsRunning && !Thread.interrupted()) {
                            int nativeRead = nativeRead(bArr, bArr.length);
                            if (nativeRead < 0) {
                                TXCLog.i(TAG, "UGC BGM播放结束");
                                onPlayProgress(nativeGetCurDurationMS(), nativeGetCurDurationMS());
                                break;
                            } else if (nativeRead != 0) {
                                audioTrack2.write(bArr, 0, nativeRead);
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
                        str2 = TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("AudioTrack play Exception: ");
                        stringBuilder.append(e2.getMessage());
                        TXCLog.e(str2, stringBuilder.toString());
                        i = -4;
                    }
                    audioTrack = audioTrack2;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    str2 = TAG;
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("new AudioTrack Exception: ");
                    stringBuilder3.append(e22.getMessage());
                    TXCLog.e(str2, stringBuilder3.toString());
                    i = -3;
                }
            } else {
                i = -1;
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
        str = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("UGC BGM player play time: ");
        stringBuilder.append(System.currentTimeMillis() - currentTimeMillis);
        TXCLog.i(str, stringBuilder.toString());
        if (this.mIsRunning) {
            onPlayEnd(i);
        }
    }
}
