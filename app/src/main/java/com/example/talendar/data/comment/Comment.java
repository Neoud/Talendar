package com.example.talendar.data.comment;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject {
    private String author;
    private String authorName;
    private String content;
    private String creationId;
    private int creationType;
    private int fansNumber;

    public String getAuthor() {
        return author;
    }

    public Comment setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Comment setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public String getCreationId() {
        return creationId;
    }

    public Comment setCreationId(String creationId) {
        this.creationId = creationId;
        return this;
    }

    public int getCreationType() {
        return creationType;
    }

    public Comment setCreationType(int creationType) {
        this.creationType = creationType;
        return this;
    }

    public int getFansNumber() {
        return fansNumber;
    }

    public Comment setFansNumber(int fansNumber) {
        this.fansNumber = fansNumber;
        return this;
    }
}
