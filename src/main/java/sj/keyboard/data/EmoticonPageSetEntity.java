package sj.keyboard.data;

import java.util.ArrayList;
import sj.keyboard.data.EmoticonPageEntity.DelBtnStatus;
import sj.keyboard.interfaces.PageViewInstantiateListener;

public class EmoticonPageSetEntity<T> extends PageSetEntity<EmoticonPageEntity> {
    final DelBtnStatus mDelBtnStatus;
    final ArrayList<T> mEmoticonList;
    final int mLine;
    final int mRow;

    public static class Builder<T> extends sj.keyboard.data.PageSetEntity.Builder {
        protected DelBtnStatus delBtnStatus = DelBtnStatus.GONE;
        protected ArrayList<T> emoticonList;
        protected int line;
        protected PageViewInstantiateListener pageViewInstantiateListener;
        protected int row;

        public Builder setLine(int i) {
            this.line = i;
            return this;
        }

        public Builder setRow(int i) {
            this.row = i;
            return this;
        }

        public Builder setShowDelBtn(DelBtnStatus delBtnStatus) {
            this.delBtnStatus = delBtnStatus;
            return this;
        }

        public Builder setEmoticonList(ArrayList<T> arrayList) {
            this.emoticonList = arrayList;
            return this;
        }

        public Builder setIPageViewInstantiateItem(PageViewInstantiateListener pageViewInstantiateListener) {
            this.pageViewInstantiateListener = pageViewInstantiateListener;
            return this;
        }

        public Builder setShowIndicator(boolean z) {
            this.isShowIndicator = z;
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

        public EmoticonPageSetEntity<T> build() {
            int size = this.emoticonList.size();
            int isShow = (this.row * this.line) - this.delBtnStatus.isShow();
            double size2 = (double) this.emoticonList.size();
            double d = (double) isShow;
            Double.isNaN(size2);
            Double.isNaN(d);
            this.pageCount = (int) Math.ceil(size2 / d);
            int i = isShow > size ? size : isShow;
            if (!this.pageEntityList.isEmpty()) {
                this.pageEntityList.clear();
            }
            int i2 = 0;
            int i3 = i;
            i = 0;
            while (i2 < this.pageCount) {
                EmoticonPageEntity emoticonPageEntity = new EmoticonPageEntity();
                emoticonPageEntity.setLine(this.line);
                emoticonPageEntity.setRow(this.row);
                emoticonPageEntity.setDelBtnStatus(this.delBtnStatus);
                emoticonPageEntity.setEmoticonList(this.emoticonList.subList(i, i3));
                emoticonPageEntity.setIPageViewInstantiateItem(this.pageViewInstantiateListener);
                this.pageEntityList.add(emoticonPageEntity);
                i = (i2 * isShow) + isShow;
                i2++;
                i3 = (i2 * isShow) + isShow;
                if (i3 >= size) {
                    i3 = size;
                }
            }
            return new EmoticonPageSetEntity(this);
        }
    }

    public EmoticonPageSetEntity(Builder builder) {
        super(builder);
        this.mLine = builder.line;
        this.mRow = builder.row;
        this.mDelBtnStatus = builder.delBtnStatus;
        this.mEmoticonList = builder.emoticonList;
    }

    public int getLine() {
        return this.mLine;
    }

    public int getRow() {
        return this.mRow;
    }

    public DelBtnStatus getDelBtnStatus() {
        return this.mDelBtnStatus;
    }

    public ArrayList<T> getEmoticonList() {
        return this.mEmoticonList;
    }
}
