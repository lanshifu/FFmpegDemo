package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import com.tencent.rtmp.TXLiveConstants;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: PictureFileUtils */
/* renamed from: my */
public class my {
    private static String a = "picture_cache";

    public static File a(Context context, int i, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            str = "/DCIM/Tomato/";
        }
        return my.a(context, str, i, str2);
    }

    private static File a(Context context, String str, int i, String str2) {
        File externalStorageDirectory = Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : context.getCacheDir();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(externalStorageDirectory.getAbsolutePath());
        stringBuilder.append(str);
        File file = new File(stringBuilder.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Tomato_");
        stringBuilder2.append(format);
        stringBuilder2.append("");
        format = stringBuilder2.toString();
        StringBuilder stringBuilder3;
        switch (i) {
            case 1:
                if (TextUtils.isEmpty(str2)) {
                    str2 = ".JPEG";
                }
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(format);
                stringBuilder3.append(str2);
                return new File(file, stringBuilder3.toString());
            case 2:
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(format);
                stringBuilder3.append(".mp4");
                return new File(file, stringBuilder3.toString());
            default:
                return null;
        }
    }

    public static int a(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                return TXLiveConstants.RENDER_ROTATION_180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return 270;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap a(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("angle2=");
        stringBuilder.append(i);
        printStream.println(stringBuilder.toString());
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void a(Bitmap bitmap, File file) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String a(Context context, String str, String str2) {
        File file;
        File externalStorageDirectory = Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : context.getCacheDir();
        if (TextUtils.isEmpty(str2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(externalStorageDirectory.getAbsolutePath());
            stringBuilder.append("/DCIM/Tomato/");
            file = new File(stringBuilder.toString());
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(externalStorageDirectory.getAbsolutePath());
            stringBuilder2.append(str2);
            file = new File(stringBuilder2.toString());
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(file);
        stringBuilder3.append("/");
        stringBuilder3.append(str);
        return stringBuilder3.toString();
    }

    public static String a() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("%");
            stringBuilder.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
            stringBuilder.append("/Camera");
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(Context context) {
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath();
        }
        return context.getCacheDir().getPath();
    }
}
