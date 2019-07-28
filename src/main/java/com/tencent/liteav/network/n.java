package com.tencent.liteav.network;

import android.content.Context;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.datareport.TXCDRExtInfo;
import com.tencent.liteav.basic.datareport.a;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import com.tencent.liteav.basic.util.b;
import com.yalantis.ucrop.view.CropImageView;
import java.util.regex.Pattern;

/* compiled from: UploadQualityReport */
public class n {
    private Context a;
    private String b;
    private String c;
    private String d;
    private String e;
    private long f;
    private long g;
    private String h;
    private long i;
    private long j;
    private long k;
    private long l;
    private long m;
    private long n;
    private long o;
    private long p;
    private long q;
    private long r;
    private long s;
    private long t;
    private long u;
    private long v;
    private boolean w = true;

    public n(Context context) {
        this.a = context.getApplicationContext();
        this.b = b.a(this.a);
        this.d = "Android";
        m.a().a(this.a);
        a();
    }

    public void a() {
        this.c = "";
        this.f = 0;
        this.g = -1;
        this.h = "";
        this.i = -1;
        this.j = -1;
        this.k = -1;
        this.l = -1;
        this.e = "";
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = true;
    }

    private void e() {
        long j = this.p;
        long j2 = this.q;
        a();
        this.n = j;
        this.o = j2;
    }

    public void b() {
        this.f = System.currentTimeMillis();
        this.c = m.a().b();
    }

    public void c() {
        f();
        e();
    }

    public void a(boolean z) {
        this.l = z ? 2 : 1;
        if (z) {
            this.w = false;
        }
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(boolean z, String str) {
        this.h = str;
        if (z) {
            this.g = 1;
        } else if (str != null) {
            int indexOf = str.indexOf(":");
            int i = 0;
            if (indexOf != -1) {
                str = str.substring(0, indexOf);
            }
            if (str != null) {
                String[] split = str.split("[.]");
                int length = split.length;
                while (i < length) {
                    if (c(split[i])) {
                        i++;
                    } else {
                        this.g = 3;
                        return;
                    }
                }
                this.g = 2;
            }
        }
    }

    public void a(long j, long j2, long j3) {
        this.i = j;
        this.j = j2;
        this.k = j3;
    }

    public void d() {
        this.m++;
    }

    public void a(long j, long j2) {
        this.p = j;
        this.q = j2;
    }

    public void b(long j, long j2) {
        this.v++;
        this.r += j;
        this.s += j2;
        if (j > this.t) {
            this.t = j;
        }
        if (j2 > this.u) {
            this.u = j2;
        }
    }

    private void f() {
        if (this.f != 0 && !b(this.b) && !b(this.e)) {
            long j;
            long j2;
            float f;
            float f2;
            String streamIDByStreamUrl = TXCCommonUtil.getStreamIDByStreamUrl(this.e);
            long currentTimeMillis = System.currentTimeMillis() - this.f;
            long j3 = this.p > this.n ? this.p - this.n : 0;
            long j4 = this.q > this.o ? this.q - this.o : 0;
            if (this.v > 0) {
                j = this.r / this.v;
                j2 = this.s / this.v;
            } else {
                j2 = 0;
                j = 0;
            }
            String txCreateToken = TXCDRApi.txCreateToken();
            TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
            tXCDRExtInfo.report_common = false;
            tXCDRExtInfo.report_status = false;
            tXCDRExtInfo.url = this.e;
            long j5 = j2;
            TXCDRApi.InitEvent(this.a, txCreateToken, a.T, a.al, tXCDRExtInfo);
            TXCDRApi.txSetEventValue(txCreateToken, a.T, "str_user_id", this.b);
            TXCDRApi.txSetEventValue(txCreateToken, a.T, "str_stream_id", streamIDByStreamUrl);
            TXCDRApi.txSetEventValue(txCreateToken, a.T, "str_access_id", this.c);
            TXCDRApi.txSetEventValue(txCreateToken, a.T, "str_platform", this.d);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_server_type", this.g);
            TXCDRApi.txSetEventValue(txCreateToken, a.T, "str_server_addr", this.h);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_dns_timecost", this.i);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_connect_timecost", this.j);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_handshake_timecost", this.k);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_push_type", this.l);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_totaltime", currentTimeMillis);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_block_count", this.m);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_video_drop", j3);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_audio_drop", j4);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_video_que_avg", j);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_audio_que_avg", j5);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_video_que_max", this.t);
            TXCDRApi.txSetEventIntValue(txCreateToken, a.T, "u32_audio_que_max", this.u);
            TXCDRApi.nativeReportEvent(txCreateToken, a.T);
            float f3 = (currentTimeMillis <= 0 || this.m == 0) ? CropImageView.DEFAULT_ASPECT_RATIO : (((float) this.m) * 60000.0f) / ((float) currentTimeMillis);
            if (this.v > 0) {
                float f4;
                if (this.r == 0) {
                    f4 = CropImageView.DEFAULT_ASPECT_RATIO;
                } else {
                    f4 = ((float) this.r) / ((float) this.v);
                }
                if (this.s == 0) {
                    f = f4;
                } else {
                    f = f4;
                    f2 = ((float) this.s) / ((float) this.v);
                    if (!(!this.w || b(this.c) || this.k == -1)) {
                        m.a().a(this.c, this.g, currentTimeMillis, this.k, f3, f, f2);
                    }
                }
            }
            f = CropImageView.DEFAULT_ASPECT_RATIO;
            f2 = CropImageView.DEFAULT_ASPECT_RATIO;
            m.a().a(this.c, this.g, currentTimeMillis, this.k, f3, f, f2);
        }
    }

    private boolean b(String str) {
        return str == null || str.length() == 0;
    }

    private boolean c(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }
}
