package com.tencent.liteav.basic.c;

import android.content.Context;

/* compiled from: HttpFileUtil */
public class c extends a {
    private Context b;
    private String c;
    private String d;
    private String e;
    private b f;
    private long g;
    private long h;
    private boolean i;

    public c(Context context, String str, String str2, String str3, b bVar, boolean z) {
        this.b = context;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = bVar;
        this.i = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:127:0x01b0 A:{SYNTHETIC, Splitter:B:127:0x01b0} */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01b5 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01ba A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01c1 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01cf A:{SYNTHETIC, Splitter:B:143:0x01cf} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01d4 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x01d9 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01b0 A:{SYNTHETIC, Splitter:B:127:0x01b0} */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01b5 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01ba A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01c1 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01cf A:{SYNTHETIC, Splitter:B:143:0x01cf} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01d4 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x01d9 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01b0 A:{SYNTHETIC, Splitter:B:127:0x01b0} */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01b5 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01ba A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01c1 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01cf A:{SYNTHETIC, Splitter:B:143:0x01cf} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01d4 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x01d9 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01a5 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:34:0x0099} */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01cf A:{SYNTHETIC, Splitter:B:143:0x01cf} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01d4 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x01d9 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01b0 A:{SYNTHETIC, Splitter:B:127:0x01b0} */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01b5 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01ba A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01c1 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01cf A:{SYNTHETIC, Splitter:B:143:0x01cf} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01d4 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x01d9 A:{Catch:{ IOException -> 0x01e1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01b0 A:{SYNTHETIC, Splitter:B:127:0x01b0} */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01b5 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01ba A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01c1 A:{Catch:{ IOException -> 0x01c6 }} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:104:0x0170, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:105:0x0171, code skipped:
            r3 = r0;
            r6 = null;
     */
    /* JADX WARNING: Missing block: B:117:0x019d, code skipped:
            if (r1.f != null) goto L_0x019f;
     */
    /* JADX WARNING: Missing block: B:118:0x019f, code skipped:
            r1.f.a();
     */
    /* JADX WARNING: Missing block: B:119:0x01a5, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:128:?, code skipped:
            r15.close();
     */
    /* JADX WARNING: Missing block: B:130:0x01b5, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:132:0x01ba, code skipped:
            r5.disconnect();
     */
    /* JADX WARNING: Missing block: B:135:0x01c1, code skipped:
            r1.f.a();
     */
    /* JADX WARNING: Missing block: B:144:?, code skipped:
            r10.close();
     */
    /* JADX WARNING: Missing block: B:146:0x01d4, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:148:0x01d9, code skipped:
            r5.disconnect();
     */
    /* JADX WARNING: Missing block: B:150:0x01de, code skipped:
            if (r1.f != null) goto L_0x019f;
     */
    public void run() {
        /*
        r16 = this;
        r1 = r16;
        r0 = r1.b;
        r0 = com.tencent.liteav.basic.util.a.a(r0);
        r2 = 0;
        r3 = 0;
        if (r0 == 0) goto L_0x01f0;
    L_0x000c:
        r0 = r1.c;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x01f0;
    L_0x0014:
        r0 = r1.d;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x01f0;
    L_0x001c:
        r0 = r1.e;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x01f0;
    L_0x0024:
        r0 = r1.c;
        r4 = "http";
        r0 = r0.startsWith(r4);
        if (r0 != 0) goto L_0x0030;
    L_0x002e:
        goto L_0x01f0;
    L_0x0030:
        r0 = new java.io.File;
        r4 = r1.d;
        r0.<init>(r4);
        r4 = r0.exists();
        if (r4 != 0) goto L_0x0041;
    L_0x003d:
        r0.mkdirs();
        goto L_0x0051;
    L_0x0041:
        r4 = r0.isFile();
        if (r4 == 0) goto L_0x0051;
    L_0x0047:
        r4 = r1.f;
        if (r4 == 0) goto L_0x0051;
    L_0x004b:
        r2 = r1.f;
        r2.a(r0, r3);
        return;
    L_0x0051:
        r4 = new java.io.File;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r5 = r1.d;
        r0.append(r5);
        r5 = java.io.File.separator;
        r0.append(r5);
        r5 = r1.e;
        r0.append(r5);
        r0 = r0.toString();
        r4.<init>(r0);
        r0 = r4.exists();	 Catch:{ Exception -> 0x01c7, all -> 0x01aa }
        if (r0 == 0) goto L_0x0086;
    L_0x0074:
        r4.delete();	 Catch:{ Exception -> 0x007e, all -> 0x0078 }
        goto L_0x0086;
    L_0x0078:
        r0 = move-exception;
        r5 = r3;
        r6 = r5;
    L_0x007b:
        r15 = r6;
        goto L_0x01ae;
    L_0x007e:
        r0 = move-exception;
        r5 = r3;
        r6 = r5;
        r10 = r6;
        r7 = 0;
    L_0x0083:
        r3 = r0;
        goto L_0x01cd;
    L_0x0086:
        r4.createNewFile();	 Catch:{ Exception -> 0x01c7, all -> 0x01aa }
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x01c7, all -> 0x01aa }
        r5 = r1.c;	 Catch:{ Exception -> 0x01c7, all -> 0x01aa }
        r0.<init>(r5);	 Catch:{ Exception -> 0x01c7, all -> 0x01aa }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x01c7, all -> 0x01aa }
        r5 = r0;
        r5 = (java.net.HttpURLConnection) r5;	 Catch:{ Exception -> 0x01c7, all -> 0x01aa }
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r5.setConnectTimeout(r0);	 Catch:{ Exception -> 0x01a7, all -> 0x01a5 }
        r5.setReadTimeout(r0);	 Catch:{ Exception -> 0x01a7, all -> 0x01a5 }
        r0 = 1;
        r5.setDoInput(r0);	 Catch:{ Exception -> 0x01a7, all -> 0x01a5 }
        r6 = "GET";
        r5.setRequestMethod(r6);	 Catch:{ Exception -> 0x01a7, all -> 0x01a5 }
        r6 = r5.getResponseCode();	 Catch:{ Exception -> 0x01a7, all -> 0x01a5 }
        r7 = r5.getResponseCode();	 Catch:{ Exception -> 0x01a7, all -> 0x01a5 }
        r8 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r7 != r8) goto L_0x00b6;
    L_0x00b4:
        r7 = 1;
        goto L_0x00b7;
    L_0x00b6:
        r7 = 0;
    L_0x00b7:
        if (r7 == 0) goto L_0x0174;
    L_0x00b9:
        r0 = r1.i;	 Catch:{ Exception -> 0x0170, all -> 0x01a5 }
        r8 = 0;
        if (r0 == 0) goto L_0x010d;
    L_0x00bf:
        r0 = r5.getContentLength();	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        r10 = (long) r0;	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        r1.g = r10;	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        r10 = r1.g;	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        r0 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1));
        if (r0 > 0) goto L_0x00e4;
    L_0x00cc:
        r0 = r1.f;	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        if (r0 == 0) goto L_0x00d5;
    L_0x00d0:
        r0 = r1.f;	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        r0.a(r4, r3);	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
    L_0x00d5:
        if (r5 == 0) goto L_0x00da;
    L_0x00d7:
        r5.disconnect();	 Catch:{ IOException -> 0x00e3 }
    L_0x00da:
        r0 = r1.f;	 Catch:{ IOException -> 0x00e3 }
        if (r0 == 0) goto L_0x00e3;
    L_0x00de:
        r0 = r1.f;	 Catch:{ IOException -> 0x00e3 }
        r0.a();	 Catch:{ IOException -> 0x00e3 }
    L_0x00e3:
        return;
    L_0x00e4:
        r10 = r1.g;	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        r0 = com.tencent.liteav.basic.util.a.a(r10);	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        if (r0 != 0) goto L_0x010d;
    L_0x00ec:
        r0 = r1.f;	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        if (r0 == 0) goto L_0x00f5;
    L_0x00f0:
        r0 = r1.f;	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
        r0.a(r4, r3);	 Catch:{ Exception -> 0x0108, all -> 0x0104 }
    L_0x00f5:
        if (r5 == 0) goto L_0x00fa;
    L_0x00f7:
        r5.disconnect();	 Catch:{ IOException -> 0x0103 }
    L_0x00fa:
        r0 = r1.f;	 Catch:{ IOException -> 0x0103 }
        if (r0 == 0) goto L_0x0103;
    L_0x00fe:
        r0 = r1.f;	 Catch:{ IOException -> 0x0103 }
        r0.a();	 Catch:{ IOException -> 0x0103 }
    L_0x0103:
        return;
    L_0x0104:
        r0 = move-exception;
        r6 = r3;
        goto L_0x007b;
    L_0x0108:
        r0 = move-exception;
        r6 = r3;
        r10 = r6;
        goto L_0x0083;
    L_0x010d:
        r6 = r5.getInputStream();	 Catch:{ Exception -> 0x0170, all -> 0x01a5 }
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r0 = new byte[r0];	 Catch:{ Exception -> 0x016d, all -> 0x016b }
        r10 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x016d, all -> 0x016b }
        r10.<init>(r4);	 Catch:{ Exception -> 0x016d, all -> 0x016b }
        r1.h = r8;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
    L_0x011c:
        r8 = r6.read(r0);	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r9 = -1;
        if (r8 == r9) goto L_0x0150;
    L_0x0123:
        r10.write(r0, r2, r8);	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r9 = r1.i;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        if (r9 == 0) goto L_0x011c;
    L_0x012a:
        r11 = r1.h;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r13 = 100;
        r11 = r11 * r13;
        r2 = r1.g;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r11 = r11 / r2;
        r2 = (int) r11;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r11 = r1.h;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r8 = (long) r8;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r11 = r11 + r8;
        r1.h = r11;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r8 = r1.h;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r8 = r8 * r13;
        r11 = r1.g;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r8 = r8 / r11;
        r3 = (int) r8;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        if (r2 == r3) goto L_0x014d;
    L_0x0144:
        r2 = r1.f;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        if (r2 == 0) goto L_0x014d;
    L_0x0148:
        r2 = r1.f;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r2.a(r3);	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
    L_0x014d:
        r2 = 0;
        r3 = 0;
        goto L_0x011c;
    L_0x0150:
        r10.flush();	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r0 = r1.f;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        if (r0 == 0) goto L_0x0163;
    L_0x0157:
        r0 = r1.f;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r2 = 100;
        r0.a(r2);	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r0 = r1.f;	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
        r0.a(r4);	 Catch:{ Exception -> 0x0168, all -> 0x0165 }
    L_0x0163:
        r3 = 0;
        goto L_0x018c;
    L_0x0165:
        r0 = move-exception;
        r15 = r10;
        goto L_0x01ae;
    L_0x0168:
        r0 = move-exception;
        goto L_0x0083;
    L_0x016b:
        r0 = move-exception;
        goto L_0x01ad;
    L_0x016d:
        r0 = move-exception;
        r3 = r0;
        goto L_0x01cc;
    L_0x0170:
        r0 = move-exception;
        r3 = r0;
        r6 = 0;
        goto L_0x01cc;
    L_0x0174:
        r3 = new com.tencent.liteav.basic.c.d;	 Catch:{ Exception -> 0x0170, all -> 0x01a5 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0170, all -> 0x01a5 }
        r0.<init>();	 Catch:{ Exception -> 0x0170, all -> 0x01a5 }
        r2 = "http status got exception. code = ";
        r0.append(r2);	 Catch:{ Exception -> 0x0170, all -> 0x01a5 }
        r0.append(r6);	 Catch:{ Exception -> 0x0170, all -> 0x01a5 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0170, all -> 0x01a5 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x0170, all -> 0x01a5 }
        r6 = 0;
        r10 = 0;
    L_0x018c:
        if (r10 == 0) goto L_0x0191;
    L_0x018e:
        r10.close();	 Catch:{ IOException -> 0x01e1 }
    L_0x0191:
        if (r6 == 0) goto L_0x0196;
    L_0x0193:
        r6.close();	 Catch:{ IOException -> 0x01e1 }
    L_0x0196:
        if (r5 == 0) goto L_0x019b;
    L_0x0198:
        r5.disconnect();	 Catch:{ IOException -> 0x01e1 }
    L_0x019b:
        r0 = r1.f;	 Catch:{ IOException -> 0x01e1 }
        if (r0 == 0) goto L_0x01e1;
    L_0x019f:
        r0 = r1.f;	 Catch:{ IOException -> 0x01e1 }
        r0.a();	 Catch:{ IOException -> 0x01e1 }
        goto L_0x01e1;
    L_0x01a5:
        r0 = move-exception;
        goto L_0x01ac;
    L_0x01a7:
        r0 = move-exception;
        r3 = r0;
        goto L_0x01ca;
    L_0x01aa:
        r0 = move-exception;
        r5 = 0;
    L_0x01ac:
        r6 = 0;
    L_0x01ad:
        r15 = 0;
    L_0x01ae:
        if (r15 == 0) goto L_0x01b3;
    L_0x01b0:
        r15.close();	 Catch:{ IOException -> 0x01c6 }
    L_0x01b3:
        if (r6 == 0) goto L_0x01b8;
    L_0x01b5:
        r6.close();	 Catch:{ IOException -> 0x01c6 }
    L_0x01b8:
        if (r5 == 0) goto L_0x01bd;
    L_0x01ba:
        r5.disconnect();	 Catch:{ IOException -> 0x01c6 }
    L_0x01bd:
        r2 = r1.f;	 Catch:{ IOException -> 0x01c6 }
        if (r2 == 0) goto L_0x01c6;
    L_0x01c1:
        r2 = r1.f;	 Catch:{ IOException -> 0x01c6 }
        r2.a();	 Catch:{ IOException -> 0x01c6 }
    L_0x01c6:
        throw r0;
    L_0x01c7:
        r0 = move-exception;
        r3 = r0;
        r5 = 0;
    L_0x01ca:
        r6 = 0;
        r7 = 0;
    L_0x01cc:
        r10 = 0;
    L_0x01cd:
        if (r10 == 0) goto L_0x01d2;
    L_0x01cf:
        r10.close();	 Catch:{ IOException -> 0x01e1 }
    L_0x01d2:
        if (r6 == 0) goto L_0x01d7;
    L_0x01d4:
        r6.close();	 Catch:{ IOException -> 0x01e1 }
    L_0x01d7:
        if (r5 == 0) goto L_0x01dc;
    L_0x01d9:
        r5.disconnect();	 Catch:{ IOException -> 0x01e1 }
    L_0x01dc:
        r0 = r1.f;	 Catch:{ IOException -> 0x01e1 }
        if (r0 == 0) goto L_0x01e1;
    L_0x01e0:
        goto L_0x019f;
    L_0x01e1:
        if (r7 == 0) goto L_0x01e5;
    L_0x01e3:
        if (r3 == 0) goto L_0x01ef;
    L_0x01e5:
        r0 = r1.f;
        if (r0 == 0) goto L_0x01ef;
    L_0x01e9:
        r0 = r1.f;
        r2 = 0;
        r0.a(r4, r2);
    L_0x01ef:
        return;
    L_0x01f0:
        r2 = r3;
        r3 = 0;
        r1.a(r2, r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.c.c.run():void");
    }

    private void a(Exception exception, int i) {
        if (this.f != null) {
            this.f.a(null, exception);
        }
        this.f = null;
    }
}
