package com.tomatolive.library.ui.view.widget.matisse.listener;

import android.net.Uri;
import android.support.annotation.NonNull;
import java.util.List;

public interface OnSelectedListener {
    void onSelected(@NonNull List<Uri> list, @NonNull List<String> list2);
}
