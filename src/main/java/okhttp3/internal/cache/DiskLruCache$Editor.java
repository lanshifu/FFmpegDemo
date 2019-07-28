package okhttp3.internal.cache;

import java.io.FileNotFoundException;
import java.io.IOException;
import okio.l;
import okio.q;

public final class DiskLruCache$Editor {
    private boolean done;
    final DiskLruCache$Entry entry;
    final /* synthetic */ DiskLruCache this$0;
    final boolean[] written;

    DiskLruCache$Editor(DiskLruCache diskLruCache, DiskLruCache$Entry diskLruCache$Entry) {
        this.this$0 = diskLruCache;
        this.entry = diskLruCache$Entry;
        this.written = diskLruCache$Entry.readable ? null : new boolean[diskLruCache.valueCount];
    }

    /* Access modifiers changed, original: 0000 */
    public void detach() {
        if (this.entry.currentEditor == this) {
            for (int i = 0; i < this.this$0.valueCount; i++) {
                try {
                    this.this$0.fileSystem.delete(this.entry.dirtyFiles[i]);
                } catch (IOException unused) {
                }
            }
            this.entry.currentEditor = null;
        }
    }

    /* JADX WARNING: Missing block: B:18:0x0028, code skipped:
            return null;
     */
    public okio.r newSource(int r5) {
        /*
        r4 = this;
        r0 = r4.this$0;
        monitor-enter(r0);
        r1 = r4.done;	 Catch:{ all -> 0x002f }
        if (r1 != 0) goto L_0x0029;
    L_0x0007:
        r1 = r4.entry;	 Catch:{ all -> 0x002f }
        r1 = r1.readable;	 Catch:{ all -> 0x002f }
        r2 = 0;
        if (r1 == 0) goto L_0x0027;
    L_0x000e:
        r1 = r4.entry;	 Catch:{ all -> 0x002f }
        r1 = r1.currentEditor;	 Catch:{ all -> 0x002f }
        if (r1 == r4) goto L_0x0015;
    L_0x0014:
        goto L_0x0027;
    L_0x0015:
        r1 = r4.this$0;	 Catch:{ FileNotFoundException -> 0x0025 }
        r1 = r1.fileSystem;	 Catch:{ FileNotFoundException -> 0x0025 }
        r3 = r4.entry;	 Catch:{ FileNotFoundException -> 0x0025 }
        r3 = r3.cleanFiles;	 Catch:{ FileNotFoundException -> 0x0025 }
        r5 = r3[r5];	 Catch:{ FileNotFoundException -> 0x0025 }
        r5 = r1.source(r5);	 Catch:{ FileNotFoundException -> 0x0025 }
        monitor-exit(r0);	 Catch:{ all -> 0x002f }
        return r5;
    L_0x0025:
        monitor-exit(r0);	 Catch:{ all -> 0x002f }
        return r2;
    L_0x0027:
        monitor-exit(r0);	 Catch:{ all -> 0x002f }
        return r2;
    L_0x0029:
        r5 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x002f }
        r5.<init>();	 Catch:{ all -> 0x002f }
        throw r5;	 Catch:{ all -> 0x002f }
    L_0x002f:
        r5 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x002f }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache$Editor.newSource(int):okio.r");
    }

    public q newSink(int i) {
        synchronized (this.this$0) {
            if (this.done) {
                throw new IllegalStateException();
            } else if (this.entry.currentEditor != this) {
                q a = l.a();
                return a;
            } else {
                if (!this.entry.readable) {
                    this.written[i] = true;
                }
                try {
                    AnonymousClass1 anonymousClass1 = new FaultHidingSink(this.this$0.fileSystem.sink(this.entry.dirtyFiles[i])) {
                        /* Access modifiers changed, original: protected */
                        public void onException(IOException iOException) {
                            synchronized (DiskLruCache$Editor.this.this$0) {
                                DiskLruCache$Editor.this.detach();
                            }
                        }
                    };
                    return anonymousClass1;
                } catch (FileNotFoundException unused) {
                    return l.a();
                }
            }
        }
    }

    public void commit() throws IOException {
        synchronized (this.this$0) {
            if (this.done) {
                throw new IllegalStateException();
            }
            if (this.entry.currentEditor == this) {
                this.this$0.completeEdit(this, true);
            }
            this.done = true;
        }
    }

    public void abort() throws IOException {
        synchronized (this.this$0) {
            if (this.done) {
                throw new IllegalStateException();
            }
            if (this.entry.currentEditor == this) {
                this.this$0.completeEdit(this, false);
            }
            this.done = true;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0013 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(2:7|8)|9|10) */
    public void abortUnlessCommitted() {
        /*
        r3 = this;
        r0 = r3.this$0;
        monitor-enter(r0);
        r1 = r3.done;	 Catch:{ all -> 0x0015 }
        if (r1 != 0) goto L_0x0013;
    L_0x0007:
        r1 = r3.entry;	 Catch:{ all -> 0x0015 }
        r1 = r1.currentEditor;	 Catch:{ all -> 0x0015 }
        if (r1 != r3) goto L_0x0013;
    L_0x000d:
        r1 = r3.this$0;	 Catch:{ IOException -> 0x0013 }
        r2 = 0;
        r1.completeEdit(r3, r2);	 Catch:{ IOException -> 0x0013 }
    L_0x0013:
        monitor-exit(r0);	 Catch:{ all -> 0x0015 }
        return;
    L_0x0015:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0015 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache$Editor.abortUnlessCommitted():void");
    }
}
