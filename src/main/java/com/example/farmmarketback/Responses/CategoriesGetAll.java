package com.example.farmmarketback.Responses;

import com.example.farmmarketback.objects.Category;

public class CategoriesGetAll {
    private String categoryName;
    private String categoryPicture;
    private String categoryIcon;

    public CategoriesGetAll(Category category) {
        this.categoryName = category.getCategoryName();
        this.categoryPicture = category.getCategoryPicture();
        this.categoryIcon = category.getCategoryIcon();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(String categoryPicture) {
        this.categoryPicture = categoryPicture;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }


}
