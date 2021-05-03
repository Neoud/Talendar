package com.example.talendar.data.bean;

import android.graphics.Bitmap;

public class Comments {
    private Bitmap profile;
    private String nick;
    private String content;
    private String date;
    private int fansNum;

    public Comments(Bitmap profile, String nick, String content, String date, int fansNum) {
        this.profile = profile;
        this.nick = nick;
        this.content = content;
        this.date = date;
        this.fansNum = fansNum;
    }

    public Comments(String nick, String content, String date, int fansNum) {
        this.nick = nick;
        this.content = content;
        this.date = date;
        this.fansNum = fansNum;
    }

    public Bitmap getProfile() {
        return profile;
    }

    public void setProfile(Bitmap profile) {
        this.profile = profile;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }
}
