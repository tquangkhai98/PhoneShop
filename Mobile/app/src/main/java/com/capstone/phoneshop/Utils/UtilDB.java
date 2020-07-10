package com.capstone.phoneshop.Utils;

public class UtilDB {

    //database version
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "PhoneShopDB";
    public static final String TABLE_USER = "USER";
    public static final String TABLE_CART_ITEM = "CART_ITEM";
    public static final String TABLE_ADDRESS = "ADDRESS";

    //column in USER_TABLE

    public static final String KEY_ID = "ID";
    public static final String KEY_USER_ID = "User_ID";
    public static final String KEY_NAME = "FullName";
    public static final String KEY_BIRTHDAY = "Birthday";
    public static final String KEY_SEX = "Sex";
    public static final String KEY_PHONE_NUMBER = "PhoneNumber";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_ADDRESS = "Address";
    public static final String KEY_AVATAR_URL = "Avatar_URL";

    //column in CART_ITEM
    public static final String KEY_ITEM_ID = "Item_ID";
    public static final String KEY_PRODUCT_ID = "Product_ID";
    public static final String KEY_TITLE = "Title";
    public static final String KEY_PRICE = "Price";
    public static final String KEY_AMOUNT = "Amount";
    public static final String KEY_IMAGE_URL = "ImageURL";

    //column in ADDRESS

    public static final String KEY_CITY = "City";
    public static final String KEY_DISTRICT = "District";
    public static final String KEY_WARDS = "Wards";
    public static final String KEY_DETAIL_ADDRESS = "Detail";


}
