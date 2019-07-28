package sj.keyboard.data;

import android.view.View;
import android.view.ViewGroup;
import sj.keyboard.interfaces.PageViewInstantiateListener;

public class PageEntity<T extends PageEntity> implements PageViewInstantiateListener<T> {
    protected PageViewInstantiateListener mPageViewInstantiateListener;
    protected View mRootView;

    public void setIPageViewInstantiateItem(PageViewInstantiateListener pageViewInstantiateListener) {
        this.mPageViewInstantiateListener = pageViewInstantiateListener;
    }

    public View getRootView() {
        return this.mRootView;
    }

    public void setRootView(View view) {
        this.mRootView = view;
    }

    public PageEntity(View view) {
        this.mRootView = view;
    }

    public View instantiateItem(ViewGroup viewGroup, int i, T t) {
        if (this.mPageViewInstantiateListener != null) {
            return this.mPageViewInstantiateListener.instantiateItem(viewGroup, i, this);
        }
        return getRootView();
    }
}
