package com.wposs.danko.login.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoriasDTO {

    private String idCategorie;
    private String name;
    private String description;
    private String img;
    private List<BusinessDTO> business;

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<BusinessDTO> getBusiness() {
        return business;
    }

    public void setBusiness(List<BusinessDTO> business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "CategoriasDTO{" +
                "idCategorie='" + idCategorie + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", business=" + business +
                '}';
    }
}
