package com.tomatolive.library.ui.view.widget.matisse.internal.model;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Bundle;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.IncapableCause;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.PathUtils;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.PhotoMetadataUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SelectedItemCollection {
    public static final int COLLECTION_IMAGE = 1;
    public static final int COLLECTION_MIXED = 3;
    public static final int COLLECTION_UNDEFINED = 0;
    public static final int COLLECTION_VIDEO = 2;
    public static final String STATE_COLLECTION_TYPE = "state_collection_type";
    public static final String STATE_SELECTION = "state_selection";
    private int mCollectionType = 0;
    private final Context mContext;
    private Set<Item> mItems;

    public SelectedItemCollection(Context context) {
        this.mContext = context;
    }

    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            this.mItems = new LinkedHashSet();
            return;
        }
        this.mItems = new LinkedHashSet(bundle.getParcelableArrayList(STATE_SELECTION));
        this.mCollectionType = bundle.getInt(STATE_COLLECTION_TYPE, 0);
    }

    public void setDefaultSelection(List<Item> list) {
        this.mItems.addAll(list);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArrayList(STATE_SELECTION, new ArrayList(this.mItems));
        bundle.putInt(STATE_COLLECTION_TYPE, this.mCollectionType);
    }

    public Bundle getDataWithBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STATE_SELECTION, new ArrayList(this.mItems));
        bundle.putInt(STATE_COLLECTION_TYPE, this.mCollectionType);
        return bundle;
    }

    public boolean add(Item item) {
        if (typeConflict(item)) {
            throw new IllegalArgumentException("Can't select images and videos at the same time.");
        }
        boolean add = this.mItems.add(item);
        if (add) {
            if (this.mCollectionType == 0) {
                if (item.isImage()) {
                    this.mCollectionType = 1;
                } else if (item.isVideo()) {
                    this.mCollectionType = 2;
                }
            } else if (this.mCollectionType == 1) {
                if (item.isVideo()) {
                    this.mCollectionType = 3;
                }
            } else if (this.mCollectionType == 2 && item.isImage()) {
                this.mCollectionType = 3;
            }
        }
        return add;
    }

    public boolean remove(Item item) {
        boolean remove = this.mItems.remove(item);
        if (remove) {
            if (this.mItems.size() == 0) {
                this.mCollectionType = 0;
            } else if (this.mCollectionType == 3) {
                refineCollectionType();
            }
        }
        return remove;
    }

    public void overwrite(ArrayList<Item> arrayList, int i) {
        if (arrayList.size() == 0) {
            this.mCollectionType = 0;
        } else {
            this.mCollectionType = i;
        }
        this.mItems.clear();
        this.mItems.addAll(arrayList);
    }

    public List<Item> asList() {
        return new ArrayList(this.mItems);
    }

    public List<Uri> asListOfUri() {
        ArrayList arrayList = new ArrayList();
        for (Item contentUri : this.mItems) {
            arrayList.add(contentUri.getContentUri());
        }
        return arrayList;
    }

    public List<String> asListOfString() {
        ArrayList arrayList = new ArrayList();
        for (Item contentUri : this.mItems) {
            arrayList.add(PathUtils.getPath(this.mContext, contentUri.getContentUri()));
        }
        return arrayList;
    }

    public boolean isEmpty() {
        return this.mItems == null || this.mItems.isEmpty();
    }

    public boolean isSelected(Item item) {
        return this.mItems.contains(item);
    }

    public IncapableCause isAcceptable(Item item) {
        if (maxSelectableReached()) {
            String string;
            int currentMaxSelectable = currentMaxSelectable();
            try {
                string = this.mContext.getString(R.string.fq_matisse_error_over_count, new Object[]{Integer.valueOf(currentMaxSelectable)});
            } catch (NotFoundException unused) {
                string = this.mContext.getString(R.string.fq_matisse_error_over_count, new Object[]{Integer.valueOf(currentMaxSelectable)});
            } catch (NoClassDefFoundError unused2) {
                string = this.mContext.getString(R.string.fq_matisse_error_over_count, new Object[]{Integer.valueOf(currentMaxSelectable)});
            }
            return new IncapableCause(string);
        } else if (typeConflict(item)) {
            return new IncapableCause(this.mContext.getString(R.string.fq_matisse_error_type_conflict));
        } else {
            return PhotoMetadataUtils.isAcceptable(this.mContext, item);
        }
    }

    public boolean maxSelectableReached() {
        return this.mItems.size() == currentMaxSelectable();
    }

    private int currentMaxSelectable() {
        SelectionSpec instance = SelectionSpec.getInstance();
        if (instance.maxSelectable > 0) {
            return instance.maxSelectable;
        }
        if (this.mCollectionType == 1) {
            return instance.maxImageSelectable;
        }
        if (this.mCollectionType == 2) {
            return instance.maxVideoSelectable;
        }
        return instance.maxSelectable;
    }

    public int getCollectionType() {
        return this.mCollectionType;
    }

    private void refineCollectionType() {
        Object obj = null;
        Object obj2 = null;
        for (Item item : this.mItems) {
            if (item.isImage() && obj == null) {
                obj = 1;
            }
            if (item.isVideo() && obj2 == null) {
                obj2 = 1;
            }
        }
        if (obj != null && obj2 != null) {
            this.mCollectionType = 3;
        } else if (obj != null) {
            this.mCollectionType = 1;
        } else if (obj2 != null) {
            this.mCollectionType = 2;
        }
    }

    public boolean typeConflict(Item item) {
        if (SelectionSpec.getInstance().mediaTypeExclusive) {
            if (item.isImage() && (this.mCollectionType == 2 || this.mCollectionType == 3)) {
                return true;
            }
            if (item.isVideo() && (this.mCollectionType == 1 || this.mCollectionType == 3)) {
                return true;
            }
        }
        return false;
    }

    public int count() {
        return this.mItems.size();
    }

    public int checkedNumOf(Item item) {
        int indexOf = new ArrayList(this.mItems).indexOf(item);
        return indexOf == -1 ? CheckView.UNCHECKED : indexOf + 1;
    }
}
