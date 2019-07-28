package com.tomatolive.library.ui.view.widget.matisse.internal.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Album;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.AlbumMediaCollection;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.AlbumMediaCollection.AlbumMediaCallbacks;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter.PreviewPagerAdapter;
import java.util.ArrayList;

public class AlbumPreviewActivity extends BasePreviewActivity implements AlbumMediaCallbacks {
    public static final String EXTRA_ALBUM = "extra_album";
    public static final String EXTRA_ITEM = "extra_item";
    private AlbumMediaCollection mCollection = new AlbumMediaCollection();
    private boolean mIsAlreadySetPosition;

    public void onAlbumMediaReset() {
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (SelectionSpec.getInstance().hasInited) {
            this.mCollection.onCreate(this, this);
            this.mCollection.load((Album) getIntent().getParcelableExtra("extra_album"));
            Item item = (Item) getIntent().getParcelableExtra(EXTRA_ITEM);
            if (this.mSpec.countable) {
                this.mCheckView.setCheckedNum(this.mSelectedCollection.checkedNumOf(item));
            } else {
                this.mCheckView.setChecked(this.mSelectedCollection.isSelected(item));
            }
            updateSize(item);
            return;
        }
        setResult(0);
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mCollection.onDestroy();
    }

    public void onAlbumMediaLoad(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(Item.valueOf(cursor));
        }
        if (!arrayList.isEmpty()) {
            PreviewPagerAdapter previewPagerAdapter = (PreviewPagerAdapter) this.mPager.getAdapter();
            previewPagerAdapter.addAll(arrayList);
            previewPagerAdapter.notifyDataSetChanged();
            if (!this.mIsAlreadySetPosition) {
                this.mIsAlreadySetPosition = true;
                int indexOf = arrayList.indexOf((Item) getIntent().getParcelableExtra(EXTRA_ITEM));
                this.mPager.setCurrentItem(indexOf, false);
                this.mPreviousPos = indexOf;
            }
        }
    }
}
