package com.tomatolive.library.download;

import com.tomatolive.library.http.utils.RetryWithDelayUtils;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.l;
import defpackage.wm;
import defpackage.xl;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import okhttp3.ResponseBody;

public class CarDownLoaderImpl implements ICarDownLoader {

    private static class SingletonHolder {
        private static final CarDownLoaderImpl INSTANCE = new CarDownLoaderImpl();

        private SingletonHolder() {
        }
    }

    /* synthetic */ CarDownLoaderImpl(AnonymousClass1 anonymousClass1) {
        this();
    }

    private CarDownLoaderImpl() {
    }

    public static CarDownLoaderImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void startDownLoad(List<CarDownloadEntity> list) {
        if (list != null && !list.isEmpty()) {
            for (CarDownloadEntity carDownloadEntity : list) {
                if (carDownloadEntity != null) {
                    downloadFile(carDownloadEntity.zipUrl, GiftConfig.INSTANCE.carAnimResRootPath, g.h(carDownloadEntity.getCarFileName()), carDownloadEntity);
                }
            }
        }
    }

    public void downloadFile(String str, final String str2, final String str3, final CarDownloadEntity carDownloadEntity) {
        if (b.e()) {
            DownLoadRetrofit.getInstance().getApiService().downLoadFile(i.c(str)).subscribeOn(xl.b()).observeOn(xl.b()).map(new wm<ResponseBody, File>() {
                public File apply(ResponseBody responseBody) throws Exception {
                    return CarDownLoaderImpl.this.saveFile(responseBody, str2, str3);
                }
            }).observeOn(xl.b()).retryWhen(new RetryWithDelayUtils(3, 3)).subscribe(new FileDownLoadObserver<File>() {
                public void onDownLoadFail(Throwable th) {
                }

                public void onProgress(int i, long j) {
                }

                public void onDownLoadSuccess(File file) {
                    CarDownLoaderImpl.this.dealDownLoadItem(carDownloadEntity);
                }
            });
        }
    }

    private void dealDownLoadItem(CarDownloadEntity carDownloadEntity) {
        if (carDownloadEntity != null) {
            CarDownLoadManager.getInstance().unZipRes(getGiftDirPath(carDownloadEntity.getCarFileName()));
            carDownloadEntity.animLocalPath = getGiftDirPath(carDownloadEntity.getCarFileName());
            CarDownLoadManager.getInstance().updateDownloadItem(carDownloadEntity);
        }
    }

    private String getGiftDirPath(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GiftConfig.INSTANCE.carAnimResRootPath);
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
