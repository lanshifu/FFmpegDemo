package jp.wasabeef.glide.transformations.gpu;

import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;

public class SepiaFilterTransformation extends GPUFilterTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private float intensity;

    public SepiaFilterTransformation() {
        this(1.0f);
    }

    public SepiaFilterTransformation(float f) {
        super(new GPUImageSepiaFilter());
        this.intensity = f;
        ((GPUImageSepiaFilter) getFilter()).setIntensity(this.intensity);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SepiaFilterTransformation(intensity=");
        stringBuilder.append(this.intensity);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof SepiaFilterTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
