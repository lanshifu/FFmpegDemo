package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: OptAnimationLoader */
/* renamed from: ml */
public class ml {
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005e  */
    public static android.view.animation.Animation a(android.content.Context r4, int r5) throws android.content.res.Resources.NotFoundException {
        /*
        r0 = 0;
        r1 = r4.getResources();	 Catch:{ XmlPullParserException -> 0x003d, IOException -> 0x001e }
        r1 = r1.getAnimation(r5);	 Catch:{ XmlPullParserException -> 0x003d, IOException -> 0x001e }
        r4 = defpackage.ml.a(r4, r1);	 Catch:{ XmlPullParserException -> 0x0019, IOException -> 0x0016, all -> 0x0013 }
        if (r1 == 0) goto L_0x0012;
    L_0x000f:
        r1.close();
    L_0x0012:
        return r4;
    L_0x0013:
        r4 = move-exception;
        r0 = r1;
        goto L_0x005c;
    L_0x0016:
        r4 = move-exception;
        r0 = r1;
        goto L_0x001f;
    L_0x0019:
        r4 = move-exception;
        r0 = r1;
        goto L_0x003e;
    L_0x001c:
        r4 = move-exception;
        goto L_0x005c;
    L_0x001e:
        r4 = move-exception;
    L_0x001f:
        r1 = new android.content.res.Resources$NotFoundException;	 Catch:{ all -> 0x001c }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x001c }
        r2.<init>();	 Catch:{ all -> 0x001c }
        r3 = "Can't load animation resource ID #0x";
        r2.append(r3);	 Catch:{ all -> 0x001c }
        r5 = java.lang.Integer.toHexString(r5);	 Catch:{ all -> 0x001c }
        r2.append(r5);	 Catch:{ all -> 0x001c }
        r5 = r2.toString();	 Catch:{ all -> 0x001c }
        r1.<init>(r5);	 Catch:{ all -> 0x001c }
        r1.initCause(r4);	 Catch:{ all -> 0x001c }
        throw r1;	 Catch:{ all -> 0x001c }
    L_0x003d:
        r4 = move-exception;
    L_0x003e:
        r1 = new android.content.res.Resources$NotFoundException;	 Catch:{ all -> 0x001c }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x001c }
        r2.<init>();	 Catch:{ all -> 0x001c }
        r3 = "Can't load animation resource ID #0x";
        r2.append(r3);	 Catch:{ all -> 0x001c }
        r5 = java.lang.Integer.toHexString(r5);	 Catch:{ all -> 0x001c }
        r2.append(r5);	 Catch:{ all -> 0x001c }
        r5 = r2.toString();	 Catch:{ all -> 0x001c }
        r1.<init>(r5);	 Catch:{ all -> 0x001c }
        r1.initCause(r4);	 Catch:{ all -> 0x001c }
        throw r1;	 Catch:{ all -> 0x001c }
    L_0x005c:
        if (r0 == 0) goto L_0x0061;
    L_0x005e:
        r0.close();
    L_0x0061:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ml.a(android.content.Context, int):android.view.animation.Animation");
    }

    private static Animation a(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return ml.a(context, xmlPullParser, null, Xml.asAttributeSet(xmlPullParser));
    }

    private static Animation a(Context context, XmlPullParser xmlPullParser, AnimationSet animationSet, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        Animation animation = null;
        while (true) {
            int next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    if (name.equals("set")) {
                        animation = new AnimationSet(context, attributeSet);
                        ml.a(context, xmlPullParser, (AnimationSet) animation, attributeSet);
                    } else if (name.equals("alpha")) {
                        animation = new AlphaAnimation(context, attributeSet);
                    } else if (name.equals("scale")) {
                        animation = new ScaleAnimation(context, attributeSet);
                    } else if (name.equals("rotate")) {
                        animation = new RotateAnimation(context, attributeSet);
                    } else if (name.equals("translate")) {
                        animation = new TranslateAnimation(context, attributeSet);
                    } else {
                        try {
                            animation = (Animation) Class.forName(name).getConstructor(new Class[]{Context.class, AttributeSet.class}).newInstance(new Object[]{context, attributeSet});
                        } catch (Exception e) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Unknown animation name: ");
                            stringBuilder.append(xmlPullParser.getName());
                            stringBuilder.append(" error:");
                            stringBuilder.append(e.getMessage());
                            throw new RuntimeException(stringBuilder.toString());
                        }
                    }
                    if (animationSet != null) {
                        animationSet.addAnimation(animation);
                    }
                }
            }
        }
        return animation;
    }
}
