package com.tomatolive.library.ui.view.widget.matisse.internal.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.PhotoMetadataUtils;
import com.tomatolive.library.ui.view.widget.matisse.listener.OnFragmentInteractionListener;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouch.c;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase.DisplayType;

public class PreviewItemFragment extends Fragment {
    private static final String ARGS_ITEM = "args_item";
    private OnFragmentInteractionListener mListener;

    public static PreviewItemFragment newInstance(Item item) {
        PreviewItemFragment previewItemFragment = new PreviewItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_ITEM, item);
        previewItemFragment.setArguments(bundle);
        return previewItemFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fq_matisse_fragment_preview_item, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        final Item item = (Item) getArguments().getParcelable(ARGS_ITEM);
        if (item != null) {
            View findViewById = view.findViewById(R.id.video_play_button);
            if (item.isVideo()) {
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setDataAndType(item.uri, "video/*");
                        try {
                            PreviewItemFragment.this.startActivity(intent);
                        } catch (ActivityNotFoundException unused) {
                            Toast.makeText(PreviewItemFragment.this.getContext(), R.string.fq_matisse_error_no_video_activity, 0).show();
                        }
                    }
                });
            } else {
                findViewById.setVisibility(8);
            }
            ImageViewTouch imageViewTouch = (ImageViewTouch) view.findViewById(R.id.image_view);
            imageViewTouch.setDisplayType(DisplayType.FIT_TO_SCREEN);
            imageViewTouch.setSingleTapListener(new c() {
                public void onSingleTapConfirmed() {
                    if (PreviewItemFragment.this.mListener != null) {
                        PreviewItemFragment.this.mListener.onClick();
                    }
                }
            });
            Point bitmapSize = PhotoMetadataUtils.getBitmapSize(item.getContentUri(), getActivity());
            if (item.isGif()) {
                SelectionSpec.getInstance().imageEngine.loadGifImage(getContext(), bitmapSize.x, bitmapSize.y, imageViewTouch, item.getContentUri());
            } else {
                SelectionSpec.getInstance().imageEngine.loadImage(getContext(), bitmapSize.x, bitmapSize.y, imageViewTouch, item.getContentUri());
            }
        }
    }

    public void resetView() {
        if (getView() != null) {
            ((ImageViewTouch) getView().findViewById(R.id.image_view)).a();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.toString());
        stringBuilder.append(" must implement OnFragmentInteractionListener");
        throw new RuntimeException(stringBuilder.toString());
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
