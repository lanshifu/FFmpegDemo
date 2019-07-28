package com.tomatolive.library.ui.view.gift;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;
import com.tomatolive.library.ui.view.gift.GiftFrameLayout.LeftGiftAnimationStatusListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GiftAnimManage implements LeftGiftAnimationStatusListener {
    private static final int FROM_BOTTOM_TO_TOP = 0;
    private static final int FROM_TOP_TO_BOTTOM = 1;
    private static final String TAG = "GiftAnimManage";
    private int curDisplayMode = 1;
    private ICustomAnim custormAnim;
    private boolean isHideMode;
    private Context mContext;
    private int mGiftLayoutMaxNums;
    private LinearLayout mGiftLayoutParent;
    private final ArrayList<GiftAnimModel> mGiftQueue;
    private OnDeleteGiftAnimListener onDeleteGiftAnimLisenter;

    public interface OnDeleteGiftAnimListener {
        void onDelete(GiftAnimModel giftAnimModel);
    }

    public GiftAnimManage(Context context) {
        this.mContext = context;
        this.mGiftQueue = new ArrayList();
    }

    public GiftAnimManage setCustormAnim(ICustomAnim iCustomAnim) {
        this.custormAnim = iCustomAnim;
        return this;
    }

    public GiftAnimManage setHideMode(boolean z) {
        this.isHideMode = z;
        return this;
    }

    public GiftAnimManage setDisplayMode(int i) {
        this.curDisplayMode = i;
        return this;
    }

    public void loadGift(GiftAnimModel giftAnimModel) {
        loadGift(giftAnimModel, true);
    }

    public void loadReceiveGift(GiftAnimModel giftAnimModel) {
        loadReceiveGift(giftAnimModel, true);
    }

    private void loadGift(GiftAnimModel giftAnimModel, boolean z) {
        if (this.mGiftQueue != null) {
            if (z) {
                if (this.mGiftLayoutParent != null) {
                    for (int i = 0; i < this.mGiftLayoutParent.getChildCount(); i++) {
                        GiftFrameLayout giftFrameLayout = (GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i);
                        if (giftFrameLayout.isShowing() && giftFrameLayout.getCurrentGiftId().equals(giftAnimModel.getGiftId()) && giftFrameLayout.getCurrentSendUserId().equals(giftAnimModel.getSendUserId())) {
                            if (giftAnimModel.getJumpCombo() > 0) {
                                giftFrameLayout.setGiftAddCount(giftAnimModel.getJumpCombo());
                            } else {
                                giftFrameLayout.setGiftCount(giftAnimModel.getGiftCount());
                                giftFrameLayout.setJumpCombo(0);
                            }
                            giftFrameLayout.setSendGiftTime(giftAnimModel.getSendGiftTime().longValue());
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
            addGiftQueue(giftAnimModel, z);
        }
    }

    private void loadReceiveGift(GiftAnimModel giftAnimModel, boolean z) {
        if (this.mGiftQueue != null) {
            if (z) {
                if (this.mGiftLayoutParent != null) {
                    for (int i = 0; i < this.mGiftLayoutParent.getChildCount(); i++) {
                        GiftFrameLayout giftFrameLayout = (GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i);
                        if (giftFrameLayout.isShowing() && giftFrameLayout.getCurrentGiftId().equals(giftAnimModel.getGiftId()) && giftFrameLayout.getCurrentSendUserId().equals(giftAnimModel.getSendUserId())) {
                            giftFrameLayout.setGiftCount(giftAnimModel.getGiftCount());
                            giftFrameLayout.setJumpCombo(0);
                            giftFrameLayout.setSendGiftTime(giftAnimModel.getSendGiftTime().longValue());
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
            addGiftQueue(giftAnimModel, z);
        }
    }

    private void addGiftQueue(GiftAnimModel giftAnimModel, boolean z) {
        if (this.mGiftQueue == null || this.mGiftQueue.size() != 0) {
            if (z) {
                Object obj = null;
                Iterator it = this.mGiftQueue.iterator();
                while (it.hasNext()) {
                    GiftAnimModel giftAnimModel2 = (GiftAnimModel) it.next();
                    if (giftAnimModel2.getGiftId().equals(giftAnimModel.getGiftId()) && giftAnimModel2.getSendUserId().equals(giftAnimModel.getSendUserId())) {
                        giftAnimModel2.setGiftCount(giftAnimModel2.getGiftCount() + giftAnimModel.getGiftCount());
                        obj = 1;
                        break;
                    }
                }
                if (obj == null && this.mGiftQueue != null) {
                    this.mGiftQueue.add(giftAnimModel);
                    showGift(null);
                }
            } else if (this.mGiftQueue != null) {
                this.mGiftQueue.add(giftAnimModel);
                showGift(null);
            }
            return;
        }
        this.mGiftQueue.add(giftAnimModel);
        showGift(null);
    }

    /* JADX WARNING: Missing block: B:46:0x0117, code skipped:
            return;
     */
    private synchronized void showGift(com.tomatolive.library.ui.view.gift.GiftAnimModel r6) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r5);
        return;
    L_0x0007:
        r0 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r0 = r0.getChildCount();	 Catch:{ all -> 0x0118 }
        r1 = r5.mGiftLayoutMaxNums;	 Catch:{ all -> 0x0118 }
        r2 = 1;
        r3 = 0;
        if (r0 >= r1) goto L_0x0079;
    L_0x0013:
        r0 = new com.tomatolive.library.ui.view.gift.GiftFrameLayout;	 Catch:{ all -> 0x0118 }
        r1 = r5.mContext;	 Catch:{ all -> 0x0118 }
        r0.<init>(r1);	 Catch:{ all -> 0x0118 }
        r0.setIndex(r3);	 Catch:{ all -> 0x0118 }
        r0.setGiftAnimationListener(r5);	 Catch:{ all -> 0x0118 }
        r1 = r5.isHideMode;	 Catch:{ all -> 0x0118 }
        r0.setHideMode(r1);	 Catch:{ all -> 0x0118 }
        r1 = r5.curDisplayMode;	 Catch:{ all -> 0x0118 }
        r4 = 12;
        if (r1 != 0) goto L_0x003c;
    L_0x002b:
        r1 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r1 = r1.getLayoutParams();	 Catch:{ all -> 0x0118 }
        r1 = (android.widget.RelativeLayout.LayoutParams) r1;	 Catch:{ all -> 0x0118 }
        r1.addRule(r4);	 Catch:{ all -> 0x0118 }
        r1 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r1.addView(r0);	 Catch:{ all -> 0x0118 }
        goto L_0x0061;
    L_0x003c:
        r1 = r5.curDisplayMode;	 Catch:{ all -> 0x0118 }
        if (r1 != r2) goto L_0x0051;
    L_0x0040:
        r1 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r1 = r1.getLayoutParams();	 Catch:{ all -> 0x0118 }
        r1 = (android.widget.RelativeLayout.LayoutParams) r1;	 Catch:{ all -> 0x0118 }
        r1.addRule(r4, r3);	 Catch:{ all -> 0x0118 }
        r1 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r1.addView(r0, r3);	 Catch:{ all -> 0x0118 }
        goto L_0x0061;
    L_0x0051:
        r1 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r1 = r1.getLayoutParams();	 Catch:{ all -> 0x0118 }
        r1 = (android.widget.RelativeLayout.LayoutParams) r1;	 Catch:{ all -> 0x0118 }
        r1.addRule(r4);	 Catch:{ all -> 0x0118 }
        r1 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r1.addView(r0);	 Catch:{ all -> 0x0118 }
    L_0x0061:
        if (r6 == 0) goto L_0x0068;
    L_0x0063:
        r6 = r0.setGift(r6);	 Catch:{ all -> 0x0118 }
        goto L_0x0070;
    L_0x0068:
        r6 = r5.getGift();	 Catch:{ all -> 0x0118 }
        r6 = r0.setGift(r6);	 Catch:{ all -> 0x0118 }
    L_0x0070:
        if (r6 == 0) goto L_0x0116;
    L_0x0072:
        r6 = r5.custormAnim;	 Catch:{ all -> 0x0118 }
        r0.startAnimation(r6);	 Catch:{ all -> 0x0118 }
        goto L_0x0116;
    L_0x0079:
        r6 = r5.getGift();	 Catch:{ all -> 0x0118 }
        if (r6 != 0) goto L_0x0081;
    L_0x007f:
        monitor-exit(r5);
        return;
    L_0x0081:
        r0 = r6.getSendUserId();	 Catch:{ all -> 0x0118 }
        r1 = com.tomatolive.library.utils.z.a();	 Catch:{ all -> 0x0118 }
        r1 = r1.c();	 Catch:{ all -> 0x0118 }
        r0 = android.text.TextUtils.equals(r0, r1);	 Catch:{ all -> 0x0118 }
        if (r0 == 0) goto L_0x00b6;
    L_0x0093:
        r0 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r0 = r0.getChildAt(r3);	 Catch:{ all -> 0x0118 }
        r0 = (com.tomatolive.library.ui.view.gift.GiftFrameLayout) r0;	 Catch:{ all -> 0x0118 }
        r0.setDelete(r2);	 Catch:{ all -> 0x0118 }
        r0 = r0.getGift();	 Catch:{ all -> 0x0118 }
        r1 = r5.onDeleteGiftAnimLisenter;	 Catch:{ all -> 0x0118 }
        r1 = r1 instanceof com.tomatolive.library.ui.view.gift.GiftAnimManage.OnDeleteGiftAnimListener;	 Catch:{ all -> 0x0118 }
        if (r1 == 0) goto L_0x00ad;
    L_0x00a8:
        r1 = r5.onDeleteGiftAnimLisenter;	 Catch:{ all -> 0x0118 }
        r1.onDelete(r0);	 Catch:{ all -> 0x0118 }
    L_0x00ad:
        r0 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r0.removeViewAt(r3);	 Catch:{ all -> 0x0118 }
        r5.showGift(r6);	 Catch:{ all -> 0x0118 }
        goto L_0x0116;
    L_0x00b6:
        r0 = r6.getEffectType();	 Catch:{ all -> 0x0118 }
        r1 = "2";
        r0 = android.text.TextUtils.equals(r0, r1);	 Catch:{ all -> 0x0118 }
        if (r0 == 0) goto L_0x0116;
    L_0x00c2:
        r0 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r0 = r0.getChildCount();	 Catch:{ all -> 0x0118 }
        if (r3 >= r0) goto L_0x0116;
    L_0x00ca:
        r0 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r0 = r0.getChildAt(r3);	 Catch:{ all -> 0x0118 }
        r0 = (com.tomatolive.library.ui.view.gift.GiftFrameLayout) r0;	 Catch:{ all -> 0x0118 }
        r0 = r0.getGift();	 Catch:{ all -> 0x0118 }
        r1 = r0.getSendUserId();	 Catch:{ all -> 0x0118 }
        r4 = com.tomatolive.library.utils.z.a();	 Catch:{ all -> 0x0118 }
        r4 = r4.c();	 Catch:{ all -> 0x0118 }
        r1 = android.text.TextUtils.equals(r1, r4);	 Catch:{ all -> 0x0118 }
        if (r1 != 0) goto L_0x0113;
    L_0x00e8:
        r1 = r0.getEffectType();	 Catch:{ all -> 0x0118 }
        r4 = "1";
        r1 = android.text.TextUtils.equals(r1, r4);	 Catch:{ all -> 0x0118 }
        if (r1 == 0) goto L_0x0113;
    L_0x00f4:
        r1 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r1 = r1.getChildAt(r3);	 Catch:{ all -> 0x0118 }
        r1 = (com.tomatolive.library.ui.view.gift.GiftFrameLayout) r1;	 Catch:{ all -> 0x0118 }
        r1.setDelete(r2);	 Catch:{ all -> 0x0118 }
        r1 = r5.onDeleteGiftAnimLisenter;	 Catch:{ all -> 0x0118 }
        r1 = r1 instanceof com.tomatolive.library.ui.view.gift.GiftAnimManage.OnDeleteGiftAnimListener;	 Catch:{ all -> 0x0118 }
        if (r1 == 0) goto L_0x010a;
    L_0x0105:
        r1 = r5.onDeleteGiftAnimLisenter;	 Catch:{ all -> 0x0118 }
        r1.onDelete(r0);	 Catch:{ all -> 0x0118 }
    L_0x010a:
        r0 = r5.mGiftLayoutParent;	 Catch:{ all -> 0x0118 }
        r0.removeViewAt(r3);	 Catch:{ all -> 0x0118 }
        r5.showGift(r6);	 Catch:{ all -> 0x0118 }
        goto L_0x0116;
    L_0x0113:
        r3 = r3 + 1;
        goto L_0x00c2;
    L_0x0116:
        monitor-exit(r5);
        return;
    L_0x0118:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.gift.GiftAnimManage.showGift(com.tomatolive.library.ui.view.gift.GiftAnimModel):void");
    }

    public GiftAnimManage setOnDeleteGiftAnimListener(OnDeleteGiftAnimListener onDeleteGiftAnimListener) {
        this.onDeleteGiftAnimLisenter = onDeleteGiftAnimListener;
        return this;
    }

    private synchronized GiftAnimModel getGift() {
        GiftAnimModel giftAnimModel;
        giftAnimModel = null;
        if (this.mGiftQueue.size() != 0) {
            giftAnimModel = (GiftAnimModel) this.mGiftQueue.get(0);
            this.mGiftQueue.remove(0);
        }
        return giftAnimModel;
    }

    public int getCurGiftCountByUserId(String str, String str2) {
        int i = 0;
        for (int i2 = 0; i2 < this.mGiftLayoutParent.getChildCount(); i2++) {
            GiftAnimModel gift = ((GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i2)).getGift();
            if (gift == null || !gift.getGiftId().equals(str) || !gift.getSendUserId().equals(str2)) {
                Iterator it = this.mGiftQueue.iterator();
                while (it.hasNext()) {
                    GiftAnimModel giftAnimModel = (GiftAnimModel) it.next();
                    if (giftAnimModel.getGiftId().equals(str) && giftAnimModel.getSendUserId().equals(str2)) {
                        i = giftAnimModel.getGiftCount();
                        break;
                    }
                }
            } else {
                i = gift.getGiftCount();
            }
        }
        return i;
    }

    public int getShowingGiftLayoutCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.mGiftLayoutParent.getChildCount(); i2++) {
            if (((GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i2)).isShowing()) {
                i++;
            }
        }
        return i;
    }

    public List<GiftFrameLayout> getShowingGiftLayouts() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mGiftLayoutParent.getChildCount(); i++) {
            GiftFrameLayout giftFrameLayout = (GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i);
            if (giftFrameLayout.isShowing()) {
                arrayList.add(giftFrameLayout);
            }
        }
        return arrayList;
    }

    public void dismiss(GiftFrameLayout giftFrameLayout) {
        reStartAnimation(giftFrameLayout, giftFrameLayout.getIndex());
    }

    private void reStartAnimation(final GiftFrameLayout giftFrameLayout, int i) {
        giftFrameLayout.setCurrentShowStatus(false);
        AnimatorSet endAnimation = giftFrameLayout.endAnimation(this.custormAnim);
        if (endAnimation != null) {
            endAnimation.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    giftFrameLayout.CurrentEndStatus(true);
                    giftFrameLayout.setGiftViewEndVisibility(GiftAnimManage.this.isEmpty());
                    GiftAnimManage.this.mGiftLayoutParent.removeView(giftFrameLayout);
                    if (!GiftAnimManage.this.isEmpty()) {
                        GiftAnimManage.this.showGift(null);
                    }
                }
            });
        }
    }

    public GiftAnimManage reSetGiftLayout(LinearLayout linearLayout, @NonNull int i) {
        return setGiftLayout(linearLayout, i);
    }

    public GiftAnimManage setGiftLayout(LinearLayout linearLayout, @NonNull int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("GiftFrameLayout数量必须大于0");
        } else if (linearLayout.getChildCount() > 0) {
            return this;
        } else {
            this.mGiftLayoutParent = linearLayout;
            this.mGiftLayoutMaxNums = i;
            LayoutTransition layoutTransition = new LayoutTransition();
            layoutTransition.setAnimator(0, layoutTransition.getAnimator(0));
            layoutTransition.setAnimator(2, layoutTransition.getAnimator(2));
            layoutTransition.setAnimator(3, layoutTransition.getAnimator(0));
            layoutTransition.setAnimator(1, layoutTransition.getAnimator(3));
            this.mGiftLayoutParent.setLayoutTransition(layoutTransition);
            return this;
        }
    }

    public synchronized void cleanAll() {
        if (this.mGiftLayoutParent != null) {
            if (this.mGiftQueue != null) {
                this.mGiftQueue.clear();
            }
            for (int i = 0; i < this.mGiftLayoutParent.getChildCount(); i++) {
                GiftFrameLayout giftFrameLayout = (GiftFrameLayout) this.mGiftLayoutParent.getChildAt(i);
                if (giftFrameLayout != null) {
                    giftFrameLayout.getGift().setAnimationListener(null);
                    giftFrameLayout.clearHandler();
                    giftFrameLayout.firstHideLayout();
                }
            }
            this.mGiftLayoutParent.removeAllViews();
        }
    }

    private synchronized boolean isEmpty() {
        boolean z;
        z = this.mGiftQueue == null || this.mGiftQueue.size() == 0;
        return z;
    }
}
