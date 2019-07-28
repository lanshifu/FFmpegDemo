package org.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.BaseUtility;

public class Dropper extends AssociationUpdater {
    private Collection<TableModel> mTableModels;

    /* Access modifiers changed, original: protected */
    public void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.mTableModels = getAllTableModels();
        this.mDb = sQLiteDatabase;
        dropTables();
    }

    private void dropTables() {
        List findTablesToDrop = findTablesToDrop();
        dropTables(findTablesToDrop, this.mDb);
        clearCopyInTableSchema(findTablesToDrop);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006b  */
    /* JADX WARNING: Missing block: B:11:0x0053, code skipped:
            if (r2 != null) goto L_0x0064;
     */
    /* JADX WARNING: Missing block: B:19:0x0062, code skipped:
            if (r2 != null) goto L_0x0064;
     */
    /* JADX WARNING: Missing block: B:20:0x0064, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:21:0x0067, code skipped:
            return r0;
     */
    private java.util.List<java.lang.String> findTablesToDrop() {
        /*
        r11 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 0;
        r2 = r11.mDb;	 Catch:{ Exception -> 0x005b, all -> 0x0058 }
        r3 = "table_schema";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x005b, all -> 0x0058 }
        r1 = r2.moveToFirst();	 Catch:{ Exception -> 0x0056 }
        if (r1 == 0) goto L_0x0053;
    L_0x001a:
        r1 = "name";
        r1 = r2.getColumnIndexOrThrow(r1);	 Catch:{ Exception -> 0x0056 }
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x0056 }
        r3 = "type";
        r3 = r2.getColumnIndexOrThrow(r3);	 Catch:{ Exception -> 0x0056 }
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x0056 }
        r3 = r11.shouldDropThisTable(r1, r3);	 Catch:{ Exception -> 0x0056 }
        if (r3 == 0) goto L_0x004d;
    L_0x0034:
        r3 = "AssociationUpdater";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0056 }
        r4.<init>();	 Catch:{ Exception -> 0x0056 }
        r5 = "need to drop ";
        r4.append(r5);	 Catch:{ Exception -> 0x0056 }
        r4.append(r1);	 Catch:{ Exception -> 0x0056 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0056 }
        org.litepal.util.LitePalLog.d(r3, r4);	 Catch:{ Exception -> 0x0056 }
        r0.add(r1);	 Catch:{ Exception -> 0x0056 }
    L_0x004d:
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x0056 }
        if (r1 != 0) goto L_0x001a;
    L_0x0053:
        if (r2 == 0) goto L_0x0067;
    L_0x0055:
        goto L_0x0064;
    L_0x0056:
        r1 = move-exception;
        goto L_0x005f;
    L_0x0058:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0069;
    L_0x005b:
        r2 = move-exception;
        r10 = r2;
        r2 = r1;
        r1 = r10;
    L_0x005f:
        r1.printStackTrace();	 Catch:{ all -> 0x0068 }
        if (r2 == 0) goto L_0x0067;
    L_0x0064:
        r2.close();
    L_0x0067:
        return r0;
    L_0x0068:
        r0 = move-exception;
    L_0x0069:
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r2.close();
    L_0x006e:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.tablemanager.Dropper.findTablesToDrop():java.util.List");
    }

    private List<String> pickTableNamesFromTableModels() {
        ArrayList arrayList = new ArrayList();
        for (TableModel tableName : this.mTableModels) {
            arrayList.add(tableName.getTableName());
        }
        return arrayList;
    }

    private boolean shouldDropThisTable(String str, int i) {
        return !BaseUtility.containsIgnoreCases(pickTableNamesFromTableModels(), str) && i == 0;
    }
}
