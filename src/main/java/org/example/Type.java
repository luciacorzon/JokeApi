package org.example;

public enum Type {
    TWOPART("Two-part joke"),
    SINGLE("Single joke");
    private final String description;

    Type(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
