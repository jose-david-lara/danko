package com.wposs.danko.login.service;

import android.content.Context;
import android.graphics.ColorSpace;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.wposs.danko.db.create.DBTables;
import com.wposs.danko.interfaces.ModelInterface;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.login.dto.UserDTO;
import com.wposs.danko.login.repository.LoginRepository;
import com.wposs.danko.login.view.ActivityLogin;
import com.wposs.danko.test.dto.TestDTO;
import com.wposs.danko.test.repository.TestRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

    private Map<String, Object> responseLocal;

    public void getUserService(Context context, UserDTO userDTO, ActivityLogin activityLogin) throws Exception{

        LoginRepository loginRepository = LoginRepository.getInstance();
        responseLocal = new HashMap<>();

        loginRepository.getLoginInterface().getUser(userDTO).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        OnResponseInterface.jsonResponse.setJsonObjetResponse(response.body());
                        if (OnResponseInterface.jsonResponse.getJsonObjetResponse().get("data") != null && !OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean()) {
                            OnResponseInterface.jsonResponse.setJsonResponseData(OnResponseInterface.jsonResponse.getJsonObjetResponse().get("data").getAsJsonObject());
                            System.out.println(":::USERNAME:::"+OnResponseInterface.jsonResponse.getJsonResponseData().get("username"));
                            System.out.println(":::DEVICE:::"+OnResponseInterface.jsonResponse.getJsonResponseData().get("device"));
                            System.out.println(":::MESSAGE:::"+OnResponseInterface.jsonResponse.getJsonResponseData().get("message"));
                            System.out.println(":::ACCESS:::"+OnResponseInterface.jsonResponse.getJsonResponseData().get("access"));
                            try {
                                responseLocal.put("email",userDTO.getUser());
                                responseLocal.put("access",OnResponseInterface.jsonResponse.getJsonResponseData().get("access").getAsString());
                                responseLocal.put("name",OnResponseInterface.jsonResponse.getJsonResponseData().get("username").getAsString());
                                responseLocal.put("last_name",OnResponseInterface.jsonResponse.getJsonResponseData().get("username").getAsString());
                                responseLocal.put("url_photo",OnResponseInterface.jsonResponse.getJsonResponseData().get("username").getAsString());
                                responseLocal.put("jwt_feed",OnResponseInterface.jsonResponse.getJsonResponseData().get("username").getAsString());
                                responseLocal.put("type_user",userDTO.getUser_app());
                                responseLocal.put("status","1");
                                System.out.println("::::GUARDAR INFORMACION::::"+responseLocal.toString());
                                new LoginRepository().saveDataUserRepository(responseLocal,context);
                                ModelInterface.user.setUser(userDTO.getUser());
                                ModelInterface.user.setUser_app(userDTO.getUser_app());
                                responseLocal.clear();
                                responseLocal.put("message",OnResponseInterface.jsonResponse.getJsonResponseData().get("message").getAsString()) ;
                                responseLocal.put("error",OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean());
                            } catch (Exception e) {
                                e.printStackTrace();
                                responseLocal.put("message","servicio fallo");
                                activityLogin.responseAuthenticateUser(responseLocal);
                            }

                        } else {
                            responseLocal.put("message", OnResponseInterface.jsonResponse.getJsonObjetResponse().get("message").getAsString());
                            responseLocal.put("error",OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean());
                        }

                    }else{
                        responseLocal.put("message","respuesta diferente a 200");
                    }
                    call.cancel();
                    activityLogin.responseAuthenticateUser(responseLocal);
                }

            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                responseLocal.put("message","servicio fallo");
                activityLogin.responseAuthenticateUser(responseLocal);
            }
        });
    }

}
