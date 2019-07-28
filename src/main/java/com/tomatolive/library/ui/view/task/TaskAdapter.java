package com.tomatolive.library.ui.view.task;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.TaskBoxEntity;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.i;
import java.util.List;

public class TaskAdapter extends BaseQuickAdapter<TaskBoxEntity, BaseViewHolder> {
    private List<TaskBoxEntity> mData;
    private int selectedPosition = -1;

    private float setAlpha(int i) {
        return i == 3 ? 0.5f : 1.0f;
    }

    public TaskAdapter(@Nullable List<TaskBoxEntity> list) {
        super(R.layout.fq_item_grid_task, list);
        this.mData = list;
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, TaskBoxEntity taskBoxEntity) {
        boolean z = false;
        boolean z2 = this.selectedPosition == baseViewHolder.getAdapterPosition();
        BaseViewHolder gone = baseViewHolder.setGone(R.id.tv_item_num, taskBoxEntity.getStatus() == 2);
        int i = R.id.tv_item_name;
        if (taskBoxEntity.getStatus() != 2) {
            z = true;
        }
        gone.setGone(i, z).setText(R.id.tv_item_name, getDesc(taskBoxEntity)).setBackgroundRes(R.id.tv_item_name, setBackColor(taskBoxEntity)).setText(R.id.tv_item_num, getDesc(taskBoxEntity)).setAlpha(R.id.iv_item_icon, setAlpha(taskBoxEntity.getStatus())).getView(R.id.ll_item_layout).setSelected(z2);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_item_icon);
        String propImg = taskBoxEntity.getPropImg();
        if (TextUtils.isEmpty(propImg) || taskBoxEntity.getStatus() != 2) {
            i.a(this.mContext, imageView, R.drawable.fq_task);
        } else {
            i.b(this.mContext, imageView, propImg, R.drawable.fq_ic_gift_default);
        }
    }

    private int setBackColor(TaskBoxEntity taskBoxEntity) {
        int i = R.drawable.fq_shape_blue;
        switch (taskBoxEntity.getStatus()) {
            case 0:
                return R.drawable.fq_shape_blue;
            case 1:
                return R.drawable.fq_shape_red;
            case 3:
                return R.drawable.fq_shape_blue;
            default:
                return i;
        }
    }

    private String getDesc(TaskBoxEntity taskBoxEntity) {
        String str = "";
        switch (taskBoxEntity.getStatus()) {
            case 0:
                return d.a(Long.parseLong(taskBoxEntity.getOpenTime()) * 1000);
            case 1:
                return this.mContext.getString(R.string.fq_receive_task);
            case 2:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("x");
                stringBuilder.append(taskBoxEntity.getPropNumber());
                return stringBuilder.toString();
            case 3:
                return this.mContext.getString(R.string.fq_please_wait);
            default:
                return str;
        }
    }

    public void updateItemBy(TaskBoxEntity taskBoxEntity) {
        int indexOf = this.mData.indexOf(taskBoxEntity);
        if (indexOf >= 0) {
            this.mData.set(indexOf, taskBoxEntity);
            notifyItemChanged(indexOf);
        }
    }

    public void setSelectedPosition(int i) {
        this.selectedPosition = i;
        notifyDataSetChanged();
    }

    public void clearSelectedPosition() {
        if (isPosition(this.selectedPosition)) {
            View viewByPosition = getViewByPosition(this.selectedPosition, R.id.ll_item_layout);
            if (viewByPosition != null) {
                viewByPosition.setSelected(false);
            }
        }
    }

    private boolean isPosition(int i) {
        return i >= 0 && i < getData().size();
    }
}
