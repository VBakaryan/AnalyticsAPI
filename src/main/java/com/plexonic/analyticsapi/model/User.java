package com.plexonic.analyticsapi.model;

import java.time.LocalDateTime;


public class User {

    private String userID;
    private LocalDateTime installDate;

    public String getUserID() {
        return userID;
    }

    public User setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public LocalDateTime getInstallDate() {
        return installDate;
    }

    public User setInstallDate(LocalDateTime installDate) {
        this.installDate = installDate;
        return this;
    }


    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", installDate=" + installDate +
                '}';
    }
}
