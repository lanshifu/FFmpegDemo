package okhttp3;

class ConnectionPool$1 implements Runnable {
    final /* synthetic */ ConnectionPool this$0;

    ConnectionPool$1(ConnectionPool connectionPool) {
        this.this$0 = connectionPool;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x002b */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public void run() {
        /*
        r6 = this;
    L_0x0000:
        r0 = r6.this$0;
        r1 = java.lang.System.nanoTime();
        r0 = r0.cleanup(r1);
        r2 = -1;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x0011;
    L_0x0010:
        return;
    L_0x0011:
        r2 = 0;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 <= 0) goto L_0x0000;
    L_0x0017:
        r2 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r4 = r0 / r2;
        r2 = r2 * r4;
        r0 = r0 - r2;
        r2 = r6.this$0;
        monitor-enter(r2);
        r3 = r6.this$0;	 Catch:{ InterruptedException -> 0x002b }
        r0 = (int) r0;	 Catch:{ InterruptedException -> 0x002b }
        r3.wait(r4, r0);	 Catch:{ InterruptedException -> 0x002b }
        goto L_0x002b;
    L_0x0029:
        r0 = move-exception;
        goto L_0x002d;
    L_0x002b:
        monitor-exit(r2);	 Catch:{ all -> 0x0029 }
        goto L_0x0000;
    L_0x002d:
        monitor-exit(r2);	 Catch:{ all -> 0x0029 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.ConnectionPool$1.run():void");
    }
}
