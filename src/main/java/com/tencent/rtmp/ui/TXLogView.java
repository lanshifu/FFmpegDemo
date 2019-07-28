package com.tencent.rtmp.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.tencent.rtmp.TXLiveBase;
import com.tencent.rtmp.TXLiveConstants;
import java.text.SimpleDateFormat;
import org.slf4j.Marker;

public class TXLogView extends LinearLayout {
    StringBuffer a;
    private TextView b;
    private TextView c;
    private ScrollView d;
    private ScrollView e;
    private final int f;
    private boolean g;

    public TXLogView(Context context) {
        this(context, null);
    }

    public TXLogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new StringBuffer("");
        this.f = 3000;
        this.g = false;
        setOrientation(1);
        this.b = new TextView(context);
        this.c = new TextView(context);
        this.d = new ScrollView(context);
        this.e = new ScrollView(context);
        LayoutParams layoutParams = new LayoutParams(-1, 0);
        layoutParams.weight = 0.2f;
        this.d.setLayoutParams(layoutParams);
        this.d.setBackgroundColor(1627389951);
        this.d.setVerticalScrollBarEnabled(true);
        this.d.setScrollbarFadingEnabled(true);
        this.b.setLayoutParams(new LayoutParams(-1, -1));
        this.b.setTextSize(2, 11.0f);
        this.b.setTextColor(-16777216);
        this.b.setTypeface(Typeface.MONOSPACE, 1);
        this.b.setLineSpacing(4.0f, 1.0f);
        this.b.setPadding(a(context, 2.0f), a(context, 2.0f), a(context, 2.0f), a(context, 2.0f));
        this.d.addView(this.b);
        layoutParams = new LayoutParams(-1, 0);
        layoutParams.weight = 0.8f;
        layoutParams.topMargin = a(context, 2.0f);
        this.e.setLayoutParams(layoutParams);
        this.e.setBackgroundColor(1627389951);
        this.e.setVerticalScrollBarEnabled(true);
        this.e.setScrollbarFadingEnabled(true);
        this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.c.setTextSize(2, 13.0f);
        this.c.setTextColor(-16777216);
        this.c.setPadding(a(context, 2.0f), a(context, 2.0f), a(context, 2.0f), a(context, 2.0f));
        this.e.addView(this.c);
        addView(this.d);
        addView(this.e);
        setVisibility(8);
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void a(Bundle bundle, Bundle bundle2, int i) {
        if (!this.g && i != TXLiveConstants.PLAY_EVT_CHANGE_ROTATION && i != TXLiveConstants.PLAY_EVT_GET_MESSAGE) {
            if (bundle != null && getVisibility() == 0) {
                this.b.setText(a(bundle));
            }
            if (this.a.length() <= 0) {
                StringBuffer stringBuffer = this.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("liteav sdk version:");
                stringBuilder.append(TXLiveBase.getSDKVersionStr());
                stringBuffer.append(stringBuilder.toString());
            }
            if (bundle2 != null) {
                String string = bundle2.getString(TXLiveConstants.EVT_DESCRIPTION);
                if (!(string == null || string.isEmpty())) {
                    a(i, string);
                    if (getVisibility() == 0) {
                        this.c.setText(this.a.toString());
                        a(this.e, this.c);
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, String str) {
        if (i != TXLiveConstants.PUSH_EVT_ROOM_USERLIST) {
            String format = new SimpleDateFormat("HH:mm:ss.SSS").format(Long.valueOf(System.currentTimeMillis()));
            while (this.a.length() > 3000) {
                int indexOf = this.a.indexOf("\n");
                if (indexOf == 0) {
                    indexOf = 1;
                }
                this.a = this.a.delete(0, indexOf);
            }
            StringBuffer stringBuffer = this.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n[");
            stringBuilder.append(format);
            stringBuilder.append("]");
            stringBuilder.append(str);
            stringBuffer.append(stringBuilder.toString());
            this.a = stringBuffer;
        }
    }

    /* Access modifiers changed, original: protected */
    public String a(Bundle bundle) {
        Object[] objArr = new Object[12];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CPU:");
        stringBuilder.append(bundle.getString(TXLiveConstants.NET_STATUS_CPU_USAGE));
        objArr[0] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("RES:");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_WIDTH));
        stringBuilder.append(Marker.ANY_MARKER);
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_HEIGHT));
        objArr[1] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("SPD:");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_NET_SPEED));
        stringBuilder.append("Kbps");
        objArr[2] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("JIT:");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_NET_JITTER));
        objArr[3] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("FPS:");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_FPS));
        objArr[4] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("GOP:");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_GOP));
        stringBuilder.append("s");
        objArr[5] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("ARA:");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_AUDIO_BITRATE));
        stringBuilder.append("Kbps");
        objArr[6] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("QUE:");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_CODEC_CACHE));
        stringBuilder.append(" | ");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_CACHE_SIZE));
        stringBuilder.append(",");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_CACHE_SIZE));
        stringBuilder.append(",");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_V_DEC_CACHE_SIZE));
        stringBuilder.append(" | ");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_AV_RECV_INTERVAL));
        stringBuilder.append(",");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_AV_PLAY_INTERVAL));
        stringBuilder.append(",");
        stringBuilder.append(String.format("%.1f", new Object[]{Float.valueOf(bundle.getFloat(TXLiveConstants.NET_STATUS_AUDIO_PLAY_SPEED))}).toString());
        objArr[7] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("VRA:");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_BITRATE));
        stringBuilder.append("Kbps");
        objArr[8] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("DRP:");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_CODEC_DROP_CNT));
        stringBuilder.append("|");
        stringBuilder.append(bundle.getInt(TXLiveConstants.NET_STATUS_DROP_SIZE));
        objArr[9] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("SVR:");
        stringBuilder.append(bundle.getString(TXLiveConstants.NET_STATUS_SERVER_IP));
        objArr[10] = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("AUDIO:");
        stringBuilder.append(bundle.getString(TXLiveConstants.NET_STATUS_AUDIO_INFO));
        objArr[11] = stringBuilder.toString();
        return String.format("%-16s %-16s %-16s\n%-12s %-12s %-12s %-12s\n%-14s %-14s %-14s\n%-16s %-16s", objArr);
    }

    public void a() {
        this.a.setLength(0);
        this.b.setText("");
        this.c.setText("");
    }

    public void a(boolean z) {
        setVisibility(z ? 0 : 8);
        if (z) {
            this.c.setText(this.a.toString());
            a(this.e, this.c);
            return;
        }
        this.c.setText("");
    }

    public void b(boolean z) {
        this.g = z;
    }

    private void a(ScrollView scrollView, View view) {
        if (scrollView != null && view != null) {
            int measuredHeight = view.getMeasuredHeight() - scrollView.getMeasuredHeight();
            if (measuredHeight < 0) {
                measuredHeight = 0;
            }
            scrollView.scrollTo(0, measuredHeight);
        }
    }
}
