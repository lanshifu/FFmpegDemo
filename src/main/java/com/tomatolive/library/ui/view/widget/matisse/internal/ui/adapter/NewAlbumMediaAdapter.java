package com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Album;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.MediaGrid;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.MediaGrid.OnMediaGridClickListener;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.MediaGrid.PreBindInfo;
import java.util.List;
import java.util.Set;

public class NewAlbumMediaAdapter extends Adapter<MediaViewHolder> implements OnMediaGridClickListener {
    private List<Item> itemList;
    private Set<Item> itemSet;
    private int mImageResize;
    private OnMediaClickListener mOnMediaClickListener;
    private RecyclerView mRecyclerView;
    private final SelectionSpec mSelectionSpec = SelectionSpec.getInstance();

    public interface OnMediaClickListener {
        void onMediaClick(Album album, Item item, int i);
    }

    static class MediaViewHolder extends ViewHolder {
        private MediaGrid mMediaGrid;

        MediaViewHolder(View view) {
            super(view);
            this.mMediaGrid = (MediaGrid) view;
        }
    }

    public void onCheckViewClicked(CheckView checkView, Item item, ViewHolder viewHolder) {
    }

    public NewAlbumMediaAdapter(List<Item> list, RecyclerView recyclerView) {
        this.itemList = list;
        this.mRecyclerView = recyclerView;
    }

    public NewAlbumMediaAdapter(Set<Item> set, RecyclerView recyclerView) {
        this.itemSet = set;
        this.mRecyclerView = recyclerView;
    }

    @NonNull
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MediaViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fq_matisse_media_grid_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MediaViewHolder mediaViewHolder, int i) {
        Item item = (Item) this.itemList.get(i);
        mediaViewHolder.mMediaGrid.preBindMedia(new PreBindInfo(getImageResize(mediaViewHolder.mMediaGrid.getContext()), null, this.mSelectionSpec.countable, mediaViewHolder));
        mediaViewHolder.mMediaGrid.bindMedia(item);
        mediaViewHolder.mMediaGrid.setOnMediaGridClickListener(this);
    }

    private int getImageResize(Context context) {
        if (this.mImageResize == 0) {
            int spanCount = ((GridLayoutManager) this.mRecyclerView.getLayoutManager()).getSpanCount();
            this.mImageResize = (context.getResources().getDisplayMetrics().widthPixels - (context.getResources().getDimensionPixelSize(R.dimen.fq_matisse_media_grid_spacing) * (spanCount - 1))) / spanCount;
            this.mImageResize = (int) (((float) this.mImageResize) * this.mSelectionSpec.thumbnailScale);
        }
        return this.mImageResize;
    }

    public int getItemCount() {
        return this.itemList == null ? 0 : this.itemList.size();
    }

    public void onThumbnailClicked(ImageView imageView, Item item, ViewHolder viewHolder) {
        if (this.mOnMediaClickListener != null) {
            this.mOnMediaClickListener.onMediaClick(null, item, viewHolder.getAdapterPosition());
        }
    }

    public void registerOnMediaClickListener(OnMediaClickListener onMediaClickListener) {
        this.mOnMediaClickListener = onMediaClickListener;
    }

    public void unregisterOnMediaClickListener() {
        this.mOnMediaClickListener = null;
    }

    public void setData(List<Item> list) {
        this.itemList = list;
        notifyDataSetChanged();
    }
}
