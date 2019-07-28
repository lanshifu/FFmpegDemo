package okhttp3.internal.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import okhttp3.internal.Util;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okio.d;
import okio.r;

final class DiskLruCache$Entry {
    final File[] cleanFiles;
    DiskLruCache$Editor currentEditor;
    final File[] dirtyFiles;
    final String key;
    final long[] lengths;
    boolean readable;
    long sequenceNumber;
    final /* synthetic */ DiskLruCache this$0;

    DiskLruCache$Entry(DiskLruCache diskLruCache, String str) {
        this.this$0 = diskLruCache;
        this.key = str;
        this.lengths = new long[diskLruCache.valueCount];
        this.cleanFiles = new File[diskLruCache.valueCount];
        this.dirtyFiles = new File[diskLruCache.valueCount];
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append('.');
        int length = stringBuilder.length();
        for (int i = 0; i < diskLruCache.valueCount; i++) {
            stringBuilder.append(i);
            this.cleanFiles[i] = new File(diskLruCache.directory, stringBuilder.toString());
            stringBuilder.append(".tmp");
            this.dirtyFiles[i] = new File(diskLruCache.directory, stringBuilder.toString());
            stringBuilder.setLength(length);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setLengths(String[] strArr) throws IOException {
        if (strArr.length == this.this$0.valueCount) {
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.lengths[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException unused) {
                    throw invalidLengths(strArr);
                }
            }
            return;
        }
        throw invalidLengths(strArr);
    }

    /* Access modifiers changed, original: 0000 */
    public void writeLengths(d dVar) throws IOException {
        for (long o : this.lengths) {
            dVar.k(32).o(o);
        }
    }

    private IOException invalidLengths(String[] strArr) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unexpected journal line: ");
        stringBuilder.append(Arrays.toString(strArr));
        throw new IOException(stringBuilder.toString());
    }

    /* Access modifiers changed, original: 0000 */
    public Snapshot snapshot() {
        if (Thread.holdsLock(this.this$0)) {
            r[] rVarArr = new r[this.this$0.valueCount];
            long[] jArr = (long[]) this.lengths.clone();
            int i = 0;
            int i2 = 0;
            while (i2 < this.this$0.valueCount) {
                try {
                    rVarArr[i2] = this.this$0.fileSystem.source(this.cleanFiles[i2]);
                    i2++;
                } catch (FileNotFoundException unused) {
                    while (i < this.this$0.valueCount && rVarArr[i] != null) {
                        Util.closeQuietly(rVarArr[i]);
                        i++;
                    }
                    try {
                        this.this$0.removeEntry(this);
                    } catch (IOException unused2) {
                    }
                    return null;
                }
            }
            return new Snapshot(this.this$0, this.key, this.sequenceNumber, rVarArr, jArr);
        }
        throw new AssertionError();
    }
}
