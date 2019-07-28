package com.tomatolive.library.utils.litepal.crud;

import android.database.sqlite.SQLiteDatabase;
import com.tomatolive.library.utils.litepal.Operator;
import com.tomatolive.library.utils.litepal.crud.async.SaveExecutor;
import com.tomatolive.library.utils.litepal.crud.async.UpdateOrDeleteExecutor;
import com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;
import com.tomatolive.library.utils.litepal.tablemanager.Connector;
import com.tomatolive.library.utils.litepal.util.BaseUtility;
import com.tomatolive.library.utils.litepal.util.DBUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LitePalSupport {
    protected static final String AES = "AES";
    protected static final String MD5 = "MD5";
    Map<String, List<Long>> associatedModelsMapForJoinTable;
    private Map<String, Set<Long>> associatedModelsMapWithFK;
    private Map<String, Long> associatedModelsMapWithoutFK;
    long baseObjId;
    private List<String> fieldsToSetToDefault;
    private List<String> listToClearAssociatedFK;
    private List<String> listToClearSelfFK;

    public int delete() {
        int onDelete;
        synchronized (LitePalSupport.class) {
            SQLiteDatabase database = Connector.getDatabase();
            database.beginTransaction();
            try {
                onDelete = new DeleteHandler(database).onDelete(this);
                this.baseObjId = 0;
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
        }
        return onDelete;
    }

    public UpdateOrDeleteExecutor deleteAsync() {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final int delete = LitePalSupport.this.delete();
                    if (updateOrDeleteExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(delete);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public int update(long j) {
        int onUpdate;
        synchronized (LitePalSupport.class) {
            try {
                onUpdate = new UpdateHandler(Connector.getDatabase()).onUpdate(this, j);
                getFieldsToSetToDefault().clear();
            } catch (Exception e) {
                throw new LitePalSupportException(e.getMessage(), e);
            } catch (Throwable th) {
            }
        }
        return onUpdate;
    }

    public UpdateOrDeleteExecutor updateAsync(final long j) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final int update = LitePalSupport.this.update(j);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(update);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public int updateAll(String... strArr) {
        int onUpdateAll;
        synchronized (LitePalSupport.class) {
            try {
                onUpdateAll = new UpdateHandler(Connector.getDatabase()).onUpdateAll(this, strArr);
                getFieldsToSetToDefault().clear();
            } catch (Exception e) {
                throw new LitePalSupportException(e.getMessage(), e);
            } catch (Throwable th) {
            }
        }
        return onUpdateAll;
    }

    public UpdateOrDeleteExecutor updateAllAsync(final String... strArr) {
        final UpdateOrDeleteExecutor updateOrDeleteExecutor = new UpdateOrDeleteExecutor();
        updateOrDeleteExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final int updateAll = LitePalSupport.this.updateAll(strArr);
                    if (updateOrDeleteExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                updateOrDeleteExecutor.getListener().onFinish(updateAll);
                            }
                        });
                    }
                }
            }
        });
        return updateOrDeleteExecutor;
    }

    public boolean save() {
        try {
            saveThrows();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SaveExecutor saveAsync() {
        final SaveExecutor saveExecutor = new SaveExecutor();
        saveExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final boolean save = LitePalSupport.this.save();
                    if (saveExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                saveExecutor.getListener().onFinish(save);
                            }
                        });
                    }
                }
            }
        });
        return saveExecutor;
    }

    public void saveThrows() {
        synchronized (LitePalSupport.class) {
            SQLiteDatabase database = Connector.getDatabase();
            database.beginTransaction();
            try {
                new SaveHandler(database).onSave(this);
                clearAssociatedData();
                database.setTransactionSuccessful();
                database.endTransaction();
            } catch (Exception e) {
                throw new LitePalSupportException(e.getMessage(), e);
            } catch (Throwable th) {
                database.endTransaction();
            }
        }
    }

    @Deprecated
    public boolean saveIfNotExist(String... strArr) {
        return !Operator.isExist(getClass(), strArr) ? save() : false;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:3:0x0005, B:28:0x005a] */
    /* JADX WARNING: Missing block: B:35:0x0063, code skipped:
            r1.endTransaction();
     */
    public boolean saveOrUpdate(java.lang.String... r5) {
        /*
        r4 = this;
        r0 = com.tomatolive.library.utils.litepal.crud.LitePalSupport.class;
        monitor-enter(r0);
        if (r5 != 0) goto L_0x000d;
    L_0x0005:
        r5 = r4.save();	 Catch:{ all -> 0x000b }
        monitor-exit(r0);	 Catch:{ all -> 0x000b }
        return r5;
    L_0x000b:
        r5 = move-exception;
        goto L_0x0067;
    L_0x000d:
        r5 = com.tomatolive.library.utils.litepal.Operator.where(r5);	 Catch:{ all -> 0x000b }
        r1 = r4.getClass();	 Catch:{ all -> 0x000b }
        r5 = r5.find(r1);	 Catch:{ all -> 0x000b }
        r1 = r5.isEmpty();	 Catch:{ all -> 0x000b }
        if (r1 == 0) goto L_0x0025;
    L_0x001f:
        r5 = r4.save();	 Catch:{ all -> 0x000b }
        monitor-exit(r0);	 Catch:{ all -> 0x000b }
        return r5;
    L_0x0025:
        r1 = com.tomatolive.library.utils.litepal.tablemanager.Connector.getDatabase();	 Catch:{ all -> 0x000b }
        r1.beginTransaction();	 Catch:{ all -> 0x000b }
        r5 = r5.iterator();	 Catch:{ Exception -> 0x0059 }
    L_0x0030:
        r2 = r5.hasNext();	 Catch:{ Exception -> 0x0059 }
        if (r2 == 0) goto L_0x004e;
    L_0x0036:
        r2 = r5.next();	 Catch:{ Exception -> 0x0059 }
        r2 = (com.tomatolive.library.utils.litepal.crud.LitePalSupport) r2;	 Catch:{ Exception -> 0x0059 }
        r2 = r2.getBaseObjId();	 Catch:{ Exception -> 0x0059 }
        r4.baseObjId = r2;	 Catch:{ Exception -> 0x0059 }
        r2 = new com.tomatolive.library.utils.litepal.crud.SaveHandler;	 Catch:{ Exception -> 0x0059 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0059 }
        r2.onSave(r4);	 Catch:{ Exception -> 0x0059 }
        r4.clearAssociatedData();	 Catch:{ Exception -> 0x0059 }
        goto L_0x0030;
    L_0x004e:
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x0059 }
        r5 = 1;
        r1.endTransaction();	 Catch:{ all -> 0x000b }
        monitor-exit(r0);	 Catch:{ all -> 0x000b }
        return r5;
    L_0x0057:
        r5 = move-exception;
        goto L_0x0063;
    L_0x0059:
        r5 = move-exception;
        r5.printStackTrace();	 Catch:{ all -> 0x0057 }
        r5 = 0;
        r1.endTransaction();	 Catch:{ all -> 0x000b }
        monitor-exit(r0);	 Catch:{ all -> 0x000b }
        return r5;
    L_0x0063:
        r1.endTransaction();	 Catch:{ all -> 0x000b }
        throw r5;	 Catch:{ all -> 0x000b }
    L_0x0067:
        monitor-exit(r0);	 Catch:{ all -> 0x000b }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.crud.LitePalSupport.saveOrUpdate(java.lang.String[]):boolean");
    }

    public SaveExecutor saveOrUpdateAsync(final String... strArr) {
        final SaveExecutor saveExecutor = new SaveExecutor();
        saveExecutor.submit(new Runnable() {
            public void run() {
                synchronized (LitePalSupport.class) {
                    final boolean saveOrUpdate = LitePalSupport.this.saveOrUpdate(strArr);
                    if (saveExecutor.getListener() != null) {
                        Operator.getHandler().post(new Runnable() {
                            public void run() {
                                saveExecutor.getListener().onFinish(saveOrUpdate);
                            }
                        });
                    }
                }
            }
        });
        return saveExecutor;
    }

    public boolean isSaved() {
        return this.baseObjId > 0;
    }

    public void clearSavedState() {
        this.baseObjId = 0;
    }

    public void setToDefault(String str) {
        getFieldsToSetToDefault().add(str);
    }

    public void assignBaseObjId(int i) {
        this.baseObjId = (long) i;
    }

    protected LitePalSupport() {
    }

    /* Access modifiers changed, original: protected */
    public long getBaseObjId() {
        return this.baseObjId;
    }

    /* Access modifiers changed, original: protected */
    public String getClassName() {
        return getClass().getName();
    }

    /* Access modifiers changed, original: protected */
    public String getTableName() {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(getClassName()));
    }

    /* Access modifiers changed, original: 0000 */
    public List<String> getFieldsToSetToDefault() {
        if (this.fieldsToSetToDefault == null) {
            this.fieldsToSetToDefault = new ArrayList();
        }
        return this.fieldsToSetToDefault;
    }

    /* Access modifiers changed, original: 0000 */
    public void addAssociatedModelWithFK(String str, long j) {
        Set set = (Set) getAssociatedModelsMapWithFK().get(str);
        if (set == null) {
            HashSet hashSet = new HashSet();
            hashSet.add(Long.valueOf(j));
            this.associatedModelsMapWithFK.put(str, hashSet);
            return;
        }
        set.add(Long.valueOf(j));
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, Set<Long>> getAssociatedModelsMapWithFK() {
        if (this.associatedModelsMapWithFK == null) {
            this.associatedModelsMapWithFK = new HashMap();
        }
        return this.associatedModelsMapWithFK;
    }

    /* Access modifiers changed, original: 0000 */
    public void addAssociatedModelForJoinTable(String str, long j) {
        List list = (List) getAssociatedModelsMapForJoinTable().get(str);
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(j));
            this.associatedModelsMapForJoinTable.put(str, arrayList);
            return;
        }
        list.add(Long.valueOf(j));
    }

    /* Access modifiers changed, original: 0000 */
    public void addEmptyModelForJoinTable(String str) {
        if (((List) getAssociatedModelsMapForJoinTable().get(str)) == null) {
            this.associatedModelsMapForJoinTable.put(str, new ArrayList());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, List<Long>> getAssociatedModelsMapForJoinTable() {
        if (this.associatedModelsMapForJoinTable == null) {
            this.associatedModelsMapForJoinTable = new HashMap();
        }
        return this.associatedModelsMapForJoinTable;
    }

    /* Access modifiers changed, original: 0000 */
    public void addAssociatedModelWithoutFK(String str, long j) {
        getAssociatedModelsMapWithoutFK().put(str, Long.valueOf(j));
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, Long> getAssociatedModelsMapWithoutFK() {
        if (this.associatedModelsMapWithoutFK == null) {
            this.associatedModelsMapWithoutFK = new HashMap();
        }
        return this.associatedModelsMapWithoutFK;
    }

    /* Access modifiers changed, original: 0000 */
    public void addFKNameToClearSelf(String str) {
        List listToClearSelfFK = getListToClearSelfFK();
        if (!listToClearSelfFK.contains(str)) {
            listToClearSelfFK.add(str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public List<String> getListToClearSelfFK() {
        if (this.listToClearSelfFK == null) {
            this.listToClearSelfFK = new ArrayList();
        }
        return this.listToClearSelfFK;
    }

    /* Access modifiers changed, original: 0000 */
    public void addAssociatedTableNameToClearFK(String str) {
        List listToClearAssociatedFK = getListToClearAssociatedFK();
        if (!listToClearAssociatedFK.contains(str)) {
            listToClearAssociatedFK.add(str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public List<String> getListToClearAssociatedFK() {
        if (this.listToClearAssociatedFK == null) {
            this.listToClearAssociatedFK = new ArrayList();
        }
        return this.listToClearAssociatedFK;
    }

    /* Access modifiers changed, original: 0000 */
    public void clearAssociatedData() {
        clearIdOfModelWithFK();
        clearIdOfModelWithoutFK();
        clearIdOfModelForJoinTable();
        clearFKNameList();
    }

    private void clearIdOfModelWithFK() {
        for (String str : getAssociatedModelsMapWithFK().keySet()) {
            ((Set) this.associatedModelsMapWithFK.get(str)).clear();
        }
        this.associatedModelsMapWithFK.clear();
    }

    private void clearIdOfModelWithoutFK() {
        getAssociatedModelsMapWithoutFK().clear();
    }

    private void clearIdOfModelForJoinTable() {
        for (String str : getAssociatedModelsMapForJoinTable().keySet()) {
            ((List) this.associatedModelsMapForJoinTable.get(str)).clear();
        }
        this.associatedModelsMapForJoinTable.clear();
    }

    private void clearFKNameList() {
        getListToClearSelfFK().clear();
        getListToClearAssociatedFK().clear();
    }
}
