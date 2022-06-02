package com.wposs.danko.signup.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.login.dto.UserDTO;
import com.wposs.danko.login.repository.LoginRepository;
import com.wposs.danko.signup.dto.UserCreateDTO;
import com.wposs.danko.signup.repository.SignUpRepository;
import com.wposs.danko.signup.view.dto.ActivitySignUp;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpService {

    private SignUpRepository signUpRepository;
    private Map<String, Object> responseLocal;

    public void getSignUpUser(Context context, UserCreateDTO userCreateDTO, ActivitySignUp activitySignUp){

        responseLocal = new HashMap<>();
        signUpRepository = signUpRepository.getInstance();

        signUpRepository.getSignUpInterface().getCreateUser(userCreateDTO).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        OnResponseInterface.jsonResponse.setJsonObjetResponse(response.body());
                        if (OnResponseInterface.jsonResponse.getJsonObjetResponse().get("data") != null && !OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean()) {
                            OnResponseInterface.jsonResponse.setJsonResponseData(OnResponseInterface.jsonResponse.getJsonObjetResponse().get("data").getAsJsonObject());
                            responseLocal.put("message",OnResponseInterface.jsonResponse.getJsonResponseData().get("message").getAsString()) ;
                            responseLocal.put("error",OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean());
                        } else {
                            responseLocal.put("message", OnResponseInterface.jsonResponse.getJsonObjetResponse().get("message").getAsString());
                            responseLocal.put("error",OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean());
                        }
                        call.cancel();
                    }else{
                        responseLocal.put("message","respuesta diferente a 200");
                    }
                }
                activitySignUp.responseCreateUser(responseLocal);

            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                responseLocal.put("message","servicio fallo");
                activitySignUp.responseCreateUser(responseLocal);
            }
        });
    }

}
