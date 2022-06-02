package com.wposs.danko.login.repository;

import android.content.Context;

import com.wposs.danko.db.repository.DBRepository;
import com.wposs.danko.login.interfaces.LoginInterface;
import com.wposs.danko.test.interfaces.TestInterface;
import com.wposs.danko.test.repository.TestRepository;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository {

    private static LoginRepository instance;
    private LoginInterface loginInterface;

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public LoginRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.25:13012/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginInterface = retrofit.create(LoginInterface.class);
    }

    public LoginInterface getLoginInterface() {
        return loginInterface;
    }


    public void saveDataUserRepository (Map<String, Object> userData, Context context) throws Exception{

        new DBRepository().insertUserInfo(userData,context);

    }

}
