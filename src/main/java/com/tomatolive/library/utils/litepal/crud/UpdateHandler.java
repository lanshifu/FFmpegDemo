package com.tomatolive.library.utils.litepal.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import com.tomatolive.library.utils.litepal.Operator;
import com.tomatolive.library.utils.litepal.annotation.Encrypt;
import com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;
import com.tomatolive.library.utils.litepal.util.BaseUtility;
import com.tomatolive.library.utils.litepal.util.DBUtility;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UpdateHandler extends DataHandler {
    public UpdateHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* Access modifiers changed, original: 0000 */
    public int onUpdate(LitePalSupport litePalSupport, long j) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List supportedFields = getSupportedFields(litePalSupport.getClassName());
        updateGenericTables(litePalSupport, getSupportedGenericFields(litePalSupport.getClassName()), j);
        ContentValues contentValues = new ContentValues();
        putFieldsValue(litePalSupport, supportedFields, contentValues);
        putFieldsToDefaultValue(litePalSupport, contentValues, j);
        if (contentValues.size() <= 0) {
            return 0;
        }
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = litePalSupport.getTableName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ");
        stringBuilder.append(j);
        return sQLiteDatabase.update(tableName, contentValues, stringBuilder.toString(), null);
    }

    public int onUpdate(Class<?> cls, long j, ContentValues contentValues) {
        if (contentValues.size() <= 0) {
            return 0;
        }
        convertContentValues(contentValues);
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = getTableName(cls);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ");
        stringBuilder.append(j);
        return sQLiteDatabase.update(tableName, contentValues, stringBuilder.toString(), null);
    }

    /* Access modifiers changed, original: varargs */
    public int onUpdateAll(LitePalSupport litePalSupport, String... strArr) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        BaseUtility.checkConditionsCorrect(strArr);
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        List supportedFields = getSupportedFields(litePalSupport.getClassName());
        List supportedGenericFields = getSupportedGenericFields(litePalSupport.getClassName());
        long[] jArr = null;
        if (!supportedGenericFields.isEmpty()) {
            List find = Operator.select("id").where(strArr).find(litePalSupport.getClass());
            if (find.size() > 0) {
                jArr = new long[find.size()];
                while (i < jArr.length) {
                    jArr[i] = ((LitePalSupport) find.get(i)).getBaseObjId();
                    i++;
                }
                updateGenericTables(litePalSupport, supportedGenericFields, jArr);
            }
        }
        ContentValues contentValues = new ContentValues();
        putFieldsValue(litePalSupport, supportedFields, contentValues);
        putFieldsToDefaultValue(litePalSupport, contentValues, jArr);
        return doUpdateAllAction(litePalSupport.getTableName(), contentValues, strArr);
    }

    public int onUpdateAll(String str, ContentValues contentValues, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        convertContentValues(contentValues);
        return doUpdateAllAction(str, contentValues, strArr);
    }

    private int doUpdateAllAction(String str, ContentValues contentValues, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        return contentValues.size() > 0 ? this.mDatabase.update(str, contentValues, getWhereClause(strArr), getWhereArgs(strArr)) : 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0096 A:{ExcHandler: Exception (r0_3 'e' java.lang.Exception), Splitter:B:1:0x0004} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0096 A:{ExcHandler: Exception (r0_3 'e' java.lang.Exception), Splitter:B:1:0x0004} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:27:0x008d, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:30:0x0093, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:31:0x0094, code skipped:
            r7 = r6;
     */
    /* JADX WARNING: Missing block: B:32:0x0096, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:34:0x00a0, code skipped:
            throw new com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException(r0.getMessage(), r0);
     */
    private void putFieldsToDefaultValue(com.tomatolive.library.utils.litepal.crud.LitePalSupport r16, android.content.ContentValues r17, long... r18) {
        /*
        r15 = this;
        r1 = r15;
        r0 = r18;
        r2 = 0;
        r3 = r15.getEmptyModel(r16);	 Catch:{ NoSuchFieldException -> 0x00a1, Exception -> 0x0096 }
        r4 = r3.getClass();	 Catch:{ NoSuchFieldException -> 0x00a1, Exception -> 0x0096 }
        r5 = r16.getFieldsToSetToDefault();	 Catch:{ NoSuchFieldException -> 0x00a1, Exception -> 0x0096 }
        r5 = r5.iterator();	 Catch:{ NoSuchFieldException -> 0x00a1, Exception -> 0x0096 }
        r6 = r2;
    L_0x0015:
        r7 = r5.hasNext();	 Catch:{ NoSuchFieldException -> 0x0093, Exception -> 0x0096 }
        if (r7 == 0) goto L_0x0092;
    L_0x001b:
        r7 = r5.next();	 Catch:{ NoSuchFieldException -> 0x0093, Exception -> 0x0096 }
        r7 = (java.lang.String) r7;	 Catch:{ NoSuchFieldException -> 0x0093, Exception -> 0x0096 }
        r8 = r15.isIdColumn(r7);	 Catch:{ NoSuchFieldException -> 0x0093, Exception -> 0x0096 }
        if (r8 != 0) goto L_0x008f;
    L_0x0027:
        r6 = r4.getDeclaredField(r7);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r8 = r6.getType();	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r8 = r15.isCollection(r8);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        if (r8 == 0) goto L_0x0086;
    L_0x0035:
        if (r0 == 0) goto L_0x0083;
    L_0x0037:
        r8 = r0.length;	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        if (r8 <= 0) goto L_0x0083;
    L_0x003a:
        r8 = r15.getGenericTypeName(r6);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r8 = com.tomatolive.library.utils.litepal.util.BaseUtility.isGenericTypeSupported(r8);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        if (r8 == 0) goto L_0x0083;
    L_0x0044:
        r8 = r16.getClassName();	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r6 = r6.getName();	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r6 = com.tomatolive.library.utils.litepal.util.DBUtility.getGenericTableName(r8, r6);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r8 = r16.getClassName();	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r8 = com.tomatolive.library.utils.litepal.util.DBUtility.getGenericValueIdColumnName(r8);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r9 = new java.lang.StringBuilder;	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r9.<init>();	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r10 = r0.length;	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r11 = 0;
        r12 = 0;
    L_0x0060:
        if (r11 >= r10) goto L_0x007a;
    L_0x0062:
        r13 = r0[r11];	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        if (r12 == 0) goto L_0x006b;
    L_0x0066:
        r12 = " or ";
        r9.append(r12);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
    L_0x006b:
        r9.append(r8);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r12 = " = ";
        r9.append(r12);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r9.append(r13);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r11 = r11 + 1;
        r12 = 1;
        goto L_0x0060;
    L_0x007a:
        r8 = r1.mDatabase;	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r9 = r9.toString();	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        r8.delete(r6, r9, r2);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
    L_0x0083:
        r8 = r17;
        goto L_0x008b;
    L_0x0086:
        r8 = r17;
        r15.putContentValuesForUpdate(r3, r6, r8);	 Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
    L_0x008b:
        r6 = r7;
        goto L_0x0015;
    L_0x008d:
        r0 = move-exception;
        goto L_0x00a3;
    L_0x008f:
        r8 = r17;
        goto L_0x0015;
    L_0x0092:
        return;
    L_0x0093:
        r0 = move-exception;
        r7 = r6;
        goto L_0x00a3;
    L_0x0096:
        r0 = move-exception;
        r2 = new com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;
        r3 = r0.getMessage();
        r2.<init>(r3, r0);
        throw r2;
    L_0x00a1:
        r0 = move-exception;
        r7 = r2;
    L_0x00a3:
        r2 = new com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;
        r3 = r16.getClassName();
        r3 = com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException.noSuchFieldExceptioin(r3, r7);
        r2.<init>(r3, r0);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.crud.UpdateHandler.putFieldsToDefaultValue(com.tomatolive.library.utils.litepal.crud.LitePalSupport, android.content.ContentValues, long[]):void");
    }

    private int doUpdateAssociations(LitePalSupport litePalSupport, long j, ContentValues contentValues) {
        analyzeAssociations(litePalSupport);
        updateSelfTableForeignKey(litePalSupport, contentValues);
        return updateAssociatedTableForeignKey(litePalSupport, j) + 0;
    }

    private void analyzeAssociations(LitePalSupport litePalSupport) {
        try {
            analyzeAssociatedModels(litePalSupport, getAssociationInfo(litePalSupport.getClassName()));
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    private void updateSelfTableForeignKey(LitePalSupport litePalSupport, ContentValues contentValues) {
        Map associatedModelsMapWithoutFK = litePalSupport.getAssociatedModelsMapWithoutFK();
        for (String str : associatedModelsMapWithoutFK.keySet()) {
            contentValues.put(getForeignKeyColumnName(str), (Long) associatedModelsMapWithoutFK.get(str));
        }
    }

    private int updateAssociatedTableForeignKey(LitePalSupport litePalSupport, long j) {
        Map associatedModelsMapWithFK = litePalSupport.getAssociatedModelsMapWithFK();
        ContentValues contentValues = new ContentValues();
        for (String str : associatedModelsMapWithFK.keySet()) {
            contentValues.clear();
            contentValues.put(getForeignKeyColumnName(litePalSupport.getTableName()), Long.valueOf(j));
            Set set = (Set) associatedModelsMapWithFK.get(str);
            if (set != null && !set.isEmpty()) {
                return this.mDatabase.update(str, contentValues, getWhereOfIdsWithOr((Collection) set), null);
            }
        }
        return 0;
    }

    private void updateGenericTables(LitePalSupport litePalSupport, List<Field> list, long... jArr) throws IllegalAccessException, InvocationTargetException {
        long[] jArr2 = jArr;
        if (jArr2 != null && jArr2.length > 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Field field = (Field) it.next();
                Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
                String genericTypeName = getGenericTypeName(field);
                String algorithm = (encrypt == null || !"java.lang.String".equals(genericTypeName)) ? null : encrypt.algorithm();
                field.setAccessible(true);
                Collection collection = (Collection) field.get(litePalSupport);
                if (!(collection == null || collection.isEmpty())) {
                    String genericTableName = DBUtility.getGenericTableName(litePalSupport.getClassName(), field.getName());
                    String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(litePalSupport.getClassName());
                    int length = jArr2.length;
                    int i = 0;
                    while (i < length) {
                        long j = jArr2[i];
                        SQLiteDatabase sQLiteDatabase = this.mDatabase;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(genericValueIdColumnName);
                        stringBuilder.append(" = ?");
                        sQLiteDatabase.delete(genericTableName, stringBuilder.toString(), new String[]{String.valueOf(j)});
                        Iterator it2 = collection.iterator();
                        while (it2.hasNext()) {
                            Iterator it3;
                            Iterator it4;
                            Object next = it2.next();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(genericValueIdColumnName, Long.valueOf(j));
                            next = encryptValue(algorithm, next);
                            if (litePalSupport.getClassName().equals(genericTypeName)) {
                                LitePalSupport litePalSupport2 = (LitePalSupport) next;
                                if (litePalSupport2 != null) {
                                    long baseObjId = litePalSupport2.getBaseObjId();
                                    if (baseObjId > 0) {
                                        contentValues.put(DBUtility.getM2MSelfRefColumnName(field), Long.valueOf(baseObjId));
                                        it3 = it2;
                                        it4 = it;
                                    }
                                }
                            } else {
                                it3 = it2;
                                it4 = it;
                                DynamicExecutor.send(contentValues, "put", new Object[]{DBUtility.convertToValidColumnName(BaseUtility.changeCase(field.getName())), next}, contentValues.getClass(), new Class[]{String.class, getGenericTypeClass(field)});
                            }
                            this.mDatabase.insert(genericTableName, null, contentValues);
                            it2 = it3;
                            it = it4;
                        }
                        i++;
                        it = it;
                        jArr2 = jArr;
                    }
                }
                it = it;
                jArr2 = jArr;
            }
        }
    }

    private void convertContentValues(ContentValues contentValues) {
        if (VERSION.SDK_INT >= 11) {
            String convertToValidColumnName;
            HashMap hashMap = new HashMap();
            for (String convertToValidColumnName2 : contentValues.keySet()) {
                if (DBUtility.isFieldNameConflictWithSQLiteKeywords(convertToValidColumnName2)) {
                    hashMap.put(convertToValidColumnName2, contentValues.get(convertToValidColumnName2));
                }
            }
            for (String str : hashMap.keySet()) {
                String str2;
                convertToValidColumnName2 = DBUtility.convertToValidColumnName(str2);
                Object obj = contentValues.get(str2);
                contentValues.remove(str2);
                if (obj == null) {
                    contentValues.putNull(convertToValidColumnName2);
                } else {
                    str2 = obj.getClass().getName();
                    if ("java.lang.Byte".equals(str2)) {
                        contentValues.put(convertToValidColumnName2, (Byte) obj);
                    } else if ("[B".equals(str2)) {
                        contentValues.put(convertToValidColumnName2, (byte[]) obj);
                    } else if ("java.lang.Boolean".equals(str2)) {
                        contentValues.put(convertToValidColumnName2, (Boolean) obj);
                    } else if ("java.lang.String".equals(str2)) {
                        contentValues.put(convertToValidColumnName2, (String) obj);
                    } else if ("java.lang.Float".equals(str2)) {
                        contentValues.put(convertToValidColumnName2, (Float) obj);
                    } else if ("java.lang.Long".equals(str2)) {
                        contentValues.put(convertToValidColumnName2, (Long) obj);
                    } else if ("java.lang.Integer".equals(str2)) {
                        contentValues.put(convertToValidColumnName2, (Integer) obj);
                    } else if ("java.lang.Short".equals(str2)) {
                        contentValues.put(convertToValidColumnName2, (Short) obj);
                    } else if ("java.lang.Double".equals(str2)) {
                        contentValues.put(convertToValidColumnName2, (Double) obj);
                    }
                }
            }
        }
    }
}
