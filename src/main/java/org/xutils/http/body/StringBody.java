package org.xutils.http.body;

import android.text.TextUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class StringBody implements RequestBody {
    private byte[] a;
    private String b;
    private String c = "UTF-8";

    public StringBody(String str, String str2) throws UnsupportedEncodingException {
        if (!TextUtils.isEmpty(str2)) {
            this.c = str2;
        }
        this.a = str.getBytes(this.c);
    }

    public long getContentLength() {
        return (long) this.a.length;
    }

    public void setContentType(String str) {
        this.b = str;
    }

    public String getContentType() {
        if (!TextUtils.isEmpty(this.b)) {
            return this.b;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("application/json;charset=");
        stringBuilder.append(this.c);
        return stringBuilder.toString();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.a);
        outputStream.flush();
    }
}
