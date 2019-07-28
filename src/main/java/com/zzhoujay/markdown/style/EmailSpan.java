package com.zzhoujay.markdown.style;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;

@SuppressLint({"ParcelCreator"})
public class EmailSpan extends URLSpan {
    private int a;

    public EmailSpan(String str, int i) {
        super(str);
        this.a = i;
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setColor(this.a);
        textPaint.setUnderlineText(false);
    }

    public void onClick(View view) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mailto:");
        stringBuilder.append(getURL());
        intent.setData(Uri.parse(stringBuilder.toString()));
        intent.putExtra("android.intent.extra.SUBJECT", "");
        intent.putExtra("android.intent.extra.TEXT", "");
        try {
            view.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Actvity was not found for intent, ");
            stringBuilder.append(intent.toString());
            Log.w("URLSpan", stringBuilder.toString());
        }
    }
}
