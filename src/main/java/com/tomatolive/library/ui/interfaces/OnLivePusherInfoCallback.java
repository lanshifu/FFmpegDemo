package com.tomatolive.library.ui.interfaces;

import android.view.View;
import com.tomatolive.library.model.UserEntity;

public interface OnLivePusherInfoCallback {
    void onClickAnchorAvatarListener(View view);

    void onClickGiftNoticeListener(View view);

    void onClickSysNoticeListener(View view);

    void onClickUserAvatarListener(UserEntity userEntity);

    void onFollowAnchorListener(View view);
}
