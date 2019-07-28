package org.xutils.db.table;

import android.text.TextUtils;
import java.util.Date;
import java.util.HashMap;

public final class DbModel {
    private HashMap<String, String> a = new HashMap();

    public String getString(String str) {
        return (String) this.a.get(str);
    }

    public int getInt(String str) {
        return Integer.valueOf((String) this.a.get(str)).intValue();
    }

    public boolean getBoolean(String str) {
        str = (String) this.a.get(str);
        if (str == null) {
            return false;
        }
        return str.length() == 1 ? "1".equals(str) : Boolean.valueOf(str).booleanValue();
    }

    public double getDouble(String str) {
        return Double.valueOf((String) this.a.get(str)).doubleValue();
    }

    public float getFloat(String str) {
        return Float.valueOf((String) this.a.get(str)).floatValue();
    }

    public long getLong(String str) {
        return Long.valueOf((String) this.a.get(str)).longValue();
    }

    public Date getDate(String str) {
        return new Date(Long.valueOf((String) this.a.get(str)).longValue());
    }

    public java.sql.Date getSqlDate(String str) {
        return new java.sql.Date(Long.valueOf((String) this.a.get(str)).longValue());
    }

    public void add(String str, String str2) {
        this.a.put(str, str2);
    }

    public HashMap<String, String> getDataMap() {
        return this.a;
    }

    public boolean isEmpty(String str) {
        return TextUtils.isEmpty((CharSequence) this.a.get(str));
    }
}
