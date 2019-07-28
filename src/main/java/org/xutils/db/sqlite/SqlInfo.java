package org.xutils.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import org.xutils.common.util.KeyValue;
import org.xutils.db.converter.ColumnConverterFactory;
import org.xutils.db.table.ColumnUtils;

public final class SqlInfo {
    private String a;
    private List<KeyValue> b;

    public SqlInfo(String str) {
        this.a = str;
    }

    public String getSql() {
        return this.a;
    }

    public void setSql(String str) {
        this.a = str;
    }

    public void addBindArg(KeyValue keyValue) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(keyValue);
    }

    public void addBindArgs(List<KeyValue> list) {
        if (this.b == null) {
            this.b = list;
        } else {
            this.b.addAll(list);
        }
    }

    public SQLiteStatement buildStatement(SQLiteDatabase sQLiteDatabase) {
        SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(this.a);
        if (this.b != null) {
            for (int i = 1; i < this.b.size() + 1; i++) {
                Object convert2DbValueIfNeeded = ColumnUtils.convert2DbValueIfNeeded(((KeyValue) this.b.get(i - 1)).value);
                if (convert2DbValueIfNeeded != null) {
                    switch (ColumnConverterFactory.getColumnConverter(convert2DbValueIfNeeded.getClass()).getColumnDbType()) {
                        case INTEGER:
                            compileStatement.bindLong(i, ((Number) convert2DbValueIfNeeded).longValue());
                            break;
                        case REAL:
                            compileStatement.bindDouble(i, ((Number) convert2DbValueIfNeeded).doubleValue());
                            break;
                        case TEXT:
                            compileStatement.bindString(i, convert2DbValueIfNeeded.toString());
                            break;
                        case BLOB:
                            compileStatement.bindBlob(i, (byte[]) convert2DbValueIfNeeded);
                            break;
                        default:
                            compileStatement.bindNull(i);
                            break;
                    }
                }
                compileStatement.bindNull(i);
            }
        }
        return compileStatement;
    }

    public Object[] getBindArgs() {
        if (this.b == null) {
            return null;
        }
        Object[] objArr = new Object[this.b.size()];
        for (int i = 0; i < this.b.size(); i++) {
            objArr[i] = ColumnUtils.convert2DbValueIfNeeded(((KeyValue) this.b.get(i)).value);
        }
        return objArr;
    }

    public String[] getBindArgsAsStrArray() {
        if (this.b == null) {
            return null;
        }
        String[] strArr = new String[this.b.size()];
        for (int i = 0; i < this.b.size(); i++) {
            String str;
            Object convert2DbValueIfNeeded = ColumnUtils.convert2DbValueIfNeeded(((KeyValue) this.b.get(i)).value);
            if (convert2DbValueIfNeeded == null) {
                str = null;
            } else {
                str = convert2DbValueIfNeeded.toString();
            }
            strArr[i] = str;
        }
        return strArr;
    }
}
