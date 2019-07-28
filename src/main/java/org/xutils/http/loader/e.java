package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.InputStream;
import org.json.JSONObject;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

/* compiled from: JSONObjectLoader */
class e extends Loader<JSONObject> {
    private String a = "UTF-8";
    private String b = null;

    e() {
    }

    public Loader<JSONObject> newInstance() {
        return new e();
    }

    public void setParams(RequestParams requestParams) {
        if (requestParams != null) {
            String charset = requestParams.getCharset();
            if (!TextUtils.isEmpty(charset)) {
                this.a = charset;
            }
        }
    }

    public JSONObject load(InputStream inputStream) throws Throwable {
        this.b = IOUtil.readStr(inputStream, this.a);
        return new JSONObject(this.b);
    }

    public JSONObject load(UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return load(uriRequest.getInputStream());
    }

    public JSONObject loadFromCache(DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity != null) {
            String textContent = diskCacheEntity.getTextContent();
            if (!TextUtils.isEmpty(textContent)) {
                return new JSONObject(textContent);
            }
        }
        return null;
    }

    public void save2Cache(UriRequest uriRequest) {
        saveStringCache(uriRequest, this.b);
    }
}
