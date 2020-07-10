package com.capstone.phoneshop.Model;

import com.google.gson.annotations.SerializedName;
import com.stfalcon.chatkit.commons.models.IUser;

public class Author implements IUser {

    @SerializedName("ID")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Avatar")
    private String avatar;
    @SerializedName("Online")
    private boolean online = true;

    public Author(String id, String name, String avatar, boolean online) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public boolean isOnline() {
        return online;
    }
}