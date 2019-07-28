package defpackage;

import android.text.TextUtils;
import android.widget.EditText;
import com.one.tomato.utils.ad;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: CommentCheckUtil */
/* renamed from: qa */
public class qa {
    private static char[] a = new char[]{',', '.', '!', '?', ':', ';', 65292, 12290, 65281, 65311, 65306, 65307};

    public static boolean a(EditText editText, String str) {
        if (TextUtils.isEmpty(str)) {
            ad.a(2131756594);
            return false;
        } else if (str.length() < 4) {
            ad.a(2131756209);
            return false;
        } else if (qa.a(str)) {
            return false;
        } else {
            if (qa.b(str)) {
                ad.a(2131756208);
                editText.setText("");
                return false;
            } else if (qa.c(str)) {
                ad.a(2131756206);
                editText.setText("");
                return false;
            } else if (qa.a(str, 8)) {
                ad.a(2131756206);
                editText.setText("");
                return false;
            } else if (qa.b(str, 8)) {
                ad.a(2131756206);
                editText.setText("");
                return false;
            } else if (!qa.d(str)) {
                return true;
            } else {
                ad.a(2131756206);
                editText.setText("");
                return false;
            }
        }
    }

    public static boolean a(String str) {
        if (str.trim().length() < 270) {
            return false;
        }
        ad.a(2131756207);
        return true;
    }

    public static boolean b(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!qa.a(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (qa.b(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(String str, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            i2 = (qa.b(str) || !qa.b(str.charAt(i3))) ? i2 + 1 : 0;
        }
        if (i2 >= i) {
            return true;
        }
        return false;
    }

    private static boolean a(char c) {
        Pattern compile = Pattern.compile("[0-9]");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c);
        stringBuilder.append("");
        return compile.matcher(stringBuilder.toString()).matches();
    }

    private static boolean b(char c) {
        Pattern compile = Pattern.compile("[\\u4E00-\\u9FA5|0-9|a-z|A-Z]");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c);
        stringBuilder.append("");
        return compile.matcher(stringBuilder.toString()).matches();
    }

    public static boolean b(String str, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (qa.a(str.charAt(i3))) {
                i2++;
            }
        }
        if (i2 >= i) {
            return true;
        }
        return false;
    }

    public static boolean d(String str) {
        List asList = Arrays.asList(new char[][]{a});
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (!(asList.contains(Character.valueOf(charAt)) || qa.b(charAt))) {
                i++;
            }
        }
        if ((i * 100) / str.length() > 30) {
            ad.a(2131756206);
            return true;
        } else if (i < 10) {
            return false;
        } else {
            ad.a(2131756206);
            return true;
        }
    }
}
