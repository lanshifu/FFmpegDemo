package com.tomatolive.library.ui.view.sticker.view;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.TypedValue;
import com.tomatolive.library.ui.view.sticker.core.IMGText;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGStickerX;
import com.yalantis.ucrop.view.CropImageView;

public class IMGStickerXText extends IMGStickerX {
    private IMGText mText;
    private StaticLayout mTextLayout;
    private TextPaint mTextPaint = new TextPaint(1);

    public IMGStickerXText(IMGText iMGText) {
        this.mTextPaint.setTextSize(TypedValue.applyDimension(2, 22.0f, Resources.getSystem().getDisplayMetrics()));
        setText(iMGText);
    }

    public void setText(IMGText iMGText) {
        this.mText = iMGText;
        this.mTextPaint.setColor(iMGText.getColor());
        this.mTextLayout = new StaticLayout(iMGText.getText(), this.mTextPaint, Math.round(((float) Resources.getSystem().getDisplayMetrics().widthPixels) * 0.8f), Alignment.ALIGN_NORMAL, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, false);
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        for (int i = 0; i < this.mTextLayout.getLineCount(); i++) {
            f = Math.max(f, this.mTextLayout.getLineWidth(i));
        }
        onMeasure(f, (float) this.mTextLayout.getHeight());
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(this.mFrame.left, this.mFrame.top);
        this.mTextLayout.draw(canvas);
        canvas.restore();
    }
}
