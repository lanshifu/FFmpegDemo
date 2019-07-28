package com.tomato.ucrop;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.one.tomato.ucrop.R;
import com.tomato.ucrop.model.AspectRatio;
import com.tomato.ucrop.view.GestureCropImageView;
import com.tomato.ucrop.view.OverlayView;
import com.tomato.ucrop.view.TransformImageView.a;
import com.tomato.ucrop.view.UCropView;
import com.tomato.ucrop.view.widget.AspectRatioTextView;
import com.tomato.ucrop.view.widget.HorizontalProgressWheelView;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.ro;
import defpackage.sc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class UCropActivity extends AppCompatActivity {
    public static final CompressFormat a = CompressFormat.PNG;
    private int A = 100;
    private int[] B = new int[]{1, 2, 3};
    private boolean C;
    private boolean D;
    private boolean E;
    private a F = new a() {
        public void a(float f) {
            UCropActivity.this.a(f);
        }

        public void b(float f) {
            UCropActivity.this.b(f);
        }

        public void a() {
            UCropActivity.this.m.animate().alpha(1.0f).setDuration(300).setInterpolator(new AccelerateInterpolator());
            UCropActivity.this.y.setClickable(false);
            UCropActivity.this.l = false;
            UCropActivity.this.supportInvalidateOptionsMenu();
        }

        public void a(@NonNull Exception exception) {
            UCropActivity.this.a((Throwable) exception);
            UCropActivity.this.b();
        }
    };
    private final OnClickListener G = new OnClickListener() {
        public void onClick(View view) {
            if (!view.isSelected()) {
                UCropActivity.this.c(view.getId());
            }
        }
    };
    private String b;
    private int c;
    private int d;
    private int e;
    private int f;
    @ColorInt
    private int g;
    @DrawableRes
    private int h;
    @DrawableRes
    private int i;
    private int j;
    private boolean k;
    private boolean l = true;
    private UCropView m;
    private GestureCropImageView n;
    private OverlayView o;
    private ViewGroup p;
    private ViewGroup q;
    private ViewGroup r;
    private ViewGroup s;
    private ViewGroup t;
    private ViewGroup u;
    private List<ViewGroup> v = new ArrayList();
    private TextView w;
    private TextView x;
    private View y;
    private CompressFormat z = a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tomato_ucrop_activity_photobox);
        Intent intent = getIntent();
        c(intent);
        a(intent);
        i();
        j();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ucrop_menu_activity, menu);
        MenuItem findItem = menu.findItem(R.id.menu_loader);
        Drawable icon = findItem.getIcon();
        if (icon != null) {
            try {
                icon.mutate();
                icon.setColorFilter(this.f, Mode.SRC_ATOP);
                findItem.setIcon(icon);
            } catch (IllegalStateException e) {
                Log.i("UCropActivity", String.format("%s - %s", new Object[]{e.getMessage(), getString(R.string.ucrop_mutate_exception_hint)}));
            }
            ((Animatable) findItem.getIcon()).start();
        }
        MenuItem findItem2 = menu.findItem(R.id.menu_crop);
        Drawable drawable = ContextCompat.getDrawable(this, this.i);
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(this.f, Mode.SRC_ATOP);
            findItem2.setIcon(drawable);
        }
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_crop).setVisible(this.l ^ 1);
        menu.findItem(R.id.menu_loader).setVisible(this.l);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_crop) {
            a();
        } else if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        if (this.n != null) {
            this.n.a();
        }
    }

    private void a(@NonNull Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra("com.one.tomato.ucrop.InputUri");
        Uri uri2 = (Uri) intent.getParcelableExtra("com.one.tomato.ucrop.OutputUri");
        b(intent);
        if (uri == null || uri2 == null) {
            a(new NullPointerException(getString(R.string.ucrop_error_input_data_is_absent)));
            b();
            return;
        }
        try {
            this.n.setRotateEnabled(this.E);
            this.n.setScaleEnabled(this.D);
            this.n.setImageUri(uri, uri2);
        } catch (Exception e) {
            a(e);
            b();
        }
    }

    private void b(@NonNull Intent intent) {
        String stringExtra = intent.getStringExtra("com.one.tomato.ucrop.CompressionFormatName");
        CompressFormat valueOf = !TextUtils.isEmpty(stringExtra) ? CompressFormat.valueOf(stringExtra) : null;
        if (valueOf == null) {
            valueOf = a;
        }
        this.z = valueOf;
        this.A = intent.getIntExtra("com.one.tomato.ucrop.CompressionQuality", 100);
        int[] intArrayExtra = intent.getIntArrayExtra("com.one.tomato.ucrop.AllowedGestures");
        if (intArrayExtra != null && intArrayExtra.length == 3) {
            this.B = intArrayExtra;
        }
        this.n.setMaxBitmapSize(intent.getIntExtra("com.one.tomato.ucrop.MaxBitmapSize", 0));
        this.n.setMaxScaleMultiplier(intent.getFloatExtra("com.one.tomato.ucrop.MaxScaleMultiplier", 10.0f));
        this.n.setImageToWrapCropBoundsAnimDuration((long) intent.getIntExtra("com.one.tomato.ucrop.ImageToCropBoundsAnimDuration", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION));
        this.o.setFreestyleCropEnabled(intent.getBooleanExtra("com.one.tomato.ucrop.FreeStyleCrop", false));
        this.o.setDragFrame(this.C);
        this.o.setDimmedColor(intent.getIntExtra("com.one.tomato.ucrop.DimmedLayerColor", getResources().getColor(R.color.ucrop_color_default_dimmed)));
        this.o.setCircleDimmedLayer(intent.getBooleanExtra("com.one.tomato.ucrop.CircleDimmedLayer", false));
        this.o.setShowCropFrame(intent.getBooleanExtra("com.one.tomato.ucrop.ShowCropFrame", true));
        this.o.setCropFrameColor(intent.getIntExtra("com.one.tomato.ucrop.CropFrameColor", getResources().getColor(R.color.ucrop_color_default_crop_frame)));
        this.o.setCropFrameStrokeWidth(intent.getIntExtra("com.one.tomato.ucrop.CropFrameStrokeWidth", getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_frame_stoke_width)));
        this.o.setShowCropGrid(intent.getBooleanExtra("com.one.tomato.ucrop.ShowCropGrid", true));
        this.o.setCropGridRowCount(intent.getIntExtra("com.one.tomato.ucrop.CropGridRowCount", 2));
        this.o.setCropGridColumnCount(intent.getIntExtra("com.one.tomato.ucrop.CropGridColumnCount", 2));
        this.o.setCropGridColor(intent.getIntExtra("com.one.tomato.ucrop.CropGridColor", getResources().getColor(R.color.ucrop_color_default_crop_grid)));
        this.o.setCropGridStrokeWidth(intent.getIntExtra("com.one.tomato.ucrop.CropGridStrokeWidth", getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_grid_stoke_width)));
        float floatExtra = intent.getFloatExtra("com.one.tomato.ucrop.AspectRatioX", CropImageView.DEFAULT_ASPECT_RATIO);
        float floatExtra2 = intent.getFloatExtra("com.one.tomato.ucrop.AspectRatioY", CropImageView.DEFAULT_ASPECT_RATIO);
        int intExtra = intent.getIntExtra("com.one.tomato.ucrop.AspectRatioSelectedByDefault", 0);
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("com.one.tomato.ucrop.AspectRatioOptions");
        if (floatExtra > CropImageView.DEFAULT_ASPECT_RATIO && floatExtra2 > CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.p != null) {
                this.p.setVisibility(8);
            }
            this.n.setTargetAspectRatio(floatExtra / floatExtra2);
        } else if (parcelableArrayListExtra == null || intExtra >= parcelableArrayListExtra.size()) {
            this.n.setTargetAspectRatio(CropImageView.DEFAULT_ASPECT_RATIO);
        } else {
            this.n.setTargetAspectRatio(((AspectRatio) parcelableArrayListExtra.get(intExtra)).b() / ((AspectRatio) parcelableArrayListExtra.get(intExtra)).c());
        }
        int intExtra2 = intent.getIntExtra("com.one.tomato.ucrop.MaxSizeX", 0);
        int intExtra3 = intent.getIntExtra("com.one.tomato.ucrop.MaxSizeY", 0);
        if (intExtra2 > 0 && intExtra3 > 0) {
            this.n.setMaxResultImageSizeX(intExtra2);
            this.n.setMaxResultImageSizeY(intExtra3);
        }
    }

    private void c(@NonNull Intent intent) {
        this.D = intent.getBooleanExtra("com.one.tomato.ucrop.scale", true);
        this.E = intent.getBooleanExtra("com.one.tomato.ucrop.rotate", true);
        this.C = intent.getBooleanExtra("com.one.tomato.ucrop.DragCropFrame", true);
        this.d = intent.getIntExtra("com.one.tomato.ucrop.StatusBarColor", ContextCompat.getColor(this, R.color.ucrop_color_statusbar));
        this.c = intent.getIntExtra("com.one.tomato.ucrop.ToolbarColor", ContextCompat.getColor(this, R.color.ucrop_color_toolbar));
        if (this.c == -1) {
            this.c = ContextCompat.getColor(this, R.color.ucrop_color_toolbar);
        }
        if (this.d == -1) {
            this.d = ContextCompat.getColor(this, R.color.ucrop_color_statusbar);
        }
        this.e = intent.getIntExtra("com.one.tomato.ucrop.UcropColorWidgetActive", ContextCompat.getColor(this, R.color.ucrop_color_widget_active));
        this.f = intent.getIntExtra("com.one.tomato.ucrop.UcropToolbarWidgetColor", ContextCompat.getColor(this, R.color.ucrop_color_toolbar_widget));
        if (this.f == -1) {
            this.f = ContextCompat.getColor(this, R.color.ucrop_color_toolbar_widget);
        }
        this.h = intent.getIntExtra("com.one.tomato.ucrop.UcropToolbarCancelDrawable", R.drawable.ucrop_ic_cross);
        this.i = intent.getIntExtra("com.one.tomato.ucrop.UcropToolbarCropDrawable", R.drawable.ucrop_ic_done);
        this.b = intent.getStringExtra("com.one.tomato.ucrop.UcropToolbarTitleText");
        this.b = this.b != null ? this.b : getResources().getString(R.string.ucrop_label_edit_photo);
        this.j = intent.getIntExtra("com.one.tomato.ucrop.UcropLogoColor", ContextCompat.getColor(this, R.color.ucrop_color_default_logo));
        this.k = intent.getBooleanExtra("com.one.tomato.ucrop.HideBottomControls", false) ^ 1;
        this.g = intent.getIntExtra("com.one.tomato.ucrop.UcropRootViewBackgroundColor", ContextCompat.getColor(this, R.color.ucrop_color_crop_background));
        c();
        d();
        if (this.k) {
            View.inflate(this, R.layout.tomato_ucrop_controls, (ViewGroup) findViewById(R.id.ucrop_photobox));
            this.p = (ViewGroup) findViewById(R.id.state_aspect_ratio);
            this.p.setOnClickListener(this.G);
            this.q = (ViewGroup) findViewById(R.id.state_rotate);
            this.q.setOnClickListener(this.G);
            this.r = (ViewGroup) findViewById(R.id.state_scale);
            this.r.setOnClickListener(this.G);
            this.s = (ViewGroup) findViewById(R.id.layout_aspect_ratio);
            this.t = (ViewGroup) findViewById(R.id.layout_rotate_wheel);
            this.u = (ViewGroup) findViewById(R.id.layout_scale_wheel);
            d(intent);
            f();
            g();
            e();
        }
    }

    private void c() {
        a(this.d);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(this.c);
        toolbar.setTitleTextColor(this.f);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setTextColor(this.f);
        textView.setText(this.b);
        Drawable mutate = ContextCompat.getDrawable(this, this.h).mutate();
        mutate.setColorFilter(this.f, Mode.SRC_ATOP);
        toolbar.setNavigationIcon(mutate);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void d() {
        this.m = (UCropView) findViewById(R.id.ucrop);
        this.n = this.m.getCropImageView();
        this.o = this.m.getOverlayView();
        this.n.setTransformImageListener(this.F);
    }

    private void e() {
        ImageView imageView = (ImageView) findViewById(R.id.image_view_state_scale);
        ImageView imageView2 = (ImageView) findViewById(R.id.image_view_state_rotate);
        ImageView imageView3 = (ImageView) findViewById(R.id.image_view_state_aspect_ratio);
        imageView.setImageDrawable(new sc(imageView.getDrawable(), this.e));
        imageView2.setImageDrawable(new sc(imageView2.getDrawable(), this.e));
        imageView3.setImageDrawable(new sc(imageView3.getDrawable(), this.e));
    }

    @TargetApi(21)
    private void a(@ColorInt int i) {
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            if (window != null) {
                window.addFlags(CheckView.UNCHECKED);
                window.setStatusBarColor(i);
            }
        }
    }

    private void d(@NonNull Intent intent) {
        int intExtra = intent.getIntExtra("com.one.tomato.ucrop.AspectRatioSelectedByDefault", 0);
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("com.one.tomato.ucrop.AspectRatioOptions");
        if (parcelableArrayListExtra == null || parcelableArrayListExtra.isEmpty()) {
            intExtra = 2;
            parcelableArrayListExtra = new ArrayList();
            parcelableArrayListExtra.add(new AspectRatio(null, 1.0f, 1.0f));
            parcelableArrayListExtra.add(new AspectRatio(null, 3.0f, 4.0f));
            parcelableArrayListExtra.add(new AspectRatio(getString(R.string.ucrop_label_original).toUpperCase(), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO));
            parcelableArrayListExtra.add(new AspectRatio(null, 3.0f, 2.0f));
            parcelableArrayListExtra.add(new AspectRatio(null, 16.0f, 9.0f));
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_aspect_ratio);
        LayoutParams layoutParams = new LayoutParams(0, -1);
        layoutParams.weight = 1.0f;
        Iterator it = parcelableArrayListExtra.iterator();
        while (it.hasNext()) {
            AspectRatio aspectRatio = (AspectRatio) it.next();
            FrameLayout frameLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.tomato_ucrop_aspect_ratio, null);
            frameLayout.setLayoutParams(layoutParams);
            AspectRatioTextView aspectRatioTextView = (AspectRatioTextView) frameLayout.getChildAt(0);
            aspectRatioTextView.setActiveColor(this.e);
            aspectRatioTextView.setAspectRatio(aspectRatio);
            linearLayout.addView(frameLayout);
            this.v.add(frameLayout);
        }
        ((ViewGroup) this.v.get(intExtra)).setSelected(true);
        for (ViewGroup onClickListener : this.v) {
            onClickListener.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    UCropActivity.this.n.setTargetAspectRatio(((AspectRatioTextView) ((ViewGroup) view).getChildAt(0)).a(view.isSelected()));
                    UCropActivity.this.n.setImageToWrapCropBounds();
                    if (!view.isSelected()) {
                        for (View view2 : UCropActivity.this.v) {
                            view2.setSelected(view2 == view);
                        }
                    }
                }
            });
        }
    }

    private void f() {
        this.w = (TextView) findViewById(R.id.text_view_rotate);
        ((HorizontalProgressWheelView) findViewById(R.id.rotate_scroll_wheel)).setScrollingListener(new HorizontalProgressWheelView.a() {
            public void a(float f, float f2) {
                UCropActivity.this.n.c(f / 42.0f);
            }

            public void a() {
                UCropActivity.this.n.setImageToWrapCropBounds();
            }

            public void b() {
                UCropActivity.this.n.a();
            }
        });
        ((HorizontalProgressWheelView) findViewById(R.id.rotate_scroll_wheel)).setMiddleLineColor(this.e);
        findViewById(R.id.wrapper_reset_rotate).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UCropActivity.this.h();
            }
        });
        findViewById(R.id.wrapper_rotate_by_angle).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UCropActivity.this.b(90);
            }
        });
    }

    private void g() {
        this.x = (TextView) findViewById(R.id.text_view_scale);
        ((HorizontalProgressWheelView) findViewById(R.id.scale_scroll_wheel)).setScrollingListener(new HorizontalProgressWheelView.a() {
            public void a(float f, float f2) {
                if (f > CropImageView.DEFAULT_ASPECT_RATIO) {
                    UCropActivity.this.n.b(UCropActivity.this.n.getCurrentScale() + (f * ((UCropActivity.this.n.getMaxScale() - UCropActivity.this.n.getMinScale()) / 15000.0f)));
                } else {
                    UCropActivity.this.n.a(UCropActivity.this.n.getCurrentScale() + (f * ((UCropActivity.this.n.getMaxScale() - UCropActivity.this.n.getMinScale()) / 15000.0f)));
                }
            }

            public void a() {
                UCropActivity.this.n.setImageToWrapCropBounds();
            }

            public void b() {
                UCropActivity.this.n.a();
            }
        });
        ((HorizontalProgressWheelView) findViewById(R.id.scale_scroll_wheel)).setMiddleLineColor(this.e);
    }

    private void a(float f) {
        if (this.w != null) {
            this.w.setText(String.format(Locale.getDefault(), "%.1f°", new Object[]{Float.valueOf(f)}));
        }
    }

    private void b(float f) {
        if (this.x != null) {
            this.x.setText(String.format(Locale.getDefault(), "%d%%", new Object[]{Integer.valueOf((int) (f * 100.0f))}));
        }
    }

    private void h() {
        this.n.c(-this.n.getCurrentAngle());
        this.n.setImageToWrapCropBounds();
    }

    private void b(int i) {
        this.n.c((float) i);
        this.n.setImageToWrapCropBounds();
    }

    private void i() {
        if (!this.k) {
            d(0);
        } else if (this.p.getVisibility() == 0) {
            c(R.id.state_aspect_ratio);
        } else {
            c(R.id.state_scale);
        }
    }

    private void c(@IdRes int i) {
        if (this.k) {
            this.p.setSelected(i == R.id.state_aspect_ratio);
            this.q.setSelected(i == R.id.state_rotate);
            this.r.setSelected(i == R.id.state_scale);
            int i2 = 8;
            this.s.setVisibility(i == R.id.state_aspect_ratio ? 0 : 8);
            this.t.setVisibility(i == R.id.state_rotate ? 0 : 8);
            ViewGroup viewGroup = this.u;
            if (i == R.id.state_scale) {
                i2 = 0;
            }
            viewGroup.setVisibility(i2);
            if (i == R.id.state_scale) {
                d(0);
            } else if (i == R.id.state_rotate) {
                d(1);
            } else {
                d(2);
            }
        }
    }

    private void d(int i) {
        if (this.k) {
            GestureCropImageView gestureCropImageView = this.n;
            boolean z = false;
            boolean z2 = this.B[i] == 3 || this.B[i] == 1;
            gestureCropImageView.setScaleEnabled(z2);
            gestureCropImageView = this.n;
            if (this.B[i] == 3 || this.B[i] == 2) {
                z = true;
            }
            gestureCropImageView.setRotateEnabled(z);
        }
    }

    private void j() {
        if (this.y == null) {
            this.y = new View(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(3, R.id.toolbar);
            this.y.setLayoutParams(layoutParams);
            this.y.setClickable(true);
        }
        ((RelativeLayout) findViewById(R.id.ucrop_photobox)).addView(this.y);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        this.y.setClickable(true);
        this.l = true;
        supportInvalidateOptionsMenu();
        this.n.a(this.z, this.A, (ro) new ro() {
            public void a(@NonNull Uri uri, int i, int i2, int i3, int i4) {
                UCropActivity.this.a(uri, UCropActivity.this.n.getTargetAspectRatio(), i, i2, i3, i4);
            }

            public void a(@NonNull Throwable th) {
                UCropActivity.this.a(th);
                UCropActivity.this.b();
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void a(Uri uri, float f, int i, int i2, int i3, int i4) {
        setResult(-1, new Intent().putExtra("com.one.tomato.ucrop.OutputUri", uri).putExtra("com.one.tomato.ucrop.CropAspectRatio", f).putExtra("com.one.tomato.ucrop.ImageWidth", i3).putExtra("com.one.tomato.ucrop.ImageHeight", i4).putExtra("com.one.tomato.ucrop.OffsetX", i).putExtra("com.one.tomato.ucrop.OffsetY", i2));
        b();
    }

    /* Access modifiers changed, original: protected */
    public void a(Throwable th) {
        setResult(96, new Intent().putExtra("com.one.tomato.ucrop.Error", th));
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        finish();
        overridePendingTransition(0, R.anim.ucrop_close);
    }
}
