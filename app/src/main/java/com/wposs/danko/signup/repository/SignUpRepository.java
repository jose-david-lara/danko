package com.wposs.danko.signup.repository;

import com.wposs.danko.login.interfaces.LoginInterface;
import com.wposs.danko.login.repository.LoginRepository;
import com.wposs.danko.signup.interfaces.SignUpInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpRepository {

    private static SignUpRepository instance;

    private SignUpInterface signUpInterface;

    public static SignUpRepository getInstance() {
        if (instance == null) {
            instance = new SignUpRepository();
        }
        return instance;
    }

    public SignUpRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.25:13012/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        signUpInterface = retrofit.create(SignUpInterface.class);
    }

    public SignUpInterface getSignUpInterface() {
        return signUpInterface;
    }
}
