package com.wposs.danko.dto;

public class UserModel {

    private String user_app;
    private String user;
    private String name_interface_connect;

    public String getUser_app() {
        return user_app;
    }

    public void setUser_app(String user_app) {
        this.user_app = user_app;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName_interface_connect() {
        return name_interface_connect;
    }

    public void setName_interface_connect(String name_interface_connect) {
        this.name_interface_connect = name_interface_connect;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_app='" + user_app + '\'' +
                ", user='" + user + '\'' +
                ", name_interface_connect='" + name_interface_connect + '\'' +
                '}';
    }
}
