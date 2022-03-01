package com.wposs.danko.io;


import android.content.Context;

import com.google.gson.JsonObject;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.utils.Defines;

import java.io.ObjectInputStream;
import java.io.ObjectStreamField;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsumeServicesExpress implements OnResponseInterface {


    public void consume_api(int typeServices, OnResponseInterface interfaceOnresponse){
        Call<JsonObject> call = null;
        switch (typeServices){

            case Defines.ECHOTEST:
                call = ApiAdapter.getApiService().getECHOTEST("ECHOTEST");
                break;

            case Defines.PARAMETERS:
                call = ApiAdapter.getApiService().getDataParans(null);
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
                    interfaceOnresponse.finish_consumer_services();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                interfaceOnresponse.finish_fail_consumer_services();
            }
        });

    }

    @Override
    public void finish_consumer_services() {

    }

    @Override
    public void finish_fail_consumer_services() {

    }
}
