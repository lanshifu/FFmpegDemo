package com.tencent.liteav;

import android.content.Context;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.datareport.TXCDRExtInfo;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.module.TXCStatus;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.network.a.b;
import com.tencent.liteav.network.a.e;
import com.tencent.rtmp.TXLiveConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: TXCDataReport */
public class d {
    private static String a = "TXCDataReport";
    private static HashMap<String, a> w = new HashMap();
    private HashMap b = new HashMap(100);
    private String c;
    private int d;
    private int e;
    private int f;
    private int g;
    private Context h;
    private String i;
    private long j;
    private int k;
    private long l;
    private boolean m;
    private long n;
    private int o;
    private boolean p = false;
    private long q = 0;
    private long r = 0;
    private long s = 0;
    private long t = 0;
    private long u = 0;
    private String v;
    private String x = "";

    /* compiled from: TXCDataReport */
    enum a {
        PENDING,
        CONFIRM,
        NEGATIVE
    }

    public d(Context context) {
        this.h = context.getApplicationContext();
        this.i = TXCCommonUtil.getAppVersion();
        this.o = 5000;
        this.u = 0;
    }

    public void a() {
        r();
        this.l = -1;
        this.q = System.currentTimeMillis();
    }

    public void b() {
        if (this.m) {
            p();
            return;
        }
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("push ");
        stringBuilder.append(this.c);
        stringBuilder.append(" failed!");
        TXCLog.e(str, stringBuilder.toString());
        n();
    }

    public void c() {
        if (!this.m) {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("play ");
            stringBuilder.append(this.c);
            stringBuilder.append(" failed");
            TXCLog.e(str, stringBuilder.toString());
            if (this.p) {
                b(false);
            } else {
                j();
            }
        } else if (this.p) {
            s();
        } else {
            k();
        }
        if (this.p) {
            m();
        }
    }

    public void a(boolean z) {
        this.p = z;
    }

    public void a(String str) {
        this.c = str;
        b(str);
    }

    public void b(String str) {
        if (str != null) {
            this.v = str;
        }
    }

    /* Access modifiers changed, original: protected */
    public a d() {
        try {
            Uri parse = Uri.parse(this.v);
            if (parse == null) {
                return a.PENDING;
            }
            final String host = parse.getHost();
            if (TextUtils.isEmpty(host)) {
                return a.PENDING;
            }
            String scheme = parse.getScheme();
            if (scheme == null) {
                return a.PENDING;
            }
            if (!scheme.equals("rtmp") && !scheme.equals("http") && !scheme.equals("https")) {
                return a.PENDING;
            }
            if (c(host)) {
                return a.CONFIRM;
            }
            Set queryParameterNames = parse.getQueryParameterNames();
            if (queryParameterNames != null && (queryParameterNames.contains("bizid") || queryParameterNames.contains("txTime") || queryParameterNames.contains("txSecret"))) {
                return a.CONFIRM;
            }
            if (w.containsKey(host)) {
                return (a) w.get(host);
            }
            w.put(host, a.PENDING);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        boolean z = false;
                        for (e eVar : com.tencent.liteav.network.a.a.a.c().a(new b(host, true), null)) {
                            if (eVar.a() && d.c(eVar.a)) {
                                z = true;
                                break;
                            }
                        }
                        d.w.put(host, z ? a.CONFIRM : a.NEGATIVE);
                        String h = d.a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(host);
                        stringBuilder.append(" isTencent ");
                        stringBuilder.append(z);
                        TXCLog.d(h, stringBuilder.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return a.PENDING;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static boolean c(String str) {
        if (str == null || !str.contains("myqcloud")) {
            return com.tencent.liteav.basic.f.b.a().a(str);
        }
        return true;
    }

    public void d(String str) {
        this.x = str;
    }

    public void a(int i, int i2) {
        this.d = i;
        this.e = i2;
    }

    public void a(int i) {
        this.f = i;
    }

    public void b(int i) {
        this.g = i;
    }

    public void e() {
        if (!(this.m || TextUtils.isEmpty(TXCStatus.c(this.x, 7012)))) {
            o();
            this.m = true;
        }
        if (this.n <= 0) {
            this.n = TXCTimeUtil.getTimeTick();
        }
        if (this.m && TXCTimeUtil.getTimeTick() - this.n > 5000) {
            q();
            this.n = TXCTimeUtil.getTimeTick();
        }
    }

    public void f() {
        if (!this.m) {
            long b = TXCStatus.b(this.x, 6001);
            long b2 = TXCStatus.b(this.x, 7104);
            if (!(b == 0 && b2 == 0)) {
                if (this.p) {
                    b(true);
                } else {
                    i();
                }
                this.o = 5000;
                this.m = true;
            }
            String c = TXCStatus.c(this.x, 7119);
            if (c != null) {
                b(c);
            }
        }
        if (this.n <= 0) {
            this.n = TXCTimeUtil.getTimeTick();
        }
        if (this.m && TXCTimeUtil.getTimeTick() > this.n + ((long) this.o)) {
            if (this.p) {
                t();
                this.o = 5000;
            } else if (d() != a.NEGATIVE) {
                l();
                this.o = TXCDRApi.getStatusReportInterval();
                if (this.o < 5000) {
                    this.o = 5000;
                }
                if (this.o > 300000) {
                    this.o = 300000;
                }
            } else {
                return;
            }
            this.l = TXCStatus.b(this.x, 6004);
            this.n = TXCTimeUtil.getTimeTick();
        }
    }

    private void i() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.url = this.c;
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = false;
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.U, com.tencent.liteav.basic.datareport.a.am, tXCDRExtInfo);
        long utcTimeTick = TXCTimeUtil.getUtcTimeTick();
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u64_timestamp", utcTimeTick);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "str_device_type", (String) this.b.get("str_device_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_network_type", (long) e("u32_network_type"));
        long b = TXCStatus.b(this.x, 7107);
        long b2 = TXCStatus.b(this.x, 7108);
        if (b2 != -1) {
            b2 -= b;
        }
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_dns_time", b2);
        String c = TXCStatus.c(this.x, 7110);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_server_ip", c);
        long b3 = TXCStatus.b(this.x, 7109);
        if (b3 != -1) {
            b3 -= b;
        }
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_connect_server_time", b3);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_stream_begin", -1);
        this.j = TXCStatus.b(this.x, 6001) - b;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_first_i_frame", this.j);
        long b4 = TXCStatus.b(this.x, 7103) - b;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_first_frame_down", b4);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "str_user_id", (String) this.b.get("str_user_id"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "str_package_name", (String) this.b.get("str_package_name"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "str_app_version", this.i);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "dev_uuid", (String) this.b.get("dev_uuid"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_isp2p", (long) this.k);
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.U);
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40101: token=");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append("u64_timestamp");
        stringBuilder.append("=");
        stringBuilder.append(utcTimeTick);
        stringBuilder.append(" ");
        stringBuilder.append("str_device_type");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_device_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_network_type");
        stringBuilder.append("=");
        stringBuilder.append(e("u32_network_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_dns_time");
        stringBuilder.append("=");
        stringBuilder.append(b2);
        stringBuilder.append(" ");
        stringBuilder.append("u32_server_ip");
        stringBuilder.append("=");
        stringBuilder.append(c);
        stringBuilder.append(" ");
        stringBuilder.append("u32_connect_server_time");
        stringBuilder.append("=");
        stringBuilder.append(b3);
        stringBuilder.append(" ");
        stringBuilder.append("u32_stream_begin");
        stringBuilder.append("=-1 ");
        stringBuilder.append("u32_first_i_frame");
        stringBuilder.append("=");
        stringBuilder.append(this.j);
        stringBuilder.append(" ");
        stringBuilder.append("u32_first_frame_down");
        stringBuilder.append("=");
        stringBuilder.append(b4);
        stringBuilder.append(" ");
        stringBuilder.append("str_user_id");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_user_id"));
        stringBuilder.append(" ");
        stringBuilder.append("str_package_name");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_package_name"));
        stringBuilder.append(" ");
        stringBuilder.append("str_app_version");
        stringBuilder.append("=");
        stringBuilder.append(this.i);
        stringBuilder.append(" ");
        stringBuilder.append("dev_uuid");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("dev_uuid"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_isp2p");
        stringBuilder.append("=");
        stringBuilder.append(this.k);
        TXCLog.d(str2, stringBuilder.toString());
    }

    private void j() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.url = this.c;
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = false;
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.U, com.tencent.liteav.basic.datareport.a.am, tXCDRExtInfo);
        long utcTimeTick = TXCTimeUtil.getUtcTimeTick();
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u64_timestamp", utcTimeTick);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "str_device_type", (String) this.b.get("str_device_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_network_type", (long) e("u32_network_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_dns_time", -1);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_server_ip", "");
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_connect_server_time", -1);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_stream_begin", -1);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_first_i_frame", -1);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_first_frame_down", -1);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "str_user_id", (String) this.b.get("str_user_id"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "str_package_name", (String) this.b.get("str_package_name"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "str_app_version", this.i);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.U, "dev_uuid", (String) this.b.get("dev_uuid"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.U, "u32_isp2p", (long) this.k);
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.U);
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40101: token=");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append("u64_timestamp");
        stringBuilder.append("=");
        stringBuilder.append(utcTimeTick);
        stringBuilder.append(" ");
        stringBuilder.append("str_device_type");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_device_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_network_type");
        stringBuilder.append("=");
        stringBuilder.append(e("u32_network_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_dns_time");
        stringBuilder.append("=-1 ");
        stringBuilder.append("u32_server_ip");
        stringBuilder.append("= ");
        stringBuilder.append("u32_connect_server_time");
        stringBuilder.append("=-1 ");
        stringBuilder.append("u32_stream_begin");
        stringBuilder.append("=-1 ");
        stringBuilder.append("u32_first_i_frame");
        stringBuilder.append("=-1 ");
        stringBuilder.append("u32_first_frame_down");
        stringBuilder.append("=-1 ");
        stringBuilder.append("str_user_id");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_user_id"));
        stringBuilder.append(" ");
        stringBuilder.append("str_package_name");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_package_name"));
        stringBuilder.append(" ");
        stringBuilder.append("str_app_version");
        stringBuilder.append("=");
        stringBuilder.append(this.i);
        stringBuilder.append(" ");
        stringBuilder.append("dev_uuid");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("dev_uuid"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_isp2p");
        stringBuilder.append("=");
        stringBuilder.append(this.k);
        TXCLog.d(str2, stringBuilder.toString());
    }

    private void k() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.url = this.c;
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = false;
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.W, com.tencent.liteav.basic.datareport.a.am, tXCDRExtInfo);
        long utcTimeTick = TXCTimeUtil.getUtcTimeTick();
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u64_timestamp", utcTimeTick);
        long timeTick = (TXCTimeUtil.getTimeTick() - TXCStatus.b(this.x, 7107)) / 1000;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_result", timeTick);
        long b = TXCStatus.b(this.x, 6003);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_avg_block_time", b);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.W, "str_app_version", this.i);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_isp2p", (long) this.k);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_avg_load", TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_CONNECT_SUCC));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_load_cnt", TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_RTMP_STREAM_BEGIN));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_max_load", TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_first_i_frame", this.j);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_speed_cnt", TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_PLAY_BEGIN));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_nodata_cnt", TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_PLAY_PROGRESS));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_avg_cache_time", TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_PLAY_LOADING));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.W, "u32_is_real_time", TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER));
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.W);
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40102: token=");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append("str_stream_url");
        stringBuilder.append("=");
        stringBuilder.append(this.c);
        stringBuilder.append(" ");
        stringBuilder.append("u64_timestamp");
        stringBuilder.append("=");
        stringBuilder.append(utcTimeTick);
        stringBuilder.append(" ");
        stringBuilder.append("u32_result");
        stringBuilder.append("=");
        stringBuilder.append(timeTick);
        stringBuilder.append(" ");
        stringBuilder.append("u32_avg_block_time");
        stringBuilder.append("=");
        stringBuilder.append(b);
        stringBuilder.append(" ");
        stringBuilder.append("str_app_version");
        stringBuilder.append("=");
        stringBuilder.append(this.i);
        stringBuilder.append(" ");
        stringBuilder.append("u32_isp2p");
        stringBuilder.append("=");
        stringBuilder.append(this.k);
        stringBuilder.append(" ");
        stringBuilder.append("u32_avg_load");
        stringBuilder.append("=");
        stringBuilder.append(TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_CONNECT_SUCC));
        stringBuilder.append(" ");
        stringBuilder.append("u32_load_cnt");
        stringBuilder.append("=");
        stringBuilder.append(TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_RTMP_STREAM_BEGIN));
        stringBuilder.append(" ");
        stringBuilder.append("u32_max_load");
        stringBuilder.append("=");
        stringBuilder.append(TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME));
        stringBuilder.append(" ");
        stringBuilder.append("u32_first_i_frame");
        stringBuilder.append("=");
        stringBuilder.append(this.j);
        stringBuilder.append(" ");
        stringBuilder.append("u32_speed_cnt");
        stringBuilder.append("=");
        stringBuilder.append(TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_PLAY_BEGIN));
        stringBuilder.append(" ");
        stringBuilder.append("u32_nodata_cnt");
        stringBuilder.append("=");
        stringBuilder.append(TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_PLAY_PROGRESS));
        stringBuilder.append(" ");
        stringBuilder.append("u32_avg_cache_time");
        stringBuilder.append("=");
        stringBuilder.append(TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_PLAY_LOADING));
        stringBuilder.append(" ");
        stringBuilder.append("u32_is_real_time");
        stringBuilder.append("=");
        stringBuilder.append(TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER));
        TXCLog.d(str2, stringBuilder.toString());
    }

    private void l() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.url = this.c;
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = true;
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.V, com.tencent.liteav.basic.datareport.a.am, tXCDRExtInfo);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_avg_net_speed", (long) (TXCStatus.d(this.x, 7102) + TXCStatus.d(this.x, 7101)));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_fps", (long) ((int) TXCStatus.e(this.x, 6002)));
        long b = TXCStatus.b(this.x, 6004);
        if (this.l == -1) {
            TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_avg_block_count", 0);
        } else if (b >= this.l) {
            TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_avg_block_count", b - this.l);
        } else {
            TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_avg_block_count", 0);
        }
        this.l = b;
        int[] a = com.tencent.liteav.basic.util.b.a();
        int b2 = com.tencent.liteav.basic.util.b.b();
        long b3 = TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_PLAY_END);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_avg_cache_count", b3);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_cpu_usage", (long) a[1]);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_app_cpu_usage", (long) a[0]);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_app_mem_usage", (long) b2);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.V, "str_app_version", this.i);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.V, "u32_isp2p", (long) this.k);
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.V);
        if (this.p) {
            this.t++;
            this.s += b3;
            if (b3 > this.r) {
                this.r = b3;
            }
        }
    }

    private void m() {
        HashMap hashMap = new HashMap();
        String c = TXCStatus.c(this.x, 7113);
        String c2 = TXCStatus.c(this.x, 7114);
        String c3 = TXCStatus.c(this.x, 7115);
        int d = TXCStatus.d(this.x, 7105);
        String c4 = TXCStatus.c(this.x, 7106);
        int d2 = TXCStatus.d(this.x, 7111);
        hashMap.put("stream_url", c);
        hashMap.put("stream_id", c2);
        hashMap.put("bizid", c3);
        hashMap.put("err_code", String.valueOf(d));
        hashMap.put("err_info", c4);
        hashMap.put("channel_type", String.valueOf(d2));
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - this.q;
        hashMap.put("start_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(this.q)));
        hashMap.put("end_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(currentTimeMillis)));
        hashMap.put("total_time", String.valueOf(j));
        currentTimeMillis = TXCStatus.b(this.x, 6003);
        j = TXCStatus.b(this.x, 6006);
        long b = TXCStatus.b(this.x, 6005);
        j = currentTimeMillis != 0 ? j / currentTimeMillis : 0;
        hashMap.put("block_count", String.valueOf(currentTimeMillis));
        hashMap.put("block_duration_max", String.valueOf(b));
        hashMap.put("block_duration_avg", String.valueOf(j));
        currentTimeMillis = this.t != 0 ? this.s / this.t : 0;
        hashMap.put("jitter_cache_max", String.valueOf(this.r));
        hashMap.put("jitter_cache_avg", String.valueOf(currentTimeMillis));
        c = TXCDRApi.txCreateToken();
        int i = com.tencent.liteav.basic.datareport.a.af;
        int i2 = com.tencent.liteav.basic.datareport.a.al;
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.command_id_comment = "LINKMIC";
        TXCDRApi.InitEvent(this.h, c, i, i2, tXCDRExtInfo);
        c3 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40402: token=");
        stringBuilder.append(c);
        TXCLog.d(c3, stringBuilder.toString());
        for (Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            c3 = (String) entry.getValue();
            c4 = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("RealTimePlayStatisticInfo: ");
            stringBuilder2.append(str);
            stringBuilder2.append(" = ");
            stringBuilder2.append(c3);
            TXCLog.e(c4, stringBuilder2.toString());
            if (!(str == null || str.length() <= 0 || c3 == null)) {
                TXCDRApi.txSetEventValue(c, i, str, c3);
            }
        }
        TXCDRApi.nativeReportEvent(c, i);
        this.p = false;
        this.q = 0;
        this.t = 0;
        this.s = 0;
        this.r = 0;
    }

    private void n() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = false;
        tXCDRExtInfo.url = this.c;
        long b = TXCStatus.b(this.x, 7013);
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.P, com.tencent.liteav.basic.datareport.a.al, tXCDRExtInfo);
        long utcTimeTick = TXCTimeUtil.getUtcTimeTick();
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u64_timestamp", TXCTimeUtil.getUtcTimeTick());
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "str_device_type", (String) this.b.get("str_device_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_network_type", (long) e("u32_network_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_dns_time", -1);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_connect_server_time", -1);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_server_ip", "");
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_video_resolution", (long) ((this.d << 16) | this.e));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_audio_samplerate", (long) this.g);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_video_bitrate", (long) this.f);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "str_user_id", (String) this.b.get("str_user_id"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "str_package_name", (String) this.b.get("str_package_name"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_channel_type", b);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "str_app_version", this.i);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "dev_uuid", (String) this.b.get("dev_uuid"));
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.P);
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40001: token=");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append("str_stream_url");
        stringBuilder.append("=");
        stringBuilder.append(this.c);
        stringBuilder.append(" ");
        stringBuilder.append("u64_timestamp");
        stringBuilder.append("=");
        stringBuilder.append(utcTimeTick);
        stringBuilder.append(" ");
        stringBuilder.append("str_device_type");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("str_device_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_network_type");
        stringBuilder.append("=");
        stringBuilder.append(e("u32_network_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_dns_time");
        stringBuilder.append("=-1 ");
        stringBuilder.append("u32_connect_server_time");
        stringBuilder.append("=-1 ");
        stringBuilder.append("u32_server_ip");
        stringBuilder.append("= ");
        stringBuilder.append("u32_video_resolution");
        stringBuilder.append("=");
        stringBuilder.append((this.d << 16) | this.e);
        stringBuilder.append(" ");
        stringBuilder.append("u32_audio_samplerate");
        stringBuilder.append("=");
        stringBuilder.append(this.g);
        stringBuilder.append(" ");
        stringBuilder.append("u32_video_bitrate");
        stringBuilder.append("=");
        stringBuilder.append(this.f);
        stringBuilder.append(" ");
        stringBuilder.append("str_user_id");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("str_user_id"));
        stringBuilder.append(" ");
        stringBuilder.append("str_package_name");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("str_package_name"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_channel_type");
        stringBuilder.append("=");
        stringBuilder.append(b);
        stringBuilder.append(" ");
        stringBuilder.append("str_app_version");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("dev_uuid"));
        stringBuilder.append(" ");
        stringBuilder.append("dev_uuid");
        stringBuilder.append("=");
        stringBuilder.append(e("u32_max_load"));
        TXCLog.d(str2, stringBuilder.toString());
    }

    private void o() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = false;
        tXCDRExtInfo.url = this.c;
        String c = TXCStatus.c(this.x, 7012);
        long b = TXCStatus.b(this.x, 7009);
        long b2 = TXCStatus.b(this.x, 7010);
        if (b2 != -1) {
            b2 -= b;
        }
        long b3 = TXCStatus.b(this.x, 7011);
        if (b3 != -1) {
            b3 -= b;
        }
        b = TXCStatus.b(this.x, 7013);
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.P, com.tencent.liteav.basic.datareport.a.al, tXCDRExtInfo);
        long utcTimeTick = TXCTimeUtil.getUtcTimeTick();
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u64_timestamp", utcTimeTick);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "str_device_type", (String) this.b.get("str_device_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_network_type", (long) e("u32_network_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_dns_time", b2);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_connect_server_time", b3);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_server_ip", c);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_video_resolution", (long) ((this.d << 16) | this.e));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_audio_samplerate", (long) this.g);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_video_bitrate", (long) this.f);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "str_user_id", (String) this.b.get("str_user_id"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "str_package_name", (String) this.b.get("str_package_name"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.P, "u32_channel_type", b);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "str_app_version", this.i);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "dev_uuid", (String) this.b.get("dev_uuid"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.P, "str_nearest_ip_list", TXCStatus.c(this.x, 7019));
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.P);
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40001: token=");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append("str_stream_url");
        stringBuilder.append("=");
        stringBuilder.append(this.c);
        stringBuilder.append(" ");
        stringBuilder.append("u64_timestamp");
        stringBuilder.append("=");
        stringBuilder.append(utcTimeTick);
        stringBuilder.append(" ");
        stringBuilder.append("str_device_type");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("str_device_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_network_type");
        stringBuilder.append("=");
        stringBuilder.append(e("u32_network_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_dns_time");
        stringBuilder.append("=");
        stringBuilder.append(b2);
        stringBuilder.append(" ");
        stringBuilder.append("u32_connect_server_time");
        stringBuilder.append("=");
        stringBuilder.append(b3);
        stringBuilder.append(" ");
        stringBuilder.append("u32_server_ip");
        stringBuilder.append("=");
        stringBuilder.append(c);
        stringBuilder.append(" ");
        stringBuilder.append("u32_video_resolution");
        stringBuilder.append("=");
        stringBuilder.append((this.d << 16) | this.e);
        stringBuilder.append(" ");
        stringBuilder.append("u32_audio_samplerate");
        stringBuilder.append("=");
        stringBuilder.append(this.g);
        stringBuilder.append(" ");
        stringBuilder.append("u32_video_bitrate");
        stringBuilder.append("=");
        stringBuilder.append(this.f);
        stringBuilder.append(" ");
        stringBuilder.append("str_user_id");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("str_user_id"));
        stringBuilder.append(" ");
        stringBuilder.append("str_package_name");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("str_package_name"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_channel_type");
        stringBuilder.append("=");
        stringBuilder.append(b);
        stringBuilder.append(" ");
        stringBuilder.append("str_app_version");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("dev_uuid"));
        stringBuilder.append(" ");
        stringBuilder.append("dev_uuid");
        stringBuilder.append("=");
        stringBuilder.append(e("u32_max_load"));
        TXCLog.d(str2, stringBuilder.toString());
    }

    private void p() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = false;
        tXCDRExtInfo.url = this.c;
        long b = TXCStatus.b(this.x, 7009);
        long b2 = TXCStatus.b(this.x, 7013);
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.R, com.tencent.liteav.basic.datareport.a.al, tXCDRExtInfo);
        long utcTimeTick = TXCTimeUtil.getUtcTimeTick();
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.R, "u64_timestamp", utcTimeTick);
        long timeTick = (TXCTimeUtil.getTimeTick() - b) / 1000;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.R, "u32_result", timeTick);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.R, "str_user_id", (String) this.b.get("str_user_id"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.R, "str_package_name", (String) this.b.get("str_package_name"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.R, "u32_channel_type", b2);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.R, "str_app_version", this.i);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.R, "dev_uuid", (String) this.b.get("dev_uuid"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.R, "u32_ip_count_quic", String.valueOf(TXCStatus.d(this.x, 7016)));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.R, "u32_connect_count_quic", String.valueOf(TXCStatus.d(this.x, 7017)));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.R, "u32_connect_count_tcp", String.valueOf(TXCStatus.d(this.x, 7018)));
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.R);
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40002: token=");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append("str_stream_url");
        stringBuilder.append("=");
        stringBuilder.append(this.c);
        stringBuilder.append(" ");
        stringBuilder.append("u64_timestamp");
        stringBuilder.append("=");
        stringBuilder.append(utcTimeTick);
        stringBuilder.append(" ");
        stringBuilder.append("u32_result");
        stringBuilder.append("=");
        stringBuilder.append(timeTick);
        stringBuilder.append(" ");
        stringBuilder.append("str_user_id");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("str_user_id"));
        stringBuilder.append(" ");
        stringBuilder.append("str_package_name");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("str_package_name"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_channel_type");
        stringBuilder.append("=");
        stringBuilder.append(b2);
        stringBuilder.append(" ");
        stringBuilder.append("str_app_version");
        stringBuilder.append("=");
        stringBuilder.append(this.i);
        stringBuilder.append(" ");
        stringBuilder.append("dev_uuid");
        stringBuilder.append("=");
        stringBuilder.append(this.b.get("dev_uuid"));
        TXCLog.d(str2, stringBuilder.toString());
    }

    private void q() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = true;
        tXCDRExtInfo.url = this.c;
        int[] a = com.tencent.liteav.basic.util.b.a();
        int i = a[0];
        int i2 = a[1];
        int b = com.tencent.liteav.basic.util.b.b();
        long b2 = TXCStatus.b(this.x, 7013);
        int d = TXCStatus.d(this.x, 7004);
        int d2 = TXCStatus.d(this.x, 7003);
        double e = TXCStatus.e(this.x, 4001);
        int d3 = TXCStatus.d(this.x, 7005);
        int d4 = TXCStatus.d(this.x, 7002);
        int d5 = TXCStatus.d(this.x, 7001);
        int d6 = TXCStatus.d(this.x, 4004);
        String c = TXCStatus.c(this.x, 7012);
        String c2 = TXCStatus.c(this.x, 7014);
        String c3 = TXCStatus.c(this.x, 7015);
        String c4 = TXCStatus.c(this.x, 3001);
        long j = b2;
        long b3 = TXCStatus.b(this.x, 3002);
        double e2 = TXCStatus.e(this.x, 3003);
        double d7 = e2;
        String str = (String) this.b.get("token");
        int d8 = TXCStatus.d(this.x, 7020);
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.Q, com.tencent.liteav.basic.datareport.a.al, tXCDRExtInfo);
        int i3 = i;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_avg_audio_bitrate", (long) d4);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_avg_video_bitrate", (long) d5);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_avg_net_speed", (long) (d2 + d));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_fps", (long) ((int) e));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_avg_cache_size", (long) d3);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_cpu_usage", (long) i2);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_app_cpu_usage", (long) i3);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_app_mem_usage", (long) b);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_channel_type", j);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Q, "str_app_version", this.i);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Q, "str_device_type", (String) this.b.get("str_device_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_hw_enc", (long) d6);
        try {
            if (this.h != null && com.tencent.liteav.basic.util.b.c(this.h) == 1) {
                WifiManager wifiManager = (WifiManager) this.h.getSystemService("wifi");
                if (wifiManager != null) {
                    WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                    if (connectionInfo != null) {
                        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Q, "str_wifi_ssid", connectionInfo.getSSID());
                        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "str_wifi_signal_strength", (long) WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 32));
                        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "str_wifi_link_speed", (long) connectionInfo.getLinkSpeed());
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Q, "str_server_ip", c);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Q, "str_quic_connection_id", c2);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Q, "str_quic_connection_stats", c3);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Q, "str_beauty_stats", c4);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_send_strategy", (long) d8);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_preprocess_timecost", b3);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Q, "u32_preprocess_fps_out", (long) ((int) d7));
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.Q);
    }

    private int e(String str) {
        Number number = (Number) this.b.get(str);
        return number != null ? number.intValue() : 0;
    }

    private void r() {
        this.m = false;
        this.n = 0;
        this.b.put("str_user_id", com.tencent.liteav.basic.util.b.a(this.h));
        this.b.put("str_device_type", com.tencent.liteav.basic.util.b.c());
        this.b.put("str_device_type", com.tencent.liteav.basic.util.b.c());
        this.b.put("u32_network_type", Integer.valueOf(com.tencent.liteav.basic.util.b.c(this.h)));
        this.b.put("token", com.tencent.liteav.basic.util.b.d());
        this.b.put("str_package_name", com.tencent.liteav.basic.util.b.b(this.h));
        this.b.put("dev_uuid", com.tencent.liteav.basic.util.b.d(this.h));
    }

    private void b(boolean z) {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.url = this.c;
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = false;
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.X, com.tencent.liteav.basic.datareport.a.am, tXCDRExtInfo);
        this.u = TXCTimeUtil.getUtcTimeTick();
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "u64_timestamp", String.valueOf(this.u));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "str_device_type", (String) this.b.get("str_device_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_network_type", (long) e("u32_network_type"));
        long b = TXCStatus.b(this.x, 7107);
        long b2 = TXCStatus.b(this.x, 7108);
        if (b2 != -1) {
            b2 -= b;
        }
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_dns_time", z ? b2 : -1);
        String c = TXCStatus.c(this.x, 7110);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_server_ip", z ? c : "");
        long b3 = TXCStatus.b(this.x, 7109);
        if (b3 != -1) {
            b3 -= b;
        }
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_connect_server_time", z ? b3 : -1);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_stream_begin", -1);
        this.j = TXCStatus.b(this.x, 6001) - b;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_first_i_frame", this.j);
        long b4 = TXCStatus.b(this.x, 7103) - b;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_first_frame_down", b4);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "str_user_id", (String) this.b.get("str_user_id"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "str_package_name", (String) this.b.get("str_package_name"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "str_app_version", this.i);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "dev_uuid", (String) this.b.get("dev_uuid"));
        int d = TXCStatus.d(this.x, TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_max_cache_time", String.valueOf(d));
        int d2 = TXCStatus.d(this.x, TXLiveConstants.PLAY_EVT_GET_MESSAGE);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_min_cache_time", String.valueOf(d2));
        int d3 = TXCStatus.d(this.x, 7105);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "u64_err_code", String.valueOf(d3));
        String c2 = TXCStatus.c(this.x, 7106);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "str_err_info", c2);
        int d4 = TXCStatus.d(this.x, 7112);
        String str2 = c2;
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_link_type", String.valueOf(d4));
        int d5 = TXCStatus.d(this.x, 7111);
        int i = d4;
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.X, "u32_channel_type", String.valueOf(d5));
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.X);
        String str3 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40501: token=");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append("u64_timestamp");
        stringBuilder.append("=");
        int i2 = d5;
        String str4 = str3;
        stringBuilder.append(this.u);
        stringBuilder.append(" ");
        stringBuilder.append("str_device_type");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_device_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_network_type");
        stringBuilder.append("=");
        stringBuilder.append(e("u32_network_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_dns_time");
        stringBuilder.append("=");
        stringBuilder.append(b2);
        stringBuilder.append(" ");
        stringBuilder.append("u32_server_ip");
        stringBuilder.append("=");
        stringBuilder.append(c);
        stringBuilder.append(" ");
        stringBuilder.append("u32_connect_server_time");
        stringBuilder.append("=");
        stringBuilder.append(b3);
        stringBuilder.append(" ");
        stringBuilder.append("u32_stream_begin");
        stringBuilder.append("=-1 ");
        stringBuilder.append("u32_first_i_frame");
        stringBuilder.append("=");
        stringBuilder.append(this.j);
        stringBuilder.append(" ");
        stringBuilder.append("u32_first_frame_down");
        stringBuilder.append("=");
        stringBuilder.append(b4);
        stringBuilder.append(" ");
        stringBuilder.append("str_user_id");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_user_id"));
        stringBuilder.append(" ");
        stringBuilder.append("str_package_name");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_package_name"));
        stringBuilder.append(" ");
        stringBuilder.append("str_app_version");
        stringBuilder.append("=");
        stringBuilder.append(this.i);
        stringBuilder.append(" ");
        stringBuilder.append("dev_uuid");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("dev_uuid"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_max_cache_time");
        stringBuilder.append("=");
        stringBuilder.append(d);
        stringBuilder.append(" ");
        stringBuilder.append("u32_min_cache_time");
        stringBuilder.append("=");
        stringBuilder.append(d2);
        stringBuilder.append(" ");
        stringBuilder.append("u64_err_code");
        stringBuilder.append("=");
        stringBuilder.append(d3);
        stringBuilder.append(" ");
        stringBuilder.append("str_err_info");
        stringBuilder.append("=");
        stringBuilder.append(str2);
        stringBuilder.append(" ");
        stringBuilder.append("u32_link_type");
        stringBuilder.append("=");
        stringBuilder.append(i);
        stringBuilder.append(" ");
        stringBuilder.append("u32_channel_type");
        stringBuilder.append("=");
        stringBuilder.append(i2);
        TXCLog.d(str4, stringBuilder.toString());
    }

    private void s() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.url = this.c;
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = false;
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.Z, com.tencent.liteav.basic.datareport.a.am, tXCDRExtInfo);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u64_begin_timestamp", String.valueOf(this.u));
        long utcTimeTick = TXCTimeUtil.getUtcTimeTick();
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Z, "u64_end_timestamp", utcTimeTick);
        long j = (utcTimeTick - this.u) / 1000;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Z, "u64_playtime", j);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "str_device_type", (String) this.b.get("str_device_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_network_type", (long) e("u32_network_type"));
        String c = TXCStatus.c(this.x, 7110);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_server_ip", c);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "str_user_id", (String) this.b.get("str_user_id"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "str_package_name", (String) this.b.get("str_package_name"));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "str_app_version", this.i);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "dev_uuid", (String) this.b.get("dev_uuid"));
        long b = TXCStatus.b(this.x, 6003);
        long b2 = TXCStatus.b(this.x, 6005);
        long b3 = TXCStatus.b(this.x, 6006);
        long j2 = 0;
        if (b > 0) {
            j2 = b3 / b;
        }
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Z, "u64_block_count", b);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Z, "u64_block_duration_max", b2);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Z, "u64_block_duration_avg", j2);
        b3 = TXCStatus.b(this.x, 6009);
        long j3 = j2;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Z, "u64_jitter_cache_max", b3);
        j2 = TXCStatus.b(this.x, 6008);
        long j4 = b3;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Z, "u64_jitter_cache_avg", j2);
        b3 = TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_PLAY_LOADING);
        long j5 = j2;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Z, "u64_audio_cache_avg", b3);
        int d = TXCStatus.d(this.x, 7112);
        long j6 = b3;
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_link_type", String.valueOf(d));
        b3 = (long) TXCStatus.d(this.x, TXLiveConstants.PLAY_EVT_CONNECT_SUCC);
        int i = d;
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_avg_load", String.valueOf(b3));
        j2 = (long) TXCStatus.d(this.x, TXLiveConstants.PLAY_EVT_RTMP_STREAM_BEGIN);
        long j7 = b3;
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_load_cnt", String.valueOf(j2));
        b3 = (long) TXCStatus.d(this.x, TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME);
        long j8 = j2;
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_max_load", String.valueOf(b3));
        d = TXCStatus.d(this.x, 7111);
        long j9 = b3;
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_channel_type", String.valueOf(d));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_ip_count_quic", String.valueOf(TXCStatus.d(this.x, 7116)));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_connect_count_quic", String.valueOf(TXCStatus.d(this.x, 7117)));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Z, "u32_connect_count_tcp", String.valueOf(TXCStatus.d(this.x, 7118)));
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.Z);
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("report evt 40502: token=");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append("str_stream_url");
        stringBuilder.append("=");
        stringBuilder.append(this.c);
        stringBuilder.append(" ");
        stringBuilder.append("u64_begin_timestamp");
        stringBuilder.append("=");
        stringBuilder.append(this.u);
        stringBuilder.append(" ");
        stringBuilder.append("u64_end_timestamp");
        stringBuilder.append("=");
        stringBuilder.append(utcTimeTick);
        stringBuilder.append(" ");
        stringBuilder.append("u64_playtime");
        stringBuilder.append("=");
        stringBuilder.append(j);
        stringBuilder.append(" ");
        stringBuilder.append("str_device_type");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_device_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_network_type");
        stringBuilder.append("=");
        stringBuilder.append(e("u32_network_type"));
        stringBuilder.append(" ");
        stringBuilder.append("u32_server_ip");
        stringBuilder.append("=");
        stringBuilder.append(c);
        stringBuilder.append(" ");
        stringBuilder.append("str_user_id");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_user_id"));
        stringBuilder.append(" ");
        stringBuilder.append("str_package_name");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("str_package_name"));
        stringBuilder.append(" ");
        stringBuilder.append("str_app_version");
        stringBuilder.append("=");
        stringBuilder.append(this.i);
        stringBuilder.append(" ");
        stringBuilder.append("dev_uuid");
        stringBuilder.append("=");
        stringBuilder.append((String) this.b.get("dev_uuid"));
        stringBuilder.append(" ");
        stringBuilder.append("u64_block_count");
        stringBuilder.append("=");
        stringBuilder.append(b);
        stringBuilder.append(" ");
        stringBuilder.append("u64_block_duration_max");
        stringBuilder.append("=");
        stringBuilder.append(b2);
        stringBuilder.append(" ");
        stringBuilder.append("u64_block_duration_avg");
        stringBuilder.append("=");
        stringBuilder.append(j3);
        stringBuilder.append(" ");
        stringBuilder.append("u64_jitter_cache_max");
        stringBuilder.append("=");
        stringBuilder.append(j4);
        stringBuilder.append(" ");
        stringBuilder.append("u64_jitter_cache_avg");
        stringBuilder.append("=");
        stringBuilder.append(j5);
        stringBuilder.append(" ");
        stringBuilder.append("u64_audio_cache_avg");
        stringBuilder.append("=");
        stringBuilder.append(j6);
        stringBuilder.append(" ");
        stringBuilder.append("u32_link_type");
        stringBuilder.append("=");
        stringBuilder.append(i);
        stringBuilder.append(" ");
        stringBuilder.append("u32_avg_load");
        stringBuilder.append("=");
        stringBuilder.append(j7);
        stringBuilder.append(" ");
        stringBuilder.append("u32_load_cnt");
        stringBuilder.append("=");
        stringBuilder.append(j8);
        stringBuilder.append(" ");
        stringBuilder.append("u32_max_load");
        stringBuilder.append("=");
        stringBuilder.append(j9);
        stringBuilder.append(" ");
        stringBuilder.append("u32_channel_type");
        stringBuilder.append("=");
        stringBuilder.append(d);
        TXCLog.d(str2, stringBuilder.toString());
    }

    private void t() {
        TXCDRExtInfo tXCDRExtInfo = new TXCDRExtInfo();
        tXCDRExtInfo.url = this.c;
        tXCDRExtInfo.report_common = false;
        tXCDRExtInfo.report_status = true;
        String str = (String) this.b.get("token");
        TXCDRApi.InitEvent(this.h, str, com.tencent.liteav.basic.datareport.a.Y, com.tencent.liteav.basic.datareport.a.am, tXCDRExtInfo);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_net_speed", (long) (TXCStatus.d(this.x, 7102) + TXCStatus.d(this.x, 7101)));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_fps", (long) ((int) TXCStatus.e(this.x, 6002)));
        long b = TXCStatus.b(this.x, 6004);
        if (this.l == -1) {
            TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_video_block_count", 0);
        } else if (b >= this.l) {
            TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_video_block_count", b - this.l);
        } else {
            TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_video_block_count", 0);
        }
        this.l = b;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_video_cache_count", TXCStatus.b(this.x, TXLiveConstants.PLAY_EVT_PLAY_END));
        int[] a = com.tencent.liteav.basic.util.b.a();
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_cpu_usage", (long) a[1]);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_app_cpu_usage", (long) a[0]);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_app_mem_usage", (long) com.tencent.liteav.basic.util.b.b());
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Y, "str_app_version", this.i);
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Y, "str_device_type", (String) this.b.get("str_device_type"));
        int i = 2;
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_video_decode_type", (long) (TXCStatus.d(this.x, 5002) == 0 ? 2 : 1));
        if (TXCStatus.d(this.x, TXLiveConstants.PLAY_EVT_STREAM_SWITCH_SUCC) != 0) {
            i = 1;
        }
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_audio_decode_type", (long) i);
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_network_type", (long) e("u32_network_type"));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_video_cache_time", (long) TXCStatus.d(this.x, 6007));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_audio_cache_time", (long) TXCStatus.d(this.x, TXLiveConstants.PLAY_EVT_GET_PLAYINFO_SUCC));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_audio_jitter", (long) TXCStatus.d(this.x, TXLiveConstants.PLAY_EVT_CHANGE_ROTATION));
        TXCDRApi.txSetEventIntValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_audio_drop", (long) TXCStatus.d(this.x, TXLiveConstants.PLAY_EVT_VOD_LOADING_END));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Y, "u64_playtime", String.valueOf((TXCTimeUtil.getUtcTimeTick() - this.u) / 1000));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_link_type", String.valueOf(TXCStatus.d(this.x, 7112)));
        TXCDRApi.txSetEventValue(str, com.tencent.liteav.basic.datareport.a.Y, "u32_channel_type", String.valueOf(TXCStatus.d(this.x, 7111)));
        TXCDRApi.nativeReportEvent(str, com.tencent.liteav.basic.datareport.a.Y);
    }
}
