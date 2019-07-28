package defpackage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.media.SoundPool;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.widget.ImageView.ScaleType;
import com.opensource.svgaplayer.c;
import com.opensource.svgaplayer.entities.SVGAVideoShapeEntity;
import com.opensource.svgaplayer.entities.b;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.qk.a;
import java.util.HashMap;
import kotlin.TypeCastException;
import kotlin.jvm.internal.f;

/* compiled from: SVGACanvasDrawer.kt */
/* renamed from: ql */
public final class ql extends qk {
    private final b a = new b();
    private final HashMap<String, Bitmap> b = new HashMap();
    private final a c = new a();
    private final float[] d = new float[16];
    private final c e;

    /* compiled from: SVGACanvasDrawer.kt */
    /* renamed from: ql$a */
    public static final class a {
        private int a;
        private int b;
        private final HashMap<SVGAVideoShapeEntity, Path> c = new HashMap();

        public final void a(Canvas canvas) {
            f.b(canvas, "canvas");
            if (!(this.a == canvas.getWidth() && this.b == canvas.getHeight())) {
                this.c.clear();
            }
            this.a = canvas.getWidth();
            this.b = canvas.getHeight();
        }

        public final Path a(SVGAVideoShapeEntity sVGAVideoShapeEntity) {
            f.b(sVGAVideoShapeEntity, "shape");
            if (!this.c.containsKey(sVGAVideoShapeEntity)) {
                Path path = new Path();
                path.set(sVGAVideoShapeEntity.d());
                this.c.put(sVGAVideoShapeEntity, path);
            }
            Object obj = this.c.get(sVGAVideoShapeEntity);
            if (obj == null) {
                f.a();
            }
            return (Path) obj;
        }
    }

    /* compiled from: SVGACanvasDrawer.kt */
    /* renamed from: ql$b */
    public static final class b {
        private final Paint a = new Paint();
        private final Path b = new Path();
        private final Path c = new Path();
        private final Matrix d = new Matrix();
        private final Matrix e = new Matrix();

        public final Paint a() {
            this.a.reset();
            return this.a;
        }

        public final Path b() {
            this.b.reset();
            return this.b;
        }

        public final Path c() {
            this.c.reset();
            return this.c;
        }

        public final Matrix d() {
            this.d.reset();
            return this.d;
        }

        public final Matrix e() {
            this.e.reset();
            return this.e;
        }
    }

    public ql(com.opensource.svgaplayer.f fVar, c cVar) {
        f.b(fVar, "videoItem");
        f.b(cVar, "dynamicItem");
        super(fVar);
        this.e = cVar;
    }

    public void a(Canvas canvas, int i, ScaleType scaleType) {
        f.b(canvas, "canvas");
        f.b(scaleType, "scaleType");
        super.a(canvas, i, scaleType);
        this.c.a(canvas);
        for (a a : a(i)) {
            a(a, canvas, i);
        }
        b(i);
    }

    private final void b(int i) {
        for (com.opensource.svgaplayer.entities.a aVar : b().f()) {
            SoundPool g;
            Integer c;
            if (aVar.a() == i) {
                g = b().g();
                if (g != null) {
                    c = aVar.c();
                    if (c != null) {
                        aVar.a(Integer.valueOf(g.play(c.intValue(), 1.0f, 1.0f, 1, 0, 1.0f)));
                    }
                }
            }
            if (aVar.b() <= i) {
                c = aVar.d();
                if (c != null) {
                    int intValue = c.intValue();
                    g = b().g();
                    if (g != null) {
                        g.stop(intValue);
                    }
                }
                aVar.a((Integer) null);
            }
        }
    }

    private final Matrix a(Matrix matrix) {
        Matrix d = this.a.d();
        d.postScale(a().c(), a().d());
        d.postTranslate(a().a(), a().b());
        d.preConcat(matrix);
        return d;
    }

    private final void a(a aVar, Canvas canvas, int i) {
        a(aVar, canvas);
        b(aVar, canvas);
        b(aVar, canvas, i);
    }

    private final void a(a aVar, Canvas canvas) {
        String a = aVar.a();
        if (a != null && !f.a((Boolean) this.e.a().get(a), Boolean.valueOf(true))) {
            Bitmap bitmap = (Bitmap) this.e.b().get(a);
            if (bitmap == null) {
                bitmap = (Bitmap) b().h().get(a);
            }
            if (bitmap != null) {
                Matrix a2 = a(aVar.b().c());
                Paint a3 = this.a.a();
                a3.setAntiAlias(b().a());
                a3.setFilterBitmap(b().a());
                double a4 = aVar.b().a();
                double d = (double) 255;
                Double.isNaN(d);
                a3.setAlpha((int) (a4 * d));
                float f;
                double a5;
                double width;
                if (aVar.b().d() != null) {
                    b d2 = aVar.b().d();
                    if (d2 != null) {
                        canvas.save();
                        a3.reset();
                        Path b = this.a.b();
                        d2.a(b);
                        b.transform(a2);
                        canvas.clipPath(b);
                        a4 = aVar.b().b().a();
                        d = (double) bitmap.getWidth();
                        Double.isNaN(d);
                        f = (float) (a4 / d);
                        a5 = aVar.b().b().a();
                        width = (double) bitmap.getWidth();
                        Double.isNaN(width);
                        a2.preScale(f, (float) (a5 / width));
                        canvas.drawBitmap(bitmap, a2, a3);
                        canvas.restore();
                    } else {
                        return;
                    }
                }
                a4 = aVar.b().b().a();
                d = (double) bitmap.getWidth();
                Double.isNaN(d);
                f = (float) (a4 / d);
                a5 = aVar.b().b().a();
                width = (double) bitmap.getWidth();
                Double.isNaN(width);
                a2.preScale(f, (float) (a5 / width));
                canvas.drawBitmap(bitmap, a2, a3);
                a(canvas, bitmap, aVar, a2);
            }
        }
    }

    private final void a(Canvas canvas, Bitmap bitmap, a aVar, Matrix matrix) {
        Canvas canvas2 = canvas;
        Matrix matrix2 = matrix;
        if (this.e.g()) {
            this.b.clear();
            this.e.a(false);
        }
        String a = aVar.a();
        if (a != null) {
            Bitmap bitmap2;
            Bitmap bitmap3 = (Bitmap) null;
            String str = (String) this.e.c().get(a);
            if (str != null) {
                Object obj = (TextPaint) this.e.d().get(a);
                if (obj != null) {
                    bitmap3 = (Bitmap) this.b.get(a);
                    if (bitmap3 == null) {
                        bitmap3 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
                        Canvas canvas3 = new Canvas(bitmap3);
                        f.a(obj, "drawingTextPaint");
                        obj.setAntiAlias(true);
                        Rect rect = new Rect();
                        obj.getTextBounds(str, 0, str.length(), rect);
                        double width = (double) (bitmap.getWidth() - rect.width());
                        Double.isNaN(width);
                        canvas3.drawText(str, (float) (width / 2.0d), ((((float) (bitmap.getHeight() + 0)) - obj.getFontMetrics().bottom) - obj.getFontMetrics().top) / ((float) 2), (Paint) obj);
                        HashMap hashMap = this.b;
                        if (bitmap3 != null) {
                            bitmap2 = (Bitmap) hashMap.put(a, bitmap3);
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type android.graphics.Bitmap");
                        }
                    }
                }
            }
            Object obj2 = (StaticLayout) this.e.e().get(a);
            if (obj2 != null) {
                bitmap3 = (Bitmap) this.b.get(a);
                if (bitmap3 == null) {
                    f.a(obj2, "it");
                    Object paint = obj2.getPaint();
                    f.a(paint, "it.paint");
                    paint.setAntiAlias(true);
                    StaticLayout staticLayout = new StaticLayout(obj2.getText(), 0, obj2.getText().length(), obj2.getPaint(), bitmap.getWidth(), obj2.getAlignment(), obj2.getSpacingMultiplier(), obj2.getSpacingAdd(), false);
                    bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
                    Canvas canvas4 = new Canvas(bitmap2);
                    canvas4.translate(CropImageView.DEFAULT_ASPECT_RATIO, (float) ((bitmap.getHeight() - staticLayout.getHeight()) / 2));
                    staticLayout.draw(canvas4);
                    HashMap hashMap2 = this.b;
                    if (bitmap2 != null) {
                        Bitmap bitmap4 = (Bitmap) hashMap2.put(a, bitmap2);
                        bitmap3 = bitmap2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type android.graphics.Bitmap");
                    }
                }
            }
            if (bitmap3 != null) {
                Paint a2 = this.a.a();
                a2.setAntiAlias(b().a());
                if (aVar.b().d() != null) {
                    b d = aVar.b().d();
                    if (d != null) {
                        canvas.save();
                        canvas2.concat(matrix2);
                        canvas2.clipRect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                        a2.setShader(new BitmapShader(bitmap3, TileMode.REPEAT, TileMode.REPEAT));
                        Path b = this.a.b();
                        d.a(b);
                        canvas2.drawPath(b, a2);
                        canvas.restore();
                    }
                } else {
                    a2.setFilterBitmap(b().a());
                    canvas2.drawBitmap(bitmap3, matrix2, a2);
                }
            }
        }
    }

    private final void b(a aVar, Canvas canvas) {
        Matrix a = a(aVar.b().c());
        for (SVGAVideoShapeEntity sVGAVideoShapeEntity : aVar.b().e()) {
            sVGAVideoShapeEntity.e();
            if (sVGAVideoShapeEntity.d() != null) {
                Paint a2 = this.a.a();
                a2.reset();
                a2.setAntiAlias(b().a());
                double a3 = aVar.b().a();
                double d = (double) 255;
                Double.isNaN(d);
                a2.setAlpha((int) (a3 * d));
                Path b = this.a.b();
                b.reset();
                b.addPath(this.c.a(sVGAVideoShapeEntity));
                Matrix e = this.a.e();
                e.reset();
                Matrix b2 = sVGAVideoShapeEntity.b();
                if (b2 != null) {
                    e.postConcat(b2);
                }
                e.postConcat(a);
                b.transform(e);
                SVGAVideoShapeEntity.a a4 = sVGAVideoShapeEntity.a();
                if (a4 != null) {
                    int a5 = a4.a();
                    if (a5 != 0) {
                        a2.setStyle(Style.FILL);
                        a2.setColor(a5);
                        double a6 = aVar.b().a();
                        Double.isNaN(d);
                        a2.setAlpha(Math.min(255, Math.max(0, (int) (a6 * d))));
                        if (aVar.b().d() != null) {
                            canvas.save();
                        }
                        b d2 = aVar.b().d();
                        if (d2 != null) {
                            Path c = this.a.c();
                            d2.a(c);
                            c.transform(a);
                            canvas.clipPath(c);
                        }
                        canvas.drawPath(b, a2);
                        if (aVar.b().d() != null) {
                            canvas.restore();
                        }
                    }
                }
                a4 = sVGAVideoShapeEntity.a();
                if (a4 != null) {
                    float f = (float) null;
                    if (a4.c() > f) {
                        String d3;
                        a2.setStyle(Style.STROKE);
                        a4 = sVGAVideoShapeEntity.a();
                        if (a4 != null) {
                            a2.setColor(a4.b());
                            double a7 = aVar.b().a();
                            Double.isNaN(d);
                            a2.setAlpha(Math.min(255, Math.max(0, (int) (a7 * d))));
                        }
                        float b3 = b(a);
                        SVGAVideoShapeEntity.a a8 = sVGAVideoShapeEntity.a();
                        if (a8 != null) {
                            a2.setStrokeWidth(a8.c() * b3);
                        }
                        a8 = sVGAVideoShapeEntity.a();
                        if (a8 != null) {
                            d3 = a8.d();
                            if (d3 != null) {
                                if (m.a(d3, "butt", true)) {
                                    a2.setStrokeCap(Cap.BUTT);
                                } else if (m.a(d3, "round", true)) {
                                    a2.setStrokeCap(Cap.ROUND);
                                } else if (m.a(d3, "square", true)) {
                                    a2.setStrokeCap(Cap.SQUARE);
                                }
                            }
                        }
                        a8 = sVGAVideoShapeEntity.a();
                        if (a8 != null) {
                            d3 = a8.e();
                            if (d3 != null) {
                                if (m.a(d3, "miter", true)) {
                                    a2.setStrokeJoin(Join.MITER);
                                } else if (m.a(d3, "round", true)) {
                                    a2.setStrokeJoin(Join.ROUND);
                                } else if (m.a(d3, "bevel", true)) {
                                    a2.setStrokeJoin(Join.BEVEL);
                                }
                            }
                        }
                        a8 = sVGAVideoShapeEntity.a();
                        if (a8 != null) {
                            a2.setStrokeMiter(((float) a8.f()) * b3);
                        }
                        SVGAVideoShapeEntity.a a9 = sVGAVideoShapeEntity.a();
                        if (a9 != null) {
                            float[] g = a9.g();
                            if (g != null && g.length == 3 && (g[0] > f || g[1] > f)) {
                                float[] fArr = new float[2];
                                float f2 = 1.0f;
                                if (g[0] >= 1.0f) {
                                    f2 = g[0];
                                }
                                fArr[0] = f2 * b3;
                                float f3 = 0.1f;
                                if (g[1] >= 0.1f) {
                                    f3 = g[1];
                                }
                                fArr[1] = f3 * b3;
                                a2.setPathEffect(new DashPathEffect(fArr, g[2] * b3));
                            }
                        }
                        if (aVar.b().d() != null) {
                            canvas.save();
                        }
                        b d4 = aVar.b().d();
                        if (d4 != null) {
                            Path c2 = this.a.c();
                            d4.a(c2);
                            c2.transform(a);
                            canvas.clipPath(c2);
                        }
                        canvas.drawPath(b, a2);
                        if (aVar.b().d() != null) {
                            canvas.restore();
                        }
                    }
                }
            }
        }
    }

    private final float b(Matrix matrix) {
        matrix.getValues(this.d);
        if (this.d[0] == CropImageView.DEFAULT_ASPECT_RATIO) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        double d = (double) this.d[0];
        double d2 = (double) this.d[3];
        double d3 = (double) this.d[1];
        double d4 = (double) this.d[4];
        Double.isNaN(d);
        Double.isNaN(d4);
        double d5 = d * d4;
        Double.isNaN(d2);
        Double.isNaN(d3);
        if (d5 == d2 * d3) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        Double.isNaN(d);
        Double.isNaN(d);
        double d6 = d * d;
        Double.isNaN(d2);
        Double.isNaN(d2);
        d6 = Math.sqrt(d6 + (d2 * d2));
        Double.isNaN(d);
        d /= d6;
        Double.isNaN(d2);
        d2 /= d6;
        Double.isNaN(d3);
        d5 = d * d3;
        Double.isNaN(d4);
        d5 += d2 * d4;
        double d7 = d * d5;
        Double.isNaN(d3);
        d3 -= d7;
        d5 *= d2;
        Double.isNaN(d4);
        d4 -= d5;
        d5 = Math.sqrt((d3 * d3) + (d4 * d4));
        if (d * (d4 / d5) < d2 * (d3 / d5)) {
            d6 = -d6;
        }
        return Math.abs(a().e() ? (float) d6 : (float) d5);
    }

    private final void b(a aVar, Canvas canvas, int i) {
        String a = aVar.a();
        if (a != null) {
            xx xxVar = (xx) this.e.f().get(a);
            if (xxVar != null) {
                Matrix a2 = a(aVar.b().c());
                canvas.save();
                canvas.concat(a2);
                xxVar.invoke(canvas, Integer.valueOf(i));
                canvas.restore();
            }
        }
    }
}
