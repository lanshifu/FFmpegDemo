package de.greenrobot.event;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class SubscriberMethodFinder {
    private static final int BRIDGE = 64;
    private static final int MODIFIERS_IGNORE = 5192;
    private static final String ON_EVENT_METHOD_NAME = "onEvent";
    private static final int SYNTHETIC = 4096;
    private static final Map<String, List<SubscriberMethod>> methodCache = new HashMap();
    private final Map<Class<?>, Class<?>> skipMethodVerificationForClasses = new ConcurrentHashMap();

    SubscriberMethodFinder(List<Class<?>> list) {
        if (list != null) {
            for (Class cls : list) {
                this.skipMethodVerificationForClasses.put(cls, cls);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public List<SubscriberMethod> findSubscriberMethods(Class<?> cls) {
        List list;
        String name = cls.getName();
        synchronized (methodCache) {
            list = (List) methodCache.get(name);
        }
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String name2 = cls2.getName();
            if (name2.startsWith("java.") || name2.startsWith("javax.") || name2.startsWith("android.")) {
                break;
            }
            for (Method method : cls2.getDeclaredMethods()) {
                String name3 = method.getName();
                if (name3.startsWith(ON_EVENT_METHOD_NAME)) {
                    int modifiers = method.getModifiers();
                    if ((modifiers & 1) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
                        Class[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1) {
                            ThreadMode threadMode;
                            String substring = name3.substring(ON_EVENT_METHOD_NAME.length());
                            if (substring.length() == 0) {
                                threadMode = ThreadMode.PostThread;
                            } else if (substring.equals("MainThread")) {
                                threadMode = ThreadMode.MainThread;
                            } else if (substring.equals("BackgroundThread")) {
                                threadMode = ThreadMode.BackgroundThread;
                            } else if (substring.equals("Async")) {
                                threadMode = ThreadMode.Async;
                            } else if (!this.skipMethodVerificationForClasses.containsKey(cls2)) {
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Illegal onEvent method, check for typos: ");
                                stringBuilder2.append(method);
                                throw new EventBusException(stringBuilder2.toString());
                            }
                            Class cls3 = parameterTypes[0];
                            stringBuilder.setLength(0);
                            stringBuilder.append(name3);
                            stringBuilder.append('>');
                            stringBuilder.append(cls3.getName());
                            if (hashSet.add(stringBuilder.toString())) {
                                arrayList.add(new SubscriberMethod(method, threadMode, cls3));
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.skipMethodVerificationForClasses.containsKey(cls2)) {
                        String str = EventBus.TAG;
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Skipping method (not public, static or abstract): ");
                        stringBuilder3.append(cls2);
                        stringBuilder3.append(".");
                        stringBuilder3.append(name3);
                        Log.d(str, stringBuilder3.toString());
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("Subscriber ");
            stringBuilder4.append(cls);
            stringBuilder4.append(" has no public methods called ");
            stringBuilder4.append(ON_EVENT_METHOD_NAME);
            throw new EventBusException(stringBuilder4.toString());
        }
        synchronized (methodCache) {
            methodCache.put(name, arrayList);
        }
        return arrayList;
    }

    static void clearCaches() {
        synchronized (methodCache) {
            methodCache.clear();
        }
    }
}
