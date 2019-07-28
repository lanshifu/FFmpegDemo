package com.tencent.liteav.network.a.a;

import com.tencent.liteav.network.a.a;
import com.tencent.liteav.network.a.b;
import com.tencent.liteav.network.a.d;
import com.tencent.liteav.network.a.e;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

/* compiled from: Resolver */
public final class c implements com.tencent.liteav.network.a.c {
    private static final Random b = new Random();
    final InetAddress a;
    private final int c;

    public c(InetAddress inetAddress) {
        this(inetAddress, 10);
    }

    public c(InetAddress inetAddress, int i) {
        this.a = inetAddress;
        this.c = i;
    }

    public e[] a(b bVar, d dVar) throws IOException {
        int nextInt;
        synchronized (b) {
            nextInt = b.nextInt() & 255;
        }
        byte[] a = a(b.a(bVar.a, nextInt));
        if (a != null) {
            return b.a(a, nextInt, bVar.a);
        }
        throw new a(bVar.a, "cant get answer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0034  */
    private byte[] a(byte[] r6) throws java.io.IOException {
        /*
        r5 = this;
        r0 = 0;
        r1 = new java.net.DatagramSocket;	 Catch:{ all -> 0x0030 }
        r1.<init>();	 Catch:{ all -> 0x0030 }
        r0 = new java.net.DatagramPacket;	 Catch:{ all -> 0x002e }
        r2 = r6.length;	 Catch:{ all -> 0x002e }
        r3 = r5.a;	 Catch:{ all -> 0x002e }
        r4 = 53;
        r0.<init>(r6, r2, r3, r4);	 Catch:{ all -> 0x002e }
        r6 = r5.c;	 Catch:{ all -> 0x002e }
        r6 = r6 * 1000;
        r1.setSoTimeout(r6);	 Catch:{ all -> 0x002e }
        r1.send(r0);	 Catch:{ all -> 0x002e }
        r6 = new java.net.DatagramPacket;	 Catch:{ all -> 0x002e }
        r0 = 1500; // 0x5dc float:2.102E-42 double:7.41E-321;
        r2 = new byte[r0];	 Catch:{ all -> 0x002e }
        r6.<init>(r2, r0);	 Catch:{ all -> 0x002e }
        r1.receive(r6);	 Catch:{ all -> 0x002e }
        r6 = r6.getData();	 Catch:{ all -> 0x002e }
        r1.close();
        return r6;
    L_0x002e:
        r6 = move-exception;
        goto L_0x0032;
    L_0x0030:
        r6 = move-exception;
        r1 = r0;
    L_0x0032:
        if (r1 == 0) goto L_0x0037;
    L_0x0034:
        r1.close();
    L_0x0037:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.network.a.a.c.a(byte[]):byte[]");
    }
}
