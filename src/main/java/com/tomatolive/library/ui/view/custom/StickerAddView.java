package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.blankj.utilcode.util.o;
import com.tomatolive.library.R;
import com.tomatolive.library.model.db.StickerEntity;
import com.tomatolive.library.ui.view.sticker.IMGTextEditDialog;
import com.tomatolive.library.ui.view.sticker.core.IMGText;
import com.tomatolive.library.ui.view.sticker.view.IMGStickerTextView;
import com.tomatolive.library.ui.view.sticker.view.IMGView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.c;
import com.tomatolive.library.utils.l;
import com.tomatolive.library.utils.r;
import com.tomatolive.library.utils.u;
import com.tomatolive.library.utils.z;
import defpackage.sh;
import defpackage.xl;
import io.reactivex.k;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StickerAddView extends RelativeLayout {
    private Context mContext;
    private FragmentManager mFragmentManager;
    private IMGTextEditDialog mImgTextEditDialog;
    private IMGView mStickerShowView;
    private OnAddStickerCallback onAddStickerCallback;

    public interface OnAddStickerCallback {
        void onSaveStickerClick();
    }

    public StickerAddView(Context context) {
        this(context, null);
    }

    public StickerAddView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }

    private void initView() {
        inflate(this.mContext, R.layout.fq_layout_sticker_add_view, this);
        addStickerView();
        this.mImgTextEditDialog = new IMGTextEditDialog(this.mContext, new -$$Lambda$StickerAddView$gBikn53EvhY9J_MmAEeYehRLjcc(this));
        addLastTextList(c.b());
        initListener();
    }

    public static /* synthetic */ void lambda$initView$0(StickerAddView stickerAddView, IMGText iMGText) {
        iMGText.setId(u.a());
        stickerAddView.mStickerShowView.addStickerText(iMGText);
    }

    private void addStickerView() {
        this.mStickerShowView = new IMGView(this.mContext);
        this.mStickerShowView.setBackgroundColor(0);
        addView(this.mStickerShowView, 0, new LayoutParams(-1, -1));
    }

    private void initListener() {
        r.a().a(findViewById(R.id.iv_sticker_back), 800, new -$$Lambda$StickerAddView$aSZGRp6RqQBYmzSNuvcTJGjO_9o(this));
        r.a().a(findViewById(R.id.tv_sticker_save), 800, new -$$Lambda$StickerAddView$xW2EXSv2PKjAgKOFASea1aX-iZ4(this));
        r.a().a(findViewById(R.id.tv_sticker_help), 800, new -$$Lambda$StickerAddView$kra-SaimYzpmn3uJNI34FQrDPWI(this));
        r.a().a(findViewById(R.id.rl_sticker_add_text), 800, new -$$Lambda$StickerAddView$Uj5bkcCgYQVx0WM3JH8jihGjK7U(this));
        this.mImgTextEditDialog.setOnDismissListener(new -$$Lambda$StickerAddView$i1m_4SmRYtbZYsj6i0YW-QTMPLo(this));
    }

    public static /* synthetic */ void lambda$initListener$2(StickerAddView stickerAddView, Object obj) {
        if (stickerAddView.onAddStickerCallback != null) {
            k.just(stickerAddView.getStickerWaterImgPath()).subscribeOn(xl.b()).observeOn(xl.b()).subscribe(new sh<String>() {
                public void accept(String str) {
                    StickerAddView.this.mStickerShowView.post(new -$$Lambda$StickerAddView$2$Z6lTXeC0TbKxRDq2eF19KJujY6I(this));
                    StickerAddView.this.onAddStickerCallback.onSaveStickerClick();
                    List arrayList = new ArrayList();
                    int childCount = StickerAddView.this.mStickerShowView.getChildCount();
                    if (childCount == 0) {
                        c.a();
                        return;
                    }
                    for (int i = 0; i < childCount; i++) {
                        View childAt = StickerAddView.this.mStickerShowView.getChildAt(i);
                        if (childAt instanceof IMGStickerTextView) {
                            IMGStickerTextView iMGStickerTextView = (IMGStickerTextView) childAt;
                            arrayList.add(new StickerEntity(iMGStickerTextView.getText().getId(), z.a().c(), childAt.getTranslationX(), childAt.getTranslationY(), iMGStickerTextView.getScale(), childAt.getRotation(), iMGStickerTextView.getText().getColor(), iMGStickerTextView.getText().getText()));
                        }
                    }
                    c.b(arrayList);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$initListener$4(StickerAddView stickerAddView, Object obj) {
        if (stickerAddView.mStickerShowView.getChildCount() >= 5) {
            o.a(R.string.fq_sticker_add_max_tips);
            return;
        }
        if (stickerAddView.mImgTextEditDialog != null) {
            stickerAddView.mImgTextEditDialog.show();
            stickerAddView.setVisibility(4);
        }
    }

    public void initData(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public OnAddStickerCallback getOnAddStickerCallback() {
        return this.onAddStickerCallback;
    }

    public void setOnAddStickerCallback(OnAddStickerCallback onAddStickerCallback) {
        this.onAddStickerCallback = onAddStickerCallback;
    }

    public void addLastTextList(List<StickerEntity> list) {
        if (list != null && !list.isEmpty()) {
            for (StickerEntity addLastText : list) {
                addLastText(addLastText);
            }
        }
    }

    public void addLastText(StickerEntity stickerEntity) {
        this.mStickerShowView.addLastStickerText(new IMGText(stickerEntity.uuID, stickerEntity.text, stickerEntity.color), stickerEntity);
    }

    private boolean isStickerEdit() {
        int childCount = this.mStickerShowView.getChildCount();
        if (childCount != c.c()) {
            return true;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mStickerShowView.getChildAt(i);
            boolean z = childAt instanceof IMGStickerTextView;
            if (z && ((IMGStickerTextView) childAt).isShowing()) {
                return true;
            }
            if (z && isEdit((IMGStickerTextView) childAt)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEdit(IMGStickerTextView iMGStickerTextView) {
        boolean z = false;
        if (TextUtils.isEmpty(iMGStickerTextView.getText().getId())) {
            return false;
        }
        StickerEntity b = c.b(iMGStickerTextView.getText().getId());
        if (b == null) {
            return false;
        }
        if (!(iMGStickerTextView.getText().getColor() == b.color && TextUtils.equals(iMGStickerTextView.getText().getText(), b.text) && iMGStickerTextView.getTranslationX() == b.translationX && iMGStickerTextView.getTranslationY() == b.translationY && iMGStickerTextView.getRotation() == b.rotation && iMGStickerTextView.getScale() == b.scale)) {
            z = true;
        }
        return z;
    }

    private String getStickerWaterImgPath() {
        FileNotFoundException e;
        Throwable th;
        String l = b.l();
        Bitmap saveBitmap = this.mStickerShowView.saveBitmap();
        if (saveBitmap != null) {
            Closeable closeable = null;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(l);
                try {
                    saveBitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                    l.a(fileOutputStream);
                } catch (FileNotFoundException e2) {
                    e = e2;
                    closeable = fileOutputStream;
                    try {
                        e.printStackTrace();
                        l.a(closeable);
                        return l;
                    } catch (Throwable th2) {
                        th = th2;
                        l.a(closeable);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    closeable = fileOutputStream;
                    l.a(closeable);
                    throw th;
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                e.printStackTrace();
                l.a(closeable);
                return l;
            }
        }
        return l;
    }
}
