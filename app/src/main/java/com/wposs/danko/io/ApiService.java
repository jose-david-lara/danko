package com.wposs.danko.io;

import com.google.gson.JsonObject;
import com.wposs.danko.utils.Defines;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers({
            "Content-Type:application/json"
    })
    @POST(Defines.ECHO_TEST)
    Call<JsonObject> getECHOTEST( @Body String test);

    @POST(Defines.PARAMS_URL)
    Call<JsonObject> getDataParans(@Body String device);

    @POST(Defines.USER_URL)
    Call<JsonObject> getUser(@Body Object UserModel);

    @POST(Defines.PARAMS_CATEGORIES)
    Call<JsonObject> getCategories(@Body Object UserModel);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @GET("search/{body}")
    Call<JsonObject> login(@Body RequestBody body);

}
