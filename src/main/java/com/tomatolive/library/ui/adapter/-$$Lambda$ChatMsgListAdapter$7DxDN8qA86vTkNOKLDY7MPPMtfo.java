package com.tomatolive.library.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.model.ChatEntity;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$ChatMsgListAdapter$7DxDN8qA86vTkNOKLDY7MPPMtfo implements OnClickListener {
    private final /* synthetic */ ChatMsgListAdapter f$0;
    private final /* synthetic */ ChatEntity f$1;

    public /* synthetic */ -$$Lambda$ChatMsgListAdapter$7DxDN8qA86vTkNOKLDY7MPPMtfo(ChatMsgListAdapter chatMsgListAdapter, ChatEntity chatEntity) {
        this.f$0 = chatMsgListAdapter;
        this.f$1 = chatEntity;
    }

    public final void onClick(View view) {
        ChatMsgListAdapter.lambda$convert$0(this.f$0, this.f$1, view);
    }
}
