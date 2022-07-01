package com.wposs.danko.parameters.repository;

import android.content.Context;

import com.wposs.danko.db.create.DBTables;
import com.wposs.danko.db.repository.DBRepository;
import com.wposs.danko.parameters.dto.CityDTO;
import com.wposs.danko.parameters.dto.CountryDTO;
import com.wposs.danko.parameters.interfaces.ParametersInterface;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParametersRepository {

    private static ParametersRepository instance;
    private final ParametersInterface parametersInterface;

    public static ParametersRepository getInstance(){
        if(instance == null){
            instance = new ParametersRepository();
        }
        return instance;
    }

    public ParametersRepository(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.25:13012/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        parametersInterface = retrofit.create(ParametersInterface.class);

    }

    public ParametersInterface getParametersInterface() throws Exception {
        return parametersInterface;
    }

    public Map<String, Object> getUserInfoRepository(String email, Context context) throws Exception{
        return new DBRepository().getUserInfo(email,context);
    }

    public int saveLocationsDefaultRepository(CountryDTO countryDTO, Context context) throws Exception{
        return (int) new DBRepository().insertLocationsInfo(countryDTO,context);
    }

    public int saveLocationsDefaultCitiesRepository(CityDTO cityDTO, Context context) throws Exception{
        return (int) new DBRepository().insertLocationsCitiesInfo(cityDTO,context);
    }

    public void deleteTableCountry(Context context){
        new DBRepository().isDeleteTable(DBTables.DANKO_COUNTRY.TABLE_NAME,context);
    }

    public void deleteTableCity(Context context){
        new DBRepository().isDeleteTable(DBTables.DANKO_CITY.TABLE_NAME,context);
    }
}
