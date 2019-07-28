package com.tomatolive.library.utils;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;

/* compiled from: EmojiFilter */
public class e {
    public static boolean a(String str) {
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (55296 > charAt || charAt > 56319) {
                if ((8448 <= charAt && charAt <= 10239 && charAt != 9787) || ((11013 <= charAt && charAt <= 11015) || ((10548 <= charAt && charAt <= 10549) || ((12951 <= charAt && charAt <= 12953) || charAt == 169 || charAt == 174 || charAt == 12349 || charAt == 12336 || charAt == 11093 || charAt == 11036 || charAt == 11035 || charAt == 11088 || charAt == 8986)))) {
                    z = true;
                }
                if (!z) {
                    if (str.length() > 1) {
                        if (i < str.length() - 1) {
                            if (str.charAt(i + 1) != 8419) {
                            }
                        }
                    }
                }
            } else {
                if (str.length() > 1) {
                    int charAt2 = (((charAt - 55296) * Filter.K) + (str.charAt(i + 1) - 56320)) + 65536;
                    if (118784 <= charAt2) {
                        if (charAt2 > 128895) {
                        }
                    }
                }
            }
            z = true;
        }
        return z;
    }
}
