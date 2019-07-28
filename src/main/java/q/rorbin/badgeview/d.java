package q.rorbin.badgeview;

import android.graphics.PointF;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

/* compiled from: MathUtil */
public class d {
    public static double a(double d) {
        return (d / 6.283185307179586d) * 360.0d;
    }

    public static double a(double d, int i) {
        if (d < 0.0d) {
            d += 1.5707963267948966d;
        }
        double d2 = (double) (i - 1);
        Double.isNaN(d2);
        return d + (d2 * 1.5707963267948966d);
    }

    public static int a(PointF pointF, PointF pointF2) {
        if (pointF.x > pointF2.x) {
            if (pointF.y > pointF2.y) {
                return 4;
            }
            if (pointF.y < pointF2.y) {
                return 1;
            }
        } else if (pointF.x < pointF2.x) {
            if (pointF.y > pointF2.y) {
                return 3;
            }
            if (pointF.y < pointF2.y) {
                return 2;
            }
        }
        return -1;
    }

    public static float b(PointF pointF, PointF pointF2) {
        return (float) Math.sqrt(Math.pow((double) (pointF.x - pointF2.x), 2.0d) + Math.pow((double) (pointF.y - pointF2.y), 2.0d));
    }

    public static void a(PointF pointF, float f, Double d, List<PointF> list) {
        float f2;
        if (d != null) {
            double atan = (double) ((float) Math.atan(d.doubleValue()));
            double cos = Math.cos(atan);
            double d2 = (double) f;
            Double.isNaN(d2);
            float f3 = (float) (cos * d2);
            atan = Math.sin(atan);
            Double.isNaN(d2);
            f2 = (float) (atan * d2);
            f = f3;
        } else {
            f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        list.add(new PointF(pointF.x + f, pointF.y + f2));
        list.add(new PointF(pointF.x - f, pointF.y - f2));
    }
}
