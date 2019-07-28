package sj.keyboard.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import java.util.Iterator;
import sj.keyboard.adpater.PageSetAdapter;
import sj.keyboard.data.PageSetEntity;

public class EmoticonsFuncView extends ViewPager {
    protected int mCurrentPagePosition;
    private OnEmoticonsPageViewListener mOnEmoticonsPageViewListener;
    protected PageSetAdapter mPageSetAdapter;

    public interface OnEmoticonsPageViewListener {
        void emoticonSetChanged(PageSetEntity pageSetEntity);

        void playBy(int i, int i2, PageSetEntity pageSetEntity);

        void playTo(int i, PageSetEntity pageSetEntity);
    }

    public EmoticonsFuncView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setAdapter(PageSetAdapter pageSetAdapter) {
        super.setAdapter(pageSetAdapter);
        this.mPageSetAdapter = pageSetAdapter;
        setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                EmoticonsFuncView.this.checkPageChange(i);
                EmoticonsFuncView.this.mCurrentPagePosition = i;
            }
        });
        if (this.mOnEmoticonsPageViewListener != null && !this.mPageSetAdapter.getPageSetEntityList().isEmpty()) {
            PageSetEntity pageSetEntity = (PageSetEntity) this.mPageSetAdapter.getPageSetEntityList().get(0);
            this.mOnEmoticonsPageViewListener.playTo(0, pageSetEntity);
            this.mOnEmoticonsPageViewListener.emoticonSetChanged(pageSetEntity);
        }
    }

    public void setCurrentPageSet(PageSetEntity pageSetEntity) {
        if (this.mPageSetAdapter != null && this.mPageSetAdapter.getCount() > 0) {
            setCurrentItem(this.mPageSetAdapter.getPageSetStartPosition(pageSetEntity));
        }
    }

    public void checkPageChange(int i) {
        if (this.mPageSetAdapter != null) {
            Iterator it = this.mPageSetAdapter.getPageSetEntityList().iterator();
            int i2 = 0;
            while (it.hasNext()) {
                PageSetEntity pageSetEntity = (PageSetEntity) it.next();
                int pageCount = pageSetEntity.getPageCount();
                int i3 = i2 + pageCount;
                if (i3 > i) {
                    Object obj = 1;
                    if (this.mCurrentPagePosition - i2 >= pageCount) {
                        if (this.mOnEmoticonsPageViewListener != null) {
                            this.mOnEmoticonsPageViewListener.playTo(i - i2, pageSetEntity);
                        }
                    } else if (this.mCurrentPagePosition - i2 >= 0) {
                        if (this.mOnEmoticonsPageViewListener != null) {
                            this.mOnEmoticonsPageViewListener.playBy(this.mCurrentPagePosition - i2, i - i2, pageSetEntity);
                        }
                        obj = null;
                    } else if (this.mOnEmoticonsPageViewListener != null) {
                        this.mOnEmoticonsPageViewListener.playTo(0, pageSetEntity);
                    }
                    if (!(obj == null || this.mOnEmoticonsPageViewListener == null)) {
                        this.mOnEmoticonsPageViewListener.emoticonSetChanged(pageSetEntity);
                    }
                    return;
                }
                i2 = i3;
            }
        }
    }

    public void setOnIndicatorListener(OnEmoticonsPageViewListener onEmoticonsPageViewListener) {
        this.mOnEmoticonsPageViewListener = onEmoticonsPageViewListener;
    }
}
