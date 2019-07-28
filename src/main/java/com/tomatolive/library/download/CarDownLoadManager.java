package com.tomatolive.library.download;

import android.text.TextUtils;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.http.utils.RetryWithDelayUtils;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.model.CarDownloadListEntity;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.j;
import defpackage.sh;
import defpackage.xl;
import io.reactivex.k;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CarDownLoadManager {
    private volatile CarDownloadListEntity mDownloadListConfig;

    private static class SingletonHolder {
        private static final CarDownLoadManager INSTANCE = new CarDownLoadManager();

        private SingletonHolder() {
        }
    }

    /* synthetic */ CarDownLoadManager(AnonymousClass1 anonymousClass1) {
        this();
    }

    private CarDownLoadManager() {
    }

    public static CarDownLoadManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void updateAnimOnlineAllRes() {
        if (b.d()) {
            ApiRetrofit.getInstance().getApiService().getCarDownloadList(new RequestParams().getAllCarParams()).map(new ServerResultFunction<List<CarDownloadEntity>>() {
            }).onErrorResumeNext(new HttpResultFunction<List<CarDownloadEntity>>() {
            }).retryWhen(new RetryWithDelayUtils(3, 3)).subscribeOn(xl.b()).observeOn(xl.b()).subscribe(new sh<List<CarDownloadEntity>>() {
                public void accept(List<CarDownloadEntity> list) {
                    CarDownLoadManager.this.updateLocalAnim(list);
                }
            });
        }
    }

    public void updateAnimOnlineSingleRes(CarDownloadEntity carDownloadEntity) {
        if (carDownloadEntity != null) {
            CarDownLoaderImpl.getInstance().downloadFile(carDownloadEntity.zipUrl, GiftConfig.INSTANCE.carAnimResRootPath, g.h(carDownloadEntity.getCarFileName()), carDownloadEntity);
        }
    }

    /* JADX WARNING: Missing block: B:32:0x00ae, code skipped:
            return;
     */
    private synchronized void updateLocalAnim(java.util.List<com.tomatolive.library.model.CarDownloadEntity> r7) {
        /*
        r6 = this;
        monitor-enter(r6);
        if (r7 != 0) goto L_0x0005;
    L_0x0003:
        monitor-exit(r6);
        return;
    L_0x0005:
        r0 = r6.getLocalDownloadList();	 Catch:{ all -> 0x00af }
        r1 = new com.tomatolive.library.model.CarDownloadListEntity;	 Catch:{ all -> 0x00af }
        r1.<init>();	 Catch:{ all -> 0x00af }
        r1.carList = r7;	 Catch:{ all -> 0x00af }
        if (r0 != 0) goto L_0x0017;
    L_0x0012:
        r6.setLocalDownloadList(r1);	 Catch:{ all -> 0x00af }
        r0 = r1;
        goto L_0x007f;
    L_0x0017:
        r7 = new java.util.LinkedHashMap;	 Catch:{ all -> 0x00af }
        r7.<init>();	 Catch:{ all -> 0x00af }
        r2 = new java.util.LinkedHashMap;	 Catch:{ all -> 0x00af }
        r2.<init>();	 Catch:{ all -> 0x00af }
        r3 = r0.carList;	 Catch:{ all -> 0x00af }
        r3 = r3.iterator();	 Catch:{ all -> 0x00af }
    L_0x0027:
        r4 = r3.hasNext();	 Catch:{ all -> 0x00af }
        if (r4 == 0) goto L_0x0039;
    L_0x002d:
        r4 = r3.next();	 Catch:{ all -> 0x00af }
        r4 = (com.tomatolive.library.model.CarDownloadEntity) r4;	 Catch:{ all -> 0x00af }
        r5 = r4.id;	 Catch:{ all -> 0x00af }
        r7.put(r5, r4);	 Catch:{ all -> 0x00af }
        goto L_0x0027;
    L_0x0039:
        r1 = r1.carList;	 Catch:{ all -> 0x00af }
        r1 = r1.iterator();	 Catch:{ all -> 0x00af }
    L_0x003f:
        r3 = r1.hasNext();	 Catch:{ all -> 0x00af }
        if (r3 == 0) goto L_0x006b;
    L_0x0045:
        r3 = r1.next();	 Catch:{ all -> 0x00af }
        r3 = (com.tomatolive.library.model.CarDownloadEntity) r3;	 Catch:{ all -> 0x00af }
        r4 = r3.id;	 Catch:{ all -> 0x00af }
        r4 = r7.get(r4);	 Catch:{ all -> 0x00af }
        r4 = (com.tomatolive.library.model.CarDownloadEntity) r4;	 Catch:{ all -> 0x00af }
        if (r4 == 0) goto L_0x0065;
    L_0x0055:
        r5 = r4.animLocalPath;	 Catch:{ all -> 0x00af }
        r5 = com.tomatolive.library.utils.b.q(r5);	 Catch:{ all -> 0x00af }
        r5 = com.tomatolive.library.utils.g.a(r5);	 Catch:{ all -> 0x00af }
        if (r5 == 0) goto L_0x0065;
    L_0x0061:
        r4 = r4.animLocalPath;	 Catch:{ all -> 0x00af }
        r3.animLocalPath = r4;	 Catch:{ all -> 0x00af }
    L_0x0065:
        r4 = r3.id;	 Catch:{ all -> 0x00af }
        r2.put(r4, r3);	 Catch:{ all -> 0x00af }
        goto L_0x003f;
    L_0x006b:
        r7.clear();	 Catch:{ all -> 0x00af }
        r7 = r0.carList;	 Catch:{ all -> 0x00af }
        r7.clear();	 Catch:{ all -> 0x00af }
        r7 = r0.carList;	 Catch:{ all -> 0x00af }
        r1 = r2.values();	 Catch:{ all -> 0x00af }
        r7.addAll(r1);	 Catch:{ all -> 0x00af }
        r6.setLocalDownloadList(r0);	 Catch:{ all -> 0x00af }
    L_0x007f:
        r7 = new java.util.ArrayList;	 Catch:{ all -> 0x00af }
        r7.<init>();	 Catch:{ all -> 0x00af }
        r0 = r0.carList;	 Catch:{ all -> 0x00af }
        r0 = r0.iterator();	 Catch:{ all -> 0x00af }
    L_0x008a:
        r1 = r0.hasNext();	 Catch:{ all -> 0x00af }
        if (r1 == 0) goto L_0x00a0;
    L_0x0090:
        r1 = r0.next();	 Catch:{ all -> 0x00af }
        r1 = (com.tomatolive.library.model.CarDownloadEntity) r1;	 Catch:{ all -> 0x00af }
        r2 = r6.isResValid(r1);	 Catch:{ all -> 0x00af }
        if (r2 != 0) goto L_0x008a;
    L_0x009c:
        r7.add(r1);	 Catch:{ all -> 0x00af }
        goto L_0x008a;
    L_0x00a0:
        r0 = r7.size();	 Catch:{ all -> 0x00af }
        if (r0 <= 0) goto L_0x00ad;
    L_0x00a6:
        r0 = com.tomatolive.library.download.CarDownLoaderImpl.getInstance();	 Catch:{ all -> 0x00af }
        r0.startDownLoad(r7);	 Catch:{ all -> 0x00af }
    L_0x00ad:
        monitor-exit(r6);
        return;
    L_0x00af:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.download.CarDownLoadManager.updateLocalAnim(java.util.List):void");
    }

    private CarDownloadListEntity getLocalDownloadList() {
        try {
            return (CarDownloadListEntity) j.a(g.g(getDownConfigPath()), CarDownloadListEntity.class);
        } catch (Exception unused) {
            return null;
        }
    }

    private synchronized void setLocalDownloadList(CarDownloadListEntity carDownloadListEntity) {
        if (carDownloadListEntity != null) {
            try {
                this.mDownloadListConfig = carDownloadListEntity;
                g.a(j.a((Object) carDownloadListEntity), getDownConfigPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    private boolean isResValid(CarDownloadEntity carDownloadEntity) {
        return TextUtils.isEmpty(carDownloadEntity.animLocalPath) ^ 1;
    }

    public synchronized void updateDownloadItem(CarDownloadEntity carDownloadEntity) {
        CarDownloadListEntity localDownloadList = getLocalDownloadList();
        for (CarDownloadEntity carDownloadEntity2 : localDownloadList.carList) {
            if (carDownloadEntity2.id.equals(carDownloadEntity.id)) {
                carDownloadEntity2.animLocalPath = carDownloadEntity.animLocalPath;
                break;
            }
        }
        setLocalDownloadList(localDownloadList);
    }

    public void unZipRes(final String str) {
        try {
            if (g.b(g.h(str), str)) {
                k.timer(4, TimeUnit.SECONDS).subscribeOn(xl.b()).observeOn(xl.b()).subscribe(new sh<Long>() {
                    public void accept(Long l) {
                        g.c(g.h(str));
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CarDownloadEntity getCarItemEntity(String str) {
        if (!(this.mDownloadListConfig == null || this.mDownloadListConfig.carList == null || this.mDownloadListConfig.carList.size() <= 0)) {
            for (CarDownloadEntity carDownloadEntity : this.mDownloadListConfig.carList) {
                if (carDownloadEntity.id.equals(str) && !TextUtils.isEmpty(carDownloadEntity.animLocalPath)) {
                    if (g.f(carDownloadEntity.animLocalPath)) {
                        return carDownloadEntity;
                    }
                    updateLocalCarItem(str);
                }
            }
        }
        return null;
    }

    private void updateLocalCarItem(String str) {
        if (this.mDownloadListConfig != null && this.mDownloadListConfig.carList != null && this.mDownloadListConfig.carList.size() > 0) {
            CarDownloadListEntity carDownloadListEntity = this.mDownloadListConfig;
            for (CarDownloadEntity carDownloadEntity : carDownloadListEntity.carList) {
                if (carDownloadEntity.id.equals(str)) {
                    carDownloadEntity.animLocalPath = "";
                    break;
                }
            }
            setLocalDownloadList(carDownloadListEntity);
        }
    }

    private String getDownConfigPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GiftConfig.INSTANCE.carAnimResRootPath);
        stringBuilder.append(GiftConfig.INSTANCE.CAR_CONFIG_NAME);
        return stringBuilder.toString();
    }
}
