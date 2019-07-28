package org.xutils.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;
import org.xutils.DbManager.DbOpenListener;
import org.xutils.DbManager.DbUpgradeListener;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.SqlInfoBuilder;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.DbBase;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

public final class DbManagerImpl extends DbBase {
    private static final HashMap<DaoConfig, DbManagerImpl> a = new HashMap();
    private SQLiteDatabase b;
    private DaoConfig c;
    private boolean d;

    private DbManagerImpl(DaoConfig daoConfig) {
        if (daoConfig != null) {
            this.c = daoConfig;
            this.d = daoConfig.isAllowTransaction();
            this.b = a(daoConfig);
            DbOpenListener dbOpenListener = daoConfig.getDbOpenListener();
            if (dbOpenListener != null) {
                dbOpenListener.onDbOpened(this);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("daoConfig may not be null");
    }

    public static synchronized DbManager getInstance(DaoConfig daoConfig) {
        DbManagerImpl dbManagerImpl;
        synchronized (DbManagerImpl.class) {
            Object daoConfig2;
            if (daoConfig2 == null) {
                try {
                    daoConfig2 = new DaoConfig();
                } catch (DbException e) {
                    LogUtil.e(e.getMessage(), e);
                } catch (Throwable th) {
                }
            }
            dbManagerImpl = (DbManagerImpl) a.get(daoConfig2);
            if (dbManagerImpl == null) {
                dbManagerImpl = new DbManagerImpl(daoConfig2);
                a.put(daoConfig2, dbManagerImpl);
            } else {
                dbManagerImpl.c = daoConfig2;
            }
            SQLiteDatabase sQLiteDatabase = dbManagerImpl.b;
            int version = sQLiteDatabase.getVersion();
            int dbVersion = daoConfig2.getDbVersion();
            if (version != dbVersion) {
                if (version != 0) {
                    DbUpgradeListener dbUpgradeListener = daoConfig2.getDbUpgradeListener();
                    if (dbUpgradeListener != null) {
                        dbUpgradeListener.onUpgrade(dbManagerImpl, version, dbVersion);
                    } else {
                        dbManagerImpl.dropDb();
                    }
                }
                sQLiteDatabase.setVersion(dbVersion);
            }
        }
        return dbManagerImpl;
    }

    public SQLiteDatabase getDatabase() {
        return this.b;
    }

    public DaoConfig getDaoConfig() {
        return this.c;
    }

    public void saveOrUpdate(Object obj) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    createTableIfNotExist(table);
                    for (Object a : list) {
                        a(table, a);
                    }
                } else {
                    return;
                }
            }
            table = getTable(obj.getClass());
            createTableIfNotExist(table);
            a(table, obj);
            b();
            c();
        } finally {
            c();
        }
    }

    public void replace(Object obj) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    createTableIfNotExist(table);
                    for (Object buildReplaceSqlInfo : list) {
                        execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(table, buildReplaceSqlInfo));
                    }
                } else {
                    return;
                }
            }
            table = getTable(obj.getClass());
            createTableIfNotExist(table);
            execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(table, obj));
            b();
            c();
        } finally {
            c();
        }
    }

    public void save(Object obj) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    createTableIfNotExist(table);
                    for (Object buildInsertSqlInfo : list) {
                        execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(table, buildInsertSqlInfo));
                    }
                } else {
                    return;
                }
            }
            table = getTable(obj.getClass());
            createTableIfNotExist(table);
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(table, obj));
            b();
            c();
        } finally {
            c();
        }
    }

    public boolean saveBindingId(Object obj) throws DbException {
        try {
            a();
            boolean z = false;
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (list.isEmpty()) {
                    return z;
                }
                table = getTable(list.get(z).getClass());
                createTableIfNotExist(table);
                for (Object b : list) {
                    if (!b(table, b)) {
                        throw new DbException("saveBindingId error, transaction will not commit!");
                    }
                }
            } else {
                table = getTable(obj.getClass());
                createTableIfNotExist(table);
                z = b(table, obj);
            }
            b();
            c();
            return z;
        } finally {
            c();
        }
    }

    public void deleteById(Class<?> cls, Object obj) throws DbException {
        TableEntity table = getTable(cls);
        if (table.tableIsExist()) {
            try {
                a();
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfoById(table, obj));
                b();
            } finally {
                c();
            }
        }
    }

    public void delete(Object obj) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    if (table.tableIsExist()) {
                        for (Object buildDeleteSqlInfo : list) {
                            execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(table, buildDeleteSqlInfo));
                        }
                    } else {
                        c();
                        return;
                    }
                }
                return;
            }
            table = getTable(obj.getClass());
            if (table.tableIsExist()) {
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(table, obj));
            } else {
                c();
                return;
            }
            b();
            c();
        } finally {
            c();
        }
    }

    public void delete(Class<?> cls) throws DbException {
        delete(cls, null);
    }

    public int delete(Class<?> cls, WhereBuilder whereBuilder) throws DbException {
        TableEntity table = getTable(cls);
        if (!table.tableIsExist()) {
            return 0;
        }
        try {
            a();
            int executeUpdateDelete = executeUpdateDelete(SqlInfoBuilder.buildDeleteSqlInfo(table, whereBuilder));
            b();
            return executeUpdateDelete;
        } finally {
            c();
        }
    }

    public void update(Object obj, String... strArr) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    if (table.tableIsExist()) {
                        for (Object buildUpdateSqlInfo : list) {
                            execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(table, buildUpdateSqlInfo, strArr));
                        }
                    } else {
                        c();
                        return;
                    }
                }
                return;
            }
            table = getTable(obj.getClass());
            if (table.tableIsExist()) {
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(table, obj, strArr));
            } else {
                c();
                return;
            }
            b();
            c();
        } finally {
            c();
        }
    }

    public int update(Class<?> cls, WhereBuilder whereBuilder, KeyValue... keyValueArr) throws DbException {
        TableEntity table = getTable(cls);
        if (!table.tableIsExist()) {
            return 0;
        }
        try {
            a();
            int executeUpdateDelete = executeUpdateDelete(SqlInfoBuilder.buildUpdateSqlInfo(table, whereBuilder, keyValueArr));
            b();
            return executeUpdateDelete;
        } finally {
            c();
        }
    }

    public <T> T findById(Class<T> cls, Object obj) throws DbException {
        TableEntity table = getTable(cls);
        if (!table.tableIsExist()) {
            return null;
        }
        Cursor execQuery = execQuery(Selector.a(table).where(table.getId().getName(), "=", obj).limit(1).toString());
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    Object a = a.a(table, execQuery);
                    IOUtil.closeQuietly(execQuery);
                    return a;
                }
                IOUtil.closeQuietly(execQuery);
            } catch (Throwable th) {
                IOUtil.closeQuietly(execQuery);
            }
        }
        return null;
    }

    public <T> T findFirst(Class<T> cls) throws DbException {
        return selector(cls).findFirst();
    }

    public <T> List<T> findAll(Class<T> cls) throws DbException {
        return selector(cls).findAll();
    }

    public <T> Selector<T> selector(Class<T> cls) throws DbException {
        return Selector.a(getTable(cls));
    }

    public DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException {
        Cursor execQuery = execQuery(sqlInfo);
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    DbModel a = a.a(execQuery);
                    IOUtil.closeQuietly(execQuery);
                    return a;
                }
                IOUtil.closeQuietly(execQuery);
            } catch (Throwable th) {
                IOUtil.closeQuietly(execQuery);
            }
        }
        return null;
    }

    public List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException {
        ArrayList arrayList = new ArrayList();
        Cursor execQuery = execQuery(sqlInfo);
        if (execQuery != null) {
            while (execQuery.moveToNext()) {
                try {
                    arrayList.add(a.a(execQuery));
                } catch (Throwable th) {
                    IOUtil.closeQuietly(execQuery);
                }
            }
            IOUtil.closeQuietly(execQuery);
        }
        return arrayList;
    }

    private SQLiteDatabase a(DaoConfig daoConfig) {
        File dbDir = daoConfig.getDbDir();
        if (dbDir == null || (!dbDir.exists() && !dbDir.mkdirs())) {
            return x.app().openOrCreateDatabase(daoConfig.getDbName(), 0, null);
        }
        return SQLiteDatabase.openOrCreateDatabase(new File(dbDir, daoConfig.getDbName()), null);
    }

    private void a(TableEntity<?> tableEntity, Object obj) throws DbException {
        ColumnEntity id = tableEntity.getId();
        if (!id.isAutoId()) {
            execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(tableEntity, obj));
        } else if (id.getColumnValue(obj) != null) {
            execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo((TableEntity) tableEntity, obj, new String[0]));
        } else {
            b(tableEntity, obj);
        }
    }

    private boolean b(TableEntity<?> tableEntity, Object obj) throws DbException {
        ColumnEntity id = tableEntity.getId();
        if (id.isAutoId()) {
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(tableEntity, obj));
            long a = a(tableEntity.getName());
            if (a == -1) {
                return false;
            }
            id.setAutoIdValue(obj, a);
            return true;
        }
        execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(tableEntity, obj));
        return true;
    }

    private long a(String str) throws DbException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT seq FROM sqlite_sequence WHERE name='");
        stringBuilder.append(str);
        stringBuilder.append("' LIMIT 1");
        Cursor execQuery = execQuery(stringBuilder.toString());
        long j = -1;
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    j = execQuery.getLong(0);
                }
                IOUtil.closeQuietly(execQuery);
            } catch (Throwable th) {
                IOUtil.closeQuietly(execQuery);
            }
        }
        return j;
    }

    public void close() throws IOException {
        if (a.containsKey(this.c)) {
            a.remove(this.c);
            this.b.close();
        }
    }

    private void a() {
        if (!this.d) {
            return;
        }
        if (VERSION.SDK_INT < 16 || !this.b.isWriteAheadLoggingEnabled()) {
            this.b.beginTransaction();
        } else {
            this.b.beginTransactionNonExclusive();
        }
    }

    private void b() {
        if (this.d) {
            this.b.setTransactionSuccessful();
        }
    }

    private void c() {
        if (this.d) {
            this.b.endTransaction();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002f A:{SYNTHETIC, Splitter:B:21:0x002f} */
    public int executeUpdateDelete(org.xutils.db.sqlite.SqlInfo r4) throws org.xutils.ex.DbException {
        /*
        r3 = this;
        r0 = 0;
        r1 = r3.b;	 Catch:{ Throwable -> 0x0026 }
        r4 = r4.buildStatement(r1);	 Catch:{ Throwable -> 0x0026 }
        r0 = r4.executeUpdateDelete();	 Catch:{ Throwable -> 0x001f, all -> 0x001a }
        if (r4 == 0) goto L_0x0019;
    L_0x000d:
        r4.releaseReference();	 Catch:{ Throwable -> 0x0011 }
        goto L_0x0019;
    L_0x0011:
        r4 = move-exception;
        r1 = r4.getMessage();
        org.xutils.common.util.LogUtil.e(r1, r4);
    L_0x0019:
        return r0;
    L_0x001a:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
        goto L_0x002d;
    L_0x001f:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
        goto L_0x0027;
    L_0x0024:
        r4 = move-exception;
        goto L_0x002d;
    L_0x0026:
        r4 = move-exception;
    L_0x0027:
        r1 = new org.xutils.ex.DbException;	 Catch:{ all -> 0x0024 }
        r1.<init>(r4);	 Catch:{ all -> 0x0024 }
        throw r1;	 Catch:{ all -> 0x0024 }
    L_0x002d:
        if (r0 == 0) goto L_0x003b;
    L_0x002f:
        r0.releaseReference();	 Catch:{ Throwable -> 0x0033 }
        goto L_0x003b;
    L_0x0033:
        r0 = move-exception;
        r1 = r0.getMessage();
        org.xutils.common.util.LogUtil.e(r1, r0);
    L_0x003b:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.db.DbManagerImpl.executeUpdateDelete(org.xutils.db.sqlite.SqlInfo):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002f A:{SYNTHETIC, Splitter:B:21:0x002f} */
    public int executeUpdateDelete(java.lang.String r4) throws org.xutils.ex.DbException {
        /*
        r3 = this;
        r0 = 0;
        r1 = r3.b;	 Catch:{ Throwable -> 0x0026 }
        r4 = r1.compileStatement(r4);	 Catch:{ Throwable -> 0x0026 }
        r0 = r4.executeUpdateDelete();	 Catch:{ Throwable -> 0x001f, all -> 0x001a }
        if (r4 == 0) goto L_0x0019;
    L_0x000d:
        r4.releaseReference();	 Catch:{ Throwable -> 0x0011 }
        goto L_0x0019;
    L_0x0011:
        r4 = move-exception;
        r1 = r4.getMessage();
        org.xutils.common.util.LogUtil.e(r1, r4);
    L_0x0019:
        return r0;
    L_0x001a:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
        goto L_0x002d;
    L_0x001f:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
        goto L_0x0027;
    L_0x0024:
        r4 = move-exception;
        goto L_0x002d;
    L_0x0026:
        r4 = move-exception;
    L_0x0027:
        r1 = new org.xutils.ex.DbException;	 Catch:{ all -> 0x0024 }
        r1.<init>(r4);	 Catch:{ all -> 0x0024 }
        throw r1;	 Catch:{ all -> 0x0024 }
    L_0x002d:
        if (r0 == 0) goto L_0x003b;
    L_0x002f:
        r0.releaseReference();	 Catch:{ Throwable -> 0x0033 }
        goto L_0x003b;
    L_0x0033:
        r0 = move-exception;
        r1 = r0.getMessage();
        org.xutils.common.util.LogUtil.e(r1, r0);
    L_0x003b:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.db.DbManagerImpl.executeUpdateDelete(java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x002e A:{SYNTHETIC, Splitter:B:20:0x002e} */
    public void execNonQuery(org.xutils.db.sqlite.SqlInfo r4) throws org.xutils.ex.DbException {
        /*
        r3 = this;
        r0 = 0;
        r1 = r3.b;	 Catch:{ Throwable -> 0x0025 }
        r4 = r4.buildStatement(r1);	 Catch:{ Throwable -> 0x0025 }
        r4.execute();	 Catch:{ Throwable -> 0x001e, all -> 0x0019 }
        if (r4 == 0) goto L_0x0018;
    L_0x000c:
        r4.releaseReference();	 Catch:{ Throwable -> 0x0010 }
        goto L_0x0018;
    L_0x0010:
        r4 = move-exception;
        r0 = r4.getMessage();
        org.xutils.common.util.LogUtil.e(r0, r4);
    L_0x0018:
        return;
    L_0x0019:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
        goto L_0x002c;
    L_0x001e:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
        goto L_0x0026;
    L_0x0023:
        r4 = move-exception;
        goto L_0x002c;
    L_0x0025:
        r4 = move-exception;
    L_0x0026:
        r1 = new org.xutils.ex.DbException;	 Catch:{ all -> 0x0023 }
        r1.<init>(r4);	 Catch:{ all -> 0x0023 }
        throw r1;	 Catch:{ all -> 0x0023 }
    L_0x002c:
        if (r0 == 0) goto L_0x003a;
    L_0x002e:
        r0.releaseReference();	 Catch:{ Throwable -> 0x0032 }
        goto L_0x003a;
    L_0x0032:
        r0 = move-exception;
        r1 = r0.getMessage();
        org.xutils.common.util.LogUtil.e(r1, r0);
    L_0x003a:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.db.DbManagerImpl.execNonQuery(org.xutils.db.sqlite.SqlInfo):void");
    }

    public void execNonQuery(String str) throws DbException {
        try {
            this.b.execSQL(str);
        } catch (Throwable th) {
            DbException dbException = new DbException(th);
        }
    }

    public Cursor execQuery(SqlInfo sqlInfo) throws DbException {
        try {
            return this.b.rawQuery(sqlInfo.getSql(), sqlInfo.getBindArgsAsStrArray());
        } catch (Throwable th) {
            DbException dbException = new DbException(th);
        }
    }

    public Cursor execQuery(String str) throws DbException {
        try {
            return this.b.rawQuery(str, null);
        } catch (Throwable th) {
            DbException dbException = new DbException(th);
        }
    }
}
