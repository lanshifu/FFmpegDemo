package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class FloatColumnConverter implements ColumnConverter<Float> {
    public Object fieldValue2DbValue(Float f) {
        return f;
    }

    public Float getFieldValue(Cursor cursor, int i) {
        return cursor.isNull(i) ? null : Float.valueOf(cursor.getFloat(i));
    }

    public ColumnDbType getColumnDbType() {
        return ColumnDbType.REAL;
    }
}
