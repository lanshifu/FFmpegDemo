package com.tomatolive.library.utils.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.tomatolive.library.utils.litepal.LitePalApplication;
import com.tomatolive.library.utils.litepal.exceptions.DatabaseGenerateException;
import com.tomatolive.library.utils.litepal.parser.LitePalAttr;
import com.tomatolive.library.utils.litepal.util.BaseUtility;
import java.io.File;

public class Connector {
    private static LitePalOpenHelper mLitePalHelper;

    public static synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (Connector.class) {
            writableDatabase = buildConnection().getWritableDatabase();
        }
        return writableDatabase;
    }

    public static SQLiteDatabase getDatabase() {
        return getWritableDatabase();
    }

    private static LitePalOpenHelper buildConnection() {
        LitePalAttr instance = LitePalAttr.getInstance();
        instance.checkSelfValid();
        if (mLitePalHelper == null) {
            String dbName = instance.getDbName();
            StringBuilder stringBuilder;
            if ("external".equalsIgnoreCase(instance.getStorage())) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(LitePalApplication.getContext().getExternalFilesDir(""));
                stringBuilder.append("/databases/");
                stringBuilder.append(dbName);
                dbName = stringBuilder.toString();
            } else if (!("internal".equalsIgnoreCase(instance.getStorage()) || TextUtils.isEmpty(instance.getStorage()))) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(Environment.getExternalStorageDirectory().getPath());
                stringBuilder.append("/");
                stringBuilder.append(instance.getStorage());
                String replace = stringBuilder.toString().replace("//", "/");
                if (!BaseUtility.isClassAndMethodExist("android.support.v4.content.ContextCompat", "checkSelfPermission") || ContextCompat.checkSelfPermission(LitePalApplication.getContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                    File file = new File(replace);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(replace);
                    stringBuilder2.append("/");
                    stringBuilder2.append(dbName);
                    dbName = stringBuilder2.toString();
                } else {
                    throw new DatabaseGenerateException(String.format("You don't have permission to access database at %1$s. Make sure you handled WRITE_EXTERNAL_STORAGE runtime permission correctly.", new Object[]{replace}));
                }
            }
            mLitePalHelper = new LitePalOpenHelper(dbName, instance.getVersion());
        }
        return mLitePalHelper;
    }

    public static void clearLitePalOpenHelperInstance() {
        if (mLitePalHelper != null) {
            mLitePalHelper.getWritableDatabase().close();
            mLitePalHelper = null;
        }
    }
}
