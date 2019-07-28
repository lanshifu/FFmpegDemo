package defpackage;

import com.mcxtzhang.indexlib.suspension.a;

/* compiled from: BaseIndexBean */
/* renamed from: nd */
public abstract class nd implements a {
    private String baseIndexTag;

    public boolean isShowSuspension() {
        return true;
    }

    public String getBaseIndexTag() {
        return this.baseIndexTag;
    }

    public nd setBaseIndexTag(String str) {
        this.baseIndexTag = str;
        return this;
    }

    public String getSuspensionTag() {
        return this.baseIndexTag;
    }
}
