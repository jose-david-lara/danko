package com.wposs.danko.test.interfaces;

import com.google.gson.JsonObject;
import com.wposs.danko.test.dto.TestDTO;
import com.wposs.danko.utils.Defines;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TestInterface {

    @POST(Defines.ECHO_TEST)
    Call<JsonObject> getEchoTest(@Body TestDTO testDTO);

}
