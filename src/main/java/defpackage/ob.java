package defpackage;

import com.one.tomato.utils.q;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

/* compiled from: RequestHeaderInterceptor */
/* renamed from: ob */
public class ob implements Interceptor {
    private Map<String, String> a = new HashMap();

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Builder newBuilder = request.newBuilder();
        a(request);
        if (this.a != null && this.a.size() > 0) {
            for (String str : this.a.keySet()) {
                newBuilder.addHeader(str, b((String) this.a.get(str)));
            }
        }
        return chain.proceed(newBuilder.build());
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00e3  */
    private void a(okhttp3.Request r8) {
        /*
        r7 = this;
        r0 = r7.a;
        r1 = "memberId";
        r2 = com.one.tomato.utils.g.d();
        r2 = java.lang.String.valueOf(r2);
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "token";
        r2 = com.one.tomato.utils.g.c();
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "versionNo";
        r2 = com.one.tomato.utils.c.i();
        r0.put(r1, r2);
        r0 = "1";
        r1 = r7.a;
        r2 = "appId";
        r1.put(r2, r0);
        r0 = r7.a;
        r1 = "channelId";
        r2 = com.one.tomato.utils.u.a();
        r3 = "channel";
        r4 = "001";
        r2 = r2.b(r3, r4);
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "macAddress";
        r2 = com.one.tomato.utils.i.b();
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "deviceNo";
        r2 = com.one.tomato.utils.i.a();
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "brand";
        r2 = com.one.tomato.utils.i.g();
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "model";
        r2 = com.one.tomato.utils.i.f();
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "osVersion";
        r2 = com.one.tomato.utils.i.h();
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "phoneResolution";
        r2 = com.one.tomato.utils.j.e();
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "netStatus";
        r2 = com.one.tomato.utils.s.c();
        r0.put(r1, r2);
        r0 = r7.a;
        r1 = "equiName";
        r2 = com.one.tomato.utils.i.i();
        r0.put(r1, r2);
        r0 = com.one.tomato.utils.u.a();
        r1 = "language_setting";
        r0 = r0.d(r1);
        r1 = android.text.TextUtils.isEmpty(r0);
        r2 = 0;
        if (r1 != 0) goto L_0x00f2;
    L_0x00aa:
        r1 = "";
        r3 = -1;
        r4 = r0.hashCode();
        r5 = 3241; // 0xca9 float:4.542E-42 double:1.6013E-320;
        if (r4 == r5) goto L_0x00d4;
    L_0x00b5:
        r5 = 115861276; // 0x6e7e71c float:8.7232127E-35 double:5.7243076E-316;
        if (r4 == r5) goto L_0x00ca;
    L_0x00ba:
        r5 = 115861812; // 0x6e7e934 float:8.7235204E-35 double:5.7243341E-316;
        if (r4 == r5) goto L_0x00c0;
    L_0x00bf:
        goto L_0x00de;
    L_0x00c0:
        r4 = "zh_TW";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x00de;
    L_0x00c8:
        r0 = 0;
        goto L_0x00df;
    L_0x00ca:
        r4 = "zh_CN";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x00de;
    L_0x00d2:
        r0 = 1;
        goto L_0x00df;
    L_0x00d4:
        r4 = "en";
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x00de;
    L_0x00dc:
        r0 = 2;
        goto L_0x00df;
    L_0x00de:
        r0 = -1;
    L_0x00df:
        switch(r0) {
            case 0: goto L_0x00e9;
            case 1: goto L_0x00e6;
            case 2: goto L_0x00e3;
            default: goto L_0x00e2;
        };
    L_0x00e2:
        goto L_0x00eb;
    L_0x00e3:
        r1 = "en_US";
        goto L_0x00eb;
    L_0x00e6:
        r1 = "zh_CN";
        goto L_0x00eb;
    L_0x00e9:
        r1 = "zh_TW";
    L_0x00eb:
        r0 = r7.a;
        r3 = "locale";
        r0.put(r3, r1);
    L_0x00f2:
        r0 = r8.url();
        r0 = r0.pathSegments();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = r0.size();
        r4 = 0;
    L_0x0104:
        if (r4 >= r3) goto L_0x0117;
    L_0x0106:
        r5 = 47;
        r1.append(r5);
        r5 = r0.get(r4);
        r5 = (java.lang.String) r5;
        r1.append(r5);
        r4 = r4 + 1;
        goto L_0x0104;
    L_0x0117:
        r0 = r1.toString();
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        r3 = r8.body();
        if (r3 == 0) goto L_0x0163;
    L_0x0126:
        r3 = r3 instanceof okhttp3.FormBody;
        if (r3 == 0) goto L_0x0163;
    L_0x012a:
        r8 = r8.body();
        r8 = (okhttp3.FormBody) r8;
        r3 = r8.size();
        if (r3 <= 0) goto L_0x0163;
    L_0x0136:
        if (r2 >= r3) goto L_0x0163;
    L_0x0138:
        r4 = r8.encodedName(r2);
        r5 = r8.encodedValue(r2);
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r6.append(r4);
        r4 = "=";
        r6.append(r4);
        r6.append(r5);
        r4 = r6.toString();
        r1.append(r4);
        r4 = r3 + -1;
        if (r2 == r4) goto L_0x0160;
    L_0x015b:
        r4 = "&";
        r1.append(r4);
    L_0x0160:
        r2 = r2 + 1;
        goto L_0x0136;
    L_0x0163:
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r1 = r1.toString();
        r8.append(r1);
        r1 = "&path=";
        r8.append(r1);
        r8.append(r0);
        r8 = r8.toString();
        r8 = r7.a(r8);
        r0 = android.text.TextUtils.isEmpty(r8);
        if (r0 != 0) goto L_0x018c;
    L_0x0185:
        r0 = r7.a;
        r1 = "gCode";
        r0.put(r1, r8);
    L_0x018c:
        r8 = r7.a;
        r0 = "NationalDay";
        r1 = "10.1";
        r8.put(r0, r1);
        r8 = r7.a;
        r0 = "v";
        r1 = "1";
        r8.put(r0, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ob.a(okhttp3.Request):void");
    }

    private String a(String str) {
        str = q.a(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("USETVOQC5ZDUVYMW");
        stringBuilder.append(str);
        return pv.a(stringBuilder.toString());
    }

    private String b(String str) {
        if (str == null) {
            return "null";
        }
        str = str.replace("\n", "");
        int i = 0;
        int length = str.length();
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt <= 31 || charAt >= 127) {
                try {
                    return URLEncoder.encode(str, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
            }
        }
        return str;
    }
}
