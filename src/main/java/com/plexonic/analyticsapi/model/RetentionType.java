package com.plexonic.analyticsapi.model;


public enum RetentionType {

    DAY1(1, "Day1"),
    DAY7(7, "Day7"),
    DAY40(40, "Day40");

    private final int value;
    private final String type;

    RetentionType(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
