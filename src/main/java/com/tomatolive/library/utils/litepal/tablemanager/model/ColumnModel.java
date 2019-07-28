package com.tomatolive.library.utils.litepal.tablemanager.model;

import android.text.TextUtils;

public class ColumnModel {
    private String columnName;
    private String columnType;
    private String defaultValue = "";
    private boolean isNullable = true;
    private boolean isUnique = false;

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String str) {
        this.columnName = str;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String str) {
        this.columnType = str;
    }

    public boolean isNullable() {
        return this.isNullable;
    }

    public void setNullable(boolean z) {
        this.isNullable = z;
    }

    public boolean isUnique() {
        return this.isUnique;
    }

    public void setUnique(boolean z) {
        this.isUnique = z;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String str) {
        if (!"text".equalsIgnoreCase(this.columnType)) {
            this.defaultValue = str;
        } else if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'");
            stringBuilder.append(str);
            stringBuilder.append("'");
            this.defaultValue = stringBuilder.toString();
        }
    }

    public boolean isIdColumn() {
        return "_id".equalsIgnoreCase(this.columnName) || "id".equalsIgnoreCase(this.columnName);
    }
}
