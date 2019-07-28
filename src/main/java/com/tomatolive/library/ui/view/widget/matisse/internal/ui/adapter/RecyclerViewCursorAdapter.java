package com.tomatolive.library.ui.view.widget.matisse.internal.ui.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;

public abstract class RecyclerViewCursorAdapter<VH extends ViewHolder> extends Adapter<VH> {
    private Cursor mCursor;
    private int mRowIDColumn;

    public abstract int getItemViewType(int i, Cursor cursor);

    public abstract void onBindViewHolder(VH vh, Cursor cursor);

    RecyclerViewCursorAdapter(Cursor cursor) {
        setHasStableIds(true);
        swapCursor(cursor);
    }

    public void onBindViewHolder(VH vh, int i) {
        if (!isDataValid(this.mCursor)) {
            throw new IllegalStateException("Cannot bind view holder when cursor is in invalid state.");
        } else if (this.mCursor.moveToPosition(i)) {
            onBindViewHolder((ViewHolder) vh, this.mCursor);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not move cursor to position ");
            stringBuilder.append(i);
            stringBuilder.append(" when trying to bind view holder");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public int getItemViewType(int i) {
        if (this.mCursor.moveToPosition(i)) {
            return getItemViewType(i, this.mCursor);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not move cursor to position ");
        stringBuilder.append(i);
        stringBuilder.append(" when trying to get item view type.");
        throw new IllegalStateException(stringBuilder.toString());
    }

    public int getItemCount() {
        return isDataValid(this.mCursor) ? this.mCursor.getCount() : 0;
    }

    public long getItemId(int i) {
        if (!isDataValid(this.mCursor)) {
            throw new IllegalStateException("Cannot lookup item id when cursor is in invalid state.");
        } else if (this.mCursor.moveToPosition(i)) {
            return this.mCursor.getLong(this.mRowIDColumn);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not move cursor to position ");
            stringBuilder.append(i);
            stringBuilder.append(" when trying to get an item id");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public void swapCursor(Cursor cursor) {
        if (cursor != this.mCursor) {
            if (cursor != null) {
                this.mCursor = cursor;
                this.mRowIDColumn = this.mCursor.getColumnIndexOrThrow("_id");
                notifyDataSetChanged();
            } else {
                notifyItemRangeRemoved(0, getItemCount());
                this.mCursor = null;
                this.mRowIDColumn = -1;
            }
        }
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    private boolean isDataValid(Cursor cursor) {
        return (cursor == null || cursor.isClosed()) ? false : true;
    }
}
