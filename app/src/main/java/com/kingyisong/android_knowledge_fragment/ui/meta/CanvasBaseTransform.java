package com.kingyisong.android_knowledge_fragment.ui.meta;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kingyisong on 16/7/20.
 * 绘制的基本变换操作
 * 图像是一个元素集，元素具有坐标和颜色属性。经过一个坐标矩阵变换，当然了，目前这个变换主要是矩阵变换，得到一个新的元素集，
 * 新的元素集，相对老的元素集，主要是坐标有变化。另，颜色变化有颜色矩阵，这个主要是paint来变换颜色了
 * 系统添加了save和restore操作，这个操作的目的也只是为了保存和恢复当前的坐标矩阵状态，并不是说恢复颜色属性
 */
public class CanvasBaseTransform extends View {

    public CanvasBaseTransform(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasBaseTransform(Context context) {
        super(context);
    }

    public CanvasBaseTransform(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        transformRotate(canvas);
    }


    /**
     * 旋转变换
     */
    private void transformRotate(Canvas canvas) {
        //圆点
        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;
        //半径
        int radius = 100;
        //canvas.clipRect(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.save();
        canvas.translate(100, 100);
        canvas.drawColor(Color.BLUE);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();

        //把圆分成24份
        for (int i = 0; i < 24; i++) {
            canvas.drawLine(centerX, centerY, centerX + radius , centerY, paint);
            canvas.rotate(15, centerX, centerY);
        }

    }


}


