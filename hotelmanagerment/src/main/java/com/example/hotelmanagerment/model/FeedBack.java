package com.example.hotelmanagerment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class FeedBack {
    @Id()
    private int feedBackId;
    private int userId;
    private String feedBackDate;
    private String feedBackContent;
    private boolean isHandle;
    private String userName;

    public FeedBack() {
    }

    public FeedBack(int feedBackId, int userId, String feedBackDate, String feedBackContent, boolean isHandle, String userName) {
        this.feedBackId = feedBackId;
        this.userId = userId;
        this.feedBackDate = feedBackDate;
        this.feedBackContent = feedBackContent;
        this.isHandle = isHandle;
        this.userName = userName;
    }

    public int getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(int feedBackId) {
        this.feedBackId = feedBackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFeedBackDate() {
        return feedBackDate;
    }

    public void setFeedBackDate(String feedBackDate) {
        this.feedBackDate = feedBackDate;
    }

    public String getFeedBackContent() {
        return feedBackContent;
    }

    public void setFeedBackContent(String feedBackContent) {
        this.feedBackContent = feedBackContent;
    }

    public boolean isHandle() {
        return isHandle;
    }

    public void setHandle(boolean handle) {
        isHandle = handle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
