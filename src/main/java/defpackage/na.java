package defpackage;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;
import com.luck.picture.lib.R;
import com.luck.picture.lib.config.a;

/* compiled from: StringUtils */
/* renamed from: na */
public class na {
    public static boolean a(String str) {
        return (!TextUtils.isEmpty(str) && str.startsWith("相机胶卷")) || str.startsWith("CameraRoll") || str.startsWith("所有音频") || str.startsWith("All audio");
    }

    public static void a(TextView textView, int i) {
        String string;
        String trim = textView.getText().toString().trim();
        if (i == a.d()) {
            string = textView.getContext().getString(R.string.picture_empty_audio_title);
        } else {
            string = textView.getContext().getString(R.string.picture_empty_title);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(trim);
        trim = stringBuilder.toString();
        SpannableString spannableString = new SpannableString(trim);
        spannableString.setSpan(new RelativeSizeSpan(0.8f), string.length(), trim.length(), 33);
        textView.setText(spannableString);
    }

    public static void a(TextView textView, Drawable drawable, int i) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (i == 0) {
            textView.setCompoundDrawables(drawable, null, null, null);
        } else if (i == 1) {
            textView.setCompoundDrawables(null, drawable, null, null);
        } else if (i == 2) {
            textView.setCompoundDrawables(null, null, drawable, null);
        } else {
            textView.setCompoundDrawables(null, null, null, drawable);
        }
    }
}
