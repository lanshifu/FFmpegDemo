package sj.keyboard.adpater;

import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import sj.keyboard.data.PageEntity;
import sj.keyboard.data.PageSetEntity;
import sj.keyboard.data.PageSetEntity.Builder;

public class PageSetAdapter extends PagerAdapter {
    private final ArrayList<PageSetEntity> mPageSetEntityList = new ArrayList();

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void notifyData() {
    }

    public ArrayList<PageSetEntity> getPageSetEntityList() {
        return this.mPageSetEntityList;
    }

    public int getPageSetStartPosition(PageSetEntity pageSetEntity) {
        if (pageSetEntity == null || TextUtils.isEmpty(pageSetEntity.getUuid())) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i < this.mPageSetEntityList.size()) {
            if (i == this.mPageSetEntityList.size() - 1 && !pageSetEntity.getUuid().equals(((PageSetEntity) this.mPageSetEntityList.get(i)).getUuid())) {
                return 0;
            }
            if (pageSetEntity.getUuid().equals(((PageSetEntity) this.mPageSetEntityList.get(i)).getUuid())) {
                return i2;
            }
            i2 += ((PageSetEntity) this.mPageSetEntityList.get(i)).getPageCount();
            i++;
        }
        return i2;
    }

    public void add(View view) {
        add(this.mPageSetEntityList.size(), view);
    }

    public void add(int i, View view) {
        this.mPageSetEntityList.add(i, new Builder().addPageEntity(new PageEntity(view)).setShowIndicator(false).build());
    }

    public void add(PageSetEntity pageSetEntity) {
        add(this.mPageSetEntityList.size(), pageSetEntity);
    }

    public void add(int i, PageSetEntity pageSetEntity) {
        if (pageSetEntity != null) {
            this.mPageSetEntityList.add(i, pageSetEntity);
        }
    }

    public PageSetEntity get(int i) {
        return (PageSetEntity) this.mPageSetEntityList.get(i);
    }

    public void remove(int i) {
        this.mPageSetEntityList.remove(i);
        notifyData();
    }

    public PageEntity getPageEntity(int i) {
        Iterator it = this.mPageSetEntityList.iterator();
        while (it.hasNext()) {
            PageSetEntity pageSetEntity = (PageSetEntity) it.next();
            if (pageSetEntity.getPageCount() > i) {
                return (PageEntity) pageSetEntity.getPageEntityList().get(i);
            }
            i -= pageSetEntity.getPageCount();
        }
        return null;
    }

    public int getCount() {
        Iterator it = this.mPageSetEntityList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((PageSetEntity) it.next()).getPageCount();
        }
        return i;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View instantiateItem = getPageEntity(i).instantiateItem(viewGroup, i, null);
        if (instantiateItem == null) {
            return null;
        }
        viewGroup.addView(instantiateItem);
        return instantiateItem;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
