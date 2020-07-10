package com.capstone.phoneshop.Model;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("UserID")
    private int userID;
    @SerializedName("Fullname")
    private String fullname;
    @SerializedName("PhoneNumber")
    private String phoneNumber;
    @SerializedName("City")
    private String city;
    @SerializedName("District")
    private String district;
    @SerializedName("Wards")
    private String wards;
    @SerializedName("DetailAddress")
    private String detailAddress;

    public Address(){

    }

    public Address(int userID, String fullname, String phoneNumber, String city, String district, String wards, String detail_address) {
        this.userID = userID;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.district = district;
        this.wards = wards;
        this.detailAddress = detail_address;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detail_address) {
        this.detailAddress = detail_address;
    }
}
