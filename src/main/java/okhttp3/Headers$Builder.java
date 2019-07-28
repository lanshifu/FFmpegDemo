package okhttp3;

import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.Util;

public final class Headers$Builder {
    final List<String> namesAndValues = new ArrayList(20);

    /* Access modifiers changed, original: 0000 */
    public Headers$Builder addLenient(String str) {
        int indexOf = str.indexOf(":", 1);
        if (indexOf != -1) {
            return addLenient(str.substring(0, indexOf), str.substring(indexOf + 1));
        }
        if (str.startsWith(":")) {
            return addLenient("", str.substring(1));
        }
        return addLenient("", str);
    }

    public Headers$Builder add(String str) {
        int indexOf = str.indexOf(":");
        if (indexOf != -1) {
            return add(str.substring(0, indexOf).trim(), str.substring(indexOf + 1));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected header: ");
        stringBuilder.append(str);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public Headers$Builder add(String str, String str2) {
        checkNameAndValue(str, str2);
        return addLenient(str, str2);
    }

    public Headers$Builder addAll(Headers headers) {
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            addLenient(headers.name(i), headers.value(i));
        }
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public Headers$Builder addLenient(String str, String str2) {
        this.namesAndValues.add(str);
        this.namesAndValues.add(str2.trim());
        return this;
    }

    public Headers$Builder removeAll(String str) {
        int i = 0;
        while (i < this.namesAndValues.size()) {
            if (str.equalsIgnoreCase((String) this.namesAndValues.get(i))) {
                this.namesAndValues.remove(i);
                this.namesAndValues.remove(i);
                i -= 2;
            }
            i += 2;
        }
        return this;
    }

    public Headers$Builder set(String str, String str2) {
        checkNameAndValue(str, str2);
        removeAll(str);
        addLenient(str, str2);
        return this;
    }

    private void checkNameAndValue(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        } else {
            int i;
            char charAt;
            int length = str.length();
            for (i = 0; i < length; i++) {
                charAt = str.charAt(i);
                if (charAt <= ' ' || charAt >= 127) {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str}));
                }
            }
            if (str2 != null) {
                length = str2.length();
                i = 0;
                while (i < length) {
                    charAt = str2.charAt(i);
                    if ((charAt > 31 || charAt == 9) && charAt < 127) {
                        i++;
                    } else {
                        throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str, str2}));
                    }
                }
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("value for name ");
            stringBuilder.append(str);
            stringBuilder.append(" == null");
            throw new NullPointerException(stringBuilder.toString());
        }
    }

    public String get(String str) {
        for (int size = this.namesAndValues.size() - 2; size >= 0; size -= 2) {
            if (str.equalsIgnoreCase((String) this.namesAndValues.get(size))) {
                return (String) this.namesAndValues.get(size + 1);
            }
        }
        return null;
    }

    public Headers build() {
        return new Headers(this);
    }
}
