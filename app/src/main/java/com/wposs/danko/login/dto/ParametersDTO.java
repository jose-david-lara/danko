package com.wposs.danko.login.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParametersDTO  implements Serializable {

    private List<CategoriasDTO> parameters;

    public List<CategoriasDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<CategoriasDTO> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "ParametersDTO{" +
                "parameters=" + parameters +
                '}';
    }
}
