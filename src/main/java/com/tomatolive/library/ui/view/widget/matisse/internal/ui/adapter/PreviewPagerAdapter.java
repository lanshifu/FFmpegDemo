package com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.PreviewItemFragment;
import java.util.ArrayList;
import java.util.List;

public class PreviewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Item> mItems = new ArrayList();
    private OnPrimaryItemSetListener mListener;

    interface OnPrimaryItemSetListener {
        void onPrimaryItemSet(int i);
    }

    public PreviewPagerAdapter(FragmentManager fragmentManager, OnPrimaryItemSetListener onPrimaryItemSetListener) {
        super(fragmentManager);
        this.mListener = onPrimaryItemSetListener;
    }

    public Fragment getItem(int i) {
        return PreviewItemFragment.newInstance((Item) this.mItems.get(i));
    }

    public int getCount() {
        return this.mItems.size();
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
        if (this.mListener != null) {
            this.mListener.onPrimaryItemSet(i);
        }
    }

    public Item getMediaItem(int i) {
        return (Item) this.mItems.get(i);
    }

    public void addAll(List<Item> list) {
        this.mItems.addAll(list);
    }
}
