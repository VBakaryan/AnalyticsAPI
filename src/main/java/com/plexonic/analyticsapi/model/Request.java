package com.plexonic.analyticsapi.model;

import java.time.LocalDateTime;


public class Request {

    private String userID;
    private LocalDateTime requestDate;

    public String getUserID() {
        return userID;
    }

    public Request setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public Request setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
        return this;
    }
}
