package org.litepal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import java.util.Collection;
import java.util.List;
import org.litepal.crud.DeleteHandler;
import org.litepal.crud.LitePalSupport;
import org.litepal.crud.QueryHandler;
import org.litepal.crud.SaveHandler;
import org.litepal.crud.UpdateHandler;
import org.litepal.crud.async.AverageExecutor;
import org.litepal.crud.async.CountExecutor;
import org.litepal.crud.async.FindExecutor;
import org.litepal.crud.async.FindMultiExecutor;
import org.litepal.crud.async.SaveExecutor;
import org.litepal.crud.async.UpdateOrDeleteExecutor;
import org.litepal.exceptions.LitePalSupportException;
import org.litepal.parser.LitePalAttr;
import org.litepal.parser.LitePalParser;
import org.litepal.tablemanager.Connector;
import org.litepal.tablemanager.callback.DatabaseListener;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;
import org.litepal.util.SharedUtil;
import org.litepal.util.cipher.CipherUtil;

public class Operator {
    private static DatabaseListener dbListener = null;
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static Handler getHandler() {
        return handler;
    }

    public static void initialize(Context context) {
        LitePalApplication.sContext = context;
    }

    public static SQLiteDatabase getDatabase() {
        SQLiteDatabase database;
        synchronized (LitePalSupport.class) {
            database = Connector.getDatabase();
        }
        return database;
    }

    public static void use(LitePalDB litePalDB) {
        synchronized (LitePalSupport.class) {
            LitePalAttr instance = LitePalAttr.getInstance();
            instance.setDbName(litePalDB.getDbName());
            instance.setVersion(litePalDB.getVersion());
            instance.setStorage(litePalDB.getStorage());
            instance.setClassNames(litePalDB.getClassNames());
            if (!isDefaultDatabase(litePalDB.getDbName())) {
                instance.setExtraKeyName(litePalDB.getDbName());
                instance.setCases("lower");
            }
            Connector.clearLitePalOpenHelperInstance();
        }
    }

    public static void useDefault() {
        synchronized (LitePalSupport.class) {
            LitePalAttr.clearInstance();
            Connector.clearLitePalOpenHelperInstance();
        }
    }

    /* JADX WARNING: Missing block: B:14:0x003d, code skipped:
            return r1;
     */
    /* JADX WARNING: Missing block: B:19:0x007a, code skipped:
            return r1;
     */
    public static boolean deleteDatabase(java.lang.String r4) {
        /*
        r0 = org.litepal.crud.LitePalSupport.class;
        monitor-enter(r0);
        r1 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x007e }
        if (r1 != 0) goto L_0x007b;
    L_0x0009:
        r1 = ".db";
        r1 = r4.endsWith(r1);	 Catch:{ all -> 0x007e }
        if (r1 != 0) goto L_0x0022;
    L_0x0011:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007e }
        r1.<init>();	 Catch:{ all -> 0x007e }
        r1.append(r4);	 Catch:{ all -> 0x007e }
        r4 = ".db";
        r1.append(r4);	 Catch:{ all -> 0x007e }
        r4 = r1.toString();	 Catch:{ all -> 0x007e }
    L_0x0022:
        r1 = org.litepal.LitePalApplication.getContext();	 Catch:{ all -> 0x007e }
        r1 = r1.getDatabasePath(r4);	 Catch:{ all -> 0x007e }
        r2 = r1.exists();	 Catch:{ all -> 0x007e }
        if (r2 == 0) goto L_0x003e;
    L_0x0030:
        r1 = r1.delete();	 Catch:{ all -> 0x007e }
        if (r1 == 0) goto L_0x003c;
    L_0x0036:
        removeVersionInSharedPreferences(r4);	 Catch:{ all -> 0x007e }
        org.litepal.tablemanager.Connector.clearLitePalOpenHelperInstance();	 Catch:{ all -> 0x007e }
    L_0x003c:
        monitor-exit(r0);	 Catch:{ all -> 0x007e }
        return r1;
    L_0x003e:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007e }
        r1.<init>();	 Catch:{ all -> 0x007e }
        r2 = org.litepal.LitePalApplication.getContext();	 Catch:{ all -> 0x007e }
        r3 = "";
        r2 = r2.getExternalFilesDir(r3);	 Catch:{ all -> 0x007e }
        r1.append(r2);	 Catch:{ all -> 0x007e }
        r2 = "/databases/";
        r1.append(r2);	 Catch:{ all -> 0x007e }
        r1 = r1.toString();	 Catch:{ all -> 0x007e }
        r2 = new java.io.File;	 Catch:{ all -> 0x007e }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007e }
        r3.<init>();	 Catch:{ all -> 0x007e }
        r3.append(r1);	 Catch:{ all -> 0x007e }
        r3.append(r4);	 Catch:{ all -> 0x007e }
        r1 = r3.toString();	 Catch:{ all -> 0x007e }
        r2.<init>(r1);	 Catch:{ all -> 0x007e }
        r1 = r2.delete();	 Catch:{ all -> 0x007e }
        if (r1 == 0) goto L_0x0079;
    L_0x0073:
        removeVersionInSharedPreferences(r4);	 Catch:{ all -> 0x007e }
        org.litepal.tablemanager.Connector.clearLitePalOpenHelperInstance();	 Catch:{ all -> 0x007e }
    L_0x0079:
        monitor-exit(r0);	 Catch:{ all -> 0x007e }
        return r1;
    L_0x007b:
        r4 = 0;
        monitor-exit(r0);	 Catch:{ all -> 0x007e }
        return r4;
    L_0x007e:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x007e }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.Operator.deleteDatabase(java.lang.String):boolean");
    }

    public static void aesKey(String str) {
        CipherUtil.aesKey = str;
    }

    private static void removeVersionInSharedPreferences(String str) {
        if (isDefaultDatabase(str)) {
            SharedUtil.removeVersion(null);
        } else {
            SharedUtil.removeVersion(str);
        }
    }

    private static boolean isDefaultDatabase(String str) {
        if (!BaseUtility.isLitePalXMLExists()) {
            return false;
        }
        if (!str.endsWith(".db")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(".db");
            str = stringBuilder.toString();
        }
        String dbName = LitePalParser.parseLitePalConfiguration().getDbName();
        if (!dbName.endsWith(".db")) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(dbName);
            stringBuilder2.append(".db");
            dbName = stringBuilder2.toString();
        }
        return str.equalsIgnoreCase(dbName);
    }

    public static FluentQuery select(String... strArr) {
        FluentQuery fluentQuery = new FluentQuery();
        fluentQuery.mColumns = strArr;
        return fluentQuery;
    }

    public static FluentQuery where(String... strArr) {
        FluentQuery fluentQuery = new FluentQuery();
        fluentQuery.mConditions = strArr;
        return fluentQuery;
    }

    public static FluentQuery order(String str) {
        FluentQuery fluentQuery = new FluentQuery();
        fluentQuery.mOrderBy = str;
        return fluentQuery;
    }

    public static FluentQuery limit(int i) {
        FluentQuery fluentQuery = new FluentQuery();
        fluentQuery.mLimit = String.valueOf(i);
        return fluentQuery;
    }

    public static FluentQuery offset(int i) {
        FluentQuery fluentQuery = new FluentQuery();
        fluentQuery.mOffset = String.valueOf(i);
        return fluentQuery;
    }

    public static int count(Class<?> cls) {
        return count(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())));
    }

    public static CountExecutor countAsync(Class<?> cls) {
        return countAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())));
    }

    public static int count(String str) {
        int count;
        synchronized (LitePalSupport.class) {
            count = new FluentQuery().count(str);
        }
        return count;
    }

    public static CountExecutor countAsync(final String str) {
        final CountExecutor countExecutor = new CountExecutor();
        countExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final int count = Operator.count(str);
                    if (countExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                countExecutor.getListener().onFinish(count);
                            }
                        });
                    }
                }
            }
        });
        return countExecutor;
    }

    public static double average(Class<?> cls, String str) {
        return average(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str);
    }

    public static AverageExecutor averageAsync(Class<?> cls, String str) {
        return averageAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str);
    }

    public static double average(String str, String str2) {
        double average;
        synchronized (LitePalSupport.class) {
            average = new FluentQuery().average(str, str2);
        }
        return average;
    }

    public static AverageExecutor averageAsync(final String str, final String str2) {
        final AverageExecutor averageExecutor = new AverageExecutor();
        averageExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final double average = Operator.average(str, str2);
                    if (averageExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                averageExecutor.getListener().onFinish(average);
                            }
                        });
                    }
                }
            }
        });
        return averageExecutor;
    }

    public static <T> T max(Class<?> cls, String str, Class<T> cls2) {
        return max(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, (Class) cls2);
    }

    public static <T> FindExecutor<T> maxAsync(Class<?> cls, String str, Class<T> cls2) {
        return maxAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, (Class) cls2);
    }

    public static <T> T max(String str, String str2, Class<T> cls) {
        Object max;
        synchronized (LitePalSupport.class) {
            max = new FluentQuery().max(str, str2, (Class) cls);
        }
        return max;
    }

    public static <T> FindExecutor<T> maxAsync(final String str, final String str2, final Class<T> cls) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final Object max = Operator.max(str, str2, cls);
                    if (findExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(max);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public static <T> T min(Class<?> cls, String str, Class<T> cls2) {
        return min(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, (Class) cls2);
    }

    public static <T> FindExecutor<T> minAsync(Class<?> cls, String str, Class<T> cls2) {
        return minAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, (Class) cls2);
    }

    public static <T> T min(String str, String str2, Class<T> cls) {
        Object min;
        synchronized (LitePalSupport.class) {
            min = new FluentQuery().min(str, str2, (Class) cls);
        }
        return min;
    }

    public static <T> FindExecutor<T> minAsync(final String str, final String str2, final Class<T> cls) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final Object min = Operator.min(str, str2, cls);
                    if (findExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(min);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public static <T> T sum(Class<?> cls, String str, Class<T> cls2) {
        return sum(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, (Class) cls2);
    }

    public static <T> FindExecutor<T> sumAsync(Class<?> cls, String str, Class<T> cls2) {
        return sumAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), str, (Class) cls2);
    }

    public static <T> T sum(String str, String str2, Class<T> cls) {
        Object sum;
        synchronized (LitePalSupport.class) {
            sum = new FluentQuery().sum(str, str2, (Class) cls);
        }
        return sum;
    }

    public static <T> FindExecutor<T> sumAsync(final String str, final String str2, final Class<T> cls) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final Object sum = Operator.sum(str, str2, cls);
                    if (findExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(sum);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public static <T> T find(Class<T> cls, long j) {
        return find(cls, j, false);
    }

    public static <T> FindExecutor<T> findAsync(Class<T> cls, long j) {
        return findAsync(cls, j, false);
    }

    public static <T> T find(Class<T> cls, long j, boolean z) {
        Object onFind;
        synchronized (LitePalSupport.class) {
            onFind = new QueryHandler(Connector.getDatabase()).onFind(cls, j, z);
        }
        return onFind;
    }

    public static <T> FindExecutor<T> findAsync(Class<T> cls, long j, boolean z) {
        FindExecutor findExecutor = new FindExecutor();
        final Class<T> cls2 = cls;
        final long j2 = j;
        final boolean z2 = z;
        final FindExecutor findExecutor2 = findExecutor;
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final Object find = Operator.find(cls2, j2, z2);
                    if (findExecutor2.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor2.getListener().onFinish(find);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public static <T> T findFirst(Class<T> cls) {
        return findFirst(cls, false);
    }

    public static <T> FindExecutor<T> findFirstAsync(Class<T> cls) {
        return findFirstAsync(cls, false);
    }

    public static <T> T findFirst(Class<T> cls, boolean z) {
        Object onFindFirst;
        synchronized (LitePalSupport.class) {
            onFindFirst = new QueryHandler(Connector.getDatabase()).onFindFirst(cls, z);
        }
        return onFindFirst;
    }

    public static <T> FindExecutor<T> findFirstAsync(final Class<T> cls, final boolean z) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final Object findFirst = Operator.findFirst(cls, z);
                    if (findExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(findFirst);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public static <T> T findLast(Class<T> cls) {
        return findLast(cls, false);
    }

    public static <T> FindExecutor<T> findLastAsync(Class<T> cls) {
        return findLastAsync(cls, false);
    }

    public static <T> T findLast(Class<T> cls, boolean z) {
        Object onFindLast;
        synchronized (LitePalSupport.class) {
            onFindLast = new QueryHandler(Connector.getDatabase()).onFindLast(cls, z);
        }
        return onFindLast;
    }

    public static <T> FindExecutor<T> findLastAsync(final Class<T> cls, final boolean z) {
        final FindExecutor findExecutor = new FindExecutor();
        findExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final Object findLast = Operator.findLast(cls, z);
                    if (findExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                findExecutor.getListener().onFinish(findLast);
                            }
                        });
                    }
                }
            }
        });
        return findExecutor;
    }

    public static <T> List<T> findAll(Class<T> cls, long... jArr) {
        return findAll(cls, false, jArr);
    }

    public static <T> FindMultiExecutor<T> findAllAsync(Class<T> cls, long... jArr) {
        return findAllAsync(cls, false, jArr);
    }

    public static <T> List<T> findAll(Class<T> cls, boolean z, long... jArr) {
        List onFindAll;
        synchronized (LitePalSupport.class) {
            onFindAll = new QueryHandler(Connector.getDatabase()).onFindAll(cls, z, jArr);
        }
        return onFindAll;
    }

    public static <T> FindMultiExecutor<T> findAllAsync(final Class<T> cls, final boolean z, final long... jArr) {
        final FindMultiExecutor findMultiExecutor = new FindMultiExecutor();
        findMultiExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final List findAll = Operator.findAll(cls, z, jArr);
                    if (findMultiExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                findMultiExecutor.getListener().onFinish(findAll);
                            }
                        });
                    }
                }
            }
        });
        return findMultiExecutor;
    }

    public static Cursor findBySQL(String... strArr) {
        synchronized (LitePalSupport.class) {
            BaseUtility.checkConditionsCorrect(strArr);
            String[] strArr2 = null;
            if (strArr == null) {
                return null;
            } else if (strArr.length <= 0) {
                return null;
            } else {
                if (strArr.length != 1) {
                    strArr2 = new String[(strArr.length - 1)];
                    System.arraycopy(strArr, 1, strArr2, 0, strArr.length - 1);
                }
                Cursor rawQuery = Connector.getDatabase().rawQuery(strArr[0], strArr2);
                return rawQuery;
            }
        }
    }

    public static int delete(Class<?> cls, long j) {
        int onDelete;
        synchronized (LitePalSupport.class) {
            SQLiteDatabase database = Connector.getDatabase();
            database.beginTransaction();
            try {
                onDelete = new DeleteHandler(database).onDelete(cls, j);
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
        }
        return onDelete;
    }

    public static UpdateOrDeleteExecutor deleteAsync(final Class<?> cls, final long j) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final int delete = Operator.delete(cls, j);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(delete);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static int deleteAll(Class<?> cls, String... strArr) {
        int onDeleteAll;
        synchronized (LitePalSupport.class) {
            onDeleteAll = new DeleteHandler(Connector.getDatabase()).onDeleteAll((Class) cls, strArr);
        }
        return onDeleteAll;
    }

    public static UpdateOrDeleteExecutor deleteAllAsync(final Class<?> cls, final String... strArr) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final int deleteAll = Operator.deleteAll(cls, strArr);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(deleteAll);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static int deleteAll(String str, String... strArr) {
        int onDeleteAll;
        synchronized (LitePalSupport.class) {
            onDeleteAll = new DeleteHandler(Connector.getDatabase()).onDeleteAll(str, strArr);
        }
        return onDeleteAll;
    }

    public static UpdateOrDeleteExecutor deleteAllAsync(final String str, final String... strArr) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final int deleteAll = Operator.deleteAll(str, strArr);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(deleteAll);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static int update(Class<?> cls, ContentValues contentValues, long j) {
        int onUpdate;
        synchronized (LitePalSupport.class) {
            onUpdate = new UpdateHandler(Connector.getDatabase()).onUpdate(cls, j, contentValues);
        }
        return onUpdate;
    }

    public static UpdateOrDeleteExecutor updateAsync(Class<?> cls, ContentValues contentValues, long j) {
        UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        final Class<?> cls2 = cls;
        final ContentValues contentValues2 = contentValues;
        final long j2 = j;
        final UpdateOrDeleteExecutor updateOrDeleteExecutor2 = updateOrDeleteExecutor;
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final int update = Operator.update(cls2, contentValues2, j2);
                    if (updateOrDeleteExecutor2.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor2.getListener().onFinish(update);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static int updateAll(Class<?> cls, ContentValues contentValues, String... strArr) {
        return updateAll(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), contentValues, strArr);
    }

    public static UpdateOrDeleteExecutor updateAllAsync(Class<?> cls, ContentValues contentValues, String... strArr) {
        return updateAllAsync(BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName())), contentValues, strArr);
    }

    public static int updateAll(String str, ContentValues contentValues, String... strArr) {
        int onUpdateAll;
        synchronized (LitePalSupport.class) {
            onUpdateAll = new UpdateHandler(Connector.getDatabase()).onUpdateAll(str, contentValues, strArr);
        }
        return onUpdateAll;
    }

    public static UpdateOrDeleteExecutor updateAllAsync(final String str, final ContentValues contentValues, final String... strArr) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final int updateAll = Operator.updateAll(str, contentValues, strArr);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(updateAll);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public static <T extends LitePalSupport> void saveAll(Collection<T> collection) {
        synchronized (LitePalSupport.class) {
            SQLiteDatabase database = Connector.getDatabase();
            database.beginTransaction();
            try {
                new SaveHandler(database).onSaveAll(collection);
                database.setTransactionSuccessful();
                database.endTransaction();
            } catch (Exception e) {
                throw new LitePalSupportException(e.getMessage(), e);
            } catch (Throwable th) {
                database.endTransaction();
            }
        }
    }

    public static <T extends LitePalSupport> SaveExecutor saveAllAsync(final Collection<T> collection) {
        final SaveExecutor saveExecutor = new SaveExecutor();
        saveExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    boolean z;
                    try {
                        Operator.saveAll(collection);
                        z = true;
                    } catch (Exception unused) {
                        z = false;
                    }
                    if (saveExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                saveExecutor.getListener().onFinish(z);
                            }
                        });
                    }
                }
            }
        });
        return saveExecutor;
    }

    public static <T extends LitePalSupport> void markAsDeleted(Collection<T> collection) {
        for (T clearSavedState : collection) {
            clearSavedState.clearSavedState();
        }
    }

    public static <T> boolean isExist(Class<T> cls, String... strArr) {
        return strArr != null && where(strArr).count((Class) cls) > 0;
    }

    public static void registerDatabaseListener(DatabaseListener databaseListener) {
        dbListener = databaseListener;
    }

    public static DatabaseListener getDBListener() {
        return dbListener;
    }
}
