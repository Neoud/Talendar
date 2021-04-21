package com.example.talendar.data.bean;

import java.util.List;

public class Creation {
    String title;
    String content;
    String date;
    List<String> tag;

    public Creation(String title, String content, String date, List<String> tag) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
