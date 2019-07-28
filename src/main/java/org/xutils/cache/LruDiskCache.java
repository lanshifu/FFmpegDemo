package org.xutils.cache;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import org.xutils.DbManager;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.FileUtil;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.common.util.MD5;
import org.xutils.common.util.ProcessLock;
import org.xutils.config.DbConfigs;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.ex.FileLockedException;
import org.xutils.x;

public final class LruDiskCache {
    private static final HashMap<String, LruDiskCache> a = new HashMap(5);
    private boolean b = false;
    private final DbManager c = x.getDb(DbConfigs.HTTP.getConfig());
    private File d;
    private long e = 104857600;
    private final Executor f = new PriorityExecutor(1, true);
    private long g = 0;

    public static synchronized LruDiskCache getDiskCache(String str) {
        LruDiskCache lruDiskCache;
        synchronized (LruDiskCache.class) {
            Object str2;
            if (TextUtils.isEmpty(str2)) {
                str2 = "xUtils_cache";
            }
            lruDiskCache = (LruDiskCache) a.get(str2);
            if (lruDiskCache == null) {
                lruDiskCache = new LruDiskCache(str2);
                a.put(str2, lruDiskCache);
            }
        }
        return lruDiskCache;
    }

    private LruDiskCache(String str) {
        this.d = FileUtil.getCacheDir(str);
        if (this.d != null && (this.d.exists() || this.d.mkdirs())) {
            this.b = true;
        }
        c();
    }

    public LruDiskCache setMaxSize(long j) {
        if (j > 0) {
            long diskAvailableSize = FileUtil.getDiskAvailableSize();
            if (diskAvailableSize > j) {
                this.e = j;
            } else {
                this.e = diskAvailableSize;
            }
        }
        return this;
    }

    public DiskCacheEntity get(String str) {
        if (!this.b || TextUtils.isEmpty(str)) {
            return null;
        }
        DiskCacheEntity diskCacheEntity;
        try {
            diskCacheEntity = (DiskCacheEntity) this.c.selector(DiskCacheEntity.class).where("key", "=", str).findFirst();
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            diskCacheEntity = null;
        }
        if (diskCacheEntity != null) {
            if (diskCacheEntity.getExpires() < System.currentTimeMillis()) {
                return null;
            }
            this.f.execute(new Runnable() {
                public void run() {
                    diskCacheEntity.setHits(diskCacheEntity.getHits() + 1);
                    diskCacheEntity.setLastAccess(System.currentTimeMillis());
                    try {
                        LruDiskCache.this.c.update(diskCacheEntity, "hits", "lastAccess");
                    } catch (Throwable th) {
                        LogUtil.e(th.getMessage(), th);
                    }
                }
            });
        }
        return diskCacheEntity;
    }

    public void put(DiskCacheEntity diskCacheEntity) {
        if (this.b && diskCacheEntity != null && !TextUtils.isEmpty(diskCacheEntity.getTextContent()) && diskCacheEntity.getExpires() >= System.currentTimeMillis()) {
            try {
                this.c.replace(diskCacheEntity);
            } catch (DbException e) {
                LogUtil.e(e.getMessage(), e);
            }
            a();
        }
    }

    public DiskCacheFile getDiskCacheFile(String str) throws InterruptedException {
        DiskCacheFile diskCacheFile = null;
        if (!this.b || TextUtils.isEmpty(str)) {
            return null;
        }
        Object obj = get(str);
        if (obj != null && new File(obj.getPath()).exists()) {
            ProcessLock tryLock = ProcessLock.tryLock(obj.getPath(), false, 3000);
            if (tryLock != null && tryLock.isValid()) {
                DiskCacheFile diskCacheFile2 = new DiskCacheFile(obj, obj.getPath(), tryLock);
                if (diskCacheFile2.exists()) {
                    diskCacheFile = diskCacheFile2;
                } else {
                    try {
                        this.c.delete(obj);
                    } catch (DbException e) {
                        LogUtil.e(e.getMessage(), e);
                    }
                }
            }
        }
        return diskCacheFile;
    }

    public DiskCacheFile createDiskCacheFile(DiskCacheEntity diskCacheEntity) throws IOException {
        if (!this.b || diskCacheEntity == null) {
            return null;
        }
        diskCacheEntity.setPath(new File(this.d, MD5.md5(diskCacheEntity.getKey())).getAbsolutePath());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(diskCacheEntity.getPath());
        stringBuilder.append(".tmp");
        String stringBuilder2 = stringBuilder.toString();
        ProcessLock tryLock = ProcessLock.tryLock(stringBuilder2, true);
        if (tryLock == null || !tryLock.isValid()) {
            throw new FileLockedException(diskCacheEntity.getPath());
        }
        DiskCacheFile diskCacheFile = new DiskCacheFile(diskCacheEntity, stringBuilder2, tryLock);
        if (!diskCacheFile.getParentFile().exists()) {
            diskCacheFile.mkdirs();
        }
        return diskCacheFile;
    }

    public void clearCacheFiles() {
        IOUtil.deleteFileOrDir(this.d);
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0081 A:{ExcHandler: InterruptedException (e java.lang.InterruptedException), Splitter:B:18:0x003f} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c0  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:23:0x004b, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:24:0x004c, code skipped:
            r1 = r0;
            r0 = r4;
     */
    /* JADX WARNING: Missing block: B:34:0x0081, code skipped:
            r0 = e;
     */
    public org.xutils.cache.DiskCacheFile a(org.xutils.cache.DiskCacheFile r7) throws java.io.IOException {
        /*
        r6 = this;
        r0 = 0;
        if (r7 == 0) goto L_0x0011;
    L_0x0003:
        r1 = r7.length();
        r3 = 1;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 >= 0) goto L_0x0011;
    L_0x000d:
        org.xutils.common.util.IOUtil.closeQuietly(r7);
        return r0;
    L_0x0011:
        r1 = r6.b;
        if (r1 == 0) goto L_0x00c8;
    L_0x0015:
        if (r7 != 0) goto L_0x0019;
    L_0x0017:
        goto L_0x00c8;
    L_0x0019:
        r1 = r7.cacheEntity;
        r2 = r7.getName();
        r3 = ".tmp";
        r2 = r2.endsWith(r3);
        if (r2 == 0) goto L_0x00c7;
    L_0x0027:
        r2 = r1.getPath();	 Catch:{ InterruptedException -> 0x0093, all -> 0x008f }
        r3 = 1;
        r4 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r3 = org.xutils.common.util.ProcessLock.tryLock(r2, r3, r4);	 Catch:{ InterruptedException -> 0x0093, all -> 0x008f }
        if (r3 == 0) goto L_0x0089;
    L_0x0034:
        r4 = r3.isValid();	 Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
        if (r4 == 0) goto L_0x0089;
    L_0x003a:
        r4 = new org.xutils.cache.DiskCacheFile;	 Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
        r4.<init>(r1, r2, r3);	 Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
        r2 = r7.renameTo(r4);	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        if (r2 == 0) goto L_0x0064;
    L_0x0045:
        r0 = r6.c;	 Catch:{ DbException -> 0x0050 }
        r0.replace(r1);	 Catch:{ DbException -> 0x0050 }
        goto L_0x0058;
    L_0x004b:
        r0 = move-exception;
        r1 = r0;
        r0 = r4;
        goto L_0x00b4;
    L_0x0050:
        r0 = move-exception;
        r1 = r0.getMessage();	 Catch:{ InterruptedException -> 0x0081, all -> 0x004b }
        org.xutils.common.util.LogUtil.e(r1, r0);	 Catch:{ InterruptedException -> 0x0081, all -> 0x004b }
    L_0x0058:
        r6.a();	 Catch:{ InterruptedException -> 0x0081, all -> 0x004b }
        org.xutils.common.util.IOUtil.closeQuietly(r7);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r7);
        r7 = r4;
        goto L_0x00c7;
    L_0x0064:
        r1 = new java.io.IOException;	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        r2 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        r2.<init>();	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        r5 = "rename:";
        r2.append(r5);	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        r5 = r7.getAbsolutePath();	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        r2.append(r5);	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        r2 = r2.toString();	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        r1.<init>(r2);	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
        throw r1;	 Catch:{ InterruptedException -> 0x0081, all -> 0x007f }
    L_0x007f:
        r1 = move-exception;
        goto L_0x00b4;
    L_0x0081:
        r0 = move-exception;
        goto L_0x0097;
    L_0x0083:
        r1 = move-exception;
        r4 = r0;
        goto L_0x00b4;
    L_0x0086:
        r1 = move-exception;
        r4 = r0;
        goto L_0x0096;
    L_0x0089:
        r1 = new org.xutils.ex.FileLockedException;	 Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
        r1.<init>(r2);	 Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
        throw r1;	 Catch:{ InterruptedException -> 0x0086, all -> 0x0083 }
    L_0x008f:
        r1 = move-exception;
        r3 = r0;
        r4 = r3;
        goto L_0x00b4;
    L_0x0093:
        r1 = move-exception;
        r3 = r0;
        r4 = r3;
    L_0x0096:
        r0 = r1;
    L_0x0097:
        r1 = r0.getMessage();	 Catch:{ all -> 0x00b1 }
        org.xutils.common.util.LogUtil.e(r1, r0);	 Catch:{ all -> 0x00b1 }
        if (r7 != 0) goto L_0x00aa;
    L_0x00a0:
        org.xutils.common.util.IOUtil.closeQuietly(r4);
        org.xutils.common.util.IOUtil.closeQuietly(r3);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r4);
        goto L_0x00c7;
    L_0x00aa:
        org.xutils.common.util.IOUtil.closeQuietly(r7);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r7);
        goto L_0x00c7;
    L_0x00b1:
        r0 = move-exception;
        r1 = r0;
        r0 = r7;
    L_0x00b4:
        if (r0 != 0) goto L_0x00c0;
    L_0x00b6:
        org.xutils.common.util.IOUtil.closeQuietly(r4);
        org.xutils.common.util.IOUtil.closeQuietly(r3);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r4);
        goto L_0x00c6;
    L_0x00c0:
        org.xutils.common.util.IOUtil.closeQuietly(r7);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r7);
    L_0x00c6:
        throw r1;
    L_0x00c7:
        return r7;
    L_0x00c8:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.cache.LruDiskCache.a(org.xutils.cache.DiskCacheFile):org.xutils.cache.DiskCacheFile");
    }

    private void a() {
        this.f.execute(new Runnable() {
            public void run() {
                if (LruDiskCache.this.b) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - LruDiskCache.this.g >= 1000) {
                        List<Object> findAll;
                        String path;
                        LruDiskCache lruDiskCache;
                        StringBuilder stringBuilder;
                        LruDiskCache.this.g = currentTimeMillis;
                        LruDiskCache.this.b();
                        try {
                            int count = (int) LruDiskCache.this.c.selector(DiskCacheEntity.class).count();
                            if (count > 5010) {
                                findAll = LruDiskCache.this.c.selector(DiskCacheEntity.class).orderBy("lastAccess").orderBy("hits").limit(count - 5000).offset(0).findAll();
                                if (findAll != null && findAll.size() > 0) {
                                    for (Object obj : findAll) {
                                        try {
                                            LruDiskCache.this.c.delete(obj);
                                            path = obj.getPath();
                                            if (!TextUtils.isEmpty(path)) {
                                                LruDiskCache.this.a(path);
                                                lruDiskCache = LruDiskCache.this;
                                                stringBuilder = new StringBuilder();
                                                stringBuilder.append(path);
                                                stringBuilder.append(".tmp");
                                                lruDiskCache.a(stringBuilder.toString());
                                            }
                                        } catch (DbException e) {
                                            LogUtil.e(e.getMessage(), e);
                                        }
                                    }
                                }
                            }
                        } catch (DbException e2) {
                            LogUtil.e(e2.getMessage(), e2);
                        }
                        while (FileUtil.getFileOrDirSize(LruDiskCache.this.d) > LruDiskCache.this.e) {
                            try {
                                findAll = LruDiskCache.this.c.selector(DiskCacheEntity.class).orderBy("lastAccess").orderBy("hits").limit(10).offset(0).findAll();
                                if (findAll != null && findAll.size() > 0) {
                                    for (Object obj2 : findAll) {
                                        try {
                                            LruDiskCache.this.c.delete(obj2);
                                            path = obj2.getPath();
                                            if (!TextUtils.isEmpty(path)) {
                                                LruDiskCache.this.a(path);
                                                lruDiskCache = LruDiskCache.this;
                                                stringBuilder = new StringBuilder();
                                                stringBuilder.append(path);
                                                stringBuilder.append(".tmp");
                                                lruDiskCache.a(stringBuilder.toString());
                                            }
                                        } catch (DbException e3) {
                                            LogUtil.e(e3.getMessage(), e3);
                                        }
                                    }
                                }
                            } catch (DbException e4) {
                                LogUtil.e(e4.getMessage(), e4);
                            }
                        }
                    }
                }
            }
        });
    }

    private void b() {
        try {
            WhereBuilder b = WhereBuilder.b("expires", "<", Long.valueOf(System.currentTimeMillis()));
            List<DiskCacheEntity> findAll = this.c.selector(DiskCacheEntity.class).where(b).findAll();
            this.c.delete(DiskCacheEntity.class, b);
            if (findAll != null && findAll.size() > 0) {
                for (DiskCacheEntity path : findAll) {
                    String path2 = path.getPath();
                    if (!TextUtils.isEmpty(path2)) {
                        a(path2);
                    }
                }
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }

    private void c() {
        this.f.execute(new Runnable() {
            public void run() {
                if (LruDiskCache.this.b) {
                    try {
                        File[] listFiles = LruDiskCache.this.d.listFiles();
                        if (listFiles != null) {
                            for (File file : listFiles) {
                                if (LruDiskCache.this.c.selector(DiskCacheEntity.class).where("path", "=", file.getAbsolutePath()).count() < 1) {
                                    IOUtil.deleteFileOrDir(file);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        LogUtil.e(th.getMessage(), th);
                    }
                }
            }
        });
    }

    private boolean a(String str) {
        Throwable th;
        Closeable tryLock;
        try {
            tryLock = ProcessLock.tryLock(str, true);
            if (tryLock != null) {
                try {
                    if (tryLock.isValid()) {
                        boolean deleteFileOrDir = IOUtil.deleteFileOrDir(new File(str));
                        IOUtil.closeQuietly(tryLock);
                        return deleteFileOrDir;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtil.closeQuietly(tryLock);
                    throw th;
                }
            }
            IOUtil.closeQuietly(tryLock);
            return false;
        } catch (Throwable th3) {
            th = th3;
            tryLock = null;
            IOUtil.closeQuietly(tryLock);
            throw th;
        }
    }
}
