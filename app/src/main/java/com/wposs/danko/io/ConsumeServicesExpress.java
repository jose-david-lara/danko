package com.wposs.danko.io;


import android.content.Context;

import com.google.gson.JsonObject;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.model.JsonResponse;
import com.wposs.danko.utils.Defines;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsumeServicesExpress implements OnResponseInterface {

    JSONObject jsonObject;

    public ConsumeServicesExpress() {
    }

    public ConsumeServicesExpress(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void consume_api(int typeServices, OnResponseInterface interfaceOnresponse){
        Call<JsonObject> call = null;
        switch (typeServices){

            case Defines.ECHOTEST:
                call = ApiAdapter.getApiService().getECHOTEST("ECHOTEST");
                break;

            case Defines.PARAMETERS:
                call = ApiAdapter.getApiService().getDataParans(null);
                break;

            case Defines.LOGIN:
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Defines.LOGIN_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
                ApiService apiAdapter = retrofit.create(ApiService.class);
                call = apiAdapter.login(body);
                break;

        }
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    jsonResponse.setJsonObjetResponse(response.body());
                    if(typeServices != Defines.ECHOTEST) {
                        if (!jsonResponse.getJsonObjetResponse().get("error").getAsBoolean()) {
                            jsonResponse.setJsonResponseData(jsonResponse.getJsonObjetResponse().get("data").getAsJsonArray());
                        }
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
