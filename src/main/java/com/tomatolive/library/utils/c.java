package com.tomatolive.library.utils;

import android.content.ContentValues;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.model.db.GiftBoxEntity;
import com.tomatolive.library.model.db.GiftDownloadItemDBEntity;
import com.tomatolive.library.model.db.SearchKeywordEntity;
import com.tomatolive.library.model.db.StickerEntity;
import com.tomatolive.library.model.db.WatchRecordEntity;
import com.tomatolive.library.utils.litepal.LitePal;
import com.tomatolive.library.utils.litepal.crud.LitePalSupport;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DBUtils */
public class c {
    public static <T extends LitePalSupport> List<T> a(Class<T> cls) {
        return LitePal.findAll(cls, new long[0]);
    }

    public static <T extends LitePalSupport> List<T> a(Class<T> cls, String... strArr) {
        return LitePal.where(strArr).find(cls);
    }

    public static <T extends LitePalSupport> List<T> a(Class<T> cls, String str, String... strArr) {
        return LitePal.where(strArr).order(str).find(cls);
    }

    public static <T extends LitePalSupport> List<T> a(Class<T> cls, String str) {
        return LitePal.order(str).find(cls);
    }

    public static <T extends LitePalSupport> void a(List<T> list) {
        LitePal.saveAll(list);
    }

    public static <T extends LitePalSupport> int b(Class<T> cls) {
        return LitePal.deleteAll((Class) cls, new String[0]);
    }

    public static <T extends LitePalSupport> int b(Class<T> cls, String... strArr) {
        return LitePal.deleteAll((Class) cls, strArr);
    }

    public static <T extends LitePalSupport> int a(Class<T> cls, String str, long j, String... strArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, Long.valueOf(j));
        return LitePal.updateAll((Class) cls, contentValues, strArr);
    }

    public static <T extends LitePalSupport> T c(Class<T> cls, String... strArr) {
        List find = LitePal.where(strArr).limit(1).find(cls);
        return (find == null || find.size() == 0) ? null : (LitePalSupport) find.get(0);
    }

    public static void a(String str) {
        if (((SearchKeywordEntity) c(SearchKeywordEntity.class, "keyword = ?", str)) == null) {
            new SearchKeywordEntity(System.currentTimeMillis(), str).save();
            return;
        }
        a(SearchKeywordEntity.class, "insertTime", System.currentTimeMillis(), "keyword = ?", str);
    }

    public static void a(WatchRecordEntity watchRecordEntity) {
        if (((WatchRecordEntity) c(WatchRecordEntity.class, "liveId = ? and userId = ?", watchRecordEntity.liveId, z.a().c())) == null) {
            watchRecordEntity.save();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", watchRecordEntity.userId);
        contentValues.put("liveId", watchRecordEntity.liveId);
        contentValues.put("title", watchRecordEntity.title);
        contentValues.put("label", watchRecordEntity.label);
        contentValues.put("anchorNickname", watchRecordEntity.anchorNickname);
        contentValues.put("liveTime", Long.valueOf(watchRecordEntity.liveTime));
        LitePal.updateAll(WatchRecordEntity.class, contentValues, "liveId = ? and userId = ?", watchRecordEntity.liveId, z.a().c());
    }

    public static void b(List<StickerEntity> list) {
        a();
        a((List) list);
    }

    public static void a() {
        b(StickerEntity.class, "userId = ?", z.a().c());
    }

    public static List<StickerEntity> b() {
        return a(StickerEntity.class, "userId = ?", z.a().c());
    }

    public static StickerEntity b(String str) {
        return (StickerEntity) c(StickerEntity.class, "uuID = ? and userId = ?", str, z.a().c());
    }

    public static int c() {
        return b().size();
    }

    public static List<String> c(String str) {
        ArrayList arrayList = new ArrayList();
        for (GiftBoxEntity giftBoxEntity : a(GiftBoxEntity.class, "userId = ? and liveId = ?", z.a().c(), str)) {
            arrayList.add(giftBoxEntity.giftBoxUniqueCode);
        }
        return arrayList;
    }

    public static void d(String str) {
        b(GiftBoxEntity.class, "userId = ? and liveId = ?", z.a().c(), str);
    }

    public static void a(GiftBoxEntity giftBoxEntity) {
        if (giftBoxEntity != null) {
            giftBoxEntity.save();
        }
    }

    public static void c(List<GiftDownloadItemDBEntity> list) {
        try {
            b(GiftDownloadItemDBEntity.class);
            a((List) list);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("数据库已保存：");
            stringBuilder.append(list);
            m.a(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(GiftDownloadItemEntity giftDownloadItemEntity) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("giftDirPath", giftDownloadItemEntity.giftDirPath);
            LitePal.updateAll(GiftDownloadItemDBEntity.class, contentValues, "giftId = ? and typeid = ?", giftDownloadItemEntity.id, giftDownloadItemEntity.typeid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean a(GiftItemEntity giftItemEntity) {
        boolean z = false;
        try {
            if (((GiftDownloadItemDBEntity) c(GiftDownloadItemDBEntity.class, "giftId = ? and typeid = ?", giftItemEntity.id, giftItemEntity.typeid)) != null) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    public static GiftDownloadItemDBEntity e(String str) {
        return (GiftDownloadItemDBEntity) c(GiftDownloadItemDBEntity.class, "typeid = ?", str);
    }
}
