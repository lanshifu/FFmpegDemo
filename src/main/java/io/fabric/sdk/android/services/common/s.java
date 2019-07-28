package io.fabric.sdk.android.services.common;

import com.yalantis.ucrop.view.CropImageView;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* compiled from: ResponseParser */
public class s {
    public static int a(int i) {
        return (i < 200 || i > 299) ? (i < IjkMediaCodecInfo.RANK_SECURE || i > 399) ? (i < 400 || i > 499) ? i >= CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION ? 1 : 1 : 0 : 1 : 0;
    }
}
