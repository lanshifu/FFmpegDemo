package com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.MimeType;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Album;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.IncapableCause;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.SelectedItemCollection;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.MediaGrid;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.MediaGrid.OnMediaGridClickListener;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.MediaGrid.PreBindInfo;

public class AlbumMediaAdapter extends RecyclerViewCursorAdapter<ViewHolder> implements OnMediaGridClickListener {
    private static final int VIEW_TYPE_CAPTURE = 1;
    private static final int VIEW_TYPE_MEDIA = 2;
    private CheckStateListener mCheckStateListener;
    private int mImageResize;
    private OnMediaClickListener mOnMediaClickListener;
    private final Drawable mPlaceholder;
    private RecyclerView mRecyclerView;
    private final SelectedItemCollection mSelectedCollection;
    private SelectionSpec mSelectionSpec = SelectionSpec.getInstance();

    public interface CheckStateListener {
        void onUpdate();
    }

    public interface OnMediaClickListener {
        void onMediaClick(Album album, Item item, int i);
    }

    public interface OnPhotoCapture {
        void capture();
    }

    private static class CaptureViewHolder extends ViewHolder {
        private TextView mHint;

        CaptureViewHolder(View view) {
            super(view);
            this.mHint = (TextView) view.findViewById(R.id.hint);
        }
    }

    private static class MediaViewHolder extends ViewHolder {
        private MediaGrid mMediaGrid;

        MediaViewHolder(View view) {
            super(view);
            this.mMediaGrid = (MediaGrid) view;
        }
    }

    public AlbumMediaAdapter(Context context, SelectedItemCollection selectedItemCollection, RecyclerView recyclerView) {
        super(null);
        this.mSelectedCollection = selectedItemCollection;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.item_placeholder});
        this.mPlaceholder = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        this.mRecyclerView = recyclerView;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 1) {
            return i == 2 ? new MediaViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fq_matisse_media_grid_item, viewGroup, false)) : null;
        } else {
            CaptureViewHolder captureViewHolder = new CaptureViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fq_matisse_photo_capture_item, viewGroup, false));
            captureViewHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (view.getContext() instanceof OnPhotoCapture) {
                        ((OnPhotoCapture) view.getContext()).capture();
                    }
                }
            });
            return captureViewHolder;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        if (viewHolder instanceof CaptureViewHolder) {
            CaptureViewHolder captureViewHolder = (CaptureViewHolder) viewHolder;
            Drawable[] compoundDrawables = captureViewHolder.mHint.getCompoundDrawables();
            TypedArray obtainStyledAttributes = viewHolder.itemView.getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.capture_textColor});
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            for (int i = 0; i < compoundDrawables.length; i++) {
                Drawable drawable = compoundDrawables[i];
                if (drawable != null) {
                    ConstantState constantState = drawable.getConstantState();
                    if (constantState != null) {
                        Drawable mutate = constantState.newDrawable().mutate();
                        mutate.setColorFilter(color, Mode.SRC_IN);
                        mutate.setBounds(drawable.getBounds());
                        compoundDrawables[i] = mutate;
                    }
                }
            }
            captureViewHolder.mHint.setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        } else if (viewHolder instanceof MediaViewHolder) {
            MediaViewHolder mediaViewHolder = (MediaViewHolder) viewHolder;
            Item valueOf = Item.valueOf(cursor);
            mediaViewHolder.mMediaGrid.preBindMedia(new PreBindInfo(getImageResize(mediaViewHolder.mMediaGrid.getContext()), this.mPlaceholder, this.mSelectionSpec.countable, viewHolder));
            mediaViewHolder.mMediaGrid.bindMedia(valueOf);
            mediaViewHolder.mMediaGrid.setOnMediaGridClickListener(this);
            setCheckStatus(valueOf, mediaViewHolder.mMediaGrid);
        }
    }

    private void setCheckStatus(Item item, MediaGrid mediaGrid) {
        if (this.mSelectionSpec.countable) {
            int checkedNumOf = this.mSelectedCollection.checkedNumOf(item);
            if (checkedNumOf > 0) {
                mediaGrid.setCheckEnabled(true);
                mediaGrid.setCheckedNum(checkedNumOf);
            } else if (this.mSelectedCollection.maxSelectableReached()) {
                mediaGrid.setCheckEnabled(false);
                mediaGrid.setCheckedNum(CheckView.UNCHECKED);
            } else {
                mediaGrid.setCheckEnabled(true);
                mediaGrid.setCheckedNum(checkedNumOf);
            }
        } else if (this.mSelectedCollection.isSelected(item)) {
            mediaGrid.setCheckEnabled(true);
            mediaGrid.setChecked(true);
        } else if (this.mSelectedCollection.maxSelectableReached()) {
            mediaGrid.setCheckEnabled(false);
            mediaGrid.setChecked(false);
        } else {
            mediaGrid.setCheckEnabled(true);
            mediaGrid.setChecked(false);
        }
    }

    private static boolean isSelectableType(Context context, Item item) {
        if (context == null) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        for (MimeType checkType : SelectionSpec.getInstance().mimeTypeSet) {
            if (checkType.checkType(contentResolver, item.getContentUri())) {
                return true;
            }
        }
        return false;
    }

    public void onThumbnailClicked(ImageView imageView, Item item, ViewHolder viewHolder) {
        if (!isSelectableType(viewHolder.itemView.getContext(), item)) {
            Toast.makeText(viewHolder.itemView.getContext(), "不支持该文件格式", 0).show();
        } else if (this.mOnMediaClickListener != null) {
            this.mOnMediaClickListener.onMediaClick(null, item, viewHolder.getAdapterPosition());
        }
    }

    public void onCheckViewClicked(CheckView checkView, Item item, ViewHolder viewHolder) {
        if (this.mSelectionSpec.countable) {
            if (this.mSelectedCollection.checkedNumOf(item) != CheckView.UNCHECKED) {
                this.mSelectedCollection.remove(item);
                notifyCheckStateChanged();
            } else if (assertAddSelection(viewHolder.itemView.getContext(), item)) {
                this.mSelectedCollection.add(item);
                notifyCheckStateChanged();
            }
        } else if (this.mSelectedCollection.isSelected(item)) {
            this.mSelectedCollection.remove(item);
            notifyCheckStateChanged();
        } else if (assertAddSelection(viewHolder.itemView.getContext(), item)) {
            this.mSelectedCollection.add(item);
            notifyCheckStateChanged();
        }
    }

    private void notifyCheckStateChanged() {
        notifyDataSetChanged();
        if (this.mCheckStateListener != null) {
            this.mCheckStateListener.onUpdate();
        }
    }

    public int getItemViewType(int i, Cursor cursor) {
        return Item.valueOf(cursor).isCapture() ? 1 : 2;
    }

    private boolean assertAddSelection(Context context, Item item) {
        IncapableCause isAcceptable = this.mSelectedCollection.isAcceptable(item);
        IncapableCause.handleCause(context, isAcceptable);
        return isAcceptable == null;
    }

    public void registerCheckStateListener(CheckStateListener checkStateListener) {
        this.mCheckStateListener = checkStateListener;
    }

    public void unregisterCheckStateListener() {
        this.mCheckStateListener = null;
    }

    public void registerOnMediaClickListener(OnMediaClickListener onMediaClickListener) {
        this.mOnMediaClickListener = onMediaClickListener;
    }

    public void unregisterOnMediaClickListener() {
        this.mOnMediaClickListener = null;
    }

    public void refreshSelection() {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) this.mRecyclerView.getLayoutManager();
        int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
        if (findFirstVisibleItemPosition != -1 && findLastVisibleItemPosition != -1) {
            Cursor cursor = getCursor();
            int i = findFirstVisibleItemPosition;
            while (i <= findLastVisibleItemPosition) {
                ViewHolder findViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
                if ((findViewHolderForAdapterPosition instanceof MediaViewHolder) && cursor.moveToPosition(i)) {
                    setCheckStatus(Item.valueOf(cursor), ((MediaViewHolder) findViewHolderForAdapterPosition).mMediaGrid);
                }
                i++;
            }
        }
    }

    private int getImageResize(Context context) {
        if (this.mImageResize == 0) {
            int spanCount = ((GridLayoutManager) this.mRecyclerView.getLayoutManager()).getSpanCount();
            this.mImageResize = (context.getResources().getDisplayMetrics().widthPixels - (context.getResources().getDimensionPixelSize(R.dimen.fq_matisse_media_grid_spacing) * (spanCount - 1))) / spanCount;
            this.mImageResize = (int) (((float) this.mImageResize) * this.mSelectionSpec.thumbnailScale);
        }
        return this.mImageResize;
    }
}
