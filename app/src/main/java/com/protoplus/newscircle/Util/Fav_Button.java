package com.protoplus.newscircle.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by Aakash on 11/3/2015.
 */
public class Fav_Button extends Button {

    public Fav_Button(Context context) {
        super(context);
    }

    public Fav_Button(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Fav_Button(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed) {
        // Make sure the parent is a View prior casting it to View
        /*if (pressed && getParent() instanceof View && ((View) getParent()).isPressed()) {
            return;
        }
        super.setPressed(pressed);*/
    }
}
