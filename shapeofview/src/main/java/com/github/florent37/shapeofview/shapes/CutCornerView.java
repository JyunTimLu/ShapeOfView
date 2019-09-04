package com.github.florent37.shapeofview.shapes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.github.florent37.shapeofview.R;
import com.github.florent37.shapeofview.ShapeOfView;
import com.github.florent37.shapeofview.manager.ClipPathManager;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CutCornerView extends ShapeOfView {

    private final RectF rectF = new RectF();

    private float topLeftCutSizePx = 0f;
    private float topRightCutSizePx = 0f;
    private float bottomRightCutSizePx = 0f;
    private float bottomLeftCutSizePx = 0f;

    private float borderWidthPx = 10f;

    @ColorInt
    private int borderColor = Color.GRAY;

    private final Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CutCornerView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public CutCornerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CutCornerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CutCornerView);
            topLeftCutSizePx = attributes.getDimensionPixelSize(R.styleable.CutCornerView_shape_cutCorner_topLeftSize, (int) topLeftCutSizePx);
            topRightCutSizePx = attributes.getDimensionPixelSize(R.styleable.CutCornerView_shape_cutCorner_topRightSize, (int) topRightCutSizePx);
            bottomLeftCutSizePx = attributes.getDimensionPixelSize(R.styleable.CutCornerView_shape_cutCorner_bottomLeftSize, (int) bottomLeftCutSizePx);
            bottomRightCutSizePx = attributes.getDimensionPixelSize(R.styleable.CutCornerView_shape_cutCorner_bottomRightSize, (int) bottomRightCutSizePx);
            attributes.recycle();
        }
        super.setClipPathCreator(new ClipPathManager.ClipPathCreator() {
            @Override
            public Path createClipPath(int width, int height) {
                rectF.set(0, 0, width, height);
                return generatePath(rectF, topLeftCutSizePx, topRightCutSizePx, bottomRightCutSizePx, bottomLeftCutSizePx);
            }

            @Override
            public boolean requiresBitmap() {
                return false;
            }
        });
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if(borderWidthPx > 0){
            borderPaint.setStrokeWidth(borderWidthPx);
            borderPaint.setColor(borderColor);

            canvas.drawLine(0, rectF.top - topLeftCutSizePx, rectF.left + topLeftCutSizePx, 0, borderPaint);


//            canvas.drawCircle(getWidth()/2f, getHeight()/2f, Math.min((getWidth() - borderWidthPx) /2f, (getHeight() - borderWidthPx) /2f), borderPaint);
        }
    }

    private Path generatePath(RectF rect, float topLeftDiameter, float topRightDiameter, float bottomRightDiameter, float bottomLeftDiameter) {
        final Path path = new Path();

        topLeftDiameter = topLeftDiameter < 0 ? 0 : topLeftDiameter;
        topRightDiameter = topRightDiameter < 0 ? 0 : topRightDiameter;
        bottomLeftDiameter = bottomLeftDiameter < 0 ? 0 : bottomLeftDiameter;
        bottomRightDiameter = bottomRightDiameter < 0 ? 0 : bottomRightDiameter;

        path.moveTo(rect.left + topLeftDiameter, rect.top);
        path.lineTo(rect.right - topRightDiameter, rect.top);
        path.lineTo(rect.right, rect.top + topRightDiameter);
        path.lineTo(rect.right, rect.bottom - bottomRightDiameter);
        path.lineTo(rect.right - bottomRightDiameter, rect.bottom);
        path.lineTo(rect.left + bottomLeftDiameter, rect.bottom);
        path.lineTo(rect.left, rect.bottom - bottomLeftDiameter);
        path.lineTo(rect.left, rect.top + topLeftDiameter);
        path.lineTo(rect.left + topLeftDiameter, rect.top);
        path.close();

        return path;
    }

    public float getTopLeftCutSize() {
        return topLeftCutSizePx;
    }

    public void setTopLeftCutSize(float topLeftCutSize) {
        this.topLeftCutSizePx = topLeftCutSize;
        requiresShapeUpdate();
    }

    public float getTopLeftCutSizeDp() {
        return pxToDp(getTopLeftCutSize());
    }

    public void setTopLeftCutSizeDp(float topLeftCutSize) {
        setTopLeftCutSize(dpToPx(topLeftCutSize));
    }

    public float getTopRightCutSize() {
        return topRightCutSizePx;
    }

    public void setTopRightCutSize(float topRightCutSize) {
        this.topRightCutSizePx = topRightCutSize;
        requiresShapeUpdate();
    }

    public float getTopRightCutSizeDp() {
        return pxToDp(getTopRightCutSize());
    }

    public void setTopRightCutSizeDp(float topRightCutSize) {
        setTopRightCutSize(dpToPx(topRightCutSize));
    }

    public float getBottomRightCutSize() {
        return bottomRightCutSizePx;
    }

    public void setBottomRightCutSize(float bottomRightCutSize) {
        this.bottomRightCutSizePx = bottomRightCutSize;
        requiresShapeUpdate();
    }

    public float getBottomRightCutSizeDp() {
        return pxToDp(getBottomRightCutSize());
    }

    public void setBottomRightCutSizeDp(float bottomRightCutSize) {
        setBottomRightCutSize(dpToPx(bottomRightCutSize));
    }

    public float getBottomLeftCutSize() {
        return bottomLeftCutSizePx;
    }

    public void setBottomLeftCutSize(float bottomLeftCutSize) {
        this.bottomLeftCutSizePx = bottomLeftCutSize;
        requiresShapeUpdate();
    }

    public float getBottomLeftCutSizeDp() {
        return pxToDp(getBottomLeftCutSize());
    }

    public void setBottomLeftCutSizeDp(float bottomLeftCutSize) {
        setBottomLeftCutSize(dpToPx(bottomLeftCutSize));
    }
}
