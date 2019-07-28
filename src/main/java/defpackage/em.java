package defpackage;

import java.io.File;
import java.io.IOException;
import org.slf4j.b;
import org.slf4j.c;

/* compiled from: Files */
/* renamed from: em */
class em {
    private static final b a = c.a("Files");

    static void a(File file) throws IOException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("File ");
                stringBuilder.append(file);
                stringBuilder.append(" is not directory!");
                throw new IOException(stringBuilder.toString());
            }
        } else if (!file.mkdirs()) {
            throw new IOException(String.format("Directory %s can't be created", new Object[]{file.getAbsolutePath()}));
        }
    }
}
