package com.kingyisong.android_knowledge_fragment.ui.meta;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.kingyisong.android_knowledge_fragment.R;

/**
 * Created by kingyisong on 16/7/21.
 * 画笔基本变换
 */
public class PaintBaseTransform extends View {


    public PaintBaseTransform(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintBaseTransform(Context context) {
        super(context);
    }

    public PaintBaseTransform(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWithShader(canvas);

    }

    /**
     * http://blog.csdn.net/iispring/article/details/50472485
     * @param canvas
     */

    private void drawWithXfermode(Canvas canvas) {
        //设置背景色
        Paint paint = new Paint();
        canvas.drawARGB(255, 139, 197, 186);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        //如果不设置图层，效果会相去万里，具体原因暂时未知
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        int r = canvasWidth / 3;
        //正常绘制黄色的圆形
        paint.setColor(0xFFFFCC44);
        canvas.drawCircle(r, r, r, paint);
        //使用CLEAR作为PorterDuffXfermode绘制蓝色的矩形
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
        //最后将画笔去除Xfermode
        paint.setXfermode(null);
        //canvas.restoreToCount(layerId);
    }

    private void drawWithColorMatrix(Canvas canvas) {

        Bitmap map = BitmapFactory.decodeResource(getResources(), R.drawable.gavatar);

        Paint paint = new Paint();

        ColorMatrix matrix = new ColorMatrix();
        float[] arry = new float[]{1, 0, 0, 0, 100,
                                   0, 1, 0, 0, 100,
                                   0, 0, 1, 0, 100,
                                   0, 0, 0, 1, 0};
        matrix.set(arry);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

        canvas.drawBitmap(map, 0, 0, paint);

    }

    /**
     * http://blog.csdn.net/iispring/article/details/50500106
     * shader有好几种形式，有位图形式的、有渐进变化的（按照直线、旋转、圆心向外）
     * @param canvas
     */
    private void drawWithShader(Canvas canvas) {
        Bitmap bm = Bitmap.createBitmap(new int[] { 0xFFFFFFFF, 0xFFCCCCCC,
                        0xFFCCCCCC, 0xFFFFFFFF }, 2, 2,
                Bitmap.Config.RGB_565);
        BitmapShader shader = new BitmapShader(bm,
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT);
        Matrix m = new Matrix();
        m.setScale(6, 6);
        shader.setLocalMatrix(m);

        //RadialGradient gradient = new RadialGradient(300, 600, 100, Color.RED, Color.BLUE, Shader.TileMode.REPEAT);

        SweepGradient gradient1 = new SweepGradient(300, 600, Color.RED, Color.BLUE);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        paint.setShader(gradient1);

        canvas.drawPaint(paint);
    }


}
