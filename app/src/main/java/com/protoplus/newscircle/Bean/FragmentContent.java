package com.protoplus.newscircle.Bean;

import java.io.Serializable;

/**
 * Created by Aakash on 9/25/2015.
 */
public class FragmentContent implements Serializable{
    private int numbofitems;
    private String[] titles;
    private String[] URLS;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    private int cid;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    private String Category;
    public String[] getURLS() {
        return URLS;
    }

    public void setURLS(String[] URLS) {
        this.URLS = URLS;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public int getNumbofitems() {
        return numbofitems;
    }

    public void setNumbofitems(int numbofitems) {
        this.numbofitems = numbofitems;
    }



}
