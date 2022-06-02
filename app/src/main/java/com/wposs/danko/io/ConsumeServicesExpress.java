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
                call = ApiAdapter.getApiService().getECHOTEST("test:'test'");
                break;

            case Defines.USER_URL:
                call = ApiAdapter.getApiService().getUser(ModelInterface.user);
                break;

            case Defines.PARAMS_CATEGORIES:
                call = ApiAdapter.getApiService().getCategories(ModelInterface.user);
                break;

        }
        System.out.println(":::REQUEST:::"+call.request().body());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    if(response.code() == 200) {
                        jsonResponse.setJsonObjetResponse(response.body());
                        if (jsonResponse.getJsonObjetResponse().get("data") != null && !jsonResponse.getJsonObjetResponse().get("error").getAsBoolean()) {
                            jsonResponse.setJsonObjetResponse(jsonResponse.getJsonObjetResponse().get("data").getAsJsonObject());
                        } else {
                            if (jsonResponse.getJsonObjetResponse().get("message") == null)
                                jsonResponse.setJsonObjetResponse(null);
                        }
                        call.cancel();
                        interfaceOnresponse.finish_consumer_services(jsonResponse);
                    }else
                        interfaceOnresponse.finish_fail_consumer_services();
                }else
                    interfaceOnresponse.finish_fail_consumer_services();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                interfaceOnresponse.finish_fail_consumer_services();
            }
        });

    }


    @Override
    public boolean finish_consumer_services(JsonResponse jsonResponse) {
        return true;
    }

    @Override
    public boolean finish_fail_consumer_services() {
        return false;
    }
}
