package org.xutils.view;

import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import org.xutils.ViewInjector;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x.Ext;

public final class ViewInjectorImpl implements ViewInjector {
    private static final HashSet<Class<?>> a = new HashSet();
    private static final Object b = new Object();
    private static volatile ViewInjectorImpl c;

    static {
        a.add(Object.class);
        a.add(Activity.class);
        a.add(Fragment.class);
        try {
            a.add(Class.forName("android.support.v4.app.Fragment"));
            a.add(Class.forName("android.support.v4.app.FragmentActivity"));
        } catch (Throwable unused) {
        }
    }

    private ViewInjectorImpl() {
    }

    public static void registerInstance() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new ViewInjectorImpl();
                }
            }
        }
        Ext.setViewInjector(c);
    }

    public void inject(View view) {
        a(view, view.getClass(), new a(view));
    }

    public void inject(Activity activity) {
        Class cls = activity.getClass();
        try {
            ContentView a = a(cls);
            if (a != null && a.value() > 0) {
                cls.getMethod("setContentView", new Class[]{Integer.TYPE}).invoke(activity, new Object[]{Integer.valueOf(r1)});
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        a(activity, cls, new a(activity));
    }

    public void inject(Object obj, View view) {
        a(obj, obj.getClass(), new a(view));
    }

    public View inject(Object obj, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        Class cls = obj.getClass();
        View view = null;
        try {
            ContentView a = a(cls);
            if (a != null) {
                int value = a.value();
                if (value > 0) {
                    view = layoutInflater.inflate(value, viewGroup, false);
                }
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        a(obj, cls, new a(view));
        return view;
    }

    private static ContentView a(Class<?> cls) {
        if (cls == null || a.contains(cls)) {
            return null;
        }
        ContentView contentView = (ContentView) cls.getAnnotation(ContentView.class);
        return contentView == null ? a(cls.getSuperclass()) : contentView;
    }

    private static void a(Object obj, Class<?> cls, a aVar) {
        if (cls != null && !a.contains(cls)) {
            a(obj, cls.getSuperclass(), aVar);
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Class type = field.getType();
                    if (!(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()) || type.isPrimitive() || type.isArray())) {
                        ViewInject viewInject = (ViewInject) field.getAnnotation(ViewInject.class);
                        if (viewInject != null) {
                            try {
                                View a = aVar.a(viewInject.value(), viewInject.parentId());
                                if (a != null) {
                                    field.setAccessible(true);
                                    field.set(obj, a);
                                } else {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("Invalid @ViewInject for ");
                                    stringBuilder.append(cls.getSimpleName());
                                    stringBuilder.append(".");
                                    stringBuilder.append(field.getName());
                                    throw new RuntimeException(stringBuilder.toString());
                                }
                            } catch (Throwable th) {
                                LogUtil.e(th.getMessage(), th);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            Method[] declaredMethods = cls.getDeclaredMethods();
            if (declaredMethods != null && declaredMethods.length > 0) {
                for (Method method : declaredMethods) {
                    if (!Modifier.isStatic(method.getModifiers()) && Modifier.isPrivate(method.getModifiers())) {
                        Event event = (Event) method.getAnnotation(Event.class);
                        if (event != null) {
                            try {
                                int i;
                                int[] value = event.value();
                                int[] parentId = event.parentId();
                                if (parentId == null) {
                                    i = 0;
                                } else {
                                    i = parentId.length;
                                }
                                int i2 = 0;
                                while (i2 < value.length) {
                                    int i3 = value[i2];
                                    if (i3 > 0) {
                                        b bVar = new b();
                                        bVar.a = i3;
                                        bVar.b = i > i2 ? parentId[i2] : 0;
                                        method.setAccessible(true);
                                        EventListenerManager.a(aVar, bVar, event, obj, method);
                                    }
                                    i2++;
                                }
                            } catch (Throwable th2) {
                                LogUtil.e(th2.getMessage(), th2);
                            }
                        }
                    }
                }
            }
        }
    }
}
