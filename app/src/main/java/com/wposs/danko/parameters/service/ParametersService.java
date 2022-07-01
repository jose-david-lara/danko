package com.wposs.danko.parameters.service;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.parameters.dto.CityDTO;
import com.wposs.danko.parameters.dto.CountryDTO;
import com.wposs.danko.parameters.repository.ParametersRepository;
import com.wposs.danko.parameters.view.ActivityParameters;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParametersService {

    private Map<String, Object>responseLocal;
    private ParametersRepository parametersRepository = ParametersRepository.getInstance();

    public void getLocationsDefaultService(Context context, Map<String,Object> requestLocationsDefault, ActivityParameters activityParameters)throws Exception{

        responseLocal = new HashMap<>();

        parametersRepository.getParametersInterface().getLocationsDefault(requestLocationsDefault).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        OnResponseInterface.jsonResponse.setJsonObjetResponse(response.body());
                        System.out.println(":::REPUESTA DE PARAMETROS LOCATIONS:::"+response.body());
                        if (OnResponseInterface.jsonResponse.getJsonObjetResponse().get("data") != null && !OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean()) {
                            try {
                                OnResponseInterface.jsonResponse.setJsonResponseDataArray(OnResponseInterface.jsonResponse.getJsonObjetResponse().get("data").getAsJsonArray());
                                saveLocationsDefaultService(new Gson().fromJson(OnResponseInterface.jsonResponse.getJsonResponseDataArray().getAsJsonArray().toString(),  new TypeToken<List<CountryDTO>>(){}.getType()), context);

                                responseLocal.put("error",false);
                                activityParameters.responseGetLocationsDefault(responseLocal);
                            }catch (Exception e){
                                e.printStackTrace();
                                responseLocal.put("message","servicio fallo");
                                responseLocal.put("error",true);
                                activityParameters.responseGetLocationsDefault(responseLocal);
                            }

                        }else{
                            responseLocal.put("message", OnResponseInterface.jsonResponse.getJsonObjetResponse().get("message").getAsString());
                            responseLocal.put("error",OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean());
                            activityParameters.responseGetLocationsDefault(responseLocal);
                        }
                    }else{
                        responseLocal.put("message","respuesta diferente a 200");
                        responseLocal.put("error",true);
                        activityParameters.responseGetLocationsDefault(responseLocal);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                responseLocal.put("message","servicio fallo");
                responseLocal.put("error",true);
                activityParameters.responseGetLocationsDefault(responseLocal);
            }
        });


    }

    private void saveLocationsDefaultService(List<CountryDTO> countryDTOList, Context context) throws Exception{

        parametersRepository.deleteTableCountry(context);
        parametersRepository.deleteTableCity(context);
        for(CountryDTO countryDTO: countryDTOList){
            parametersRepository.saveLocationsDefaultRepository(countryDTO,context);
            for(CityDTO cityDTO : countryDTO.getCities()){
                parametersRepository.saveLocationsDefaultCitiesRepository(cityDTO,context);
            }
        }

    }

    public Map<String, Object> getUserInfoService (String email, Context context) throws Exception{
        Map<String, Object> response;
        responseLocal = new HashMap<>();

        response = parametersRepository.getUserInfoRepository(email,context);

        System.out.println("::::INFORMACION DE USUARIO CONSULTADA EN DB:::"+response.toString());

        responseLocal.put("user_app",response.get("TYPE_USER"));
        responseLocal.put("user",response.get("EMAIL"));
        responseLocal.put("token_access",response.get("ACCESS"));
        responseLocal.put("locations","0");

        return  responseLocal;
    }

    public void getLocationsCategorieService(Context context, Map<String,Object> requestCategorieDefault, ActivityParameters activityParameters)throws Exception{

        responseLocal = new HashMap<>();

        parametersRepository.getParametersInterface().getCategoriesDefault(requestCategorieDefault).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        OnResponseInterface.jsonResponse.setJsonObjetResponse(response.body());
                        System.out.println(":::REPUESTA DE PARAMETROS CATEGORIAS:::"+response.body());
                        if (OnResponseInterface.jsonResponse.getJsonObjetResponse().get("data") != null && !OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean()) {
                            try {
                                //OnResponseInterface.jsonResponse.setJsonResponseDataArray(OnResponseInterface.jsonResponse.getJsonObjetResponse().get("data").getAsJsonArray());
                               // saveLocationsDefaultService(new Gson().fromJson(OnResponseInterface.jsonResponse.getJsonResponseDataArray().getAsJsonArray().toString(),  new TypeToken<List<CountryDTO>>(){}.getType()), context);
                                Toast.makeText(context, "Informacion recibida", Toast.LENGTH_LONG).show();
                            }catch (Exception e){
                                call.cancel();
                                e.printStackTrace();
                                responseLocal.put("message","servicio fallo");
                                responseLocal.put("error",true);
                                activityParameters.responseGetLocationsDefault(responseLocal);
                            }

                        }else{
                            responseLocal.put("message", OnResponseInterface.jsonResponse.getJsonObjetResponse().get("message").getAsString());
                            responseLocal.put("error",OnResponseInterface.jsonResponse.getJsonObjetResponse().get("error").getAsBoolean());
                            activityParameters.responseGetLocationsDefault(responseLocal);
                        }
                    }else{
                        responseLocal.put("message","respuesta diferente a 200");
                        responseLocal.put("error",true);
                        activityParameters.responseGetLocationsDefault(responseLocal);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                responseLocal.put("message","servicio fallo");
                responseLocal.put("error",true);
                activityParameters.responseGetLocationsDefault(responseLocal);
            }
        });

    }

}
