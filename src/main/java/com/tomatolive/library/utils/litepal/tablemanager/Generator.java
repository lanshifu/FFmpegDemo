package com.tomatolive.library.utils.litepal.tablemanager;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.tomatolive.library.utils.litepal.LitePalBase;
import com.tomatolive.library.utils.litepal.exceptions.DatabaseGenerateException;
import com.tomatolive.library.utils.litepal.parser.LitePalAttr;
import com.tomatolive.library.utils.litepal.tablemanager.model.AssociationsModel;
import com.tomatolive.library.utils.litepal.tablemanager.model.TableModel;
import com.tomatolive.library.utils.litepal.util.BaseUtility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Generator extends LitePalBase {
    public static final String TAG = "Generator";
    private Collection<AssociationsModel> mAllRelationModels;
    private Collection<TableModel> mTableModels;

    public abstract void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z);

    public abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* Access modifiers changed, original: protected */
    public Collection<TableModel> getAllTableModels() {
        if (this.mTableModels == null) {
            this.mTableModels = new ArrayList();
        }
        if (!canUseCache()) {
            this.mTableModels.clear();
            for (String tableModel : LitePalAttr.getInstance().getClassNames()) {
                this.mTableModels.add(getTableModel(tableModel));
            }
        }
        return this.mTableModels;
    }

    /* Access modifiers changed, original: protected */
    public Collection<AssociationsModel> getAllAssociations() {
        if (this.mAllRelationModels == null || this.mAllRelationModels.isEmpty()) {
            this.mAllRelationModels = getAssociations(LitePalAttr.getInstance().getClassNames());
        }
        return this.mAllRelationModels;
    }

    /* Access modifiers changed, original: protected */
    public void execute(List<String> list, SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder;
        String str = "";
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    for (String str2 : list) {
                        String str22;
                        if (!TextUtils.isEmpty(str22)) {
                            str22 = BaseUtility.changeCase(str22);
                            try {
                                sQLiteDatabase.execSQL(str22);
                                str = str22;
                            } catch (SQLException unused) {
                                str = str22;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("An exception that indicates there was an error with SQL parsing or execution. ");
                                stringBuilder.append(str);
                                throw new DatabaseGenerateException(stringBuilder.toString());
                            }
                        }
                    }
                }
            } catch (SQLException unused2) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("An exception that indicates there was an error with SQL parsing or execution. ");
                stringBuilder.append(str);
                throw new DatabaseGenerateException(stringBuilder.toString());
            }
        }
    }

    private static void addAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        new Creator().addOrUpdateAssociation(sQLiteDatabase, z);
    }

    private static void updateAssociations(SQLiteDatabase sQLiteDatabase) {
        new Upgrader().addOrUpdateAssociation(sQLiteDatabase, false);
    }

    private static void upgradeTables(SQLiteDatabase sQLiteDatabase) {
        new Upgrader().createOrUpgradeTable(sQLiteDatabase, false);
    }

    private static void create(SQLiteDatabase sQLiteDatabase, boolean z) {
        new Creator().createOrUpgradeTable(sQLiteDatabase, z);
    }

    private static void drop(SQLiteDatabase sQLiteDatabase) {
        new Dropper().createOrUpgradeTable(sQLiteDatabase, false);
    }

    private boolean canUseCache() {
        boolean z = false;
        if (this.mTableModels == null) {
            return false;
        }
        if (this.mTableModels.size() == LitePalAttr.getInstance().getClassNames().size()) {
            z = true;
        }
        return z;
    }

    static void create(SQLiteDatabase sQLiteDatabase) {
        create(sQLiteDatabase, true);
        addAssociation(sQLiteDatabase, true);
    }

    static void upgrade(SQLiteDatabase sQLiteDatabase) {
        drop(sQLiteDatabase);
        create(sQLiteDatabase, false);
        updateAssociations(sQLiteDatabase);
        upgradeTables(sQLiteDatabase);
        addAssociation(sQLiteDatabase, false);
    }
}
