package defpackage;

import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: WebvttParserUtil */
/* renamed from: lr */
public final class lr {
    private static final Pattern a = Pattern.compile("^NOTE(( |\t).*)?$");
    private static final Pattern b = Pattern.compile("^﻿?WEBVTT(( |\t).*)?$");

    public static void a(n nVar) throws SubtitleDecoderException {
        String z = nVar.z();
        if (z == null || !b.matcher(z).matches()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected WEBVTT. Got ");
            stringBuilder.append(z);
            throw new SubtitleDecoderException(stringBuilder.toString());
        }
    }

    public static long a(String str) throws NumberFormatException {
        String[] b = z.b(str, "\\.");
        int i = 0;
        String[] a = z.a(b[0], ":");
        long j = 0;
        while (i < a.length) {
            j = (j * 60) + Long.parseLong(a[i]);
            i++;
        }
        j *= 1000;
        if (b.length == 2) {
            j += Long.parseLong(b[1]);
        }
        return j * 1000;
    }

    public static float b(String str) throws NumberFormatException {
        if (str.endsWith("%")) {
            return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }

    public static Matcher b(n nVar) {
        while (true) {
            String z = nVar.z();
            if (z == null) {
                return null;
            }
            if (a.matcher(z).matches()) {
                while (true) {
                    z = nVar.z();
                    if (z == null || z.isEmpty()) {
                        break;
                    }
                }
            } else {
                Matcher matcher = lp.a.matcher(z);
                if (matcher.matches()) {
                    return matcher;
                }
            }
        }
    }
}
