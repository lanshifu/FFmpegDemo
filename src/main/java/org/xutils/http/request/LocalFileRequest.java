package org.xutils.http.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.xutils.common.util.IOUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.loader.FileLoader;

public class LocalFileRequest extends UriRequest {
    private InputStream a;

    public void clearCacheHeader() {
    }

    public String getCacheKey() {
        return null;
    }

    public String getETag() {
        return null;
    }

    public long getExpiration() {
        return -1;
    }

    public long getHeaderFieldDate(String str, long j) {
        return j;
    }

    public String getResponseHeader(String str) {
        return null;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return null;
    }

    public String getResponseMessage() throws IOException {
        return null;
    }

    public boolean isLoading() {
        return true;
    }

    public Object loadResultFromCache() throws Throwable {
        return null;
    }

    public void save2Cache() {
    }

    public void sendRequest() throws Throwable {
    }

    LocalFileRequest(RequestParams requestParams, Type type) throws Throwable {
        super(requestParams, type);
    }

    public Object loadResult() throws Throwable {
        if (this.loader instanceof FileLoader) {
            return a();
        }
        return this.loader.load((UriRequest) this);
    }

    private File a() {
        String substring;
        if (this.queryUrl.startsWith("file:")) {
            substring = this.queryUrl.substring("file:".length());
        } else {
            substring = this.queryUrl;
        }
        return new File(substring);
    }

    public InputStream getInputStream() throws IOException {
        if (this.a == null) {
            this.a = new FileInputStream(a());
        }
        return this.a;
    }

    public void close() throws IOException {
        IOUtil.closeQuietly(this.a);
        this.a = null;
    }

    public long getContentLength() {
        return a().length();
    }

    public int getResponseCode() throws IOException {
        return a().exists() ? 200 : 404;
    }

    public long getLastModified() {
        return a().lastModified();
    }
}
