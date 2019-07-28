package com.tencent.liteav.network;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.basic.util.b;
import com.tencent.liteav.network.TXCStreamDownloader.DownloadStats;
import com.tencent.rtmp.TXLiveConstants;
import java.util.Vector;

public class TXCRTMPDownloader extends TXIStreamDownloader {
    private final int MSG_EVENT;
    private final int MSG_RECONNECT;
    private final String TAG;
    private int mConnectCountQuic;
    private int mConnectCountTcp;
    private a mCurrentThread;
    private boolean mEnableNearestIP;
    private Handler mHandler;
    private boolean mHasTcpPlayUrl;
    private boolean mIsPlayRtmpAccStream;
    private int mLastNetworkType;
    private String mPlayUrl;
    private boolean mQuicChannel;
    private Object mRTMPThreadLock;
    private String mServerIp;
    private HandlerThread mThread;
    private Vector<e> mVecPlayUrls;

    class a extends Thread {
        private long b = 0;
        private String c;
        private boolean d;

        a(String str, boolean z) {
            super("RTMPDownLoad");
            this.c = str;
            this.d = z;
        }

        public void run() {
            synchronized (this) {
                this.b = TXCRTMPDownloader.this.nativeInitRtmpHandler(TXCRTMPDownloader.this.mOriginUrl, this.c, this.d, TXCRTMPDownloader.this.mEnableMessage);
            }
            TXCRTMPDownloader.this.nativeStart(this.b);
            synchronized (this) {
                TXCRTMPDownloader.this.nativeUninitRtmpHandler(this.b);
                this.b = 0;
            }
        }

        public void a() {
            synchronized (this) {
                if (this.b != 0) {
                    TXCRTMPDownloader.this.nativeStop(this.b);
                }
            }
        }

        public DownloadStats b() {
            DownloadStats access$400;
            synchronized (this) {
                access$400 = this.b != 0 ? TXCRTMPDownloader.this.nativeGetStats(this.b) : null;
            }
            return access$400;
        }
    }

    private native DownloadStats nativeGetStats(long j);

    private native long nativeInitRtmpHandler(String str, String str2, boolean z, boolean z2);

    private native void nativeStart(long j);

    private native void nativeStop(long j);

    private native void nativeUninitRtmpHandler(long j);

    public TXCRTMPDownloader(Context context) {
        super(context);
        this.TAG = "network.TXCRTMPDownloader";
        this.MSG_RECONNECT = 101;
        this.MSG_EVENT = 102;
        this.mPlayUrl = "";
        this.mQuicChannel = false;
        this.mServerIp = "";
        this.mCurrentThread = null;
        this.mRTMPThreadLock = null;
        this.mThread = null;
        this.mHandler = null;
        this.mIsPlayRtmpAccStream = false;
        this.mEnableNearestIP = false;
        this.mConnectCountQuic = 0;
        this.mConnectCountTcp = 0;
        this.mLastNetworkType = 255;
        this.mRTMPThreadLock = new Object();
    }

    private void startInternal() {
        if (this.mQuicChannel) {
            this.mConnectCountQuic++;
        } else {
            this.mConnectCountTcp++;
        }
        synchronized (this.mRTMPThreadLock) {
            this.mCurrentThread = new a(this.mPlayUrl, this.mQuicChannel);
            this.mCurrentThread.start();
        }
    }

    private void postReconnectMsg() {
        Message message = new Message();
        message.what = 101;
        if (this.mHandler != null) {
            this.mHandler.sendMessageDelayed(message, (long) (this.connectRetryInterval * 1000));
        }
    }

    private void reconnect(final boolean z) {
        synchronized (this.mRTMPThreadLock) {
            if (this.mCurrentThread != null) {
                this.mCurrentThread.a();
                this.mCurrentThread = null;
            }
        }
        if (this.mHandler != null) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    TXCRTMPDownloader.this.internalReconnect(z);
                }
            }, (long) (this.connectRetryInterval * 1000));
        }
    }

    private void internalReconnect(boolean z) {
        if (!this.mIsRunning) {
            return;
        }
        if (!this.mIsPlayRtmpAccStream || this.mLastNetworkType == b.c(this.mApplicationContext)) {
            boolean z2 = this.mQuicChannel;
            if (this.mIsPlayRtmpAccStream) {
                if (!this.mEnableNearestIP) {
                    z = false;
                }
                if (z2) {
                    z = true;
                }
                if (!(!z || this.mVecPlayUrls == null || this.mVecPlayUrls.isEmpty())) {
                    e eVar = (e) this.mVecPlayUrls.get(0);
                    this.mVecPlayUrls.remove(0);
                    this.mPlayUrl = eVar.a;
                    this.mQuicChannel = eVar.b;
                }
            }
            if (z2 && this.mHasTcpPlayUrl) {
                sendNotifyEvent(TXCStreamDownloader.TXE_DOWNLOAD_ERROR_NET_RECONNECT);
                startInternal();
            } else if (this.connectRetryTimes < this.connectRetryLimit) {
                this.connectRetryTimes++;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("reconnect retry count:");
                stringBuilder.append(this.connectRetryTimes);
                stringBuilder.append(" limit:");
                stringBuilder.append(this.connectRetryLimit);
                TXCLog.d("network.TXCRTMPDownloader", stringBuilder.toString());
                sendNotifyEvent(TXCStreamDownloader.TXE_DOWNLOAD_ERROR_NET_RECONNECT);
                startInternal();
            } else {
                TXCLog.e("network.TXCRTMPDownloader", "reconnect all times retried, send failed event ");
                sendNotifyEvent(TXCStreamDownloader.TXE_DOWNLOAD_ERROR_DISCONNECT);
            }
            return;
        }
        this.mLastNetworkType = b.c(this.mApplicationContext);
        if (this.mRestartListener != null) {
            this.mRestartListener.onRestartDownloader();
        }
    }

    public void sendNotifyEvent(int i, String str) {
        if (str.isEmpty()) {
            sendNotifyEvent(i);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(TXLiveConstants.EVT_DESCRIPTION, str);
        bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
        if (this.mNotifyListener != null) {
            this.mNotifyListener.onNotifyEvent(i, bundle);
        }
    }

    public void sendNotifyEvent(int i) {
        boolean z = true;
        if (i == 0 || i == 1) {
            if (i != 1) {
                z = false;
            }
            reconnect(z);
            return;
        }
        super.sendNotifyEvent(i);
    }

    public void startDownload(Vector<e> vector, boolean z, boolean z2, boolean z3) {
        if (!this.mIsRunning && vector != null && !vector.isEmpty()) {
            this.mEnableMessage = z3;
            this.mIsPlayRtmpAccStream = z;
            this.mEnableNearestIP = z2;
            this.mVecPlayUrls = vector;
            this.mHasTcpPlayUrl = false;
            for (int i = 0; i < this.mVecPlayUrls.size(); i++) {
                if (!((e) this.mVecPlayUrls.elementAt(i)).b) {
                    this.mHasTcpPlayUrl = true;
                    break;
                }
            }
            e eVar = (e) this.mVecPlayUrls.get(0);
            this.mVecPlayUrls.remove(0);
            this.mPlayUrl = eVar.a;
            this.mQuicChannel = eVar.b;
            this.mIsRunning = true;
            String str = "network.TXCRTMPDownloader";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("start pull with url:");
            stringBuilder.append(this.mPlayUrl);
            stringBuilder.append(" quic:");
            stringBuilder.append(this.mQuicChannel ? "yes" : "no");
            TXCLog.d(str, stringBuilder.toString());
            this.mConnectCountQuic = 0;
            this.mConnectCountTcp = 0;
            this.connectRetryTimes = 0;
            if (this.mThread == null) {
                this.mThread = new HandlerThread("RTMP_PULL");
                this.mThread.start();
            }
            this.mHandler = new Handler(this.mThread.getLooper()) {
                public void handleMessage(Message message) {
                    if (message.what == 101) {
                        TXCRTMPDownloader.this.startInternal();
                    }
                }
            };
            startInternal();
        }
    }

    public void stopDownload() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            this.mVecPlayUrls.removeAllElements();
            this.mVecPlayUrls = null;
            this.mIsPlayRtmpAccStream = false;
            this.mEnableNearestIP = false;
            TXCLog.d("network.TXCRTMPDownloader", "stop pull");
            synchronized (this.mRTMPThreadLock) {
                if (this.mCurrentThread != null) {
                    this.mCurrentThread.a();
                    this.mCurrentThread = null;
                }
            }
            if (this.mThread != null) {
                this.mThread.quit();
                this.mThread = null;
            }
            if (this.mHandler != null) {
                this.mHandler = null;
            }
        }
    }

    public DownloadStats getDownloadStats() {
        synchronized (this.mRTMPThreadLock) {
            if (this.mCurrentThread != null) {
                DownloadStats b = this.mCurrentThread.b();
                return b;
            }
            return null;
        }
    }

    public String getCurrentStreamUrl() {
        return this.mPlayUrl;
    }

    public boolean isQuicChannel() {
        return this.mQuicChannel;
    }

    public int getConnectCountQuic() {
        return this.mConnectCountQuic;
    }

    public int getConnectCountTcp() {
        return this.mConnectCountTcp;
    }
}
