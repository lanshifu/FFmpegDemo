package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Movie;
import com.zzhoujay.richtext.ImageHolder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: SourceDecode */
/* renamed from: uz */
abstract class uz<T> {
    static uz<byte[]> a = new 1();
    static uz<String> b = new 2();
    static uz<InputStream> c = new 3();

    /* compiled from: SourceDecode */
    /* renamed from: uz$1 */
    static class 1 extends uz<byte[]> {
        1() {
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: a */
        public void b(byte[] bArr, Options options) {
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        }

        /* renamed from: b */
        public uw c(byte[] bArr, Options options) {
            return uw.a(BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options));
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: c */
        public uw d(byte[] bArr, Options options) {
            return uw.a(new ud(Movie.decodeByteArray(bArr, 0, bArr.length), options.outHeight, options.outWidth));
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: d */
        public boolean a(byte[] bArr, Options options) {
            return ui.a(bArr);
        }
    }

    /* compiled from: SourceDecode */
    /* renamed from: uz$2 */
    static class 2 extends uz<String> {
        2() {
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: a */
        public void b(String str, Options options) {
            BitmapFactory.decodeFile(str, options);
        }

        /* renamed from: b */
        public uw c(String str, Options options) {
            return uw.a(BitmapFactory.decodeFile(str, options));
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: c */
        public uw d(String str, Options options) {
            return uw.a(new ud(Movie.decodeFile(str), options.outHeight, options.outWidth));
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: d */
        public boolean a(String str, Options options) {
            return ui.a(str);
        }
    }

    /* compiled from: SourceDecode */
    /* renamed from: uz$3 */
    static class 3 extends uz<InputStream> {
        3() {
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: a */
        public void b(InputStream inputStream, Options options) {
            BufferedInputStream bufferedInputStream;
            if (inputStream instanceof BufferedInputStream) {
                bufferedInputStream = (BufferedInputStream) inputStream;
            } else {
                bufferedInputStream = new BufferedInputStream(inputStream);
            }
            if (options.inJustDecodeBounds) {
                bufferedInputStream.mark(1048576);
            }
            BitmapFactory.decodeStream(bufferedInputStream, null, options);
            if (options.inJustDecodeBounds) {
                try {
                    bufferedInputStream.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /* renamed from: b */
        public uw c(InputStream inputStream, Options options) {
            BufferedInputStream bufferedInputStream;
            if (inputStream instanceof BufferedInputStream) {
                bufferedInputStream = (BufferedInputStream) inputStream;
            } else {
                bufferedInputStream = new BufferedInputStream(inputStream);
            }
            if (options.inJustDecodeBounds) {
                bufferedInputStream.mark(1048576);
            }
            Bitmap decodeStream = BitmapFactory.decodeStream(bufferedInputStream, null, options);
            if (options.inJustDecodeBounds) {
                try {
                    bufferedInputStream.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return uw.a(decodeStream);
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: c */
        public uw d(InputStream inputStream, Options options) {
            return uw.a(new ud(Movie.decodeStream(inputStream), options.outHeight, options.outWidth));
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: d */
        public boolean a(InputStream inputStream, Options options) {
            return ui.a(inputStream);
        }
    }

    public abstract boolean a(T t, Options options);

    public abstract void b(T t, Options options);

    public abstract uw c(T t, Options options);

    public abstract uw d(T t, Options options);

    uz() {
    }

    /* Access modifiers changed, original: 0000 */
    public uw a(ImageHolder imageHolder, T t, Options options) {
        if (!imageHolder.g() || (!imageHolder.f() && !a(t, options))) {
            return c(t, options);
        }
        imageHolder.a(true);
        return d(t, options);
    }
}
