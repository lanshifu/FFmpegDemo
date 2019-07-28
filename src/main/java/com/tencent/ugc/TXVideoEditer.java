package com.tencent.ugc;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.b;
import com.tencent.liteav.c.d;
import com.tencent.liteav.c.i;
import com.tencent.liteav.c.j;
import com.tencent.liteav.c.k;
import com.tencent.liteav.e.ad;
import com.tencent.liteav.e.ae;
import com.tencent.liteav.e.ag;
import com.tencent.liteav.e.u;
import com.tencent.liteav.e.y;
import com.tencent.liteav.e.z;
import com.tencent.liteav.f.f;
import com.tencent.liteav.f.h;
import com.tencent.liteav.i.a.g;
import com.tencent.liteav.i.b.a;
import com.tencent.liteav.i.b.c;
import com.tencent.liteav.i.b.e;
import com.tencent.ugc.TXVideoEditConstants.TXAnimatedPaster;
import com.tencent.ugc.TXVideoEditConstants.TXGenerateResult;
import com.tencent.ugc.TXVideoEditConstants.TXPaster;
import com.tencent.ugc.TXVideoEditConstants.TXPreviewParam;
import com.tencent.ugc.TXVideoEditConstants.TXRect;
import com.tencent.ugc.TXVideoEditConstants.TXRepeat;
import com.tencent.ugc.TXVideoEditConstants.TXSpeed;
import com.tencent.ugc.TXVideoEditConstants.TXSubtitle;
import com.tencent.ugc.TXVideoEditConstants.TXThumbnail;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class TXVideoEditer {
    private static final String TAG = "TXVideoEditer";
    private b mBgmConfig;
    private Context mContext;
    private volatile boolean mIsPreviewStart;
    private d mMotionFilterConfig;
    private boolean mSmartLicenseSupport = true;
    private a mTXCThumbnailListener = new a() {
        public void a(int i, long j, Bitmap bitmap) {
            if (TXVideoEditer.this.mTXThumbnailListener != null) {
                TXVideoEditer.this.mTXThumbnailListener.onThumbnail(i, j, bitmap);
            }
        }
    };
    private com.tencent.liteav.i.b.b mTXCVideoCustomProcessListener = new com.tencent.liteav.i.b.b() {
        public int a(int i, int i2, int i3, long j) {
            return TXVideoEditer.this.mTXVideoCustomProcessListener != null ? TXVideoEditer.this.mTXVideoCustomProcessListener.onTextureCustomProcess(i, i2, i3, j) : 0;
        }

        public void a() {
            if (TXVideoEditer.this.mTXVideoCustomProcessListener != null) {
                TXVideoEditer.this.mTXVideoCustomProcessListener.onTextureDestroyed();
            }
        }
    };
    private c mTXCVideoGenerateListener = new c() {
        public void a(float f) {
            if (TXVideoEditer.this.mTXVideoGenerateListener != null) {
                TXVideoEditer.this.mTXVideoGenerateListener.onGenerateProgress(f);
            }
        }

        public void a(com.tencent.liteav.i.a.c cVar) {
            TXGenerateResult tXGenerateResult = new TXGenerateResult();
            tXGenerateResult.retCode = cVar.a;
            tXGenerateResult.descMsg = cVar.b;
            if (tXGenerateResult.retCode == 0) {
                int p = i.a().p();
                int q = i.a().q();
                TXCDRApi.txReportDAU(TXVideoEditer.this.mContext, com.tencent.liteav.basic.datareport.a.aY, p, "");
                TXCDRApi.txReportDAU(TXVideoEditer.this.mContext, com.tencent.liteav.basic.datareport.a.aZ, q, "");
            }
            if (TXVideoEditer.this.mTXVideoGenerateListener != null) {
                TXVideoEditer.this.mTXVideoGenerateListener.onGenerateComplete(tXGenerateResult);
            }
        }
    };
    private com.tencent.liteav.i.b.d mTXCVideoPreviewListener = new com.tencent.liteav.i.b.d() {
        public void a(int i) {
            if (TXVideoEditer.this.mTXVideoPreviewListener != null) {
                TXVideoEditer.this.mTXVideoPreviewListener.onPreviewProgress(i);
            }
        }

        public void a() {
            if (TXVideoEditer.this.mTXVideoPreviewListener != null) {
                TXVideoEditer.this.mTXVideoPreviewListener.onPreviewFinished();
            }
        }
    };
    private e mTXCVideoProcessListener = new e() {
        public void a(float f) {
            if (TXVideoEditer.this.mTXVideoProcessListener != null) {
                TXVideoEditer.this.mTXVideoProcessListener.onProcessProgress(f);
            }
        }

        public void a(com.tencent.liteav.i.a.c cVar) {
            if (TXVideoEditer.this.mTXVideoProcessListener != null) {
                TXGenerateResult tXGenerateResult = new TXGenerateResult();
                tXGenerateResult.retCode = cVar.a;
                tXGenerateResult.descMsg = cVar.b;
                TXVideoEditer.this.mTXVideoProcessListener.onProcessComplete(tXGenerateResult);
            }
        }
    };
    private TXThumbnailListener mTXThumbnailListener;
    private TXVideoCustomProcessListener mTXVideoCustomProcessListener;
    private TXVideoGenerateListener mTXVideoGenerateListener;
    private TXVideoPreviewListener mTXVideoPreviewListener;
    private TXVideoProcessListener mTXVideoProcessListener;
    private u mVideoAverageThumbnailGenerate;
    private y mVideoEditGenerate;
    private z mVideoEditerPreview;
    private i mVideoOutputConfig;
    private j mVideoPreProcessConfig;
    private ad mVideoProcessGenerate;
    private ae mVideoRecordGenerate;
    private k mVideoSourceConfig;
    private ag mVideoTimelistThumbnailGenerate;

    public interface TXPCMCallbackListener {
        TXAudioFrame onPCMCallback(TXAudioFrame tXAudioFrame);
    }

    public interface TXThumbnailListener {
        void onThumbnail(int i, long j, Bitmap bitmap);
    }

    public interface TXVideoCustomProcessListener {
        int onTextureCustomProcess(int i, int i2, int i3, long j);

        void onTextureDestroyed();
    }

    public interface TXVideoGenerateListener {
        void onGenerateComplete(TXGenerateResult tXGenerateResult);

        void onGenerateProgress(float f);
    }

    public interface TXVideoPreviewListener {
        void onPreviewFinished();

        void onPreviewProgress(int i);
    }

    public interface TXVideoProcessListener {
        void onProcessComplete(TXGenerateResult tXGenerateResult);

        void onProcessProgress(float f);
    }

    public TXVideoEditer(Context context) {
        TXCLog.init();
        this.mContext = context.getApplicationContext();
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aw);
        TXCDRApi.initCrashReport(this.mContext);
        com.tencent.liteav.basic.c.e.a().a(null, this.mContext);
        this.mVideoEditerPreview = new z(this.mContext);
        this.mVideoEditGenerate = new y(this.mContext);
        this.mVideoProcessGenerate = new ad(this.mContext);
        this.mVideoRecordGenerate = new ae(this.mContext);
        this.mVideoAverageThumbnailGenerate = new u(this.mContext);
        this.mVideoTimelistThumbnailGenerate = new ag(this.mContext);
        this.mVideoOutputConfig = i.a();
        this.mVideoSourceConfig = k.a();
        this.mVideoPreProcessConfig = j.a();
        this.mBgmConfig = b.a();
        this.mMotionFilterConfig = d.a();
    }

    public int setVideoPath(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=== setVideoPath === videoPath: ");
        stringBuilder.append(str);
        TXCLog.i(str2, stringBuilder.toString());
        this.mVideoSourceConfig.a = str;
        return this.mVideoSourceConfig.e();
    }

    private boolean isSmartLicense() {
        com.tencent.liteav.basic.c.e.a().a(null, this.mContext);
        if (com.tencent.liteav.basic.c.e.a().c() == -1) {
            this.mSmartLicenseSupport = false;
        } else if (com.tencent.liteav.basic.c.e.a().c() == 2) {
            return true;
        }
        return false;
    }

    public void setCustomVideoProcessListener(TXVideoCustomProcessListener tXVideoCustomProcessListener) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setCustomVideoProcessListener is not supported in UGC_Smart license");
            return;
        }
        this.mTXVideoCustomProcessListener = tXVideoCustomProcessListener;
        if (this.mVideoEditerPreview != null) {
            this.mVideoEditerPreview.a(this.mTXCVideoCustomProcessListener);
        }
        if (this.mVideoEditGenerate != null) {
            this.mVideoEditGenerate.a(this.mTXCVideoCustomProcessListener);
        }
    }

    public void setSpecialRatio(float f) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setSpecialRatio is not supported in UGC_Smart license");
            return;
        }
        com.tencent.liteav.d.d d = this.mVideoPreProcessConfig.d();
        if (d == null) {
            d = new com.tencent.liteav.d.d();
        }
        d.a(f);
        d.b(CropImageView.DEFAULT_ASPECT_RATIO);
        this.mVideoPreProcessConfig.a(d);
    }

    public void setFilter(Bitmap bitmap) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setFilter is not supported in UGC_Smart license");
            return;
        }
        float b;
        float c;
        com.tencent.liteav.d.d d = this.mVideoPreProcessConfig.d();
        if (d != null) {
            b = d.b();
            c = d.c();
        } else {
            b = 0.5f;
            c = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        setFilter(bitmap, b, null, c, 1.0f);
    }

    public void setFilter(Bitmap bitmap, float f, Bitmap bitmap2, float f2, float f3) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setFilter is not supported in UGC_Smart license");
            return;
        }
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aP);
        this.mVideoPreProcessConfig.a(new com.tencent.liteav.d.d(f3, bitmap, f, bitmap2, f2));
    }

    public void setBeautyFilter(int i, int i2) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBeautyFilter is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setBeautyFilter ==== beautyLevel: ");
        stringBuilder.append(i);
        stringBuilder.append(",whiteningLevel:");
        stringBuilder.append(i2);
        TXCLog.i(str, stringBuilder.toString());
        this.mVideoPreProcessConfig.a(new com.tencent.liteav.d.c(i, i2));
    }

    public int setPictureList(List<Bitmap> list, int i) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBeautyFilter is not supported in UGC_Smart license");
            return -1;
        } else if (list == null || list.size() <= 0) {
            TXCLog.e(TAG, "setPictureList, bitmapList is empty!");
            return -1;
        } else {
            if (i <= 15) {
                TXCLog.i(TAG, "setPictureList, fps <= 15, set 15");
                i = 15;
            }
            if (i >= 30) {
                TXCLog.i(TAG, "setPictureList, fps >= 30, set 30");
                i = 30;
            }
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aW);
            this.mVideoSourceConfig.a(list, i);
            this.mVideoEditerPreview.a((List) list, i);
            return 0;
        }
    }

    public long setPictureTransition(int i) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setPictureTransition is not supported in UGC_Smart license");
            return 0;
        }
        long a = this.mVideoEditerPreview.a(i);
        i.a().l = 1000 * a;
        return a;
    }

    public int setBGM(String str) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBGM is not supported in UGC_Smart license");
            return 0;
        }
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setBGM ==== path: ");
        stringBuilder.append(str);
        TXCLog.i(str2, stringBuilder.toString());
        int a = !TextUtils.isEmpty(str) ? k.a().a(str) : 0;
        if (a != 0) {
            return a;
        }
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aQ);
        this.mBgmConfig.a(str);
        this.mVideoEditerPreview.b(str);
        stopPlay();
        return 0;
    }

    public void setBGMLoop(boolean z) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBGMLoop is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setBGMLoop ==== looping: ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mBgmConfig.e = z;
        this.mVideoEditerPreview.a(z);
    }

    public void setBGMAtVideoTime(long j) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBGMAtVideoTime is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setBGMAtVideoTime ==== videoStartTime: ");
        stringBuilder.append(j);
        TXCLog.i(str, stringBuilder.toString());
        this.mBgmConfig.d = j;
        this.mVideoEditerPreview.a(j);
    }

    public void setBGMStartTime(long j, long j2) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBGMStartTime is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setBGMStartTime ==== startTime: ");
        stringBuilder.append(j);
        stringBuilder.append(", endTime: ");
        stringBuilder.append(j2);
        TXCLog.i(str, stringBuilder.toString());
        this.mBgmConfig.b = j;
        this.mBgmConfig.c = j2;
        this.mVideoEditerPreview.a(j, j2);
    }

    public void setBGMVolume(float f) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBGMVolume is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setBGMVolume ==== volume: ");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        this.mBgmConfig.g = f;
        this.mVideoEditerPreview.b(f);
    }

    public void setBGMFadeInOutDuration(long j, long j2) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setBGMFadeInOutDuration is not supported in UGC_Smart license");
        } else if (!(j == 0 && j2 == 0) && j >= 0 && j2 >= 0) {
            this.mBgmConfig.j = true;
            this.mBgmConfig.k = j;
            this.mBgmConfig.l = j2;
        } else {
            this.mBgmConfig.j = false;
        }
    }

    public void setWaterMark(Bitmap bitmap, TXRect tXRect) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setWaterMark is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setWaterMark ==== waterMark: ");
        stringBuilder.append(bitmap);
        stringBuilder.append(", rect: ");
        stringBuilder.append(tXRect);
        TXCLog.i(str, stringBuilder.toString());
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aU);
        g gVar = new g();
        gVar.c = tXRect.width;
        gVar.a = tXRect.x;
        gVar.b = tXRect.y;
        this.mVideoPreProcessConfig.a(new com.tencent.liteav.d.j(bitmap, gVar));
    }

    public void setTailWaterMark(Bitmap bitmap, TXRect tXRect, int i) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setTailWaterMark is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setTailWaterMark ==== tailWaterMark: ");
        stringBuilder.append(bitmap);
        stringBuilder.append(", rect: ");
        stringBuilder.append(tXRect);
        stringBuilder.append(", duration: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aV);
        g gVar = new g();
        gVar.c = tXRect.width;
        gVar.a = tXRect.x;
        gVar.b = tXRect.y;
        com.tencent.liteav.f.j.a().a(new com.tencent.liteav.d.i(bitmap, gVar, i));
    }

    public void setSubtitleList(List<TXSubtitle> list) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setSubtitleList is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setSubtitleList ==== subtitleList: ");
        stringBuilder.append(list);
        TXCLog.i(str, stringBuilder.toString());
        if (list != null) {
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aT);
            List arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                TXSubtitle tXSubtitle = (TXSubtitle) list.get(i);
                com.tencent.liteav.i.a.j jVar = new com.tencent.liteav.i.a.j();
                g gVar = new g();
                gVar.c = tXSubtitle.frame.width;
                gVar.a = tXSubtitle.frame.x;
                gVar.b = tXSubtitle.frame.y;
                jVar.b = gVar;
                jVar.a = tXSubtitle.titleImage;
                jVar.c = tXSubtitle.startTime;
                jVar.d = tXSubtitle.endTime;
                arrayList.add(jVar);
            }
            h.a().a(arrayList);
        } else {
            h.a().a(null);
        }
    }

    public void setAnimatedPasterList(List<TXAnimatedPaster> list) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setAnimatedPasterList is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setAnimatedPasterList ==== animatedPasterList: ");
        stringBuilder.append(list);
        TXCLog.i(str, stringBuilder.toString());
        if (list != null) {
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aS);
            List arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                TXAnimatedPaster tXAnimatedPaster = (TXAnimatedPaster) list.get(i);
                com.tencent.liteav.i.a.b bVar = new com.tencent.liteav.i.a.b();
                g gVar = new g();
                gVar.c = tXAnimatedPaster.frame.width;
                gVar.a = tXAnimatedPaster.frame.x;
                gVar.b = tXAnimatedPaster.frame.y;
                bVar.b = gVar;
                bVar.a = tXAnimatedPaster.animatedPasterPathFolder;
                bVar.c = tXAnimatedPaster.startTime;
                bVar.d = tXAnimatedPaster.endTime;
                bVar.e = tXAnimatedPaster.rotation;
                arrayList.add(bVar);
            }
            com.tencent.liteav.f.a.a().a(arrayList);
        } else {
            com.tencent.liteav.f.a.a().a(null);
        }
    }

    public void setPasterList(List<TXPaster> list) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setPasterList is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setPasterList ==== pasterList: ");
        stringBuilder.append(list);
        TXCLog.i(str, stringBuilder.toString());
        if (list != null) {
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aR);
            List arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                TXPaster tXPaster = (TXPaster) list.get(i);
                com.tencent.liteav.i.a.e eVar = new com.tencent.liteav.i.a.e();
                g gVar = new g();
                gVar.c = tXPaster.frame.width;
                gVar.a = tXPaster.frame.x;
                gVar.b = tXPaster.frame.y;
                eVar.b = gVar;
                eVar.a = tXPaster.pasterImage;
                eVar.c = tXPaster.startTime;
                eVar.d = tXPaster.endTime;
                arrayList.add(eVar);
            }
            f.a().a(arrayList);
        } else {
            f.a().a(null);
        }
    }

    public void setRenderRotation(int i) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setRenderRotation is not supported in UGC_Smart license");
        } else {
            j.a().a(i);
        }
    }

    public void setSpeedList(List<TXSpeed> list) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setSpeedList is not supported in UGC_Smart license");
            return;
        }
        TXCLog.i(TAG, "==== setSpeedList ==== ");
        if (list != null) {
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aL);
            List arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                TXSpeed tXSpeed = (TXSpeed) list.get(i);
                com.tencent.liteav.i.a.i iVar = new com.tencent.liteav.i.a.i();
                iVar.a = tXSpeed.speedLevel;
                iVar.b = tXSpeed.startTime;
                iVar.c = tXSpeed.endTime;
                arrayList.add(iVar);
            }
            com.tencent.liteav.f.g.a().a(arrayList);
        } else {
            com.tencent.liteav.f.g.a().a(null);
        }
    }

    public void setRepeatPlay(List<TXRepeat> list) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setRepeatPlay is not supported in UGC_Smart license");
            return;
        }
        TXCLog.i(TAG, "==== setRepeatPlay ==== ");
        if (list != null) {
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aM);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                TXRepeat tXRepeat = (TXRepeat) list.get(i);
                com.tencent.liteav.i.a.h hVar = new com.tencent.liteav.i.a.h();
                hVar.c = tXRepeat.repeatTimes;
                hVar.a = tXRepeat.startTime;
                hVar.b = tXRepeat.endTime;
                arrayList.add(hVar);
            }
            com.tencent.liteav.c.f.a().a(arrayList);
            TXRepeat tXRepeat2 = (TXRepeat) list.get(0);
            this.mVideoEditerPreview.c(tXRepeat2.startTime * 1000, tXRepeat2.endTime * 1000);
        } else {
            TXCLog.i(TAG, "==== cancel setRepeatPlay ==== ");
            com.tencent.liteav.c.f.a().a(null);
            this.mVideoEditerPreview.c(0, 0);
        }
    }

    public void setReverse(boolean z) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "setReverse is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setReverse ====isReverse:");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        if (z) {
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aN);
        }
        this.mVideoEditerPreview.c();
        com.tencent.liteav.c.g.a().a(z);
    }

    public void startEffect(int i, long j) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "startEffect is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== startEffect ==== type: ");
        stringBuilder.append(i);
        stringBuilder.append(", startTime: ");
        stringBuilder.append(j);
        TXCLog.i(str, stringBuilder.toString());
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aO, i, "");
        com.tencent.liteav.d.f fVar = null;
        switch (i) {
            case 0:
                fVar = new com.tencent.liteav.d.f(2);
                break;
            case 1:
                fVar = new com.tencent.liteav.d.f(3);
                break;
            case 2:
                fVar = new com.tencent.liteav.d.f(0);
                break;
            case 3:
                fVar = new com.tencent.liteav.d.f(1);
                break;
            case 4:
                fVar = new com.tencent.liteav.d.f(4);
                break;
            case 5:
                fVar = new com.tencent.liteav.d.f(5);
                break;
            case 6:
                fVar = new com.tencent.liteav.d.f(6);
                break;
            case 7:
                fVar = new com.tencent.liteav.d.f(7);
                break;
            case 8:
                fVar = new com.tencent.liteav.d.f(8);
                break;
            case 9:
                fVar = new com.tencent.liteav.d.f(11);
                break;
            case 10:
                fVar = new com.tencent.liteav.d.f(10);
                break;
        }
        if (fVar != null) {
            if (com.tencent.liteav.c.g.a().b()) {
                fVar.c = j * 1000;
            } else {
                fVar.b = j * 1000;
            }
            this.mMotionFilterConfig.a(fVar);
        }
    }

    public void stopEffect(int i, long j) {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "stopEffect is not supported in UGC_Smart license");
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== stopEffect ==== type: ");
        stringBuilder.append(i);
        stringBuilder.append(", endTime: ");
        stringBuilder.append(j);
        TXCLog.i(str, stringBuilder.toString());
        com.tencent.liteav.d.f b = this.mMotionFilterConfig.b();
        if (b != null) {
            if (com.tencent.liteav.c.g.a().b()) {
                b.b = j * 1000;
            } else {
                b.c = j * 1000;
            }
        }
    }

    public void deleteLastEffect() {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "deleteLastEffect is not supported in UGC_Smart license");
            return;
        }
        TXCLog.i(TAG, "==== deleteLastEffect ====");
        this.mMotionFilterConfig.c();
    }

    public void deleteAllEffect() {
        if (isSmartLicense()) {
            TXCLog.e(TAG, "deleteAllEffect is not supported in UGC_Smart license");
            return;
        }
        TXCLog.i(TAG, "==== deleteAllEffect ====");
        this.mMotionFilterConfig.e();
    }

    public void setVideoProcessListener(TXVideoProcessListener tXVideoProcessListener) {
        this.mTXVideoProcessListener = tXVideoProcessListener;
        if (tXVideoProcessListener == null) {
            if (this.mVideoProcessGenerate != null) {
                this.mVideoProcessGenerate.a(null);
            }
            if (this.mVideoRecordGenerate != null) {
                this.mVideoRecordGenerate.a(null);
            }
            if (this.mVideoAverageThumbnailGenerate != null) {
                this.mVideoAverageThumbnailGenerate.a(null);
                return;
            }
            return;
        }
        if (this.mVideoProcessGenerate != null) {
            this.mVideoProcessGenerate.a(this.mTXCVideoProcessListener);
        }
        if (this.mVideoRecordGenerate != null) {
            this.mVideoRecordGenerate.a(this.mTXCVideoProcessListener);
        }
        if (this.mVideoAverageThumbnailGenerate != null) {
            this.mVideoAverageThumbnailGenerate.a(this.mTXCVideoProcessListener);
        }
    }

    public void getThumbnail(List<Long> list, int i, int i2, boolean z, TXThumbnailListener tXThumbnailListener) {
        if (list != null && list.size() != 0) {
            this.mTXThumbnailListener = tXThumbnailListener;
            com.tencent.liteav.c.h.a().i();
            com.tencent.liteav.c.h.a().a(z);
            if (this.mVideoTimelistThumbnailGenerate != null) {
                this.mVideoTimelistThumbnailGenerate.a(this.mTXCThumbnailListener);
                this.mVideoTimelistThumbnailGenerate.a(i);
                this.mVideoTimelistThumbnailGenerate.b(i2);
                this.mVideoTimelistThumbnailGenerate.b(z);
                this.mVideoTimelistThumbnailGenerate.a((List) list);
                this.mVideoTimelistThumbnailGenerate.a();
            }
        }
    }

    public void getThumbnail(int i, int i2, int i3, boolean z, TXThumbnailListener tXThumbnailListener) {
        com.tencent.liteav.c.h.a().i();
        this.mTXThumbnailListener = tXThumbnailListener;
        com.tencent.liteav.i.a.k kVar = new com.tencent.liteav.i.a.k();
        kVar.a = i;
        kVar.b = i2;
        kVar.c = i3;
        com.tencent.liteav.c.h.a().a(kVar);
        com.tencent.liteav.c.h.a().a(z);
        if (this.mVideoAverageThumbnailGenerate != null) {
            this.mVideoAverageThumbnailGenerate.a(this.mTXCThumbnailListener);
            this.mVideoAverageThumbnailGenerate.a(true);
            this.mVideoAverageThumbnailGenerate.b(z);
            this.mVideoAverageThumbnailGenerate.a();
        }
    }

    public void setThumbnail(TXThumbnail tXThumbnail) {
        com.tencent.liteav.i.a.k kVar = new com.tencent.liteav.i.a.k();
        kVar.a = tXThumbnail.count;
        kVar.b = tXThumbnail.width;
        kVar.c = tXThumbnail.height;
        com.tencent.liteav.c.h.a().a(kVar);
    }

    public void setThumbnailListener(TXThumbnailListener tXThumbnailListener) {
        this.mTXThumbnailListener = tXThumbnailListener;
        if (tXThumbnailListener == null) {
            if (this.mVideoProcessGenerate != null) {
                this.mVideoProcessGenerate.a(null);
            }
            if (this.mVideoRecordGenerate != null) {
                this.mVideoRecordGenerate.a(null);
            }
            if (this.mVideoAverageThumbnailGenerate != null) {
                this.mVideoAverageThumbnailGenerate.a(null);
            }
            if (this.mVideoTimelistThumbnailGenerate != null) {
                this.mVideoTimelistThumbnailGenerate.a(null);
                return;
            }
            return;
        }
        if (this.mVideoProcessGenerate != null) {
            this.mVideoProcessGenerate.a(this.mTXCThumbnailListener);
        }
        if (this.mVideoRecordGenerate != null) {
            this.mVideoRecordGenerate.a(this.mTXCThumbnailListener);
        }
        if (this.mVideoAverageThumbnailGenerate != null) {
            this.mVideoAverageThumbnailGenerate.a(this.mTXCThumbnailListener);
        }
        if (this.mVideoTimelistThumbnailGenerate != null) {
            this.mVideoTimelistThumbnailGenerate.a(this.mTXCThumbnailListener);
        }
    }

    public void processVideo() {
        TXCLog.i(TAG, "=== processVideo ===");
        if (com.tencent.liteav.basic.c.e.a().a(null, this.mContext) != 0 || (com.tencent.liteav.basic.c.e.a().c() == 2 && !this.mSmartLicenseSupport)) {
            TXGenerateResult tXGenerateResult = new TXGenerateResult();
            tXGenerateResult.retCode = -5;
            tXGenerateResult.descMsg = "licence校验失败";
            if (this.mTXVideoProcessListener != null) {
                this.mTXVideoProcessListener.onProcessComplete(tXGenerateResult);
            }
            return;
        }
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.ba);
        this.mVideoOutputConfig.o = com.tencent.liteav.j.f.a(1);
        this.mVideoOutputConfig.j = 3;
        this.mVideoOutputConfig.m = true;
        boolean f = this.mVideoSourceConfig.f();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("allFullFrame:");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        this.mVideoOutputConfig.r = f;
        if (f) {
            if (this.mVideoRecordGenerate != null) {
                this.mVideoRecordGenerate.a();
            }
        } else if (this.mVideoProcessGenerate != null) {
            this.mVideoProcessGenerate.a();
        }
    }

    public void release() {
        TXCLog.i(TAG, "=== release ===");
        i.a().o();
        k.a().g();
        com.tencent.liteav.c.c.a().h();
        com.tencent.liteav.c.g.a().c();
        com.tencent.liteav.c.f.a().c();
        com.tencent.liteav.f.g.a().d();
        j.a().f();
        h.a().c();
        f.a().c();
        com.tencent.liteav.f.a.a().c();
        d.a().e();
        b.a().b();
        com.tencent.liteav.f.j.a().i();
        com.tencent.liteav.c.h.a().j();
        if (this.mVideoEditerPreview != null) {
            this.mVideoEditerPreview.f();
        }
        if (this.mVideoRecordGenerate != null) {
            this.mVideoRecordGenerate.c();
        }
        if (this.mVideoProcessGenerate != null) {
            this.mVideoProcessGenerate.c();
        }
        if (this.mVideoEditGenerate != null) {
            this.mVideoEditGenerate.c();
        }
        if (this.mVideoTimelistThumbnailGenerate != null) {
            this.mVideoTimelistThumbnailGenerate.c();
        }
        if (this.mVideoAverageThumbnailGenerate != null) {
            this.mVideoAverageThumbnailGenerate.c();
        }
        this.mTXCThumbnailListener = null;
        this.mTXCVideoCustomProcessListener = null;
        this.mTXCVideoGenerateListener = null;
        this.mTXCVideoPreviewListener = null;
        this.mTXCVideoProcessListener = null;
    }

    public void setTXVideoPreviewListener(TXVideoPreviewListener tXVideoPreviewListener) {
        this.mTXVideoPreviewListener = tXVideoPreviewListener;
        if (this.mVideoEditerPreview == null) {
            return;
        }
        if (tXVideoPreviewListener == null) {
            this.mVideoEditerPreview.a(null);
        } else {
            this.mVideoEditerPreview.a(this.mTXCVideoPreviewListener);
        }
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
        fVar.b = tXPreviewParam.renderMode;
        fVar.a = tXPreviewParam.videoView;
        this.mVideoOutputConfig.s = fVar.b;
        if (this.mVideoEditerPreview != null) {
            this.mVideoEditerPreview.a(fVar);
        }
    }

    public void startPlayFromTime(long j, long j2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== startPlayFromTime ==== startTime: ");
        stringBuilder.append(j);
        stringBuilder.append(", endTime: ");
        stringBuilder.append(j2);
        TXCLog.i(str, stringBuilder.toString());
        if (this.mIsPreviewStart) {
            stopPlay();
        }
        this.mIsPreviewStart = true;
        if (this.mVideoEditerPreview != null) {
            com.tencent.liteav.c.c.a().b(j * 1000, 1000 * j2);
            this.mVideoEditerPreview.b(j, j2);
            this.mVideoEditerPreview.b();
        }
    }

    public void pausePlay() {
        TXCLog.i(TAG, "==== pausePlay ====");
        if (this.mVideoEditerPreview != null) {
            this.mVideoEditerPreview.e();
        }
    }

    public void resumePlay() {
        TXCLog.i(TAG, "==== resumePlay ====");
        if (this.mVideoEditerPreview != null) {
            this.mVideoEditerPreview.d();
        }
    }

    public void stopPlay() {
        TXCLog.i(TAG, "==== stopPlay ====");
        if (this.mIsPreviewStart) {
            if (this.mVideoEditerPreview != null) {
                this.mVideoEditerPreview.c();
            }
            this.mIsPreviewStart = false;
        }
    }

    public void previewAtTime(long j) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== previewAtTime ==== timeMs: ");
        stringBuilder.append(j);
        TXCLog.i(str, stringBuilder.toString());
        if (this.mVideoEditerPreview != null) {
            this.mVideoEditerPreview.b(j);
        }
    }

    public void setVideoGenerateListener(TXVideoGenerateListener tXVideoGenerateListener) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=== setVideoGenerateListener === listener:");
        stringBuilder.append(tXVideoGenerateListener);
        TXCLog.i(str, stringBuilder.toString());
        this.mTXVideoGenerateListener = tXVideoGenerateListener;
        if (this.mVideoEditGenerate == null) {
            return;
        }
        if (tXVideoGenerateListener == null) {
            this.mVideoEditGenerate.a(null);
        } else {
            this.mVideoEditGenerate.a(this.mTXCVideoGenerateListener);
        }
    }

    public void setCutFromTime(long j, long j2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setCutFromTime ==== startTime: ");
        stringBuilder.append(j);
        stringBuilder.append(", endTime: ");
        stringBuilder.append(j2);
        TXCLog.i(str, stringBuilder.toString());
        TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.aK);
        com.tencent.liteav.c.c.a().a(j * 1000, j2 * 1000);
    }

    public void setVideoBitrate(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setVideoBitrate ==== videoBitrate: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mVideoOutputConfig.p = i;
    }

    public void setAudioBitrate(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setAudioBitrate ==== audioBitrate: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mVideoOutputConfig.q = i * Filter.K;
    }

    public void generateVideo(int i, String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== generateVideo ==== videoCompressed: ");
        stringBuilder.append(i);
        stringBuilder.append(", videoOutputPath: ");
        stringBuilder.append(str);
        TXCLog.i(str2, stringBuilder.toString());
        if (com.tencent.liteav.basic.c.e.a().a(null, this.mContext) != 0 || (com.tencent.liteav.basic.c.e.a().c() == 2 && !this.mSmartLicenseSupport)) {
            TXGenerateResult tXGenerateResult = new TXGenerateResult();
            tXGenerateResult.retCode = -5;
            tXGenerateResult.descMsg = "licence校验失败";
            if (this.mTXVideoGenerateListener != null) {
                this.mTXVideoGenerateListener.onGenerateComplete(tXGenerateResult);
            }
            return;
        }
        this.mVideoOutputConfig.i = str;
        this.mVideoOutputConfig.j = i;
        this.mVideoOutputConfig.m = false;
        if (this.mVideoEditGenerate != null) {
            this.mVideoEditGenerate.a();
        }
    }

    public void cancel() {
        TXCLog.i(TAG, "==== cancel ====");
        if (this.mVideoAverageThumbnailGenerate != null) {
            this.mVideoAverageThumbnailGenerate.b();
        }
        if (this.mVideoTimelistThumbnailGenerate != null) {
            this.mVideoTimelistThumbnailGenerate.b();
        }
        if (this.mVideoRecordGenerate != null) {
            this.mVideoRecordGenerate.b();
        }
        if (this.mVideoProcessGenerate != null) {
            this.mVideoProcessGenerate.b();
        }
        if (this.mVideoEditGenerate != null) {
            this.mVideoEditGenerate.b();
        }
    }

    public void refreshOneFrame() {
        TXCLog.i(TAG, "==== refreshOneFrame ====");
        if (this.mVideoEditerPreview != null) {
            this.mVideoEditerPreview.a();
        }
    }

    public void setVideoVolume(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== setVideoVolume ==== volume: ");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        this.mBgmConfig.f = f;
        if (this.mVideoEditerPreview != null) {
            this.mVideoEditerPreview.a(f);
        }
    }
}
