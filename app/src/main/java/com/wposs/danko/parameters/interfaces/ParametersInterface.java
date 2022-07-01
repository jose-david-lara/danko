package com.wposs.danko.parameters.interfaces;

import com.google.gson.JsonObject;
import com.wposs.danko.login.dto.UserDTO;
import com.wposs.danko.utils.Defines;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ParametersInterface {

    @POST(Defines.PARAMS_LOCATIONS_DEFAULT)
    Call<JsonObject> getLocationsDefault(@Body Map<String, Object> getLocationsDefault);

    @POST(Defines.PARAMS_CATEGORIES)
    Call<JsonObject> getCategoriesDefault(@Body Map<String, Object> getCategoriesDefault);

}
