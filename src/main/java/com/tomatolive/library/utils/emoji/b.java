package com.tomatolive.library.utils.emoji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: EmojiLoader */
public class b {
    public static List<a> a(InputStream inputStream) throws IOException {
        JSONException e;
        List<a> list = null;
        try {
            JSONArray jSONArray = new JSONArray(b(inputStream));
            List<a> arrayList = new ArrayList(jSONArray.length());
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    a a = a(jSONArray.getJSONObject(i));
                    if (a != null) {
                        arrayList.add(a);
                    }
                    i++;
                } catch (JSONException e2) {
                    JSONException jSONException = e2;
                    list = arrayList;
                    e = jSONException;
                    e.printStackTrace();
                    return list;
                }
            }
            return arrayList;
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            return list;
        }
    }

    private static String b(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuilder.append(readLine);
            } else {
                bufferedReader.close();
                return stringBuilder.toString();
            }
        }
    }

    protected static a a(JSONObject jSONObject) throws UnsupportedEncodingException, JSONException {
        a aVar = null;
        if (!jSONObject.has("emoji")) {
            return null;
        }
        byte[] bytes = jSONObject.getString("emoji").getBytes("UTF-8");
        if (jSONObject.has("description")) {
            aVar = jSONObject.getString("description");
        }
        return new a(aVar, jSONObject.has("supports_fitzpatrick") ? jSONObject.getBoolean("supports_fitzpatrick") : false, a(jSONObject.getJSONArray("aliases")), a(jSONObject.getJSONArray("tags")), bytes);
    }

    private static List<String> a(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }
}
