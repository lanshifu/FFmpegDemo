package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.util.aa;
import com.google.android.exoplayer2.util.z;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: TtmlDecoder */
/* renamed from: lc */
public final class lc extends c {
    private static final Pattern a = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern b = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern c = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern d = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern e = Pattern.compile("^(\\d+) (\\d+)$");
    private static final b f = new b(30.0f, 1, 1);
    private static final a g = new a(32, 15);
    private final XmlPullParserFactory h;

    /* compiled from: TtmlDecoder */
    /* renamed from: lc$a */
    private static final class a {
        final int a;
        final int b;

        a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    /* compiled from: TtmlDecoder */
    /* renamed from: lc$b */
    private static final class b {
        final float a;
        final int b;
        final int c;

        b(float f, int i, int i2) {
            this.a = f;
            this.b = i;
            this.c = i2;
        }
    }

    public lc() {
        super("TtmlDecoder");
        try {
            this.h = XmlPullParserFactory.newInstance();
            this.h.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public lh a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        try {
            XmlPullParser newPullParser = this.h.newPullParser();
            Map hashMap = new HashMap();
            Map hashMap2 = new HashMap();
            lh lhVar = null;
            hashMap2.put("", new le(null));
            int i2 = 0;
            newPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), null);
            ArrayDeque arrayDeque = new ArrayDeque();
            b bVar = f;
            a aVar = g;
            for (i = newPullParser.getEventType(); i != 1; i = newPullParser.getEventType()) {
                ld ldVar = (ld) arrayDeque.peek();
                if (i2 == 0) {
                    String name = newPullParser.getName();
                    if (i == 2) {
                        if ("tt".equals(name)) {
                            bVar = a(newPullParser);
                            aVar = a(newPullParser, g);
                        }
                        if (!lc.b(name)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Ignoring unsupported tag: ");
                            stringBuilder.append(newPullParser.getName());
                            Log.i("TtmlDecoder", stringBuilder.toString());
                            i2++;
                        } else if ("head".equals(name)) {
                            a(newPullParser, hashMap, hashMap2, aVar);
                        } else {
                            try {
                                ld a = a(newPullParser, ldVar, hashMap2, bVar);
                                arrayDeque.push(a);
                                if (ldVar != null) {
                                    ldVar.a(a);
                                }
                            } catch (SubtitleDecoderException e) {
                                Log.w("TtmlDecoder", "Suppressing parser error", e);
                                i2++;
                            }
                        }
                    } else if (i == 4) {
                        ldVar.a(ld.a(newPullParser.getText()));
                    } else if (i == 3) {
                        if (newPullParser.getName().equals("tt")) {
                            lhVar = new lh((ld) arrayDeque.peek(), hashMap, hashMap2);
                        }
                        arrayDeque.pop();
                    }
                } else if (i == 2) {
                    i2++;
                } else if (i == 3) {
                    i2--;
                }
                newPullParser.next();
            }
            return lhVar;
        } catch (XmlPullParserException e2) {
            throw new SubtitleDecoderException("Unable to decode source", e2);
        } catch (IOException e3) {
            throw new IllegalStateException("Unexpected error when reading input.", e3);
        }
    }

    private b a(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRate");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] a = z.a(attributeValue2, " ");
            if (a.length == 2) {
                f = ((float) Integer.parseInt(a[0])) / ((float) Integer.parseInt(a[1]));
            } else {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
        }
        int i = f.b;
        String attributeValue3 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "subFrameRate");
        if (attributeValue3 != null) {
            i = Integer.parseInt(attributeValue3);
        }
        int i2 = f.c;
        String attributeValue4 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "tickRate");
        if (attributeValue4 != null) {
            i2 = Integer.parseInt(attributeValue4);
        }
        return new b(((float) parseInt) * f, i, i2);
    }

    private a a(XmlPullParser xmlPullParser, a aVar) throws SubtitleDecoderException {
        StringBuilder stringBuilder;
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "cellResolution");
        if (attributeValue == null) {
            return aVar;
        }
        Matcher matcher = e.matcher(attributeValue);
        if (matcher.matches()) {
            try {
                int parseInt = Integer.parseInt(matcher.group(1));
                int parseInt2 = Integer.parseInt(matcher.group(2));
                if (parseInt != 0 && parseInt2 != 0) {
                    return new a(parseInt, parseInt2);
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Invalid cell resolution ");
                stringBuilder2.append(parseInt);
                stringBuilder2.append(" ");
                stringBuilder2.append(parseInt2);
                throw new SubtitleDecoderException(stringBuilder2.toString());
            } catch (NumberFormatException unused) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Ignoring malformed cell resolution: ");
                stringBuilder.append(attributeValue);
                Log.w("TtmlDecoder", stringBuilder.toString());
                return aVar;
            }
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Ignoring malformed cell resolution: ");
        stringBuilder.append(attributeValue);
        Log.w("TtmlDecoder", stringBuilder.toString());
        return aVar;
    }

    private Map<String, lg> a(XmlPullParser xmlPullParser, Map<String, lg> map, Map<String, le> map2, a aVar) throws IOException, XmlPullParserException {
        do {
            xmlPullParser.next();
            if (aa.b(xmlPullParser, "style")) {
                String c = aa.c(xmlPullParser, "style");
                lg a = a(xmlPullParser, new lg());
                if (c != null) {
                    for (Object obj : a(c)) {
                        a.a((lg) map.get(obj));
                    }
                }
                if (a.i() != null) {
                    map.put(a.i(), a);
                }
            } else if (aa.b(xmlPullParser, "region")) {
                le b = b(xmlPullParser, aVar);
                if (b != null) {
                    map2.put(b.a, b);
                }
            }
        } while (!aa.a(xmlPullParser, "head"));
        return map;
    }

    private le b(XmlPullParser xmlPullParser, a aVar) {
        StringBuilder stringBuilder;
        String c = aa.c(xmlPullParser, "id");
        if (c == null) {
            return null;
        }
        String c2 = aa.c(xmlPullParser, "origin");
        if (c2 != null) {
            Matcher matcher = d.matcher(c2);
            if (matcher.matches()) {
                try {
                    float parseFloat = Float.parseFloat(matcher.group(1)) / 100.0f;
                    float parseFloat2 = Float.parseFloat(matcher.group(2)) / 100.0f;
                    String c3 = aa.c(xmlPullParser, "extent");
                    if (c3 != null) {
                        Matcher matcher2 = d.matcher(c3);
                        if (matcher2.matches()) {
                            try {
                                int hashCode;
                                float f;
                                float parseFloat3 = Float.parseFloat(matcher2.group(1)) / 100.0f;
                                float parseFloat4 = Float.parseFloat(matcher2.group(2)) / 100.0f;
                                String c4 = aa.c(xmlPullParser, "displayAlign");
                                if (c4 != null) {
                                    c4 = z.d(c4);
                                    Object obj = -1;
                                    hashCode = c4.hashCode();
                                    if (hashCode != -1364013995) {
                                        if (hashCode == 92734940 && c4.equals("after")) {
                                            obj = 1;
                                        }
                                    } else if (c4.equals("center")) {
                                        obj = null;
                                    }
                                    switch (obj) {
                                        case null:
                                            f = parseFloat2 + (parseFloat4 / 2.0f);
                                            hashCode = 1;
                                            break;
                                        case 1:
                                            f = parseFloat2 + parseFloat4;
                                            hashCode = 2;
                                            break;
                                    }
                                }
                                f = parseFloat2;
                                hashCode = 0;
                                return new le(c, parseFloat, f, 0, hashCode, parseFloat3, 1, 1.0f / ((float) aVar.b));
                            } catch (NumberFormatException unused) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Ignoring region with malformed extent: ");
                                stringBuilder.append(c2);
                                Log.w("TtmlDecoder", stringBuilder.toString());
                                return null;
                            }
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Ignoring region with unsupported extent: ");
                        stringBuilder.append(c2);
                        Log.w("TtmlDecoder", stringBuilder.toString());
                        return null;
                    }
                    Log.w("TtmlDecoder", "Ignoring region without an extent");
                    return null;
                } catch (NumberFormatException unused2) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Ignoring region with malformed origin: ");
                    stringBuilder.append(c2);
                    Log.w("TtmlDecoder", stringBuilder.toString());
                    return null;
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring region with unsupported origin: ");
            stringBuilder.append(c2);
            Log.w("TtmlDecoder", stringBuilder.toString());
            return null;
        }
        Log.w("TtmlDecoder", "Ignoring region without an origin");
        return null;
    }

    private String[] a(String str) {
        str = str.trim();
        return str.isEmpty() ? new String[0] : z.a(str, "\\s+");
    }

    /* JADX WARNING: Removed duplicated region for block: B:128:0x0215 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0215 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0215 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c9  */
    /* JADX WARNING: Missing block: B:48:0x00b6, code skipped:
            if (r3.equals("underline") != false) goto L_0x00c4;
     */
    /* JADX WARNING: Missing block: B:73:0x012b, code skipped:
            if (r3.equals("center") != false) goto L_0x012f;
     */
    private defpackage.lg a(org.xmlpull.v1.XmlPullParser r12, defpackage.lg r13) {
        /*
        r11 = this;
        r0 = r12.getAttributeCount();
        r1 = 0;
        r2 = r13;
        r13 = 0;
    L_0x0007:
        if (r13 >= r0) goto L_0x0219;
    L_0x0009:
        r3 = r12.getAttributeValue(r13);
        r4 = r12.getAttributeName(r13);
        r5 = r4.hashCode();
        r6 = 4;
        r7 = 2;
        r8 = 3;
        r9 = -1;
        r10 = 1;
        switch(r5) {
            case -1550943582: goto L_0x006f;
            case -1224696685: goto L_0x0065;
            case -1065511464: goto L_0x005b;
            case -879295043: goto L_0x0050;
            case -734428249: goto L_0x0046;
            case 3355: goto L_0x003c;
            case 94842723: goto L_0x0032;
            case 365601008: goto L_0x0028;
            case 1287124693: goto L_0x001e;
            default: goto L_0x001d;
        };
    L_0x001d:
        goto L_0x0079;
    L_0x001e:
        r5 = "backgroundColor";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0079;
    L_0x0026:
        r4 = 1;
        goto L_0x007a;
    L_0x0028:
        r5 = "fontSize";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0079;
    L_0x0030:
        r4 = 4;
        goto L_0x007a;
    L_0x0032:
        r5 = "color";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0079;
    L_0x003a:
        r4 = 2;
        goto L_0x007a;
    L_0x003c:
        r5 = "id";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0079;
    L_0x0044:
        r4 = 0;
        goto L_0x007a;
    L_0x0046:
        r5 = "fontWeight";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0079;
    L_0x004e:
        r4 = 5;
        goto L_0x007a;
    L_0x0050:
        r5 = "textDecoration";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0079;
    L_0x0058:
        r4 = 8;
        goto L_0x007a;
    L_0x005b:
        r5 = "textAlign";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0079;
    L_0x0063:
        r4 = 7;
        goto L_0x007a;
    L_0x0065:
        r5 = "fontFamily";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0079;
    L_0x006d:
        r4 = 3;
        goto L_0x007a;
    L_0x006f:
        r5 = "fontStyle";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0079;
    L_0x0077:
        r4 = 6;
        goto L_0x007a;
    L_0x0079:
        r4 = -1;
    L_0x007a:
        switch(r4) {
            case 0: goto L_0x0201;
            case 1: goto L_0x01de;
            case 2: goto L_0x01bb;
            case 3: goto L_0x01b2;
            case 4: goto L_0x0190;
            case 5: goto L_0x0180;
            case 6: goto L_0x0170;
            case 7: goto L_0x00f1;
            case 8: goto L_0x007f;
            default: goto L_0x007d;
        };
    L_0x007d:
        goto L_0x0215;
    L_0x007f:
        r3 = com.google.android.exoplayer2.util.z.d(r3);
        r4 = r3.hashCode();
        r5 = -1461280213; // 0xffffffffa8e6a22b float:-2.5605459E-14 double:NaN;
        if (r4 == r5) goto L_0x00b9;
    L_0x008c:
        r5 = -1026963764; // 0xffffffffc2c9c6cc float:-100.888275 double:NaN;
        if (r4 == r5) goto L_0x00b0;
    L_0x0091:
        r5 = 913457136; // 0x36723ff0 float:3.6098027E-6 double:4.5130779E-315;
        if (r4 == r5) goto L_0x00a6;
    L_0x0096:
        r5 = 1679736913; // 0x641ec051 float:1.1713774E22 double:8.29900303E-315;
        if (r4 == r5) goto L_0x009c;
    L_0x009b:
        goto L_0x00c3;
    L_0x009c:
        r4 = "linethrough";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x00c3;
    L_0x00a4:
        r7 = 0;
        goto L_0x00c4;
    L_0x00a6:
        r4 = "nolinethrough";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x00c3;
    L_0x00ae:
        r7 = 1;
        goto L_0x00c4;
    L_0x00b0:
        r4 = "underline";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x00c3;
    L_0x00b8:
        goto L_0x00c4;
    L_0x00b9:
        r4 = "nounderline";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x00c3;
    L_0x00c1:
        r7 = 3;
        goto L_0x00c4;
    L_0x00c3:
        r7 = -1;
    L_0x00c4:
        switch(r7) {
            case 0: goto L_0x00e7;
            case 1: goto L_0x00dd;
            case 2: goto L_0x00d3;
            case 3: goto L_0x00c9;
            default: goto L_0x00c7;
        };
    L_0x00c7:
        goto L_0x0215;
    L_0x00c9:
        r2 = r11.a(r2);
        r2 = r2.b(r1);
        goto L_0x0215;
    L_0x00d3:
        r2 = r11.a(r2);
        r2 = r2.b(r10);
        goto L_0x0215;
    L_0x00dd:
        r2 = r11.a(r2);
        r2 = r2.a(r1);
        goto L_0x0215;
    L_0x00e7:
        r2 = r11.a(r2);
        r2 = r2.a(r10);
        goto L_0x0215;
    L_0x00f1:
        r3 = com.google.android.exoplayer2.util.z.d(r3);
        r4 = r3.hashCode();
        switch(r4) {
            case -1364013995: goto L_0x0125;
            case 100571: goto L_0x011b;
            case 3317767: goto L_0x0111;
            case 108511772: goto L_0x0107;
            case 109757538: goto L_0x00fd;
            default: goto L_0x00fc;
        };
    L_0x00fc:
        goto L_0x012e;
    L_0x00fd:
        r4 = "start";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012e;
    L_0x0105:
        r6 = 1;
        goto L_0x012f;
    L_0x0107:
        r4 = "right";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012e;
    L_0x010f:
        r6 = 2;
        goto L_0x012f;
    L_0x0111:
        r4 = "left";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012e;
    L_0x0119:
        r6 = 0;
        goto L_0x012f;
    L_0x011b:
        r4 = "end";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012e;
    L_0x0123:
        r6 = 3;
        goto L_0x012f;
    L_0x0125:
        r4 = "center";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012e;
    L_0x012d:
        goto L_0x012f;
    L_0x012e:
        r6 = -1;
    L_0x012f:
        switch(r6) {
            case 0: goto L_0x0164;
            case 1: goto L_0x0158;
            case 2: goto L_0x014c;
            case 3: goto L_0x0140;
            case 4: goto L_0x0134;
            default: goto L_0x0132;
        };
    L_0x0132:
        goto L_0x0215;
    L_0x0134:
        r2 = r11.a(r2);
        r3 = android.text.Layout.Alignment.ALIGN_CENTER;
        r2 = r2.a(r3);
        goto L_0x0215;
    L_0x0140:
        r2 = r11.a(r2);
        r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE;
        r2 = r2.a(r3);
        goto L_0x0215;
    L_0x014c:
        r2 = r11.a(r2);
        r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE;
        r2 = r2.a(r3);
        goto L_0x0215;
    L_0x0158:
        r2 = r11.a(r2);
        r3 = android.text.Layout.Alignment.ALIGN_NORMAL;
        r2 = r2.a(r3);
        goto L_0x0215;
    L_0x0164:
        r2 = r11.a(r2);
        r3 = android.text.Layout.Alignment.ALIGN_NORMAL;
        r2 = r2.a(r3);
        goto L_0x0215;
    L_0x0170:
        r2 = r11.a(r2);
        r4 = "italic";
        r3 = r4.equalsIgnoreCase(r3);
        r2 = r2.d(r3);
        goto L_0x0215;
    L_0x0180:
        r2 = r11.a(r2);
        r4 = "bold";
        r3 = r4.equalsIgnoreCase(r3);
        r2 = r2.c(r3);
        goto L_0x0215;
    L_0x0190:
        r4 = r11.a(r2);	 Catch:{ SubtitleDecoderException -> 0x019b }
        defpackage.lc.a(r3, r4);	 Catch:{ SubtitleDecoderException -> 0x019a }
        r2 = r4;
        goto L_0x0215;
    L_0x019a:
        r2 = r4;
    L_0x019b:
        r4 = "TtmlDecoder";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Failed parsing fontSize value: ";
        r5.append(r6);
        r5.append(r3);
        r3 = r5.toString();
        android.util.Log.w(r4, r3);
        goto L_0x0215;
    L_0x01b2:
        r2 = r11.a(r2);
        r2 = r2.a(r3);
        goto L_0x0215;
    L_0x01bb:
        r2 = r11.a(r2);
        r4 = com.google.android.exoplayer2.util.d.a(r3);	 Catch:{ IllegalArgumentException -> 0x01c7 }
        r2.a(r4);	 Catch:{ IllegalArgumentException -> 0x01c7 }
        goto L_0x0215;
    L_0x01c7:
        r4 = "TtmlDecoder";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Failed parsing color value: ";
        r5.append(r6);
        r5.append(r3);
        r3 = r5.toString();
        android.util.Log.w(r4, r3);
        goto L_0x0215;
    L_0x01de:
        r2 = r11.a(r2);
        r4 = com.google.android.exoplayer2.util.d.a(r3);	 Catch:{ IllegalArgumentException -> 0x01ea }
        r2.b(r4);	 Catch:{ IllegalArgumentException -> 0x01ea }
        goto L_0x0215;
    L_0x01ea:
        r4 = "TtmlDecoder";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Failed parsing background value: ";
        r5.append(r6);
        r5.append(r3);
        r3 = r5.toString();
        android.util.Log.w(r4, r3);
        goto L_0x0215;
    L_0x0201:
        r4 = "style";
        r5 = r12.getName();
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0215;
    L_0x020d:
        r2 = r11.a(r2);
        r2 = r2.b(r3);
    L_0x0215:
        r13 = r13 + 1;
        goto L_0x0007;
    L_0x0219:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lc.a(org.xmlpull.v1.XmlPullParser, lg):lg");
    }

    private lg a(lg lgVar) {
        return lgVar == null ? new lg() : lgVar;
    }

    private defpackage.ld a(org.xmlpull.v1.XmlPullParser r20, defpackage.ld r21, java.util.Map<java.lang.String, defpackage.le> r22, defpackage.lc.b r23) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
        r19 = this;
        r0 = r19;
        r1 = r20;
        r2 = r21;
        r3 = r23;
        r4 = "";
        r5 = r20.getAttributeCount();
        r6 = 0;
        r12 = r0.a(r1, r6);
        r17 = r6;
        r10 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r13 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r15 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r6 = r4;
        r4 = 0;
    L_0x0026:
        if (r4 >= r5) goto L_0x00a1;
    L_0x0028:
        r7 = r1.getAttributeName(r4);
        r8 = r1.getAttributeValue(r4);
        r18 = r7.hashCode();
        switch(r18) {
            case -934795532: goto L_0x0060;
            case 99841: goto L_0x0056;
            case 100571: goto L_0x004c;
            case 93616297: goto L_0x0042;
            case 109780401: goto L_0x0038;
            default: goto L_0x0037;
        };
    L_0x0037:
        goto L_0x006a;
    L_0x0038:
        r9 = "style";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x006a;
    L_0x0040:
        r7 = 3;
        goto L_0x006b;
    L_0x0042:
        r9 = "begin";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x006a;
    L_0x004a:
        r7 = 0;
        goto L_0x006b;
    L_0x004c:
        r9 = "end";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x006a;
    L_0x0054:
        r7 = 1;
        goto L_0x006b;
    L_0x0056:
        r9 = "dur";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x006a;
    L_0x005e:
        r7 = 2;
        goto L_0x006b;
    L_0x0060:
        r9 = "region";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x006a;
    L_0x0068:
        r7 = 4;
        goto L_0x006b;
    L_0x006a:
        r7 = -1;
    L_0x006b:
        switch(r7) {
            case 0: goto L_0x0097;
            case 1: goto L_0x008f;
            case 2: goto L_0x0087;
            case 3: goto L_0x007b;
            case 4: goto L_0x0071;
            default: goto L_0x006e;
        };
    L_0x006e:
        r7 = r22;
        goto L_0x009e;
    L_0x0071:
        r7 = r22;
        r9 = r7.containsKey(r8);
        if (r9 == 0) goto L_0x009e;
    L_0x0079:
        r6 = r8;
        goto L_0x009e;
    L_0x007b:
        r7 = r22;
        r8 = r0.a(r8);
        r9 = r8.length;
        if (r9 <= 0) goto L_0x009e;
    L_0x0084:
        r17 = r8;
        goto L_0x009e;
    L_0x0087:
        r7 = r22;
        r8 = defpackage.lc.a(r8, r3);
        r15 = r8;
        goto L_0x009e;
    L_0x008f:
        r7 = r22;
        r8 = defpackage.lc.a(r8, r3);
        r13 = r8;
        goto L_0x009e;
    L_0x0097:
        r7 = r22;
        r8 = defpackage.lc.a(r8, r3);
        r10 = r8;
    L_0x009e:
        r4 = r4 + 1;
        goto L_0x0026;
    L_0x00a1:
        if (r2 == 0) goto L_0x00bd;
    L_0x00a3:
        r3 = r2.d;
        r7 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r5 == 0) goto L_0x00c2;
    L_0x00ae:
        r3 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1));
        if (r3 == 0) goto L_0x00b5;
    L_0x00b2:
        r3 = r2.d;
        r10 = r10 + r3;
    L_0x00b5:
        r3 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1));
        if (r3 == 0) goto L_0x00c2;
    L_0x00b9:
        r3 = r2.d;
        r13 = r13 + r3;
        goto L_0x00c2;
    L_0x00bd:
        r7 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
    L_0x00c2:
        r3 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1));
        if (r3 != 0) goto L_0x00d8;
    L_0x00c6:
        r3 = (r15 > r7 ? 1 : (r15 == r7 ? 0 : -1));
        if (r3 == 0) goto L_0x00cd;
    L_0x00ca:
        r15 = r15 + r10;
        r13 = r15;
        goto L_0x00d8;
    L_0x00cd:
        if (r2 == 0) goto L_0x00d8;
    L_0x00cf:
        r3 = r2.e;
        r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r5 == 0) goto L_0x00d8;
    L_0x00d5:
        r2 = r2.e;
        r13 = r2;
    L_0x00d8:
        r7 = r20.getName();
        r8 = r10;
        r10 = r13;
        r13 = r17;
        r14 = r6;
        r1 = defpackage.ld.a(r7, r8, r10, r12, r13, r14);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lc.a(org.xmlpull.v1.XmlPullParser, ld, java.util.Map, lc$b):ld");
    }

    private static boolean b(String str) {
        return str.equals("tt") || str.equals("head") || str.equals("body") || str.equals("div") || str.equals("p") || str.equals("span") || str.equals("br") || str.equals("style") || str.equals("styling") || str.equals("layout") || str.equals("region") || str.equals("metadata") || str.equals("smpte:image") || str.equals("smpte:data") || str.equals("smpte:information");
    }

    private static void a(String str, lg lgVar) throws SubtitleDecoderException {
        Matcher matcher;
        StringBuilder stringBuilder;
        String[] a = z.a(str, "\\s+");
        if (a.length == 1) {
            matcher = c.matcher(str);
        } else if (a.length == 2) {
            matcher = c.matcher(a[1]);
            Log.w("TtmlDecoder", "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid number of entries for fontSize: ");
            stringBuilder.append(a.length);
            stringBuilder.append(".");
            throw new SubtitleDecoderException(stringBuilder.toString());
        }
        if (matcher.matches()) {
            String group = matcher.group(3);
            Object obj = -1;
            int hashCode = group.hashCode();
            if (hashCode != 37) {
                if (hashCode != 3240) {
                    if (hashCode == 3592 && group.equals("px")) {
                        obj = null;
                    }
                } else if (group.equals("em")) {
                    obj = 1;
                }
            } else if (group.equals("%")) {
                obj = 2;
            }
            switch (obj) {
                case null:
                    lgVar.c(1);
                    break;
                case 1:
                    lgVar.c(2);
                    break;
                case 2:
                    lgVar.c(3);
                    break;
                default:
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Invalid unit for fontSize: '");
                    stringBuilder.append(group);
                    stringBuilder.append("'.");
                    throw new SubtitleDecoderException(stringBuilder.toString());
            }
            lgVar.a(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Invalid expression for fontSize: '");
        stringBuilder2.append(str);
        stringBuilder2.append("'.");
        throw new SubtitleDecoderException(stringBuilder2.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fc  */
    /* JADX WARNING: Missing block: B:27:0x00c2, code skipped:
            if (r14.equals("t") != false) goto L_0x00f8;
     */
    private static long a(java.lang.String r14, defpackage.lc.b r15) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
        r0 = a;
        r0 = r0.matcher(r14);
        r1 = r0.matches();
        r2 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r4 = 5;
        r5 = 4;
        r6 = 3;
        r7 = 2;
        r8 = 1;
        if (r1 == 0) goto L_0x008b;
    L_0x0016:
        r14 = r0.group(r8);
        r8 = java.lang.Long.parseLong(r14);
        r10 = 3600; // 0xe10 float:5.045E-42 double:1.7786E-320;
        r8 = r8 * r10;
        r8 = (double) r8;
        r14 = r0.group(r7);
        r10 = java.lang.Long.parseLong(r14);
        r12 = 60;
        r10 = r10 * r12;
        r10 = (double) r10;
        java.lang.Double.isNaN(r8);
        java.lang.Double.isNaN(r10);
        r8 = r8 + r10;
        r14 = r0.group(r6);
        r6 = java.lang.Long.parseLong(r14);
        r6 = (double) r6;
        java.lang.Double.isNaN(r6);
        r8 = r8 + r6;
        r14 = r0.group(r5);
        r5 = 0;
        if (r14 == 0) goto L_0x0051;
    L_0x004c:
        r10 = java.lang.Double.parseDouble(r14);
        goto L_0x0052;
    L_0x0051:
        r10 = r5;
    L_0x0052:
        r14 = 0;
        r8 = r8 + r10;
        r14 = r0.group(r4);
        if (r14 == 0) goto L_0x0064;
    L_0x005a:
        r10 = java.lang.Long.parseLong(r14);
        r14 = (float) r10;
        r1 = r15.a;
        r14 = r14 / r1;
        r10 = (double) r14;
        goto L_0x0065;
    L_0x0064:
        r10 = r5;
    L_0x0065:
        r14 = 0;
        r8 = r8 + r10;
        r14 = 6;
        r14 = r0.group(r14);
        if (r14 == 0) goto L_0x0085;
    L_0x006e:
        r0 = java.lang.Long.parseLong(r14);
        r0 = (double) r0;
        r14 = r15.b;
        r4 = (double) r14;
        java.lang.Double.isNaN(r0);
        java.lang.Double.isNaN(r4);
        r0 = r0 / r4;
        r14 = r15.a;
        r14 = (double) r14;
        java.lang.Double.isNaN(r14);
        r5 = r0 / r14;
    L_0x0085:
        r14 = 0;
        r8 = r8 + r5;
        r8 = r8 * r2;
        r14 = (long) r8;
        return r14;
    L_0x008b:
        r0 = b;
        r0 = r0.matcher(r14);
        r1 = r0.matches();
        if (r1 == 0) goto L_0x0123;
    L_0x0097:
        r14 = r0.group(r8);
        r9 = java.lang.Double.parseDouble(r14);
        r14 = r0.group(r7);
        r0 = -1;
        r1 = r14.hashCode();
        r11 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r1 == r11) goto L_0x00ed;
    L_0x00ac:
        r5 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        if (r1 == r5) goto L_0x00e3;
    L_0x00b0:
        r5 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        if (r1 == r5) goto L_0x00d9;
    L_0x00b4:
        r5 = 3494; // 0xda6 float:4.896E-42 double:1.7263E-320;
        if (r1 == r5) goto L_0x00cf;
    L_0x00b8:
        switch(r1) {
            case 115: goto L_0x00c5;
            case 116: goto L_0x00bc;
            default: goto L_0x00bb;
        };
    L_0x00bb:
        goto L_0x00f7;
    L_0x00bc:
        r1 = "t";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00f7;
    L_0x00c4:
        goto L_0x00f8;
    L_0x00c5:
        r1 = "s";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00f7;
    L_0x00cd:
        r4 = 2;
        goto L_0x00f8;
    L_0x00cf:
        r1 = "ms";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00f7;
    L_0x00d7:
        r4 = 3;
        goto L_0x00f8;
    L_0x00d9:
        r1 = "m";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00f7;
    L_0x00e1:
        r4 = 1;
        goto L_0x00f8;
    L_0x00e3:
        r1 = "h";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00f7;
    L_0x00eb:
        r4 = 0;
        goto L_0x00f8;
    L_0x00ed:
        r1 = "f";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00f7;
    L_0x00f5:
        r4 = 4;
        goto L_0x00f8;
    L_0x00f7:
        r4 = -1;
    L_0x00f8:
        switch(r4) {
            case 0: goto L_0x0118;
            case 1: goto L_0x0113;
            case 2: goto L_0x011f;
            case 3: goto L_0x010c;
            case 4: goto L_0x0104;
            case 5: goto L_0x00fc;
            default: goto L_0x00fb;
        };
    L_0x00fb:
        goto L_0x011f;
    L_0x00fc:
        r14 = r15.c;
        r14 = (double) r14;
        java.lang.Double.isNaN(r14);
        r9 = r9 / r14;
        goto L_0x011f;
    L_0x0104:
        r14 = r15.a;
        r14 = (double) r14;
        java.lang.Double.isNaN(r14);
        r9 = r9 / r14;
        goto L_0x011f;
    L_0x010c:
        r14 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r9 = r9 / r14;
        goto L_0x011f;
    L_0x0113:
        r14 = 4633641066610819072; // 0x404e000000000000 float:0.0 double:60.0;
        r9 = r9 * r14;
        goto L_0x011f;
    L_0x0118:
        r14 = 4660134898793709568; // 0x40ac200000000000 float:0.0 double:3600.0;
        r9 = r9 * r14;
    L_0x011f:
        r9 = r9 * r2;
        r14 = (long) r9;
        return r14;
    L_0x0123:
        r15 = new com.google.android.exoplayer2.text.SubtitleDecoderException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Malformed time expression: ";
        r0.append(r1);
        r0.append(r14);
        r14 = r0.toString();
        r15.<init>(r14);
        throw r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lc.a(java.lang.String, lc$b):long");
    }
}
