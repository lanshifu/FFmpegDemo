package com.tomato.ucrop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* compiled from: UCrop */
public class a {
    private Intent a = new Intent();
    private Bundle b = new Bundle();

    /* compiled from: UCrop */
    public static class a {
        private final Bundle a = new Bundle();

        @NonNull
        public Bundle a() {
            return this.a;
        }

        public void a(@IntRange(from = 0) int i) {
            this.a.putInt("com.one.tomato.ucrop.CompressionQuality", i);
        }

        public void a(boolean z) {
            this.a.putBoolean("com.one.tomato.ucrop.CircleDimmedLayer", z);
        }

        public void b(boolean z) {
            this.a.putBoolean("com.one.tomato.ucrop.ShowCropFrame", z);
        }

        public void c(boolean z) {
            this.a.putBoolean("com.one.tomato.ucrop.ShowCropGrid", z);
        }

        public void d(boolean z) {
            this.a.putBoolean("com.one.tomato.ucrop.DragCropFrame", z);
        }

        public void e(boolean z) {
            this.a.putBoolean("com.one.tomato.ucrop.scale", z);
        }

        public void f(boolean z) {
            this.a.putBoolean("com.one.tomato.ucrop.rotate", z);
        }

        public void b(@ColorInt int i) {
            this.a.putInt("com.one.tomato.ucrop.ToolbarColor", i);
        }

        public void c(@ColorInt int i) {
            this.a.putInt("com.one.tomato.ucrop.StatusBarColor", i);
        }

        public void d(@ColorInt int i) {
            this.a.putInt("com.one.tomato.ucrop.UcropToolbarWidgetColor", i);
        }

        public void g(boolean z) {
            this.a.putBoolean("com.one.tomato.ucrop.HideBottomControls", z);
        }

        public void h(boolean z) {
            this.a.putBoolean("com.one.tomato.ucrop.FreeStyleCrop", z);
        }
    }

    public static a a(@NonNull Uri uri, @NonNull Uri uri2) {
        return new a(uri, uri2);
    }

    private a(@NonNull Uri uri, @NonNull Uri uri2) {
        this.b.putParcelable("com.one.tomato.ucrop.InputUri", uri);
        this.b.putParcelable("com.one.tomato.ucrop.OutputUri", uri2);
    }

    public a a(float f, float f2) {
        this.b.putFloat("com.one.tomato.ucrop.AspectRatioX", f);
        this.b.putFloat("com.one.tomato.ucrop.AspectRatioY", f2);
        return this;
    }

    public a a(@IntRange(from = 100) int i, @IntRange(from = 100) int i2) {
        this.b.putInt("com.one.tomato.ucrop.MaxSizeX", i);
        this.b.putInt("com.one.tomato.ucrop.MaxSizeY", i2);
        return this;
    }

    public a a(@NonNull a aVar) {
        this.b.putAll(aVar.a());
        return this;
    }

    public void a(@NonNull Activity activity) {
        a(activity, 69);
    }

    public void a(@NonNull Activity activity, int i) {
        activity.startActivityForResult(a((Context) activity), i);
    }

    public Intent a(@NonNull Context context) {
        this.a.setClass(context, UCropActivity.class);
        this.a.putExtras(this.b);
        return this.a;
    }

    @Nullable
    public static Uri a(@NonNull Intent intent) {
        return (Uri) intent.getParcelableExtra("com.one.tomato.ucrop.OutputUri");
    }
}
