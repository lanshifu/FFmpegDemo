package com.tomatolive.library.utils.litepal.util;

import android.text.TextUtils;
import com.tomatolive.library.utils.litepal.LitePalApplication;
import com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;
import com.tomatolive.library.utils.litepal.parser.LitePalAttr;
import com.tomatolive.library.utils.litepal.util.Const.Config;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Locale;

public class BaseUtility {
    private BaseUtility() {
    }

    public static String changeCase(String str) {
        if (str == null) {
            return null;
        }
        String cases = LitePalAttr.getInstance().getCases();
        if ("keep".equals(cases)) {
            return str;
        }
        if ("upper".equals(cases)) {
            return str.toUpperCase(Locale.US);
        }
        return str.toLowerCase(Locale.US);
    }

    public static boolean containsIgnoreCases(Collection<String> collection, String str) {
        boolean z = false;
        if (collection == null) {
            return false;
        }
        if (str == null) {
            return collection.contains(null);
        }
        for (String equalsIgnoreCase : collection) {
            if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                z = true;
                break;
            }
        }
        return z;
    }

    public static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str == null ? null : "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.substring(0, 1).toUpperCase(Locale.US));
        stringBuilder.append(str.substring(1));
        return stringBuilder.toString();
    }

    public static int count(String str, String str2) {
        int i = 0;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        int indexOf = str.indexOf(str2);
        while (indexOf != -1) {
            i++;
            str = str.substring(indexOf + str2.length());
            indexOf = str.indexOf(str2);
        }
        return i;
    }

    public static void checkConditionsCorrect(String... strArr) {
        if (strArr != null) {
            int length = strArr.length;
            if (length > 0 && length != count(strArr[0], "?") + 1) {
                throw new LitePalSupportException("The parameters in conditions are incorrect.");
            }
        }
    }

    /* JADX WARNING: Missing block: B:39:0x00a0, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:40:0x00a1, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:41:0x00a2, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:42:0x00a3, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:43:0x00a4, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:44:0x00a5, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:45:0x00a6, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:46:0x00a7, code skipped:
            return true;
     */
    public static boolean isFieldTypeSupported(java.lang.String r2) {
        /*
        r0 = "boolean";
        r0 = r0.equals(r2);
        r1 = 1;
        if (r0 != 0) goto L_0x00a7;
    L_0x0009:
        r0 = "java.lang.Boolean";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0013;
    L_0x0011:
        goto L_0x00a7;
    L_0x0013:
        r0 = "float";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x00a6;
    L_0x001b:
        r0 = "java.lang.Float";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0025;
    L_0x0023:
        goto L_0x00a6;
    L_0x0025:
        r0 = "double";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x00a5;
    L_0x002d:
        r0 = "java.lang.Double";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0037;
    L_0x0035:
        goto L_0x00a5;
    L_0x0037:
        r0 = "int";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x00a4;
    L_0x003f:
        r0 = "java.lang.Integer";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0048;
    L_0x0047:
        goto L_0x00a4;
    L_0x0048:
        r0 = "long";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x00a3;
    L_0x0050:
        r0 = "java.lang.Long";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0059;
    L_0x0058:
        goto L_0x00a3;
    L_0x0059:
        r0 = "short";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x00a2;
    L_0x0061:
        r0 = "java.lang.Short";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x006a;
    L_0x0069:
        goto L_0x00a2;
    L_0x006a:
        r0 = "char";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x00a1;
    L_0x0072:
        r0 = "java.lang.Character";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x007b;
    L_0x007a:
        goto L_0x00a1;
    L_0x007b:
        r0 = "[B";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x00a0;
    L_0x0083:
        r0 = "[Ljava.lang.Byte;";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x008c;
    L_0x008b:
        goto L_0x00a0;
    L_0x008c:
        r0 = "java.lang.String";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x009f;
    L_0x0094:
        r0 = "java.util.Date";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x009d;
    L_0x009c:
        goto L_0x009f;
    L_0x009d:
        r2 = 0;
        return r2;
    L_0x009f:
        return r1;
    L_0x00a0:
        return r1;
    L_0x00a1:
        return r1;
    L_0x00a2:
        return r1;
    L_0x00a3:
        return r1;
    L_0x00a4:
        return r1;
    L_0x00a5:
        return r1;
    L_0x00a6:
        return r1;
    L_0x00a7:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.litepal.util.BaseUtility.isFieldTypeSupported(java.lang.String):boolean");
    }

    public static boolean isGenericTypeSupported(String str) {
        if ("java.lang.String".equals(str) || "java.lang.Integer".equals(str) || "java.lang.Float".equals(str) || "java.lang.Double".equals(str) || "java.lang.Long".equals(str) || "java.lang.Short".equals(str) || "java.lang.Boolean".equals(str) || "java.lang.Character".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isLitePalXMLExists() {
        try {
            String[] list = LitePalApplication.getContext().getAssets().list("");
            if (list != null && list.length > 0) {
                for (String equalsIgnoreCase : list) {
                    if (Config.CONFIGURATION_FILE_NAME.equalsIgnoreCase(equalsIgnoreCase)) {
                        return true;
                    }
                }
            }
        } catch (IOException unused) {
        }
        return false;
    }

    public static boolean isClassAndMethodExist(String str, String str2) {
        try {
            for (Method name : Class.forName(str).getMethods()) {
                if (str2.equals(name.getName())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
