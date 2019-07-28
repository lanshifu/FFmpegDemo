package org.xutils;

import android.app.Application;
import android.content.Context;
import org.xutils.DbManager.DaoConfig;
import org.xutils.common.TaskController;
import org.xutils.db.DbManagerImpl;
import org.xutils.http.HttpManagerImpl;
import org.xutils.image.ImageManagerImpl;
import org.xutils.view.ViewInjectorImpl;

public final class x {

    private static class a extends Application {
        public a(Context context) {
            attachBaseContext(context);
        }
    }

    private x() {
    }

    public static boolean isDebug() {
        return Ext.a();
    }

    public static Application app() {
        if (Ext.b() == null) {
            try {
                Ext.a(new a((Context) Class.forName("com.android.layoutlib.bridge.impl.RenderAction").getDeclaredMethod("getCurrentContext", new Class[0]).invoke(null, new Object[0])));
            } catch (Throwable unused) {
                RuntimeException runtimeException = new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate() and register your Application in manifest.");
            }
        }
        return Ext.b();
    }

    public static TaskController task() {
        return Ext.c();
    }

    public static HttpManager http() {
        if (Ext.d() == null) {
            HttpManagerImpl.registerInstance();
        }
        return Ext.d();
    }

    public static ImageManager image() {
        if (Ext.e() == null) {
            ImageManagerImpl.registerInstance();
        }
        return Ext.e();
    }

    public static ViewInjector view() {
        if (Ext.f() == null) {
            ViewInjectorImpl.registerInstance();
        }
        return Ext.f();
    }

    public static DbManager getDb(DaoConfig daoConfig) {
        return DbManagerImpl.getInstance(daoConfig);
    }
}
