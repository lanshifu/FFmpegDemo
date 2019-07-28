package com.tencent.liteav.screencapture;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjection.Callback;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Surface;
import com.tencent.rtmp.video.TXScreenCapture.TXScreenCaptureAssistantActivity;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@TargetApi(21)
/* compiled from: TXCScreenCaptureImplSingleton */
public class b {
    private static final String b = "b";
    private static b c = new b();
    Callback a;
    private HashMap<Surface, VirtualDisplay> d;
    private Context e;
    private MediaProjectionManager f;
    private MediaProjection g;
    private HashSet<b> h;
    private int i;
    private Handler j;
    private HashSet<Object> k;
    private boolean l;
    private WeakReference<com.tencent.liteav.basic.d.a> m;
    private HandlerThread n;
    private Handler o;
    private int p;
    private HashSet<a> q;
    private Runnable r;
    private BroadcastReceiver s;

    /* compiled from: TXCScreenCaptureImplSingleton */
    public interface a {
        void a(int i);
    }

    /* compiled from: TXCScreenCaptureImplSingleton */
    private class b {
        Surface a;
        int b;
        int c;

        private b() {
        }

        /* synthetic */ b(b bVar, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private b() {
        this.d = new HashMap();
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = new HashSet();
        this.i = 1;
        this.j = null;
        this.k = new HashSet();
        this.l = false;
        this.m = null;
        this.n = new HandlerThread("TXCScreenCaptureImplSingleton");
        this.o = null;
        this.p = 0;
        this.q = new HashSet();
        this.r = new Runnable() {
            /* JADX WARNING: Missing block: B:9:0x0027, code skipped:
            if (r0 == 0) goto L_0x007b;
     */
            /* JADX WARNING: Missing block: B:11:0x002a, code skipped:
            if (r0 != 2) goto L_0x002d;
     */
            /* JADX WARNING: Missing block: B:13:0x0035, code skipped:
            if (com.tencent.liteav.screencapture.b.c(r4.a) == 90) goto L_0x0075;
     */
            /* JADX WARNING: Missing block: B:14:0x0037, code skipped:
            r0 = com.tencent.liteav.screencapture.b.c();
            r2 = new java.lang.StringBuilder();
            r2.append("ORIENTATION_LANDSCAPE, mDelegateSet size = ");
            r2.append(com.tencent.liteav.screencapture.b.d(r4.a).size());
            com.tencent.liteav.basic.log.TXCLog.d(r0, r2.toString());
            r0 = com.tencent.liteav.screencapture.b.d(r4.a).iterator();
     */
            /* JADX WARNING: Missing block: B:16:0x0067, code skipped:
            if (r0.hasNext() == false) goto L_0x0075;
     */
            /* JADX WARNING: Missing block: B:17:0x0069, code skipped:
            r2 = (com.tencent.liteav.screencapture.b.a) r0.next();
     */
            /* JADX WARNING: Missing block: B:18:0x006f, code skipped:
            if (r2 == null) goto L_0x0063;
     */
            /* JADX WARNING: Missing block: B:19:0x0071, code skipped:
            r2.a(90);
     */
            /* JADX WARNING: Missing block: B:20:0x0075, code skipped:
            com.tencent.liteav.screencapture.b.a(r4.a, 90);
     */
            /* JADX WARNING: Missing block: B:21:0x007a, code skipped:
            return;
     */
            /* JADX WARNING: Missing block: B:23:0x0082, code skipped:
            if (com.tencent.liteav.screencapture.b.c(r4.a) == 0) goto L_0x00c2;
     */
            /* JADX WARNING: Missing block: B:24:0x0084, code skipped:
            r0 = com.tencent.liteav.screencapture.b.c();
            r2 = new java.lang.StringBuilder();
            r2.append("ORIENTATION_PORTRAIT, mDelegateSet size = ");
            r2.append(com.tencent.liteav.screencapture.b.d(r4.a).size());
            com.tencent.liteav.basic.log.TXCLog.d(r0, r2.toString());
            r0 = com.tencent.liteav.screencapture.b.d(r4.a).iterator();
     */
            /* JADX WARNING: Missing block: B:26:0x00b4, code skipped:
            if (r0.hasNext() == false) goto L_0x00c2;
     */
            /* JADX WARNING: Missing block: B:27:0x00b6, code skipped:
            r2 = (com.tencent.liteav.screencapture.b.a) r0.next();
     */
            /* JADX WARNING: Missing block: B:28:0x00bc, code skipped:
            if (r2 == null) goto L_0x00b0;
     */
            /* JADX WARNING: Missing block: B:29:0x00be, code skipped:
            r2.a(0);
     */
            /* JADX WARNING: Missing block: B:30:0x00c2, code skipped:
            com.tencent.liteav.screencapture.b.a(r4.a, 0);
     */
            /* JADX WARNING: Missing block: B:31:0x00c7, code skipped:
            return;
     */
            public void run() {
                /*
                r4 = this;
                r0 = com.tencent.liteav.screencapture.b.this;
                r0.f();
                monitor-enter(r4);
                r0 = com.tencent.liteav.screencapture.b.this;	 Catch:{ all -> 0x00c8 }
                r0 = r0.e;	 Catch:{ all -> 0x00c8 }
                if (r0 != 0) goto L_0x0010;
            L_0x000e:
                monitor-exit(r4);	 Catch:{ all -> 0x00c8 }
                return;
            L_0x0010:
                r0 = com.tencent.liteav.screencapture.b.this;	 Catch:{ all -> 0x00c8 }
                r0 = r0.e;	 Catch:{ all -> 0x00c8 }
                r1 = "window";
                r0 = r0.getSystemService(r1);	 Catch:{ all -> 0x00c8 }
                r0 = (android.view.WindowManager) r0;	 Catch:{ all -> 0x00c8 }
                r0 = r0.getDefaultDisplay();	 Catch:{ all -> 0x00c8 }
                r0 = r0.getRotation();	 Catch:{ all -> 0x00c8 }
                monitor-exit(r4);	 Catch:{ all -> 0x00c8 }
                if (r0 == 0) goto L_0x007b;
            L_0x0029:
                r1 = 2;
                if (r0 != r1) goto L_0x002d;
            L_0x002c:
                goto L_0x007b;
            L_0x002d:
                r0 = com.tencent.liteav.screencapture.b.this;
                r0 = r0.p;
                r1 = 90;
                if (r0 == r1) goto L_0x0075;
            L_0x0037:
                r0 = com.tencent.liteav.screencapture.b.b;
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r3 = "ORIENTATION_LANDSCAPE, mDelegateSet size = ";
                r2.append(r3);
                r3 = com.tencent.liteav.screencapture.b.this;
                r3 = r3.q;
                r3 = r3.size();
                r2.append(r3);
                r2 = r2.toString();
                com.tencent.liteav.basic.log.TXCLog.d(r0, r2);
                r0 = com.tencent.liteav.screencapture.b.this;
                r0 = r0.q;
                r0 = r0.iterator();
            L_0x0063:
                r2 = r0.hasNext();
                if (r2 == 0) goto L_0x0075;
            L_0x0069:
                r2 = r0.next();
                r2 = (com.tencent.liteav.screencapture.b.a) r2;
                if (r2 == 0) goto L_0x0063;
            L_0x0071:
                r2.a(r1);
                goto L_0x0063;
            L_0x0075:
                r0 = com.tencent.liteav.screencapture.b.this;
                r0.p = r1;
                return;
            L_0x007b:
                r0 = com.tencent.liteav.screencapture.b.this;
                r0 = r0.p;
                r1 = 0;
                if (r0 == 0) goto L_0x00c2;
            L_0x0084:
                r0 = com.tencent.liteav.screencapture.b.b;
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r3 = "ORIENTATION_PORTRAIT, mDelegateSet size = ";
                r2.append(r3);
                r3 = com.tencent.liteav.screencapture.b.this;
                r3 = r3.q;
                r3 = r3.size();
                r2.append(r3);
                r2 = r2.toString();
                com.tencent.liteav.basic.log.TXCLog.d(r0, r2);
                r0 = com.tencent.liteav.screencapture.b.this;
                r0 = r0.q;
                r0 = r0.iterator();
            L_0x00b0:
                r2 = r0.hasNext();
                if (r2 == 0) goto L_0x00c2;
            L_0x00b6:
                r2 = r0.next();
                r2 = (com.tencent.liteav.screencapture.b.a) r2;
                if (r2 == 0) goto L_0x00b0;
            L_0x00be:
                r2.a(r1);
                goto L_0x00b0;
            L_0x00c2:
                r0 = com.tencent.liteav.screencapture.b.this;
                r0.p = r1;
                return;
            L_0x00c8:
                r0 = move-exception;
                monitor-exit(r4);	 Catch:{ all -> 0x00c8 }
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.screencapture.b$AnonymousClass1.run():void");
            }
        };
        this.a = new Callback() {
            public void onStop() {
                if (b.this.l) {
                    b.this.l = false;
                }
            }
        };
        this.s = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getAction().equalsIgnoreCase("TXScreenCapture.OnAssistantActivityResult")) {
                    b.this.a(intent.getIntExtra("TXScreenCapture.RequestCode", 0), intent.getIntExtra("TXScreenCapture.ResultCode", 0), (Intent) intent.getParcelableExtra("TXScreenCapture.ResultData"));
                }
            }
        };
        this.j = new Handler(Looper.getMainLooper());
        this.n.start();
        this.o = new Handler(this.n.getLooper());
    }

    public static b a() {
        return c;
    }

    public void a(com.tencent.liteav.basic.d.a aVar) {
        if (aVar == null) {
            this.m = null;
        } else {
            this.m = new WeakReference(aVar);
        }
    }

    /* JADX WARNING: Missing block: B:12:0x0024, code skipped:
            return;
     */
    public void a(android.content.Context r2) {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = r1.e;	 Catch:{ all -> 0x0025 }
        if (r0 == r2) goto L_0x0023;
    L_0x0005:
        r1.b();	 Catch:{ all -> 0x0025 }
        r0 = 0;
        r1.f = r0;	 Catch:{ all -> 0x0025 }
        r1.e = r2;	 Catch:{ all -> 0x0025 }
        r2 = r1.e;	 Catch:{ all -> 0x0025 }
        if (r2 != 0) goto L_0x0013;
    L_0x0011:
        monitor-exit(r1);	 Catch:{ all -> 0x0025 }
        return;
    L_0x0013:
        r2 = r1.f;	 Catch:{ all -> 0x0025 }
        if (r2 != 0) goto L_0x0023;
    L_0x0017:
        r2 = r1.e;	 Catch:{ all -> 0x0025 }
        r0 = "media_projection";
        r2 = r2.getSystemService(r0);	 Catch:{ all -> 0x0025 }
        r2 = (android.media.projection.MediaProjectionManager) r2;	 Catch:{ all -> 0x0025 }
        r1.f = r2;	 Catch:{ all -> 0x0025 }
    L_0x0023:
        monitor-exit(r1);	 Catch:{ all -> 0x0025 }
        return;
    L_0x0025:
        r2 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0025 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.screencapture.b.a(android.content.Context):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0050 A:{Catch:{ Exception -> 0x004a }} */
    @android.annotation.TargetApi(21)
    public boolean a(android.view.Surface r13, int r14, int r15) {
        /*
        r12 = this;
        monitor-enter(r12);
        r0 = 0;
        r1 = r12.i;	 Catch:{ Exception -> 0x004a }
        r2 = 3;
        if (r1 == r2) goto L_0x0024;
    L_0x0007:
        r1 = r12.i;	 Catch:{ Exception -> 0x004a }
        r3 = 4;
        if (r1 != r3) goto L_0x000d;
    L_0x000c:
        goto L_0x0024;
    L_0x000d:
        r1 = new com.tencent.liteav.screencapture.b$b;	 Catch:{ Exception -> 0x004a }
        r2 = 0;
        r1.<init>(r12, r2);	 Catch:{ Exception -> 0x004a }
        r1.c = r15;	 Catch:{ Exception -> 0x004a }
        r1.b = r14;	 Catch:{ Exception -> 0x004a }
        r1.a = r13;	 Catch:{ Exception -> 0x004a }
        r13 = r12.h;	 Catch:{ Exception -> 0x004a }
        r13.add(r1);	 Catch:{ Exception -> 0x004a }
        r13 = r12.h();	 Catch:{ Exception -> 0x004a }
        r0 = r13;
        goto L_0x004e;
    L_0x0024:
        r1 = r12.g;	 Catch:{ Exception -> 0x004a }
        r1 = r12.g;	 Catch:{ Exception -> 0x004a }
        if (r1 == 0) goto L_0x004e;
    L_0x002a:
        r1 = 1;
        r12.l = r1;	 Catch:{ Exception -> 0x004a }
        r3 = r12.g;	 Catch:{ Exception -> 0x004a }
        r4 = "TXCScreenCapture";
        r7 = 1;
        r8 = 1;
        r10 = 0;
        r11 = 0;
        r5 = r14;
        r6 = r15;
        r9 = r13;
        r14 = r3.createVirtualDisplay(r4, r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x004a }
        if (r14 != 0) goto L_0x003f;
    L_0x003e:
        goto L_0x004e;
    L_0x003f:
        r12.i = r2;	 Catch:{ Exception -> 0x004a }
        r15 = r12.d;	 Catch:{ Exception -> 0x004a }
        r15.put(r13, r14);	 Catch:{ Exception -> 0x004a }
        r0 = 1;
        goto L_0x004e;
    L_0x0048:
        r13 = move-exception;
        goto L_0x005b;
    L_0x004a:
        r13 = move-exception;
        r13.printStackTrace();	 Catch:{ all -> 0x0048 }
    L_0x004e:
        if (r0 != 0) goto L_0x0059;
    L_0x0050:
        r13 = r12.m;	 Catch:{ all -> 0x0048 }
        r14 = -1308; // 0xfffffffffffffae4 float:NaN double:NaN;
        r15 = "录屏失败";
        com.tencent.liteav.basic.util.b.a(r13, r14, r15);	 Catch:{ all -> 0x0048 }
    L_0x0059:
        monitor-exit(r12);	 Catch:{ all -> 0x0048 }
        return r0;
    L_0x005b:
        monitor-exit(r12);	 Catch:{ all -> 0x0048 }
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.screencapture.b.a(android.view.Surface, int, int):boolean");
    }

    public void a(Surface surface) {
        synchronized (this) {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (!(bVar == null || bVar.a == null || bVar.b == 0)) {
                    if (bVar.c != 0) {
                        if (bVar.a == surface) {
                            this.h.remove(bVar);
                            break;
                        }
                    }
                }
            }
            if (this.d.containsKey(surface)) {
                ((VirtualDisplay) this.d.get(surface)).release();
                this.d.remove(surface);
                d();
                return;
            }
        }
    }

    public void b() {
        synchronized (this) {
            e();
        }
    }

    private void d() {
        this.i = 4;
        this.j.postDelayed(new Runnable() {
            public void run() {
                synchronized (b.this) {
                    if (b.this.d.size() == 0) {
                        b.this.i();
                    }
                }
            }
        }, 1000);
    }

    private void e() {
        for (VirtualDisplay virtualDisplay : this.d.values()) {
            if (virtualDisplay != null) {
                virtualDisplay.release();
            }
        }
        this.d.clear();
        this.k.clear();
        i();
    }

    private void f() {
        this.o.postDelayed(this.r, 50);
    }

    private void g() {
        this.o.removeCallbacks(this.r);
    }

    public void a(final a aVar) {
        this.j.post(new Runnable() {
            public void run() {
                b.this.q.add(aVar);
            }
        });
    }

    public void b(final a aVar) {
        this.j.post(new Runnable() {
            public void run() {
                b.this.q.remove(aVar);
            }
        });
    }

    @TargetApi(21)
    private boolean h() {
        if (this.i != 1) {
            return true;
        }
        if (this.e == null || this.f == null) {
            return false;
        }
        f();
        this.i = 2;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("TXScreenCapture.OnAssistantActivityResult");
        this.e.registerReceiver(this.s, intentFilter);
        Intent intent = new Intent(this.e, TXScreenCaptureAssistantActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("TXScreenCapture.ScreenCaptureIntent", this.f.createScreenCaptureIntent());
        this.e.startActivity(intent);
        return true;
    }

    @TargetApi(21)
    private void i() {
        if (this.k.isEmpty()) {
            this.l = false;
            if (this.g != null) {
                this.g.stop();
                this.g.unregisterCallback(this.a);
            }
            try {
                if (this.e != null) {
                    this.e.unregisterReceiver(this.s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.g = null;
            this.i = 1;
            g();
        }
    }

    /* JADX WARNING: Missing block: B:44:0x009e, code skipped:
            if (r11.m == null) goto L_0x00b1;
     */
    /* JADX WARNING: Missing block: B:45:0x00a0, code skipped:
            new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.tencent.liteav.screencapture.b.AnonymousClass6(r11));
     */
    /* JADX WARNING: Missing block: B:46:0x00b1, code skipped:
            return;
     */
    @android.annotation.TargetApi(21)
    private void a(int r12, int r13, android.content.Intent r14) {
        /*
        r11 = this;
        r0 = -1308; // 0xfffffffffffffae4 float:NaN double:NaN;
        r1 = 1;
        monitor-enter(r11);	 Catch:{ Exception -> 0x00bf }
        r2 = r11.e;	 Catch:{ Exception -> 0x0013 }
        if (r2 == 0) goto L_0x0017;
    L_0x0008:
        r2 = r11.e;	 Catch:{ Exception -> 0x0013 }
        r3 = r11.s;	 Catch:{ Exception -> 0x0013 }
        r2.unregisterReceiver(r3);	 Catch:{ Exception -> 0x0013 }
        goto L_0x0017;
    L_0x0010:
        r12 = move-exception;
        goto L_0x00bd;
    L_0x0013:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0010 }
    L_0x0017:
        r2 = 1001; // 0x3e9 float:1.403E-42 double:4.946E-321;
        if (r12 != r2) goto L_0x00b2;
    L_0x001b:
        r12 = -1;
        if (r13 != r12) goto L_0x00b2;
    L_0x001e:
        r12 = r11.i;	 Catch:{ all -> 0x0010 }
        r2 = 2;
        if (r12 == r2) goto L_0x0025;
    L_0x0023:
        goto L_0x00b2;
    L_0x0025:
        r12 = r11.f;	 Catch:{ all -> 0x0010 }
        r12 = r12.getMediaProjection(r13, r14);	 Catch:{ all -> 0x0010 }
        r11.g = r12;	 Catch:{ all -> 0x0010 }
        r12 = r11.g;	 Catch:{ all -> 0x0010 }
        r13 = r11.a;	 Catch:{ all -> 0x0010 }
        r14 = r11.j;	 Catch:{ all -> 0x0010 }
        r12.registerCallback(r13, r14);	 Catch:{ all -> 0x0010 }
        r11.l = r1;	 Catch:{ all -> 0x0010 }
        r12 = r11.h;	 Catch:{ all -> 0x0010 }
        r12 = r12.size();	 Catch:{ all -> 0x0010 }
        if (r12 != 0) goto L_0x004b;
    L_0x0040:
        r12 = r11.m;	 Catch:{ all -> 0x0010 }
        r13 = "录屏失败";
        com.tencent.liteav.basic.util.b.a(r12, r0, r13);	 Catch:{ all -> 0x0010 }
        r11.i = r1;	 Catch:{ all -> 0x0010 }
        monitor-exit(r11);	 Catch:{ all -> 0x0010 }
        return;
    L_0x004b:
        r12 = r11.h;	 Catch:{ all -> 0x0010 }
        r12 = r12.iterator();	 Catch:{ all -> 0x0010 }
    L_0x0051:
        r13 = r12.hasNext();	 Catch:{ all -> 0x0010 }
        if (r13 == 0) goto L_0x0093;
    L_0x0057:
        r13 = r12.next();	 Catch:{ all -> 0x0010 }
        r13 = (com.tencent.liteav.screencapture.b.b) r13;	 Catch:{ all -> 0x0010 }
        if (r13 == 0) goto L_0x0051;
    L_0x005f:
        r14 = r13.a;	 Catch:{ all -> 0x0010 }
        if (r14 == 0) goto L_0x0051;
    L_0x0063:
        r14 = r13.b;	 Catch:{ all -> 0x0010 }
        if (r14 == 0) goto L_0x0051;
    L_0x0067:
        r14 = r13.c;	 Catch:{ all -> 0x0010 }
        if (r14 != 0) goto L_0x006c;
    L_0x006b:
        goto L_0x0051;
    L_0x006c:
        r2 = r11.g;	 Catch:{ all -> 0x0010 }
        r3 = "TXCScreenCapture";
        r4 = r13.b;	 Catch:{ all -> 0x0010 }
        r5 = r13.c;	 Catch:{ all -> 0x0010 }
        r6 = 1;
        r7 = 1;
        r8 = r13.a;	 Catch:{ all -> 0x0010 }
        r9 = 0;
        r10 = 0;
        r14 = r2.createVirtualDisplay(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ all -> 0x0010 }
        if (r14 != 0) goto L_0x008b;
    L_0x0080:
        r12 = r11.m;	 Catch:{ all -> 0x0010 }
        r13 = "录屏失败";
        com.tencent.liteav.basic.util.b.a(r12, r0, r13);	 Catch:{ all -> 0x0010 }
        r11.i = r1;	 Catch:{ all -> 0x0010 }
        monitor-exit(r11);	 Catch:{ all -> 0x0010 }
        return;
    L_0x008b:
        r2 = r11.d;	 Catch:{ all -> 0x0010 }
        r13 = r13.a;	 Catch:{ all -> 0x0010 }
        r2.put(r13, r14);	 Catch:{ all -> 0x0010 }
        goto L_0x0051;
    L_0x0093:
        r12 = r11.h;	 Catch:{ all -> 0x0010 }
        r12.clear();	 Catch:{ all -> 0x0010 }
        r12 = 3;
        r11.i = r12;	 Catch:{ all -> 0x0010 }
        monitor-exit(r11);	 Catch:{ all -> 0x0010 }
        r12 = r11.m;
        if (r12 == 0) goto L_0x00b1;
    L_0x00a0:
        r12 = new android.os.Handler;
        r13 = android.os.Looper.getMainLooper();
        r12.<init>(r13);
        r13 = new com.tencent.liteav.screencapture.b$6;
        r13.<init>();
        r12.post(r13);
    L_0x00b1:
        return;
    L_0x00b2:
        r11.i = r1;	 Catch:{ all -> 0x0010 }
        r12 = r11.m;	 Catch:{ all -> 0x0010 }
        r13 = "录屏失败";
        com.tencent.liteav.basic.util.b.a(r12, r0, r13);	 Catch:{ all -> 0x0010 }
        monitor-exit(r11);	 Catch:{ all -> 0x0010 }
        return;
    L_0x00bd:
        monitor-exit(r11);	 Catch:{ all -> 0x0010 }
        throw r12;	 Catch:{ Exception -> 0x00bf }
    L_0x00bf:
        r12 = move-exception;
        r12.printStackTrace();
        r11.i = r1;
        r12 = r11.m;
        r13 = "录屏失败";
        com.tencent.liteav.basic.util.b.a(r12, r0, r13);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.screencapture.b.a(int, int, android.content.Intent):void");
    }
}
