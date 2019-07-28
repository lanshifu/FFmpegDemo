package org.xutils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.xutils.common.util.KeyValue;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;

public interface DbManager extends Closeable {

    public static class DaoConfig {
        private File a;
        private String b = "xUtils.db";
        private int c = 1;
        private boolean d = true;
        private DbUpgradeListener e;
        private TableCreateListener f;
        private DbOpenListener g;

        public DaoConfig setDbDir(File file) {
            this.a = file;
            return this;
        }

        public DaoConfig setDbName(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.b = str;
            }
            return this;
        }

        public DaoConfig setDbVersion(int i) {
            this.c = i;
            return this;
        }

        public DaoConfig setAllowTransaction(boolean z) {
            this.d = z;
            return this;
        }

        public DaoConfig setDbOpenListener(DbOpenListener dbOpenListener) {
            this.g = dbOpenListener;
            return this;
        }

        public DaoConfig setDbUpgradeListener(DbUpgradeListener dbUpgradeListener) {
            this.e = dbUpgradeListener;
            return this;
        }

        public DaoConfig setTableCreateListener(TableCreateListener tableCreateListener) {
            this.f = tableCreateListener;
            return this;
        }

        public File getDbDir() {
            return this.a;
        }

        public String getDbName() {
            return this.b;
        }

        public int getDbVersion() {
            return this.c;
        }

        public boolean isAllowTransaction() {
            return this.d;
        }

        public DbOpenListener getDbOpenListener() {
            return this.g;
        }

        public DbUpgradeListener getDbUpgradeListener() {
            return this.e;
        }

        public TableCreateListener getTableCreateListener() {
            return this.f;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DaoConfig daoConfig = (DaoConfig) obj;
            if (!this.b.equals(daoConfig.b)) {
                return false;
            }
            if (this.a != null) {
                z = this.a.equals(daoConfig.a);
            } else if (daoConfig.a != null) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (this.b.hashCode() * 31) + (this.a != null ? this.a.hashCode() : 0);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(this.a));
            stringBuilder.append("/");
            stringBuilder.append(this.b);
            return stringBuilder.toString();
        }
    }

    public interface DbOpenListener {
        void onDbOpened(DbManager dbManager);
    }

    public interface DbUpgradeListener {
        void onUpgrade(DbManager dbManager, int i, int i2);
    }

    public interface TableCreateListener {
        void onTableCreated(DbManager dbManager, TableEntity<?> tableEntity);
    }

    void addColumn(Class<?> cls, String str) throws DbException;

    void close() throws IOException;

    int delete(Class<?> cls, WhereBuilder whereBuilder) throws DbException;

    void delete(Class<?> cls) throws DbException;

    void delete(Object obj) throws DbException;

    void deleteById(Class<?> cls, Object obj) throws DbException;

    void dropDb() throws DbException;

    void dropTable(Class<?> cls) throws DbException;

    void execNonQuery(String str) throws DbException;

    void execNonQuery(SqlInfo sqlInfo) throws DbException;

    Cursor execQuery(String str) throws DbException;

    Cursor execQuery(SqlInfo sqlInfo) throws DbException;

    int executeUpdateDelete(String str) throws DbException;

    int executeUpdateDelete(SqlInfo sqlInfo) throws DbException;

    <T> List<T> findAll(Class<T> cls) throws DbException;

    <T> T findById(Class<T> cls, Object obj) throws DbException;

    List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException;

    DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException;

    <T> T findFirst(Class<T> cls) throws DbException;

    DaoConfig getDaoConfig();

    SQLiteDatabase getDatabase();

    <T> TableEntity<T> getTable(Class<T> cls) throws DbException;

    void replace(Object obj) throws DbException;

    void save(Object obj) throws DbException;

    boolean saveBindingId(Object obj) throws DbException;

    void saveOrUpdate(Object obj) throws DbException;

    <T> Selector<T> selector(Class<T> cls) throws DbException;

    int update(Class<?> cls, WhereBuilder whereBuilder, KeyValue... keyValueArr) throws DbException;

    void update(Object obj, String... strArr) throws DbException;
}
