package com.tomatolive.library.download;

import com.blankj.utilcode.util.i;
import java.io.File;

public enum GiftConfig {
    INSTANCE;
    
    private final String ANIM_CAR_DIR_NAME;
    private final String ANIM_ROOT_DIR_NAME;
    private final String APP_ROOT_DIR_NAME;
    public final String CAR_CONFIG_NAME;
    public final String CONFIG_NAME;
    public String animResRootPath;
    public String carAnimResRootPath;

    private String getAnimResRootPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i.b());
        stringBuilder.append(File.separator);
        stringBuilder.append("giftRes");
        stringBuilder.append(File.separator);
        stringBuilder.append(this.ANIM_ROOT_DIR_NAME);
        stringBuilder.append(File.separator);
        File file = new File(stringBuilder.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return stringBuilder.toString();
    }

    private String getCarAnimResRootPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i.b());
        stringBuilder.append(File.separator);
        stringBuilder.append("giftRes");
        stringBuilder.append(File.separator);
        stringBuilder.append(this.ANIM_CAR_DIR_NAME);
        stringBuilder.append(File.separator);
        File file = new File(stringBuilder.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return stringBuilder.toString();
    }
}
