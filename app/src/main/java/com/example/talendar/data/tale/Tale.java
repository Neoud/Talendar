package com.example.talendar.data.tale;

import java.util.List;

import cn.bmob.v3.BmobObject;

public class Tale extends BmobObject {
    private String title;
    private String content;
    private String author;
    private String authorName;
    private int fansNumber;
    private int commonsNumber;
    private List<String> tags;
    private List<String> commons;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getFansNumber() {
        return fansNumber;
    }

    public int getCommonsNumber() {
        return commonsNumber;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getCommons() {
        return commons;
    }

    public Tale setTitle(String title) {
        this.title = title;
        return this;
    }

    public Tale setContent(String content) {
        this.content = content;
        return this;
    }

    public Tale setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Tale setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public Tale setFansNumber(int fansNumber) {
        this.fansNumber = fansNumber;
        return this;
    }

    public Tale setCommonsNumber(int commonsNumber) {
        this.commonsNumber = commonsNumber;
        return this;
    }

    public Tale setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Tale setCommons(List<String> commons) {
        this.commons = commons;
        return this;
    }
}
