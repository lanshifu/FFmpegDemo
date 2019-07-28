package com.tomatolive.library.http.utils;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.tomatolive.library.http.exception.ServerException;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    private class ResultMode {
        private int code;
        private Object data;
        private String msg;

        private ResultMode() {
            this.code = 0;
        }

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public Object getData() {
            return this.data;
        }

        public void setData(Object obj) {
            this.data = obj;
        }
    }

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    public T convert(@NonNull ResponseBody responseBody) throws IOException {
        String string = responseBody.string();
        JsonParser jsonParser = new JsonParser();
        if (isEncrypt(jsonParser, string)) {
            EncryptHttpResultModel encryptHttpResultModel = (EncryptHttpResultModel) this.gson.fromJson(string, EncryptHttpResultModel.class);
            ResultMode resultMode = new ResultMode();
            resultMode.code = encryptHttpResultModel.getCode();
            resultMode.msg = encryptHttpResultModel.getMessage();
            string = encryptHttpResultModel.getJsonData();
            if (jsonParser.parse(string).isJsonArray()) {
                resultMode.data = new JsonParser().parse(string).getAsJsonArray();
            } else {
                resultMode.data = new JsonParser().parse(string).getAsJsonObject();
            }
            string = this.gson.toJson(resultMode);
        }
        try {
            ResultMode resultMode2 = (ResultMode) this.gson.fromJson(string, ResultMode.class);
            if (resultMode2.getCode() != 101001) {
                T fromJson = this.adapter.fromJson(string);
                return fromJson;
            }
            responseBody.close();
            throw new ServerException(resultMode2.getCode(), resultMode2.getMsg());
        } finally {
            responseBody.close();
        }
    }

    private boolean isEncrypt(JsonParser jsonParser, String str) {
        boolean z = false;
        try {
            str = this.gson.toJson(((ResultMode) this.gson.fromJson(str, ResultMode.class)).data);
            if (jsonParser.parse(str).isJsonArray()) {
                return false;
            }
            if (jsonParser.parse(str).getAsJsonObject().size() == 2 && jsonParser.parse(str).getAsJsonObject().has("key") && jsonParser.parse(str).getAsJsonObject().has("data")) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }
}
