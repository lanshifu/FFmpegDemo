package com.tomatolive.library.utils.litepal.crud;

import android.database.Cursor;
import com.tomatolive.library.utils.litepal.crud.model.AssociationsInfo;
import com.tomatolive.library.utils.litepal.tablemanager.Connector;
import com.tomatolive.library.utils.litepal.util.BaseUtility;
import com.tomatolive.library.utils.litepal.util.DBUtility;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class Many2ManyAnalyzer extends AssociationsAnalyzer {
    /* Access modifiers changed, original: 0000 */
    public void analyze(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Collection<LitePalSupport> associatedModels = getAssociatedModels(litePalSupport, associationsInfo);
        declareAssociations(litePalSupport, associationsInfo);
        if (associatedModels != null) {
            for (LitePalSupport litePalSupport2 : associatedModels) {
                Collection checkAssociatedModelCollection = checkAssociatedModelCollection(getReverseAssociatedModels(litePalSupport2, associationsInfo), associationsInfo.getAssociateSelfFromOtherModel());
                addNewModelForAssociatedModel(checkAssociatedModelCollection, litePalSupport);
                setReverseAssociatedModels(litePalSupport2, associationsInfo, checkAssociatedModelCollection);
                dealAssociatedModel(litePalSupport, litePalSupport2);
            }
        }
    }

    private void declareAssociations(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) {
        litePalSupport.addEmptyModelForJoinTable(getAssociatedTableName(associationsInfo));
    }

    private void addNewModelForAssociatedModel(Collection<LitePalSupport> collection, LitePalSupport litePalSupport) {
        if (!collection.contains(litePalSupport)) {
            collection.add(litePalSupport);
        }
    }

    private void dealAssociatedModel(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        if (litePalSupport2.isSaved()) {
            litePalSupport.addAssociatedModelForJoinTable(litePalSupport2.getTableName(), litePalSupport2.getBaseObjId());
        }
    }

    private String getAssociatedTableName(AssociationsInfo associationsInfo) {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName()));
    }

    @Deprecated
    private boolean isDataExists(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        Exception e;
        Throwable th;
        boolean z = true;
        Cursor cursor = null;
        try {
            Cursor query = Connector.getDatabase().query(getJoinTableName(litePalSupport, litePalSupport2), null, getSelection(litePalSupport, litePalSupport2), getSelectionArgs(litePalSupport, litePalSupport2), null, null, null);
            try {
                if (query.getCount() <= 0) {
                    z = false;
                }
                query.close();
                return z;
            } catch (Exception e2) {
                e = e2;
                cursor = query;
                try {
                    e.printStackTrace();
                    cursor.close();
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    cursor.close();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = query;
                cursor.close();
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            cursor.close();
            return true;
        }
    }

    private String getSelection(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getForeignKeyColumnName(litePalSupport.getTableName()));
        stringBuilder.append(" = ? and ");
        stringBuilder.append(getForeignKeyColumnName(litePalSupport2.getTableName()));
        stringBuilder.append(" = ?");
        return stringBuilder.toString();
    }

    private String[] getSelectionArgs(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        return new String[]{String.valueOf(litePalSupport.getBaseObjId()), String.valueOf(litePalSupport2.getBaseObjId())};
    }

    private String getJoinTableName(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        return getIntermediateTableName(litePalSupport, litePalSupport2.getTableName());
    }
}
