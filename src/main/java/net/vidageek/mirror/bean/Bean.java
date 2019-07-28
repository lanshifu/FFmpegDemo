package net.vidageek.mirror.bean;

import java.util.ArrayList;
import java.util.List;

public final class Bean {
    private String capitalize(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.substring(0, 1).toUpperCase());
        stringBuilder.append(str.substring(1));
        return stringBuilder.toString();
    }

    public List<String> getter(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(capitalize(str));
        arrayList.add(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("is");
        stringBuilder.append(capitalize(str));
        arrayList.add(stringBuilder.toString());
        return arrayList;
    }

    public String setter(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(capitalize(str));
        return stringBuilder.toString();
    }
}
