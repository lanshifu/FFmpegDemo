package jp.wasabeef.glide.transformations.gpu;

import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.GPUImageToonFilter;

public class ToonFilterTransformation extends GPUFilterTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private float quantizationLevels;
    private float threshold;

    public ToonFilterTransformation() {
        this(0.2f, 10.0f);
    }

    public ToonFilterTransformation(float f, float f2) {
        super(new GPUImageToonFilter());
        this.threshold = f;
        this.quantizationLevels = f2;
        GPUImageToonFilter gPUImageToonFilter = (GPUImageToonFilter) getFilter();
        gPUImageToonFilter.setThreshold(this.threshold);
        gPUImageToonFilter.setQuantizationLevels(this.quantizationLevels);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ToonFilterTransformation(threshold=");
        stringBuilder.append(this.threshold);
        stringBuilder.append(",quantizationLevels=");
        stringBuilder.append(this.quantizationLevels);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof ToonFilterTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
