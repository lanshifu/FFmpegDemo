package defpackage;

import android.net.Uri;
import android.util.Base64;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.b;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.upstream.o.a;
import com.google.android.exoplayer2.util.z;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: HlsPlaylistParser */
/* renamed from: kg */
public final class kg implements a<kf> {
    private static final Pattern a = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern b = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern c = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern d = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern e = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern f = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern g = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern h = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern i = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern j = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern k = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern l = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern m = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern n = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern o = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(,|$)");
    private static final Pattern p = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern q = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern r = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern s = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern t = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern u = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern v = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern w = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern x = kg.b("AUTOSELECT");
    private static final Pattern y = kg.b("DEFAULT");
    private static final Pattern z = kg.b("FORCED");

    /* compiled from: HlsPlaylistParser */
    /* renamed from: kg$a */
    private static class a {
        private final BufferedReader a;
        private final Queue<String> b;
        private String c;

        public a(Queue<String> queue, BufferedReader bufferedReader) {
            this.b = queue;
            this.a = bufferedReader;
        }

        public boolean a() throws IOException {
            if (this.c != null) {
                return true;
            }
            if (this.b.isEmpty()) {
                do {
                    String readLine = this.a.readLine();
                    this.c = readLine;
                    if (readLine == null) {
                        return false;
                    }
                    this.c = this.c.trim();
                } while (this.c.isEmpty());
                return true;
            }
            this.c = (String) this.b.poll();
            return true;
        }

        public String b() throws IOException {
            if (!a()) {
                return null;
            }
            String str = this.c;
            this.c = null;
            return str;
        }
    }

    /* renamed from: a */
    public kf b(Uri uri, InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
            if (kg.a(bufferedReader)) {
                String readLine;
                while (true) {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        readLine = readLine.trim();
                        if (!readLine.isEmpty()) {
                            if (!readLine.startsWith("#EXT-X-STREAM-INF")) {
                                if (readLine.startsWith("#EXT-X-TARGETDURATION") || readLine.startsWith("#EXT-X-MEDIA-SEQUENCE") || readLine.startsWith("#EXTINF") || readLine.startsWith("#EXT-X-KEY") || readLine.startsWith("#EXT-X-BYTERANGE") || readLine.equals("#EXT-X-DISCONTINUITY") || readLine.equals("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                                    break;
                                } else if (readLine.equals("#EXT-X-ENDLIST")) {
                                    break;
                                } else {
                                    arrayDeque.add(readLine);
                                }
                            } else {
                                arrayDeque.add(readLine);
                                kd a = kg.a(new a(arrayDeque, bufferedReader), uri.toString());
                                z.a(bufferedReader);
                                return a;
                            }
                        }
                    } else {
                        z.a(bufferedReader);
                        throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                    }
                }
                arrayDeque.add(readLine);
                kf b = kg.b(new a(arrayDeque, bufferedReader), uri.toString());
                return b;
            }
            throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.", uri);
        } finally {
            z.a(bufferedReader);
        }
    }

    private static boolean a(BufferedReader bufferedReader) throws IOException {
        int read = bufferedReader.read();
        if (read == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            read = bufferedReader.read();
        }
        read = kg.a(bufferedReader, true, read);
        int length = "#EXTM3U".length();
        int i = read;
        for (read = 0; read < length; read++) {
            if (i != "#EXTM3U".charAt(read)) {
                return false;
            }
            i = bufferedReader.read();
        }
        return z.a(kg.a(bufferedReader, false, i));
    }

    private static int a(BufferedReader bufferedReader, boolean z, int i) throws IOException {
        while (i != -1 && Character.isWhitespace(i) && (z || !z.a(i))) {
            i = bufferedReader.read();
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:88:0x01e6 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01e6 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01e6 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0157  */
    private static defpackage.kd a(defpackage.kg.a r27, java.lang.String r28) throws java.io.IOException {
        /*
        r0 = new java.util.HashSet;
        r0.<init>();
        r1 = new java.util.HashMap;
        r1.<init>();
        r5 = new java.util.ArrayList;
        r5.<init>();
        r6 = new java.util.ArrayList;
        r6.<init>();
        r7 = new java.util.ArrayList;
        r7.<init>();
        r2 = new java.util.ArrayList;
        r2.<init>();
        r4 = new java.util.ArrayList;
        r4.<init>();
        r3 = 0;
        r8 = 0;
    L_0x0025:
        r9 = r27.a();
        r10 = 1;
        if (r9 == 0) goto L_0x00e9;
    L_0x002c:
        r9 = r27.b();
        r12 = "#EXT";
        r12 = r9.startsWith(r12);
        if (r12 == 0) goto L_0x003b;
    L_0x0038:
        r4.add(r9);
    L_0x003b:
        r12 = "#EXT-X-MEDIA";
        r12 = r9.startsWith(r12);
        if (r12 == 0) goto L_0x0047;
    L_0x0043:
        r2.add(r9);
        goto L_0x0025;
    L_0x0047:
        r12 = "#EXT-X-STREAM-INF";
        r12 = r9.startsWith(r12);
        if (r12 == 0) goto L_0x0025;
    L_0x004f:
        r12 = "CLOSED-CAPTIONS=NONE";
        r12 = r9.contains(r12);
        r8 = r8 | r12;
        r12 = c;
        r12 = defpackage.kg.a(r9, r12);
        r13 = a;
        r13 = defpackage.kg.d(r9, r13);
        if (r13 == 0) goto L_0x0068;
    L_0x0064:
        r12 = java.lang.Integer.parseInt(r13);
    L_0x0068:
        r17 = r12;
        r12 = d;
        r12 = defpackage.kg.d(r9, r12);
        r13 = e;
        r13 = defpackage.kg.d(r9, r13);
        if (r13 == 0) goto L_0x0098;
    L_0x0078:
        r14 = "x";
        r13 = r13.split(r14);
        r14 = r13[r3];
        r14 = java.lang.Integer.parseInt(r14);
        r13 = r13[r10];
        r13 = java.lang.Integer.parseInt(r13);
        if (r14 <= 0) goto L_0x0091;
    L_0x008c:
        if (r13 > 0) goto L_0x008f;
    L_0x008e:
        goto L_0x0091;
    L_0x008f:
        r11 = r14;
        goto L_0x0093;
    L_0x0091:
        r11 = -1;
        r13 = -1;
    L_0x0093:
        r18 = r11;
        r19 = r13;
        goto L_0x009c;
    L_0x0098:
        r18 = -1;
        r19 = -1;
    L_0x009c:
        r11 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r13 = f;
        r13 = defpackage.kg.d(r9, r13);
        if (r13 == 0) goto L_0x00ad;
    L_0x00a6:
        r11 = java.lang.Float.parseFloat(r13);
        r20 = r11;
        goto L_0x00af;
    L_0x00ad:
        r20 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
    L_0x00af:
        r11 = b;
        r9 = defpackage.kg.d(r9, r11);
        if (r9 == 0) goto L_0x00c0;
    L_0x00b7:
        if (r12 == 0) goto L_0x00c0;
    L_0x00b9:
        r10 = com.google.android.exoplayer2.util.z.a(r12, r10);
        r1.put(r9, r10);
    L_0x00c0:
        r9 = r27.b();
        r10 = r0.add(r9);
        if (r10 == 0) goto L_0x0025;
    L_0x00ca:
        r10 = r5.size();
        r13 = java.lang.Integer.toString(r10);
        r14 = "application/x-mpegURL";
        r15 = 0;
        r21 = 0;
        r22 = 0;
        r16 = r12;
        r10 = com.google.android.exoplayer2.Format.a(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22);
        r11 = new kd$a;
        r11.<init>(r9, r10);
        r5.add(r11);
        goto L_0x0025;
    L_0x00e9:
        r9 = 0;
        r12 = 0;
        r13 = 0;
    L_0x00ec:
        r14 = r2.size();
        if (r9 >= r14) goto L_0x01ee;
    L_0x00f2:
        r14 = r2.get(r9);
        r14 = (java.lang.String) r14;
        r23 = defpackage.kg.a(r14);
        r15 = q;
        r15 = defpackage.kg.d(r14, r15);
        r0 = u;
        r0 = defpackage.kg.e(r14, r0);
        r3 = t;
        r24 = defpackage.kg.d(r14, r3);
        r3 = v;
        r3 = defpackage.kg.d(r14, r3);
        r10 = s;
        r10 = defpackage.kg.e(r14, r10);
        r11 = r10.hashCode();
        r25 = r2;
        r2 = -959297733; // 0xffffffffc6d2473b float:-26915.615 double:NaN;
        r26 = r15;
        r15 = 2;
        if (r11 == r2) goto L_0x0147;
    L_0x0128:
        r2 = -333210994; // 0xffffffffec239a8e float:-7.911391E26 double:NaN;
        if (r11 == r2) goto L_0x013d;
    L_0x012d:
        r2 = 62628790; // 0x3bba3b6 float:1.1028458E-36 double:3.09427336E-316;
        if (r11 == r2) goto L_0x0133;
    L_0x0132:
        goto L_0x0151;
    L_0x0133:
        r2 = "AUDIO";
        r2 = r10.equals(r2);
        if (r2 == 0) goto L_0x0151;
    L_0x013b:
        r2 = 0;
        goto L_0x0152;
    L_0x013d:
        r2 = "CLOSED-CAPTIONS";
        r2 = r10.equals(r2);
        if (r2 == 0) goto L_0x0151;
    L_0x0145:
        r2 = 2;
        goto L_0x0152;
    L_0x0147:
        r2 = "SUBTITLES";
        r2 = r10.equals(r2);
        if (r2 == 0) goto L_0x0151;
    L_0x014f:
        r2 = 1;
        goto L_0x0152;
    L_0x0151:
        r2 = -1;
    L_0x0152:
        switch(r2) {
            case 0: goto L_0x01b6;
            case 1: goto L_0x019a;
            case 2: goto L_0x0157;
            default: goto L_0x0155;
        };
    L_0x0155:
        goto L_0x01e6;
    L_0x0157:
        r2 = w;
        r2 = defpackage.kg.e(r14, r2);
        r3 = "CC";
        r3 = r2.startsWith(r3);
        if (r3 == 0) goto L_0x0174;
    L_0x0165:
        r3 = "application/cea-608";
        r2 = r2.substring(r15);
        r2 = java.lang.Integer.parseInt(r2);
    L_0x016f:
        r22 = r2;
        r17 = r3;
        goto L_0x0180;
    L_0x0174:
        r3 = "application/cea-708";
        r10 = 7;
        r2 = r2.substring(r10);
        r2 = java.lang.Integer.parseInt(r2);
        goto L_0x016f;
    L_0x0180:
        if (r13 != 0) goto L_0x0187;
    L_0x0182:
        r13 = new java.util.ArrayList;
        r13.<init>();
    L_0x0187:
        r16 = 0;
        r18 = 0;
        r19 = -1;
        r15 = r0;
        r20 = r23;
        r21 = r24;
        r0 = com.google.android.exoplayer2.Format.a(r15, r16, r17, r18, r19, r20, r21, r22);
        r13.add(r0);
        goto L_0x01e6;
    L_0x019a:
        r16 = "application/x-mpegURL";
        r17 = "text/vtt";
        r18 = 0;
        r19 = -1;
        r2 = r26;
        r15 = r0;
        r20 = r23;
        r21 = r24;
        r0 = com.google.android.exoplayer2.Format.a(r15, r16, r17, r18, r19, r20, r21);
        r3 = new kd$a;
        r3.<init>(r2, r0);
        r7.add(r3);
        goto L_0x01e6;
    L_0x01b6:
        r2 = r26;
        r3 = r1.get(r3);
        r18 = r3;
        r18 = (java.lang.String) r18;
        if (r18 == 0) goto L_0x01c9;
    L_0x01c2:
        r3 = com.google.android.exoplayer2.util.k.f(r18);
        r17 = r3;
        goto L_0x01cb;
    L_0x01c9:
        r17 = 0;
    L_0x01cb:
        r16 = "application/x-mpegURL";
        r19 = -1;
        r20 = -1;
        r21 = -1;
        r22 = 0;
        r15 = r0;
        r0 = com.google.android.exoplayer2.Format.a(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24);
        if (r2 != 0) goto L_0x01de;
    L_0x01dc:
        r12 = r0;
        goto L_0x01e6;
    L_0x01de:
        r3 = new kd$a;
        r3.<init>(r2, r0);
        r6.add(r3);
    L_0x01e6:
        r9 = r9 + 1;
        r2 = r25;
        r3 = 0;
        r10 = 1;
        goto L_0x00ec;
    L_0x01ee:
        if (r8 == 0) goto L_0x01f6;
    L_0x01f0:
        r0 = java.util.Collections.emptyList();
        r9 = r0;
        goto L_0x01f7;
    L_0x01f6:
        r9 = r13;
    L_0x01f7:
        r0 = new kd;
        r2 = r0;
        r3 = r28;
        r8 = r12;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kg.a(kg$a, java.lang.String):kd");
    }

    private static int a(String str) {
        int i = 0;
        int a = kg.a(str, y, false) | (kg.a(str, z, false) ? 2 : 0);
        if (kg.a(str, x, false)) {
            i = 4;
        }
        return a | i;
    }

    private static ke b(a aVar, String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long j = -9223372036854775807L;
        long j2 = j;
        int i = 0;
        int i2 = 0;
        long j3 = 0;
        boolean z = false;
        int i3 = 0;
        long j4 = 0;
        int i4 = 1;
        boolean z2 = false;
        boolean z3 = false;
        DrmInitData drmInitData = null;
        long j5 = 0;
        long j6 = 0;
        boolean z4 = false;
        long j7 = -1;
        long j8 = 0;
        String str2 = null;
        String str3 = null;
        ke.a aVar2 = null;
        loop0:
        while (true) {
            long j9 = 0;
            while (aVar.a()) {
                String b = aVar.b();
                if (b.startsWith("#EXT")) {
                    arrayList2.add(b);
                }
                String[] split;
                if (b.startsWith("#EXT-X-PLAYLIST-TYPE")) {
                    b = kg.e(b, i);
                    if ("VOD".equals(b)) {
                        i2 = 1;
                    } else if ("EVENT".equals(b)) {
                        i2 = 2;
                    }
                } else if (b.startsWith("#EXT-X-START")) {
                    j = (long) (kg.c(b, l) * 1000000.0d);
                } else if (b.startsWith("#EXT-X-MAP")) {
                    String e = kg.e(b, q);
                    b = kg.d(b, n);
                    if (b != null) {
                        split = b.split("@");
                        j7 = Long.parseLong(split[0]);
                        if (split.length > 1) {
                            j5 = Long.parseLong(split[1]);
                        }
                    }
                    aVar2 = new ke.a(e, j5, j7);
                    j5 = 0;
                    j7 = -1;
                } else if (b.startsWith("#EXT-X-TARGETDURATION")) {
                    j2 = 1000000 * ((long) kg.a(b, g));
                } else if (b.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                    j6 = kg.b(b, j);
                    j4 = j6;
                } else if (b.startsWith("#EXT-X-VERSION")) {
                    i4 = kg.a(b, h);
                } else if (b.startsWith("#EXTINF")) {
                    j9 = (long) (kg.c(b, k) * 1000000.0d);
                } else if (b.startsWith("#EXT-X-KEY")) {
                    String d = kg.d(b, o);
                    String d2 = kg.d(b, p);
                    if ("NONE".equals(d)) {
                        str2 = null;
                        str3 = null;
                    } else {
                        String d3 = kg.d(b, r);
                        if ("identity".equals(d2) || d2 == null) {
                            if ("AES-128".equals(d)) {
                                str2 = kg.e(b, q);
                                str3 = d3;
                            }
                        } else if (!(d == null || kg.a(b, d2) == null)) {
                            d = ("SAMPLE-AES-CENC".equals(d) || "SAMPLE-AES-CTR".equals(d)) ? "cenc" : "cbcs";
                            drmInitData = new DrmInitData(d, new SchemeData[]{r2});
                        }
                        str3 = d3;
                        str2 = null;
                    }
                } else if (b.startsWith("#EXT-X-BYTERANGE")) {
                    split = kg.e(b, m).split("@");
                    j7 = Long.parseLong(split[0]);
                    if (split.length > 1) {
                        j5 = Long.parseLong(split[1]);
                    }
                } else if (b.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                    i3 = Integer.parseInt(b.substring(b.indexOf(58) + 1));
                    z = true;
                } else if (b.equals("#EXT-X-DISCONTINUITY")) {
                    i++;
                } else if (b.startsWith("#EXT-X-PROGRAM-DATE-TIME")) {
                    if (j3 == 0) {
                        j3 = b.b(z.f(b.substring(b.indexOf(58) + 1))) - j8;
                    }
                } else if (b.equals("#EXT-X-GAP")) {
                    z4 = true;
                } else if (b.equals("#EXT-X-INDEPENDENT-SEGMENTS")) {
                    z2 = true;
                } else if (b.equals("#EXT-X-ENDLIST")) {
                    z3 = true;
                } else if (!b.startsWith("#")) {
                    String toHexString = str2 == null ? null : str3 != null ? str3 : Long.toHexString(j6);
                    long j10 = j6 + 1;
                    if (j7 == -1) {
                        j5 = 0;
                    }
                    arrayList.add(new ke.a(b, aVar2, j9, i, j8, str2, toHexString, j5, j7, z4));
                    j8 += j9;
                    if (j7 != -1) {
                        j5 += j7;
                    }
                    j6 = j10;
                    j7 = -1;
                    z4 = false;
                }
            }
            break loop0;
        }
        return new ke(i2, str, arrayList2, j, j3, z, i3, j4, i4, j2, z2, z3, j3 != 0, drmInitData, arrayList);
    }

    private static SchemeData a(String str, String str2) throws ParserException {
        if ("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed".equals(str2)) {
            str = kg.e(str, q);
            return new SchemeData(b.e, "video/mp4", Base64.decode(str.substring(str.indexOf(44)), 0));
        } else if (!"com.widevine".equals(str2)) {
            return null;
        } else {
            try {
                return new SchemeData(b.e, "hls", str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new ParserException(e);
            }
        }
    }

    private static int a(String str, Pattern pattern) throws ParserException {
        return Integer.parseInt(kg.e(str, pattern));
    }

    private static long b(String str, Pattern pattern) throws ParserException {
        return Long.parseLong(kg.e(str, pattern));
    }

    private static double c(String str, Pattern pattern) throws ParserException {
        return Double.parseDouble(kg.e(str, pattern));
    }

    private static String d(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1) : null;
    }

    private static String e(String str, Pattern pattern) throws ParserException {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Couldn't match ");
        stringBuilder.append(pattern.pattern());
        stringBuilder.append(" in ");
        stringBuilder.append(str);
        throw new ParserException(stringBuilder.toString());
    }

    private static boolean a(String str, Pattern pattern, boolean z) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1).equals("YES") : z;
    }

    private static Pattern b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("=(");
        stringBuilder.append("NO");
        stringBuilder.append("|");
        stringBuilder.append("YES");
        stringBuilder.append(")");
        return Pattern.compile(stringBuilder.toString());
    }
}
