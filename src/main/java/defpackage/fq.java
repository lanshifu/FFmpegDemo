package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.danikula.videocache.j;
import com.danikula.videocache.n;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

/* compiled from: DatabaseSourceInfoStorage */
/* renamed from: fq */
class fq extends SQLiteOpenHelper implements fs {
    private static final String[] b = new String[]{"_id", OnNativeInvokeListener.ARG_URL, "length", "save_time", IMediaFormat.KEY_MIME};
    public Context a;

    fq(Context context) {
        super(context, "AndroidVideoCache.db", null, 2);
        j.a(context);
        this.a = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        j.a(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,url TEXT NOT NULL,mime TEXT,length INTEGER,save_time INTEGER);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new IllegalStateException("Should not be called. There is no any migration");
    }

    public Context a() {
        return this.a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003b  */
    public com.danikula.videocache.n a(java.lang.String r11) {
        /*
        r10 = this;
        com.danikula.videocache.j.a(r11);
        r0 = 0;
        r11 = defpackage.el.e(r11);	 Catch:{ all -> 0x0038 }
        r1 = r10.getReadableDatabase();	 Catch:{ all -> 0x0038 }
        r2 = "SourceInfo";
        r3 = b;	 Catch:{ all -> 0x0038 }
        r4 = "url=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ all -> 0x0038 }
        r6 = 0;
        r5[r6] = r11;	 Catch:{ all -> 0x0038 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r11 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0038 }
        if (r11 == 0) goto L_0x0032;
    L_0x0021:
        r1 = r11.moveToFirst();	 Catch:{ all -> 0x002d }
        if (r1 != 0) goto L_0x0028;
    L_0x0027:
        goto L_0x0032;
    L_0x0028:
        r0 = r10.a(r11);	 Catch:{ all -> 0x002d }
        goto L_0x0032;
    L_0x002d:
        r0 = move-exception;
        r9 = r0;
        r0 = r11;
        r11 = r9;
        goto L_0x0039;
    L_0x0032:
        if (r11 == 0) goto L_0x0037;
    L_0x0034:
        r11.close();
    L_0x0037:
        return r0;
    L_0x0038:
        r11 = move-exception;
    L_0x0039:
        if (r0 == 0) goto L_0x003e;
    L_0x003b:
        r0.close();
    L_0x003e:
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fq.a(java.lang.String):com.danikula.videocache.n");
    }

    public void a(String str, n nVar) {
        j.a(new Object[]{str, nVar});
        Object obj = a(el.e(str)) != null ? 1 : null;
        ContentValues a = a(nVar);
        if (obj != null) {
            getWritableDatabase().update("SourceInfo", a, "url=?", new String[]{str});
        } else {
            getWritableDatabase().insert("SourceInfo", null, a);
        }
    }

    private n a(Cursor cursor) {
        return new n(cursor.getString(cursor.getColumnIndexOrThrow(OnNativeInvokeListener.ARG_URL)), cursor.getLong(cursor.getColumnIndexOrThrow("length")), cursor.getLong(cursor.getColumnIndexOrThrow("save_time")), cursor.getString(cursor.getColumnIndexOrThrow(IMediaFormat.KEY_MIME)));
    }

    private ContentValues a(n nVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(OnNativeInvokeListener.ARG_URL, nVar.a);
        contentValues.put("length", Long.valueOf(nVar.b));
        contentValues.put("save_time", Long.valueOf(nVar.c));
        contentValues.put(IMediaFormat.KEY_MIME, nVar.d);
        return contentValues;
    }
}
