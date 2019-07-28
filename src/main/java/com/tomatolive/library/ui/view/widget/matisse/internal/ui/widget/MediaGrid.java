package com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;

public class MediaGrid extends SquareFrameLayout implements OnClickListener {
    private CheckView mCheckView;
    private ImageView mGifTag;
    private OnMediaGridClickListener mListener;
    private Item mMedia;
    private PreBindInfo mPreBindInfo;
    private ImageView mThumbnail;
    private TextView mVideoDuration;

    public interface OnMediaGridClickListener {
        void onCheckViewClicked(CheckView checkView, Item item, ViewHolder viewHolder);

        void onThumbnailClicked(ImageView imageView, Item item, ViewHolder viewHolder);
    }

    public static class PreBindInfo {
        boolean mCheckViewCountable;
        Drawable mPlaceholder;
        int mResize;
        ViewHolder mViewHolder;

        public PreBindInfo(int i, Drawable drawable, boolean z, ViewHolder viewHolder) {
            this.mResize = i;
            this.mPlaceholder = drawable;
            this.mCheckViewCountable = z;
            this.mViewHolder = viewHolder;
        }
    }

    public MediaGrid(Context context) {
        super(context);
        init(context);
    }

    public MediaGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fq_matisse_media_grid_content, this, true);
        this.mThumbnail = (ImageView) findViewById(R.id.media_thumbnail);
        this.mCheckView = (CheckView) findViewById(R.id.check_view);
        this.mGifTag = (ImageView) findViewById(R.id.gif);
        this.mVideoDuration = (TextView) findViewById(R.id.video_duration);
        this.mThumbnail.setOnClickListener(this);
        this.mCheckView.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.mListener == null) {
            return;
        }
        if (view == this.mThumbnail) {
            this.mListener.onThumbnailClicked(this.mThumbnail, this.mMedia, this.mPreBindInfo.mViewHolder);
        } else if (view == this.mCheckView) {
            this.mListener.onCheckViewClicked(this.mCheckView, this.mMedia, this.mPreBindInfo.mViewHolder);
        }
    }

    public void preBindMedia(PreBindInfo preBindInfo) {
        this.mPreBindInfo = preBindInfo;
    }

    public void bindMedia(Item item) {
        this.mMedia = item;
        setGifTag();
        initCheckView();
        setImage();
        setVideoDuration();
    }

    public Item getMedia() {
        return this.mMedia;
    }

    private void setGifTag() {
        this.mGifTag.setVisibility(this.mMedia.isGif() ? 0 : 8);
    }

    private void initCheckView() {
        this.mCheckView.setCountable(this.mPreBindInfo.mCheckViewCountable);
    }

    public void setCheckEnabled(boolean z) {
        this.mCheckView.setEnabled(z);
    }

    public void setCheckedNum(int i) {
        this.mCheckView.setCheckedNum(i);
    }

    public void setChecked(boolean z) {
        this.mCheckView.setChecked(z);
    }

    private void setImage() {
        if (this.mMedia.isGif()) {
            SelectionSpec.getInstance().imageEngine.loadGifThumbnail(getContext(), this.mPreBindInfo.mResize, this.mPreBindInfo.mPlaceholder, this.mThumbnail, this.mMedia.getContentUri());
        } else {
            SelectionSpec.getInstance().imageEngine.loadThumbnail(getContext(), this.mPreBindInfo.mResize, this.mPreBindInfo.mPlaceholder, this.mThumbnail, this.mMedia.getContentUri());
        }
    }

    private void setVideoDuration() {
        if (this.mMedia.isVideo()) {
            this.mVideoDuration.setVisibility(0);
            this.mVideoDuration.setText(DateUtils.formatElapsedTime(this.mMedia.duration / 1000));
            return;
        }
        this.mVideoDuration.setVisibility(8);
    }

    public void setOnMediaGridClickListener(OnMediaGridClickListener onMediaGridClickListener) {
        this.mListener = onMediaGridClickListener;
    }

    public void removeOnMediaGridClickListener() {
        this.mListener = null;
    }
}
