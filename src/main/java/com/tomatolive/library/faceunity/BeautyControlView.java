package com.tomatolive.library.faceunity;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.blankj.utilcode.util.k;
import com.faceunity.beautycontrolview.BeautyBox;
import com.faceunity.beautycontrolview.BeautyBoxGroup;
import com.faceunity.beautycontrolview.CheckGroup;
import com.faceunity.beautycontrolview.FilterEnum;
import com.faceunity.beautycontrolview.seekbar.DiscreteSeekBar;
import com.tomatolive.library.R;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.fv;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeautyControlView extends FrameLayout {
    private static final String a = "BeautyControlView";
    private static final List<Integer> u = Arrays.asList(new Integer[]{Integer.valueOf(R.id.face_shape_0_nvshen), Integer.valueOf(R.id.face_shape_1_wanghong), Integer.valueOf(R.id.face_shape_2_ziran), Integer.valueOf(R.id.face_shape_3_default), Integer.valueOf(R.id.face_shape_4)});
    private float A;
    private float B;
    private float C;
    private float D;
    private float E;
    private float F;
    private float G;
    private float H;
    private float I;
    private float J;
    private float K;
    private float L;
    private float M;
    private int N;
    private int O;
    private int P;
    private b Q;
    private ValueAnimator R;
    private c S;
    private Context b;
    private com.faceunity.beautycontrolview.a c;
    private CheckGroup d;
    private FrameLayout e;
    private HorizontalScrollView f;
    private BeautyBoxGroup g;
    private HorizontalScrollView h;
    private BeautyBoxGroup i;
    private BeautyBox j;
    private BeautyBox k;
    private BeautyBox l;
    private BeautyBox m;
    private BeautyBox n;
    private RecyclerView o;
    private a p;
    private List<fv> q;
    private List<fv> r;
    private FrameLayout s;
    private DiscreteSeekBar t;
    private RadioGroup v;
    private Map<String, Float> w;
    private float x;
    private float y;
    private float z;

    public interface b {
        void a(float f);
    }

    public interface c {
        void a(String str);
    }

    class a extends Adapter<a> {
        int a;

        class a extends ViewHolder {
            ImageView a;
            TextView b;

            public a(View view) {
                super(view);
                this.a = (ImageView) view.findViewById(R.id.control_recycler_img);
                this.b = (TextView) view.findViewById(R.id.control_recycler_text);
            }
        }

        a() {
        }

        /* renamed from: a */
        public a onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new a(LayoutInflater.from(BeautyControlView.this.b).inflate(R.layout.layout_beauty_control_recycler, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(a aVar, @SuppressLint({"RecyclerView"}) final int i) {
            final List b = b(this.a);
            aVar.a.setBackgroundResource(((fv) b.get(i)).b());
            aVar.b.setText(((fv) b.get(i)).c());
            if (BeautyControlView.this.O == i && this.a == BeautyControlView.this.P) {
                aVar.a.setImageResource(R.drawable.control_filter_select);
            } else {
                aVar.a.setImageResource(0);
            }
            aVar.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    BeautyControlView.this.O = i;
                    BeautyControlView.this.P = a.this.a;
                    a.this.a();
                    a.this.notifyDataSetChanged();
                    BeautyControlView.this.s.setVisibility(0);
                    BeautyControlView.this.a(false);
                    if (BeautyControlView.this.c != null) {
                        BeautyControlView.this.c.a((fv) b.get(BeautyControlView.this.O));
                    }
                }
            });
        }

        public int getItemCount() {
            return b(this.a).size();
        }

        public void a(int i) {
            this.a = i;
            notifyDataSetChanged();
        }

        public void a(float f) {
            BeautyControlView.this.setFaceBeautyFilterLevel(((fv) b(BeautyControlView.this.P).get(BeautyControlView.this.O)).a(), f);
        }

        public void a() {
            BeautyControlView.this.a(BeautyControlView.this.a(((fv) b(BeautyControlView.this.P).get(BeautyControlView.this.O)).a()));
        }

        public List<fv> b(int i) {
            switch (i) {
                case 0:
                    return BeautyControlView.this.r;
                case 1:
                    return BeautyControlView.this.q;
                default:
                    return BeautyControlView.this.r;
            }
        }
    }

    public void setOnFaceUnityControlListener(@NonNull com.faceunity.beautycontrolview.a aVar) {
        this.c = aVar;
    }

    public BeautyControlView(Context context) {
        this(context, null);
    }

    public BeautyControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BeautyControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.w = new HashMap();
        this.x = 1.0f;
        this.y = CropImageView.DEFAULT_ASPECT_RATIO;
        this.z = 0.7f;
        this.A = 0.5f;
        this.B = 0.5f;
        this.C = 1000.7f;
        this.D = 1000.7f;
        this.E = 4.0f;
        this.F = 0.4f;
        this.G = 0.4f;
        this.H = 0.4f;
        this.I = 0.4f;
        this.J = 0.3f;
        this.K = 0.3f;
        this.L = 0.5f;
        this.M = 0.4f;
        this.N = 0;
        this.O = 0;
        this.P = 1;
        this.b = context;
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        LayoutInflater.from(context).inflate(R.layout.layout_beauty_control, this);
        b();
        post(new Runnable() {
            public void run() {
                BeautyControlView.this.e();
                BeautyControlView.this.g();
                BeautyControlView.this.g.a(-1);
                BeautyControlView.this.i.a(-1);
            }
        });
        this.q = FilterEnum.getFiltersByFilterType(1);
        this.r = FilterEnum.getFiltersByFilterType(0);
        getLastParam();
    }

    private void getLastParam() {
        float b;
        Map map;
        StringBuilder stringBuilder;
        this.x = k.a().b("mFaceBeautyALLBlurLevel", 1.0f);
        this.y = k.a().b("mFaceBeautyType", CropImageView.DEFAULT_ASPECT_RATIO);
        this.z = k.a().b("mFaceBeautyBlurLevel", 0.7f);
        this.A = k.a().b("mFaceBeautyColorLevel", 0.5f);
        this.B = k.a().b("mFaceBeautyRedLevel", 0.5f);
        this.C = k.a().b("mBrightEyesLevel", CropImageView.DEFAULT_ASPECT_RATIO);
        this.D = k.a().b("mBeautyTeethLevel", CropImageView.DEFAULT_ASPECT_RATIO);
        this.E = k.a().b("mFaceBeautyFaceShape", 4.0f);
        this.F = k.a().b("mFaceBeautyEnlargeEye", 0.4f);
        this.G = k.a().b("mFaceBeautyCheekThin", 0.4f);
        this.J = k.a().b("mChinLevel", 0.3f);
        this.K = k.a().b("mForeheadLevel", 0.3f);
        this.L = k.a().b("mThinNoseLevel", 0.5f);
        this.M = k.a().b("mMouthShape", 0.4f);
        this.w.clear();
        for (fv fvVar : this.q) {
            b = k.a().b(fvVar.a(), 1.0f);
            map = this.w;
            stringBuilder = new StringBuilder();
            stringBuilder.append("FaceBeautyFilterLevel_");
            stringBuilder.append(fvVar.a());
            map.put(stringBuilder.toString(), Float.valueOf(b));
        }
        for (fv fvVar2 : this.r) {
            b = k.a().b(fvVar2.a(), 1.0f);
            map = this.w;
            stringBuilder = new StringBuilder();
            stringBuilder.append("FaceBeautyFilterLevel_");
            stringBuilder.append(fvVar2.a());
            map.put(stringBuilder.toString(), Float.valueOf(b));
        }
    }

    private void b() {
        c();
        this.e = (FrameLayout) findViewById(R.id.beauty_mid_layout);
        d();
        f();
        h();
        i();
    }

    private void c() {
        this.d = (CheckGroup) findViewById(R.id.beauty_radio_group);
        this.d.setOnCheckedChangeListener(new com.faceunity.beautycontrolview.CheckGroup.b() {
            public void a(CheckGroup checkGroup, int i) {
                BeautyControlView.this.b(i);
                BeautyControlView.this.a(true);
            }
        });
    }

    private void d() {
        this.f = (HorizontalScrollView) findViewById(R.id.skin_beauty_select_block);
        this.g = (BeautyBoxGroup) findViewById(R.id.beauty_box_skin_beauty);
        this.g.setOnCheckedChangeListener(new com.faceunity.beautycontrolview.BeautyBoxGroup.b() {
            public void a(BeautyBoxGroup beautyBoxGroup, int i, boolean z) {
                BeautyControlView.this.v.setVisibility(8);
                BeautyControlView.this.s.setVisibility(8);
                float f = 1.0f;
                BeautyControlView beautyControlView;
                if (i == R.id.beauty_all_blur_box) {
                    beautyControlView = BeautyControlView.this;
                    if (!z) {
                        f = CropImageView.DEFAULT_ASPECT_RATIO;
                    }
                    beautyControlView.x = f;
                    BeautyControlView.this.setDescriptionShowStr(BeautyControlView.this.x == CropImageView.DEFAULT_ASPECT_RATIO ? "精准美肤 关闭" : "精准美肤 开启");
                    BeautyControlView.this.a(i, BeautyControlView.this.x);
                } else if (i == R.id.beauty_type_box) {
                    beautyControlView = BeautyControlView.this;
                    if (!z) {
                        f = CropImageView.DEFAULT_ASPECT_RATIO;
                    }
                    beautyControlView.y = f;
                    BeautyControlView.this.setDescriptionShowStr(BeautyControlView.this.y == CropImageView.DEFAULT_ASPECT_RATIO ? "当前为 清晰磨皮 模式" : "当前为 朦胧磨皮 模式");
                    BeautyControlView.this.a(i, BeautyControlView.this.y);
                } else if (i == R.id.beauty_blur_box) {
                    if (z && BeautyControlView.this.z >= 1000.0f) {
                        BeautyControlView.this.z = BeautyControlView.this.z - 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("磨皮 开启");
                    } else if (!z && BeautyControlView.this.z < 1000.0f) {
                        BeautyControlView.this.z = BeautyControlView.this.z + 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("磨皮 关闭");
                    }
                    BeautyControlView.this.a(BeautyControlView.this.z);
                    BeautyControlView.this.a(i, BeautyControlView.this.z);
                } else if (i == R.id.beauty_color_box) {
                    if (z && BeautyControlView.this.A >= 1000.0f) {
                        BeautyControlView.this.A = BeautyControlView.this.A - 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("美白 开启");
                    } else if (!z && BeautyControlView.this.A < 1000.0f) {
                        BeautyControlView.this.A = BeautyControlView.this.A + 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("美白 关闭");
                    }
                    BeautyControlView.this.a(BeautyControlView.this.A);
                    BeautyControlView.this.a(i, BeautyControlView.this.A);
                } else if (i == R.id.beauty_red_box) {
                    if (z && BeautyControlView.this.B >= 1000.0f) {
                        BeautyControlView.this.B = BeautyControlView.this.B - 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("红润 开启");
                    } else if (!z && BeautyControlView.this.B < 1000.0f) {
                        BeautyControlView.this.B = BeautyControlView.this.B + 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("红润 关闭");
                    }
                    BeautyControlView.this.a(BeautyControlView.this.B);
                    BeautyControlView.this.a(i, BeautyControlView.this.B);
                } else if (i == R.id.beauty_bright_eyes_box) {
                    if (z && BeautyControlView.this.C >= 1000.0f) {
                        BeautyControlView.this.C = BeautyControlView.this.C - 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("亮眼 开启");
                    } else if (!z && BeautyControlView.this.C < 1000.0f) {
                        BeautyControlView.this.C = BeautyControlView.this.C + 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("亮眼 关闭");
                    }
                    BeautyControlView.this.a(BeautyControlView.this.C);
                    BeautyControlView.this.a(i, BeautyControlView.this.C);
                } else if (i == R.id.beauty_teeth_box) {
                    if (z && BeautyControlView.this.D >= 1000.0f) {
                        BeautyControlView.this.D = BeautyControlView.this.D - 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("美牙 开启");
                    } else if (!z && BeautyControlView.this.D < 1000.0f) {
                        BeautyControlView.this.D = BeautyControlView.this.D + 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("美牙 关闭");
                    }
                    BeautyControlView.this.a(BeautyControlView.this.D);
                    BeautyControlView.this.a(i, BeautyControlView.this.D);
                }
                BeautyControlView.this.a(false);
            }
        });
    }

    private void e() {
        boolean z = false;
        ((BeautyBox) findViewById(R.id.beauty_all_blur_box)).setChecked(this.x == 1.0f);
        ((BeautyBox) findViewById(R.id.beauty_type_box)).setChecked(this.y == 1.0f);
        ((BeautyBox) findViewById(R.id.beauty_blur_box)).setChecked(this.z < 1000.0f);
        ((BeautyBox) findViewById(R.id.beauty_color_box)).setChecked(this.A < 1000.0f);
        ((BeautyBox) findViewById(R.id.beauty_red_box)).setChecked(this.B < 1000.0f);
        ((BeautyBox) findViewById(R.id.beauty_bright_eyes_box)).setChecked(this.C < 1000.0f);
        BeautyBox beautyBox = (BeautyBox) findViewById(R.id.beauty_teeth_box);
        if (this.D < 1000.0f) {
            z = true;
        }
        beautyBox.setChecked(z);
    }

    private void f() {
        this.h = (HorizontalScrollView) findViewById(R.id.face_shape_select_block);
        this.i = (BeautyBoxGroup) findViewById(R.id.beauty_box_face_shape);
        this.i.setOnCheckedChangeListener(new com.faceunity.beautycontrolview.BeautyBoxGroup.b() {
            public void a(BeautyBoxGroup beautyBoxGroup, int i, boolean z) {
                BeautyControlView.this.v.setVisibility(8);
                BeautyControlView.this.s.setVisibility(8);
                if (i == R.id.face_shape_box) {
                    BeautyControlView.this.v.setVisibility(0);
                } else if (i == R.id.enlarge_eye_level_box) {
                    if (BeautyControlView.this.E == 4.0f) {
                        if (z && BeautyControlView.this.F >= 1000.0f) {
                            BeautyControlView.this.F = BeautyControlView.this.F - 1000.0f;
                            BeautyControlView.this.setDescriptionShowStr("大眼 开启");
                        } else if (!z && BeautyControlView.this.F < 1000.0f) {
                            BeautyControlView.this.F = BeautyControlView.this.F + 1000.0f;
                            BeautyControlView.this.setDescriptionShowStr("大眼 关闭");
                        }
                        BeautyControlView.this.a(BeautyControlView.this.F);
                        BeautyControlView.this.a(i, BeautyControlView.this.F);
                    } else {
                        if (z && BeautyControlView.this.H >= 1000.0f) {
                            BeautyControlView.this.H = BeautyControlView.this.H - 1000.0f;
                            BeautyControlView.this.setDescriptionShowStr("大眼 开启");
                        } else if (!z && BeautyControlView.this.H < 1000.0f) {
                            BeautyControlView.this.H = BeautyControlView.this.H + 1000.0f;
                            BeautyControlView.this.setDescriptionShowStr("大眼 关闭");
                        }
                        BeautyControlView.this.a(BeautyControlView.this.H);
                        BeautyControlView.this.a(i, BeautyControlView.this.H);
                    }
                } else if (i == R.id.cheek_thin_level_box) {
                    if (BeautyControlView.this.E == 4.0f) {
                        if (z && BeautyControlView.this.G >= 1000.0f) {
                            BeautyControlView.this.G = BeautyControlView.this.G - 1000.0f;
                            BeautyControlView.this.setDescriptionShowStr("瘦脸 开启");
                        } else if (!z && BeautyControlView.this.G < 1000.0f) {
                            BeautyControlView.this.G = BeautyControlView.this.G + 1000.0f;
                            BeautyControlView.this.setDescriptionShowStr("瘦脸 关闭");
                        }
                        BeautyControlView.this.a(BeautyControlView.this.G);
                        BeautyControlView.this.a(i, BeautyControlView.this.G);
                    } else {
                        if (z && BeautyControlView.this.I >= 1000.0f) {
                            BeautyControlView.this.I = BeautyControlView.this.I - 1000.0f;
                            BeautyControlView.this.setDescriptionShowStr("瘦脸 开启");
                        } else if (!z && BeautyControlView.this.I < 1000.0f) {
                            BeautyControlView.this.I = BeautyControlView.this.I + 1000.0f;
                            BeautyControlView.this.setDescriptionShowStr("瘦脸 关闭");
                        }
                        BeautyControlView.this.a(BeautyControlView.this.I);
                        BeautyControlView.this.a(i, BeautyControlView.this.I);
                    }
                } else if (i == R.id.chin_level_box) {
                    if (z && BeautyControlView.this.J >= 1000.0f) {
                        BeautyControlView.this.J = BeautyControlView.this.J - 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("下巴 开启");
                    } else if (!z && BeautyControlView.this.J < 1000.0f) {
                        BeautyControlView.this.J = BeautyControlView.this.J + 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("下巴 关闭");
                    }
                    BeautyControlView.this.a(BeautyControlView.this.J, -50, 50);
                    BeautyControlView.this.a(i, BeautyControlView.this.J);
                } else if (i == R.id.forehead_level_box) {
                    if (z && BeautyControlView.this.K >= 1000.0f) {
                        BeautyControlView.this.K = BeautyControlView.this.K - 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("额头 开启");
                    } else if (!z && BeautyControlView.this.K < 1000.0f) {
                        BeautyControlView.this.K = BeautyControlView.this.K + 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("额头 关闭");
                    }
                    BeautyControlView.this.a(BeautyControlView.this.K, -50, 50);
                    BeautyControlView.this.a(i, BeautyControlView.this.K);
                } else if (i == R.id.thin_nose_level_box) {
                    if (z && BeautyControlView.this.L >= 1000.0f) {
                        BeautyControlView.this.L = BeautyControlView.this.L - 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("瘦鼻 开启");
                    } else if (!z && BeautyControlView.this.L < 1000.0f) {
                        BeautyControlView.this.L = BeautyControlView.this.L + 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("瘦鼻 关闭");
                    }
                    BeautyControlView.this.a(BeautyControlView.this.L);
                    BeautyControlView.this.a(i, BeautyControlView.this.L);
                } else if (i == R.id.mouth_shape_box) {
                    if (z && BeautyControlView.this.M >= 1000.0f) {
                        BeautyControlView.this.M = BeautyControlView.this.M - 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("嘴形 开启");
                    } else if (!z && BeautyControlView.this.M < 1000.0f) {
                        BeautyControlView.this.M = BeautyControlView.this.M + 1000.0f;
                        BeautyControlView.this.setDescriptionShowStr("嘴形 关闭");
                    }
                    BeautyControlView.this.a(BeautyControlView.this.M, -50, 50);
                    BeautyControlView.this.a(i, BeautyControlView.this.M);
                }
                BeautyControlView.this.a(false);
            }
        });
        this.j = (BeautyBox) findViewById(R.id.face_shape_box);
        this.k = (BeautyBox) findViewById(R.id.chin_level_box);
        this.l = (BeautyBox) findViewById(R.id.forehead_level_box);
        this.m = (BeautyBox) findViewById(R.id.thin_nose_level_box);
        this.n = (BeautyBox) findViewById(R.id.mouth_shape_box);
    }

    private void g() {
        boolean z = true;
        if (this.E == 4.0f) {
            ((BeautyBox) findViewById(R.id.enlarge_eye_level_box)).setChecked(this.F < 1000.0f);
            ((BeautyBox) findViewById(R.id.cheek_thin_level_box)).setChecked(this.G < 1000.0f);
        } else {
            ((BeautyBox) findViewById(R.id.enlarge_eye_level_box)).setChecked(this.H < 1000.0f);
            ((BeautyBox) findViewById(R.id.cheek_thin_level_box)).setChecked(this.I < 1000.0f);
        }
        ((BeautyBox) findViewById(R.id.chin_level_box)).setChecked(this.J < 1000.0f);
        ((BeautyBox) findViewById(R.id.forehead_level_box)).setChecked(this.K < 1000.0f);
        ((BeautyBox) findViewById(R.id.thin_nose_level_box)).setChecked(this.L < 1000.0f);
        BeautyBox beautyBox = (BeautyBox) findViewById(R.id.mouth_shape_box);
        if (this.M >= 1000.0f) {
            z = false;
        }
        beautyBox.setChecked(z);
        if (this.E != 4.0f) {
            this.v.check(((Integer) u.get((int) this.E)).intValue());
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            return;
        }
        this.v.check(R.id.face_shape_4);
        this.k.setVisibility(0);
        this.l.setVisibility(0);
        this.m.setVisibility(0);
        this.n.setVisibility(0);
    }

    private void h() {
        this.o = (RecyclerView) findViewById(R.id.filter_recycle_view);
        this.o.setLayoutManager(new LinearLayoutManager(this.b, 0, false));
        RecyclerView recyclerView = this.o;
        a aVar = new a();
        this.p = aVar;
        recyclerView.setAdapter(aVar);
    }

    private void i() {
        this.v = (RadioGroup) findViewById(R.id.face_shape_radio_group);
        this.v.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                float o;
                float q;
                boolean z = false;
                if (i == R.id.face_shape_4) {
                    BeautyControlView.this.k.setVisibility(0);
                    BeautyControlView.this.l.setVisibility(0);
                    BeautyControlView.this.m.setVisibility(0);
                    BeautyControlView.this.n.setVisibility(0);
                } else {
                    BeautyControlView.this.k.setVisibility(8);
                    BeautyControlView.this.l.setVisibility(8);
                    BeautyControlView.this.m.setVisibility(8);
                    BeautyControlView.this.n.setVisibility(8);
                }
                BeautyControlView.this.E = (float) BeautyControlView.u.indexOf(Integer.valueOf(i));
                if (BeautyControlView.this.c != null) {
                    BeautyControlView.this.c.i(BeautyControlView.this.E);
                }
                if (BeautyControlView.this.E == 4.0f) {
                    o = BeautyControlView.this.F;
                    q = BeautyControlView.this.G;
                } else {
                    o = BeautyControlView.this.H;
                    q = BeautyControlView.this.I;
                }
                BeautyControlView.this.a(R.id.enlarge_eye_level_box, o);
                BeautyControlView.this.a(R.id.cheek_thin_level_box, q);
                ((BeautyBox) BeautyControlView.this.findViewById(R.id.enlarge_eye_level_box)).setChecked(o < 1000.0f);
                ((BeautyBox) BeautyControlView.this.findViewById(R.id.cheek_thin_level_box)).setChecked(q < 1000.0f);
                BeautyBox B = BeautyControlView.this.j;
                if (i != R.id.face_shape_3_default) {
                    z = true;
                }
                B.setChecked(z);
            }
        });
        this.s = (FrameLayout) findViewById(R.id.beauty_seek_bar_layout);
        this.t = (DiscreteSeekBar) findViewById(R.id.beauty_seek_bar);
        this.t.setOnProgressChangeListener(new com.faceunity.beautycontrolview.seekbar.DiscreteSeekBar.c() {
            public void a(DiscreteSeekBar discreteSeekBar) {
            }

            public void b(DiscreteSeekBar discreteSeekBar) {
            }

            public void a(DiscreteSeekBar discreteSeekBar, int i, boolean z) {
                if (z) {
                    float min = (((float) (i - discreteSeekBar.getMin())) * 1.0f) / 100.0f;
                    if (BeautyControlView.this.d.getCheckedCheckBoxId() == R.id.beauty_radio_skin_beauty) {
                        BeautyControlView.this.a(BeautyControlView.this.g.getCheckedBeautyBoxId(), min);
                    } else if (BeautyControlView.this.d.getCheckedCheckBoxId() == R.id.beauty_radio_face_shape) {
                        BeautyControlView.this.a(BeautyControlView.this.i.getCheckedBeautyBoxId(), min);
                    } else if (BeautyControlView.this.d.getCheckedCheckBoxId() == R.id.beauty_radio_beauty_filter || BeautyControlView.this.d.getCheckedCheckBoxId() == R.id.beauty_radio_filter) {
                        BeautyControlView.this.p.a(min);
                        if (BeautyControlView.this.c != null) {
                            BeautyControlView.this.c.a(min);
                        }
                    }
                }
            }
        });
    }

    private void a(int i) {
        this.v.setVisibility(8);
        this.s.setVisibility(8);
        if (i == R.id.beauty_blur_box) {
            a(this.z);
        } else if (i == R.id.beauty_color_box) {
            a(this.A);
        } else if (i == R.id.beauty_red_box) {
            a(this.B);
        } else if (i == R.id.beauty_bright_eyes_box) {
            a(this.C);
        } else if (i == R.id.beauty_teeth_box) {
            a(this.D);
        } else if (i == R.id.face_shape_box) {
            this.v.setVisibility(0);
        } else if (i == R.id.enlarge_eye_level_box) {
            if (this.E == 4.0f) {
                a(this.F);
            } else {
                a(this.H);
            }
        } else if (i == R.id.cheek_thin_level_box) {
            if (this.E == 4.0f) {
                a(this.G);
            } else {
                a(this.I);
            }
        } else if (i == R.id.chin_level_box) {
            a(this.J, -50, 50);
        } else if (i == R.id.forehead_level_box) {
            a(this.K, -50, 50);
        } else if (i == R.id.thin_nose_level_box) {
            a(this.L);
        } else if (i == R.id.mouth_shape_box) {
            a(this.M, -50, 50);
        }
        a(false);
    }

    private void a(int i, float f) {
        Object obj = f >= 1000.0f ? 1 : null;
        if (i == R.id.beauty_all_blur_box) {
            if (this.c != null) {
                this.c.b(f);
            }
        } else if (i != R.id.beauty_type_box) {
            int i2 = R.id.beauty_blur_box;
            float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
            com.faceunity.beautycontrolview.a aVar;
            if (i == i2) {
                this.z = f;
                if (this.c != null) {
                    aVar = this.c;
                    if (obj == null) {
                        f2 = this.z;
                    }
                    aVar.d(f2);
                }
            } else if (i == R.id.beauty_color_box) {
                this.A = f;
                if (this.c != null) {
                    aVar = this.c;
                    if (obj == null) {
                        f2 = this.A;
                    }
                    aVar.e(f2);
                }
            } else if (i == R.id.beauty_red_box) {
                this.B = f;
                if (this.c != null) {
                    aVar = this.c;
                    if (obj == null) {
                        f2 = this.B;
                    }
                    aVar.f(f2);
                }
            } else if (i == R.id.beauty_bright_eyes_box) {
                this.C = f;
                if (this.c != null) {
                    aVar = this.c;
                    if (obj == null) {
                        f2 = this.C;
                    }
                    aVar.g(f2);
                }
            } else if (i == R.id.beauty_teeth_box) {
                this.D = f;
                if (this.c != null) {
                    aVar = this.c;
                    if (obj == null) {
                        f2 = this.D;
                    }
                    aVar.h(f2);
                }
            } else if (i == R.id.enlarge_eye_level_box) {
                if (this.E == 4.0f) {
                    this.F = f;
                    if (this.c != null) {
                        aVar = this.c;
                        if (obj == null) {
                            f2 = this.F;
                        }
                        aVar.j(f2);
                        return;
                    }
                    return;
                }
                this.H = f;
                if (this.c != null) {
                    aVar = this.c;
                    if (obj == null) {
                        f2 = this.H;
                    }
                    aVar.j(f2);
                }
            } else if (i != R.id.cheek_thin_level_box) {
                float f3 = 0.5f;
                if (i == R.id.chin_level_box) {
                    this.J = f;
                    if (this.c != null) {
                        aVar = this.c;
                        if (obj == null) {
                            f3 = this.J;
                        }
                        aVar.l(f3);
                    }
                } else if (i == R.id.forehead_level_box) {
                    this.K = f;
                    if (this.c != null) {
                        aVar = this.c;
                        if (obj == null) {
                            f3 = this.K;
                        }
                        aVar.m(f3);
                    }
                } else if (i == R.id.thin_nose_level_box) {
                    this.L = f;
                    if (this.c != null) {
                        aVar = this.c;
                        if (obj == null) {
                            f2 = this.L;
                        }
                        aVar.n(f2);
                    }
                } else if (i == R.id.mouth_shape_box) {
                    this.M = f;
                    if (this.c != null) {
                        aVar = this.c;
                        if (obj == null) {
                            f3 = this.M;
                        }
                        aVar.o(f3);
                    }
                }
            } else if (this.E == 4.0f) {
                this.G = f;
                if (this.c != null) {
                    aVar = this.c;
                    if (obj == null) {
                        f2 = this.G;
                    }
                    aVar.k(f2);
                }
            } else {
                this.I = f;
                if (this.c != null) {
                    aVar = this.c;
                    if (obj == null) {
                        f2 = this.I;
                    }
                    aVar.k(f2);
                }
            }
        } else if (this.c != null) {
            this.c.c(f);
        }
    }

    private void b(int i) {
        this.e.setVisibility(8);
        this.f.setVisibility(8);
        this.h.setVisibility(8);
        this.o.setVisibility(8);
        this.v.setVisibility(8);
        this.s.setVisibility(8);
        if (i == R.id.beauty_radio_skin_beauty) {
            this.e.setVisibility(0);
            this.f.setVisibility(0);
            a(this.g.getCheckedBeautyBoxId());
        } else if (i == R.id.beauty_radio_face_shape) {
            this.e.setVisibility(0);
            this.h.setVisibility(0);
            a(this.i.getCheckedBeautyBoxId());
        } else if (i == R.id.beauty_radio_beauty_filter) {
            this.p.a(1);
            this.e.setVisibility(0);
            this.o.setVisibility(0);
            if (this.P == 1) {
                this.p.a();
            }
        } else if (i == R.id.beauty_radio_filter) {
            this.p.a(0);
            this.e.setVisibility(0);
            this.o.setVisibility(0);
            if (this.P == 0) {
                this.p.a();
            }
        }
    }

    private void a(float f) {
        a(f, 0, 100);
    }

    private void a(float f, int i, int i2) {
        if (f < 1000.0f) {
            this.s.setVisibility(0);
            this.t.setMin(i);
            this.t.setMax(i2);
            this.t.setProgress((int) ((f * ((float) (i2 - i))) + ((float) i)));
        }
    }

    public float a(String str) {
        float f;
        Map map = this.w;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FaceBeautyFilterLevel_");
        stringBuilder.append(str);
        Float f2 = (Float) map.get(stringBuilder.toString());
        if (f2 == null) {
            f = 1.0f;
        } else {
            f = f2.floatValue();
        }
        setFaceBeautyFilterLevel(str, f);
        return f;
    }

    public void setFaceBeautyFilterLevel(String str, float f) {
        k.a().a(str, f);
        Map map = this.w;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FaceBeautyFilterLevel_");
        stringBuilder.append(str);
        map.put(stringBuilder.toString(), Float.valueOf(f));
        if (this.c != null) {
            this.c.a(f);
        }
    }

    public void setOnBottomAnimatorChangeListener(b bVar) {
        this.Q = bVar;
    }

    private void a(final boolean z) {
        if (this.R != null && this.R.isRunning()) {
            this.R.end();
        }
        final int height = getHeight();
        measure(0, MeasureSpec.makeMeasureSpec(0, 0));
        final int measuredHeight = getMeasuredHeight();
        if (height != measuredHeight) {
            this.R = ValueAnimator.ofInt(new int[]{height, measuredHeight}).setDuration(50);
            this.R.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    LayoutParams layoutParams = BeautyControlView.this.getLayoutParams();
                    layoutParams.height = intValue;
                    BeautyControlView.this.setLayoutParams(layoutParams);
                    if (z && BeautyControlView.this.Q != null) {
                        float f = (((float) (intValue - height)) * 1.0f) / ((float) (measuredHeight - height));
                        b J = BeautyControlView.this.Q;
                        if (height > measuredHeight) {
                            f = 1.0f - f;
                        }
                        J.a(f);
                    }
                }
            });
            this.R.start();
        }
    }

    public void setOnDescriptionShowListener(c cVar) {
        this.S = cVar;
    }

    private void setDescriptionShowStr(String str) {
        if (this.S != null) {
            this.S.a(str);
        }
    }

    public void setBeautyFilters(List<fv> list) {
        this.q = list;
    }

    public void setFilters(List<fv> list) {
        this.r = list;
    }
}
