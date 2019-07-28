package com.tomatolive.library.utils.litepal.util;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBUtility {
    private static final String KEYWORDS_COLUMN_SUFFIX = "_lpcolumn";
    private static final String REG_COLLECTION = "\\s+(not\\s+)?(in)\\s*\\(";
    private static final String REG_FUZZY = "\\s+(not\\s+)?(like|between)\\s+";
    private static final String REG_OPERATOR = "\\s*(=|!=|<>|<|>)";
    private static final String SQLITE_KEYWORDS = ",abort,add,after,all,alter,and,as,asc,autoincrement,before,begin,between,by,cascade,check,collate,column,commit,conflict,constraint,create,cross,database,deferrable,deferred,delete,desc,distinct,drop,each,end,escape,except,exclusive,exists,foreign,from,glob,group,having,in,index,inner,insert,intersect,into,is,isnull,join,like,limit,match,natural,not,notnull,null,of,offset,on,or,order,outer,plan,pragma,primary,query,raise,references,regexp,reindex,release,rename,replace,restrict,right,rollback,row,savepoint,select,set,table,temp,temporary,then,to,transaction,trigger,union,unique,update,using,vacuum,values,view,virtual,when,where,";
    private static final String TAG = "DBUtility";

    private DBUtility() {
    }

    public static String getTableNameByClassName(String str) {
        if (TextUtils.isEmpty(str) || '.' == str.charAt(str.length() - 1)) {
            return null;
        }
        return str.substring(str.lastIndexOf(".") + 1);
    }

    public static List<String> getTableNameListByClassNameList(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (!(list == null || list.isEmpty())) {
            for (String tableNameByClassName : list) {
                arrayList.add(getTableNameByClassName(tableNameByClassName));
            }
        }
        return arrayList;
    }

    public static String getTableNameByForeignColumn(String str) {
        if (TextUtils.isEmpty(str) || !str.toLowerCase(Locale.US).endsWith("_id")) {
            return null;
        }
        return str.substring(0, str.length() - "_id".length());
    }

    public static String getIntermediateTableName(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        StringBuilder stringBuilder;
        if (str.toLowerCase(Locale.US).compareTo(str2.toLowerCase(Locale.US)) <= 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("_");
            stringBuilder.append(str2);
            str = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append("_");
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        return str;
    }

    public static String getGenericTableName(String str, String str2) {
        str = getTableNameByClassName(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_");
        stringBuilder.append(str2);
        return BaseUtility.changeCase(stringBuilder.toString());
    }

    public static String getGenericValueIdColumnName(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTableNameByClassName(str));
        stringBuilder.append("_id");
        return BaseUtility.changeCase(stringBuilder.toString());
    }

    public static String getM2MSelfRefColumnName(Field field) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(field.getName());
        stringBuilder.append("_id");
        return BaseUtility.changeCase(stringBuilder.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0065  */
    public static boolean isIntermediateTable(java.lang.String r9, android.database.sqlite.SQLiteDatabase r10) {
        /*
        r0 = android.text.TextUtils.isEmpty(r9);
        if (r0 != 0) goto L_0x0069;
    L_0x0006:
        r0 = "[0-9a-zA-Z]+_[0-9a-zA-Z]+";
        r0 = r9.matches(r0);
        if (r0 == 0) goto L_0x0069;
    L_0x000e:
        r0 = 0;
        r2 = "table_schema";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r1 = r10;
        r10 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0059 }
        r0 = r10.moveToFirst();	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        if (r0 == 0) goto L_0x004b;
    L_0x0022:
        r0 = "name";
        r0 = r10.getColumnIndexOrThrow(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r0 = r10.getString(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r0 = r9.equalsIgnoreCase(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        if (r0 == 0) goto L_0x0045;
    L_0x0032:
        r9 = "type";
        r9 = r10.getColumnIndexOrThrow(r9);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r9 = r10.getInt(r9);	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        r0 = 1;
        if (r9 != r0) goto L_0x004b;
    L_0x003f:
        if (r10 == 0) goto L_0x0044;
    L_0x0041:
        r10.close();
    L_0x0044:
        return r0;
    L_0x0045:
        r0 = r10.moveToNext();	 Catch:{ Exception -> 0x0053, all -> 0x0051 }
        if (r0 != 0) goto L_0x0022;
    L_0x004b:
        if (r10 == 0) goto L_0x0069;
    L_0x004d:
        r10.close();
        goto L_0x0069;
    L_0x0051:
        r9 = move-exception;
        goto L_0x0063;
    L_0x0053:
        r9 = move-exception;
        r0 = r10;
        goto L_0x005a;
    L_0x0056:
        r9 = move-exception;
        r10 = r0;
        goto L_0x0063;
    L_0x0059:
        r9 = move-exception;
    L_0x005a:
        r9.printStackTrace();	 Catch:{ all -> 0x0056 }
        if (r0 == 0) goto L_0x0069;
    L_0x005f:
        r0.close();
        goto L_0x0069;
    L_0x0063:
        if (r10 == 0) goto L_0x0068;
    L_0x0065:
        r10.close();
    L_0x0068:
        throw r9;
    L_0x0069:
        r9 = 0;
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.util.DBUtility.isIntermediateTable(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0066  */
    public static boolean isGenericTable(java.lang.String r9, android.database.sqlite.SQLiteDatabase r10) {
        /*
        r0 = android.text.TextUtils.isEmpty(r9);
        if (r0 != 0) goto L_0x006a;
    L_0x0006:
        r0 = "[0-9a-zA-Z]+_[0-9a-zA-Z]+";
        r0 = r9.matches(r0);
        if (r0 == 0) goto L_0x006a;
    L_0x000e:
        r0 = 0;
        r2 = "table_schema";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r1 = r10;
        r10 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x005a }
        r0 = r10.moveToFirst();	 Catch:{ Exception -> 0x0054, all -> 0x0052 }
        if (r0 == 0) goto L_0x004c;
    L_0x0022:
        r0 = "name";
        r0 = r10.getColumnIndexOrThrow(r0);	 Catch:{ Exception -> 0x0054, all -> 0x0052 }
        r0 = r10.getString(r0);	 Catch:{ Exception -> 0x0054, all -> 0x0052 }
        r0 = r9.equalsIgnoreCase(r0);	 Catch:{ Exception -> 0x0054, all -> 0x0052 }
        if (r0 == 0) goto L_0x0046;
    L_0x0032:
        r9 = "type";
        r9 = r10.getColumnIndexOrThrow(r9);	 Catch:{ Exception -> 0x0054, all -> 0x0052 }
        r9 = r10.getInt(r9);	 Catch:{ Exception -> 0x0054, all -> 0x0052 }
        r0 = 2;
        if (r9 != r0) goto L_0x004c;
    L_0x003f:
        r9 = 1;
        if (r10 == 0) goto L_0x0045;
    L_0x0042:
        r10.close();
    L_0x0045:
        return r9;
    L_0x0046:
        r0 = r10.moveToNext();	 Catch:{ Exception -> 0x0054, all -> 0x0052 }
        if (r0 != 0) goto L_0x0022;
    L_0x004c:
        if (r10 == 0) goto L_0x006a;
    L_0x004e:
        r10.close();
        goto L_0x006a;
    L_0x0052:
        r9 = move-exception;
        goto L_0x0064;
    L_0x0054:
        r9 = move-exception;
        r0 = r10;
        goto L_0x005b;
    L_0x0057:
        r9 = move-exception;
        r10 = r0;
        goto L_0x0064;
    L_0x005a:
        r9 = move-exception;
    L_0x005b:
        r9.printStackTrace();	 Catch:{ all -> 0x0057 }
        if (r0 == 0) goto L_0x006a;
    L_0x0060:
        r0.close();
        goto L_0x006a;
    L_0x0064:
        if (r10 == 0) goto L_0x0069;
    L_0x0066:
        r10.close();
    L_0x0069:
        throw r9;
    L_0x006a:
        r9 = 0;
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.util.DBUtility.isGenericTable(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    public static boolean isTableExists(String str, SQLiteDatabase sQLiteDatabase) {
        try {
            return BaseUtility.containsIgnoreCases(findAllTableNames(sQLiteDatabase), str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005b  */
    public static boolean isColumnExists(java.lang.String r4, java.lang.String r5, android.database.sqlite.SQLiteDatabase r6) {
        /*
        r0 = android.text.TextUtils.isEmpty(r4);
        r1 = 0;
        if (r0 != 0) goto L_0x0065;
    L_0x0007:
        r0 = android.text.TextUtils.isEmpty(r5);
        if (r0 == 0) goto L_0x000e;
    L_0x000d:
        goto L_0x0065;
    L_0x000e:
        r0 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0055 }
        r2.<init>();	 Catch:{ Exception -> 0x0055 }
        r3 = "pragma table_info(";
        r2.append(r3);	 Catch:{ Exception -> 0x0055 }
        r2.append(r5);	 Catch:{ Exception -> 0x0055 }
        r5 = ")";
        r2.append(r5);	 Catch:{ Exception -> 0x0055 }
        r5 = r2.toString();	 Catch:{ Exception -> 0x0055 }
        r5 = r6.rawQuery(r5, r0);	 Catch:{ Exception -> 0x0055 }
        r6 = r5.moveToFirst();	 Catch:{ Exception -> 0x0050, all -> 0x004d }
        if (r6 == 0) goto L_0x0047;
    L_0x002f:
        r6 = "name";
        r6 = r5.getColumnIndexOrThrow(r6);	 Catch:{ Exception -> 0x0050, all -> 0x004d }
        r6 = r5.getString(r6);	 Catch:{ Exception -> 0x0050, all -> 0x004d }
        r6 = r4.equalsIgnoreCase(r6);	 Catch:{ Exception -> 0x0050, all -> 0x004d }
        if (r6 == 0) goto L_0x0041;
    L_0x003f:
        r1 = 1;
        goto L_0x0047;
    L_0x0041:
        r6 = r5.moveToNext();	 Catch:{ Exception -> 0x0050, all -> 0x004d }
        if (r6 != 0) goto L_0x002f;
    L_0x0047:
        if (r5 == 0) goto L_0x005e;
    L_0x0049:
        r5.close();
        goto L_0x005e;
    L_0x004d:
        r4 = move-exception;
        r0 = r5;
        goto L_0x005f;
    L_0x0050:
        r4 = move-exception;
        r0 = r5;
        goto L_0x0056;
    L_0x0053:
        r4 = move-exception;
        goto L_0x005f;
    L_0x0055:
        r4 = move-exception;
    L_0x0056:
        r4.printStackTrace();	 Catch:{ all -> 0x0053 }
        if (r0 == 0) goto L_0x005e;
    L_0x005b:
        r0.close();
    L_0x005e:
        return r1;
    L_0x005f:
        if (r0 == 0) goto L_0x0064;
    L_0x0061:
        r0.close();
    L_0x0064:
        throw r4;
    L_0x0065:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.util.DBUtility.isColumnExists(java.lang.String, java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    public static java.util.List<java.lang.String> findAllTableNames(android.database.sqlite.SQLiteDatabase r6) {
        /*
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 0;
        r2 = "select * from sqlite_master where type = ?";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0041 }
        r4 = 0;
        r5 = "table";
        r3[r4] = r5;	 Catch:{ Exception -> 0x0041 }
        r6 = r6.rawQuery(r2, r3);	 Catch:{ Exception -> 0x0041 }
        r1 = r6.moveToFirst();	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        if (r1 == 0) goto L_0x0033;
    L_0x001a:
        r1 = "tbl_name";
        r1 = r6.getColumnIndexOrThrow(r1);	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r1 = r6.getString(r1);	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r2 = r0.contains(r1);	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        if (r2 != 0) goto L_0x002d;
    L_0x002a:
        r0.add(r1);	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
    L_0x002d:
        r1 = r6.moveToNext();	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        if (r1 != 0) goto L_0x001a;
    L_0x0033:
        if (r6 == 0) goto L_0x0038;
    L_0x0035:
        r6.close();
    L_0x0038:
        return r0;
    L_0x0039:
        r0 = move-exception;
        r1 = r6;
        goto L_0x004f;
    L_0x003c:
        r0 = move-exception;
        r1 = r6;
        goto L_0x0042;
    L_0x003f:
        r0 = move-exception;
        goto L_0x004f;
    L_0x0041:
        r0 = move-exception;
    L_0x0042:
        r0.printStackTrace();	 Catch:{ all -> 0x003f }
        r6 = new com.tomatolive.library.utils.litepal.exceptions.DatabaseGenerateException;	 Catch:{ all -> 0x003f }
        r0 = r0.getMessage();	 Catch:{ all -> 0x003f }
        r6.<init>(r0);	 Catch:{ all -> 0x003f }
        throw r6;	 Catch:{ all -> 0x003f }
    L_0x004f:
        if (r1 == 0) goto L_0x0054;
    L_0x0051:
        r1.close();
    L_0x0054:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.util.DBUtility.findAllTableNames(android.database.sqlite.SQLiteDatabase):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ac  */
    public static com.tomatolive.library.utils.litepal.tablemanager.model.TableModel findPragmaTableInfo(java.lang.String r7, android.database.sqlite.SQLiteDatabase r8) {
        /*
        r0 = isTableExists(r7, r8);
        if (r0 == 0) goto L_0x00b0;
    L_0x0006:
        r0 = findUniqueColumns(r7, r8);
        r1 = new com.tomatolive.library.utils.litepal.tablemanager.model.TableModel;
        r1.<init>();
        r1.setTableName(r7);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "pragma table_info(";
        r2.append(r3);
        r2.append(r7);
        r7 = ")";
        r2.append(r7);
        r7 = r2.toString();
        r2 = 0;
        r7 = r8.rawQuery(r7, r2);	 Catch:{ Exception -> 0x009c }
        r8 = r7.moveToFirst();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        if (r8 == 0) goto L_0x008e;
    L_0x0033:
        r8 = new com.tomatolive.library.utils.litepal.tablemanager.model.ColumnModel;	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r8.<init>();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r2 = "name";
        r2 = r7.getColumnIndexOrThrow(r2);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r2 = r7.getString(r2);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3 = "type";
        r3 = r7.getColumnIndexOrThrow(r3);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r3 = r7.getString(r3);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r4 = "notnull";
        r4 = r7.getColumnIndexOrThrow(r4);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r4 = r7.getInt(r4);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r5 = 1;
        if (r4 == r5) goto L_0x005a;
    L_0x0059:
        goto L_0x005b;
    L_0x005a:
        r5 = 0;
    L_0x005b:
        r4 = r0.contains(r2);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r6 = "dflt_value";
        r6 = r7.getColumnIndexOrThrow(r6);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r6 = r7.getString(r6);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r8.setColumnName(r2);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r8.setColumnType(r3);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r8.setNullable(r5);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r8.setUnique(r4);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        if (r6 == 0) goto L_0x0080;
    L_0x0077:
        r2 = "'";
        r3 = "";
        r2 = r6.replace(r2, r3);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        goto L_0x0082;
    L_0x0080:
        r2 = "";
    L_0x0082:
        r8.setDefaultValue(r2);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r1.addColumnModel(r8);	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        r8 = r7.moveToNext();	 Catch:{ Exception -> 0x0096, all -> 0x0094 }
        if (r8 != 0) goto L_0x0033;
    L_0x008e:
        if (r7 == 0) goto L_0x0093;
    L_0x0090:
        r7.close();
    L_0x0093:
        return r1;
    L_0x0094:
        r8 = move-exception;
        goto L_0x00aa;
    L_0x0096:
        r8 = move-exception;
        r2 = r7;
        goto L_0x009d;
    L_0x0099:
        r8 = move-exception;
        r7 = r2;
        goto L_0x00aa;
    L_0x009c:
        r8 = move-exception;
    L_0x009d:
        r8.printStackTrace();	 Catch:{ all -> 0x0099 }
        r7 = new com.tomatolive.library.utils.litepal.exceptions.DatabaseGenerateException;	 Catch:{ all -> 0x0099 }
        r8 = r8.getMessage();	 Catch:{ all -> 0x0099 }
        r7.<init>(r8);	 Catch:{ all -> 0x0099 }
        throw r7;	 Catch:{ all -> 0x0099 }
    L_0x00aa:
        if (r7 == 0) goto L_0x00af;
    L_0x00ac:
        r7.close();
    L_0x00af:
        throw r8;
    L_0x00b0:
        r8 = new com.tomatolive.library.utils.litepal.exceptions.DatabaseGenerateException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Table doesn't exist when executing ";
        r0.append(r1);
        r0.append(r7);
        r7 = r0.toString();
        r8.<init>(r7);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.util.DBUtility.findPragmaTableInfo(java.lang.String, android.database.sqlite.SQLiteDatabase):com.tomatolive.library.utils.litepal.tablemanager.model.TableModel");
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ae  */
    public static java.util.List<java.lang.String> findUniqueColumns(java.lang.String r6, android.database.sqlite.SQLiteDatabase r7) {
        /*
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0096, all -> 0x0092 }
        r2.<init>();	 Catch:{ Exception -> 0x0096, all -> 0x0092 }
        r3 = "pragma index_list(";
        r2.append(r3);	 Catch:{ Exception -> 0x0096, all -> 0x0092 }
        r2.append(r6);	 Catch:{ Exception -> 0x0096, all -> 0x0092 }
        r6 = ")";
        r2.append(r6);	 Catch:{ Exception -> 0x0096, all -> 0x0092 }
        r6 = r2.toString();	 Catch:{ Exception -> 0x0096, all -> 0x0092 }
        r6 = r7.rawQuery(r6, r1);	 Catch:{ Exception -> 0x0096, all -> 0x0092 }
        r2 = r6.moveToFirst();	 Catch:{ Exception -> 0x008e, all -> 0x008b }
        if (r2 == 0) goto L_0x0080;
    L_0x0026:
        r2 = r1;
    L_0x0027:
        r3 = "unique";
        r3 = r6.getColumnIndexOrThrow(r3);	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r3 = r6.getInt(r3);	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r4 = 1;
        if (r3 != r4) goto L_0x0074;
    L_0x0034:
        r3 = "name";
        r3 = r6.getColumnIndexOrThrow(r3);	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r3 = r6.getString(r3);	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r4.<init>();	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r5 = "pragma index_info(";
        r4.append(r5);	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r4.append(r3);	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r3 = ")";
        r4.append(r3);	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r3 = r4.toString();	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r3 = r7.rawQuery(r3, r1);	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        r2 = r3.moveToFirst();	 Catch:{ Exception -> 0x0070, all -> 0x006d }
        if (r2 == 0) goto L_0x006b;
    L_0x005e:
        r2 = "name";
        r2 = r3.getColumnIndexOrThrow(r2);	 Catch:{ Exception -> 0x0070, all -> 0x006d }
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x0070, all -> 0x006d }
        r0.add(r2);	 Catch:{ Exception -> 0x0070, all -> 0x006d }
    L_0x006b:
        r2 = r3;
        goto L_0x0074;
    L_0x006d:
        r7 = move-exception;
        r2 = r3;
        goto L_0x00a7;
    L_0x0070:
        r7 = move-exception;
        r1 = r6;
        r2 = r3;
        goto L_0x0098;
    L_0x0074:
        r3 = r6.moveToNext();	 Catch:{ Exception -> 0x007e, all -> 0x007c }
        if (r3 != 0) goto L_0x0027;
    L_0x007a:
        r1 = r2;
        goto L_0x0080;
    L_0x007c:
        r7 = move-exception;
        goto L_0x00a7;
    L_0x007e:
        r7 = move-exception;
        goto L_0x0090;
    L_0x0080:
        if (r6 == 0) goto L_0x0085;
    L_0x0082:
        r6.close();
    L_0x0085:
        if (r1 == 0) goto L_0x008a;
    L_0x0087:
        r1.close();
    L_0x008a:
        return r0;
    L_0x008b:
        r7 = move-exception;
        r2 = r1;
        goto L_0x00a7;
    L_0x008e:
        r7 = move-exception;
        r2 = r1;
    L_0x0090:
        r1 = r6;
        goto L_0x0098;
    L_0x0092:
        r7 = move-exception;
        r6 = r1;
        r2 = r6;
        goto L_0x00a7;
    L_0x0096:
        r7 = move-exception;
        r2 = r1;
    L_0x0098:
        r7.printStackTrace();	 Catch:{ all -> 0x00a5 }
        r6 = new com.tomatolive.library.utils.litepal.exceptions.DatabaseGenerateException;	 Catch:{ all -> 0x00a5 }
        r7 = r7.getMessage();	 Catch:{ all -> 0x00a5 }
        r6.<init>(r7);	 Catch:{ all -> 0x00a5 }
        throw r6;	 Catch:{ all -> 0x00a5 }
    L_0x00a5:
        r7 = move-exception;
        r6 = r1;
    L_0x00a7:
        if (r6 == 0) goto L_0x00ac;
    L_0x00a9:
        r6.close();
    L_0x00ac:
        if (r2 == 0) goto L_0x00b1;
    L_0x00ae:
        r2.close();
    L_0x00b1:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.util.DBUtility.findUniqueColumns(java.lang.String, android.database.sqlite.SQLiteDatabase):java.util.List");
    }

    public static boolean isFieldNameConflictWithSQLiteKeywords(String str) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(",");
            stringBuilder.append(str.toLowerCase(Locale.US));
            stringBuilder.append(",");
            if (SQLITE_KEYWORDS.contains(stringBuilder.toString())) {
                return true;
            }
        }
        return false;
    }

    public static String convertToValidColumnName(String str) {
        if (!isFieldNameConflictWithSQLiteKeywords(str)) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(KEYWORDS_COLUMN_SUFFIX);
        return stringBuilder.toString();
    }

    public static String convertWhereClauseToColumnName(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                Matcher matcher = Pattern.compile("(\\w+\\s*(=|!=|<>|<|>)|\\w+\\s+(not\\s+)?(like|between)\\s+|\\w+\\s+(not\\s+)?(in)\\s*\\()").matcher(str);
                while (matcher.find()) {
                    String group = matcher.group();
                    String replaceAll = group.replaceAll("(\\s*(=|!=|<>|<|>)|\\s+(not\\s+)?(like|between)\\s+|\\s+(not\\s+)?(in)\\s*\\()", "");
                    group = group.replace(replaceAll, "");
                    replaceAll = convertToValidColumnName(replaceAll);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(replaceAll);
                    stringBuilder.append(group);
                    matcher.appendReplacement(stringBuffer, stringBuilder.toString());
                }
                matcher.appendTail(stringBuffer);
                return stringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String[] convertSelectClauseToValidNames(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = convertToValidColumnName(strArr[i]);
        }
        return strArr2;
    }

    public static String convertOrderByClauseToValidName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str = str.trim().toLowerCase(Locale.US);
        if (str.contains(",")) {
            String[] split = str.split(",");
            StringBuilder stringBuilder = new StringBuilder();
            int length = split.length;
            int i = 0;
            Object obj = null;
            while (i < length) {
                String str2 = split[i];
                if (obj != null) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(convertOrderByItem(str2));
                i++;
                obj = 1;
            }
            str = stringBuilder.toString();
        } else {
            str = convertOrderByItem(str);
        }
        return str;
    }

    private static String convertOrderByItem(String str) {
        String str2;
        if (str.endsWith("asc")) {
            str = str.replace("asc", "").trim();
            str2 = " asc";
        } else if (str.endsWith("desc")) {
            str = str.replace("desc", "").trim();
            str2 = " desc";
        } else {
            str2 = "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(convertToValidColumnName(str));
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }
}
