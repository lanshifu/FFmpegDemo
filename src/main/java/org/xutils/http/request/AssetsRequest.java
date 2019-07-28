package org.xutils.http.request;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class AssetsRequest extends UriRequest {
    private long a = 0;
    private InputStream b;

    public void clearCacheHeader() {
    }

    public String getETag() {
        return null;
    }

    public long getExpiration() {
        return Long.MAX_VALUE;
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

    public void sendRequest() throws Throwable {
    }

    public AssetsRequest(RequestParams requestParams, Type type) throws Throwable {
        super(requestParams, type);
    }

    public String getCacheKey() {
        return this.queryUrl;
    }

    public Object loadResult() throws Throwable {
        return this.loader.load((UriRequest) this);
    }

    public Object loadResultFromCache() throws Throwable {
        DiskCacheEntity diskCacheEntity = LruDiskCache.getDiskCache(this.params.getCacheDirName()).setMaxSize(this.params.getCacheSize()).get(getCacheKey());
        if (diskCacheEntity == null) {
            return null;
        }
        Date lastModify = diskCacheEntity.getLastModify();
        if (lastModify == null || lastModify.getTime() < getAssetsLastModified()) {
            return null;
        }
        return this.loader.loadFromCache(diskCacheEntity);
    }

    public InputStream getInputStream() throws IOException {
        if (this.b == null && this.callingClassLoader != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("assets/");
            stringBuilder.append(this.queryUrl.substring("assets://".length()));
            this.b = this.callingClassLoader.getResourceAsStream(stringBuilder.toString());
            this.a = (long) this.b.available();
        }
        return this.b;
    }

    public void close() throws IOException {
        IOUtil.closeQuietly(this.b);
        this.b = null;
    }

    public long getContentLength() {
        try {
            getInputStream();
            return this.a;
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            return 0;
        }
    }

    public int getResponseCode() throws IOException {
        return getInputStream() != null ? 200 : 404;
    }

    public long getLastModified() {
        return getAssetsLastModified();
    }

    /* Access modifiers changed, original: protected */
    public long getAssetsLastModified() {
        return new File(x.app().getApplicationInfo().sourceDir).lastModified();
    }
}
