package com.capstone.phoneshop.Model;

import com.capstone.phoneshop.Utils.API;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("ProductID")
    private int productID;
    @SerializedName("Name")
    private String name;
    @SerializedName("Price")
    private float price;
    @SerializedName("Inventory")
    private int inventory;    //số lượng tồn kho
    @SerializedName("ImageURL")
    private String imageURL;
    @SerializedName("Description")
    private String description;
    @SerializedName("ColorID")
    private int colorID;
    @SerializedName("CategoryID")
    private int categoryID;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getImageURL() {
        return API.HOSTNAME +"images/AnhDT/" + imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Product(int productID, String name, float price, String imageURL) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }

    public Product(int productID, String name, float price, int inventory, String imageURL, String description, int colorID, int categoryID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.imageURL = imageURL;
        this.description = description;
        this.colorID = colorID;
        this.categoryID = categoryID;
    }

    public Product(){

    }
}
