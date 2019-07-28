package jp.wasabeef.glide.transformations.gpu;

import android.graphics.PointF;
import com.yalantis.ucrop.view.CropImageView;
import java.security.MessageDigest;
import java.util.Arrays;
import jp.co.cyberagent.android.gpuimage.GPUImageVignetteFilter;

public class VignetteFilterTransformation extends GPUFilterTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private PointF center;
    private float[] vignetteColor;
    private float vignetteEnd;
    private float vignetteStart;

    public VignetteFilterTransformation() {
        this(new PointF(0.5f, 0.5f), new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO}, CropImageView.DEFAULT_ASPECT_RATIO, 0.75f);
    }

    public VignetteFilterTransformation(PointF pointF, float[] fArr, float f, float f2) {
        super(new GPUImageVignetteFilter());
        this.center = pointF;
        this.vignetteColor = fArr;
        this.vignetteStart = f;
        this.vignetteEnd = f2;
        GPUImageVignetteFilter gPUImageVignetteFilter = (GPUImageVignetteFilter) getFilter();
        gPUImageVignetteFilter.setVignetteCenter(this.center);
        gPUImageVignetteFilter.setVignetteColor(this.vignetteColor);
        gPUImageVignetteFilter.setVignetteStart(this.vignetteStart);
        gPUImageVignetteFilter.setVignetteEnd(this.vignetteEnd);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("VignetteFilterTransformation(center=");
        stringBuilder.append(this.center.toString());
        stringBuilder.append(",color=");
        stringBuilder.append(Arrays.toString(this.vignetteColor));
        stringBuilder.append(",start=");
        stringBuilder.append(this.vignetteStart);
        stringBuilder.append(",end=");
        stringBuilder.append(this.vignetteEnd);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof VignetteFilterTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
