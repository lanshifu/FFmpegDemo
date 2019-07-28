package defpackage;

import android.text.TextUtils;
import com.one.tomato.thirdpart.m3u8.download.entity.M3U8;
import com.one.tomato.thirdpart.m3u8.download.entity.M3U8Ts;
import com.one.tomato.utils.k;
import com.one.tomato.utils.o;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/* compiled from: MUtils */
/* renamed from: pl */
public class pl {
    public static M3U8 a(String str) throws Exception {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        M3U8 m3u8 = new M3U8();
        m3u8.setUrl(str);
        m3u8.setBaseUrl(pl.f(str));
        String c = pl.c(str);
        File file = new File(c);
        if (!file.exists()) {
            file.mkdirs();
        }
        m3u8.setDirFilePath(c);
        String e = pl.e(str);
        File file2 = new File(c, e);
        Object obj = (!file2.exists() || file2.length() <= 0) ? null : 1;
        File file3 = null;
        if (obj != null) {
            o.a("Download", "本地已存在m3u8文件");
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
            bufferedWriter = null;
        } else {
            o.a("Download", "本地没有m3u8文件，网络读流");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(e);
            stringBuilder.append(".download");
            file3 = new File(c, stringBuilder.toString());
            bufferedWriter = new BufferedWriter(new FileWriter(file3));
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(str).openStream()));
        }
        loop0:
        while (true) {
            String readLine;
            float f = CropImageView.DEFAULT_ASPECT_RATIO;
            while (true) {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break loop0;
                }
                if (obj == null) {
                    bufferedWriter.write(readLine);
                    bufferedWriter.write(System.getProperty("line.separator"));
                }
                if (!readLine.startsWith("#")) {
                    break;
                } else if (readLine.startsWith("#EXTINF:")) {
                    String substring = readLine.substring(8);
                    if (substring.endsWith(",")) {
                        substring = substring.substring(0, substring.length() - 1);
                    }
                    f = Float.parseFloat(substring);
                }
            }
            m3u8.addTs(new M3U8Ts(readLine, f));
        }
        if (obj == null) {
            if (file3.renameTo(file2)) {
                o.a("Download", "网络下载m3u8文件到本地成功");
                bufferedWriter.flush();
                bufferedWriter.close();
            } else {
                throw new Exception("替换m3u8文件失败");
            }
        }
        bufferedReader.close();
        return m3u8;
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains("?")) {
            str = str.substring(0, str.lastIndexOf(63));
        }
        return str.substring(str.lastIndexOf(47) + 1, str.length());
    }

    private static String f(String str) {
        return str.substring(0, str.lastIndexOf(47));
    }

    public static String b(String str) {
        if (str.contains("_s3")) {
            return pl.g(str.substring(str.indexOf("_s3")));
        }
        return pl.g(str);
    }

    private static String g(String str) {
        int lastIndexOf = str.lastIndexOf(63);
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    public static String c(String str) {
        str = pl.b(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(k.f().getPath());
        stringBuilder.append(File.separator);
        stringBuilder.append(pv.a(str));
        return stringBuilder.toString();
    }

    public static boolean d(String str) {
        String c = pl.c(str);
        File file = new File(c, pl.e(str));
        if (!file.exists() || file.length() == 0) {
            return false;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return true;
                } else if (!readLine.startsWith("#")) {
                    if (!new File(c, readLine).exists()) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
