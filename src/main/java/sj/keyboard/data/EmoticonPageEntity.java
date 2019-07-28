package sj.keyboard.data;

import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import sj.keyboard.widget.EmoticonPageView;

public class EmoticonPageEntity<T> extends PageEntity<EmoticonPageEntity> {
    private DelBtnStatus mDelBtnStatus;
    private List<T> mEmoticonList;
    private int mLine;
    private int mRow;

    public enum DelBtnStatus {
        GONE,
        FOLLOW,
        LAST;

        public boolean isShow() {
            return GONE.toString().equals(toString()) ^ 1;
        }
    }

    public List<T> getEmoticonList() {
        return this.mEmoticonList;
    }

    public void setEmoticonList(List<T> list) {
        this.mEmoticonList = list;
    }

    public int getLine() {
        return this.mLine;
    }

    public void setLine(int i) {
        this.mLine = i;
    }

    public int getRow() {
        return this.mRow;
    }

    public void setRow(int i) {
        this.mRow = i;
    }

    public DelBtnStatus getDelBtnStatus() {
        return this.mDelBtnStatus;
    }

    public void setDelBtnStatus(DelBtnStatus delBtnStatus) {
        this.mDelBtnStatus = delBtnStatus;
    }

    public View instantiateItem(ViewGroup viewGroup, int i, EmoticonPageEntity emoticonPageEntity) {
        if (this.mPageViewInstantiateListener != null) {
            return this.mPageViewInstantiateListener.instantiateItem(viewGroup, i, this);
        }
        if (getRootView() == null) {
            EmoticonPageView emoticonPageView = new EmoticonPageView(viewGroup.getContext());
            emoticonPageView.setNumColumns(this.mRow);
            setRootView(emoticonPageView);
        }
        return getRootView();
    }
}
