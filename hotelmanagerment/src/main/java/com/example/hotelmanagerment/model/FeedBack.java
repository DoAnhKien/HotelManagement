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
    private long feedBackDate;
    private String feedBackContent;

    public FeedBack() {
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

    public long getFeedBackDate() {
        return feedBackDate;
    }

    public void setFeedBackDate(long feedBackDate) {
        this.feedBackDate = feedBackDate;
    }

    public String getFeedBackContent() {
        return feedBackContent;
    }

    public void setFeedBackContent(String feedBackContent) {
        this.feedBackContent = feedBackContent;
    }
}