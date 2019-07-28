package com.tencent.liteav.f;

import android.text.TextUtils;
import com.tencent.liteav.i.a.b;
import com.tencent.liteav.i.a.g;
import com.tomatolive.library.ui.view.widget.matisse.internal.loader.AlbumLoader;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AnimatedPasterFilterChain */
public class a extends c {
    private static a d;
    private List<b> e;
    private CopyOnWriteArrayList<com.tencent.liteav.d.a> f = new CopyOnWriteArrayList();

    public static a a() {
        if (d == null) {
            d = new a();
        }
        return d;
    }

    private a() {
    }

    public void a(List<b> list) {
        this.e = list;
        this.f.clear();
        if (this.c != null) {
            a(this.c);
        }
    }

    public List<com.tencent.liteav.d.a> b() {
        return this.f;
    }

    /* JADX WARNING: Missing block: B:36:0x00c5, code skipped:
            return;
     */
    public void a(com.tencent.liteav.d.e r19) {
        /*
        r18 = this;
        r0 = r18;
        if (r19 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r2 = r0.a;
        if (r2 == 0) goto L_0x00c5;
    L_0x0009:
        r2 = r0.b;
        if (r2 != 0) goto L_0x000f;
    L_0x000d:
        goto L_0x00c5;
    L_0x000f:
        r2 = r0.e;
        if (r2 == 0) goto L_0x00c4;
    L_0x0013:
        r2 = r0.e;
        r2 = r2.size();
        if (r2 != 0) goto L_0x001d;
    L_0x001b:
        goto L_0x00c4;
    L_0x001d:
        r1 = r18.b(r19);
        r2 = r0.e;
        r2 = r2.iterator();
    L_0x0027:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x00c3;
    L_0x002d:
        r3 = r2.next();
        r3 = (com.tencent.liteav.i.a.b) r3;
        if (r3 != 0) goto L_0x0036;
    L_0x0035:
        goto L_0x0027;
    L_0x0036:
        r4 = r3.b;
        r4 = r0.a(r4, r1);
        r3 = r0.a(r3, r4);
        r4 = r3.a;
        r4 = r0.a(r4);
        if (r4 == 0) goto L_0x0027;
    L_0x0048:
        r5 = r4.c;
        if (r5 > 0) goto L_0x004d;
    L_0x004c:
        goto L_0x0027;
    L_0x004d:
        r5 = r3.c;
        r7 = r3.d;
        r7 = r7 - r5;
        r9 = r4.b;
        r10 = r4.c;
        r9 = r9 / r10;
        r10 = r4.b;
        r10 = (long) r10;
        r10 = r7 / r10;
        r10 = (int) r10;
        r11 = r4.b;
        r11 = (long) r11;
        r7 = r7 % r11;
        r11 = 0;
        r13 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1));
        if (r13 <= 0) goto L_0x0069;
    L_0x0067:
        r10 = r10 + 1;
    L_0x0069:
        r11 = r5;
        r5 = 0;
    L_0x006b:
        if (r5 >= r10) goto L_0x0027;
    L_0x006d:
        r6 = 0;
    L_0x006e:
        r8 = r4.c;
        if (r6 >= r8) goto L_0x00bc;
    L_0x0072:
        r13 = (long) r9;
        r13 = r13 + r11;
        r7 = r3.d;
        r16 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1));
        if (r16 <= 0) goto L_0x007b;
    L_0x007a:
        goto L_0x00bc;
    L_0x007b:
        r7 = r4.g;
        r7 = r7.get(r6);
        r7 = (com.tencent.liteav.c.a.a) r7;
        r8 = new com.tencent.liteav.d.a;
        r8.<init>();
        r15 = new java.lang.StringBuilder;
        r15.<init>();
        r17 = r1;
        r1 = r3.a;
        r15.append(r1);
        r1 = r7.a;
        r15.append(r1);
        r1 = ".png";
        r15.append(r1);
        r1 = r15.toString();
        r8.a = r1;
        r1 = r3.b;
        r8.b = r1;
        r8.c = r11;
        r8.d = r13;
        r1 = r3.e;
        r8.e = r1;
        r1 = r0.f;
        r1.add(r8);
        r11 = r8.d;
        r6 = r6 + 1;
        r1 = r17;
        goto L_0x006e;
    L_0x00bc:
        r17 = r1;
        r5 = r5 + 1;
        r1 = r17;
        goto L_0x006b;
    L_0x00c3:
        return;
    L_0x00c4:
        return;
    L_0x00c5:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.f.a.a(com.tencent.liteav.d.e):void");
    }

    private com.tencent.liteav.c.a a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("config.json");
        str = b(stringBuilder.toString());
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            com.tencent.liteav.c.a aVar = new com.tencent.liteav.c.a();
            try {
                aVar.a = jSONObject.getString("name");
                aVar.c = jSONObject.getInt(AlbumLoader.COLUMN_COUNT);
                aVar.b = jSONObject.getInt("period");
                aVar.d = jSONObject.getInt("width");
                aVar.e = jSONObject.getInt("height");
                aVar.f = jSONObject.getInt("keyframe");
                JSONArray jSONArray = jSONObject.getJSONArray("frameArray");
                for (int i = 0; i < aVar.c; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    com.tencent.liteav.c.a.a aVar2 = new com.tencent.liteav.c.a.a();
                    aVar2.a = jSONObject2.getString("picture");
                    aVar.g.add(aVar2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return aVar;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043 A:{SYNTHETIC, Splitter:B:20:0x0043} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a A:{SYNTHETIC, Splitter:B:25:0x004a} */
    private java.lang.String b(java.lang.String r6) {
        /*
        r5 = this;
        r0 = "";
        r1 = 0;
        r2 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x003a, all -> 0x0037 }
        r2.<init>(r6);	 Catch:{ IOException -> 0x003a, all -> 0x0037 }
        r6 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x003a, all -> 0x0037 }
        r3 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x003a, all -> 0x0037 }
        r3.<init>(r2);	 Catch:{ IOException -> 0x003a, all -> 0x0037 }
        r6.<init>(r3);	 Catch:{ IOException -> 0x003a, all -> 0x0037 }
    L_0x0012:
        r1 = r6.readLine();	 Catch:{ IOException -> 0x0035 }
        if (r1 == 0) goto L_0x0029;
    L_0x0018:
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0035 }
        r2.<init>();	 Catch:{ IOException -> 0x0035 }
        r2.append(r0);	 Catch:{ IOException -> 0x0035 }
        r2.append(r1);	 Catch:{ IOException -> 0x0035 }
        r1 = r2.toString();	 Catch:{ IOException -> 0x0035 }
        r0 = r1;
        goto L_0x0012;
    L_0x0029:
        r6.close();	 Catch:{ IOException -> 0x0035 }
        r6.close();	 Catch:{ IOException -> 0x0030 }
        goto L_0x0046;
    L_0x0030:
        r6 = move-exception;
        r6.printStackTrace();
        goto L_0x0046;
    L_0x0035:
        r1 = move-exception;
        goto L_0x003e;
    L_0x0037:
        r0 = move-exception;
        r6 = r1;
        goto L_0x0048;
    L_0x003a:
        r6 = move-exception;
        r4 = r1;
        r1 = r6;
        r6 = r4;
    L_0x003e:
        r1.printStackTrace();	 Catch:{ all -> 0x0047 }
        if (r6 == 0) goto L_0x0046;
    L_0x0043:
        r6.close();	 Catch:{ IOException -> 0x0030 }
    L_0x0046:
        return r0;
    L_0x0047:
        r0 = move-exception;
    L_0x0048:
        if (r6 == 0) goto L_0x0052;
    L_0x004a:
        r6.close();	 Catch:{ IOException -> 0x004e }
        goto L_0x0052;
    L_0x004e:
        r6 = move-exception;
        r6.printStackTrace();
    L_0x0052:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.f.a.b(java.lang.String):java.lang.String");
    }

    private b a(b bVar, g gVar) {
        b bVar2 = new b();
        bVar2.b = gVar;
        bVar2.a = bVar.a;
        bVar2.c = bVar.c;
        bVar2.d = bVar.d;
        bVar2.e = bVar.e;
        return bVar2;
    }

    public void c() {
        this.f.clear();
        if (this.e != null) {
            this.e.clear();
        }
        this.e = null;
    }
}
