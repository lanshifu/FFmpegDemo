package com.tomatolive.library.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.f;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.ChatEntity;
import com.tomatolive.library.ui.view.custom.UserGradeView;
import com.tomatolive.library.ui.view.widget.VerticalImageSpan;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChatMsgListAdapter extends BaseQuickAdapter<ChatEntity, BaseViewHolder> {
    private static final int MAX_ITEM_COUNT = 400;
    private final int invalidImgResID = -1;
    private boolean isBig = false;
    private OnItemClickListener listener;

    private class IconEntity {
        public Drawable guardDrawable;
        public Drawable roleDrawable;
        public Drawable userGradeDrawable;
        public String value;

        private IconEntity() {
            this.value = "";
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ChatEntity chatEntity);
    }

    public ChatMsgListAdapter(int i) {
        super(i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public void setTextSize(boolean z) {
        this.isBig = z;
        notifyDataSetChanged();
    }

    public void addMsg(ChatEntity chatEntity) {
        if (chatEntity != null) {
            if (this.mData == null) {
                this.mData = new LinkedList();
            }
            ensureMessageListNotOver(1);
            this.mData.add(chatEntity);
            notifyItemInserted(this.mData.size());
            if (!getRecyclerView().canScrollVertically(1)) {
                ((LinearLayoutManager) getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(this.mData.size() - 1, 0);
            }
        }
    }

    public void addMsgs(List<ChatEntity> list) {
        if (list != null && list.size() != 0) {
            if (this.mData == null) {
                this.mData = new LinkedList();
            }
            ensureMessageListNotOver(list.size());
            this.mData.addAll(list);
            notifyDataSetChanged();
            if (!getRecyclerView().canScrollVertically(1)) {
                ((LinearLayoutManager) getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(this.mData.size() - 1, 0);
            }
        }
    }

    private void ensureMessageListNotOver(int i) {
        if (i + this.mData.size() > MAX_ITEM_COUNT) {
            this.mData.removeAll(new ArrayList(this.mData.subList(100, 200)));
            notifyItemRangeRemoved(100, 100);
        }
    }

    public void convert(BaseViewHolder baseViewHolder, ChatEntity chatEntity) {
        LinearLayout linearLayout = (LinearLayout) baseViewHolder.getView(R.id.ll_msg_bg);
        TextView textView = (TextView) baseViewHolder.getView(R.id.tv_chat_text);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_car_icon);
        UserGradeView userGradeView = (UserGradeView) baseViewHolder.getView(R.id.user_grade_view);
        userGradeView.setVisibility(8);
        if (chatEntity != null && !TextUtils.isEmpty(chatEntity.getMsgText())) {
            baseViewHolder.itemView.setOnClickListener(new -$$Lambda$ChatMsgListAdapter$7DxDN8qA86vTkNOKLDY7MPPMtfo(this, chatEntity));
            linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.fq_shape_msg_bg));
            textView.setTextSize(2, this.isBig ? 18.0f : 12.0f);
            textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_white_color));
            imageView.setVisibility(8);
            IconEntity iconEntity;
            String msgSendName;
            int length;
            StringBuilder stringBuilder;
            SpannableString spannableString;
            int indexOf;
            StringBuilder stringBuilder2;
            String stringBuilder3;
            switch (chatEntity.getMsgType()) {
                case 0:
                    linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, getMsgTextJoinDrawableRes(chatEntity.getRole(), chatEntity.getGuardType(), chatEntity.getExpGrade())));
                    iconEntity = getIconEntity(chatEntity, userGradeView);
                    if (!TextUtils.isEmpty(chatEntity.getCarIcon())) {
                        imageView.setVisibility(0);
                        i.b(this.mContext, imageView, chatEntity.getCarIcon());
                    }
                    msgSendName = chatEntity.getMsgSendName();
                    length = iconEntity.value.length();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(iconEntity.value);
                    stringBuilder.append(msgSendName);
                    stringBuilder.append(" ");
                    stringBuilder.append(chatEntity.getMsgText());
                    iconEntity.value = stringBuilder.toString();
                    spannableString = new SpannableString(iconEntity.value);
                    setIcon(iconEntity, spannableString);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, getMsgTextJoinColorRes(chatEntity.getExpGrade()))), length, iconEntity.value.length(), 33);
                    textView.setText(spannableString);
                    break;
                case 1:
                case 3:
                    iconEntity = getIconEntity(chatEntity, userGradeView);
                    msgSendName = chatEntity.getMsgSendName();
                    length = iconEntity.value.length();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(iconEntity.value);
                    stringBuilder.append(msgSendName);
                    stringBuilder.append(getColonStr(chatEntity.getRole(), chatEntity.getMsgType()));
                    stringBuilder.append(chatEntity.getMsgText());
                    iconEntity.value = stringBuilder.toString();
                    int length2 = (msgSendName.length() + length) + getColonStr(chatEntity.getRole(), chatEntity.getMsgType()).length();
                    SpannableString spannableString2 = new SpannableString(iconEntity.value);
                    setIcon(iconEntity, spannableString2);
                    spannableString2.setSpan(new ForegroundColorSpan(getRoleMsgColor(chatEntity.getMsgType(), chatEntity.getRole())), length, length2, 33);
                    if (chatEntity.getMsgType() == 1) {
                        int length3 = this.mContext.getString(R.string.fq_give_to_anchor).length() + length2;
                        length = chatEntity.getGiftName().length() + length3;
                        if (length > iconEntity.value.length()) {
                            length = iconEntity.value.length();
                        }
                        spannableString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_white_color)), length2, length3, 33);
                        spannableString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_gift_color)), length3, length, 33);
                        spannableString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_white_color)), length, iconEntity.value.length(), 33);
                    } else {
                        spannableString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, b.k(chatEntity.getRole()) ? R.color.fq_live_msg_red_dark_color : R.color.fq_live_msg_white_color)), length2, iconEntity.value.length(), 33);
                    }
                    if (isSendNameDrawableRight(chatEntity.getRole())) {
                        indexOf = iconEntity.value.indexOf(chatEntity.getMsgText());
                        if (indexOf >= 2) {
                            spannableString2.setSpan(new ForegroundColorSpan(getRoleMsgColor(chatEntity.getMsgType(), chatEntity.getRole())), indexOf - 2, indexOf, 33);
                        }
                    }
                    textView.setText(spannableString2);
                    break;
                case 2:
                    textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color));
                    textView.setText(Html.fromHtml(this.mContext.getString(R.string.fq_socket_reconnet_click)));
                    break;
                case 4:
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(b.g());
                    stringBuilder2.append(chatEntity.getTargetName());
                    stringBuilder2.append(" ");
                    stringBuilder2.append(chatEntity.getMsgText());
                    stringBuilder3 = stringBuilder2.toString();
                    spannableString = new SpannableString(stringBuilder3);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color)), 0, b.h(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_white_color)), b.h(), b.h() + chatEntity.getTargetName().length(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color)), b.h() + chatEntity.getTargetName().length(), (b.h() + chatEntity.getTargetName().length()) + 3, 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_white_color)), (b.h() + chatEntity.getTargetName().length()) + 3, ((b.h() + chatEntity.getTargetName().length()) + 3) + chatEntity.getMsgSendName().length(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color)), ((b.h() + chatEntity.getTargetName().length()) + 3) + chatEntity.getMsgSendName().length(), stringBuilder3.length(), 33);
                    textView.setText(spannableString);
                    break;
                case 5:
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(b.g());
                    stringBuilder2.append(chatEntity.getMsgText());
                    textView.setText(stringBuilder2.toString());
                    textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color));
                    break;
                case 6:
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(b.g());
                    stringBuilder2.append(chatEntity.getTargetName());
                    stringBuilder2.append(" ");
                    stringBuilder2.append(chatEntity.getMsgText());
                    stringBuilder3 = stringBuilder2.toString();
                    spannableString = new SpannableString(stringBuilder3);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color)), 0, b.h(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_white_color)), b.h(), b.h() + chatEntity.getTargetName().length(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color)), b.h() + chatEntity.getTargetName().length(), stringBuilder3.length(), 33);
                    textView.setText(spannableString);
                    break;
                case 7:
                    textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color));
                    textView.setText(chatEntity.getMsgText());
                    break;
                case 8:
                    textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color));
                    textView.setText(this.mContext.getString(R.string.fq_modify_title_tip, new Object[]{chatEntity.getMsgText()}));
                    break;
                case 9:
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(b.g());
                    stringBuilder2.append(chatEntity.getTargetName());
                    stringBuilder2.append(" ");
                    stringBuilder2.append(chatEntity.getMsgText());
                    stringBuilder3 = stringBuilder2.toString();
                    spannableString = new SpannableString(stringBuilder3);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color)), 0, b.h(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_white_color)), b.h(), b.h() + chatEntity.getTargetName().length(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color)), b.h() + chatEntity.getTargetName().length(), (b.h() + chatEntity.getTargetName().length()) + 9, 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_white_color)), (b.h() + chatEntity.getTargetName().length()) + 9, ((b.h() + chatEntity.getTargetName().length()) + 9) + chatEntity.getMsgSendName().length(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color)), ((b.h() + chatEntity.getTargetName().length()) + 9) + chatEntity.getMsgSendName().length(), stringBuilder3.length(), 33);
                    textView.setText(spannableString);
                    break;
                case 10:
                    textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color));
                    textView.setText(chatEntity.getMsgText());
                    break;
                case 11:
                    stringBuilder3 = chatEntity.getExpGrade();
                    textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_green_color));
                    textView.setText(Html.fromHtml(this.mContext.getString(R.string.fq_speak_level_tip_for_socket, new Object[]{stringBuilder3})));
                    break;
                case 12:
                    indexOf = b.d(chatEntity.getGuardType()) ? R.drawable.fq_guard_year_bg_shape : R.drawable.fq_guard_mouth_bg_shape;
                    length = b.d(chatEntity.getGuardType()) ? R.color.fq_guard_year_text_bg : R.color.fq_guard_mouth_text_bg;
                    linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, indexOf));
                    iconEntity = getIconEntity(chatEntity, userGradeView);
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append(chatEntity.getMsgSendName());
                    stringBuilder4.append(" ");
                    msgSendName = stringBuilder4.toString();
                    int length4 = (iconEntity.value.length() + msgSendName.length()) + 3;
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append(iconEntity.value);
                    stringBuilder5.append(msgSendName);
                    stringBuilder5.append(chatEntity.getMsgText());
                    iconEntity.value = stringBuilder5.toString();
                    SpannableString spannableString3 = new SpannableString(iconEntity.value);
                    setIcon(iconEntity, spannableString3);
                    spannableString3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, length)), length4, iconEntity.value.length(), 33);
                    textView.setText(spannableString3);
                    break;
                case 13:
                    textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_live_msg_white_color));
                    textView.setText(Html.fromHtml(this.mContext.getString(R.string.fq_gift_box_broadcast_tips, new Object[]{chatEntity.getMsgSendName(), chatEntity.getTargetName(), chatEntity.getPropNum(), chatEntity.getPropName()})));
                    break;
            }
        }
    }

    public static /* synthetic */ void lambda$convert$0(ChatMsgListAdapter chatMsgListAdapter, ChatEntity chatEntity, View view) {
        if (chatMsgListAdapter.listener != null) {
            chatMsgListAdapter.listener.onItemClick(chatEntity);
        }
    }

    private void setIcon(IconEntity iconEntity, SpannableString spannableString) {
        if (iconEntity.roleDrawable != null) {
            spannableString.setSpan(new VerticalImageSpan(iconEntity.roleDrawable), 0, 1, 33);
            if (iconEntity.guardDrawable != null) {
                spannableString.setSpan(new VerticalImageSpan(iconEntity.guardDrawable), 2, 3, 33);
                if (iconEntity.userGradeDrawable != null) {
                    spannableString.setSpan(new VerticalImageSpan(iconEntity.userGradeDrawable), 4, 5, 33);
                }
            } else if (iconEntity.userGradeDrawable != null) {
                spannableString.setSpan(new VerticalImageSpan(iconEntity.userGradeDrawable), 2, 3, 33);
            }
        } else if (iconEntity.guardDrawable != null) {
            spannableString.setSpan(new VerticalImageSpan(iconEntity.guardDrawable), 0, 1, 33);
            if (iconEntity.userGradeDrawable != null) {
                spannableString.setSpan(new VerticalImageSpan(iconEntity.userGradeDrawable), 2, 3, 33);
            }
        } else if (iconEntity.userGradeDrawable != null) {
            spannableString.setSpan(new VerticalImageSpan(iconEntity.userGradeDrawable), 0, 1, 33);
        }
    }

    private Bitmap getViewBitmap(View view) {
        try {
            view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.buildDrawingCache();
            return Bitmap.createBitmap(view.getDrawingCache());
        } catch (Exception unused) {
            return null;
        }
    }

    private int getRoleMsgColor(int i, String str) {
        if (b.m(str)) {
            return ContextCompat.getColor(this.mContext, R.color.fq_live_msg_anchor_color);
        }
        if (b.k(str)) {
            return ContextCompat.getColor(this.mContext, R.color.fq_live_msg_super_color);
        }
        if (b.j(str)) {
            return ContextCompat.getColor(this.mContext, i == 1 ? R.color.fq_live_msg_gift_manager_color : R.color.fq_live_msg_manager_color);
        }
        return ContextCompat.getColor(this.mContext, i == 1 ? R.color.fq_live_msg_gift_audience_color : R.color.fq_live_msg_audience_color);
    }

    private String getColonStr(String str, int i) {
        if (isSendNameDrawableRight(str)) {
            return i == 1 ? " " : " : ";
        }
        return i == 1 ? " " : " : ";
    }

    private boolean isSendNameDrawableRight(String str) {
        return b.m(str) || b.j(str) || b.k(str);
    }

    @DrawableRes
    private int getRoleMsgIconRes(String str) {
        if (b.m(str)) {
            return this.isBig ? R.drawable.fq_ic_live_msg_anchor_big : R.drawable.fq_ic_live_msg_anchor;
        } else if (b.j(str)) {
            return this.isBig ? R.drawable.fq_ic_live_msg_manager_big : R.drawable.fq_ic_live_msg_manager;
        } else if (!b.k(str)) {
            return -1;
        } else {
            return this.isBig ? R.drawable.fq_ic_live_msg_super_big : R.drawable.fq_ic_live_msg_super;
        }
    }

    @DrawableRes
    private int getGuardIconRes(int i) {
        switch (i) {
            case 1:
            case 2:
                return this.isBig ? R.drawable.fq_ic_live_msg_mouth_guard_big : R.drawable.fq_ic_live_msg_mouth_guard;
            case 3:
                return this.isBig ? R.drawable.fq_ic_live_msg_year_guard_big : R.drawable.fq_ic_live_msg_year_guard;
            default:
                return -1;
        }
    }

    private Drawable formatDrawableBounds(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    @DrawableRes
    private int getMsgTextJoinDrawableRes(String str, int i, String str2) {
        if (b.k(str)) {
            return R.drawable.fq_shape_user_grade_second_join_super_bg;
        }
        if (b.m(str)) {
            return R.drawable.fq_shape_user_grade_second_join_anchor_bg;
        }
        return R.drawable.fq_shape_msg_bg;
    }

    private int getMsgTextJoinColorRes(String str) {
        return R.color.fq_live_msg_white_color;
    }

    private IconEntity getIconEntity(ChatEntity chatEntity, UserGradeView userGradeView) {
        IconEntity iconEntity = new IconEntity();
        String str = "";
        if (getRoleMsgIconRes(chatEntity.getRole()) != -1) {
            iconEntity.roleDrawable = formatDrawableBounds(ContextCompat.getDrawable(this.mContext, getRoleMsgIconRes(chatEntity.getRole())));
            if (iconEntity.roleDrawable != null) {
                str = "  ";
            }
        }
        if (chatEntity.getGuardType() > 0) {
            int guardIconRes = getGuardIconRes(chatEntity.getGuardType());
            if (guardIconRes != -1) {
                iconEntity.guardDrawable = formatDrawableBounds(ContextCompat.getDrawable(this.mContext, guardIconRes));
                if (iconEntity.guardDrawable != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("  ");
                    str = stringBuilder.toString();
                }
            }
        }
        userGradeView.initUserGradeMsg(chatEntity.getExpGrade(), true);
        Bitmap viewBitmap = getViewBitmap(userGradeView);
        if (viewBitmap != null) {
            iconEntity.userGradeDrawable = formatDrawableBounds(f.a(viewBitmap));
            if (iconEntity.userGradeDrawable != null) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("  ");
                str = stringBuilder2.toString();
            }
        }
        iconEntity.value = str;
        return iconEntity;
    }
}
