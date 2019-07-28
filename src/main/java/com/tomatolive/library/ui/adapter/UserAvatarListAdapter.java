package com.tomatolive.library.ui.adapter;

import android.text.TextUtils;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;
import defpackage.mh;
import defpackage.sh;
import java.util.concurrent.TimeUnit;

public class UserAvatarListAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {
    private UserListClickListener listener;

    public interface UserListClickListener {
        void onUserClick(UserEntity userEntity);
    }

    public UserAvatarListAdapter(int i) {
        super(i);
    }

    public void removeItemById(String str) {
        try {
            for (UserEntity userEntity : this.mData) {
                if (TextUtils.equals(str, userEntity.getUserId())) {
                    this.mData.remove(userEntity);
                    notifyDataSetChanged();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, final UserEntity userEntity) {
        mh.a(baseViewHolder.itemView).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new sh<Object>() {
            public void accept(Object obj) {
                if (UserAvatarListAdapter.this.listener != null) {
                    UserAvatarListAdapter.this.listener.onUserClick(userEntity);
                }
            }
        });
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_user_avatar), userEntity.getAvatar());
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.fq_year_guard_icon);
        imageView.setVisibility(userEntity.getGuardType() > p.a("0") ? 0 : 4);
        imageView.setImageResource(userEntity.getGuardType() == p.a("3") ? R.drawable.fq_ic_live_msg_year_guard : R.drawable.fq_ic_live_msg_mouth_guard);
    }

    public void setOnItemClickListener(UserListClickListener userListClickListener) {
        this.listener = userListClickListener;
    }
}
