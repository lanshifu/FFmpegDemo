package com.youdao.sdk.app;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class d {
    public static List<String> a(JSONObject jSONObject, String str) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || jSONObject.isNull(str)) {
            return arrayList;
        }
        JSONArray jSONArray = jSONObject.getJSONArray(str);
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(String.valueOf(jSONArray.get(i)));
        }
        return arrayList;
    }

    public static String a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || jSONObject.isNull(str)) {
            return str2;
        }
        return jSONObject.getString(str);
    }

    public static int a(JSONObject jSONObject, String str, int i) {
        if (jSONObject == null || jSONObject.isNull(str)) {
            return i;
        }
        return jSONObject.getInt(str);
    }

    public static JSONObject b(JSONObject jSONObject, String str) {
        if (jSONObject == null || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.getJSONObject(str);
    }
}
