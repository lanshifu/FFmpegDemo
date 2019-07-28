package defpackage;

import android.content.Context;
import io.fabric.sdk.android.c;
import io.fabric.sdk.android.h;
import java.io.File;

/* compiled from: FileStoreImpl */
/* renamed from: vy */
public class vy implements vx {
    private final Context a;
    private final String b;
    private final String c;

    public vy(h hVar) {
        if (hVar.r() != null) {
            this.a = hVar.r();
            this.b = hVar.t();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Android/");
            stringBuilder.append(this.a.getPackageName());
            this.c = stringBuilder.toString();
            return;
        }
        throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
    }

    public File a() {
        return a(this.a.getFilesDir());
    }

    /* Access modifiers changed, original: 0000 */
    public File a(File file) {
        if (file == null) {
            c.g().a("Fabric", "Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            c.g().d("Fabric", "Couldn't create file");
        }
        return null;
    }
}
