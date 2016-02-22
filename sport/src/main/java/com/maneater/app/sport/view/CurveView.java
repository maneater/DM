package com.maneater.app.sport.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CurveView extends View {

    private int mCoverColor = 0x2e00FFB5;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();
    private float mAnimateRadio = 0.0f;

    private int mLabelTextSize = 50;
    // x最小间隔
    private int minXInterval = 300;

    private float mPaddingLeft = 100;
    private float mPaddingRight = 100;
    private float mPaddingTop = 50;
    private float mPaddingBottom = 50;

    private int mDividerSize = 8;
    private float mDividerLineWidth = 2;
    private int mDividerColor = Color.WHITE;

    private float mLineWidth = 8;
    private int mLineColor = 0xFF00FFB5;

    private int mCicleRadius = 18;
    private int mCicleRadius2 = 12;

    private GestureDetector mGestureDetector = null;

    public CurveView(Context context) {
        super(context);
        init();
    }

    public CurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setTextSize(mLabelTextSize);
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                int mRight = getRight();
                int targetScrollX = (int) (getScrollX() + distanceX);
                if (targetScrollX <= 0) {
                    scrollTo(0, 0);
                } else if (targetScrollX + mWindowWidth >= getWidth()) {
                    scrollTo(getWidth() - mWindowWidth, 0);
                } else {
                    scrollBy((int) distanceX, 0);
                }
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });
    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return mGestureDetector.onTouchEvent(event);
//    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDivider(canvas);
        drawLine(canvas);
    }

    private void drawDivider(Canvas canvas) {
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(mDividerLineWidth);
        mPaint.setColor(mDividerColor);
        float height = getMaxDrawHeight() / mDividerSize;
        for (int i = 0; i <= mDividerSize + 1; i++) {
            canvas.drawLine(mPaddingLeft, height * i + mPaddingTop, getWidth() - mPaddingRight, height * i + mPaddingTop, mPaint);
        }
    }


    //除去padding的高度
    private float getMaxDrawHeight() {
        return getHeight() - mPaddingBottom - mPaddingTop;
    }

    //除去padding的宽度
    private float getMaxDrawWidth() {
        return getWidth() - mPaddingRight - mPaddingLeft;
    }

    private void drawLine(Canvas canvas) {
        if (mMaxItem != null && dataItem != null && dataItem.size() > 0) {

            float maxHeight = getHeight() - mPaddingTop - mPaddingBottom;
            int pointSize = dataItem.size();

            float radioY = (float) (maxHeight / mMaxItem.value);
            float xStep = (getMaxDrawWidth() / pointSize);


            float[] drawPoints = new float[pointSize * 2];
            mPath.reset();
            float mXPoint = mPaddingLeft;

            if (xStep > minXInterval) {
                mXPoint += (xStep / 2);
            }

            for (int i = 0; i < pointSize; i++) {
                drawPoints[i * 2] = mXPoint;
                drawPoints[i * 2 + 1] = (float) (getHeight() - mPaddingBottom - radioY * dataItem.get(i).value * mAnimateRadio);

                if (i == 0) {
                    mPath.moveTo(mXPoint, getHeight() - mPaddingBottom);
                }

                mPath.lineTo(mXPoint, drawPoints[i * 2 + 1]);

                if (i == dataItem.size() - 1) {
                    mPath.lineTo(mXPoint, getHeight() - mPaddingBottom);
                }
                mXPoint += xStep;
            }

            mPaint.setStyle(Style.FILL_AND_STROKE);
            mPaint.setColor(mCoverColor);
            canvas.drawPath(mPath, mPaint);

//            mPath.close();
            mPaint.setStyle(Style.STROKE);
            mPaint.setStrokeWidth(mLineWidth);
            mPaint.setColor(mLineColor);
            canvas.drawPath(mPath, mPaint);

            // draw point and label

            mPaint.setStyle(Style.FILL);
            for (int i = 0; i < pointSize; i++) {
                float x = drawPoints[i * 2];
                float y = drawPoints[i * 2 + 1];
                CurveItem curveItem = dataItem.get(i);
                mPaint.setColor(mLineColor);
                canvas.drawCircle(x, y, mCicleRadius, mPaint);
                mPaint.setColor(Color.WHITE);
                canvas.drawCircle(x, y, mCicleRadius2, mPaint);

                mPaint.setColor(mDividerColor);
                float xOffset = -mPaint.measureText(String.valueOf(curveItem.value)) / 2;
                float yOffset = (mPaint.ascent() - mPaint.descent()) / 2;
                canvas.drawText(String.valueOf(curveItem.value), x + xOffset, y + yOffset, mPaint);
                xOffset = -mPaint.measureText(String.valueOf(curveItem.label)) / 2;
                canvas.drawText(String.valueOf(curveItem.label), x + xOffset, getHeight(), mPaint);
            }
        }
    }

    private CurveItem checkMaxItem(List<CurveItem> dataItem) {
        CurveItem item = null;
        for (CurveItem curveItem : dataItem) {
            if (item == null || curveItem.value > item.value) {
                item = curveItem;
            }
        }
        return item;
    }

    private List<CurveItem> dataItem = new ArrayList<CurveItem>();
    private CurveItem mMaxItem = null;

    public void render(List<CurveItem> dataItem, boolean animate) {
        this.dataItem = dataItem;
        scrollTo(0, 0);
        requestLayout();
        this.mMaxItem = checkMaxItem(dataItem);
        if (animate && this.dataItem != null && this.dataItem.size() > 0) {
            mAnimateRadio = 0.0f;
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(1);
            valueAnimator.setDuration(1000);
            valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mAnimateRadio = (Float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.start();
        } else {
            invalidate();
        }
    }

    private int mWindowWidth = 0;



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = getMeasuredWidth();
        if (this.dataItem != null) {
            //最小宽度
            int needWidth = minXInterval * this.dataItem.size();
            if (mWidth < needWidth) {
                setMeasuredDimension(needWidth, getMeasuredHeight());
            }
        }
    }

    public static class CurveItem {

        public CurveItem(String label, double value) {
            this.label = label;
            this.value = value;
        }

        String label;
        double value;
    }

}
