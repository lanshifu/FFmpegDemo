package kotlin.text;

/* compiled from: Char.kt */
class c extends b {
    public static final boolean a(char c, char c2, boolean z) {
        if (c == c2) {
            return true;
        }
        if (z) {
            return Character.toUpperCase(c) == Character.toUpperCase(c2) || Character.toLowerCase(c) == Character.toLowerCase(c2);
        } else {
            return false;
        }
    }
}
