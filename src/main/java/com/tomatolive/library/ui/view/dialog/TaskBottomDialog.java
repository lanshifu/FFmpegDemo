package com.tomatolive.library.ui.view.dialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tomatolive.library.R;
import com.tomatolive.library.model.TaskBoxEntity;
import com.tomatolive.library.ui.view.task.TaskAdapter;
import java.util.List;

public class TaskBottomDialog extends BaseBottomDialogFragment {
    private static final String KEY_LAYOUT_RES = "bottom_layout_res";
    private List<TaskBoxEntity> mData;
    private FragmentManager mFragmentManager;
    @LayoutRes
    private int mLayoutRes;
    private TaskAdapter mTaskAdapter;
    private TaskClickListener taskSendClickListener;

    public interface TaskClickListener {
        void onTaskCallback(TaskBoxEntity taskBoxEntity);
    }

    public static TaskBottomDialog create(FragmentManager fragmentManager, TaskClickListener taskClickListener) {
        TaskBottomDialog taskBottomDialog = new TaskBottomDialog();
        taskBottomDialog.setFragmentManager(fragmentManager);
        taskBottomDialog.setOnSendClickListener(taskClickListener);
        return taskBottomDialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mLayoutRes = bundle.getInt(KEY_LAYOUT_RES);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(KEY_LAYOUT_RES, this.mLayoutRes);
        super.onSaveInstanceState(bundle);
    }

    public int getLayoutRes() {
        this.mLayoutRes = R.layout.fq_layout_task_view;
        return this.mLayoutRes;
    }

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rl_content);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        this.mTaskAdapter = new TaskAdapter(getData());
        recyclerView.setAdapter(this.mTaskAdapter);
        this.mTaskAdapter.bindToRecyclerView(recyclerView);
        this.mTaskAdapter.setOnItemClickListener(new -$$Lambda$TaskBottomDialog$xq85uQClvyvtg4Fiv2jlfM1OgIA(this));
    }

    public static /* synthetic */ void lambda$initView$0(TaskBottomDialog taskBottomDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        TaskBoxEntity taskBoxEntity = (TaskBoxEntity) baseQuickAdapter.getItem(i);
        if (!(taskBoxEntity == null || taskBottomDialog.taskSendClickListener == null)) {
            taskBottomDialog.taskSendClickListener.onTaskCallback(taskBoxEntity);
        }
    }

    public List<TaskBoxEntity> getData() {
        return this.mData;
    }

    public void setmData(List<TaskBoxEntity> list) {
        this.mData = list;
    }

    private void setOnSendClickListener(TaskClickListener taskClickListener) {
        this.taskSendClickListener = taskClickListener;
    }

    private void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public void show() {
        show(this.mFragmentManager);
    }

    public void updateSingleData(TaskBoxEntity taskBoxEntity) {
        if (this.mTaskAdapter != null) {
            this.mTaskAdapter.updateItemBy(taskBoxEntity);
        }
    }
}
