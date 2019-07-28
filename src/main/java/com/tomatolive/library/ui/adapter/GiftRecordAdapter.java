package com.tomatolive.library.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.ReceiveGiftRecordEntity;
import defpackage.mh;
import defpackage.sh;
import java.util.concurrent.TimeUnit;

public class GiftRecordAdapter extends BaseQuickAdapter<ReceiveGiftRecordEntity, BaseViewHolder> {
    private OnGiftRecordListener listener;

    public interface OnGiftRecordListener {
        void onItemClick(ReceiveGiftRecordEntity receiveGiftRecordEntity);
    }

    public GiftRecordAdapter(int i) {
        super(i);
    }

    public void setOnGiftRecordListener(OnGiftRecordListener onGiftRecordListener) {
        this.listener = onGiftRecordListener;
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, final ReceiveGiftRecordEntity receiveGiftRecordEntity) {
        String string = this.mContext.getString(R.string.fq_gift_desc, new Object[]{receiveGiftRecordEntity.userName, receiveGiftRecordEntity.giftName, receiveGiftRecordEntity.giftNum});
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_colorRed)), 0, receiveGiftRecordEntity.userName.length(), 33);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_colorWhite)), receiveGiftRecordEntity.userName.length() + 1, string.length(), 33);
        baseViewHolder.setText(R.id.tv_gift_time, receiveGiftRecordEntity.createTime).setText(R.id.tv_gift_desc, spannableString);
        mh.a(baseViewHolder.itemView).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new sh<Object>() {
            public void accept(Object obj) {
                if (GiftRecordAdapter.this.listener != null) {
                    GiftRecordAdapter.this.listener.onItemClick(receiveGiftRecordEntity);
                }
            }
        });
    }
}
