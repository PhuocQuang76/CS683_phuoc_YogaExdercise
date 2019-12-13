package com.androiddev.termproject.dto;

import java.util.Date;

public class History {
    private Integer mId;
    private Integer mRoutineId;
    private Long mTime;
    private Integer mDuration;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getRoutineId() {
        return mRoutineId;
    }

    public void setRoutineId(Integer routineId) {
        mRoutineId = routineId;
    }

    public Date getStartDate() {
        return new Date(mTime);
    }

    public void setStartDate(Date startDate) {
        mTime = startDate.getTime();
    }

    public Integer getDuration() {
        return mDuration;
    }

    public void setDuration(Integer duration) {
        mDuration = duration;
    }

    public Long getTime() {
        return mTime;
    }

    public void setTime(Long time) {
        mTime = time;
    }

    public String getDurationInHoursMinutesSeconds() {
        int quotient = mDuration / 1000;
        int seconds = quotient % 60;
        quotient = quotient / 60;
        int minutes =  quotient % 60;
        int hours = quotient;
        StringBuilder builder = new StringBuilder(255);
        if (hours > 0) {
            builder.append(hours + " hrs ");
        }
        if (minutes > 0) {
            builder.append(minutes + " min ");
        }
        if (seconds > 0) {
            builder.append(seconds + " sec");
        }
        return builder.toString().trim();
    }
}
