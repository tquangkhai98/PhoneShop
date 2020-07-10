package com.capstone.phoneshop.Model;

import com.google.gson.annotations.SerializedName;

public class Color {
    @SerializedName("ColorID")
    private int colorID;
    @SerializedName("ColorName")
    private String colorName;


    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Color(int colorID, String colorName) {
        this.colorID = colorID;
        this.colorName = colorName;
    }
}
