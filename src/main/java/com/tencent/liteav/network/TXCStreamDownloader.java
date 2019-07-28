package com.tencent.liteav.network;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.g.b;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.rtmp.TXLiveConstants;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TXCStreamDownloader extends com.tencent.liteav.basic.module.a implements com.tencent.liteav.basic.d.a, com.tencent.liteav.network.TXIStreamDownloader.a, com.tencent.liteav.network.d.a, h {
    public static final String TAG = "TXCStreamDownloader";
    public static final int TXE_DOWNLOAD_ERROR_ALLADDRESS_FAILED = 12031;
    public static final int TXE_DOWNLOAD_ERROR_CONNECT_FAILED = 12011;
    public static final int TXE_DOWNLOAD_ERROR_DISCONNECT = 12012;
    public static final int TXE_DOWNLOAD_ERROR_GET_RTMP_ACC_URL_FAIL = 12030;
    public static final int TXE_DOWNLOAD_ERROR_NET_RECONNECT = 12015;
    public static final int TXE_DOWNLOAD_ERROR_READ_FAILED = 12013;
    public static final int TXE_DOWNLOAD_ERROR_WRITE_FAILED = 12014;
    public static final int TXE_DOWNLOAD_INFO_CONNECT_END = 12007;
    public static final int TXE_DOWNLOAD_INFO_CONNECT_FAILED = 12004;
    public static final int TXE_DOWNLOAD_INFO_CONNECT_SUCCESS = 12001;
    public static final int TXE_DOWNLOAD_INFO_HANDSHAKE_FAIL = 12005;
    public static final int TXE_DOWNLOAD_INFO_PLAY_BEGIN = 12008;
    public static final int TXE_DOWNLOAD_INFO_SERVER_REFUSE = 12009;
    private k mAccUrlFetcher;
    private Context mApplicationContext;
    private int mChannelType = 0;
    private long mCurrentNalTs = 0;
    private int mDownloadFormat = 1;
    private TXIStreamDownloader mDownloader = null;
    private boolean mDownloaderRunning = false;
    private boolean mEnableMessage = false;
    private boolean mEnableNearestIP = false;
    private Handler mHandler = null;
    protected Map<String, String> mHeaders;
    private DownloadStats mLastDownloadStats = null;
    private long mLastIFramelTs = 0;
    private long mLastTimeStamp = 0;
    private h mListener = null;
    private byte[] mListenerLock = new byte[0];
    private com.tencent.liteav.basic.d.a mNotifyListener = null;
    private String mOriginPlayUrl = "";
    private boolean mRecvFirstNal = false;
    private Runnable mReportNetStatusRunnalbe = new Runnable() {
        public void run() {
            TXCStreamDownloader.this.reportNetStatus();
        }
    };
    private d mStreamSwitcher = null;
    private long mSwitchStartTime = 0;

    public static class DownloadStats {
        public long afterParseAudioBytes;
        public long afterParseVideoBytes;
        public long beforeParseAudioBytes;
        public long beforeParseVideoBytes;
        public long connTS;
        public long dnsTS;
        public long firstAudioTS;
        public long firstVideoTS;
        public String serverIP;
        public long startTS;
    }

    public static class a {
        public String a;
        public String b;
        public String c;
        public int d;
        public String e;
        public boolean f;
    }

    /* JADX WARNING: Missing block: B:18:0x006c, code skipped:
            r3 = com.tencent.rtmp.TXLiveConstants.PLAY_ERR_NET_DISCONNECT;
     */
    /* JADX WARNING: Missing block: B:21:0x007e, code skipped:
            r3 = com.tencent.rtmp.TXLiveConstants.PLAY_WARNING_RECONNECT;
     */
    /* JADX WARNING: Missing block: B:24:0x0092, code skipped:
            r3 = r8;
     */
    public void onNotifyEvent(int r8, android.os.Bundle r9) {
        /*
        r7 = this;
        r0 = r7.mListenerLock;
        monitor-enter(r0);
        r1 = r7.mNotifyListener;	 Catch:{ all -> 0x00df }
        r2 = 12001; // 0x2ee1 float:1.6817E-41 double:5.9293E-320;
        if (r1 == 0) goto L_0x00d8;
    L_0x0009:
        r1 = new android.os.Bundle;	 Catch:{ all -> 0x00df }
        r1.<init>();	 Catch:{ all -> 0x00df }
        r3 = 3005; // 0xbbd float:4.211E-42 double:1.4847E-320;
        r4 = -2301; // 0xfffffffffffff703 float:NaN double:NaN;
        r5 = 2103; // 0x837 float:2.947E-42 double:1.039E-320;
        r6 = 3002; // 0xbba float:4.207E-42 double:1.483E-320;
        if (r8 == r2) goto L_0x00a8;
    L_0x0018:
        switch(r8) {
            case 12004: goto L_0x009e;
            case 12005: goto L_0x0094;
            default: goto L_0x001b;
        };	 Catch:{ all -> 0x00df }
    L_0x001b:
        switch(r8) {
            case 12007: goto L_0x008b;
            case 12008: goto L_0x0081;
            case 12009: goto L_0x0077;
            default: goto L_0x001e;
        };	 Catch:{ all -> 0x00df }
    L_0x001e:
        switch(r8) {
            case 12011: goto L_0x006f;
            case 12012: goto L_0x0065;
            case 12013: goto L_0x005d;
            case 12014: goto L_0x0055;
            case 12015: goto L_0x004d;
            default: goto L_0x0021;
        };	 Catch:{ all -> 0x00df }
    L_0x0021:
        switch(r8) {
            case 12030: goto L_0x0043;
            case 12031: goto L_0x003b;
            default: goto L_0x0024;
        };	 Catch:{ all -> 0x00df }
    L_0x0024:
        r3 = "EVT_MSG";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00df }
        r4.<init>();	 Catch:{ all -> 0x00df }
        r5 = "UNKNOWN event = ";
        r4.append(r5);	 Catch:{ all -> 0x00df }
        r4.append(r8);	 Catch:{ all -> 0x00df }
        r4 = r4.toString();	 Catch:{ all -> 0x00df }
        r1.putString(r3, r4);	 Catch:{ all -> 0x00df }
        goto L_0x0092;
    L_0x003b:
        r3 = "EVT_MSG";
        r5 = "所有拉流地址尝试失败,可以放弃治疗";
        r1.putString(r3, r5);	 Catch:{ all -> 0x00df }
        goto L_0x006c;
    L_0x0043:
        r3 = -2302; // 0xfffffffffffff702 float:NaN double:NaN;
        r4 = "EVT_MSG";
        r5 = "获取加速拉流地址失败";
        r1.putString(r4, r5);	 Catch:{ all -> 0x00df }
        goto L_0x00b1;
    L_0x004d:
        r3 = "EVT_MSG";
        r4 = "启动网络重连";
        r1.putString(r3, r4);	 Catch:{ all -> 0x00df }
        goto L_0x007e;
    L_0x0055:
        r4 = "EVT_MSG";
        r5 = "写数据错误，网络连接断开";
        r1.putString(r4, r5);	 Catch:{ all -> 0x00df }
        goto L_0x00b1;
    L_0x005d:
        r4 = "EVT_MSG";
        r5 = "读数据错误，网络连接断开";
        r1.putString(r4, r5);	 Catch:{ all -> 0x00df }
        goto L_0x00b1;
    L_0x0065:
        r3 = "EVT_MSG";
        r5 = "经多次自动重连失败，放弃连接";
        r1.putString(r3, r5);	 Catch:{ all -> 0x00df }
    L_0x006c:
        r3 = -2301; // 0xfffffffffffff703 float:NaN double:NaN;
        goto L_0x00b1;
    L_0x006f:
        r3 = "EVT_MSG";
        r4 = "连接服务器失败";
        r1.putString(r3, r4);	 Catch:{ all -> 0x00df }
        goto L_0x00a5;
    L_0x0077:
        r3 = "EVT_MSG";
        r4 = "服务器拒绝连接请求";
        r1.putString(r3, r4);	 Catch:{ all -> 0x00df }
    L_0x007e:
        r3 = 2103; // 0x837 float:2.947E-42 double:1.039E-320;
        goto L_0x00b1;
    L_0x0081:
        r3 = 2002; // 0x7d2 float:2.805E-42 double:9.89E-321;
        r4 = "EVT_MSG";
        r5 = "开始拉流";
        r1.putString(r4, r5);	 Catch:{ all -> 0x00df }
        goto L_0x00b1;
    L_0x008b:
        r3 = "EVT_MSG";
        r4 = "连接结束";
        r1.putString(r3, r4);	 Catch:{ all -> 0x00df }
    L_0x0092:
        r3 = r8;
        goto L_0x00b1;
    L_0x0094:
        r3 = 3003; // 0xbbb float:4.208E-42 double:1.4837E-320;
        r4 = "EVT_MSG";
        r5 = "RTMP服务器握手失败";
        r1.putString(r4, r5);	 Catch:{ all -> 0x00df }
        goto L_0x00b1;
    L_0x009e:
        r3 = "EVT_MSG";
        r4 = "连接服务器失败";
        r1.putString(r3, r4);	 Catch:{ all -> 0x00df }
    L_0x00a5:
        r3 = 3002; // 0xbba float:4.207E-42 double:1.483E-320;
        goto L_0x00b1;
    L_0x00a8:
        r3 = 2001; // 0x7d1 float:2.804E-42 double:9.886E-321;
        r4 = "EVT_MSG";
        r5 = "已连接服务器";
        r1.putString(r4, r5);	 Catch:{ all -> 0x00df }
    L_0x00b1:
        r4 = "";
        if (r9 == 0) goto L_0x00bd;
    L_0x00b5:
        r4 = "EVT_MSG";
        r5 = "";
        r4 = r9.getString(r4, r5);	 Catch:{ all -> 0x00df }
    L_0x00bd:
        if (r4 == 0) goto L_0x00ca;
    L_0x00bf:
        r9 = r4.isEmpty();	 Catch:{ all -> 0x00df }
        if (r9 != 0) goto L_0x00ca;
    L_0x00c5:
        r9 = "EVT_MSG";
        r1.putString(r9, r4);	 Catch:{ all -> 0x00df }
    L_0x00ca:
        r9 = "EVT_TIME";
        r4 = com.tencent.liteav.basic.util.TXCTimeUtil.getTimeTick();	 Catch:{ all -> 0x00df }
        r1.putLong(r9, r4);	 Catch:{ all -> 0x00df }
        r9 = r7.mNotifyListener;	 Catch:{ all -> 0x00df }
        r9.onNotifyEvent(r3, r1);	 Catch:{ all -> 0x00df }
    L_0x00d8:
        monitor-exit(r0);	 Catch:{ all -> 0x00df }
        if (r8 != r2) goto L_0x00de;
    L_0x00db:
        r7.reportNetStatusInternal();
    L_0x00de:
        return;
    L_0x00df:
        r8 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00df }
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.network.TXCStreamDownloader.onNotifyEvent(int, android.os.Bundle):void");
    }

    private void tryResetRetryCount() {
        if (this.mDownloader != null) {
            this.mDownloader.connectRetryTimes = 0;
        }
    }

    public void onPullAudio(com.tencent.liteav.basic.g.a aVar) {
        tryResetRetryCount();
        synchronized (this.mListenerLock) {
            if (this.mListener != null) {
                this.mListener.onPullAudio(aVar);
            }
        }
    }

    public void onPullNAL(b bVar) {
        tryResetRetryCount();
        if (!this.mRecvFirstNal) {
            reportNetStatusInternal();
            this.mRecvFirstNal = true;
        }
        synchronized (this.mListenerLock) {
            this.mCurrentNalTs = bVar.pts;
            if (bVar.nalType == 0) {
                this.mLastIFramelTs = bVar.pts;
            }
            if (this.mListener != null) {
                this.mListener.onPullNAL(bVar);
            }
        }
    }

    public void onRestartDownloader() {
        if (this.mHandler != null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    TXCStreamDownloader.this.stop();
                    TXCStreamDownloader.this.start(TXCStreamDownloader.this.mOriginPlayUrl, TXCStreamDownloader.this.mEnableNearestIP, TXCStreamDownloader.this.mChannelType, TXCStreamDownloader.this.mEnableMessage);
                }
            });
        }
    }

    public void setListener(h hVar) {
        synchronized (this.mListenerLock) {
            this.mListener = hVar;
        }
    }

    public void setNotifyListener(com.tencent.liteav.basic.d.a aVar) {
        synchronized (this.mListenerLock) {
            this.mNotifyListener = aVar;
        }
    }

    public void onSwitchFinish(TXIStreamDownloader tXIStreamDownloader, boolean z) {
        synchronized (this.mListenerLock) {
            int currentTimeMillis = (int) (System.currentTimeMillis() - this.mSwitchStartTime);
            this.mSwitchStartTime = 0;
            Bundle bundle = new Bundle();
            bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
            if (z) {
                this.mDownloader = tXIStreamDownloader;
                this.mDownloader.setListener(this);
                this.mDownloader.setNotifyListener(this);
                this.mDownloader.setRestartListener(this);
                bundle.putInt("EVT_ID", TXLiveConstants.PLAY_EVT_STREAM_SWITCH_SUCC);
                bundle.putCharSequence(TXLiveConstants.EVT_DESCRIPTION, "切换分辨率成功");
                if (this.mNotifyListener != null) {
                    this.mNotifyListener.onNotifyEvent(TXLiveConstants.PLAY_EVT_STREAM_SWITCH_SUCC, bundle);
                }
                TXCDRApi.txReportDAU(this.mApplicationContext, com.tencent.liteav.basic.datareport.a.bt, currentTimeMillis, "");
            } else {
                bundle.putInt("EVT_ID", TXLiveConstants.PLAY_EVT_STREAM_SWITCH_SUCC);
                bundle.putCharSequence(TXLiveConstants.EVT_DESCRIPTION, "切换分辨率失败");
                if (this.mNotifyListener != null) {
                    this.mNotifyListener.onNotifyEvent(TXLiveConstants.PLAY_EVT_STREAM_SWITCH_SUCC, bundle);
                }
                TXCDRApi.txReportDAU(this.mApplicationContext, com.tencent.liteav.basic.datareport.a.bu);
            }
            this.mStreamSwitcher = null;
        }
    }

    static {
        com.tencent.liteav.basic.util.b.e();
    }

    public TXCStreamDownloader(Context context, int i) {
        if (i == 0) {
            this.mDownloader = new TXCFLVDownloader(context);
        } else if (i == 1 || i == 4) {
            this.mDownloader = new TXCRTMPDownloader(context);
        }
        if (this.mDownloader != null) {
            this.mDownloader.setListener(this);
            this.mDownloader.setNotifyListener(this);
            this.mDownloader.setRestartListener(this);
        }
        this.mDownloadFormat = i;
        this.mAccUrlFetcher = new k(context);
        this.mApplicationContext = context;
        if (this.mApplicationContext != null) {
            this.mHandler = new Handler(this.mApplicationContext.getMainLooper());
        }
    }

    public void setRetryTimes(int i) {
        if (this.mDownloader != null) {
            this.mDownloader.connectRetryLimit = i;
        }
    }

    public void setRetryInterval(int i) {
        if (this.mDownloader != null) {
            this.mDownloader.connectRetryInterval = i;
        }
    }

    public int start(final String str, boolean z, int i, final boolean z2) {
        boolean z3 = true;
        this.mDownloaderRunning = true;
        this.mRecvFirstNal = false;
        this.mOriginPlayUrl = str;
        this.mEnableNearestIP = z;
        this.mChannelType = i;
        this.mEnableMessage = z2;
        setStatusValue(7116, Long.valueOf(0));
        setStatusValue(7117, Long.valueOf(0));
        setStatusValue(7118, Long.valueOf(0));
        if (str.startsWith("room")) {
            setStatusValue(7116, Long.valueOf(1));
            setStatusValue(7112, Long.valueOf(2));
            if (this.mDownloader != null) {
                Vector vector = new Vector();
                vector.add(new e(str, true));
                this.mDownloader.setOriginUrl(str);
                this.mDownloader.startDownload(vector, false, false, z2);
            }
            if (this.mHandler != null) {
                this.mHandler.postDelayed(this.mReportNetStatusRunnalbe, 2000);
            }
            return 0;
        } else if (z && this.mDownloadFormat == 4) {
            int a = this.mAccUrlFetcher.a(str, i, new com.tencent.liteav.network.k.a() {
                public void a(int i, String str, Vector<e> vector) {
                    if (i != 0 || vector == null || vector.isEmpty()) {
                        TXCStreamDownloader.this.onNotifyEvent(TXCStreamDownloader.TXE_DOWNLOAD_ERROR_GET_RTMP_ACC_URL_FAIL, null);
                        TXCDRApi.txReportDAU(TXCStreamDownloader.this.mApplicationContext, com.tencent.liteav.basic.datareport.a.at, i, str);
                        TXCLog.e(TXCStreamDownloader.TAG, "getAccelerateStreamPlayUrl failed, play stream with raw url");
                        if (TXCStreamDownloader.this.mDownloaderRunning) {
                            TXCStreamDownloader.this.playStreamWithRawUrl(str, z2);
                            if (TXCStreamDownloader.this.mHandler != null) {
                                TXCStreamDownloader.this.mHandler.postDelayed(TXCStreamDownloader.this.mReportNetStatusRunnalbe, 2000);
                            }
                        }
                    } else if (TXCStreamDownloader.this.mDownloaderRunning) {
                        if (TXCStreamDownloader.this.mDownloader != null) {
                            int i2 = 0;
                            Iterator it = vector.iterator();
                            while (it.hasNext()) {
                                e eVar = (e) it.next();
                                if (eVar != null && eVar.b && eVar.a != null && eVar.a.length() > 0) {
                                    i2++;
                                }
                            }
                            TXCStreamDownloader.this.setStatusValue(7116, Long.valueOf((long) i2));
                            TXCStreamDownloader.this.setStatusValue(7112, Long.valueOf(2));
                            TXCStreamDownloader.this.mDownloader.setOriginUrl(str);
                            TXCStreamDownloader.this.mDownloader.startDownload(vector, true, true, z2);
                        }
                        if (TXCStreamDownloader.this.mHandler != null) {
                            TXCStreamDownloader.this.mHandler.postDelayed(TXCStreamDownloader.this.mReportNetStatusRunnalbe, 2000);
                        }
                        TXCDRApi.txReportDAU(TXCStreamDownloader.this.mApplicationContext, com.tencent.liteav.basic.datareport.a.at, i, TXCStreamDownloader.this.mAccUrlFetcher.b());
                    } else {
                        TXCDRApi.txReportDAU(TXCStreamDownloader.this.mApplicationContext, com.tencent.liteav.basic.datareport.a.at, -4, "livePlayer have been stopped");
                    }
                }
            });
            if (a != 0) {
                if (a == -1) {
                    TXCDRApi.txReportDAU(this.mApplicationContext, com.tencent.liteav.basic.datareport.a.at, a, "invalid playUrl");
                } else if (a == -2) {
                    TXCDRApi.txReportDAU(this.mApplicationContext, com.tencent.liteav.basic.datareport.a.at, a, "invalid streamID");
                } else if (a == -3) {
                    TXCDRApi.txReportDAU(this.mApplicationContext, com.tencent.liteav.basic.datareport.a.at, a, "invalid signature");
                }
                String str2 = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("getAccelerateStreamPlayUrl failed, result = ");
                stringBuilder.append(a);
                stringBuilder.append(", play stream with raw url");
                TXCLog.e(str2, stringBuilder.toString());
                onNotifyEvent(TXE_DOWNLOAD_ERROR_GET_RTMP_ACC_URL_FAIL, null);
                playStreamWithRawUrl(str, z2);
                if (this.mHandler != null) {
                    this.mHandler.postDelayed(this.mReportNetStatusRunnalbe, 2000);
                }
            }
            return 0;
        } else {
            if (this.mDownloader != null) {
                setStatusValue(7112, Long.valueOf(1));
                Vector vector2 = new Vector();
                vector2.add(new e(str, false));
                this.mDownloader.setOriginUrl(str);
                TXIStreamDownloader tXIStreamDownloader = this.mDownloader;
                if (this.mDownloadFormat != 4) {
                    z3 = false;
                }
                tXIStreamDownloader.startDownload(vector2, z3, z, z2);
                if (this.mHandler != null) {
                    this.mHandler.postDelayed(this.mReportNetStatusRunnalbe, 2000);
                }
            }
            return 0;
        }
    }

    private void playStreamWithRawUrl(String str, boolean z) {
        if (this.mDownloader != null) {
            if (str != null && ((str.startsWith("http://") || str.startsWith("https://")) && str.contains(".flv"))) {
                int i = this.mDownloader.connectRetryLimit;
                int i2 = this.mDownloader.connectRetryInterval;
                this.mDownloader = null;
                this.mDownloader = new TXCFLVDownloader(this.mApplicationContext);
                this.mDownloader.setListener(this);
                this.mDownloader.setNotifyListener(this);
                this.mDownloader.setRestartListener(this);
                this.mDownloader.connectRetryLimit = i;
                this.mDownloader.connectRetryInterval = i2;
                this.mDownloader.setHeaders(this.mHeaders);
            }
            setStatusValue(7112, Long.valueOf(1));
            Vector vector = new Vector();
            vector.add(new e(str, false));
            this.mDownloader.setOriginUrl(str);
            this.mDownloader.startDownload(vector, false, false, z);
        }
    }

    public void stop() {
        this.mDownloaderRunning = false;
        this.mRecvFirstNal = false;
        if (this.mDownloader != null) {
            this.mDownloader.stopDownload();
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mReportNetStatusRunnalbe);
        }
        synchronized (this.mListenerLock) {
            if (this.mStreamSwitcher != null) {
                this.mStreamSwitcher.a(null);
                this.mStreamSwitcher.a();
                this.mStreamSwitcher = null;
            }
        }
    }

    public boolean switchStream(String str) {
        synchronized (this.mListenerLock) {
            if (this.mStreamSwitcher != null) {
                TXCLog.w(TAG, "stream_switch stream is changing ignore this change");
                return false;
            }
            TXCFLVDownloader tXCFLVDownloader = new TXCFLVDownloader(this.mApplicationContext);
            tXCFLVDownloader.connectRetryLimit = this.mDownloader.connectRetryLimit;
            tXCFLVDownloader.connectRetryInterval = this.mDownloader.connectRetryInterval;
            tXCFLVDownloader.setHeaders(this.mHeaders);
            this.mStreamSwitcher = new d(this);
            this.mStreamSwitcher.a((h) this);
            this.mStreamSwitcher.a(this.mDownloader, tXCFLVDownloader, this.mCurrentNalTs, this.mLastIFramelTs, str);
            this.mSwitchStartTime = System.currentTimeMillis();
            return true;
        }
    }

    private DownloadStats getDownloadStats() {
        return this.mDownloader != null ? this.mDownloader.getDownloadStats() : null;
    }

    private a getRealTimeStreamInfo() {
        a aVar = new a();
        if (this.mAccUrlFetcher != null) {
            aVar.b = this.mAccUrlFetcher.a();
            aVar.c = this.mAccUrlFetcher.b();
            aVar.d = this.mAccUrlFetcher.c();
            aVar.e = this.mAccUrlFetcher.d();
        }
        if (this.mDownloader != null) {
            aVar.a = this.mDownloader.getCurrentStreamUrl();
            aVar.f = this.mDownloader.isQuicChannel();
        }
        return aVar;
    }

    private void reportNetStatus() {
        reportNetStatusInternal();
        this.mHandler.postDelayed(this.mReportNetStatusRunnalbe, 2000);
    }

    private void reportNetStatusInternal() {
        long timeTick = TXCTimeUtil.getTimeTick();
        long j = timeTick - this.mLastTimeStamp;
        DownloadStats downloadStats = getDownloadStats();
        a realTimeStreamInfo = getRealTimeStreamInfo();
        if (downloadStats != null) {
            long j2 = 0;
            if (this.mLastDownloadStats != null) {
                long j3 = j;
                long longValue = getSpeed(this.mLastDownloadStats.afterParseVideoBytes, downloadStats.afterParseVideoBytes, j3).longValue();
                j = getSpeed(this.mLastDownloadStats.afterParseAudioBytes, downloadStats.afterParseAudioBytes, j3).longValue();
                j2 = longValue;
            } else {
                j = 0;
            }
            setStatusValue(7101, Long.valueOf(j2));
            setStatusValue(7102, Long.valueOf(j));
            setStatusValue(7103, Long.valueOf(downloadStats.firstVideoTS));
            setStatusValue(7104, Long.valueOf(downloadStats.firstAudioTS));
            if (realTimeStreamInfo != null) {
                setStatusValue(7105, Long.valueOf((long) realTimeStreamInfo.d));
                setStatusValue(7106, realTimeStreamInfo.e);
                setStatusValue(7111, Long.valueOf(realTimeStreamInfo.f ? 2 : 1));
                setStatusValue(7113, realTimeStreamInfo.a);
                setStatusValue(7114, realTimeStreamInfo.b);
                setStatusValue(7115, realTimeStreamInfo.c);
            }
            setStatusValue(7107, Long.valueOf(downloadStats.startTS));
            setStatusValue(7108, Long.valueOf(downloadStats.dnsTS));
            setStatusValue(7109, Long.valueOf(downloadStats.connTS));
            setStatusValue(7110, String.valueOf(downloadStats.serverIP));
        }
        if (this.mDownloader != null) {
            int connectCountQuic = this.mDownloader.getConnectCountQuic();
            int connectCountTcp = this.mDownloader.getConnectCountTcp();
            setStatusValue(7117, Long.valueOf((long) (connectCountQuic + 1)));
            setStatusValue(7118, Long.valueOf((long) (connectCountTcp + 1)));
            setStatusValue(7119, this.mDownloader.getRealStreamUrl());
        }
        this.mLastTimeStamp = timeTick;
        this.mLastDownloadStats = downloadStats;
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

    public void setHeaders(Map<String, String> map) {
        this.mHeaders = map;
        if (this.mDownloader != null) {
            this.mDownloader.setHeaders(this.mHeaders);
        }
    }
}
