package com.tencent.liteav.txcvodplayer.a;

import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

/* compiled from: TXCVodCacheMgr */
public class b {
    private static final String a = "b";
    private static b b = new b();
    private ArrayList<a> c;
    private HashSet<a> d;
    private String e;
    private int f;
    private String g;

    /* compiled from: TXCVodCacheMgr */
    static class a implements Serializable {
        String fileType;
        String path;
        Long time;
        String url;

        a() {
        }

        public String a() {
            return this.url;
        }

        public void a(String str) {
            this.url = str;
        }

        public String b() {
            return this.path;
        }

        public void b(String str) {
            this.path = str;
        }

        public Long c() {
            return this.time;
        }

        public void a(Long l) {
            this.time = l;
        }

        public void c(String str) {
            this.fileType = str;
        }

        public String d() {
            if (this.fileType == null && this.path != null) {
                if (this.path.endsWith("mp4")) {
                    return "mp4";
                }
                if (this.path.endsWith("m3u8.sqlite")) {
                    return "m3u8";
                }
            }
            return this.fileType;
        }
    }

    public static b a() {
        return b;
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(String str) {
        this.g = str;
    }

    public void b(String str) {
        Object concat;
        if (str.endsWith("/")) {
            concat = str.concat("txvodcache");
        } else {
            concat = str.concat("/txvodcache");
        }
        if (this.e == null || !this.e.equals(concat)) {
            this.e = concat;
            if (this.e != null) {
                new File(this.e).mkdirs();
                if (!b()) {
                    d();
                }
            }
        }
    }

    public a c(String str) {
        if (this.e == null || str == null) {
            return null;
        }
        File file = new File(this.e);
        if (!file.mkdirs() && !file.isDirectory()) {
            return null;
        }
        a aVar;
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            aVar = (a) it.next();
            if (aVar.url.equals(str)) {
                a(aVar);
                this.d.add(aVar);
                return new a(aVar.path, this.e, aVar.fileType);
            }
        }
        it = this.c.iterator();
        while (it.hasNext() && this.c.size() > this.f) {
            aVar = (a) it.next();
            if (!this.d.contains(aVar)) {
                b(aVar);
                it.remove();
            }
        }
        a e = e(str);
        if (e == null) {
            return null;
        }
        this.d.add(e);
        return new a(e.path, this.e, e.fileType);
    }

    /* JADX WARNING: Missing block: B:19:0x0053, code skipped:
            return false;
     */
    public boolean d(java.lang.String r4) {
        /*
        r3 = this;
        r4 = android.net.Uri.parse(r4);
        r0 = 0;
        if (r4 == 0) goto L_0x0053;
    L_0x0007:
        r1 = r4.getPath();
        if (r1 == 0) goto L_0x0053;
    L_0x000d:
        r1 = r4.getScheme();
        if (r1 != 0) goto L_0x0014;
    L_0x0013:
        goto L_0x0053;
    L_0x0014:
        r1 = r4.getScheme();
        r2 = "http";
        r1 = r1.startsWith(r2);
        if (r1 != 0) goto L_0x0021;
    L_0x0020:
        return r0;
    L_0x0021:
        r1 = r4.getPath();
        r2 = ".mp4";
        r1 = r1.endsWith(r2);
        if (r1 != 0) goto L_0x0051;
    L_0x002d:
        r1 = r4.getPath();
        r2 = "m3u8";
        r1 = r1.endsWith(r2);
        if (r1 != 0) goto L_0x0051;
    L_0x0039:
        r1 = r4.getPath();
        r2 = ".MP4";
        r1 = r1.endsWith(r2);
        if (r1 != 0) goto L_0x0051;
    L_0x0045:
        r4 = r4.getPath();
        r1 = "M3U8";
        r4 = r4.endsWith(r1);
        if (r4 == 0) goto L_0x0052;
    L_0x0051:
        r0 = 1;
    L_0x0052:
        return r0;
    L_0x0053:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.txcvodplayer.a.b.d(java.lang.String):boolean");
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b() {
        this.c = new ArrayList();
        this.d = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.e);
        stringBuilder.append("/");
        stringBuilder.append("tx_cache.xml");
        try {
            for (Node firstChild = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(stringBuilder.toString())).getElementsByTagName("caches").item(0).getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                a aVar = new a();
                for (Node firstChild2 = firstChild.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
                    if (firstChild2.getNodeName().equals("path")) {
                        aVar.b(firstChild2.getFirstChild().getNodeValue());
                    } else if (firstChild2.getNodeName().equals("time")) {
                        aVar.a(Long.valueOf(Long.parseLong(firstChild2.getFirstChild().getNodeValue())));
                    } else if (firstChild2.getNodeName().equals(OnNativeInvokeListener.ARG_URL)) {
                        aVar.a(firstChild2.getFirstChild().getNodeValue());
                    } else if (firstChild2.getNodeName().equals("fileType")) {
                        aVar.c(firstChild2.getFirstChild().getNodeValue());
                    }
                }
                this.c.add(aVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        try {
            Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element createElement = newDocument.createElement("caches");
            newDocument.appendChild(createElement);
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                Element createElement2 = newDocument.createElement("cache");
                createElement.appendChild(createElement2);
                Element createElement3 = newDocument.createElement("path");
                createElement3.appendChild(newDocument.createTextNode(aVar.b()));
                createElement2.appendChild(createElement3);
                createElement3 = newDocument.createElement("time");
                createElement3.appendChild(newDocument.createTextNode(aVar.c().toString()));
                createElement2.appendChild(createElement3);
                createElement3 = newDocument.createElement(OnNativeInvokeListener.ARG_URL);
                createElement3.appendChild(newDocument.createTextNode(aVar.a()));
                createElement2.appendChild(createElement3);
                createElement3 = newDocument.createElement("fileType");
                createElement3.appendChild(newDocument.createTextNode(aVar.d()));
                createElement2.appendChild(createElement3);
            }
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            DOMSource dOMSource = new DOMSource(newDocument);
            StreamResult streamResult = new StreamResult();
            streamResult.setSystemId(new File(this.e, "tx_cache.xml").getAbsolutePath());
            newTransformer.transform(dOMSource, streamResult);
            System.out.println("File saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(a aVar) {
        aVar.time = Long.valueOf(System.currentTimeMillis());
        this.c.remove(aVar);
        this.c.add(aVar);
        c();
    }

    /* Access modifiers changed, original: 0000 */
    public a e(String str) {
        a aVar = new a();
        aVar.url = str;
        aVar.time = Long.valueOf(System.currentTimeMillis());
        String f = f(str);
        Uri parse = Uri.parse(str);
        StringBuilder stringBuilder;
        if (parse.getPath().endsWith(".mp4") || parse.getPath().endsWith(".MP4")) {
            if (this.g != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(f);
                stringBuilder.append(".");
                stringBuilder.append(this.g);
                aVar.b(stringBuilder.toString());
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(f);
                stringBuilder.append(".mp4");
                aVar.b(stringBuilder.toString());
            }
            aVar.c("mp4");
        } else if (!parse.getPath().endsWith(".m3u8") && !parse.getPath().endsWith(".M3U8")) {
            return null;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(f);
            stringBuilder.append(".m3u8.sqlite");
            aVar.b(stringBuilder.toString());
            aVar.c("m3u8");
        }
        this.c.add(aVar);
        c();
        return aVar;
    }

    public static String f(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(i));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void d() {
        if (new File(this.e).listFiles().length > 0) {
            String str = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("!!!警告：TXVodPlayer缓存目录不为空 ");
            stringBuilder.append(this.e);
            stringBuilder.append("!!!");
            Log.w(str, stringBuilder.toString());
        }
    }

    private void a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.e);
        stringBuilder.append("/");
        stringBuilder.append(str);
        str = stringBuilder.toString();
        new File(str).delete();
        if (str2.equals("mp4")) {
            new File(str.concat(".info")).delete();
        }
        str2 = a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("delete ");
        stringBuilder.append(str);
        Log.w(str2, stringBuilder.toString());
    }

    private void b(a aVar) {
        a(aVar.b(), aVar.d());
    }

    public void a(String str, boolean z) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.b().equals(str)) {
                it.remove();
                if (z) {
                    b(aVar);
                    this.c.remove(aVar);
                    c();
                }
            }
        }
    }
}
