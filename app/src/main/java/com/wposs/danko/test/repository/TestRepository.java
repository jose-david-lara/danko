package com.wposs.danko.test.repository;

import com.wposs.danko.test.interfaces.TestInterface;
import com.wposs.danko.test.service.TestService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class TestRepository {

    private static TestRepository instance;

    private TestInterface testInterface;

    public static TestRepository getInstance() {
        if (instance == null) {
            instance = new TestRepository();
        }
        return instance;
    }

    public TestRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.25:13012/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        testInterface = retrofit.create(TestInterface.class);
    }

    public TestInterface getTestInterface() {
        return testInterface;
    }

}
