package com.tomatolive.library.ui.view.widget.matisse.internal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.IncapableCause;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.SelectedItemCollection;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter.PreviewPagerAdapter;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckRadioView;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.IncapableDialog;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.PhotoMetadataUtils;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.Platform;
import com.tomatolive.library.ui.view.widget.matisse.listener.OnFragmentInteractionListener;

public abstract class BasePreviewActivity extends AppCompatActivity implements OnPageChangeListener, OnClickListener, OnFragmentInteractionListener {
    public static final String CHECK_STATE = "checkState";
    public static final String EXTRA_DEFAULT_BUNDLE = "extra_default_bundle";
    public static final String EXTRA_RESULT_APPLY = "extra_result_apply";
    public static final String EXTRA_RESULT_BUNDLE = "extra_result_bundle";
    public static final String EXTRA_RESULT_ORIGINAL_ENABLE = "extra_result_original_enable";
    protected PreviewPagerAdapter mAdapter;
    private FrameLayout mBottomToolbar;
    protected TextView mButtonApply;
    protected TextView mButtonBack;
    protected CheckView mCheckView;
    private boolean mIsToolbarHide = false;
    private CheckRadioView mOriginal;
    protected boolean mOriginalEnable;
    private LinearLayout mOriginalLayout;
    protected ViewPager mPager;
    protected int mPreviousPos = -1;
    protected final SelectedItemCollection mSelectedCollection = new SelectedItemCollection(this);
    protected TextView mSize;
    protected SelectionSpec mSpec;
    private FrameLayout mTopToolbar;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        setTheme(SelectionSpec.getInstance().themeId);
        super.onCreate(bundle);
        if (SelectionSpec.getInstance().hasInited) {
            setContentView(R.layout.fq_matisse_activity_media_preview);
            if (Platform.hasKitKat()) {
                getWindow().addFlags(67108864);
            }
            this.mSpec = SelectionSpec.getInstance();
            if (this.mSpec.needOrientationRestriction()) {
                setRequestedOrientation(this.mSpec.orientation);
            }
            if (bundle == null) {
                this.mSelectedCollection.onCreate(getIntent().getBundleExtra(EXTRA_DEFAULT_BUNDLE));
                this.mOriginalEnable = getIntent().getBooleanExtra("extra_result_original_enable", false);
            } else {
                this.mSelectedCollection.onCreate(bundle);
                this.mOriginalEnable = bundle.getBoolean("checkState");
            }
            this.mButtonBack = (TextView) findViewById(R.id.button_back);
            this.mButtonApply = (TextView) findViewById(R.id.button_apply);
            this.mSize = (TextView) findViewById(R.id.size);
            this.mButtonBack.setOnClickListener(this);
            this.mButtonApply.setOnClickListener(this);
            this.mPager = (ViewPager) findViewById(R.id.pager);
            this.mPager.addOnPageChangeListener(this);
            this.mAdapter = new PreviewPagerAdapter(getSupportFragmentManager(), null);
            this.mPager.setAdapter(this.mAdapter);
            this.mCheckView = (CheckView) findViewById(R.id.check_view);
            this.mCheckView.setCountable(this.mSpec.countable);
            this.mBottomToolbar = (FrameLayout) findViewById(R.id.bottom_toolbar);
            this.mTopToolbar = (FrameLayout) findViewById(R.id.top_toolbar);
            this.mCheckView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Item mediaItem = BasePreviewActivity.this.mAdapter.getMediaItem(BasePreviewActivity.this.mPager.getCurrentItem());
                    if (BasePreviewActivity.this.mSelectedCollection.isSelected(mediaItem)) {
                        BasePreviewActivity.this.mSelectedCollection.remove(mediaItem);
                        if (BasePreviewActivity.this.mSpec.countable) {
                            BasePreviewActivity.this.mCheckView.setCheckedNum(CheckView.UNCHECKED);
                        } else {
                            BasePreviewActivity.this.mCheckView.setChecked(false);
                        }
                    } else if (BasePreviewActivity.this.assertAddSelection(mediaItem)) {
                        BasePreviewActivity.this.mSelectedCollection.add(mediaItem);
                        if (BasePreviewActivity.this.mSpec.countable) {
                            BasePreviewActivity.this.mCheckView.setCheckedNum(BasePreviewActivity.this.mSelectedCollection.checkedNumOf(mediaItem));
                        } else {
                            BasePreviewActivity.this.mCheckView.setChecked(true);
                        }
                    }
                    BasePreviewActivity.this.updateApplyButton();
                    if (BasePreviewActivity.this.mSpec.onSelectedListener != null) {
                        BasePreviewActivity.this.mSpec.onSelectedListener.onSelected(BasePreviewActivity.this.mSelectedCollection.asListOfUri(), BasePreviewActivity.this.mSelectedCollection.asListOfString());
                    }
                }
            });
            this.mOriginalLayout = (LinearLayout) findViewById(R.id.originalLayout);
            this.mOriginal = (CheckRadioView) findViewById(R.id.original);
            this.mOriginalLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (BasePreviewActivity.this.countOverMaxSize() > 0) {
                        IncapableDialog.newInstance("", BasePreviewActivity.this.getString(R.string.fq_matisse_error_over_original_count, new Object[]{Integer.valueOf(r7), Integer.valueOf(BasePreviewActivity.this.mSpec.originalMaxSize)})).show(BasePreviewActivity.this.getSupportFragmentManager(), IncapableDialog.class.getName());
                        return;
                    }
                    BasePreviewActivity.this.mOriginalEnable = 1 ^ BasePreviewActivity.this.mOriginalEnable;
                    BasePreviewActivity.this.mOriginal.setChecked(BasePreviewActivity.this.mOriginalEnable);
                    if (!BasePreviewActivity.this.mOriginalEnable) {
                        BasePreviewActivity.this.mOriginal.setColor(-1);
                    }
                    if (BasePreviewActivity.this.mSpec.onCheckedListener != null) {
                        BasePreviewActivity.this.mSpec.onCheckedListener.onCheck(BasePreviewActivity.this.mOriginalEnable);
                    }
                }
            });
            updateApplyButton();
            return;
        }
        setResult(0);
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle bundle) {
        this.mSelectedCollection.onSaveInstanceState(bundle);
        bundle.putBoolean("checkState", this.mOriginalEnable);
        super.onSaveInstanceState(bundle);
    }

    public void onBackPressed() {
        sendBackResult(false);
        super.onBackPressed();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_back) {
            onBackPressed();
        } else if (view.getId() == R.id.button_apply) {
            sendBackResult(true);
            finish();
        }
    }

    public void onClick() {
        if (this.mSpec.autoHideToobar) {
            if (this.mIsToolbarHide) {
                this.mTopToolbar.animate().setInterpolator(new FastOutSlowInInterpolator()).translationYBy((float) this.mTopToolbar.getMeasuredHeight()).start();
                this.mBottomToolbar.animate().translationYBy((float) (-this.mBottomToolbar.getMeasuredHeight())).setInterpolator(new FastOutSlowInInterpolator()).start();
            } else {
                this.mTopToolbar.animate().setInterpolator(new FastOutSlowInInterpolator()).translationYBy((float) (-this.mTopToolbar.getMeasuredHeight())).start();
                this.mBottomToolbar.animate().setInterpolator(new FastOutSlowInInterpolator()).translationYBy((float) this.mBottomToolbar.getMeasuredHeight()).start();
            }
            this.mIsToolbarHide ^= 1;
        }
    }

    public void onPageSelected(int i) {
        PreviewPagerAdapter previewPagerAdapter = (PreviewPagerAdapter) this.mPager.getAdapter();
        if (!(this.mPreviousPos == -1 || this.mPreviousPos == i)) {
            ((PreviewItemFragment) previewPagerAdapter.instantiateItem(this.mPager, this.mPreviousPos)).resetView();
            Item mediaItem = previewPagerAdapter.getMediaItem(i);
            if (this.mSpec.countable) {
                int checkedNumOf = this.mSelectedCollection.checkedNumOf(mediaItem);
                this.mCheckView.setCheckedNum(checkedNumOf);
                if (checkedNumOf > 0) {
                    this.mCheckView.setEnabled(true);
                } else {
                    this.mCheckView.setEnabled(1 ^ this.mSelectedCollection.maxSelectableReached());
                }
            } else {
                boolean isSelected = this.mSelectedCollection.isSelected(mediaItem);
                this.mCheckView.setChecked(isSelected);
                if (isSelected) {
                    this.mCheckView.setEnabled(true);
                } else {
                    this.mCheckView.setEnabled(1 ^ this.mSelectedCollection.maxSelectableReached());
                }
            }
            updateSize(mediaItem);
        }
        this.mPreviousPos = i;
    }

    private void updateApplyButton() {
        int count = this.mSelectedCollection.count();
        if (count == 0) {
            this.mButtonApply.setText(R.string.fq_matisse_button_sure_default);
            this.mButtonApply.setEnabled(false);
        } else if (count == 1 && this.mSpec.singleSelectionModeEnabled()) {
            this.mButtonApply.setText(R.string.fq_matisse_button_sure_default);
            this.mButtonApply.setEnabled(true);
        } else {
            this.mButtonApply.setEnabled(true);
            this.mButtonApply.setText(getString(R.string.fq_matisse_button_sure, new Object[]{Integer.valueOf(count)}));
        }
        if (this.mSpec.originalable) {
            this.mOriginalLayout.setVisibility(0);
            updateOriginalState();
            return;
        }
        this.mOriginalLayout.setVisibility(8);
    }

    private void updateOriginalState() {
        this.mOriginal.setChecked(this.mOriginalEnable);
        if (!this.mOriginalEnable) {
            this.mOriginal.setColor(-1);
        }
        if (countOverMaxSize() > 0 && this.mOriginalEnable) {
            IncapableDialog.newInstance("", getString(R.string.fq_matisse_error_over_original_size, new Object[]{Integer.valueOf(this.mSpec.originalMaxSize)})).show(getSupportFragmentManager(), IncapableDialog.class.getName());
            this.mOriginal.setChecked(false);
            this.mOriginal.setColor(-1);
            this.mOriginalEnable = false;
        }
    }

    private int countOverMaxSize() {
        int count = this.mSelectedCollection.count();
        int i = 0;
        for (int i2 = 0; i2 < count; i2++) {
            Item item = (Item) this.mSelectedCollection.asList().get(i2);
            if (item.isImage() && PhotoMetadataUtils.getSizeInMB(item.size) > ((float) this.mSpec.originalMaxSize)) {
                i++;
            }
        }
        return i;
    }

    /* Access modifiers changed, original: protected */
    public void updateSize(Item item) {
        if (item.isGif()) {
            this.mSize.setVisibility(0);
            TextView textView = this.mSize;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(PhotoMetadataUtils.getSizeInMB(item.size));
            stringBuilder.append("M");
            textView.setText(stringBuilder.toString());
        } else {
            this.mSize.setVisibility(8);
        }
        if (item.isVideo()) {
            this.mOriginalLayout.setVisibility(8);
        } else if (this.mSpec.originalable) {
            this.mOriginalLayout.setVisibility(0);
        }
    }

    /* Access modifiers changed, original: protected */
    public void sendBackResult(boolean z) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULT_BUNDLE, this.mSelectedCollection.getDataWithBundle());
        intent.putExtra(EXTRA_RESULT_APPLY, z);
        intent.putExtra("extra_result_original_enable", this.mOriginalEnable);
        setResult(-1, intent);
    }

    private boolean assertAddSelection(Item item) {
        IncapableCause isAcceptable = this.mSelectedCollection.isAcceptable(item);
        IncapableCause.handleCause(this, isAcceptable);
        return isAcceptable == null;
    }
}
