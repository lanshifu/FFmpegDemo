package org.xutils.db.table;

import android.database.Cursor;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import org.xutils.DbManager;
import org.xutils.common.util.IOUtil;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;

public final class TableEntity<T> {
    private final DbManager a;
    private final String b;
    private final String c;
    private ColumnEntity d;
    private Class<T> e;
    private Constructor<T> f;
    private volatile boolean g;
    private final LinkedHashMap<String, ColumnEntity> h;

    TableEntity(DbManager dbManager, Class<T> cls) throws Throwable {
        this.a = dbManager;
        this.e = cls;
        this.f = cls.getConstructor(new Class[0]);
        this.f.setAccessible(true);
        Table table = (Table) cls.getAnnotation(Table.class);
        this.b = table.name();
        this.c = table.onCreated();
        this.h = a.a(cls);
        for (ColumnEntity columnEntity : this.h.values()) {
            if (columnEntity.isId()) {
                this.d = columnEntity;
                return;
            }
        }
    }

    public T createEntity() throws Throwable {
        return this.f.newInstance(new Object[0]);
    }

    public boolean tableIsExist() throws DbException {
        if (a()) {
            return true;
        }
        DbManager dbManager = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='");
        stringBuilder.append(this.b);
        stringBuilder.append("'");
        Cursor execQuery = dbManager.execQuery(stringBuilder.toString());
        if (execQuery != null) {
            try {
                if (!execQuery.moveToNext() || execQuery.getInt(0) <= 0) {
                    IOUtil.closeQuietly(execQuery);
                } else {
                    a(true);
                    IOUtil.closeQuietly(execQuery);
                    return true;
                }
            } catch (Throwable th) {
                IOUtil.closeQuietly(execQuery);
            }
        }
        return false;
    }

    public DbManager getDb() {
        return this.a;
    }

    public String getName() {
        return this.b;
    }

    public Class<T> getEntityType() {
        return this.e;
    }

    public String getOnCreated() {
        return this.c;
    }

    public ColumnEntity getId() {
        return this.d;
    }

    public LinkedHashMap<String, ColumnEntity> getColumnMap() {
        return this.h;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        return this.g;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(boolean z) {
        this.g = z;
    }

    public String toString() {
        return this.b;
    }
}
