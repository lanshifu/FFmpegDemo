package com.tomatolive.library.ui.view.widget.matisse.internal.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.SelectedItemCollection;
import java.util.ArrayList;

public class SelectedPreviewActivity extends BasePreviewActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (SelectionSpec.getInstance().hasInited) {
            ArrayList parcelableArrayList = getIntent().getBundleExtra(BasePreviewActivity.EXTRA_DEFAULT_BUNDLE).getParcelableArrayList(SelectedItemCollection.STATE_SELECTION);
            this.mAdapter.addAll(parcelableArrayList);
            this.mAdapter.notifyDataSetChanged();
            if (this.mSpec.countable) {
                this.mCheckView.setCheckedNum(1);
            } else {
                this.mCheckView.setChecked(true);
            }
            this.mPreviousPos = 0;
            updateSize((Item) parcelableArrayList.get(0));
            return;
        }
        setResult(0);
        finish();
    }
}
