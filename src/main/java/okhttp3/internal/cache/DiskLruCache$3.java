package okhttp3.internal.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import okhttp3.internal.cache.DiskLruCache.Snapshot;

class DiskLruCache$3 implements Iterator<Snapshot> {
    final Iterator<DiskLruCache$Entry> delegate = new ArrayList(this.this$0.lruEntries.values()).iterator();
    Snapshot nextSnapshot;
    Snapshot removeSnapshot;
    final /* synthetic */ DiskLruCache this$0;

    DiskLruCache$3(DiskLruCache diskLruCache) {
        this.this$0 = diskLruCache;
    }

    public boolean hasNext() {
        if (this.nextSnapshot != null) {
            return true;
        }
        synchronized (this.this$0) {
            if (this.this$0.closed) {
                return false;
            }
            while (this.delegate.hasNext()) {
                Snapshot snapshot = ((DiskLruCache$Entry) this.delegate.next()).snapshot();
                if (snapshot != null) {
                    this.nextSnapshot = snapshot;
                    return true;
                }
            }
            return false;
        }
    }

    public Snapshot next() {
        if (hasNext()) {
            this.removeSnapshot = this.nextSnapshot;
            this.nextSnapshot = null;
            return this.removeSnapshot;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.removeSnapshot != null) {
            try {
                this.this$0.remove(Snapshot.access$000(this.removeSnapshot));
            } catch (IOException unused) {
            } catch (Throwable th) {
                this.removeSnapshot = null;
            }
            this.removeSnapshot = null;
            return;
        }
        throw new IllegalStateException("remove() before next()");
    }
}
