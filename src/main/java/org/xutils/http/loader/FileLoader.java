package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.DiskCacheFile;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class FileLoader extends Loader<File> {
    private String a;
    private String b;
    private boolean c;
    private boolean d;
    private long e;
    private String f;
    private DiskCacheFile g;

    public void save2Cache(UriRequest uriRequest) {
    }

    public Loader<File> newInstance() {
        return new FileLoader();
    }

    public void setParams(RequestParams requestParams) {
        if (requestParams != null) {
            this.params = requestParams;
            this.c = requestParams.isAutoResume();
            this.d = requestParams.isAutoRename();
        }
    }

    public File load(InputStream inputStream) throws Throwable {
        Closeable fileInputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        Closeable closeable;
        BufferedInputStream bufferedInputStream;
        InputStream inputStream2 = inputStream;
        Closeable closeable2 = null;
        try {
            OutputStream fileOutputStream;
            File file = new File(this.a);
            if (file.isDirectory()) {
                IOUtil.deleteFileOrDir(file);
            }
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    if (!parentFile.mkdirs()) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("can not create dir: ");
                        stringBuilder.append(parentFile.getAbsolutePath());
                        throw new IOException(stringBuilder.toString());
                    }
                }
            }
            long length = file.length();
            if (this.c && length > 0) {
                long j = length - 512;
                if (j > 0) {
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = null;
                        IOUtil.closeQuietly(fileInputStream);
                        throw th;
                    }
                    try {
                        if (Arrays.equals(IOUtil.readBytes(inputStream2, 0, IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED), IOUtil.readBytes(fileInputStream, j, IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED))) {
                            this.e -= 512;
                            IOUtil.closeQuietly(fileInputStream);
                        } else {
                            IOUtil.closeQuietly(fileInputStream);
                            IOUtil.deleteFileOrDir(file);
                            throw new RuntimeException("need retry");
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        IOUtil.closeQuietly(fileInputStream);
                        throw th;
                    }
                }
                IOUtil.deleteFileOrDir(file);
                throw new RuntimeException("need retry");
            }
            if (this.c) {
                fileOutputStream = new FileOutputStream(file, true);
            } else {
                fileOutputStream = new FileOutputStream(file);
                length = 0;
            }
            long j2 = this.e + length;
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream2);
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                try {
                    if (this.progressHandler != null) {
                        try {
                            bufferedOutputStream = bufferedOutputStream2;
                            try {
                                if (!this.progressHandler.updateProgress(j2, length, true)) {
                                    throw new CancelledException("download stopped!");
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                closeable = bufferedOutputStream;
                                closeable2 = bufferedInputStream2;
                                IOUtil.closeQuietly(closeable2);
                                IOUtil.closeQuietly(closeable);
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            bufferedOutputStream = bufferedOutputStream2;
                            closeable = bufferedOutputStream;
                            closeable2 = bufferedInputStream2;
                            IOUtil.closeQuietly(closeable2);
                            IOUtil.closeQuietly(closeable);
                            throw th;
                        }
                    }
                    bufferedOutputStream = bufferedOutputStream2;
                    byte[] bArr = new byte[4096];
                    long j3 = length;
                    while (true) {
                        int read = bufferedInputStream2.read(bArr);
                        if (read == -1) {
                            Closeable closeable3 = bufferedInputStream2;
                            bufferedOutputStream.flush();
                            if (this.g != null) {
                                file = this.g.commit();
                            }
                            if (this.progressHandler != null) {
                                this.progressHandler.updateProgress(j2, j3, true);
                            }
                            IOUtil.closeQuietly(closeable3);
                            IOUtil.closeQuietly((Closeable) bufferedOutputStream);
                            return a(file);
                        } else if (file.getParentFile().exists()) {
                            bufferedOutputStream.write(bArr, 0, read);
                            length = ((long) read) + j3;
                            if (this.progressHandler != null) {
                                bufferedInputStream = bufferedInputStream2;
                                try {
                                    if (!this.progressHandler.updateProgress(j2, length, false)) {
                                        bufferedOutputStream.flush();
                                        throw new CancelledException("download stopped!");
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    closeable = bufferedOutputStream;
                                    closeable2 = bufferedInputStream;
                                    IOUtil.closeQuietly(closeable2);
                                    IOUtil.closeQuietly(closeable);
                                    throw th;
                                }
                            }
                            bufferedInputStream = bufferedInputStream2;
                            j3 = length;
                            bufferedInputStream2 = bufferedInputStream;
                        } else {
                            bufferedInputStream = bufferedInputStream2;
                            file.getParentFile().mkdirs();
                            throw new IOException("parent be deleted!");
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    bufferedOutputStream = bufferedOutputStream2;
                    bufferedInputStream = bufferedInputStream2;
                    closeable = bufferedOutputStream;
                    closeable2 = bufferedInputStream;
                    IOUtil.closeQuietly(closeable2);
                    IOUtil.closeQuietly(closeable);
                    throw th;
                }
            } catch (Throwable th8) {
                th = th8;
                bufferedInputStream = bufferedInputStream2;
                closeable = bufferedOutputStream;
                closeable2 = bufferedInputStream;
                IOUtil.closeQuietly(closeable2);
                IOUtil.closeQuietly(closeable);
                throw th;
            }
        } catch (Throwable th9) {
            th = th9;
            closeable = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:82:0x01bf A:{Catch:{ all -> 0x01c0 }} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0170 A:{Catch:{ all -> 0x01c0 }} */
    public java.io.File load(org.xutils.http.request.UriRequest r11) throws java.lang.Throwable {
        /*
        r10 = this;
        r0 = 0;
        r1 = r10.params;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r1 = r1.getSaveFilePath();	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r10.b = r1;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r10.g = r0;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r1 = r10.b;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        if (r1 == 0) goto L_0x0031;
    L_0x0013:
        r1 = r10.progressHandler;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        if (r1 == 0) goto L_0x002d;
    L_0x0017:
        r2 = r10.progressHandler;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r3 = 0;
        r5 = 0;
        r7 = 0;
        r1 = r2.updateProgress(r3, r5, r7);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        if (r1 == 0) goto L_0x0025;
    L_0x0024:
        goto L_0x002d;
    L_0x0025:
        r1 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r2 = "download stopped!";
        r1.<init>(r2);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        throw r1;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
    L_0x002d:
        r10.a(r11);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        goto L_0x0046;
    L_0x0031:
        r1 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r1.<init>();	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r2 = r10.b;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r1.append(r2);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r2 = ".tmp";
        r1.append(r2);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r1 = r1.toString();	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r10.a = r1;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
    L_0x0046:
        r1 = r10.progressHandler;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        if (r1 == 0) goto L_0x0060;
    L_0x004a:
        r2 = r10.progressHandler;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r3 = 0;
        r5 = 0;
        r7 = 0;
        r1 = r2.updateProgress(r3, r5, r7);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        if (r1 == 0) goto L_0x0058;
    L_0x0057:
        goto L_0x0060;
    L_0x0058:
        r1 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r2 = "download stopped!";
        r1.<init>(r2);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        throw r1;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
    L_0x0060:
        r1 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r1.<init>();	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r2 = r10.b;	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r1.append(r2);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r2 = "_lock";
        r1.append(r2);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r1 = r1.toString();	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        r2 = 1;
        r1 = org.xutils.common.util.ProcessLock.tryLock(r1, r2);	 Catch:{ HttpException -> 0x0164, all -> 0x0161 }
        if (r1 == 0) goto L_0x0148;
    L_0x007a:
        r0 = r1.isValid();	 Catch:{ HttpException -> 0x0146 }
        if (r0 == 0) goto L_0x0148;
    L_0x0080:
        r0 = r11.getParams();	 Catch:{ HttpException -> 0x0146 }
        r10.params = r0;	 Catch:{ HttpException -> 0x0146 }
        r0 = r10.c;	 Catch:{ HttpException -> 0x0146 }
        r2 = 0;
        if (r0 == 0) goto L_0x00a4;
    L_0x008c:
        r0 = new java.io.File;	 Catch:{ HttpException -> 0x0146 }
        r4 = r10.a;	 Catch:{ HttpException -> 0x0146 }
        r0.<init>(r4);	 Catch:{ HttpException -> 0x0146 }
        r4 = r0.length();	 Catch:{ HttpException -> 0x0146 }
        r6 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 > 0) goto L_0x00a1;
    L_0x009d:
        org.xutils.common.util.IOUtil.deleteFileOrDir(r0);	 Catch:{ HttpException -> 0x0146 }
        goto L_0x00a4;
    L_0x00a1:
        r0 = 0;
        r2 = r4 - r6;
    L_0x00a4:
        r0 = r10.params;	 Catch:{ HttpException -> 0x0146 }
        r4 = "RANGE";
        r5 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0146 }
        r5.<init>();	 Catch:{ HttpException -> 0x0146 }
        r6 = "bytes=";
        r5.append(r6);	 Catch:{ HttpException -> 0x0146 }
        r5.append(r2);	 Catch:{ HttpException -> 0x0146 }
        r2 = "-";
        r5.append(r2);	 Catch:{ HttpException -> 0x0146 }
        r2 = r5.toString();	 Catch:{ HttpException -> 0x0146 }
        r0.setHeader(r4, r2);	 Catch:{ HttpException -> 0x0146 }
        r0 = r10.progressHandler;	 Catch:{ HttpException -> 0x0146 }
        if (r0 == 0) goto L_0x00db;
    L_0x00c5:
        r2 = r10.progressHandler;	 Catch:{ HttpException -> 0x0146 }
        r3 = 0;
        r5 = 0;
        r7 = 0;
        r0 = r2.updateProgress(r3, r5, r7);	 Catch:{ HttpException -> 0x0146 }
        if (r0 == 0) goto L_0x00d3;
    L_0x00d2:
        goto L_0x00db;
    L_0x00d3:
        r0 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpException -> 0x0146 }
        r2 = "download stopped!";
        r0.<init>(r2);	 Catch:{ HttpException -> 0x0146 }
        throw r0;	 Catch:{ HttpException -> 0x0146 }
    L_0x00db:
        r11.sendRequest();	 Catch:{ HttpException -> 0x0146 }
        r2 = r11.getContentLength();	 Catch:{ HttpException -> 0x0146 }
        r10.e = r2;	 Catch:{ HttpException -> 0x0146 }
        r0 = r10.d;	 Catch:{ HttpException -> 0x0146 }
        if (r0 == 0) goto L_0x00ee;
    L_0x00e8:
        r0 = b(r11);	 Catch:{ HttpException -> 0x0146 }
        r10.f = r0;	 Catch:{ HttpException -> 0x0146 }
    L_0x00ee:
        r0 = r10.c;	 Catch:{ HttpException -> 0x0146 }
        if (r0 == 0) goto L_0x00f8;
    L_0x00f2:
        r0 = c(r11);	 Catch:{ HttpException -> 0x0146 }
        r10.c = r0;	 Catch:{ HttpException -> 0x0146 }
    L_0x00f8:
        r0 = r10.progressHandler;	 Catch:{ HttpException -> 0x0146 }
        if (r0 == 0) goto L_0x0112;
    L_0x00fc:
        r2 = r10.progressHandler;	 Catch:{ HttpException -> 0x0146 }
        r3 = 0;
        r5 = 0;
        r7 = 0;
        r0 = r2.updateProgress(r3, r5, r7);	 Catch:{ HttpException -> 0x0146 }
        if (r0 == 0) goto L_0x010a;
    L_0x0109:
        goto L_0x0112;
    L_0x010a:
        r0 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpException -> 0x0146 }
        r2 = "download stopped!";
        r0.<init>(r2);	 Catch:{ HttpException -> 0x0146 }
        throw r0;	 Catch:{ HttpException -> 0x0146 }
    L_0x0112:
        r0 = r10.g;	 Catch:{ HttpException -> 0x0146 }
        if (r0 == 0) goto L_0x013d;
    L_0x0116:
        r0 = r10.g;	 Catch:{ HttpException -> 0x0146 }
        r0 = r0.getCacheEntity();	 Catch:{ HttpException -> 0x0146 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x0146 }
        r0.setLastAccess(r2);	 Catch:{ HttpException -> 0x0146 }
        r2 = r11.getETag();	 Catch:{ HttpException -> 0x0146 }
        r0.setEtag(r2);	 Catch:{ HttpException -> 0x0146 }
        r2 = r11.getExpiration();	 Catch:{ HttpException -> 0x0146 }
        r0.setExpires(r2);	 Catch:{ HttpException -> 0x0146 }
        r2 = new java.util.Date;	 Catch:{ HttpException -> 0x0146 }
        r3 = r11.getLastModified();	 Catch:{ HttpException -> 0x0146 }
        r2.<init>(r3);	 Catch:{ HttpException -> 0x0146 }
        r0.setLastModify(r2);	 Catch:{ HttpException -> 0x0146 }
    L_0x013d:
        r0 = r11.getInputStream();	 Catch:{ HttpException -> 0x0146 }
        r0 = r10.load(r0);	 Catch:{ HttpException -> 0x0146 }
        goto L_0x0198;
    L_0x0146:
        r0 = move-exception;
        goto L_0x0168;
    L_0x0148:
        r0 = new org.xutils.ex.FileLockedException;	 Catch:{ HttpException -> 0x0146 }
        r2 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0146 }
        r2.<init>();	 Catch:{ HttpException -> 0x0146 }
        r3 = "download exists: ";
        r2.append(r3);	 Catch:{ HttpException -> 0x0146 }
        r3 = r10.b;	 Catch:{ HttpException -> 0x0146 }
        r2.append(r3);	 Catch:{ HttpException -> 0x0146 }
        r2 = r2.toString();	 Catch:{ HttpException -> 0x0146 }
        r0.<init>(r2);	 Catch:{ HttpException -> 0x0146 }
        throw r0;	 Catch:{ HttpException -> 0x0146 }
    L_0x0161:
        r11 = move-exception;
        r1 = r0;
        goto L_0x01c1;
    L_0x0164:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
    L_0x0168:
        r2 = r0.getCode();	 Catch:{ all -> 0x01c0 }
        r3 = 416; // 0x1a0 float:5.83E-43 double:2.055E-321;
        if (r2 != r3) goto L_0x01bf;
    L_0x0170:
        r0 = r10.g;	 Catch:{ all -> 0x01c0 }
        if (r0 == 0) goto L_0x017b;
    L_0x0174:
        r0 = r10.g;	 Catch:{ all -> 0x01c0 }
        r0 = r0.commit();	 Catch:{ all -> 0x01c0 }
        goto L_0x0182;
    L_0x017b:
        r0 = new java.io.File;	 Catch:{ all -> 0x01c0 }
        r2 = r10.a;	 Catch:{ all -> 0x01c0 }
        r0.<init>(r2);	 Catch:{ all -> 0x01c0 }
    L_0x0182:
        if (r0 == 0) goto L_0x01a1;
    L_0x0184:
        r2 = r0.exists();	 Catch:{ all -> 0x01c0 }
        if (r2 == 0) goto L_0x01a1;
    L_0x018a:
        r2 = r10.d;	 Catch:{ all -> 0x01c0 }
        if (r2 == 0) goto L_0x0194;
    L_0x018e:
        r11 = b(r11);	 Catch:{ all -> 0x01c0 }
        r10.f = r11;	 Catch:{ all -> 0x01c0 }
    L_0x0194:
        r0 = r10.a(r0);	 Catch:{ all -> 0x01c0 }
    L_0x0198:
        org.xutils.common.util.IOUtil.closeQuietly(r1);
        r11 = r10.g;
        org.xutils.common.util.IOUtil.closeQuietly(r11);
        return r0;
    L_0x01a1:
        org.xutils.common.util.IOUtil.deleteFileOrDir(r0);	 Catch:{ all -> 0x01c0 }
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x01c0 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01c0 }
        r2.<init>();	 Catch:{ all -> 0x01c0 }
        r3 = "cache file not found";
        r2.append(r3);	 Catch:{ all -> 0x01c0 }
        r11 = r11.getCacheKey();	 Catch:{ all -> 0x01c0 }
        r2.append(r11);	 Catch:{ all -> 0x01c0 }
        r11 = r2.toString();	 Catch:{ all -> 0x01c0 }
        r0.<init>(r11);	 Catch:{ all -> 0x01c0 }
        throw r0;	 Catch:{ all -> 0x01c0 }
    L_0x01bf:
        throw r0;	 Catch:{ all -> 0x01c0 }
    L_0x01c0:
        r11 = move-exception;
    L_0x01c1:
        org.xutils.common.util.IOUtil.closeQuietly(r1);
        r0 = r10.g;
        org.xutils.common.util.IOUtil.closeQuietly(r0);
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.http.loader.FileLoader.load(org.xutils.http.request.UriRequest):java.io.File");
    }

    private void a(UriRequest uriRequest) throws Throwable {
        DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
        diskCacheEntity.setKey(uriRequest.getCacheKey());
        this.g = LruDiskCache.getDiskCache(this.params.getCacheDirName()).createDiskCacheFile(diskCacheEntity);
        if (this.g != null) {
            this.b = this.g.getAbsolutePath();
            this.a = this.b;
            this.d = false;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create cache file error:");
        stringBuilder.append(uriRequest.getCacheKey());
        throw new IOException(stringBuilder.toString());
    }

    private File a(File file) {
        File file2;
        if (this.d && file.exists() && !TextUtils.isEmpty(this.f)) {
            file2 = new File(file.getParent(), this.f);
            while (file2.exists()) {
                String parent = file.getParent();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(System.currentTimeMillis());
                stringBuilder.append(this.f);
                file2 = new File(parent, stringBuilder.toString());
            }
            if (file.renameTo(file2)) {
                file = file2;
            }
            return file;
        } else if (this.b.equals(this.a)) {
            return file;
        } else {
            file2 = new File(this.b);
            if (file.renameTo(file2)) {
                file = file2;
            }
            return file;
        }
    }

    private static String b(UriRequest uriRequest) {
        if (uriRequest == null) {
            return null;
        }
        String responseHeader = uriRequest.getResponseHeader("Content-Disposition");
        if (!TextUtils.isEmpty(responseHeader)) {
            int indexOf = responseHeader.indexOf("filename=");
            if (indexOf > 0) {
                indexOf += 9;
                int indexOf2 = responseHeader.indexOf(";", indexOf);
                if (indexOf2 < 0) {
                    indexOf2 = responseHeader.length();
                }
                if (indexOf2 > indexOf) {
                    try {
                        String decode = URLDecoder.decode(responseHeader.substring(indexOf, indexOf2), uriRequest.getParams().getCharset());
                        if (decode.startsWith("\"") && decode.endsWith("\"")) {
                            decode = decode.substring(1, decode.length() - 1);
                        }
                        return decode;
                    } catch (UnsupportedEncodingException e) {
                        LogUtil.e(e.getMessage(), e);
                    }
                }
            }
        }
        return null;
    }

    private static boolean c(UriRequest uriRequest) {
        boolean z = false;
        if (uriRequest == null) {
            return false;
        }
        String responseHeader = uriRequest.getResponseHeader("Accept-Ranges");
        if (responseHeader != null) {
            return responseHeader.contains("bytes");
        }
        String responseHeader2 = uriRequest.getResponseHeader("Content-Range");
        if (responseHeader2 != null && responseHeader2.contains("bytes")) {
            z = true;
        }
        return z;
    }

    public File loadFromCache(DiskCacheEntity diskCacheEntity) throws Throwable {
        return LruDiskCache.getDiskCache(this.params.getCacheDirName()).getDiskCacheFile(diskCacheEntity.getKey());
    }
}
