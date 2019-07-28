package com.tomatolive.library.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.LabelEntity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchKeywordAdapter extends BaseQuickAdapter<LabelEntity, BaseViewHolder> {
    private String keyWord;

    public SearchKeywordAdapter(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, LabelEntity labelEntity) {
        baseViewHolder.setText(R.id.tv_keyword, getTextHighLightByMatcher(getKeyWord(), labelEntity.keyword, false));
    }

    private String getKeyWord() {
        return this.keyWord;
    }

    public void setKeyWord(String str) {
        this.keyWord = str;
    }

    private SpannableString getTextHighLightByMatcher(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return new SpannableString(" ");
        }
        CharSequence str22;
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ID：");
            stringBuilder.append(str22);
            str22 = stringBuilder.toString();
        }
        SpannableString spannableString = new SpannableString(str22);
        if (TextUtils.isEmpty(str)) {
            return spannableString;
        }
        for (int i = 0; i < str.length(); i++) {
            Matcher matcher = Pattern.compile(String.valueOf(str.charAt(i)), 2).matcher(spannableString);
            while (matcher.find()) {
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_colorRed)), matcher.start(), matcher.end(), 33);
            }
        }
        return spannableString;
    }
}
