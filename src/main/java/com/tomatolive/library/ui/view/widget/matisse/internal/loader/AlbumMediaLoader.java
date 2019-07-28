package com.tomatolive.library.ui.view.widget.matisse.internal.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.provider.MediaStore.Files;
import android.support.v4.content.CursorLoader;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Album;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.MediaStoreCompat;

public class AlbumMediaLoader extends CursorLoader {
    private static final String ORDER_BY = "datetaken DESC";
    private static final String[] PROJECTION = new String[]{"_id", "_display_name", "mime_type", "_size", "duration"};
    private static final Uri QUERY_URI = Files.getContentUri("external");
    private static final String SELECTION_ALBUM = "(media_type=? OR media_type=?) AND  bucket_id=? AND _size>0";
    private static final String SELECTION_ALBUM_FOR_SINGLE_MEDIA_TYPE = "media_type=? AND  bucket_id=? AND _size>0";
    private static final String SELECTION_ALL = "(media_type=? OR media_type=?) AND _size>0";
    private static final String[] SELECTION_ALL_ARGS = new String[]{String.valueOf(1), String.valueOf(3)};
    private static final String SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE = "media_type=? AND _size>0";
    private final boolean mEnableCapture;

    public void onContentChanged() {
    }

    private static String[] getSelectionArgsForSingleMediaType(int i) {
        return new String[]{String.valueOf(i)};
    }

    private static String[] getSelectionAlbumArgs(String str) {
        return new String[]{String.valueOf(1), String.valueOf(3), str};
    }

    private static String[] getSelectionAlbumArgsForSingleMediaType(int i, String str) {
        return new String[]{String.valueOf(i), str};
    }

    private AlbumMediaLoader(Context context, String str, String[] strArr, boolean z) {
        super(context, QUERY_URI, PROJECTION, str, strArr, ORDER_BY);
        this.mEnableCapture = z;
    }

    public static CursorLoader newInstance(Context context, Album album, boolean z) {
        String[] strArr;
        String str;
        if (!album.isAll()) {
            String str2;
            String[] selectionAlbumArgsForSingleMediaType;
            if (SelectionSpec.getInstance().onlyShowImages()) {
                str2 = SELECTION_ALBUM_FOR_SINGLE_MEDIA_TYPE;
                selectionAlbumArgsForSingleMediaType = getSelectionAlbumArgsForSingleMediaType(1, album.getId());
            } else if (SelectionSpec.getInstance().onlyShowVideos()) {
                str2 = SELECTION_ALBUM_FOR_SINGLE_MEDIA_TYPE;
                selectionAlbumArgsForSingleMediaType = getSelectionAlbumArgsForSingleMediaType(3, album.getId());
            } else {
                str2 = SELECTION_ALBUM;
                selectionAlbumArgsForSingleMediaType = getSelectionAlbumArgs(album.getId());
            }
            strArr = selectionAlbumArgsForSingleMediaType;
            str = str2;
            z = false;
        } else if (SelectionSpec.getInstance().onlyShowImages()) {
            str = SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE;
            strArr = getSelectionArgsForSingleMediaType(1);
        } else if (SelectionSpec.getInstance().onlyShowVideos()) {
            str = SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE;
            strArr = getSelectionArgsForSingleMediaType(3);
        } else {
            str = SELECTION_ALL;
            strArr = SELECTION_ALL_ARGS;
        }
        return new AlbumMediaLoader(context, str, strArr, z);
    }

    public Cursor loadInBackground() {
        Cursor loadInBackground = super.loadInBackground();
        if (!this.mEnableCapture || !MediaStoreCompat.hasCameraFeature(getContext())) {
            return loadInBackground;
        }
        new MatrixCursor(PROJECTION).addRow(new Object[]{Long.valueOf(-1), Item.ITEM_DISPLAY_NAME_CAPTURE, "", Integer.valueOf(0), Integer.valueOf(0)});
        return new MergeCursor(new Cursor[]{r1, loadInBackground});
    }
}
