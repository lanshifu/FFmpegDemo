package com.tencent.liteav.network;

import android.content.Context;
import android.os.Handler;
import com.tencent.liteav.basic.f.b;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import java.util.Iterator;
import java.util.Vector;

/* compiled from: TXRTMPAccUrlFetcher */
public class k {
    private String a = "";
    private String b = "";
    private int c = 0;
    private String d = "";
    private Handler e;

    /* compiled from: TXRTMPAccUrlFetcher */
    public interface a {
        void a(int i, String str, Vector<e> vector);
    }

    public k(Context context) {
        if (context != null) {
            this.e = new Handler(context.getMainLooper());
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public int a(String str, int i, a aVar) {
        this.a = "";
        this.b = "";
        this.c = 0;
        this.d = "";
        if (str == null || str.isEmpty()) {
            return -1;
        }
        String streamIDByStreamUrl = TXCCommonUtil.getStreamIDByStreamUrl(str);
        if (streamIDByStreamUrl == null || streamIDByStreamUrl.isEmpty()) {
            return -2;
        }
        String a = a("bizid", str);
        String a2 = a("txSecret", str);
        str = a("txTime", str);
        if (!a(true, a, str, a2)) {
            return -3;
        }
        final String str2 = streamIDByStreamUrl;
        final String str3 = a;
        final String str4 = a2;
        final String str5 = str;
        final a aVar2 = aVar;
        a(streamIDByStreamUrl, a, a2, str, i, new a() {
            public void a(int i, String str, Vector<e> vector) {
                k.this.a = str2;
                k.this.b = str3;
                k.this.c = i;
                k.this.d = str;
                if (vector != null && !vector.isEmpty()) {
                    e eVar;
                    StringBuilder stringBuilder;
                    Vector vector2 = new Vector();
                    Iterator it = vector.iterator();
                    while (it.hasNext()) {
                        eVar = (e) it.next();
                        String str2 = eVar.a;
                        if (str2.indexOf("?") != -1) {
                            str2 = str2.substring(0, str2.indexOf("?"));
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str2);
                        stringBuilder.append("?txSecret=");
                        stringBuilder.append(str4);
                        stringBuilder.append("&txTime=");
                        stringBuilder.append(str5);
                        stringBuilder.append("&bizid=");
                        stringBuilder.append(str3);
                        vector2.add(new e(stringBuilder.toString(), eVar.b));
                    }
                    if (aVar2 != null) {
                        it = vector2.iterator();
                        while (it.hasNext()) {
                            eVar = (e) it.next();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("accurl = ");
                            stringBuilder.append(eVar.a);
                            stringBuilder.append(" quic = ");
                            stringBuilder.append(eVar.b);
                            TXCLog.e("TXRTMPAccUrlFetcher", stringBuilder.toString());
                        }
                        aVar2.a(i, str, vector2);
                    }
                } else if (aVar2 != null) {
                    aVar2.a(i, str, null);
                }
            }
        });
        return 0;
    }

    private boolean a(boolean z, String str, String str2, String str3) {
        boolean z2 = true;
        if (z) {
            if (str == null || str.isEmpty() || str2 == null || str2.isEmpty() || str3 == null || str3.isEmpty()) {
                z2 = false;
            }
            return z2;
        }
        if (str == null || str2 == null || str3 == null) {
            z2 = false;
        }
        return z2;
    }

    private long e() {
        return b.a().a("Network", "AccRetryCountWithoutSecret");
    }

    private void a(String str, String str2, String str3, String str4, int i, a aVar) {
        final String str5 = str2;
        final String str6 = str4;
        final String str7 = str3;
        final String str8 = str;
        final int i2 = i;
        final a aVar2 = aVar;
        new Thread("getRTMPAccUrl") {
            /* JADX WARNING: Removed duplicated region for block: B:57:0x01f4 A:{Catch:{ Exception -> 0x0237 }} */
            /* JADX WARNING: Removed duplicated region for block: B:53:0x01df A:{Catch:{ Exception -> 0x0237 }} */
            public void run() {
                /*
                r15 = this;
                r0 = "";
                r1 = r3;
                r2 = 0;
                if (r1 == 0) goto L_0x001a;
            L_0x0007:
                r1 = r3;
                r1 = r1.isEmpty();
                if (r1 != 0) goto L_0x001a;
            L_0x000f:
                r1 = r3;
                r1 = java.lang.Integer.valueOf(r1);
                r1 = r1.intValue();
                goto L_0x001b;
            L_0x001a:
                r1 = 0;
            L_0x001b:
                r3 = 5;
                r4 = com.tencent.liteav.network.k.this;
                r5 = r3;
                r6 = r4;
                r7 = r5;
                r8 = 1;
                r4 = r4.a(r8, r5, r6, r7);
                if (r4 != 0) goto L_0x0035;
            L_0x002b:
                r3 = com.tencent.liteav.network.k.this;
                r3 = r3.e();
                r3 = (int) r3;
                if (r3 > 0) goto L_0x0035;
            L_0x0034:
                r3 = 1;
            L_0x0035:
                r4 = -1;
            L_0x0036:
                if (r3 < r8) goto L_0x0257;
            L_0x0038:
                r5 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0237 }
                r5.<init>();	 Catch:{ Exception -> 0x0237 }
                r6 = "bizid";
                r5.put(r6, r1);	 Catch:{ Exception -> 0x0237 }
                r6 = "stream_id";
                r7 = r6;	 Catch:{ Exception -> 0x0237 }
                r5.put(r6, r7);	 Catch:{ Exception -> 0x0237 }
                r6 = "txSecret";
                r7 = r5;	 Catch:{ Exception -> 0x0237 }
                r5.put(r6, r7);	 Catch:{ Exception -> 0x0237 }
                r6 = "txTime";
                r7 = r4;	 Catch:{ Exception -> 0x0237 }
                r5.put(r6, r7);	 Catch:{ Exception -> 0x0237 }
                r6 = "type";
                r5.put(r6, r8);	 Catch:{ Exception -> 0x0237 }
                r5 = r5.toString();	 Catch:{ Exception -> 0x0237 }
                r6 = new java.net.URL;	 Catch:{ Exception -> 0x0237 }
                r7 = "https://livepull.myqcloud.com/getpulladdr";
                r6.<init>(r7);	 Catch:{ Exception -> 0x0237 }
                r6 = r6.openConnection();	 Catch:{ Exception -> 0x0237 }
                r6 = (javax.net.ssl.HttpsURLConnection) r6;	 Catch:{ Exception -> 0x0237 }
                r6.setDoOutput(r8);	 Catch:{ Exception -> 0x0237 }
                r6.setDoInput(r8);	 Catch:{ Exception -> 0x0237 }
                r6.setUseCaches(r2);	 Catch:{ Exception -> 0x0237 }
                r7 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
                r6.setConnectTimeout(r7);	 Catch:{ Exception -> 0x0237 }
                r6.setReadTimeout(r7);	 Catch:{ Exception -> 0x0237 }
                r7 = "POST";
                r6.setRequestMethod(r7);	 Catch:{ Exception -> 0x0237 }
                r7 = "Charsert";
                r9 = "UTF-8";
                r6.setRequestProperty(r7, r9);	 Catch:{ Exception -> 0x0237 }
                r7 = "Content-Type";
                r9 = "text/plain;";
                r6.setRequestProperty(r7, r9);	 Catch:{ Exception -> 0x0237 }
                r7 = "Content-Length";
                r9 = r5.length();	 Catch:{ Exception -> 0x0237 }
                r9 = java.lang.String.valueOf(r9);	 Catch:{ Exception -> 0x0237 }
                r6.setRequestProperty(r7, r9);	 Catch:{ Exception -> 0x0237 }
                r7 = "TXRTMPAccUrlFetcher";
                r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0237 }
                r9.<init>();	 Catch:{ Exception -> 0x0237 }
                r10 = "getAccelerateStreamPlayUrl: sendHttpRequest[ ";
                r9.append(r10);	 Catch:{ Exception -> 0x0237 }
                r9.append(r5);	 Catch:{ Exception -> 0x0237 }
                r10 = "] retryIndex = ";
                r9.append(r10);	 Catch:{ Exception -> 0x0237 }
                r9.append(r3);	 Catch:{ Exception -> 0x0237 }
                r9 = r9.toString();	 Catch:{ Exception -> 0x0237 }
                com.tencent.liteav.basic.log.TXCLog.e(r7, r9);	 Catch:{ Exception -> 0x0237 }
                r7 = new java.io.DataOutputStream;	 Catch:{ Exception -> 0x0237 }
                r9 = r6.getOutputStream();	 Catch:{ Exception -> 0x0237 }
                r7.<init>(r9);	 Catch:{ Exception -> 0x0237 }
                r5 = r5.getBytes();	 Catch:{ Exception -> 0x0237 }
                r7.write(r5);	 Catch:{ Exception -> 0x0237 }
                r5 = "";
                r7 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0237 }
                r9 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0237 }
                r6 = r6.getInputStream();	 Catch:{ Exception -> 0x0237 }
                r9.<init>(r6);	 Catch:{ Exception -> 0x0237 }
                r7.<init>(r9);	 Catch:{ Exception -> 0x0237 }
            L_0x00dc:
                r6 = r7.readLine();	 Catch:{ Exception -> 0x0237 }
                if (r6 == 0) goto L_0x00f2;
            L_0x00e2:
                r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0237 }
                r9.<init>();	 Catch:{ Exception -> 0x0237 }
                r9.append(r5);	 Catch:{ Exception -> 0x0237 }
                r9.append(r6);	 Catch:{ Exception -> 0x0237 }
                r5 = r9.toString();	 Catch:{ Exception -> 0x0237 }
                goto L_0x00dc;
            L_0x00f2:
                r6 = "TXRTMPAccUrlFetcher";
                r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0237 }
                r7.<init>();	 Catch:{ Exception -> 0x0237 }
                r9 = "getAccelerateStreamPlayUrl: receive response, strResponse = ";
                r7.append(r9);	 Catch:{ Exception -> 0x0237 }
                r7.append(r5);	 Catch:{ Exception -> 0x0237 }
                r7 = r7.toString();	 Catch:{ Exception -> 0x0237 }
                com.tencent.liteav.basic.log.TXCLog.e(r6, r7);	 Catch:{ Exception -> 0x0237 }
                r6 = new org.json.JSONTokener;	 Catch:{ Exception -> 0x0237 }
                r6.<init>(r5);	 Catch:{ Exception -> 0x0237 }
                r5 = r6.nextValue();	 Catch:{ Exception -> 0x0237 }
                r5 = (org.json.JSONObject) r5;	 Catch:{ Exception -> 0x0237 }
                r6 = "code";
                r6 = r5.has(r6);	 Catch:{ Exception -> 0x0237 }
                if (r6 == 0) goto L_0x0122;
            L_0x011b:
                r6 = "code";
                r6 = r5.getInt(r6);	 Catch:{ Exception -> 0x0237 }
                r4 = r6;
            L_0x0122:
                if (r4 == 0) goto L_0x0151;
            L_0x0124:
                r6 = "message";
                r6 = r5.has(r6);	 Catch:{ Exception -> 0x0237 }
                if (r6 == 0) goto L_0x0133;
            L_0x012c:
                r6 = "message";
                r6 = r5.getString(r6);	 Catch:{ Exception -> 0x0237 }
                r0 = r6;
            L_0x0133:
                r6 = "TXRTMPAccUrlFetcher";
                r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0237 }
                r7.<init>();	 Catch:{ Exception -> 0x0237 }
                r9 = "getAccelerateStreamPlayUrl: errorCode = ";
                r7.append(r9);	 Catch:{ Exception -> 0x0237 }
                r7.append(r4);	 Catch:{ Exception -> 0x0237 }
                r9 = " errorMessage = ";
                r7.append(r9);	 Catch:{ Exception -> 0x0237 }
                r7.append(r0);	 Catch:{ Exception -> 0x0237 }
                r7 = r7.toString();	 Catch:{ Exception -> 0x0237 }
                com.tencent.liteav.basic.log.TXCLog.e(r6, r7);	 Catch:{ Exception -> 0x0237 }
            L_0x0151:
                r6 = "pull_addr";
                r6 = r5.has(r6);	 Catch:{ Exception -> 0x0237 }
                if (r6 == 0) goto L_0x0242;
            L_0x0159:
                r6 = new java.util.Vector;	 Catch:{ Exception -> 0x0237 }
                r6.<init>();	 Catch:{ Exception -> 0x0237 }
                r7 = new java.util.Vector;	 Catch:{ Exception -> 0x0237 }
                r7.<init>();	 Catch:{ Exception -> 0x0237 }
                r9 = "pull_addr";
                r5 = r5.getJSONArray(r9);	 Catch:{ Exception -> 0x0237 }
                if (r5 == 0) goto L_0x01d4;
            L_0x016b:
                r9 = r5.length();	 Catch:{ Exception -> 0x0237 }
                if (r9 != 0) goto L_0x0172;
            L_0x0171:
                goto L_0x01d4;
            L_0x0172:
                r9 = 0;
            L_0x0173:
                r10 = r5.length();	 Catch:{ Exception -> 0x0237 }
                if (r9 >= r10) goto L_0x01db;
            L_0x0179:
                r10 = r5.get(r9);	 Catch:{ Exception -> 0x0237 }
                r10 = (org.json.JSONObject) r10;	 Catch:{ Exception -> 0x0237 }
                if (r10 == 0) goto L_0x01d1;
            L_0x0181:
                r11 = "rtmp_url";
                r11 = r10.getString(r11);	 Catch:{ Exception -> 0x0237 }
                r12 = "proto";
                r10 = r10.getInt(r12);	 Catch:{ Exception -> 0x0237 }
                if (r10 != r8) goto L_0x0191;
            L_0x018f:
                r10 = 1;
                goto L_0x0192;
            L_0x0191:
                r10 = 0;
            L_0x0192:
                r12 = "TXRTMPAccUrlFetcher";
                r13 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0237 }
                r13.<init>();	 Catch:{ Exception -> 0x0237 }
                r14 = "getAccelerateStreamPlayUrl: streamUrl = ";
                r13.append(r14);	 Catch:{ Exception -> 0x0237 }
                r13.append(r11);	 Catch:{ Exception -> 0x0237 }
                r14 = " Q channel = ";
                r13.append(r14);	 Catch:{ Exception -> 0x0237 }
                r13.append(r10);	 Catch:{ Exception -> 0x0237 }
                r13 = r13.toString();	 Catch:{ Exception -> 0x0237 }
                com.tencent.liteav.basic.log.TXCLog.e(r12, r13);	 Catch:{ Exception -> 0x0237 }
                r12 = com.tencent.liteav.basic.util.TXCCommonUtil.getStreamIDByStreamUrl(r11);	 Catch:{ Exception -> 0x0237 }
                if (r12 == 0) goto L_0x01d1;
            L_0x01b6:
                r13 = r6;	 Catch:{ Exception -> 0x0237 }
                r12 = r12.equalsIgnoreCase(r13);	 Catch:{ Exception -> 0x0237 }
                if (r12 == 0) goto L_0x01d1;
            L_0x01be:
                if (r10 == 0) goto L_0x01c9;
            L_0x01c0:
                r12 = new com.tencent.liteav.network.e;	 Catch:{ Exception -> 0x0237 }
                r12.<init>(r11, r10);	 Catch:{ Exception -> 0x0237 }
                r6.add(r12);	 Catch:{ Exception -> 0x0237 }
                goto L_0x01d1;
            L_0x01c9:
                r12 = new com.tencent.liteav.network.e;	 Catch:{ Exception -> 0x0237 }
                r12.<init>(r11, r10);	 Catch:{ Exception -> 0x0237 }
                r7.add(r12);	 Catch:{ Exception -> 0x0237 }
            L_0x01d1:
                r9 = r9 + 1;
                goto L_0x0173;
            L_0x01d4:
                r5 = "TXRTMPAccUrlFetcher";
                r9 = "getAccelerateStreamPlayUrl: no pull_addr";
                com.tencent.liteav.basic.log.TXCLog.e(r5, r9);	 Catch:{ Exception -> 0x0237 }
            L_0x01db:
                r5 = r7;	 Catch:{ Exception -> 0x0237 }
                if (r5 != r8) goto L_0x01f4;
            L_0x01df:
                r5 = r7.size();	 Catch:{ Exception -> 0x0237 }
                if (r5 <= 0) goto L_0x0242;
            L_0x01e5:
                r5 = com.tencent.liteav.network.k.this;	 Catch:{ Exception -> 0x0237 }
                r5 = r5.e;	 Catch:{ Exception -> 0x0237 }
                r6 = new com.tencent.liteav.network.k$2$1;	 Catch:{ Exception -> 0x0237 }
                r6.<init>(r7);	 Catch:{ Exception -> 0x0237 }
                r5.post(r6);	 Catch:{ Exception -> 0x0237 }
                return;
            L_0x01f4:
                r5 = r7;	 Catch:{ Exception -> 0x0237 }
                r9 = 2;
                if (r5 != r9) goto L_0x020e;
            L_0x01f9:
                r5 = r6.size();	 Catch:{ Exception -> 0x0237 }
                if (r5 <= 0) goto L_0x0242;
            L_0x01ff:
                r5 = com.tencent.liteav.network.k.this;	 Catch:{ Exception -> 0x0237 }
                r5 = r5.e;	 Catch:{ Exception -> 0x0237 }
                r7 = new com.tencent.liteav.network.k$2$2;	 Catch:{ Exception -> 0x0237 }
                r7.<init>(r6);	 Catch:{ Exception -> 0x0237 }
                r5.post(r7);	 Catch:{ Exception -> 0x0237 }
                return;
            L_0x020e:
                r5 = r7.iterator();	 Catch:{ Exception -> 0x0237 }
            L_0x0212:
                r7 = r5.hasNext();	 Catch:{ Exception -> 0x0237 }
                if (r7 == 0) goto L_0x0222;
            L_0x0218:
                r7 = r5.next();	 Catch:{ Exception -> 0x0237 }
                r7 = (com.tencent.liteav.network.e) r7;	 Catch:{ Exception -> 0x0237 }
                r6.add(r7);	 Catch:{ Exception -> 0x0237 }
                goto L_0x0212;
            L_0x0222:
                r5 = r6.size();	 Catch:{ Exception -> 0x0237 }
                if (r5 <= 0) goto L_0x0242;
            L_0x0228:
                r5 = com.tencent.liteav.network.k.this;	 Catch:{ Exception -> 0x0237 }
                r5 = r5.e;	 Catch:{ Exception -> 0x0237 }
                r7 = new com.tencent.liteav.network.k$2$3;	 Catch:{ Exception -> 0x0237 }
                r7.<init>(r6);	 Catch:{ Exception -> 0x0237 }
                r5.post(r7);	 Catch:{ Exception -> 0x0237 }
                return;
            L_0x0237:
                r5 = move-exception;
                r6 = "TXRTMPAccUrlFetcher";
                r7 = "getAccelerateStreamPlayUrl exception";
                com.tencent.liteav.basic.log.TXCLog.e(r6, r7);
                r5.printStackTrace();
            L_0x0242:
                r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
                com.tencent.liteav.network.k.AnonymousClass2.sleep(r5, r2);	 Catch:{ Exception -> 0x0248 }
                goto L_0x0253;
            L_0x0248:
                r5 = move-exception;
                r6 = "TXRTMPAccUrlFetcher";
                r7 = "getAccelerateStreamPlayUrl exception sleep";
                com.tencent.liteav.basic.log.TXCLog.e(r6, r7);
                r5.printStackTrace();
            L_0x0253:
                r3 = r3 + -1;
                goto L_0x0036;
            L_0x0257:
                r1 = com.tencent.liteav.network.k.this;
                r1 = r1.e;
                r2 = new com.tencent.liteav.network.k$2$4;
                r2.<init>(r4, r0);
                r1.post(r2);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.network.k$AnonymousClass2.run():void");
            }
        }.start();
    }

    private String a(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return null;
        }
        str = str.toLowerCase();
        for (String str3 : str2.split("[?&]")) {
            String str32;
            if (str32.indexOf("=") != -1) {
                String[] split = str32.split("[=]");
                if (split.length == 2) {
                    String str4 = split[0];
                    str32 = split[1];
                    if (str4 != null && str4.toLowerCase().equalsIgnoreCase(str)) {
                        return str32;
                    }
                }
                continue;
            }
        }
        return "";
    }
}
