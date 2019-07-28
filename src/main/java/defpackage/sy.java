package defpackage;

import android.util.Log;
import com.youdao.sdk.app.other.m;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* renamed from: sy */
public class sy {
    private static final Logger a = Logger.getLogger("com.youdao");
    private static final a b = new a();

    /* renamed from: sy$a */
    static final class a extends Handler {
        private static final Map<Level, Integer> a = new HashMap(7);

        public void close() {
        }

        public void flush() {
        }

        private a() {
        }

        /* synthetic */ a(m mVar) {
            this();
        }

        static {
            a.put(Level.FINEST, Integer.valueOf(2));
            a.put(Level.FINER, Integer.valueOf(2));
            a.put(Level.FINE, Integer.valueOf(2));
            a.put(Level.CONFIG, Integer.valueOf(3));
            a.put(Level.INFO, Integer.valueOf(4));
            a.put(Level.WARNING, Integer.valueOf(5));
            a.put(Level.SEVERE, Integer.valueOf(6));
        }

        public void publish(LogRecord logRecord) {
            if (isLoggable(logRecord)) {
                int intValue = a.containsKey(logRecord.getLevel()) ? ((Integer) a.get(logRecord.getLevel())).intValue() : 2;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(logRecord.getMessage());
                stringBuilder.append("\n");
                String stringBuilder2 = stringBuilder.toString();
                Throwable thrown = logRecord.getThrown();
                if (thrown != null) {
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(stringBuilder2);
                    stringBuilder3.append(Log.getStackTraceString(thrown));
                    stringBuilder2 = stringBuilder3.toString();
                }
                Log.println(intValue, "YouDao", stringBuilder2);
            }
        }
    }

    static {
        LogManager.getLogManager().addLogger(a);
        a.addHandler(b);
        a.setLevel(Level.FINE);
    }

    public static void a(String str) {
        sy.a(str, null);
    }

    public static void b(String str) {
        sy.b(str, null);
    }

    public static void c(String str) {
        sy.c(str, null);
    }

    public static void a(String str, Throwable th) {
        a.log(Level.CONFIG, str, th);
    }

    public static void b(String str, Throwable th) {
        a.log(Level.WARNING, str, th);
    }

    public static void c(String str, Throwable th) {
        a.log(Level.SEVERE, str, th);
    }
}
