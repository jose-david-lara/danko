package com.wposs.danko.model;

public class UserModel {

    private String user_app;
    private String user;
    private String password;
    private String device;
    private String version;
    private String ip;
    private String nameInterfaceConnect;
    private Boolean redConnect;

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

    public String getNameInterfaceConnect() {
        return nameInterfaceConnect;
    }

    public void setNameInterfaceConnect(String nameInterfaceConnect) {
        this.nameInterfaceConnect = nameInterfaceConnect;
    }

    public Boolean getRedConnect() {
        return redConnect;
    }

    public void setRedConnect(Boolean redConnect) {
        this.redConnect = redConnect;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_app='" + user_app + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", device='" + device + '\'' +
                ", version='" + version + '\'' +
                ", ip='" + ip + '\'' +
                ", nameInterfaceConnect='" + nameInterfaceConnect + '\'' +
                ", redConnect=" + redConnect +
                '}';
    }
}
