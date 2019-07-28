package defpackage;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/* compiled from: GZIPQueueFileEventStorage */
/* renamed from: vu */
public class vu extends vv {
    public vu(Context context, File file, String str, String str2) throws IOException {
        super(context, file, str, str2);
    }

    public OutputStream a(File file) throws IOException {
        return new GZIPOutputStream(new FileOutputStream(file));
    }
}
