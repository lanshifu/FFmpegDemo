package io.fabric.sdk.android.services.settings;

import com.tencent.ugc.TXRecordCommon;
import io.fabric.sdk.android.services.common.j;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

/* compiled from: DefaultSettingsJsonTransform */
class k implements u {
    k() {
    }

    public s a(j jVar, JSONObject jSONObject) throws JSONException {
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", 3600);
        return new s(a(jVar, (long) optInt2, jSONObject), a(jSONObject.getJSONObject("app")), e(jSONObject.getJSONObject("session")), f(jSONObject.getJSONObject("prompt")), c(jSONObject.getJSONObject("features")), d(jSONObject.getJSONObject("analytics")), g(jSONObject.getJSONObject("beta")), optInt, optInt2);
    }

    private e a(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("identifier");
        String string2 = jSONObject.getString("status");
        String string3 = jSONObject.getString(OnNativeInvokeListener.ARG_URL);
        String string4 = jSONObject.getString("reports_url");
        String string5 = jSONObject.getString("ndk_reports_url");
        boolean optBoolean = jSONObject.optBoolean("update_required", false);
        c b = (jSONObject.has("icon") && jSONObject.getJSONObject("icon").has("hash")) ? b(jSONObject.getJSONObject("icon")) : null;
        return new e(string, string2, string3, string4, string5, optBoolean, b);
    }

    private c b(JSONObject jSONObject) throws JSONException {
        return new c(jSONObject.getString("hash"), jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    private m c(JSONObject jSONObject) {
        return new m(jSONObject.optBoolean("prompt_enabled", false), jSONObject.optBoolean("collect_logged_exceptions", true), jSONObject.optBoolean("collect_reports", true), jSONObject.optBoolean("collect_analytics", false), jSONObject.optBoolean("firebase_crashlytics_enabled", false));
    }

    private b d(JSONObject jSONObject) {
        return new b(jSONObject.optString(OnNativeInvokeListener.ARG_URL, "https://e.crashlytics.com/spi/v2/events"), jSONObject.optInt("flush_interval_secs", IjkMediaCodecInfo.RANK_LAST_CHANCE), jSONObject.optInt("max_byte_size_per_file", TXRecordCommon.AUDIO_SAMPLERATE_8000), jSONObject.optInt("max_file_count_per_send", 1), jSONObject.optInt("max_pending_send_file_count", 100), jSONObject.optBoolean("forward_to_google_analytics", false), jSONObject.optBoolean("include_purchase_events_in_forwarded_events", false), jSONObject.optBoolean("track_custom_events", true), jSONObject.optBoolean("track_predefined_events", true), jSONObject.optInt("sampling_rate", 1), jSONObject.optBoolean("flush_on_background", true));
    }

    private p e(JSONObject jSONObject) throws JSONException {
        return new p(jSONObject.optInt("log_buffer_size", 64000), jSONObject.optInt("max_chained_exception_depth", 8), jSONObject.optInt("max_custom_exception_events", 64), jSONObject.optInt("max_custom_key_value_pairs", 64), jSONObject.optInt("identifier_mask", 255), jSONObject.optBoolean("send_session_without_crash", false), jSONObject.optInt("max_complete_sessions_count", 4));
    }

    private o f(JSONObject jSONObject) throws JSONException {
        return new o(jSONObject.optString("title", "Send Crash Report?"), jSONObject.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject.optString("send_button_title", "Send"), jSONObject.optBoolean("show_cancel_button", true), jSONObject.optString("cancel_button_title", "Don't Send"), jSONObject.optBoolean("show_always_send_button", true), jSONObject.optString("always_send_button_title", "Always Send"));
    }

    private f g(JSONObject jSONObject) throws JSONException {
        return new f(jSONObject.optString("update_endpoint", t.a), jSONObject.optInt("update_suspend_duration", 3600));
    }

    private long a(j jVar, long j, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("expires_at")) {
            return jSONObject.getLong("expires_at");
        }
        return (j * 1000) + jVar.a();
    }
}
