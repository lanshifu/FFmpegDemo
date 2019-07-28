package com.youdao.sdk.app.other;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class e {
    private d a;
    private a b;
    private b c = null;
    private volatile ConcurrentLinkedQueue<String> d = null;
    private com.youdao.sdk.app.other.j.a e;
    private Context f;

    class a extends Handler {
        a() {
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                if (e.this.a != null && e.this.a.a() >= 16) {
                    e.this.c();
                }
            } else if (message.what == 2) {
                e.this.c();
            } else if (message.what == 3) {
                String str = (String) message.obj;
                if (e.this.a != null) {
                    int a = e.this.a.a();
                    if (a < IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED && e.this.a.a(str) > 0) {
                        if (a >= 16) {
                            e.this.c();
                        } else if (a == 0) {
                            removeMessages(2);
                            sendEmptyMessageDelayed(2, 15000);
                        }
                    }
                }
            }
        }
    }

    class b implements Runnable {
        Handler a;

        private b() {
            this.a = null;
        }

        /* synthetic */ b(e eVar, f fVar) {
            this();
        }

        public void run() {
            Looper.prepare();
            this.a = new a();
            Looper.loop();
        }
    }

    public e(Context context, String str) {
        if (context != null) {
            SQLiteDatabase openOrCreateDatabase;
            this.f = context.getApplicationContext();
            this.b = new a(context);
            try {
                openOrCreateDatabase = context.getApplicationContext().openOrCreateDatabase(str, 0, null);
            } catch (SQLiteException unused) {
                openOrCreateDatabase = null;
            }
            if (openOrCreateDatabase != null) {
                this.a = new d(openOrCreateDatabase);
            }
            this.e = new com.youdao.sdk.app.other.j.a(context.getApplicationContext());
            this.c = new b(this, null);
            new Thread(this.c).start();
        }
    }

    /* JADX WARNING: Missing block: B:26:0x0060, code skipped:
            return;
     */
    private synchronized void c() {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.a;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r6);
        return;
    L_0x0007:
        r0 = r6.a;	 Catch:{ all -> 0x0070 }
        r0 = r0.a();	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r6);
        return;
    L_0x0011:
        r0 = r6.a;	 Catch:{ all -> 0x0070 }
        r0 = r0.b();	 Catch:{ all -> 0x0070 }
        r2 = new java.lang.StringBuffer;	 Catch:{ all -> 0x0070 }
        r2.<init>();	 Catch:{ all -> 0x0070 }
        r3 = r6.a;	 Catch:{ all -> 0x0070 }
        r3 = r3.a(r0);	 Catch:{ all -> 0x0070 }
        r4 = 91;
        r2.append(r4);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        r4 = r3.moveToFirst();	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        if (r4 == 0) goto L_0x0061;
    L_0x002d:
        r4 = 0;
        r5 = r3.getString(r4);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        r2.append(r5);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
    L_0x0035:
        r5 = r3.moveToNext();	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        if (r5 == 0) goto L_0x0048;
    L_0x003b:
        r5 = 44;
        r2.append(r5);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        r5 = r3.getString(r4);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        r2.append(r5);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        goto L_0x0035;
    L_0x0048:
        r4 = 93;
        r2.append(r4);	 Catch:{ Exception -> 0x006b, all -> 0x0066 }
        r3.close();	 Catch:{ all -> 0x0070 }
        r2 = r2.toString();	 Catch:{ all -> 0x0070 }
        r2 = r6.b(r2);	 Catch:{ all -> 0x0070 }
        if (r2 == 0) goto L_0x005f;
    L_0x005a:
        r2 = r6.a;	 Catch:{ all -> 0x0070 }
        r2.b(r0);	 Catch:{ all -> 0x0070 }
    L_0x005f:
        monitor-exit(r6);
        return;
    L_0x0061:
        r3.close();	 Catch:{ all -> 0x0070 }
        monitor-exit(r6);
        return;
    L_0x0066:
        r0 = move-exception;
        r3.close();	 Catch:{ all -> 0x0070 }
        throw r0;	 Catch:{ all -> 0x0070 }
    L_0x006b:
        r3.close();	 Catch:{ all -> 0x0070 }
        monitor-exit(r6);
        return;
    L_0x0070:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youdao.sdk.app.other.e.c():void");
    }

    /* JADX WARNING: Missing block: B:29:0x00e6, code skipped:
            return false;
     */
    private boolean b(java.lang.String r12) {
        /*
        r11 = this;
        r0 = 0;
        if (r12 == 0) goto L_0x00e6;
    L_0x0003:
        r1 = r12.length();
        if (r1 > 0) goto L_0x000b;
    L_0x0009:
        goto L_0x00e6;
    L_0x000b:
        r1 = r11.f;
        if (r1 != 0) goto L_0x0010;
    L_0x000f:
        return r0;
    L_0x0010:
        r1 = new org.apache.http.impl.client.DefaultHttpClient;
        r1.<init>();
        r2 = com.youdao.sdk.app.i.a();	 Catch:{ Exception -> 0x00d8 }
        if (r2 == 0) goto L_0x001e;
    L_0x001b:
        r2 = "http://openapi-sg.youdao.com/log";
        goto L_0x0020;
    L_0x001e:
        r2 = "http://openapi.youdao.com/log";
    L_0x0020:
        r3 = new org.apache.http.client.methods.HttpPost;	 Catch:{ Exception -> 0x00d8 }
        r3.<init>(r2);	 Catch:{ Exception -> 0x00d8 }
        r2 = "Accept-Encoding";
        r4 = "gzip";
        r3.addHeader(r2, r4);	 Catch:{ Exception -> 0x00d8 }
        r2 = new java.util.ArrayList;	 Catch:{ Exception -> 0x00d8 }
        r2.<init>();	 Catch:{ Exception -> 0x00d8 }
        r4 = r11.b;	 Catch:{ Exception -> 0x00d8 }
        r4 = r4.b();	 Catch:{ Exception -> 0x00d8 }
        r5 = new com.youdao.sdk.ydtranslate.TranslateSdk;	 Catch:{ Exception -> 0x00d8 }
        r5.<init>();	 Catch:{ Exception -> 0x00d8 }
        r6 = new java.util.Random;	 Catch:{ Exception -> 0x00d8 }
        r6.<init>();	 Catch:{ Exception -> 0x00d8 }
        r7 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r6 = r6.nextInt(r7);	 Catch:{ Exception -> 0x00d8 }
        r7 = "";
        r8 = r11.f;	 Catch:{ Exception -> 0x00d8 }
        r9 = com.youdao.sdk.app.i.a;	 Catch:{ Exception -> 0x00d8 }
        r10 = java.lang.String.valueOf(r6);	 Catch:{ Exception -> 0x00d8 }
        r5 = r5.sign(r8, r9, r7, r10);	 Catch:{ Exception -> 0x00d8 }
        r8 = "q";
        r4.put(r8, r7);	 Catch:{ Exception -> 0x00d8 }
        r7 = "salt";
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Exception -> 0x00d8 }
        r4.put(r7, r6);	 Catch:{ Exception -> 0x00d8 }
        r6 = "signType";
        r7 = "v1";
        r4.put(r6, r7);	 Catch:{ Exception -> 0x00d8 }
        r6 = "sign";
        r4.put(r6, r5);	 Catch:{ Exception -> 0x00d8 }
        r5 = "batchLog";
        r4.put(r5, r12);	 Catch:{ Exception -> 0x00d8 }
        r12 = "method";
        r5 = "batchLog";
        r4.put(r12, r5);	 Catch:{ Exception -> 0x00d8 }
        r12 = "appKey";
        r5 = com.youdao.sdk.app.i.a;	 Catch:{ Exception -> 0x00d8 }
        r4.put(r12, r5);	 Catch:{ Exception -> 0x00d8 }
        r12 = r11.a(r4);	 Catch:{ Exception -> 0x00d8 }
        r12 = com.youdao.sdk.app.b.a(r12);	 Catch:{ Exception -> 0x00d8 }
        r4 = new org.apache.http.message.BasicNameValuePair;	 Catch:{ Exception -> 0x00d8 }
        r5 = "s";
        r6 = r12[r0];	 Catch:{ Exception -> 0x00d8 }
        r4.<init>(r5, r6);	 Catch:{ Exception -> 0x00d8 }
        r2.add(r4);	 Catch:{ Exception -> 0x00d8 }
        r4 = new org.apache.http.message.BasicNameValuePair;	 Catch:{ Exception -> 0x00d8 }
        r5 = "et";
        r6 = 1;
        r12 = r12[r6];	 Catch:{ Exception -> 0x00d8 }
        r4.<init>(r5, r12);	 Catch:{ Exception -> 0x00d8 }
        r2.add(r4);	 Catch:{ Exception -> 0x00d8 }
        r12 = new org.apache.http.client.entity.UrlEncodedFormEntity;	 Catch:{ Exception -> 0x00d8 }
        r4 = "UTF-8";
        r12.<init>(r2, r4);	 Catch:{ Exception -> 0x00d8 }
        r2 = "Content-Type";
        r4 = "application/x-www-form-urlencoded; charset=utf-8";
        r3.setHeader(r2, r4);	 Catch:{ Exception -> 0x00d8 }
        r3.setEntity(r12);	 Catch:{ Exception -> 0x00d8 }
        r2 = r11.e;	 Catch:{ Exception -> 0x00d8 }
        r2.a(r1);	 Catch:{ Exception -> 0x00d8 }
        r2 = r1.execute(r3);	 Catch:{ Exception -> 0x00d8 }
        r2 = r2.getStatusLine();	 Catch:{ Exception -> 0x00d8 }
        r2 = r2.getStatusCode();	 Catch:{ Exception -> 0x00d8 }
        r2 = r2 / 100;
        r3 = 2;
        if (r2 != r3) goto L_0x00cb;
    L_0x00ca:
        r0 = 1;
    L_0x00cb:
        r12.consumeContent();	 Catch:{ Exception -> 0x00d8 }
    L_0x00ce:
        r12 = r1.getConnectionManager();	 Catch:{ Exception -> 0x00dd }
        r12.shutdown();	 Catch:{ Exception -> 0x00dd }
        goto L_0x00dd;
    L_0x00d6:
        r12 = move-exception;
        goto L_0x00de;
    L_0x00d8:
        r12 = move-exception;
        r12.printStackTrace();	 Catch:{ all -> 0x00d6 }
        goto L_0x00ce;
    L_0x00dd:
        return r0;
    L_0x00de:
        r0 = r1.getConnectionManager();	 Catch:{ Exception -> 0x00e5 }
        r0.shutdown();	 Catch:{ Exception -> 0x00e5 }
    L_0x00e5:
        throw r12;
    L_0x00e6:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youdao.sdk.app.other.e.b(java.lang.String):boolean");
    }

    /* Access modifiers changed, original: 0000 */
    public String a(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (!TextUtils.isEmpty(str2)) {
                stringBuilder.append(str);
                stringBuilder.append("=");
                stringBuilder.append(Uri.encode(str2));
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }

    public boolean a(String str) {
        if ((this.a != null ? this.a.a() : 0) >= IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED || this.c == null || this.c.a == null) {
            return false;
        }
        return this.c.a.sendMessage(this.c.a.obtainMessage(3, str));
    }

    public void a() {
        this.a.c();
    }

    public void b() {
        if (this.c != null && this.c.a != null) {
            this.c.a.sendEmptyMessage(1);
        }
    }
}
