package org.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.litepal.parser.LitePalAttr;
import org.litepal.tablemanager.model.AssociationsModel;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.GenericModel;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;
import org.litepal.util.LitePalLog;

public abstract class AssociationUpdater extends Creator {
    public static final String TAG = "AssociationUpdater";
    private Collection<AssociationsModel> mAssociationModels;
    protected SQLiteDatabase mDb;

    public abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* Access modifiers changed, original: protected */
    public void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.mAssociationModels = getAllAssociations();
        this.mDb = sQLiteDatabase;
        removeAssociations();
    }

    /* Access modifiers changed, original: protected */
    public List<String> getForeignKeyColumns(TableModel tableModel) {
        ArrayList arrayList = new ArrayList();
        for (ColumnModel columnModel : getTableModelFromDB(tableModel.getTableName()).getColumnModels()) {
            String columnName = columnModel.getColumnName();
            if (isForeignKeyColumnFormat(columnModel.getColumnName()) && !tableModel.containsColumn(columnName)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("getForeignKeyColumnNames >> foreign key column is ");
                stringBuilder.append(columnName);
                LitePalLog.d("AssociationUpdater", stringBuilder.toString());
                arrayList.add(columnName);
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public boolean isForeignKeyColumn(TableModel tableModel, String str) {
        return BaseUtility.containsIgnoreCases(getForeignKeyColumns(tableModel), str);
    }

    /* Access modifiers changed, original: protected */
    public TableModel getTableModelFromDB(String str) {
        return DBUtility.findPragmaTableInfo(str, this.mDb);
    }

    /* Access modifiers changed, original: protected */
    public void dropTables(List<String> list, SQLiteDatabase sQLiteDatabase) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(generateDropTableSQL((String) list.get(i)));
            }
            execute(arrayList, sQLiteDatabase);
        }
    }

    /* Access modifiers changed, original: protected */
    public void removeColumns(Collection<String> collection, String str) {
        if (collection != null && !collection.isEmpty()) {
            execute(getRemoveColumnSQLs(collection, str), this.mDb);
        }
    }

    /* Access modifiers changed, original: protected */
    public void clearCopyInTableSchema(List<String> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("delete from ");
            stringBuilder.append("table_schema");
            stringBuilder.append(" where");
            Object obj = null;
            for (String str : list) {
                if (obj != null) {
                    stringBuilder.append(" or ");
                }
                obj = 1;
                stringBuilder.append(" lower(");
                stringBuilder.append("name");
                stringBuilder.append(") ");
                stringBuilder.append("=");
                stringBuilder.append(" lower('");
                stringBuilder.append(str);
                stringBuilder.append("')");
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("clear table schema value sql is ");
            stringBuilder2.append(stringBuilder);
            LitePalLog.d("AssociationUpdater", stringBuilder2.toString());
            ArrayList arrayList = new ArrayList();
            arrayList.add(stringBuilder.toString());
            execute(arrayList, this.mDb);
        }
    }

    private void removeAssociations() {
        removeForeignKeyColumns();
        removeIntermediateTables();
        removeGenericTables();
    }

    private void removeForeignKeyColumns() {
        for (String tableModel : LitePalAttr.getInstance().getClassNames()) {
            TableModel tableModel2 = getTableModel(tableModel);
            removeColumns(findForeignKeyToRemove(tableModel2), tableModel2.getTableName());
        }
    }

    private void removeIntermediateTables() {
        List findIntermediateTablesToDrop = findIntermediateTablesToDrop();
        dropTables(findIntermediateTablesToDrop, this.mDb);
        clearCopyInTableSchema(findIntermediateTablesToDrop);
    }

    private void removeGenericTables() {
        List findGenericTablesToDrop = findGenericTablesToDrop();
        dropTables(findGenericTablesToDrop, this.mDb);
        clearCopyInTableSchema(findGenericTablesToDrop);
    }

    private List<String> findForeignKeyToRemove(TableModel tableModel) {
        ArrayList arrayList = new ArrayList();
        List<String> foreignKeyColumns = getForeignKeyColumns(tableModel);
        String tableName = tableModel.getTableName();
        for (String str : foreignKeyColumns) {
            if (shouldDropForeignKey(tableName, DBUtility.getTableNameByForeignColumn(str))) {
                arrayList.add(str);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("findForeignKeyToRemove >> ");
        stringBuilder.append(tableModel.getTableName());
        stringBuilder.append(" ");
        stringBuilder.append(arrayList);
        LitePalLog.d("AssociationUpdater", stringBuilder.toString());
        return arrayList;
    }

    private List<String> findIntermediateTablesToDrop() {
        ArrayList arrayList = new ArrayList();
        for (String str : DBUtility.findAllTableNames(this.mDb)) {
            if (DBUtility.isIntermediateTable(str, this.mDb)) {
                Object obj = 1;
                for (AssociationsModel associationsModel : this.mAssociationModels) {
                    if (associationsModel.getAssociationType() == 3 && str.equalsIgnoreCase(DBUtility.getIntermediateTableName(associationsModel.getTableName(), associationsModel.getAssociatedTableName()))) {
                        obj = null;
                    }
                }
                if (obj != null) {
                    arrayList.add(str);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("findIntermediateTablesToDrop >> ");
        stringBuilder.append(arrayList);
        LitePalLog.d("AssociationUpdater", stringBuilder.toString());
        return arrayList;
    }

    private List<String> findGenericTablesToDrop() {
        ArrayList arrayList = new ArrayList();
        for (String str : DBUtility.findAllTableNames(this.mDb)) {
            if (DBUtility.isGenericTable(str, this.mDb)) {
                Object obj = 1;
                for (GenericModel tableName : getGenericModels()) {
                    if (str.equalsIgnoreCase(tableName.getTableName())) {
                        obj = null;
                    }
                }
                if (obj != null) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public String generateAlterToTempTableSQL(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("alter table ");
        stringBuilder.append(str);
        stringBuilder.append(" rename to ");
        stringBuilder.append(getTempTableName(str));
        return stringBuilder.toString();
    }

    private String generateCreateNewTableSQL(Collection<String> collection, TableModel tableModel) {
        for (String removeColumnModelByName : collection) {
            tableModel.removeColumnModelByName(removeColumnModelByName);
        }
        return generateCreateTableSQL(tableModel);
    }

    /* Access modifiers changed, original: protected */
    public String generateDataMigrationSQL(TableModel tableModel) {
        String tableName = tableModel.getTableName();
        List<ColumnModel> columnModels = tableModel.getColumnModels();
        if (columnModels.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into ");
        stringBuilder.append(tableName);
        stringBuilder.append("(");
        Object obj = null;
        Object obj2 = null;
        for (ColumnModel columnModel : columnModels) {
            if (obj2 != null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(columnModel.getColumnName());
            obj2 = 1;
        }
        stringBuilder.append(") ");
        stringBuilder.append("select ");
        for (ColumnModel columnModel2 : columnModels) {
            if (obj != null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(columnModel2.getColumnName());
            obj = 1;
        }
        stringBuilder.append(" from ");
        stringBuilder.append(getTempTableName(tableName));
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public String generateDropTempTableSQL(String str) {
        return generateDropTableSQL(getTempTableName(str));
    }

    /* Access modifiers changed, original: protected */
    public String getTempTableName(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_temp");
        return stringBuilder.toString();
    }

    private List<String> getRemoveColumnSQLs(Collection<String> collection, String str) {
        TableModel tableModelFromDB = getTableModelFromDB(str);
        String generateAlterToTempTableSQL = generateAlterToTempTableSQL(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("generateRemoveColumnSQL >> ");
        stringBuilder.append(generateAlterToTempTableSQL);
        LitePalLog.d("AssociationUpdater", stringBuilder.toString());
        String generateCreateNewTableSQL = generateCreateNewTableSQL(collection, tableModelFromDB);
        stringBuilder = new StringBuilder();
        stringBuilder.append("generateRemoveColumnSQL >> ");
        stringBuilder.append(generateCreateNewTableSQL);
        LitePalLog.d("AssociationUpdater", stringBuilder.toString());
        String generateDataMigrationSQL = generateDataMigrationSQL(tableModelFromDB);
        stringBuilder = new StringBuilder();
        stringBuilder.append("generateRemoveColumnSQL >> ");
        stringBuilder.append(generateDataMigrationSQL);
        LitePalLog.d("AssociationUpdater", stringBuilder.toString());
        str = generateDropTempTableSQL(str);
        stringBuilder = new StringBuilder();
        stringBuilder.append("generateRemoveColumnSQL >> ");
        stringBuilder.append(str);
        LitePalLog.d("AssociationUpdater", stringBuilder.toString());
        ArrayList arrayList = new ArrayList();
        arrayList.add(generateAlterToTempTableSQL);
        arrayList.add(generateCreateNewTableSQL);
        arrayList.add(generateDataMigrationSQL);
        arrayList.add(str);
        return arrayList;
    }

    private boolean shouldDropForeignKey(String str, String str2) {
        for (AssociationsModel associationsModel : this.mAssociationModels) {
            if (associationsModel.getAssociationType() == 1) {
                if (!str.equalsIgnoreCase(associationsModel.getTableHoldsForeignKey())) {
                    continue;
                } else if (associationsModel.getTableName().equalsIgnoreCase(str)) {
                    if (isRelationCorrect(associationsModel, str, str2)) {
                        return false;
                    }
                } else if (associationsModel.getAssociatedTableName().equalsIgnoreCase(str) && isRelationCorrect(associationsModel, str2, str)) {
                    return false;
                }
            } else if (associationsModel.getAssociationType() == 2 && isRelationCorrect(associationsModel, str2, str)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRelationCorrect(AssociationsModel associationsModel, String str, String str2) {
        return associationsModel.getTableName().equalsIgnoreCase(str) && associationsModel.getAssociatedTableName().equalsIgnoreCase(str2);
    }
}
