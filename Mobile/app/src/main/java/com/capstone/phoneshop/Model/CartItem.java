package com.capstone.phoneshop.Model;

import com.capstone.phoneshop.Utils.API;
import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("ItemID")
    private int itemID;   //id trong gio hang != id san pham
    @SerializedName("ProductID")
    private int productID;
    @SerializedName("Title")
    private String title; //tilte =  product name + option
    @SerializedName("Price")
    private float price;
    @SerializedName("ImageURL")
    private String imageURL;
    @SerializedName("Amount")
    private int amount;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageURL() {

        return imageURL;
    }

    public String getImageURL1() {

        return API.HOSTNAME +"/images/AnhDT/" + imageURL;
    }



    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public CartItem(int itemID, int productID, String title, float price, String imageURL, int amount) {
        this.itemID = itemID;
        this.productID = productID;
        this.title = title;
        this.price = price;
        this.imageURL = imageURL;
        this.amount = amount;
    }

    public CartItem(int productID, String title, float price, String imageURL, int amount) {
        this.productID = productID;
        this.title = title;
        this.price = price;
        this.imageURL = imageURL;
        this.amount = amount;
    }



    public CartItem(){

    }
}
