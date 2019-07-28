package org.litepal.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.litepal.annotation.Encrypt;
import org.litepal.exceptions.LitePalSupportException;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class SaveHandler extends DataHandler {
    private ContentValues values = new ContentValues();

    private boolean shouldGiveModelIdValue(String str, Class<?> cls, long j) {
        return (str == null || cls == null || j <= 0) ? false : true;
    }

    public SaveHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* Access modifiers changed, original: 0000 */
    public void onSave(LitePalSupport litePalSupport) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String className = litePalSupport.getClassName();
        List supportedFields = getSupportedFields(className);
        List supportedGenericFields = getSupportedGenericFields(className);
        Collection associationInfo = getAssociationInfo(className);
        if (litePalSupport.isSaved()) {
            analyzeAssociatedModels(litePalSupport, associationInfo);
            doUpdateAction(litePalSupport, supportedFields, supportedGenericFields);
            return;
        }
        analyzeAssociatedModels(litePalSupport, associationInfo);
        doSaveAction(litePalSupport, supportedFields, supportedGenericFields);
        analyzeAssociatedModels(litePalSupport, associationInfo);
    }

    public <T extends LitePalSupport> void onSaveAll(Collection<T> collection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (collection != null && collection.size() > 0) {
            int i = 0;
            LitePalSupport[] litePalSupportArr = (LitePalSupport[]) collection.toArray(new LitePalSupport[0]);
            String className = litePalSupportArr[0].getClassName();
            List supportedFields = getSupportedFields(className);
            List supportedGenericFields = getSupportedGenericFields(className);
            Collection associationInfo = getAssociationInfo(className);
            int length = litePalSupportArr.length;
            while (i < length) {
                LitePalSupport litePalSupport = litePalSupportArr[i];
                if (litePalSupport.isSaved()) {
                    analyzeAssociatedModels(litePalSupport, associationInfo);
                    doUpdateAction(litePalSupport, supportedFields, supportedGenericFields);
                } else {
                    analyzeAssociatedModels(litePalSupport, associationInfo);
                    doSaveAction(litePalSupport, supportedFields, supportedGenericFields);
                    analyzeAssociatedModels(litePalSupport, associationInfo);
                }
                litePalSupport.clearAssociatedData();
                i++;
            }
        }
    }

    private void doSaveAction(LitePalSupport litePalSupport, List<Field> list, List<Field> list2) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this.values.clear();
        beforeSave(litePalSupport, list, this.values);
        afterSave(litePalSupport, list, list2, saving(litePalSupport, this.values));
    }

    private void beforeSave(LitePalSupport litePalSupport, List<Field> list, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        putFieldsValue(litePalSupport, list, contentValues);
        putForeignKeyValue(contentValues, litePalSupport);
    }

    private long saving(LitePalSupport litePalSupport, ContentValues contentValues) {
        if (contentValues.size() == 0) {
            contentValues.putNull("id");
        }
        return this.mDatabase.insert(litePalSupport.getTableName(), null, contentValues);
    }

    private void afterSave(LitePalSupport litePalSupport, List<Field> list, List<Field> list2, long j) throws IllegalAccessException, InvocationTargetException {
        throwIfSaveFailed(j);
        assignIdValue(litePalSupport, getIdField(list), j);
        updateGenericTables(litePalSupport, list2, j);
        updateAssociatedTableWithFK(litePalSupport);
        insertIntermediateJoinTableValue(litePalSupport, false);
    }

    private void doUpdateAction(LitePalSupport litePalSupport, List<Field> list, List<Field> list2) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this.values.clear();
        beforeUpdate(litePalSupport, list, this.values);
        updating(litePalSupport, this.values);
        afterUpdate(litePalSupport, list2);
    }

    private void beforeUpdate(LitePalSupport litePalSupport, List<Field> list, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        putFieldsValue(litePalSupport, list, contentValues);
        putForeignKeyValue(contentValues, litePalSupport);
        for (String putNull : litePalSupport.getListToClearSelfFK()) {
            contentValues.putNull(putNull);
        }
    }

    private void updating(LitePalSupport litePalSupport, ContentValues contentValues) {
        if (contentValues.size() > 0) {
            this.mDatabase.update(litePalSupport.getTableName(), contentValues, "id = ?", new String[]{String.valueOf(litePalSupport.getBaseObjId())});
        }
    }

    private void afterUpdate(LitePalSupport litePalSupport, List<Field> list) throws InvocationTargetException, IllegalAccessException {
        updateGenericTables(litePalSupport, list, litePalSupport.getBaseObjId());
        updateAssociatedTableWithFK(litePalSupport);
        insertIntermediateJoinTableValue(litePalSupport, true);
        clearFKValueInAssociatedTable(litePalSupport);
    }

    private Field getIdField(List<Field> list) {
        for (Field field : list) {
            if (isIdColumn(field.getName())) {
                return field;
            }
        }
        return null;
    }

    private void throwIfSaveFailed(long j) {
        if (j == -1) {
            throw new LitePalSupportException("Save current model failed.");
        }
    }

    private void assignIdValue(LitePalSupport litePalSupport, Field field, long j) {
        try {
            giveBaseObjIdValue(litePalSupport, j);
            if (field != null) {
                giveModelIdValue(litePalSupport, field.getName(), field.getType(), j);
            }
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    private void giveModelIdValue(LitePalSupport litePalSupport, String str, Class<?> cls, long j) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (shouldGiveModelIdValue(str, cls, j)) {
            Object valueOf;
            if (cls == Integer.TYPE || cls == Integer.class) {
                valueOf = Integer.valueOf((int) j);
            } else if (cls == Long.TYPE || cls == Long.class) {
                valueOf = Long.valueOf(j);
            } else {
                throw new LitePalSupportException("id type is not supported. Only int or long is acceptable for id");
            }
            DynamicExecutor.setField(litePalSupport, str, valueOf, litePalSupport.getClass());
        }
    }

    private void putForeignKeyValue(ContentValues contentValues, LitePalSupport litePalSupport) {
        Map associatedModelsMapWithoutFK = litePalSupport.getAssociatedModelsMapWithoutFK();
        for (String str : associatedModelsMapWithoutFK.keySet()) {
            contentValues.put(getForeignKeyColumnName(str), (Long) associatedModelsMapWithoutFK.get(str));
        }
    }

    private void updateAssociatedTableWithFK(LitePalSupport litePalSupport) {
        Map associatedModelsMapWithFK = litePalSupport.getAssociatedModelsMapWithFK();
        ContentValues contentValues = new ContentValues();
        for (String str : associatedModelsMapWithFK.keySet()) {
            contentValues.clear();
            contentValues.put(getForeignKeyColumnName(litePalSupport.getTableName()), Long.valueOf(litePalSupport.getBaseObjId()));
            Set set = (Set) associatedModelsMapWithFK.get(str);
            if (!(set == null || set.isEmpty())) {
                this.mDatabase.update(str, contentValues, getWhereOfIdsWithOr((Collection) set), null);
            }
        }
    }

    private void clearFKValueInAssociatedTable(LitePalSupport litePalSupport) {
        for (String str : litePalSupport.getListToClearAssociatedFK()) {
            String foreignKeyColumnName = getForeignKeyColumnName(litePalSupport.getTableName());
            ContentValues contentValues = new ContentValues();
            contentValues.putNull(foreignKeyColumnName);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(foreignKeyColumnName);
            stringBuilder.append(" = ");
            stringBuilder.append(litePalSupport.getBaseObjId());
            this.mDatabase.update(str, contentValues, stringBuilder.toString(), null);
        }
    }

    private void insertIntermediateJoinTableValue(LitePalSupport litePalSupport, boolean z) {
        Map associatedModelsMapForJoinTable = litePalSupport.getAssociatedModelsMapForJoinTable();
        ContentValues contentValues = new ContentValues();
        for (String str : associatedModelsMapForJoinTable.keySet()) {
            String intermediateTableName = getIntermediateTableName(litePalSupport, str);
            if (z) {
                this.mDatabase.delete(intermediateTableName, getWhereForJoinTableToDelete(litePalSupport), new String[]{String.valueOf(litePalSupport.getBaseObjId())});
            }
            for (Long longValue : (List) associatedModelsMapForJoinTable.get(str)) {
                long longValue2 = longValue.longValue();
                contentValues.clear();
                contentValues.put(getForeignKeyColumnName(litePalSupport.getTableName()), Long.valueOf(litePalSupport.getBaseObjId()));
                contentValues.put(getForeignKeyColumnName(str), Long.valueOf(longValue2));
                this.mDatabase.insert(intermediateTableName, null, contentValues);
            }
        }
    }

    private String getWhereForJoinTableToDelete(LitePalSupport litePalSupport) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getForeignKeyColumnName(litePalSupport.getTableName()));
        stringBuilder.append(" = ?");
        return stringBuilder.toString();
    }

    private void updateGenericTables(LitePalSupport litePalSupport, List<Field> list, long j) throws IllegalAccessException, InvocationTargetException {
        for (Field field : list) {
            Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
            String genericTypeName = getGenericTypeName(field);
            String algorithm = (encrypt == null || !"java.lang.String".equals(genericTypeName)) ? null : encrypt.algorithm();
            int i = 1;
            field.setAccessible(true);
            Collection collection = (Collection) field.get(litePalSupport);
            if (collection != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("updateGenericTables: class name is ");
                stringBuilder.append(litePalSupport.getClassName());
                stringBuilder.append(" , field name is ");
                stringBuilder.append(field.getName());
                Log.d("DataHandler", stringBuilder.toString());
                String genericTableName = DBUtility.getGenericTableName(litePalSupport.getClassName(), field.getName());
                String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(litePalSupport.getClassName());
                SQLiteDatabase sQLiteDatabase = this.mDatabase;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(genericValueIdColumnName);
                stringBuilder2.append(" = ?");
                sQLiteDatabase.delete(genericTableName, stringBuilder2.toString(), new String[]{String.valueOf(j)});
                for (Object next : collection) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(genericValueIdColumnName, Long.valueOf(j));
                    Object next2 = encryptValue(algorithm, next2);
                    if (litePalSupport.getClassName().equals(genericTypeName)) {
                        LitePalSupport litePalSupport2 = (LitePalSupport) next2;
                        if (litePalSupport2 != null) {
                            long baseObjId = litePalSupport2.getBaseObjId();
                            if (baseObjId > 0) {
                                contentValues.put(DBUtility.getM2MSelfRefColumnName(field), Long.valueOf(baseObjId));
                            }
                        }
                    } else {
                        DynamicExecutor.send(contentValues, "put", new Object[]{BaseUtility.changeCase(DBUtility.convertToValidColumnName(field.getName())), next2}, contentValues.getClass(), new Class[]{String.class, getGenericTypeClass(field)});
                    }
                    this.mDatabase.insert(genericTableName, null, contentValues);
                    i = 1;
                }
            }
        }
    }
}
