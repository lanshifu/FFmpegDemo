package org.xutils.http.loader;

import java.io.InputStream;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.request.UriRequest;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* compiled from: BooleanLoader */
class a extends Loader<Boolean> {
    public Boolean loadFromCache(DiskCacheEntity diskCacheEntity) throws Throwable {
        return null;
    }

    public void save2Cache(UriRequest uriRequest) {
    }

    a() {
    }

    public Loader<Boolean> newInstance() {
        return new a();
    }

    public Boolean load(InputStream inputStream) throws Throwable {
        return Boolean.valueOf(false);
    }

    public Boolean load(UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return Boolean.valueOf(uriRequest.getResponseCode() < IjkMediaCodecInfo.RANK_SECURE);
    }
}
