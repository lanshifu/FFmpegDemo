package com.tomatolive.library.utils.litepal.crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.tomatolive.library.utils.litepal.LitePalBase;
import com.tomatolive.library.utils.litepal.Operator;
import com.tomatolive.library.utils.litepal.annotation.Encrypt;
import com.tomatolive.library.utils.litepal.crud.model.AssociationsInfo;
import com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;
import com.tomatolive.library.utils.litepal.util.BaseUtility;
import com.tomatolive.library.utils.litepal.util.DBUtility;
import com.tomatolive.library.utils.litepal.util.cipher.CipherUtil;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

abstract class DataHandler extends LitePalBase {
    public static final String TAG = "DataHandler";
    private List<AssociationsInfo> fkInCurrentModel;
    private List<AssociationsInfo> fkInOtherModel;
    SQLiteDatabase mDatabase;
    private LitePalSupport tempEmptyModel;

    class QueryInfoCache {
        Field field;
        String getMethodName;

        QueryInfoCache() {
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldGetOrSet(LitePalSupport litePalSupport, Field field) {
        return (litePalSupport == null || field == null) ? false : true;
    }

    DataHandler() {
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a5  */
    public <T> java.util.List<T> query(java.lang.Class<T> r17, java.lang.String[] r18, java.lang.String r19, java.lang.String[] r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.util.List<com.tomatolive.library.utils.litepal.crud.model.AssociationsInfo> r25) {
        /*
        r16 = this;
        r1 = r16;
        r0 = r25;
        r2 = new java.util.ArrayList;
        r2.<init>();
        r3 = 0;
        r4 = r17.getName();	 Catch:{ Exception -> 0x0098 }
        r4 = r1.getSupportedFields(r4);	 Catch:{ Exception -> 0x0098 }
        r5 = r17.getName();	 Catch:{ Exception -> 0x0098 }
        r5 = r1.getSupportedGenericFields(r5);	 Catch:{ Exception -> 0x0098 }
        r6 = r18;
        r6 = r1.getCustomizedColumns(r6, r5, r0);	 Catch:{ Exception -> 0x0098 }
        r9 = com.tomatolive.library.utils.litepal.util.DBUtility.convertSelectClauseToValidNames(r6);	 Catch:{ Exception -> 0x0098 }
        r8 = r16.getTableName(r17);	 Catch:{ Exception -> 0x0098 }
        r7 = r1.mDatabase;	 Catch:{ Exception -> 0x0098 }
        r10 = r19;
        r11 = r20;
        r12 = r21;
        r13 = r22;
        r14 = r23;
        r15 = r24;
        r6 = r7.query(r8, r9, r10, r11, r12, r13, r14, r15);	 Catch:{ Exception -> 0x0098 }
        r3 = r6.moveToFirst();	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        if (r3 == 0) goto L_0x008a;
    L_0x0040:
        r3 = new android.util.SparseArray;	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r3.<init>();	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r7 = new java.util.HashMap;	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r7.<init>();	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
    L_0x004a:
        r8 = r16.createInstanceFromClass(r17);	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r9 = r8;
        r9 = (com.tomatolive.library.utils.litepal.crud.LitePalSupport) r9;	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r10 = "id";
        r10 = r6.getColumnIndexOrThrow(r10);	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r10 = r6.getLong(r10);	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r1.giveBaseObjIdValue(r9, r10);	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r18 = r16;
        r19 = r8;
        r20 = r4;
        r21 = r25;
        r22 = r6;
        r23 = r3;
        r18.setValueToModel(r19, r20, r21, r22, r23);	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r9 = r8;
        r9 = (com.tomatolive.library.utils.litepal.crud.LitePalSupport) r9;	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r1.setGenericValueToModel(r9, r5, r7);	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        if (r0 == 0) goto L_0x007b;
    L_0x0075:
        r9 = r8;
        r9 = (com.tomatolive.library.utils.litepal.crud.LitePalSupport) r9;	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r1.setAssociatedModel(r9);	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
    L_0x007b:
        r2.add(r8);	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r8 = r6.moveToNext();	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        if (r8 != 0) goto L_0x004a;
    L_0x0084:
        r3.clear();	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
        r7.clear();	 Catch:{ Exception -> 0x0092, all -> 0x0090 }
    L_0x008a:
        if (r6 == 0) goto L_0x008f;
    L_0x008c:
        r6.close();
    L_0x008f:
        return r2;
    L_0x0090:
        r0 = move-exception;
        goto L_0x00a3;
    L_0x0092:
        r0 = move-exception;
        r3 = r6;
        goto L_0x0099;
    L_0x0095:
        r0 = move-exception;
        r6 = r3;
        goto L_0x00a3;
    L_0x0098:
        r0 = move-exception;
    L_0x0099:
        r2 = new com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;	 Catch:{ all -> 0x0095 }
        r4 = r0.getMessage();	 Catch:{ all -> 0x0095 }
        r2.<init>(r4, r0);	 Catch:{ all -> 0x0095 }
        throw r2;	 Catch:{ all -> 0x0095 }
    L_0x00a3:
        if (r6 == 0) goto L_0x00a8;
    L_0x00a5:
        r6.close();
    L_0x00a8:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.crud.DataHandler.query(java.lang.Class, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List):java.util.List");
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058  */
    public <T> T mathQuery(java.lang.String r10, java.lang.String[] r11, java.lang.String[] r12, java.lang.Class<T> r13) {
        /*
        r9 = this;
        com.tomatolive.library.utils.litepal.util.BaseUtility.checkConditionsCorrect(r12);
        r0 = 0;
        r1 = r9.mDatabase;	 Catch:{ Exception -> 0x004b }
        r4 = r9.getWhereClause(r12);	 Catch:{ Exception -> 0x004b }
        r5 = r9.getWhereArgs(r12);	 Catch:{ Exception -> 0x004b }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r2 = r10;
        r3 = r11;
        r10 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x004b }
        r11 = r10.moveToFirst();	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        if (r11 == 0) goto L_0x003d;
    L_0x001d:
        r11 = r10.getClass();	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        r12 = r9.genGetColumnMethod(r13);	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        r13 = 1;
        r0 = new java.lang.Class[r13];	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        r1 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        r2 = 0;
        r0[r2] = r1;	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        r11 = r11.getMethod(r12, r0);	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        r12 = new java.lang.Object[r13];	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        r13 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        r12[r2] = r13;	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
        r0 = r11.invoke(r10, r12);	 Catch:{ Exception -> 0x0045, all -> 0x0043 }
    L_0x003d:
        if (r10 == 0) goto L_0x0042;
    L_0x003f:
        r10.close();
    L_0x0042:
        return r0;
    L_0x0043:
        r11 = move-exception;
        goto L_0x0056;
    L_0x0045:
        r11 = move-exception;
        r0 = r10;
        goto L_0x004c;
    L_0x0048:
        r11 = move-exception;
        r10 = r0;
        goto L_0x0056;
    L_0x004b:
        r11 = move-exception;
    L_0x004c:
        r10 = new com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;	 Catch:{ all -> 0x0048 }
        r12 = r11.getMessage();	 Catch:{ all -> 0x0048 }
        r10.<init>(r12, r11);	 Catch:{ all -> 0x0048 }
        throw r10;	 Catch:{ all -> 0x0048 }
    L_0x0056:
        if (r10 == 0) goto L_0x005b;
    L_0x0058:
        r10.close();
    L_0x005b:
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.crud.DataHandler.mathQuery(java.lang.String, java.lang.String[], java.lang.String[], java.lang.Class):java.lang.Object");
    }

    /* Access modifiers changed, original: protected */
    public void giveBaseObjIdValue(LitePalSupport litePalSupport, long j) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (j > 0) {
            DynamicExecutor.set(litePalSupport, "baseObjId", Long.valueOf(j), LitePalSupport.class);
        }
    }

    /* Access modifiers changed, original: protected */
    public void putFieldsValue(LitePalSupport litePalSupport, List<Field> list, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (Field field : list) {
            if (!isIdColumn(field.getName())) {
                putFieldsValueDependsOnSaveOrUpdate(litePalSupport, field, contentValues);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void putContentValuesForSave(LitePalSupport litePalSupport, Field field, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object field2 = DynamicExecutor.getField(litePalSupport, field.getName(), litePalSupport.getClass());
        if (field2 != null) {
            if ("java.util.Date".equals(field.getType().getName())) {
                field2 = Long.valueOf(((Date) field2).getTime());
            }
            Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
            if (encrypt != null && "java.lang.String".equals(field.getType().getName())) {
                field2 = encryptValue(encrypt.algorithm(), field2);
            }
            Object[] objArr = new Object[]{BaseUtility.changeCase(DBUtility.convertToValidColumnName(field.getName())), field2};
            DynamicExecutor.send(contentValues, "put", objArr, contentValues.getClass(), getParameterTypes(field, field2, objArr));
        }
    }

    /* Access modifiers changed, original: protected */
    public void putContentValuesForUpdate(LitePalSupport litePalSupport, Field field, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object fieldValue = getFieldValue(litePalSupport, field);
        if ("java.util.Date".equals(field.getType().getName()) && fieldValue != null) {
            fieldValue = Long.valueOf(((Date) fieldValue).getTime());
        }
        Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
        if (encrypt != null && "java.lang.String".equals(field.getType().getName())) {
            fieldValue = encryptValue(encrypt.algorithm(), fieldValue);
        }
        Object[] objArr = new Object[]{BaseUtility.changeCase(DBUtility.convertToValidColumnName(field.getName())), fieldValue};
        DynamicExecutor.send(contentValues, "put", objArr, contentValues.getClass(), getParameterTypes(field, fieldValue, objArr));
    }

    /* Access modifiers changed, original: protected */
    public Object encryptValue(String str, Object obj) {
        if (str == null || obj == null) {
            return obj;
        }
        if ("AES".equalsIgnoreCase(str)) {
            return CipherUtil.aesEncrypt((String) obj);
        }
        return "MD5".equalsIgnoreCase(str) ? CipherUtil.md5Encrypt((String) obj) : obj;
    }

    /* Access modifiers changed, original: protected */
    public Object getFieldValue(LitePalSupport litePalSupport, Field field) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return shouldGetOrSet(litePalSupport, field) ? DynamicExecutor.getField(litePalSupport, field.getName(), litePalSupport.getClass()) : null;
    }

    /* Access modifiers changed, original: protected */
    public void setFieldValue(LitePalSupport litePalSupport, Field field, Object obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (shouldGetOrSet(litePalSupport, field)) {
            DynamicExecutor.setField(litePalSupport, field.getName(), obj, litePalSupport.getClass());
        }
    }

    /* Access modifiers changed, original: protected */
    public void analyzeAssociatedModels(LitePalSupport litePalSupport, Collection<AssociationsInfo> collection) {
        try {
            for (AssociationsInfo associationsInfo : collection) {
                if (associationsInfo.getAssociationType() == 2) {
                    new Many2OneAnalyzer().analyze(litePalSupport, associationsInfo);
                } else if (associationsInfo.getAssociationType() == 1) {
                    new One2OneAnalyzer().analyze(litePalSupport, associationsInfo);
                } else if (associationsInfo.getAssociationType() == 3) {
                    new Many2ManyAnalyzer().analyze(litePalSupport, associationsInfo);
                }
            }
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    /* Access modifiers changed, original: protected */
    public LitePalSupport getAssociatedModel(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (LitePalSupport) getFieldValue(litePalSupport, associationsInfo.getAssociateOtherModelFromSelf());
    }

    /* Access modifiers changed, original: protected */
    public Collection<LitePalSupport> getAssociatedModels(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (Collection) getFieldValue(litePalSupport, associationsInfo.getAssociateOtherModelFromSelf());
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d A:{ExcHandler: Exception (r5_3 'e' java.lang.Exception), Splitter:B:5:0x0008} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:10:0x001b, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:11:0x001d, code skipped:
            r5 = move-exception;
     */
    /* JADX WARNING: Missing block: B:13:0x0027, code skipped:
            throw new com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException(r5.getMessage(), r5);
     */
    public com.tomatolive.library.utils.litepal.crud.LitePalSupport getEmptyModel(com.tomatolive.library.utils.litepal.crud.LitePalSupport r5) {
        /*
        r4 = this;
        r0 = r4.tempEmptyModel;
        if (r0 == 0) goto L_0x0007;
    L_0x0004:
        r5 = r4.tempEmptyModel;
        return r5;
    L_0x0007:
        r0 = 0;
        r5 = r5.getClassName();	 Catch:{ ClassNotFoundException -> 0x0043, InstantiationException -> 0x0028, Exception -> 0x001d }
        r0 = java.lang.Class.forName(r5);	 Catch:{ ClassNotFoundException -> 0x0044, InstantiationException -> 0x001b, Exception -> 0x001d }
        r0 = r0.newInstance();	 Catch:{ ClassNotFoundException -> 0x0044, InstantiationException -> 0x001b, Exception -> 0x001d }
        r0 = (com.tomatolive.library.utils.litepal.crud.LitePalSupport) r0;	 Catch:{ ClassNotFoundException -> 0x0044, InstantiationException -> 0x001b, Exception -> 0x001d }
        r4.tempEmptyModel = r0;	 Catch:{ ClassNotFoundException -> 0x0044, InstantiationException -> 0x001b, Exception -> 0x001d }
        r0 = r4.tempEmptyModel;	 Catch:{ ClassNotFoundException -> 0x0044, InstantiationException -> 0x001b, Exception -> 0x001d }
        return r0;
    L_0x001b:
        r0 = move-exception;
        goto L_0x002c;
    L_0x001d:
        r5 = move-exception;
        r0 = new com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;
        r1 = r5.getMessage();
        r0.<init>(r1, r5);
        throw r0;
    L_0x0028:
        r5 = move-exception;
        r3 = r0;
        r0 = r5;
        r5 = r3;
    L_0x002c:
        r1 = new com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r5);
        r5 = " needs a default constructor.";
        r2.append(r5);
        r5 = r2.toString();
        r1.<init>(r5, r0);
        throw r1;
    L_0x0043:
        r5 = r0;
    L_0x0044:
        r0 = new com.tomatolive.library.utils.litepal.exceptions.DatabaseGenerateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "can not find a class named ";
        r1.append(r2);
        r1.append(r5);
        r5 = r1.toString();
        r0.<init>(r5);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.crud.DataHandler.getEmptyModel(com.tomatolive.library.utils.litepal.crud.LitePalSupport):com.tomatolive.library.utils.litepal.crud.LitePalSupport");
    }

    /* Access modifiers changed, original: protected|varargs */
    public String getWhereClause(String... strArr) {
        if (isAffectAllLines(strArr) || strArr == null || strArr.length <= 0) {
            return null;
        }
        return strArr[0];
    }

    /* Access modifiers changed, original: protected|varargs */
    public String[] getWhereArgs(String... strArr) {
        if (isAffectAllLines(strArr) || strArr == null || strArr.length <= 1) {
            return null;
        }
        String[] strArr2 = new String[(strArr.length - 1)];
        System.arraycopy(strArr, 1, strArr2, 0, strArr.length - 1);
        return strArr2;
    }

    /* Access modifiers changed, original: protected|varargs */
    public boolean isAffectAllLines(Object... objArr) {
        return objArr != null && objArr.length == 0;
    }

    /* Access modifiers changed, original: protected */
    public String getWhereOfIdsWithOr(Collection<Long> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        for (Long longValue : collection) {
            long longValue2 = longValue.longValue();
            if (obj != null) {
                stringBuilder.append(" or ");
            }
            obj = 1;
            stringBuilder.append("id = ");
            stringBuilder.append(longValue2);
        }
        return BaseUtility.changeCase(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected|varargs */
    public String getWhereOfIdsWithOr(long... jArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = jArr.length;
        int i = 0;
        Object obj = null;
        while (i < length) {
            long j = jArr[i];
            if (obj != null) {
                stringBuilder.append(" or ");
            }
            stringBuilder.append("id = ");
            stringBuilder.append(j);
            i++;
            obj = 1;
        }
        return BaseUtility.changeCase(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public String getIntermediateTableName(LitePalSupport litePalSupport, String str) {
        return BaseUtility.changeCase(DBUtility.getIntermediateTableName(litePalSupport.getTableName(), str));
    }

    /* Access modifiers changed, original: protected */
    public String getTableName(Class<?> cls) {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName()));
    }

    /* Access modifiers changed, original: protected */
    public Object createInstanceFromClass(Class<?> cls) {
        try {
            Constructor findBestSuitConstructor = findBestSuitConstructor(cls);
            return findBestSuitConstructor.newInstance(getConstructorParams(cls, findBestSuitConstructor));
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    /* Access modifiers changed, original: protected */
    public Constructor<?> findBestSuitConstructor(Class<?> cls) {
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        SparseArray sparseArray = new SparseArray();
        int i = Filter.MAX;
        for (Constructor constructor : declaredConstructors) {
            int length = constructor.getParameterTypes().length;
            int i2 = length;
            for (Class<?> cls2 : constructor.getParameterTypes()) {
                if (cls2 == cls) {
                    i2 += 10000;
                } else if (cls2.getName().startsWith("com.android") && cls2.getName().endsWith("InstantReloadException")) {
                    i2 += 10000;
                }
            }
            if (sparseArray.get(i2) == null) {
                sparseArray.put(i2, constructor);
            }
            if (i2 < i) {
                i = i2;
            }
        }
        Constructor constructor2 = (Constructor) sparseArray.get(i);
        if (constructor2 != null) {
            constructor2.setAccessible(true);
        }
        return constructor2;
    }

    /* Access modifiers changed, original: protected */
    public Object[] getConstructorParams(Class<?> cls, Constructor<?> constructor) {
        Class[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            objArr[i] = getInitParamValue(cls, parameterTypes[i]);
        }
        return objArr;
    }

    /* Access modifiers changed, original: protected */
    public void setValueToModel(Object obj, List<Field> list, List<AssociationsInfo> list2, Cursor cursor, SparseArray<QueryInfoCache> sparseArray) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int size = sparseArray.size();
        QueryInfoCache queryInfoCache;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                int keyAt = sparseArray.keyAt(i);
                queryInfoCache = (QueryInfoCache) sparseArray.get(keyAt);
                setToModelByReflection(obj, queryInfoCache.field, keyAt, queryInfoCache.getMethodName, cursor);
            }
        } else {
            for (Field field : list) {
                String genGetColumnMethod = genGetColumnMethod(field);
                int columnIndex = cursor.getColumnIndex(BaseUtility.changeCase(isIdColumn(field.getName()) ? "id" : DBUtility.convertToValidColumnName(field.getName())));
                if (columnIndex != -1) {
                    setToModelByReflection(obj, field, columnIndex, genGetColumnMethod, cursor);
                    queryInfoCache = new QueryInfoCache();
                    queryInfoCache.getMethodName = genGetColumnMethod;
                    queryInfoCache.field = field;
                    sparseArray.put(columnIndex, queryInfoCache);
                }
            }
        }
        if (list2 != null) {
            for (AssociationsInfo associationsInfo : list2) {
                int columnIndex2 = cursor.getColumnIndex(getForeignKeyColumnName(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName())));
                if (columnIndex2 != -1) {
                    try {
                        LitePalSupport litePalSupport = (LitePalSupport) Operator.find(Class.forName(associationsInfo.getAssociatedClassName()), cursor.getLong(columnIndex2));
                        if (litePalSupport != null) {
                            setFieldValue((LitePalSupport) obj, associationsInfo.getAssociateOtherModelFromSelf(), litePalSupport);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00db  */
    public void setGenericValueToModel(com.tomatolive.library.utils.litepal.crud.LitePalSupport r21, java.util.List<java.lang.reflect.Field> r22, java.util.Map<java.lang.reflect.Field, com.tomatolive.library.utils.litepal.tablemanager.model.GenericModel> r23) throws java.lang.NoSuchMethodException, java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
        r20 = this;
        r7 = r20;
        r0 = r23;
        r8 = r22.iterator();
    L_0x0008:
        r1 = r8.hasNext();
        if (r1 == 0) goto L_0x00df;
    L_0x000e:
        r1 = r8.next();
        r9 = r1;
        r9 = (java.lang.reflect.Field) r9;
        r1 = 0;
        r2 = r0.get(r9);
        r2 = (com.tomatolive.library.utils.litepal.tablemanager.model.GenericModel) r2;
        if (r2 != 0) goto L_0x006c;
    L_0x001e:
        r2 = r7.getGenericTypeName(r9);
        r3 = r21.getClassName();
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0033;
    L_0x002c:
        r2 = com.tomatolive.library.utils.litepal.util.DBUtility.getM2MSelfRefColumnName(r9);
        r3 = "getLong";
        goto L_0x003f;
    L_0x0033:
        r2 = r9.getName();
        r2 = com.tomatolive.library.utils.litepal.util.DBUtility.convertToValidColumnName(r2);
        r3 = r7.genGetColumnMethod(r9);
    L_0x003f:
        r4 = r21.getClassName();
        r5 = r9.getName();
        r4 = com.tomatolive.library.utils.litepal.util.DBUtility.getGenericTableName(r4, r5);
        r5 = r21.getClassName();
        r5 = com.tomatolive.library.utils.litepal.util.DBUtility.getGenericValueIdColumnName(r5);
        r6 = new com.tomatolive.library.utils.litepal.tablemanager.model.GenericModel;
        r6.<init>();
        r6.setTableName(r4);
        r6.setValueColumnName(r2);
        r6.setValueIdColumnName(r5);
        r6.setGetMethodName(r3);
        r0.put(r9, r6);
        r18 = r2;
        r19 = r3;
        goto L_0x0080;
    L_0x006c:
        r4 = r2.getTableName();
        r3 = r2.getValueColumnName();
        r5 = r2.getValueIdColumnName();
        r2 = r2.getGetMethodName();
        r19 = r2;
        r18 = r3;
    L_0x0080:
        r11 = r4;
        r10 = r7.mDatabase;	 Catch:{ all -> 0x00d8 }
        r12 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d8 }
        r2.<init>();	 Catch:{ all -> 0x00d8 }
        r2.append(r5);	 Catch:{ all -> 0x00d8 }
        r3 = " = ?";
        r2.append(r3);	 Catch:{ all -> 0x00d8 }
        r13 = r2.toString();	 Catch:{ all -> 0x00d8 }
        r2 = 1;
        r14 = new java.lang.String[r2];	 Catch:{ all -> 0x00d8 }
        r2 = 0;
        r3 = r21.getBaseObjId();	 Catch:{ all -> 0x00d8 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x00d8 }
        r14[r2] = r3;	 Catch:{ all -> 0x00d8 }
        r15 = 0;
        r16 = 0;
        r17 = 0;
        r10 = r10.query(r11, r12, r13, r14, r15, r16, r17);	 Catch:{ all -> 0x00d8 }
        r1 = r10.moveToFirst();	 Catch:{ all -> 0x00d5 }
        if (r1 == 0) goto L_0x00ce;
    L_0x00b2:
        r1 = com.tomatolive.library.utils.litepal.util.BaseUtility.changeCase(r18);	 Catch:{ all -> 0x00d5 }
        r4 = r10.getColumnIndex(r1);	 Catch:{ all -> 0x00d5 }
        r1 = -1;
        if (r4 == r1) goto L_0x00c8;
    L_0x00bd:
        r1 = r20;
        r2 = r21;
        r3 = r9;
        r5 = r19;
        r6 = r10;
        r1.setToModelByReflection(r2, r3, r4, r5, r6);	 Catch:{ all -> 0x00d5 }
    L_0x00c8:
        r1 = r10.moveToNext();	 Catch:{ all -> 0x00d5 }
        if (r1 != 0) goto L_0x00b2;
    L_0x00ce:
        if (r10 == 0) goto L_0x0008;
    L_0x00d0:
        r10.close();
        goto L_0x0008;
    L_0x00d5:
        r0 = move-exception;
        r1 = r10;
        goto L_0x00d9;
    L_0x00d8:
        r0 = move-exception;
    L_0x00d9:
        if (r1 == 0) goto L_0x00de;
    L_0x00db:
        r1.close();
    L_0x00de:
        throw r0;
    L_0x00df:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.crud.DataHandler.setGenericValueToModel(com.tomatolive.library.utils.litepal.crud.LitePalSupport, java.util.List, java.util.Map):void");
    }

    /* Access modifiers changed, original: protected */
    public List<AssociationsInfo> getForeignKeyAssociations(String str, boolean z) {
        if (!z) {
            return null;
        }
        analyzeAssociations(str);
        return this.fkInCurrentModel;
    }

    /* Access modifiers changed, original: protected */
    public Class<?>[] getParameterTypes(Field field, Object obj, Object[] objArr) {
        if (isCharType(field)) {
            objArr[1] = String.valueOf(obj);
            return new Class[]{String.class, String.class};
        }
        Class<?>[] clsArr;
        if (field.getType().isPrimitive()) {
            clsArr = new Class[]{String.class, getObjectType(field.getType())};
        } else if ("java.util.Date".equals(field.getType().getName())) {
            return new Class[]{String.class, Long.class};
        } else {
            clsArr = new Class[]{String.class, field.getType()};
        }
        return clsArr;
    }

    private Class<?> getObjectType(Class<?> cls) {
        if (cls != null && cls.isPrimitive()) {
            String name = cls.getName();
            if ("int".equals(name)) {
                return Integer.class;
            }
            if ("short".equals(name)) {
                return Short.class;
            }
            if ("long".equals(name)) {
                return Long.class;
            }
            if ("float".equals(name)) {
                return Float.class;
            }
            if ("double".equals(name)) {
                return Double.class;
            }
            if ("boolean".equals(name)) {
                return Boolean.class;
            }
            if ("char".equals(name)) {
                return Character.class;
            }
        }
        return null;
    }

    private Object getInitParamValue(Class<?> cls, Class<?> cls2) {
        String name = cls2.getName();
        if ("boolean".equals(name) || "java.lang.Boolean".equals(name)) {
            return Boolean.valueOf(false);
        }
        if ("float".equals(name) || "java.lang.Float".equals(name)) {
            return Float.valueOf(CropImageView.DEFAULT_ASPECT_RATIO);
        }
        if ("double".equals(name) || "java.lang.Double".equals(name)) {
            return Double.valueOf(0.0d);
        }
        if ("int".equals(name) || "java.lang.Integer".equals(name)) {
            return Integer.valueOf(0);
        }
        if ("long".equals(name) || "java.lang.Long".equals(name)) {
            return Long.valueOf(0);
        }
        if ("short".equals(name) || "java.lang.Short".equals(name)) {
            return Integer.valueOf(0);
        }
        if ("char".equals(name) || "java.lang.Character".equals(name)) {
            return Character.valueOf(' ');
        }
        if ("[B".equals(name) || "[Ljava.lang.Byte;".equals(name)) {
            return new byte[0];
        }
        if ("java.lang.String".equals(name)) {
            return "";
        }
        if (cls == cls2) {
            return null;
        }
        return createInstanceFromClass(cls2);
    }

    private boolean isCharType(Field field) {
        String name = field.getType().getName();
        return name.equals("char") || name.endsWith("Character");
    }

    private boolean isPrimitiveBooleanType(Field field) {
        return "boolean".equals(field.getType().getName());
    }

    private void putFieldsValueDependsOnSaveOrUpdate(LitePalSupport litePalSupport, Field field, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (isUpdating()) {
            if (!isFieldWithDefaultValue(litePalSupport, field)) {
                putContentValuesForUpdate(litePalSupport, field, contentValues);
            }
        } else if (isSaving()) {
            putContentValuesForSave(litePalSupport, field, contentValues);
        }
    }

    private boolean isUpdating() {
        return UpdateHandler.class.getName().equals(getClass().getName());
    }

    private boolean isSaving() {
        return SaveHandler.class.getName().equals(getClass().getName());
    }

    private boolean isFieldWithDefaultValue(LitePalSupport litePalSupport, Field field) throws IllegalAccessException, SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        LitePalSupport emptyModel = getEmptyModel(litePalSupport);
        Object fieldValue = getFieldValue(litePalSupport, field);
        Object fieldValue2 = getFieldValue(emptyModel, field);
        if (fieldValue != null && fieldValue2 != null) {
            return fieldValue.toString().equals(fieldValue2.toString());
        }
        return fieldValue == fieldValue2;
    }

    private String makeGetterMethodName(Field field) {
        String str;
        String name = field.getName();
        if (isPrimitiveBooleanType(field)) {
            if (name.matches("^is[A-Z]{1}.*$")) {
                name = name.substring(2);
            }
            str = "is";
        } else {
            str = "get";
        }
        StringBuilder stringBuilder;
        if (name.matches("^[a-z]{1}[A-Z]{1}.*")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(name);
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(BaseUtility.capitalize(name));
        return stringBuilder.toString();
    }

    private String makeSetterMethodName(Field field) {
        String str = "set";
        StringBuilder stringBuilder;
        if (isPrimitiveBooleanType(field) && field.getName().matches("^is[A-Z]{1}.*$")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(field.getName().substring(2));
            return stringBuilder.toString();
        } else if (field.getName().matches("^[a-z]{1}[A-Z]{1}.*")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(field.getName());
            return stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(BaseUtility.capitalize(field.getName()));
            return stringBuilder.toString();
        }
    }

    private String genGetColumnMethod(Field field) {
        Class genericTypeClass;
        if (isCollection(field.getType())) {
            genericTypeClass = getGenericTypeClass(field);
        } else {
            genericTypeClass = field.getType();
        }
        return genGetColumnMethod(genericTypeClass);
    }

    private String genGetColumnMethod(Class<?> cls) {
        String capitalize;
        if (cls.isPrimitive()) {
            capitalize = BaseUtility.capitalize(cls.getName());
        } else {
            capitalize = cls.getSimpleName();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(capitalize);
        capitalize = stringBuilder.toString();
        if ("getBoolean".equals(capitalize)) {
            return "getInt";
        }
        if ("getChar".equals(capitalize) || "getCharacter".equals(capitalize)) {
            return "getString";
        }
        if ("getDate".equals(capitalize)) {
            return "getLong";
        }
        if ("getInteger".equals(capitalize)) {
            return "getInt";
        }
        return "getbyte[]".equalsIgnoreCase(capitalize) ? "getBlob" : capitalize;
    }

    private String[] getCustomizedColumns(String[] strArr, List<Field> list, List<AssociationsInfo> list2) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (Field name : list) {
            arrayList2.add(name.getName());
        }
        Object obj = null;
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (BaseUtility.containsIgnoreCases(arrayList2, str)) {
                arrayList3.add(Integer.valueOf(i));
            } else if (isIdColumn(str)) {
                if ("_id".equalsIgnoreCase(str)) {
                    arrayList.set(i, BaseUtility.changeCase("id"));
                }
                obj = 1;
            }
        }
        for (int size = arrayList3.size() - 1; size >= 0; size--) {
            arrayList4.add((String) arrayList.remove(((Integer) arrayList3.get(size)).intValue()));
        }
        for (Field field : list) {
            if (BaseUtility.containsIgnoreCases(arrayList4, field.getName())) {
                arrayList5.add(field);
            }
        }
        list.clear();
        list.addAll(arrayList5);
        if (list2 != null && list2.size() > 0) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                arrayList.add(getForeignKeyColumnName(DBUtility.getTableNameByClassName(((AssociationsInfo) list2.get(i2)).getAssociatedClassName())));
            }
        }
        if (obj == null) {
            arrayList.add(BaseUtility.changeCase("id"));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void analyzeAssociations(String str) {
        Collection<AssociationsInfo> associationInfo = getAssociationInfo(str);
        if (this.fkInCurrentModel == null) {
            this.fkInCurrentModel = new ArrayList();
        } else {
            this.fkInCurrentModel.clear();
        }
        if (this.fkInOtherModel == null) {
            this.fkInOtherModel = new ArrayList();
        } else {
            this.fkInOtherModel.clear();
        }
        for (AssociationsInfo associationsInfo : associationInfo) {
            if (associationsInfo.getAssociationType() == 2 || associationsInfo.getAssociationType() == 1) {
                if (associationsInfo.getClassHoldsForeignKey().equals(str)) {
                    this.fkInCurrentModel.add(associationsInfo);
                } else {
                    this.fkInOtherModel.add(associationsInfo);
                }
            } else if (associationsInfo.getAssociationType() == 3) {
                this.fkInOtherModel.add(associationsInfo);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0174 A:{LOOP_END, LOOP:1: B:21:0x00f1->B:42:0x0174} */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x016d A:{SYNTHETIC, EDGE_INSN: B:71:0x016d->B:41:0x016d ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x000f A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01a9  */
    private void setAssociatedModel(com.tomatolive.library.utils.litepal.crud.LitePalSupport r27) {
        /*
        r26 = this;
        r7 = r26;
        r0 = r27;
        r1 = r7.fkInOtherModel;
        if (r1 != 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r1 = r7.fkInOtherModel;
        r8 = r1.iterator();
    L_0x000f:
        r1 = r8.hasNext();
        if (r1 == 0) goto L_0x01ad;
    L_0x0015:
        r1 = r8.next();
        r9 = r1;
        r9 = (com.tomatolive.library.utils.litepal.crud.model.AssociationsInfo) r9;
        r10 = r9.getAssociatedClassName();
        r2 = r9.getAssociationType();
        r3 = 3;
        r4 = 0;
        r11 = 1;
        if (r2 != r3) goto L_0x002b;
    L_0x0029:
        r12 = 1;
        goto L_0x002c;
    L_0x002b:
        r12 = 0;
    L_0x002c:
        r13 = r7.getSupportedFields(r10);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r14 = r7.getSupportedGenericFields(r10);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r15 = 2;
        if (r12 == 0) goto L_0x0099;
    L_0x0037:
        r2 = r27.getTableName();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r3 = com.tomatolive.library.utils.litepal.util.DBUtility.getTableNameByClassName(r10);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r5 = com.tomatolive.library.utils.litepal.util.DBUtility.getIntermediateTableName(r2, r3);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r6.<init>();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = "select * from ";
        r6.append(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r6.append(r3);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = " a inner join ";
        r6.append(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r6.append(r5);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = " b on a.id = b.";
        r6.append(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1.<init>();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1.append(r3);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r3 = "_id";
        r1.append(r3);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r6.append(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = " where b.";
        r6.append(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r6.append(r2);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = "_id = ?";
        r6.append(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = new java.lang.String[r15];	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r2 = r6.toString();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r2 = com.tomatolive.library.utils.litepal.util.BaseUtility.changeCase(r2);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1[r4] = r2;	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r2 = r27.getBaseObjId();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1[r11] = r2;	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = com.tomatolive.library.utils.litepal.Operator.findBySQL(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        goto L_0x00de;
    L_0x0099:
        r1 = r9.getSelfClassName();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = com.tomatolive.library.utils.litepal.util.DBUtility.getTableNameByClassName(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = r7.getForeignKeyColumnName(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r2 = com.tomatolive.library.utils.litepal.util.DBUtility.getTableNameByClassName(r10);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r3 = r7.mDatabase;	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r18 = com.tomatolive.library.utils.litepal.util.BaseUtility.changeCase(r2);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r19 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r2.<init>();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r2.append(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = "=?";
        r2.append(r1);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r20 = r2.toString();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1 = new java.lang.String[r11];	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r5 = r27.getBaseObjId();	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r2 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r1[r4] = r2;	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
        r22 = 0;
        r23 = 0;
        r24 = 0;
        r25 = 0;
        r17 = r3;
        r21 = r1;
        r1 = r17.query(r18, r19, r20, r21, r22, r23, r24, r25);	 Catch:{ Exception -> 0x0198, all -> 0x0194 }
    L_0x00de:
        r6 = r1;
        if (r6 == 0) goto L_0x018b;
    L_0x00e1:
        r1 = r6.moveToFirst();	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        if (r1 == 0) goto L_0x018b;
    L_0x00e7:
        r16 = new android.util.SparseArray;	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r16.<init>();	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r5 = new java.util.HashMap;	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r5.<init>();	 Catch:{ Exception -> 0x0185, all -> 0x017f }
    L_0x00f1:
        r1 = java.lang.Class.forName(r10);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r1 = r7.createInstanceFromClass(r1);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r4 = r1;
        r4 = (com.tomatolive.library.utils.litepal.crud.LitePalSupport) r4;	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r1 = "id";
        r1 = r6.getColumnIndexOrThrow(r1);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r1 = r6.getLong(r1);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r7.giveBaseObjIdValue(r4, r1);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r17 = 0;
        r1 = r26;
        r2 = r4;
        r3 = r13;
        r11 = r4;
        r4 = r17;
        r15 = r5;
        r5 = r6;
        r17 = r6;
        r6 = r16;
        r1.setValueToModel(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r7.setGenericValueToModel(r11, r14, r15);	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r1 = r9.getAssociationType();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r2 = 2;
        if (r1 == r2) goto L_0x0137;
    L_0x0125:
        if (r12 == 0) goto L_0x0128;
    L_0x0127:
        goto L_0x0137;
    L_0x0128:
        r1 = r9.getAssociationType();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r3 = 1;
        if (r1 != r3) goto L_0x0167;
    L_0x012f:
        r1 = r9.getAssociateOtherModelFromSelf();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r7.setFieldValue(r0, r1, r11);	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        goto L_0x0167;
    L_0x0137:
        r3 = 1;
        r1 = r9.getAssociateOtherModelFromSelf();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r4 = r7.getFieldValue(r0, r1);	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r4 = (java.util.Collection) r4;	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        if (r4 != 0) goto L_0x0164;
    L_0x0144:
        r4 = r1.getType();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r4 = r7.isList(r4);	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        if (r4 == 0) goto L_0x0154;
    L_0x014e:
        r4 = new java.util.ArrayList;	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r4.<init>();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        goto L_0x0159;
    L_0x0154:
        r4 = new java.util.HashSet;	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r4.<init>();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
    L_0x0159:
        r1 = r1.getName();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r5 = r27.getClass();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        com.tomatolive.library.utils.litepal.crud.DynamicExecutor.setField(r0, r1, r4, r5);	 Catch:{ Exception -> 0x017d, all -> 0x017b }
    L_0x0164:
        r4.add(r11);	 Catch:{ Exception -> 0x017d, all -> 0x017b }
    L_0x0167:
        r1 = r17.moveToNext();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        if (r1 != 0) goto L_0x0174;
    L_0x016d:
        r16.clear();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        r15.clear();	 Catch:{ Exception -> 0x017d, all -> 0x017b }
        goto L_0x018d;
    L_0x0174:
        r5 = r15;
        r6 = r17;
        r11 = 1;
        r15 = 2;
        goto L_0x00f1;
    L_0x017b:
        r0 = move-exception;
        goto L_0x0182;
    L_0x017d:
        r0 = move-exception;
        goto L_0x0188;
    L_0x017f:
        r0 = move-exception;
        r17 = r6;
    L_0x0182:
        r16 = r17;
        goto L_0x01a7;
    L_0x0185:
        r0 = move-exception;
        r17 = r6;
    L_0x0188:
        r1 = r17;
        goto L_0x019a;
    L_0x018b:
        r17 = r6;
    L_0x018d:
        if (r17 == 0) goto L_0x000f;
    L_0x018f:
        r17.close();
        goto L_0x000f;
    L_0x0194:
        r0 = move-exception;
        r16 = 0;
        goto L_0x01a7;
    L_0x0198:
        r0 = move-exception;
        r1 = 0;
    L_0x019a:
        r2 = new com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;	 Catch:{ all -> 0x01a4 }
        r3 = r0.getMessage();	 Catch:{ all -> 0x01a4 }
        r2.<init>(r3, r0);	 Catch:{ all -> 0x01a4 }
        throw r2;	 Catch:{ all -> 0x01a4 }
    L_0x01a4:
        r0 = move-exception;
        r16 = r1;
    L_0x01a7:
        if (r16 == 0) goto L_0x01ac;
    L_0x01a9:
        r16.close();
    L_0x01ac:
        throw r0;
    L_0x01ad:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.crud.DataHandler.setAssociatedModel(com.tomatolive.library.utils.litepal.crud.LitePalSupport):void");
    }

    private void setToModelByReflection(Object obj, Field field, int i, String str, Cursor cursor) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object invoke = cursor.getClass().getMethod(str, new Class[]{Integer.TYPE}).invoke(cursor, new Object[]{Integer.valueOf(i)});
        if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
            if ("0".equals(String.valueOf(invoke))) {
                invoke = Boolean.valueOf(false);
            } else if ("1".equals(String.valueOf(invoke))) {
                invoke = Boolean.valueOf(true);
            }
        } else if (field.getType() == Character.TYPE || field.getType() == Character.class) {
            invoke = Character.valueOf(((String) invoke).charAt(0));
        } else if (field.getType() == Date.class) {
            long longValue = ((Long) invoke).longValue();
            invoke = longValue <= 0 ? null : new Date(longValue);
        }
        if (isCollection(field.getType())) {
            Collection collection = (Collection) DynamicExecutor.getField(obj, field.getName(), obj.getClass());
            if (collection == null) {
                if (isList(field.getType())) {
                    collection = new ArrayList();
                } else {
                    collection = new HashSet();
                }
                DynamicExecutor.setField(obj, field.getName(), collection, obj.getClass());
            }
            String genericTypeName = getGenericTypeName(field);
            if ("java.lang.String".equals(genericTypeName)) {
                Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
                if (encrypt != null) {
                    invoke = decryptValue(encrypt.algorithm(), invoke);
                }
            } else if (obj.getClass().getName().equals(genericTypeName) && ((invoke instanceof Long) || (invoke instanceof Integer))) {
                invoke = Operator.find(obj.getClass(), ((Long) invoke).longValue());
            }
            collection.add(invoke);
            return;
        }
        Encrypt encrypt2 = (Encrypt) field.getAnnotation(Encrypt.class);
        if (encrypt2 != null && "java.lang.String".equals(field.getType().getName())) {
            invoke = decryptValue(encrypt2.algorithm(), invoke);
        }
        DynamicExecutor.setField(obj, field.getName(), invoke, obj.getClass());
    }

    /* Access modifiers changed, original: protected */
    public Object decryptValue(String str, Object obj) {
        return (str == null || obj == null || !"AES".equalsIgnoreCase(str)) ? obj : CipherUtil.aesDecrypt((String) obj);
    }
}
