package com.protoplus.newscircle.Bean;

/**
 * Created by Aakash on 9/23/2015.
 */
public class CategoryBean {
    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getCategoryIcon() {
        return CategoryIcon;
    }

    public void setCategoryIcon(int categoryIcon) {
        CategoryIcon = categoryIcon;
    }
    
    private String Category;
    private int CategoryIcon;

    public Boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(Boolean isFav) {
        this.isFav = isFav;
    }

    private Boolean isFav;

    public String getHeadLine() {
        return HeadLine;
    }

    public void setHeadLine(String headLine) {
        HeadLine = headLine;
    }

    private String HeadLine;
}
