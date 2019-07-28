package com.tomatolive.library.utils.litepal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tomatolive.library.utils.litepal.crud.LitePalSupport;
import com.tomatolive.library.utils.litepal.crud.async.AverageExecutor;
import com.tomatolive.library.utils.litepal.crud.async.CountExecutor;
import com.tomatolive.library.utils.litepal.crud.async.FindExecutor;
import com.tomatolive.library.utils.litepal.crud.async.FindMultiExecutor;
import com.tomatolive.library.utils.litepal.crud.async.SaveExecutor;
import com.tomatolive.library.utils.litepal.crud.async.UpdateOrDeleteExecutor;
import com.tomatolive.library.utils.litepal.tablemanager.callback.DatabaseListener;
import java.util.Collection;
import java.util.List;

public class LitePal {
    public static void initialize(Context context) {
        Operator.initialize(context);
    }

    public static SQLiteDatabase getDatabase() {
        return Operator.getDatabase();
    }

    public static void use(LitePalDB litePalDB) {
        Operator.use(litePalDB);
    }

    public static void useDefault() {
        Operator.useDefault();
    }

    public static boolean deleteDatabase(String str) {
        return Operator.deleteDatabase(str);
    }

    public static void aesKey(String str) {
        Operator.aesKey(str);
    }

    public static FluentQuery select(String... strArr) {
        return Operator.select(strArr);
    }

    public static FluentQuery where(String... strArr) {
        return Operator.where(strArr);
    }

    public static FluentQuery order(String str) {
        return Operator.order(str);
    }

    public static FluentQuery limit(int i) {
        return Operator.limit(i);
    }

    public static FluentQuery offset(int i) {
        return Operator.offset(i);
    }

    public static int count(Class<?> cls) {
        return Operator.count((Class) cls);
    }

    public static CountExecutor countAsync(Class<?> cls) {
        return Operator.countAsync((Class) cls);
    }

    public static int count(String str) {
        return Operator.count(str);
    }

    public static CountExecutor countAsync(String str) {
        return Operator.countAsync(str);
    }

    public static double average(Class<?> cls, String str) {
        return Operator.average((Class) cls, str);
    }

    public static AverageExecutor averageAsync(Class<?> cls, String str) {
        return Operator.averageAsync((Class) cls, str);
    }

    public static double average(String str, String str2) {
        return Operator.average(str, str2);
    }

    public static AverageExecutor averageAsync(String str, String str2) {
        return Operator.averageAsync(str, str2);
    }

    public static <T> T max(Class<?> cls, String str, Class<T> cls2) {
        return Operator.max((Class) cls, str, (Class) cls2);
    }

    public static <T> FindExecutor<T> maxAsync(Class<?> cls, String str, Class<T> cls2) {
        return Operator.maxAsync((Class) cls, str, (Class) cls2);
    }

    public static <T> T max(String str, String str2, Class<T> cls) {
        return Operator.max(str, str2, (Class) cls);
    }

    public static <T> FindExecutor<T> maxAsync(String str, String str2, Class<T> cls) {
        return Operator.maxAsync(str, str2, (Class) cls);
    }

    public static <T> T min(Class<?> cls, String str, Class<T> cls2) {
        return Operator.min((Class) cls, str, (Class) cls2);
    }

    public static <T> FindExecutor<T> minAsync(Class<?> cls, String str, Class<T> cls2) {
        return Operator.minAsync((Class) cls, str, (Class) cls2);
    }

    public static <T> T min(String str, String str2, Class<T> cls) {
        return Operator.min(str, str2, (Class) cls);
    }

    public static <T> FindExecutor<T> minAsync(String str, String str2, Class<T> cls) {
        return Operator.minAsync(str, str2, (Class) cls);
    }

    public static <T> T sum(Class<?> cls, String str, Class<T> cls2) {
        return Operator.sum((Class) cls, str, (Class) cls2);
    }

    public static <T> FindExecutor<T> sumAsync(Class<?> cls, String str, Class<T> cls2) {
        return Operator.sumAsync((Class) cls, str, (Class) cls2);
    }

    public static <T> T sum(String str, String str2, Class<T> cls) {
        return Operator.sum(str, str2, (Class) cls);
    }

    public static <T> FindExecutor<T> sumAsync(String str, String str2, Class<T> cls) {
        return Operator.sumAsync(str, str2, (Class) cls);
    }

    public static <T> T find(Class<T> cls, long j) {
        return Operator.find(cls, j);
    }

    public static <T> FindExecutor<T> findAsync(Class<T> cls, long j) {
        return Operator.findAsync(cls, j);
    }

    public static <T> T find(Class<T> cls, long j, boolean z) {
        return Operator.find(cls, j, z);
    }

    public static <T> FindExecutor<T> findAsync(Class<T> cls, long j, boolean z) {
        return Operator.findAsync(cls, j, z);
    }

    public static <T> T findFirst(Class<T> cls) {
        return Operator.findFirst(cls);
    }

    public static <T> FindExecutor<T> findFirstAsync(Class<T> cls) {
        return Operator.findFirstAsync(cls);
    }

    public static <T> T findFirst(Class<T> cls, boolean z) {
        return Operator.findFirst(cls, z);
    }

    public static <T> FindExecutor<T> findFirstAsync(Class<T> cls, boolean z) {
        return Operator.findFirstAsync(cls, z);
    }

    public static <T> T findLast(Class<T> cls) {
        return Operator.findLast(cls);
    }

    public static <T> FindExecutor<T> findLastAsync(Class<T> cls) {
        return Operator.findLastAsync(cls);
    }

    public static <T> T findLast(Class<T> cls, boolean z) {
        return Operator.findLast(cls, z);
    }

    public static <T> FindExecutor<T> findLastAsync(Class<T> cls, boolean z) {
        return Operator.findLastAsync(cls, z);
    }

    public static <T> List<T> findAll(Class<T> cls, long... jArr) {
        return Operator.findAll(cls, jArr);
    }

    public static <T> FindMultiExecutor<T> findAllAsync(Class<T> cls, long... jArr) {
        return Operator.findAllAsync(cls, jArr);
    }

    public static <T> List<T> findAll(Class<T> cls, boolean z, long... jArr) {
        return Operator.findAll(cls, z, jArr);
    }

    public static <T> FindMultiExecutor<T> findAllAsync(Class<T> cls, boolean z, long... jArr) {
        return Operator.findAllAsync(cls, z, jArr);
    }

    public static Cursor findBySQL(String... strArr) {
        return Operator.findBySQL(strArr);
    }

    public static int delete(Class<?> cls, long j) {
        return Operator.delete(cls, j);
    }

    public static UpdateOrDeleteExecutor deleteAsync(Class<?> cls, long j) {
        return Operator.deleteAsync(cls, j);
    }

    public static int deleteAll(Class<?> cls, String... strArr) {
        return Operator.deleteAll((Class) cls, strArr);
    }

    public static UpdateOrDeleteExecutor deleteAllAsync(Class<?> cls, String... strArr) {
        return Operator.deleteAllAsync((Class) cls, strArr);
    }

    public static int deleteAll(String str, String... strArr) {
        return Operator.deleteAll(str, strArr);
    }

    public static UpdateOrDeleteExecutor deleteAllAsync(String str, String... strArr) {
        return Operator.deleteAllAsync(str, strArr);
    }

    public static int update(Class<?> cls, ContentValues contentValues, long j) {
        return Operator.update(cls, contentValues, j);
    }

    public static UpdateOrDeleteExecutor updateAsync(Class<?> cls, ContentValues contentValues, long j) {
        return Operator.updateAsync(cls, contentValues, j);
    }

    public static int updateAll(Class<?> cls, ContentValues contentValues, String... strArr) {
        return Operator.updateAll((Class) cls, contentValues, strArr);
    }

    public static UpdateOrDeleteExecutor updateAllAsync(Class<?> cls, ContentValues contentValues, String... strArr) {
        return Operator.updateAllAsync((Class) cls, contentValues, strArr);
    }

    public static int updateAll(String str, ContentValues contentValues, String... strArr) {
        return Operator.updateAll(str, contentValues, strArr);
    }

    public static UpdateOrDeleteExecutor updateAllAsync(String str, ContentValues contentValues, String... strArr) {
        return Operator.updateAllAsync(str, contentValues, strArr);
    }

    public static <T extends LitePalSupport> void saveAll(Collection<T> collection) {
        Operator.saveAll(collection);
    }

    public static <T extends LitePalSupport> SaveExecutor saveAllAsync(Collection<T> collection) {
        return Operator.saveAllAsync(collection);
    }

    public static <T extends LitePalSupport> void markAsDeleted(Collection<T> collection) {
        Operator.markAsDeleted(collection);
    }

    public static <T> boolean isExist(Class<T> cls, String... strArr) {
        return Operator.isExist(cls, strArr);
    }

    public static void registerDatabaseListener(DatabaseListener databaseListener) {
        Operator.registerDatabaseListener(databaseListener);
    }
}
