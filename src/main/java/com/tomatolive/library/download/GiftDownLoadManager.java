package com.tomatolive.library.download;

import android.text.TextUtils;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.http.utils.RetryWithDelayUtils;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.model.GiftDownloadListEntity;
import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.model.db.GiftDownloadItemDBEntity;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.c;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.m;
import defpackage.sh;
import defpackage.xl;
import io.reactivex.k;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GiftDownLoadManager {

    private static class SingletonHolder {
        private static final GiftDownLoadManager INSTANCE = new GiftDownLoadManager();

        private SingletonHolder() {
        }
    }

    /* synthetic */ GiftDownLoadManager(AnonymousClass1 anonymousClass1) {
        this();
    }

    private GiftDownLoadManager() {
    }

    public static GiftDownLoadManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void updateAnimOnlineRes() {
        if (b.d()) {
            ApiRetrofit.getInstance().getApiService().giftList(new RequestParams().getGiftListParams(-1)).map(new ServerResultFunction<GiftDownloadListEntity>() {
            }).onErrorResumeNext(new HttpResultFunction<GiftDownloadListEntity>() {
            }).retryWhen(new RetryWithDelayUtils(3, 3)).subscribeOn(xl.b()).observeOn(xl.b()).subscribe(new sh<GiftDownloadListEntity>() {
                public void accept(GiftDownloadListEntity giftDownloadListEntity) {
                    GiftDownLoadManager.this.updateLocalAnim(giftDownloadListEntity);
                }
            });
        }
    }

    private void updateLocalAnim(GiftDownloadListEntity giftDownloadListEntity) {
        if (giftDownloadListEntity != null && giftDownloadListEntity.list != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("线上礼物列表：");
            stringBuilder.append(giftDownloadListEntity.list);
            m.a(stringBuilder.toString());
            List<GiftDownloadItemEntity> localDownloadList = getLocalDownloadList();
            if (localDownloadList == null || localDownloadList.isEmpty()) {
                localDownloadList.clear();
                localDownloadList.addAll(giftDownloadListEntity.list);
            } else {
                GiftDownloadItemEntity giftDownloadItemEntity;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (GiftDownloadItemEntity giftDownloadItemEntity2 : localDownloadList) {
                    linkedHashMap.put(giftDownloadItemEntity2.typeid, giftDownloadItemEntity2);
                }
                for (GiftDownloadItemEntity giftDownloadItemEntity3 : giftDownloadListEntity.list) {
                    if (linkedHashMap.containsKey(giftDownloadItemEntity3.typeid)) {
                        giftDownloadItemEntity2 = (GiftDownloadItemEntity) linkedHashMap.get(giftDownloadItemEntity3.typeid);
                        if (TextUtils.equals(giftDownloadItemEntity3.id, giftDownloadItemEntity2.id) && g.b(b.a(giftDownloadItemEntity2.giftDirPath, giftDownloadItemEntity2.jsonname))) {
                            giftDownloadItemEntity3.giftDirPath = giftDownloadItemEntity2.giftDirPath;
                        }
                    }
                    linkedHashMap2.put(giftDownloadItemEntity3.typeid, giftDownloadItemEntity3);
                }
                linkedHashMap.clear();
                localDownloadList.clear();
                localDownloadList.addAll(linkedHashMap2.values());
            }
            setLocalDownloadList(localDownloadList);
            startLoadRes(localDownloadList);
        }
    }

    private void startLoadRes(List<GiftDownloadItemEntity> list) {
        ArrayList arrayList = new ArrayList();
        for (GiftDownloadItemEntity giftDownloadItemEntity : list) {
            if (isNeedDownload(giftDownloadItemEntity.giftDirPath)) {
                arrayList.add(giftDownloadItemEntity);
            }
        }
        if (arrayList.size() > 0) {
            GiftDownLoaderImpl.getInstance().startDownLoad(arrayList);
        }
    }

    public synchronized List<GiftDownloadItemEntity> getLocalDownloadList() {
        try {
            List<GiftDownloadItemDBEntity> a = c.a(GiftDownloadItemDBEntity.class);
            if (a == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (GiftDownloadItemDBEntity formatGiftDownloadItemEntity : a) {
                arrayList.add(formatGiftDownloadItemEntity(formatGiftDownloadItemEntity));
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public synchronized void setLocalDownloadList(List<GiftDownloadItemEntity> list) {
        List arrayList = new ArrayList();
        for (GiftDownloadItemEntity formatGiftDownloadItemDBEntity : list) {
            arrayList.add(formatGiftDownloadItemDBEntity(formatGiftDownloadItemDBEntity));
        }
        c.c(arrayList);
    }

    public synchronized void updateLocalDownloadList(List<GiftDownloadItemEntity> list) {
        List list2;
        List<GiftDownloadItemEntity> localDownloadList = getLocalDownloadList();
        if (localDownloadList != null) {
            if (!localDownloadList.isEmpty()) {
                GiftDownloadItemEntity giftDownloadItemEntity;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (GiftDownloadItemEntity giftDownloadItemEntity2 : localDownloadList) {
                    linkedHashMap.put(giftDownloadItemEntity2.typeid, giftDownloadItemEntity2);
                }
                for (GiftDownloadItemEntity giftDownloadItemEntity3 : list2) {
                    if (linkedHashMap.containsKey(giftDownloadItemEntity3.typeid)) {
                        giftDownloadItemEntity2 = (GiftDownloadItemEntity) linkedHashMap.get(giftDownloadItemEntity3.typeid);
                        if (TextUtils.equals(giftDownloadItemEntity3.id, giftDownloadItemEntity2.id) && g.b(b.a(giftDownloadItemEntity2.giftDirPath, giftDownloadItemEntity2.jsonname))) {
                            giftDownloadItemEntity3.giftDirPath = giftDownloadItemEntity2.giftDirPath;
                        }
                    }
                    linkedHashMap2.put(giftDownloadItemEntity3.typeid, giftDownloadItemEntity3);
                }
                linkedHashMap.clear();
                localDownloadList.clear();
                localDownloadList.addAll(linkedHashMap2.values());
                list2 = localDownloadList;
            }
        }
        setLocalDownloadList(list2);
    }

    private boolean isNeedDownload(String str) {
        return TextUtils.isEmpty(str);
    }

    public synchronized void updateDownloadItem(GiftDownloadItemEntity giftDownloadItemEntity) {
        c.a(giftDownloadItemEntity);
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

    public GiftItemEntity getGiftItemEntity(String str) {
        GiftDownloadItemDBEntity e = c.e(str);
        if (e == null) {
            return null;
        }
        if (!g.b(b.a(e.giftDirPath, e.jsonname))) {
            e.giftDirPath = "";
            c.a(formatGiftDownloadItemEntity(e));
        }
        return formatGiftItemEntity(e);
    }

    public String getGiftItemImgUrl(String str) {
        GiftDownloadItemDBEntity e = c.e(str);
        if (e == null) {
            return "";
        }
        return e.imgurl;
    }

    public GiftItemEntity formatGiftItemEntity(GiftDownloadItemEntity giftDownloadItemEntity) {
        GiftItemEntity giftItemEntity = new GiftItemEntity();
        giftItemEntity.id = giftDownloadItemEntity.id;
        giftItemEntity.giftDirPath = giftDownloadItemEntity.giftDirPath;
        giftItemEntity.typeid = giftDownloadItemEntity.typeid;
        giftItemEntity.name = giftDownloadItemEntity.name;
        giftItemEntity.imgurl = giftDownloadItemEntity.imgurl;
        giftItemEntity.price = giftDownloadItemEntity.price;
        giftItemEntity.tomatoPrice = giftDownloadItemEntity.tomatoPrice;
        giftItemEntity.jsonname = giftDownloadItemEntity.jsonname;
        giftItemEntity.zipurl = giftDownloadItemEntity.zipurl;
        giftItemEntity.num = giftDownloadItemEntity.num;
        giftItemEntity.duration = giftDownloadItemEntity.duration;
        giftItemEntity.active_time = giftDownloadItemEntity.active_time;
        giftItemEntity.effect_type = giftDownloadItemEntity.effect_type;
        giftItemEntity.onlineUrl = giftDownloadItemEntity.onlineUrl;
        giftItemEntity.broadcastRange = giftDownloadItemEntity.broadcastRange;
        giftItemEntity.boxType = giftDownloadItemEntity.boxType;
        giftItemEntity.boxId = giftDownloadItemEntity.boxId;
        giftItemEntity.boxName = giftDownloadItemEntity.boxName;
        giftItemEntity.isBroadcast = giftDownloadItemEntity.isBroadcast;
        giftItemEntity.isStayTuned = giftDownloadItemEntity.isStayTuned;
        return giftItemEntity;
    }

    public GiftItemEntity formatGiftItemEntity(GiftDownloadItemDBEntity giftDownloadItemDBEntity) {
        GiftItemEntity giftItemEntity = new GiftItemEntity();
        giftItemEntity.id = giftDownloadItemDBEntity.giftId;
        giftItemEntity.giftDirPath = giftDownloadItemDBEntity.giftDirPath;
        giftItemEntity.typeid = giftDownloadItemDBEntity.typeid;
        giftItemEntity.name = giftDownloadItemDBEntity.name;
        giftItemEntity.imgurl = giftDownloadItemDBEntity.imgurl;
        giftItemEntity.price = giftDownloadItemDBEntity.price;
        giftItemEntity.tomatoPrice = giftDownloadItemDBEntity.tomatoPrice;
        giftItemEntity.jsonname = giftDownloadItemDBEntity.jsonname;
        giftItemEntity.zipurl = giftDownloadItemDBEntity.zipurl;
        giftItemEntity.num = giftDownloadItemDBEntity.num;
        giftItemEntity.duration = giftDownloadItemDBEntity.duration;
        giftItemEntity.active_time = giftDownloadItemDBEntity.active_time;
        giftItemEntity.effect_type = giftDownloadItemDBEntity.effect_type;
        giftItemEntity.onlineUrl = giftDownloadItemDBEntity.onlineUrl;
        giftItemEntity.broadcastRange = giftDownloadItemDBEntity.broadcastRange;
        giftItemEntity.boxType = giftDownloadItemDBEntity.boxType;
        giftItemEntity.boxId = giftDownloadItemDBEntity.boxId;
        giftItemEntity.boxName = giftDownloadItemDBEntity.boxName;
        giftItemEntity.isBroadcast = giftDownloadItemDBEntity.isBroadcast;
        return giftItemEntity;
    }

    public GiftDownloadItemDBEntity formatGiftDownloadItemDBEntity(GiftDownloadItemEntity giftDownloadItemEntity) {
        GiftDownloadItemDBEntity giftDownloadItemDBEntity = new GiftDownloadItemDBEntity();
        giftDownloadItemDBEntity.giftId = giftDownloadItemEntity.id;
        giftDownloadItemDBEntity.giftDirPath = giftDownloadItemEntity.giftDirPath;
        giftDownloadItemDBEntity.typeid = giftDownloadItemEntity.typeid;
        giftDownloadItemDBEntity.name = giftDownloadItemEntity.name;
        giftDownloadItemDBEntity.imgurl = giftDownloadItemEntity.imgurl;
        giftDownloadItemDBEntity.price = giftDownloadItemEntity.price;
        giftDownloadItemDBEntity.tomatoPrice = giftDownloadItemEntity.tomatoPrice;
        giftDownloadItemDBEntity.jsonname = giftDownloadItemEntity.jsonname;
        giftDownloadItemDBEntity.zipurl = giftDownloadItemEntity.zipurl;
        giftDownloadItemDBEntity.num = giftDownloadItemEntity.num;
        giftDownloadItemDBEntity.duration = giftDownloadItemEntity.duration;
        giftDownloadItemDBEntity.active_time = giftDownloadItemEntity.active_time;
        giftDownloadItemDBEntity.effect_type = giftDownloadItemEntity.effect_type;
        giftDownloadItemDBEntity.onlineUrl = giftDownloadItemEntity.onlineUrl;
        giftDownloadItemDBEntity.broadcastRange = giftDownloadItemEntity.broadcastRange;
        giftDownloadItemDBEntity.boxType = giftDownloadItemEntity.boxType;
        giftDownloadItemDBEntity.boxId = giftDownloadItemEntity.boxId;
        giftDownloadItemDBEntity.boxName = giftDownloadItemEntity.boxName;
        giftDownloadItemDBEntity.isBroadcast = giftDownloadItemEntity.isBroadcast;
        return giftDownloadItemDBEntity;
    }

    public GiftDownloadItemEntity formatGiftDownloadItemEntity(GiftDownloadItemDBEntity giftDownloadItemDBEntity) {
        GiftDownloadItemEntity giftDownloadItemEntity = new GiftDownloadItemEntity();
        giftDownloadItemEntity.id = giftDownloadItemDBEntity.giftId;
        giftDownloadItemEntity.giftDirPath = giftDownloadItemDBEntity.giftDirPath;
        giftDownloadItemEntity.typeid = giftDownloadItemDBEntity.typeid;
        giftDownloadItemEntity.name = giftDownloadItemDBEntity.name;
        giftDownloadItemEntity.imgurl = giftDownloadItemDBEntity.imgurl;
        giftDownloadItemEntity.price = giftDownloadItemDBEntity.price;
        giftDownloadItemEntity.tomatoPrice = giftDownloadItemDBEntity.tomatoPrice;
        giftDownloadItemEntity.jsonname = giftDownloadItemDBEntity.jsonname;
        giftDownloadItemEntity.zipurl = giftDownloadItemDBEntity.zipurl;
        giftDownloadItemEntity.num = giftDownloadItemDBEntity.num;
        giftDownloadItemEntity.duration = giftDownloadItemDBEntity.duration;
        giftDownloadItemEntity.active_time = giftDownloadItemDBEntity.active_time;
        giftDownloadItemEntity.effect_type = giftDownloadItemDBEntity.effect_type;
        giftDownloadItemEntity.onlineUrl = giftDownloadItemDBEntity.onlineUrl;
        giftDownloadItemEntity.broadcastRange = giftDownloadItemDBEntity.broadcastRange;
        giftDownloadItemEntity.boxType = giftDownloadItemDBEntity.boxType;
        giftDownloadItemEntity.boxId = giftDownloadItemDBEntity.boxId;
        giftDownloadItemEntity.boxName = giftDownloadItemDBEntity.boxName;
        giftDownloadItemEntity.isBroadcast = giftDownloadItemDBEntity.isBroadcast;
        return giftDownloadItemEntity;
    }

    public boolean checkGiftExist(GiftItemEntity giftItemEntity) {
        return c.a(giftItemEntity);
    }

    public void updateAnimOnlineSingleRes(GiftItemEntity giftItemEntity) {
        if (giftItemEntity != null) {
            GiftDownLoaderImpl.getInstance().downloadFile(giftItemEntity.zipurl, GiftConfig.INSTANCE.animResRootPath, g.h(giftItemEntity.getLocalDirName()), giftItemEntity);
        }
    }

    private String getDownConfigPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GiftConfig.INSTANCE.animResRootPath);
        stringBuilder.append(GiftConfig.INSTANCE.CONFIG_NAME);
        return stringBuilder.toString();
    }
}
