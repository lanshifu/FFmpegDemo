package com.tomatolive.library.utils.emoji;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmojiTrie {
    private a a = new a();

    public enum Matches {
        EXACTLY,
        POSSIBLY,
        IMPOSSIBLE;

        public boolean exactMatch() {
            return this == EXACTLY;
        }

        public boolean impossibleMatch() {
            return this == IMPOSSIBLE;
        }
    }

    private class a {
        private Map<Character, a> b;
        private a c;

        private a() {
            this.b = new HashMap();
        }

        private void a(a aVar) {
            this.c = aVar;
        }

        private a a() {
            return this.c;
        }

        private boolean a(char c) {
            return this.b.containsKey(Character.valueOf(c));
        }

        private void b(char c) {
            this.b.put(Character.valueOf(c), new a());
        }

        private a c(char c) {
            return (a) this.b.get(Character.valueOf(c));
        }

        private boolean b() {
            return this.c != null;
        }
    }

    public EmojiTrie(Collection<a> collection) {
        for (a aVar : collection) {
            a aVar2 = this.a;
            for (char c : aVar.c().toCharArray()) {
                if (!aVar2.a(c)) {
                    aVar2.b(c);
                }
                aVar2 = aVar2.c(c);
            }
            aVar2.a(aVar);
        }
    }

    public Matches a(char[] cArr) {
        if (cArr == null) {
            return Matches.POSSIBLY;
        }
        a aVar = this.a;
        for (char c : cArr) {
            if (!aVar.a(c)) {
                return Matches.IMPOSSIBLE;
            }
            aVar = aVar.c(c);
        }
        return aVar.b() ? Matches.EXACTLY : Matches.POSSIBLY;
    }

    public a a(String str) {
        a aVar = this.a;
        for (char c : str.toCharArray()) {
            if (!aVar.a(c)) {
                return null;
            }
            aVar = aVar.c(c);
        }
        return aVar.a();
    }
}
