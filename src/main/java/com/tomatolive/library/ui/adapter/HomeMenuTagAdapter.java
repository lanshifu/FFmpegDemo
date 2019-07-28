package com.tomatolive.library.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.tomatolive.library.base.BaseFragment;
import com.tomatolive.library.model.LabelEntity;
import java.util.List;

public class HomeMenuTagAdapter extends FragmentStatePagerAdapter {
    private List<LabelEntity> entityList;
    private List<BaseFragment> mFragments;

    public HomeMenuTagAdapter(List<BaseFragment> list, List<LabelEntity> list2, FragmentManager fragmentManager) {
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
        return ((LabelEntity) this.entityList.get(i)).name;
    }
}
