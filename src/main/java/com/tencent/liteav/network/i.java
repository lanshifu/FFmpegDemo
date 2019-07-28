package com.tencent.liteav.network;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

/* compiled from: TXPlayInfoResponse */
public class i {
    protected JSONObject a;

    /* compiled from: TXPlayInfoResponse */
    public static class a {
        public String a;
        public String b;
        public List<Integer> c;
    }

    public i(JSONObject jSONObject) {
        this.a = jSONObject;
    }

    public String a() {
        if (e() != null) {
            return e().a;
        }
        if (c().size() == 0) {
            return d() != null ? d().a : null;
        } else {
            List j = j();
            if (j != null) {
                for (j jVar : c()) {
                    if (j.contains(Integer.valueOf(jVar.a()))) {
                        return jVar.a;
                    }
                }
            }
            return ((j) c().get(0)).a;
        }
    }

    public String b() {
        try {
            JSONObject jSONObject = this.a.getJSONObject("coverInfo");
            if (jSONObject != null) {
                return jSONObject.getString("coverUrl");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<j> c() {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = this.a.getJSONObject("videoInfo").getJSONArray("transcodeList");
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    j jVar = new j();
                    jVar.a = jSONObject.getString(OnNativeInvokeListener.ARG_URL);
                    jVar.e = jSONObject.getInt("duration");
                    jVar.c = jSONObject.getInt("width");
                    jVar.b = jSONObject.getInt("height");
                    jVar.d = Math.max(jSONObject.getInt("totalSize"), jSONObject.getInt("size"));
                    jVar.f = jSONObject.getInt(IjkMediaMeta.IJKM_KEY_BITRATE);
                    jVar.h = jSONObject.getInt("definition");
                    jVar.g = jSONObject.getString("container");
                    arrayList.add(jVar);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public j d() {
        try {
            JSONObject jSONObject = this.a.getJSONObject("videoInfo").getJSONObject("sourceVideo");
            j jVar = new j();
            jVar.a = jSONObject.getString(OnNativeInvokeListener.ARG_URL);
            jVar.e = jSONObject.getInt("duration");
            jVar.c = jSONObject.getInt("width");
            jVar.b = jSONObject.getInt("height");
            jVar.d = Math.max(jSONObject.getInt("size"), jSONObject.getInt("totalSize"));
            jVar.f = jSONObject.getInt(IjkMediaMeta.IJKM_KEY_BITRATE);
            return jVar;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public j e() {
        try {
            JSONObject jSONObject = this.a.getJSONObject("videoInfo").getJSONObject("masterPlayList");
            j jVar = new j();
            jVar.a = jSONObject.getString(OnNativeInvokeListener.ARG_URL);
            return jVar;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String f() {
        try {
            JSONObject jSONObject = this.a.getJSONObject("videoInfo").getJSONObject("basicInfo");
            if (jSONObject != null) {
                return jSONObject.getString("name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String g() {
        try {
            JSONObject jSONObject = this.a.getJSONObject("videoInfo").getJSONObject("basicInfo");
            if (jSONObject != null) {
                return jSONObject.getString("description");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String h() {
        try {
            return this.a.getJSONObject("playerInfo").getString("defaultVideoClassification");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<a> i() {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = this.a.getJSONObject("playerInfo").getJSONArray("videoClassification");
            for (int i = 0; i < jSONArray.length(); i++) {
                a aVar = new a();
                aVar.a = jSONArray.getJSONObject(i).getString("id");
                aVar.b = jSONArray.getJSONObject(i).getString("name");
                aVar.c = new ArrayList();
                JSONArray jSONArray2 = jSONArray.getJSONObject(i).getJSONArray("definitionList");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    aVar.c.add(Integer.valueOf(jSONArray2.getInt(i2)));
                }
                arrayList.add(aVar);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Integer> j() {
        List<a> i = i();
        String h = h();
        if (!(h == null || i == null)) {
            for (a aVar : i) {
                if (aVar.a.equals(h)) {
                    return aVar.c;
                }
            }
        }
        return null;
    }

    public j a(String str, String str2) {
        List list;
        List<a> i = i();
        if (!(str == null || i == null)) {
            for (a aVar : i) {
                if (aVar.a.equals(str)) {
                    list = aVar.c;
                    break;
                }
            }
        }
        list = null;
        if (list != null) {
            for (j jVar : c()) {
                if (list.contains(Integer.valueOf(jVar.h)) && (jVar.e() == null || jVar.e().contains(str2))) {
                    return jVar;
                }
            }
        }
        return null;
    }
}
