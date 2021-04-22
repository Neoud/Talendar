package com.example.talendar.data.talesolitaire;

import java.util.List;

import cn.bmob.v3.BmobObject;

public class TaleSolitaire extends BmobObject {
    private String title;
    private String content;
    private String author;
    private String authorName;
    private int fansNumber;
    private int commonsNumber;
    private List<String> tags;
    private List<String> commons;
    private String firstTale;
    private String lastTale;
    private List<String> nextTale;

    public String getTitle() {
        return title;
    }

    public TaleSolitaire setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public TaleSolitaire setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public TaleSolitaire setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public TaleSolitaire setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public int getFansNumber() {
        return fansNumber;
    }

    public TaleSolitaire setFansNumber(int fansNumber) {
        this.fansNumber = fansNumber;
        return this;
    }

    public int getCommonsNumber() {
        return commonsNumber;
    }

    public TaleSolitaire setCommonsNumber(int commonsNumber) {
        this.commonsNumber = commonsNumber;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public TaleSolitaire setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public List<String> getCommons() {
        return commons;
    }

    public TaleSolitaire setCommons(List<String> commons) {
        this.commons = commons;
        return this;
    }

    public String getFirstTale() {
        return firstTale;
    }

    public TaleSolitaire setFirstTale(String firstTale) {
        this.firstTale = firstTale;
        return this;
    }

    public String getLastTale() {
        return lastTale;
    }

    public TaleSolitaire setLastTale(String lastTale) {
        this.lastTale = lastTale;
        return this;
    }

    public List<String> getNextTale() {
        return nextTale;
    }

    public TaleSolitaire setNextTale(List<String> nextTale) {
        this.nextTale = nextTale;
        return this;
    }
}
