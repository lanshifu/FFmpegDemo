package org.xutils.http.body;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

public class FileBody extends InputStreamBody {
    private File a;
    private String b;

    public FileBody(File file) throws IOException {
        this(file, null);
    }

    public FileBody(File file, String str) throws IOException {
        super(new FileInputStream(file));
        this.a = file;
        this.b = str;
    }

    public void setContentType(String str) {
        this.b = str;
    }

    public String getContentType() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = getFileContentType(this.a);
        }
        return this.b;
    }

    public static String getFileContentType(File file) {
        String guessContentTypeFromName = HttpURLConnection.guessContentTypeFromName(file.getName());
        if (TextUtils.isEmpty(guessContentTypeFromName)) {
            return "application/octet-stream";
        }
        return guessContentTypeFromName.replaceFirst("\\/jpg$", "/jpeg");
    }
}
