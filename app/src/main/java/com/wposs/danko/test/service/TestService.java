package com.wposs.danko.test.service;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.test.dto.TestDTO;
import com.wposs.danko.test.repository.TestRepository;
import com.wposs.danko.utils.Defines;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class TestService {

    private TestRepository testRepository;
    private String responseLocal = "";

    public String getTestService(Context context){


        TestDTO testDTO = new TestDTO();
        testRepository = testRepository.getInstance();
        testDTO.setTest("test");

        testRepository.getTestInterface().getEchoTest(testDTO).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        OnResponseInterface.jsonResponse.setJsonObjetResponse(response.body());
                        if (!OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean()) {
                            responseLocal = OnResponseInterface.jsonResponse.getJsonObjetResponse().get("message").getAsString();
                            Toast.makeText(context, responseLocal, Toast.LENGTH_SHORT).show();
                        } else {
                            if (OnResponseInterface.jsonResponse.getJsonObjetResponse().get("message") != null)
                                responseLocal = OnResponseInterface.jsonResponse.getJsonObjetResponse().get("message").getAsString();
                            Toast.makeText(context, responseLocal, Toast.LENGTH_SHORT).show();
                        }
                        call.cancel();
                    }else{
                        responseLocal = "respuesta diferente a 200";
                    }
                }

            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(context, "::::Error TEST:::: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                responseLocal = "servicio fallo";
            }
        });
        return responseLocal;
    }

}
