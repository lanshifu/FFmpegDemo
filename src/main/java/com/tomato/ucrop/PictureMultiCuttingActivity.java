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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.tomato.ucrop.model.CutInfo;
import com.tomato.ucrop.view.GestureCropImageView;
import com.tomato.ucrop.view.OverlayView;
import com.tomato.ucrop.view.TransformImageView.a;
import com.tomato.ucrop.view.UCropView;
import com.tomato.ucrop.view.widget.AspectRatioTextView;
import com.tomato.ucrop.view.widget.HorizontalProgressWheelView;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.ro;
import defpackage.ry;
import defpackage.sc;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class PictureMultiCuttingActivity extends AppCompatActivity {
    public static final CompressFormat a = CompressFormat.PNG;
    private TextView A;
    private TextView B;
    private View C;
    private CompressFormat D = a;
    private int E = 90;
    private int[] F = new int[]{1, 2, 3};
    private List<CutInfo> G = new ArrayList();
    private boolean H;
    private boolean I;
    private boolean J;
    private int K;
    private a L = new a() {
        public void a(float f) {
            PictureMultiCuttingActivity.this.a(f);
        }

        public void b(float f) {
            PictureMultiCuttingActivity.this.b(f);
        }

        public void a() {
            PictureMultiCuttingActivity.this.q.animate().alpha(1.0f).setDuration(300).setInterpolator(new AccelerateInterpolator());
            PictureMultiCuttingActivity.this.C.setClickable(false);
            PictureMultiCuttingActivity.this.o = false;
            PictureMultiCuttingActivity.this.supportInvalidateOptionsMenu();
        }

        public void a(@NonNull Exception exception) {
            PictureMultiCuttingActivity.this.a((Throwable) exception);
            PictureMultiCuttingActivity.this.c();
        }
    };
    private final OnClickListener M = new OnClickListener() {
        public void onClick(View view) {
            if (!view.isSelected()) {
                PictureMultiCuttingActivity.this.c(view.getId());
            }
        }
    };
    private RecyclerView b;
    private PicturePhotoGalleryAdapter c;
    private String d;
    private ArrayList<String> e;
    private int f;
    private int g;
    private int h;
    private int i;
    @ColorInt
    private int j;
    @DrawableRes
    private int k;
    @DrawableRes
    private int l;
    private int m;
    private boolean n;
    private boolean o = true;
    private boolean p;
    private UCropView q;
    private GestureCropImageView r;
    private OverlayView s;
    private ViewGroup t;
    private ViewGroup u;
    private ViewGroup v;
    private ViewGroup w;
    private ViewGroup x;
    private ViewGroup y;
    private List<ViewGroup> z = new ArrayList();

    private void d(int i) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tomato_ucrop_picture_activity_multi_cutting);
        Intent intent = getIntent();
        this.e = getIntent().getStringArrayListExtra("com.one.tomato.ucrop.cuts");
        if (this.e != null && this.e.size() > 0) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                this.G.add(new CutInfo((String) it.next(), false));
            }
            d();
        }
        c(intent);
        k();
        l();
        a(intent);
    }

    private void d() {
        this.b = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.b.setLayoutManager(linearLayoutManager);
        for (CutInfo cut : this.G) {
            cut.setCut(false);
        }
        ((CutInfo) this.G.get(this.K)).setCut(true);
        this.c = new PicturePhotoGalleryAdapter(this, this.G);
        this.b.setAdapter(this.c);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ucrop_menu_activity, menu);
        MenuItem findItem = menu.findItem(R.id.menu_loader);
        Drawable icon = findItem.getIcon();
        if (icon != null) {
            try {
                icon.mutate();
                icon.setColorFilter(this.i, Mode.SRC_ATOP);
                findItem.setIcon(icon);
            } catch (IllegalStateException e) {
                Log.i("UCropActivity", String.format("%s - %s", new Object[]{e.getMessage(), getString(R.string.ucrop_mutate_exception_hint)}));
            }
            ((Animatable) findItem.getIcon()).start();
        }
        MenuItem findItem2 = menu.findItem(R.id.menu_crop);
        Drawable drawable = ContextCompat.getDrawable(this, this.l);
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(this.i, Mode.SRC_ATOP);
            findItem2.setIcon(drawable);
        }
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_crop).setVisible(this.o ^ 1);
        menu.findItem(R.id.menu_loader).setVisible(this.o);
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
        if (this.r != null) {
            this.r.a();
        }
    }

    private void a(@NonNull Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra("com.one.tomato.ucrop.InputUri");
        Uri uri2 = (Uri) intent.getParcelableExtra("com.one.tomato.ucrop.OutputUri");
        b(intent);
        if (uri == null || uri2 == null) {
            a(new NullPointerException(getString(R.string.ucrop_error_input_data_is_absent)));
            c();
            return;
        }
        try {
            if (ry.a(uri.getPath())) {
                this.r.setRotateEnabled(false);
                this.r.setScaleEnabled(false);
            } else {
                this.r.setRotateEnabled(this.J);
                this.r.setScaleEnabled(this.I);
            }
            this.r.setImageUri(uri, uri2);
        } catch (Exception e) {
            a(e);
            c();
        }
    }

    private void b(@NonNull Intent intent) {
        String stringExtra = intent.getStringExtra("com.one.tomato.ucrop.CompressionFormatName");
        CompressFormat valueOf = !TextUtils.isEmpty(stringExtra) ? CompressFormat.valueOf(stringExtra) : null;
        if (valueOf == null) {
            valueOf = a;
        }
        this.D = valueOf;
        this.E = intent.getIntExtra("com.one.tomato.ucrop.CompressionQuality", 90);
        int[] intArrayExtra = intent.getIntArrayExtra("com.one.tomato.ucrop.AllowedGestures");
        if (intArrayExtra != null && intArrayExtra.length == 3) {
            this.F = intArrayExtra;
        }
        this.r.setMaxBitmapSize(intent.getIntExtra("com.one.tomato.ucrop.MaxBitmapSize", 0));
        this.r.setMaxScaleMultiplier(intent.getFloatExtra("com.one.tomato.ucrop.MaxScaleMultiplier", 10.0f));
        this.r.setImageToWrapCropBoundsAnimDuration((long) intent.getIntExtra("com.one.tomato.ucrop.ImageToCropBoundsAnimDuration", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION));
        this.s.setDragFrame(this.H);
        this.s.setFreestyleCropEnabled(intent.getBooleanExtra("com.one.tomato.ucrop.FreeStyleCrop", false));
        this.p = intent.getBooleanExtra("com.one.tomato.ucrop.CircleDimmedLayer", false);
        this.s.setDimmedColor(intent.getIntExtra("com.one.tomato.ucrop.DimmedLayerColor", getResources().getColor(R.color.ucrop_color_default_dimmed)));
        this.s.setCircleDimmedLayer(this.p);
        this.s.setShowCropFrame(intent.getBooleanExtra("com.one.tomato.ucrop.ShowCropFrame", true));
        this.s.setCropFrameColor(intent.getIntExtra("com.one.tomato.ucrop.CropFrameColor", getResources().getColor(R.color.ucrop_color_default_crop_frame)));
        this.s.setCropFrameStrokeWidth(intent.getIntExtra("com.one.tomato.ucrop.CropFrameStrokeWidth", getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_frame_stoke_width)));
        this.s.setShowCropGrid(intent.getBooleanExtra("com.one.tomato.ucrop.ShowCropGrid", true));
        this.s.setCropGridRowCount(intent.getIntExtra("com.one.tomato.ucrop.CropGridRowCount", 2));
        this.s.setCropGridColumnCount(intent.getIntExtra("com.one.tomato.ucrop.CropGridColumnCount", 2));
        this.s.setCropGridColor(intent.getIntExtra("com.one.tomato.ucrop.CropGridColor", getResources().getColor(R.color.ucrop_color_default_crop_grid)));
        this.s.setCropGridStrokeWidth(intent.getIntExtra("com.one.tomato.ucrop.CropGridStrokeWidth", getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_grid_stoke_width)));
        float floatExtra = intent.getFloatExtra("com.one.tomato.ucrop.AspectRatioX", CropImageView.DEFAULT_ASPECT_RATIO);
        float floatExtra2 = intent.getFloatExtra("com.one.tomato.ucrop.AspectRatioY", CropImageView.DEFAULT_ASPECT_RATIO);
        int intExtra = intent.getIntExtra("com.one.tomato.ucrop.AspectRatioSelectedByDefault", 0);
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("com.one.tomato.ucrop.AspectRatioOptions");
        if (floatExtra > CropImageView.DEFAULT_ASPECT_RATIO && floatExtra2 > CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.t != null) {
                this.t.setVisibility(8);
            }
            this.r.setTargetAspectRatio(floatExtra / floatExtra2);
        } else if (parcelableArrayListExtra == null || intExtra >= parcelableArrayListExtra.size()) {
            this.r.setTargetAspectRatio(CropImageView.DEFAULT_ASPECT_RATIO);
        } else {
            this.r.setTargetAspectRatio(((AspectRatio) parcelableArrayListExtra.get(intExtra)).b() / ((AspectRatio) parcelableArrayListExtra.get(intExtra)).c());
        }
        int intExtra2 = intent.getIntExtra("com.one.tomato.ucrop.MaxSizeX", 0);
        int intExtra3 = intent.getIntExtra("com.one.tomato.ucrop.MaxSizeY", 0);
        if (intExtra2 > 0 && intExtra3 > 0) {
            this.r.setMaxResultImageSizeX(intExtra2);
            this.r.setMaxResultImageSizeY(intExtra3);
        }
    }

    private void c(@NonNull Intent intent) {
        this.I = intent.getBooleanExtra("com.one.tomato.ucrop.scale", false);
        this.J = intent.getBooleanExtra("com.one.tomato.ucrop.rotate", false);
        this.g = intent.getIntExtra("com.one.tomato.ucrop.StatusBarColor", ContextCompat.getColor(this, R.color.ucrop_color_statusbar));
        this.f = intent.getIntExtra("com.one.tomato.ucrop.ToolbarColor", ContextCompat.getColor(this, R.color.ucrop_color_toolbar));
        if (this.f == -1) {
            this.f = ContextCompat.getColor(this, R.color.ucrop_color_toolbar);
        }
        if (this.g == -1) {
            this.g = ContextCompat.getColor(this, R.color.ucrop_color_statusbar);
        }
        this.h = intent.getIntExtra("com.one.tomato.ucrop.UcropColorWidgetActive", ContextCompat.getColor(this, R.color.ucrop_color_widget_active));
        this.i = intent.getIntExtra("com.one.tomato.ucrop.UcropToolbarWidgetColor", ContextCompat.getColor(this, R.color.ucrop_color_toolbar_widget));
        if (this.i == -1) {
            this.i = ContextCompat.getColor(this, R.color.ucrop_color_toolbar_widget);
        }
        this.k = intent.getIntExtra("com.one.tomato.ucrop.UcropToolbarCancelDrawable", R.drawable.ucrop_ic_cross);
        this.l = intent.getIntExtra("com.one.tomato.ucrop.UcropToolbarCropDrawable", R.drawable.ucrop_ic_done);
        this.d = intent.getStringExtra("com.one.tomato.ucrop.UcropToolbarTitleText");
        this.d = this.d != null ? this.d : getResources().getString(R.string.ucrop_label_edit_photo);
        this.m = intent.getIntExtra("com.one.tomato.ucrop.UcropLogoColor", ContextCompat.getColor(this, R.color.ucrop_color_default_logo));
        this.n = intent.getBooleanExtra("com.one.tomato.ucrop.HideBottomControls", false) ^ 1;
        this.j = intent.getIntExtra("com.one.tomato.ucrop.UcropRootViewBackgroundColor", ContextCompat.getColor(this, R.color.ucrop_color_crop_background));
        e();
        f();
        if (this.n) {
            View.inflate(this, R.layout.tomato_ucrop_controls, (ViewGroup) findViewById(R.id.ucrop_mulit_photobox));
            this.t = (ViewGroup) findViewById(R.id.state_aspect_ratio);
            this.t.setOnClickListener(this.M);
            this.u = (ViewGroup) findViewById(R.id.state_rotate);
            this.u.setOnClickListener(this.M);
            this.v = (ViewGroup) findViewById(R.id.state_scale);
            this.v.setOnClickListener(this.M);
            this.w = (ViewGroup) findViewById(R.id.layout_aspect_ratio);
            this.x = (ViewGroup) findViewById(R.id.layout_rotate_wheel);
            this.y = (ViewGroup) findViewById(R.id.layout_scale_wheel);
            d(intent);
            h();
            i();
            g();
        }
    }

    private void e() {
        a(this.g);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(this.f);
        toolbar.setTitleTextColor(this.i);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textView.setTextColor(this.i);
        textView.setText(this.d);
        Drawable mutate = ContextCompat.getDrawable(this, this.k).mutate();
        mutate.setColorFilter(this.i, Mode.SRC_ATOP);
        toolbar.setNavigationIcon(mutate);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void f() {
        this.q = (UCropView) findViewById(R.id.ucrop);
        this.r = this.q.getCropImageView();
        this.s = this.q.getOverlayView();
        this.r.setTransformImageListener(this.L);
    }

    private void g() {
        ImageView imageView = (ImageView) findViewById(R.id.image_view_state_scale);
        ImageView imageView2 = (ImageView) findViewById(R.id.image_view_state_rotate);
        ImageView imageView3 = (ImageView) findViewById(R.id.image_view_state_aspect_ratio);
        imageView.setImageDrawable(new sc(imageView.getDrawable(), this.h));
        imageView2.setImageDrawable(new sc(imageView2.getDrawable(), this.h));
        imageView3.setImageDrawable(new sc(imageView3.getDrawable(), this.h));
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
            aspectRatioTextView.setActiveColor(this.h);
            aspectRatioTextView.setAspectRatio(aspectRatio);
            linearLayout.addView(frameLayout);
            this.z.add(frameLayout);
        }
        ((ViewGroup) this.z.get(intExtra)).setSelected(true);
        for (ViewGroup onClickListener : this.z) {
            onClickListener.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PictureMultiCuttingActivity.this.r.setTargetAspectRatio(((AspectRatioTextView) ((ViewGroup) view).getChildAt(0)).a(view.isSelected()));
                    PictureMultiCuttingActivity.this.r.setImageToWrapCropBounds();
                    if (!view.isSelected()) {
                        for (View view2 : PictureMultiCuttingActivity.this.z) {
                            view2.setSelected(view2 == view);
                        }
                    }
                }
            });
        }
    }

    private void h() {
        this.A = (TextView) findViewById(R.id.text_view_rotate);
        ((HorizontalProgressWheelView) findViewById(R.id.rotate_scroll_wheel)).setScrollingListener(new HorizontalProgressWheelView.a() {
            public void a(float f, float f2) {
                PictureMultiCuttingActivity.this.r.c(f / 42.0f);
            }

            public void a() {
                PictureMultiCuttingActivity.this.r.setImageToWrapCropBounds();
            }

            public void b() {
                PictureMultiCuttingActivity.this.r.a();
            }
        });
        ((HorizontalProgressWheelView) findViewById(R.id.rotate_scroll_wheel)).setMiddleLineColor(this.h);
        findViewById(R.id.wrapper_reset_rotate).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PictureMultiCuttingActivity.this.j();
            }
        });
        findViewById(R.id.wrapper_rotate_by_angle).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PictureMultiCuttingActivity.this.b(90);
            }
        });
    }

    private void i() {
        this.B = (TextView) findViewById(R.id.text_view_scale);
        ((HorizontalProgressWheelView) findViewById(R.id.scale_scroll_wheel)).setScrollingListener(new HorizontalProgressWheelView.a() {
            public void a(float f, float f2) {
                if (f > CropImageView.DEFAULT_ASPECT_RATIO) {
                    PictureMultiCuttingActivity.this.r.b(PictureMultiCuttingActivity.this.r.getCurrentScale() + (f * ((PictureMultiCuttingActivity.this.r.getMaxScale() - PictureMultiCuttingActivity.this.r.getMinScale()) / 15000.0f)));
                } else {
                    PictureMultiCuttingActivity.this.r.a(PictureMultiCuttingActivity.this.r.getCurrentScale() + (f * ((PictureMultiCuttingActivity.this.r.getMaxScale() - PictureMultiCuttingActivity.this.r.getMinScale()) / 15000.0f)));
                }
            }

            public void a() {
                PictureMultiCuttingActivity.this.r.setImageToWrapCropBounds();
            }

            public void b() {
                PictureMultiCuttingActivity.this.r.a();
            }
        });
        ((HorizontalProgressWheelView) findViewById(R.id.scale_scroll_wheel)).setMiddleLineColor(this.h);
    }

    private void a(float f) {
        if (this.A != null) {
            this.A.setText(String.format(Locale.getDefault(), "%.1f°", new Object[]{Float.valueOf(f)}));
        }
    }

    private void b(float f) {
        if (this.B != null) {
            this.B.setText(String.format(Locale.getDefault(), "%d%%", new Object[]{Integer.valueOf((int) (f * 100.0f))}));
        }
    }

    private void j() {
        this.r.c(-this.r.getCurrentAngle());
        this.r.setImageToWrapCropBounds();
    }

    private void b(int i) {
        this.r.c((float) i);
        this.r.setImageToWrapCropBounds();
    }

    private void k() {
        if (!this.n) {
            d(0);
        } else if (this.t.getVisibility() == 0) {
            c(R.id.state_aspect_ratio);
        } else {
            c(R.id.state_scale);
        }
    }

    private void c(@IdRes int i) {
        if (this.n) {
            this.t.setSelected(i == R.id.state_aspect_ratio);
            this.u.setSelected(i == R.id.state_rotate);
            this.v.setSelected(i == R.id.state_scale);
            int i2 = 8;
            this.w.setVisibility(i == R.id.state_aspect_ratio ? 0 : 8);
            this.x.setVisibility(i == R.id.state_rotate ? 0 : 8);
            ViewGroup viewGroup = this.y;
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

    private void l() {
        if (this.C == null) {
            this.C = new View(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(3, R.id.toolbar);
            this.C.setLayoutParams(layoutParams);
            this.C.setClickable(true);
        }
        ((RelativeLayout) findViewById(R.id.ucrop_mulit_photobox)).addView(this.C);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        this.C.setClickable(true);
        this.o = true;
        supportInvalidateOptionsMenu();
        this.r.a(this.D, this.E, (ro) new ro() {
            public void a(@NonNull Uri uri, int i, int i2, int i3, int i4) {
                PictureMultiCuttingActivity.this.a(uri, PictureMultiCuttingActivity.this.r.getTargetAspectRatio(), i, i2, i3, i4);
            }

            public void a(@NonNull Throwable th) {
                PictureMultiCuttingActivity.this.a(th);
                PictureMultiCuttingActivity.this.c();
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void a(Uri uri, float f, int i, int i2, int i3, int i4) {
        try {
            CutInfo cutInfo = (CutInfo) this.G.get(this.K);
            cutInfo.setCutPath(uri.getPath());
            cutInfo.setCut(true);
            cutInfo.setResultAspectRatio(f);
            cutInfo.setOffsetX(i);
            cutInfo.setOffsetY(i2);
            cutInfo.setImageWidth(i3);
            cutInfo.setImageHeight(i4);
            this.K++;
            if (this.K >= this.G.size()) {
                setResult(-1, new Intent().putExtra("com.one.tomato.ucrop.OutputUriList", (Serializable) this.G));
                c();
                return;
            }
            b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        setContentView(R.layout.tomato_ucrop_picture_activity_multi_cutting);
        Intent intent = new Intent();
        Bundle extras = getIntent().getExtras();
        String path = ((CutInfo) this.G.get(this.K)).getPath();
        boolean c = ry.c(path);
        String a = a(path);
        extras.putParcelable("com.one.tomato.ucrop.InputUri", c ? Uri.parse(path) : Uri.fromFile(new File(path)));
        File cacheDir = getCacheDir();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append(a);
        extras.putParcelable("com.one.tomato.ucrop.OutputUri", Uri.fromFile(new File(cacheDir, stringBuilder.toString())));
        intent.putExtras(extras);
        c(intent);
        k();
        ((RelativeLayout) findViewById(R.id.ucrop_mulit_photobox)).removeView(this.C);
        this.C = null;
        l();
        a(intent);
        d();
        if (this.K >= 5) {
            this.b.scrollToPosition(this.K);
        }
    }

    public static String a(String str) {
        try {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf <= 0) {
                return ".png";
            }
            str = str.substring(lastIndexOf, str.length());
            Object obj = -1;
            switch (str.hashCode()) {
                case 1436279:
                    if (str.equals(".BMP")) {
                        obj = 7;
                        break;
                    }
                    break;
                case 1449755:
                    if (str.equals(".PNG")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 1468055:
                    if (str.equals(".bmp")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 1475827:
                    if (str.equals(".jpg")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 1481531:
                    if (str.equals(".png")) {
                        obj = null;
                        break;
                    }
                    break;
                case 44765590:
                    if (str.equals(".JPEG")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 45142218:
                    if (str.equals(".WEBP")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 45750678:
                    if (str.equals(".jpeg")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 46127306:
                    if (str.equals(".webp")) {
                        obj = 8;
                        break;
                    }
                    break;
                default:
                    break;
            }
            switch (obj) {
                case null:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    return str;
                default:
                    return ".png";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ".png";
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(Throwable th) {
        setResult(96, new Intent().putExtra("com.one.tomato.ucrop.Error", th));
    }

    /* Access modifiers changed, original: protected */
    public void c() {
        finish();
        overridePendingTransition(0, R.anim.ucrop_close);
    }
}
