package com.tomatolive.library.ui.view.widget.heard.animation;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import com.tomatolive.library.R;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class RxAbstractPathAnimator {
    protected final Config mConfig;
    private final Random mRandom = new Random();

    public static class Config {
        public int animDuration;
        public int animLength;
        public int animLengthRand;
        public int bezierFactor;
        public int heartHeight;
        public int heartWidth;
        public int initX;
        public int initY;
        public int xPointFactor;
        public int xRand;

        public static Config fromTypeArray(TypedArray typedArray) {
            Config config = new Config();
            Resources resources = typedArray.getResources();
            config.initX = (int) typedArray.getDimension(R.styleable.RxHeartLayout_initX, (float) resources.getDimensionPixelOffset(R.dimen.heart_anim_init_x));
            config.initY = (int) typedArray.getDimension(R.styleable.RxHeartLayout_initY, (float) resources.getDimensionPixelOffset(R.dimen.heart_anim_init_y));
            config.xRand = (int) typedArray.getDimension(R.styleable.RxHeartLayout_xRand, (float) resources.getDimensionPixelOffset(R.dimen.heart_anim_bezier_x_rand));
            config.animLength = (int) typedArray.getDimension(R.styleable.RxHeartLayout_animLength, (float) resources.getDimensionPixelOffset(R.dimen.heart_anim_length));
            config.animLengthRand = (int) typedArray.getDimension(R.styleable.RxHeartLayout_animLengthRand, (float) resources.getDimensionPixelOffset(R.dimen.heart_anim_length_rand));
            config.bezierFactor = typedArray.getInteger(R.styleable.RxHeartLayout_bezierFactor, 6);
            config.xPointFactor = (int) typedArray.getDimension(R.styleable.RxHeartLayout_xPointFactor, (float) resources.getDimensionPixelOffset(R.dimen.heart_anim_x_point_factor));
            config.heartWidth = (int) typedArray.getDimension(R.styleable.RxHeartLayout_heart_width, (float) resources.getDimensionPixelOffset(R.dimen.heart_size_width));
            config.heartHeight = (int) typedArray.getDimension(R.styleable.RxHeartLayout_heart_height, (float) resources.getDimensionPixelOffset(R.dimen.heart_size_height));
            config.animDuration = typedArray.getInteger(R.styleable.RxHeartLayout_anim_duration, 3000);
            return config;
        }
    }

    public abstract void start(View view, ViewGroup viewGroup);

    public RxAbstractPathAnimator(Config config) {
        this.mConfig = config;
    }

    public float randomRotation() {
        return (this.mRandom.nextFloat() * 28.6f) - 14.3f;
    }

    public Path createPath(AtomicInteger atomicInteger, View view, int i) {
        Random random = this.mRandom;
        int nextInt = random.nextInt(this.mConfig.xRand);
        int nextInt2 = random.nextInt(this.mConfig.xRand);
        int height = view.getHeight() - this.mConfig.initY;
        int intValue = ((atomicInteger.intValue() * 15) + (this.mConfig.animLength * i)) + random.nextInt(this.mConfig.animLengthRand);
        i = intValue / this.mConfig.bezierFactor;
        int i2 = this.mConfig.xPointFactor + nextInt;
        nextInt = this.mConfig.xPointFactor + nextInt2;
        nextInt2 = height - intValue;
        intValue = height - (intValue / 2);
        Path path = new Path();
        path.moveTo((float) this.mConfig.initX, (float) height);
        float f = (float) (height - i);
        float f2 = (float) i2;
        float f3 = (float) intValue;
        Path path2 = path;
        path2.cubicTo((float) this.mConfig.initX, f, f2, (float) (intValue + i), f2, f3);
        path.moveTo(f2, f3);
        float f4 = (float) nextInt;
        path2.cubicTo(f2, (float) (intValue - i), f4, (float) (i + nextInt2), f4, (float) nextInt2);
        return path;
    }
}
