package defpackage;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: Util */
/* renamed from: mf */
public final class mf {
    static final Charset a = Charset.forName("US-ASCII");
    static final Charset b = Charset.forName("UTF-8");

    static void a(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file2 = listFiles[i];
                if (file2.isDirectory()) {
                    mf.a(file2);
                }
                if (file2.delete()) {
                    i++;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("failed to delete file: ");
                    stringBuilder.append(file2);
                    throw new IOException(stringBuilder.toString());
                }
            }
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("not a readable directory: ");
        stringBuilder2.append(file);
        throw new IOException(stringBuilder2.toString());
    }

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }
}
