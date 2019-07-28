package org.xutils.http.body;

import android.net.Uri;
import android.text.TextUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.xutils.common.util.KeyValue;

public class UrlEncodedParamsBody implements RequestBody {
    private byte[] a;
    private String b = "UTF-8";

    public void setContentType(String str) {
    }

    public UrlEncodedParamsBody(List<KeyValue> list, String str) throws IOException {
        if (!TextUtils.isEmpty(str)) {
            this.b = str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null) {
            for (KeyValue keyValue : list) {
                String str2 = keyValue.key;
                String valueStr = keyValue.getValueStr();
                if (!(TextUtils.isEmpty(str2) || valueStr == null)) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(Uri.encode(str2, this.b));
                    stringBuilder.append("=");
                    stringBuilder.append(Uri.encode(valueStr, this.b));
                }
            }
        }
        this.a = stringBuilder.toString().getBytes(this.b);
    }

    public long getContentLength() {
        return (long) this.a.length;
    }

    public String getContentType() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("application/x-www-form-urlencoded;charset=");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.a);
        outputStream.flush();
    }
}
