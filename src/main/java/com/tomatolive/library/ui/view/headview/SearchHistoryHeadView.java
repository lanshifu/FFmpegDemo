package com.tomatolive.library.ui.view.headview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.db.SearchKeywordEntity;
import com.tomatolive.library.ui.view.widget.tagview.TagContainerLayout;
import com.tomatolive.library.ui.view.widget.tagview.TagView.OnTagClickListener;
import java.util.ArrayList;
import java.util.List;

public class SearchHistoryHeadView extends LinearLayout {
    private TagContainerLayout tagHistory;
    private TagContainerLayout tagHot;

    public SearchHistoryHeadView(Context context) {
        this(context, null);
    }

    public SearchHistoryHeadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SearchHistoryHeadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.fq_layout_head_view_search_history, this);
        this.tagHot = (TagContainerLayout) findViewById(R.id.tag_hot);
        this.tagHistory = (TagContainerLayout) findViewById(R.id.tag_history);
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener, OnTagClickListener onTagClickListener2) {
        if (onTagClickListener != null) {
            this.tagHot.setOnTagClickListener(onTagClickListener);
        }
        if (onTagClickListener2 != null) {
            this.tagHistory.setOnTagClickListener(onTagClickListener2);
        }
    }

    public void initHotTagList(List<LabelEntity> list) {
        if (list != null) {
            List arrayList = new ArrayList();
            for (LabelEntity labelEntity : list) {
                arrayList.add(labelEntity.keyword);
            }
            this.tagHot.setTags(arrayList);
        }
    }

    public void initHistoryTagList(List<SearchKeywordEntity> list) {
        if (list != null) {
            List arrayList = new ArrayList();
            for (SearchKeywordEntity keyword : list) {
                arrayList.add(keyword.getKeyword());
            }
            this.tagHistory.setTags(arrayList);
        }
    }
}
