package sj.keyboard.utils.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import sj.keyboard.utils.imageloader.ImageBase.Scheme;

public class ImageLoader implements ImageBase {
    private static volatile Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");
    private static volatile ImageLoader instance;
    protected final Context context;

    /* Access modifiers changed, original: protected */
    public void displayImageFromContent(String str, ImageView imageView) throws FileNotFoundException {
    }

    /* Access modifiers changed, original: protected */
    public void displayImageFromNetwork(String str, Object obj) throws IOException {
    }

    /* Access modifiers changed, original: protected */
    public void displayImageFromOtherSource(String str, ImageView imageView) throws IOException {
    }

    public static ImageLoader getInstance(Context context) {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader(context);
                }
            }
        }
        return instance;
    }

    public ImageLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    public void displayImage(String str, ImageView imageView) throws IOException {
        switch (Scheme.ofUri(str)) {
            case FILE:
                displayImageFromFile(str, imageView);
                return;
            case ASSETS:
                displayImageFromAssets(str, imageView);
                return;
            case DRAWABLE:
                displayImageFromDrawable(str, imageView);
                return;
            case HTTP:
            case HTTPS:
                displayImageFromNetwork(str, imageView);
                return;
            case CONTENT:
                displayImageFromContent(str, imageView);
                return;
            default:
                if (NUMBER_PATTERN.matcher(str).matches()) {
                    displayImageFromResource(Integer.parseInt(str), imageView);
                    return;
                } else {
                    displayImageFromOtherSource(str, imageView);
                    return;
                }
        }
    }

    /* Access modifiers changed, original: protected */
    public void displayImageFromFile(String str, ImageView imageView) throws IOException {
        str = Scheme.FILE.crop(str);
        if (new File(str).exists()) {
            try {
                Bitmap decodeFile = BitmapFactory.decodeFile(str);
                if (imageView != null) {
                    imageView.setImageBitmap(decodeFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void displayImageFromAssets(String str, ImageView imageView) throws IOException {
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(this.context.getAssets().open(Scheme.ASSETS.crop(str)));
            if (imageView != null) {
                imageView.setImageBitmap(decodeStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Access modifiers changed, original: protected */
    public void displayImageFromDrawable(String str, ImageView imageView) {
        str = Scheme.DRAWABLE.crop(str);
        int identifier = this.context.getResources().getIdentifier(str, "mipmap", this.context.getPackageName());
        if (identifier <= 0) {
            identifier = this.context.getResources().getIdentifier(str, "drawable", this.context.getPackageName());
        }
        if (identifier > 0 && imageView != null) {
            imageView.setImageResource(identifier);
        }
    }

    /* Access modifiers changed, original: protected */
    public void displayImageFromResource(int i, ImageView imageView) {
        if (i > 0 && imageView != null) {
            imageView.setImageResource(i);
        }
    }
}
