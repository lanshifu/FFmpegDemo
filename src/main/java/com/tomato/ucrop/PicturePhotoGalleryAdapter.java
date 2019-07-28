package com.tomato.ucrop;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.one.tomato.ucrop.R;
import com.tomato.ucrop.model.CutInfo;
import java.util.ArrayList;
import java.util.List;

public class PicturePhotoGalleryAdapter extends Adapter<ViewHolder> {
    private Context a;
    private List<CutInfo> b = new ArrayList();
    private LayoutInflater c;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        ImageView a;
        ImageView b;

        public ViewHolder(View view) {
            super(view);
            this.a = (ImageView) view.findViewById(R.id.iv_photo);
            this.b = (ImageView) view.findViewById(R.id.iv_dot);
        }
    }

    public PicturePhotoGalleryAdapter(Context context, List<CutInfo> list) {
        this.c = LayoutInflater.from(context);
        this.a = context;
        this.b = list;
    }

    /* renamed from: a */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.c.inflate(R.layout.tomato_ucrop_picture_gf_adapter_edit_list, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String str = "";
        CutInfo cutInfo = (CutInfo) this.b.get(i);
        if (cutInfo != null) {
            str = cutInfo.getPath();
        }
        if (cutInfo.isCut()) {
            viewHolder.b.setVisibility(0);
            viewHolder.b.setImageResource(R.drawable.ucrop_oval_true);
        } else {
            viewHolder.b.setVisibility(8);
        }
        Glide.with(this.a).load(str).transition(DrawableTransitionOptions.withCrossFade()).apply(new RequestOptions().placeholder(R.color.ucrop_color_grey).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.a);
    }

    public int getItemCount() {
        return this.b.size();
    }
}
