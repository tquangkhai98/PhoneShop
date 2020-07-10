package com.capstone.phoneshop.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.capstone.phoneshop.Model.Address;
import com.capstone.phoneshop.Model.CartItem;
import com.capstone.phoneshop.Model.User;
import com.capstone.phoneshop.Utils.UtilDB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, UtilDB.DATABASE_NAME,null, UtilDB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER = "CREATE TABLE "+ UtilDB.TABLE_USER + "("
            //    + UtilDB.KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + UtilDB.KEY_USER_ID + " INTEGER PRIMARY KEY NOT NULL,"
                + UtilDB.KEY_NAME + " NVARCHAR,"
                + UtilDB.KEY_BIRTHDAY + " SMALLDATE,"
                + UtilDB.KEY_SEX + " INTEGER,"
                + UtilDB.KEY_PHONE_NUMBER + " VARCHAR,"
                + UtilDB.KEY_EMAIL + " VARCHAR,"
                + UtilDB.KEY_ADDRESS + " NVARCHAR,"
                + UtilDB.KEY_AVATAR_URL + " TEXT);";
        String CREATE_TABLE_CART_ITEM = "CREATE TABLE "+ UtilDB.TABLE_CART_ITEM + "("
                + UtilDB.KEY_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + UtilDB.KEY_PRODUCT_ID + " INTEGER,"
                + UtilDB.KEY_TITLE + " NVARCHAR,"
                + UtilDB.KEY_PRICE + " FLOAT,"
                + UtilDB.KEY_AMOUNT + " INT,"
                + UtilDB.KEY_IMAGE_URL + " TEXT);";
        String CREATE_TABLE_ADDRESS = "CREATE TABLE "+ UtilDB.TABLE_ADDRESS + "("
                + UtilDB.KEY_USER_ID + " INTEGER PRIMARY KEY NOT NULL,"
                + UtilDB.KEY_NAME+ " NVARCHAR,"
                + UtilDB.KEY_PHONE_NUMBER + " VARCHAR,"
                + UtilDB.KEY_CITY + " NVARCHAR,"
                + UtilDB.KEY_DISTRICT + " NVARCHAR,"
                + UtilDB.KEY_WARDS + " NVARCHAR,"
                + UtilDB.KEY_DETAIL_ADDRESS + " NVARCHAR);";

        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CART_ITEM);
        db.execSQL(CREATE_TABLE_ADDRESS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ UtilDB.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+ UtilDB.TABLE_CART_ITEM);
        db.execSQL("DROP TABLE IF EXISTS "+ UtilDB.TABLE_ADDRESS);
        onCreate(db);
    }

    //Add User
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        if(getCountUser()>0){
            deleteOldUser();
        }

        ContentValues values  = new ContentValues();
        values.put(UtilDB.KEY_USER_ID, user.getUserID());
        //values.put(UtilDB.KEY_USER_ID, 12);
        values.put(UtilDB.KEY_NAME, user.getUserName());
        //Log.i("Birthday",user.getBirthday().toString());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sbirthday= dateFormat.format(user.getBirthday());
        values.put(UtilDB.KEY_BIRTHDAY, sbirthday);
        values.put(UtilDB.KEY_SEX, user.getSex());
        values.put(UtilDB.KEY_PHONE_NUMBER, user.getPhoneNumber());
        values.put(UtilDB.KEY_EMAIL, user.getEmail());
        values.put(UtilDB.KEY_ADDRESS, user.getAddress());
        values.put(UtilDB.KEY_AVATAR_URL, user.getImageURL());

        db.insert(UtilDB.TABLE_USER,null,values);
        Log.i("Insert user","Success");
    }

    public User getUser(){
        if(getCountUser()==0){
            Log.i("User","Empty");
            return null;
        }else {
            SQLiteDatabase db = this.getReadableDatabase();

            String sql = "SELECT * FROM " + UtilDB.TABLE_USER + " LIMIT 1";

            Cursor cursor = db.rawQuery(sql, null);
        /*Cursor cursor = db.query(UtilDB.TABLE_USER, new String[] {
                        UtilDB.KEY_USER_ID,
                        UtilDB.KEY_NAME,
                  //    UtilDB.KEY_BIRTHDAY,
                        UtilDB.KEY_SEX,
                        UtilDB.KEY_PHONE_NUMBER,
                        UtilDB.KEY_EMAIL,
                        UtilDB.KEY_ADDRESS,
                        UtilDB.KEY_AVATAR_URL},
                UtilDB.KEY_ITEM_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);*/
        /*Cursor cursor  = db.query(UtilDB.TABLE_USER, new String[] {UtilDB.KEY_USER_ID, UtilDB.KEY_NAME,UtilDB.KEY_BIRTHDAY,
                UtilDB.KEY_SEX,UtilDB.KEY_PHONE_NUMBER,UtilDB.KEY_EMAIL,UtilDB.KEY_ADDRESS},
                UtilDB.KEY_USER_ID + "=?",
                new String[] {String.valueOf(id)},null,null,null,null);*/
            if (cursor != null)
                cursor.moveToFirst();
            User newUser = new User();
            newUser.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_USER_ID))));
            newUser.setUserName(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_NAME)));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = new Date();
            try {
                birthday = (Date) formatter.parse(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_BIRTHDAY)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.i("Birthday", birthday.toString());
            newUser.setBirthday(birthday);

            newUser.setSex(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_SEX))));
            newUser.setPhoneNumber(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_PHONE_NUMBER)));
            newUser.setEmail(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_EMAIL)));
            newUser.setAddress(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_ADDRESS)));
            newUser.setImageURL(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_AVATAR_URL)));
            return newUser;
        }
    }

    public void deleteOldUser(){
        //String sql =  "DELETE FROM "+ UtilDB.TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UtilDB.TABLE_USER,null,null);
        //db.close();
    }

    public int getCountUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+ UtilDB.TABLE_USER;
        Cursor cursor = db.rawQuery(sql,null);
        return cursor.getCount();
    }



    //add Item into Cart
    public void addCartItem(CartItem cartItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UtilDB.KEY_PRODUCT_ID,cartItem.getProductID());
        values.put(UtilDB.KEY_TITLE,cartItem.getTitle());
        values.put(UtilDB.KEY_PRICE,cartItem.getPrice());
        values.put(UtilDB.KEY_AMOUNT,cartItem.getAmount());
        values.put(UtilDB.KEY_IMAGE_URL,cartItem.getImageURL());
        db.insert(UtilDB.TABLE_CART_ITEM,null,values);
        Log.i("Insert","Success");
    }

    //get Item from cart by item_ID
    public CartItem getCartItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(UtilDB.TABLE_CART_ITEM, new String[] {
                        UtilDB.KEY_ITEM_ID,
                        UtilDB.KEY_PRODUCT_ID,
                        UtilDB.KEY_TITLE,
                        UtilDB.KEY_PRICE,
                        UtilDB.KEY_AMOUNT,
                        UtilDB.KEY_IMAGE_URL},
                UtilDB.KEY_ITEM_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if(cursor!=null)
            cursor.moveToFirst();
            /*Log.i("column",cursor.getColumnName(0)
                    +" "+cursor.getColumnName(1)
                    +" "+cursor.getColumnName(2)
                    +" "+cursor.getColumnName(3)
                    +" "+cursor.getColumnName(4)
                    +" "+cursor.getColumnName(5));*/
            CartItem cartItem = new CartItem();
            cartItem.setItemID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_ITEM_ID))));
            cartItem.setProductID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_PRODUCT_ID))));
            cartItem.setTitle(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_TITLE)));
            cartItem.setPrice(Float.parseFloat(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_PRICE))));
            cartItem.setAmount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_AMOUNT))));
            cartItem.setImageURL(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_IMAGE_URL)));

            Log.i("Get Item",cartItem.getItemID()+"");
            return cartItem;
    }




    //get all item in cart
    public List<CartItem> getAllCartItem(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<CartItem> itemList = new ArrayList<>();

        Cursor cursor = db.query(UtilDB.TABLE_CART_ITEM, new String[] {
                        UtilDB.KEY_ITEM_ID,
                        UtilDB.KEY_PRODUCT_ID,
                        UtilDB.KEY_TITLE,
                        UtilDB.KEY_PRICE,
                        UtilDB.KEY_AMOUNT,
                        UtilDB.KEY_IMAGE_URL},
                null, null, null, null, UtilDB.KEY_ITEM_ID+ " ASC", null);
        if(cursor.moveToFirst()){
            do {
                CartItem cartItem = new CartItem();
                cartItem.setItemID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_ITEM_ID))));
                cartItem.setProductID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_PRODUCT_ID))));
                cartItem.setTitle(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_TITLE)));
                cartItem.setPrice(Float.parseFloat(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_PRICE))));
                cartItem.setAmount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_AMOUNT))));
                cartItem.setImageURL(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_IMAGE_URL)));
                itemList.add(cartItem);
            }while (cursor.moveToNext());
        }
        Log.i("Get List","Success");
        return itemList;
    }


    //update item in cart
    public int updateCartItem(CartItem cartItem){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilDB.KEY_PRODUCT_ID,cartItem.getProductID());
        values.put(UtilDB.KEY_TITLE,cartItem.getTitle());
        values.put(UtilDB.KEY_PRICE,cartItem.getPrice());
        values.put(UtilDB.KEY_AMOUNT,cartItem.getAmount());
        values.put(UtilDB.KEY_IMAGE_URL,cartItem.getImageURL());

        return db.update(UtilDB.TABLE_CART_ITEM,values, UtilDB.KEY_ITEM_ID+"=?",
                new String[] { String.valueOf(cartItem.getItemID())});
    }

    public void deleteCartItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UtilDB.TABLE_CART_ITEM, UtilDB.KEY_ITEM_ID+"=?",
                new String[] {String.valueOf(id)});
        //db.close();
    }

    public void deleteAllCart(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UtilDB.TABLE_CART_ITEM,null,null);
        //db.close();
    }

    public int getCartItemCount(){
        String sql = "SELECT * FROM "+ UtilDB.TABLE_CART_ITEM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return cursor.getCount();
    }

    public boolean checkCartContains(CartItem newItem){
        String title = newItem.getTitle();
        List<CartItem> itemList = this.getAllCartItem();
        for(int i=0; i<itemList.size();i++)
            if(title.equals(itemList.get(i).getTitle())){
                return true;
            }
        return false;
    }

    public void addAddress(Address address){
        if(getCountUser()>0){
            deleteAddress();
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(UtilDB.KEY_USER_ID, address.getUserID());
        values.put(UtilDB.KEY_USER_ID, address.getUserID());
        values.put(UtilDB.KEY_NAME, address.getFullname());
        values.put(UtilDB.KEY_PHONE_NUMBER, address.getPhoneNumber());
        values.put(UtilDB.KEY_CITY, address.getCity());
        values.put(UtilDB.KEY_DISTRICT, address.getDistrict());
        values.put(UtilDB.KEY_WARDS, address.getWards());
        values.put(UtilDB.KEY_DETAIL_ADDRESS, address.getDetailAddress());

        db.insert(UtilDB.TABLE_ADDRESS,null,values);
        Log.i("Insert address","Success");
    }

    public Address getAddress(){
        if(countAddress()==0)
            return null;

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + UtilDB.TABLE_ADDRESS + " LIMIT 1";

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor!=null){
            cursor.moveToFirst();

            Address newAddress = new Address();
            newAddress.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_USER_ID))));
            newAddress.setFullname(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_NAME)));
            newAddress.setPhoneNumber(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_PHONE_NUMBER)));
            newAddress.setCity(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_CITY)));
            newAddress.setDistrict(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_DISTRICT)));
            newAddress.setWards(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_WARDS)));
            newAddress.setDetailAddress(cursor.getString(cursor.getColumnIndex(UtilDB.KEY_DETAIL_ADDRESS)));

            return newAddress;
        }
        return null;

    }

    public void deleteAddress(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UtilDB.TABLE_ADDRESS,null,null);
        //db.close();
    }

    public int countAddress(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+ UtilDB.TABLE_ADDRESS;
        Cursor cursor = db.rawQuery(sql,null);
        return cursor.getCount();
    }





}
