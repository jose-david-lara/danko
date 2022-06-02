package com.wposs.danko.login.interfaces;

import com.google.gson.JsonObject;
import com.wposs.danko.login.dto.UserDTO;
import com.wposs.danko.test.dto.TestDTO;
import com.wposs.danko.utils.Defines;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {

    @POST(Defines.USER_URL)
    Call<JsonObject> getUser(@Body UserDTO userDTO);

}
