package com.wposs.danko.io;


import android.content.Context;

import com.google.gson.JsonObject;
import com.wposs.danko.interfaces.ModelInterface;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.model.JsonResponse;
import com.wposs.danko.utils.Defines;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsumeServicesExpress implements OnResponseInterface {

    JSONObject jsonObject;


    public void consume_api(String typeServices, OnResponseInterface interfaceOnresponse){
        Call<JsonObject> call = null;
        switch (typeServices){

            case Defines.ECHO_TEST:
                call = ApiAdapter.getApiService().getECHOTEST("ECHOTEST");
                break;

            case Defines.USER_URL:
                call = ApiAdapter.getApiService().getUser(ModelInterface.user);
                break;

        }
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    jsonResponse.setJsonObjetResponse(response.body());
                    if(jsonResponse.getJsonObjetResponse().get("model") != null) {
                        jsonResponse.setJsonObjetResponse(jsonResponse.getJsonObjetResponse().get("model").getAsJsonObject());
                    }else{
                        if(jsonResponse.getJsonObjetResponse().get("msgError") == null)
                            jsonResponse.setJsonObjetResponse(null);
                    }
                    call.cancel();
                    interfaceOnresponse.finish_consumer_services(jsonResponse);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                interfaceOnresponse.finish_fail_consumer_services();
            }
        });

    }


    @Override
    public void finish_consumer_services(JsonResponse jsonResponse) {

    }

    @Override
    public void finish_fail_consumer_services() {

    }
}
