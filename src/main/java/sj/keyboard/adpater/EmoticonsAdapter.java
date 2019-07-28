package sj.keyboard.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.keyboard.view.R;
import java.util.ArrayList;
import sj.keyboard.data.EmoticonPageEntity;
import sj.keyboard.data.EmoticonPageEntity.DelBtnStatus;
import sj.keyboard.interfaces.EmoticonClickListener;
import sj.keyboard.interfaces.EmoticonDisplayListener;

public class EmoticonsAdapter<T> extends BaseAdapter {
    protected final int DEF_HEIGHTMAXTATIO = 2;
    protected Context mContext;
    protected ArrayList<T> mData = new ArrayList();
    protected final int mDefalutItemHeight;
    protected int mDelbtnPosition;
    protected EmoticonPageEntity mEmoticonPageEntity;
    protected LayoutInflater mInflater;
    protected int mItemHeight;
    protected int mItemHeightMax;
    protected double mItemHeightMaxRatio;
    protected int mItemHeightMin;
    protected EmoticonDisplayListener mOnDisPlayListener;
    protected EmoticonClickListener mOnEmoticonClickListener;

    public static class ViewHolder {
        public ImageView iv_emoticon;
        public LinearLayout ly_root;
        public View rootView;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public EmoticonsAdapter(Context context, EmoticonPageEntity emoticonPageEntity, EmoticonClickListener emoticonClickListener) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mEmoticonPageEntity = emoticonPageEntity;
        this.mOnEmoticonClickListener = emoticonClickListener;
        this.mItemHeightMaxRatio = 2.0d;
        this.mDelbtnPosition = -1;
        int dimension = (int) context.getResources().getDimension(R.dimen.item_emoticon_size_default);
        this.mItemHeight = dimension;
        this.mDefalutItemHeight = dimension;
        this.mData.addAll(emoticonPageEntity.getEmoticonList());
        checkDelBtn(emoticonPageEntity);
    }

    private void checkDelBtn(EmoticonPageEntity emoticonPageEntity) {
        DelBtnStatus delBtnStatus = emoticonPageEntity.getDelBtnStatus();
        if (!DelBtnStatus.GONE.equals(delBtnStatus)) {
            if (DelBtnStatus.FOLLOW.equals(delBtnStatus)) {
                this.mDelbtnPosition = getCount();
                this.mData.add(null);
            } else if (DelBtnStatus.LAST.equals(delBtnStatus)) {
                int line = emoticonPageEntity.getLine() * emoticonPageEntity.getRow();
                while (getCount() < line) {
                    this.mData.add(null);
                }
                this.mDelbtnPosition = getCount() - 1;
            }
        }
    }

    public int getCount() {
        return this.mData == null ? 0 : this.mData.size();
    }

    public Object getItem(int i) {
        return this.mData == null ? null : this.mData.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View inflate;
        if (view == null) {
            viewHolder = new ViewHolder();
            inflate = this.mInflater.inflate(R.layout.item_emoticon, null);
            viewHolder.rootView = inflate;
            viewHolder.ly_root = (LinearLayout) inflate.findViewById(R.id.ly_root);
            viewHolder.iv_emoticon = (ImageView) inflate.findViewById(R.id.iv_emoticon);
            inflate.setTag(viewHolder);
        } else {
            inflate = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        bindView(i, viewGroup, viewHolder);
        updateUI(viewHolder, viewGroup);
        return inflate;
    }

    /* Access modifiers changed, original: protected */
    public void bindView(int i, ViewGroup viewGroup, ViewHolder viewHolder) {
        if (this.mOnDisPlayListener != null) {
            this.mOnDisPlayListener.onBindView(i, viewGroup, viewHolder, this.mData.get(i), i == this.mDelbtnPosition);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isDelBtn(int i) {
        return i == this.mDelbtnPosition;
    }

    /* Access modifiers changed, original: protected */
    public void updateUI(ViewHolder viewHolder, ViewGroup viewGroup) {
        int i;
        if (this.mDefalutItemHeight != this.mItemHeight) {
            viewHolder.iv_emoticon.setLayoutParams(new LayoutParams(-1, this.mItemHeight));
        }
        if (this.mItemHeightMax != 0) {
            i = this.mItemHeightMax;
        } else {
            double d = (double) this.mItemHeight;
            double d2 = this.mItemHeightMaxRatio;
            Double.isNaN(d);
            i = (int) (d * d2);
        }
        this.mItemHeightMax = i;
        this.mItemHeightMin = this.mItemHeightMin != 0 ? this.mItemHeightMin : this.mItemHeight;
        viewHolder.ly_root.setLayoutParams(new LayoutParams(-1, Math.max(Math.min(((View) viewGroup.getParent()).getMeasuredHeight() / this.mEmoticonPageEntity.getLine(), this.mItemHeightMax), this.mItemHeightMin)));
    }

    public void setOnDisPlayListener(EmoticonDisplayListener emoticonDisplayListener) {
        this.mOnDisPlayListener = emoticonDisplayListener;
    }

    public void setItemHeightMaxRatio(double d) {
        this.mItemHeightMaxRatio = d;
    }

    public void setItemHeightMax(int i) {
        this.mItemHeightMax = i;
    }

    public void setItemHeightMin(int i) {
        this.mItemHeightMin = i;
    }

    public void setItemHeight(int i) {
        this.mItemHeight = i;
    }

    public void setDelbtnPosition(int i) {
        this.mDelbtnPosition = i;
    }
}
