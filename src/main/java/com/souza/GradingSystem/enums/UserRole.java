package com.souza.GradingSystem.enums;

public enum UserRole {
    TEACHER("teacher"),
    STUDENT("student");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
