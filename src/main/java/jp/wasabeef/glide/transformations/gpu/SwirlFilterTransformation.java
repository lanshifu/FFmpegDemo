package jp.wasabeef.glide.transformations.gpu;

import android.graphics.PointF;
import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.GPUImageSwirlFilter;

public class SwirlFilterTransformation extends GPUFilterTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private float angle;
    private PointF center;
    private float radius;

    public SwirlFilterTransformation() {
        this(0.5f, 1.0f, new PointF(0.5f, 0.5f));
    }

    public SwirlFilterTransformation(float f, float f2, PointF pointF) {
        super(new GPUImageSwirlFilter());
        this.radius = f;
        this.angle = f2;
        this.center = pointF;
        GPUImageSwirlFilter gPUImageSwirlFilter = (GPUImageSwirlFilter) getFilter();
        gPUImageSwirlFilter.setRadius(this.radius);
        gPUImageSwirlFilter.setAngle(this.angle);
        gPUImageSwirlFilter.setCenter(this.center);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SwirlFilterTransformation(radius=");
        stringBuilder.append(this.radius);
        stringBuilder.append(",angle=");
        stringBuilder.append(this.angle);
        stringBuilder.append(",center=");
        stringBuilder.append(this.center.toString());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof SwirlFilterTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
