package com.zhd.qqlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 2015032501 on 2015/8/14.
 */
public class CircleView extends ImageView {

    private static final Xfermode MASK_XFERMODE;
    private Bitmap mBitmap;
    private Paint mPaint;
    private int mBorderWidth = 10;
    private int mBorderColor = Color.BLACK;

    static {
        //画图通道
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


}
