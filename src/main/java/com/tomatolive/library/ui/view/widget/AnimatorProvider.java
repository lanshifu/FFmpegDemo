package com.tomatolive.library.ui.view.widget;

import android.animation.Animator;
import android.view.View;

public interface AnimatorProvider {
    Animator hideAnimation(View view);

    Animator showAnimation(View view);
}
