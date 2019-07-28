package com.tencent.liteav;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.datareport.TXCDRExtInfo;
import com.tencent.liteav.basic.datareport.a;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import com.tencent.liteav.basic.util.b;

/* compiled from: TXCVodPlayCollection */
public class n {
    private final String a = "TXCVodPlayCollection";
    private Context b;
    private String c = null;
    private long d = 0;
    private long e = 0;
    private boolean f = false;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m;
    private String n;
    private boolean o = false;
    private boolean p = false;
    private int q = 0;
    private int r = 0;
    private int s = 0;
    private int t = 0;
    private int u = 0;
    private int v;
    private int w;
    private int x;

    public n(Context context) {
        this.b = context;
        this.n = TXCCommonUtil.getAppVersion();
    }

    public void a(String str) {
        this.c = str;
    }

    public String a() {
        Context context = this.b;
        ApplicationInfo applicationInfo = this.b.getApplicationInfo();
        int i = applicationInfo.labelRes;
        return i == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i);
    }

    private void m() {
        String d = b.d();
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        int i = 0;
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = false;
        tXCDRExtInfo.url = this.c;
        TXCDRApi.InitEvent(this.b, d, a.ad, a.as, tXCDRExtInfo);
        TXCDRApi.txSetEventIntValue(d, a.ad, "u32_timeuse", (long) this.h);
        TXCDRApi.txSetEventValue(d, a.ad, "str_stream_url", this.c);
        TXCDRApi.txSetEventIntValue(d, a.ad, "u32_videotime", (long) this.g);
        TXCDRApi.txSetEventValue(d, a.ad, "str_device_type", b.c());
        TXCDRApi.txSetEventIntValue(d, a.ad, "u32_network_type", (long) b.c(this.b));
        TXCDRApi.txSetEventValue(d, a.ad, "str_user_id", b.a(this.b));
        TXCDRApi.txSetEventValue(d, a.ad, "str_package_name", b.b(this.b));
        TXCDRApi.txSetEventValue(d, a.ad, "str_app_version", this.n);
        TXCDRApi.txSetEventValue(d, a.ad, "dev_uuid", b.d(this.b));
        TXCDRApi.txSetEventIntValue(d, a.ad, "u32_first_i_frame", (long) this.i);
        TXCDRApi.txSetEventIntValue(d, a.ad, "u32_isp2p", (long) this.j);
        TXCDRApi.txSetEventIntValue(d, a.ad, "u32_avg_load", this.k == 0 ? 0 : (long) (this.l / this.k));
        TXCDRApi.txSetEventIntValue(d, a.ad, "u32_load_cnt", (long) this.k);
        TXCDRApi.txSetEventIntValue(d, a.ad, "u32_max_load", (long) this.m);
        TXCDRApi.txSetEventIntValue(d, a.ad, "u32_player_type", (long) this.r);
        TXCDRApi.txSetEventValue(d, a.ad, "str_app_name", a());
        if (this.t > 0) {
            TXCDRApi.txSetEventIntValue(d, a.ad, "u32_dns_time", (long) this.t);
        } else {
            TXCDRApi.txSetEventIntValue(d, a.ad, "u32_dns_time", -1);
        }
        if (this.s > 0) {
            TXCDRApi.txSetEventIntValue(d, a.ad, "u32_tcp_did_connect", (long) this.s);
        } else {
            TXCDRApi.txSetEventIntValue(d, a.ad, "u32_tcp_did_connect", -1);
        }
        if (this.u > 0) {
            TXCDRApi.txSetEventIntValue(d, a.ad, "u32_first_video_packet", (long) this.u);
        } else {
            TXCDRApi.txSetEventIntValue(d, a.ad, "u32_first_video_packet", -1);
        }
        TXCDRApi.nativeReportEvent(d, a.ad);
        String str = "TXCVodPlayCollection";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40301: token=");
        stringBuilder.append(d);
        stringBuilder.append(" ");
        stringBuilder.append("u32_timeuse");
        stringBuilder.append("=");
        stringBuilder.append(this.h);
        stringBuilder.append(" ");
        stringBuilder.append("str_stream_url");
        stringBuilder.append("=");
        stringBuilder.append(this.c);
        stringBuilder.append(" ");
        stringBuilder.append("u32_videotime");
        stringBuilder.append("=");
        stringBuilder.append(this.g);
        stringBuilder.append(" ");
        stringBuilder.append("str_device_type");
        stringBuilder.append("=");
        stringBuilder.append(b.c());
        stringBuilder.append(" ");
        stringBuilder.append("u32_network_type");
        stringBuilder.append("=");
        stringBuilder.append(b.c(this.b));
        stringBuilder.append(" ");
        stringBuilder.append("str_user_id");
        stringBuilder.append("=");
        stringBuilder.append(b.a(this.b));
        stringBuilder.append(" ");
        stringBuilder.append("str_package_name");
        stringBuilder.append("=");
        stringBuilder.append(b.b(this.b));
        stringBuilder.append(" ");
        stringBuilder.append("str_app_version");
        stringBuilder.append("=");
        stringBuilder.append(this.n);
        stringBuilder.append(" ");
        stringBuilder.append("dev_uuid");
        stringBuilder.append("=");
        stringBuilder.append(b.d(this.b));
        stringBuilder.append(" ");
        stringBuilder.append("u32_first_i_frame");
        stringBuilder.append("=");
        stringBuilder.append(this.i);
        stringBuilder.append(" ");
        stringBuilder.append("u32_isp2p");
        stringBuilder.append("=");
        stringBuilder.append(this.j);
        stringBuilder.append(" ");
        stringBuilder.append("u32_avg_load");
        stringBuilder.append("=");
        if (this.k != 0) {
            i = this.l / this.k;
        }
        stringBuilder.append(i);
        stringBuilder.append(" ");
        stringBuilder.append("u32_load_cnt");
        stringBuilder.append("=");
        stringBuilder.append(this.k);
        stringBuilder.append(" ");
        stringBuilder.append("u32_max_load");
        stringBuilder.append("=");
        stringBuilder.append(this.m);
        stringBuilder.append(" ");
        stringBuilder.append("u32_player_type");
        stringBuilder.append("=");
        stringBuilder.append(this.r);
        stringBuilder.append(" ");
        stringBuilder.append("u32_dns_time");
        stringBuilder.append("=");
        stringBuilder.append(this.t);
        stringBuilder.append(" ");
        stringBuilder.append("u32_tcp_did_connect");
        stringBuilder.append("=");
        stringBuilder.append(this.s);
        stringBuilder.append(" ");
        stringBuilder.append("u32_first_video_packet");
        stringBuilder.append("=");
        stringBuilder.append(this.u);
        TXCLog.w(str, stringBuilder.toString());
    }

    public void a(int i) {
        this.g = i;
    }

    public void b() {
        this.f = true;
        this.d = System.currentTimeMillis();
    }

    public void c() {
        if (this.f) {
            this.h = (int) ((System.currentTimeMillis() - this.d) / 1000);
            m();
            this.f = false;
        }
        this.o = false;
        this.p = false;
    }

    public void d() {
        if (this.i != 0 && this.p) {
            int currentTimeMillis = (int) (System.currentTimeMillis() - this.e);
            this.l += currentTimeMillis;
            this.k++;
            if (this.m < currentTimeMillis) {
                this.m = currentTimeMillis;
            }
            this.p = false;
        }
        if (this.o) {
            this.o = false;
        }
    }

    public void e() {
        if (this.i == 0) {
            this.i = (int) (System.currentTimeMillis() - this.d);
        }
    }

    public void f() {
        if (this.s == 0) {
            this.s = (int) (System.currentTimeMillis() - this.d);
        }
    }

    public void g() {
        if (this.t == 0) {
            this.t = (int) (System.currentTimeMillis() - this.d);
        }
    }

    public void h() {
        if (this.u == 0) {
            this.u = (int) (System.currentTimeMillis() - this.d);
        }
    }

    public void i() {
        this.e = System.currentTimeMillis();
        this.p = true;
    }

    public void j() {
        this.o = true;
        this.q++;
        TXCDRApi.txReportDAU(this.b, a.bv);
    }

    public void b(int i) {
        this.r = i;
    }

    public void a(boolean z) {
        if (z) {
            this.v = 1;
            TXCDRApi.txReportDAU(this.b, a.bx);
            return;
        }
        this.v = 0;
    }

    public void k() {
        this.x++;
        TXCDRApi.txReportDAU(this.b, a.by);
    }

    public void l() {
        this.w++;
        TXCDRApi.txReportDAU(this.b, a.bw);
    }
}
