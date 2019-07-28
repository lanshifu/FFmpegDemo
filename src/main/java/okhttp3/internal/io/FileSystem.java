package okhttp3.internal.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import okio.l;
import okio.q;
import okio.r;

public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem() {
        public r source(File file) throws FileNotFoundException {
            return l.a(file);
        }

        public q sink(File file) throws FileNotFoundException {
            try {
                return l.b(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return l.b(file);
            }
        }

        public q appendingSink(File file) throws FileNotFoundException {
            try {
                return l.c(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return l.c(file);
            }
        }

        public void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("failed to delete ");
                stringBuilder.append(file);
                throw new IOException(stringBuilder.toString());
            }
        }

        public boolean exists(File file) {
            return file.exists();
        }

        public long size(File file) {
            return file.length();
        }

        public void rename(File file, File file2) throws IOException {
            delete(file2);
            if (!file.renameTo(file2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("failed to rename ");
                stringBuilder.append(file);
                stringBuilder.append(" to ");
                stringBuilder.append(file2);
                throw new IOException(stringBuilder.toString());
            }
        }

        public void deleteContents(File file) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                int length = listFiles.length;
                int i = 0;
                while (i < length) {
                    File file2 = listFiles[i];
                    if (file2.isDirectory()) {
                        deleteContents(file2);
                    }
                    if (file2.delete()) {
                        i++;
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("failed to delete ");
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
    };

    q appendingSink(File file) throws FileNotFoundException;

    void delete(File file) throws IOException;

    void deleteContents(File file) throws IOException;

    boolean exists(File file);

    void rename(File file, File file2) throws IOException;

    q sink(File file) throws FileNotFoundException;

    long size(File file);

    r source(File file) throws FileNotFoundException;
}
