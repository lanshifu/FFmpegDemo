package org.litepal.crud;

import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class QueryHandler extends DataHandler {
    public QueryHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    public <T> T onFind(Class<T> cls, long j, boolean z) {
        List query = query(cls, null, "id = ?", new String[]{String.valueOf(j)}, null, null, null, null, getForeignKeyAssociations(cls.getName(), z));
        return query.size() > 0 ? query.get(0) : null;
    }

    public <T> T onFindFirst(Class<T> cls, boolean z) {
        List query = query(cls, null, null, null, null, null, "id", "1", getForeignKeyAssociations(cls.getName(), z));
        return query.size() > 0 ? query.get(0) : null;
    }

    public <T> T onFindLast(Class<T> cls, boolean z) {
        List query = query(cls, null, null, null, null, null, "id desc", "1", getForeignKeyAssociations(cls.getName(), z));
        return query.size() > 0 ? query.get(0) : null;
    }

    public <T> List<T> onFindAll(Class<T> cls, boolean z, long... jArr) {
        if (isAffectAllLines(jArr)) {
            return query(cls, null, null, null, null, null, "id", null, getForeignKeyAssociations(cls.getName(), z));
        }
        return query(cls, null, getWhereOfIdsWithOr(jArr), null, null, null, "id", null, getForeignKeyAssociations(cls.getName(), z));
    }

    public <T> List<T> onFind(Class<T> cls, String[] strArr, String[] strArr2, String str, String str2, boolean z) {
        String[] strArr3 = strArr2;
        BaseUtility.checkConditionsCorrect(strArr2);
        if (strArr3 != null && strArr3.length > 0) {
            strArr3[0] = DBUtility.convertWhereClauseToColumnName(strArr3[0]);
        }
        String convertOrderByClauseToValidName = DBUtility.convertOrderByClauseToValidName(str);
        return query(cls, strArr, getWhereClause(strArr2), getWhereArgs(strArr2), null, null, convertOrderByClauseToValidName, str2, getForeignKeyAssociations(cls.getName(), z));
    }

    public int onCount(String str, String[] strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return ((Integer) mathQuery(str, new String[]{"count(1)"}, strArr, Integer.TYPE)).intValue();
    }

    public double onAverage(String str, String str2, String[] strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        String[] strArr2 = new String[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("avg(");
        stringBuilder.append(str2);
        stringBuilder.append(")");
        strArr2[0] = stringBuilder.toString();
        return ((Double) mathQuery(str, strArr2, strArr, Double.TYPE)).doubleValue();
    }

    public <T> T onMax(String str, String str2, String[] strArr, Class<T> cls) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        String[] strArr2 = new String[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("max(");
        stringBuilder.append(str2);
        stringBuilder.append(")");
        strArr2[0] = stringBuilder.toString();
        return mathQuery(str, strArr2, strArr, cls);
    }

    public <T> T onMin(String str, String str2, String[] strArr, Class<T> cls) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        String[] strArr2 = new String[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("min(");
        stringBuilder.append(str2);
        stringBuilder.append(")");
        strArr2[0] = stringBuilder.toString();
        return mathQuery(str, strArr2, strArr, cls);
    }

    public <T> T onSum(String str, String str2, String[] strArr, Class<T> cls) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        String[] strArr2 = new String[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sum(");
        stringBuilder.append(str2);
        stringBuilder.append(")");
        strArr2[0] = stringBuilder.toString();
        return mathQuery(str, strArr2, strArr, cls);
    }
}
