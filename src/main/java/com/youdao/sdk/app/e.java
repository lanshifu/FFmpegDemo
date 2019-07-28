package com.youdao.sdk.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class e {
    public static String a = "aicloud";
    public static Set<String> b = new HashSet();
    public static final String[] c = new String[]{"自动", "中文", "日文", "英文", "韩文", "法文", "俄文", "西班牙文", "葡萄牙文", "越南文", "繁体中文", "印地文"};
    public static Set<String> d = new HashSet();
    public static Set<String> e = new HashSet();
    public static Set<String> f = new HashSet();
    private static Map<String, Language> g = new HashMap();
    private static Map<String, Language> h = new HashMap();

    static {
        g.put("自动", Language.AUTO);
        g.put("中文", Language.CHINESE);
        g.put("日文", Language.JAPANESE);
        g.put("英文", Language.ENGLISH);
        g.put("韩文", Language.KOREAN);
        g.put("法文", Language.FRENCH);
        g.put("西班牙文", Language.SPANISH);
        g.put("俄文", Language.RUSSIAN);
        g.put("葡萄牙文", Language.PORTUGUESE);
        g.put("越南文", Language.Vietnamese);
        g.put("繁体中文", Language.TraditionalChinese);
        g.put("印地文", Language.INDO);
        h.put(Language.AUTO.getCode(), Language.AUTO);
        h.put(Language.CHINESE.getCode(), Language.CHINESE);
        h.put(Language.JAPANESE.getCode(), Language.JAPANESE);
        h.put(Language.ENGLISH.getCode(), Language.ENGLISH);
        h.put(Language.KOREAN.getCode(), Language.KOREAN);
        h.put(Language.FRENCH.getCode(), Language.FRENCH);
        h.put(Language.SPANISH.getCode(), Language.SPANISH);
        h.put(Language.RUSSIAN.getCode(), Language.RUSSIAN);
        h.put(Language.PORTUGUESE.getCode(), Language.PORTUGUESE);
        h.put(Language.Vietnamese.getCode(), Language.Vietnamese);
        h.put(Language.TraditionalChinese.getCode(), Language.TraditionalChinese);
        h.put(Language.INDO.getCode(), Language.INDO);
        e.add("zh-CHS");
        e.add("en");
        d.add("zh-CHS");
        d.add("en");
        d.add("ja");
        d.add("ko");
        d.add("fr");
        d.add("es");
        d.add("vi");
        b.add("oppo");
        b.add("aicloud");
        b.add("zhangyue");
        f.add("en");
        f.add("hi");
    }

    public static Language a(String str) {
        return (Language) g.get(str);
    }

    public static Language b(String str) {
        return (Language) h.get(str);
    }
}
