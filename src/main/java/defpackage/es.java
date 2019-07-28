package defpackage;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: FileHelper */
/* renamed from: es */
public final class es {

    /* compiled from: FileHelper */
    /* renamed from: es$a */
    private final class a implements Comparator<File> {
        private int a(long j, long j2) {
            return j > j2 ? -1 : j == j2 ? 0 : 1;
        }

        private a() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            return a(file.lastModified(), file2.lastModified());
        }
    }

    public static boolean a(String str) {
        File file = new File(str);
        return (file.isFile() && file.exists()) ? file.delete() : false;
    }

    public static boolean b(String str) {
        if (!str.endsWith(File.separator)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(File.separator);
            str = stringBuilder.toString();
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        boolean z = true;
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
                z = es.a(listFiles[i].getAbsolutePath());
                if (!z) {
                    break;
                }
            } else {
                z = es.b(listFiles[i].getAbsolutePath());
                if (!z) {
                    break;
                }
            }
        }
        if (z) {
            return file.delete();
        }
        return false;
    }

    public List<File> a(File[] fileArr) {
        List<File> linkedList = new LinkedList();
        if (fileArr == null) {
            return linkedList;
        }
        List asList = Arrays.asList(fileArr);
        Collections.sort(asList, new a());
        return asList;
    }
}
