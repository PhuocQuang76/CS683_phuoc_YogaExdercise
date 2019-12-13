package com.androiddev.termproject.dto;

public class Yoga {

    private int mId;
    private String mTitle;
    private String mSummary;
    private String mLinks;
    private int mImageId;
    private int mTime;
    private int mFavorite;



    public Yoga() {
        super();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getLinks() {
        return mLinks;
    }

    public void setLinks(String links) {
        mLinks = links;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }

    public int getTime() {
        return mTime;
    }

    public void setTime(int time) {
        mTime = time;
    }

    public int getFavorite() {
        return mFavorite;
    }

    public void setFavorite(int favorite) {
        mFavorite = favorite;
    }


}
