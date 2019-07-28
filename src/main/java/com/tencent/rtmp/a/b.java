package com.tencent.rtmp.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.yalantis.ucrop.view.CropImageView;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: TXImageSpriteImpl */
public class b implements a {
    private final Options a;
    private HandlerThread b;
    private Handler c;
    private List<c> d;
    private Map<String, BitmapRegionDecoder> e;

    /* compiled from: TXImageSpriteImpl */
    private static class a implements Runnable {
        private WeakReference<b> a;
        private String b;

        public a(b bVar, String str) {
            this.a = new WeakReference(bVar);
            this.b = str;
        }

        private float a(String str) {
            String str2;
            String[] split = str.split(":");
            String str3 = null;
            if (split.length == 3) {
                str3 = split[0];
                str2 = split[1];
                str = split[2];
            } else if (split.length == 2) {
                str2 = split[0];
                str = split[1];
            } else if (split.length == 1) {
                str = split[0];
                str2 = null;
            } else {
                str = null;
                str2 = str;
            }
            float f = CropImageView.DEFAULT_ASPECT_RATIO;
            if (str3 != null) {
                f = CropImageView.DEFAULT_ASPECT_RATIO + (Float.valueOf(CropImageView.DEFAULT_ASPECT_RATIO).floatValue() * 3600.0f);
            }
            if (str2 != null) {
                f += Float.valueOf(str2).floatValue() * 60.0f;
            }
            return str != null ? f + Float.valueOf(str).floatValue() : f;
        }

        /* JADX WARNING: Removed duplicated region for block: B:62:0x010e A:{SYNTHETIC, Splitter:B:62:0x010e} */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x0103 A:{SYNTHETIC, Splitter:B:56:0x0103} */
        public void run() {
            /*
            r10 = this;
            r0 = r10.a;
            r0 = r0.get();
            r0 = (com.tencent.rtmp.a.b) r0;
            r1 = 0;
            r2 = r10.b;	 Catch:{ IOException -> 0x00fd }
            r2 = r0.a(r2);	 Catch:{ IOException -> 0x00fd }
            if (r2 != 0) goto L_0x0012;
        L_0x0011:
            return;
        L_0x0012:
            r3 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x00fd }
            r4 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x00fd }
            r4.<init>(r2);	 Catch:{ IOException -> 0x00fd }
            r3.<init>(r4);	 Catch:{ IOException -> 0x00fd }
            r1 = r3.readLine();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            if (r1 == 0) goto L_0x00e0;
        L_0x0022:
            r2 = r1.length();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            if (r2 == 0) goto L_0x00e0;
        L_0x0028:
            r2 = "WEBVTT";
            r1 = r1.contains(r2);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            if (r1 != 0) goto L_0x0032;
        L_0x0030:
            goto L_0x00e0;
        L_0x0032:
            r1 = r3.readLine();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            if (r1 == 0) goto L_0x00da;
        L_0x0038:
            r2 = "-->";
            r2 = r1.contains(r2);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            if (r2 == 0) goto L_0x00da;
        L_0x0040:
            r2 = " --> ";
            r2 = r1.split(r2);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = r2.length;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r5 = 2;
            if (r4 == r5) goto L_0x004c;
        L_0x004a:
            goto L_0x00da;
        L_0x004c:
            r4 = r3.readLine();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6 = new com.tencent.rtmp.a.c;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6.<init>();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r7 = 0;
            r8 = r2[r7];	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r8 = r10.a(r8);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6.a = r8;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r8 = 1;
            r2 = r2[r8];	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r2 = r10.a(r2);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6.b = r2;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6.c = r4;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r2 = "#";
            r2 = r4.indexOf(r2);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r9 = -1;
            if (r2 == r9) goto L_0x0078;
        L_0x0072:
            r2 = r4.substring(r7, r2);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6.d = r2;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
        L_0x0078:
            r2 = "=";
            r2 = r4.indexOf(r2);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            if (r2 == r9) goto L_0x00cb;
        L_0x0080:
            r2 = r2 + 1;
            r9 = r4.length();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            if (r2 >= r9) goto L_0x00cb;
        L_0x0088:
            r9 = r4.length();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r2 = r4.substring(r2, r9);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = ",";
            r2 = r2.split(r4);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = r2.length;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r9 = 4;
            if (r4 != r9) goto L_0x00cb;
        L_0x009a:
            r4 = r2[r7];	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = java.lang.Integer.valueOf(r4);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = r4.intValue();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6.e = r4;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = r2[r8];	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = java.lang.Integer.valueOf(r4);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = r4.intValue();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6.f = r4;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = r2[r5];	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = java.lang.Integer.valueOf(r4);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = r4.intValue();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6.g = r4;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r4 = 3;
            r2 = r2[r4];	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r2 = java.lang.Integer.valueOf(r2);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r2 = r2.intValue();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r6.h = r2;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
        L_0x00cb:
            if (r0 == 0) goto L_0x00da;
        L_0x00cd:
            r2 = r0.d;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            if (r2 == 0) goto L_0x00da;
        L_0x00d3:
            r2 = r0.d;	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            r2.add(r6);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
        L_0x00da:
            if (r1 != 0) goto L_0x0032;
        L_0x00dc:
            r3.close();	 Catch:{ IOException -> 0x0107 }
            goto L_0x010b;
        L_0x00e0:
            r1 = "TXImageSprite";
            r2 = "DownloadAndParseVTTFileTask : getVTT File Error!";
            com.tencent.liteav.basic.log.TXCLog.e(r1, r2);	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
            if (r0 == 0) goto L_0x00ec;
        L_0x00e9:
            r0.b();	 Catch:{ IOException -> 0x00f7, all -> 0x00f5 }
        L_0x00ec:
            r3.close();	 Catch:{ IOException -> 0x00f0 }
            goto L_0x00f4;
        L_0x00f0:
            r0 = move-exception;
            r0.printStackTrace();
        L_0x00f4:
            return;
        L_0x00f5:
            r0 = move-exception;
            goto L_0x010c;
        L_0x00f7:
            r0 = move-exception;
            r1 = r3;
            goto L_0x00fe;
        L_0x00fa:
            r0 = move-exception;
            r3 = r1;
            goto L_0x010c;
        L_0x00fd:
            r0 = move-exception;
        L_0x00fe:
            r0.printStackTrace();	 Catch:{ all -> 0x00fa }
            if (r1 == 0) goto L_0x010b;
        L_0x0103:
            r1.close();	 Catch:{ IOException -> 0x0107 }
            goto L_0x010b;
        L_0x0107:
            r0 = move-exception;
            r0.printStackTrace();
        L_0x010b:
            return;
        L_0x010c:
            if (r3 == 0) goto L_0x0116;
        L_0x010e:
            r3.close();	 Catch:{ IOException -> 0x0112 }
            goto L_0x0116;
        L_0x0112:
            r1 = move-exception;
            r1.printStackTrace();
        L_0x0116:
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.a.b$a.run():void");
        }
    }

    /* compiled from: TXImageSpriteImpl */
    private static class b implements Runnable {
        private WeakReference<b> a;
        private String b;
        private String c;

        public b(b bVar, String str, String str2) {
            this.a = new WeakReference(bVar);
            this.b = str;
            this.c = str2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:39:? A:{SYNTHETIC, RETURN} */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x005b A:{SYNTHETIC, Splitter:B:26:0x005b} */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0066 A:{SYNTHETIC, Splitter:B:31:0x0066} */
        public void run() {
            /*
            r6 = this;
            r0 = r6.a;
            r0 = r0.get();
            r0 = (com.tencent.rtmp.a.b) r0;
            r1 = r6.a;
            if (r1 == 0) goto L_0x006f;
        L_0x000c:
            if (r0 == 0) goto L_0x006f;
        L_0x000e:
            r1 = 0;
            r2 = r6.c;	 Catch:{ IOException -> 0x0055 }
            r2 = r0.a(r2);	 Catch:{ IOException -> 0x0055 }
            r1 = r6.c;	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            r3 = "/";
            r1 = r1.lastIndexOf(r3);	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            r3 = -1;
            if (r1 == r3) goto L_0x0047;
        L_0x0020:
            r3 = 1;
            r1 = r1 + r3;
            r4 = r6.c;	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            r4 = r4.length();	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            if (r1 >= r4) goto L_0x0047;
        L_0x002a:
            r4 = r6.c;	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            r5 = r6.c;	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            r5 = r5.length();	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            r1 = r4.substring(r1, r5);	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            r4 = r0.e;	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            if (r4 == 0) goto L_0x0047;
        L_0x003c:
            r0 = r0.e;	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            r3 = android.graphics.BitmapRegionDecoder.newInstance(r2, r3);	 Catch:{ IOException -> 0x004f, all -> 0x004d }
            r0.put(r1, r3);	 Catch:{ IOException -> 0x004f, all -> 0x004d }
        L_0x0047:
            if (r2 == 0) goto L_0x006f;
        L_0x0049:
            r2.close();	 Catch:{ IOException -> 0x005f }
            goto L_0x006f;
        L_0x004d:
            r0 = move-exception;
            goto L_0x0064;
        L_0x004f:
            r0 = move-exception;
            r1 = r2;
            goto L_0x0056;
        L_0x0052:
            r0 = move-exception;
            r2 = r1;
            goto L_0x0064;
        L_0x0055:
            r0 = move-exception;
        L_0x0056:
            r0.printStackTrace();	 Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x006f;
        L_0x005b:
            r1.close();	 Catch:{ IOException -> 0x005f }
            goto L_0x006f;
        L_0x005f:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x006f;
        L_0x0064:
            if (r2 == 0) goto L_0x006e;
        L_0x0066:
            r2.close();	 Catch:{ IOException -> 0x006a }
            goto L_0x006e;
        L_0x006a:
            r1 = move-exception;
            r1.printStackTrace();
        L_0x006e:
            throw r0;
        L_0x006f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.a.b$b.run():void");
        }
    }

    public b() {
        this.a = new Options();
        this.d = new ArrayList();
        this.d = Collections.synchronizedList(this.d);
        this.e = new HashMap();
        this.e = Collections.synchronizedMap(this.e);
    }

    public void setVTTUrlAndImageUrls(String str, List<String> list) {
        if (TextUtils.isEmpty(str)) {
            TXCLog.e("TXImageSprite", "setVTTUrlAndImageUrls: vttUrl can't be null!");
            return;
        }
        b();
        a();
        this.c.post(new a(this, str));
        if (!(list == null || list.size() == 0)) {
            for (String bVar : list) {
                this.c.post(new b(this, str, bVar));
            }
        }
    }

    public Bitmap getThumbnail(float f) {
        if (this.d.size() == 0) {
            return null;
        }
        c a = a(0, this.d.size() - 1, f);
        if (a == null) {
            return null;
        }
        BitmapRegionDecoder bitmapRegionDecoder = (BitmapRegionDecoder) this.e.get(a.d);
        if (bitmapRegionDecoder == null) {
            return null;
        }
        Rect rect = new Rect();
        rect.left = a.e;
        rect.top = a.f;
        rect.right = a.e + a.g;
        rect.bottom = a.f + a.h;
        return bitmapRegionDecoder.decodeRegion(rect, this.a);
    }

    public void release() {
        b();
        if (this.b != null && this.c != null) {
            if (VERSION.SDK_INT >= 18) {
                this.b.quitSafely();
            } else {
                this.b.quit();
            }
            this.c = null;
            this.b = null;
        }
    }

    private c a(int i, int i2, float f) {
        int i3 = ((i2 - i) / 2) + i;
        if (((c) this.d.get(i3)).a <= f && ((c) this.d.get(i3)).b > f) {
            return (c) this.d.get(i3);
        }
        if (i >= i2) {
            return (c) this.d.get(i);
        }
        if (f >= ((c) this.d.get(i3)).b) {
            return a(i3 + 1, i2, f);
        }
        return f < ((c) this.d.get(i3)).a ? a(i, i3 - 1, f) : null;
    }

    private void a() {
        if (this.b == null) {
            this.b = new HandlerThread("SuperVodThumbnailsWorkThread");
            this.b.start();
            this.c = new Handler(this.b.getLooper());
        }
    }

    private void b() {
        if (this.c != null) {
            TXCLog.i("TXImageSprite", " remove all tasks!");
            this.c.removeCallbacksAndMessages(null);
            this.c.post(new Runnable() {
                public void run() {
                    if (b.this.d != null) {
                        b.this.d.clear();
                    }
                    if (b.this.e != null) {
                        for (BitmapRegionDecoder bitmapRegionDecoder : b.this.e.values()) {
                            if (bitmapRegionDecoder != null) {
                                bitmapRegionDecoder.recycle();
                            }
                        }
                        b.this.e.clear();
                    }
                }
            });
        }
    }

    private InputStream a(String str) throws IOException {
        URLConnection openConnection = new URL(str).openConnection();
        openConnection.connect();
        openConnection.getInputStream();
        openConnection.setConnectTimeout(15000);
        openConnection.setReadTimeout(15000);
        return openConnection.getInputStream();
    }
}
