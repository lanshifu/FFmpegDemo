package com.tomatolive.library.ui.view.gift.giftpanel;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> views;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ViewPagerAdapter(List<View> list) {
        this.views = list;
    }

    public int getCount() {
        return this.views != null ? this.views.size() : 0;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (i < this.views.size()) {
            viewGroup.addView((View) this.views.get(i), 0);
        }
        return this.views.get(i);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (i < this.views.size()) {
            viewGroup.removeView((View) this.views.get(i));
        }
    }
}
