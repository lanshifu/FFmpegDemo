package defpackage;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Files;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import com.luck.picture.lib.R;
import com.luck.picture.lib.config.a;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* compiled from: LocalMediaLoader */
/* renamed from: mt */
public class mt {
    private static final Uri a = Files.getContentUri("external");
    private static final String[] g = new String[]{"_id", "_data", "mime_type", "width", "height", "duration"};
    private static final String[] h = new String[]{String.valueOf(1), String.valueOf(3)};
    private int b = 1;
    private FragmentActivity c;
    private boolean d;
    private long e = 0;
    private long f = 0;

    /* compiled from: LocalMediaLoader */
    /* renamed from: mt$2 */
    class 2 implements Comparator<LocalMediaFolder> {
        2() {
        }

        /* renamed from: a */
        public int compare(LocalMediaFolder localMediaFolder, LocalMediaFolder localMediaFolder2) {
            int i = 0;
            if (localMediaFolder.e() == null || localMediaFolder2.e() == null) {
                return 0;
            }
            int d = localMediaFolder.d();
            int d2 = localMediaFolder2.d();
            if (d != d2) {
                i = d < d2 ? 1 : -1;
            }
            return i;
        }
    }

    /* compiled from: LocalMediaLoader */
    /* renamed from: mt$a */
    public interface a {
        void a(List<LocalMediaFolder> list);
    }

    private static String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("media_type=? AND _size>0 AND ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private static String b(String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(media_type=?");
        stringBuilder.append(z ? "" : " AND mime_type!='image/gif'");
        stringBuilder.append(" OR ");
        stringBuilder.append("media_type=? AND ");
        stringBuilder.append(str);
        stringBuilder.append(") AND ");
        stringBuilder.append("_size");
        stringBuilder.append(">0");
        return stringBuilder.toString();
    }

    private static String[] b(int i) {
        return new String[]{String.valueOf(i)};
    }

    public mt(FragmentActivity fragmentActivity, int i, boolean z, long j, long j2) {
        this.c = fragmentActivity;
        this.b = i;
        this.d = z;
        this.e = j;
        this.f = j2;
    }

    public void a(final a aVar) {
        this.c.getSupportLoaderManager().initLoader(this.b, null, new LoaderCallbacks<Cursor>() {
            public void onLoaderReset(Loader<Cursor> loader) {
            }

            public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
                switch (i) {
                    case 0:
                        return new CursorLoader(mt.this.c, mt.a, mt.g, mt.b(mt.this.a(0, 0), mt.this.d), mt.h, "_id DESC");
                    case 1:
                        return new CursorLoader(mt.this.c, mt.a, mt.g, mt.this.d ? "media_type=? AND _size>0" : "media_type=? AND _size>0 AND mime_type!='image/gif'", mt.b(1), "_id DESC");
                    case 2:
                        return new CursorLoader(mt.this.c, mt.a, mt.g, mt.b(mt.this.a(0, 0)), mt.b(3), "_id DESC");
                    case 3:
                        return new CursorLoader(mt.this.c, mt.a, mt.g, mt.b(mt.this.a(0, 500)), mt.b(2), "_id DESC");
                    default:
                        return null;
                }
            }

            /* renamed from: a */
            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                try {
                    List arrayList = new ArrayList();
                    LocalMediaFolder localMediaFolder = new LocalMediaFolder();
                    ArrayList arrayList2 = new ArrayList();
                    if (cursor == null) {
                        return;
                    }
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        do {
                            String string = cursor.getString(cursor.getColumnIndexOrThrow(mt.g[1]));
                            String str = string;
                            LocalMedia localMedia = new LocalMedia(str, (long) cursor.getInt(cursor.getColumnIndexOrThrow(mt.g[5])), mt.this.b, cursor.getString(cursor.getColumnIndexOrThrow(mt.g[2])), cursor.getInt(cursor.getColumnIndexOrThrow(mt.g[3])), cursor.getInt(cursor.getColumnIndexOrThrow(mt.g[4])));
                            LocalMediaFolder a = mt.this.a(string, arrayList);
                            a.e().add(localMedia);
                            a.a(a.d() + 1);
                            arrayList2.add(localMedia);
                            localMediaFolder.a(localMediaFolder.d() + 1);
                        } while (cursor.moveToNext());
                        if (arrayList2.size() > 0) {
                            String string2;
                            mt.this.a(arrayList);
                            arrayList.add(0, localMediaFolder);
                            localMediaFolder.c(((LocalMedia) arrayList2.get(0)).b());
                            if (mt.this.b == a.d()) {
                                string2 = mt.this.c.getString(R.string.picture_all_audio);
                            } else {
                                string2 = mt.this.c.getString(R.string.picture_camera_roll);
                            }
                            localMediaFolder.a(string2);
                            localMediaFolder.a(arrayList2);
                        }
                        aVar.a(arrayList);
                        return;
                    }
                    aVar.a(arrayList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void a(List<LocalMediaFolder> list) {
        Collections.sort(list, new 2());
    }

    private LocalMediaFolder a(String str, List<LocalMediaFolder> list) {
        File parentFile = new File(str).getParentFile();
        for (LocalMediaFolder localMediaFolder : list) {
            if (localMediaFolder.b().equals(parentFile.getName())) {
                return localMediaFolder;
            }
        }
        LocalMediaFolder localMediaFolder2 = new LocalMediaFolder();
        localMediaFolder2.a(parentFile.getName());
        localMediaFolder2.b(parentFile.getAbsolutePath());
        localMediaFolder2.c(str);
        list.add(localMediaFolder2);
        return localMediaFolder2;
    }

    private String a(long j, long j2) {
        long j3 = this.e == 0 ? Long.MAX_VALUE : this.e;
        if (j != 0) {
            j3 = Math.min(j3, j);
        }
        Locale locale = Locale.CHINA;
        String str = "%d <%s duration and duration <= %d";
        Object[] objArr = new Object[3];
        objArr[0] = Long.valueOf(Math.max(j2, this.f));
        objArr[1] = Math.max(j2, this.f) == 0 ? "" : "=";
        objArr[2] = Long.valueOf(j3);
        return String.format(locale, str, objArr);
    }
}
