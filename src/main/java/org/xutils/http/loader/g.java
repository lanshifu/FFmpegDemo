package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.InputStream;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

/* compiled from: StringLoader */
class g extends Loader<String> {
    private String a = "UTF-8";
    private String b = null;

    g() {
    }

    public Loader<String> newInstance() {
        return new g();
    }

    public void setParams(RequestParams requestParams) {
        if (requestParams != null) {
            String charset = requestParams.getCharset();
            if (!TextUtils.isEmpty(charset)) {
                this.a = charset;
            }
        }
    }

    public String load(InputStream inputStream) throws Throwable {
        this.b = IOUtil.readStr(inputStream, this.a);
        return this.b;
    }

    public String load(UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return load(uriRequest.getInputStream());
    }

    public String loadFromCache(DiskCacheEntity diskCacheEntity) throws Throwable {
        return diskCacheEntity != null ? diskCacheEntity.getTextContent() : null;
    }

    public void save2Cache(UriRequest uriRequest) {
        saveStringCache(uriRequest, this.b);
    }
}
