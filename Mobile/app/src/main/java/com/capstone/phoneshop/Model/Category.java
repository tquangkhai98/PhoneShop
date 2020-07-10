package com.capstone.phoneshop.Model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("CategoryID")
    private int categoryID;
    @SerializedName("CategoryName")
    private String categoryName;
    @SerializedName("ParentID")
    private int parentID;
    @SerializedName("ImageURL")
    private String imageURL;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Category(int categoryID, String categoryName, int parentID, String imageURL) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.parentID = parentID;
        this.imageURL = imageURL;
    }

    public Category(int categoryID, String categoryName, String imageURL) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.imageURL = imageURL;
    }

    public Category(){

    }
}
