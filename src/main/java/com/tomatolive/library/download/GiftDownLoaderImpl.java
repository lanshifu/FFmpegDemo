package com.tomatolive.library.download;

import com.tomatolive.library.http.utils.RetryWithDelayUtils;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.l;
import com.tomatolive.library.utils.m;
import defpackage.wm;
import defpackage.xl;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import okhttp3.ResponseBody;

public class GiftDownLoaderImpl implements IGiftDownLoader {

    private static class SingletonHolder {
        private static final GiftDownLoaderImpl INSTANCE = new GiftDownLoaderImpl();

        private SingletonHolder() {
        }
    }

    /* synthetic */ GiftDownLoaderImpl(AnonymousClass1 anonymousClass1) {
        this();
    }

    private GiftDownLoaderImpl() {
    }

    public static GiftDownLoaderImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void startDownLoad(List<GiftDownloadItemEntity> list) {
        if (list != null && !list.isEmpty()) {
            for (GiftDownloadItemEntity giftDownloadItemEntity : list) {
                if (giftDownloadItemEntity != null) {
                    downloadFile(giftDownloadItemEntity.zipurl, GiftConfig.INSTANCE.animResRootPath, g.h(giftDownloadItemEntity.getLocalDirName()), giftDownloadItemEntity);
                }
            }
        }
    }

    public void downloadFile(String str, final String str2, final String str3, final GiftDownloadItemEntity giftDownloadItemEntity) {
        if (b.e()) {
            DownLoadRetrofit.getInstance().getApiService().downLoadFile(i.c(str)).subscribeOn(xl.b()).observeOn(xl.b()).map(new wm<ResponseBody, File>() {
                public File apply(ResponseBody responseBody) throws Exception {
                    return GiftDownLoaderImpl.this.saveFile(responseBody, str2, str3);
                }
            }).observeOn(xl.b()).retryWhen(new RetryWithDelayUtils(3, 3)).subscribe(new FileDownLoadObserver<File>() {
                public void onProgress(int i, long j) {
                }

                public void onDownLoadSuccess(File file) {
                    GiftDownLoaderImpl.this.dealDownLoadItem(giftDownloadItemEntity);
                }

                public void onDownLoadFail(Throwable th) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str3);
                    stringBuilder.append("下载失败：");
                    stringBuilder.append(th);
                    m.a(stringBuilder.toString());
                }
            });
        }
    }

    private void dealDownLoadItem(GiftDownloadItemEntity giftDownloadItemEntity) {
        if (giftDownloadItemEntity != null) {
            GiftDownLoadManager.getInstance().unZipRes(getGiftDirPath(giftDownloadItemEntity.getLocalDirName()));
            giftDownloadItemEntity.giftDirPath = getGiftDirPath(giftDownloadItemEntity.getLocalDirName());
            GiftDownLoadManager.getInstance().updateDownloadItem(giftDownloadItemEntity);
        }
    }

    private String getGiftDirPath(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GiftConfig.INSTANCE.animResRootPath);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private File saveFile(ResponseBody responseBody, String str, String str2) throws IOException {
        Throwable th;
        byte[] bArr = new byte[1048576];
        Closeable closeable = null;
        Closeable byteStream;
        try {
            byteStream = responseBody.byteStream();
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str2);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                while (true) {
                    try {
                        int read = byteStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.flush();
                            l.a(byteStream);
                            l.a(fileOutputStream);
                            return file2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = fileOutputStream;
                        l.a(byteStream);
                        l.a(closeable);
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                l.a(byteStream);
                l.a(closeable);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            byteStream = null;
            l.a(byteStream);
            l.a(closeable);
            throw th;
        }
    }
}
