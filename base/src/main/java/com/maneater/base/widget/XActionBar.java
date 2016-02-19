package com.maneater.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.maneater.base.R;
import com.maneater.base.util.StringUtils;

public class XActionBar extends FrameLayout implements View.OnClickListener {
    private TextView titleView = null;
    private TextView txActionName = null;

    private ActionClickListener mActionBarClickListener = null;

    public void setOnActionBarClickListener(ActionClickListener actionBarClickListener) {
        this.mActionBarClickListener = actionBarClickListener;
    }

    public XActionBar(Context context) {
        super(context);
        init();
    }

    public XActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RTActionBar, 0, 0);
        setTitleText(a.getString(R.styleable.RTActionBar_titleText));
        setActionText(a.getString(R.styleable.RTActionBar_actionText));

        a.recycle();

    }

    private void init() {
        setClickable(true);
        LayoutInflater.from(getContext()).inflate(R.layout.view_action_bar, this, true);
        titleView = (TextView) findViewById(R.id.titleView);
        txActionName = (TextView) findViewById(R.id.txActionName);
        txActionName.setOnClickListener(this);
    }

    public void setTitleText(String titleText) {
        titleView.setText(titleText);
        if (StringUtils.isBlank(titleText)) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleText(int titleTextResId) {
        setTitleText(getResources().getString(titleTextResId));
    }

    public void setActionText(String actionText) {
        txActionName.setText(actionText);
        if (StringUtils.isBlank(actionText)) {
            txActionName.setVisibility(View.GONE);
        } else {
            txActionName.setVisibility(View.VISIBLE);
        }
    }

    public void setActionVisibility(int visibility) {
        txActionName.setVisibility(visibility);
    }

    public void setBackActionVisibility(int visibility) {
        findViewById(R.id.backView).setVisibility(visibility);
    }

    public void setTitleVisibility(int visibility) {
        txActionName.setVisibility(visibility);
    }

    public void setActionText(int titleTextResId) {
        setActionText(getResources().getString(titleTextResId));
    }

    @Override
    public void onClick(final View v) {
        if (v.equals(txActionName)) {
            if (mActionBarClickListener != null) {
                mActionBarClickListener.onActionClick(v);
            }
        }
    }

    public interface ActionClickListener {
        public void onActionClick(View view);
    }
}
