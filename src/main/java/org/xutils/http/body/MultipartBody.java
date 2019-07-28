package org.xutils.http.body;

import android.text.TextUtils;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.http.ProgressHandler;

public class MultipartBody implements ProgressBody {
    private static byte[] a = "--------7da3d81520810".getBytes();
    private static byte[] b = "\r\n".getBytes();
    private static byte[] c = "--".getBytes();
    private byte[] d;
    private String e;
    private String f = "UTF-8";
    private List<KeyValue> g;
    private long h = 0;
    private long i = 0;
    private ProgressHandler j;

    private class a extends OutputStream {
        final AtomicLong a = new AtomicLong(0);

        public void a(File file) {
            if (this.a.get() != -1) {
                this.a.addAndGet(file.length());
            }
        }

        public void a(InputStream inputStream) {
            if (this.a.get() != -1) {
                long inputStreamLength = InputStreamBody.getInputStreamLength(inputStream);
                if (inputStreamLength > 0) {
                    this.a.addAndGet(inputStreamLength);
                } else {
                    this.a.set(-1);
                }
            }
        }

        public void write(int i) throws IOException {
            if (this.a.get() != -1) {
                this.a.incrementAndGet();
            }
        }

        public void write(byte[] bArr) throws IOException {
            if (this.a.get() != -1) {
                this.a.addAndGet((long) bArr.length);
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.a.get() != -1) {
                this.a.addAndGet((long) i2);
            }
        }
    }

    public MultipartBody(List<KeyValue> list, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f = str;
        }
        this.g = list;
        a();
        a aVar = new a();
        try {
            writeTo(aVar);
            this.h = aVar.a.get();
        } catch (IOException unused) {
            this.h = -1;
        }
    }

    public void setProgressHandler(ProgressHandler progressHandler) {
        this.j = progressHandler;
    }

    private void a() {
        String toHexString = Double.toHexString(Math.random() * 65535.0d);
        this.d = toHexString.getBytes();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("multipart/form-data; boundary=");
        stringBuilder.append(new String(a));
        stringBuilder.append(toHexString);
        this.e = stringBuilder.toString();
    }

    public long getContentLength() {
        return this.h;
    }

    public void setContentType(String str) {
        int indexOf = this.e.indexOf(";");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("multipart/");
        stringBuilder.append(str);
        stringBuilder.append(this.e.substring(indexOf));
        this.e = stringBuilder.toString();
    }

    public String getContentType() {
        return this.e;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (this.j == null || this.j.updateProgress(this.h, this.i, true)) {
            for (KeyValue keyValue : this.g) {
                String str = keyValue.key;
                Object obj = keyValue.value;
                if (!(TextUtils.isEmpty(str) || obj == null)) {
                    a(outputStream, str, obj);
                }
            }
            a(outputStream, c, a, this.d, c);
            outputStream.flush();
            if (this.j != null) {
                this.j.updateProgress(this.h, this.h, true);
                return;
            }
            return;
        }
        throw new CancelledException("upload stopped!");
    }

    private void a(OutputStream outputStream, String str, Object obj) throws IOException {
        String contentType;
        a(outputStream, c, a, this.d);
        String str2 = "";
        if (obj instanceof BodyItemWrapper) {
            BodyItemWrapper bodyItemWrapper = (BodyItemWrapper) obj;
            Object value = bodyItemWrapper.getValue();
            String fileName = bodyItemWrapper.getFileName();
            contentType = bodyItemWrapper.getContentType();
            obj = value;
            str2 = fileName;
        } else {
            contentType = null;
        }
        if (obj instanceof File) {
            File file = (File) obj;
            if (TextUtils.isEmpty(str2)) {
                str2 = file.getName();
            }
            if (TextUtils.isEmpty(contentType)) {
                contentType = FileBody.getFileContentType(file);
            }
            a(outputStream, a(str, str2, this.f));
            a(outputStream, a(obj, contentType, this.f));
            a(outputStream, new byte[0][]);
            a(outputStream, file);
            a(outputStream, new byte[0][]);
            return;
        }
        a(outputStream, a(str, str2, this.f));
        a(outputStream, a(obj, contentType, this.f));
        a(outputStream, new byte[0][]);
        if (obj instanceof InputStream) {
            a(outputStream, (InputStream) obj);
            a(outputStream, new byte[0][]);
            return;
        }
        byte[] bArr;
        if (obj instanceof byte[]) {
            bArr = (byte[]) obj;
        } else {
            bArr = String.valueOf(obj).getBytes(this.f);
        }
        a(outputStream, bArr);
        this.i += (long) bArr.length;
        if (this.j != null && !this.j.updateProgress(this.h, this.i, false)) {
            throw new CancelledException("upload stopped!");
        }
    }

    private void a(OutputStream outputStream, byte[]... bArr) throws IOException {
        if (bArr != null) {
            for (byte[] write : bArr) {
                outputStream.write(write);
            }
        }
        outputStream.write(b);
    }

    private void a(OutputStream outputStream, File file) throws IOException {
        if (outputStream instanceof a) {
            ((a) outputStream).a(file);
        } else {
            a(outputStream, new FileInputStream(file));
        }
    }

    private void a(OutputStream outputStream, InputStream inputStream) throws IOException {
        if (outputStream instanceof a) {
            ((a) outputStream).a(inputStream);
            return;
        }
        try {
            byte[] bArr = new byte[Filter.K];
            while (true) {
                int read = inputStream.read(bArr);
                if (read < 0) {
                    break;
                }
                outputStream.write(bArr, 0, read);
                this.i += (long) read;
                if (this.j != null) {
                    if (!this.j.updateProgress(this.h, this.i, false)) {
                        throw new CancelledException("upload stopped!");
                    }
                }
            }
        } finally {
            IOUtil.closeQuietly((Closeable) inputStream);
        }
    }

    private static byte[] a(String str, String str2, String str3) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder("Content-Disposition: form-data");
        stringBuilder.append("; name=\"");
        stringBuilder.append(str.replace("\"", "\\\""));
        stringBuilder.append("\"");
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append("; filename=\"");
            stringBuilder.append(str2.replace("\"", "\\\""));
            stringBuilder.append("\"");
        }
        return stringBuilder.toString().getBytes(str3);
    }

    private static byte[] a(Object obj, String str, String str2) throws UnsupportedEncodingException {
        String replaceFirst;
        StringBuilder stringBuilder = new StringBuilder("Content-Type: ");
        if (!TextUtils.isEmpty(str)) {
            replaceFirst = str.replaceFirst("\\/jpg$", "/jpeg");
        } else if (obj instanceof String) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("text/plain; charset=");
            stringBuilder2.append(str2);
            replaceFirst = stringBuilder2.toString();
        } else {
            replaceFirst = "application/octet-stream";
        }
        stringBuilder.append(replaceFirst);
        return stringBuilder.toString().getBytes(str2);
    }
}
