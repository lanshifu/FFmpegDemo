package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.model.TaskBoxEntity;
import com.tomatolive.library.ui.view.task.TaskConstance;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.r;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.disposables.b;
import io.reactivex.k;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import q.rorbin.badgeview.QBadgeView;

public class TaskBoxView extends RelativeLayout {
    private String TAG;
    private RelativeLayout boxRoot;
    private b disposable;
    private ImageView ivIcon;
    private OnRefreshTaskListener listener;
    private Context mContext;
    private TextView mTaskCountdown;
    private TextView mTaskReceive;
    private QBadgeView qBadgeView;
    private AtomicInteger recieveCount;

    public interface OnRefreshTaskListener {
        void onRefreshTask(TaskBoxEntity taskBoxEntity, Long l);

        void onShowDialog();
    }

    public void setOnRefreshTaskListener(OnRefreshTaskListener onRefreshTaskListener) {
        this.listener = onRefreshTaskListener;
    }

    public TaskBoxView(Context context) {
        this(context, null);
    }

    public TaskBoxView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TaskBoxView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "TaskBoxView";
        this.recieveCount = new AtomicInteger(0);
        this.mContext = context;
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.fq_taskbox_layout, this);
        this.boxRoot = (RelativeLayout) findViewById(R.id.rl_task_root);
        this.ivIcon = (ImageView) findViewById(R.id.iv_taskbox);
        this.boxRoot.setVisibility(4);
        this.mTaskReceive = (TextView) findViewById(R.id.task_receive);
        this.mTaskCountdown = (TextView) findViewById(R.id.task_countdown);
        this.qBadgeView = new QBadgeView(context);
        this.qBadgeView.a(this.ivIcon).b(-1).a(1.0f, true).c(8388661).a(-65536).a(-1, 1.0f, true);
        initlistener();
    }

    private void initlistener() {
        r.a().a(this.boxRoot, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$TaskBoxView$i7xuSbm8ATWaLpVm-ZUtFT7RgVg(this));
    }

    public void changeRedCount(boolean z) {
        if (z) {
            this.recieveCount.getAndIncrement();
        } else {
            this.recieveCount.getAndDecrement();
        }
        refreshRedCount();
    }

    private void refreshRedCount() {
        if (this.recieveCount.get() > 0) {
            this.qBadgeView.setVisibility(0);
            this.qBadgeView.d(this.recieveCount.get());
        } else {
            this.qBadgeView.setVisibility(8);
        }
        Log.i(this.TAG, "refreshRedCount");
    }

    public void checkToCountdown() {
        TaskBoxEntity taskingEntity = getTaskingEntity(TaskConstance.getInstance().getmData());
        if (this.recieveCount.get() != 0) {
            if (taskingEntity != null && (this.disposable == null || this.disposable.isDisposed())) {
                startTaskCountdown(taskingEntity);
            }
            this.mTaskCountdown.setVisibility(8);
            this.mTaskReceive.setVisibility(0);
        } else if (taskingEntity != null) {
            if (this.disposable == null || this.disposable.isDisposed()) {
                startTaskCountdown(taskingEntity);
            }
            this.mTaskCountdown.setVisibility(0);
            this.mTaskReceive.setVisibility(8);
        } else {
            this.mTaskCountdown.setVisibility(8);
            this.mTaskReceive.setVisibility(8);
        }
    }

    public void refreshTaskButton() {
        this.boxRoot.setVisibility(0);
        for (TaskBoxEntity taskBoxEntity : TaskConstance.getInstance().getmData()) {
            if (taskBoxEntity.getStatus() == 1) {
                this.recieveCount.getAndIncrement();
            }
            if (taskBoxEntity.getStatus() == 0) {
                this.mTaskCountdown.setText(d.a(Long.parseLong(taskBoxEntity.getOpenTime()) * 1000));
            }
        }
        refreshRedCount();
        checkToCountdown();
    }

    @Nullable
    private TaskBoxEntity getTaskingEntity(List<TaskBoxEntity> list) {
        for (TaskBoxEntity taskBoxEntity : list) {
            if (taskBoxEntity.getStatus() == 0) {
                return taskBoxEntity;
            }
        }
        return null;
    }

    private void startTaskCountdown(final TaskBoxEntity taskBoxEntity) {
        String openTime = taskBoxEntity.getOpenTime();
        if (!TaskConstance.getInstance().isPushInBackground) {
            TaskConstance.getInstance().setOpentime(openTime);
        }
        k.interval(1, TimeUnit.SECONDS).map(new -$$Lambda$TaskBoxView$GKOt4qWDxK6jycNiPqLgLnlIdd8(openTime)).take(Long.parseLong(openTime) + 1).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new io.reactivex.r<Long>() {
            public void onError(Throwable th) {
            }

            public void onSubscribe(b bVar) {
                TaskBoxView.this.disposable = bVar;
            }

            public void onNext(Long l) {
                TaskBoxView.this.mTaskCountdown.setText(d.a(l.longValue() * 1000));
                taskBoxEntity.setOpenTime(String.valueOf(l));
                TaskBoxView.this.listener.onRefreshTask(taskBoxEntity, l);
            }

            public void onComplete() {
                TaskBoxView.this.disposable.dispose();
            }
        });
    }

    public void release() {
        if (!(this.disposable == null || this.disposable.isDisposed())) {
            this.disposable.dispose();
            this.disposable = null;
        }
        if (this.recieveCount != null) {
            this.recieveCount.set(0);
        }
    }
}
