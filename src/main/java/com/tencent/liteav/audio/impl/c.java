package com.tencent.liteav.audio.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.tencent.liteav.basic.log.TXCLog;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: TXCTelephonyMgr */
public class c {
    private static final c a = new c();
    private ConcurrentHashMap<Integer, WeakReference<e>> b = new ConcurrentHashMap();
    private PhoneStateListener c = null;
    private Context d;

    public static c a() {
        return a;
    }

    private c() {
    }

    public synchronized void a(e eVar) {
        if (eVar != null) {
            this.b.put(Integer.valueOf(eVar.hashCode()), new WeakReference(eVar));
        }
    }

    /* JADX WARNING: Missing block: B:9:0x0023, code skipped:
            return;
     */
    public synchronized void b(com.tencent.liteav.audio.impl.e r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        if (r3 != 0) goto L_0x0005;
    L_0x0003:
        monitor-exit(r2);
        return;
    L_0x0005:
        r0 = r2.b;	 Catch:{ all -> 0x0024 }
        r1 = r3.hashCode();	 Catch:{ all -> 0x0024 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x0024 }
        r0 = r0.containsKey(r1);	 Catch:{ all -> 0x0024 }
        if (r0 == 0) goto L_0x0022;
    L_0x0015:
        r0 = r2.b;	 Catch:{ all -> 0x0024 }
        r3 = r3.hashCode();	 Catch:{ all -> 0x0024 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x0024 }
        r0.remove(r3);	 Catch:{ all -> 0x0024 }
    L_0x0022:
        monitor-exit(r2);
        return;
    L_0x0024:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.audio.impl.c.b(com.tencent.liteav.audio.impl.e):void");
    }

    private synchronized void a(int i) {
        Iterator it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            e eVar = (e) ((WeakReference) ((Entry) it.next()).getValue()).get();
            if (eVar != null) {
                eVar.b(i);
            } else {
                it.remove();
            }
        }
    }

    public void a(Context context) {
        if (this.c == null) {
            this.d = context.getApplicationContext();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (c.this.c == null) {
                        c.this.c = new PhoneStateListener() {
                            public void onCallStateChanged(int i, String str) {
                                super.onCallStateChanged(i, str);
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("onCallStateChanged:");
                                stringBuilder.append(i);
                                TXCLog.i("AudioCenter:TXCTelephonyMgr", stringBuilder.toString());
                                c.this.a(i);
                            }
                        };
                        ((TelephonyManager) c.this.d.getSystemService("phone")).listen(c.this.c, 32);
                    }
                }
            });
        }
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        super.finalize();
        if (this.c != null && this.d != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (!(c.this.c == null || c.this.d == null)) {
                        ((TelephonyManager) c.this.d.getApplicationContext().getSystemService("phone")).listen(c.this.c, 0);
                    }
                    c.this.c = null;
                }
            });
        }
    }
}
