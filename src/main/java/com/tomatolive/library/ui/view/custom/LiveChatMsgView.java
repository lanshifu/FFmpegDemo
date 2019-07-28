package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.blankj.utilcode.util.m;
import com.tomatolive.library.R;
import com.tomatolive.library.model.ChatEntity;
import com.tomatolive.library.ui.adapter.ChatMsgListAdapter;
import com.tomatolive.library.ui.adapter.ChatMsgListAdapter.OnItemClickListener;
import com.tomatolive.library.ui.view.divider.RVDividerListMsg;
import com.tomatolive.library.ui.view.widget.ScrollTextView;
import com.tomatolive.library.ui.view.widget.ScrollTextViewForGrade;
import defpackage.sh;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.k;
import java.util.List;

public class LiveChatMsgView extends RelativeLayout {
    private Context mContext;
    private RecyclerView mRecycleChatMsg;
    private ChatMsgListAdapter msgListAdapter;
    private TextView tvUnreadMsg;
    private ScrollTextViewForGrade tvUserGrade;
    private ScrollTextView tvUserName;

    public LiveChatMsgView(Context context) {
        super(context);
        initView(context);
    }

    public LiveChatMsgView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.fq_layout_chat_msg_view, this);
        this.mContext = context;
        this.mRecycleChatMsg = (RecyclerView) findViewById(R.id.rv_chat_msg);
        this.tvUnreadMsg = (TextView) findViewById(R.id.tv_unread_msg);
        this.tvUserGrade = (ScrollTextViewForGrade) findViewById(R.id.tv_user_grade);
        this.tvUserName = (ScrollTextView) findViewById(R.id.tv_user_name);
        initMsgAdapter();
        initListener();
    }

    private void initMsgAdapter() {
        this.msgListAdapter = new ChatMsgListAdapter(R.layout.fq_recycle_item_chat_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setAutoMeasureEnabled(false);
        linearLayoutManager.setOrientation(1);
        this.mRecycleChatMsg.setLayoutManager(linearLayoutManager);
        this.mRecycleChatMsg.setHasFixedSize(true);
        this.mRecycleChatMsg.addItemDecoration(new RVDividerListMsg(this.mContext, 17170445));
        this.mRecycleChatMsg.setAdapter(this.msgListAdapter);
        this.msgListAdapter.bindToRecyclerView(this.mRecycleChatMsg);
    }

    private void initListener() {
        this.mRecycleChatMsg.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (!recyclerView.canScrollVertically(1)) {
                    LiveChatMsgView.this.tvUnreadMsg.setVisibility(4);
                }
            }
        });
        this.tvUnreadMsg.setOnClickListener(new -$$Lambda$LiveChatMsgView$tXtngGTSUXJHxzVMKYDLvk_Dgv4(this));
    }

    public static /* synthetic */ void lambda$initListener$0(LiveChatMsgView liveChatMsgView, View view) {
        liveChatMsgView.tvUnreadMsg.setVisibility(4);
        liveChatMsgView.mRecycleChatMsg.scrollToPosition(liveChatMsgView.msgListAdapter.getItemCount() - 1);
    }

    public void setOnChatMsgItemClickListener(OnItemClickListener onItemClickListener) {
        this.msgListAdapter.setOnItemClickListener(onItemClickListener);
    }

    public void addChatMsg(ChatEntity chatEntity) {
        this.msgListAdapter.addMsg(chatEntity);
        if (this.tvUnreadMsg.getVisibility() != 0 && this.mRecycleChatMsg.canScrollVertically(1)) {
            this.tvUnreadMsg.setVisibility(0);
        }
    }

    public void addChatMsgList(List<ChatEntity> list) {
        this.msgListAdapter.addMsgs(list);
        if (this.tvUnreadMsg.getVisibility() != 0 && this.mRecycleChatMsg.canScrollVertically(1)) {
            this.tvUnreadMsg.setVisibility(0);
        }
    }

    public void clearChatMsg() {
        if (this.msgListAdapter != null && this.msgListAdapter.getData() != null) {
            this.msgListAdapter.getData().clear();
            this.msgListAdapter.notifyDataSetChanged();
        }
    }

    public void setUserGradeInfo(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(" ");
        stringBuilder.append(getResources().getString(R.string.fq_live_joinroom_notify));
        str2 = stringBuilder.toString();
        this.tvUserGrade.setText(str);
        this.tvUserName.setText(str2);
    }

    public void setChatMsgBigSize(DisplayMetrics displayMetrics, boolean z) {
        this.msgListAdapter.setTextSize(z);
        LayoutParams layoutParams = (LayoutParams) this.mRecycleChatMsg.getLayoutParams();
        layoutParams.height = z ? displayMetrics.heightPixels / 2 : m.a(180.0f);
        this.mRecycleChatMsg.setLayoutParams(layoutParams);
        if (!z) {
            this.mRecycleChatMsg.scrollToPosition(this.msgListAdapter.getItemCount() - 1);
        }
        float f = 12.0f;
        this.tvUserGrade.updateSize(z ? 18.0f : 12.0f);
        ScrollTextView scrollTextView = this.tvUserName;
        if (z) {
            f = 18.0f;
        }
        scrollTextView.updateSize(f);
    }

    public void updateGuardTypeItemDataByUid(String str, int i) {
        k.just(this.msgListAdapter.getData()).map(new -$$Lambda$LiveChatMsgView$y6f5fJwk_R99Axp5LfvEmGMeHZs(str, i)).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new sh<Boolean>() {
            public void accept(Boolean bool) {
                LiveChatMsgView.this.msgListAdapter.notifyDataSetChanged();
            }
        });
    }

    static /* synthetic */ Boolean lambda$updateGuardTypeItemDataByUid$1(String str, int i, List list) throws Exception {
        for (ChatEntity chatEntity : list) {
            if (TextUtils.equals(chatEntity.getUid(), str)) {
                chatEntity.setGuardType(i);
            }
        }
        return Boolean.valueOf(true);
    }

    public void updateRoleItemDataByUid(String str, String str2) {
        k.just(this.msgListAdapter.getData()).map(new -$$Lambda$LiveChatMsgView$t7nbYMOnRM2znOllNDG79y41Axk(str, str2)).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new sh<Boolean>() {
            public void accept(Boolean bool) {
                LiveChatMsgView.this.msgListAdapter.notifyDataSetChanged();
            }
        });
    }

    static /* synthetic */ Boolean lambda$updateRoleItemDataByUid$2(String str, String str2, List list) throws Exception {
        for (ChatEntity chatEntity : list) {
            if (TextUtils.equals(chatEntity.getUid(), str)) {
                chatEntity.setRole(str2);
            }
        }
        return Boolean.valueOf(true);
    }
}
