package com.tencent.ugc;

import android.content.Context;
import com.tencent.liteav.basic.c.e;
import com.tencent.liteav.basic.c.f;

public class TXUGCBase {
    private static TXUGCBase sInstance;
    private e mUGCLicenseNewCheck;

    public static TXUGCBase getInstance() {
        if (sInstance == null) {
            synchronized (TXUGCBase.class) {
                if (sInstance == null) {
                    sInstance = new TXUGCBase();
                }
            }
        }
        return sInstance;
    }

    private TXUGCBase() {
    }

    public void setLicence(Context context, String str, String str2) {
        this.mUGCLicenseNewCheck = e.a();
        this.mUGCLicenseNewCheck.a(context, str, str2);
    }

    public String getLicenceInfo(Context context) {
        f fVar = new f();
        e.a().a(fVar, context);
        return fVar.a;
    }
}
