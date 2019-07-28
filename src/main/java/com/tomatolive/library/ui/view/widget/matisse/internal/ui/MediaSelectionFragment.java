package com.tomatolive.library.ui.view.widget.matisse.internal.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.MimeType;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Album;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.AlbumMediaCollection;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.AlbumMediaCollection.AlbumMediaCallbacks;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.SelectedItemCollection;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter.AlbumMediaAdapter;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter.AlbumMediaAdapter.CheckStateListener;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter.AlbumMediaAdapter.OnMediaClickListener;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.MediaGridInset;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.UIUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediaSelectionFragment extends Fragment implements AlbumMediaCallbacks, CheckStateListener, OnMediaClickListener {
    public static final String EXTRA_ALBUM = "extra_album";
    Set<Item> itemSet = new HashSet();
    List<Item> items = new ArrayList();
    private AlbumMediaAdapter mAdapter;
    private final AlbumMediaCollection mAlbumMediaCollection = new AlbumMediaCollection();
    private CheckStateListener mCheckStateListener;
    private Handler mMainHandler = new Handler();
    private OnMediaClickListener mOnMediaClickListener;
    private RecyclerView mRecyclerView;
    private SelectionProvider mSelectionProvider;

    public interface SelectionProvider {
        SelectedItemCollection provideSelectedItemCollection();
    }

    public void refreshSelection() {
    }

    public static MediaSelectionFragment newInstance(Album album) {
        MediaSelectionFragment mediaSelectionFragment = new MediaSelectionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_album", album);
        mediaSelectionFragment.setArguments(bundle);
        return mediaSelectionFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SelectionProvider) {
            this.mSelectionProvider = (SelectionProvider) context;
            if (context instanceof CheckStateListener) {
                this.mCheckStateListener = (CheckStateListener) context;
            }
            if (context instanceof OnMediaClickListener) {
                this.mOnMediaClickListener = (OnMediaClickListener) context;
                return;
            }
            return;
        }
        throw new IllegalStateException("Context must implement SelectionProvider.");
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fq_matisse_fragment_media_selection, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        int spanCount;
        super.onActivityCreated(bundle);
        Album album = (Album) getArguments().getParcelable("extra_album");
        this.mAdapter = new AlbumMediaAdapter(getContext(), this.mSelectionProvider.provideSelectedItemCollection(), this.mRecyclerView);
        this.mAdapter.registerCheckStateListener(this);
        this.mAdapter.registerOnMediaClickListener(this);
        this.mRecyclerView.setHasFixedSize(true);
        SelectionSpec instance = SelectionSpec.getInstance();
        if (instance.gridExpectedSize > 0) {
            spanCount = UIUtils.spanCount(getContext(), instance.gridExpectedSize);
        } else {
            spanCount = instance.spanCount;
        }
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        this.mRecyclerView.addItemDecoration(new MediaGridInset(spanCount, getResources().getDimensionPixelSize(R.dimen.fq_matisse_media_grid_spacing), false));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAlbumMediaCollection.onCreate(getActivity(), this);
        this.mAlbumMediaCollection.load(album, instance.capture);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mAlbumMediaCollection.onDestroy();
    }

    public void refreshMediaGrid() {
        this.mAdapter.notifyDataSetChanged();
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

    public void onAlbumMediaLoad(Cursor cursor) {
        this.mAdapter.swapCursor(cursor);
    }

    public void onAlbumMediaReset() {
        this.mAdapter.swapCursor(null);
    }

    public void onUpdate() {
        if (this.mCheckStateListener != null) {
            this.mCheckStateListener.onUpdate();
        }
    }

    public void onMediaClick(Album album, Item item, int i) {
        if (this.mOnMediaClickListener != null) {
            this.mOnMediaClickListener.onMediaClick((Album) getArguments().getParcelable("extra_album"), item, i);
        }
    }
}
