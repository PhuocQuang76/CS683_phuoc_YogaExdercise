package com.androiddev.termproject.dto;

import java.io.Serializable;

public class Favorite implements Serializable {
    private int mId;
    private int mRoutineTime;
    private int mRoutineId;
    private String mTitle;
    private String mSummary;
    private String mLinks;
    private int mImageId;
    private int mTime;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getRoutineTime() {
        return mRoutineTime;
    }

    public void setRoutineTime(int routineTime) {
        mRoutineTime = routineTime;
    }

    public int getRoutineId() {
        return mRoutineId;
    }

    public void setRoutineId(int routineId) {
        mRoutineId = routineId;
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
}
