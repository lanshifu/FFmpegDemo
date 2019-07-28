package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.InputStream;
import org.json.JSONArray;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

/* compiled from: JSONArrayLoader */
class d extends Loader<JSONArray> {
    private String a = "UTF-8";
    private String b = null;

    d() {
    }

    public Loader<JSONArray> newInstance() {
        return new d();
    }

    public void setParams(RequestParams requestParams) {
        if (requestParams != null) {
            String charset = requestParams.getCharset();
            if (!TextUtils.isEmpty(charset)) {
                this.a = charset;
            }
        }
    }

    public JSONArray load(InputStream inputStream) throws Throwable {
        this.b = IOUtil.readStr(inputStream, this.a);
        return new JSONArray(this.b);
    }

    public JSONArray load(UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return load(uriRequest.getInputStream());
    }

    public JSONArray loadFromCache(DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity != null) {
            String textContent = diskCacheEntity.getTextContent();
            if (!TextUtils.isEmpty(textContent)) {
                return new JSONArray(textContent);
            }
        }
        return null;
    }

    public void save2Cache(UriRequest uriRequest) {
        saveStringCache(uriRequest, this.b);
    }
}
