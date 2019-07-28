package sj.keyboard.utils.imageloader;

import android.widget.ImageView;
import java.io.IOException;
import java.util.Locale;

public interface ImageBase {

    public enum Scheme {
        HTTP("http"),
        HTTPS("https"),
        FILE("file"),
        CONTENT("content"),
        ASSETS("assets"),
        DRAWABLE("drawable"),
        UNKNOWN("");
        
        private String scheme;
        private String uriPrefix;

        private Scheme(String str) {
            this.scheme = str;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("://");
            this.uriPrefix = stringBuilder.toString();
        }

        public static Scheme ofUri(String str) {
            if (str != null) {
                for (Scheme scheme : values()) {
                    if (scheme.belongsTo(str)) {
                        return scheme;
                    }
                }
            }
            return UNKNOWN;
        }

        public String toUri(String str) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.uriPrefix);
            stringBuilder.append(str);
            return stringBuilder.toString();
        }

        public String crop(String str) {
            if (belongsTo(str)) {
                return str.substring(this.uriPrefix.length());
            }
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{str, this.scheme}));
        }

        /* Access modifiers changed, original: protected */
        public boolean belongsTo(String str) {
            return str.toLowerCase(Locale.US).startsWith(this.uriPrefix);
        }

        public static String cropScheme(String str) throws IllegalArgumentException {
            return ofUri(str).crop(str);
        }
    }

    void displayImage(String str, ImageView imageView) throws IOException;
}
