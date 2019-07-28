package org.litepal.tablemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.litepal.exceptions.DatabaseGenerateException;
import org.litepal.tablemanager.model.AssociationsModel;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.GenericModel;
import org.litepal.util.DBUtility;
import org.litepal.util.LitePalLog;

public abstract class AssociationCreator extends Generator {
    public abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* Access modifiers changed, original: protected */
    public void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        addAssociations(getAllAssociations(), sQLiteDatabase, z);
    }

    /* Access modifiers changed, original: protected */
    public String generateCreateTableSQL(String str, List<ColumnModel> list, boolean z) {
        StringBuilder stringBuilder = new StringBuilder("create table ");
        stringBuilder.append(str);
        stringBuilder.append(" (");
        if (z) {
            stringBuilder.append("id integer primary key autoincrement,");
        }
        if (isContainsOnlyIdField(list)) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        Object obj = null;
        for (ColumnModel columnModel : list) {
            if (!columnModel.isIdColumn()) {
                if (obj != null) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(columnModel.getColumnName());
                stringBuilder.append(" ");
                stringBuilder.append(columnModel.getColumnType());
                if (!columnModel.isNullable()) {
                    stringBuilder.append(" not null");
                }
                if (columnModel.isUnique()) {
                    stringBuilder.append(" unique");
                }
                str = columnModel.getDefaultValue();
                if (!TextUtils.isEmpty(str)) {
                    stringBuilder.append(" default ");
                    stringBuilder.append(str);
                }
                obj = 1;
            }
        }
        stringBuilder.append(")");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("create table sql is >> ");
        stringBuilder2.append(stringBuilder);
        LitePalLog.d("Generator", stringBuilder2.toString());
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public String generateDropTableSQL(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("drop table if exists ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public String generateAddColumnSQL(String str, ColumnModel columnModel) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("alter table ");
        stringBuilder.append(str);
        stringBuilder.append(" add column ");
        stringBuilder.append(columnModel.getColumnName());
        stringBuilder.append(" ");
        stringBuilder.append(columnModel.getColumnType());
        if (!columnModel.isNullable()) {
            stringBuilder.append(" not null");
        }
        if (columnModel.isUnique()) {
            stringBuilder.append(" unique");
        }
        str = columnModel.getDefaultValue();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(" default ");
            stringBuilder.append(str);
        } else if (!columnModel.isNullable()) {
            if ("integer".equalsIgnoreCase(columnModel.getColumnType())) {
                str = "0";
            } else if ("text".equalsIgnoreCase(columnModel.getColumnType())) {
                str = "''";
            } else if ("real".equalsIgnoreCase(columnModel.getColumnType())) {
                str = "0.0";
            }
            stringBuilder.append(" default ");
            stringBuilder.append(str);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("add column sql is >> ");
        stringBuilder2.append(stringBuilder);
        LitePalLog.d("Generator", stringBuilder2.toString());
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public boolean isForeignKeyColumnFormat(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.toLowerCase(Locale.US).endsWith("_id") && !str.equalsIgnoreCase("_id")) {
            z = true;
        }
        return z;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0067  */
    public void giveTableSchemaACopy(java.lang.String r5, int r6, android.database.sqlite.SQLiteDatabase r7) {
        /*
        r4 = this;
        r0 = new java.lang.StringBuilder;
        r1 = "select * from ";
        r0.<init>(r1);
        r1 = "table_schema";
        r0.append(r1);
        r1 = "Generator";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "giveTableSchemaACopy SQL is >> ";
        r2.append(r3);
        r2.append(r0);
        r2 = r2.toString();
        org.litepal.util.LitePalLog.d(r1, r2);
        r1 = 0;
        r0 = r0.toString();	 Catch:{ Exception -> 0x005b }
        r0 = r7.rawQuery(r0, r1);	 Catch:{ Exception -> 0x005b }
        r2 = r4.isNeedtoGiveACopy(r0, r5);	 Catch:{ Exception -> 0x0055, all -> 0x0053 }
        if (r2 == 0) goto L_0x004d;
    L_0x0031:
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0055, all -> 0x0053 }
        r2.<init>();	 Catch:{ Exception -> 0x0055, all -> 0x0053 }
        r3 = "name";
        r5 = org.litepal.util.BaseUtility.changeCase(r5);	 Catch:{ Exception -> 0x0055, all -> 0x0053 }
        r2.put(r3, r5);	 Catch:{ Exception -> 0x0055, all -> 0x0053 }
        r5 = "type";
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x0055, all -> 0x0053 }
        r2.put(r5, r6);	 Catch:{ Exception -> 0x0055, all -> 0x0053 }
        r5 = "table_schema";
        r7.insert(r5, r1, r2);	 Catch:{ Exception -> 0x0055, all -> 0x0053 }
    L_0x004d:
        if (r0 == 0) goto L_0x0064;
    L_0x004f:
        r0.close();
        goto L_0x0064;
    L_0x0053:
        r5 = move-exception;
        goto L_0x0065;
    L_0x0055:
        r5 = move-exception;
        r1 = r0;
        goto L_0x005c;
    L_0x0058:
        r5 = move-exception;
        r0 = r1;
        goto L_0x0065;
    L_0x005b:
        r5 = move-exception;
    L_0x005c:
        r5.printStackTrace();	 Catch:{ all -> 0x0058 }
        if (r1 == 0) goto L_0x0064;
    L_0x0061:
        r1.close();
    L_0x0064:
        return;
    L_0x0065:
        if (r0 == 0) goto L_0x006a;
    L_0x0067:
        r0.close();
    L_0x006a:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.tablemanager.AssociationCreator.giveTableSchemaACopy(java.lang.String, int, android.database.sqlite.SQLiteDatabase):void");
    }

    private boolean isNeedtoGiveACopy(Cursor cursor, String str) {
        return (isValueExists(cursor, str) || isSpecialTable(str)) ? false : true;
    }

    private boolean isValueExists(Cursor cursor, String str) {
        if (cursor.moveToFirst()) {
            while (!cursor.getString(cursor.getColumnIndexOrThrow("name")).equalsIgnoreCase(str)) {
                if (!cursor.moveToNext()) {
                }
            }
            return true;
        }
        return false;
    }

    private boolean isSpecialTable(String str) {
        return "table_schema".equalsIgnoreCase(str);
    }

    private void addAssociations(Collection<AssociationsModel> collection, SQLiteDatabase sQLiteDatabase, boolean z) {
        for (AssociationsModel associationsModel : collection) {
            if (2 == associationsModel.getAssociationType() || 1 == associationsModel.getAssociationType()) {
                addForeignKeyColumn(associationsModel.getTableName(), associationsModel.getAssociatedTableName(), associationsModel.getTableHoldsForeignKey(), sQLiteDatabase);
            } else if (3 == associationsModel.getAssociationType()) {
                createIntermediateTable(associationsModel.getTableName(), associationsModel.getAssociatedTableName(), sQLiteDatabase, z);
            }
        }
        for (GenericModel createGenericTable : getGenericModels()) {
            createGenericTable(createGenericTable, sQLiteDatabase, z);
        }
    }

    private void createIntermediateTable(String str, String str2, SQLiteDatabase sQLiteDatabase, boolean z) {
        ArrayList arrayList = new ArrayList();
        ColumnModel columnModel = new ColumnModel();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_id");
        columnModel.setColumnName(stringBuilder.toString());
        columnModel.setColumnType("integer");
        ColumnModel columnModel2 = new ColumnModel();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str2);
        stringBuilder2.append("_id");
        columnModel2.setColumnName(stringBuilder2.toString());
        columnModel2.setColumnType("integer");
        arrayList.add(columnModel);
        arrayList.add(columnModel2);
        str = DBUtility.getIntermediateTableName(str, str2);
        ArrayList arrayList2 = new ArrayList();
        if (!DBUtility.isTableExists(str, sQLiteDatabase)) {
            arrayList2.add(generateCreateTableSQL(str, arrayList, false));
        } else if (z) {
            arrayList2.add(generateDropTableSQL(str));
            arrayList2.add(generateCreateTableSQL(str, arrayList, false));
        }
        execute(arrayList2, sQLiteDatabase);
        giveTableSchemaACopy(str, 1, sQLiteDatabase);
    }

    private void createGenericTable(GenericModel genericModel, SQLiteDatabase sQLiteDatabase, boolean z) {
        String tableName = genericModel.getTableName();
        String valueColumnName = genericModel.getValueColumnName();
        String valueColumnType = genericModel.getValueColumnType();
        String valueIdColumnName = genericModel.getValueIdColumnName();
        ArrayList arrayList = new ArrayList();
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnName(valueColumnName);
        columnModel.setColumnType(valueColumnType);
        ColumnModel columnModel2 = new ColumnModel();
        columnModel2.setColumnName(valueIdColumnName);
        columnModel2.setColumnType("integer");
        arrayList.add(columnModel);
        arrayList.add(columnModel2);
        ArrayList arrayList2 = new ArrayList();
        if (!DBUtility.isTableExists(tableName, sQLiteDatabase)) {
            arrayList2.add(generateCreateTableSQL(tableName, arrayList, false));
        } else if (z) {
            arrayList2.add(generateDropTableSQL(tableName));
            arrayList2.add(generateCreateTableSQL(tableName, arrayList, false));
        }
        execute(arrayList2, sQLiteDatabase);
        giveTableSchemaACopy(tableName, 2, sQLiteDatabase);
    }

    /* Access modifiers changed, original: protected */
    public void addForeignKeyColumn(String str, String str2, String str3, SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder;
        if (!DBUtility.isTableExists(str, sQLiteDatabase)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Table doesn't exist with the name of ");
            stringBuilder.append(str);
            throw new DatabaseGenerateException(stringBuilder.toString());
        } else if (DBUtility.isTableExists(str2, sQLiteDatabase)) {
            String str4 = null;
            if (str.equals(str3)) {
                str4 = getForeignKeyColumnName(str2);
            } else if (str2.equals(str3)) {
                str4 = getForeignKeyColumnName(str);
            }
            if (DBUtility.isColumnExists(str4, str3, sQLiteDatabase)) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("column ");
                stringBuilder2.append(str4);
                stringBuilder2.append(" is already exist, no need to add one");
                LitePalLog.d("Generator", stringBuilder2.toString());
                return;
            }
            ColumnModel columnModel = new ColumnModel();
            columnModel.setColumnName(str4);
            columnModel.setColumnType("integer");
            ArrayList arrayList = new ArrayList();
            arrayList.add(generateAddColumnSQL(str3, columnModel));
            execute(arrayList, sQLiteDatabase);
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Table doesn't exist with the name of ");
            stringBuilder.append(str2);
            throw new DatabaseGenerateException(stringBuilder.toString());
        }
    }

    private boolean isContainsOnlyIdField(List<ColumnModel> list) {
        if (list.size() == 0 || ((list.size() == 1 && isIdColumn(((ColumnModel) list.get(0)).getColumnName())) || (list.size() == 2 && isIdColumn(((ColumnModel) list.get(0)).getColumnName()) && isIdColumn(((ColumnModel) list.get(1)).getColumnName())))) {
            return true;
        }
        return false;
    }
}
