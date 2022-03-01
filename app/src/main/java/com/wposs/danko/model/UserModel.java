package com.wposs.danko.model;

public class UserModel {

    private String user;
    private String pass;
    private String idDevice;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", idDevice='" + idDevice + '\'' +
                '}';
    }
}
