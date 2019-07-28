package sj.keyboard.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

public class PageSetEntity<T extends PageEntity> implements Serializable {
    protected final String mIconUri;
    protected final boolean mIsShowIndicator;
    protected final int mPageCount;
    protected final LinkedList<T> mPageEntityList;
    protected final String mSetName;
    protected final String uuid = UUID.randomUUID().toString();

    public static class Builder<T extends PageEntity> {
        protected String iconUri;
        protected boolean isShowIndicator = true;
        protected int pageCount;
        protected LinkedList<T> pageEntityList = new LinkedList();
        protected String setName;

        public Builder setPageCount(int i) {
            this.pageCount = i;
            return this;
        }

        public Builder setShowIndicator(boolean z) {
            this.isShowIndicator = z;
            return this;
        }

        public Builder setPageEntityList(LinkedList<T> linkedList) {
            this.pageEntityList = linkedList;
            return this;
        }

        public Builder addPageEntity(T t) {
            this.pageEntityList.add(t);
            return this;
        }

        public Builder setIconUri(String str) {
            this.iconUri = str;
            return this;
        }

        public Builder setIconUri(int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(i);
            this.iconUri = stringBuilder.toString();
            return this;
        }

        public Builder setSetName(String str) {
            this.setName = str;
            return this;
        }

        public PageSetEntity<T> build() {
            return new PageSetEntity(this);
        }
    }

    public PageSetEntity(Builder builder) {
        this.mPageCount = builder.pageCount;
        this.mIsShowIndicator = builder.isShowIndicator;
        this.mPageEntityList = builder.pageEntityList;
        this.mIconUri = builder.iconUri;
        this.mSetName = builder.setName;
    }

    public String getIconUri() {
        return this.mIconUri;
    }

    public int getPageCount() {
        return this.mPageEntityList == null ? 0 : this.mPageEntityList.size();
    }

    public LinkedList<T> getPageEntityList() {
        return this.mPageEntityList;
    }

    public String getUuid() {
        return this.uuid;
    }

    public boolean isShowIndicator() {
        return this.mIsShowIndicator;
    }
}
