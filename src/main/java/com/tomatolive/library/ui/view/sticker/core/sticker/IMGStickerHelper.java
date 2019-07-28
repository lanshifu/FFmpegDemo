package com.tomatolive.library.ui.view.sticker.core.sticker;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGStickerPortrait.Callback;
import com.yalantis.ucrop.view.CropImageView;

public class IMGStickerHelper<StickerView extends View & IMGSticker> implements IMGStickerPortrait, Callback {
    private boolean isShowing = false;
    private Callback mCallback;
    private RectF mFrame;
    private StickerView mView;

    public void onSticker(Canvas canvas) {
    }

    public IMGStickerHelper(StickerView stickerView) {
        this.mView = stickerView;
    }

    public boolean show() {
        if (isShowing()) {
            return false;
        }
        this.isShowing = true;
        onShowing(this.mView);
        return true;
    }

    public boolean remove() {
        return onRemove(this.mView);
    }

    public boolean dismiss() {
        if (!isShowing()) {
            return false;
        }
        this.isShowing = false;
        onDismiss(this.mView);
        return true;
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    public RectF getFrame() {
        if (this.mFrame == null) {
            this.mFrame = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mView.getWidth(), (float) this.mView.getHeight());
            float x = this.mView.getX() + this.mView.getPivotX();
            float y = this.mView.getY() + this.mView.getPivotY();
            Matrix matrix = new Matrix();
            matrix.setTranslate(this.mView.getX(), this.mView.getY());
            matrix.postScale(this.mView.getScaleX(), this.mView.getScaleY(), x, y);
            matrix.mapRect(this.mFrame);
        }
        return this.mFrame;
    }

    public void registerCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void unregisterCallback(Callback callback) {
        this.mCallback = null;
    }

    public <V extends View & IMGSticker> boolean onRemove(V v) {
        return this.mCallback != null && this.mCallback.onRemove(v);
    }

    public <V extends View & IMGSticker> void onDismiss(V v) {
        this.mFrame = null;
        v.invalidate();
        if (this.mCallback != null) {
            this.mCallback.onDismiss(v);
        }
    }

    public <V extends View & IMGSticker> void onShowing(V v) {
        v.invalidate();
        if (this.mCallback != null) {
            this.mCallback.onShowing(v);
        }
    }
}
