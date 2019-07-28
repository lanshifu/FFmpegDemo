package org.litepal.crud;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.litepal.Operator;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.LitePalSupportException;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class DeleteHandler extends DataHandler {
    private List<String> foreignKeyTableToDelete;

    public DeleteHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* Access modifiers changed, original: 0000 */
    public int onDelete(LitePalSupport litePalSupport) {
        if (!litePalSupport.isSaved()) {
            return 0;
        }
        List supportedGenericFields = getSupportedGenericFields(litePalSupport.getClassName());
        deleteGenericData(litePalSupport.getClass(), supportedGenericFields, litePalSupport.getBaseObjId());
        Collection analyzeAssociations = analyzeAssociations(litePalSupport);
        int deleteCascade = deleteCascade(litePalSupport);
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = litePalSupport.getTableName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ");
        stringBuilder.append(litePalSupport.getBaseObjId());
        deleteCascade += sQLiteDatabase.delete(tableName, stringBuilder.toString(), null);
        clearAssociatedModelSaveState(litePalSupport, analyzeAssociations);
        return deleteCascade;
    }

    public int onDelete(Class<?> cls, long j) {
        deleteGenericData(cls, getSupportedGenericFields(cls.getName()), j);
        analyzeAssociations((Class) cls);
        int deleteCascade = deleteCascade(cls, j);
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = getTableName(cls);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ");
        stringBuilder.append(j);
        deleteCascade += sQLiteDatabase.delete(tableName, stringBuilder.toString(), null);
        getForeignKeyTableToDelete().clear();
        return deleteCascade;
    }

    public int onDeleteAll(String str, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return this.mDatabase.delete(str, getWhereClause(strArr), getWhereArgs(strArr));
    }

    public int onDeleteAll(Class<?> cls, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        List supportedGenericFields = getSupportedGenericFields(cls.getName());
        if (!supportedGenericFields.isEmpty()) {
            List find = Operator.select("id").where(strArr).find(cls);
            if (find.size() > 0) {
                long[] jArr = new long[find.size()];
                while (i < jArr.length) {
                    jArr[i] = ((LitePalSupport) find.get(i)).getBaseObjId();
                    i++;
                }
                deleteGenericData(cls, supportedGenericFields, jArr);
            }
        }
        analyzeAssociations((Class) cls);
        i = deleteAllCascade(cls, strArr) + this.mDatabase.delete(getTableName(cls), getWhereClause(strArr), getWhereArgs(strArr));
        getForeignKeyTableToDelete().clear();
        return i;
    }

    private void analyzeAssociations(Class<?> cls) {
        for (AssociationsInfo associationsInfo : getAssociationInfo(cls.getName())) {
            String tableNameByClassName = DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName());
            if (associationsInfo.getAssociationType() == 2 || associationsInfo.getAssociationType() == 1) {
                if (!cls.getName().equals(associationsInfo.getClassHoldsForeignKey())) {
                    getForeignKeyTableToDelete().add(tableNameByClassName);
                }
            } else if (associationsInfo.getAssociationType() == 3) {
                getForeignKeyTableToDelete().add(BaseUtility.changeCase(DBUtility.getIntermediateTableName(getTableName(cls), tableNameByClassName)));
            }
        }
    }

    private int deleteCascade(Class<?> cls, long j) {
        int i = 0;
        for (String str : getForeignKeyTableToDelete()) {
            String foreignKeyColumnName = getForeignKeyColumnName(getTableName(cls));
            SQLiteDatabase sQLiteDatabase = this.mDatabase;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(foreignKeyColumnName);
            stringBuilder.append(" = ");
            stringBuilder.append(j);
            i += sQLiteDatabase.delete(str, stringBuilder.toString(), null);
        }
        return i;
    }

    private int deleteAllCascade(Class<?> cls, String... strArr) {
        int i = 0;
        for (String str : getForeignKeyTableToDelete()) {
            String tableName = getTableName(cls);
            String foreignKeyColumnName = getForeignKeyColumnName(tableName);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(foreignKeyColumnName);
            stringBuilder.append(" in (select id from ");
            stringBuilder.append(tableName);
            if (strArr != null && strArr.length > 0) {
                stringBuilder.append(" where ");
                stringBuilder.append(buildConditionString(strArr));
            }
            stringBuilder.append(")");
            i += this.mDatabase.delete(str, BaseUtility.changeCase(stringBuilder.toString()), null);
        }
        return i;
    }

    private String buildConditionString(String... strArr) {
        int length = strArr.length - 1;
        int i = 0;
        String str = strArr[0];
        while (i < length) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'");
            i++;
            stringBuilder.append(strArr[i]);
            stringBuilder.append("'");
            str = str.replaceFirst("\\?", stringBuilder.toString());
        }
        return str;
    }

    private Collection<AssociationsInfo> analyzeAssociations(LitePalSupport litePalSupport) {
        try {
            Collection associationInfo = getAssociationInfo(litePalSupport.getClassName());
            analyzeAssociatedModels(litePalSupport, associationInfo);
            return associationInfo;
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    private void clearAssociatedModelSaveState(LitePalSupport litePalSupport, Collection<AssociationsInfo> collection) {
        try {
            for (AssociationsInfo associationsInfo : collection) {
                if (associationsInfo.getAssociationType() == 2 && !litePalSupport.getClassName().equals(associationsInfo.getClassHoldsForeignKey())) {
                    Collection<LitePalSupport> associatedModels = getAssociatedModels(litePalSupport, associationsInfo);
                    if (!(associatedModels == null || associatedModels.isEmpty())) {
                        for (LitePalSupport litePalSupport2 : associatedModels) {
                            if (litePalSupport2 != null) {
                                litePalSupport2.clearSavedState();
                            }
                        }
                    }
                } else if (associationsInfo.getAssociationType() == 1) {
                    LitePalSupport associatedModel = getAssociatedModel(litePalSupport, associationsInfo);
                    if (associatedModel != null) {
                        associatedModel.clearSavedState();
                    }
                }
            }
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    private int deleteCascade(LitePalSupport litePalSupport) {
        return deleteAssociatedForeignKeyRows(litePalSupport) + deleteAssociatedJoinTableRows(litePalSupport);
    }

    private int deleteAssociatedForeignKeyRows(LitePalSupport litePalSupport) {
        int i = 0;
        for (String str : litePalSupport.getAssociatedModelsMapWithFK().keySet()) {
            String foreignKeyColumnName = getForeignKeyColumnName(litePalSupport.getTableName());
            SQLiteDatabase sQLiteDatabase = this.mDatabase;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(foreignKeyColumnName);
            stringBuilder.append(" = ");
            stringBuilder.append(litePalSupport.getBaseObjId());
            i += sQLiteDatabase.delete(str, stringBuilder.toString(), null);
        }
        return i;
    }

    private int deleteAssociatedJoinTableRows(LitePalSupport litePalSupport) {
        int i = 0;
        for (String intermediateTableName : litePalSupport.getAssociatedModelsMapForJoinTable().keySet()) {
            String intermediateTableName2 = DBUtility.getIntermediateTableName(litePalSupport.getTableName(), intermediateTableName2);
            String foreignKeyColumnName = getForeignKeyColumnName(litePalSupport.getTableName());
            SQLiteDatabase sQLiteDatabase = this.mDatabase;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(foreignKeyColumnName);
            stringBuilder.append(" = ");
            stringBuilder.append(litePalSupport.getBaseObjId());
            i += sQLiteDatabase.delete(intermediateTableName2, stringBuilder.toString(), null);
        }
        return i;
    }

    private List<String> getForeignKeyTableToDelete() {
        if (this.foreignKeyTableToDelete == null) {
            this.foreignKeyTableToDelete = new ArrayList();
        }
        return this.foreignKeyTableToDelete;
    }

    private void deleteGenericData(Class<?> cls, List<Field> list, long... jArr) {
        for (Field name : list) {
            String genericTableName = DBUtility.getGenericTableName(cls.getName(), name.getName());
            String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(cls.getName());
            int length = jArr.length;
            int i = (length - 1) / CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
            int i2 = 0;
            while (i2 <= i) {
                int i3;
                StringBuilder stringBuilder = new StringBuilder();
                int i4 = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION * i2;
                Object obj = null;
                while (true) {
                    i3 = i2 + 1;
                    if (i4 < CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION * i3 && i4 < length) {
                        long j = jArr[i4];
                        if (obj != null) {
                            stringBuilder.append(" or ");
                        }
                        stringBuilder.append(genericValueIdColumnName);
                        stringBuilder.append(" = ");
                        stringBuilder.append(j);
                        i4++;
                        obj = 1;
                    }
                }
                if (!TextUtils.isEmpty(stringBuilder.toString())) {
                    this.mDatabase.delete(genericTableName, stringBuilder.toString(), null);
                }
                i2 = i3;
            }
        }
    }
}
