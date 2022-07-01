package com.wposs.danko.home.repository;

import android.content.Context;

import com.wposs.danko.db.repository.DBRepository;
import com.wposs.danko.parameters.dto.CityDTO;
import com.wposs.danko.parameters.dto.CountryDTO;

import java.util.List;

public class HomeRepository {

    private static HomeRepository instance;

    public static HomeRepository getInstance(){
        if(instance == null){
            instance = new HomeRepository();
        }
        return instance;
    }

    public List<CountryDTO> getLocationsDefaultRepository (Context context){

        return new DBRepository().getLocationsDefault(context);

    }

    public List<CityDTO> getLocationsDefaultCitiesRepository (Context context){

        return new DBRepository().getLocationsCitiesDefault(context);

    }

}
