package com.wposs.danko.parameters.dto;

import java.util.List;

public class CountryDTO {

    private String idCountry;
    private String name;
    private String description;
    private String latitud;
    private String longitud;
    private String image;
    private List<CityDTO> cities;

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
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

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<CityDTO> getCities() {
        return cities;
    }

    public void setCities(List<CityDTO> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "idCountry='" + idCountry + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", image='" + image + '\'' +
                ", cities=" + cities +
                '}';
    }
}
