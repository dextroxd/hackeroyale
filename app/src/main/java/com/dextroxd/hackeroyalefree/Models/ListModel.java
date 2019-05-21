package com.dextroxd.hackeroyalefree.Models;

public class ListModel
{
    private String mTitle;
    private String mImage;
    private String readTime;
    private String text1;
    private String desc1;
    private boolean starred = false,read1=false;
    public ListModel(String mTitle, String mImage, String readTime, String text1,String desc1) {
        this.mTitle = mTitle;
        this.mImage = mImage;
        this.readTime = readTime;
        this.text1 = text1;
        this.desc1 = desc1;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmImage() {
        return mImage;
    }

    public String getReadTime() {
        return readTime;
    }

    public String getText1() {
        return text1;
    }

    public String getDesc1() {
        return desc1;
    }

    public boolean isStarred() {
        return starred;
    }

    public boolean isRead1() {
        return read1;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public void setRead1(boolean read1) {
        this.read1 = read1;
    }
}
