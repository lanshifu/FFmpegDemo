package com.youdao.sdk.app.other;

import android.database.Cursor;
import android.database.DatabaseUtils.InsertHelper;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;

class d {
    private final AtomicInteger a = new AtomicInteger();
    private final String[] b = new String[]{"raw"};
    private SQLiteDatabase c;
    private volatile long d = 0;
    private InsertHelper e;
    private int f;
    private int g;

    d(SQLiteDatabase sQLiteDatabase) {
        this.c = sQLiteDatabase;
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS statistics (time INT8, raw TEXT);");
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT COUNT(*) FROM statistics", null);
        try {
            if (rawQuery.moveToFirst()) {
                this.a.set(rawQuery.getInt(0));
            }
            rawQuery.close();
            rawQuery = sQLiteDatabase.rawQuery("SELECT MAX(_ROWID_) FROM statistics", null);
            if (rawQuery.moveToFirst()) {
                this.d = rawQuery.getLong(0);
            }
            rawQuery.close();
            this.e = new InsertHelper(sQLiteDatabase, "statistics");
            this.f = this.e.getColumnIndex("time");
            this.g = this.e.getColumnIndex("raw");
        } catch (Exception e) {
            Log.e("StatisticsDB", e.getLocalizedMessage(), e);
        } catch (Throwable th) {
            rawQuery.close();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int a() {
        return this.a.get();
    }

    /* Access modifiers changed, original: 0000 */
    public long b() {
        return this.d;
    }

    /* JADX WARNING: Missing block: B:20:0x0073, code skipped:
            return r6;
     */
    private long a(java.lang.String r6, long r7) {
        /*
        r5 = this;
        r0 = r5.c;
        r1 = -1;
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return r1;
    L_0x0007:
        r0 = r5.e;
        monitor-enter(r0);
        r3 = r5.e;	 Catch:{ all -> 0x0079 }
        r3.prepareForInsert();	 Catch:{ all -> 0x0079 }
        r3 = r5.e;	 Catch:{ all -> 0x0079 }
        r4 = r5.f;	 Catch:{ all -> 0x0079 }
        r3.bind(r4, r7);	 Catch:{ all -> 0x0079 }
        r7 = r5.e;	 Catch:{ all -> 0x0079 }
        r8 = r5.g;	 Catch:{ all -> 0x0079 }
        r7.bind(r8, r6);	 Catch:{ all -> 0x0079 }
        r6 = r5.e;	 Catch:{ all -> 0x0079 }
        r6 = r6.execute();	 Catch:{ all -> 0x0079 }
        r3 = 0;
        r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1));
        if (r8 >= 0) goto L_0x002b;
    L_0x0029:
        monitor-exit(r0);	 Catch:{ all -> 0x0079 }
        return r1;
    L_0x002b:
        r8 = r5.a;	 Catch:{ all -> 0x0079 }
        r8.incrementAndGet();	 Catch:{ all -> 0x0079 }
        r1 = r5.d;	 Catch:{ all -> 0x0079 }
        r8 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1));
        if (r8 <= 0) goto L_0x0039;
    L_0x0036:
        r5.d = r6;	 Catch:{ all -> 0x0079 }
        goto L_0x0072;
    L_0x0039:
        r8 = "StatisticsDB";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0079 }
        r1.<init>();	 Catch:{ all -> 0x0079 }
        r2 = "_ROWID_ NOT INCREASE: ";
        r1.append(r2);	 Catch:{ all -> 0x0079 }
        r1.append(r6);	 Catch:{ all -> 0x0079 }
        r2 = ", ";
        r1.append(r2);	 Catch:{ all -> 0x0079 }
        r2 = r5.d;	 Catch:{ all -> 0x0079 }
        r1.append(r2);	 Catch:{ all -> 0x0079 }
        r1 = r1.toString();	 Catch:{ all -> 0x0079 }
        android.util.Log.e(r8, r1);	 Catch:{ all -> 0x0079 }
        r8 = r5.c;	 Catch:{ all -> 0x0079 }
        r1 = "SELECT MAX(_ROWID_) FROM statistics";
        r2 = 0;
        r8 = r8.rawQuery(r1, r2);	 Catch:{ all -> 0x0079 }
        r1 = r8.moveToFirst();	 Catch:{ all -> 0x0074 }
        if (r1 == 0) goto L_0x006f;
    L_0x0068:
        r1 = 0;
        r1 = r8.getLong(r1);	 Catch:{ all -> 0x0074 }
        r5.d = r1;	 Catch:{ all -> 0x0074 }
    L_0x006f:
        r8.close();	 Catch:{ all -> 0x0079 }
    L_0x0072:
        monitor-exit(r0);	 Catch:{ all -> 0x0079 }
        return r6;
    L_0x0074:
        r6 = move-exception;
        r8.close();	 Catch:{ all -> 0x0079 }
        throw r6;	 Catch:{ all -> 0x0079 }
    L_0x0079:
        r6 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0079 }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youdao.sdk.app.other.d.a(java.lang.String, long):long");
    }

    /* Access modifiers changed, original: 0000 */
    public long a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return a(str, System.currentTimeMillis());
    }

    /* Access modifiers changed, original: 0000 */
    public Cursor a(long j) {
        if (this.c == null) {
            return null;
        }
        String[] strArr = this.b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("_ROWID_<=");
        stringBuilder.append(j);
        return this.c.query("statistics", strArr, stringBuilder.toString(), null, null, null, null);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b(long j) {
        boolean z = false;
        if (this.c == null) {
            return false;
        }
        int delete;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("_ROWID_<=");
            stringBuilder.append(j);
            delete = this.c.delete("statistics", stringBuilder.toString(), null);
        } catch (Exception e) {
            Log.e("StatisticsDB", e.getLocalizedMessage(), e);
            delete = 0;
        }
        this.a.addAndGet(-delete);
        if (delete > 0) {
            z = true;
        }
        return z;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean c() {
        boolean z = false;
        if (this.c == null) {
            return false;
        }
        int delete;
        try {
            delete = this.c.delete("statistics", null, null);
        } catch (Exception e) {
            Log.e("StatisticsDB", e.getLocalizedMessage(), e);
            delete = 0;
        }
        this.a.set(0);
        if (delete > 0) {
            z = true;
        }
        return z;
    }
}
