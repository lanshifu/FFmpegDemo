package com.tomatolive.library.ui.view.widget.matisse.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Album;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.AlbumCollection;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.AlbumCollection.AlbumCallbacks;
import com.tomatolive.library.ui.view.widget.matisse.internal.model.SelectedItemCollection;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.BasePreviewActivity;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.MediaSelectionFragment;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.MediaSelectionFragment.SelectionProvider;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.SelectedPreviewActivity;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter.AlbumMediaAdapter.CheckStateListener;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter.AlbumMediaAdapter.OnMediaClickListener;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter.AlbumMediaAdapter.OnPhotoCapture;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter.AlbumsAdapter;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.AlbumsSpinner;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckRadioView;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.IncapableDialog;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.MediaStoreCompat;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.PathUtils;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.PhotoMetadataUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class MatisseActivity extends AppCompatActivity implements OnClickListener, OnItemSelectedListener, AlbumCallbacks, SelectionProvider, CheckStateListener, OnMediaClickListener, OnPhotoCapture {
    public static final String CHECK_STATE = "checkState";
    public static final String EXTRA_RESULT_ORIGINAL_ENABLE = "extra_result_original_enable";
    public static final String EXTRA_RESULT_SELECTION = "extra_result_selection";
    public static final String EXTRA_RESULT_SELECTION_PATH = "extra_result_selection_path";
    private static final int REQUEST_CODE_CAPTURE = 24;
    private static final int REQUEST_CODE_PREVIEW = 23;
    private final AlbumCollection mAlbumCollection = new AlbumCollection();
    private AlbumsAdapter mAlbumsAdapter;
    private AlbumsSpinner mAlbumsSpinner;
    private TextView mButtonApply;
    private TextView mButtonPreview;
    private View mContainer;
    private View mEmptyView;
    private MediaStoreCompat mMediaStoreCompat;
    private CheckRadioView mOriginal;
    private boolean mOriginalEnable;
    private LinearLayout mOriginalLayout;
    private SelectedItemCollection mSelectedCollection = new SelectedItemCollection(this);
    private SelectionSpec mSpec;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.mSpec = SelectionSpec.getInstance();
        setTheme(this.mSpec.themeId);
        super.onCreate(bundle);
        if (this.mSpec.hasInited) {
            setContentView(R.layout.fq_matisse_activity_matisse);
            if (this.mSpec.needOrientationRestriction()) {
                setRequestedOrientation(this.mSpec.orientation);
            }
            if (this.mSpec.capture) {
                this.mMediaStoreCompat = new MediaStoreCompat(this);
                if (this.mSpec.captureStrategy != null) {
                    this.mMediaStoreCompat.setCaptureStrategy(this.mSpec.captureStrategy);
                } else {
                    throw new RuntimeException("Don't forget to set CaptureStrategy.");
                }
            }
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar supportActionBar = getSupportActionBar();
            supportActionBar.setDisplayShowTitleEnabled(false);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            Drawable navigationIcon = toolbar.getNavigationIcon();
            TypedArray obtainStyledAttributes = getTheme().obtainStyledAttributes(new int[]{R.attr.album_element_color});
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            navigationIcon.setColorFilter(color, Mode.SRC_IN);
            this.mButtonPreview = (TextView) findViewById(R.id.button_preview);
            this.mButtonApply = (TextView) findViewById(R.id.button_apply);
            this.mButtonPreview.setOnClickListener(this);
            this.mButtonApply.setOnClickListener(this);
            this.mContainer = findViewById(R.id.container);
            this.mEmptyView = findViewById(R.id.empty_view);
            this.mOriginalLayout = (LinearLayout) findViewById(R.id.originalLayout);
            this.mOriginal = (CheckRadioView) findViewById(R.id.original);
            this.mOriginalLayout.setOnClickListener(this);
            this.mSelectedCollection.onCreate(bundle);
            if (bundle != null) {
                this.mOriginalEnable = bundle.getBoolean("checkState");
            }
            updateBottomToolbar();
            this.mAlbumsAdapter = new AlbumsAdapter((Context) this, null, false);
            this.mAlbumsSpinner = new AlbumsSpinner(this);
            this.mAlbumsSpinner.setOnItemSelectedListener(this);
            this.mAlbumsSpinner.setSelectedTextView((TextView) findViewById(R.id.selected_album));
            this.mAlbumsSpinner.setPopupAnchorView(findViewById(R.id.toolbar));
            this.mAlbumsSpinner.setAdapter(this.mAlbumsAdapter);
            this.mAlbumCollection.onCreate(this, this);
            this.mAlbumCollection.onRestoreInstanceState(bundle);
            this.mAlbumCollection.loadAlbums();
            return;
        }
        setResult(0);
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mSelectedCollection.onSaveInstanceState(bundle);
        this.mAlbumCollection.onSaveInstanceState(bundle);
        bundle.putBoolean("checkState", this.mOriginalEnable);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mAlbumCollection.onDestroy();
        this.mSpec.onCheckedListener = null;
        this.mSpec.onSelectedListener = null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        setResult(0);
        super.onBackPressed();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            ArrayList arrayList;
            ArrayList arrayList2;
            if (i == 23) {
                Bundle bundleExtra = intent.getBundleExtra(BasePreviewActivity.EXTRA_RESULT_BUNDLE);
                ArrayList parcelableArrayList = bundleExtra.getParcelableArrayList(SelectedItemCollection.STATE_SELECTION);
                this.mOriginalEnable = intent.getBooleanExtra("extra_result_original_enable", false);
                i = bundleExtra.getInt(SelectedItemCollection.STATE_COLLECTION_TYPE, 0);
                if (intent.getBooleanExtra(BasePreviewActivity.EXTRA_RESULT_APPLY, false)) {
                    Intent intent2 = new Intent();
                    arrayList = new ArrayList();
                    arrayList2 = new ArrayList();
                    if (parcelableArrayList != null) {
                        Iterator it = parcelableArrayList.iterator();
                        while (it.hasNext()) {
                            Item item = (Item) it.next();
                            arrayList.add(item.getContentUri());
                            arrayList2.add(PathUtils.getPath(this, item.getContentUri()));
                        }
                    }
                    intent2.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, arrayList);
                    intent2.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, arrayList2);
                    intent2.putExtra("extra_result_original_enable", this.mOriginalEnable);
                    setResult(-1, intent2);
                    finish();
                } else {
                    this.mSelectedCollection.overwrite(parcelableArrayList, i);
                    Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(MediaSelectionFragment.class.getSimpleName());
                    if (findFragmentByTag instanceof MediaSelectionFragment) {
                        ((MediaSelectionFragment) findFragmentByTag).refreshMediaGrid();
                    }
                    updateBottomToolbar();
                }
            } else if (i == 24) {
                Uri currentPhotoUri = this.mMediaStoreCompat.getCurrentPhotoUri();
                String currentPhotoPath = this.mMediaStoreCompat.getCurrentPhotoPath();
                arrayList = new ArrayList();
                arrayList.add(currentPhotoUri);
                arrayList2 = new ArrayList();
                arrayList2.add(currentPhotoPath);
                Intent intent3 = new Intent();
                intent3.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, arrayList);
                intent3.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, arrayList2);
                setResult(-1, intent3);
                if (VERSION.SDK_INT < 21) {
                    revokeUriPermission(currentPhotoUri, 3);
                }
                finish();
            }
        }
    }

    private void updateBottomToolbar() {
        int count = this.mSelectedCollection.count();
        if (count == 0) {
            this.mButtonPreview.setEnabled(false);
            this.mButtonApply.setEnabled(false);
            this.mButtonApply.setText(getString(R.string.fq_matisse_button_sure_default));
        } else if (count == 1 && this.mSpec.singleSelectionModeEnabled()) {
            this.mButtonPreview.setEnabled(true);
            this.mButtonApply.setText(R.string.fq_matisse_button_sure_default);
            this.mButtonApply.setEnabled(true);
        } else {
            this.mButtonPreview.setEnabled(true);
            this.mButtonApply.setEnabled(true);
            this.mButtonApply.setText(getString(R.string.fq_matisse_button_sure, new Object[]{Integer.valueOf(count)}));
        }
        if (this.mSpec.originalable) {
            this.mOriginalLayout.setVisibility(0);
            updateOriginalState();
            return;
        }
        this.mOriginalLayout.setVisibility(4);
    }

    private void updateOriginalState() {
        this.mOriginal.setChecked(this.mOriginalEnable);
        if (countOverMaxSize() > 0 && this.mOriginalEnable) {
            IncapableDialog.newInstance("", getString(R.string.fq_matisse_error_over_original_size, new Object[]{Integer.valueOf(this.mSpec.originalMaxSize)})).show(getSupportFragmentManager(), IncapableDialog.class.getName());
            this.mOriginal.setChecked(false);
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

    public void onClick(View view) {
        Intent intent;
        if (view.getId() == R.id.button_preview) {
            intent = new Intent(this, SelectedPreviewActivity.class);
            intent.putExtra(BasePreviewActivity.EXTRA_DEFAULT_BUNDLE, this.mSelectedCollection.getDataWithBundle());
            intent.putExtra("extra_result_original_enable", this.mOriginalEnable);
            startActivityForResult(intent, 23);
        } else if (view.getId() == R.id.button_apply) {
            intent = new Intent();
            intent.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, (ArrayList) this.mSelectedCollection.asListOfUri());
            intent.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, (ArrayList) this.mSelectedCollection.asListOfString());
            intent.putExtra("extra_result_original_enable", this.mOriginalEnable);
            setResult(-1, intent);
            finish();
        } else if (view.getId() == R.id.originalLayout) {
            if (countOverMaxSize() > 0) {
                IncapableDialog.newInstance("", getString(R.string.fq_matisse_error_over_original_count, new Object[]{Integer.valueOf(r6), Integer.valueOf(this.mSpec.originalMaxSize)})).show(getSupportFragmentManager(), IncapableDialog.class.getName());
                return;
            }
            this.mOriginalEnable ^= 1;
            this.mOriginal.setChecked(this.mOriginalEnable);
            if (this.mSpec.onCheckedListener != null) {
                this.mSpec.onCheckedListener.onCheck(this.mOriginalEnable);
            }
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.mAlbumCollection.setStateCurrentSelection(i);
        this.mAlbumsAdapter.getCursor().moveToPosition(i);
        Album valueOf = Album.valueOf(this.mAlbumsAdapter.getCursor());
        if (valueOf.isAll() && SelectionSpec.getInstance().capture) {
            valueOf.addCaptureCount();
        }
        onAlbumSelected(valueOf);
    }

    public void onAlbumLoad(final Cursor cursor) {
        this.mAlbumsAdapter.swapCursor(cursor);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                cursor.moveToPosition(MatisseActivity.this.mAlbumCollection.getCurrentSelection());
                MatisseActivity.this.mAlbumsSpinner.setSelection(MatisseActivity.this, MatisseActivity.this.mAlbumCollection.getCurrentSelection());
                Album valueOf = Album.valueOf(cursor);
                if (valueOf.isAll() && SelectionSpec.getInstance().capture) {
                    valueOf.addCaptureCount();
                }
                MatisseActivity.this.onAlbumSelected(valueOf);
            }
        });
    }

    public void onAlbumReset() {
        this.mAlbumsAdapter.swapCursor(null);
    }

    private void onAlbumSelected(Album album) {
        if (album.isAll() && album.isEmpty()) {
            this.mContainer.setVisibility(8);
            this.mEmptyView.setVisibility(0);
            return;
        }
        this.mContainer.setVisibility(0);
        this.mEmptyView.setVisibility(8);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MediaSelectionFragment.newInstance(album), MediaSelectionFragment.class.getSimpleName()).commitAllowingStateLoss();
    }

    public void onUpdate() {
        updateBottomToolbar();
        if (this.mSpec.onSelectedListener != null) {
            this.mSpec.onSelectedListener.onSelected(this.mSelectedCollection.asListOfUri(), this.mSelectedCollection.asListOfString());
        }
    }

    public void onMediaClick(Album album, Item item, int i) {
        Uri contentUri = item.getContentUri();
        ArrayList arrayList = new ArrayList();
        arrayList.add(contentUri);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(PathUtils.getPath(this, contentUri));
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, arrayList);
        intent.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, arrayList2);
        setResult(-1, intent);
        if (VERSION.SDK_INT < 21) {
            revokeUriPermission(contentUri, 3);
        }
        finish();
    }

    public SelectedItemCollection provideSelectedItemCollection() {
        return this.mSelectedCollection;
    }

    public void capture() {
        if (this.mMediaStoreCompat != null) {
            this.mMediaStoreCompat.dispatchCaptureIntent(this, 24);
        }
    }
}
