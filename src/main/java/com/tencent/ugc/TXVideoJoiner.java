package com.tencent.ugc;

import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.liteav.b.f;
import com.tencent.liteav.basic.c.e;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.i;
import com.tencent.liteav.g.p;
import com.tencent.liteav.g.r;
import com.tencent.liteav.g.s;
import com.tencent.liteav.g.t;
import com.tencent.liteav.i.a.d;
import com.tencent.liteav.i.c;
import com.tencent.liteav.i.c.a;
import com.tencent.liteav.videoediter.ffmpeg.b;
import com.tencent.ugc.TXVideoEditConstants.TXAbsoluteRect;
import com.tencent.ugc.TXVideoEditConstants.TXJoinerResult;
import com.tencent.ugc.TXVideoEditConstants.TXPreviewParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TXVideoJoiner {
    private static final String TAG = "TXVideoJoiner";
    private Context mContext;
    private b mQuickJointer;
    private a mTXCVideoJoinerListener = new a() {
        public void a(float f) {
            if (TXVideoJoiner.this.mTXVideoJoinerListener != null) {
                TXVideoJoiner.this.mTXVideoJoinerListener.onJoinProgress(f);
            }
        }

        public void a(d dVar) {
            TXJoinerResult tXJoinerResult = new TXJoinerResult();
            tXJoinerResult.retCode = dVar.a;
            tXJoinerResult.descMsg = dVar.b;
            if (tXJoinerResult.retCode == 0) {
                int p = i.a().p();
                int q = i.a().q();
                TXCDRApi.txReportDAU(TXVideoJoiner.this.mContext, com.tencent.liteav.basic.datareport.a.aY, p, "");
                TXCDRApi.txReportDAU(TXVideoJoiner.this.mContext, com.tencent.liteav.basic.datareport.a.aZ, q, "");
            }
            if (TXVideoJoiner.this.mTXVideoJoinerListener != null) {
                TXVideoJoiner.this.mTXVideoJoinerListener.onJoinComplete(tXJoinerResult);
            }
        }
    };
    private c.b mTXCVideoPreviewListener = new c.b() {
        public void a(int i) {
            if (TXVideoJoiner.this.mTXVideoPreviewListener != null) {
                TXVideoJoiner.this.mTXVideoPreviewListener.onPreviewProgress(i);
            }
        }

        public void a() {
            if (TXVideoJoiner.this.mTXVideoPreviewListener != null) {
                TXVideoJoiner.this.mTXVideoPreviewListener.onPreviewFinished();
            }
        }
    };
    private f mTXCombineVideo;
    private TXVideoJoinerListener mTXVideoJoinerListener;
    private TXVideoPreviewListener mTXVideoPreviewListener;
    private p mVideoJoinGenerate;
    private r mVideoJoinPreview;
    private s mVideoOutputListConfig;
    private List<String> mVideoPathList;
    private t mVideoSourceListConfig;

    public interface TXVideoJoinerListener {
        void onJoinComplete(TXJoinerResult tXJoinerResult);

        void onJoinProgress(float f);
    }

    public interface TXVideoPreviewListener {
        void onPreviewFinished();

        void onPreviewProgress(int i);
    }

    public TXVideoJoiner(Context context) {
        TXCLog.init();
        this.mContext = context.getApplicationContext();
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.ax);
        TXCDRApi.initCrashReport(this.mContext);
        e.a().a(null, this.mContext);
        this.mVideoJoinPreview = new r(this.mContext);
        this.mVideoJoinGenerate = new p(this.mContext);
        this.mTXCombineVideo = new f(context);
        this.mVideoSourceListConfig = t.a();
        this.mVideoOutputListConfig = s.r();
    }

    public int setVideoPathList(List<String> list) {
        if (list == null || list.size() == 0) {
            TXCLog.e(TAG, "==== setVideoPathList ==== is empty");
            return 0;
        }
        this.mVideoPathList = list;
        this.mVideoSourceListConfig.a(list);
        this.mTXCombineVideo.a((List) list);
        return this.mVideoSourceListConfig.b();
    }

    public void initWithPreview(TXPreviewParam tXPreviewParam) {
        if (tXPreviewParam == null) {
            TXCLog.e(TAG, "=== initWithPreview === please set param not null");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=== initWithPreview === rendeMode: ");
        stringBuilder.append(tXPreviewParam.renderMode);
        TXCLog.i(str, stringBuilder.toString());
        com.tencent.liteav.i.a.f fVar = new com.tencent.liteav.i.a.f();
        fVar.a = tXPreviewParam.videoView;
        fVar.b = tXPreviewParam.renderMode;
        this.mVideoOutputListConfig.t = fVar.b;
        if (this.mVideoJoinPreview != null) {
            this.mVideoJoinPreview.a(fVar);
        }
    }

    public void setTXVideoPreviewListener(TXVideoPreviewListener tXVideoPreviewListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setTXVideoPreviewListener ====listener:");
        stringBuilder.append(tXVideoPreviewListener);
        TXCLog.i(str, stringBuilder.toString());
        this.mTXVideoPreviewListener = tXVideoPreviewListener;
        if (this.mVideoJoinPreview == null) {
            return;
        }
        if (tXVideoPreviewListener == null) {
            this.mVideoJoinPreview.a(null);
        } else {
            this.mVideoJoinPreview.a(this.mTXCVideoPreviewListener);
        }
    }

    public void startPlay() {
        TXCLog.i(TAG, "==== startPlay ====");
        if (this.mVideoJoinPreview != null) {
            this.mVideoJoinPreview.a();
        }
    }

    public void pausePlay() {
        TXCLog.i(TAG, "==== pausePlay ====");
        if (this.mVideoJoinPreview != null) {
            this.mVideoJoinPreview.c();
        }
    }

    public void resumePlay() {
        TXCLog.i(TAG, "==== resumePlay ====");
        if (this.mVideoJoinPreview != null) {
            this.mVideoJoinPreview.d();
        }
    }

    public void stopPlay() {
        TXCLog.i(TAG, "==== stopPlay ====");
        if (this.mVideoJoinPreview != null) {
            this.mVideoJoinPreview.b();
        }
    }

    public void setVideoJoinerListener(TXVideoJoinerListener tXVideoJoinerListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=== setVideoJoinerListener === listener:");
        stringBuilder.append(tXVideoJoinerListener);
        TXCLog.i(str, stringBuilder.toString());
        this.mTXVideoJoinerListener = tXVideoJoinerListener;
        if (this.mVideoJoinGenerate == null) {
            return;
        }
        if (tXVideoJoinerListener == null) {
            this.mVideoJoinGenerate.a(null);
        } else {
            this.mVideoJoinGenerate.a(this.mTXCVideoJoinerListener);
        }
    }

    public void joinVideo(int i, String str) {
        TXCLog.i(TAG, "==== joinVideo ====");
        int a = e.a().a(null, this.mContext);
        if (a != 0 || e.a().c() == 2) {
            String str2 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("joinVideo, checkErrCode = ");
            stringBuilder.append(a);
            stringBuilder.append(", licenseVersionType = ");
            stringBuilder.append(e.a().c());
            TXCLog.e(str2, stringBuilder.toString());
            TXJoinerResult tXJoinerResult = new TXJoinerResult();
            tXJoinerResult.retCode = -5;
            tXJoinerResult.descMsg = "licence校验失败";
            if (this.mTXVideoJoinerListener != null) {
                this.mTXVideoJoinerListener.onJoinComplete(tXJoinerResult);
            }
            return;
        }
        this.mVideoOutputListConfig.i = str;
        this.mVideoOutputListConfig.u = i;
        if (!(quickJoin() || this.mVideoJoinGenerate == null)) {
            this.mVideoJoinGenerate.a();
        }
    }

    public void cancel() {
        TXCLog.i(TAG, "==== cancel ====");
        if (this.mVideoJoinGenerate != null) {
            this.mVideoJoinGenerate.b();
        }
        if (this.mQuickJointer != null) {
            this.mQuickJointer.c();
            this.mQuickJointer = null;
        }
        if (this.mTXCombineVideo != null) {
            this.mTXCombineVideo.a(null);
            this.mTXCombineVideo.b();
        }
    }

    private boolean quickJoin() {
        this.mQuickJointer = new b();
        this.mQuickJointer.a(this.mVideoPathList);
        this.mQuickJointer.a(this.mVideoOutputListConfig.i);
        boolean isMatchQuickJoin = isMatchQuickJoin();
        if (isMatchQuickJoin) {
            this.mQuickJointer.a(new b.a() {
                public void a(b bVar, int i, String str) {
                    bVar.c();
                    bVar.d();
                    TXVideoJoiner.this.mQuickJointer = null;
                    TXJoinerResult tXJoinerResult = new TXJoinerResult();
                    tXJoinerResult.retCode = i == 0 ? 0 : -1;
                    tXJoinerResult.descMsg = str;
                    if (TXVideoJoiner.this.mTXVideoJoinerListener != null) {
                        TXVideoJoiner.this.mTXVideoJoinerListener.onJoinComplete(tXJoinerResult);
                    }
                }

                public void a(b bVar, float f) {
                    if (TXVideoJoiner.this.mTXVideoJoinerListener != null) {
                        TXVideoJoiner.this.mTXVideoJoinerListener.onJoinProgress(f);
                    }
                }
            });
            TXCLog.i(TAG, "==== quickJoin ====");
            this.mQuickJointer.b();
        }
        return isMatchQuickJoin;
    }

    private boolean isMatchQuickJoin() {
        boolean a = this.mQuickJointer.a();
        if (a) {
            int e = this.mQuickJointer.e();
            int f = this.mQuickJointer.f();
            this.mVideoOutputListConfig.a(this.mVideoSourceListConfig.h());
            int[] a2 = com.tencent.liteav.j.c.a(this.mVideoOutputListConfig.u, e, f);
            a = e == a2[0] && f == a2[1];
        }
        if (!a) {
            return a;
        }
        if (isVideoDurationBiggerTooMuchThanAudio()) {
            a = false;
        }
        return a;
    }

    private boolean isVideoDurationBiggerTooMuchThanAudio() {
        if (VERSION.SDK_INT >= 16) {
            com.tencent.liteav.g.e eVar = new com.tencent.liteav.g.e();
            for (int i = 0; i < this.mVideoPathList.size(); i++) {
                try {
                    eVar.a((String) this.mVideoPathList.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                long j = eVar.j();
                long k = eVar.k();
                if (j <= 0 || k <= 0) {
                    return true;
                }
                if (j - k > 400000) {
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("isVideoDurationBiggerTooMuchThanAudio, videoDuration = ");
                    stringBuilder.append(j);
                    stringBuilder.append(", audioDuration = ");
                    stringBuilder.append(k);
                    TXCLog.i(str, stringBuilder.toString());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasBFrame() {
        if (VERSION.SDK_INT >= 16) {
            com.tencent.liteav.g.e eVar = new com.tencent.liteav.g.e();
            for (int i = 0; i < this.mVideoPathList.size(); i++) {
                long j = -1;
                try {
                    eVar.a((String) this.mVideoPathList.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int d = eVar.d();
                if (d <= 0) {
                    d = 1;
                }
                eVar.a(0);
                while (true) {
                    long r = eVar.r();
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("isMatchQuickJoin, video index = ");
                    stringBuilder.append(i);
                    stringBuilder.append(", pts = ");
                    stringBuilder.append(r);
                    stringBuilder.append(", lastVideoPts = ");
                    stringBuilder.append(j);
                    TXCLog.i(str, stringBuilder.toString());
                    if (r >= ((long) d) * 1000000) {
                        break;
                    } else if (r < j) {
                        eVar.o();
                        return true;
                    } else if (eVar.c(new com.tencent.liteav.d.e())) {
                        break;
                    } else {
                        j = r;
                    }
                }
            }
            eVar.o();
        }
        return false;
    }

    public void setSplitScreenList(List<TXAbsoluteRect> list, int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setSplitScreenList ====canvasWidth:");
        stringBuilder.append(i);
        stringBuilder.append(",canvasHeight:");
        stringBuilder.append(i2);
        TXCLog.i(str, stringBuilder.toString());
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < list.size(); i3++) {
                TXAbsoluteRect tXAbsoluteRect = (TXAbsoluteRect) list.get(i3);
                com.tencent.liteav.i.a.a aVar = new com.tencent.liteav.i.a.a();
                aVar.c = tXAbsoluteRect.width;
                aVar.d = tXAbsoluteRect.height;
                aVar.a = tXAbsoluteRect.x;
                aVar.b = tXAbsoluteRect.y;
                arrayList.add(aVar);
            }
            this.mTXCombineVideo.a(arrayList, i, i2);
        }
    }

    public void splitJoinVideo(int i, String str) {
        TXCLog.i(TAG, "==== splitJoinVideo ====");
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aX);
        i = e.a().a(null, this.mContext);
        if (i != 0 || e.a().c() == 2) {
            str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("splitJoinVideo, checkErrCode = ");
            stringBuilder.append(i);
            stringBuilder.append(", licenseVersionType = ");
            stringBuilder.append(e.a().c());
            TXCLog.e(str, stringBuilder.toString());
            TXJoinerResult tXJoinerResult = new TXJoinerResult();
            tXJoinerResult.retCode = -5;
            tXJoinerResult.descMsg = "licence校验失败";
            if (this.mTXVideoJoinerListener != null) {
                this.mTXVideoJoinerListener.onJoinComplete(tXJoinerResult);
            }
            return;
        }
        if (this.mTXCombineVideo != null) {
            this.mTXCombineVideo.a(str);
            this.mTXCombineVideo.a(this.mTXCVideoJoinerListener);
            this.mTXCombineVideo.a();
        }
    }
}
