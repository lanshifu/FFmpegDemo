package okio;

/* compiled from: AsyncTimeout */
final class a$a extends Thread {
    a$a() {
        super("Okio Watchdog");
        setDaemon(true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A:{LOOP_START, SYNTHETIC, Splitter:B:0:0x0000, LOOP:0: B:0:0x0000->B:22:0x0000} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:0|1|2) */
    /* JADX WARNING: Missing block: B:15:?, code skipped:
            r1.timedOut();
     */
    public void run() {
        /*
        r3 = this;
    L_0x0000:
        r0 = okio.a.class;
        monitor-enter(r0);	 Catch:{ InterruptedException -> 0x0000 }
        r1 = okio.a.awaitTimeout();	 Catch:{ all -> 0x0019 }
        if (r1 != 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r0);	 Catch:{ all -> 0x0019 }
        goto L_0x0000;
    L_0x000b:
        r2 = okio.a.head;	 Catch:{ all -> 0x0019 }
        if (r1 != r2) goto L_0x0014;
    L_0x000f:
        r1 = 0;
        okio.a.head = r1;	 Catch:{ all -> 0x0019 }
        monitor-exit(r0);	 Catch:{ all -> 0x0019 }
        return;
    L_0x0014:
        monitor-exit(r0);	 Catch:{ all -> 0x0019 }
        r1.timedOut();	 Catch:{ InterruptedException -> 0x0000 }
        goto L_0x0000;
    L_0x0019:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0019 }
        throw r1;	 Catch:{ InterruptedException -> 0x0000 }
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.a$a.run():void");
    }
}
