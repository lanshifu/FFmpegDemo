package jp.wasabeef.glide.transformations.gpu;

import com.yalantis.ucrop.view.CropImageView;
import java.security.MessageDigest;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;

public class BrightnessFilterTransformation extends GPUFilterTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private float brightness;

    public BrightnessFilterTransformation() {
        this(CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public BrightnessFilterTransformation(float f) {
        super(new GPUImageBrightnessFilter());
        this.brightness = f;
        ((GPUImageBrightnessFilter) getFilter()).setBrightness(this.brightness);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BrightnessFilterTransformation(brightness=");
        stringBuilder.append(this.brightness);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof BrightnessFilterTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
