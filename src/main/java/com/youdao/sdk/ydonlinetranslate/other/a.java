package com.youdao.sdk.ydonlinetranslate.other;

import android.util.Log;
import com.youdao.sdk.ydonlinetranslate.OCRTranslateResult;
import com.youdao.sdk.ydonlinetranslate.Region;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    public static void a(OCRTranslateResult oCRTranslateResult) {
        try {
            JSONObject jSONObject = new JSONObject(oCRTranslateResult.getJson());
            oCRTranslateResult.setErrorCode(Integer.parseInt(jSONObject.getString("errorCode")));
            oCRTranslateResult.setFrom(jSONObject.getString("lanFrom"));
            oCRTranslateResult.setTo(jSONObject.getString("lanTo"));
            oCRTranslateResult.setOrientation(jSONObject.getString("orientation"));
            oCRTranslateResult.setTextAngle(jSONObject.getInt("textAngle"));
            JSONArray jSONArray = jSONObject.getJSONArray("resRegions");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                Region region = new Region();
                a(region, jSONObject2);
                oCRTranslateResult.getRegions().add(region);
            }
        } catch (Exception e) {
            Log.w("ocr parse", e);
        }
    }

    private static void a(Region region, JSONObject jSONObject) {
        region.setBoundingBox(a(jSONObject.getString("boundingBox")));
        region.setContext(jSONObject.getString("context"));
        region.setTranContent(jSONObject.getString("tranContent"));
        region.setLinesCount(jSONObject.getInt("linesCount"));
        region.setLineheight(jSONObject.getInt("lineheight"));
    }

    private static d a(String str) {
        d dVar = new d();
        String[] split = str.split(",");
        int parseInt = Integer.parseInt(split[0]);
        int parseInt2 = Integer.parseInt(split[1]);
        int parseInt3 = Integer.parseInt(split[2]);
        int parseInt4 = Integer.parseInt(split[3]);
        dVar.a(parseInt);
        dVar.b(parseInt2);
        dVar.c(parseInt3);
        dVar.d(parseInt4);
        return dVar;
    }
}
