package com.wposs.danko.login.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoriasDTO {

    private String id;
    private String name;
    private String description;
    private String img;
    private ArrayList<BusinessDTO> businessDTOList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<BusinessDTO> getBusinessDTOList() {
        return businessDTOList;
    }

    public void setBusinessDTOList(ArrayList<BusinessDTO> businessDTOList) {
        this.businessDTOList = businessDTOList;
    }

}
