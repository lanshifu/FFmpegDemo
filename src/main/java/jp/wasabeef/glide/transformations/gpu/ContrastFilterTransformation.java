package jp.wasabeef.glide.transformations.gpu;

import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;

public class ContrastFilterTransformation extends GPUFilterTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private float contrast;

    public ContrastFilterTransformation() {
        this(1.0f);
    }

    public ContrastFilterTransformation(float f) {
        super(new GPUImageContrastFilter());
        this.contrast = f;
        ((GPUImageContrastFilter) getFilter()).setContrast(this.contrast);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ContrastFilterTransformation(contrast=");
        stringBuilder.append(this.contrast);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof ContrastFilterTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
