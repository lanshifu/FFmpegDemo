package org.xutils.http.body;

import android.text.TextUtils;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.util.IOUtil;
import org.xutils.http.ProgressHandler;

public class InputStreamBody implements ProgressBody {
    private InputStream a;
    private String b;
    private final long c;
    private long d;
    private ProgressHandler e;

    public InputStreamBody(InputStream inputStream) {
        this(inputStream, null);
    }

    public InputStreamBody(InputStream inputStream, String str) {
        this.d = 0;
        this.a = inputStream;
        this.b = str;
        this.c = getInputStreamLength(inputStream);
    }

    public void setProgressHandler(ProgressHandler progressHandler) {
        this.e = progressHandler;
    }

    public long getContentLength() {
        return this.c;
    }

    public void setContentType(String str) {
        this.b = str;
    }

    public String getContentType() {
        return TextUtils.isEmpty(this.b) ? "application/octet-stream" : this.b;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (this.e == null || this.e.updateProgress(this.c, this.d, true)) {
            byte[] bArr = new byte[Filter.K];
            while (true) {
                try {
                    int read = this.a.read(bArr);
                    if (read != -1) {
                        outputStream.write(bArr, 0, read);
                        this.d += (long) read;
                        if (this.e != null) {
                            if (!this.e.updateProgress(this.c, this.d, false)) {
                                throw new CancelledException("upload stopped!");
                            }
                        }
                    } else {
                        outputStream.flush();
                        if (this.e != null) {
                            this.e.updateProgress(this.c, this.c, true);
                        }
                        IOUtil.closeQuietly(this.a);
                        return;
                    }
                } catch (Throwable th) {
                    IOUtil.closeQuietly(this.a);
                }
            }
        } else {
            throw new CancelledException("upload stopped!");
        }
    }

    public static long getInputStreamLength(InputStream inputStream) {
        try {
            if ((inputStream instanceof FileInputStream) || (inputStream instanceof ByteArrayInputStream)) {
                return (long) inputStream.available();
            }
        } catch (Throwable unused) {
        }
        return -1;
    }
}
