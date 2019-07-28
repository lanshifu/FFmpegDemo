package org.xutils.db;

import android.database.Cursor;
import com.tomatolive.library.ui.view.widget.matisse.internal.loader.AlbumLoader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Marker;
import org.xutils.common.util.IOUtil;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;

public final class Selector<T> {
    private final TableEntity<T> a;
    private WhereBuilder b;
    private List<OrderBy> c;
    private int d = 0;
    private int e = 0;

    public static class OrderBy {
        private String a;
        private boolean b;

        public OrderBy(String str) {
            this.a = str;
        }

        public OrderBy(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\"");
            stringBuilder.append(this.a);
            stringBuilder.append("\"");
            stringBuilder.append(this.b ? " DESC" : " ASC");
            return stringBuilder.toString();
        }
    }

    private Selector(TableEntity<T> tableEntity) {
        this.a = tableEntity;
    }

    static <T> Selector<T> a(TableEntity<T> tableEntity) {
        return new Selector(tableEntity);
    }

    public Selector<T> where(WhereBuilder whereBuilder) {
        this.b = whereBuilder;
        return this;
    }

    public Selector<T> where(String str, String str2, Object obj) {
        this.b = WhereBuilder.b(str, str2, obj);
        return this;
    }

    public Selector<T> and(String str, String str2, Object obj) {
        this.b.and(str, str2, obj);
        return this;
    }

    public Selector<T> and(WhereBuilder whereBuilder) {
        this.b.and(whereBuilder);
        return this;
    }

    public Selector<T> or(String str, String str2, Object obj) {
        this.b.or(str, str2, obj);
        return this;
    }

    public Selector or(WhereBuilder whereBuilder) {
        this.b.or(whereBuilder);
        return this;
    }

    public Selector<T> expr(String str) {
        if (this.b == null) {
            this.b = WhereBuilder.b();
        }
        this.b.expr(str);
        return this;
    }

    public DbModelSelector groupBy(String str) {
        return new DbModelSelector(this, str);
    }

    public DbModelSelector select(String... strArr) {
        return new DbModelSelector(this, strArr);
    }

    public Selector<T> orderBy(String str) {
        if (this.c == null) {
            this.c = new ArrayList(5);
        }
        this.c.add(new OrderBy(str));
        return this;
    }

    public Selector<T> orderBy(String str, boolean z) {
        if (this.c == null) {
            this.c = new ArrayList(5);
        }
        this.c.add(new OrderBy(str, z));
        return this;
    }

    public Selector<T> limit(int i) {
        this.d = i;
        return this;
    }

    public Selector<T> offset(int i) {
        this.e = i;
        return this;
    }

    public TableEntity<T> getTable() {
        return this.a;
    }

    public WhereBuilder getWhereBuilder() {
        return this.b;
    }

    public List<OrderBy> getOrderByList() {
        return this.c;
    }

    public int getLimit() {
        return this.d;
    }

    public int getOffset() {
        return this.e;
    }

    public T findFirst() throws DbException {
        if (!this.a.tableIsExist()) {
            return null;
        }
        limit(1);
        Cursor execQuery = this.a.getDb().execQuery(toString());
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    Object a = a.a(this.a, execQuery);
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

    public List<T> findAll() throws DbException {
        List<T> list = null;
        if (!this.a.tableIsExist()) {
            return null;
        }
        Cursor execQuery = this.a.getDb().execQuery(toString());
        if (execQuery != null) {
            try {
                list = new ArrayList();
                while (execQuery.moveToNext()) {
                    list.add(a.a(this.a, execQuery));
                }
                IOUtil.closeQuietly(execQuery);
            } catch (Throwable th) {
                IOUtil.closeQuietly(execQuery);
            }
        }
        return list;
    }

    public long count() throws DbException {
        if (!this.a.tableIsExist()) {
            return 0;
        }
        String[] strArr = new String[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("count(\"");
        stringBuilder.append(this.a.getId().getName());
        stringBuilder.append("\") as count");
        strArr[0] = stringBuilder.toString();
        DbModel findFirst = select(strArr).findFirst();
        if (findFirst != null) {
            return findFirst.getLong(AlbumLoader.COLUMN_COUNT);
        }
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(Marker.ANY_MARKER);
        stringBuilder.append(" FROM ");
        stringBuilder.append("\"");
        stringBuilder.append(this.a.getName());
        stringBuilder.append("\"");
        if (this.b != null && this.b.getWhereItemSize() > 0) {
            stringBuilder.append(" WHERE ");
            stringBuilder.append(this.b.toString());
        }
        if (this.c != null && this.c.size() > 0) {
            stringBuilder.append(" ORDER BY ");
            for (OrderBy orderBy : this.c) {
                stringBuilder.append(orderBy.toString());
                stringBuilder.append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (this.d > 0) {
            stringBuilder.append(" LIMIT ");
            stringBuilder.append(this.d);
            stringBuilder.append(" OFFSET ");
            stringBuilder.append(this.e);
        }
        return stringBuilder.toString();
    }
}
