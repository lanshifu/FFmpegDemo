package defpackage;

import android.os.Environment;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: LogUtil */
/* renamed from: fk */
public class fk {
    public static Boolean a = Boolean.valueOf(true);
    private static Boolean b = Boolean.valueOf(false);
    private static char c = 'i';
    private static String d = "MH-VTM";
    private static int e = 0;
    private static String f = "anzer_explain_log.txt";
    private static SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat h = new SimpleDateFormat("yyyy-MM-dd");

    public static void a(String str, String str2) {
        fk.a(str, str2, 'i');
    }

    private static void a(String str, String str2, char c) {
        if (b.booleanValue()) {
            if ('e' == c && ('e' == c || 'v' == c)) {
                Log.e(str, str2);
            } else if ('w' == c && ('w' == c || 'v' == c)) {
                Log.w(str, str2);
            } else if ('d' == c && ('d' == c || 'v' == c)) {
                Log.d(str, str2);
            } else if ('i' == c && ('d' == c || 'v' == c)) {
                Log.i(str, str2);
            } else {
                Log.v(str, str2);
            }
            if (a.booleanValue()) {
                fk.a(String.valueOf(c), str, str2);
            }
        }
    }

    private static String a() {
        String str = "";
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return str;
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(externalStorageDirectory.getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(d);
        return stringBuilder.toString();
    }

    private static void a(String str, String str2, String str3) {
        Date date = new Date();
        String format = h.format(date);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(g.format(date));
        stringBuilder.append("    ");
        stringBuilder.append(str);
        stringBuilder.append("    ");
        stringBuilder.append(str2);
        stringBuilder.append("    ");
        stringBuilder.append(str3);
        str = stringBuilder.toString();
        str2 = fk.a();
        if (str2 != null && !"".equals(str2)) {
            try {
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdir();
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append(File.separator);
                stringBuilder2.append(format);
                stringBuilder2.append(f);
                FileWriter fileWriter = new FileWriter(new File(stringBuilder2.toString()), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
