package com.example.hotelmanagerment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report")
public class Report {
    @Id()
    private int reportId;
    private int adminId;
    private int userId;
    private String reportCreateDate;
    private String reportHandleDate;
    private String reportContent;
    private String reportResult;
    private boolean reportStatus;

    public Report() {
    }

    public Report(int reportId, int adminId, int userId, String reportCreateDate, String reportHandleDate, String reportContent, String reportResult, boolean reportStatus) {
        this.reportId = reportId;
        this.adminId = adminId;
        this.userId = userId;
        this.reportCreateDate = reportCreateDate;
        this.reportHandleDate = reportHandleDate;
        this.reportContent = reportContent;
        this.reportResult = reportResult;
        this.reportStatus = reportStatus;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReportCreateDate() {
        return reportCreateDate;
    }

    public void setReportCreateDate(String reportCreateDate) {
        this.reportCreateDate = reportCreateDate;
    }

    public String getReportHandleDate() {
        return reportHandleDate;
    }

    public void setReportHandleDate(String reportHandleDate) {
        this.reportHandleDate = reportHandleDate;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getReportResult() {
        return reportResult;
    }

    public void setReportResult(String reportResult) {
        this.reportResult = reportResult;
    }

    public boolean isReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(boolean reportStatus) {
        this.reportStatus = reportStatus;
    }
}
