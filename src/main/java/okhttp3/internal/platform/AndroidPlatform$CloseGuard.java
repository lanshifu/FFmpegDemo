package okhttp3.internal.platform;

import java.lang.reflect.Method;

final class AndroidPlatform$CloseGuard {
    private final Method getMethod;
    private final Method openMethod;
    private final Method warnIfOpenMethod;

    AndroidPlatform$CloseGuard(Method method, Method method2, Method method3) {
        this.getMethod = method;
        this.openMethod = method2;
        this.warnIfOpenMethod = method3;
    }

    /* Access modifiers changed, original: 0000 */
    public Object createAndOpen(String str) {
        if (this.getMethod != null) {
            try {
                Object invoke = this.getMethod.invoke(null, new Object[0]);
                this.openMethod.invoke(invoke, new Object[]{str});
                return invoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean warnIfOpen(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            this.warnIfOpenMethod.invoke(obj, new Object[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    static AndroidPlatform$CloseGuard get() {
        Method method;
        Method method2;
        Method method3 = null;
        try {
            Class cls = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls.getMethod("get", new Class[0]);
            method = cls.getMethod("open", new Class[]{String.class});
            method2 = cls.getMethod("warnIfOpen", new Class[0]);
            method3 = method4;
        } catch (Exception unused) {
            method2 = null;
            method = method2;
        }
        return new AndroidPlatform$CloseGuard(method3, method, method2);
    }
}
