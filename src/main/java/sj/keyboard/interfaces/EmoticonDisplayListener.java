package sj.keyboard.interfaces;

import android.view.ViewGroup;
import sj.keyboard.adpater.EmoticonsAdapter.ViewHolder;

public interface EmoticonDisplayListener<T> {
    void onBindView(int i, ViewGroup viewGroup, ViewHolder viewHolder, T t, boolean z);
}
