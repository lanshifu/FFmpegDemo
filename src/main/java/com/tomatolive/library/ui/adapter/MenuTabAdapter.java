package com.tomatolive.library.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.tomatolive.library.base.BaseFragment;
import java.util.List;

public class MenuTabAdapter extends FragmentStatePagerAdapter {
    private List<String> entityList;
    private List<BaseFragment> mFragments;

    public MenuTabAdapter(List<BaseFragment> list, List<String> list2, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragments = list;
        this.entityList = list2;
    }

    public BaseFragment getItem(int i) {
        return (BaseFragment) this.mFragments.get(i);
    }

    public int getCount() {
        return this.mFragments.size();
    }

    public CharSequence getPageTitle(int i) {
        return (CharSequence) this.entityList.get(i);
    }
}
