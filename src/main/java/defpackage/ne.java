package defpackage;

/* compiled from: BaseIndexPinyinBean */
/* renamed from: ne */
public abstract class ne extends nd {
    private String baseIndexPinyin;

    public abstract String getTarget();

    public boolean isNeedToPinyin() {
        return true;
    }

    public String getBaseIndexPinyin() {
        return this.baseIndexPinyin;
    }

    public ne setBaseIndexPinyin(String str) {
        this.baseIndexPinyin = str;
        return this;
    }
}
