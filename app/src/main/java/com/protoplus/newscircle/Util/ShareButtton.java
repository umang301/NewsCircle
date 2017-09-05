package com.protoplus.newscircle.Util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.widget.Button;

import com.protoplus.newscircle.R;

/**
 * Created by Aakash on 12/14/2015.
 */
public class ShareButtton extends Button {
    public ShareButtton(Context context) {
        super(context);
        Drawable drawable = getResources().getDrawable(R.drawable.share);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                (int)(drawable.getIntrinsicHeight()*0.5));
        ScaleDrawable sd = new ScaleDrawable(drawable, 0, 31, 29);

        setCompoundDrawables(sd.getDrawable(), null, null, null);
    }

}
