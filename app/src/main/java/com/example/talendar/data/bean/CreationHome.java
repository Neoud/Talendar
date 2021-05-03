package com.example.talendar.data.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class CreationHome implements Parcelable {
    private Bitmap profile;
    private String creationId;
    private String nickname;
    private String date;
    private String title;
    private String content;
    private int commentNum;
    private int fansNum;
    private int creationType;

    public CreationHome(){}

    public CreationHome(Bitmap profile, String creationId, String nickname, String date, String title, String content, int commentNum, int fansNum) {
        this.profile = profile;
        this.creationId = creationId;
        this.nickname = nickname;
        this.date = date;
        this.title = title;
        this.content = content;
        this.commentNum = commentNum;
        this.fansNum = fansNum;
    }

    public CreationHome(String nickname, String date, String title, String content, int commentNum, int fansNum) {
        this.nickname = nickname;
        this.date = date;
        this.title = title;
        this.content = content;
        this.commentNum = commentNum;
        this.fansNum = fansNum;
    }

    /**
     * @description 测试使用
     * @param 
     * @return 
     * @author Pontus
     * @date 2021/4/29 1:33
     */
    public CreationHome(Bitmap profile, String creationId, String nickname, String date, String title, String content, int commentNum, int fansNum, int creationType) {
        this.profile = profile;
        this.creationId = creationId;
        this.nickname = nickname;
        this.date = date;
        this.title = title;
        this.content = content;
        this.commentNum = commentNum;
        this.fansNum = fansNum;
        this.creationType = creationType;
    }

    /**
     * @description 测试使用
     * @param 
     * @return 
     * @author Pontus
     * @date 2021/4/29 1:48
     */
    public CreationHome(String nickname, String date, String title, String content, int commentNum, int fansNum, int creationType) {
        this.nickname = nickname;
        this.date = date;
        this.title = title;
        this.content = content;
        this.commentNum = commentNum;
        this.fansNum = fansNum;
        this.creationType = creationType;
    }

    public Bitmap getProfile() {
        return profile;
    }

    public void setProfile(Bitmap profile) {
        this.profile = profile;
    }

    public String getCreationId() {
        return creationId;
    }

    public void setCreationId(String creationId) {
        this.creationId = creationId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getCreationType() {
        return creationType;
    }

    public void setCreationType(int creationType) {
        this.creationType = creationType;
    }

    public static final Creator<CreationHome> CREATOR = new Creator<CreationHome>() {
        @Override
        public CreationHome createFromParcel(Parcel source) {
            CreationHome creationHome = new CreationHome();
            creationHome.creationId = source.readString();
            creationHome.nickname = source.readString();
            creationHome.date = source.readString();
            creationHome.title = source.readString();
            creationHome.content = source.readString();
            creationHome.commentNum = source.readInt();
            creationHome.fansNum = source.readInt();
            creationHome.creationType = source.readInt();
            return creationHome;
        }

        @Override
        public CreationHome[] newArray(int size) {
            return new CreationHome[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(creationId);
        dest.writeString(nickname);
        dest.writeString(date);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(commentNum);
        dest.writeInt(fansNum);
        dest.writeInt(creationType);
    }
}
