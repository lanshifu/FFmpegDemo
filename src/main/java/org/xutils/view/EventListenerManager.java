package org.xutils.view;

import android.text.TextUtils;
import android.view.View;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import org.xutils.common.util.DoubleKeyValueMap;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.Event;

final class EventListenerManager {
    private static final HashSet<String> a = new HashSet(2);
    private static final DoubleKeyValueMap<b, Class<?>, Object> b = new DoubleKeyValueMap();

    public static class DynamicHandler implements InvocationHandler {
        private static long c;
        private WeakReference<Object> a;
        private final HashMap<String, Method> b = new HashMap(1);

        public DynamicHandler(Object obj) {
            this.a = new WeakReference(obj);
        }

        public void addMethod(String str, Method method) {
            this.b.put(str, method);
        }

        public Object getHandler() {
            return this.a.get();
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            obj = this.a.get();
            if (obj != null) {
                String name = method.getName();
                if ("toString".equals(name)) {
                    return DynamicHandler.class.getSimpleName();
                }
                Method method2 = (Method) this.b.get(name);
                if (method2 == null && this.b.size() == 1) {
                    Iterator it = this.b.entrySet().iterator();
                    if (it.hasNext()) {
                        Entry entry = (Entry) it.next();
                        if (TextUtils.isEmpty((CharSequence) entry.getKey())) {
                            method2 = (Method) entry.getValue();
                        }
                    }
                }
                if (method2 != null) {
                    if (EventListenerManager.a.contains(name)) {
                        long currentTimeMillis = System.currentTimeMillis() - c;
                        if (currentTimeMillis < 300) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("onClick cancelled: ");
                            stringBuilder.append(currentTimeMillis);
                            LogUtil.d(stringBuilder.toString());
                            return null;
                        }
                        c = System.currentTimeMillis();
                    }
                    try {
                        return method2.invoke(obj, objArr);
                    } catch (Throwable th) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("invoke method error:");
                        stringBuilder2.append(obj.getClass().getName());
                        stringBuilder2.append("#");
                        stringBuilder2.append(method2.getName());
                        RuntimeException runtimeException = new RuntimeException(stringBuilder2.toString(), th);
                    }
                } else {
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("method not impl: ");
                    stringBuilder3.append(name);
                    stringBuilder3.append("(");
                    stringBuilder3.append(obj.getClass().getSimpleName());
                    stringBuilder3.append(")");
                    LogUtil.w(stringBuilder3.toString());
                }
            }
            return null;
        }
    }

    static {
        a.add("onClick");
        a.add("onItemClick");
    }

    public static void a(a aVar, b bVar, Event event, Object obj, Method method) {
        try {
            View a = aVar.a(bVar);
            if (a != null) {
                boolean equals;
                Class type = event.type();
                String str = event.setter();
                if (TextUtils.isEmpty(str)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("set");
                    stringBuilder.append(type.getSimpleName());
                    str = stringBuilder.toString();
                }
                String method2 = event.method();
                Object obj2 = b.get(bVar, type);
                if (obj2 != null) {
                    DynamicHandler dynamicHandler = (DynamicHandler) Proxy.getInvocationHandler(obj2);
                    equals = obj.equals(dynamicHandler.getHandler());
                    if (equals) {
                        dynamicHandler.addMethod(method2, method);
                    }
                } else {
                    equals = false;
                }
                if (!equals) {
                    DynamicHandler dynamicHandler2 = new DynamicHandler(obj);
                    dynamicHandler2.addMethod(method2, method);
                    obj2 = Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, dynamicHandler2);
                    b.put(bVar, type, obj2);
                }
                a.getClass().getMethod(str, new Class[]{type}).invoke(a, new Object[]{obj2});
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }
}
