package sj.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import com.keyboard.view.R;
import java.util.ArrayList;
import java.util.Iterator;
import sj.keyboard.adpater.PageSetAdapter;
import sj.keyboard.data.PageSetEntity;
import sj.keyboard.utils.EmoticonsKeyboardUtils;
import sj.keyboard.widget.EmoticonsFuncView;
import sj.keyboard.widget.EmoticonsFuncView.OnEmoticonsPageViewListener;
import sj.keyboard.widget.EmoticonsIndicatorView;
import sj.keyboard.widget.EmoticonsToolBarView;
import sj.keyboard.widget.EmoticonsToolBarView.OnToolBarItemClickListener;

public class EmoticonsKeyBoardPopWindow extends PopupWindow implements OnEmoticonsPageViewListener, OnToolBarItemClickListener {
    private Context mContext;
    protected EmoticonsFuncView mEmoticonsFuncView;
    protected EmoticonsIndicatorView mEmoticonsIndicatorView;
    protected EmoticonsToolBarView mEmoticonsToolBarView;

    public EmoticonsKeyBoardPopWindow(Context context) {
        super(context, null);
        this.mContext = context;
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.view_func_emoticon, null);
        setContentView(inflate);
        setWidth(EmoticonsKeyboardUtils.getDisplayWidthPixels(this.mContext));
        setHeight(EmoticonsKeyboardUtils.getDefKeyboardHeight(this.mContext));
        setAnimationStyle(R.style.PopupAnimation);
        setOutsideTouchable(true);
        update();
        setBackgroundDrawable(new ColorDrawable(0));
        updateView(inflate);
    }

    private void updateView(View view) {
        this.mEmoticonsFuncView = (EmoticonsFuncView) view.findViewById(R.id.view_epv);
        this.mEmoticonsIndicatorView = (EmoticonsIndicatorView) view.findViewById(R.id.view_eiv);
        this.mEmoticonsToolBarView = (EmoticonsToolBarView) view.findViewById(R.id.view_etv);
        this.mEmoticonsFuncView.setOnIndicatorListener(this);
        this.mEmoticonsToolBarView.setOnToolBarItemClickListener(this);
    }

    public void setAdapter(PageSetAdapter pageSetAdapter) {
        if (pageSetAdapter != null) {
            ArrayList pageSetEntityList = pageSetAdapter.getPageSetEntityList();
            if (pageSetEntityList != null) {
                Iterator it = pageSetEntityList.iterator();
                while (it.hasNext()) {
                    this.mEmoticonsToolBarView.addToolItemView((PageSetEntity) it.next());
                }
            }
        }
        this.mEmoticonsFuncView.setAdapter(pageSetAdapter);
    }

    public void showPopupWindow() {
        View rootView = EmoticonsKeyboardUtils.getRootView((Activity) this.mContext);
        if (isShowing()) {
            dismiss();
            return;
        }
        EmoticonsKeyboardUtils.closeSoftKeyboard(this.mContext);
        showAtLocation(rootView, 80, 0, 0);
    }

    public void emoticonSetChanged(PageSetEntity pageSetEntity) {
        this.mEmoticonsToolBarView.setToolBtnSelect(pageSetEntity.getUuid());
    }

    public void playTo(int i, PageSetEntity pageSetEntity) {
        this.mEmoticonsIndicatorView.playTo(i, pageSetEntity);
    }

    public void playBy(int i, int i2, PageSetEntity pageSetEntity) {
        this.mEmoticonsIndicatorView.playBy(i, i2, pageSetEntity);
    }

    public void onToolBarItemClick(PageSetEntity pageSetEntity) {
        this.mEmoticonsFuncView.setCurrentPageSet(pageSetEntity);
    }
}
