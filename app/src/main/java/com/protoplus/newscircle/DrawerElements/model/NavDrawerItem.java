package com.protoplus.newscircle.DrawerElements.model;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Ravi on 29/07/15.
 */
public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private int imageId;
    private int nav_item_arrow;
    private RelativeLayout relativeLayout;

    public LinearLayout getUnderline() {
        return underline;
    }

    public void setUnderline(LinearLayout underline) {
        this.underline = underline;
    }

    private LinearLayout underline;
    public int getNav_item_arrow() {
        return nav_item_arrow;
    }

    public void setNav_item_arrow(int nav_item_arrow) {
        this.nav_item_arrow = nav_item_arrow;
    }

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }



    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title, int imageId)  {
        this.showNotify = showNotify;
        this.title = title;
        this.imageId=imageId;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
