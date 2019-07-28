package com.tomatolive.library.utils.emoji;

import com.tomatolive.library.a;
import com.tomatolive.library.utils.emoji.EmojiTrie.Matches;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: EmojiManager */
public class c {
    private static final Map<String, a> a = new HashMap();
    private static final Map<String, Set<a>> b = new HashMap();
    private static final List<a> c;
    private static final EmojiTrie d;

    static {
        try {
            InputStream open = a.a().c().getAssets().open("emojis.json");
            List<a> a = b.a(open);
            c = a;
            for (a aVar : a) {
                for (String str : aVar.b()) {
                    if (b.get(str) == null) {
                        b.put(str, new HashSet());
                    }
                    ((Set) b.get(str)).add(aVar);
                }
                for (String str2 : aVar.a()) {
                    a.put(str2, aVar);
                }
            }
            d = new EmojiTrie(a);
            open.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static a a(String str) {
        return str == null ? null : d.a(str);
    }

    public static Matches a(char[] cArr) {
        return d.a(cArr);
    }
}
