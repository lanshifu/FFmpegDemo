package defpackage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import de.greenrobot.event.EventBus;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import org.json.JSONObject;

/* compiled from: AgentPicUploadUtils */
/* renamed from: qf */
public class qf {

    /* compiled from: AgentPicUploadUtils */
    /* renamed from: qf$a */
    public static class a {
        String a;
        String b;

        public String a() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public a(String str) {
            this.a = str;
        }

        public String b() {
            return this.a;
        }
    }

    public static InputStream a(Bitmap bitmap, double d) {
        if (d > 1.0d) {
            d = 1.0d;
        }
        if (d < 0.0d) {
            d = 0.0d;
        }
        int i = (int) (100.0d * d);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bitmap2InputStream quality is ");
        stringBuilder.append(d);
        stringBuilder.append(" iQuality is ");
        stringBuilder.append(i);
        Log.d("ImageUpLoad", stringBuilder.toString());
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("before compress size is ");
            stringBuilder2.append(byteArrayOutputStream.size());
            Log.d("ImageUpLoad", stringBuilder2.toString());
            byteArrayOutputStream.reset();
            bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("after compress size is ");
            stringBuilder3.append(byteArrayInputStream.available());
            Log.d("ImageUpLoad", stringBuilder3.toString());
            byteArrayOutputStream.close();
            return byteArrayInputStream;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(final String str, final double d, final String str2) {
        new Thread() {
            public void run() {
                String uuid = UUID.randomUUID().toString();
                String str = "--";
                String str2 = "\r\n";
                String str3 = "multipart/form-data";
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
                    httpURLConnection.setReadTimeout(300000);
                    httpURLConnection.setConnectTimeout(300000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    int i = 0;
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Charset", "chset");
                    httpURLConnection.setRequestProperty("connection", "keep-alive");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str3);
                    stringBuilder.append(";boundary=");
                    stringBuilder.append(uuid);
                    httpURLConnection.setRequestProperty("Content-Type", stringBuilder.toString());
                    Bitmap decodeFile = BitmapFactory.decodeFile(str);
                    String substring = str.substring(str.lastIndexOf("/") + 1);
                    String substring2 = substring.substring(substring.lastIndexOf(".") + 1);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("fileName is ");
                    stringBuilder2.append(substring);
                    stringBuilder2.append(" suffixStr is ");
                    stringBuilder2.append(substring2);
                    Log.d("ImageUpLoad", stringBuilder2.toString());
                    if (decodeFile != null) {
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(str);
                        stringBuffer.append(uuid);
                        stringBuffer.append(str2);
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Content-Disposition: form-data; name=\"pay_screen_shot\"; filename=\"");
                        stringBuilder3.append(substring);
                        stringBuilder3.append("\"");
                        stringBuilder3.append(str2);
                        stringBuffer.append(stringBuilder3.toString());
                        stringBuffer.append(String.format("Content-Type: image/%s; charset=%s%s", new Object[]{substring2, "chset", str2}));
                        stringBuffer.append(str2);
                        dataOutputStream.write(stringBuffer.toString().getBytes());
                        InputStream a = qf.a(decodeFile, d);
                        byte[] bArr = new byte[4096];
                        long j = 0;
                        long available = (long) a.available();
                        while (true) {
                            int read = a.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            dataOutputStream.write(bArr, i, read);
                            j += (long) read;
                            double d = (double) j;
                            double d2 = (double) available;
                            Double.isNaN(d);
                            Double.isNaN(d2);
                            d /= d2;
                            StringBuilder stringBuilder4 = new StringBuilder();
                            byte[] bArr2 = bArr;
                            stringBuilder4.append("ImageUpLoad percent is ");
                            stringBuilder4.append(d);
                            Log.d("ImageUpLoad", stringBuilder4.toString());
                            bArr = bArr2;
                            i = 0;
                        }
                        a.close();
                        dataOutputStream.write(str2.getBytes());
                        StringBuilder stringBuilder5 = new StringBuilder();
                        stringBuilder5.append(str);
                        stringBuilder5.append(uuid);
                        stringBuilder5.append(str);
                        stringBuilder5.append(str2);
                        dataOutputStream.write(stringBuilder5.toString().getBytes());
                        dataOutputStream.flush();
                        StringBuilder stringBuilder6 = new StringBuilder();
                        stringBuilder6.append("fileName totLen is ");
                        stringBuilder6.append(available);
                        stringBuilder6.append(" fileoutput len is ");
                        stringBuilder6.append(dataOutputStream.size());
                        Log.d("ImageUpLoad", stringBuilder6.toString());
                        if (httpURLConnection.getResponseCode() == 200) {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] bArr3 = new byte[Filter.K];
                            while (true) {
                                int read2 = inputStream.read(bArr3);
                                if (read2 == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr3, 0, read2);
                            }
                            str2 = byteArrayOutputStream.toString();
                            byteArrayOutputStream.close();
                            inputStream.close();
                            if (new JSONObject(str2).getInt("status") == 1) {
                                Log.d("ImageUpLoad", "上传成功");
                                a aVar = new a("成功");
                                aVar.a(str);
                                EventBus.getDefault().post(aVar);
                            } else {
                                Log.d("ImageUpLoad", "上传失败");
                                EventBus.getDefault().post(new a("失败"));
                            }
                        } else {
                            Log.d("ImageUpLoad", "访问上传地址失败");
                            EventBus.getDefault().post(new a("失败"));
                        }
                        dataOutputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    EventBus.getDefault().post(new a("失败"));
                }
            }
        }.start();
        return "";
    }
}
