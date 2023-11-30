package com.example.yatrackapp;

public class Notification {
    String notifText;
    Notification(String notifText) {
        this.notifText = notifText;
    }
    Notification() {
        this.notifText = "Empty notification";
    }

    public String getNotifText() {
        return notifText;
    }

    public void setNotifText(String notifText) {
        this.notifText = notifText;
    }
}
