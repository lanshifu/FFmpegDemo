package com.tomatolive.library.utils.emoji;

import com.tomatolive.library.utils.emoji.EmojiTrie.Matches;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class EmojiParser {
    private static final Pattern a = Pattern.compile("(?<=:)\\+?(\\w|\\||\\-)+(?=:)");

    public enum FitzpatrickAction {
        PARSE,
        REMOVE,
        IGNORE
    }

    public interface a {
        String transform(b bVar);
    }

    public static class b {
        private final a a;
        private final Fitzpatrick b;
        private final int c;

        /* synthetic */ b(a aVar, String str, int i, AnonymousClass1 anonymousClass1) {
            this(aVar, str, i);
        }

        private b(a aVar, String str, int i) {
            this.a = aVar;
            this.b = Fitzpatrick.fitzpatrickFromUnicode(str);
            this.c = i;
        }

        public a a() {
            return this.a;
        }

        public boolean b() {
            return c() != null;
        }

        public Fitzpatrick c() {
            return this.b;
        }

        public String d() {
            return b() ? this.b.name().toLowerCase() : "";
        }

        public String e() {
            return b() ? this.b.unicode : "";
        }

        public int f() {
            return this.c;
        }

        public int g() {
            return this.c + this.a.c().length();
        }

        public int h() {
            return g() + (this.b != null ? 2 : 0);
        }
    }

    /* renamed from: com.tomatolive.library.utils.emoji.EmojiParser$1 */
    static class AnonymousClass1 implements a {
        final /* synthetic */ FitzpatrickAction a;

        public String transform(b bVar) {
            StringBuilder stringBuilder;
            switch (this.a) {
                case PARSE:
                    if (bVar.b()) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(":");
                        stringBuilder.append((String) bVar.a().a().get(0));
                        stringBuilder.append("|");
                        stringBuilder.append(bVar.d());
                        stringBuilder.append(":");
                        return stringBuilder.toString();
                    }
                    break;
                case REMOVE:
                    break;
                case IGNORE:
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(":");
                    stringBuilder.append((String) bVar.a().a().get(0));
                    stringBuilder.append(":");
                    stringBuilder.append(bVar.e());
                    return stringBuilder.toString();
                default:
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(":");
                    stringBuilder.append((String) bVar.a().a().get(0));
                    stringBuilder.append("|");
                    stringBuilder.append(bVar.d());
                    stringBuilder.append(":");
                    return stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(":");
            stringBuilder.append((String) bVar.a().a().get(0));
            stringBuilder.append(":");
            return stringBuilder.toString();
        }
    }

    public static String a(String str) {
        return a(str, -$$Lambda$EmojiParser$h0pfMuZHBIOE1lCdqEo00OWsPD4.INSTANCE);
    }

    public static String a(String str, a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (b bVar : b(str)) {
            stringBuilder.append(str.substring(i, bVar.f()));
            stringBuilder.append(aVar.transform(bVar));
            i = bVar.h();
        }
        stringBuilder.append(str.substring(i));
        return stringBuilder.toString();
    }

    protected static List<b> b(String str) {
        char[] toCharArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            b a = a(toCharArray, i);
            if (a == null) {
                return arrayList;
            }
            arrayList.add(a);
            i = a.h();
        }
    }

    protected static b a(char[] cArr, int i) {
        while (i < cArr.length) {
            int b = b(cArr, i);
            if (b != -1) {
                return new b(c.a(new String(cArr, i, b - i)), b + 2 <= cArr.length ? new String(cArr, b, 2) : null, i, null);
            }
            i++;
        }
        return null;
    }

    protected static int b(char[] cArr, int i) {
        int i2 = -1;
        for (int i3 = i + 1; i3 <= cArr.length; i3++) {
            Matches a = c.a(Arrays.copyOfRange(cArr, i, i3));
            if (a.exactMatch()) {
                i2 = i3;
            } else if (a.impossibleMatch()) {
                return i2;
            }
        }
        return i2;
    }
}
