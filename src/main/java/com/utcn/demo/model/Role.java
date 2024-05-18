package com.utcn.demo.model;

public enum Role {
    USER("User"),
    ADMIN("Admin");

    private final String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

