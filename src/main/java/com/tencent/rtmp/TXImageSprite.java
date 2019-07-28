package com.tencent.rtmp;

import android.content.Context;
import android.graphics.Bitmap;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.rtmp.a.a;
import com.tencent.rtmp.a.b;
import java.util.List;

public class TXImageSprite implements a {
    private Context mContext;
    private a mImageSprite;

    public TXImageSprite(Context context) {
        this.mContext = context.getApplicationContext();
        TXCDRApi.initCrashReport(context);
    }

    public void setVTTUrlAndImageUrls(String str, List<String> list) {
        if (this.mImageSprite != null) {
            release();
        }
        if (str != null && list != null && list.size() != 0) {
            TXCDRApi.txReportDAU(this.mContext, com.tencent.liteav.basic.datareport.a.bz);
            this.mImageSprite = new b();
            this.mImageSprite.setVTTUrlAndImageUrls(str, list);
        }
    }

    public Bitmap getThumbnail(float f) {
        return this.mImageSprite != null ? this.mImageSprite.getThumbnail(f) : null;
    }

    public void release() {
        if (this.mImageSprite != null) {
            this.mImageSprite.release();
            this.mImageSprite = null;
        }
    }
}
