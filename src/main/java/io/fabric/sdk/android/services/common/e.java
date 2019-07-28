package io.fabric.sdk.android.services.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import io.fabric.sdk.android.c;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: AdvertisingInfoServiceStrategy */
class e implements f {
    private final Context a;

    /* compiled from: AdvertisingInfoServiceStrategy */
    private static final class a implements ServiceConnection {
        private boolean a;
        private final LinkedBlockingQueue<IBinder> b;

        private a() {
            this.a = false;
            this.b = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.b.clear();
        }

        public IBinder a() {
            if (this.a) {
                c.g().e("Fabric", "getBinder already called");
            }
            this.a = true;
            try {
                return (IBinder) this.b.poll(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }

    /* compiled from: AdvertisingInfoServiceStrategy */
    private static final class b implements IInterface {
        private final IBinder a;

        public b(IBinder iBinder) {
            this.a = iBinder;
        }

        public IBinder asBinder() {
            return this.a;
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0024 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:5|6|11|10|8) */
        /* JADX WARNING: Missing block: B:6:?, code skipped:
            io.fabric.sdk.android.c.g().a("Fabric", "Could not get parcel from Google Play Service to capture AdvertisingId");
     */
        /* JADX WARNING: Missing block: B:7:0x0037, code skipped:
            r1.recycle();
            r0.recycle();
     */
        /* JADX WARNING: Missing block: B:8:?, code skipped:
            return null;
     */
        public java.lang.String a() throws android.os.RemoteException {
            /*
            r5 = this;
            r0 = android.os.Parcel.obtain();
            r1 = android.os.Parcel.obtain();
            r2 = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";
            r0.writeInterfaceToken(r2);	 Catch:{ Exception -> 0x0024 }
            r2 = r5.a;	 Catch:{ Exception -> 0x0024 }
            r3 = 1;
            r4 = 0;
            r2.transact(r3, r0, r1, r4);	 Catch:{ Exception -> 0x0024 }
            r1.readException();	 Catch:{ Exception -> 0x0024 }
            r2 = r1.readString();	 Catch:{ Exception -> 0x0024 }
            r1.recycle();
            r0.recycle();
            goto L_0x0036;
        L_0x0022:
            r2 = move-exception;
            goto L_0x0037;
        L_0x0024:
            r2 = io.fabric.sdk.android.c.g();	 Catch:{ all -> 0x0022 }
            r3 = "Fabric";
            r4 = "Could not get parcel from Google Play Service to capture AdvertisingId";
            r2.a(r3, r4);	 Catch:{ all -> 0x0022 }
            r1.recycle();
            r0.recycle();
            r2 = 0;
        L_0x0036:
            return r2;
        L_0x0037:
            r1.recycle();
            r0.recycle();
            throw r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.common.e$b.a():java.lang.String");
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0025 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(2:6|7) */
        /* JADX WARNING: Missing block: B:7:?, code skipped:
            io.fabric.sdk.android.c.g().a("Fabric", "Could not get parcel from Google Play Service to capture Advertising limitAdTracking");
     */
        /* JADX WARNING: Missing block: B:10:0x0037, code skipped:
            r1.recycle();
            r0.recycle();
     */
        public boolean b() throws android.os.RemoteException {
            /*
            r6 = this;
            r0 = android.os.Parcel.obtain();
            r1 = android.os.Parcel.obtain();
            r2 = 0;
            r3 = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";
            r0.writeInterfaceToken(r3);	 Catch:{ Exception -> 0x0025 }
            r3 = 1;
            r0.writeInt(r3);	 Catch:{ Exception -> 0x0025 }
            r4 = r6.a;	 Catch:{ Exception -> 0x0025 }
            r5 = 2;
            r4.transact(r5, r0, r1, r2);	 Catch:{ Exception -> 0x0025 }
            r1.readException();	 Catch:{ Exception -> 0x0025 }
            r4 = r1.readInt();	 Catch:{ Exception -> 0x0025 }
            if (r4 == 0) goto L_0x0030;
        L_0x0021:
            r2 = 1;
            goto L_0x0030;
        L_0x0023:
            r2 = move-exception;
            goto L_0x0037;
        L_0x0025:
            r3 = io.fabric.sdk.android.c.g();	 Catch:{ all -> 0x0023 }
            r4 = "Fabric";
            r5 = "Could not get parcel from Google Play Service to capture Advertising limitAdTracking";
            r3.a(r4, r5);	 Catch:{ all -> 0x0023 }
        L_0x0030:
            r1.recycle();
            r0.recycle();
            return r2;
        L_0x0037:
            r1.recycle();
            r0.recycle();
            throw r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.common.e$b.b():boolean");
        }
    }

    public e(Context context) {
        this.a = context.getApplicationContext();
    }

    public b a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            c.g().a("Fabric", "AdvertisingInfoServiceStrategy cannot be called on the main thread");
            return null;
        }
        try {
            this.a.getPackageManager().getPackageInfo("com.android.vending", 0);
            a aVar = new a();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (this.a.bindService(intent, aVar, 1)) {
                    try {
                        b bVar = new b(aVar.a());
                        b bVar2 = new b(bVar.a(), bVar.b());
                        this.a.unbindService(aVar);
                        return bVar2;
                    } catch (Exception e) {
                        c.g().d("Fabric", "Exception in binding to Google Play Service to capture AdvertisingId", e);
                        this.a.unbindService(aVar);
                    }
                } else {
                    c.g().a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId");
                    return null;
                }
            } catch (Throwable th) {
                c.g().a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId", th);
            }
        } catch (NameNotFoundException unused) {
            c.g().a("Fabric", "Unable to find Google Play Services package name");
            return null;
        } catch (Exception th2) {
            c.g().a("Fabric", "Unable to determine if Google Play Services is available", th2);
            return null;
        }
    }
}
