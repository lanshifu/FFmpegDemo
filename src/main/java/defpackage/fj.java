package defpackage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* compiled from: FileUtils */
/* renamed from: fj */
public class fj {
    public static void a(StringBuffer stringBuffer, String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        fj.a(bufferedReader, stringBuffer);
        bufferedReader.close();
        fileInputStream.close();
    }

    public static void a(StringBuffer stringBuffer, byte[] bArr) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr)));
        fj.a(bufferedReader, stringBuffer);
        bufferedReader.close();
    }

    public static void a(BufferedReader bufferedReader, StringBuffer stringBuffer) throws IOException {
        String readLine = bufferedReader.readLine();
        while (readLine != null) {
            if (readLine != null && readLine.trim().toLowerCase().endsWith(".ts")) {
                stringBuffer.append(readLine);
                stringBuffer.append(System.getProperty("line.separator"));
            } else if (readLine == null || !readLine.trim().toUpperCase().startsWith("#EXTINF")) {
                String str = "#EXT-SECRET-KEY:";
                String str2 = "#EXT-SECRET-KEY-INDEX:";
                if (readLine.startsWith(str)) {
                    eh.b(readLine.substring(str.length()));
                } else if (readLine.startsWith(str2)) {
                    eh.a(Integer.parseInt(readLine.substring(str2.length())));
                }
                stringBuffer.append(readLine);
                stringBuffer.append(System.getProperty("line.separator"));
            } else {
                stringBuffer.append(readLine);
                stringBuffer.append(System.getProperty("line.separator"));
            }
            readLine = bufferedReader.readLine();
        }
    }
}
