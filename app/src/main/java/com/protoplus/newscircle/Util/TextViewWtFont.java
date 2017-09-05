package com.protoplus.newscircle.Util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.protoplus.newscircle.ContextData;
import com.protoplus.newscircle.R;

/**
 * Created by Aakash on 11/26/2015.
 */
public class TextViewWtFont extends TextView {
    private int defaultDimension = 0;
    private int TYPE_BOLD = 1;
    private int TYPE_ITALIC = 2;
    private int FONT_LATO = 1;
    private int FONT_OPEN_SANS = 2;
    private int fontType;
    private int fontName;
    public TextViewWtFont(Context context) {
        super(context);
    }
    public TextViewWtFont(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.name);
        //init(attrs, 0);
    }
    public TextViewWtFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.font, defStyle, 0);
        fontName = a.getInt(R.styleable.font_name, defaultDimension);
        fontType = a.getInt(R.styleable.font_type, defaultDimension);
        a.recycle();
        ContextData application = (ContextData ) getContext().getApplicationContext();
        //if (fontName == FONT_LATO) {
            setFontType(application .getLatoBlack());
            //setFontType(application .getLatoBlack());
        /*} else if (fontName == FONT_OPEN_SANS) {
            setFontType(application .getOpenSans());
        }*/
    }
    private void setFontType(Typeface font) {
        if (fontType == TYPE_BOLD) {
            setTypeface(font, Typeface.BOLD);
        } else if (fontType == TYPE_ITALIC) {
            setTypeface(font, Typeface.ITALIC);
        } else {
            setTypeface(font);
        }
    }
}
