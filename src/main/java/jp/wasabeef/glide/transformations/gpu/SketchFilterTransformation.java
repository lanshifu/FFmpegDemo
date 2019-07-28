package jp.wasabeef.glide.transformations.gpu;

import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.GPUImageSketchFilter;

public class SketchFilterTransformation extends GPUFilterTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;

    public String toString() {
        return "SketchFilterTransformation()";
    }

    public SketchFilterTransformation() {
        super(new GPUImageSketchFilter());
    }

    public boolean equals(Object obj) {
        return obj instanceof SketchFilterTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
