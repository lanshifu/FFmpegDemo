package defpackage;

import kotlin.TypeCastException;
import kotlin.jvm.internal.f;

/* compiled from: PlatformImplementations.kt */
/* renamed from: xr */
public final class xr {
    public static final xq a;

    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0060 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x0113 */
    static {
        /*
        r0 = defpackage.xr.a();
        r1 = 65544; // 0x10008 float:9.1847E-41 double:3.2383E-319;
        if (r0 < r1) goto L_0x00b7;
    L_0x0009:
        r1 = "kotlin.internal.jdk8.JDK8PlatformImplementations";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r2 = "Class.forName(\"kotlin.in…entations\").newInstance()";
        kotlin.jvm.internal.f.a(r1, r2);	 Catch:{ ClassNotFoundException -> 0x0060 }
        if (r1 == 0) goto L_0x001f;
    L_0x001a:
        r2 = r1;
        r2 = (defpackage.xq) r2;	 Catch:{ ClassCastException -> 0x0027 }
        goto L_0x016e;
    L_0x001f:
        r2 = new kotlin.TypeCastException;	 Catch:{ ClassCastException -> 0x0027 }
        r3 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";
        r2.<init>(r3);	 Catch:{ ClassCastException -> 0x0027 }
        throw r2;	 Catch:{ ClassCastException -> 0x0027 }
    L_0x0027:
        r2 = move-exception;
        r1 = r1.getClass();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = r1.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r3 = defpackage.xq.class;
        r3 = r3.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r4 = new java.lang.ClassCastException;	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5.<init>();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r6 = "Instance classloader: ";
        r5.append(r6);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5.append(r1);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = ", base type classloader: ";
        r5.append(r1);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r5.append(r3);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = r5.toString();	 Catch:{ ClassNotFoundException -> 0x0060 }
        r4.<init>(r1);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r2 = (java.lang.Throwable) r2;	 Catch:{ ClassNotFoundException -> 0x0060 }
        r1 = r4.initCause(r2);	 Catch:{ ClassNotFoundException -> 0x0060 }
        r2 = "ClassCastException(\"Inst…baseTypeCL\").initCause(e)";
        kotlin.jvm.internal.f.a(r1, r2);	 Catch:{ ClassNotFoundException -> 0x0060 }
        throw r1;	 Catch:{ ClassNotFoundException -> 0x0060 }
    L_0x0060:
        r1 = "kotlin.internal.JRE8PlatformImplementations";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r2 = "Class.forName(\"kotlin.in…entations\").newInstance()";
        kotlin.jvm.internal.f.a(r1, r2);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        if (r1 == 0) goto L_0x0076;
    L_0x0071:
        r2 = r1;
        r2 = (defpackage.xq) r2;	 Catch:{ ClassCastException -> 0x007e }
        goto L_0x016e;
    L_0x0076:
        r2 = new kotlin.TypeCastException;	 Catch:{ ClassCastException -> 0x007e }
        r3 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";
        r2.<init>(r3);	 Catch:{ ClassCastException -> 0x007e }
        throw r2;	 Catch:{ ClassCastException -> 0x007e }
    L_0x007e:
        r2 = move-exception;
        r1 = r1.getClass();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = r1.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r3 = defpackage.xq.class;
        r3 = r3.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r4 = new java.lang.ClassCastException;	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5.<init>();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r6 = "Instance classloader: ";
        r5.append(r6);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5.append(r1);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = ", base type classloader: ";
        r5.append(r1);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r5.append(r3);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = r5.toString();	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r4.<init>(r1);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r2 = (java.lang.Throwable) r2;	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r1 = r4.initCause(r2);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        r2 = "ClassCastException(\"Inst…baseTypeCL\").initCause(e)";
        kotlin.jvm.internal.f.a(r1, r2);	 Catch:{ ClassNotFoundException -> 0x00b7 }
        throw r1;	 Catch:{ ClassNotFoundException -> 0x00b7 }
    L_0x00b7:
        r1 = 65543; // 0x10007 float:9.1845E-41 double:3.23825E-319;
        if (r0 < r1) goto L_0x0169;
    L_0x00bc:
        r0 = "xt";
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = r0.newInstance();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r1 = "Class.forName(\"kotlin.in…entations\").newInstance()";
        kotlin.jvm.internal.f.a(r0, r1);	 Catch:{ ClassNotFoundException -> 0x0113 }
        if (r0 == 0) goto L_0x00d2;
    L_0x00cd:
        r2 = r0;
        r2 = (defpackage.xq) r2;	 Catch:{ ClassCastException -> 0x00da }
        goto L_0x016e;
    L_0x00d2:
        r1 = new kotlin.TypeCastException;	 Catch:{ ClassCastException -> 0x00da }
        r2 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";
        r1.<init>(r2);	 Catch:{ ClassCastException -> 0x00da }
        throw r1;	 Catch:{ ClassCastException -> 0x00da }
    L_0x00da:
        r1 = move-exception;
        r0 = r0.getClass();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = r0.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r2 = defpackage.xq.class;
        r2 = r2.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r3 = new java.lang.ClassCastException;	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4.<init>();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r5 = "Instance classloader: ";
        r4.append(r5);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4.append(r0);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = ", base type classloader: ";
        r4.append(r0);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r4.append(r2);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = r4.toString();	 Catch:{ ClassNotFoundException -> 0x0113 }
        r3.<init>(r0);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r1 = (java.lang.Throwable) r1;	 Catch:{ ClassNotFoundException -> 0x0113 }
        r0 = r3.initCause(r1);	 Catch:{ ClassNotFoundException -> 0x0113 }
        r1 = "ClassCastException(\"Inst…baseTypeCL\").initCause(e)";
        kotlin.jvm.internal.f.a(r0, r1);	 Catch:{ ClassNotFoundException -> 0x0113 }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x0113 }
    L_0x0113:
        r0 = "kotlin.internal.JRE7PlatformImplementations";
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = r0.newInstance();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r1 = "Class.forName(\"kotlin.in…entations\").newInstance()";
        kotlin.jvm.internal.f.a(r0, r1);	 Catch:{ ClassNotFoundException -> 0x0169 }
        if (r0 == 0) goto L_0x0128;
    L_0x0124:
        r2 = r0;
        r2 = (defpackage.xq) r2;	 Catch:{ ClassCastException -> 0x0130 }
        goto L_0x016e;
    L_0x0128:
        r1 = new kotlin.TypeCastException;	 Catch:{ ClassCastException -> 0x0130 }
        r2 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";
        r1.<init>(r2);	 Catch:{ ClassCastException -> 0x0130 }
        throw r1;	 Catch:{ ClassCastException -> 0x0130 }
    L_0x0130:
        r1 = move-exception;
        r0 = r0.getClass();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = r0.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r2 = defpackage.xq.class;
        r2 = r2.getClassLoader();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r3 = new java.lang.ClassCastException;	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4.<init>();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r5 = "Instance classloader: ";
        r4.append(r5);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4.append(r0);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = ", base type classloader: ";
        r4.append(r0);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r4.append(r2);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = r4.toString();	 Catch:{ ClassNotFoundException -> 0x0169 }
        r3.<init>(r0);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r1 = (java.lang.Throwable) r1;	 Catch:{ ClassNotFoundException -> 0x0169 }
        r0 = r3.initCause(r1);	 Catch:{ ClassNotFoundException -> 0x0169 }
        r1 = "ClassCastException(\"Inst…baseTypeCL\").initCause(e)";
        kotlin.jvm.internal.f.a(r0, r1);	 Catch:{ ClassNotFoundException -> 0x0169 }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x0169 }
    L_0x0169:
        r2 = new xq;
        r2.<init>();
    L_0x016e:
        a = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xr.<clinit>():void");
    }

    private static final int a() {
        String property = System.getProperty("java.specification.version");
        int i = 65542;
        if (property == null) {
            return 65542;
        }
        CharSequence charSequence = property;
        int a = n.a(charSequence, '.', 0, false, 6, null);
        if (a < 0) {
            try {
                i = Integer.parseInt(property) * 65536;
            } catch (NumberFormatException unused) {
            }
            return i;
        }
        int i2 = a + 1;
        int a2 = n.a(charSequence, '.', i2, false, 4, null);
        if (a2 < 0) {
            a2 = property.length();
        }
        if (property != null) {
            Object substring = property.substring(0, a);
            f.a(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            if (property != null) {
                Object substring2 = property.substring(i2, a2);
                f.a(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                try {
                    i = (Integer.parseInt(substring) * 65536) + Integer.parseInt(substring2);
                } catch (NumberFormatException unused2) {
                }
                return i;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
