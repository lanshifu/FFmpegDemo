package com.tomatolive.library.ui.view.headview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.a;
import com.tomatolive.library.R;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.p;
import defpackage.dh;
import defpackage.dp;
import defpackage.dv;
import java.util.Calendar;
import java.util.Date;

public class IncomeListHeadView extends LinearLayout {
    private Date currentDate;
    private boolean freeType = true;
    private Context mContext;
    private OnDateSelectedListener onDateSelectedListener;
    private OnPropsDateSelectedListener onPropsDateSelectedListener;
    private dv pvTime;
    private RelativeLayout rlPropsBg;
    private TextView tvCalendar;
    private TextView tvCalendarProps;
    private TextView tvFree;
    private TextView tvGold;
    private TextView tvSettle;

    public interface OnDateSelectedListener {
        void onDateSelected(Date date);
    }

    public interface OnPropsDateSelectedListener {
        void onDateSelected(Date date, boolean z);
    }

    public IncomeListHeadView(Context context) {
        super(context);
        initView(context);
    }

    public IncomeListHeadView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        inflate(context, R.layout.fq_layout_head_view_income, this);
        this.tvGold = (TextView) findViewById(R.id.tv_gold);
        this.tvCalendar = (TextView) findViewById(R.id.tv_calendar);
        this.tvCalendarProps = (TextView) findViewById(R.id.tv_calendar_props);
        this.tvFree = (TextView) findViewById(R.id.tv_free);
        this.tvSettle = (TextView) findViewById(R.id.tv_settle);
        this.rlPropsBg = (RelativeLayout) findViewById(R.id.rl_props_bg);
        this.tvCalendar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (IncomeListHeadView.this.pvTime != null) {
                    IncomeListHeadView.this.pvTime.d();
                }
            }
        });
        this.tvCalendarProps.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (IncomeListHeadView.this.pvTime != null) {
                    IncomeListHeadView.this.pvTime.d();
                }
            }
        });
        this.tvFree.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                IncomeListHeadView.this.initLabelView(true);
                if (IncomeListHeadView.this.onPropsDateSelectedListener != null) {
                    if (IncomeListHeadView.this.currentDate == null) {
                        IncomeListHeadView.this.currentDate = Calendar.getInstance().getTime();
                    }
                    IncomeListHeadView.this.onPropsDateSelectedListener.onDateSelected(IncomeListHeadView.this.currentDate, IncomeListHeadView.this.freeType);
                }
            }
        });
        this.tvSettle.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                IncomeListHeadView.this.initLabelView(false);
                if (IncomeListHeadView.this.onPropsDateSelectedListener != null) {
                    if (IncomeListHeadView.this.currentDate == null) {
                        IncomeListHeadView.this.currentDate = Calendar.getInstance().getTime();
                    }
                    IncomeListHeadView.this.onPropsDateSelectedListener.onDateSelected(IncomeListHeadView.this.currentDate, IncomeListHeadView.this.freeType);
                }
            }
        });
    }

    public void initData(boolean z) {
        int i = 4;
        this.rlPropsBg.setVisibility(z ? 0 : 4);
        TextView textView = this.tvCalendar;
        if (!z) {
            i = 0;
        }
        textView.setVisibility(i);
        initLabelView(true);
        initTimePickerView(z);
    }

    private void initLabelView(boolean z) {
        this.freeType = z;
        this.tvFree.setSelected(z);
        this.tvSettle.setSelected(z ^ 1);
    }

    private void initTimePickerView(final boolean z) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.set(2017, 0, 1);
        Calendar instance3 = Calendar.getInstance();
        instance3.set(2030, 11, 1);
        if (z) {
            this.tvCalendarProps.setText(d.b("yyyy年MM月dd日"));
        } else {
            this.tvCalendar.setText(d.b("yyyy年MM月dd日"));
        }
        this.pvTime = new dh(this.mContext, new dp() {
            public void onTimeSelect(Date date, View view) {
                IncomeListHeadView.this.currentDate = date;
                if (z) {
                    IncomeListHeadView.this.tvCalendarProps.setText(d.a(date, "yyyy年MM月dd日"));
                    if (IncomeListHeadView.this.onPropsDateSelectedListener != null) {
                        IncomeListHeadView.this.onPropsDateSelectedListener.onDateSelected(date, IncomeListHeadView.this.freeType);
                        return;
                    }
                    return;
                }
                IncomeListHeadView.this.tvCalendar.setText(d.a(date, "yyyy年MM月dd日"));
                if (IncomeListHeadView.this.onDateSelectedListener != null) {
                    IncomeListHeadView.this.onDateSelectedListener.onDateSelected(date);
                }
            }
        }).a(new boolean[]{true, true, true, false, false, false}).b(this.mContext.getString(R.string.fq_btn_cancel)).a(this.mContext.getString(R.string.fq_done)).b(true).a(false).e(ContextCompat.getColor(this.mContext, R.color.fq_text_black)).a(ContextCompat.getColor(this.mContext, R.color.fq_colorRed)).b(ContextCompat.getColor(this.mContext, R.color.fq_text_black)).d(ContextCompat.getColor(this.mContext, R.color.fq_colorWhite)).c(ContextCompat.getColor(this.mContext, R.color.fq_colorWhite)).f(20).a(instance).a(instance2, instance3).a("年", "月", "日", "时", "分", "秒").a();
        if (a.b()) {
            LayoutParams layoutParams = (LayoutParams) this.pvTime.j().getLayoutParams();
            layoutParams.bottomMargin = a.a();
            this.pvTime.j().setLayoutParams(layoutParams);
        }
    }

    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.onDateSelectedListener = onDateSelectedListener;
    }

    public void setOnPropsDateSelectedListener(OnPropsDateSelectedListener onPropsDateSelectedListener) {
        this.onPropsDateSelectedListener = onPropsDateSelectedListener;
    }

    public void setCurrentGold(String str) {
        CharSequence str2;
        if (p.a(str2) < 0) {
            str2 = "0";
        }
        this.tvGold.setText(str2);
    }

    public void setSelectDate(String str) {
        String[] split = str.split("-");
        this.tvCalendar.setText(String.format("%s年%s月%s日", new Object[]{split[0], split[1], split[2]}));
        this.tvCalendarProps.setText(String.format("%s年%s月%s日", new Object[]{split[0], split[1], split[2]}));
        this.currentDate = d.e(str).getTime();
        this.pvTime.a(d.e(str));
    }
}
