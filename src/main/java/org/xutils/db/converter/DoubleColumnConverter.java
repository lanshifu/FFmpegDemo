package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class DoubleColumnConverter implements ColumnConverter<Double> {
    public Object fieldValue2DbValue(Double d) {
        return d;
    }

    public Double getFieldValue(Cursor cursor, int i) {
        return cursor.isNull(i) ? null : Double.valueOf(cursor.getDouble(i));
    }

    public ColumnDbType getColumnDbType() {
        return ColumnDbType.REAL;
    }
}
