package com.example.gymmanagement;

import com.google.firebase.firestore.FieldValue;

public class ModelMessage {
    private String text;
    private String sender;
    private boolean isImage;
    private FieldValue timestamp;

    public ModelMessage(String text, String sender, boolean isImage, FieldValue timestamp) {
        this.text = text;
        this.sender = sender;
        this.isImage = isImage;
        this.timestamp = timestamp;
    }

    public ModelMessage() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public FieldValue getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(FieldValue timestamp) {
        this.timestamp = timestamp;
    }
}
