package com.example.talendar.data.user;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobPointer;

public class User extends BmobUser {
    private String nickname;
    private String description;
    private int followNumber;
    private int fansNumber;
    private int level;
    private String sex;
    private String age;
    private String area;
    private String school;
    private String quotes;
    private BmobFile profile;
    private List<String> tales;
    private List<String> taleSolitaires;
    private List<String> posts;

    public String getNickname() {
        return nickname;
    }

    public String getDescription() {
        return description;
    }

    public int getFollowNumber() {
        return followNumber;
    }

    public int getFansNumber() {
        return fansNumber;
    }

    public int getLevel() {
        return level;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getArea() {
        return area;
    }

    public String getSchool() {
        return school;
    }

    public String getQuotes() {
        return quotes;
    }

    public BmobFile getProfile() {
        return profile;
    }

    public List<String> getTales() {
        return tales;
    }

    public List<String> getTaleSolitaires() {
        return taleSolitaires;
    }

    public List<String> getPosts() {
        return posts;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public User setFollowNumber(int followNumber) {
        this.followNumber = followNumber;
        return this;
    }

    public User setFansNumber(int fansNumber) {
        this.fansNumber = fansNumber;
        return this;
    }

    public User setLevel(int level) {
        this.level = level;
        return this;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public User setAge(String age) {
        this.age = age;
        return this;
    }

    public User setArea(String area) {
        this.area = area;
        return this;
    }

    public User setSchool(String school) {
        this.school = school;
        return this;
    }

    public User setQuotes(String quotes) {
        this.quotes = quotes;
        return this;
    }

    public User setProfile(BmobFile profile) {
        this.profile = profile;
        return this;
    }

    public User setTales(List<String> tales) {
        this.tales = tales;
        return this;
    }

    public User setTaleSolitaires(List<String> taleSolitaires) {
        this.taleSolitaires = taleSolitaires;
        return this;
    }

    public User setPosts(List<String> posts) {
        this.posts = posts;
        return this;
    }
}
