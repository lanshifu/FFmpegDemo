package okhttp3;

import okio.ByteString;

final class CertificatePinner$Pin {
    private static final String WILDCARD = "*.";
    final String canonicalHostname;
    final ByteString hash;
    final String hashAlgorithm;
    final String pattern;

    CertificatePinner$Pin(String str, String str2) {
        StringBuilder stringBuilder;
        this.pattern = str;
        if (str.startsWith(WILDCARD)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("http://");
            stringBuilder.append(str.substring(WILDCARD.length()));
            str = HttpUrl.get(stringBuilder.toString()).host();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("http://");
            stringBuilder.append(str);
            str = HttpUrl.get(stringBuilder.toString()).host();
        }
        this.canonicalHostname = str;
        if (str2.startsWith("sha1/")) {
            this.hashAlgorithm = "sha1/";
            this.hash = ByteString.decodeBase64(str2.substring("sha1/".length()));
        } else if (str2.startsWith("sha256/")) {
            this.hashAlgorithm = "sha256/";
            this.hash = ByteString.decodeBase64(str2.substring("sha256/".length()));
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("pins must start with 'sha256/' or 'sha1/': ");
            stringBuilder.append(str2);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        if (this.hash == null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("pins must be base64: ");
            stringBuilder.append(str2);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Missing block: B:5:0x0030, code skipped:
            if (r11.regionMatches(false, r0 + 1, r10.canonicalHostname, 0, r10.canonicalHostname.length()) != false) goto L_0x0034;
     */
    public boolean matches(java.lang.String r11) {
        /*
        r10 = this;
        r0 = r10.pattern;
        r1 = "*.";
        r0 = r0.startsWith(r1);
        if (r0 == 0) goto L_0x0035;
    L_0x000a:
        r0 = 46;
        r0 = r11.indexOf(r0);
        r1 = r11.length();
        r1 = r1 - r0;
        r2 = 1;
        r1 = r1 - r2;
        r3 = r10.canonicalHostname;
        r3 = r3.length();
        if (r1 != r3) goto L_0x0033;
    L_0x001f:
        r5 = 0;
        r6 = r0 + 1;
        r7 = r10.canonicalHostname;
        r8 = 0;
        r0 = r10.canonicalHostname;
        r9 = r0.length();
        r4 = r11;
        r11 = r4.regionMatches(r5, r6, r7, r8, r9);
        if (r11 == 0) goto L_0x0033;
    L_0x0032:
        goto L_0x0034;
    L_0x0033:
        r2 = 0;
    L_0x0034:
        return r2;
    L_0x0035:
        r0 = r10.canonicalHostname;
        r11 = r11.equals(r0);
        return r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.CertificatePinner$Pin.matches(java.lang.String):boolean");
    }

    public boolean equals(Object obj) {
        if (obj instanceof CertificatePinner$Pin) {
            CertificatePinner$Pin certificatePinner$Pin = (CertificatePinner$Pin) obj;
            if (this.pattern.equals(certificatePinner$Pin.pattern) && this.hashAlgorithm.equals(certificatePinner$Pin.hashAlgorithm) && this.hash.equals(certificatePinner$Pin.hash)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.pattern.hashCode()) * 31) + this.hashAlgorithm.hashCode()) * 31) + this.hash.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.hashAlgorithm);
        stringBuilder.append(this.hash.base64());
        return stringBuilder.toString();
    }
}
