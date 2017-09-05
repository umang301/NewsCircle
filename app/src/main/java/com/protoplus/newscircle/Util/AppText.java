package com.protoplus.newscircle.Util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Aakash on 12/1/2015.
 */
public class AppText extends TextView{
    public AppText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public AppText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppText(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), Helper.LatoBlack);
        setTypeface(tf, 1);

    }
}
