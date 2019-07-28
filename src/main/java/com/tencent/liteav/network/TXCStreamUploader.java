package com.tencent.liteav.network;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.module.TXCStatus;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.rtmp.TXLiveConstants;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TXCStreamUploader extends com.tencent.liteav.basic.module.a implements b {
    public static final int RTMPSENDSTRATEGY_LIVE = 1;
    public static final int RTMPSENDSTRATEGY_REALTIME_QUIC = 3;
    public static final int RTMPSENDSTRATEGY_REALTIME_TCP = 2;
    static final String TAG = "TXCStreamUploader";
    public static final int TXE_UPLOAD_ERROR_ALLADDRESS_FAILED = 11011;
    public static final int TXE_UPLOAD_ERROR_INVALID_ADDRESS = 11019;
    public static final int TXE_UPLOAD_ERROR_NET_DISCONNECT = 11012;
    public static final int TXE_UPLOAD_ERROR_NET_RECONNECT = 11016;
    public static final int TXE_UPLOAD_ERROR_NO_DATA = 11013;
    public static final int TXE_UPLOAD_ERROR_NO_NETWORK = 11015;
    public static final int TXE_UPLOAD_ERROR_READ_FAILED = 11017;
    public static final int TXE_UPLOAD_ERROR_WRITE_FAILED = 11018;
    public static final int TXE_UPLOAD_INFO_CONNECT_FAILED = 11006;
    public static final int TXE_UPLOAD_INFO_CONNECT_SUCCESS = 11001;
    public static final int TXE_UPLOAD_INFO_HANDSHAKE_FAIL = 11005;
    public static final int TXE_UPLOAD_INFO_NET_BUSY = 11003;
    public static final int TXE_UPLOAD_INFO_PUBLISH_START = 11008;
    public static final int TXE_UPLOAD_INFO_PUSH_BEGIN = 11002;
    public static final int TXE_UPLOAD_INFO_ROOM_IN = 11021;
    public static final int TXE_UPLOAD_INFO_ROOM_NEED_REENTER = 11024;
    public static final int TXE_UPLOAD_INFO_ROOM_OUT = 11022;
    public static final int TXE_UPLOAD_INFO_ROOM_USERLIST = 11023;
    public static final int TXE_UPLOAD_INFO_SERVER_REFUSE = 11007;
    public static final int TXE_UPLOAD_MODE_AUDIO_ONLY = 1;
    public static final int TXE_UPLOAD_MODE_LINK_MIC = 2;
    public static final int TXE_UPLOAD_MODE_REAL_TIME = 0;
    public static final int TXE_UPLOAD_PROTOCOL_AV = 1;
    public static final int TXE_UPLOAD_PROTOCOL_RTMP = 0;
    private final int MSG_EVENT = 102;
    private final int MSG_RECONNECT = 101;
    private final int MSG_REPORT_STATUS = 103;
    private final int MSG_RTMPPROXY_HEARTBEAT = 104;
    private boolean mAudioMuted = false;
    private int mChannelType = 0;
    private int mConnectCountQuic = 0;
    private int mConnectCountTcp = 0;
    private long mConnectSuccessTimeStamps = 0;
    private Context mContext = null;
    private int mCurrentRecordIdx = 0;
    private boolean mEnableNearestIP = true;
    private long mGoodPushTime = 30000;
    private Handler mHandler = null;
    private HandlerThread mHandlerThread = null;
    private c mIntelligentRoute = null;
    private ArrayList<a> mIpList = null;
    private boolean mIsPushing = false;
    private int mLastNetworkType = 255;
    private long mLastTimeStamp = 0;
    private UploadStats mLastUploadStats = null;
    private WeakReference<com.tencent.liteav.basic.d.a> mNotifyListener = null;
    private l mParam = null;
    private long mPushStartTS = 0;
    private boolean mQuicChannel = false;
    private int mRetryCount = 0;
    private long mRtmpMsgRecvThreadInstance = 0;
    private Object mRtmpMsgRecvThreadLock = new Object();
    private boolean mRtmpProxyEnable = false;
    private int mRtmpProxyIPIndex = 0;
    private Vector<String> mRtmpProxyIPList = new Vector();
    private long mRtmpProxyInstance = 0;
    private Object mRtmpProxyLock = new Object();
    private a mRtmpProxyParam = new a();
    private String mRtmpUrl = "";
    private Thread mThread = null;
    private Object mThreadLock = null;
    private n mUploadQualityReport = null;
    private long mUploaderInstance = 0;
    private Vector<com.tencent.liteav.basic.g.b> mVecPendingNAL = new Vector();

    public class RtmpProxyUserInfo {
        public String account = "";
        public String playUrl = "";
    }

    public class UploadStats {
        public long audioCacheLen;
        public long audioDropCount;
        public long channelType;
        public long connTS;
        public long connectTimeCost;
        public String connectionID;
        public String connectionStats;
        public long dnsTS;
        public long dnsparseTimeCost;
        public long handshakeTimeCost;
        public long inAudioBytes;
        public long inVideoBytes;
        public long outAudioBytes;
        public long outVideoBytes;
        public String serverIP;
        public long startTS;
        public long videoCacheLen;
        public long videoDropCount;
    }

    public class a {
        public long a;
        public long b;
        public String c;
        public long d;
        public String e;
        public long f;
        public long g;
        public String h;
        public boolean i;
        public String j;

        public void a() {
            this.a = 0;
            this.b = 0;
            this.c = "";
            this.d = 0;
            this.e = "";
            this.f = 0;
            this.g = 0;
            this.i = false;
            this.j = "";
        }
    }

    private class b {
        public String a = "";
        public boolean b = false;

        public b(String str, boolean z) {
            this.a = str;
            this.b = z;
        }
    }

    private native void nativeCacheJNIParams();

    private native void nativeEnableDrop(long j, boolean z);

    private native UploadStats nativeGetStats(long j);

    private native long nativeInitRtmpMsgRecvThreadInstance(long j, long j2);

    private native long nativeInitRtmpProxyInstance(long j, long j2, String str, long j3, String str2, long j4, long j5, String str3, boolean z, String str4);

    private native long nativeInitUploader(String str, String str2, boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2, int i8);

    private native void nativeOnThreadRun(long j);

    private native void nativePushAAC(long j, byte[] bArr, long j2);

    private native void nativePushNAL(long j, byte[] bArr, int i, long j2, long j3, long j4);

    private native void nativeReleaseJNIParams();

    private native void nativeRtmpMsgRecvThreadStart(long j);

    private native void nativeRtmpMsgRecvThreadStop(long j);

    private native void nativeRtmpProxyEnterRoom(long j);

    private native void nativeRtmpProxyLeaveRoom(long j);

    private native void nativeRtmpProxySendHeartBeat(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11);

    private native void nativeSendRtmpProxyMsg(long j, byte[] bArr);

    private native void nativeSetSendStrategy(long j, int i, boolean z);

    private native void nativeSetVideoDropParams(long j, boolean z, int i, int i2);

    private native void nativeStopPush(long j);

    private native void nativeUninitRtmpMsgRecvThreadInstance(long j);

    private native void nativeUninitRtmpProxyInstance(long j);

    private native void nativeUninitUploader(long j);

    public int init() {
        return 0;
    }

    static {
        com.tencent.liteav.basic.util.b.e();
    }

    public void setNotifyListener(com.tencent.liteav.basic.d.a aVar) {
        this.mNotifyListener = new WeakReference(aVar);
    }

    public TXCStreamUploader(Context context, l lVar) {
        this.mContext = context;
        if (lVar == null) {
            lVar = new l();
            lVar.a = 0;
            lVar.g = 3;
            lVar.f = 3;
            lVar.h = 40;
            lVar.i = 1000;
            lVar.j = true;
        }
        this.mParam = lVar;
        this.mThreadLock = new Object();
        this.mIntelligentRoute = new c();
        this.mIntelligentRoute.a = this;
        this.mUploaderInstance = 0;
        this.mRetryCount = 0;
        this.mCurrentRecordIdx = 0;
        this.mIpList = null;
        this.mIsPushing = false;
        this.mThread = null;
        this.mRtmpUrl = null;
        this.mLastNetworkType = 255;
        this.mHandlerThread = null;
        this.mUploadQualityReport = new n(context);
        m.a().a(context);
    }

    public void setRetryInterval(int i) {
        if (this.mParam != null) {
            this.mParam.g = i;
        }
    }

    public void setAudioInfo(int i, int i2) {
        if (this.mParam != null) {
            this.mParam.d = i2;
            this.mParam.e = i;
        }
    }

    public void setRetryTimes(int i) {
        if (this.mParam != null) {
            this.mParam.f = i;
        }
    }

    public void setMode(int i) {
        if (this.mParam != null) {
            this.mParam.a = i;
        }
    }

    private void postReconnectMsg(String str, boolean z, int i) {
        Message message = new Message();
        message.what = 101;
        message.obj = str;
        message.arg1 = z ? 2 : 1;
        if (this.mHandler != null) {
            this.mHandler.sendMessageDelayed(message, (long) i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0038  */
    public void onFetchDone(int r5, java.util.ArrayList<com.tencent.liteav.network.a> r6) {
        /*
        r4 = this;
        r0 = r4.mIsPushing;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = 0;
        if (r6 == 0) goto L_0x0035;
    L_0x0008:
        r1 = "TXCStreamUploader";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "onFetchDone: code = ";
        r2.append(r3);
        r2.append(r5);
        r3 = " ip count = ";
        r2.append(r3);
        r3 = r6.size();
        r2.append(r3);
        r2 = r2.toString();
        com.tencent.liteav.basic.log.TXCLog.e(r1, r2);
        if (r5 != 0) goto L_0x0035;
    L_0x002c:
        r4.mIpList = r6;
        r5 = r6.size();
        r4.mCurrentRecordIdx = r0;
        goto L_0x0036;
    L_0x0035:
        r5 = 0;
    L_0x0036:
        if (r5 <= 0) goto L_0x00ae;
    L_0x0038:
        r5 = "";
        r6 = r4.mIpList;
        r6 = r6.iterator();
        r1 = r5;
        r5 = 0;
    L_0x0042:
        r2 = r6.hasNext();
        if (r2 == 0) goto L_0x0089;
    L_0x0048:
        r2 = r6.next();
        r2 = (com.tencent.liteav.network.a) r2;
        if (r2 == 0) goto L_0x0062;
    L_0x0050:
        r3 = r2.c;
        if (r3 == 0) goto L_0x0062;
    L_0x0054:
        r3 = r2.a;
        if (r3 == 0) goto L_0x0062;
    L_0x0058:
        r3 = r2.a;
        r3 = r3.length();
        if (r3 <= 0) goto L_0x0062;
    L_0x0060:
        r5 = r5 + 1;
    L_0x0062:
        if (r2 == 0) goto L_0x0042;
    L_0x0064:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r1);
        r1 = " ";
        r3.append(r1);
        r1 = r2.a;
        r1 = r4.getConfusionIP(r1);
        r3.append(r1);
        r1 = ":";
        r3.append(r1);
        r1 = r2.b;
        r3.append(r1);
        r1 = r3.toString();
        goto L_0x0042;
    L_0x0089:
        r6 = 7016; // 0x1b68 float:9.832E-42 double:3.4664E-320;
        r2 = (long) r5;
        r5 = java.lang.Long.valueOf(r2);
        r4.setStatusValue(r6, r5);
        r5 = 7019; // 0x1b6b float:9.836E-42 double:3.468E-320;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r2 = "{";
        r6.append(r2);
        r6.append(r1);
        r1 = " }";
        r6.append(r1);
        r6 = r6.toString();
        r4.setStatusValue(r5, r6);
    L_0x00ae:
        r5 = r4.getRtmpRealConnectInfo();
        r6 = r5.a;
        r5 = r5.b;
        r4.postReconnectMsg(r6, r5, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.network.TXCStreamUploader.onFetchDone(int, java.util.ArrayList):void");
    }

    public String getConfusionIP(String str) {
        if (str != null) {
            int indexOf = str.indexOf(".");
            if (indexOf != -1) {
                String substring = str.substring(indexOf + 1);
                int indexOf2 = substring.indexOf(".");
                if (indexOf2 != -1) {
                    str = substring.substring(indexOf2 + 1);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("A.B.");
                    stringBuilder.append(str);
                    return stringBuilder.toString();
                }
            }
        }
        return str;
    }

    public String start(String str, boolean z, int i) {
        if (this.mIsPushing) {
            return this.mRtmpUrl;
        }
        this.mIsPushing = true;
        this.mConnectSuccessTimeStamps = 0;
        this.mRetryCount = 0;
        this.mRtmpUrl = str;
        this.mChannelType = i;
        this.mPushStartTS = 0;
        this.mConnectCountQuic = 0;
        this.mConnectCountTcp = 0;
        this.mRtmpProxyEnable = false;
        this.mRtmpProxyParam.a();
        this.mRtmpProxyIPList.clear();
        this.mRtmpProxyIPIndex = 0;
        this.mRtmpProxyInstance = 0;
        this.mRtmpMsgRecvThreadInstance = 0;
        setStatusValue(7016, Long.valueOf(0));
        setStatusValue(7017, Long.valueOf(0));
        setStatusValue(7018, Long.valueOf(0));
        this.mUploadQualityReport.a();
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("start push with url:");
        stringBuilder.append(this.mRtmpUrl);
        stringBuilder.append(" enable nearest ip:");
        stringBuilder.append(z ? "yes" : "no");
        stringBuilder.append("channel type:");
        stringBuilder.append(i);
        TXCLog.d(str2, stringBuilder.toString());
        if (com.tencent.liteav.basic.util.b.c(this.mContext) == 255) {
            sendNotifyEvent(TXE_UPLOAD_ERROR_NO_NETWORK);
            return this.mRtmpUrl;
        }
        this.mEnableNearestIP = z;
        if (this.mHandlerThread == null) {
            this.mHandlerThread = new HandlerThread("RTMP_PUSH");
            this.mHandlerThread.start();
        }
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 101:
                        TXCStreamUploader.this.startPushTask((String) message.obj, message.arg1 == 2, 0);
                        return;
                    case 103:
                        TXCStreamUploader.this.reportNetStatus();
                        return;
                    case 104:
                        TXCStreamUploader.this.rtmpProxySendHeartBeat();
                        if (TXCStreamUploader.this.mHandler != null) {
                            TXCStreamUploader.this.mHandler.sendEmptyMessageDelayed(104, 2000);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        parseProxyInfo(str);
        if (this.mRtmpProxyEnable) {
            this.mLastNetworkType = com.tencent.liteav.basic.util.b.c(this.mContext);
            nativeCacheJNIParams();
            startPushTask(this.mRtmpUrl, this.mQuicChannel, 0);
        } else if (!this.mEnableNearestIP || this.mLastNetworkType == com.tencent.liteav.basic.util.b.c(this.mContext)) {
            startPushTask(this.mRtmpUrl, this.mQuicChannel, 0);
        } else {
            TXCLog.d(TAG, "fetching nearest ip list");
            this.mLastNetworkType = com.tencent.liteav.basic.util.b.c(this.mContext);
            this.mIntelligentRoute.a(str, i);
        }
        this.mHandler.sendEmptyMessageDelayed(103, 2000);
        return this.mRtmpUrl;
    }

    public void stop() {
        if (this.mIsPushing) {
            this.mIsPushing = false;
            TXCLog.d(TAG, "stop push");
            if (this.mRtmpProxyEnable) {
                synchronized (this.mRtmpProxyLock) {
                    nativeRtmpProxyLeaveRoom(this.mRtmpProxyInstance);
                }
            }
            synchronized (this.mThreadLock) {
                nativeStopPush(this.mUploaderInstance);
                this.mPushStartTS = 0;
            }
            if (this.mHandlerThread != null) {
                this.mHandlerThread.getLooper().quit();
                this.mHandlerThread = null;
            }
            if (this.mHandler != null) {
                this.mHandler = null;
            }
            if (this.mRtmpProxyEnable) {
                nativeReleaseJNIParams();
            }
            this.mUploadQualityReport.c();
            this.mUploadQualityReport.a();
        }
    }

    private void tryResetRetryCount() {
        if (this.mConnectSuccessTimeStamps != 0 && TXCTimeUtil.getTimeTick() - this.mConnectSuccessTimeStamps > ((long) ((this.mParam.f * (this.mParam.g + 13)) * 1000))) {
            this.mRetryCount = 0;
            this.mConnectSuccessTimeStamps = 0;
            TXCLog.d(TAG, "reset retry count");
        }
    }

    public void pushAAC(byte[] bArr, long j) {
        tryResetRetryCount();
        synchronized (this.mThreadLock) {
            if (this.mPushStartTS == 0) {
                this.mPushStartTS = j - 3600000;
            }
            if (!this.mAudioMuted || !this.mRtmpProxyEnable) {
                nativePushAAC(this.mUploaderInstance, bArr, j - this.mPushStartTS);
            }
        }
    }

    public void pushNAL(com.tencent.liteav.basic.g.b bVar) {
        tryResetRetryCount();
        synchronized (this.mThreadLock) {
            if (this.mUploaderInstance != 0) {
                if (this.mPushStartTS == 0) {
                    this.mPushStartTS = bVar.dts - 3600000;
                }
                if (!(bVar == null || bVar.nalData == null || bVar.nalData.length <= 0)) {
                    nativePushNAL(this.mUploaderInstance, bVar.nalData, bVar.nalType, bVar.frameIndex, bVar.pts - this.mPushStartTS, bVar.dts - this.mPushStartTS);
                }
            } else {
                if (bVar.nalType == 0) {
                    this.mVecPendingNAL.removeAllElements();
                }
                this.mVecPendingNAL.add(bVar);
            }
        }
    }

    public void setAudioMute(boolean z) {
        synchronized (this.mThreadLock) {
            this.mAudioMuted = z;
            if (this.mRtmpProxyEnable && this.mUploaderInstance != 0) {
                int i = this.mParam.m ? this.mQuicChannel ? 3 : 2 : 1;
                nativeSetSendStrategy(this.mUploaderInstance, i, false);
            }
        }
    }

    public void setDropEanble(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("drop enable ");
        stringBuilder.append(z ? "yes" : "no");
        TXCLog.d(str, stringBuilder.toString());
        synchronized (this.mThreadLock) {
            nativeEnableDrop(this.mUploaderInstance, z);
        }
    }

    public void setVideoDropParams(boolean z, int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("drop params wait i frame:");
        stringBuilder.append(z ? "yes" : "no");
        stringBuilder.append(" max video count:");
        stringBuilder.append(i);
        stringBuilder.append(" max video cache time: ");
        stringBuilder.append(i2);
        stringBuilder.append(" ms");
        TXCLog.d(str, stringBuilder.toString());
        synchronized (this.mThreadLock) {
            this.mParam.j = z;
            this.mParam.h = i;
            this.mParam.i = i2;
            if (this.mUploaderInstance != 0) {
                nativeSetVideoDropParams(this.mUploaderInstance, this.mParam.j, this.mParam.h, this.mParam.i);
            }
        }
    }

    public void setSendStrategy(boolean z, boolean z2) {
        this.mParam.m = z;
        this.mParam.n = z2;
        this.mUploadQualityReport.a(z);
        int i = z ? this.mQuicChannel ? 3 : 2 : 1;
        if (!this.mRtmpProxyEnable && (this.mIpList == null || this.mIpList.size() == 0)) {
            i = 1;
        }
        synchronized (this.mThreadLock) {
            if (this.mUploaderInstance != 0) {
                nativeSetSendStrategy(this.mUploaderInstance, i, z2);
            }
        }
        setStatusValue(7020, Long.valueOf((long) i));
    }

    public UploadStats getUploadStats() {
        UploadStats nativeGetStats;
        synchronized (this.mThreadLock) {
            nativeGetStats = nativeGetStats(this.mUploaderInstance);
            if (nativeGetStats != null) {
                nativeGetStats.channelType = this.mQuicChannel ? 2 : 1;
            }
        }
        return nativeGetStats;
    }

    private void startPushTask(final String str, final boolean z, int i) {
        TXCLog.d(TAG, "start push task");
        if (this.mQuicChannel != z && this.mQuicChannel) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("limits:");
            stringBuilder.append(this.mParam.f);
            stringBuilder.append(" current:");
            stringBuilder.append(this.mRetryCount);
            TXCDRApi.reportEvent40003(this.mRtmpUrl, com.tencent.liteav.basic.datareport.a.M, "switch video push channel from quic to tcp", stringBuilder.toString());
        }
        int i2;
        if (z) {
            i2 = this.mConnectCountQuic + 1;
            this.mConnectCountQuic = i2;
            setStatusValue(7017, Long.valueOf((long) i2));
        } else {
            i2 = this.mConnectCountTcp + 1;
            this.mConnectCountTcp = i2;
            setStatusValue(7018, Long.valueOf((long) i2));
        }
        this.mThread = new Thread("RTMPUpload") {
            public void run() {
                while (TXCStreamUploader.this.mUploaderInstance != 0) {
                    try {
                        AnonymousClass2.sleep(100, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                TXCStreamUploader.this.mUploadQualityReport.b();
                TXCStreamUploader.this.mUploadQualityReport.a(TXCStreamUploader.this.mParam.m);
                TXCStreamUploader.this.mUploadQualityReport.a(TXCStreamUploader.this.mRtmpUrl);
                TXCStreamUploader.this.mUploadQualityReport.a(z, TXCStreamUploader.this.getAddressFromUrl(str));
                synchronized (TXCStreamUploader.this.mThreadLock) {
                    TXCStreamUploader.this.mQuicChannel = z;
                    int i = TXCStreamUploader.this.mParam.m ? TXCStreamUploader.this.mQuicChannel ? 3 : 2 : 1;
                    if (TXCStreamUploader.this.mRtmpProxyEnable) {
                        if (TXCStreamUploader.this.mAudioMuted) {
                            TXCStreamUploader.this.mParam.n = false;
                        }
                    } else if (TXCStreamUploader.this.mIpList == null || TXCStreamUploader.this.mIpList.size() == 0) {
                        i = 1;
                    }
                    TXCStreamUploader.this.setStatusValue(7020, Long.valueOf((long) i));
                    TXCStreamUploader.this.mUploaderInstance = TXCStreamUploader.this.nativeInitUploader(TXCStreamUploader.this.mRtmpUrl, str, z, TXCStreamUploader.this.mParam.e, TXCStreamUploader.this.mParam.d, TXCStreamUploader.this.mParam.a, TXCStreamUploader.this.mParam.c, TXCStreamUploader.this.mParam.h, 16, i, TXCStreamUploader.this.mParam.n, TXCStreamUploader.this.mParam.o);
                    if (TXCStreamUploader.this.mUploaderInstance != 0) {
                        TXCStreamUploader.this.nativeSetVideoDropParams(TXCStreamUploader.this.mUploaderInstance, TXCStreamUploader.this.mParam.j, TXCStreamUploader.this.mParam.h, TXCStreamUploader.this.mParam.i);
                        Iterator it = TXCStreamUploader.this.mVecPendingNAL.iterator();
                        Object obj = null;
                        while (it.hasNext()) {
                            com.tencent.liteav.basic.g.b bVar = (com.tencent.liteav.basic.g.b) it.next();
                            if (obj == null && bVar.nalType == 0) {
                                obj = 1;
                            }
                            if (obj != null) {
                                if (TXCStreamUploader.this.mPushStartTS == 0) {
                                    TXCStreamUploader.this.mPushStartTS = bVar.dts - 3600000;
                                }
                                TXCStreamUploader.this.nativePushNAL(TXCStreamUploader.this.mUploaderInstance, bVar.nalData, bVar.nalType, bVar.frameIndex, bVar.pts - TXCStreamUploader.this.mPushStartTS, bVar.dts - TXCStreamUploader.this.mPushStartTS);
                            }
                        }
                        TXCStreamUploader.this.mVecPendingNAL.removeAllElements();
                    }
                }
                if (TXCStreamUploader.this.mRtmpProxyEnable) {
                    synchronized (TXCStreamUploader.this.mRtmpProxyLock) {
                        TXCStreamUploader.this.mRtmpProxyInstance = TXCStreamUploader.this.nativeInitRtmpProxyInstance(TXCStreamUploader.this.mRtmpProxyParam.a, TXCStreamUploader.this.mRtmpProxyParam.b, TXCStreamUploader.this.mRtmpProxyParam.c, TXCStreamUploader.this.mRtmpProxyParam.d, TXCStreamUploader.this.mRtmpProxyParam.e, TXCStreamUploader.this.mRtmpProxyParam.f, TXCStreamUploader.this.mRtmpProxyParam.g, TXCStreamUploader.this.mRtmpProxyParam.h, TXCStreamUploader.this.mRtmpProxyParam.i, TXCStreamUploader.this.mRtmpProxyParam.j);
                    }
                    synchronized (TXCStreamUploader.this.mRtmpMsgRecvThreadLock) {
                        TXCStreamUploader.this.mRtmpMsgRecvThreadInstance = TXCStreamUploader.this.nativeInitRtmpMsgRecvThreadInstance(TXCStreamUploader.this.mRtmpProxyInstance, TXCStreamUploader.this.mUploaderInstance);
                    }
                }
                TXCStreamUploader.this.nativeOnThreadRun(TXCStreamUploader.this.mUploaderInstance);
                if (TXCStreamUploader.this.mRtmpProxyEnable) {
                    synchronized (TXCStreamUploader.this.mRtmpMsgRecvThreadLock) {
                        TXCStreamUploader.this.nativeRtmpMsgRecvThreadStop(TXCStreamUploader.this.mRtmpMsgRecvThreadInstance);
                        TXCStreamUploader.this.nativeUninitRtmpMsgRecvThreadInstance(TXCStreamUploader.this.mRtmpMsgRecvThreadInstance);
                        TXCStreamUploader.this.mRtmpMsgRecvThreadInstance = 0;
                    }
                    synchronized (TXCStreamUploader.this.mRtmpProxyLock) {
                        TXCStreamUploader.this.nativeUninitRtmpProxyInstance(TXCStreamUploader.this.mRtmpProxyInstance);
                        TXCStreamUploader.this.mRtmpProxyInstance = 0;
                    }
                }
                synchronized (TXCStreamUploader.this.mThreadLock) {
                    TXCStreamUploader.this.nativeUninitUploader(TXCStreamUploader.this.mUploaderInstance);
                    TXCStreamUploader.this.mUploaderInstance = 0;
                }
            }
        };
        this.mThread.start();
    }

    private void stopPushTask() {
        TXCLog.d(TAG, "stop push task");
        synchronized (this.mThreadLock) {
            this.mVecPendingNAL.removeAllElements();
            nativeStopPush(this.mUploaderInstance);
        }
    }

    private b getRtmpRealConnectInfo() {
        if (!this.mEnableNearestIP) {
            return new b(this.mRtmpUrl, false);
        }
        if (this.mIpList == null) {
            return new b(this.mRtmpUrl, false);
        }
        if (this.mCurrentRecordIdx >= this.mIpList.size() || this.mCurrentRecordIdx < 0) {
            return new b(this.mRtmpUrl, false);
        }
        a aVar = (a) this.mIpList.get(this.mCurrentRecordIdx);
        String[] split = this.mRtmpUrl.split("://");
        if (split.length < 2) {
            return new b(this.mRtmpUrl, false);
        }
        int i = 1;
        String[] split2 = split[1].split("/");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(aVar.a);
        stringBuilder.append(":");
        stringBuilder.append(aVar.b);
        split2[0] = stringBuilder.toString();
        stringBuilder = new StringBuilder(split2[0]);
        while (i < split2.length) {
            stringBuilder.append("/");
            stringBuilder.append(split2[i]);
            i++;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(split[0]);
        stringBuilder2.append("://");
        stringBuilder2.append(stringBuilder.toString());
        return new b(stringBuilder2.toString(), aVar.c);
    }

    private boolean nextRecordIdx(boolean z) {
        if (this.mIpList == null || this.mIpList.size() == 0) {
            return false;
        }
        if (z) {
            a aVar = (a) this.mIpList.get(this.mCurrentRecordIdx);
            aVar.e++;
        }
        if (this.mCurrentRecordIdx + 1 >= this.mIpList.size()) {
            return false;
        }
        this.mCurrentRecordIdx++;
        return true;
    }

    private String getAddressFromUrl(String str) {
        if (str != null) {
            int indexOf = str.indexOf("://");
            if (indexOf != -1) {
                str = str.substring(indexOf + 3);
                indexOf = str.indexOf("/");
                if (indexOf != -1) {
                    return str.substring(0, indexOf);
                }
            }
        }
        return "";
    }

    private void reconnect(final boolean z) {
        stopPushTask();
        if (this.mHandler != null) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    TXCStreamUploader.this.internalReconnect(z);
                }
            }, (long) (this.mParam.g * 1000));
        }
    }

    private void internalReconnect(boolean z) {
        if (this.mIsPushing) {
            String str;
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2;
            if (!this.mRtmpProxyEnable) {
                this.mUploadQualityReport.c();
                if (!this.mEnableNearestIP || this.mLastNetworkType == com.tencent.liteav.basic.util.b.c(this.mContext)) {
                    if (!this.mEnableNearestIP) {
                        z = false;
                    }
                    if (this.mQuicChannel) {
                        z = true;
                    }
                    if (z && !nextRecordIdx(true)) {
                        TXCLog.e(TAG, "reconnect: try all addresses failed");
                        TXCDRApi.reportEvent40003(this.mRtmpUrl, com.tencent.liteav.basic.datareport.a.E, "connect upload server failed", "try all addresses failed");
                    }
                    b rtmpRealConnectInfo = getRtmpRealConnectInfo();
                    String addressFromUrl = getAddressFromUrl(rtmpRealConnectInfo.a);
                    String str2 = TAG;
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("reconnect change ip: ");
                    stringBuilder3.append(addressFromUrl);
                    stringBuilder3.append(" enableNearestIP: ");
                    stringBuilder3.append(this.mEnableNearestIP);
                    stringBuilder3.append(" last channel type: ");
                    stringBuilder3.append(this.mQuicChannel ? "Q Channel" : "TCP");
                    TXCLog.e(str2, stringBuilder3.toString());
                    String str3;
                    int i;
                    StringBuilder stringBuilder4;
                    if (this.mQuicChannel) {
                        TXCLog.e(TAG, "reconnect last channel type is Q Channel，ignore retry limit");
                        str3 = this.mRtmpUrl;
                        i = com.tencent.liteav.basic.datareport.a.F;
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append("reconnect upload server:");
                        stringBuilder4.append(addressFromUrl);
                        TXCDRApi.reportEvent40003(str3, i, stringBuilder4.toString(), "last channel type is Q Channel");
                        startPushTask(rtmpRealConnectInfo.a, rtmpRealConnectInfo.b, 0);
                        sendNotifyEvent(TXE_UPLOAD_ERROR_NET_RECONNECT);
                    } else {
                        str2 = TAG;
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("reconnect retry count:");
                        stringBuilder3.append(this.mRetryCount);
                        stringBuilder3.append(" retry limit:");
                        stringBuilder3.append(this.mParam.f);
                        TXCLog.e(str2, stringBuilder3.toString());
                        if (this.mRetryCount < this.mParam.f) {
                            this.mRetryCount++;
                            str3 = this.mRtmpUrl;
                            i = com.tencent.liteav.basic.datareport.a.F;
                            stringBuilder4 = new StringBuilder();
                            stringBuilder4.append("reconnect upload server:");
                            stringBuilder4.append(addressFromUrl);
                            addressFromUrl = stringBuilder4.toString();
                            stringBuilder4 = new StringBuilder();
                            stringBuilder4.append("retry count:");
                            stringBuilder4.append(this.mRetryCount);
                            stringBuilder4.append(" retry limit:");
                            stringBuilder4.append(this.mParam.f);
                            TXCDRApi.reportEvent40003(str3, i, addressFromUrl, stringBuilder4.toString());
                            startPushTask(rtmpRealConnectInfo.a, rtmpRealConnectInfo.b, 0);
                            sendNotifyEvent(TXE_UPLOAD_ERROR_NET_RECONNECT);
                        } else {
                            TXCLog.e(TAG, "reconnect: try all times failed");
                            TXCDRApi.reportEvent40003(this.mRtmpUrl, com.tencent.liteav.basic.datareport.a.E, "connect upload server failed", "try all times failed");
                            sendNotifyEvent(TXE_UPLOAD_ERROR_ALLADDRESS_FAILED);
                        }
                    }
                } else {
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("reconnect network switch from ");
                    stringBuilder.append(this.mLastNetworkType);
                    stringBuilder.append(" to ");
                    stringBuilder.append(com.tencent.liteav.basic.util.b.c(this.mContext));
                    TXCLog.e(str, stringBuilder.toString());
                    this.mLastNetworkType = com.tencent.liteav.basic.util.b.c(this.mContext);
                    this.mIntelligentRoute.a(this.mRtmpUrl, this.mChannelType);
                    this.mRetryCount = 0;
                }
            } else if (this.mLastNetworkType != com.tencent.liteav.basic.util.b.c(this.mContext)) {
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("reconnect network switch from ");
                stringBuilder.append(this.mLastNetworkType);
                stringBuilder.append(" to ");
                stringBuilder.append(com.tencent.liteav.basic.util.b.c(this.mContext));
                TXCLog.e(str, stringBuilder.toString());
                this.mLastNetworkType = com.tencent.liteav.basic.util.b.c(this.mContext);
                this.mRetryCount = 0;
                sendNotifyEvent(TXE_UPLOAD_INFO_ROOM_NEED_REENTER, String.format("网络类型发生变化，需要重新进房", new Object[0]));
            } else if (this.mRetryCount < this.mParam.f) {
                this.mRetryCount++;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("reconnect retry count:");
                stringBuilder2.append(this.mRetryCount);
                stringBuilder2.append(" retry limit:");
                stringBuilder2.append(this.mParam.f);
                TXCDRApi.reportEvent40003(this.mRtmpUrl, com.tencent.liteav.basic.datareport.a.F, "reconnect rtmp-proxy server", stringBuilder2.toString());
                sendNotifyEvent(TXE_UPLOAD_ERROR_NET_RECONNECT);
                startPushTask(this.mRtmpUrl, this.mQuicChannel, 0);
            } else if (getNextRtmpProxyIP()) {
                this.mRetryCount = 0;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("reconnect retry count:");
                stringBuilder2.append(this.mRetryCount);
                stringBuilder2.append(" retry limit:");
                stringBuilder2.append(this.mParam.f);
                TXCDRApi.reportEvent40003(this.mRtmpUrl, com.tencent.liteav.basic.datareport.a.F, "reconnect rtmp-proxy server", stringBuilder2.toString());
                sendNotifyEvent(TXE_UPLOAD_ERROR_NET_RECONNECT);
                startPushTask(this.mRtmpUrl, this.mQuicChannel, 0);
            } else {
                TXCDRApi.reportEvent40003(this.mRtmpUrl, com.tencent.liteav.basic.datareport.a.E, "connect rtmp-proxy server failed", "try all addresses");
                sendNotifyEvent(TXE_UPLOAD_ERROR_ALLADDRESS_FAILED);
            }
        }
    }

    private void sendNotifyEvent(int i, String str) {
        if (str == null || str.isEmpty()) {
            sendNotifyEvent(i);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(TXLiveConstants.EVT_DESCRIPTION, str);
            bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
            if (i != TXE_UPLOAD_INFO_CONNECT_FAILED) {
                switch (i) {
                    case TXE_UPLOAD_ERROR_READ_FAILED /*11017*/:
                    case TXE_UPLOAD_ERROR_WRITE_FAILED /*11018*/:
                        i = 3005;
                        break;
                    case TXE_UPLOAD_ERROR_INVALID_ADDRESS /*11019*/:
                        i = TXLiveConstants.PUSH_ERR_INVALID_ADDRESS;
                        break;
                    default:
                        switch (i) {
                            case TXE_UPLOAD_INFO_ROOM_IN /*11021*/:
                                i = TXLiveConstants.PUSH_EVT_ROOM_IN;
                                break;
                            case TXE_UPLOAD_INFO_ROOM_OUT /*11022*/:
                                i = TXLiveConstants.PUSH_EVT_ROOM_OUT;
                                break;
                            case TXE_UPLOAD_INFO_ROOM_USERLIST /*11023*/:
                                i = TXLiveConstants.PUSH_EVT_ROOM_USERLIST;
                                break;
                            case TXE_UPLOAD_INFO_ROOM_NEED_REENTER /*11024*/:
                                i = TXLiveConstants.PUSH_EVT_ROOM_NEED_REENTER;
                                break;
                        }
                        break;
                }
            }
            i = 3002;
            com.tencent.liteav.basic.util.b.a(this.mNotifyListener, i, bundle);
        }
        if (i == TXE_UPLOAD_INFO_PUSH_BEGIN) {
            UploadStats uploadStats = getUploadStats();
            if (uploadStats != null) {
                this.mUploadQualityReport.a(uploadStats.dnsparseTimeCost, uploadStats.connectTimeCost, uploadStats.handshakeTimeCost);
            }
        } else if (i == TXE_UPLOAD_INFO_NET_BUSY) {
            this.mUploadQualityReport.d();
        }
    }

    /* JADX WARNING: Missing block: B:18:0x005b, code skipped:
            r5 = com.tencent.rtmp.TXLiveConstants.PUSH_ERR_NET_DISCONNECT;
     */
    /* JADX WARNING: Missing block: B:49:0x00c4, code skipped:
            r0.putLong("EVT_TIME", com.tencent.liteav.basic.util.TXCTimeUtil.getTimeTick());
            com.tencent.liteav.basic.util.b.a(r4.mNotifyListener, r5, r0);
     */
    private void sendNotifyEvent(int r5) {
        /*
        r4 = this;
        if (r5 != 0) goto L_0x0008;
    L_0x0002:
        r5 = 0;
        r4.reconnect(r5);
        goto L_0x00d2;
    L_0x0008:
        r0 = 1;
        if (r5 != r0) goto L_0x0010;
    L_0x000b:
        r4.reconnect(r0);
        goto L_0x00d2;
    L_0x0010:
        r0 = 11001; // 0x2af9 float:1.5416E-41 double:5.435E-320;
        if (r5 != r0) goto L_0x001a;
    L_0x0014:
        r0 = com.tencent.liteav.basic.util.TXCTimeUtil.getTimeTick();
        r4.mConnectSuccessTimeStamps = r0;
    L_0x001a:
        r0 = r4.mNotifyListener;
        if (r0 == 0) goto L_0x00d2;
    L_0x001e:
        r0 = new android.os.Bundle;
        r0.<init>();
        r1 = -1307; // 0xfffffffffffffae5 float:NaN double:NaN;
        switch(r5) {
            case 11001: goto L_0x00bb;
            case 11002: goto L_0x00b1;
            case 11003: goto L_0x00a7;
            case 11004: goto L_0x0028;
            case 11005: goto L_0x009d;
            case 11006: goto L_0x0093;
            case 11007: goto L_0x0089;
            case 11008: goto L_0x005e;
            case 11009: goto L_0x0028;
            case 11010: goto L_0x0028;
            case 11011: goto L_0x0054;
            case 11012: goto L_0x004c;
            case 11013: goto L_0x0044;
            case 11014: goto L_0x0028;
            case 11015: goto L_0x003c;
            case 11016: goto L_0x0031;
            default: goto L_0x0028;
        };
    L_0x0028:
        r1 = "EVT_MSG";
        r2 = "UNKNOWN";
        r0.putString(r1, r2);
        goto L_0x00c4;
    L_0x0031:
        r5 = 1102; // 0x44e float:1.544E-42 double:5.445E-321;
        r1 = "EVT_MSG";
        r2 = "启动网络重连";
        r0.putString(r1, r2);
        goto L_0x00c4;
    L_0x003c:
        r5 = "EVT_MSG";
        r2 = "没有网络，请检测WiFi或移动数据是否开启";
        r0.putString(r5, r2);
        goto L_0x005b;
    L_0x0044:
        r5 = "EVT_MSG";
        r2 = "超过30s没有数据发送，主动断开连接";
        r0.putString(r5, r2);
        goto L_0x005b;
    L_0x004c:
        r5 = "EVT_MSG";
        r2 = "经连续多次重连失败，放弃重连";
        r0.putString(r5, r2);
        goto L_0x005b;
    L_0x0054:
        r5 = "EVT_MSG";
        r2 = "所有IP都已经尝试失败,可以放弃治疗";
        r0.putString(r5, r2);
    L_0x005b:
        r5 = -1307; // 0xfffffffffffffae5 float:NaN double:NaN;
        goto L_0x00c4;
    L_0x005e:
        r5 = r4.mRtmpProxyEnable;
        if (r5 == 0) goto L_0x0088;
    L_0x0062:
        r5 = r4.mRtmpMsgRecvThreadLock;
        monitor-enter(r5);
        r0 = r4.mRtmpMsgRecvThreadInstance;	 Catch:{ all -> 0x0085 }
        r4.nativeRtmpMsgRecvThreadStart(r0);	 Catch:{ all -> 0x0085 }
        monitor-exit(r5);	 Catch:{ all -> 0x0085 }
        r0 = r4.mRtmpProxyLock;
        monitor-enter(r0);
        r1 = r4.mRtmpProxyInstance;	 Catch:{ all -> 0x0082 }
        r4.nativeRtmpProxyEnterRoom(r1);	 Catch:{ all -> 0x0082 }
        monitor-exit(r0);	 Catch:{ all -> 0x0082 }
        r5 = r4.mHandler;
        if (r5 == 0) goto L_0x0088;
    L_0x0078:
        r5 = r4.mHandler;
        r0 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r1 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r5.sendEmptyMessageDelayed(r0, r1);
        goto L_0x0088;
    L_0x0082:
        r5 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0082 }
        throw r5;
    L_0x0085:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0085 }
        throw r0;
    L_0x0088:
        return;
    L_0x0089:
        r5 = 3004; // 0xbbc float:4.21E-42 double:1.484E-320;
        r1 = "EVT_MSG";
        r2 = "服务器拒绝连接请求，可能是该推流地址已经被占用或过期，或者防盗链错误";
        r0.putString(r1, r2);
        goto L_0x00c4;
    L_0x0093:
        r5 = "EVT_MSG";
        r1 = "连接服务器失败";
        r0.putString(r5, r1);
        r5 = 3002; // 0xbba float:4.207E-42 double:1.483E-320;
        goto L_0x00c4;
    L_0x009d:
        r5 = 3003; // 0xbbb float:4.208E-42 double:1.4837E-320;
        r1 = "EVT_MSG";
        r2 = "RTMP服务器握手失败";
        r0.putString(r1, r2);
        goto L_0x00c4;
    L_0x00a7:
        r5 = "EVT_MSG";
        r1 = "上行带宽不足，数据发送不及时";
        r0.putString(r5, r1);
        r5 = 1101; // 0x44d float:1.543E-42 double:5.44E-321;
        goto L_0x00c4;
    L_0x00b1:
        r5 = 1002; // 0x3ea float:1.404E-42 double:4.95E-321;
        r1 = "EVT_MSG";
        r2 = "rtmp开始推流";
        r0.putString(r1, r2);
        goto L_0x00c4;
    L_0x00bb:
        r5 = 1001; // 0x3e9 float:1.403E-42 double:4.946E-321;
        r1 = "EVT_MSG";
        r2 = "已经连接rtmp服务器";
        r0.putString(r1, r2);
    L_0x00c4:
        r1 = "EVT_TIME";
        r2 = com.tencent.liteav.basic.util.TXCTimeUtil.getTimeTick();
        r0.putLong(r1, r2);
        r1 = r4.mNotifyListener;
        com.tencent.liteav.basic.util.b.a(r1, r5, r0);
    L_0x00d2:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.network.TXCStreamUploader.sendNotifyEvent(int):void");
    }

    private void reportNetStatus() {
        long timeTick = TXCTimeUtil.getTimeTick();
        long j = timeTick - this.mLastTimeStamp;
        UploadStats uploadStats = getUploadStats();
        if (uploadStats != null) {
            long longValue;
            long longValue2;
            long longValue3;
            long j2 = 0;
            if (this.mLastUploadStats != null) {
                long j3 = j;
                long longValue4 = getSpeed(this.mLastUploadStats.inVideoBytes, uploadStats.inVideoBytes, j3).longValue();
                longValue = getSpeed(this.mLastUploadStats.inAudioBytes, uploadStats.inAudioBytes, j3).longValue();
                longValue2 = getSpeed(this.mLastUploadStats.outVideoBytes, uploadStats.outVideoBytes, j3).longValue();
                longValue3 = getSpeed(this.mLastUploadStats.outAudioBytes, uploadStats.outAudioBytes, j3).longValue();
                j2 = longValue4;
            } else {
                longValue3 = 0;
                longValue = longValue3;
                longValue2 = longValue;
            }
            setStatusValue(7001, Long.valueOf(j2));
            setStatusValue(7002, Long.valueOf(longValue));
            setStatusValue(7003, Long.valueOf(longValue2));
            setStatusValue(7004, Long.valueOf(longValue3));
            setStatusValue(7005, Long.valueOf(uploadStats.videoCacheLen));
            setStatusValue(7006, Long.valueOf(uploadStats.audioCacheLen));
            setStatusValue(7007, Long.valueOf(uploadStats.videoDropCount));
            setStatusValue(7008, Long.valueOf(uploadStats.audioDropCount));
            setStatusValue(7009, Long.valueOf(uploadStats.startTS));
            setStatusValue(7010, Long.valueOf(uploadStats.dnsTS));
            setStatusValue(7011, Long.valueOf(uploadStats.connTS));
            setStatusValue(7012, String.valueOf(uploadStats.serverIP));
            setStatusValue(7013, Long.valueOf(this.mQuicChannel ? 2 : 1));
            setStatusValue(7014, uploadStats.connectionID);
            setStatusValue(7015, uploadStats.connectionStats);
            this.mUploadQualityReport.a(uploadStats.videoDropCount, uploadStats.audioDropCount);
            this.mUploadQualityReport.b(uploadStats.videoCacheLen, uploadStats.audioCacheLen);
        }
        this.mLastTimeStamp = timeTick;
        this.mLastUploadStats = uploadStats;
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessageDelayed(103, 2000);
        }
    }

    private Long getSpeed(long j, long j2, long j3) {
        if (j <= j2) {
            j2 -= j;
        }
        j = 0;
        if (j3 > 0) {
            j = ((j2 * 8) * 1000) / (j3 * IjkMediaMeta.AV_CH_SIDE_RIGHT);
        }
        return Long.valueOf(j);
    }

    private boolean isQCloudStreamUrl(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String str2 = "://";
        int indexOf = str.indexOf(str2);
        if (indexOf != -1) {
            str = str.substring(indexOf + str2.length());
            if (str != null && str.startsWith("cloud.tencent.com")) {
                return true;
            }
        }
        return false;
    }

    private void parseProxyInfo(String str) {
        if (str != null && str.length() != 0 && str.startsWith("room")) {
            this.mRtmpProxyParam.i = isQCloudStreamUrl(str);
            HashMap paramsFromUrl = getParamsFromUrl(str);
            if (paramsFromUrl != null) {
                if (paramsFromUrl.containsKey("sdkappid")) {
                    this.mRtmpProxyParam.a = Long.valueOf((String) paramsFromUrl.get("sdkappid")).longValue();
                }
                if (paramsFromUrl.containsKey("roomid") && paramsFromUrl.containsKey("userid") && paramsFromUrl.containsKey("roomsig")) {
                    String str2;
                    this.mRtmpProxyParam.d = Long.valueOf((String) paramsFromUrl.get("roomid")).longValue();
                    this.mRtmpProxyParam.c = (String) paramsFromUrl.get("userid");
                    if (paramsFromUrl.containsKey("bizbuf")) {
                        try {
                            str2 = (String) paramsFromUrl.get("bizbuf");
                            this.mRtmpProxyParam.j = URLDecoder.decode(str2, "UTF-8");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(URLDecoder.decode((String) paramsFromUrl.get("roomsig"), "UTF-8"));
                        this.mRtmpProxyParam.b = 0;
                        if (jSONObject.has("Key")) {
                            this.mRtmpProxyParam.e = jSONObject.optString("Key");
                            JSONObject optJSONObject = jSONObject.optJSONObject("RtmpProxy");
                            if (optJSONObject == null || (optJSONObject.has("Ip") && optJSONObject.has("Port") && optJSONObject.has("Type"))) {
                                JSONArray optJSONArray = jSONObject.optJSONArray("AccessList");
                                if (optJSONArray != null && optJSONArray.length() > 0) {
                                    for (int i = 0; i < optJSONArray.length(); i++) {
                                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                                        if (jSONObject2 != null && jSONObject2.has("Ip") && jSONObject2.has("Port")) {
                                            if (jSONObject2.has("Type")) {
                                                String optString = jSONObject2.optString("Ip");
                                                long optLong = jSONObject2.optLong("Port");
                                                if (jSONObject2.optLong("Type") == 2) {
                                                    Vector vector = this.mRtmpProxyIPList;
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    stringBuilder.append(optString);
                                                    stringBuilder.append(":");
                                                    stringBuilder.append(optLong);
                                                    vector.add(stringBuilder.toString());
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!this.mRtmpProxyParam.i) {
                                    this.mRtmpUrl = str;
                                    this.mQuicChannel = false;
                                } else if (optJSONObject != null) {
                                    str = str.substring(0, str.indexOf("?"));
                                    StringBuilder stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(this.mRtmpProxyParam.a);
                                    stringBuilder2.append("_");
                                    stringBuilder2.append(this.mRtmpProxyParam.d);
                                    stringBuilder2.append("_");
                                    stringBuilder2.append(this.mRtmpProxyParam.c);
                                    str2 = stringBuilder2.toString();
                                    StringBuilder stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append(str);
                                    stringBuilder3.append("/webrtc/");
                                    stringBuilder3.append(str2);
                                    stringBuilder3.append("?real_rtmp_ip=");
                                    stringBuilder3.append(optJSONObject.optString("Ip"));
                                    stringBuilder3.append("&real_rtmp_port=");
                                    stringBuilder3.append(optJSONObject.optLong("Port"));
                                    stringBuilder3.append("&tinyid=");
                                    stringBuilder3.append(this.mRtmpProxyParam.b);
                                    stringBuilder3.append("&srctinyid=0");
                                    this.mRtmpUrl = stringBuilder3.toString();
                                    getNextRtmpProxyIP();
                                } else {
                                    return;
                                }
                                this.mRtmpProxyEnable = true;
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private HashMap getParamsFromUrl(String str) {
        HashMap hashMap = new HashMap();
        String[] split = str.split("[?]");
        if (split == null || split.length < 2 || split[1] == null || split[1].length() == 0) {
            return hashMap;
        }
        for (String str2 : split[1].split("[&]")) {
            if (str2.indexOf("=") != -1) {
                String[] split2 = str2.split("[=]");
                if (split2.length == 2) {
                    hashMap.put(split2[0], split2[1]);
                }
            }
        }
        return hashMap;
    }

    private boolean getNextRtmpProxyIP() {
        this.mRtmpProxyParam.f = 234;
        this.mRtmpProxyParam.g = 80;
        if (this.mRtmpProxyIPList == null || this.mRtmpProxyIPList.size() <= 0) {
            return false;
        }
        if (this.mRtmpProxyIPIndex >= this.mRtmpProxyIPList.size()) {
            this.mRtmpProxyIPIndex = 0;
            return false;
        }
        String[] split = this.mRtmpUrl.split("://");
        if (split.length < 2) {
            return false;
        }
        String substring = split[1].substring(split[1].indexOf("/"));
        String str = (String) this.mRtmpProxyIPList.get(this.mRtmpProxyIPIndex);
        this.mRtmpProxyParam.h = str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("room://");
        stringBuilder.append(str);
        stringBuilder.append(substring);
        this.mRtmpUrl = stringBuilder.toString();
        this.mQuicChannel = true;
        this.mRtmpProxyIPIndex++;
        return true;
    }

    private void rtmpProxySendHeartBeat() {
        Throwable th;
        int[] a = com.tencent.liteav.basic.util.b.a();
        long j = (long) (a[0] / 10);
        long j2 = (long) (a[1] / 10);
        long d = (long) TXCStatus.d(getID(), 7004);
        long d2 = (long) TXCStatus.d(getID(), 7003);
        long d3 = (long) TXCStatus.d(getID(), 1001);
        long d4 = (long) TXCStatus.d(getID(), 4001);
        long d5 = (long) TXCStatus.d(getID(), 7006);
        long d6 = (long) TXCStatus.d(getID(), 7005);
        long d7 = (long) TXCStatus.d(getID(), 7008);
        long d8 = (long) TXCStatus.d(getID(), 7007);
        Object obj = this.mRtmpProxyLock;
        synchronized (obj) {
            try {
                long j3 = d6;
                d6 = obj;
                nativeRtmpProxySendHeartBeat(this.mRtmpProxyInstance, j, j2, d, d2, d3, d4, d5, j3, d7, d8);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    private void onSendRtmpProxyMsg(byte[] bArr) {
        synchronized (this.mThreadLock) {
            if (this.mUploaderInstance != 0) {
                nativeSendRtmpProxyMsg(this.mUploaderInstance, bArr);
            }
        }
    }

    private void onRtmpProxyUserListPushed(RtmpProxyUserInfo[] rtmpProxyUserInfoArr) {
        if (rtmpProxyUserInfoArr != null && this.mIsPushing && this.mRtmpProxyEnable && this.mRtmpProxyParam != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < rtmpProxyUserInfoArr.length; i++) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("userid", rtmpProxyUserInfoArr[i].account);
                    jSONObject.put("playurl", rtmpProxyUserInfoArr[i].playUrl);
                    jSONArray.put(i, jSONObject);
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("userlist", jSONArray);
                sendNotifyEvent(TXE_UPLOAD_INFO_ROOM_USERLIST, jSONObject2.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void onRtmpProxyRoomEvent(int i, int i2) {
        if (i == 1) {
            sendNotifyEvent(TXE_UPLOAD_INFO_ROOM_IN, String.format("已在房间中，[%d]", new Object[]{Integer.valueOf(i2)}));
        } else if (i == 2) {
            sendNotifyEvent(TXE_UPLOAD_INFO_ROOM_OUT, String.format("不在房间中，[%d]", new Object[]{Integer.valueOf(i2)}));
        }
    }
}
