package org.xutils.common.util;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.xutils.x;

public final class ProcessLock implements Closeable {
    private static final DoubleKeyValueMap<String, Integer, ProcessLock> f = new DoubleKeyValueMap();
    private static final DecimalFormat g = new DecimalFormat("0.##################");
    private final String a;
    private final FileLock b;
    private final File c;
    private final Closeable d;
    private final boolean e;

    static {
        IOUtil.deleteFileOrDir(x.app().getDir("process_lock", 0));
    }

    private ProcessLock(String str, File file, FileLock fileLock, Closeable closeable, boolean z) {
        this.a = str;
        this.b = fileLock;
        this.c = file;
        this.d = closeable;
        this.e = z;
    }

    public static ProcessLock tryLock(String str, boolean z) {
        return a(str, a(str), z);
    }

    public static ProcessLock tryLock(String str, boolean z, long j) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis() + j;
        String a = a(str);
        ProcessLock processLock = null;
        while (System.currentTimeMillis() < currentTimeMillis) {
            processLock = a(str, a, z);
            if (processLock != null) {
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw e;
            } catch (Throwable unused) {
            }
        }
        return processLock;
    }

    public boolean isValid() {
        return a(this.b);
    }

    public void release() {
        a(this.a, this.b, this.c, this.d);
    }

    public void close() throws IOException {
        release();
    }

    private static boolean a(FileLock fileLock) {
        return fileLock != null && fileLock.isValid();
    }

    private static void a(String str, FileLock fileLock, File file, Closeable closeable) {
        synchronized (f) {
            if (fileLock != null) {
                Closeable channel;
                try {
                    f.remove(str, Integer.valueOf(fileLock.hashCode()));
                    ConcurrentHashMap concurrentHashMap = f.get(str);
                    if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
                        IOUtil.deleteFileOrDir(file);
                    }
                    if (fileLock.channel().isOpen()) {
                        fileLock.release();
                    }
                    channel = fileLock.channel();
                } catch (Throwable th) {
                    try {
                        LogUtil.e(th.getMessage(), th);
                        channel = fileLock.channel();
                    } catch (Throwable th2) {
                        IOUtil.closeQuietly(fileLock.channel());
                    }
                }
                IOUtil.closeQuietly(channel);
            }
            IOUtil.closeQuietly(closeable);
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        double d = 0.0d;
        byte[] bytes = str.getBytes();
        for (int i = 0; i < str.length(); i++) {
            d *= 255.0d;
            double d2 = (double) bytes[i];
            Double.isNaN(d2);
            d = (d + d2) * 0.005d;
        }
        return g.format(d);
    }

    private static ProcessLock a(String str, String str2, boolean z) {
        Throwable th;
        StringBuilder stringBuilder;
        synchronized (f) {
            ProcessLock processLock;
            ConcurrentHashMap concurrentHashMap = f.get(str);
            if (!(concurrentHashMap == null || concurrentHashMap.isEmpty())) {
                Iterator it = concurrentHashMap.entrySet().iterator();
                while (it.hasNext()) {
                    processLock = (ProcessLock) ((Entry) it.next()).getValue();
                    if (processLock == null) {
                        it.remove();
                    } else if (!processLock.isValid()) {
                        it.remove();
                    } else if (z) {
                        return null;
                    } else if (processLock.e) {
                        return null;
                    }
                }
            }
            Closeable fileOutputStream;
            Closeable channel;
            try {
                File file = new File(x.app().getDir("process_lock", 0), str2);
                if (file.exists() || file.createNewFile()) {
                    if (z) {
                        fileOutputStream = new FileOutputStream(file, false);
                        channel = fileOutputStream.getChannel();
                    } else {
                        fileOutputStream = new FileInputStream(file);
                        channel = fileOutputStream.getChannel();
                    }
                    if (channel != null) {
                        try {
                            FileLock tryLock = channel.tryLock(0, Long.MAX_VALUE, z ^ 1);
                            if (a(tryLock)) {
                                processLock = new ProcessLock(str, file, tryLock, fileOutputStream, z);
                                f.put(str, Integer.valueOf(tryLock.hashCode()), processLock);
                                return processLock;
                            }
                            a(str, tryLock, file, fileOutputStream);
                        } catch (Throwable th2) {
                            th = th2;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("tryLock: ");
                            stringBuilder.append(str);
                            stringBuilder.append(", ");
                            stringBuilder.append(th.getMessage());
                            LogUtil.d(stringBuilder.toString());
                            IOUtil.closeQuietly(fileOutputStream);
                            IOUtil.closeQuietly(channel);
                            return null;
                        }
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("can not get file channel:");
                    stringBuilder.append(file.getAbsolutePath());
                    throw new IOException(stringBuilder.toString());
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                channel = fileOutputStream;
                stringBuilder = new StringBuilder();
                stringBuilder.append("tryLock: ");
                stringBuilder.append(str);
                stringBuilder.append(", ");
                stringBuilder.append(th.getMessage());
                LogUtil.d(stringBuilder.toString());
                IOUtil.closeQuietly(fileOutputStream);
                IOUtil.closeQuietly(channel);
                return null;
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append(": ");
        stringBuilder.append(this.c.getName());
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        super.finalize();
        release();
    }
}
