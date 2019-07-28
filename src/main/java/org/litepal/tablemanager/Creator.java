package org.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.DBUtility;

class Creator extends AssociationCreator {
    public static final String TAG = "Creator";

    Creator() {
    }

    /* Access modifiers changed, original: protected */
    public void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        for (TableModel createOrUpgradeTable : getAllTableModels()) {
            createOrUpgradeTable(createOrUpgradeTable, sQLiteDatabase, z);
        }
    }

    /* Access modifiers changed, original: protected */
    public void createOrUpgradeTable(TableModel tableModel, SQLiteDatabase sQLiteDatabase, boolean z) {
        execute(getCreateTableSQLs(tableModel, sQLiteDatabase, z), sQLiteDatabase);
        giveTableSchemaACopy(tableModel.getTableName(), 0, sQLiteDatabase);
    }

    /* Access modifiers changed, original: protected */
    public List<String> getCreateTableSQLs(TableModel tableModel, SQLiteDatabase sQLiteDatabase, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(generateDropTableSQL(tableModel));
            arrayList.add(generateCreateTableSQL(tableModel));
        } else if (DBUtility.isTableExists(tableModel.getTableName(), sQLiteDatabase)) {
            return null;
        } else {
            arrayList.add(generateCreateTableSQL(tableModel));
        }
        return arrayList;
    }

    private String generateDropTableSQL(TableModel tableModel) {
        return generateDropTableSQL(tableModel.getTableName());
    }

    /* Access modifiers changed, original: 0000 */
    public String generateCreateTableSQL(TableModel tableModel) {
        return generateCreateTableSQL(tableModel.getTableName(), tableModel.getColumnModels(), true);
    }
}
