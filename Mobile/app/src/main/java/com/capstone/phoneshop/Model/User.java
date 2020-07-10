package com.capstone.phoneshop.Model;


import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.GregorianCalendar;

public class User {
    @SerializedName("UserID")
    private int userID = 0;
    @SerializedName("UserName")
    private String userName;
    @SerializedName("Birthday")
    private Date birthday;
    @SerializedName("Sex")
    private int sex = 1;   //default là Đực rựa
    @SerializedName("PhoneNumber")
    private String phoneNumber;
    @SerializedName("Email")
    private String email;
    @SerializedName("Address")
    private String address;
    @SerializedName("HoobyID")
    private int hobbyID;
    @SerializedName("NguHanhID")
    private int nguHanhID;

    public String getImageURL() {
        if(imageURL==null)
            return "";
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    private String imageURL;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        if(this.birthday==null)
           return new GregorianCalendar(1960,0,1).getTime();
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHobbyID() {
        return hobbyID;
    }

    public void setHobbyID(int hobbyID) {
        this.hobbyID = hobbyID;
    }

    public int getNguHanhID() {
        return nguHanhID;
    }

    public void setNguHanhID(int nguHanhID) {
        this.nguHanhID = nguHanhID;
    }

    public User(int userID, String userName, Date birthday, int sex, String phoneNumber, String email, String address, int hobbyID, int nguHanhID) {
        this.userID = userID;
        this.userName = userName;
        this.birthday = birthday;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.hobbyID = hobbyID;
        this.nguHanhID = nguHanhID;
    }

    public User(int userID, String userName, String email, String imageURL) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.imageURL = imageURL;
    }

    public User(String userName, String email, String imageURL) {
        this.userName = userName;
        this.email = email;
        this.imageURL = imageURL;
    }

    public User(int userID, String userName, Date birthday, int sex, String phoneNumber, String email, String address, String imageURL) {
        this.userID = userID;
        this.userName = userName;
        this.birthday = birthday;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.imageURL = imageURL;
    }

    public User(){
        this.userID = 0;
        this.userName = "";
        this.birthday = new Date();
        this.sex = 1;
        this.phoneNumber = "";
        this.email = "";
        this.address = "";
        this.imageURL = "";

    }




}
