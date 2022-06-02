package com.wposs.danko.login.dto;

public class UserDTO {

    private String user;
    private String user_app;
    private String password;
    private String device;
    private String version;
    private String ip;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser_app() {
        return user_app;
    }

    public void setUser_app(String user_app) {
        this.user_app = user_app;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "user='" + user + '\'' +
                ", user_app='" + user_app + '\'' +
                ", password='" + password + '\'' +
                ", device='" + device + '\'' +
                ", version='" + version + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
