package com.wposs.danko.login.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginDTO extends ArrayList<String> implements Serializable {

    private String response;
    private ArrayList<CategoriasDTO> categorias;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<CategoriasDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<CategoriasDTO> categorias) {
        this.categorias = categorias;
    }

}
