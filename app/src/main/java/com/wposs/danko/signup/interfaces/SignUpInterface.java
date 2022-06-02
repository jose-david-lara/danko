package com.wposs.danko.signup.interfaces;

import com.google.gson.JsonObject;
import com.wposs.danko.login.dto.UserDTO;
import com.wposs.danko.signup.dto.UserCreateDTO;
import com.wposs.danko.utils.Defines;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpInterface {

    @POST(Defines.CREATE_USER)
    Call<JsonObject> getCreateUser(@Body UserCreateDTO userCreateDTO);

}
