package com.example.talendar.data.bean;

import java.util.List;

public class Creation {
    String creationId;
    String title;
    String content;
    String date;
    String type;
    String authorName;
    List<String> tags;

    public Creation(String creationId, String title, String content, String date, String type, List<String> tags) {
        this.creationId = creationId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
        this.tags = tags;
    }

    public Creation(String creationId, String title, String content, String date, String type, String authorName, List<String> tags) {
        this.creationId = creationId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
        this.authorName = authorName;
        this.tags = tags;
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

    public String getType() {
        return type;
    }

    public List<String> getTags() {
        return tags;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCreationId() {
        return creationId;
    }

    public void setCreationId(String creationId) {
        this.creationId = creationId;
    }
}
