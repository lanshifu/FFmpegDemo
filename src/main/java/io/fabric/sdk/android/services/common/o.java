package io.fabric.sdk.android.services.common;

import android.content.Context;
import io.fabric.sdk.android.c;
import java.lang.reflect.Method;

/* compiled from: FirebaseAppImpl */
final class o implements n {
    private final Method a;
    private final Object b;

    public static n a(Context context) {
        try {
            Class loadClass = context.getClassLoader().loadClass("com.google.firebase.FirebaseApp");
            return new o(loadClass, loadClass.getDeclaredMethod("getInstance", new Class[0]).invoke(loadClass, new Object[0]));
        } catch (ClassNotFoundException unused) {
            c.g().a("Fabric", "Could not find class: com.google.firebase.FirebaseApp");
            return null;
        } catch (NoSuchMethodException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not find method: ");
            stringBuilder.append(e.getMessage());
            c.g().a("Fabric", stringBuilder.toString());
            return null;
        } catch (Exception e2) {
            c.g().a("Fabric", "Unexpected error loading FirebaseApp instance.", e2);
            return null;
        }
    }

    private o(Class cls, Object obj) throws NoSuchMethodException {
        this.b = obj;
        this.a = cls.getDeclaredMethod("isDataCollectionDefaultEnabled", new Class[0]);
    }

    public boolean a() {
        try {
            return ((Boolean) this.a.invoke(this.b, new Object[0])).booleanValue();
        } catch (Exception e) {
            c.g().a("Fabric", "Cannot check isDataCollectionDefaultEnabled on FirebaseApp.", e);
            return false;
        }
    }
}
