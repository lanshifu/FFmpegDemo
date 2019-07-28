package org.xutils.http.loader;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

public final class LoaderFactory {
    private static final HashMap<Type, Loader> a = new HashMap();

    private LoaderFactory() {
    }

    static {
        a.put(JSONObject.class, new e());
        a.put(JSONArray.class, new d());
        a.put(String.class, new g());
        a.put(File.class, new FileLoader());
        a.put(byte[].class, new b());
        a aVar = new a();
        a.put(Boolean.TYPE, aVar);
        a.put(Boolean.class, aVar);
        c cVar = new c();
        a.put(Integer.TYPE, cVar);
        a.put(Integer.class, cVar);
    }

    public static Loader<?> getLoader(Type type, RequestParams requestParams) {
        Loader loader = (Loader) a.get(type);
        if (loader == null) {
            loader = new f(type);
        } else {
            loader = loader.newInstance();
        }
        loader.setParams(requestParams);
        return loader;
    }

    public static <T> void registerLoader(Type type, Loader<T> loader) {
        a.put(type, loader);
    }
}
