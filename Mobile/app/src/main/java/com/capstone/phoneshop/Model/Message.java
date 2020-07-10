package com.capstone.phoneshop.Model;

import com.google.gson.annotations.SerializedName;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.MessageContentType;

import java.util.Date;

public class Message implements IMessage,
        MessageContentType.Image, /*this is for default image messages implementation*/
        MessageContentType          /*and this one is for custom content type (in this case - voice message)*/ {

    @SerializedName("ID")
    private String id;
    @SerializedName("Text")
    private String text;
    @SerializedName("CreateAt")
    private Date createdAt;
    @SerializedName("Author")
    private Author author;
    @SerializedName("Image")
    private Image image;
    @SerializedName("Voice")
    private Voice voice;


    public Message(String id, Author author, String text) { this(id, author, text, new Date()); }

    public Message(String id, Author author, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }


    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public Author getUser() {
        return this.author;
    }

    @Override
    public String getImageUrl() {
        return image == null ? null : image.url;
    }

    public Voice getVoice() {
        return voice;
    }

    public String getStatus() {
        return "Sent";
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public static class Image {

        private String url;

        public Image(String url) {
            this.url = url;
        }
    }

    public static class Voice {

        private String url;
        private int duration;

        public Voice(String url, int duration) {
            this.url = url;
            this.duration = duration;
        }

        public String getUrl() {
            return url;
        }

        public int getDuration() {
            return duration;
        }
    }
}
