package com.tencent.liteav.beauty.b;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.util.Log;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: TXCGPUloopupInvertFilter */
public class ai extends s {
    private String x = "Lighting";
    private int y = -1;

    public ai(Bitmap bitmap) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\nuniform sampler2D inputImageTexture2; // lookup texture\n\nuniform lowp float intensity;     // 粉嫩级别\nuniform lowp float invertLevel;   // 颜色反转级别\n\nmediump float rgb2v(mediump vec3 c)\n{\n    mediump vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n    mediump vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));\n    mediump vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));\n\n    return q.x;\n}\n\n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n\n     mediump float blueColor = textureColor.b * 63.0;\n     \n     mediump vec2 quad1;\n     quad1.y = floor(floor(blueColor) / 8.0);\n     quad1.x = floor(blueColor) - (quad1.y * 8.0);\n     \n     mediump vec2 quad2;\n     quad2.y = floor(ceil(blueColor) / 8.0);\n     quad2.x = ceil(blueColor) - (quad2.y * 8.0);\n     \n     highp vec2 texPos1;\n     texPos1.x = (quad1.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);\n     texPos1.y = (quad1.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);\n     \n     highp vec2 texPos2;\n     texPos2.x = (quad2.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);\n     texPos2.y = (quad2.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);\n     \n     lowp vec4 newColor1 = texture2D(inputImageTexture2, texPos1);\n     lowp vec4 newColor2 = texture2D(inputImageTexture2, texPos2);\n     \n     lowp vec4 newColor = mix(newColor1, newColor2, fract(blueColor));\n     highp vec4 lookupColor = mix(textureColor, vec4(newColor.rgb, textureColor.w), intensity);\n\n    // 计算当前色彩亮度\n     mediump float hsv = rgb2v(textureColor.rgb);\n     mediump float u = 0.8;\n     mediump float x = hsv - u;\n     mediump float vDegree = exp(-1.0 * x * x);\n\n     // 防止人脸或亮度过高的地方，失去闪电效果\n     if (vDegree > 0.8){\n      vDegree = 0.8;\n     }\n     highp vec4 invertBlenColor = lookupColor;\n     if (invertLevel > 0.0){\n        highp vec4 invertColor = vec4((1.0 - textureColor.rgb), textureColor.w);\n\n        invertBlenColor = mix(lookupColor, invertColor, (1.0 - vDegree) * invertLevel);\n     }\n\n     gl_FragColor = invertBlenColor;\n }\n");
        this.s = bitmap;
    }

    public boolean a() {
        if (super.a()) {
            this.y = GLES20.glGetUniformLocation(q(), "invertLevel");
            a((float) CropImageView.DEFAULT_ASPECT_RATIO);
            return true;
        }
        Log.e(this.x, "super.onInit failed");
        return false;
    }

    public void b(float f) {
        a(this.y, f);
    }
}
