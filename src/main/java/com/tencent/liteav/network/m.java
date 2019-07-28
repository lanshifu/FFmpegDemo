package com.tencent.liteav.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.b;
import com.yalantis.ucrop.view.CropImageView;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: UploadQualityData */
public class m {
    protected static m a = new m();
    private Context b = null;
    private long c = 3;

    /* compiled from: UploadQualityData */
    protected class a {
        public float a = CropImageView.DEFAULT_ASPECT_RATIO;
        public float b = CropImageView.DEFAULT_ASPECT_RATIO;
        public float c = CropImageView.DEFAULT_ASPECT_RATIO;
        public float d = CropImageView.DEFAULT_ASPECT_RATIO;
        public long e = 0;

        protected a() {
        }
    }

    public static m a() {
        return a;
    }

    protected m() {
    }

    public void a(Context context) {
        if (this.b == null) {
            this.b = context.getApplicationContext();
        }
    }

    public String b() {
        try {
            if (this.b != null) {
                int c = b.c(this.b);
                if (c == 255) {
                    return "";
                }
                if (c == 1) {
                    WifiManager wifiManager = (WifiManager) this.b.getSystemService("wifi");
                    if (wifiManager != null) {
                        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                        if (connectionInfo != null) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("wifi:");
                            stringBuilder.append(connectionInfo.getSSID());
                            return stringBuilder.toString();
                        }
                    }
                } else if (c == 2) {
                    return "4g:";
                } else {
                    if (c == 3) {
                        return "3g:";
                    }
                    if (c == 4) {
                        return "2g:";
                    }
                    return c == 5 ? "ethernet:" : "xg:";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void a(String str, long j, long j2, long j3, float f, float f2, float f3) {
        String str2 = str;
        if ((com.tencent.liteav.basic.f.b.a().a("Network", "QualityDataCacheCount") > 0 ? 1 : null) != null) {
            TXCLog.e("UploadQualityData", String.format("updateQualityData: accessID = %s serverType = %d totalTime = %d networkRTT = %d avgBlockCnt = %f avgVideoQue = %f avgAudioQue = %f", new Object[]{str2, Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3), Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)}));
            if (!b(str)) {
                try {
                    SharedPreferences sharedPreferences = this.b.getSharedPreferences("com.tencent.liteav.network", 0);
                    JSONObject c = c(sharedPreferences.getString("34238512-C08C-4931-A000-40E1D8B5BA5B", ""));
                    JSONObject optJSONObject = c.optJSONObject(str);
                    if (optJSONObject == null) {
                        optJSONObject = new JSONObject();
                    }
                    String str3 = j == 3 ? "DomainArrayData" : "OriginArrayData";
                    Object optJSONArray = optJSONObject.optJSONArray(str3);
                    if (optJSONArray == null) {
                        optJSONArray = new JSONArray();
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("totalTime", j2);
                    jSONObject.put("networkRTT", j3);
                    jSONObject.put("avgBlockCnt", (double) f);
                    jSONObject.put("avgVideoQue", (double) f2);
                    jSONObject.put("avgAudioQue", (double) f3);
                    optJSONArray.put(jSONObject);
                    int length = optJSONArray.length();
                    long j4 = (long) length;
                    if (j4 > this.c) {
                        JSONArray jSONArray = new JSONArray();
                        for (int i = (int) (j4 - this.c); i < length; i++) {
                            jSONArray.put(optJSONArray.get(i));
                        }
                        optJSONArray = jSONArray;
                    }
                    optJSONObject.put(str3, optJSONArray);
                    c.put(str, optJSONObject);
                    sharedPreferences.edit().putString("34238512-C08C-4931-A000-40E1D8B5BA5B", c.toString()).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: Missing block: B:20:0x00cd, code skipped:
            return false;
     */
    public boolean c() {
        /*
        r14 = this;
        r14.d();
        r0 = r14.b();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "isDomainAddressBetter: accessID = ";
        r1.append(r2);
        r1.append(r0);
        r2 = " minQualityDataCount = ";
        r1.append(r2);
        r2 = r14.c;
        r1.append(r2);
        r1 = r1.toString();
        r2 = 1;
        r3 = r14.a(r0, r2);
        r4 = 0;
        r0 = r14.a(r0, r4);
        r5 = 5;
        r6 = 4;
        r7 = 3;
        r8 = 2;
        r9 = 6;
        if (r3 == 0) goto L_0x0065;
    L_0x0033:
        r10 = "%s \n isDomainAddressBetter：domainQualityData count = %d avgNetworkRTT = %f avgBlockCount = %f avgVideoQueue = %f avgAudioQueue = %f";
        r11 = new java.lang.Object[r9];
        r11[r4] = r1;
        r12 = r3.e;
        r1 = java.lang.Long.valueOf(r12);
        r11[r2] = r1;
        r1 = r3.a;
        r1 = java.lang.Float.valueOf(r1);
        r11[r8] = r1;
        r1 = r3.b;
        r1 = java.lang.Float.valueOf(r1);
        r11[r7] = r1;
        r1 = r3.c;
        r1 = java.lang.Float.valueOf(r1);
        r11[r6] = r1;
        r1 = r3.d;
        r1 = java.lang.Float.valueOf(r1);
        r11[r5] = r1;
        r1 = java.lang.String.format(r10, r11);
    L_0x0065:
        if (r0 == 0) goto L_0x0099;
    L_0x0067:
        r10 = "%s \n isDomainAddressBetter：originQualityData count = %d avgNetworkRTT = %f avgBlockCount = %f avgVideoQueue = %f avgAudioQueue = %f";
        r9 = new java.lang.Object[r9];
        r9[r4] = r1;
        r11 = r0.e;
        r1 = java.lang.Long.valueOf(r11);
        r9[r2] = r1;
        r1 = r0.a;
        r1 = java.lang.Float.valueOf(r1);
        r9[r8] = r1;
        r1 = r0.b;
        r1 = java.lang.Float.valueOf(r1);
        r9[r7] = r1;
        r1 = r0.c;
        r1 = java.lang.Float.valueOf(r1);
        r9[r6] = r1;
        r1 = r0.d;
        r1 = java.lang.Float.valueOf(r1);
        r9[r5] = r1;
        r1 = java.lang.String.format(r10, r9);
    L_0x0099:
        r5 = "UploadQualityData";
        com.tencent.liteav.basic.log.TXCLog.e(r5, r1);
        if (r3 == 0) goto L_0x00cd;
    L_0x00a0:
        r5 = r3.e;
        r7 = r14.c;
        r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r1 < 0) goto L_0x00cd;
    L_0x00a8:
        if (r0 == 0) goto L_0x00cd;
    L_0x00aa:
        r5 = r0.e;
        r7 = r14.c;
        r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r1 >= 0) goto L_0x00b3;
    L_0x00b2:
        goto L_0x00cd;
    L_0x00b3:
        r1 = r3.b;
        r5 = r0.b;
        r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1));
        if (r1 >= 0) goto L_0x00cc;
    L_0x00bb:
        r1 = r3.c;
        r5 = r0.c;
        r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1));
        if (r1 >= 0) goto L_0x00cc;
    L_0x00c3:
        r1 = r3.d;
        r0 = r0.d;
        r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1));
        if (r0 >= 0) goto L_0x00cc;
    L_0x00cb:
        return r2;
    L_0x00cc:
        return r4;
    L_0x00cd:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.network.m.c():boolean");
    }

    private a a(String str, boolean z) {
        if (b(str)) {
            return null;
        }
        try {
            int i = 0;
            String string = this.b.getSharedPreferences("com.tencent.liteav.network", 0).getString("34238512-C08C-4931-A000-40E1D8B5BA5B", "");
            if (b(string)) {
                return null;
            }
            JSONObject optJSONObject = new JSONObject(string).optJSONObject(str);
            if (optJSONObject == null) {
                return null;
            }
            JSONArray optJSONArray = optJSONObject.optJSONArray(z ? "DomainArrayData" : "OriginArrayData");
            if (optJSONArray == null) {
                return null;
            }
            long length = (long) optJSONArray.length();
            if (length == 0) {
                return null;
            }
            float f = CropImageView.DEFAULT_ASPECT_RATIO;
            String str2 = "";
            int i2 = 0;
            float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
            float f3 = CropImageView.DEFAULT_ASPECT_RATIO;
            float f4 = CropImageView.DEFAULT_ASPECT_RATIO;
            while (((long) i2) < length) {
                JSONObject jSONObject = optJSONArray.getJSONObject(i2);
                f += (float) jSONObject.optLong("networkRTT");
                double d = (double) f2;
                double optDouble = jSONObject.optDouble("avgBlockCnt");
                Double.isNaN(d);
                f2 = (float) (d + optDouble);
                d = (double) f3;
                optDouble = jSONObject.optDouble("avgVideoQue");
                Double.isNaN(d);
                f3 = (float) (d + optDouble);
                d = (double) f4;
                optDouble = jSONObject.optDouble("avgAudioQue");
                Double.isNaN(d);
                f4 = (float) (d + optDouble);
                str2 = String.format("%s \n isDomainAddressBetter：itemData domain = %b NetworkRTT = %d avgBlockCount = %f avgVideoQueue = %f avgAudioQueue = %f", new Object[]{str2, Boolean.valueOf(z), Long.valueOf(jSONObject.optLong("networkRTT")), Double.valueOf(jSONObject.optDouble("avgBlockCnt")), Double.valueOf(jSONObject.optDouble("avgVideoQue")), Double.valueOf(jSONObject.optDouble("avgAudioQue"))});
                i2++;
                i = 0;
            }
            float f5 = (float) length;
            f /= f5;
            f2 /= f5;
            f3 /= f5;
            f4 /= f5;
            a aVar = new a();
            aVar.a = f;
            aVar.b = f2;
            aVar.c = f3;
            aVar.d = f4;
            aVar.e = length;
            return aVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean b(String str) {
        return str == null || str.length() == 0;
    }

    private JSONObject c(String str) {
        if (!b(str)) {
            try {
                return new JSONObject(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new JSONObject();
    }

    private void d() {
        this.c = com.tencent.liteav.basic.f.b.a().a("Network", "QualityDataCacheCount");
        if (this.c == -1 || this.c < 3) {
            this.c = 3;
        }
    }

    public long a(String str) {
        if (this.b != null) {
            return this.b.getSharedPreferences("com.tencent.liteav.network", 0).getLong(str, 0);
        }
        return 0;
    }

    public void a(String str, long j) {
        if (this.b != null) {
            this.b.getSharedPreferences("com.tencent.liteav.network", 0).edit().putLong(str, j).commit();
        }
    }
}
