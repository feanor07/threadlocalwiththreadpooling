package com.dilek.alptug.threadlocalwiththreadpooling.model;

public enum Title {
    BACKEND_DEVELOPER("Backend Developer"), FRONTEND_DEVELOPER("Frontend Developer"), FULLSTACK_DEVELOPER("Fullstack Developer");

    private String label;

    Title(String label) {
        this.label = label;
    }
}
