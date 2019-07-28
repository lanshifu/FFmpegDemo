package com.tomatolive.library.ui.view.widget.matisse.internal.entity;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.support.annotation.Nullable;
import com.tomatolive.library.ui.view.widget.matisse.MimeType;

public class Item implements Parcelable {
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Nullable
        public Item createFromParcel(Parcel parcel) {
            return new Item(parcel, null);
        }

        public Item[] newArray(int i) {
            return new Item[i];
        }
    };
    public static final String ITEM_DISPLAY_NAME_CAPTURE = "Capture";
    public static final long ITEM_ID_CAPTURE = -1;
    public final long duration;
    public final long id;
    public final String mimeType;
    public final long size;
    public final Uri uri;

    public int describeContents() {
        return 0;
    }

    /* synthetic */ Item(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    private Item(long j, String str, long j2, long j3) {
        Uri uri;
        this.id = j;
        this.mimeType = str;
        if (isImage()) {
            uri = Media.EXTERNAL_CONTENT_URI;
        } else if (isVideo()) {
            uri = Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = Files.getContentUri("external");
        }
        this.uri = ContentUris.withAppendedId(uri, j);
        this.size = j2;
        this.duration = j3;
    }

    private Item(Parcel parcel) {
        this.id = parcel.readLong();
        this.mimeType = parcel.readString();
        this.uri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.size = parcel.readLong();
        this.duration = parcel.readLong();
    }

    public static Item valueOf(Cursor cursor) {
        return new Item(cursor.getLong(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("mime_type")), cursor.getLong(cursor.getColumnIndex("_size")), cursor.getLong(cursor.getColumnIndex("duration")));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.mimeType);
        parcel.writeParcelable(this.uri, 0);
        parcel.writeLong(this.size);
        parcel.writeLong(this.duration);
    }

    public Uri getContentUri() {
        return this.uri;
    }

    public boolean isCapture() {
        return this.id == -1;
    }

    public boolean isImage() {
        boolean z = false;
        if (this.mimeType == null) {
            return false;
        }
        if (this.mimeType.equals(MimeType.JPEG.toString()) || this.mimeType.equals(MimeType.PNG.toString()) || this.mimeType.equals(MimeType.GIF.toString()) || this.mimeType.equals(MimeType.BMP.toString()) || this.mimeType.equals(MimeType.WEBP.toString())) {
            z = true;
        }
        return z;
    }

    public boolean isGif() {
        if (this.mimeType == null) {
            return false;
        }
        return this.mimeType.equals(MimeType.GIF.toString());
    }

    public boolean isVideo() {
        boolean z = false;
        if (this.mimeType == null) {
            return false;
        }
        if (this.mimeType.equals(MimeType.MPEG.toString()) || this.mimeType.equals(MimeType.MP4.toString()) || this.mimeType.equals(MimeType.QUICKTIME.toString()) || this.mimeType.equals(MimeType.THREEGPP.toString()) || this.mimeType.equals(MimeType.THREEGPP2.toString()) || this.mimeType.equals(MimeType.MKV.toString()) || this.mimeType.equals(MimeType.WEBM.toString()) || this.mimeType.equals(MimeType.TS.toString()) || this.mimeType.equals(MimeType.AVI.toString())) {
            z = true;
        }
        return z;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof Item)) {
            return false;
        }
        Item item = (Item) obj;
        if (this.id == item.id && (((this.mimeType != null && this.mimeType.equals(item.mimeType)) || (this.mimeType == null && item.mimeType == null)) && (((this.uri != null && this.uri.equals(item.uri)) || (this.uri == null && item.uri == null)) && this.size == item.size && this.duration == item.duration))) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = Long.valueOf(this.id).hashCode() + 31;
        if (this.mimeType != null) {
            hashCode = (hashCode * 31) + this.mimeType.hashCode();
        }
        return (((((hashCode * 31) + this.uri.hashCode()) * 31) + Long.valueOf(this.size).hashCode()) * 31) + Long.valueOf(this.duration).hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Item{id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", mimeType='");
        stringBuilder.append(this.mimeType);
        stringBuilder.append('\'');
        stringBuilder.append(", uri=");
        stringBuilder.append(this.uri);
        stringBuilder.append(", size=");
        stringBuilder.append(this.size);
        stringBuilder.append(", duration=");
        stringBuilder.append(this.duration);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
