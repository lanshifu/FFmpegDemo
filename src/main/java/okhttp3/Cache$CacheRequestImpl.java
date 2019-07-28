package okhttp3;

import java.io.IOException;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.DiskLruCache$Editor;
import okio.g;
import okio.q;

final class Cache$CacheRequestImpl implements CacheRequest {
    private q body;
    private q cacheOut;
    boolean done;
    private final DiskLruCache$Editor editor;
    final /* synthetic */ Cache this$0;

    Cache$CacheRequestImpl(final Cache cache, final DiskLruCache$Editor diskLruCache$Editor) {
        this.this$0 = cache;
        this.editor = diskLruCache$Editor;
        this.cacheOut = diskLruCache$Editor.newSink(1);
        this.body = new g(this.cacheOut) {
            public void close() throws IOException {
                synchronized (Cache$CacheRequestImpl.this.this$0) {
                    if (Cache$CacheRequestImpl.this.done) {
                        return;
                    }
                    Cache$CacheRequestImpl.this.done = true;
                    Cache cache = Cache$CacheRequestImpl.this.this$0;
                    cache.writeSuccessCount++;
                    super.close();
                    diskLruCache$Editor.commit();
                }
            }
        };
    }

    /* JADX WARNING: Missing block: B:9:0x0014, code skipped:
            okhttp3.internal.Util.closeQuietly(r4.cacheOut);
     */
    /* JADX WARNING: Missing block: B:11:?, code skipped:
            r4.editor.abort();
     */
    public void abort() {
        /*
        r4 = this;
        r0 = r4.this$0;
        monitor-enter(r0);
        r1 = r4.done;	 Catch:{ all -> 0x001f }
        if (r1 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r0);	 Catch:{ all -> 0x001f }
        return;
    L_0x0009:
        r1 = 1;
        r4.done = r1;	 Catch:{ all -> 0x001f }
        r2 = r4.this$0;	 Catch:{ all -> 0x001f }
        r3 = r2.writeAbortCount;	 Catch:{ all -> 0x001f }
        r3 = r3 + r1;
        r2.writeAbortCount = r3;	 Catch:{ all -> 0x001f }
        monitor-exit(r0);	 Catch:{ all -> 0x001f }
        r0 = r4.cacheOut;
        okhttp3.internal.Util.closeQuietly(r0);
        r0 = r4.editor;	 Catch:{ IOException -> 0x001e }
        r0.abort();	 Catch:{ IOException -> 0x001e }
    L_0x001e:
        return;
    L_0x001f:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x001f }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cache$CacheRequestImpl.abort():void");
    }

    public q body() {
        return this.body;
    }
}
