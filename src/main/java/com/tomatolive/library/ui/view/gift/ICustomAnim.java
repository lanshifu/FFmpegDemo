package com.tomatolive.library.ui.view.gift;

import android.animation.AnimatorSet;
import android.view.View;

public interface ICustomAnim {
    AnimatorSet comboAnim(GiftFrameLayout giftFrameLayout, View view, boolean z);

    AnimatorSet endAnim(GiftFrameLayout giftFrameLayout, View view);

    AnimatorSet startAnim(GiftFrameLayout giftFrameLayout, View view);
}
