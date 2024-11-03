package com.sthe.jobapplicationtracker.domain;

import java.util.Date;

public class Application {
    private Integer applicationId;
    private Integer userId;
    private String company;
    private String role;
    private String status;
    private String link;
    private Date dateOfApplication;
    private String notes;

    public Application(Integer applicationId, Integer userId, String company, String role,
                       String status, String link, Date dateOfApplication, String notes) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.company = company;
        this.role = role;
        this.status = status;
        this.link = link;
        this.dateOfApplication = dateOfApplication;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", userId=" + userId +
                ", company='" + company + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", link='" + link + '\'' +
                ", dateOfApplication=" + dateOfApplication +
                ", notes='" + notes + '\'' +
                '}';
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(Date dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}


