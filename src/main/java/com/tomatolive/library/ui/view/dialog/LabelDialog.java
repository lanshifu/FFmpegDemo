package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.R;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.ui.adapter.LabelMenuAdapter;
import com.tomatolive.library.ui.view.divider.decoration.Y_Divider;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerBuilder;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerItemDecoration;
import com.yalantis.ucrop.view.CropImageView;

public class LabelDialog extends BaseDialogFragment {
    private LabelMenuAdapter labelMenuAdapter;
    private RecyclerView recyclerView;

    public interface OnLabelSelectedListener {
        void onLabelSelected(LabelMenuAdapter labelMenuAdapter, int i, LabelEntity labelEntity);
    }

    private class RVDividerDropGrid extends Y_DividerItemDecoration {
        private int colorRes;
        private final Context context;

        /* synthetic */ RVDividerDropGrid(LabelDialog labelDialog, Context context, int i, AnonymousClass1 anonymousClass1) {
            this(context, i);
        }

        private RVDividerDropGrid(Context context, int i) {
            super(context);
            this.context = context;
            this.colorRes = i;
        }

        public Y_Divider getDivider(int i) {
            switch (i % 2) {
                case 0:
                case 1:
                    return new Y_DividerBuilder().setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 20.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                default:
                    return new Y_DividerBuilder().setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 20.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
            }
        }
    }

    @SuppressLint({"ValidFragment"})
    private LabelDialog() {
    }

    public static LabelDialog newInstance(LabelMenuAdapter labelMenuAdapter) {
        Bundle bundle = new Bundle();
        LabelDialog labelDialog = new LabelDialog();
        labelDialog.setArguments(bundle);
        labelDialog.setLabelMenuAdapter(labelMenuAdapter);
        return labelDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_layout_label;
    }

    public void initView(View view) {
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        initAdapter();
        view.findViewById(R.id.rl_label_root).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LabelDialog.this.dismiss();
            }
        });
    }

    private void initAdapter() {
        this.recyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.recyclerView.addItemDecoration(new RVDividerDropGrid(this, this.mContext, 17170445, null));
        if (this.labelMenuAdapter != null) {
            this.recyclerView.setAdapter(this.labelMenuAdapter);
        }
    }

    public void onStart() {
        super.onStart();
        if (this.mDialog != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            this.mDialog.getWindow().setLayout(-1, -1);
            this.mDialog.setCancelable(false);
            this.mDialog.getWindow().setDimAmount(0.5f);
            this.mDialog.setCanceledOnTouchOutside(false);
        }
    }

    public LabelMenuAdapter getLabelMenuAdapter() {
        return this.labelMenuAdapter;
    }

    private void setLabelMenuAdapter(LabelMenuAdapter labelMenuAdapter) {
        this.labelMenuAdapter = labelMenuAdapter;
    }
}
